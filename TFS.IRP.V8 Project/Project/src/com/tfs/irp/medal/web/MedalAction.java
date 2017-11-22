package com.tfs.irp.medal.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

public class MedalAction extends ActionSupport {
	
	private IrpMedalService irpMedalService;
	private IrpUserService irpUserService;
	private IrpAttachedService irpAttachedService;
	private IrpAttachedTypeService irpAttachedTypeService;
	private IrpUserMedalService irpUserMedalService;
	private IrpCategoryService irpCategoryService;
	
	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
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

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}
	private Long medalid;
	private int pageNum=1; 
	private int pageSize=8; 
	private String pageHTML;
	private IrpMedal irpMedal;
	List<IrpCategory> catogrylist;
	private List<IrpCategory> listCategory;
	private List<IrpMedal>  medallist;
	private Long categoryId;
	private String medalCategoryId;
	private String pageString;
	private List<IrpCategory> list;

	
	
	public List<IrpCategory> getList() {
		return list;
	}

	public void setList(List<IrpCategory> list) {
		this.list = list;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public String getMedalCategoryId() {
		return medalCategoryId;
	}

	public void setMedalCategoryId(String medalCategoryId) {
		this.medalCategoryId = medalCategoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<IrpMedal> getMedallist() {
		return medallist;
	}

	public void setMedallist(List<IrpMedal> medallist) {
		this.medallist = medallist;
	}

	public List<IrpCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<IrpCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public List<IrpCategory> getCatogrylist() {
		return catogrylist;
	}

	public void setCatogrylist(List<IrpCategory> catogrylist) {
		this.catogrylist = catogrylist;
	}

	public Long getMedalid() {
		return medalid;
	}

	public void setMedalid(Long medalid) {
		this.medalid = medalid;
	}

	public IrpMedal getIrpMedal() {
		return irpMedal;
	}

	public void setIrpMedal(IrpMedal irpMedal) {
		this.irpMedal = irpMedal;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	/**
	 * 添加页面跳转
	 * @return
	 */
	public String toaddmedal(){
/*	下拉框式获取	
 * String categoryMedalId = SysConfigUtil.getSysConfigValue(IrpCategory.MEDAL);
		List<Long> categoryMedalId_Longs = new ArrayList<Long>();
		categoryMedalId_Longs.add(Long.valueOf(categoryMedalId));
		catogrylist = new ArrayList<IrpCategory>();
		List<IrpCategory> list = irpCategoryService.findChildCategoryList(categoryMedalId_Longs);
		for(int i = 0 ;i < list.size() ; i++){
			catogrylist.add(list.get(i));
			for(int j = 0; j< catogrylist.size(); j++){
				List<Long> categoryMedalId_Longs_progress = new ArrayList<Long>();
				categoryMedalId_Longs_progress.add(Long.valueOf(catogrylist.get(j).getCategoryid()));
				List<IrpCategory> list_progress = irpCategoryService.findChildCategoryList(categoryMedalId_Longs_progress);
				if(list_progress.size() > 0){
					for(int k = 0 ; k < list_progress.size() ; k++){
						catogrylist.add(list_progress.get(k));
					}
				}
			}
		}
*/		
		HttpServletRequest request = ServletActionContext.getRequest();
		medalCategoryId = request.getParameter("categoryId");
		IrpCategory irpcategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(medalCategoryId));
		irpMedal = new IrpMedal();
		irpMedal.setCategoryid(medalCategoryId);
		irpMedal.setCategoryname(irpcategory.getCdesc());
		return SUCCESS;
	}
	private File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	private String fileFileName;
	private  String jsonFile;
	private String attachedids;
	

	public String getAttachedids() {
		return attachedids;
	}

	public void setAttachedids(String attachedids) {
		this.attachedids = attachedids;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public void addMedal(){
		int msg = 0;
		Date date = new Date();
		irpMedal.setMedalid(TableIdUtil.getNextId(IrpMedal.TABLE_NAME));
		irpMedal.setCrtime(date);
/*		if(file!=null){
			String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			dateStr=dateStr.replace("-", "");
			dateStr=dateStr.replace(":", "");
			dateStr=dateStr.replace(" ", "");
			String year=dateStr.substring(0, 6);
			String day=dateStr.substring(6, 8);
			String imgUrl="D:/apache/medalfile/"+year+"/"+day;
			File newfile = new File("D:\\apache\\medalfile\\"+year+"\\"+day);
			String filename=null;
			try {
				if(!newfile.exists()){
					newfile.mkdirs();  
				}
				InputStream in=new FileInputStream(file);
				HttpServletRequest _request =ServletActionContext.getRequest();
				String path=_request.getRealPath("/");
				
				filename="UM"+dateStr+this.getFileFileName();
				OutputStream os=new FileOutputStream(new File(imgUrl,filename));
				byte buffer[]=new byte[1024];
				int length=0;
				while(-1!=(length=in.read(buffer, 0, buffer.length))){
					os.write(buffer);
				}
				os.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			irpMedal.setMedalimage(filename);
		}*/
		long tableid=irpMedal.getMedalid();
		if (jsonFile != null && jsonFile != "") { 
			try{
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
				List<Long> 	_attachedids=addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, tableid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				if(_attachedids!=null){
					String medalimage="";
					for (Long long1 : _attachedids) {
						medalimage=long1+","+medalimage;
					}
					irpMedal.setMedalimage(medalimage);
				}
				irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.MEDAL);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}
		msg = irpMedalService.addMedal(irpMedal);
		ActionUtil.writer(""+msg);
	}
	//查看某个商品的所有附件
 	public void findmedalsallattache() {
 		List<IrpAttached> attacheds = new ArrayList<IrpAttached>();
		try {
			if(attachedids!=null&&attachedids.length()>0){
				String[] attachedid=attachedids.split(",");
				attacheds = new ArrayList<IrpAttached>();
				for(int j=0;j<attachedid.length;j++){
					IrpAttached attached = irpAttachedService.getAttachedByPrimary(Long.parseLong(attachedid[j]));
					attacheds.add(attached);
				}
			}
			ActionUtil.writer(JsonUtil.list2json(attacheds));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
 	}  
	 /***
		 * 添加附件信息到数据库
		 * 
		 * @param _attflag
		 * @param _sAttFile
		 * @param TypeId
		 * @param document
		 * @param _sAttDesc
		 * @param _sAttLinkAlt
		 * @param _sEditversions
		 * @param isClient
		 * @param _touChannelid
		 * @param addUserFile
		 * @return
		 */
		private ArrayList<Long> addAttQuestionFile(Integer _attflag,
				String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
				String _sAttLinkAlt, String _sEditversions, boolean isClient,
				Long _touChannelid, Boolean addUserFile) {
			ArrayList<Long> _attachedids = new ArrayList<Long>();
			String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
			File newFile = new File(filePath);
			String newFileName = "";
			if (newFile.exists()) {
				if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
						TypeId.toString())) { // 这是创建文档之后的附件名字
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
				} else {
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
				}
				// 删除临时表中的文件
				String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

				Long _attachedid = irpAttachedService.addFile(_docid, 0L,
						newFileName, _sAttLinkAlt, _sAttDesc,
						IrpAttached.MEDAL, newFilePath,
						Integer.parseInt(_sEditversions), TypeId, _attflag);
				_attachedids.add(_attachedid);
			}
			return _attachedids;

		}
	private String searchWord;
	private String searchType;
	private List<IrpMedal> listmedal;
	private List<IrpUserMedal> listlivemedal;
	private Long medalstate;
	private String orderField;
	private String orderBy;
	
	
	
	public List<IrpUserMedal> getListlivemedal() {
		return listlivemedal;
	}

	public void setListlivemedal(List<IrpUserMedal> listlivemedal) {
		this.listlivemedal = listlivemedal;
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
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	

	public List<IrpMedal> getListmedal() {
		return listmedal;
	}

	public void setListmedal(List<IrpMedal> listmedal) {
		this.listmedal = listmedal;
	}

	public Long getMedalstate() {
		return medalstate;
	}

	public void setMedalstate(Long medalstate) {
		this.medalstate = medalstate;
	}
	public String listMedal(){
		int aDataCount=irpMedalService.countMedal(searchType,searchWord);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listmedal = irpMedalService.showAllMedal(pageUtil,orderBy,searchType,searchWord,orderField);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public String listlivemedal(){
		//勋章动态列表
		IrpUser loginuser = LoginUtil.getLoginUser();
		int aDataCount=irpUserMedalService.countUserMedal(loginuser.getUserid());
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listlivemedal = irpUserMedalService.showAllUserMedal(pageUtil, null,null);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	private String medalids;
	
	public String getMedalids() {
		return medalids;
	}

	public void setMedalids(String medalids) {
		this.medalids = medalids;
	}

	public void deleteMoreMedal(){
		int msg = 0;
		if(medalids!=""&&medalids!=null){
    		String[] array =medalids.split(",");
    		for(int j=0;j<array.length;j++){
    			irpMedalService.deleteMedal(Long.parseLong(array[j]));
    			msg++;
    		}
		}
		ActionUtil.writer(""+msg);
	}
	
	public void deleteMedal(){
		int msg = 0;
		msg = irpMedalService.deleteMedal(medalid);
		ActionUtil.writer(""+msg);
	}

	public String findMedalById(){
		String categoryMedalId = SysConfigUtil.getSysConfigValue(IrpCategory.MEDAL);
		List<Long> categoryMedalId_Longs = new ArrayList<Long>();
		categoryMedalId_Longs.add(Long.valueOf(categoryMedalId));
		catogrylist = new ArrayList<IrpCategory>();
		List<IrpCategory> list = irpCategoryService.findChildCategoryList(categoryMedalId_Longs);
		for(int i = 0 ;i < list.size() ; i++){
			catogrylist.add(list.get(i));
			for(int j = 0; j< catogrylist.size(); j++){
				List<Long> categoryMedalId_Longs_progress = new ArrayList<Long>();
				categoryMedalId_Longs_progress.add(Long.valueOf(catogrylist.get(j).getCategoryid()));
				List<IrpCategory> list_progress = irpCategoryService.findChildCategoryList(categoryMedalId_Longs_progress);
				if(list_progress.size() > 0){
					for(int k = 0 ; k < list_progress.size() ; k++){
						catogrylist.add(list_progress.get(k));
					}
				}
			}
		}
		irpMedal=irpMedalService.findMedalByMedalid(medalid);
		return SUCCESS;
	}
	public String getMedalDetialById(){
		int aDataCount=irpMedalService.countMedal(searchType,searchWord);
		PageUtil pageUtil = new PageUtil(1, 10, aDataCount);
		listmedal = irpMedalService.showAllMedal(pageUtil,orderBy,searchType,searchWord,orderField);
		irpMedal=irpMedalService.findMedalByMedalid(medalid);
		return SUCCESS;
	}
	/**
	 * 获得商品图片
	 * @param _docid
	 * @return
	 */
	public String coverPath(String attachedids){
		String filePath="";
		String[] _attachedid=attachedids.split(",");
		for(int j=0;j<_attachedid.length;j++){
			Long attachedid=Long.parseLong(_attachedid[j]);
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(attachedid); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_300X300");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			} 
		}
		return filePath;
	}
	public void updateMedal(){
		int msg = 0;
	/*	if(file!=null){
			Date date = new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			dateStr=dateStr.replace("-", "");
			dateStr=dateStr.replace(":", "");
			dateStr=dateStr.replace(" ", "");
			String year=dateStr.substring(0, 6);
			String day=dateStr.substring(6, 8);
			String imgUrl="D:/apache/medalfile/"+year+"/"+day;
			File newfile = new File("D:\\apache\\medalfile\\"+year+"\\"+day);
			String filename=null;
			try {
				if(!newfile.exists()){
					newfile.mkdirs();  
				}
				InputStream in=new FileInputStream(file);
				HttpServletRequest _request =ServletActionContext.getRequest();
				String path=_request.getRealPath("/");
				
				filename="UM"+dateStr+this.getFileFileName();
				OutputStream os=new FileOutputStream(new File(imgUrl,filename));
				byte buffer[]=new byte[1024];
				int length=0;
				while(-1!=(length=in.read(buffer, 0, buffer.length))){
					os.write(buffer);
				}
				os.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			irpMedal.setMedalimage(filename);
		}*/
		
		long tableid=irpMedal.getMedalid();
		if (jsonFile != null && jsonFile != "") { 
			try{
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
				List<Long> 	_attachedids=addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, tableid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				if(_attachedids!=null){
					String medalimage="";
					for (Long long1 : _attachedids) {
						medalimage=long1+","+medalimage;
					}
					irpMedal.setMedalimage(medalimage);
				}
				irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.MEDAL);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}else{
			irpMedal.setMedalimage("");
		}
		msg = irpMedalService.updateMedalByMedalid(irpMedal);
		ActionUtil.writer(""+msg);
	}
	
	public String tomedalcategory(){
		HttpServletRequest request = ServletActionContext.getRequest();
		categoryId = Long.valueOf(request.getParameter("categoryId"));
		String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.MEDAL);
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("parentid", categoryQuestionId);
		list=irpCategoryService.findCategoryByConditions(map);
		listCategory=irpCategoryService.currentCategory(categoryId,listCategory,Long.parseLong(categoryQuestionId));
		return SUCCESS;
	}
	
	//分类显示勋章
	public String listmedalClient(){
		HttpServletRequest request = ServletActionContext.getRequest();
		medalCategoryId = String.valueOf(request.getParameter("categoryId"));
		if(this.getSearchWord()==null||this.getSearchWord().trim().length()==0||this.getSearchWord().equals("null")){
    		this.searchWord=null;
    	}else{
    		this.setSearchWord(searchWord);
    	}
		PageUtil pageUtil =null;
		int aDataCount=irpMedalService.countshowAllMedalByCategory("medalname", searchWord, medalCategoryId);
		String page = String.valueOf(request.getParameter("pageString"));
		pageUtil = new PageUtil( page == "" || page == null || page.equals("null")?this.pageNum:Integer.valueOf(request.getParameter("pageString")),
					this.pageSize, aDataCount);
		listmedal = irpMedalService.showAllMedalByCategory(pageUtil, "", "medalname", searchWord, "",medalCategoryId);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	
	
	public List<IrpCategory> findChildCategoryByParentId(long _nParentId){
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("parentid", _nParentId);
		List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
		return childCategory;
	}
	
	 /**
     * 更新右侧内容
     * @return
     */
    public String tolistmedalcategoryBackground() {
    	HttpServletRequest request = ServletActionContext.getRequest();
		medalCategoryId = String.valueOf(request.getParameter("categoryId")!= null ?request.getParameter("categoryId"):"");
		searchWord = String.valueOf(request.getParameter("searchWord"));
		pageString = String.valueOf(request.getParameter("pageNum"));
		return SUCCESS;
    }
    
}