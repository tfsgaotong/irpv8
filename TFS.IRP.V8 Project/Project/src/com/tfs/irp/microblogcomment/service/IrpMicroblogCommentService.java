package com.tfs.irp.microblogcomment.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentExample;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentView;
import com.tfs.irp.util.PageUtil;


public interface IrpMicroblogCommentService {
	
	/**
	 * 增加评论的id
	 * @param tfsMicroblogComment
	 * @return
	 */
	public int addMicroBlogComment(long  _replyuserid,long _microblogid,String _content,long _replycommentid);
	public int addBlogComment(long  _replyuserid,long _microblogid,String _content,long _replycommentid,long commentuserid);
	/**
	 * 微知评论回复列表
	 * @return
	 */
	public List<String> findMicroBlogComment(Long _microblogid,Integer _isdel,PageUtil pageUtil);
	/**
	 * 没有被删除的评论个数
	 */
	public int findMicroBlogCommentCount(Long _microblogid,Integer _isdel);
	/**
	 * 根据评论的id删除指定的评论
	 * @param _commentid
	 * @return
	 */
	public int updateMicroBlogCommentIsDel(Long _commentid);
	/**
	 * 删除指定微知的id下的所有评论
	 * @return
	 */
	public int updateMicroblogCommentMicroblog(Long _microblogid);
	/**
	 * 增加回复“回复”评论的id
	 * @param _replyCommentid
	 * @param _content
	 * @return
	 */
	public int addMicroBlogReplyCommentReply(Long _replyCommentid,String _content);
	/**
	 * 查看某位用户收到的评论列表(不包括自己给自己的评论)
	 * @param _userid
	 * @return
	 */
	public List<IrpMicroblogComment> findMicroblogCommentOfUserid(long _userid,Integer _isdel,PageUtil pageUtil);
	public int findMicroblogCommentOfUseridCount(long _userid,Integer _isdel);
	
	/**
	 * 查看某位用户发出的评论列表(包括自己给自己的评论)
	 * @param _userid
	 * @return
	 */
	public List<IrpMicroblogComment> findMicroblogCommentOfSendUserid(long _userid,Integer _isdel,PageUtil pageUtil);
	public int findMicroblogCommentOfSendUseridCount(long _userid,Integer _isdel);
	/**
	 * 查看某个用户没有被删的微知
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	public List findMicrCommentByUserid(long _userid,Integer _isdel);
	/**
	 * 查看某个评论的对象
	 * @param _commentid
	 * @return
	 */
    public IrpMicroblogComment irpMicroblogComment(long _commentid);
	/**
	 * 获得微知评论字数限制
	 * @return
	 */
    public String findMicroblogCommentNumberWord(String _cKey);
    /**
     * 获得某个用户发出最新的一条评论
     * @param _userid
     * @param _crisdel
     * @return
     */
    public IrpMicroblogComment findNewWestCommentByUserid(long _userid,Integer _isdel);
    /**
     * 获得某个用户收到最新的一条评论
     * @param _userid
     * @param _crisdel
     * @return
     */
    public IrpMicroblogComment findSdNewWestCommentByUserid(long _userid,Integer _isdel);
    /**
     * 查看某条微知下有多少条未删除的评论
     * @param _userid
     * @param _isdel
     * @return
     */
    public int getCountCommentByMicroblog(long _microblogid,Integer _isdel);
    /**
	 * 微知评论列表
	 * @return
	 */
	List<String> selectMicroBlogCommentlist(Long _microBlogid,Integer _isdel,PageUtil pageUtil);
	List<IrpMicroblogComment> selectCommentlistByWbid(Long _microBlogid,Integer _isdel,PageUtil pageUtil);

	/**
	 * 根据微知的id获得有多少条评论
	 * @param _microblogid
	 * @return
	 */
	Long selectByMicroBlogComment(Long _microblogid);
	/**
	 * 分页
	 * @return
	 */
	public List<IrpMicroblogComment> selectByExample(IrpMicroblogCommentExample example,PageUtil pageUtil);
    
	/**
	   * 获得登录用户还没有查看的评论
	   * @param _fromUid
	   * @param _browsestatus
	   * @return
	   */
	public int selectUnReadComment(long _replyuserid, int _browsestatus, int _isdel);
	/**
     * 改变单条评论未读状态
     * @param _fromUid
     * @param _browserstatus
     * @return
     */
	public int chanageCommentStatusByMsgId(long _replyuserid,Long _chatMsid);
}
