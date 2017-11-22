package com.tfs.irp.messagecontent.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.tfs.irp.leaveoper.entity.IrpLeaveoperExample;
import com.tfs.irp.messagecontent.dao.IrpMessageContentDAO;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.entity.IrpMessageContentExample;
import com.tfs.irp.messagecontent.entity.IrpMessageContentExample.Criteria;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.userprivacy.dao.IrpUserPrivacyDAO;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacyExample;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.mailmessage.MailSenderInfo;
import com.tfs.irp.util.mailmessage.SimpleMailSender;

public class IrpMessageContentServiceImpl implements IrpMessageContentService {

	private IrpMessageContentDAO irpMessageContentDAO;
	private IrpUserPrivacyDAO irpUserPrivacyDAO;
	private IrpUserDAO irpUserDAO;
	
	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}
	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}
	public IrpUserPrivacyDAO getIrpUserPrivacyDAO() {
		return irpUserPrivacyDAO;
	}
	public void setIrpUserPrivacyDAO(IrpUserPrivacyDAO irpUserPrivacyDAO) {
		this.irpUserPrivacyDAO = irpUserPrivacyDAO;
	}
	public IrpMessageContentDAO getIrpMessageContentDAO() {
		return irpMessageContentDAO;
	}
	public void setIrpMessageContentDAO(IrpMessageContentDAO irpMessageContentDAO) {
		this.irpMessageContentDAO = irpMessageContentDAO;
	}
	@Override
	public int addMessageContent(IrpMessageContent _irpMessageContent) {
		// TODO Auto-generated method stub
		int _messageStatus = 0;
		IrpMessageContent  irpMessageContent = new IrpMessageContent();
		irpMessageContent.setMessageid(TableIdUtil.getNextId(IrpMessageContent.TABLE_NAME));
		irpMessageContent.setFromUid(_irpMessageContent.getFromUid());
		irpMessageContent.setContent(_irpMessageContent.getContent());
		irpMessageContent.setIsdel(IrpMessageContent.NORMALSTATUS);
		irpMessageContent.setCrtime(new Date());
		if(_irpMessageContent.getCruserid()!=null){
			irpMessageContent.setCruserid(_irpMessageContent.getCruserid());
		}else{
			irpMessageContent.setCruserid(LoginUtil.getLoginUserId());
		}
		if(_irpMessageContent.getMessagetype()!=null){
			irpMessageContent.setMessagetype(_irpMessageContent.getMessagetype());
		}else{
			irpMessageContent.setMessagetype(0);
		}
		try {
				this.irpMessageContentDAO.insertSelective(irpMessageContent);
				_messageStatus=1;
				//判断隐私设置消息是否推送到邮箱
				boolean result = onLineMessageForwardMail(_irpMessageContent.getFromUid(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
				if(result){
					forwardMail(_irpMessageContent.getFromUid(),_irpMessageContent.getContent());
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _messageStatus;
	}
	@Override
	public int addMessageContent(IrpMessageContent _irpMessageContent,Long _cruserid) {
		// TODO Auto-generated method stub
		int _messageStatus = 0;
		IrpMessageContent  irpMessageContent = new IrpMessageContent();
		irpMessageContent.setMessageid(TableIdUtil.getNextId(IrpMessageContent.TABLE_NAME));
		irpMessageContent.setFromUid(_irpMessageContent.getFromUid());
		irpMessageContent.setContent(_irpMessageContent.getContent());
		irpMessageContent.setIsdel(IrpMessageContent.NORMALSTATUS);
		irpMessageContent.setCrtime(new Date());
		irpMessageContent.setCruserid(_cruserid);
		
		try {
				this.irpMessageContentDAO.insertSelective(irpMessageContent);
				_messageStatus=1;
				//判断隐私设置消息是否推送到邮箱
				boolean result = onLineMessageForwardMail(_irpMessageContent.getFromUid(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
				if(result){
					forwardMail(_irpMessageContent.getFromUid(),_irpMessageContent.getContent());
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _messageStatus;
	}
	@Override
	public int addMessageContent(Long _cruserid,String _messageContent,Long _fromuid) {
		// TODO Auto-generated method stub
		int _messageStatus = 0;
		IrpMessageContent  irpMessageContent = new IrpMessageContent();
		irpMessageContent.setMessageid(TableIdUtil.getNextId(IrpMessageContent.TABLE_NAME));
		irpMessageContent.setFromUid(_fromuid);
		irpMessageContent.setContent(_messageContent);
		irpMessageContent.setIsdel(IrpMessageContent.NORMALSTATUS);
		irpMessageContent.setCrtime(new Date());
		irpMessageContent.setCruserid(_cruserid);
		
		try {
			this.irpMessageContentDAO.insertSelective(irpMessageContent);
			_messageStatus=1;
			//判断隐私设置消息是否推送到邮箱
			boolean result = onLineMessageForwardMail(_fromuid,IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
			if(result){
				forwardMail(_fromuid,_messageContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _messageStatus;
	}
	
	/**
	 * 判断是否推送到其他位置
	 * @param _toUserId
	 * @param forwardType
	 * @return
	 */
	public boolean onLineMessageForwardMail(Long _toUserId,String forwardType){
		IrpUserPrivacyExample example = new IrpUserPrivacyExample();
		example.createCriteria().andUseridEqualTo(_toUserId)
								.andPrivacytypeEqualTo(forwardType);
		try {
			List<IrpUserPrivacy> userPrivacyList = irpUserPrivacyDAO.selectByExample(example);
			if(userPrivacyList!=null && userPrivacyList.size()>0){
				if(userPrivacyList.get(0).getPrivacyvalue()==1){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将消息发送至邮箱
	 */
	public void forwardMail(Long userId,String content){
		try {
			IrpUser toUser = irpUserDAO.selectByPrimaryKey(userId);
			if(toUser.getEmail()!=null && !"".equals(toUser.getEmail())){
				MailSenderInfo mailInfo = new MailSenderInfo();
				
				List<String> toAddress = mailInfo.getToAddress();
				toAddress.add(toUser.getEmail());//收件人地址

				// 内容
				mailInfo.setContent(content);

				// 发送
				SimpleMailSender sms = new SimpleMailSender();
				sms.sendHtmlMail(mailInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<IrpMessageContent> findMessageByUser(Integer _isdel,
			Long _cruserid,Long _loginid,Long _crisdel,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMessageContent> list = null;
		list =this.irpMessageContentDAO.selectDetailMessage(_cruserid, _isdel, _loginid,_crisdel,pageUtil);
		return list;
	}
	@Override
	public List<IrpMessageContent> findMessageByUserId(Integer _isdel,
			Long _loginid,PageUtil pageUtil,Integer _messagetype) {
		// TODO Auto-generated method stub
		List<IrpMessageContent> list = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		 criteria.andIsdelEqualTo(_isdel);
		 criteria.andFromUidEqualTo(_loginid);
		 if(_messagetype!=null){
			 criteria.andMessagetypeEqualTo(_messagetype);
		 }else{
			 criteria.andMessagetypeNotEqualTo(1);
		 }
		 example.setOrderByClause("crtime desc");
		list =this.irpMessageContentDAO.selectDetailMessage( example,pageUtil);
		return list;
	}
	@Override
	public int findMessageByUserCount(Integer _isdel,Long _cruserid,Long _loginid,Long _crisdel){
		int _nCount = 0;
		_nCount = this.irpMessageContentDAO.selectDetailMessageCount(_cruserid, _isdel, _loginid,_crisdel);
		return _nCount;
	}
	@Override
	public int findMessageByUserIdCount(Integer _isdel,Long _loginid,Integer _messagetype){
		int _nCount = 0;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		 criteria.andIsdelEqualTo(_isdel);
		 criteria.andFromUidEqualTo(_loginid);
		 if(_messagetype!=null){
			 criteria.andMessagetypeEqualTo(_messagetype);
		 }else{
			 criteria.andMessagetypeNotEqualTo(1);
		 }
		_nCount = this.irpMessageContentDAO.selectDetailMessageCount(example);
		return _nCount;
	}
	
	@Override
	public List<Long> findGroupUserid(Long _fromUid,Integer _isdel,Long _crisdel,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<Long> list = null;
		list = this.irpMessageContentDAO.selectGroupByUserid(_fromUid,_isdel,_crisdel,pageUtil);
		
		return list;
	}
	@Override
	public int findGroupUseridCount(Long _fromUid,Integer _isdel,Long _crisdel){
		int nCount = 0;
	  nCount=this.irpMessageContentDAO.selectGroupByUseridCount(_fromUid,_isdel,_crisdel);
		return nCount;
	}
	@Override
	public IrpMessageContent irpMessageContent(Long _userid,Long _fromuid) {
		// TODO Auto-generated method stub
		IrpMessageContent irpMessageContent = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		 criteria.andCruseridEqualTo(_userid);
		 criteria.andFromUidEqualTo(_fromuid);
		 example.setOrderByClause("crtime desc");
		try {
		irpMessageContent = this.irpMessageContentDAO.selectByExample(example).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpMessageContent;
	}
	@Override
	public IrpMessageContent findSdIrpMessageContent(Long _userid) {
		IrpMessageContent irpMessageContent = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andFromUidEqualTo(_userid).andIsdelEqualTo(0);
		example.setOrderByClause("crtime desc");
		try {
			if(irpMessageContentDAO.selectByExample(example).size()>0){
				irpMessageContent = this.irpMessageContentDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return irpMessageContent;
	}
	@Override
	public IrpMessageContent findNewIrpMessageContent(Long _userid,int _messageType) {
		IrpMessageContent irpMessageContent = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andFromUidEqualTo(_userid).andIsdelEqualTo(0).andMessagetypeEqualTo(_messageType);
		example.setOrderByClause("crtime desc");
		try {
			if(irpMessageContentDAO.selectByExample(example).size()>0){
				irpMessageContent = this.irpMessageContentDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return irpMessageContent;
	}
	@Override
	public List<IrpMessageContent> findSdIrpMessageContentById(Long _userid) {
		List<IrpMessageContent> irpMessageContent = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andFromUidEqualTo(_userid);
		example.setOrderByClause("crtime desc");
		try {
			irpMessageContent = this.irpMessageContentDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpMessageContent;
	}
	@Override
	public IrpMessageContent findFcIrpMessageContent(Long _userid) {
		IrpMessageContent irpMessageContent = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCruseridEqualTo(_userid);
		example.setOrderByClause("crtime desc");
		try {
			if(irpMessageContentDAO.selectByExample(example).size()>0){
				irpMessageContent = this.irpMessageContentDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpMessageContent;
	}
	@Override
	public int deleteMessageInfo(Long _messageid,Long _crisdelid,Integer _isdel) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMessageContentExample example = new IrpMessageContentExample();
		example.createCriteria().andMessageidEqualTo(_messageid);
		IrpMessageContent  record = new IrpMessageContent();
		record.setIsdel(_isdel);
		record.setCrisdel(_crisdelid);
		try {
			nStatus = this.irpMessageContentDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nStatus;
	}
	@Override
	public int deleteMessage(Long _fromUid,Long _crisdelid,Integer _isdel) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		example.or(criteria.andFromUidEqualTo(_fromUid));
		example.or(criteria.andCrisdelEqualTo(_crisdelid));
		IrpMessageContent  record = new IrpMessageContent();
		record.setIsdel(_isdel);
		record.setCrisdel(_crisdelid);
		try {
			nStatus = this.irpMessageContentDAO.updateByExampleSelective(record, example);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nStatus;
	}
	@Override
	public IrpMessageContent getUserbyMessageid(Long _messageid) {
		// TODO Auto-generated method stub
		IrpMessageContent   irpMessageContent = null; 
		try {
			irpMessageContent =	this.irpMessageContentDAO.selectByPrimaryKey(_messageid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMessageContent;
	}
	@Override
	public IrpMessageContent getUserbyFromUid(Long _fromUid) {
		// TODO Auto-generated method stub
		IrpMessageContent   irpMessageContent = null; 
		try {
			IrpMessageContentExample example = new IrpMessageContentExample();
			example.createCriteria().andFromUidEqualTo(_fromUid);
			irpMessageContent =	this.irpMessageContentDAO.selectByExample(example).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMessageContent;
	}
	@Override
	public List getUserByFromUidOrCruserid(Long _fromUid, Long _cruserid) {
		// TODO Auto-generated method stub
		List frCrlist = null;
		frCrlist = this.irpMessageContentDAO.selectMessageidInCridFrid(_cruserid, _fromUid);
		return frCrlist;
	}
	@Override
	public List getUserMessageidByLogin(Long _loginid) {
		// TODO Auto-generated method stub
		List loglist = null;
		loglist = this.irpMessageContentDAO.selectMessageidAll(_loginid);
		return loglist;
	}
	@Override
	public List findSendOutMessage(Integer _isdel,Integer _isdelSend, Long _cruserid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List list = null;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCruseridEqualTo(_cruserid);
		criteria.andIsdelNotEqualTo(_isdel);
		criteria.andIsdelNotEqualTo(_isdelSend);
		example.setOrderByClause("crtime desc");
		try {
			list = this.irpMessageContentDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	@Override
	public int findSendOutMessageCount(Integer _isdel, Integer _isdelSend,
			Long _cruserid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCruseridEqualTo(_cruserid);
		criteria.andIsdelNotEqualTo(_isdel);
		criteria.andIsdelNotEqualTo(_isdelSend);
		try {
			num =	this.irpMessageContentDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	@Override
	public int selectUnReadMessage(long _fromUid, int _browsestatus) {
		int _readStatus = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromUid", _fromUid);
		map.put("browsestatus", _browsestatus);
		try {
			_readStatus = this.irpMessageContentDAO.selectUnReadMessage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int selectUnReadMsg(long _fromUid,int isDel,int _browsestatus,int _messagetype) {
		int _readStatus = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromUid", _fromUid);
		map.put("browsestatus", _browsestatus);
		map.put("messagetype", _messagetype);
		map.put("crisdel",isDel);
		try {
			_readStatus = this.irpMessageContentDAO.selectUnReadMsg(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int changeMessageReadStatus(long _fromUid,Integer _nowStatus,Integer _fromStatus,long _cruserid) {
		// TODO Auto-generated method stub
		int _readStatus = 0;
		
		IrpMessageContent record = new IrpMessageContent();
		record.setBrowsestatus(_fromStatus);
		IrpMessageContentExample example = new IrpMessageContentExample();
		Criteria criteria = example.createCriteria();
	    criteria.andFromUidEqualTo(_fromUid);
	    if(_cruserid!=IrpMessageContent.NORMALSTATUS){
	    	criteria.andCruseridEqualTo(_cruserid);	
	    }
		criteria.andBrowsestatusEqualTo(_nowStatus);
		
		try {
			_readStatus = this.irpMessageContentDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int chanageMessageStatusByMsgId(long _cruserid,Long _chatMsid) {
		// TODO Auto-generated method stub
		int _readStatus = 0;
		try {
			IrpMessageContent record = new IrpMessageContent();
			IrpMessageContent irpMessageContent = irpMessageContentDAO.selectByPrimaryKey(_chatMsid);
			IrpMessageContentExample example = new IrpMessageContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCruseridEqualTo(_cruserid);	
			if(irpMessageContent.getBrowsestatus()==0){
				irpMessageContent.setBrowsestatus(2);
				_readStatus = irpMessageContentDAO.updateByPrimaryKey(irpMessageContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int selectUnReadMessageDetail(long _fromUid, Integer _browsestatus,
			long _cruserid) {
		// TODO Auto-generated method stub
		int _readStatus = 0;
		_readStatus = this.irpMessageContentDAO.selectUnReadMessage(_fromUid, _browsestatus,_cruserid);
		return _readStatus;
	}
	
		@Override
		public int addMessageContentForLeave(IrpMessageContent _irpMessageContent) {
			// TODO Auto-generated method stub
			int _messageStatus = 0;
			IrpMessageContent  irpMessageContent = new IrpMessageContent();
			irpMessageContent.setMessageid(TableIdUtil.getNextId(IrpMessageContent.TABLE_NAME));
			irpMessageContent.setFromUid(_irpMessageContent.getFromUid());
			irpMessageContent.setContent(_irpMessageContent.getContent());
			irpMessageContent.setIsdel(IrpMessageContent.NORMALSTATUS);
			irpMessageContent.setCrtime(new Date());
			irpMessageContent.setCruserid(LoginUtil.getLoginUserId());
			irpMessageContent.setCrisdel(_irpMessageContent.getCrisdel());
			try {
					this.irpMessageContentDAO.insertSelective(irpMessageContent);
					_messageStatus=1;
					//判断隐私设置消息是否推送到邮箱
					boolean result = onLineMessageForwardMail(_irpMessageContent.getFromUid(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
					if(result){
						forwardMail(_irpMessageContent.getFromUid(),_irpMessageContent.getContent());
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return _messageStatus;
	
		
	}
		@Override
		public int deleteMessageByLeaveapplyId(long parseLong) {
			int msg=0;
			IrpMessageContentExample example=new  IrpMessageContentExample();
			example.createCriteria().andCrisdelEqualTo(parseLong);
			try {
				msg=this.irpMessageContentDAO.deleteByExample(example);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return msg;
		}

		@Override
		public List<IrpMessageContent> findMessageByUserId(Integer _isdel,
				Long _loginid,PageUtil pageUtil,Integer _messagetype,Integer browseStatus) {
			// TODO Auto-generated method stub
			List<IrpMessageContent> list = null;
			IrpMessageContentExample example = new IrpMessageContentExample();
			Criteria criteria = example.createCriteria();
			 criteria.andIsdelEqualTo(_isdel);
			 criteria.andFromUidEqualTo(_loginid);
			 criteria.andBrowsestatusEqualTo(browseStatus);
			 if(_messagetype!=null){
				 criteria.andMessagetypeEqualTo(_messagetype);
			 }else{
				 criteria.andMessagetypeNotEqualTo(1);
			 }
			 example.setOrderByClause("crtime desc");
			list =this.irpMessageContentDAO.selectDetailMessage( example,pageUtil);
			return list;
		}
		@Override
		public int findMessageByUserIdCount(Integer _isdel,Long _loginid,Integer _messagetype,Integer browseStatus){
			int _nCount = 0;
			IrpMessageContentExample example = new IrpMessageContentExample();
			Criteria criteria = example.createCriteria();
			 criteria.andIsdelEqualTo(_isdel);
			 criteria.andFromUidEqualTo(_loginid);
			 criteria.andBrowsestatusEqualTo(browseStatus);
			 if(_messagetype!=null){
				 criteria.andMessagetypeEqualTo(_messagetype);
			 }else{
				 criteria.andMessagetypeNotEqualTo(1);
			 }
			_nCount = this.irpMessageContentDAO.selectDetailMessageCount(example);
			return _nCount;
		}
}
