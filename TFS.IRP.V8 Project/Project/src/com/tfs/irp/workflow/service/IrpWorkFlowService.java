package com.tfs.irp.workflow.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.workflow.entity.IrpWorkflow;
import com.tfs.irp.workflow.entity.IrpWorkflowNode;
import com.tfs.irp.workflow.entity.IrpWorkflowObj;

public interface IrpWorkFlowService {
	/**
	 * 根据检索条件分页显示流程
	 * @param pageUtil
	 * @param _sOrderBy
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	List<IrpWorkflow> findAllWorkFlowOfPageBySearch(PageUtil pageUtil, String _sOrderBy, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据检索条件查询流程个数
	 * @param pageUtil
	 * @param _sOrderBy
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int findWorkFlowCountOfPageBySearch(String _sSearchWord, String _sSearchType);
	
	/**
	 * 编辑工作流程
	 * @param _nFlowId
	 * @param _jsonObject
	 */
	void flowEdit(long _nFlowId, JSONObject _jsonObject);
	
	/**
	 * 根据流程ID获得流程json数据
	 * @param _nFlowId
	 * @return
	 */
	JSONObject findFlowDataByFlowId(Long _nFlowId);
	
	/**
	 * 根据FlowId集合删除流程
	 * @param _sFlowIds
	 * @return
	 */
	int deleteFlowByFlowIds(String _sFlowIds);
	
	/**
	 * 获得所有的工作流对象
	 * @return
	 */
	List<IrpWorkflow> findAllWorkFlow();
	
	/**
	 * 启动工作流程
	 * @param _nFlowId
	 * @param _baseObj
	 * @return
	 */
	boolean startProcess(Long _nFlowId, IrpBaseObj _baseObj);
	
	/**
	 * 获得工作流的开始节点
	 * @param _nFlowId
	 * @return
	 */
	IrpWorkflowNode findStartFlowNodeByFlowId(Long _nFlowId);
	
	/**
	 * 获得用户ID的代办信息
	 * @param _nUserId
	 * @return
	 */
	List<Map<String, Object>> findUserFlowInfoByUserId(Long _nUserId,PageUtil pageUtil);
	
	/**
	 * 获得用户ID的代办信息数量
	 * @param _nUserId
	 * @return
	 */
	int findUserFlowInfoCountByUserId(Long _nUserId);
	
	/**
	 * 签收审核对象
	 * @param _nFlowObjId
	 * @return
	 */
	int signFlow(Long _nFlowObjId);
	
	/**
	 * 根据ID获得审核对象
	 * @param _nFlowObjId
	 * @return
	 */
	IrpWorkflowObj findFlowObjByFlowObjId(Long _nFlowObjId);
	
	/**
	 * 对节点进行审核流转
	 * @param _nFlowObjId
	 * @param _sPostDesc
	 * @param _nTransferType (1:正常流转，2：打回上一级，3：打回给作者)
	 * @return
	 */
	boolean transferProcess(Long _nFlowObjId, String _sPostDesc, int _nTransferType);
	
	/**
	 * 获得审核流转轨迹
	 * @param _nFlowId
	 * @param _nStatus
	 * @param _flowObj
	 * @return
	 */
	List<IrpWorkflowObj> findFlowObjOfStatusByObjId(Integer _nStatus, IrpBaseObj _flowObj);
	
	/**
	 * 获得根据ID获得审核节点
	 * @param _nFlowNodeId
	 * @return
	 */
	IrpWorkflowNode findFlowNodeByFlowNodeId(Long _nFlowNodeId);

	/**
	 * 根据用户ID未签收的审核数量
	 * @param _nUserId
	 * @return
	 */
	int notSignFlowCountByUserId(long _nUserId);
}
