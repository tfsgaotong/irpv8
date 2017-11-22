package com.tfs.irp.microblogcollection.service;

import java.sql.SQLException;
import java.util.List;

public interface IrpMicroblogCollectionKeyService {
	/**
	 * 微知增加收藏
	 * @param _nMicroblogid
	 * @return
	 */
	public int addTfsMicroblogCollectionKey(long _nMicroblogid,long _nUserid);
	/**
	 * 删除指定收藏
	 * @param _nMicroblogid
	 * @return
	 */
	public int deleteTfsMicroblogCollectionKey(long _nMicroblogid);
    /**
	 * 通过当前登录的id取出所收藏微知的id
	 * @return
	 */
	List selectMicroblogidOfUserid(Long _userid)throws SQLException;

}
