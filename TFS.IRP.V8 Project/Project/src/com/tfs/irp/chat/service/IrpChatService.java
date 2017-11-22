package com.tfs.irp.chat.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.chat.entity.IrpChat;
import com.tfs.irp.util.PageUtil;

public interface IrpChatService {
	
	/**
	 * 增加新消息
	 * @param _irpchat
	 * @return
	 */
	public boolean addChat(IrpChat _irpchat);
	/**
	 * 获取一个对话的信息
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _isboolrecord
	 * @param _fuserid
	 * @param _senduserid
	 * @param _orderby
	 * @param _pageutil
	 * @return
	 */
	public List<IrpChat> getAllChatContentById(Long _chatobj,Integer _chatstatus,Long _fuserid,Long _senduserid,String _orderbystr,PageUtil _pageutil);
	
	/**
	 * 获取组内消息集合
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _fuserid
	 * @param _senduserid
	 * @param _orderbystr
	 * @param _pageutil
	 * @return
	 */
	public List<IrpChat> getAllChatContentByGroupId(Long _chatobj,Integer _chatstatus,Long _fuserid,String _orderbystr,PageUtil _pageutil);
	
	/**
	 * 获取组内消息集合个数
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _fuserid
	 * @param _senduserid
	 * @return
	 */
	public int getAllChatContentByGroupIdNum(Long _chatobj,Integer _chatstatus,Long _fuserid);
	/**
	 * 获取一个对话的信息数量
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _isboolrecord
	 * @param _fuserid
	 * @param _senduserid
	 * @return
	 */
	public int getAllChatContentByIdNum(Long _chatobj,Integer _chatstatus,Long _fuserid,Long _senduserid);
	/**
	 * 标记为已读
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _fuserid
	 * @param _senduserid
	 * @return
	 */
	public boolean changeAlreadyReady(Long _chatobj,Integer _chatstatus,Long _fuserid,Long _senduserid,Integer _upval);
	/**
	 * message detection this user unread count
	 * 检测当前用户未读的消息数量
	 * @param _chatobj
	 * @param _chatstatus
	 * @param _fuserid
	 * @param _unread
	 * @return
	 */
	public int unreadNumByLoginUser(Long _chatobj,Integer _chatstatus,Long _fuserid,Integer _unread);
	 /**
	  * 分组查询多少用户的未读消息
	  * @param _chatobj
	  * @param _chatstatus
	  * @param _fuserid
	  * @param _unread
	  * @return
	  */
	 int findUnreadCount(Integer _chatstatus,Long _fuserid, Integer _unread);
	 
	 /**
	  * 获得未读消息的集合
	  * @param _chatobj
	  * @param _chatstatus
	  * @param _fuserid
	  * @param _unread
	  * @return
	  */
	 List<Map> findUnreadUserList(Integer _chatstatus,Long _fuserid, Integer _unread);
	 /**
	  * 根据id获取相应的记录
	  * @param _chatid
	  * @return
	  */
	 IrpChat irpChatByCId(Long _chatid);

}
