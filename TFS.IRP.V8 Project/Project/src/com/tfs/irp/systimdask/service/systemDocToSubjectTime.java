package com.tfs.irp.systimdask.service;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.SysConfigUtil;

public class systemDocToSubjectTime implements Job{
	private IrpUserLoveService irpUserLoveService;//猜你喜欢service
	private IrpTopicService irpTopicService;//专题service
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService; //知识中间表service
	/**
	 * 利用构造方法初始化参数
	 */
	public systemDocToSubjectTime(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpUserLoveService = (IrpUserLoveService) ac.getBean("irpUserLoveService");
		irpChnl_Doc_LinkService = (IrpChnl_Doc_LinkService) ac.getBean("irpChnl_Doc_LinkService");
		irpTopicService =(IrpTopicService) ac.getBean("irpTopicService");
	}

	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}

	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}

	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		List<IrpDocument> allDocumentList=irpChnl_Doc_LinkService.allDatas(null);//所有知识的相关知识
		if(allDocumentList!=null && allDocumentList.size()>0){
			for (IrpDocument irpDocument : allDocumentList) {
				Long docid=irpDocument.getDocid();
				String keyWords=irpDocument.getDockeywords();
				//查询旧数据
				List<IrpUserLove> oldDatas=irpUserLoveService.allData(null, docid, IrpUserLove.XIANG_GUAN_SUBJECT);
				//保存新数据
				collectionSubjectLei(keyWords, docid);
				//删除旧数据
				irpUserLoveService.deletedoc(oldDatas);
			}
		}
	}
	//相关专题逻辑
	public void collectionSubjectLei(String keyWords, Long docid){
		if(keyWords==null){
			return;
		}
		try {
			List<IrpTopic> irpTopicList=new ArrayList<IrpTopic>();
			int aCount=SysConfigUtil.getSysConfigNumValue("XIANG_GAUN_SUBJECT");
			String[] keys=keyWords.split(",");
			for (int i = 0; i < keys.length; i++) {
				String key=keys[i];
				if(key!=null&&key.trim().length()>0){
					String skey="#"+key+"#"; 
					IrpTopic irpTopic=irpTopicService.getIrpTopic(skey);
					if(irpTopic!=null){
						irpTopicList.add(irpTopic);
						if(irpTopicList.size()>=aCount)break;
					}
				}
			}
			if(irpTopicList!=null && irpTopicList.size()>0){
				for (IrpTopic irpTopic : irpTopicList){ 
					irpUserLoveService.save(null, docid, irpTopic.getTopicid(), IrpUserLove.XIANG_GUAN_SUBJECT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
