package com.tfs.irp.uservaluelink.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.util.PageUtil;

public interface IrpUserValueLinkService {

	/**
	 * 增加用户贡献统计
	 * @param _irpUserValueLink
	 * @return
	 */
	int addIrpUserValueLink(IrpUserValueLink _irpUserValueLink);
	/**
	 * 判断某个用户+某个积分类型 是否存在
	 * ture:已存在（执行更新操作）
	 * false:不存在(执行新增操作)
	 * @param _irpUserValueLink
	 * @return
	 */
	boolean irpUserValueLinkByUseridValueid(long _userid,String _valueckey);
	/**
	 * 根据用户id和配置id更新分数和经验
     * @param _irpUserValueLink
	 * @return
	 */
	int updateIrpUserValueLinkByUseridValueid(IrpUserValueLink _irpUserValueLink);
	/**
	 * 根据用户id和配置id查出现有的分数
	 * @param _irpUserValueLink
	 * @return
	 */
	long scoreByUseridValueid(long _userid,String _valuekey);
	/**
	 * 根据用户id和配置id查出现有的经验
	 * @param _irpUserValueLink
	 * @return
	 */
	long experienceByUseridValueid(long _userid,String _valuekey);
    /**
     * 根据指定日期获得用户贡献列表
     * @param _startTime
     * @param _endtime
     * @param _userid
     * @return
     */
	List<IrpUserValueLink> getIrpUserValueLinkListByTime(Date _starttime,Date _endtime,long _userid,PageUtil pageUtil,String _oOrderby);
	/**
	 * 根据指定日期获得用户休假表
	 * @param _startTime
	 * @param _endtime
	 * @param _userid
	 * @return
	 */
	List<IrpLeaveapply> getLeaveDaysListByTime(Date _starttime,Date _endtime,long _userid,PageUtil pageUtil,String _oOrderby);
	/**
	 * 根据指定日期获得用户贡献列表条数
	 * @param _starttime
	 * @param _endtime
	 * @param _userid
	 * @return
	 */
    int getIrpUserValueLinkListByTimeCount(Date _starttime,Date _endtime,long _userid);
    /**
     * 根据指定日期获得用户休假时间
     * @param _starttime
     * @param _endtime
     * @param _userid
     * @return
     */
    int getLeaveDaysByTimeCount(Date _starttime,Date _endtime,long _userid);
	/**
	 * 计算用户的积分
	 * @param _userid
	 * @return
	 * @throws SQLException
	 */
    int sumScoreByUserid(long _userid);
    int getScoreByUserid(long _userid);

	/**
	 * 计算用户的经验
	 * @param _userid
	 * @return
	 * @throws SQLException
	 */
    int sumExperienceByUserid(long _userid);
    /**
     * 查询某段时间的所有积分记录
     * @param startTime
     * @param endTime
     * @return
     */
    public List<IrpUserValueLink> findDataByExpert(Date startTime, Date endTime,Long userId);
}
