package com.tfs.irp.asseroom.web;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample;
import com.tfs.irp.asseroom.entity.IrpAsseroomExample.Criteria;
import com.tfs.irp.asseroom.service.IrpAsseroomService;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample;
import com.tfs.irp.asseroomapply.service.IrpAsseroomapplyService;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsb;
import com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample;
import com.tfs.irp.asseroomsb.service.IrpAsseroomsbService;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblink;
import com.tfs.irp.asseroomsblink.service.IrpAsseroomsblinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpAsseroomAction extends ActionSupport {
		private IrpAsseroomService asseroomService;
		private IrpAsseroomsbService asseroomsbService;
		private IrpAsseroomsblinkService asseroomsblinkService;
		private IrpAsseroom asseroom;
		private IrpAsseroomsb asseroomsb;
		private IrpAsseroomapplyService asseroomapplyService;
		public IrpAsseroomapplyService getAsseroomapplyService() {
			return asseroomapplyService;
		}

		public void setAsseroomapplyService(IrpAsseroomapplyService asseroomapplyService) {
			this.asseroomapplyService = asseroomapplyService;
		}
		/**
		 * 会议室设备id
		 */
		private String[] asseroomsbids;
		/*检索*/
		private String searchWord;
		private String searchType;
		/*
		 * 多选删除
		 */
		private String asseroomids;
		/*
		 * 分页排序
		 */
		private String pageHTML = "";
		private int pageNum = 1;

		private int pageSize = 10;

		private String orderField = "";

		private String orderBy = "";
		/**
		 * 存放IrpAsseroom list集合
		 */
		private List<IrpAsseroom> asseroomList;
		/**
		 * 存放IrpAsseroomsb list集合
		 */
		private List<IrpAsseroomsb> asseroomsbList;
		/**
		 * 存放IrpAsseroomsblink list集合
		 */
		private List<IrpAsseroomsblink> asseroomsblinkList;
		/**
		 * 存放会议室设备 list集合
		 */
		private List<IrpAsseroomsb> sbList;
		/**
		 * 存放会议室的id
		 */
		private Long asseroomid;
		/**
		 * 
		 * 更新检索验证
		 */
		private String ck_nametwo;
		/**
		 * 存放会议室名称
		 */
		private String ck_name;
		
	

		public List<IrpAsseroomsblink> getAsseroomsblinkList() {
			return asseroomsblinkList;
		}

		public void setAsseroomsblinkList(List<IrpAsseroomsblink> asseroomsblinkList) {
			this.asseroomsblinkList = asseroomsblinkList;
		}

		public List<IrpAsseroomsb> getSbList() {
			return sbList;
		}

		public void setSbList(List<IrpAsseroomsb> sbList) {
			this.sbList = sbList;
		}

		public String[] getAsseroomsbids() {
			return asseroomsbids;
		}

		public void setAsseroomsbids(String[] asseroomsbids) {
			this.asseroomsbids = asseroomsbids;
		}

		public List<IrpAsseroomsb> getAsseroomsbList() {
			return asseroomsbList;
		}

		public void setAsseroomsbList(List<IrpAsseroomsb> asseroomsbList) {
			this.asseroomsbList = asseroomsbList;
		}

		public IrpAsseroomService getAsseroomService() {
			return asseroomService;
		}

		public void setAsseroomService(IrpAsseroomService asseroomService) {
			this.asseroomService = asseroomService;
		}

		public IrpAsseroomsbService getAsseroomsbService() {
			return asseroomsbService;
		}

		public void setAsseroomsbService(IrpAsseroomsbService asseroomsbService) {
			this.asseroomsbService = asseroomsbService;
		}

		public IrpAsseroomsblinkService getAsseroomsblinkService() {
			return asseroomsblinkService;
		}

		public void setAsseroomsblinkService(
				IrpAsseroomsblinkService asseroomsblinkService) {
			this.asseroomsblinkService = asseroomsblinkService;
		}

		public IrpAsseroom getAsseroom() {
			return asseroom;
		}

		public void setAsseroom(IrpAsseroom asseroom) {
			this.asseroom = asseroom;
		}

		public IrpAsseroomsb getAsseroomsb() {
			return asseroomsb;
		}

		public void setAsseroomsb(IrpAsseroomsb asseroomsb) {
			this.asseroomsb = asseroomsb;
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

		public String getAsseroomids() {
			return asseroomids;
		}

		public void setAsseroomids(String asseroomids) {
			this.asseroomids = asseroomids;
		}

		public String getPageHTML() {
			return pageHTML;
		}

		public void setPageHTML(String pageHTML) {
			this.pageHTML = pageHTML;
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

		public List<IrpAsseroom> getAsseroomList() {
			return asseroomList;
		}

		public void setAsseroomList(List<IrpAsseroom> asseroomList) {
			this.asseroomList = asseroomList;
		}

		public Long getAsseroomid() {
			return asseroomid;
		}

		public void setAsseroomid(Long asseroomid) {
			this.asseroomid = asseroomid;
		}

		public String getCk_nametwo() {
			return ck_nametwo;
		}

		public void setCk_nametwo(String ck_nametwo) {
			this.ck_nametwo = ck_nametwo;
		}

		public String getCk_name() {
			return ck_name;
		}

		public void setCk_name(String ck_name) {
			this.ck_name = ck_name;
		}
		/**
		 * 查找会议室
		 * @return
		 * @throws Exception
		 */
		public String findAll() throws Exception {
			findAsseroom();
			return SUCCESS;
			}
		
		/**
		 * 查会议室
		 * @throws Exception
		 */
		public void findAsseroom() throws Exception{
			IrpAsseroomExample example=new IrpAsseroomExample();
			Criteria criteria=example.createCriteria();
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "asseroomid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			if(searchType!=null&&!"".equals(searchType)){
				if("all".equals(searchType)){
					example.or(criteria.andAsseroomnameLike("%"+searchWord+"%"));
					example.or(criteria.andAsseroompeoplesEqualTo(Long.parseLong(searchWord)));
				} else if("asseroomname".equals(searchType)){
					criteria.andAsseroomnameLike("%"+searchWord+"%");
				} else if("asseroompeoples".equals(searchType)){
					criteria.andAsseroompeoplesEqualTo(Long.parseLong(searchWord));
				}
			}
			example.setOrderByClause(_oOrderby);
			//按条件查询数据的总记录条数
			int count =asseroomService.getDataCount(example);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					count);
			asseroomList=asseroomService.querySbForPage(example, pageUtil);
			if(null!=asseroomList){
				for (IrpAsseroom  room : asseroomList) {
					List<IrpAsseroomsblink> asseroomsblinksList=asseroomsblinkService.findbyAsseroomid(room.getAsseroomid());
					StringBuffer sbname=new StringBuffer();
					if(null!=asseroomsblinksList){
						for (IrpAsseroomsblink irpAsseroomsblink : asseroomsblinksList) {
							sbname.append(asseroomsbService.selectByPrimaryKey(irpAsseroomsblink.getAsseroomsbid()).getAsseroomsbname()+";");
						}
					}
					room.setAsseroomsbs(sbname.toString());
				}
			}
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}
		
		/**
		 * 为了统计会议室而查找会议室信息
		 * @return
		 * @throws Exception
		 */
		public String findAsseroomForCount() throws Exception{
			findAsseroom();
			return SUCCESS;
		}
		/**
		 * 查询会议室设备
		 * @return
		 * @throws Exception
		 */
		public String roomFrom() throws Exception {
			queryAllSb();
		return super.execute();
		}
		
		/**
		 * 添加会议室
		 * @throws Exception
		 */
		public void addRoom(){
			Long id=TableIdUtil.getNextId(IrpAsseroom.TABLE_NAME);
			asseroom.setAsseroomid(id);
			Long _cataStatus=0L;
			LogUtil logUtil=new LogUtil("ASSEROOM_ADD",asseroom);
			try {
				asseroomsblinkService.addSb(id, asseroomsbids);
				 _cataStatus=asseroomService.addSb(asseroom,asseroomsbids);
				 logUtil.successLogs( "添加会议室["+logUtil.getLogUser()+"]成功");
			} catch (Exception e) {
				e.printStackTrace();
				logUtil.errorLogs( "添加会议室["+logUtil.getLogUser()+"]失败",e);
			}
			ActionUtil.writer(_cataStatus + "");
		}
		/**
		 * 修改会议室表单
		 * @throws Exception
		 */
		public String updateFrom() throws Exception {
			asseroom=asseroomService.selectByPrimaryKey(asseroomid);
			if(asseroom!=null){
				asseroomsblinkList=asseroomsblinkService.findbyAsseroomid(asseroom.getAsseroomid());
				if(null!=asseroomsblinkList){
					sbList=new ArrayList<IrpAsseroomsb>();
					for (IrpAsseroomsblink asseroomsblink : asseroomsblinkList) {
						asseroomsb=asseroomsbService.selectByPrimaryKey(asseroomsblink.getAsseroomsbid());
						if(asseroomsb!=null){
							sbList.add(asseroomsb);
						}
					}
				}
			}
			queryAllSb();
			return "success";
		}
		/**修改会议室
		 *  有未完成的会议不能修改会议室的状态
		 * @throws Exception
		 */
		public void updateRoom() {
			String  _cataStatus="0";
			 LogUtil logUtil=null;
			try {
				if(asseroom.getAsseroomstatus()==0){
					int flag = queryApply(asseroomid);
					if(flag==0){
						IrpAsseroomExample example =new IrpAsseroomExample();
						Criteria criteria=example.createCriteria();
						criteria.andAsseroomidEqualTo(asseroomid);
						asseroom.setAsseroomid(asseroomid);
						logUtil=new LogUtil("ASSEROOM_UPDATE",asseroom);
						asseroomsblinkService.deletebyAsseroomid(asseroomid);
						asseroomsblinkService.addSb(asseroomid, asseroomsbids);
						_cataStatus=""+asseroomService.updateByExampleSelective(asseroom, example);
						logUtil.successLogs( "修改会议室["+logUtil.getLogUser()+"]成功");
					}else{
						_cataStatus=""+flag;
					}
				}else{
					IrpAsseroomExample example =new IrpAsseroomExample();
					Criteria criteria=example.createCriteria();
					criteria.andAsseroomidEqualTo(asseroomid);
					asseroom.setAsseroomid(asseroomid);
					logUtil=new LogUtil("ASSEROOM_UPDATE",asseroom);
					asseroomsblinkService.deletebyAsseroomid(asseroomid);
					asseroomsblinkService.addSb(asseroomid, asseroomsbids);
					_cataStatus=""+asseroomService.updateByExampleSelective(asseroom, example);
					logUtil.successLogs( "修改会议室["+logUtil.getLogUser()+"]成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logUtil.errorLogs( "修改会议室["+logUtil.getLogUser()+"]失败",e);
			}
			ActionUtil.writer(_cataStatus + "");
		}
		/**
		 * 删除会议室
		 * @throws Exception
		 */
		public void deleteRoom(){
			String  _cataStatus="0";
			LogUtil logUtil=null;
			try {
				int flag= queryApply(asseroomid);
				if(flag==0){
					asseroom=asseroomService.selectByPrimaryKey(asseroomid);
					IrpAsseroomExample example =new IrpAsseroomExample();
					Criteria criteria=example.createCriteria();
					criteria.andAsseroomidEqualTo(asseroomid);
					 logUtil=new LogUtil("ASSEROOM_DEL",asseroom);
					asseroomService.deleteByExample(example);
					asseroomsblinkService.deletebyAsseroomid(asseroomid);
					logUtil.successLogs( "删除会议室["+logUtil.getLogUser()+"]成功");
				}
				_cataStatus=""+flag;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logUtil.errorLogs( "删除会议室["+logUtil.getLogUser()+"]失败",e);
			}
			ActionUtil.writer(_cataStatus + "");
		}
		/**
		 * 删除多个会议室
		 * @throws Exception
		 */
		public void deleteAll(){
			String _cataStatus="0";
			List<Long > ids=new ArrayList<Long>();
			String[] idsStrings=asseroomids.split(",");
			LogUtil logUtil=null;
			for (int i = 0; i < idsStrings.length; i++) {
				if(!"".equals(idsStrings[i]))
				ids.add(Long.parseLong(idsStrings[i].trim()));
			}
			try {
				StringBuffer nameBuffer=new StringBuffer();
				if(!ids.isEmpty()){
					for (Long idLong : ids) {
						int flag = queryApply(idLong);
						
						if(flag!=0){
							nameBuffer.append("【"+asseroomService.selectByPrimaryKey(idLong).getAsseroomname()+"】");
						}
					}
				}
				if(nameBuffer.length()>0){
					 _cataStatus=nameBuffer.toString();
				}else{
					asseroom=asseroomService.selectByPrimaryKey(ids.get(0));
					 logUtil=new LogUtil("ASSEROOM_DEL",asseroom);
					IrpAsseroomExample example =new IrpAsseroomExample();
					Criteria criteria=example.createCriteria();
					criteria.andAsseroomidIn(ids);
					 _cataStatus=asseroomService.deleteByExample(example)+"";
					 _cataStatus=Integer.parseInt(_cataStatus)>0? "1":"0";
					asseroomsblinkService.deletebyAsseroomsbidList(ids);
					if(logUtil!=null){
						logUtil.successLogs( "删除会议室"+ids.toString()+"["+logUtil.getLogUser()+"]成功");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(logUtil!=null){
					logUtil.errorLogs( "删除会议室"+ids.toString()+"["+logUtil.getLogUser()+"]失败",e);
				}
			}
			ActionUtil.writer(_cataStatus + "");
		}
		/**
		 * 会议室的名称是否重名
		 * 
		 * @return
		 * @throws SQLException 
		 * @throws UnsupportedEncodingException 
		 */
		public void checkName() throws SQLException, UnsupportedEncodingException {
			ck_name=ck_name;
			ck_nametwo=ck_nametwo;
			ck_name=new String(ck_name.trim().getBytes("ISO-8859-1"),"UTF-8");
			ck_nametwo=new String(ck_nametwo.trim().getBytes("iso-8859-1"),"UTF-8");
			if (ck_name.trim().equals(ck_nametwo.trim())) {
				ActionUtil.writer("true");
			} else {
				int _ckeyStatus = asseroomService.findname(ck_name.trim());
				if (_ckeyStatus == 1) {
					ActionUtil.writer("false");
				} else if (_ckeyStatus == 2) {
					ActionUtil.writer("true");
				} else {
					ActionUtil.writer("false");
				}
			}
		}
		public void queryAllSb() throws Exception {
			IrpAsseroomsbExample example=new IrpAsseroomsbExample();
			com.tfs.irp.asseroomsb.entity.IrpAsseroomsbExample.Criteria criteria=example.createCriteria();
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "asseroomsbid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			criteria.andAsseroomsbstatusEqualTo(1);
//			List<IrpAsseroomsblink> asseroomsblinkList=asseroomsblinkService.findAll();
//			List<Long> sbids=new ArrayList<Long>();
//			if(!asseroomsblinkList.isEmpty()){
//				for (IrpAsseroomsblink sb : asseroomsblinkList) {
//					sbids.add(sb.getAsseroomsbid());
//				}
//				criteria.andAsseroomsbidNotIn(sbids);
//			}
			example.setOrderByClause(_oOrderby);
			asseroomsbList=asseroomsbService.selectByExample(example);
		}
		public int queryApply(long _asseroomid) throws Exception{
			IrpAsseroomapplyExample example=new IrpAsseroomapplyExample();
			com.tfs.irp.asseroomapply.entity.IrpAsseroomapplyExample.Criteria criteria=example.createCriteria();
			String _oOrderby = "";
			if (this.orderField == null || this.orderField.equals("")) {
				_oOrderby = "asseroomapplyid desc";
			} else {
				_oOrderby = this.orderField + " " + this.orderBy;
			}
			//按会议室查询
			criteria.andAsseroomidEqualTo(_asseroomid);
			//结束时间大于当前时间
			criteria.andEndtimeGreaterThan(new Date());
			example.setOrderByClause(_oOrderby);
			List<IrpAsseroomapply> asseroomapplyList=asseroomapplyService.querySbForPage(example);
			return asseroomapplyList.isEmpty()? 0:2;
		}

}
