package com.tfs.irp.microblogcomment.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentExample;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentView;
import com.tfs.irp.util.PageUtil;

public interface IrpMicroblogCommentDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int countByExample(IrpMicroblogCommentExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int deleteByExample(IrpMicroblogCommentExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int deleteByPrimaryKey(Long commentid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    void insert(IrpMicroblogComment record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    void insertSelective(IrpMicroblogComment record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    List<IrpMicroblogComment> selectByExample(IrpMicroblogCommentExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    IrpMicroblogComment selectByPrimaryKey(Long commentid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int updateByExampleSelective(IrpMicroblogComment record, IrpMicroblogCommentExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int updateByExample(IrpMicroblogComment record, IrpMicroblogCommentExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int updateByPrimaryKeySelective(IrpMicroblogComment record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG_COMMENT
     *
     * @ibatorgenerated Tue Apr 23 13:12:31 CST 2013
     */
    int updateByPrimaryKey(IrpMicroblogComment record) throws SQLException;
    /**
	 * 微知评论列表
	 * @return
	 */
	List<String> selectMicroBlogCommentlist(Long _microBlogid,Integer _isdel,PageUtil pageUtil)  throws SQLException;
	List<IrpMicroblogComment> selectCommentlistByWbid(Long _microBlogid,Integer _isdel,PageUtil pageUtil)  throws SQLException;

	/**
	 * 根据微知的id获得有多少条评论
	 * @param _microblogid
	 * @return
	 */
	Long selectByMicroBlogComment(Long _microblogid)throws SQLException;
	/**
	 * 分页
	 * @return
	 */
	public List<IrpMicroblogComment> selectByExample(IrpMicroblogCommentExample example,PageUtil pageUtil) throws SQLException;
	
	/**
	   * 获得登录用户还没有查看的评论
	   * @param _fromUid
	   * @param _browsestatus
	   * @return
	   */
	  public int selectUnReadComment(Map<String, Object> _mParam) throws SQLException;
}