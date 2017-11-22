package com.tfs.irp.topiclink.service.impl;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.topic.dao.IrpTopicDAO;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.entity.IrpTopicExample;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.topiclink.dao.IrpTopicLinkDAO;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.entity.IrpTopicLinkExample;
import com.tfs.irp.topiclink.service.IrpTopicLinkService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTopicLinkServiceImpl implements IrpTopicLinkService {

	private IrpTopicLinkDAO irpTopicLinkDAO;
	
	private IrpTopicDAO irpTopicDAO;
	
	private IrpTopicService irpTopicService;

	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}

	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
	}

	public IrpTopicDAO getIrpTopicDAO() {
		return irpTopicDAO;
	}

	public void setIrpTopicDAO(IrpTopicDAO irpTopicDAO) {
		this.irpTopicDAO = irpTopicDAO;
	}

	public IrpTopicLinkDAO getIrpTopicLinkDAO() {
		return irpTopicLinkDAO;
	}

	public void setIrpTopicLinkDAO(IrpTopicLinkDAO irpTopicLinkDAO) {
		this.irpTopicLinkDAO = irpTopicLinkDAO;
	}

	@Override
	public int addTopicLink(String _topicname, long _microblogid,
			Integer _topictype) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpTopicLink irpTopicLink = new IrpTopicLink();
		Long topiclinkid = TableIdUtil.getNextId(IrpTopicLink.TABLE_NAME);
		irpTopicLink.setTopiclinkid(topiclinkid);
		irpTopicLink.setTopicname(_topicname);
		irpTopicLink.setMicroblogid(_microblogid);
		irpTopicLink.setTopictype(_topictype);
		irpTopicLink.setCrtime(new Date());
		irpTopicLink.setCruserid(LoginUtil.getLoginUserId());
		//根据当前登录用户的角色
		Long status = 0l;
		if(LoginUtil.getLoginUser().isTopicManager()){
			status = IrpTopicLink.TOPICLINKNORMAL;
		}else{
			//查看当前的专题是否属于审核通过的状态
			if(SysConfigUtil.getSysConfigValue(IrpTopic.TOPICAUDITISBOOL).equals("true")){
				IrpTopic irpTopictype = null;
				IrpTopicExample topicexemple = new IrpTopicExample();
				topicexemple.createCriteria().andTopicnameEqualTo(_topicname);
				 try {
					 List<IrpTopic> topiclist = irpTopicDAO.selectByExample(topicexemple);
					 if(topiclist.size()>0){
						 irpTopictype =	topiclist.get(0);
						 if (irpTopictype.getTopictype().equals(IrpTopic.TOPICTYPE_NORMAL)){
								status = IrpTopicLink.TOPICLINKNORMAL;
							}else{
								status = IrpTopicLink.TOPICLINKNOAUDIT;	
							} 
					 }else{
						 status = IrpTopicLink.TOPICLINKNOAUDIT;	
					 }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				status = IrpTopicLink.TOPICLINKNORMAL;
			}
		}
		irpTopicLink.setExpandfield(status);
		try {
			this.irpTopicLinkDAO.insertSelective(irpTopicLink);
			_status =1;
			if(_status == 1){
				IrpTopic irpTopic = this.irpTopicService.getIrpTopic(_topicname);
				updateTopicIdById(irpTopic.getTopicid(),topiclinkid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _status;
	}

	@Override
	public int updateTopicLink(String _topicname) {
		// TODO Auto-generated method stub
		int status = 0;
		
		IrpTopicLinkExample example = new IrpTopicLinkExample();
		example.createCriteria().andTopicnameEqualTo(_topicname);	
		IrpTopicLink record = new IrpTopicLink();
		record.setExpandfield(IrpTopicLink.TOPICLINKDELETE);
		try {
			
			status = this.irpTopicLinkDAO.updateByExampleSelective(record, example);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public IrpTopicLink getTopicListLink(Long _microblogid) {
		// TODO Auto-generated method stub
		IrpTopicLink irpTopicLink = null;
		
		IrpTopicLinkExample example = new IrpTopicLinkExample();
	 
		example.createCriteria().andMicroblogidEqualTo(_microblogid)
		                        .andTopictypeEqualTo(IrpTopicLink.TOPICTYPE_MICR)
		                        .andExpandfieldEqualTo(IrpTopicLink.TOPICLINKNORMAL);
		
		try {
			int msg = this.irpTopicLinkDAO.selectByExample(example).size();
			if(msg>0){
				irpTopicLink = this.irpTopicLinkDAO.selectByExample(example).get(0);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpTopicLink;
	}

	@Override
	public List<IrpTopicLink> getIrpTopicLinkByName(String _topicname) {
		// TODO Auto-generated method stub
		List<IrpTopicLink> irptopiclink = null;
		
		IrpTopicLinkExample example = new IrpTopicLinkExample();
		example.createCriteria().andTopicnameEqualTo(_topicname)
                                .andTopictypeEqualTo(IrpTopicLink.TOPICTYPE_MICR)
                                .andExpandfieldEqualTo(IrpTopicLink.TOPICLINKNORMAL);
		
		try {
			irptopiclink = this.irpTopicLinkDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irptopiclink;
	}

	@Override
	public List selectTopicNumByName(String _topiclinkname) {
		// TODO Auto-generated method stub
		List list = null;
		list = this.irpTopicLinkDAO.selectTopicNumByName(_topiclinkname);
		return list;
	}

	@Override
	public int updateIrpTopicLink(String _topiclinkname,Long _topicLinkType) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpTopicLinkExample example = new IrpTopicLinkExample();
		example.createCriteria().andTopicnameEqualTo(_topiclinkname)
		                        .andExpandfieldEqualTo(IrpTopicLink.TOPICLINKNOAUDIT);
		IrpTopicLink record = new IrpTopicLink();
		record.setExpandfield(_topicLinkType);
		
		try {
			status =	this.irpTopicLinkDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int updateTopicIdById(Long _topicid, Long _id) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpTopicLink itl = new IrpTopicLink();
		itl.setTopiclinkid(_id);
		itl.setTopicid(_topicid);
		try {
			status = this.irpTopicLinkDAO.updateByPrimaryKeySelective(itl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
}
