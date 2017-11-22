package com.tfs.irp.messagecontent.service;

import java.util.List;

import org.springframework.web.context.WebApplicationContext;

import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.util.PageUtil;

public interface IrpMessageContentService {

	/**
	 * 发送私信（增加一条私信）
	 * 
	 * @param irpMessageContent
	 * @return
	 */
	public int addMessageContent(IrpMessageContent _irpMessageContent);
    /**
     * 发送私信（增加一条私信）
     * @param _irpMessageContent
     * @param _webcontext
     * @return
     */
	public int addMessageContent(IrpMessageContent _irpMessageContent,Long _cruserid);
	public int addMessageContent(Long _cruserid,String _messageContent,Long _fromuid);
	/**
	 * 根据用户ID求出所有的所有没删除的私信
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public List<IrpMessageContent> findMessageByUser(Integer _isdel,Long _cruserid,Long _loginid,Long _crisdel,PageUtil pageUtil);
	/**
	 * 根据用户ID求出所有的所有没删除的私信(数量)
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public int findMessageByUserCount(Integer _isdel,Long _cruserid,Long _loginid,Long _crisdel);
	/**
	 * 分组查询我的私信的用户
	 * @return
	 */
	public List<Long> findGroupUserid(Long _fromUid,Integer _isdel,Long _crisdel,PageUtil pageUtil);
	/**
	 * 分组查询我的私信的用户(获得长度)
	 * @return
	 */
	public int findGroupUseridCount(Long _fromUid,Integer _isdel,Long _crisdel);
	/**
	 * 根据用户的ID求出某个用户最新的一条私信
	 * @param _userid
	 * @return
	 */
	public IrpMessageContent irpMessageContent(Long _userid,Long _fromuid);
	/**
	 * 根据用户的ID求出收到最新的一条私信
	 * @param _userid
	 * @return
	 */
	public IrpMessageContent findSdIrpMessageContent(Long _userid);
	//手机端
	public IrpMessageContent findNewIrpMessageContent(Long _userid,int _messageType);
	/**
	 * 根据用户的ID查看收到的所有私信
	 * @param _userid
	 * @return
	 */
	public List<IrpMessageContent> findSdIrpMessageContentById(Long _userid);
	/**
	 * 根据用户的ID求出发出最新的一条私信
	 * @param _userid
	 * @return
	 */
	public IrpMessageContent findFcIrpMessageContent(Long _userid);
	/**
	 * 删除某一条详细信息的私信
	 * @param _messageid
	 * @return
	 */
	public int deleteMessageInfo(Long _messageid,Long _crisdelid,Integer _isdel);
	/**
	 * 删除一组私信对话
	 * @param _crisdelid
	 * @return
	 */
	public int deleteMessage(Long _fromUid,Long _crisdelid,Integer _isdel);
	/**
	 * 根据私信id求出用户这条私信的对象
	 * @param _messageid
	 * @return
	 */
	public IrpMessageContent   getUserbyMessageid(Long _messageid);
	/**
	 * 根据私信的用户ID求出这条私信的对象
	 * @return
	 */
	public IrpMessageContent   getUserbyFromUid(Long _fromUid);
	/**
	 * 得到一个会话所有的id(删除会话用);
	 * @param _fromUid
	 * @param _cruserid
	 * @return
	 */
	public List getUserByFromUidOrCruserid(Long _fromUid,Long _cruserid);
	/**
	 * 得到清空数据的id
	 * @param _messageid
	 * @return
	 */
	public List getUserMessageidByLogin(Long _loginid);
	/**
	 * 获得发出的私信信息
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public List findSendOutMessage(Integer _isdel,Integer _isdelSend,Long _cruserid,PageUtil pageUtil);
	/**
	 * 获得发出的私信信息(个数)
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public int findSendOutMessageCount(Integer _isdel,Integer _isdelSend,Long _cruserid);
	  /**
	   * 获得登录用户还没有查看的私信
	   * @param _fromUid
	   * @param _browsestatus
	   * @return
	   */
    public int selectUnReadMessage(long _fromUid, int _browsestatus);
    public int selectUnReadMsg(long _fromUid,int isDel,int _browsestatus,int _messagetype);
    /**
     * 获得登录用户还没有查看的私信(会话时)
     * @param _fromUid
     * @param _browsestatus
     * @param _cruserid
     * @return
     */
    public int selectUnReadMessageDetail(long _fromUid,Integer _browsestatus,long _cruserid);
    /**
     * 改变私信未读状态
     * @param _fromUid
     * @param _browserstatus
     * @return
     */
    public int changeMessageReadStatus(long _fromUid,Integer _nowStatus,Integer _fromStatus,long _cruserid);
	public int addMessageContentForLeave(IrpMessageContent mc);
	public int deleteMessageByLeaveapplyId(long parseLong);
	/**
     * 改变单条私信未读状态
     * @param _fromUid
     * @param _browserstatus
     * @return
     */
	public int chanageMessageStatusByMsgId(long _fromUid,Long _chatMsid);
	
	/**
	 * 根据登录用户ID求出所有的所有没删除的私信(数量)
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public int findMessageByUserIdCount(Integer _isdel,Long _loginid,Integer _messagetype);
	/**
	 * 根据用户ID求出所有的所有没删除的私信
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public List<IrpMessageContent> findMessageByUserId(Integer _isdel,Long _loginid,PageUtil pageUtil,Integer _messagetype);
	/**
	 * 根据用户ID求出所有的所有未读状态的私信
	 * @param _isdel
	 * @param _cruserid
	 * @return
	 */
	public List<IrpMessageContent> findMessageByUserId(Integer _isdel,Long _loginid,PageUtil pageUtil,Integer _messagetype,Integer browseStatus);
	public int findMessageByUserIdCount(Integer _isdel,Long _loginid,Integer _messagetype,Integer browseStatus);
}
