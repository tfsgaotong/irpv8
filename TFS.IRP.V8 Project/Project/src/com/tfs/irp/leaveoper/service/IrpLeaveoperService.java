package com.tfs.irp.leaveoper.service;

import java.util.List;







import com.tfs.irp.leaveoper.entity.IrpLeaveoper;
import com.tfs.irp.user.entity.IrpUser;




public interface IrpLeaveoperService {

	Long addOper(Long applyid, String checker);

	/**
	 * 根据申请ID获得审核人的集合
	 * @param applyid
	 * @return
	 */
	List<IrpLeaveoper> getListByapplyId(Long applyid);
	/**
	 * 通过当前登录用户的id查询用户需处理的单号
	 * @param 
	 * @return
	 */

	/**
	 * 通过当前登录用户的id查询用户需处理的单号
	 * @param 
	 * @return
	 */
	List<Long> selLeaveapplyidByUserid(Integer status);
	List<Long> selLeaveapplyidByUserid(Integer status,IrpUser irpuser);

	void updateOperStatus(long applyleaveid,long loginUserId, Integer status);

	List<Long> selLeaveapplyidByUseridList(List<Integer> list2);
	List<Long> selLeaveapplyidByUseridList(List<Integer> list2,IrpUser irpuser);
	List<IrpLeaveoper> getOperByapplyId(Long userid, long leaveapplyid);
	
	/**
	 * 通过申请单id查找审核人id
	 * @param 
	 * @return
	 */
	String selLeaveapplyidByLeaveapplyid(Long applyleaveid);
	/**
	 * 通过申请单id查询审核人的id集合
	 */
	List<Long> getCheckuserids(Long applyleaveid);

	int deleteoper(long leaveapplyid);
	

	/**
	 * 根据申请id删除相关数据
	 */
	int deleteLeaveoper(Long applyleaveid);
	/**
	 *更新审核人
	 */
	int upcheckuser(IrpLeaveoper oper);
	/**
	 * 根据条件查询一个实体
	 */
	IrpLeaveoper selIrpLeaveoper(Long applyleaveid);
}
