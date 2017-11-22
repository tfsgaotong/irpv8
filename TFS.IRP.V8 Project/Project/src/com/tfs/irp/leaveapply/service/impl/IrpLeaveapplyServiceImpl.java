package com.tfs.irp.leaveapply.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample.Criteria;
import com.tfs.irp.leaveapply.dao.IrpLeaveapplyDAO;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample;
import com.tfs.irp.leaveapply.service.IrpLeaveapplyService;
import com.tfs.irp.leaveapply.web.IrpLeaveapplyInfo;
import com.tfs.irp.leaveconf.dao.IrpLeaveconfigDAO;
import com.tfs.irp.leaveoper.dao.IrpLeaveoperDAO;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;
import com.tfs.irp.sign.dao.IrpSignInfoDAO;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SignUtil;

public class IrpLeaveapplyServiceImpl implements IrpLeaveapplyService {
	private  IrpLeaveapplyDAO  irpLeaveapplyDAO;
	private  IrpLeaveoperDAO  irpLeaveoperDAO;
	private IrpUserDAO irpUserDAO;
	
	/**
	 * sheet计数器
	 */
	private int sheetNum = 0;
	/**
	 * 用户id
	 */
	private Long userid;
	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}

	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}

	public IrpLeaveapplyDAO getIrpLeaveapplyDAO() {
		return irpLeaveapplyDAO;
	}

	public void setIrpLeaveapplyDAO(IrpLeaveapplyDAO irpLeaveapplyDAO) {
		this.irpLeaveapplyDAO = irpLeaveapplyDAO;
	}
	public IrpLeaveoperDAO getIrpLeaveoperDAO() {
		return irpLeaveoperDAO;
	}

	public void setIrpLeaveoperDAO(IrpLeaveoperDAO irpLeaveoperDAO) {
		this.irpLeaveoperDAO = irpLeaveoperDAO;
	}
