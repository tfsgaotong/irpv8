package com.tfs.irp.systimdask.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.SysConfigUtil;

public class systemUserDocumentTime implements Job{
	private IrpUserLoveService irpUserLoveService;//猜你喜欢service
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService; //知识中间表service
	private IrpChannelService irpChannelService;
	private SolrService solrService;//solrservice
	private IrpUserService irpUserService;//用户service
	private IrpTagService irpTagService;//标签service
	/**
	 * 利用构造方法初始化参数
	 */
	public systemUserDocumentTime(){
		ApplicationContext wac = ApplicationContextHelper.getContext();
		irpUserLoveService = (IrpUserLoveService) wac.getBean("irpUserLoveService");
		irpChnl_Doc_LinkService = (IrpChnl_Doc_LinkService) wac.getBean("irpChnl_Doc_LinkService");
		solrService = (SolrService) wac.getBean("solrService");
		irpUserService = (IrpUserService) wac.getBean("irpUserService");
		irpTagService = (IrpTagService) wac.getBean("irpTagService");
		irpChannelService = (IrpChannelService) wac.getBean("irpChannelService");
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<IrpUser> sysAllUsers = irpUserService.findAllRegUsers();
		if(sysAllUsers!=null && !sysAllUsers.isEmpty()){
			for (IrpUser irpUser : sysAllUsers) {
				if(irpUser==null)
					continue;
				//判断用户是否是管理员
				for (Long roleId : irpUser.getRoleIds()) {
					if(roleId==null)
						continue;
					if(roleId==1){
						irpUser.setAdministrator(true);
						break;
					}
				}
				long userId = irpUser.getUserid();
				List<IrpTag> irpTags=irpTagService.findTagsByUserId(userId);//获取用户当前所有的标签
				List<String> tagsLogs=irpChnl_Doc_LinkService.selectByUserIdOrderbyCountDescBetweenTime(userId, LogTimeUtil.getLastEndWeek(), new Date());
				String selectKeyStr="";
				//紧接着拼接用户的个人标签
				if(irpTags!=null && irpTags.size()>0){
					for (IrpTag tag : irpTags) {
						String skey=tag.getTagname();
						if(skey!=null &&!"".equals(skey.trim())){
							selectKeyStr+=" "+skey;
						}
					}
				}
				//上周结束到现在的检索记录
				if(tagsLogs!=null && tagsLogs.size()>0){
					for (String log : tagsLogs) {
						if(log!=null && !"".equals(log.trim())){
							selectKeyStr+=" "+log;
						}
					}
				}
				//查询旧数据
				List<IrpUserLove> oldDatas=irpUserLoveService.allData(userId, IrpUserLove.CAI_YOU_LIKE);
				int nCount=0;
				selectKeyStr = selectKeyStr.trim();
				if(!selectKeyStr.isEmpty()){
					nCount=addUserLove(selectKeyStr,irpUser);
				}
				if(nCount==0){
					nCount=addNewDocument(irpUser);
				}
				if(nCount>0){
					irpUserLoveService.deletedoc(oldDatas);//删除旧数据
				}
			}
		}
	}
	
	 /** 猜你喜欢逻辑 */
	public int  addUserLove(String selectKeyStr, IrpUser user){ 
		int count=0;
		try {
			if(selectKeyStr.length()>38){
				selectKeyStr=selectKeyStr.substring(0,37);
			}
			//查询到有权限的所有栏目列表
			
			String searchUrl=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
			List<Long> docIds=solrService.searchIdByStr(selectKeyStr,50,searchUrl);
			if(docIds!=null && !docIds.isEmpty()){
				//从这些知识里面查询有权限的知识
				docIds=irpChnl_Doc_LinkService.findDocidsByDocIdsAndChannelidAndRight(docIds, null, user, IrpDocument.DOCUMENT_NOT_INFORM);
			}
			if(docIds!=null && docIds.size()>0){
				for (Long docid : docIds) {
					if(count>=5){
						return count;
					}
					irpUserLoveService.save(user.getUserid(),docid,IrpUserLove.CAI_YOU_LIKE);//添加新数据
					count++;
				}
			}else{//如果一个猜你喜欢都没有的话，就将 最新的知识推荐给他
				count = addNewDocument(user);
			}
		} catch (Exception e) { e.printStackTrace(); }
		return count;
	}
	
	public int addNewDocument(IrpUser irpUser){
		int count=0;
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("sOrderByClause"," docpubtime desc ");
		if(!irpUser.isAdministrator()){
			List<Long> _arrChannelIds=irpChannelService.seeDocumentListRightChannel(irpUser);
			if(_arrChannelIds==null || _arrChannelIds.isEmpty()){
				return 0;
			}
			map.put("channelidsList", _arrChannelIds);
		}
		List<IrpChnlDocLink> chnlDocLinks=irpChnl_Doc_LinkService.clientShowDoc(map,IrpDocument.DOCUMENT_NOT_INFORM, 5);
		if(chnlDocLinks!=null && chnlDocLinks.size()>0){
			for (IrpChnlDocLink irpChnlDocLink : chnlDocLinks) {
				if(count>=5){
					return count;
				}
				irpUserLoveService.save(irpUser.getUserid(),irpChnlDocLink.getDocid(), IrpUserLove.CAI_YOU_LIKE);
				count++;
			}
		}
 		return count;
	}
}
