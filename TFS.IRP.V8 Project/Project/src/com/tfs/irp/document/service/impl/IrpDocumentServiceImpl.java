package com.tfs.irp.document.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedExample; 
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.channel.dao.IrpChannelDAO; 
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.docrecommend.dao.IrpDocrecommendDAO;
import com.tfs.irp.docrecommend.entity.IrpDocrecommendExample;
import com.tfs.irp.document.dao.IrpDocumentDAO;  
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentExample.Criteria;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentcollection.dao.IrpDocumentCollectionDAO;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollectionExample;
import com.tfs.irp.documentmap.dao.IrpDocumentMapDAO;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;
import com.tfs.irp.microblog.dao.IrpMicroblogDAO;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.motetread.dao.IrpMostTreadDAO;
import com.tfs.irp.motetread.entity.IrpMostTread;
import com.tfs.irp.motetread.entity.IrpMostTreadExample;
import com.tfs.irp.opertype.dao.IrpOpertypeDAO; 
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.solr.entity.DocumentForSolr;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.subscribe.service.IrpSubscribeService;
import com.tfs.irp.tag.dao.IrpTagDAO;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpTagExample; 
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil; 
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.workflow.entity.IrpWorkflowNode;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class IrpDocumentServiceImpl implements IrpDocumentService {
	private IrpDocumentDAO irpDocumentDAO;
	private IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO;
	private IrpAttachedService irpAttachedService;
	private IrpAttachedTypeService irpAttachedTypeService;
    private IrpTagDAO irpTagDAO;//标签表dao
    private IrpChannelDAO irpChannelDAO;//栏目dao
    private IrpOpertypeDAO irpOpertypeDAO;//操作类型dao
    private IrpDocumentMapDAO irpDocumentMapDao;
    
    private SolrService solrService;
    
    public IrpDocumentMapDAO getIrpDocumentMapDao() {
		return irpDocumentMapDao;
	}
	public void setIrpDocumentMapDao(IrpDocumentMapDAO irpDocumentMapDao) {
		this.irpDocumentMapDao = irpDocumentMapDao;
	}
	public SolrService getSolrService() {
		return solrService;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}
	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}
	public void setIrpDocumentMapService(
			IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}
	private IrpDocrecommendDAO irpDocrecommendDAO;//评论dao
    private IrpDocumentCollectionDAO irpDocumentCollectionDAO;//收藏dao
    private IrpMostTreadDAO irpMoteTreadDAOImpl;//顶踩dao
    private IrpMicroblogDAO irpMicroblogDAO;//微知dao
    private IrpWorkFlowService irpWorkFlowService;
    private IrpDocumentMapService irpDocumentMapService;//知识地图service
    private IrpSubscribeService irpSubscribeService;
    
    public IrpSubscribeService getIrpSubscribeService() {
		return irpSubscribeService;
	}
	public void setIrpSubscribeService(IrpSubscribeService irpSubscribeService) {
		this.irpSubscribeService = irpSubscribeService;
	}
