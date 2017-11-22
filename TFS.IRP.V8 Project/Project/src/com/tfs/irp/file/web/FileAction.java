package com.tfs.irp.file.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.apache.poi.hwpf.HWPFDocument;  
import org.apache.poi.hwpf.model.PicturesTable;  
import org.apache.poi.hwpf.usermodel.CharacterRun;  
import org.apache.poi.hwpf.usermodel.Picture;  
import org.apache.poi.hwpf.usermodel.Range; 
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DocConverter;
import com.tfs.irp.util.ExcelConverter;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.FreeMarkerUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.WordUtil;
import com.tfs.irp.util.XmlConverter;

import freemarker.template.TemplateException;
  
public class FileAction extends ActionSupport {
	private String srcfile;
	public String getSrcfile() {
		return srcfile;
	}

	public void setSrcfile(String srcfile) {
		this.srcfile = srcfile;
	}

	private IrpUserService irpUserService;
	
	private IrpAttachedService irpAttachedService;
	
	private IrpAttachedTypeService irpAttachedTypeService;
	
	private IrpChannelService irpChannelService;
	
	private IrpDocumentService irpDocumentService;
	
	private Long docid;
	
	private long userId;

	private File picFile;
	
	private String upload;

	private String picFileFileName;
	
	private int width;
	
	private String contentValue;
	
	private int height;
	
	private int x;
	
	private int y;
	
	private String imgUrl;
	
	private String fileName;
	
	private String fileTrueName;
	
	private String microblogPic;
	
	private String uploadFileName;
	
	private String CKEditorFuncNum;
	
	private String microbloguploadpic;
	
	private String swfFileName; 
	
	private File file;
	
	private long channelId;
	
	private IrpChannel channel;
	
	private int isremove;
	
	private IrpDocumentWithBLOBs document;
	
	private IrpUser irpUser;
	
	private String docids;
	
	public String getDocids() {
		return docids;
	}

