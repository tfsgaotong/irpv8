package com.tfs.irp.grouptestpaper.service.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.grouptestpaper.dao.IrpGroupTestpaperDAO;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaper;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaperExample;
import com.tfs.irp.grouptestpaper.service.IrpGroupTestpaperService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpGroupTestpaperServiceImpl implements IrpGroupTestpaperService {
	
		private IrpGroupTestpaperDAO irpGroupTestpaperDAO;

		public IrpGroupTestpaperDAO getIrpGroupTestpaperDAO() {
			return irpGroupTestpaperDAO;
		}

		public void setIrpGroupTestpaperDAO(IrpGroupTestpaperDAO irpGroupTestpaperDAO) {
			this.irpGroupTestpaperDAO = irpGroupTestpaperDAO;
		}

		@Override
		public int addIrpGroupTestpaper(IrpGroupTestpaper _irpGroupTestpaper) {
			int status = 0;
			if (_irpGroupTestpaper!=null) {
				 LogUtil logUtil=new LogUtil("GROUPTESTPAPER_ADD",_irpGroupTestpaper);
				try {
					this.irpGroupTestpaperDAO.insertSelective(_irpGroupTestpaper);
					status = 1;
					logUtil.successLogs( "为考试id["+_irpGroupTestpaper.getTestpaperid()+"]添加考试组织["+logUtil.getLogUser()+"]成功");
				} catch (SQLException e) {
					e.printStackTrace();
					logUtil.errorLogs( "为考试id["+_irpGroupTestpaper.getTestpaperid()+"]添加考试组织["+logUtil.getLogUser()+"]失败",e);
				}
			}
			return status;
		}

		@Override
		public List<IrpGroupTestpaper> getGTPaperByTPId(Long _testpaperid,
				Integer _isdel) {
			// TODO Auto-generated method stub
			List<IrpGroupTestpaper> list = null;
			IrpGroupTestpaperExample example = new IrpGroupTestpaperExample();
			example.createCriteria().andIsdelEqualTo(_isdel)
									.andTestpaperidEqualTo(_testpaperid);
			
			try {
				list = this.irpGroupTestpaperDAO.selectByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}

		@Override
		public int deleteGPByTPId(Long _testpaperid) {
			int msg = 0;
			  LogUtil logUtil=null;
			IrpGroupTestpaperExample example = new IrpGroupTestpaperExample();
			example.createCriteria().andTestpaperidEqualTo(_testpaperid);
			try {
				List<IrpGroupTestpaper> s = irpGroupTestpaperDAO.selectByExample(example);
				if(s!=null&&s.size()>0){
					 logUtil=new LogUtil("GROUPTESTPAPER_DEL",s.get(0));
				}
				msg = this.irpGroupTestpaperDAO.deleteByExample(example);
				if(logUtil!=null){
					logUtil.successLogs( "删除考试id为["+_testpaperid+"]的所有参加考试组织["+logUtil.getLogUser()+"]成功");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				if(logUtil!=null){
					logUtil.errorLogs( "删除考试id为["+_testpaperid+"]的所有参加考试组织["+logUtil.getLogUser()+"]失败",e);
				}
			}
			return msg;
		}

		@Override
		public int selectStatusOfGTId(Long _groupid, Long _testpaperid) {
			// TODO Auto-generated method stub
			int msg = 0;
			IrpGroupTestpaperExample example = new IrpGroupTestpaperExample();
			example.createCriteria().andTestpaperidEqualTo(_testpaperid)
									.andGroupidEqualTo(_groupid);
			try {
				msg = this.irpGroupTestpaperDAO.selectByExample(example).size();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return msg;
		}

		@Override
		public List<Long> getGroupTestpapeByGroup(Long _goupid, Integer _isdel,PageUtil _pageutil) {
			// TODO Auto-generated method stub
			List<Long> list  = new ArrayList<Long>();
			IrpGroupTestpaperExample example = new IrpGroupTestpaperExample();
			example.createCriteria().andGroupidEqualTo(_goupid)
									.andIsdelEqualTo(_isdel);
			example.setOrderByClause("grouptpaperid desc");
			 try {
				 
			List<IrpGroupTestpaper>  listg = irpGroupTestpaperDAO.selectByExample(example);
			if (listg.size()>0) {
				for (int i = 0; i < listg.size(); i++) {
					IrpGroupTestpaper igt = listg.get(i);
					list.add(igt.getTestpaperid());
				}	
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}

		@Override
		public int getGroupTestpapeByGroup(Long _goupid, Integer _isdel) {
			// TODO Auto-generated method stub
			int num = 0;
			IrpGroupTestpaperExample example = new IrpGroupTestpaperExample();
			example.createCriteria().andGroupidEqualTo(_goupid)
									.andIsdelEqualTo(_isdel);
			try {
				num = irpGroupTestpaperDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return num;
		}


}
