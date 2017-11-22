package com.tfs.irp.workflow.web;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.workflow.entity.IrpWorkflow;
import com.tfs.irp.workflow.entity.IrpWorkflowNode;
import com.tfs.irp.workflow.entity.IrpWorkflowObj;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class WorkFlowAction extends ActionSupport {
	private IrpWorkFlowService irpWorkFlowService;
	
	private IrpDocStatusService irpDocStatusService;
	
	private IrpDocumentService irpDocumentService;
	
	private IrpAttachedService irpAttachedService;
	
	private IrpUserService irpUserService;
	
	private List<IrpWorkflow> irpWorkflows;
	
	private List<IrpWorkflowObj> irpWorkflowObjs;
	
	private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private String pageHTML;

	private String searchWord;
	
	private String searchType;
	
	private String flowData;
	
	private long flowId;
	
	private long flowObjId;
	
	private String flowIds;
	
	private String statusData;
	
	private long docId;
	
	private IrpDocumentWithBLOBs irpDocument;
	
	private IrpWorkflowObj flowObj;
	
	private IrpWorkflowNode flowNode;
	
	private List<IrpAttached> attacheds;
	
	private String postDesc;
	
	private int transferType;
	
	public IrpWorkflowNode getFlowNode() {
		return flowNode;
	}

	public void setFlowNode(IrpWorkflowNode flowNode) {
		this.flowNode = flowNode;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpWorkflowObj> getIrpWorkflowObjs() {
		return irpWorkflowObjs;
	}

	public void setIrpWorkflowObjs(List<IrpWorkflowObj> irpWorkflowObjs) {
		this.irpWorkflowObjs = irpWorkflowObjs;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}

	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}

	public IrpDocumentWithBLOBs getIrpDocument() {
		return irpDocument;
	}

	public void setIrpDocument(IrpDocumentWithBLOBs irpDocument) {
		this.irpDocument = irpDocument;
	}

	public IrpWorkflowObj getFlowObj() {
		return flowObj;
	}

	public void setFlowObj(IrpWorkflowObj flowObj) {
		this.flowObj = flowObj;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public long getDocId() {
		return docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public long getFlowObjId() {
		return flowObjId;
	}

	public void setFlowObjId(long flowObjId) {
		this.flowObjId = flowObjId;
	}

	private List<Map<String, Object>> flowInfos;

	public List<Map<String, Object>> getFlowInfos() {
		return flowInfos;
	}

	public void setFlowInfos(List<Map<String, Object>> flowInfos) {
		this.flowInfos = flowInfos;
	}

	public String getStatusData() {
		return statusData;
	}

	public void setStatusData(String statusData) {
		this.statusData = statusData;
	}

	public IrpDocStatusService getIrpDocStatusService() {
		return irpDocStatusService;
	}

	public void setIrpDocStatusService(IrpDocStatusService irpDocStatusService) {
		this.irpDocStatusService = irpDocStatusService;
	}

	public String getFlowIds() {
		return flowIds;
	}

	public void setFlowIds(String flowIds) {
		this.flowIds = flowIds;
	}

	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public String getFlowData() {
		return flowData;
	}

	public void setFlowData(String flowData) {
		this.flowData = flowData;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public List<IrpWorkflow> getIrpWorkflows() {
		return irpWorkflows;
	}

	public void setIrpWorkflows(List<IrpWorkflow> irpWorkflows) {
		this.irpWorkflows = irpWorkflows;
	}

	public IrpWorkFlowService getIrpWorkFlowService() {
		return irpWorkFlowService;
	}

	public void setIrpWorkFlowService(IrpWorkFlowService irpWorkFlowService) {
		this.irpWorkFlowService = irpWorkFlowService;
	}
	
	/**
	 * 流程管理列表
	 * @return
	 */
	public String flowList() {
		int nDataCount = irpWorkFlowService.findWorkFlowCountOfPageBySearch(searchWord, searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "";
		if(this.orderField!=null && !orderField.isEmpty()){
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		irpWorkflows = irpWorkFlowService.findAllWorkFlowOfPageBySearch(pageUtil, sOrderByClause, searchWord, searchType);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public String flowEdit() {
		JSONObject flow =null;
		if(flowId>0L){
			flow = irpWorkFlowService.findFlowDataByFlowId(flowId);
		}else{
			flow = new JSONObject();
		}
		List<IrpDocstatus> statusList = irpDocStatusService.findAllStatus();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < statusList.size(); i++) {
			IrpDocstatus docstatus = statusList.get(i);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", docstatus.getSdesc()==null?docstatus.getSname():docstatus.getSdesc());
			jsonObj.put("value", docstatus.getStatusid());
			jsonArray.add(jsonObj);
		}
		statusData = jsonArray.toString();
		flowData = flow.toString();
		return SUCCESS;
	}
	
	public void flowEditDowith() {
		JSONObject jsonObject = JSONObject.fromObject(flowData);
		irpWorkFlowService.flowEdit(flowId, jsonObject);
	}
	
	public void flowDeleteDowith() {
		int nCount = irpWorkFlowService.deleteFlowByFlowIds(flowIds);
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	public String findFlowInfo() {
		long nUserId = LoginUtil.getLoginUserId();
		int nDataCount = irpWorkFlowService.findUserFlowInfoCountByUserId(nUserId);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		flowInfos = irpWorkFlowService.findUserFlowInfoByUserId(nUserId, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("findFlowConn(#PageNum#)");
		return SUCCESS;
	}
	
	public void loadNotSignFlow() {
		int nCount = irpWorkFlowService.notSignFlowCountByUserId(LoginUtil.getLoginUserId());
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	public void signFlow() {
		int nRows = irpWorkFlowService.signFlow(flowObjId);
		ActionUtil.writer(nRows>0?"1":"0");
	}
	
	public String flowDoc() {
		this.flowObj = irpWorkFlowService.findFlowObjByFlowObjId(this.flowObjId);
		if(flowObj!=null){
			this.flowNode = irpWorkFlowService.findFlowNodeByFlowNodeId(flowObj.getNodeid());
		}
		this.irpDocument = irpDocumentService.findDocumentByDocId(this.docId);
		 //**调用分割文章方法，进行对文章进行分页*/
		irpDocument.setDochtmlcon( pageDocument(irpDocument.getDochtmlcon()));
		this.attacheds = irpAttachedService.getAttachedByAttDocId(this.docId,0);
		return SUCCESS;
	}
	/**知识的分页
	*/
	public String pageDocument(String docHtmlCon){
		String outStr="";
		String returnValue="";
		int nCount=1;
		
		if(docHtmlCon!=null && !docHtmlCon.trim().equals("")){
			docHtmlCon=docHtmlCon.trim();
			String pattern = "<div style=\"page-break-after:.*always;?\"><span style=\"display:none.?\">&nbsp;</span></div>";
			String[] strSiplit=docHtmlCon.split(pattern);
			
			nCount=strSiplit.length;
			if(nCount>1){
				outStr = "<div id='page_break'>"; 
					for(int i = 1; i<=nCount;i++){
						if(i<=1){ 
							outStr += "<div id='page_"+String.valueOf(i)+"'>"+strSiplit[i-1]+"</div>";
						}else{
							outStr += "<div id='page_"+String.valueOf(i)+"' class='collapse'>"+strSiplit[i-1]+"</div>";
						} 
					}
					
					outStr += "<div class='num'>"; 
					//上一页
					outStr+="<a href='#documenttop'>上一页</a>";
					
					for(int j=1; j<=nCount;j++){
						outStr += "<li id='"+String.valueOf(j)+"'><a href='#documenttop'>"+String.valueOf(j)+"</a></li>";
					}
					//下一页
					outStr+="<a href='#documenttop'>下一页</a>&nbsp&nbsp;";
					outStr+="共"+nCount+"页";
					outStr+="&nbsp&nbsp;<a href='#documenttop' onclick='showAllData()' id='show'>查看全文</a>";
					outStr += "</div>"; 
				outStr += "</div>";
				returnValue = outStr;
			}else{
				returnValue = docHtmlCon;
			}  
		}
		//共几页
		return returnValue;
	} 
	public void transferProcess() {
		boolean bSuccess = irpWorkFlowService.transferProcess(flowObjId, postDesc, transferType);
		ActionUtil.writer(bSuccess?"1":"0");
	}
	
	public String flowPath() {
		irpWorkflowObjs = irpWorkFlowService.findFlowObjOfStatusByObjId(IrpWorkflowObj.FLOW_OBJ_STATUS_END, irpDocumentService.findDocumentByDocId(docId));
		return SUCCESS;
	}
	
	public String findFlowNodeNameByFlowNodeId(long nFlowNodeId) {
		IrpWorkflowNode flowNode = irpWorkFlowService.findFlowNodeByFlowNodeId(nFlowNodeId);
		if(flowNode!=null){
			return flowNode.getNodename();
		}else{
			return "";
		}
	}
	
	public String findUserNameByUserId(long nUserId) {
		IrpUser irpUser = irpUserService.findUserByUserId(nUserId);
		if(irpUser!=null){
			return irpUser.getTruename()==null?irpUser.getUsername():irpUser.getTruename();
		}else{
			return "";
		}
	}
	
	public String flowManage(){
		return SUCCESS;
	}
}
