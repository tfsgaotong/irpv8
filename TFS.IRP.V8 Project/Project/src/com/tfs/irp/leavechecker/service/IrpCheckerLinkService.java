package com.tfs.irp.leavechecker.service;


import java.util.List;

import com.tfs.irp.leavechecker.entity.IrpCheckerLink;




public interface IrpCheckerLinkService {
	
	/**
	 * 增加审核人联系
	 * @param irpCheckerLink
	 * @return
	 */
	int addIrpCheckerLink(IrpCheckerLink irpCheckerLink);
	/**
	 * 根据表单id获得所有的审核意见
	 */
	List<IrpCheckerLink> getAllCheckersByleaveapplyid(Long leaveapplyid);
	
	/**
	 * 上一级能看到这一级别的审核意见
	 */
	IrpCheckerLink  getIrpCheckerLinkByNext(Long leaveapplyid,Long userid);
	/**
	 * 根据leaveapplyid查询对应的下一级审核人id
	 */
	List<Long> getAllCheckUserID(Long leaveapplyid);
	/**
	 * 是否进入审核流程
	 */
	boolean isBeginCheck(Long leaveapplyid);
	int deleteLinkByleaveId(long parseLong);
}