@Override
	public List<IrpDocumentMap> docDocumentMap(Long docid,String type) {
	  return  irpDocumentMapService.oneDocDocumentMap(docid,type);
		 
	}
    @Override
    public List<Long > selectDocIds(IrpDocumentExample example) {
    	List<Long >  lsit=null;
    	try {
			lsit=irpDocumentDAO.selectDocIdsByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return lsit;
    }
    public IrpWorkFlowService getIrpWorkFlowService() {
		return irpWorkFlowService;
	}
	public void setIrpWorkFlowService(IrpWorkFlowService irpWorkFlowService) {
		this.irpWorkFlowService = irpWorkFlowService;
	}
	public IrpChannelDAO getIrpChannelDAO() {
		return irpChannelDAO;
	}
	public IrpMostTreadDAO getIrpMoteTreadDAOImpl() {
		return irpMoteTreadDAOImpl;
	}
	public void setIrpMoteTreadDAOImpl(IrpMostTreadDAO irpMoteTreadDAOImpl) {
		this.irpMoteTreadDAOImpl = irpMoteTreadDAOImpl;
	}
	public void setIrpChannelDAO(IrpChannelDAO irpChannelDAO) {
		this.irpChannelDAO = irpChannelDAO;
	}
	public IrpOpertypeDAO getIrpOpertypeDAO() {
		return irpOpertypeDAO;
	}
	public void setIrpOpertypeDAO(IrpOpertypeDAO irpOpertypeDAO) {
		this.irpOpertypeDAO = irpOpertypeDAO;
	}
	public IrpMicroblogDAO getIrpMicroblogDAO() {
		return irpMicroblogDAO;
	}
	public void setIrpMicroblogDAO(IrpMicroblogDAO irpMicroblogDAO) {
		this.irpMicroblogDAO = irpMicroblogDAO;
	}
	public IrpDocrecommendDAO getIrpDocrecommendDAO() {
		return irpDocrecommendDAO;
	}
	public void setIrpDocrecommendDAO(IrpDocrecommendDAO irpDocrecommendDAO) {
		this.irpDocrecommendDAO = irpDocrecommendDAO;
	}
	public IrpDocumentCollectionDAO getIrpDocumentCollectionDAO() {
		return irpDocumentCollectionDAO;
	}
	public void setIrpDocumentCollectionDAO(
			IrpDocumentCollectionDAO irpDocumentCollectionDAO) {
		this.irpDocumentCollectionDAO = irpDocumentCollectionDAO;
	}
	@Override
	 public int clientUpdateDocumentChannelId(IrpDocumentWithBLOBs document) {  
			int nCount=0;  
			try { 
				IrpChnlDocLink record=new IrpChnlDocLink();
				IrpChannel channel=irpChannelDAO.selectByPrimaryKey(document.getChannelid());
				record.setChannelid(document.getChannelid());
				record.setDocid(document.getDocid());
				record.setSiteid(channel.getSiteid());
				IrpChnlDocLinkExample example=new IrpChnlDocLinkExample();
				example.createCriteria().andDocidEqualTo(document.getDocid());
				
				irpChnl_Doc_LinkDAO.updateByExampleSelective(record, example);
				document.setSiteid(channel.getSiteid());
				nCount =irpDocumentDAO.updateByPrimaryKeySelective(document);
			} catch (SQLException e) {
				e.printStackTrace();
				nCount=0;
			}
			return nCount; 
	    }
	
	@Override
	public int adminUpdateDocumentChannelId(long _nChannelId, List<Long> _arrDocIds, List<Long> _arrChnlDocIds){
		int nCount=0;
		//获得移动的栏目判断是否存在
		IrpChannel toChannel = null;
		try {
			toChannel=irpChannelDAO.selectByPrimaryKey(_nChannelId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(toChannel==null){
			return 0;
		}
		//修改IrpDocument表中的栏目和站点ID
		IrpDocumentWithBLOBs updateDocument = new IrpDocumentWithBLOBs();
		updateDocument.setChannelid(_nChannelId);
		updateDocument.setSiteid(toChannel.getSiteid());
		try {
			IrpDocumentExample example = new IrpDocumentExample();
			example.createCriteria().andDocidIn(_arrDocIds);
			nCount = irpDocumentDAO.updateByExampleSelective(updateDocument, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//修改IrpChnlDocLink表中的栏目和站点ID
		IrpChnlDocLink updateChnlDoc = new IrpChnlDocLink();
		updateChnlDoc.setChannelid(_nChannelId);
		updateChnlDoc.setSiteid(toChannel.getSiteid());
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			example.createCriteria().andChnldocidIn(_arrChnlDocIds);
			nCount = irpChnl_Doc_LinkDAO.updateByExampleSelective(updateChnlDoc, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	  public int clientUpdateDocument(IrpDocumentWithBLOBs document, String jsonFile,boolean isClient,Long _touChanneild,String[] documentmaps,String[] subjects,String flag) {  
			int nCount=0; 
			try {
				boolean isSubmit = document.getIsdraft()==null && _touChanneild!=null && _touChanneild>0L;
				if(isSubmit){
					document.setIsdraft(_touChanneild);
				}
				nCount = updateDocumentByDocId(document,jsonFile,isClient,_touChanneild,true,documentmaps,subjects,flag);
				if(isSubmit){
					 Long docid=TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);//他的主键值
					 document.setChannelid(_touChanneild);
					 document.setDocoutupid(document.getDocid()); 
					 document.setDocid(docid);
					 addDocument(document, jsonFile,isClient,_touChanneild,false,documentmaps,IrpDocument.DOCTYPE_DOCUMENT); 
					 saveorupdate(subjects,flag,docid);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return nCount; 
	    }
	
	/**
	 * 专题保存or修改
	 */
	public void saveorupdate(String[] subjects,String flag,Long docid){
		try {
			//判断是个人还是企业
	    	List<IrpChannel> channels = null;
	    	if("person".equals(flag)){
	    		//判断是否有个人专题
	        	IrpChannelExample channelExample = new IrpChannelExample();
	        	channelExample.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId())
	        									.andParentidEqualTo(SysConfigUtil.getSysConfigNumValue("DOCUMENT_PERSON_SUBJECT_ID").longValue())
	        									.andSiteidEqualTo(0L);
	        	channels = irpChannelDAO.selectByExample(channelExample);
	        	if(channels!=null && channels.size()>0){
	        		IrpChannel channel = channels.get(0);
	        		channelExample = new IrpChannelExample();
	        		channelExample.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId())
	        										.andParentidEqualTo(channel.getChannelid())
	        										.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
	        		channels = irpChannelDAO.selectByExample(channelExample);
	        		
	        	}
	    	}else{
	        	IrpChannelExample channelExample = new IrpChannelExample();
	        	channelExample.createCriteria().andParentidEqualTo(SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID").longValue())
	        									.andSiteidEqualTo(0L);
	        	channels = irpChannelDAO.selectByExample(channelExample);
	    	}
	    	if(channels!=null && channels.size()>0){
				List<Long> channelidList = new ArrayList<Long>();
				for(IrpChannel ele : channels){
					channelidList.add(ele.getChannelid());
				}
				IrpDocumentMapExample mapExample = new IrpDocumentMapExample();
	    		mapExample.createCriteria().andDocidEqualTo(docid)
	    								   .andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT)
	    								   .andChannelidIn(channelidList);
	    		List<IrpDocumentMap> mapList = irpDocumentMapDao.selectByExample(mapExample);
	    		if(mapList!=null && mapList.size()>0){
	    			//删除之前的,增加现在的
	    			for(IrpDocumentMap ele : mapList){
	    				irpDocumentMapDao.deleteByPrimaryKey(ele.getMid());
	    			}
	    		}
			}
			if(subjects!=null && subjects.length>0){
				IrpDocumentMap newMap = null;
				for(int i = 0;i<subjects.length;i++){
					newMap = new IrpDocumentMap();
					newMap.setMid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
					newMap.setDocid(docid);
					newMap.setChannelid(Long.valueOf(subjects[i]));
					newMap.setCrtime(new Date());
					newMap.setType(IrpDocumentMap.KNOWLEDGE_SUBJECT);
					irpDocumentMapDao.insertSelective(newMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public int clientAddDocument(IrpDocumentWithBLOBs document, String jsonFile,Long _channelid,Long _isTrue,boolean isClient,String[] documentmaps,String[] subjects,String flag) {  
		int nCount=0; 
        try {
        	//判断穿过来的channelid是否有值，有则执行下面的。否则查询他所有的可查看的栏目并且都投稿   
        	Long docid=TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);//他的主键值
        	document.setDocid(docid);
        	document.setIsdraft(_channelid);
        	nCount=addDocument(document, jsonFile,isClient,null,true,documentmaps ,IrpDocument.DOCTYPE_DOCUMENT);//第一次添加是不可以给他有的——channelid的xx
        	Long docid2=0L;
        	if(_channelid!=null && _channelid>0L){//投稿
            	docid2=TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);//他的主键值
            	document.setDocoutupid(document.getDocid());
            	document.setDocid(docid2);
            	document.setChannelid(_channelid);
            	//当前投稿的channle所在的站点id
            	IrpChannel qiYeChannel=irpChannelDAO.selectByPrimaryKey(_channelid);
            	if(qiYeChannel!=null){
            		document.setSiteid(qiYeChannel.getSiteid());
            	}else{
            		return nCount;//若企业中的栏目不存在，就不进行投稿
            	}
            	//由于前台是投稿到投稿栏目中去的，因此需要吧他的状态设置为草稿状态，这样避免进入流程
            	 //document.setDocstatus(IrpDocument.DRAFT_STATUS);//草稿状态
            	
            	 addDocument(document, jsonFile,isClient,_channelid,false,documentmaps,IrpDocument.DOCTYPE_DOCUMENT); 
            }
        	saveorupdate(subjects,flag,"person".equals(flag)?docid:docid2);
            if(nCount==1&&_isTrue!=null&&_isTrue==1L){
               String cValue= SysConfigUtil.getSysConfigValue("DOCUMENT_ADD_BLOG");
                if(cValue!=null && cValue.length()>0){
                	 //发布一条微知
                	IrpUser irpUser=LoginUtil.getLoginUser();
                  
					cValue=cValue.replace("#user#",LoginUtil.getUserNameString(irpUser));//昵称
					
                	Date date=new Date();
                	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                	cValue=cValue.replace("#time#",dateFormat.format(date));
                	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
                	String href="<a href='"+path+"/document/document_detail.action?docid="+docid+"'  class='linkbh14' ><strong>”"+document.getDoctitle()+"“</strong></a>";
                	cValue=cValue.replace("#doctitle#",href );
                	cValue=cValue.replace("#doctitle#", href);
                	cValue=cValue+"<br>";
                    IrpMicroblog irpMicroblog=new IrpMicroblog();
                    irpMicroblog.setMicroblogid(TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME));
                    irpMicroblog.setMicroblogcontent(cValue);
                    irpMicroblog.setUserid(irpUser.getUserid());
                    irpMicroblog.setCrtime(date);
                    irpMicroblog.setBlogtype(IrpMicroblog.PUBLICMICROBLOG);
                    irpMicroblog.setFromdata(IrpMicroblog.FROMDATA); 
                	irpMicroblogDAO.insertSelective(irpMicroblog);
                }
            } 
		} catch (Exception e) {
			e.printStackTrace();
			nCount=0;
		}
    	return nCount;
    }
    /**
     * 图片
     */
    //private String IMGTYPEID="2";//图片 
    /**
     * 文件
     */ 
	@Override
	public int updateDocumentByDocId(IrpDocumentWithBLOBs document ,String jsonFile,boolean isClient,Long _touChannelid,Boolean addUserFileLog,String[] documentmaps,String[] subjects,String flag) {
		if(documentmaps!=null && documentmaps.length>0){
			for(int i = 0;i<documentmaps.length;i++){
				try {
					Long.parseLong(documentmaps[i]);
				} catch (Exception e) {
					documentmaps[i] = null;
					continue;
				}
			}
		}
		int nCount=0;
		LogUtil logUtil=null;
		 try {
			 logUtil=new LogUtil("DOCUMENT_UPDATE",document); 
			 IrpUser irpUser=LoginUtil.getLoginUser();
			 document.setOperuser(irpUser.getUsername());
			 document.setOpertime(new Date()); 
	         //添加标签
			 if(document.getDockeywords()!=null && document.getDockeywords().length()>0){
		         String documentKeyWord=addTag(document.getDockeywords()); 
		         document.setDockeywords(documentKeyWord);  
			 }
	         IrpChannel channel=irpChannelDAO.selectByPrimaryKey(document.getChannelid());
	         document.setSiteid(channel.getSiteid());
	         document.setAttachedcontent(document.getDochtmlcon());
	         	/**
				 * 进行修改知识分类地图
				 */
				//查询，然后删除
				List<IrpDocumentMap> maplist=irpDocumentMapService.oneDocDocumentMap(document.getDocid(),IrpDocumentMap.KONWLEDGE_MAP);
				int defaultScore=SysConfigUtil.getSysConfigNumValue("DEFALUT_ALLSCORE");
				if(documentmaps==null || documentmaps.length==0){
					document.setCompletescore(new Long(defaultScore));
				}else{
					int jiaScore=SysConfigUtil.getSysConfigNumValue("DEFALUT_ONE_SCORE");
					document.setCompletescore(new Long(defaultScore+jiaScore));
					for (int i = 0; i < documentmaps.length; i++) {
						String string=documentmaps[i];
						if(string!=null &&!string.trim().equals("")){
							irpDocumentMapService.addDocumentMap(document.getDocid(),new Long(string),IrpDocumentMap.KONWLEDGE_MAP);
						}
					}
				}
				if(maplist!=null && maplist.size()>0){
					for (int i = 0; i < maplist.size(); i++) {
						IrpDocumentMap map=maplist.get(i);
						irpDocumentMapService.deltetDocumentMap(map.getMid());
					}
				}
	         IrpDocumentWithBLOBs oldDocument=irpDocumentDAO.selectByPrimaryKey(document.getDocid());
	         
	         boolean bWorkFlow = false; 
			if((document.getDocstatus()==null || document.getDocstatus()!=IrpDocument.DRAFT_STATUS) && oldDocument.getDocstatus().longValue()==IrpDocument.PUBLISH_STATUS.longValue()){
			    bWorkFlow = channel!=null && channel.getSchedule()!=null && channel.getSchedule()>0L;
			    if(bWorkFlow){
			    	IrpWorkflowNode flowNode = irpWorkFlowService.findStartFlowNodeByFlowId(channel.getSchedule());
			    	document.setDocstatus(new Long(flowNode.getDocstatus()));
			    }else{
			    	document.setDocstatus(IrpDocument.PUBLISH_STATUS);
			    	document.setDocpubtime(new Date());
			    }
			}
				
			 nCount=irpDocumentDAO.updateByPrimaryKeySelective(document);//修改文档表
			 
			 saveorupdate(subjects,flag,document.getDocid());
			 
			//修改完成后重启工作流程
			if(bWorkFlow){
				irpWorkFlowService.startProcess(channel.getSchedule(), document);
				 //删除solr
				if(document.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID){
					 solrService.deleteSolrIndex(document.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL));
				}else{
					solrService.deleteSolrIndex(document.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE));
				}
			}
			
			if(document.getDocstatus()==null){
				document.setDocstatus(oldDocument.getDocstatus());
			}
			//修改solor
			updateSolrIndex(document);

			 /**添加附件*/
			boolean checkEditversions = false;
			if(jsonFile!=null && jsonFile.length()>0){ 
				ArrayList<Long> _arrAttachedid=new ArrayList<Long>();//装入当前的所有附件id  
				 _arrAttachedid.add(0L);
				JSONArray _Array=  JSONArray.fromObject(jsonFile);  
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject=(JSONObject)_Array.getJSONObject(i);  
					Long _Attachedid=jsonObject.getLong("attachedid"); 
					String _sAttFile=jsonObject.getString("attfile"); 
					String _sAttDesc=jsonObject.getString("attdesc"); 
					String _sAttLinkAlt=jsonObject.getString("attlinkalt"); 
					String _sEditversions=  jsonObject.getString("editversions");
					if(!checkEditversions && _sEditversions!=null && _sEditversions.equals(IrpAttached.IS_FENGMIAN.toString())){
						 checkEditversions = true;
					 }
					String _attflag=jsonObject.getString("attflag"); 
					if( _Attachedid==0L){//添加  
							String _lastName=FileUtil.findFileExt(_sAttFile); //得到后缀名
							Long typeId=irpAttachedTypeService.findAttachedTypeIdByFileExt(_lastName);
			    			if(typeId!=null && typeId.longValue()!=0L){//存在就查看临时表中的文件是否存在    
				    			List<Long> _attids= addAttFile(Integer.parseInt(_attflag),_sAttFile, typeId, document, _sAttDesc, _sAttLinkAlt,_sEditversions,isClient,_touChannelid,addUserFileLog);
				    			_arrAttachedid.addAll( _attids);
			    			}else{//如果没有查到就是其他类型5
			    				List<Long> _attids= addAttFile(Integer.parseInt(_attflag),_sAttFile,IrpAttachedType.ID_FIELD_NAMEOTHER, document, _sAttDesc, _sAttLinkAlt,_sEditversions,isClient,_touChannelid,addUserFileLog);
				    			 _arrAttachedid.addAll( _attids);
			    			}  
					}else{ 
					 irpAttachedService.udpateAttachedByExprt(document.getDocid(), _Attachedid, Integer.parseInt(_sEditversions),Integer.parseInt(_attflag));
				    _arrAttachedid.add(_Attachedid);//加入集合
			       }
				}   
				//删除数据库文件 不在集合中的文件
				irpAttachedService.deleteFileNotInList(_arrAttachedid, document.getDocid(),0);
			} 
				
			if(checkEditversions){
				IrpDocumentWithBLOBs flagDoc = new IrpDocumentWithBLOBs();
				flagDoc.setDocid(document.getDocid());
				flagDoc.setDocflag(0L);
				irpDocumentDAO.updateByPrimaryKeySelective(flagDoc);
				document.setDocflag(0L);
			}
				
			List<Long> chandocids=irpChnl_Doc_LinkDAO.findChanDocIdByDocid(document.getDocid()); 
				if(chandocids!=null &&chandocids.size()>0){
					for (Long chandocid : chandocids) {
						 IrpChnlDocLink link=new IrpChnlDocLink();
						 link.setChnldocid(chandocid);   
						 link.setChannelid(document.getChannelid());
						 link.setDoctitle(document.getDoctitle());
						 link.setDocstatus(document.getDocstatus()); 
						 link.setModal(document.getDoctype()); 
						 link.setSiteid(document.getSiteid());
						 link.setDocflag(document.getDocflag()!=null?document.getDocflag().intValue():0);
						 irpChnl_Doc_LinkDAO.updateByPrimaryKeySelective(link); 
				} 
			} 
			
			//触发订阅，发布私信
			if("qiye".equals(flag) && document.getDocstatus().intValue()==IrpDocument.PUBLISH_STATUS){
				irpSubscribeService.sendSubscribeMessage(document);
			}
			logUtil.successLogs("修改文档["+logUtil.getLogUser()+"],成功");
		} catch (SQLException e) { 
			logUtil.errorLogs("修改文档["+logUtil.getLogUser()+"],失败");
			e.printStackTrace();
		}
		return nCount;
	} 
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}
	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	
	//添加栏目文档中间表
	public int  addChnlDocLink(IrpDocumentWithBLOBs document){
		 IrpChnlDocLink link=new IrpChnlDocLink();
		 Long chnldocid=TableIdUtil.getNextId(IrpChnlDocLink.TABLE_NAME);//获得主键
		 link.setChnldocid(chnldocid);
		 link.setChannelid(document.getChannelid());
		 link.setDocid(document.getDocid()); 
		 link.setDocpubtime(document.getDocpubtime());
		 link.setDoctitle(document.getDoctitle());
		 link.setDocstatus(document.getDocstatus());
		 String cruser = null;
		 Long cruserid = null;
		 if(document.getCruser()!=null && document.getCruserid()!=null){
			 cruser = document.getCruser();
			 cruserid = document.getCruserid();
		 }else{
			 IrpUser irpUser = LoginUtil.getLoginUser();
			 cruser = irpUser.getUsername();
			 cruserid = irpUser.getUserid();
		 }
		 link.setCruser(cruser);
		 link.setCruserid(cruserid);
		 link.setCrtime(document.getCrtime());
		 link.setModal(document.getDoctype()); 
		 link.setCruser(document.getCruser()); 
		 link.setSiteid(document.getSiteid()); 
		 link.setSiteid(document.getSiteid());
		 link.setDockind(document.getDocoutupid());//投稿 知识id
		 link.setCompletescore(document.getCompletescore());//完整度
		 link.setCryscore(document.getCryscore());//系数
		 link.setDocchannel(document.getFlowpreoperationmark());//加精数量
		 link.setDocflag(document.getDocflag()!=null?document.getDocflag().intValue():0);
		 LogUtil logUtil = new LogUtil("DOCUMENT_ADD",link); 
		 int nCount=0;
		 try {
			 //此时需要添加文档简约表
			 irpChnl_Doc_LinkDAO.insertSelective(link);
			 nCount=1;
			 logUtil.successLogs("添加文档["+logUtil.getLogUser()+"],成功");
		} catch (SQLException e) { 
			e.printStackTrace();
			nCount=0;
			logUtil.errorLogs("添加文档["+logUtil.getLogUser()+"],失败"); 
		}  
		return nCount;
	}
	//添加栏目文档中间表
	public int  addChnlDocLink1(IrpDocument document){
		IrpChnlDocLink link=new IrpChnlDocLink();
		Long chnldocid=TableIdUtil.getNextId(IrpChnlDocLink.TABLE_NAME);//获得主键
		link.setChnldocid(chnldocid);
		link.setChannelid(document.getChannelid());
		link.setDocid(document.getDocid()); 
		link.setDocpubtime(document.getDocpubtime());
		link.setDoctitle(document.getDoctitle());
		link.setDocstatus(document.getDocstatus());
		String cruser = null;
		Long cruserid = null;
		if(document.getCruser()!=null && document.getCruserid()!=null){
			cruser = document.getCruser();
			cruserid = document.getCruserid();
		}else{
			IrpUser irpUser = LoginUtil.getLoginUser();
			cruser = irpUser.getUsername();
			cruserid = irpUser.getUserid();
		}
		link.setCruser(cruser);
		link.setCruserid(cruserid);
		link.setCrtime(document.getCrtime());
		link.setModal(document.getDoctype()); 
		link.setCruser(document.getCruser()); 
		link.setSiteid(document.getSiteid()); 
		link.setSiteid(document.getSiteid());
		link.setDockind(document.getDocoutupid());//投稿 知识id
		link.setCompletescore(document.getCompletescore());//完整度
		link.setCryscore(document.getCryscore());//系数
		link.setDocchannel(document.getFlowpreoperationmark());//加精数量
		link.setDocflag(document.getDocflag()!=null?document.getDocflag().intValue():0);
		LogUtil logUtil = new LogUtil("DOCUMENT_ADD",link); 
		int nCount=0;
		try {
			//此时需要添加文档简约表
			irpChnl_Doc_LinkDAO.insertSelective(link);
			nCount=1;
			logUtil.successLogs("添加文档["+logUtil.getLogUser()+"],成功");
		} catch (SQLException e) { 
			e.printStackTrace();
			nCount=0;
			logUtil.errorLogs("添加文档["+logUtil.getLogUser()+"],失败"); 
		}  
		return nCount;
	}
	@Override
	public IrpDocumentWithBLOBs findDocumentByDocId(Long docid) {
		IrpDocumentWithBLOBs irpDocument=null; 
	    try { 
		   irpDocument=irpDocumentDAO.selectByPrimaryKey(docid);
		   if(irpDocument!=null){
			   IrpUser irpUser=LoginUtil.getLoginUser();
			   IrpMostTreadExample mostTreadExampleDing=new IrpMostTreadExample(); 
			   //查看自己顶的对象
			   mostTreadExampleDing.createCriteria().andDocidEqualTo(docid)
			   									.andCruseridEqualTo(irpUser.getUserid())
			   									.andStatusEqualTo(new BigDecimal(IrpMostTread.MOTE_TREAD_STATUS_DING));
			   List<IrpMostTread> dings=irpMoteTreadDAOImpl.selectByExample(mostTreadExampleDing);
			   if(dings!=null && dings.size()>0){
				   irpDocument.setIrpMostTreadDing(dings.get(0));
			   }
			   //查看自己踩的对象
			   IrpMostTreadExample mostTreadExampleCai=new IrpMostTreadExample(); 
			   mostTreadExampleCai.createCriteria().andDocidEqualTo(docid)
					.andCruseridEqualTo(irpUser.getUserid())
					.andStatusEqualTo(new BigDecimal(IrpMostTread.MOTE_TREAD_STATUS_CAI));
			   	List<IrpMostTread> cais=irpMoteTreadDAOImpl.selectByExample(mostTreadExampleCai);
			    if(cais!=null && cais.size()>0){
					   irpDocument.setIrpMostTreadCai(cais.get(0));
				   }
			   //查询收藏对象
			   IrpDocumentCollectionExample example=new IrpDocumentCollectionExample();
			   example.createCriteria().andDocidEqualTo(new BigDecimal(irpDocument.getDocid()))
			   							.andUseridEqualTo(new BigDecimal(irpUser.getUserid()));
			   List<IrpDocumentCollection> collections= irpDocumentCollectionDAO.selectByExample(example);
			   if(collections!=null &&collections.size()>0){
				   irpDocument.setIrpDocumentCollection(collections.get(0));//查询他是不是收藏了这个文档
			   } 
		   }
		   
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return irpDocument;
	} 
	 //修改点击量+ 点击量只有加
	@Override
	public IrpDocumentWithBLOBs updateHitScountJia( IrpDocumentWithBLOBs irpDocument){
		 
		 try {
			 irpDocument.setHitscount(irpDocument.getHitscount()+1);
			 irpDocumentDAO.updateByPrimaryKey(irpDocument);
			 
			 IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			 chnlDocLinkExample.createCriteria().andDocidEqualTo(irpDocument.getDocid());
			 List<IrpChnlDocLink> chnlDocLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
			 if(chnlDocLinks!=null && chnlDocLinks.size()>0){
				 IrpChnlDocLink record=chnlDocLinks.get(0);
				 record.setHitscount(record.getHitscount()+1);
				 irpChnl_Doc_LinkDAO.updateByPrimaryKeySelective(record);
			 } 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpDocument;
	} 
	////////////付燕妮
	@Override
	public int addDocument(IrpDocument document) {
		int nCount=0;  
		 try {  
			 
			 IrpUser irpUser=LoginUtil.getLoginUser(); //获得当前登录用户
			 if(document.getDocid()==null || document.getDocid()==0L){
				 document.setDocid(TableIdUtil.getNextId(document));
			 }
			 //处理创建用户
			 if(document.getCruser()==null || document.getCruser().length()==0){
				 document.setCruser(irpUser.getUsername());
			 }
			 if(document.getCruserid()==null || document.getCruserid()<=0L){
				 document.setCruserid(irpUser.getUserid());//ID
			 }
			 document.setDocsecurity(0L);
			 document.setFlowpreoperationmark(0L);//加精数量
			 Date newdate=new Date();//当前站点创建时间
			 if(document.getCrtime()==null){
				 document.setCrtime(newdate);
			 }
			 
			 //添加标签
		     String documentKeyWord= addTag(document.getDockeywords()); 
			 //添加文档
		     document.setDockeywords(documentKeyWord);
		    
			 /**添加附件*/
	         /**添加中间表**/
	         /**
	          * 由于没有流程，，
	          * 如果docustatus=1 则状态为草稿， 否则就为发布
	          * 因此他默认状态全部为发布状态10
	          */
		     
	         document.setCollectioncount(0L);//收藏数量默认为0；
	         //获得当前文章栏目，判断是否进入审核
	         IrpChannel channel = irpChannelDAO.selectByPrimaryKey(document.getChannelid());
	         System.out.println("士大夫长度"+channel.getSiteid());
	         boolean bWorkFlow = false; 
	         if(document.getDocstatus()==null || document.getDocstatus()!=IrpDocument.DRAFT_STATUS){
		         bWorkFlow = channel!=null && channel.getSchedule()!=null && channel.getSchedule()>0L;
		         if(bWorkFlow){
		        	 IrpWorkflowNode flowNode = irpWorkFlowService.findStartFlowNodeByFlowId(channel.getSchedule());
		        	 document.setDocstatus(new Long(flowNode.getDocstatus()));
		         }else{
		        	 document.setDocstatus(IrpDocument.PUBLISH_STATUS);
		        	 if(document.getDocpubtime()==null){
		        		 document.setDocpubtime(newdate);
		        	 }
		         }
	         }
	         document.setSiteid(channel.getSiteid());//拿到当前这个栏目所属的站点
	         /**判断知识完整度
	          * 添加知识分类法记录
	          */  
			 int defaultcom=SysConfigUtil.getSysConfigNumValue("DEFALUT_ALLSCORE");//默认完整度
			 
			 //增加默认系数
			 int tryScore=SysConfigUtil.getSysConfigNumValue("DEFALUT_CRYSCORE");//默认完整度
			 document.setCryscore(new Long(tryScore));
			 //先添加文档，在添加附件 
	         irpDocumentDAO.insertSelective(document);
	         
	      
			 //同步知识索引表数据
			 nCount=addChnlDocLink1(document);
			 nCount= 1;
		} catch (Exception e) {
			e.printStackTrace();
			nCount=0; 
		}
		 return nCount;
	} 
	
	
	@Override
	public int addDocument(IrpDocumentWithBLOBs document,String jsonFile,boolean  isClient, Long _toChannelid,Boolean addUserFileLog,String[] documentmaps,Integer _docsource) {
		int nCount=0;  
		 try {  
			 
			 IrpUser irpUser=LoginUtil.getLoginUser(); //获得当前登录用户
			 if(document.getDocid()==null || document.getDocid()==0L){
				 document.setDocid(TableIdUtil.getNextId(document));
			 }
			 //处理创建用户
			 if(document.getCruser()==null || document.getCruser().length()==0){
				 document.setCruser(irpUser.getUsername());
			 }
			 if(document.getCruserid()==null || document.getCruserid()<=0L){
				 document.setCruserid(irpUser.getUserid());//ID
			 }
			 if(document.getDoctype()==null){
				 document.setDoctype(_docsource);
			 }
			 document.setDocsecurity(0L);
			 document.setFlowpreoperationmark(0L);//加精数量
			 Date newdate=new Date();//当前站点创建时间
			 if(document.getCrtime()==null){
				 document.setCrtime(newdate);
			 }
			 
			 //添加标签
		     String documentKeyWord= addTag(document.getDockeywords()); 
			 //添加文档
		     document.setDockeywords(documentKeyWord);
		     
		     //给第三个clob添加数据
		     document.setAttachedcontent(document.getDochtmlcon());
			 /**添加附件*/
	         /**添加中间表**/
	         /**
	          * 由于没有流程，，
	          * 如果docustatus=1 则状态为草稿， 否则就为发布
	          * 因此他默认状态全部为发布状态10
	          */
		     
	         document.setCollectioncount(0L);//收藏数量默认为0；
	         //获得当前文章栏目，判断是否进入审核
	         IrpChannel channel = irpChannelDAO.selectByPrimaryKey(document.getChannelid());
	         boolean bWorkFlow = false; 
	         if(document.getDocstatus()==null || document.getDocstatus()!=IrpDocument.DRAFT_STATUS){
		         bWorkFlow = channel!=null && channel.getSchedule()!=null && channel.getSchedule()>0L;
		         if(bWorkFlow){
		        	 IrpWorkflowNode flowNode = irpWorkFlowService.findStartFlowNodeByFlowId(channel.getSchedule());
		        	 document.setDocstatus(new Long(flowNode.getDocstatus()));
		         }else{
		        	 document.setDocstatus(IrpDocument.PUBLISH_STATUS);
		        	 if(document.getDocpubtime()==null){
		        		 document.setDocpubtime(newdate);
		        	 }
		         }
	         }
	         document.setSiteid(channel.getSiteid());//拿到当前这个栏目所属的站点
	         /**判断知识完整度
	          * 添加知识分类法记录
	          */  
			 int defaultcom=SysConfigUtil.getSysConfigNumValue("DEFALUT_ALLSCORE");//默认完整度
			 if(documentmaps==null || documentmaps.length==0){
				 document.setCompletescore(new Long(defaultcom));
			 }else{
				 Long chnlId ;
				 List list = new ArrayList();
				 for(String string : documentmaps){
					 if(string!=null && !string.trim().equals("")){
						 try {
							 chnlId = new Long(string);
							 list.add(chnlId);
						} catch (Exception e) {
							continue;
						}
					 }
				 }
				 int jiaScore=SysConfigUtil.getSysConfigNumValue("DEFALUT_ONE_SCORE");
				 int allScore=list.size()*jiaScore;
				 document.setCompletescore(new Long(defaultcom+allScore));
				 //添加分类法记录
				 Long channelId ;
				 for (String string : documentmaps) {
					 if(string!=null && !string.trim().equals("")){
						 try {
							 channelId = new Long(string);
							 irpDocumentMapService.addDocumentMap(document.getDocid(),channelId,IrpDocumentMap.KONWLEDGE_MAP);
						} catch (Exception e) {
							continue;
						}
					 }
				}
			 }
			 //增加默认系数
			 int tryScore=SysConfigUtil.getSysConfigNumValue("DEFALUT_CRYSCORE");//默认完整度
			 document.setCryscore(new Long(tryScore));
			 //先添加文档，在添加附件 
	         irpDocumentDAO.insertSelective(document);
	         
	         //添加solr
	         addSolrIndex(document);
	         
	         boolean checkEditversions = false;
			 if(jsonFile!=null && jsonFile.length()>0 && !"[]".equals(jsonFile)){
				 jsonFile = jsonFile.replace("&amp;amp;","\"");
				 JSONArray _Array=  JSONArray.fromObject(jsonFile);
				 for (int i = 0; i < _Array.size(); i++) {
					 JSONObject jsonObject=(JSONObject)_Array.getJSONObject(i);   
					 String _sAttFile=jsonObject.getString("attfile"); 
					 String _sAttDesc=jsonObject.getString("attdesc");  
					 String _sAttLinkAlt=jsonObject.getString("attlinkalt"); 
					 String _sEditversions=  jsonObject.getString("editversions") ; 
					 if(!checkEditversions && _sEditversions!=null && _sEditversions.equals(IrpAttached.IS_FENGMIAN.toString())){
						 checkEditversions = true;
					 }
					 String _lastName=FileUtil.findFileExt(_sAttFile);
					 String _attflag=jsonObject.getString("attflag"); 
					 Long typeId=irpAttachedTypeService.findAttachedTypeIdByFileExt(_lastName); //查询所附件类型是否存在  
		   		  	 if(typeId!=null && typeId!=0){  
		   		  		 if(document.getDoctype().equals(IrpDocument.DOCTYPE_QUESTION)){
		   		  		     addAttQuestionFile(Integer.parseInt(_attflag),_sAttFile,typeId, document, _sAttDesc, _sAttLinkAlt ,_sEditversions,isClient,_toChannelid,addUserFileLog);	 
		   		  		 }else{
		   		  			 addAttFile(Integer.parseInt(_attflag),_sAttFile,typeId, document, _sAttDesc, _sAttLinkAlt ,_sEditversions,isClient,_toChannelid,addUserFileLog);	 
		   		  		 }
		   		     }else{//就是其他类型
		   		    	if(document.getDoctype().equals(IrpDocument.DOCTYPE_QUESTION)){
		   		    		addAttQuestionFile(Integer.parseInt(_attflag),_sAttFile,IrpAttachedType.ID_FIELD_NAMEOTHER, document, _sAttDesc, _sAttLinkAlt,_sEditversions,isClient,_toChannelid,addUserFileLog);	 
		   		    	}else{
		   		    		addAttFile(Integer.parseInt(_attflag),_sAttFile,IrpAttachedType.ID_FIELD_NAMEOTHER, document, _sAttDesc, _sAttLinkAlt,_sEditversions,isClient,_toChannelid,addUserFileLog);	 
		   		    	}
		   		     }
				  }  
			  }
			 //启动工作流程
			 if(bWorkFlow){
				 irpWorkFlowService.startProcess(channel.getSchedule(), document);
			 }
			 
			 if(!checkEditversions){
				 Random random = new Random(System.currentTimeMillis());
				 //根据系统时间产生1-15的随机数
				 long nDocFlag = (random.nextInt(15)+1);
				 IrpDocumentWithBLOBs flagDoc = new IrpDocumentWithBLOBs();
				 flagDoc.setDocid(document.getDocid());
				 flagDoc.setDocflag(nDocFlag);
				 irpDocumentDAO.updateByPrimaryKeySelective(flagDoc);
				 document.setDocflag(nDocFlag);
			 }
			 
			 //同步知识索引表数据
			 nCount=addChnlDocLink(document);
			 nCount= 1;
		} catch (Exception e) {
			e.printStackTrace();
			nCount=0; 
		}
		 return nCount;
	}
	//添加标签
	public String addTag(String sTags){
		if(sTags==null)
			return "";
		sTags = sTags.trim();
		//替换所有的分隔符为逗号
		sTags = sTags.replaceAll(" ", ",");
		sTags = sTags.replaceAll("；", ",");
		sTags = sTags.replaceAll(";", ",");
		sTags = sTags.replaceAll("，", ","); 
		try { 
			String[] arrTags = sTags.split(",");
			for(int i=0;i<arrTags.length;i++){
				String sTag = arrTags[i].trim();
				if(sTag==null || sTag.length()==0){
					continue;
				}
				IrpTagExample example = new IrpTagExample();
				example.createCriteria().andTagnameEqualTo(sTag);
				List<IrpTag> list = irpTagDAO.selectByExample(example);
				if(list==null ||list.size()==0){
					IrpTag record=new IrpTag();
					record.setTagid(TableIdUtil.getNextId(IrpTag.TABLE_NAME));
					record.setTagname(sTag);
					irpTagDAO.insertSelective(record);
				} 
			}
		} catch (Exception e) {
				e.printStackTrace();
		} 
		/**
		 * 将字符串中的重复词语给去掉
		 */
		Set<String> onlyStr=new HashSet<String>();
		if(sTags!=null && sTags.length()>0){
			String[] strs=sTags.split(",");
			for (int i = 0; i < strs.length; i++) {
				onlyStr.add(strs[i].trim());
			}
		}
		/**
		 * 如果标签不为null
		 */
		String sKey="";
		for (String sWord : onlyStr) {
			if(!sKey.isEmpty()){
				sKey += (","+sWord);
			}else{
				sKey = sWord;
			}
		}
		return sKey;
	}
	
	public ArrayList<Long>  addAttQuestionFile(Integer _attflag,String _sAttFile,Long TypeId,IrpDocumentWithBLOBs document,String _sAttDesc,String _sAttLinkAlt,String _sEditversions,boolean isClient, Long _touChannelid,Boolean addUserFile){
		ArrayList<Long>  _attachedids=new ArrayList<Long>();
	    String filePath=SysFileUtil.getFilePathByFileName(_sAttFile); 
       	File newFile=new File(filePath);  
       	String newFileName="";  
       	if(newFile.exists()){
			if(IrpAttachedType.JPG_FIELD_NAME.toString().equals(TypeId.toString())){ //这是创建文档之后的附件名字
				  newFileName=SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC,addUserFile);
			}else{
				  newFileName=SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
			} 
			//删除临时表中的文件 
			 String newFilePath=SysFileUtil.getFilePathByFileName(newFileName); 
		 
		    Long _attachedid= irpAttachedService.addFile(document.getDocid(), 0L, newFileName, _sAttLinkAlt, _sAttDesc, 0, newFilePath,Integer.parseInt(_sEditversions), TypeId,_attflag);
		    _attachedids.add(_attachedid);
       	}  		
		 return _attachedids;
		
	}
	
	@Override /////////修改重写   付燕妮
	public ArrayList<Long>  addAttFile(Integer _attflag,String _sAttFile,Long TypeId,IrpDocumentWithBLOBs document,String _sAttDesc,String _sAttLinkAlt,String _sEditversions,boolean isClient, Long _touChannelid,Boolean addUserFile){
		ArrayList<Long>  _attachedids=new ArrayList<Long>();
			    String filePath=SysFileUtil.getFilePathByFileName(_sAttFile); 
		       	File newFile=new File(filePath);  
		       	String newFileName="";  
		       	if(newFile.exists()){
					if(IrpAttachedType.JPG_FIELD_NAME.toString().equals(TypeId.toString())){ //这是创建文档之后的附件名字
						  newFileName=SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC,addUserFile);
					}else{
						  newFileName=SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
					} 
					if(isClient==false ){//不是客户端
						SysFileUtil.deleteFileByFileName(_sAttFile);//后台添加
					}else if (isClient==true&&(_touChannelid!=null &&_touChannelid!=0)) {
						SysFileUtil.deleteFileByFileName(_sAttFile);//就是在投稿的时候删除
					} 
					 String newFilePath=SysFileUtil.getFilePathByFileName(newFileName); 
					 Long docid = 0l;
					 if(document!=null){
						 docid=document.getDocid();
					 }
				     Long _attachedid= irpAttachedService.addFile(docid, 0L, newFileName, _sAttLinkAlt, _sAttDesc, 0, newFilePath,Integer.parseInt(_sEditversions), TypeId,_attflag);
				     _attachedids.add(_attachedid);   
		       	}   
		 return _attachedids;
	}
	
	@Override
	public IrpDocumentWithBLOBs getIrpDocumentById(Long docid) {
		IrpDocumentWithBLOBs bloBs=null;
		try {
			bloBs=irpDocumentDAO.selectByPrimaryKey(docid);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return bloBs;
	}
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}

	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}
	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}
	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}
	public IrpTagDAO getIrpTagDAO() {
		return irpTagDAO;
	}
	public void setIrpTagDAO(IrpTagDAO irpTagDAO) {
		this.irpTagDAO = irpTagDAO;
	}
	 
	@Override
	public int clientDeleteDocument(Long _docid) {
		int nCount=0;
		try { 
			/**
			 * 前半部分为真实删除，后半部分为逻辑删除
			 */
			/*
			//删除收藏表
			IrpDocumentCollectionExample collectionExample=new IrpDocumentCollectionExample();
			collectionExample.createCriteria().andDocidEqualTo(new BigDecimal(_docid));
			irpDocumentCollectionDAO.deleteByExample(collectionExample);
			//删除回复表
			IrpDocrecommendExample docrecommendExample=new IrpDocrecommendExample();
			docrecommendExample.createCriteria().andDocidEqualTo(_docid);
			irpDocrecommendDAO.deleteByExample(docrecommendExample);
			//删除中间表
			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
			irpChnl_Doc_LinkDAO.deleteByExample(chnlDocLinkExample);
			//删除附件 
			IrpAttachedExample attachedExample=new IrpAttachedExample();
			attachedExample.createCriteria().andAttdocidEqualTo(_docid);
			irpAttachedDAO.deleteByExample(attachedExample);
			//删除自己
			nCount=irpDocumentDAO.deleteByPrimaryKey(_docid);
			*/
			
			/**
			 * 删除documentMap表doc与sub关系
			 */
			IrpDocumentMapExample mapExample = new IrpDocumentMapExample();
			mapExample.createCriteria().andDocidEqualTo(_docid);
			irpDocumentMapDao.deleteByExample(mapExample);
			
			/**
			 * 删除自己，目前是逻辑删除，因此直接将它的docstatus因为他默认10因此直接把他给修改为发布状态的相反数就可以了                                     
			 */
			 //查询一下再修改他的状态
			 IrpDocumentWithBLOBs documentWithBLOBs= irpDocumentDAO.selectByPrimaryKey(_docid);
			 Long _nDocstatus=-documentWithBLOBs.getDocstatus();
			 documentWithBLOBs.setDocstatus(_nDocstatus); 
			 documentWithBLOBs.setDocid(_docid);
			 irpDocumentDAO.updateByPrimaryKeySelective(documentWithBLOBs);//修改文档表状态为负
			 IrpChnlDocLink chnlDocLink=new IrpChnlDocLink();
			 chnlDocLink.setDocid(_docid);
			 chnlDocLink.setDocstatus(_nDocstatus);//变为他相反数(因为前台能够看见的文档状态都是为10的)
			  IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			  chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
			 irpChnl_Doc_LinkDAO.updateByExampleSelective(chnlDocLink, chnlDocLinkExample);//修改中间表状态为负
			 //删除solr
			if(documentWithBLOBs.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID){
				 solrService.deleteSolrIndex(documentWithBLOBs.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL));
			}else{
				solrService.deleteSolrIndex(documentWithBLOBs.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE));
			}
			 nCount=1;
		} catch (SQLException e) { 
			e.printStackTrace();
			nCount=0;
		} 
		return nCount;
	}
	@Override
	public void addHitScount(Long _docid) {
		try {
			IrpDocument document=irpDocumentDAO.selectByPrimaryKey(_docid);
			if(document!=null){
				document.setHitscount(document.getHitscount()+1);
				irpDocumentDAO.updateByPrimaryKey(document);
			}
			IrpChnlDocLinkExample example=new IrpChnlDocLinkExample();
			example.createCriteria().andDocidEqualTo(_docid);
			List<IrpChnlDocLink> chnlDocLinks=irpChnl_Doc_LinkDAO.selectByExample(example); 
			if(chnlDocLinks!=null && chnlDocLinks.size()>0){
				for (int i = 0; i < chnlDocLinks.size(); i++) {
					IrpChnlDocLink chnlDocLink=chnlDocLinks.get(i);
					chnlDocLink.setHitscount(chnlDocLink.getHitscount()+1);
					irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 
	@Override
	public int updateDocStatusByDocId(Long _nDocId,Long _nDocStatus) {
		int nRows = 0;
		Date pubtime = new Date();
		try {
			IrpDocumentWithBLOBs document = new IrpDocumentWithBLOBs();
			document.setDocid(_nDocId);
			document.setDocstatus(_nDocStatus);
			if(_nDocStatus.longValue() == IrpDocumentWithBLOBs.PUBLISH_STATUS.longValue()){
				document.setDocpubtime(pubtime);
			}
			nRows = irpDocumentDAO.updateByPrimaryKeySelective(document);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(nRows>0){
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			example.createCriteria().andDocidEqualTo(_nDocId);
			IrpChnlDocLink irpChnlDocLink = new IrpChnlDocLink();
			irpChnlDocLink.setDocstatus(_nDocStatus);
			if(_nDocStatus.longValue() == IrpDocumentWithBLOBs.PUBLISH_STATUS.longValue()){
				irpChnlDocLink.setDocpubtime(pubtime);
			}
			try {
				irpChnl_Doc_LinkDAO.updateByExampleSelective(irpChnlDocLink, example);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nRows;
	}
	
	@Override
	public int updateDocStatusByDocIds(String _sDocId, Long _nDocStatus) {
		int nRows = 0;
		Date pubtime = new Date();
		List<Long> arrDocIds = new ArrayList<Long>();
		List<Long> arrChnldocids = new ArrayList<Long>();
		try {
			String[] sDocIds = _sDocId.split(",");
			for (String string : sDocIds) {
				String[] ids = string.split("#");
				try {
					arrDocIds.add(Long.valueOf(ids[0]));
					arrChnldocids.add(Long.valueOf(ids[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			IrpDocumentExample example = new IrpDocumentExample();
			example.createCriteria().andDocidIn(arrDocIds);
			IrpDocumentWithBLOBs document = new IrpDocumentWithBLOBs();
			document.setDocstatus(_nDocStatus);
			if(_nDocStatus.longValue() == IrpDocumentWithBLOBs.PUBLISH_STATUS.longValue()){
				document.setDocpubtime(pubtime);
			}
			nRows = irpDocumentDAO.updateByExampleSelective(document, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(nRows>0){
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			example.createCriteria().andChnldocidIn(arrChnldocids);
			IrpChnlDocLink irpChnlDocLink = new IrpChnlDocLink();
			irpChnlDocLink.setDocstatus(_nDocStatus);
			if(_nDocStatus.longValue() == IrpDocumentWithBLOBs.PUBLISH_STATUS.longValue()){
				irpChnlDocLink.setDocpubtime(pubtime);
			}
			try {
				irpChnl_Doc_LinkDAO.updateByExampleSelective(irpChnlDocLink, example);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nRows;
	}
	
	@Override
	public int deleteDocument(Long _docid) {
		int nCount=0;
		try { 
			/**
			 * 前半部分为真实删除，后半部分为逻辑删除
			 */ 
			//删除收藏表
			IrpDocumentCollectionExample collectionExample=new IrpDocumentCollectionExample();
			collectionExample.createCriteria().andDocidEqualTo(new BigDecimal(_docid));
			irpDocumentCollectionDAO.deleteByExample(collectionExample);
			//删除回复表
			IrpDocrecommendExample docrecommendExample=new IrpDocrecommendExample();
			docrecommendExample.createCriteria().andDocidEqualTo(_docid);
			irpDocrecommendDAO.deleteByExample(docrecommendExample);
//			//删除中间表
			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
			irpChnl_Doc_LinkDAO.deleteByExample(chnlDocLinkExample);
			//删除附件 
			IrpAttachedExample attachedExample=new IrpAttachedExample();
			attachedExample.createCriteria().andAttdocidEqualTo(_docid);
//			删除附件
			irpAttachedService.deleteFileByExpt(_docid, 0);
			//删除自己
			nCount=irpDocumentDAO.deleteByPrimaryKey(_docid); 
			nCount=1;
		} catch (SQLException e) { 
			e.printStackTrace();
			nCount=0;
		} 
		return nCount;
	}
	@Override
	public IrpDocumentWithBLOBs findDoucumentByOldId(Long _oldId) {
		IrpDocumentWithBLOBs document=null;
		IrpDocumentExample documentExample=new IrpDocumentExample();
		documentExample.createCriteria().andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)//发布状态
										.andDocoutupidEqualTo(_oldId);//投稿的知识
		try {
			List<IrpDocumentWithBLOBs> list=irpDocumentDAO.selectByExampleWithBLOBs(documentExample);
			if(list!=null && list.size()>0){
				document=list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}
	@Override
	public int sumTouHit(List<Long> _docids) {
		int nCount=0;
		IrpDocumentExample example=new IrpDocumentExample();
		example.or(example.createCriteria().andDocidIn(_docids));
		example.or(example.createCriteria().andDocoutupidIn(_docids));
		try {
			nCount=irpDocumentDAO.sumHit(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public IrpDocument irpDocument(Long docid) {
		IrpDocument irpDocument = null;
		try {
			if(docid!=null){
				irpDocument = this.irpDocumentDAO.selectByPrimaryKey(docid);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpDocument;
	}
	@Override
	public int updateDocumentInformType(Long docid, Long infromType) {
		int nCount=0;
		try {
			IrpDocumentWithBLOBs doucDocument=irpDocumentDAO.selectByPrimaryKey(docid);
			if(doucDocument!=null){
				doucDocument.setInformtype(infromType);
				IrpDocumentExample example=new IrpDocumentExample();
				example.createCriteria().andDocidEqualTo(doucDocument.getDocid());
				nCount=irpDocumentDAO.updateByExampleSelective(doucDocument, example);
				if(infromType.longValue()==IrpDocument.DOCUMENT_IS_INFORM.longValue()){//非法状态 
					 //删除solr
					if(doucDocument.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID){
						 solrService.deleteSolrIndex(doucDocument.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL));
					}else{
						solrService.deleteSolrIndex(doucDocument.getDocid(),SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE));
					}
				}else if(infromType.longValue()==IrpDocument.DOCUMENT_NOT_INFORM.longValue()){//正常状态
					//添加solr
					addSolrIndex(doucDocument);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int updateDocumentToChannel(Long toChannel, Long fromChannel) {
		int nCount=0;
		try { 
			IrpDocumentExample example=new IrpDocumentExample();
			example.createCriteria().andChannelidEqualTo(fromChannel);
			IrpDocumentWithBLOBs record=new IrpDocumentWithBLOBs();
			record.setChannelid(toChannel);
			nCount=irpDocumentDAO.updateByExampleSelective(record, example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	 @Override
	public List<IrpDocument> findAllDatas(IrpDocumentExample documentExample) {
		List<IrpDocument> documents=null;
		try {
			documents= irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documents;
	}
	 @Override
	public int countDocument(BigDecimal minScore,BigDecimal maxScore,Date startTime, Date endTime) {
		int nCount=0;
		try {
			IrpDocumentExample example=new IrpDocumentExample();
			com.tfs.irp.document.entity.IrpDocumentExample.Criteria criteria=example.createCriteria();
		
			criteria.andDocpubtimeBetween(startTime, endTime);
			
			if(minScore.intValue()==0){
				criteria.andAvgscoreEqualTo(minScore);
			}else{
				criteria.andAvgscoreGreaterThanOrEqualTo(minScore)
				.andAvgscoreLessThan(maxScore);
			}
			nCount= irpDocumentDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	 @Override
	public int addChisslAmount(Long _docid, Integer _amount) {
		 int nCount=0;
		 try {
			    //将文档表加精次数加_amount 
	       		IrpDocumentExample documentExample=new IrpDocumentExample();
	       		documentExample.createCriteria().andDocidEqualTo(_docid);
	       		IrpDocument document=irpDocumentDAO.selectByPrimaryKey(_docid);
	       		if(document!=null ){ 
	       			document.setFlowpreoperationmark( document.getFlowpreoperationmark()+_amount );
	       			irpDocumentDAO.updateByPrimaryKey(document);
	       		}
	       		// 文档栏目中间表的加精次数 加_amount
	   			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
	   			chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
	   			List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
	   			if(docLinks!=null && docLinks.size()>0){
	   				IrpChnlDocLink chnlDocLink=docLinks.get(0);
	   				chnlDocLink.setDocchannel(chnlDocLink.getDocchannel()+_amount);
	   				irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
	   			}
	   			nCount=1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return nCount;
	}
	 
	 @Override
	public List<IrpDocument> sctterDocument(Date startTime, Date endTime,Long  siteId) {
		 List<IrpDocument> documents=null;
		 IrpDocumentExample documentExample=new IrpDocumentExample();
		 com.tfs.irp.document.entity.IrpDocumentExample.Criteria criteria=documentExample.createCriteria();
		 criteria.andDocpubtimeBetween(startTime, endTime);
		 criteria.andSiteidEqualTo(siteId);
		 try {
			 documents=irpDocumentDAO.selectSctterDocumentByExample(documentExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documents;
	}
	 
	@Override
	public void addSolrIndex(IrpDocumentWithBLOBs document) {
		if(document.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID.longValue()){
			//获得是否添加个人站点配置
			String sAddPrivateDocumentSolr = SysConfigUtil.getSysConfigValue("ADD_DOCUMENT_SOLR");
			if(!Boolean.parseBoolean(sAddPrivateDocumentSolr)){
				return;
			}
		}
		//如果知识状态不为发布，则返回
		if(document.getDocstatus().longValue()!=IrpDocument.PUBLISH_STATUS.longValue()){
			return;
		}

		String sDocumentSolrUrl ="";
		if(document.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID){
			sDocumentSolrUrl=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL);
		}else{
			sDocumentSolrUrl=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
		}
		
		DocumentForSolr documentForSolr=new DocumentForSolr();
		documentForSolr.setCHANNELID(document.getChannelid());
		documentForSolr.setSITEID(document.getSiteid());
		documentForSolr.setDOCID(document.getDocid());
		documentForSolr.setCRTIME(new Date());
		documentForSolr.setCRUSERID(document.getCruserid());
		documentForSolr.setCRUSER(document.getCruser());
		documentForSolr.setDOCCONTENT(document.getDoccontent());
		documentForSolr.setDOCKEYWORDS(document.getDockeywords());
		documentForSolr.setDOCTITLE(document.getDoctitle());
		solrService.addDocumentIndex(documentForSolr, sDocumentSolrUrl);
	}
	
	@Override
	public void updateSolrIndex(IrpDocumentWithBLOBs document) {
		if(document.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID.longValue()){
			//获得是否添加个人站点配置
			String sAddPrivateDocumentSolr = SysConfigUtil.getSysConfigValue("ADD_DOCUMENT_SOLR");
			if(!Boolean.parseBoolean(sAddPrivateDocumentSolr)){
				return;
			}
		}
		String sDocumentSolrUrl ="";
		if(document.getSiteid().longValue()==IrpSite.PRIVATE_SITE_ID){
			sDocumentSolrUrl=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL);
		}else{
			sDocumentSolrUrl=SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
		}
		if(document.getDocstatus().longValue()==IrpDocument.PUBLISH_STATUS.longValue()){
			DocumentForSolr documentForSolr=new DocumentForSolr();
			documentForSolr.setCHANNELID(document.getChannelid());
			documentForSolr.setSITEID(document.getSiteid());
			documentForSolr.setDOCID(document.getDocid());
			documentForSolr.setCRTIME(new Date());
			documentForSolr.setCRUSERID(document.getCruserid());
			documentForSolr.setCRUSER(document.getCruser());
			documentForSolr.setDOCCONTENT(document.getDoccontent());
			documentForSolr.setDOCKEYWORDS(document.getDockeywords());
			documentForSolr.setDOCTITLE(document.getDoctitle());
			solrService.updateDocumentIndex(documentForSolr,sDocumentSolrUrl);
		}
	}
	@Override
	public List<IrpDocument> selectByExample(PageUtil page,
			IrpDocumentExample example) {
		return irpDocumentDAO.selectByExample(page, example);
	}
	
	@Override
	public List<IrpDocument> checkAllDocSub(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType) {
		List<IrpDocument> list = null;
		try {
			IrpDocumentExample example = new IrpDocumentExample();
			IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
			com.tfs.irp.document.entity.IrpDocumentExample.Criteria criteria = example.createCriteria();
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			// 排序
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			list = irpDocumentDAO.selectByExample(pageUtil, example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					IrpDocument subDocument = list.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(subDocument.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						list.get(i)
								.setIrpDocumentCollection(collections.get(0));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,
			Long informType) {
		int nCount = 0;
		try {
			IrpDocumentExample example = new IrpDocumentExample();
			com.tfs.irp.document.entity.IrpDocumentExample.Criteria criteria = example.createCriteria();
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {// /閫掑綊寰楀埌浠栦笅闈㈡墍鏈夌殑鏍忕洰id
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			// 鏍规嵁鍚嶇О
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle.toString() + "%");
			}
			// 鏍规嵁鐘舵�
			Object searchModal = map.get("searchModal");
			if (searchModal != null && searchModal.toString().length()>0) {// 鏌ヨ澶勪簬璇ョ姸鎬佷笅鐨勬枃妗�
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			List list = irpDocumentDAO.selectByExampleWithBLOBs(example);
			if(list!=null && list.size()>0){
				nCount = list.size();
			}else{
				nCount = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<IrpDocument> findAllDocByuser(List<Long> stuts) {
		List<IrpDocument> list=null;
		IrpDocumentExample example = new IrpDocumentExample();
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId()).andDocstatusIn(stuts);
		example.setOrderByClause("crtime desc");
		list=irpDocumentDAO.selectByExample(null, example);
		return list;
	}
	@Override
	public void addDocument(Map<String,Object> map) {
		IrpDocumentWithBLOBs document = new IrpDocumentWithBLOBs();
				
		// 公告id		
		IrpChannelExample channelExample = new IrpChannelExample();
		channelExample.createCriteria().andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_GONGGAO)
										.andChnlnameEqualTo("公告")
										.andParentidEqualTo(0L);
		try {
			IrpChannel announcementChannel = irpChannelDAO.selectByExample(channelExample).get(0);
			
			// 赋值
			Date date = new Date();
			document.setDocid(TableIdUtil.getNextId(IrpDocument.TABLE_NAME));
			document.setChannelid(announcementChannel.getChannelid());
			document.setSiteid(IrpSite.PUBLIC_SITE_ID);
			document.setDoctype(IrpChannel.CHANNEL_TYPE_GONGGAO);
			document.setDoctitle("统计信息");
			document.setDocstatus(IrpDocument.PUBLISH_STATUS);
			document.setDoccontent(map.get("content").toString());
			document.setDochtmlcon(map.get("contentHtml").toString());
			document.setDocabstract(map.get("contentHtml").toString());
			document.setDockeywords("公告");
			document.setDocauthor("admin");
			document.setDocpubtime(date);
			document.setCruser("admin");
			document.setCruserid(1L);
			document.setCrtime(date);
			document.setAttachedcontent(map.get("contentHtml").toString());
			document.setReplyflag(1);
			
			
			
			irpDocumentDAO.insertSelective(document);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public List<IrpDocument> boolSimilByTitle(String _title, int infortype,
			int _docstatus) {
		// TODO Auto-generated method stub
		List<IrpDocument> doc = null;
		IrpDocumentExample example = new IrpDocumentExample();
		example.createCriteria().andDoctitleEqualTo(_title)
								.andDocstatusEqualTo((long)_docstatus)	
								.andInformtypeEqualTo((long)infortype);
		
		try {
			doc = this.irpDocumentDAO.selectByExampleWithoutBLOBs(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	@Override
	public int updateDocumentByDocId(IrpDocumentWithBLOBs _irpDoument) {
		int nDataCount=0;
		try {
			nDataCount = irpDocumentDAO.updateByPrimaryKeySelective(_irpDoument);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}
	
	@Override
	public int updateDocument(long _docoutupid,IrpDocversionWithBLOBs _irpDoument ) {
		// TODO Auto-generated method stub
		int statu = 0;
		IrpDocumentWithBLOBs irpDoument = new IrpDocumentWithBLOBs();
		irpDoument.setDocid(_docoutupid);


		irpDoument.setChannelid(_irpDoument.getChannelid());
		irpDoument.setSiteid(_irpDoument.getSiteid());
		irpDoument.setDoctype(_irpDoument.getDoctype());
		irpDoument.setDoctitle(_irpDoument.getDoctitle());
		irpDoument.setDocstatus(_irpDoument.getDocstatus());

		
		irpDoument.setDoccontent(_irpDoument.getDoccontent().toString());
		irpDoument.setDochtmlcon(_irpDoument.getDochtmlcon().toString());
		irpDoument.setDocabstract(_irpDoument.getDocabstract());
		irpDoument.setDockeywords(_irpDoument.getDockeywords());
		irpDoument.setDocoutupid(_irpDoument.getDocoutupid());
		irpDoument.setDocpubtime(_irpDoument.getDocpubtime());
		irpDoument.setCruser(_irpDoument.getCruser());
		irpDoument.setCruserid(_irpDoument.getCruserid());
		irpDoument.setCrtime(_irpDoument.getCrtime());
		irpDoument.setKnowledgeid(_irpDoument.getDocid());
		irpDoument.setHitscount(_irpDoument.getHitscount());
		irpDoument.setReplyflag(_irpDoument.getReplyflag());
		irpDoument.setReplycount(_irpDoument.getReplycount());
		irpDoument.setAttachedcontent(_irpDoument.getAttachedcontent().toString());
		irpDoument.setDocscore(_irpDoument.getDocscore());
		irpDoument.setInformtype(_irpDoument.getInformtype());
		irpDoument.setDocversion(_irpDoument.getDocversion());
		
		
		try {
			statu = irpDocumentDAO.updateByPrimaryKeySelective(irpDoument);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statu;
	}
	@Override
	public int selectCountByGroupidDoc(Long siteid, List<Long> groupid) {
		int nCount=0;
		IrpDocumentExample example=new IrpDocumentExample();
		Criteria criteria=example.createCriteria();
		criteria.andSiteidEqualTo(siteid).andGroupidIn(groupid).andDocstatusEqualTo(10l);
		try {
			nCount= irpDocumentDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int selectCountByChannelDoc(Long siteid,List<Long> channelid) {
		int nCount=0;
		IrpDocumentExample example=new IrpDocumentExample();
		Criteria criteria=example.createCriteria();
		criteria.andSiteidEqualTo(siteid).andChannelidIn(channelid).andDocstatusEqualTo(10l);
		try {
			nCount= irpDocumentDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int updateDocscoreByPrimaryKey(long _nDocId, long _nScore){
		IrpDocument irpdocument = new IrpDocument();
		irpdocument.setDocid(_nDocId);
		irpdocument.setDocscore(_nScore);
		return irpDocumentDAO.updateDocscoreByPrimaryKey(irpdocument);
	}
	
	@Override
	public long findAllDocumentCount() {
		long nCount=0;
		IrpDocumentExample example = new IrpDocumentExample();
		example.createCriteria()
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS);
		try {
			nCount= irpDocumentDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpDocument> similarityDocByDocId(long _nDocId){
		return solrService.similarityDocByDocId(_nDocId);
	}
	@Override
	public IrpDocumentWithBLOBs findDocumentByDocId(Long docid,String token) {
		IrpDocumentWithBLOBs irpDocument=null; 
	    try { 
		   irpDocument=irpDocumentDAO.selectByPrimaryKey(docid);
		   if(irpDocument!=null){
			   IrpUser irpUser=mobileAction.getlogin(token);
			   IrpMostTreadExample mostTreadExampleDing=new IrpMostTreadExample(); 
			   //查看自己顶的对象
			   mostTreadExampleDing.createCriteria().andDocidEqualTo(docid)
			   									.andCruseridEqualTo(irpUser.getUserid())
			   									.andStatusEqualTo(new BigDecimal(IrpMostTread.MOTE_TREAD_STATUS_DING));
			   List<IrpMostTread> dings=irpMoteTreadDAOImpl.selectByExample(mostTreadExampleDing);
			   if(dings!=null && dings.size()>0){
				   irpDocument.setIrpMostTreadDing(dings.get(0));
			   }
			   //查看自己踩的对象
			   IrpMostTreadExample mostTreadExampleCai=new IrpMostTreadExample(); 
			   mostTreadExampleCai.createCriteria().andDocidEqualTo(docid)
					.andCruseridEqualTo(irpUser.getUserid())
					.andStatusEqualTo(new BigDecimal(IrpMostTread.MOTE_TREAD_STATUS_CAI));
			   	List<IrpMostTread> cais=irpMoteTreadDAOImpl.selectByExample(mostTreadExampleCai);
			    if(cais!=null && cais.size()>0){
					   irpDocument.setIrpMostTreadCai(cais.get(0));
				   }
			   //查询收藏对象
			   IrpDocumentCollectionExample example=new IrpDocumentCollectionExample();
			   example.createCriteria().andDocidEqualTo(new BigDecimal(irpDocument.getDocid()))
			   							.andUseridEqualTo(new BigDecimal(irpUser.getUserid()));
			   List<IrpDocumentCollection> collections= irpDocumentCollectionDAO.selectByExample(example);
			   if(collections!=null &&collections.size()>0){
				   irpDocument.setIrpDocumentCollection(collections.get(0));//查询他是不是收藏了这个文档
			   } 
		   }
		   
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return irpDocument;
	}
}
