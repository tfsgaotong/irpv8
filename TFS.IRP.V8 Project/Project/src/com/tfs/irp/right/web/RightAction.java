package com.tfs.irp.right.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.managementoper.service.IrpManagementOperService;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.right.service.IrpRightService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.SysConfigUtil;

public class RightAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(RightAction.class);
	
	private IrpRightService irpRightService;
	
	private IrpOpertypeService irpOpertypeService;
	
	private IrpManagementOperService irpManagementOperService;
	
	private String jsonData;
	
	public IrpManagementOperService getIrpManagementOperService() {
		return irpManagementOperService;
	}

	public void setIrpManagementOperService(
			IrpManagementOperService irpManagementOperService) {
		this.irpManagementOperService = irpManagementOperService;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String id;
	
	private String type;
	
	private long operId;
	
	private Long objId;
	
	private String operType;
	
	private String objType;
	
	private List<IrpOpertype> operTypes;
	
	private IrpSiteService irpSiteService;
	
	private IrpChannelService irpChannelService;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getOperId() {
		return operId;
	}

	public void setOperId(long operId) {
		this.operId = operId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}

	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}

	public List<IrpOpertype> getOperTypes() {
		return operTypes;
	}

	public void setOperTypes(List<IrpOpertype> operTypes) {
		this.operTypes = operTypes;
	}

	public IrpRightService getIrpRightService() {
		return irpRightService;
	}

	public void setIrpRightService(IrpRightService irpRightService) {
		this.irpRightService = irpRightService;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public String rightList() {
		return SUCCESS;
	}
	
	public String rightSite() {
		this.type = "SITE";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public String rightChannel() {
		this.type = "CHANNEL";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public String rightSubject() {
		this.type = "SUBJECT";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public String rightMap() {
		this.type = "MAP";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public String rightDocument() {
		this.type = "DOCUMENT";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public String rightManagement() {
		this.type = "MANAGEMENT";
		operTypes = irpOpertypeService.findOperTypeByLikeOperType(this.type);
		return SUCCESS;
	}
	
	public void findDataGridNode() {
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		List<IrpSite> irpSites = irpSiteService.findSitesBySiteType(IrpSite.SITE_TYPE_PUBLISH);
		for (int i = 0; i < irpSites.size(); i++) {
			IrpSite irpSite = irpSites.get(i);
			if(irpSite==null){
				continue;
			}
			long nSiteId = irpSite.getId();
			Map<String, Object> map = irpRightService.findObjRowRight(type, IrpSite.TABLE_NAME, nSiteId, objType, objId);
			map.put("operid", "s_"+nSiteId);
			map.put("opername", irpSite.getSitename());
			treeNode.add(map);
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	/**
	 * 获得权限配置TreeGrid节点数据
	 */
	public void findTreeGridNode() {
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		if("MANAGEMENT".equals(this.type)){
			long nId = 0L;
			if(id!=null && id.length()>0){
				String[] arrId = id.split("_");
				nId = Long.parseLong(arrId[1]);
			}
			List<IrpManagementOper> list = irpManagementOperService.findManagementOpersByParentId(nId,IrpManagementOper.FOOT);
			for (IrpManagementOper irpManagementOper : list) {
				if(irpManagementOper==null)
					continue;
				long nManagementoperId = irpManagementOper.getManagementoperid();
				Map<String, Object> map = irpRightService.findObjRowRight(type, IrpManagementOper.TABLE_NAME, nManagementoperId, objType, objId);
				map.put("operid", "m_"+nManagementoperId);
				map.put("opername", irpManagementOper.getOperdesc());
				int nCount = irpManagementOperService.findManagementOpersCountByParentId(nManagementoperId,IrpManagementOper.FOOT);
				if(nCount>0){
					map.put("state", "closed");
				}else{
					map.put("state", "open");
				}
				treeNode.add(map);
			}
		} else if("MAP".equals(this.type)){
			long nId = SysConfigUtil.getSysConfigNumValue("DOCUMENT_MAP_ID");
			if(id!=null && id.length()>0){
				String[] arrId = id.split("_");
				nId = Long.parseLong(arrId[1]);
				List<IrpChannel> list = irpChannelService.findChanlsByParentid(nId);
				for (IrpChannel knowledgeMap : list) {
					if(knowledgeMap==null)
						continue;
					long knowledgeMapId = knowledgeMap.getChannelid();
					Map<String, Object> map = irpRightService.findObjRowRight(type, IrpChannel.TABLE_NAME, knowledgeMapId, objType, objId);
					map.put("operid", "map_"+knowledgeMapId);
					map.put("opername", knowledgeMap.getChnldesc());
					int nCount = irpChannelService.findChild_Channl_CountByParentId(knowledgeMapId);
					if(nCount>0){
						map.put("state", "closed");
					}else{
						map.put("state", "open");
					}
					treeNode.add(map);
				}
			}else{
				Map<String, Object> map = irpRightService.findObjRowRight(type, IrpChannel.TABLE_NAME, nId, objType, objId);
				IrpChannel knowledgeMap = irpChannelService.finChannelByChannelid(nId);
				map.put("operid", "map_"+knowledgeMap.getChannelid());
				map.put("opername", knowledgeMap.getChnldesc());
				int nCount = irpChannelService.findChild_Channl_CountByParentId(knowledgeMap.getChannelid());
				if(nCount>0){
					map.put("state", "closed");
				}else{
					map.put("state", "open");
				}
				treeNode.add(map);
			}
			
		} else if("SUBJECT".equals(this.type)){
			long nId = SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID");
			if(id!=null && id.length()>0){
				String[] arrId = id.split("_");
				nId = Long.parseLong(arrId[1]);
				List<IrpChannel> list = irpChannelService.findChanlsByParentid(nId);
				for (IrpChannel knowledgeSubject : list) {
					if(knowledgeSubject==null)
						continue;
					long knowledgeSubjectId = knowledgeSubject.getChannelid();
					Map<String, Object> map = irpRightService.findObjRowRight(type, IrpChannel.TABLE_NAME, knowledgeSubjectId, objType, objId);
					map.put("operid", "subject_"+knowledgeSubjectId);
					map.put("opername", knowledgeSubject.getChnlname());
					int nCount = irpChannelService.findChild_Channl_CountByParentId(knowledgeSubjectId);
					if(knowledgeSubject.getChnltype()==IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT){
						map.put("iconCls", "icon-star");
					}
					if(nCount>0){
						map.put("state", "closed");
					}else{
						map.put("state", "open");
					}
					treeNode.add(map);
				}
			} else{
				Map<String, Object> map = irpRightService.findObjRowRight(type, IrpChannel.TABLE_NAME, nId, objType, objId);
				IrpChannel knowledgeSubject = irpChannelService.finChannelByChannelid(nId);
				map.put("operid", "subject_"+knowledgeSubject.getChannelid());
				map.put("opername", knowledgeSubject.getChnlname());
				int nCount = irpChannelService.findChild_Channl_CountByParentId(knowledgeSubject.getChannelid());
				if(knowledgeSubject.getChnltype()==IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT){
					map.put("iconCls", "icon-star");
				}
				if(nCount>0){
					map.put("state", "closed");
				}else{
					map.put("state", "open");
				}
				treeNode.add(map);
			}
			
		} else if(id==null || "".equals(id)){
			List<IrpSite> irpSites = irpSiteService.findSitesBySiteType(IrpSite.SITE_TYPE_PUBLISH);
			for (int i = 0; i < irpSites.size(); i++) {
				IrpSite irpSite = irpSites.get(i);
				if(irpSite==null){
					continue;
				}
				long nSiteId = irpSite.getId();
				Map<String, Object> map = irpRightService.findObjRowRight(type, IrpSite.TABLE_NAME, nSiteId, objType, objId);
				map.put("operid", "s_"+nSiteId);
				map.put("opername", irpSite.getSitename());
				int nCount = irpChannelService.findChannelCountBySiteId(nSiteId);
				if(nCount>0){
					map.put("state", "closed");
				}else{
					map.put("state", "open");
				}
				treeNode.add(map);
			}
		}else{
			String[] arrId = id.split("_");
			Long nId = Long.parseLong(arrId[1]);
			List<IrpChannel> irpChannels = null;
			if(arrId[0].equals("s")){
				irpChannels = irpChannelService.findChannelsBySiteId(nId, IrpChannel.CHANNEL_TYPE_PUBLIC);
			} else if (arrId[0].equals("c")){
				irpChannels = irpChannelService.findChanlsByParentid(nId);
			}
			if(irpChannels!=null){
				for (int i = 0; i < irpChannels.size(); i++){
					IrpChannel irpChannel = irpChannels.get(i);
					if(irpChannel==null){
						continue;
					}
					long nChannelId = irpChannel.getId();
					Map<String, Object> map = irpRightService.findObjRowRight(type, IrpChannel.TABLE_NAME, nChannelId, objType, objId);
					map.put("operid", "c_"+nChannelId);
					map.put("opername", irpChannel.getChnldesc());
					int nCount = irpChannelService.findChild_Channl_CountByParentId(nChannelId);
					if(nCount>0){
						map.put("state", "closed");
					}else{
						map.put("state", "open");
					}
					treeNode.add(map);
				}
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	public void rightEdit() {
		int nDataCount = 0;
		JSONArray jsonArray = JSONArray.fromObject(jsonData);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObj = (JSONObject)jsonArray.get(i);
			String[] arrOperId = jsonObj.getString("operid").split("_");
			String sOperType = "";
			if("s".equals(arrOperId[0])){
				sOperType=IrpSite.TABLE_NAME;
			}else if("c".equals(arrOperId[0])){
				sOperType=IrpChannel.TABLE_NAME;
			}else if("m".equals(arrOperId[0])){
				sOperType=IrpManagementOper.TABLE_NAME;
			}else if("map".equals(arrOperId[0])){
				sOperType=IrpChannel.TABLE_NAME;
			}else if("subject".equals(arrOperId[0])){
				sOperType=IrpChannel.TABLE_NAME;
			}
			Long nOperId = Long.parseLong(arrOperId[1]);
			Iterator iterator = jsonObj.keys();
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				if(isNaN(key)){
					Long nOperTypeId = Long.parseLong(key);
					int nNum = jsonObj.getInt(key);
					if(nNum==1){
						irpRightService.rightEdit(objId, objType, nOperId, sOperType, nOperTypeId);
					} else if(nNum==0) {
						irpRightService.rightDelete(objId, objType, nOperId, sOperType, nOperTypeId);
					}
					nDataCount++;
				}
			}
		}
		ActionUtil.writer(nDataCount+"");
	}
		
	public void synChildNode() {
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		Map<Long, Integer> mSynRight = new HashMap<Long, Integer>();
		Iterator iterator = jsonObj.keys();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			if(isNaN(key)){
				mSynRight.put(Long.parseLong(key), jsonObj.getInt(key));
			}
		}
		String[] arrOperId = jsonObj.getString("operid").split("_");
		long nOperId = Long.parseLong(arrOperId[1]);
		
		//根据类型循环子级进行授权
		if("s".equals(arrOperId[0])){
			List<IrpChannel> channels = irpChannelService.findChannelsBySiteId(nOperId, IrpChannel.CHANNEL_TYPE_PUBLIC);
			for (IrpChannel irpChannel : channels) {
				if(irpChannel==null)
					continue;
				synChannelChild(irpChannel,objId,objType,IrpChannel.TABLE_NAME,mSynRight);
			}
		}else if("c".equals(arrOperId[0])){
			List<IrpChannel> channels = irpChannelService.findChanlsByParentid(nOperId);
			for (IrpChannel irpChannel : channels) {
				if(irpChannel==null)
					continue;
				synChannelChild(irpChannel,objId,objType,IrpChannel.TABLE_NAME,mSynRight);
			}
		}else if("map".equals(arrOperId[0])){
			List<IrpChannel> channels = irpChannelService.findChanlsByParentid(nOperId);
			for (IrpChannel irpChannel : channels) {
				if(irpChannel==null)
					continue;
				synChannelChild(irpChannel,objId,objType,IrpChannel.TABLE_NAME,mSynRight);
			}
		}else if("subject".equals(arrOperId[0])){
			List<IrpChannel> channels = irpChannelService.findChanlsByParentid(nOperId);
			for (IrpChannel irpChannel : channels) {
				if(irpChannel==null)
					continue;
				synChannelChild(irpChannel,objId,objType,IrpChannel.TABLE_NAME,mSynRight);
			}
		}
	}
	
	/**
	 * 同步子栏目权限
	 * @param _channel
	 * @param _nObjId
	 * @param _sObjType
	 * @param _sOperType
	 * @param mSynRight
	 */
	private void synChannelChild(IrpChannel _channel, long _nObjId, String _sObjType, String _sOperType, Map<Long, Integer> mSynRight){
		if(_channel==null)
			return;
		long nChannelId = _channel.getChannelid();
		//设置栏目权限
		Iterator<Long> iterator = mSynRight.keySet().iterator();
		while (iterator.hasNext()) {
			long nKey = iterator.next().longValue();
			int nValue = mSynRight.get(nKey);
			if(nValue==1){
				irpRightService.rightEdit(objId, objType, nChannelId, _sOperType, nKey);
			}else if(nValue==0){
				irpRightService.rightDelete(objId, objType, nChannelId, _sOperType, nKey);
			}
		}
				
		//遍历子栏目
		List<IrpChannel> channels = irpChannelService.findChanlsByParentid(nChannelId);
		if(channels==null || channels.size()==0)
			return;
		for (IrpChannel irpChannel : channels) {
			if(irpChannel==null)
				continue;
			synChannelChild(irpChannel, _nObjId, _sObjType, _sOperType, mSynRight);
		}
	}
	
	
	/**
	 * 判断字符串是否是Long类型
	 * @param _sNumber
	 * @return
	 */
	private boolean isNaN(String _sNumber){
		boolean bIsNan = false;
		try {
			Long.parseLong(_sNumber);
			bIsNan = true;
		} catch (NumberFormatException e) {
			return false;
		}
		return bIsNan;
	}
}