	public void setDocids(String docids) {
		this.docids = docids;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public int getIsremove() {
		return isremove;
	}

	public void setIsremove(int isremove) {
		this.isremove = isremove;
	}
	
	public IrpDocumentWithBLOBs getDocument() {
		return document;
	}

	public void setDocument(IrpDocumentWithBLOBs document) {
		this.document = document;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpChannel getChannel() {
		return channel;
	}

	public File getZw() {
		return zw;
	}

	public void setZw(File zw) {
		this.zw = zw;
	}

	public void setChannel(IrpChannel channel) {
		this.channel = channel;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSwfFileName() {
		return swfFileName;
	}

	public void setSwfFileName(String swfFileName) {
		this.swfFileName = swfFileName;
	}

	public String getMicrobloguploadpic() {
		return microbloguploadpic;
	}

	public String getContentValue() {
		return contentValue;
	}

	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}

	public void setMicrobloguploadpic(String microbloguploadpic) {
		this.microbloguploadpic = microbloguploadpic;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getMicroblogPic() {
		return microblogPic;
	}

	public void setMicroblogPic(String microblogPic) {
		this.microblogPic = microblogPic;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public String getFileTrueName() {
		return fileTrueName;
	}

	public void setFileTrueName(String fileTrueName) {
		this.fileTrueName = fileTrueName;
	}

	private InputStream inputStream;

	private File zw;

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		
		this.docid = docid;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public File getPicFile() {
		return picFile;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}

	public String getPicFileFileName() {
		return picFileFileName;
	}

	public void setPicFileFileName(String picFileFileName) {
		this.picFileFileName = picFileFileName;
	}
	
	/**
	 * 直接输出Stream
	 * @return
	 * @throws FileNotFoundException
	 */
	public String readFile() throws FileNotFoundException{
		//获得文件路径
		String sFilePath = SysFileUtil.getFilePathByFileName(fileName);
		File file = new File(sFilePath);
		if(file.isFile() && file.exists()){
			//判断是否传入fileTrueName
			if(fileTrueName!=null && fileTrueName.length()>0){
		        String sAgent = ServletActionContext.getRequest().getHeader("User-Agent");  
		        if (sAgent!=null) {  
		        	sAgent = sAgent.toLowerCase();
		            try {
		            	
						if (sAgent.indexOf("msie") != -1) {
							this.fileName = java.net.URLEncoder.encode(new String(fileTrueName.getBytes("ISO-8859-1"),"UTF-8"),"UTF-8");
							this.fileName = StringUtils.replace(this.fileName, "+", "%20");
						} else if (sAgent.indexOf("firefox") != -1) {
							this.fileName = fileTrueName;
						} else if (sAgent.indexOf("chrome") != -1) {  
							this.fileName = fileTrueName;
						} else if (sAgent.indexOf("safari") != -1) {  
							this.fileName = fileTrueName;
						} else{
							
						}
						fileName = new String(fileName.getBytes(),"ISO-8859-1"); 
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        }
			}
			inputStream = new FileInputStream(file);
		}else{
			inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
		}
		return SUCCESS;
	}
	/**
	 * 直接输出Stream
	 * @return
	 * @throws FileNotFoundException
	 */
	public String readFileFormExcel() throws FileNotFoundException{
		
		
		File file = new File(srcfile);
		if(file.isFile() && file.exists()){
			//判断是否传入fileTrueName
			if(fileTrueName!=null && fileTrueName.length()>0){
		        String sAgent = ServletActionContext.getRequest().getHeader("User-Agent");  
		        if (sAgent!=null) {  
		        	sAgent = sAgent.toLowerCase();
		            try {
		            	
						if (sAgent.indexOf("msie") != -1) {
							this.fileName = java.net.URLEncoder.encode(new String(fileTrueName.getBytes("ISO-8859-1"),"UTF-8"),"UTF-8");
							this.fileName = StringUtils.replace(this.fileName, "+", "%20");
						} else if (sAgent.indexOf("firefox") != -1) {
							this.fileName = fileTrueName;
						} else if (sAgent.indexOf("chrome") != -1) {  
							this.fileName = fileTrueName;
						} else if (sAgent.indexOf("safari") != -1) {  
							this.fileName = fileTrueName;
						} 
						fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8"); 
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		        }
			}
			inputStream = new FileInputStream(file);
		}else{
			inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
		}
		return SUCCESS;
	}
	
	/**
	 * 直接输出Stream
	 * @return
	 * @throws FileNotFoundException
	 */
	public String readPicFile() throws FileNotFoundException{
		//获得文件路径
		String sFilePath = SysFileUtil.getFilePathByFileName(fileName);
		File file = new File(sFilePath);
		if(file.isFile() && file.exists()){
			//判断是否传入fileTrueName
			if(fileTrueName!=null && fileTrueName.length()>0){
				this.fileName = fileTrueName;
			}
			inputStream = FileUtil.zoomImg(file, width, height);
		}else{
			inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
		}
		String sExtName = FileUtil.findFileExt(file.getName()).toUpperCase();
		if("BMP" .equals(sExtName)){contentValue="image/bmp";}
		else if("GIF" .equals(sExtName)){contentValue="image/gif";}
		else if("IEF" .equals(sExtName)){contentValue="image/ief";}
		else if("JPEG" .equals(sExtName)){contentValue="image/jpeg";}
		else if("JPG" .equals(sExtName)){contentValue="image/jpeg";}
		else if("PNG" .equals(sExtName)){contentValue="image/png";}
		else if("TIFF" .equals(sExtName)){contentValue="image/tiff";}
		else if("TIF" .equals(sExtName)){contentValue="image/tif";}
		return SUCCESS;
	}
	
	/**
	 * 根据文档id查询文档封面
	 * 直接输出Stream
	 * @return
	 * @throws FileNotFoundException
	 */
	public String documentReadFile() throws FileNotFoundException{ 
		IrpAttached attached=irpAttachedService.getIrpATttachedByDocIDFIle(docid);
		String myFileName=attached.getAttfile(); 
		//获得文件路径
		String sFilePath = SysFileUtil.getFilePathByFileName(myFileName);
		fileName=myFileName;  
		File file = new File(sFilePath);
		if(file.isFile() && file.exists()){
			//判断是否传入fileTrueName
			if(fileTrueName!=null && fileTrueName.length()>0){
				this.fileName = fileTrueName;
				
			}
			inputStream = new FileInputStream(file);
		}else{
			inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
		}
		return SUCCESS;
	}
	/**
	 * 上传用户头像图片
	 */
	public void userPicUpload() {
		String sSaveName = SysFileUtil.saveFile(picFile, SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(picFileFileName));
		ActionUtil.writer(sSaveName);
	}
	/**
	 * 上传微知正文图片
	 */
	public void microblogPicUpload(){
		String sSaveName = SysFileUtil.saveFile(picFile,SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(picFileFileName));	
		ActionUtil.writer(sSaveName);
		
	}
	
	/**
	 * 剪切用户头像图片
	 */
	public void userPicModify() {
		boolean success = false;
		IrpUser loginUser = LoginUtil.getLoginUser();
		//获得大图地址
		String sPicPath = SysFileUtil.getFilePathByFileName(this.fileName);
		File imgfile = new File(sPicPath);
		//缩放图片
		if(imgfile.isFile() && imgfile.exists()){
			success = FileUtil.zoomImg(imgfile, x, y, width, height);
			//成功后删除原有头像文件
			if(success){
				//保存为用户文件类型
				String sFileName = SysFileUtil.saveFile(imgfile, SysFileUtil.FILE_TYPE_USER_FILE);
				//删除用户现有头像
				String sUserPic = loginUser.getUserpic();
				if(sUserPic!=null && sUserPic.length()>0){
					SysFileUtil.deleteFileByFileName(sUserPic);
				}
				//修改用户当前图片
				IrpUser updateUser = new IrpUser();
				updateUser.setUserid(loginUser.getUserid());
				updateUser.setUserpic(sFileName);
				irpUserService.userEdit(updateUser);
			}
		}
		ActionUtil.writer(success?"1":"0");
	}
	/**
	 * 剪切微知头像
	 * @return
	 */
	public void microblogPicUploadModify(){
		String msg = "";
		String disposemsg = "";
		String microbloguploadarray[] =  microbloguploadpic.toString().split(";");
		for(int i = 0;i<microbloguploadarray.length;i++){
			String sPicPath = SysFileUtil.getFilePathByFileName(microbloguploadarray[i]);
			File imgfile = new File(sPicPath);	
				msg += SysFileUtil.saveFile(imgfile, SysFileUtil.FILE_TYPE_DOC_FILE)+" ";			
			
				SysFileUtil.deleteFileByFileName(microbloguploadarray[i]);
		}
		String dispose[] = msg.replace("null"," ").trim().split("\\s+");
		for(int i=0;i<dispose.length;i++){
			disposemsg+=dispose[i]+" ";
		}
		ActionUtil.writer(disposemsg);
	}
	
	/**
	 * 删除临时图片
	 */
	public void tempPicDel() {
		SysFileUtil.deleteFileByFileName(this.fileName);
	}
	/**
	 * 删除微知上传图片
	 */
	public void microblogDeletePic(){
	 int msg = 0;	
	 
	 boolean flag =	SysFileUtil.deleteFileByFileName(microblogPic);
	 if(flag==true){
		 msg=1;
	 }else{
		 msg=0;
	 }
	 ActionUtil.writer(msg+"");
	}
	
	public void ckUpload() {
		String sPath = ServletActionContext.getRequest().getContextPath();
		File file = new File(upload);
		if(!file.exists()){
			return;
		}
		String sSaveName = SysFileUtil.saveFile(file, SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(uploadFileName));
		String sCall = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+",'"+sPath+"/file/readfile.action?fileName="+sSaveName+"','');</script>";
		ActionUtil.writer(sCall);
	}
	//上传项目文件
	public void projectFileListAdd(){
		String sSaveName = SysFileUtil.saveFile(picFile,SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(picFileFileName));		
		
		ActionUtil.writer(sSaveName+"#"+picFileFileName); 
	}
	/**
	 * 获取图片类型上传类型
	 */
	public void findPicExtSave(){
		String write="";
		Object object=irpAttachedTypeService.AttachedTypeSuffixImage(IrpAttachedType.JPG_FIELD_NAME);
		if(object!=null ){
			String extstr=object.toString();
			if(extstr!=null && extstr.trim().length()>0){
				String[] exts=extstr.split(",");
				for (int i = 0; i < exts.length; i++) {
					if(exts[i].trim().length()>0){
						write+="*."+exts[i].trim()+";";
					}
				}
			}
		}
		write=write.substring(0,write.length()-1);
		ActionUtil.writer(write);
	}
	/**
	 * 获取图片类型
	 */
	public void findPicExt(){
		String write="";
		Object object=irpAttachedTypeService.AttachedTypeSuffixImage(IrpAttachedType.JPG_FIELD_NAME);
		if(object!=null ){
			String extstr=object.toString();
			if(extstr!=null && extstr.trim().length()>0){
				String[] exts=extstr.split(",");
				for (int i = 0; i < exts.length; i++) {
					if(exts[i].trim().length()>0){
						write+="."+exts[i].trim()+",";
					}
				}
			}
		}
		write=write.substring(0,write.length()-1);
		ActionUtil.writer(write);
	}
	/**
	 * 获取上传配置
	 */
	public void findSaveFileDeploy(){
		int maxAmount=SysConfigUtil.getSysConfigNumValue("SAVE_MAX_SIZE_FILE");
		ActionUtil.writer(maxAmount+"K");
	}
	/**
	 * 获取所有可以上传的文件类型
	 */
	public void findAllFileExt(){
		String fileExtStr="";
		List<IrpAttachedType> attTypeList=irpAttachedTypeService.allAttachedTypes();
		if(attTypeList!=null && attTypeList.size()>0){
			for (IrpAttachedType irpAttachedType : attTypeList) {
				String fileext=irpAttachedType.getSuffix();
				if(fileext!=null && fileext.trim().length()>0){
					 String[] exts=fileext.split(",");
					  if(exts.length>0){
						  for (int i = 0; i < exts.length; i++) {
							  if(exts[i].trim().length()>0){
								  fileExtStr+="*."+exts[i].trim()+";";
							  }
						}
					  }
				}
			}
		}
		//去掉最后一个逗号'fileTypeExts': '*.jpg;*.jpeg;*.gif;*.png',
		fileExtStr=fileExtStr.substring(0, fileExtStr.length()-1);
		ActionUtil.writer(fileExtStr);
	}
	/**
	 * 预览文件（各种格式）
	 */
	public String readAllFile(){
		try {
			
			String sFilePath = SysFileUtil.getFilePathByFileName(fileName);
			File file = new File(sFilePath);
			if(file.isFile() && file.exists()){
				//判断是否传入fileTrueName
				if(fileTrueName!=null && fileTrueName.length()>0){
					this.fileName = fileTrueName;
				}
				inputStream = new FileInputStream(file);
			}else{
				inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
				return SUCCESS;
			}//如果在这之前没有return，代表是有文件
			String extName=FileUtil.findFileExt(fileName); 
			 if("DOC".equals(extName.toUpperCase())){contentValue="application/msword";} 
			 else if("DOCX" .equals(extName.toUpperCase())){contentValue="application/vnd.openxmlformats-officedocument.wordprocessingml.document";}
			 else if("PDF" .equals(extName.toUpperCase())){contentValue="application/pdf";}
			 else if("TXT" .equals(extName.toUpperCase())){contentValue="text/html";}
			 else if("XLS" .equals(extName.toUpperCase())){contentValue="application/vnd.ms-excel";}
			 else if("XLSX" .equals(extName.toUpperCase())){contentValue="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";}
			 else if("PPT" .equals(extName.toUpperCase())){contentValue="application/vnd.ms-powerpoint";}
			 else if("PPTX" .equals(extName.toUpperCase())){contentValue="application/vnd.openxmlformats-officedocument.presentationml.presentation";}
			 else if("BMP" .equals(extName.toUpperCase())){contentValue="image/bmp";}
			 else if("GIF" .equals(extName.toUpperCase())){contentValue="image/gif";}
			 else if("IEF" .equals(extName.toUpperCase())){contentValue="image/ief";}
			 else if("JPEG" .equals(extName.toUpperCase())){contentValue="image/jpeg";}
			 else if("JPG" .equals(extName.toUpperCase())){contentValue="image/jpeg";}
			 else if("PNG" .equals(extName.toUpperCase())){contentValue="image/png";}
			 else if("TIFF" .equals(extName.toUpperCase())){contentValue="image/tiff";}
			 else if("TIF" .equals(extName.toUpperCase())){contentValue="image/tif";}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	public String onlinePreview() {
		DocConverter docConverter = new DocConverter(fileName);
		if(docConverter.conver()){
			this.swfFileName = fileName.substring(0, fileName.lastIndexOf("."))+".swf";
		}
		return SUCCESS;
	}
	
	public void ckWordUpload() {
		String sResult = null;
		if(file!=null && file.exists() && file.isFile()){
			sResult = SysFileUtil.saveFile(file,SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(fileName));
		}
		if(sResult==null || sResult.length()==0){
			sResult = "error";
		}else{
			sResult = ServletActionContext.getRequest().getContextPath()+"/file/readfile.action?fileName="+sResult;
		}
		ActionUtil.writer(sResult);
	}
	
	public String toBatchUpload() {
		channel = irpChannelService.finChannelByChannelid(channelId);
		if(channel==null){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void convertDoc2Html() {
		DocConverter docConverter = new DocConverter(fileName);
		String sHtmlCon = docConverter.doc2html(isremove);
		if(sHtmlCon!=null){
			if(document==null){
				document = new IrpDocumentWithBLOBs();
			}
			//读取Word文件属性到IrpDocument
			WordUtil wordUtil = new WordUtil(fileName);
			wordUtil.readProperties2IrpDocument(document);
			document.setDocid(TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME));
			document.setSiteid(IrpSite.PUBLIC_SITE_ID);
			document.setChannelid(channelId);
			if(document.getDoctitle()==null || document.getDoctitle().trim().length()==0){
				document.setDoctitle(fileTrueName.substring(0, fileTrueName.lastIndexOf('.')));
			}
			document.setDochtmlcon(sHtmlCon);
			if(document.getDockeywords()==null){
				document.setDockeywords("");
			}
			if(document.getDocabstract()==null){
				document.setDocabstract("");
			}
			int nCount = irpDocumentService.addDocument(document, "", false, null, true, null, IrpDocument.DOCTYPE_DOCUMENT);
			if(nCount>0){
				ActionUtil.writer("1");
			}else{
				ActionUtil.writer("0");
			}
		}else{
			ActionUtil.writer("0");
		}
		SysFileUtil.deleteFileByFileName(fileName);
	}
	
	public String toImportXML() {
		channel = irpChannelService.finChannelByChannelid(channelId);
		if(channel==null){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void convertXml2Document() {
		XmlConverter docConverter = new XmlConverter(fileName);
		List<IrpDocumentWithBLOBs> documents = null;
		try {
			documents = docConverter.readXmlFile();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		int nCount = 0;
		if(documents!=null && documents.size()>0){
			for (IrpDocumentWithBLOBs irpDocumentWithBLOBs : documents) {
				irpDocumentWithBLOBs.setDocid(TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME));
				irpDocumentWithBLOBs.setSiteid(IrpSite.PUBLIC_SITE_ID);
				irpDocumentWithBLOBs.setChannelid(channelId);
				if(document.getCruser()!=null && document.getCruser().trim().length()>0){
					irpDocumentWithBLOBs.setCruser(document.getCruser());
				}
				if(document.getCruserid()!=null && document.getCruserid()>0L){
					irpDocumentWithBLOBs.setCruserid(document.getCruserid());
				}
				if(document.getDocauthor()!=null && document.getDocauthor().trim().length()>0){
					irpDocumentWithBLOBs.setDocauthor(document.getDocauthor());
				}
				nCount += irpDocumentService.addDocument(irpDocumentWithBLOBs, "", false, null, true, null, IrpDocument.DOCTYPE_DOCUMENT);
			}
		}
		ActionUtil.writer(String.valueOf(nCount));
		SysFileUtil.deleteFileByFileName(fileName);
	}
	
	public String toImportUserExcel() {
		return SUCCESS;
	}
	
	public void convertUserExcel() {
		ExcelConverter converter = new ExcelConverter(fileName);
		List<IrpUser> users = null;
		try {
			users = converter.readUserFile(irpUser.getStatus());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		int nCount = 0;
		if(users!=null && users.size()>0){
			for (IrpUser irpUser : users) {
				try {
					irpUserService.regUser(irpUser);
					nCount++;
				} catch (Exception e) {
				}
			}
		}
		ActionUtil.writer(String.valueOf(nCount));
		SysFileUtil.deleteFileByFileName(fileName);
	}
	
	public String exportHTML() {
		if(docids==null)
			return ERROR;
		String[] arrDocids = docids.split(",");
		List<File> files = new ArrayList<File>();
		for (String string : arrDocids) {
			try {
				long nDocId = Long.valueOf(string);
				IrpDocument expDoc = irpDocumentService.findDocumentByDocId(nDocId);
				if(expDoc!=null){
					File tempFile = FreeMarkerUtil.readDocument2HTML(expDoc);
					files.add(tempFile);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		File zipFile = null;
		try {
			zipFile = File.createTempFile("document", ".zip");
			FileUtil.compressFiles2Zip((File[])files.toArray(new File[files.size()]), zipFile.getAbsolutePath());
			inputStream = new FileInputStream(zipFile);
			fileName="document.zip";
			zipFile.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String loadMultiImg(){
		return SUCCESS;
	}
	
	public String loadpasteByWord(){
		return SUCCESS;
	}
	/*
	 * 设置ck编辑器图片数量
	 */
	public void ckEditfindSize() {
		// 从配置项中取图片上传数量
		String string=SysConfigUtil.getSysConfigValue("CKEDITPHOTOSIZE");
		int cksize = Integer.parseInt(string);
		ActionUtil.writer(cksize + "");
	}
	/*
	 * ck编辑器上传图片方法
	 */
	public void ckEditphotoUpload() throws IOException, FileUploadException {
		// 从配置项中取图片宽度
		String ckwidth = SysConfigUtil.getSysConfigValue("CKEDITPHOTOWIDTH");
		String sPath = ServletActionContext.getRequest().getContextPath();
		File files = new File(zw.getAbsolutePath());
		if (!files.exists()) {
			return;
		}
		String sSaveName = SysFileUtil.saveFile(files, SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(filename));
		String zPhotourl = sPath + "/file/readfile.action?fileName=" + sSaveName;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_width", ckwidth);
		map.put("_src", zPhotourl);
		JSONObject jsonObject = JSONObject.fromObject(map);
		// ServletActionContext.getResponse().getWriter().write(jsonObject);
		ActionUtil.writer(jsonObject.toString());
	}
	/*
	 * ck编辑器上传Word方法
	 */
	public void ckEditWordUpload() throws IOException, FileUploadException {
		// 从配置项中取图片宽度
		String sPath = ServletActionContext.getRequest().getContextPath();
		File files = new File(zw.getAbsolutePath());
		if (!files.exists()) {
			return;
		}
		List<Map<String,String>> sSaveName = SysFileUtil.saveWordFile(files, SysFileUtil.FILE_TYPE_UPLOAD_FILE, FileUtil.findFileExt(filename), true, true);
		String zPhotourl = sPath + "/file/readfile.action?fileName=" + sSaveName.get(0).get("filename");
		Map<String, Object> map = new HashMap<String, Object>();
		String contents = "";
		try {
			//contents = readPicture(sSaveName.get(0).get("filepath"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("content", contents);
		JSONObject jsonObject = JSONObject.fromObject(map);
		ActionUtil.writer(jsonObject.toString());
	}
	
	private String readPicture(String path)throws Exception{
   	 List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        FileInputStream in=new FileInputStream(new File(path));
        HWPFDocument doc=new HWPFDocument(in);   
        int length=doc.characterLength();  
        PicturesTable pTable=doc.getPicturesTable();
        for (int i=0;i<length;i++){  
          Range range=new Range(i, i+1, doc);  
          CharacterRun cr=range.getCharacterRun(0);  
          if(pTable.hasPicture(cr)){  
       	    Map<String, String> map = new HashMap<String, String>();
               Picture pic=pTable.extractPicture(cr, false);  
               String afileName=pic.suggestFullFileName();  
               OutputStream out=new FileOutputStream(new File("D:\\"+UUID.randomUUID()+afileName));  
               pic.writeImageContent(out);
               map.put("name", UUID.randomUUID()+afileName);
               map.put("position", String.valueOf(i));
               list.add(map);
         }
        }  
        
        StringBuffer sbf = new StringBuffer();
        int firstpostion = 0;
        for(int j = 0 ; j < list.size(); j++){
            sbf.append(doc.getDocumentText().substring(firstpostion - 1 > 0 ? firstpostion + 1 : 0,Integer.valueOf(list.get(j).get("position"))));
       	 sbf.append("<img src='file:///D:\\"+list.get(j).get("name")  + "' />" );
       	 firstpostion = Integer.valueOf(list.get(j).get("position"));
        }
        return sbf.toString();
   }  
}
