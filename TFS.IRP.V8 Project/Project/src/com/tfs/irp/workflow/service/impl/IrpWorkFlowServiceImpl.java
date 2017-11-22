package com.tfs.irp.workflow.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.workflow.dao.IrpWorkflowDAO;
import com.tfs.irp.workflow.dao.IrpWorkflowEmployDAO;
import com.tfs.irp.workflow.dao.IrpWorkflowNodeDAO;
import com.tfs.irp.workflow.dao.IrpWorkflowObjDAO;
import com.tfs.irp.workflow.entity.IrpWorkflow;
import com.tfs.irp.workflow.entity.IrpWorkflowEmploy;
import com.tfs.irp.workflow.entity.IrpWorkflowEmployExample;
import com.tfs.irp.workflow.entity.IrpWorkflowExample;
import com.tfs.irp.workflow.entity.IrpWorkflowNode;
import com.tfs.irp.workflow.entity.IrpWorkflowNodeExample;
import com.tfs.irp.workflow.entity.IrpWorkflowObj;
import com.tfs.irp.workflow.entity.IrpWorkflowObjExample;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class IrpWorkFlowServiceImpl implements IrpWorkFlowService {
	private IrpWorkflowDAO irpWorkflowDAO;
	
	private IrpWorkflowNodeDAO irpWorkflowNodeDAO;
	
	private IrpWorkflowEmployDAO irpWorkflowEmployDAO;
	
	private IrpWorkflowObjDAO irpWorkflowObjDAO;
	
	private IrpDocumentService irpDocumentService;

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpWorkflowObjDAO getIrpWorkflowObjDAO() {
		return irpWorkflowObjDAO;
	}

	public void setIrpWorkflowObjDAO(IrpWorkflowObjDAO irpWorkflowObjDAO) {
		this.irpWorkflowObjDAO = irpWorkflowObjDAO;
	}

	public IrpWorkflowEmployDAO getIrpWorkflowEmployDAO() {
		return irpWorkflowEmployDAO;
	}

	public void setIrpWorkflowEmployDAO(IrpWorkflowEmployDAO irpWorkflowEmployDAO) {
		this.irpWorkflowEmployDAO = irpWorkflowEmployDAO;
	}

	public IrpWorkflowNodeDAO getIrpWorkflowNodeDAO() {
		return irpWorkflowNodeDAO;
	}

	public void setIrpWorkflowNodeDAO(IrpWorkflowNodeDAO irpWorkflowNodeDAO) {
		this.irpWorkflowNodeDAO = irpWorkflowNodeDAO;
	}

	public IrpWorkflowDAO getIrpWorkflowDAO() {
		return irpWorkflowDAO;
	}

	public void setIrpWorkflowDAO(IrpWorkflowDAO irpWorkflowDAO) {
		this.irpWorkflowDAO = irpWorkflowDAO;
	}
	
	@Override
	public int findWorkFlowCountOfPageBySearch(String _sSearchWord, String  _sSearchType) {
		int nCount = 0;
		IrpWorkflowExample example = new IrpWorkflowExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andFlownameNotLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andFlowdescNotLike("%"+_sSearchWord+"%"));
		} else if("flowname".equals(_sSearchType)){
			example.createCriteria().andFlownameNotLike("%"+_sSearchWord+"%");
		} else if("flowdesc".equals(_sSearchType)){
			example.createCriteria().andFlowdescNotLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria();
		}
		try {
			nCount = irpWorkflowDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpWorkflow> findAllWorkFlowOfPageBySearch(PageUtil pageUtil, String _sOrderBy, String _sSearchWord, String  _sSearchType) {
		List<IrpWorkflow> workFlows = null;
		IrpWorkflowExample example = new IrpWorkflowExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andFlownameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andFlowdescLike("%"+_sSearchWord+"%"));
		} else if("flowname".equals(_sSearchType)){
			example.createCriteria().andFlownameLike("%"+_sSearchWord+"%");
		} else if("flowdesc".equals(_sSearchType)){
			example.createCriteria().andFlowdescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria();
		}
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "flowid desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			workFlows = irpWorkflowDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return workFlows;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void flowEdit(long _nFlowId, JSONObject _jsonObject) {
		LogUtil logUtil=null;
		//1.保存工作流信息
		if(_nFlowId==0L){
			IrpWorkflow workFlow = new IrpWorkflow();
			long nTempId = TableIdUtil.getNextId(IrpWorkflow.TABLE_NAME);
			workFlow.setFlowid(nTempId);
			String sFlowName = _jsonObject.getJSONObject("props").getJSONObject("props").getJSONObject("name").getString("value");
			workFlow.setFlowname(sFlowName);
			String sFlowDesc = _jsonObject.getJSONObject("props").getJSONObject("props").getJSONObject("desc").getString("value");
			workFlow.setFlowdesc(sFlowDesc);
			workFlow.setCrtime(new Date());
			workFlow.setCruser(LoginUtil.getLoginUser().getUsername());
			workFlow.setCruserid(LoginUtil.getLoginUserId());
			try {
				  logUtil=new LogUtil("WORKFLOW_ADD",workFlow);
				irpWorkflowDAO.insertSelective(workFlow);
				logUtil.successLogs( "添加流程["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "添加流程["+logUtil.getLogUser()+"]失败",e);
			}
			_nFlowId = nTempId;
		}else{
			IrpWorkflow workFlow = new IrpWorkflow();
			workFlow.setFlowid(_nFlowId);
			String sFlowName = _jsonObject.getJSONObject("props").getJSONObject("props").getJSONObject("name").getString("value");
			workFlow.setFlowname(sFlowName);
			String sFlowDesc = _jsonObject.getJSONObject("props").getJSONObject("props").getJSONObject("desc").getString("value");
			workFlow.setFlowdesc(sFlowDesc);
			 logUtil=new LogUtil("WORKFLOW_UPDATE",workFlow);
			try {
				irpWorkflowDAO.updateByPrimaryKeySelective(workFlow);
				logUtil.successLogs( "修改流程["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "修改流程["+logUtil.getLogUser()+"]失败",e);
			}
		}
		//2.保存工作流节点信息
		//判断工作流是否存在
		if(_nFlowId==0L){
			return;
		}
		JSONObject states = _jsonObject.getJSONObject("states");
		Iterator<String> iterator = states.keys();
		List<Long> values = new ArrayList<Long>();
		while (iterator.hasNext()) {
			String sKey = iterator.next();
			IrpWorkflowNode flowNode = new IrpWorkflowNode();
			flowNode.setFlowid(_nFlowId);
			flowNode.setNodekey(sKey);
			JSONObject rect = states.getJSONObject(sKey);
			
			long nNodeId = rect.getLong("id");
			if(nNodeId>0L){
				flowNode.setNodeid(nNodeId);
			}else{
				flowNode.setNodeid(TableIdUtil.getNextId(IrpWorkflowNode.TABLE_NAME));
				flowNode.setCrtime(new Date());
				flowNode.setCruser(LoginUtil.getLoginUser().getUsername());
				flowNode.setCruserid(LoginUtil.getLoginUserId());
			}
			rect.put("nodeid", flowNode.getNodeid());
			values.add(flowNode.getNodeid());
			
			flowNode.setNodetype(rect.getString("type"));
			flowNode.setXindex(rect.getJSONObject("attr").getInt("x"));
			flowNode.setYindex(rect.getJSONObject("attr").getInt("y"));
			flowNode.setWidth(rect.getJSONObject("attr").getInt("width"));
			flowNode.setHeight(rect.getJSONObject("attr").getInt("height"));
			flowNode.setNodename(rect.getJSONObject("props").getJSONObject("text").getString("value"));
			JSONObject descObj = rect.getJSONObject("props").getJSONObject("desc");
			flowNode.setNodedesc(descObj.isNullObject()?"":descObj.getString("value"));
			JSONObject statusObj = rect.getJSONObject("props").getJSONObject("status");
			flowNode.setDocstatus(!statusObj.isNullObject()&&!statusObj.getString("value").equals("")?statusObj.getInt("value"):null);
			try {
				if(nNodeId>0L){
					logUtil=new LogUtil("WORKFLOWNODE_UPDATE",flowNode);
					irpWorkflowNodeDAO.updateByPrimaryKeySelective(flowNode);
					logUtil.successLogs( "修改流程节点["+logUtil.getLogUser()+"]成功");
				}else{
					logUtil=new LogUtil("WORKFLOWNODE_ADD",flowNode);
					irpWorkflowNodeDAO.insertSelective(flowNode);
					logUtil.successLogs( "添加流程节点["+logUtil.getLogUser()+"]成功");
				}
				//保存节点选择组织
				JSONObject groupObj = rect.getJSONObject("props").getJSONObject("group");
				String groupIds = groupObj.isNullObject()?null:groupObj.getString("value");
				if(groupIds!=null){
					//清空现有审核对象
					IrpWorkflowEmployExample example = new IrpWorkflowEmployExample();
					example.createCriteria().andFlownodeidEqualTo(flowNode.getNodeid()).andEmployobjtypeEqualTo(IrpGroup.TABLE_NAME);
					irpWorkflowEmployDAO.deleteByExample(example);
					for (String groupId : groupIds.split(",")) {
						if(groupId==null || groupId.length()==0){
							continue;
						}
						IrpWorkflowEmploy employ = new IrpWorkflowEmploy();
						employ.setEmployid(TableIdUtil.getNextId(IrpWorkflowEmploy.TABLE_NAME));
						employ.setFlownodeid(flowNode.getNodeid());
						employ.setEmployobjid(Long.parseLong(groupId));
						employ.setEmployobjtype(IrpGroup.TABLE_NAME);
						irpWorkflowEmployDAO.insert(employ);
					}
				}
				//保存节点选择用户
				JSONObject userObj = rect.getJSONObject("props").getJSONObject("user");
				String userIds = userObj.isNullObject()?null:userObj.getString("value");
				if(userIds!=null){
					//清空现有审核对象
					IrpWorkflowEmployExample example = new IrpWorkflowEmployExample();
					example.createCriteria().andFlownodeidEqualTo(flowNode.getNodeid()).andEmployobjtypeEqualTo(IrpUser.TABLE_NAME);
					irpWorkflowEmployDAO.deleteByExample(example);
					for (String userId : userIds.split(",")) {
						if(userId==null || userId.length()==0){
							continue;
						}
						IrpWorkflowEmploy employ = new IrpWorkflowEmploy();
						employ.setEmployid(TableIdUtil.getNextId(IrpWorkflowEmploy.TABLE_NAME));
						employ.setFlownodeid(flowNode.getNodeid());
						employ.setEmployobjid(Long.parseLong(userId));
						employ.setEmployobjtype(IrpUser.TABLE_NAME);
						irpWorkflowEmployDAO.insert(employ);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//设置节点关系
		//清空之前的关系
		try {
			irpWorkflowNodeDAO.updateParentIdNull(_nFlowId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject paths = _jsonObject.getJSONObject("paths");
		Iterator<String> iterator1 = paths.keys();
		while (iterator1.hasNext()) {
			String sKey = iterator1.next();
			String from = paths.getJSONObject(sKey).getString("from");
			String to = paths.getJSONObject(sKey).getString("to");
			IrpWorkflowNode flowNode = new IrpWorkflowNode();
			flowNode.setNodeid(states.getJSONObject(to).getLong("nodeid"));
			flowNode.setParentid(states.getJSONObject(from).getLong("nodeid"));
			try {
				irpWorkflowNodeDAO.updateByPrimaryKeySelective(flowNode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//删除多余的节点
		if(values!=null && values.size()>0){
			try {
				IrpWorkflowNodeExample example = new IrpWorkflowNodeExample();
				example.createCriteria().andFlowidEqualTo(_nFlowId).andNodeidNotIn(values);
				
				irpWorkflowNodeDAO.deleteByExample(example);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public JSONObject findFlowDataByFlowId(Long _nFlowId) {
		JSONObject flowData = new JSONObject();
		//获得工作流对象
		IrpWorkflow workFlow = null;
		try {
			workFlow = irpWorkflowDAO.selectByPrimaryKey(_nFlowId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(workFlow==null){
			return new JSONObject();
		}
		//获得工作流节点
		List<IrpWorkflowNode> nodes = null;
		try {
			IrpWorkflowNodeExample example = new IrpWorkflowNodeExample();
			example.createCriteria().andFlowidEqualTo(_nFlowId);
			nodes = irpWorkflowNodeDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//构造props属性
		JSONObject nameValue = new JSONObject();
		nameValue.put("value", workFlow.getFlowname());
		JSONObject descValue = new JSONObject();
		descValue.put("value", workFlow.getFlowdesc());
		JSONObject props = new JSONObject();
		props.put("name", nameValue);
		props.put("desc", descValue);
		JSONObject prop = new JSONObject();
		prop.put("props", props);
		flowData.put("props", prop);
		//构造节点属性
		JSONObject states = new JSONObject();
		JSONObject paths = new JSONObject();
		Map<String, IrpWorkflowNode> pathsMap = new HashMap<String, IrpWorkflowNode>();
		if(nodes!=null && nodes.size()>0){
			for (IrpWorkflowNode irpWorkflowNode : nodes) {
				if(irpWorkflowNode==null){
					continue;
				}
				JSONObject rect = new JSONObject();
				rect.put("id", irpWorkflowNode.getNodeid());
				JSONObject rectText = new JSONObject();
				rectText.put("text", irpWorkflowNode.getNodename());
				rect.put("text", rectText);
				
				JSONObject attr = new JSONObject();
				attr.put("height", irpWorkflowNode.getHeight());
				attr.put("width", irpWorkflowNode.getWidth());
				attr.put("y", irpWorkflowNode.getYindex());
				attr.put("x", irpWorkflowNode.getXindex());
				rect.put("attr", attr);
				
				String sNodeType = irpWorkflowNode.getNodetype();
				rect.put("type", sNodeType);
				
				JSONObject rectProps = new JSONObject();
				JSONObject textValue = new JSONObject();
				textValue.put("value", irpWorkflowNode.getNodename());
				rectProps.put("text", textValue);
				
				JSONObject statusValue = new JSONObject();
				statusValue.put("value", irpWorkflowNode.getDocstatus()==null?"":irpWorkflowNode.getDocstatus().toString());
				rectProps.put("status", statusValue);
				
				if("node".equals(sNodeType)){
					JSONObject descValue1 = new JSONObject();
					descValue1.put("value", irpWorkflowNode.getNodedesc());
					rectProps.put("desc", descValue1);
					
					//获得审核组织信息
					String sGroupIds = "";
					String sGroupNames = "";
					List<Map<String, Object>> groupsList = null;
					try {
						groupsList = irpWorkflowEmployDAO.selectGroupInfo(irpWorkflowNode.getNodeid());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(groupsList!=null){
						for (int i = 0; i < groupsList.size(); i++) {
							Map<String, Object> map = groupsList.get(i);
							if(i>0){
								sGroupIds+=","+map.get("GVALULE").toString();
								sGroupNames+=";"+map.get("GNAME").toString();
							}else{
								sGroupIds=map.get("GVALULE").toString();
								sGroupNames=map.get("GNAME").toString();
							}
						}
					}		
					JSONObject groupValue = new JSONObject();
					groupValue.put("gname", sGroupNames);
					groupValue.put("value", sGroupIds);
					rectProps.put("group", groupValue);
					
					//获得审核用户信息
					String sUserIds = "";
					String sUserNames = "";
					List<Map<String, Object>> userList = null;
					try {
						userList = irpWorkflowEmployDAO.selectUserInfo(irpWorkflowNode.getNodeid());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(userList!=null){
						for (int i = 0; i < userList.size(); i++) {
							Map<String, Object> map = userList.get(i);
							if(i>0){
								sUserIds+=","+map.get("UVALULE").toString();
								sUserNames+=";"+map.get("UNAME").toString();
							}else{
								sUserIds=map.get("UVALULE").toString();
								sUserNames=map.get("UNAME").toString();
							}
						}
					}		
					
					JSONObject userValue = new JSONObject();
					userValue.put("uname", sUserNames);
					userValue.put("value", sUserIds);
					rectProps.put("user", userValue);
				}
				
				pathsMap.put(irpWorkflowNode.getNodeid().toString(), irpWorkflowNode);
				
				rect.put("props", rectProps);
				states.put(irpWorkflowNode.getNodekey(), rect);
			}
			//构造节点关系
			int nPathIndex = 1;
			for (IrpWorkflowNode irpWorkflowNode : nodes) {
				if(irpWorkflowNode.getParentid()==null){
					continue;
				}
				IrpWorkflowNode parentWorkflowNode = pathsMap.get(irpWorkflowNode.getParentid().toString());
				String sPathName = "path"+(nodes.size()+nPathIndex);
				nPathIndex++;
				
				JSONObject path = new JSONObject();
				path.put("to", irpWorkflowNode.getNodekey());
				path.put("from", parentWorkflowNode.getNodekey());
				
				JSONObject pathText = new JSONObject();
				pathText.put("text", "TO "+irpWorkflowNode.getNodename());
				path.put("text", pathText);
				
				JSONObject textPos = new JSONObject();
				textPos.put("x", 0);
				textPos.put("y", -10);
				path.put("textPos", textPos);
				
				path.put("dots", new JSONArray());
				
				JSONObject pathPropsText = new JSONObject();
				JSONObject pathTextValue = new JSONObject();
				pathTextValue.put("value", "");
				pathPropsText.put("text", pathTextValue);
				path.put("props", pathPropsText);
				
				paths.put(sPathName, path);
			}
		}
		flowData.put("states", states);
		flowData.put("paths", paths);
		
		return flowData;
	}
	
	@Override
	public int deleteFlowByFlowIds(String _sFlowIds) {
		List<Long> values = new ArrayList<Long>();
		String[] array = _sFlowIds.split(",");
		IrpWorkflowExample examplew = new IrpWorkflowExample();
		examplew.createCriteria().andFlowidEqualTo(Long.parseLong(array[0]));
		List<IrpWorkflow> irpForm=null;
		try {
			irpForm = irpWorkflowDAO.selectByExample(examplew);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LogUtil logUtil=new LogUtil("WORKFLOW_DEL",irpForm.get(0));
		for (int i = 0; i < array.length; i++) {
			String sFlowId = array[i];
			if(sFlowId==null || sFlowId.equals("")){
				continue;
			}
			values.add(Long.parseLong(sFlowId));
		}
		IrpWorkflowExample example = new IrpWorkflowExample();
		example.createCriteria().andFlowidIn(values);
		IrpWorkflowNodeExample example1 = new IrpWorkflowNodeExample();
		example1.createCriteria().andFlowidIn(values);
		
		int nRow = 0;
		try {
			nRow = irpWorkflowDAO.deleteByExample(example);
			irpWorkflowEmployDAO.deleteByFlowIds(_sFlowIds);
			irpWorkflowNodeDAO.deleteByExample(example1);
			logUtil.successLogs( "删除流程["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除流程["+logUtil.getLogUser()+"]失败",e);
		}
		return nRow;
	}
	
	@Override
	public List<IrpWorkflow> findAllWorkFlow() {
		List<IrpWorkflow> list = null;
		try {
			list = irpWorkflowDAO.selectByExample(new IrpWorkflowExample());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpWorkflowObj> findFlowObjOfStatusByObjId(Integer _nStatus, IrpBaseObj _flowObj) {
		List<IrpWorkflowObj> flowObjs = null;
		IrpWorkflowObjExample example = new IrpWorkflowObjExample();
		example.createCriteria().andObjidEqualTo(_flowObj.getId()).andObjtypeEqualTo(_flowObj.getTableName()).andStatusEqualTo(_nStatus);
		example.setOrderByClause("flowobjid asc");
		try {
			flowObjs = irpWorkflowObjDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flowObjs;
	}
	
	/**
	 * 获得下一个审核节点ID
	 * @param _nFlowNodeId
	 * @return
	 */
	private IrpWorkflowNode findFlowNodeByParentId(Long _nFlowNodeId){
		IrpWorkflowNodeExample example = new IrpWorkflowNodeExample();
		example.createCriteria().andParentidEqualTo(_nFlowNodeId);
		IrpWorkflowNode flowNode = null;
		try {
			List<IrpWorkflowNode> flowNodes = irpWorkflowNodeDAO.selectByExample(example);
			if(flowNodes!=null && flowNodes.size()>0){
				flowNode = flowNodes.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flowNode;
	}
	
	@Override
	public IrpWorkflowNode findFlowNodeByFlowNodeId(Long _nFlowNodeId){
		IrpWorkflowNode flowNode = null;
		try {
			flowNode = irpWorkflowNodeDAO.selectByPrimaryKey(_nFlowNodeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flowNode;
	} 
	
	@Override
	public IrpWorkflowNode findStartFlowNodeByFlowId(Long _nFlowId){
		IrpWorkflowNodeExample example = new IrpWorkflowNodeExample();
		example.createCriteria().andFlowidEqualTo(_nFlowId).andNodetypeEqualTo("start");
		IrpWorkflowNode startFlowNode = null;
		try {
			List<IrpWorkflowNode> flowNodes = irpWorkflowNodeDAO.selectByExample(example);
			if(flowNodes!=null && flowNodes.size()>0){
				startFlowNode = flowNodes.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return startFlowNode;
	}
	
	@Override
	public boolean transferProcess(Long _nFlowObjId, String _sPostDesc, int _nTransferType) {
		boolean bSuccess = false;
		IrpUser loginUser = LoginUtil.getLoginUser();
		Date crdate = new Date();
		//获得对象当前的审核信息
		IrpWorkflowObj irpWorkflowObj = null;
		try {
			irpWorkflowObj = irpWorkflowObjDAO.selectByPrimaryKey(_nFlowObjId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(irpWorkflowObj==null){
			return false;
		}
		//获得当前节点对象
		IrpWorkflowNode flowNode = null;
		try {
			flowNode = irpWorkflowNodeDAO.selectByPrimaryKey(irpWorkflowObj.getNodeid());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(flowNode==null){
			return false;
		}
		
		//添加下一个节点的信息状态
		IrpWorkflowObj newFlowObj = new IrpWorkflowObj();
		Long nTableId = TableIdUtil.getNextId(IrpWorkflowObj.TABLE_NAME);
		newFlowObj.setFlowobjid(nTableId);
		newFlowObj.setFlowid(irpWorkflowObj.getFlowid());
		
		//判断审核操作
		IrpWorkflowNode irpWorkflowNode = null;
		if(_nTransferType==1){
			//获得下一个审核节点
			irpWorkflowNode = findFlowNodeByParentId(irpWorkflowObj.getNodeid());
			if(irpWorkflowNode==null){
				return false;
			}
			newFlowObj.setNodeid(irpWorkflowNode.getNodeid());
		} else if (_nTransferType == 2){
			//获得上一个审核节点
			irpWorkflowNode = findFlowNodeByFlowNodeId(flowNode.getParentid());
			if(irpWorkflowNode==null){
				return false;
			}
			newFlowObj.setNodeid(irpWorkflowNode.getNodeid());
		} else if (_nTransferType == 3){
			irpWorkflowNode = findStartFlowNodeByFlowId(irpWorkflowObj.getFlowid());
			if(irpWorkflowNode==null){
				return false;
			}
			newFlowObj.setNodeid(irpWorkflowNode.getNodeid());
		}
		
		//判断下一个节点是否是结束节点。
		if(irpWorkflowNode.getNodetype().equals("end")){
			newFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_END);
			newFlowObj.setPostuserid(loginUser.getUserid());
			newFlowObj.setPosttime(crdate);
			newFlowObj.setTransfertype(1);
			newFlowObj.setPostdesc("结束流转");
			flowNode = irpWorkflowNode;
		} else if(irpWorkflowNode.getNodetype().equals("start")){
			newFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_HASSIGN);
			//获得审核对象的创建者
			IrpDocument document = irpDocumentService.getIrpDocumentById(irpWorkflowObj.getObjid());
			newFlowObj.setPostuserid(document.getCruserid());
		} else{
			newFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_NOTSIGN);
		}
		
		newFlowObj.setPrenodeid(irpWorkflowObj.getFlowobjid());
		newFlowObj.setObjid(irpWorkflowObj.getObjid());
		newFlowObj.setObjtype(irpWorkflowObj.getObjtype());
		newFlowObj.setCruserid(loginUser.getUserid());
		newFlowObj.setCruser(loginUser.getUsername());
		newFlowObj.setCrtime(crdate);
		try {
			irpWorkflowObjDAO.insertSelective(newFlowObj);
			bSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//结束当前审核信息状态
		if(bSuccess){
			IrpWorkflowObj updateFlowObj = new IrpWorkflowObj();
			updateFlowObj.setFlowobjid(irpWorkflowObj.getFlowobjid());
			updateFlowObj.setPostdesc(_sPostDesc);
			if(irpWorkflowObj.getPostuserid()==null || irpWorkflowObj.getPostuserid()==0){
				updateFlowObj.setPostuserid(LoginUtil.getLoginUserId());
			}
			updateFlowObj.setPosttime(crdate);
			updateFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_END);
			updateFlowObj.setTransfertype(_nTransferType);
			int nRows = 0;
			try {
				nRows = irpWorkflowObjDAO.updateByPrimaryKeySelective(updateFlowObj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			bSuccess = nRows>0;
			//如果失败了删除刚创建的审核节点
			if(!bSuccess){
				try {
					irpWorkflowObjDAO.deleteByPrimaryKey(nTableId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//修改当前审核对象状态
		if(bSuccess){
			if(IrpDocument.TABLE_NAME.equals(irpWorkflowObj.getObjtype())){
				int nRows = irpDocumentService.updateDocStatusByDocId(irpWorkflowObj.getObjid(), new Long(flowNode.getDocstatus()));
				if(flowNode.getDocstatus().longValue()==IrpDocument.PUBLISH_STATUS.longValue()){
					IrpDocumentWithBLOBs document = irpDocumentService.getIrpDocumentById(irpWorkflowObj.getObjid());
					irpDocumentService.addSolrIndex(document);
				}
				bSuccess = nRows>0;
			}
		}
		return bSuccess;
	}
	
	@Override
	public boolean startProcess(Long _nFlowId, IrpBaseObj _baseObj) {
		boolean bSuccess = false;
		//获得传入的工作流ID的开始节点
		IrpWorkflowNode startFlowNode = findStartFlowNodeByFlowId(_nFlowId);
		if(startFlowNode==null){
			return false;
		}
		//创建开始审核信息
		IrpWorkflowObj startFlowObj = new IrpWorkflowObj();
		long nFlowObjId = TableIdUtil.getNextId(IrpWorkflowObj.TABLE_NAME);
		startFlowObj.setFlowobjid(nFlowObjId);
		startFlowObj.setFlowid(_nFlowId);
		startFlowObj.setNodeid(startFlowNode.getNodeid());
		startFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_END);
		IrpUser loginUser = LoginUtil.getLoginUser();
		startFlowObj.setPostuserid(loginUser.getUserid());
		Date crdate = new Date();
		startFlowObj.setPosttime(crdate);
		startFlowObj.setPostdesc("新建审核流转");
		startFlowObj.setObjid(_baseObj.getId());
		startFlowObj.setObjtype(_baseObj.getTableName());
		startFlowObj.setCruserid(loginUser.getUserid());
		startFlowObj.setCruser(loginUser.getUsername());
		startFlowObj.setCrtime(crdate);
		startFlowObj.setTransfertype(1);
		try {
			irpWorkflowObjDAO.insertSelective(startFlowObj);
			bSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//修改当前审核对象状态
		if(bSuccess){
			if(IrpDocument.TABLE_NAME.equals(startFlowObj.getObjtype())){
				int nRows = irpDocumentService.updateDocStatusByDocId(startFlowObj.getObjid(), new Long(startFlowNode.getDocstatus()));
				bSuccess = nRows>0;
			}
		}
		
		//创建开始流程节点后创建第一个审批流程
		if(bSuccess){
			//获得下一个审核节点
			IrpWorkflowNode flowNode = findFlowNodeByParentId(startFlowNode.getNodeid());
			if(flowNode==null){
				return false;
			}
			startFlowObj = new IrpWorkflowObj();
			startFlowObj.setFlowobjid(TableIdUtil.getNextId(IrpWorkflowObj.TABLE_NAME));
			startFlowObj.setFlowid(_nFlowId);
			startFlowObj.setNodeid(flowNode.getNodeid());
			startFlowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_NOTSIGN);
			startFlowObj.setPrenodeid(nFlowObjId);
			startFlowObj.setObjid(_baseObj.getId());
			startFlowObj.setObjtype(_baseObj.getTableName());
			startFlowObj.setCruserid(loginUser.getUserid());
			startFlowObj.setCruser(loginUser.getUsername());
			startFlowObj.setCrtime(crdate);
			try {
				bSuccess = false;
				irpWorkflowObjDAO.insertSelective(startFlowObj);
				bSuccess = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bSuccess;
	}
	
	@Override
	public List<Map<String, Object>> findUserFlowInfoByUserId(Long _nUserId,PageUtil pageUtil) {
		List<Map<String, Object>> list = null;
		try {
			list = irpWorkflowObjDAO.selectWorkFlowObjByUserId(_nUserId, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int findUserFlowInfoCountByUserId(Long _nUserId) {
		int nCount = 0;
		
		try {
			nCount = irpWorkflowObjDAO.selectWorkFlowObjCountByUserId(_nUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int signFlow(Long _nFlowObjId) {
		int nRows = 0;
		IrpWorkflowObj flowObj = new IrpWorkflowObj();
		flowObj.setFlowobjid(_nFlowObjId);
		flowObj.setStatus(IrpWorkflowObj.FLOW_OBJ_STATUS_HASSIGN);
		flowObj.setPostuserid(LoginUtil.getLoginUserId());
		try {
			nRows = irpWorkflowObjDAO.updateByPrimaryKeySelective(flowObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRows;
	}

	@Override
	public IrpWorkflowObj findFlowObjByFlowObjId(Long _nFlowObjId) {
		IrpWorkflowObj flowObj = null;
		try {
			flowObj = irpWorkflowObjDAO.selectByPrimaryKey(_nFlowObjId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flowObj;
	}
	
	@Override
	public int notSignFlowCountByUserId(long _nUserId) {
		int nCount = 0;
		try {
			nCount = irpWorkflowObjDAO.selectNotSignFlowCountByUserId(_nUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
