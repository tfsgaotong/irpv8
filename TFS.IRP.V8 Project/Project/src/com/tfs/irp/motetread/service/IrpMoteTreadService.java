package com.tfs.irp.motetread.service;

public interface IrpMoteTreadService {
	/**
	 * 添加顶或者踩
	 * @param _status
	 * @return
	 */
	public int addMoteThread(Long _docid,Integer _status,Integer _mostType);
	public int addMoteThreadMobile(Long _docid,Integer _status,Integer _mostType);
	/**
	 * 根据知识或问答的id获得顶或踩的数量
	 * @param _id
	 * @return
	 */
	public int IrpMoteThreadCountByQuestionid(Long _id,Integer _status,Integer _mostType);
	/**
	 * 查看用户是否赞同或反对了   0：用户已经赞同过此信息了  1：用户没有
	 * @param _id
	 * @param _status
	 * @param _mostType
	 * @return
	 */
	public int IrpMoteThreadCountByUser(Long _id,Integer _status,Integer _mostType,Long _userid);
	/**
	 * 删除指定状态的记录
	 * @param _id
	 * @param _status
	 * @param _mostType
	 * @param _userid
	 * @return
	 */
	public int deleteIrpMoteThreadCountByUser(Long _id,Integer _status,Integer _mostType,Long _userid);
	
}
