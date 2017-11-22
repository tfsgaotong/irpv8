package com.tfs.irp.chat.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tfs.irp.chat.dao.IrpChatDAO;
import com.tfs.irp.chat.entity.IrpChat;
import com.tfs.irp.chat.entity.IrpChatExample;
import com.tfs.irp.chat.service.IrpChatService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpChatServiceImpl implements IrpChatService {
	
	private IrpChatDAO irpChatDAO;

	public IrpChatDAO getIrpChatDAO() {
		return irpChatDAO;
	}

	public void setIrpChatDAO(IrpChatDAO irpChatDAO) {
		this.irpChatDAO = irpChatDAO;
	}

	@Override
	public boolean addChat(IrpChat _irpchat) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			this.irpChatDAO.insertSelective(_irpchat);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<IrpChat> getAllChatContentById(Long _chatobj,
			Integer _chatstatus, Long _fuserid,
			Long _senduserid, String _orderbystr, PageUtil _pageutil) {
		// TODO Auto-generated method stub
		List<IrpChat> list = null;
		IrpChatExample example = new IrpChatExample();
		example.or(example.createCriteria().andReceiveruseridEqualTo(_fuserid)
				                           .andSenderuseridEqualTo(_senduserid)
				                           .andChatpointtypeEqualTo(_chatobj)
											.andChatstatusEqualTo(_chatstatus)
				
				);
		
		example.or(example.createCriteria().andSenderuseridEqualTo(_fuserid)
							.andReceiveruseridEqualTo(_senduserid)
				              .andChatpointtypeEqualTo(_chatobj)
								.andChatstatusEqualTo(_chatstatus)
				);
		
		
		example.setOrderByClause(_orderbystr);
		
		
		try {
			list = this.irpChatDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public int getAllChatContentByIdNum(Long _chatobj, Integer _chatstatus, Long _fuserid, Long _senduserid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpChatExample example = new IrpChatExample();
		example.or(example.createCriteria().andReceiveruseridEqualTo(_fuserid)
                .andSenderuseridEqualTo(_senduserid)
                .andChatpointtypeEqualTo(_chatobj)
					.andChatstatusEqualTo(_chatstatus)

					);
					
		example.or(example.createCriteria().andSenderuseridEqualTo(_fuserid)
			.andReceiveruseridEqualTo(_senduserid)
		   .andChatpointtypeEqualTo(_chatobj)
				.andChatstatusEqualTo(_chatstatus)
		);

		try {
			num = this.irpChatDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public boolean changeAlreadyReady(Long _chatobj, Integer _chatstatus,
			Long _fuserid, Long _senduserid,Integer _upval) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		IrpChat irpchat = new IrpChat();
		irpchat.setCharreadstatus(_upval);
		IrpChatExample example = new IrpChatExample();
		example.createCriteria().andChatpointtypeEqualTo(_chatobj)
								.andChatstatusEqualTo(_chatstatus)
								.andReceiveruseridEqualTo(_fuserid)
								.andSenderuseridEqualTo(_senduserid);
		
		try {
		  int msg =	this.irpChatDAO.updateByExampleSelective(irpchat, example);
		  if (msg==1) {
			  flag = true;
		  }	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}

	@Override
	public int unreadNumByLoginUser(Long _chatobj, Integer _chatstatus,
			Long _fuserid, Integer _unread) {
		// TODO Auto-generated method stub
		int count = 0;
		IrpChatExample example  = new IrpChatExample();
		example.createCriteria().andChatpointtypeEqualTo(_chatobj)
								.andChatstatusEqualTo(_chatstatus)
								.andReceiveruseridEqualTo(_fuserid)
								.andCharreadstatusEqualTo(_unread);
		
		try {
			this.irpChatDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int findUnreadCount(Integer _chatstatus,
			Long _fuserid, Integer _unread) {
		// TODO Auto-generated method stub
		int num = 0;
		List list = this.irpChatDAO.findUnreadList(_chatstatus, _fuserid, _unread);
		if (list!=null) {
			num = list.size();
		}
		return num;
	}

	

	@Override
	public List<Map> findUnreadUserList(Integer _chatstatus,
			Long _fuserid, Integer _unread) {
		// TODO Auto-generated method stub
		List<Map> list = new ArrayList();
		
		List lists = this.irpChatDAO.findUnreadList(_chatstatus, _fuserid, _unread);
		
		if (lists.size()>0) {
			for (int i = 0; i < lists.size(); i++) {
				
				Map map = (Map) lists.get(i);
				
				list.add(map);
				
				
			}
		}
		
		
		return list;
	}

	@Override
	public List<IrpChat> getAllChatContentByGroupId(Long _chatobj,
			Integer _chatstatus, Long _fuserid, 
			String _orderbystr, PageUtil _pageutil) {
		// TODO Auto-generated method stub
		List<IrpChat> list = null;
		
		IrpChatExample example = new IrpChatExample();
		
		example.createCriteria().andReceiveruseridEqualTo(_fuserid)
										.andChatpointtypeEqualTo(_chatobj)
										.andChatstatusEqualTo(_chatstatus);
			
		example.setOrderByClause(_orderbystr);
			
		try {
			list = this.irpChatDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public int getAllChatContentByGroupIdNum(Long _chatobj,
			Integer _chatstatus, Long _fuserid) {
		// TODO Auto-generated method stub
		int num = 0;
		
		IrpChatExample example = new IrpChatExample();
		
		example.createCriteria().andReceiveruseridEqualTo(_fuserid)
						        .andChatpointtypeEqualTo(_chatobj)
								.andChatstatusEqualTo(_chatstatus);
		
		try {
			num = this.irpChatDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public IrpChat irpChatByCId(Long _chatid) {
		// TODO Auto-generated method stub
		IrpChat irpchat = null;
		if (_chatid!=null) {
			try {
				irpchat = this.irpChatDAO.selectByPrimaryKey(_chatid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return irpchat;
	}

}
