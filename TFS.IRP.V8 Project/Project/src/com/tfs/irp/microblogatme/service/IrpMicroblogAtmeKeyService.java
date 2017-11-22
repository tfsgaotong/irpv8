package com.tfs.irp.microblogatme.service;

import java.util.List;

import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.util.PageUtil;

public interface IrpMicroblogAtmeKeyService {
	
	/**
	 * 增加@用户
	 * @param irpMicroblogAtmeKey
	 * @return
	 */
	public int addMicroblogAtmeKey(IrpMicroblogAtmeKey _irpMicroblogAtmeKey);
	/**
	 * 查看某位用户的@
	 * @param userid
	 * @return
	 */
	public List<IrpMicroblogAtmeKey> IrpMicroblogAtmeKey(Long  userid,PageUtil pageUtil);
	/**
	 * 查看某位用户的最新一条@
	 * @param userid
	 * @return
	 */
	public IrpMicroblogAtmeKey getIrpMicroblogAtmeKey(Long  userid);
	/**
	 * 查看某位用户的@个数
	 * @param userid
	 * @return
	 */
	public int IrpMicroblogAtmeKeyCount(Long  userid);
	/**
	 * 根据@的主键删除相应的数据
	 * @param _atmeid
	 * @return
	 */
	public int deleteMicroblogAtmeKeyByAtmeId(Long _atmeid);
	/**
	 * 清空登录用户@数据
	 * @param _userid
	 * @return
	 */
	public int deleteMicroblogAtmeKeyByUserid(Long _userid);
	/**
	   * 获得登录用户还没有查看的@我的消息
	   * @param _fromUid
	   * @param _browsestatus
	   * @return
	   */
	public int selectUnReadAtme(long _userid, int _browsestatus);
	/**
     * 改变单条@我未读状态
     * @param _fromUid
     * @param _browserstatus
     * @return
     */
	public int chanageAtmeStatusByMsgId(long _userid,Long _chatMsid);
}
