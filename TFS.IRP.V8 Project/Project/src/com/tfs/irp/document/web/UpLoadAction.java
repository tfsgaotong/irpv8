package com.tfs.irp.document.web;

import java.io.File;
import java.util.List;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.SysFileUtil;
 

public class UpLoadAction
{ 
	private IrpAttachedService irpAttachedService;//附件service
    private List<IrpAttached> attacheds;//附件列表
    private IrpAttachedTypeService irpAttachedTypeService;
    private static final long serialVersionUID = 1L;
    private String attAchedId;//附件表主键 
    private Long docid; //文档主键 
    private static final int BUFFER_SIZE = 20 * 1024; // 20K 
    private File myFile;  
    private String fileName; //
    private String contentType; 
    public File getMyFile() {
       return myFile;
    } 
    public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}
	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	public void setMyFile(File myFile) {
       this.myFile = myFile;
    } 
    public String getFileName() {
       return fileName;
    } 
    public void setMyFileFileName(String fileName) {
       this.fileName = fileName;
    } 
    public String getContentType() {
       return contentType;
    } 
    public void setMyFileContentType(String contentType) {
       this.contentType = contentType;
    } 
    
    //处理上传过来的图片 
    public void upload()
    {   
    	
    	String newFileName=SysFileUtil.saveFile(myFile,SysFileUtil.FILE_TYPE_TEMP_FILE,FileUtil.findFileExt(fileName),false,false); 
    	ActionUtil.writer(newFileName);  
    }  
    	//是否显示时候为附件的按钮
        public void isRadio()
        {   
        	 
        	/**
        	 * 根据扩展名查询他的id看他是不是图片，如果是图片，返回一个常量，
        	 * 在页面可以利用他来显示因此时候封面的按钮
        	 * 字段editversions
        	 */
        	String isTrue="";
        	String _sFileExt=FileUtil.findFileExt(fileName);
        	Long atttypeid=irpAttachedTypeService.findAttachedTypeIdByFileExt(_sFileExt); //查询所附件类型id
        	/**
        	 * 如果附件id==0，则没有找到
        	 * 若果附件类型等于图片类型，则标示
        	 */ 
        	if(atttypeid!=null &&atttypeid.toString()!="0"){
        		isTrue="0"; 
        	}
        	if(atttypeid==IrpAttachedType.JPG_FIELD_NAME){ 
        		isTrue="1";
        	}
        	ActionUtil.writer(isTrue);   
        }  
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	public static int getBufferSize() {
		return BUFFER_SIZE;
	} 
	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
  
	public String getAttAchedId() {
		return attAchedId;
	}
	public void setAttAchedId(String attAchedId) {
		this.attAchedId = attAchedId;
	}
	public Long getDocid() {
		return docid;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	} 

}