private IrpLeaveconfigDAO irpLeaveconfigDAO;

	

	public IrpLeaveconfigDAO getIrpLeaveconfigDAO() {
		return irpLeaveconfigDAO;
	}



	public void setIrpLeaveconfigDAO(IrpLeaveconfigDAO irpLeaveconfigDAO) {
		this.irpLeaveconfigDAO = irpLeaveconfigDAO;
	}
	private IrpSignInfoDAO signDao;
	public IrpSignInfoDAO getSignDao() {
		return signDao;
	}

	public void setSignDao(IrpSignInfoDAO signDao) {
		this.signDao = signDao;
	}
	private IrpLeaveoperService irpLeaveoperService;
	public IrpLeaveoperService getIrpLeaveoperService() {
		return irpLeaveoperService;
	}
	public void setIrpLeaveoperService(IrpLeaveoperService irpLeaveoperService) {
		this.irpLeaveoperService = irpLeaveoperService;
	}
	@Override
	public List<IrpLeaveapply> getAllList(Integer leave,PageUtil pageUtil,int status,Long userid) {	
		
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		List<IrpLeaveapply>  list=new ArrayList<IrpLeaveapply>();
		if(status>0){	
		
		example.createCriteria().andLeavemarkingEqualTo(leave).andApplystatusEqualTo(status).andCruseridEqualTo(userid);
		}else{
			example.createCriteria().andLeavemarkingEqualTo(leave).andCruseridEqualTo(userid);
			
		}
		example.setOrderByClause("crtime desc");
		list=irpLeaveapplyDAO.selectByExample(example,pageUtil);
		return list;
	}


	@Override
	public int findListNums(Integer leave,Integer status,Long userid) {
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		if(status>0){
		example.createCriteria().andLeavemarkingEqualTo(leave).andApplystatusEqualTo(status).andCruseridEqualTo(userid);
		}else{
			example.createCriteria().andLeavemarkingEqualTo(leave).andCruseridEqualTo(userid);
		}
		int nCount = 0;
		try {
			nCount = irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}


	@Override
	public IrpLeaveapply getById(Long applyid) {
		IrpLeaveapply irpLeaveapply=null;
		if(applyid>0){
			try {
				irpLeaveapply = this.irpLeaveapplyDAO.selectByPrimaryKey(applyid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irpLeaveapply;
	}
		
	
	
	@Override
	public int findListOverTimeNums(Integer leave,Integer status,List<Long> list) {
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria criteria = example.createCriteria();
		criteria.andLeavemarkingEqualTo(leave);
		
		if(list!=null&&list.size()>0){
			criteria.andLeaveapplyidIn(list);
		}else{
			IrpUser user = LoginUtil.getLoginUser();
			criteria.andCruseridEqualTo(user.getUserid());
		}
		int nCount = 0;
		try {
			nCount = irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	public List<IrpLeaveapply> getAll(PageUtil pageUtil,Integer leave,Integer status,List<Long> _list){		
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		List<IrpLeaveapply>  list=new ArrayList<IrpLeaveapply>();
		Criteria criteria = example.createCriteria();
		criteria.andLeavemarkingEqualTo(leave);
		example.setOrderByClause("crtime desc");
		
		if(_list!=null&&_list.size()>0){
			criteria.andLeaveapplyidIn(_list);
		}else{
			IrpUser user = LoginUtil.getLoginUser();
			criteria.andCruseridEqualTo(user.getUserid());
		}
		list=irpLeaveapplyDAO.selectByExample(example,pageUtil);
		return list;
	}
	public List<IrpLeaveapply> getAll1(PageUtil pageUtil,Integer leave,Integer status,List<Long> _list){		
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		List<IrpLeaveapply>  list=new ArrayList<IrpLeaveapply>();
		Criteria criteria = example.createCriteria();
		criteria.andLeavemarkingEqualTo(leave);
		example.setOrderByClause("crtime desc");
		if(status!=0){
			if(status.equals(20)){			
				criteria.andCheckmoreEqualTo(status);
			}else if(status.equals(40)){
				List<Integer> list2=new ArrayList<Integer>();
				list2.add(10);list2.add(30);			
				criteria.andCheckmoreIn(list2);
			}else{			
				criteria.andCheckmoreEqualTo(status);
			}
		}	
		
		list=irpLeaveapplyDAO.selectByExample(example,pageUtil);
		return list;
	}

	@Override
	public int findListQueryNums(String marking, Date startTime,
			Date endTime, String applystatus, 
			String applytypeid,Long userid,int emergency) {
		int nCount = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria criteria=example.createCriteria();
		//标识：工作还是加班
		if(marking!=""&&marking!=null){
			criteria.andLeavemarkingEqualTo(Integer.parseInt(marking));			
	     }
		
		 //时间段   
		if(endTime!=null && startTime!=null){
		    criteria.andCrtimeBetween(startTime,endTime);	
		    
		    
		}
		//请假进度
		if(Integer.parseInt(applystatus)!=0){
			criteria.andApplystatusEqualTo(Integer.parseInt(applystatus));
			
		}
		//请假类型
		if(Integer.parseInt(applytypeid)!=0){
			
			criteria.andApplytypeidEqualTo(Long.parseLong(applytypeid));
			
		}
		//紧急程度
				if(emergency!=0){
					criteria.andEmergencyEqualTo(emergency);
					
				}
		criteria.andCruseridEqualTo(userid);
		try {
			nCount=this.irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpLeaveapply> getQueryList(PageUtil pageUtil, String marking,
			Date startTime, Date endTime, String applystatus,
			 String applytypeid,Long userid,int emergency) {
		 List<IrpLeaveapply> list=new ArrayList<IrpLeaveapply>();
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria criteria=example.createCriteria();
		//标识：工作还是加班
		if(marking!=""&&marking!=null){
			criteria.andLeavemarkingEqualTo(Integer.parseInt(marking));			
	     }

		 //时间段   
		if(endTime!=null && startTime!=null){
		    criteria.andCrtimeBetween(startTime,endTime);	
		    
		    
		}
		//请假进度
		if(Integer.parseInt(applystatus)!=0){
			criteria.andApplystatusEqualTo(Integer.parseInt(applystatus));
			
		}
		//请假类型
		if(Integer.parseInt(applytypeid)!=0){
			criteria.andApplytypeidEqualTo(Long.parseLong(applytypeid));
			
		}
		//紧急程度
		if(emergency!=0){
			criteria.andEmergencyEqualTo(emergency);
			
		}
		criteria.andCruseridEqualTo(userid);
		list=this.irpLeaveapplyDAO.selectByExample(example, pageUtil);
		return list;
	}

	@Override
	public int upStatus(IrpLeaveapply _irpLeaveapply) {
		// TODO Auto-generated method stub
		int msg = 0;
		try {
			msg = this.irpLeaveapplyDAO.updateByPrimaryKey(_irpLeaveapply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * 将加班信息写入Excel并压缩为Zip文件
	 */
	@Override
	public void exportAllWorkTimeToZip(List<IrpLeaveapplyInfo> list,String getPath,String fileName) {
		try {
			//创建文件路径
			String path=getPath+"/worktime";
			// 创建文件夹;
			createFile(path);
			// 创建excel文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path+"/"
					+ fileName+"_"+".xls"));
			// 接收返回的isend,用来判断是否循环到最后
			int count = 0;
			if (list != null && list.size() != 0) {
				// 初始化用户id
				this.userid = list.get(0).getCruseridl();
				do {
					count = createAllWorkTimeToExcel(list, workbook);
				} while (count != -1);
				// 写入Excel表格中;
				workbook.write();
			}			
			// 关闭流;
			workbook.close();
			// 生成.zip文件;
			craeteZipPath(path,fileName);
			// 删除目录下所有的文件;
			File file = new File(path);
			// 删除文件;
			deleteExcelPath(file);
			// 重新创建文件;
			// file.mkdirs();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 创建文件夹;
	 * 
	 * @param path
	 * @return
	 */
	public String createFile(String path) {
		File file = new File(path);
		// 判断文件是否存在;
		if (!file.exists()) {
			// 创建文件;
			boolean bol = file.mkdirs();
			if (bol) {
				// System.out.println(path+" 路径创建成功!");
			} else {
				// System.out.println(path+" 路径创建失败!");
			}
		} else {
			// System.out.println(path+" 文件已经存在!");
		}
		return path;
	}
	/**
	 * 循环写入Excel
	 * 
	 * @param workTimeInfo
	 * @return
	 */
	private int createAllWorkTimeToExcel(List<IrpLeaveapplyInfo> workTimeInfo,
			WritableWorkbook workbook) throws Exception {
		// 循环中计数器
		int count = 0;
		// 判断是不是最后一个 1代表不是最后一个, -1代表是最后一个
		int isend = 1;
		// 是否循环过数据
		boolean isexport = false;
		// 创建第一个sheet文件;
		//String truename = irpUserDAO.findShowNameByUserid(userid);
		WritableSheet sheet = workbook.createSheet("加班统计", 1);
		// 设置默认宽度;
		sheet.getSettings().setDefaultColumnWidth(20);
		// 设置字体,正常状态(黑色);
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat1.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat1.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 设置字体,异常状态(红色)
		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);

		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat2.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat2.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat2.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
		Label label = new Label(0, 0, "标题", cellFormat1);
		Label label1 = new Label(1, 0, "开始时间", cellFormat1);
		Label label2 = new Label(2, 0, "结束时间", cellFormat1);
		Label label3 = new Label(3, 0, "紧急程度", cellFormat1);
		Label label4 = new Label(4, 0, "状态", cellFormat1);
		Label label5 = new Label(5, 0, "创建人", cellFormat1);
		Label label6 = new Label(6, 0, "创建时间", cellFormat1);
		sheet.addCell(label);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		// 行数计数器
		int i = 0;
		int pass = 0;
		int upass = 0;
		int refuse = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (; count < workTimeInfo.size(); count++) {
			IrpLeaveapplyInfo info = workTimeInfo.get(count);
//			if (info.getCruseridl().equals(userid) && !isexport) {
				Label lab1 = new Label(0, i + 1,info.getTitle(), cellFormat1);
				Label lab2 = new Label(1, i + 1,sdf.format(info.getStarttime()).toString(), cellFormat1);
				Label lab3 = new Label(2, i + 1, sdf.format(info.getEndtime()).toString(), cellFormat1);
				Label lab4 = new Label(3, i + 1,info.getEmergency(),cellFormat1);
				Label lab5 = new Label(4, i + 1, info.getApplystatus().toString(),cellFormat1);
				Label lab6 = new Label(5, i + 1, info.getCruserid(),cellFormat1);
				Label lab7 = new Label(6, i + 1, sdf.format(info.getCrtime()).toString(),cellFormat1);
				sheet.addCell(lab1);
				sheet.addCell(lab2);
				sheet.addCell(lab3);
				sheet.addCell(lab4);
				sheet.addCell(lab5);
				sheet.addCell(lab6);
				sheet.addCell(lab7);
				i++;
			if (count == (workTimeInfo.size() - 1)) {
					isend = -1;
				}
				if (isend != -1
						&& !workTimeInfo.get(count + 1).getCruseridl()
								.equals(userid)) {
					isexport = true;
				}
				if(info.getApplystatus().equalsIgnoreCase("已同意")){
					pass=pass+1;
				}
				if(info.getApplystatus().equalsIgnoreCase("未审核")){
					upass=upass+1;
				}
				if(info.getApplystatus().equalsIgnoreCase("已拒绝")){
					refuse=refuse+1;
				}
				
//
//			} else if (isexport && !info.getCruseridl().equals(userid)) {
//				userid = info.getCruseridl();
//				break;
//			}
//			sheetNum++;
		}
		Label label7 = new Label(0, i + 1, "总加班条数",cellFormat1);
		sheet.addCell(label7);
		Label label8 = new Label(1, i + 1, workTimeInfo.size()+"",cellFormat1);
		sheet.addCell(label8);
		
		Label label9 = new Label(2, i + 1, "已审核条数",cellFormat1);
		sheet.addCell(label9);
		Label label10 = new Label(3, i + 1, pass+"",cellFormat1);
		sheet.addCell(label10);
		
		Label label11 = new Label(4, i + 1, "未审核条数",cellFormat1);
		sheet.addCell(label11);
		Label label12 = new Label(5, i + 1, upass+"",cellFormat1);
		sheet.addCell(label12);
		
		Label label13 = new Label(6, i + 1, "已拒绝条数",cellFormat1);
		sheet.addCell(label13);
		Label label14 = new Label(7, i + 1, refuse+"",cellFormat1);
		sheet.addCell(label14);
		return isend;
	}
	/**
	 * 生成.zip文件;
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void craeteZipPath(String path,String zipName) throws IOException {
		//System.out.println("进入生成zip方法");
		ZipOutputStream zipOutputStream = null;
		File file = new File(path
				+ zipName
				+ ".zip");
		zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		File[] files = new File(path).listFiles();
		FileInputStream fileInputStream = null;
		byte[] buf = new byte[1024];
		int len = 0;
		if (files != null && files.length > 0) {
			for (File excelFile : files) {
				// 测试使用 记得删除
				// System.out.println("哈哈哈我在第314行");
				String fileName = excelFile.getName();
				fileInputStream = new FileInputStream(excelFile);
				// 放入压缩zip包中;
				zipOutputStream.putNextEntry(new ZipEntry("/" + fileName));

				// 读取文件;
				while ((len = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, len);
				}
				// 关闭;
				zipOutputStream.closeEntry();
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			}
		}

		if (zipOutputStream != null) {
			zipOutputStream.close();
		}
	}
	
	@Override
	public List<IrpLeaveapply> selectByExample(Date _starttime,Date _endtime,Long _applytypeid) {
		// TODO Auto-generated method stub
		List<IrpLeaveapply> list = null;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria create = example.createCriteria();
		create.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
		if(_starttime!=null&&_endtime!=null){
			create.andCrtimeBetween(_starttime, _endtime);
		}
		if(_applytypeid!=null&&!_applytypeid.equals("")){
			create.andApplytypeidEqualTo(_applytypeid);
		}
		try {
			list = irpLeaveapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectCountByList(List<Long> _userList,Date _starttime,Date _endtime,Long _applytypeid) {
		int count = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria create = example.createCriteria();
		create.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
		if(_starttime!=null&&_endtime!=null){
			create.andCrtimeBetween(_starttime, _endtime);
		}
		if(_applytypeid!=null&&!_applytypeid.equals("")){
			create.andApplytypeidEqualTo(_applytypeid);
		}
		if(_userList.size()>0){
			create.andCruseridIn(_userList);
		}else{
			create.andCruseridEqualTo(0L);
		}
		create.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
		try {
			count = irpLeaveapplyDAO.countByExampleAndDistinct(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int selectCountByListForLeave(List<Long> _userList,Date _starttime,Date _endtime) {
		int count = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria create = example.createCriteria();
		
		if(_starttime!=null&&_endtime!=null){
			create.andCrtimeBetween(_starttime, _endtime);
		}
		
		if(_userList.size()>0){
			create.andCruseridIn(_userList);
		}else{
			create.andCruseridEqualTo(0L);
		}
		create.andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
		try {
			count = irpLeaveapplyDAO.countByExampleAndDistinct(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int selectCountBySsearch(String _sSearchWord, String _sSearchType,
			Date _starttime,Date _endtime,Long _applytypeid,String _applystatus) {
		int count = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		IrpUserExample uexample = new IrpUserExample();
		Criteria crate = example.createCriteria();
		try {
		if(_sSearchType!=null&&!_sSearchType.equals("all")){
			uexample.createCriteria().andUsernameLike("%"+_sSearchWord+"%");
			List<Long> userids = irpUserDAO.selectUserIdsByExample(uexample);
			crate.andCruseridIn(userids);
		}
		if(_starttime!=null&&_endtime!=null){
			crate.andCrtimeBetween(_starttime, _endtime);
		}
		if(_applytypeid!=null&&_applytypeid!=0){
			crate.andApplytypeidEqualTo(_applytypeid);
		}
		if(_applystatus!=null&&!_applystatus.equals("0")){
			crate.andApplystatusEqualTo(Integer.parseInt(_applystatus));
		}
		crate.andLeavemarkingEqualTo(IrpLeaveapply.WORK);	
			count = irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<IrpLeaveapply> selectInfoBySearchAndPage(PageUtil _pageUtil,
			String _sSearchWord, String _sSearchType,String _orderField,String _orderBy,
			Date _starttime,Date _endtime,Long _applytypeid,String _applystatus) {
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		IrpUserExample uexample = new IrpUserExample();
		Criteria crate = example.createCriteria();
		List<IrpLeaveapply> list = null;		
		 if(_sSearchType!=null&&_sSearchType.equals("cruser")&&!_sSearchWord.equals("")){
			uexample.createCriteria().andUsernameLike("%"+_sSearchWord+"%");
			List<Long> userids;
			try {
				userids = irpUserDAO.selectUserIdsByExample(uexample);
				crate.andCruseridIn(userids);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(_applytypeid!=null&&_applytypeid!=0){
			crate.andApplytypeidEqualTo(_applytypeid);
		}			
		if(_orderField!=null&&!_orderField.equals("")&&!_orderField.equals("")&&_orderField!=null){
			example.setOrderByClause(_orderField +" "+_orderBy);
		}
		if(_applystatus!=null&&!_applystatus.equals("0")){
			crate.andApplystatusEqualTo(Integer.parseInt(_applystatus));
		}
		crate.andCrtimeBetween(_starttime, _endtime).andLeavemarkingEqualTo(IrpLeaveapply.WORK);	
		list = irpLeaveapplyDAO.selectByExample(example,_pageUtil);	
		return list;
	}

	@Override
	public int searchUsername(String _sSearchWord, String _sSearchType) {
		int num = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		if(_sSearchWord != null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				IrpUserExample example1 = new IrpUserExample();
				example1.createCriteria().andUsernameLike("%"+_sSearchWord+"%");
				List<Long> ids=new ArrayList<Long>();
				List<IrpUser> list=null;
				try {
					 list=this.irpUserDAO.selectByExample(example1);
					if(list.size()>0){
						for(IrpUser oper:list){
							ids.add(oper.getUserid());
						}
					}else{
						ids.add(0l);
					}		
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				example.or(example.createCriteria().andCruseridIn(ids).andLeavemarkingEqualTo(IrpLeaveapply.LEAVE));
			}else{
				example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
			}
		}else{
			example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
		}
		try {
			num = this.irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<IrpLeaveapply> findAllQuery(PageUtil pageUtil,
			String _oOrderby, String _sSearchWord, String _sSearchType) {
		List<IrpLeaveapply> list2=new ArrayList<IrpLeaveapply>();
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		if(_sSearchWord != null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				IrpUserExample example1 = new IrpUserExample();
				example1.createCriteria().andUsernameLike("%"+_sSearchWord+"%");
				List<Long> ids=new ArrayList<Long>();
				List<IrpUser> list=null;
				try {
					 list=this.irpUserDAO.selectByExample(example1);
					if(list.size()>0){
						for(IrpUser oper:list){
							ids.add(oper.getUserid());
						}
					}else{
						ids.add(0l);
					}				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				example.or(example.createCriteria().andCruseridIn(ids).andLeavemarkingEqualTo(IrpLeaveapply.LEAVE));
			}else{
				example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
			} 
		}else{
			example.createCriteria().andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
		}
		 
		
			
			list2 = this.irpLeaveapplyDAO.selectByExample(example,pageUtil);
		
		return list2;
	}

	@Override
	public void exportAllSignInfoToZip(List<IrpLeaveapplyInfo> list, String getPath,
			String fileName) {
		try {
			//创建文件路径
			String path=getPath+"/leave";
			
			// 创建文件夹;
			createFile(path);
			// 创建excel文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path+"/"
					+ fileName+"_"+".xls"));
			// 接收返回的isend,用来判断是否循环到最后
			int count = 0;
			// 初始化用户id
			if (list != null && list.size() != 0) {
				do {
					count = createAllSignToExcel(list, workbook);
				} while (count != -1);
				// 写入Excel表格中;
				workbook.write();
			}
			
			// 关闭流;
			workbook.close();
			// 生成.zip文件;
			craeteZipPath(path,fileName);
			// 删除目录下所有的文件;
			File file = new File(path);
			// 删除文件;
			deleteExcelPath(file);
			// 重新创建文件;
			// file.mkdirs();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public List<IrpLeaveapply> getQueryListForAdmin(String marking,
			Date starttimes, Date endtimes, String applystatus,
			String applytypeid, int emergency) {
		List<IrpLeaveapply> list=new ArrayList<IrpLeaveapply>();
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria criteria=example.createCriteria();
		//标识：工作还是加班
		if(marking!=""&&marking!=null){
			criteria.andLeavemarkingEqualTo(Integer.parseInt(marking));			
	     }

		 //时间段   
		if(starttimes!=null && endtimes!=null){
		    criteria.andCrtimeBetween(starttimes,endtimes);	
		    
		    
		}
		//请假进度
		if(Integer.parseInt(applystatus)!=0){
			criteria.andApplystatusEqualTo(Integer.parseInt(applystatus));
			
		}
		//请假类型
		if(Integer.parseInt(applytypeid)!=0){
			criteria.andApplytypeidEqualTo(Long.parseLong(applytypeid));
			
		}
		//紧急程度
		if(emergency!=0){
			criteria.andEmergencyEqualTo(emergency);
			
		}
		
		try {
			list=this.irpLeaveapplyDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 循环写入Excel
	 * 
	 * @param signInfo
	 * @return
	 */
	private int createAllSignToExcel(List<IrpLeaveapplyInfo> list,
			WritableWorkbook workbook) throws Exception {
		
		// 循环中计数器
				int count = 0;
				// 判断是不是最后一个 1代表不是最后一个, -1代表是最后一个
				int isend = 1;				
				WritableSheet sheet = workbook.createSheet("请假统计表", 1);
				boolean isexport = false;
				// 设置默认宽度;
				sheet.getSettings().setDefaultColumnWidth(20);
				// 设置字体,正常状态(黑色);
				WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						Colour.BLACK);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				
				// 设置边框;
				cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
				// 设置自动换行;
				cellFormat1.setWrap(true);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
				// 设置字体,异常状态(红色)
				WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						Colour.BLACK);

				WritableCellFormat cellFormat2 = new WritableCellFormat(font2);				
				// 设置边框;
				cellFormat2.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
				// 设置自动换行;
				cellFormat2.setWrap(true);
				// 设置文字居中对齐方式;
				cellFormat2.setAlignment(Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
				Label label = new Label(0, 0, "申请人", cellFormat1);
				Label label1 = new Label(1, 0, "申请标题", cellFormat1);
				Label label2 = new Label(2, 0, "创建时间", cellFormat1);
				Label label3 = new Label(3, 0, "审核进度", cellFormat1);
				Label label4 = new Label(4, 0, "审核人", cellFormat1);
				Label label5 = new Label(5, 0, "请假天数", cellFormat1);
				Label label6 = new Label(6, 0, "请假类型", cellFormat1);
				Label label7 = new Label(7, 0, "请假开始时间", cellFormat1);
				Label label8 = new Label(8, 0, "请假结束时间", cellFormat1);
				sheet.addCell(label);
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				sheet.addCell(label5);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);
				// 行数计数器
				int i = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				int pass=0;
				int upass=0;
				int refuse=0;
				
			for (; count < list.size(); count++) {
				

				IrpLeaveapplyInfo info = list.get(count);

					Label lab1 = new Label(0, i + 1,info.getCruserid(), cellFormat1);
					Label lab2 = new Label(1, i + 1,info.getTitle(), cellFormat1);
					Label lab3 = new Label(2, i + 1, sdf.format(info.getCrtime()).toString(), cellFormat1);
					Label lab4 = new Label(3, i + 1,info.getApplystatus(),cellFormat1);
					Label lab5 = new Label(4, i + 1, info.getChecker(),cellFormat1);
					Label lab6 = new Label(5, i + 1, info.getLeavedays().toString(),cellFormat1);
					Label lab7 = new Label(6, i + 1, info.getApplytypeid(),cellFormat1);
					Label lab8 = new Label(7, i + 1, sdf.format(info.getStarttime()).toString(),cellFormat1);
					Label lab9 = new Label(8, i + 1, sdf.format(info.getEndtime()).toString(),cellFormat1);
					
					sheet.addCell(lab1);
					sheet.addCell(lab2);
					sheet.addCell(lab3);
					sheet.addCell(lab4);
					sheet.addCell(lab5);
					sheet.addCell(lab6);
					sheet.addCell(lab7);
					sheet.addCell(lab8);
					sheet.addCell(lab9);
					i++;
					if (count == (list.size() - 1)) {
						isend = -1;
					}
					if (isend != -1
							&& !list.get(count + 1).getCruseridl()
									.equals(userid)) {
						isexport = true;
					}
					
					if(info.getApplystatus().equalsIgnoreCase("已同意")){
						pass=pass+1;
					}
					if(info.getApplystatus().equalsIgnoreCase("未审核")){
						upass=upass+1;
					}
					if(info.getApplystatus().equalsIgnoreCase("已拒绝")){
						refuse=refuse+1;
					}
					
					
					
					

				
			
			}
			int total=list.size();
			
			Label lb1 = new Label(0, i + 1,"总请假条数", cellFormat1);
			Label lb2 = new Label(1, i + 1,total+"条");
			Label lb3 = new Label(2, i + 1,"已同意条数", cellFormat1);
			Label lb4 = new Label(3,i + 1,pass+"条");
			Label lb5 = new Label(4, i + 1, "未审核条数",cellFormat1);
			Label lb6 = new Label(5,i + 1, upass+"条");
			Label lb7 = new Label(6, i + 1, "已拒绝条数",cellFormat1);
			Label lb8 = new Label(7, i + 1, refuse+"条");
			sheet.addCell(lb1);
			sheet.addCell(lb2);
			sheet.addCell(lb3);
			sheet.addCell(lb4);
			sheet.addCell(lb5);
			sheet.addCell(lb6);
			sheet.addCell(lb7);
			sheet.addCell(lb8);
		
			
				return isend;
			}	

	/**
	 * 删除目录下所有的文件;
	 * 
	 * @param path
	 */
	public boolean deleteExcelPath(File file) {
		String[] files = null;
		if (file != null) {
			files = file.list();
		}

		if (file.isDirectory()) {
			for (int i = 0; i < files.length; i++) {
				boolean bol = deleteExcelPath(new File(file, files[i]));
				if (bol) {
					// System.out.println("删除成功!");
				} else {
					// System.out.println("删除失败!");
				}
			}
		}
		return file.delete();
	}
	
	@Override
	public int findListQueryNumsFromExp(String marking, Date startTime,
			Date endTime, String applystatus, String applytypeid,
			String searchName, int emergency) {

		int nCount = 0;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria criteria=example.createCriteria();
		//标识：工作还是加班
		if(marking!=""&&marking!=null){
			criteria.andLeavemarkingEqualTo(Integer.parseInt(marking));			
	     }
		
		 //时间段   
		if(endTime!=null && startTime!=null){
		    criteria.andCrtimeBetween(startTime,endTime);	
		    
		    
		}
		//请假进度
		if(Integer.parseInt(applystatus)!=0){
			criteria.andApplystatusEqualTo(Integer.parseInt(applystatus));
			
		}
		//请假类型
		if(Integer.parseInt(applytypeid)!=0){
			
			criteria.andApplytypeidEqualTo(Long.parseLong(applytypeid));
			
		}
		//紧急程度
				if(emergency!=0){
					criteria.andEmergencyEqualTo(emergency);
					
				}
				
				if(searchName != "" && searchName != null){
						IrpUserExample example1 = new IrpUserExample();
						example1.createCriteria().andUsernameLike("%"+searchName+"%");
						List<Long> ids=new ArrayList<Long>();
						List<IrpUser> list=null;
						try {
							 list=this.irpUserDAO.selectByExample(example1);
							if(list.size()>0){
								for(IrpUser oper:list){
									ids.add(oper.getUserid());
								}
							}else{
								ids.add(0l);
							}				
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(!ids.equals(0)){
							criteria.andCruseridIn(ids);
							
						}
					
				}
		try {
			nCount=this.irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	
	}
	@Override
	public List<IrpLeaveapply> getQueryListFromExp(PageUtil pageUtil,
			String marking, Date startTime, Date endTime, String applystatus,
			String applytypeid, String searchName, int emergency) {
		 List<IrpLeaveapply> list1=new ArrayList<IrpLeaveapply>();
			IrpLeaveapplyExample example = new IrpLeaveapplyExample();
			Criteria criteria=example.createCriteria();
			//标识：工作还是加班
			if(marking!=""&&marking!=null){
				criteria.andLeavemarkingEqualTo(Integer.parseInt(marking));			
		     }

			 //时间段   
			if(endTime!=null && startTime!=null){
			    criteria.andCrtimeBetween(startTime,endTime);	
			    
			    
			}
			//请假进度
			if(Integer.parseInt(applystatus)!=0){
				criteria.andApplystatusEqualTo(Integer.parseInt(applystatus));
				
			}
			//请假类型
			if(Integer.parseInt(applytypeid)!=0){
				criteria.andApplytypeidEqualTo(Long.parseLong(applytypeid));
				
			}
			//紧急程度
			if(emergency!=0){
				criteria.andEmergencyEqualTo(emergency);
				
			}
			if(searchName != "" && searchName != null){
				IrpUserExample example1 = new IrpUserExample();
				example1.createCriteria().andUsernameLike("%"+searchName+"%");
				List<Long> ids=new ArrayList<Long>();
				List<IrpUser> list=null;
				try {
					 list=this.irpUserDAO.selectByExample(example1);
					if(list.size()>0){
						for(IrpUser oper:list){
							ids.add(oper.getUserid());
						}
					}else{
						ids.add(0l);
					}				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(!ids.equals(0)){
					criteria.andCruseridIn(ids);
					
				}
			
		}  if(pageUtil==null){
			example.setOrderByClause("cruserid,crtime");
		}else{
			
			example.setOrderByClause("crtime desc");
		}
			list1=this.irpLeaveapplyDAO.selectByExample(example, pageUtil);
			return list1;
	}

	@Override
	public int selectTotaltimeByList(List<Long> _userList, Date _starttime,
			Date _endtime, Long _applytypeid) {
		int totaltime = 0;
		List<IrpLeaveapply> list = null;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria create = example.createCriteria();
		create.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
		if(_starttime!=null&&_endtime!=null){
			create.andCrtimeBetween(_starttime, _endtime);
		}
		if(_applytypeid!=null&&!_applytypeid.equals("")){
			create.andApplytypeidEqualTo(_applytypeid);
		}
		if(_userList.size()>0){
			create.andCruseridIn(_userList);
		}else{
			create.andCruseridEqualTo(0L);
		}
		create.andLeavemarkingEqualTo(IrpLeaveapply.WORK);
		try {
			list = irpLeaveapplyDAO.selectByExample(example);
			if(list!=null){
				for(IrpLeaveapply irp:list){
					totaltime = (int) (irp.getLeavedays()+totaltime);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totaltime;
	}
	@Override
	public int selectTotaltimeByListForLeave(List<Long> _userList, Date _starttime,
			Date _endtime) {
		int totaltime = 0;
		List<IrpLeaveapply> list = null;
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Criteria create = example.createCriteria();
		if(_starttime!=null&&_endtime!=null){
			create.andCrtimeBetween(_starttime, _endtime);
		}
		
		if(_userList.size()>0){
			create.andCruseridIn(_userList);
		}else{
			create.andCruseridEqualTo(0L);
		}
		create.andLeavemarkingEqualTo(IrpLeaveapply.LEAVE);
		try {
			list = irpLeaveapplyDAO.selectByExample(example);
			if(list!=null){
				for(IrpLeaveapply irp:list){
					totaltime = (int) (irp.getLeavedays()+totaltime);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totaltime;
	}

	@Override
	public int deleteLeaveapply(Long _leaveapplyid) {
		int res = 0;
		try {
			res = irpLeaveapplyDAO.deleteByPrimaryKey(_leaveapplyid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int upLeaveapplyinfo(IrpLeaveapply _irpLeaveapply) {
		int res = 0;
		try {
			res = irpLeaveapplyDAO.updateByPrimaryKeySelective(_irpLeaveapply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
