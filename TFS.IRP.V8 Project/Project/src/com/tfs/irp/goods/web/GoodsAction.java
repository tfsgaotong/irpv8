package com.tfs.irp.goods.web;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;





import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.util.filter.LoginCheckFilter;

public class GoodsAction extends ActionSupport {
	
	private IrpGoodsService irpGoodsService;
	private IrpDocumentService irpDocumentService;
	private IrpAttachedTypeService irpAttachedTypeService;
	private IrpAttachedService irpAttachedService;
	private IrpMedalService irpMedalService;
	
	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public IrpGoodsService getIrpGoodsService() {
		return irpGoodsService;
	}

	public void setIrpGoodsService(IrpGoodsService irpGoodsService) {
		this.irpGoodsService = irpGoodsService;
	}
	
	
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}


	private Long goodsid;
	private int pageNum=1; 
	private int pageSize=8; 
	private String pageHTML;
	private IrpGoods irpGoods;
	
	public IrpGoods getIrpGoods() {
		return irpGoods;
	}

	public void setIrpGoods(IrpGoods irpGoods) {
		this.irpGoods = irpGoods;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
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
	private File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	private String fileFileName;
	

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	private List<IrpMedal> listMedal;
	
	public List<IrpMedal> getListMedal() {
		return listMedal;
	}

	public void setListMedal(List<IrpMedal> listMedal) {
		this.listMedal = listMedal;
	}

	/**
	 * 添加页面跳转
	 * @return
	 */
	public String toaddgoods(){
		listMedal=irpMedalService.listMedal();
		return SUCCESS;
		
	}
	private String jsonFile;

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}
	private String goodsno;
	
	 public String getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(String goodsno) {
		this.goodsno = goodsno;
	}

	/**
     * 验证编码唯一性
     */
    public void isexitcode(){
        int msg=0;
   	    if(goodsno!=null&&goodsno!=""){
   		
   		boolean flag=this.irpGoodsService.isexitcode(goodsno);
   		if(!flag){
   			msg=1;//意思是不可用
   		}
   		
   	}
   
   	ActionUtil.writer(msg+"");
   
    	
    }
	public void addGoods(){
		int msg = 0;
		Date date = new Date();
		Long id=TableIdUtil.getNextId(IrpGoods.TABLE_NAME);
		irpGoods.setGoodsid(id);
		irpGoods.setCrtime(date);
		long tableid=irpGoods.getGoodsid();
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
					irpGoods.setGoodsimage(medalimage);
				}
				irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.GOODS);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}else{
			irpGoods.setGoodsimage("");
		}
		IrpMedal irpMedal=null;
		if(irpGoods.getMedalid()!=0){
			
			irpMedal=irpMedalService.findMedalByMedalid(irpGoods.getMedalid());
			if(irpMedal.getGoodsid()==null||irpMedal.getGoodsid().equals("")){
				goodsids=id+"";
			}else{
				goodsids=irpMedal.getGoodsid()+","+id;
			}
			irpMedal.setGoodsid(goodsids);
			irpGoods.setMedalname(irpMedal.getMedalname());
		}
		int newreorder = irpGoods.getReorder();
		IrpGoods Goods=irpGoodsService.selectByeqReorder(newreorder);
		if(Goods!=null){
			List<IrpGoods> list=irpGoodsService.selectByReorder(newreorder);
			if(list!=null&&list.size()>0){
				for (IrpGoods irpGoods : list) {
					irpGoods.setReorder(irpGoods.getReorder()+1);
					irpGoodsService.updateGoodsByGoodsid(irpGoods);
				}
			}
		}
		
		msg = irpGoodsService.addGoods(irpGoods);
		if(irpGoods.getMedalid()!=0){
			irpMedalService.updateMedalByMedalid(irpMedal);
		}
		ActionUtil.writer(""+msg);
	}
	private String searchWord;
	private String searchType;
	private String orderField;
	private String orderBy;
	private List<IrpGoods> listgoods;
	private Long coverstate;
	
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

	public Long getCoverstate() {
		return coverstate;
	}

	public void setCoverstate(Long coverstate) {
		this.coverstate = coverstate;
	}

	public List<IrpGoods> getListgoods() {
		return listgoods;
	}

	public void setListgoods(List<IrpGoods> listgoods) {
		this.listgoods = listgoods;
	}
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	private String imagesrc;
	

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	//后台显示商品列表
	public String listGoods(){
		int aDataCount=irpGoodsService.countGoods(searchType,searchWord);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listgoods = irpGoodsService.listAllGoods(pageUtil,orderBy,searchType,searchWord,orderField);
	
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	private Long userid;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	private IrpUserCovertGoodsService irpUserCovertGoodsService;
	
	public IrpUserCovertGoodsService getIrpUserCovertGoodsService() {
		return irpUserCovertGoodsService;
	}

	public void setIrpUserCovertGoodsService(
			IrpUserCovertGoodsService irpUserCovertGoodsService) {
		this.irpUserCovertGoodsService = irpUserCovertGoodsService;
	}
	private List<IrpUserCovertGoods> listcovertrecord;
	
	
	public List<IrpUserCovertGoods> getListcovertrecord() {
		return listcovertrecord;
	}

	public void setListcovertrecord(List<IrpUserCovertGoods> listcovertrecord) {
		this.listcovertrecord = listcovertrecord;
	}
	
	//前台显示已上架商品
	public String listGoodsClient(){
		if(this.getSearchWord()==null||this.getSearchWord().trim().length()==0){
    		this.searchWord=null;
    	}else{
    		this.setSearchWord(searchWord);
    	}
		PageUtil pageUtil =null;
		if(null != coverstate){
			//为了分类表与商品表状态转换
			if(coverstate == 14){
				coverstate = null;
			}
		}
		if(coverstate!=null&&coverstate==1){
			int aDataCount=irpGoodsService.countGoodsByUserid(LoginUtil.getLoginUserId(),searchWord);
			pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			String _orderby="reorder desc,crtime desc";
			listgoods = irpGoodsService.showAllGoodsByUserid(pageUtil,LoginUtil.getLoginUserId(),searchWord,_orderby);
		}else{
			int aDataCount=irpGoodsService.countGoods(coverstate,searchWord);
			pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			String _orderby="reorder desc,crtime desc";
			listgoods = irpGoodsService.showAllGoods(pageUtil,coverstate,searchWord,_orderby);
		}
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
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
	private String goodsids;
	
	public String getGoodsids() {
		return goodsids;
	}

	public void setGoodsids(String goodsids) {
		this.goodsids = goodsids;
	}

	public void deleteMoreGoods(){
		int msg = 0;
		if(goodsids!=""&&goodsids!=null){
    		msg=irpGoodsService.deleteMoreGoods(goodsids);
		}
		ActionUtil.writer(""+msg);
	}
	
	public void deleteGoods(){
		int msg = 0;
		msg = irpGoodsService.deleteGoods(goodsid);
		ActionUtil.writer(""+msg);
	}

	public String findGoodsById(){
		listMedal=irpMedalService.listMedal();
		irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
		attachedids=irpGoods.getGoodsimage();
		return SUCCESS;
	}
	public void updateGoods(){
		int msg = 0;
		boolean isClient = false;
		long tableid=irpGoods.getGoodsid();
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
					irpGoods.setGoodsimage(medalimage);
				}
				irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.GOODS);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}else{
			irpGoods.setGoodsimage("");
		}
		IrpMedal irpMedal=null;
		if(irpGoods.getMedalid()!=0){
			
			irpMedal=irpMedalService.findMedalByMedalid(irpGoods.getMedalid());
			if(irpMedal.getGoodsid()==null||irpMedal.getGoodsid().equals("")){
				goodsids=irpGoods.getGoodsid()+"";
			}else{
				goodsids=irpMedal.getGoodsid()+","+irpGoods.getGoodsid();
			}
			irpMedal.setGoodsid(goodsids);
			irpGoods.setMedalname(irpMedal.getMedalname());
		}
		int newreorder = irpGoods.getReorder();
		IrpGoods goods=irpGoodsService.findGoodsByGoodsid(irpGoods.getGoodsid());
		int oldreorder = goods.getReorder();
		if(oldreorder!=newreorder){
			IrpGoods Good=irpGoodsService.selectByeqReorder(newreorder);
			if(Good!=null){
				List<IrpGoods> list=irpGoodsService.selectByReorder(newreorder);
				if(list!=null&&list.size()>0){
					for (IrpGoods irpGood : list) {
						irpGood.setReorder(irpGood.getReorder()+1);
						irpGoodsService.updateGoodsByGoodsid(irpGood);
					}
				}
			}
		}
		msg = irpGoodsService.updateGoodsByGoodsid(irpGoods);
		if(irpGoods.getMedalid()!=0){
			irpMedalService.updateMedalByMedalid(irpMedal);
		}
		ActionUtil.writer(""+msg);
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
					IrpAttached.GOODS, newFilePath,
					Integer.parseInt(_sEditversions), TypeId, _attflag);
			_attachedids.add(_attachedid);
		}
		return _attachedids;

	}
	private Long stocknum;
	
	

	public Long getStocknum() {
		return stocknum;
	}

	public void setStocknum(Long stocknum) {
		this.stocknum = stocknum;
	}

	public void findstocknum(){
		irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
		stocknum=irpGoods.getStocknum();
	    ActionUtil.writer(stocknum+"");
	 }
	
	private String attachedids;
	
	public String getAttachedids() {
		return attachedids;
	}

	public void setAttachedids(String attachedids) {
		this.attachedids = attachedids;
	}

		//查看某个商品的所有附件
	 	public void findgoodsallattache() {
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
	 	public String getGoodsDetialById(){
			listMedal=irpMedalService.listMedal();
			irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
			attachedids=irpGoods.getGoodsimage();
			return SUCCESS;
		}
	 	
	 	//热销商品
		public String hotGoodsList(){
			PageUtil pageUtil =null;
			int aDataCount=irpGoodsService.countGoods(searchType,searchWord);
			pageUtil = new PageUtil(this.pageNum, 5, aDataCount);
			String _orderby="salesvolume,crtime desc";
			listgoods = irpGoodsService.showAllGoods(pageUtil,null,null,_orderby);
			//this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			return SUCCESS;
			
		}
}