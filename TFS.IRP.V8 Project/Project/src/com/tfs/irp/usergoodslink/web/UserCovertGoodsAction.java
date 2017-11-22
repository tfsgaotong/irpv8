package com.tfs.irp.usergoodslink.web;


import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class UserCovertGoodsAction extends ActionSupport {
	private IrpUserCovertGoodsService irpUserCovertGoodsService;
	private IrpUserService irpUserService;
	private IrpGoodsService irpGoodsService;
	private IrpUserValueLinkService irpUserValueLinkService;
	private IrpMessageContentService irpMessageContentService;
	private IrpUserMedalService irpUserMedalService;
	
	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}

	public IrpGoodsService getIrpGoodsService() {
		return irpGoodsService;
	}

	public void setIrpGoodsService(IrpGoodsService irpGoodsService) {
		this.irpGoodsService = irpGoodsService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpUserCovertGoodsService getIrpUserCovertGoodsService() {
		return irpUserCovertGoodsService;
	}

	public void setIrpUserCovertGoodsService(
			IrpUserCovertGoodsService irpUserCovertGoodsService) {
		this.irpUserCovertGoodsService = irpUserCovertGoodsService;
	}
	private Long covertid;
	private String covertids;
	private int pageNum=1; 
	private int pageSize=10; 
	private String pageHTML;
	private IrpUserCovertGoods covert;

	public Long getCovertid() {
		return covertid;
	}

	public void setCovertid(Long covertid) {
		this.covertid = covertid;
	}

	
	public String getCovertids() {
		return covertids;
	}

	public void setCovertids(String covertids) {
		this.covertids = covertids;
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

	public IrpUserCovertGoods getCovert() {
		return covert;
	}

	public void setCovert(IrpUserCovertGoods covert) {
		this.covert = covert;
	}
	private int coverttype;////判断用积分兑换还是用勋章兑换
	private Long goodsid;
	private Long covertState;
	
	
	public Long getCovertState() {
		return covertState;
	}

	public void setCovertState(Long covertState) {
		this.covertState = covertState;
	}

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public int getCoverttype() {
		return coverttype;
	}

	public void setCoverttype(int coverttype) {
		this.coverttype = coverttype;
	}

	public String coverttype(){
		IrpGoods irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
		covertState=irpGoods.getCoverstate();
		return SUCCESS;
		
	}
	public void addcovertrecord(){
		int msg = 0;
		Date date = new Date();
		IrpMessageContent irpMessageContent=new IrpMessageContent();
		IrpUserCovertGoods covert1=new IrpUserCovertGoods();
		HttpServletRequest _request =ServletActionContext.getRequest();
		Long goodsid=Long.parseLong(_request.getParameter("goodsid"));
		Long covertnum=Long.parseLong(_request.getParameter("covertnumber"));
		covert1.setUsergoodsid(TableIdUtil.getNextId(IrpUserCovertGoods.TABLE_NAME));
		covert1.setCoverttime(date);
		covert1.setGoodsid(goodsid);
		covert1.setCovertnum(covertnum);
		covert1.setUserid(LoginUtil.getLoginUserId());
		IrpUser user=irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
		covert1.setCovertuser(user.getUsername());
		IrpGoods goods=irpGoodsService.findGoodsByGoodsid(goodsid);
		covert1.setCovertgoods(goods.getGoodsname());
		Long needscore=goods.getNeedscore();
		if(coverttype==20){
			Long goodsScorenum=covertnum*needscore;
			if(user.getSumscore()>=goodsScorenum){
				covert1.setSingleScore(needscore);
				covert1.setConvertType(Long.parseLong(coverttype+""));
				covert1.setTotalScore(goodsScorenum);
				msg = irpUserCovertGoodsService.addCovert(covert1);
				Long sumscore=user.getSumscore()-goodsScorenum;
				// 添加一条扣除积分记录
				IrpUserValueLink irpUserValueLink = new IrpUserValueLink();
				irpUserValueLink.setUserid(LoginUtil.getLoginUserId());
				irpUserValueLink.setValueckey("GOODS_COVERT");
				irpUserValueLink.setScore(-goodsScorenum);
				irpUserValueLink.setExperience(10l);
				irpUserValueLinkService.addIrpUserValueLink(irpUserValueLink);
				irpMessageContent.setFromUid(LoginUtil.getLoginUserId());
				irpMessageContent.setContent("恭喜您,成功兑换"+covertnum+"件"+goods.getGoodsname()+",共花费"+goodsScorenum+"积分,兑换前可用积分为"+user.getSumscore()+",您目前剩余可用积分为"+sumscore+"。");
				irpMessageContent.setCruserid(2l);
				irpMessageContent.setMessagetype(0);
				irpMessageContentService.addMessageContent(irpMessageContent);
				user.setSumscore(sumscore);
				user.setSumexperience(user.getSumexperience()+10);
				irpUserService.updateSumScoreExperience(user);
				Long stocknum=goods.getStocknum()-covertnum;
				goods.setStocknum(stocknum);
				Integer salesLong=goods.getSalesvolume();
				if(salesLong==null){
					salesLong=0;
				}
				goods.setSalesvolume((int) (salesLong+covertnum));
				irpGoodsService.updateGoodsByGoodsid(goods);
			}
		}else{
			int aDataCount=irpUserMedalService.countUserMedal();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			List<IrpUserMedal> listusermedal = irpUserMedalService.getUserMedalByUserid(pageUtil, LoginUtil.getLoginUserId());
			Long medalid=goods.getMedalid();
			/*for(int i=0;i<listusermedal.size();i++){*/
				if(medalid==0){
					medalid=(long) irpUserMedalService.selectMedalid(goodsid);
				}
				List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),medalid,0l);
				//if(listusermedal.get(i).getMedalid().equals(medalid)){
				if(listmedal.size()>0){
					int count=irpUserMedalService.countUserMedal(LoginUtil.getLoginUserId(),goodsid,0l);
					if(count>=covertnum){
						//List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),medalid,0l);
						for(int j=0;j<covertnum;j++){
							listmedal.get(j).setMedalstate(1l);
							irpUserMedalService.updateUserMedalById(listmedal.get(j));
						}
						covert1.setSingleScore(needscore);
						covert1.setConvertType(Long.parseLong(coverttype+""));
						covert1.setTotalScore(covertnum);
						
						msg = irpUserCovertGoodsService.addCovert(covert1);
						Long countmedal=count-covertnum;
						irpMessageContent.setFromUid(LoginUtil.getLoginUserId());
						irpMessageContent.setContent("恭喜您,成功兑换"+covertnum+"件"+goods.getGoodsname()+",共花费"+covertnum+"个勋章,兑换前可用"+listusermedal.get(0).getMedalname()+"为"+count+"个,您目前可用"+listusermedal.get(0).getMedalname()+"为"+countmedal+"个。");
						irpMessageContent.setCruserid(2l);
						irpMessageContent.setMessagetype(0);
						irpMessageContentService.addMessageContent(irpMessageContent);
						Long stocknum=goods.getStocknum()-covertnum;
						goods.setStocknum(stocknum);
						Integer salesLong=goods.getSalesvolume();
						if(salesLong==null){
							salesLong=0;
						}
						goods.setSalesvolume((int) (salesLong+covertnum));
						irpGoodsService.updateGoodsByGoodsid(goods);
					}
				}
			//}
		}
		ActionUtil.writer(""+msg);
	}
	private List<IrpUserCovertGoods> listcovertrecord;
	
	
	public List<IrpUserCovertGoods> getListcovertrecord() {
		return listcovertrecord;
	}

	public void setListcovertrecord(List<IrpUserCovertGoods> listcovertrecord) {
		this.listcovertrecord = listcovertrecord;
	}
	private String username;
	private String goodsname;
	private String orderField;
	private String orderBy;
	private String c_start_end;//操作时间段
	//时间段 时间选择框
	private String c_date_start_time;
	private String c_date_end_time;
	
	public String getC_start_end() {
		return c_start_end;
	}

	public void setC_start_end(String c_start_end) {
		this.c_start_end = c_start_end;
	}

	public String getC_date_start_time() {
		return c_date_start_time;
	}

	public void setC_date_start_time(String c_date_start_time) {
		this.c_date_start_time = c_date_start_time;
	}

	public String getC_date_end_time() {
		return c_date_end_time;
	}

	public void setC_date_end_time(String c_date_end_time) {
		this.c_date_end_time = c_date_end_time;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String listCovertRecord(){
		int aDataCount=irpUserCovertGoodsService.countCovert();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listcovertrecord = irpUserCovertGoodsService.showAllCovert(pageUtil,orderField,orderBy);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	
	//检索
		public String ckCovertInfo(){	
			Date starttimes=null;
			Date endtimes=null;
			int nDateCount = this.irpUserCovertGoodsService.countCovert();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					nDateCount);
			// 处理排序
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "coverttime desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			IrpUserCovertGoods ucg=new IrpUserCovertGoods();
			//兑换用户
			if (username!=null) {
				ucg.setCovertuser(username.trim());
			}
			//兑换商品	
			if (goodsname!=null) {
				ucg.setCovertgoods(goodsname.trim());
			}
				
			//时间段
			if (this.c_start_end.equals("covert_week")) {
				starttimes=new LogTimeUtil().getWeek();
				endtimes=new LogTimeUtil().getLastWeek();
			}else if(this.c_start_end.equals("covert_month")){
				starttimes=new LogTimeUtil().getMonth();
				endtimes=new LogTimeUtil().getLastMonth();
			}else if(this.c_start_end.equals("covert_quarter")){
				starttimes=new LogTimeUtil().getQuarter();
				endtimes=new LogTimeUtil().getLastQuarter();
			}else if(this.c_start_end.equals("covert_appoint_date")){
				String starttimeL[]=this.c_date_start_time.split("-");
				Date dateS=new Date(new LogTimeUtil().getYearOfYear(starttimeL[0]),Integer.parseInt(starttimeL[1])-1,Integer.parseInt(starttimeL[2]));
				dateS.setHours(0);
				dateS.setMinutes(0);
				dateS.setSeconds(0);
				starttimes=dateS;
				String endtimeL[]=this.c_date_end_time.split("-");
				Date dateE=new Date(new LogTimeUtil().getYearOfYear(endtimeL[0]),Integer.parseInt(endtimeL[1])-1,Integer.parseInt(endtimeL[2])+1);
				dateE.setHours(24);
				dateE.setMinutes(59);
				dateE.setSeconds(59);
				endtimes=dateE;
			}
			int covertsize= this.irpUserCovertGoodsService.findCovertOfPageSize(pageUtil, _oOrderby,ucg,starttimes,endtimes).size();
			PageUtil pageUtilCheck = new PageUtil(this.pageNum, this.getPageSize(),
					covertsize);
		    listcovertrecord=this.irpUserCovertGoodsService.findCovertOfPage(pageUtilCheck, _oOrderby,ucg,starttimes,endtimes);
		    this.pageHTML = pageUtilCheck.getPageHtml("page(#PageNum#)");
			return  SUCCESS;
		}

	public void plDeleteCovert(){
		int msg = 0;
		if(covertids!=""&&covertids!=null){
    		String[] array =covertids.split(",");
    		for(int j=0;j<array.length;j++){
    			irpUserCovertGoodsService.deleteMoreCovert(Long.parseLong(array[j]));
    			msg++;
    		}
		}
		ActionUtil.writer(""+msg);
	}
	public String findCovertInfoById(){
		covert=irpUserCovertGoodsService.findCovertByCovertid(covertid);
		/*username=irpUserService.findShowNameByUserid(covert.getUserid());
		IrpGoods irpGoods=irpGoodsService.findGoodsByGoodsid(covert.getGoodsid());
		goodsname=irpGoods.getGoodsname();*/
		return SUCCESS;
	}
	public void deleteCovert(){
		int msg = 0;
		msg = irpUserCovertGoodsService.deleteMoreCovert(covertid);
		ActionUtil.writer(""+msg);
	}
	public String covertRecord() throws Exception {
		return super.execute();
	}
	public String listCovertRecordByUserid(){
		int aDataCount=irpUserCovertGoodsService.countCovertByUserid(LoginUtil.getLoginUserId());
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listcovertrecord = irpUserCovertGoodsService.showAllCovertByUserid(pageUtil,orderField,orderBy,LoginUtil.getLoginUserId());
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	/**
	 * 通过对象id求出相应的user对象
	 * @param _userid
	 * @return
	 */
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
	/*public void findcovertnum(){
		HttpServletRequest _request =ServletActionContext.getRequest();
		Long goodsid=Long.parseLong(_request.getParameter("goodsid"));
		Long covertnum=Long.parseLong(_request.getParameter("covertnum"));
		System.out.println("aaaa"+covertnum);
	    ActionUtil.writer(covertnum+"");
	 }*/

}