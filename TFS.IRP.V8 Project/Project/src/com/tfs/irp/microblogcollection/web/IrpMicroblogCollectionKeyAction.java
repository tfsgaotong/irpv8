package com.tfs.irp.microblogcollection.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;

public class IrpMicroblogCollectionKeyAction extends ActionSupport {
	
	private IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService;
	
	private IrpUserService irpUserService;


    public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpMicroblogCollectionKeyService getIrpMicroblogCollectionKeyService() {
		return irpMicroblogCollectionKeyService;
	}

	public void setIrpMicroblogCollectionKeyService(
			IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService) {
		this.irpMicroblogCollectionKeyService = irpMicroblogCollectionKeyService;
	}
	private String microblogid;
	public String getMicroblogid() {
		return microblogid;
	}

	public void setMicroblogid(String microblogid) {
		this.microblogid = microblogid;
	}
	
   /**
    *增加收藏 
    */
	public void microblogCollectAdd(){
     int msg = this.irpMicroblogCollectionKeyService.addTfsMicroblogCollectionKey(Long.parseLong(microblogid), LoginUtil.getLoginUserId());
	 ActionUtil.writer(""+msg);	
		
	}
	/**
	 * 取消收藏
	 * @return
	 */
	public void deleteBlogCollection(){
	int data =	this.irpMicroblogCollectionKeyService.deleteTfsMicroblogCollectionKey(Long.parseLong(microblogid));
	ActionUtil.writer(""+data);
	}
	  /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
}
