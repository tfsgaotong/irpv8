package com.tfs.irp.docrecommend.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.util.PageUtil;

public interface IrpDocrecommendService {
	/**
	 * 根据条件查询count
	 */
	public int countRecommend(Long _docid ,Long _replayUserId );
	/**
	 * 手机端
	 * 根据条件查询count
	 */
	public int countRecommend_app(Long _docid ,Long _replayUserId ,String token);
	/**
	 * 添加回复
	 * @param _docid
	 * @param recommend
	 * @return
	 */
	public int addDocRrecommend(Long _docid,Long _replyUserid,String recommend) ;
	/**
	 * 手机端
	 * 添加回复
	 * @param _docid
	 * @param recommend
	 * @return
	 */
	public int addDocRrecommend_app(Long _docid,Long _replyUserid,String recommend,String token) ;
	/**
	 * 查询某个文档的所有评论
	 * @param _docid
	 * @return
	 */
	public  List<IrpDocrecommend > findDocReommend(Long _docid);
	/**
	 * 查询自己某个文档的所有评论
	 * @param _docid
	 * @param isdel
	 * @return
	 */
	List<IrpDocrecommend> findDocReommend(Long _docid,Long _replyUserid,Integer isde,PageUtil pageUtil);
	/**
	 * 删除某个文档回复
	 * @param _docid
	 * @param _recommendid
	 * @return
	 */
	int deleteDocReommend(Long _docid,Long _recommendid);
	/**
	 * （给高彤写的接口）查询某个人所有知识的总回复数量
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int selectCountByExpert(Long userId,Date startTime,Date endTime);
	/**
	 * 查询某个动态
	 */
	IrpDocrecommend findRecommendByPrimaryKey(Long recommendid);
	/**查询所有动态条数
	 * @return
	 */
	public long getAllCount();
}
