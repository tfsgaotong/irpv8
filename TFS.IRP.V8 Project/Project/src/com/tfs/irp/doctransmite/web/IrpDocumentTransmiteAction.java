package com.tfs.irp.doctransmite.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.doctransmite.service.IrpDocumentTransmiteService;
import com.tfs.irp.group.entity.IrpUsergroupLink;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpDocumentTransmiteAction extends ActionSupport {
private IrpDocumentTransmiteService irpDocumentTransmiteService; 
private String summary;
private Long docid;
private String userNames;
private String userIds;
private IrpGroupService irpGroupService;
public Integer getInformdescnum() {
	return informdescnum;
}


public void setInformdescnum(Integer informdescnum) {
	this.informdescnum = informdescnum;
}


private String groupNames;
private String groupIds;
private Integer informdescnum;//转发字数控制
private String transmiteType;//转发标示
public String getTransmiteType() {
	return transmiteType;
}


public IrpGroupService getIrpGroupService() {
	return irpGroupService;
}


public void setIrpGroupService(IrpGroupService irpGroupService) {
	this.irpGroupService = irpGroupService;
}


public void setTransmiteType(String transmiteType) {
	this.transmiteType = transmiteType;
}


public Long getDocid() {
	return docid;
}


public String getUserNames() {
	return userNames;
}


public String getGroupNames() {
	return groupNames;
}


public void setGroupNames(String groupNames) {
	this.groupNames = groupNames;
}


public String getGroupIds() {
	return groupIds;
}


public void setGroupIds(String groupIds) {
	this.groupIds = groupIds;
}


public void setUserNames(String userNames) {
	this.userNames = userNames;
}


public String getUserIds() {
	return userIds;
}


public void setUserIds(String userIds) {
	this.userIds = userIds;
}


public void setDocid(Long docid) {
	this.docid = docid;
}
//跳到转发页面
public String toAddTransmite(){  
	//查询推荐理由最大字数
	informdescnum=SysConfigUtil.getSysConfigNumValue("DOCUMENT_TRANSMITE_AMOUNT");
	return SUCCESS; 
}
//前台选择个人组织或者系统用户页面选择用户转发文档需要
	public String toCheckGroupUser(){
		return SUCCESS;
	}
//转发某个人的文档给某些人
public void documentFromPersonToOther(){
	int nCount=0;  
	/*
	 * 判断groupids和userids
	 */
	List<Long> focususeridsArr=new ArrayList<Long>(); 
	if(groupIds!=null && groupIds.length()>0){
		String[]focususerids=groupIds.split(","); 
		List<Long> userids=new ArrayList<Long>();
		for (int i = 0; i <focususerids.length; i++) {
			userids.add(new Long(focususerids[i]));
		} 
		List<IrpUsergroupLink> irpUsergroupLinks= irpGroupService.findUserByGroupIdList(userids);
		if(irpUsergroupLinks!=null && irpUsergroupLinks.size()>0){
			for (int i = 0; i < irpUsergroupLinks.size(); i++) {
				IrpUsergroupLink irpUsergroupLink=irpUsergroupLinks.get(i);
				if(!findEqual(irpUsergroupLink.getCruserid(), focususeridsArr)){
					focususeridsArr.add(irpUsergroupLink.getCruserid());///将组织下的用户id装入集合中去
				}
			}
		}
	} 
	if(userIds!=null && userIds.length()>0){
			String[] focStrings=userIds.split(",");
			for (int i = 0; i <focStrings.length; i++) {
				if(!findEqual(Long.valueOf(focStrings[i]), focususeridsArr)){
					focususeridsArr.add(Long.valueOf(focStrings[i]));
				}
			} 
	}
	if(focususeridsArr!=null && focususeridsArr.size()>0){
		nCount=irpDocumentTransmiteService.addDocTrans(focususeridsArr, summary, docid);
	} 
	ActionUtil.writer(String.valueOf(nCount));
}
 
public boolean findEqual(Long userId,List<Long> userIdList){
	if(userIdList!=null && userIdList.size()>0){
		for (Long userIdL : userIdList) {
			if(Long.valueOf(userId)==Long.valueOf(userIdL)){
				return true;
			}
		}
	}
	return false;
}
public IrpDocumentTransmiteService getIrpDocumentTransmiteService() {
	return irpDocumentTransmiteService;
}

public void setIrpDocumentTransmiteService(
		IrpDocumentTransmiteService irpDocumentTransmiteService) {
	this.irpDocumentTransmiteService = irpDocumentTransmiteService;
}

 

public String getSummary() {
	return summary;
}


public void setSummary(String summary) {
	this.summary = summary;
} 

}
