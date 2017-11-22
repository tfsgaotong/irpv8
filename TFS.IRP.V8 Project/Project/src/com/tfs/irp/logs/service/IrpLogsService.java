package com.tfs.irp.logs.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.tableid.entity.IrpTableid;
import com.tfs.irp.util.PageUtil;

public interface IrpLogsService {

	/**
	 * 根据日志类型日志 查出所有数据
	 * @param session
	 * @param pageUtil
	 * @param _oOrderby
	 * @param _logtype
	 * @return
	 */
	List<IrpLogs> findIrpLogsOfPage(PageUtil pageUtil,
			String _oOrderby, long _logtype);

	/**
	 * 获得检索数据
	 * @param session
	 * @param pageUtil
	 * @param _oOrderby
	 * @param _irpLogs
	 * @param _starttime
	 * @param _endtime
	 * @return
	 */
	List<IrpLogs> findIrpLogsOfPage(PageUtil pageUtil,
			String _oOrderby,IrpLogs _irpLogs,Date _starttime,Date _endtime);
	List<IrpLogs> findIrpLogsOfPageSize(PageUtil pageUtil,
			String _oOrderby,IrpLogs _irpLogs,Date _starttime,Date _endtime);
	/**
	 * 日志表里有多少条数据
	 * @return
	 */
	int findLogsCountByStatus();
	/**
	 * 生成日志记录
	 * @param _irpLogs
	 */
	void logsUserBehaviour(IrpLogs _irpLogs);
	/**
	 * 根据日志id查出对应的数据
	 * @param _logId
	 * @return
	 */
	IrpLogs findIrpLogsByLogid(long _logId);
	/**
	 * 获得全局设置里的操作类型
	 * @return
	 */
	List<IrpOpertype> getIrpOpertype_opertype();
	/**
	 * 根据日志类型获得日志个数
	 * @param _logtype
	 * @return
	 */
	int irpLogSizeOfLogtype(long _logtype);

	/**
	 * 根据id去 对象document
	 * @param _documentid
	 * @return
	 */
	IrpDocument irpDocument(long _documentid);
	/**
	 * 根据channeldocid  获得对象
	 * @param _channeldocid
	 * @return
	 */
	IrpChnlDocLink irpChnlDocLink(long _channeldocid);
	/**
	 * 取得tableid对象
	 * @param _tablename
	 * @return
	 */
	IrpTableid irpTableid(String _tablename);
	/**
	 * 根据操作类型转操作名称
	 * @param _opertype
	 * @return
	 */
	String getOpername(String _opertype);
	/**
	 * 返回所有的tableid的数据的集合
	 * @return
	 */
	List<IrpTableid> findAllIrpTableid();
	/**
	 * 返回日志对象类型
	 */
	List<IrpTableid> findAllTableObjType();
	/**
	 * 在规定的时间找出相应的日志信息
	 * @param _startTime
	 * @param _endTime
	 * @return
	 */
	List<IrpLogs> findIrpLogsByDate(Date _startTime,Date _endTime);
	/**
	 * 在规定的时间找出相应的日志信息
	 * @param _startTime
	 * @param _endTime
	 * @return
	 */
	int findIrpLogsByDateCount(Date _startTime,Date _endTime);
	/**
	 * 在规定的时间某个用户出现的次数
	 * @param _startTime
	 * @param _endTime
	 * @return
	 */
	int findCountOfUserByDate(String _uname,Date _startTime,Date _endTime);
	/**
	 * 获得正确登陆人的所有信息
	 * @return
	 */
	List<IrpLogs> getAllDateByLogin(Date startTime,Date endTime);
	/**
	 * 根据类型和时间段获取数量  张银珠
	 * @param startTime
	 * @param endTime
	 * @param _logOpType
	 * @return
	 */
	int findCountByExpert( Date startTime,Date endTime, String _logOpType);
	/**
	 * 获取count
	 * @param logOpType
	 * @param _starttime
	 * @param _endtime
	 * @return
	 */
	int findIrpLogsOfPageSize(String logOpType, Date _starttime, Date _endtime);
	
	/**
	 * 查询文章操作
	 * @param _nDocId
	 * @return
	 */
	List<IrpLogs> findDocumentLogs(long _nDocId);

}
