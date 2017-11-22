package com.tfs.irp.inform.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpInformAction extends ActionSupport {
	
	private IrpInformService irpInformService;
	
	private Integer informdescnum;
	
	private IrpInform irpInform;

	private IrpDocumentService irpDocumentService;
	
	private Integer microblogid;
	
	private Long informid;
	
	private Long microblogids;
	
	private IrpInformTypeService irpInformTypeService;
	
	private List<IrpInformType> irpInformTypelist;
	
	private IrpUserService irpUserService;
	
	private String informkeykind;
	
	public String getInformkeykind() {
		return informkeykind;
	}

	public void setInformkeykind(String informkeykind) {
		this.informkeykind = informkeykind;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpInformType> getIrpInformTypelist() {
		return irpInformTypelist;
	}

	public void setIrpInformTypelist(List<IrpInformType> irpInformTypelist) {
		this.irpInformTypelist = irpInformTypelist;
	}

	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}

	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}

	public Long getMicroblogids() {
		return microblogids;
	}

	public void setMicroblogids(Long microblogids) {
		this.microblogids = microblogids;
	}
	private IrpMicroblogService irpMicroblogService;

	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}

	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}

	public Long getInformid() {
		return informid;
	}

	public void setInformid(Long informid) {
		this.informid = informid;
	}

	public Integer getMicroblogid() {
		return microblogid;
	}

	public void setMicroblogid(Integer microblogid) {
		this.microblogid = microblogid;
	}

	public IrpInform getIrpInform() {
		return irpInform;
	}

	public void setIrpInform(IrpInform irpInform) {
		this.irpInform = irpInform;
	}

	public Integer getInformdescnum() {
		return informdescnum;
	}

	public void setInformdescnum(Integer informdescnum) {
		this.informdescnum = informdescnum;
	}

	public IrpInformService getIrpInformService() {
		return irpInformService;
	}

	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}
	
    /**
     * 加载举报框
     * @return
     */
    public String getInformFrame(){
    	
    	this.informdescnum = SysConfigUtil.getSysConfigNumValue(IrpInform.INFORMDESCNUM);
    	irpInformTypelist = this.irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE);
    	
    	
    	return SUCCESS;
    }	
    /**
     * 加载知识举报框
     */
    public String findDocumentInformFrame(){
    	this.informdescnum=SysConfigUtil.getSysConfigNumValue(IrpInform.INFORMDESCNUM);
    	irpInformTypelist=this.irpInformTypeService.findAllIrpInformType( IrpInformType.REPORT_TYPE_KNOW);
    	return SUCCESS;
    }
    /**
     * 获得知识举报数据
     * mobile
     * @return
     */
    public void findDocumentInformMobile(){
    	this.informdescnum=SysConfigUtil.getSysConfigNumValue(IrpInform.INFORMDESCNUM);
    	List<IrpInformType> irpInformTypelist=this.irpInformTypeService.findAllIrpInformType( IrpInformType.REPORT_TYPE_KNOW);
    	
    	List mobilelist = new ArrayList();
    	for (int i = 0; i < irpInformTypelist.size(); i++) {
    		IrpInformType _irpInformTypelist =  irpInformTypelist.get(i);
    		mobilelist.add("{\"formdesc\":\""+_irpInformTypelist.getInformvalue()+"\"");
    		mobilelist.add("\"formnameid\":\""+_irpInformTypelist.getInformtype()+"\"");
    		mobilelist.add("\"formkey\":\""+_irpInformTypelist.getInformkey()+"\"}");
		}
    	
    	ActionUtil.writer(mobilelist.toString());
    	
    	
    }
    
    /**
     * 增加举报内容
     */
    public void saveInform(){
   
    int msg =  this.irpInformService.addInform(irpInform,IrpInform.INFORMMICRO,informkeykind);
    ActionUtil.writer(msg+"");
    }
    /**
     * 增加微知举报内容
     * mobile
     * @return 
     */
    
    
    public void saveInformOfMobile(){
	   	IrpInform irpInform = new IrpInform();
	   	irpInform.setInformnameid(mobileinformnameid);
	   	irpInform.setInformcontent(mobileinformcontent);
	   	
        int msg =  this.irpInformService.addInform(irpInform,IrpInform.INFORMMICRO,informkeykind);
        ActionUtil.writer(msg+"");
    }
    
    /**
     * 增加加精内容
     */
    public void addClassicl(){
    	IrpInformType informType=irpInformTypeService.irpInformType(informkeykind);
		if(informType!=null){
			irpInform.setInformcontent(irpInform.getInformcontent()+","+informType.getInformvalue());
		}
		int msg =  this.irpInformService.addInform(irpInform,IrpInform.INFORMJIAJINGDOC,informkeykind);
		if(msg>0){
    		irpDocumentService.addChisslAmount(irpInform.getInformnameid(), 1);
		}
		ActionUtil.writer(msg+"");
    }
    
    /**
     * 取消加精
     */
    public void  deleteClassicl(){
    	IrpInform inform=irpInformService.findInformByExpert(IrpInform.INFORMJIAJINGDOC, informid, LoginUtil.getLoginUserId());
    	if(inform!=null){
        	int aCount=irpInformService.deleteInform(inform.getInformid());
        	if(aCount>0){
        		irpDocumentService.addChisslAmount(informid, -1);
        	}
        	ActionUtil.writer(String.valueOf(aCount));
    	}
    }
    
    /**
     * 增加知识举报内容
     */
    public void saveDocumentInforDesc(){
    	 int msg=0;
    	
    	if(informkeykind!=null &&! "0".equals(informkeykind)){
    		 msg =this.irpInformService.addInform(irpInform,IrpInform.INFORMKNOW,informkeykind);
    	}
    	 ActionUtil.writer(msg+"");
    }
    /**
     * 增加举报知识
     * @return
     */
    private String mobileinformcontent;
    private long mobileinformnameid;
    public String getMobileinformcontent() {
		return mobileinformcontent;
	}
	public void setMobileinformcontent(String mobileinformcontent) {
		this.mobileinformcontent = mobileinformcontent;
	}
	public long getMobileinformnameid() {
		return mobileinformnameid;
	}

	public void setMobileinformnameid(long mobileinformnameid) {
		this.mobileinformnameid = mobileinformnameid;
	}
	public void saveDocumentInforDescMobile(){
    	
		   	 int msg=0;
		   	IrpInform irpInform = new IrpInform();
		   	irpInform.setInformnameid(mobileinformnameid);
		   	irpInform.setInformcontent(mobileinformcontent);
		   	if(informkeykind!=null &&! "0".equals(informkeykind)){
		   		 msg =this.irpInformService.addInform(irpInform,IrpInform.INFORMKNOW,informkeykind);
		   	}
		   	 ActionUtil.writer(msg+"");
    	
    }
    
    
    
    
    
    
    /**
     * 逻辑删除举报内容
     */
    public void deleteInform(){
    int msg =  this.irpInformService.changeInformStatus(informid);
    ActionUtil.writer(msg+"");
    }
    /**
     * 修改微知状态 举报内容状态
     */
    public void changeIrpFormIllegality(){
    int msg = 0;	
    int msg_one = this.irpInformService.changeInformStatusByillegality(microblogids,IrpInform.INFORM_STATUSDELETE_ILLEGALITY);
    if(msg_one>=1){
    	msg = this.irpMicroblogService.changeMicroblogTypeByids(microblogids,IrpMicroblog.ISDELINFORM);
    }
    	
    	ActionUtil.writer(msg+"");
    }
    /**
     * 恢复非法微知
     */
    public void recoverInform(){
        int msg = this.irpMicroblogService.changeMicroblogTypeByids(microblogids,IrpMicroblog.ISDELFALSE);;	
    	this.irpInformService.deleteInformByMicroblogId(microblogids);
    	ActionUtil.writer(msg+"");
    }
    /**
     * 根据用户id获得人物对象
     * @param _microblogid
     * @return
     */
    public IrpUser getIrpUser(Long _userid){
    	IrpUser irpUser = null;
    	irpUser = this.irpUserService.findUserByUserId(_userid);
    	return irpUser;
    }
    /**
     * 根据微知id获得微知对象
     * @param _microblogid
     * @return
     */
    public IrpMicroblog getIrpMicroblog(Long _microblogid){
    	IrpMicroblog irpMicroblog = null;
    	irpMicroblog = this.irpMicroblogService.irpMicroblog(_microblogid);
    	return irpMicroblog;
    }
    public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}
}
