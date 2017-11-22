package com.tfs.irp.selectkey.service.impl;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.selectkey.dao.IrpSelectKeyDAO;
import com.tfs.irp.selectkey.entity.IrpSelectKey;
import com.tfs.irp.selectkey.entity.IrpSelectKeyExample;
import com.tfs.irp.selectkey.entity.IrpSelectKeyExample.Criteria;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.tag.dao.IrpTagDAO;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpTagExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
public class IrpSelectKeyServiceImpl implements IrpSelectKeyService{

	private IrpSelectKeyDAO irpSelectKeyDAO;
	   private IrpTagDAO irpTagDAO;//标签表dao

	public IrpTagDAO getIrpTagDAO() {
		return irpTagDAO;
	}

	public void setIrpTagDAO(IrpTagDAO irpTagDAO) {
		this.irpTagDAO = irpTagDAO;
	}

	public IrpSelectKeyDAO getIrpSelectKeyDAO() {
		return irpSelectKeyDAO;
	}

	public void setIrpSelectKeyDAO(IrpSelectKeyDAO irpSelectKeyDAO) {
		this.irpSelectKeyDAO = irpSelectKeyDAO;
	}
	@Override
	public List<String> userLogs() {
		 List<String> list=null;
		try {
			list=irpSelectKeyDAO.selectTagByUserIdDesc(LoginUtil.getLoginUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int delete(Long _sid) {
		int nCount=0;
		try {
			nCount=irpSelectKeyDAO.deleteByPrimaryKey(_sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public void save(String _sKey) {
		LogUtil logUtil=new LogUtil();
		String _ip=logUtil.getIpAddr();
		try { 
			IrpSelectKey irpSelectKey=new IrpSelectKey();
			irpSelectKey.setUserid(LoginUtil.getLoginUserId());
			irpSelectKey.setUserip(_ip);
			irpSelectKey.setSkey(_sKey);
			irpSelectKey.setCrtime(new Date());
			irpSelectKey.setSid(TableIdUtil.getNextId(IrpSelectKey.TABLE_NAME));
			irpSelectKeyDAO.insertSelective(irpSelectKey);
			//在讲这个词添加到tag表中
			addTag(_sKey);//有加个数， 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	@Override
	public void saveserachword(String _sKey) {
		LogUtil logUtil=new LogUtil();
		String _ip=logUtil.getIpAddr();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		try { 
			IrpSelectKey irpSelectKey=new IrpSelectKey();
			irpSelectKey.setUserid(irpuser.getUserid());
			irpSelectKey.setUserip(_ip);
			irpSelectKey.setSkey(_sKey);
			irpSelectKey.setCrtime(new Date());
			irpSelectKey.setSid(TableIdUtil.getNextId(IrpSelectKey.TABLE_NAME));
			irpSelectKeyDAO.insertSelective(irpSelectKey);
			//在讲这个词添加到tag表中
			addTag(_sKey);//有加个数， 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	//添加标签
		public void  addTag(String sTags){
			sTags = sTags.trim();
			//替换所有的分隔符为逗号
			sTags = sTags.replaceAll(" ", ",");
			sTags = sTags.replaceAll("；", ",");
			sTags = sTags.replaceAll(";", ",");
			sTags = sTags.replaceAll("，", ","); 
			try { 
				String[] arrTags = sTags.split(",");
				for(int i=0;i<arrTags.length;i++){
					String sTag = arrTags[i].trim();
					if(sTag==null || sTag.length()==0){
						continue;
					}
					IrpTagExample example = new IrpTagExample();
					example.createCriteria().andTagnameEqualTo(sTag);
					List<IrpTag> list = irpTagDAO.selectByExample(example);
					if(list==null ||list.size()==0){
						IrpTag record=new IrpTag();
						record.setTagid(TableIdUtil.getNextId(IrpTag.TABLE_NAME));
						record.setTagname(sTag);
						irpTagDAO.insertSelective(record);
					} else{
						IrpTag tag=list.get(0);
						tag.setNcount(tag.getNcount()+1);
						IrpTagExample example2=new IrpTagExample();
						example2.createCriteria().andTagidEqualTo(tag.getTagid());
						irpTagDAO.updateByExample(tag, example);
					}
				}
			} catch (Exception e) {
					e.printStackTrace();
			} 
			/**
			 * 将字符串中的重复词语给去掉
			 */
			HashSet<String> onlyStr=new HashSet<String>();
			if(sTags!=null && sTags.length()>0){
				String[] strs=sTags.split(",");
				for (int i = 0; i < strs.length; i++) {
					onlyStr.add(strs[i]);
				}
			}
		} 
	@Override
	public void save(Long _userId, String _sKey, String _ip) {
		try { 
				IrpSelectKey irpSelectKey=new IrpSelectKey();
				irpSelectKey.setUserid(_userId);
				irpSelectKey.setUserip(_ip);
				irpSelectKey.setSkey(_sKey);
				irpSelectKey.setCrtime(new Date());
				irpSelectKey.setSid(TableIdUtil.getNextId(IrpSelectKey.TABLE_NAME));
				irpSelectKeyDAO.insertSelective(irpSelectKey);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void save(IrpSelectKey irpSelectKey) {
		try {
			if(irpSelectKey!=null){
				irpSelectKey.setSid(TableIdUtil.getNextId(IrpSelectKey.TABLE_NAME));
				irpSelectKeyDAO.insertSelective(irpSelectKey);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<IrpSelectKey> allData(PageUtil pageUtil, Long _userId,String _sKey) {
		List<IrpSelectKey> irpSelectKeys=null;
		try {
			 	IrpSelectKeyExample example=new IrpSelectKeyExample();
				 Criteria criteria = example.createCriteria();
				 if(_sKey!=null && !_sKey.equals("")){
					 criteria.andSkeyLike("%"+_sKey+"%");
				 }
				 if(_userId!=null && _userId!=0L){
					 criteria.andUseridEqualTo(_userId);
				 }
				 irpSelectKeyDAO.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return irpSelectKeys;
	}
	@Override
	public  IrpSelectKey maxData( Long _userId) {
		try {
			 	IrpSelectKeyExample example=new IrpSelectKeyExample();
				 Criteria criteria = example.createCriteria();
				 if(_userId!=null && _userId!=0L){
					 criteria.andUseridEqualTo(_userId);
				 }
				 irpSelectKeyDAO.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String> selectByUserIdOrderbyCountDescBetweenTime(Long userId, Date starttime, Date endtime) {
		List<String> keyList=null;
		try {
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("userid",userId);
			map.put("starttime",starttime);//上周结束时间
			map.put("endtime",endtime);//到现在
			irpSelectKeyDAO.selectByUserIdOrderbyCountDescBetweenTime(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return keyList;
	}
}
