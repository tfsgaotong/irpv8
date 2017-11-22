package com.tfs.irp.leaveapply.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.web.IrpLeaveapplyInfo;
import com.tfs.irp.util.PageUtil;

public interface IrpLeaveapplyService {

	List<IrpLeaveapply> getAllList(Integer leave,PageUtil pageUtil, int status, Long userid);

	int findListNums(Integer leave,Integer status, Long userid);

	IrpLeaveapply getById(Long applyid);
	
	List<IrpLeaveapply> getAll(PageUtil pageUtil,Integer leave,Integer status,List<Long> list);
	
	int findListOverTimeNums(Integer leave,Integer status,List<Long> list);

	int findListQueryNums(String marking, Date starttimes, Date endtimes,
			String applystatus,  String applytypeid,Long userid, int emergency);

	List<IrpLeaveapply> getQueryList(PageUtil pageUtil, String marking,
			Date starttimes, Date endtimes, String applystatus,
			 String applytypeid,Long userid,int emergency);
	/**
	 * 修改请假加班的申请状态
	 **/
	int upStatus(IrpLeaveapply irpLeaveapply);
	
	List<IrpLeaveapply> getAll1(PageUtil pageUtil, Integer type,
			Integer status, List<Long> list);

	int searchUsername(String searchWord, String searchType);

	List<IrpLeaveapply> findAllQuery(PageUtil pageUtil, String _oOrderby,
			String searchWord, String searchType);

	void exportAllSignInfoToZip(List<IrpLeaveapplyInfo> irpLeaveapplyInfos, String path,
			String fileName);
	
	List<IrpLeaveapply> getQueryListForAdmin(String marking,
			Date starttimes, Date endtimes, String applystatus,
			 String applytypeid,int emergency);

	int findListQueryNumsFromExp(String marking, Date starttimes,
			Date endtimes, String applystatus, String applytypeid,
			String searchName, int emergency);

	List<IrpLeaveapply> getQueryListFromExp(PageUtil pageUtil, String marking,
			Date starttimes, Date endtimes, String applystatus,
			String applytypeid, String searchName, int emergency);
	
	/**
	 * 导出信息到zip文件
	 */
	public void exportAllWorkTimeToZip(List<IrpLeaveapplyInfo> list,String path,String fileName);
	/**
	 * 通过条件查询加班
	 */
	List<IrpLeaveapply> selectByExample(Date _starttime,Date _endtime,Long _applytypeid);
	/**
	 * 通过userid集合、开始时间、结束时间和加班类型查询加班人数
	 */
	int selectCountByList(List<Long> _userList,Date _starttime,Date _endtime,Long _applytypeid);
	/**
	 * 根据检索词查询加班数据总数
	 */
	int selectCountBySsearch(String _sSearchWord, String _sSearchType,Date _starttime,Date _endtime,Long _applytypeid,String applystatus);
	/**
	 * 根据检索条件分页查询数据
	 */
	List<IrpLeaveapply> selectInfoBySearchAndPage(PageUtil _pageUtil,String _sSearchWord, String _sSearchType,String _orderField,String _orderBy,Date _starttime,Date _endtime,Long _applytypeid,String applystatus);
	/**
	 *通过userid集合、开始时间、结束时间和加班类型查询加班总时间
	 *
	 */
	int selectTotaltimeByList(List<Long> _userList,Date _starttime,Date _endtime,Long _applytypeid);
	/**
	 * 通过id删除申请表单
	 */
	int deleteLeaveapply(Long _leaveapplyid);
	/**
	 * 根据实体更新数据
	 */
	int upLeaveapplyinfo(IrpLeaveapply _irpLeaveapply);

	int selectCountByListForLeave(List<Long> userids, Date starttimes,
			Date endtimes);

	int selectTotaltimeByListForLeave(List<Long> userids, Date starttimes,
			Date endtimes);
}
