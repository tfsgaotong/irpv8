package com.tfs.irp.topic.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.topic.dao.IrpTopicDAO;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.entity.IrpTopicExample;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTopicServiceImpl implements IrpTopicService {

	
	private IrpTopicDAO irpTopicDAO;
	
	public IrpTopicDAO getIrpTopicDAO() {
		return irpTopicDAO;
	}

	public void setIrpTopicDAO(IrpTopicDAO irpTopicDAO) {
		this.irpTopicDAO = irpTopicDAO;
	}

	@Override
	public int addTopic(String _topicname) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpTopicExample  example = new IrpTopicExample();
		example.createCriteria().andTopicnameEqualTo(_topicname);
		IrpTopic  record = new IrpTopic();
		IrpTopic irpTopicObj =  getIrpTopic(_topicname);
		
		if(irpTopicObj!=null){
			//更新主题热度
			
			record.setTopicclicknum(irpTopicObj.getTopicclicknum()+1);
			try {
				irpTopicDAO.updateByExampleSelective(record, example);
				_status = 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//新建主题
			
			IrpTopic  irpTopic = new IrpTopic();
			irpTopic.setTopicid(TableIdUtil.getNextId(IrpTopic.TABLE_NAME));
			irpTopic.setTopicname(_topicname.trim());
			irpTopic.setCrtime(new Date());
			irpTopic.setExpandfield(LoginUtil.getLoginUser().getUsername());
			irpTopic.setUserid(LoginUtil.getLoginUserId());
			int topictype = 0;
			
			if(SysConfigUtil.getSysConfigValue(IrpTopic.TOPICAUDITISBOOL).equals("true")){
			    if(LoginUtil.getLoginUser().isTopicManager()){
			    	
			        topictype=IrpTopic.TOPICTYPE_NORMAL;
			    }else{
			    	topictype = IrpTopic.TOPICTYPE_ILLEGALITY;
			    }				
			}else{
				topictype = IrpTopic.TOPICTYPE_NORMAL;
			}

		    
			irpTopic.setTopictype(topictype);
			
			try {
				irpTopicDAO.insertSelective(irpTopic);
				_status = 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return _status;
	}

	@Override
	public IrpTopic getIrpTopic(String _topicname) {
		// TODO Auto-generated method stub
		IrpTopic irpTopic = null;
		IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andTopicnameEqualTo(_topicname);
		try {
         	if(this.irpTopicDAO.selectByExample(example).size()==0){
         		
         		return irpTopic;
         	}else{
         		
         		irpTopic = this.irpTopicDAO.selectByExample(example).get(0);
         	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return irpTopic;
	}

	@Override
	public List<IrpTopic> getAllIrpTopic(PageUtil pageUtil,Integer _topictype,String _orderby) {
		// TODO Auto-generated method stub
		List<IrpTopic> list = null;
		IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andTopictypeEqualTo(_topictype);
		if(_orderby==null){
			example.setOrderByClause("topicclicknum desc");
		}else{
			example.setOrderByClause(_orderby);
		}
		try {
			list = this.irpTopicDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getAllIrpTopicLength(Integer _topictype,String _orderby) {
		// TODO Auto-generated method stub
		int topiclength = 0;
      IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andTopictypeEqualTo(_topictype);
		if(_orderby==null){
			example.setOrderByClause("topicclicknum desc");
		}else{
			example.setOrderByClause(_orderby);
		}
		       
		try {
			topiclength = 	irpTopicDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return topiclength;
	}

	@Override
	public int deleteTopicById(Long _topicid) {
		// TODO Auto-generated method stub
		int _status = 0;
		
		try {
			_status = this.irpTopicDAO.deleteByPrimaryKey(_topicid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _status;
	}

	@Override
	public IrpTopic getIrpTopicById(Long _topicid) {
		// TODO Auto-generated method stub
		IrpTopic irpTopic = null;
		try {
			irpTopic =	this.irpTopicDAO.selectByPrimaryKey(_topicid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpTopic;
	}

	@Override
	public int updateTopicDesc(IrpTopic irpTopic) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpTopic _irpTopic = new IrpTopic();
		_irpTopic.setTopicid(irpTopic.getTopicid());
		_irpTopic.setTopicdesc(irpTopic.getTopicdesc());
		
		try {
			status = this.irpTopicDAO.updateByPrimaryKeySelective(_irpTopic);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int updateTopicHotNum(String _topicname) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpTopic record = new IrpTopic();
		long hotnum = getIrpTopic(_topicname).getTopicclicknum();
		if(hotnum<1){
			hotnum=1;
		}
		record.setTopicclicknum(hotnum-1);
		IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andTopicnameEqualTo(_topicname);
		
		try {
			status = this.irpTopicDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public long  addTopic(IrpTopic irpTopic) {
		// TODO Auto-generated method stub
		long status = 0;
		IrpTopic record = new IrpTopic();
		long   topicid = TableIdUtil.getNextId(IrpTopic.TABLE_NAME);
		record.setTopicid(topicid);
		String topicname = irpTopic.getTopicname().trim().replace("#","");
		
		record.setTopicname("#"+topicname+"#");
		record.setTopicdesc(irpTopic.getTopicdesc().trim());
		record.setTopicclicknum(0l);
		record.setCrtime(new Date());
		record.setExpandfield(LoginUtil.getLoginUser().getUsername());
		record.setUserid(LoginUtil.getLoginUserId());
		int topictype = 0;
		
	    if(LoginUtil.getLoginUser().isTopicManager()){
	    	
	        topictype=IrpTopic.TOPICTYPE_NORMAL;
	    }else{
	    	topictype = IrpTopic.TOPICTYPE_ILLEGALITY;
	    }
	    record.setTopictype(topictype);
		try {
			this.irpTopicDAO.insert(record);
			status = topicid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int updateTopicType(Long _topicid, Integer _topictype) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpTopic record = new IrpTopic();
		record.setTopicid(_topicid);
		record.setTopictype(_topictype);
		
		try {
			status = this.irpTopicDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<IrpTopic> getAllIrpTopicByMine(PageUtil pageUtil, Long _userid,String _orderby) {
		// TODO Auto-generated method stub
		List<IrpTopic> list = null;
		IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andUseridEqualTo(_userid);
		if(_orderby==null){
			example.setOrderByClause("topicclicknum desc");
		}else{
			example.setOrderByClause(_orderby);
		}
		try {
			list = this.irpTopicDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getAllIrpTopicByMineCount(Long _userid,String _orderby) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpTopicExample example = new IrpTopicExample();
		example.createCriteria().andUseridEqualTo(_userid);
		if(_orderby==null){
			example.setOrderByClause("topicclicknum desc");
		}else{
			example.setOrderByClause(_orderby);
		}
		try {
			num = this.irpTopicDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	
	@Override
	public List<IrpTopic> findAllToPic(List<Long> topicIds) {
		List<IrpTopic> list=null;
		IrpTopicExample example=new IrpTopicExample();
		example.createCriteria().andTopicidIn(topicIds);
		try {
			list=irpTopicDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
