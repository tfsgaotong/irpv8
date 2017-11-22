package com.tfs.irp.selectkey.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.selectkey.entity.IrpSelectKey;
import com.tfs.irp.util.PageUtil;

public interface IrpSelectKeyService {
/**
 * 检索用户检索记录按照检索次数倒叙
 * @return
 */
	public List<String> userLogs() ;
	 
	/**
	 * 增加检索记录 
	 * @param _sKey 
	 */
	public void save(String _sKey);
	public void saveserachword(String _sKey);
	/**
	 * 增加检索记录
	 * @param _userId
	 * @param _sKey
	 * @param _ip
	 */
	public void save(Long _userId,String _sKey,String _ip);
	
	/**
	 * 添加检索记录 
	 * @param irpSelectKey
	 */
	public void save(IrpSelectKey irpSelectKey);
	/**
	 * 根据主键删除检索记录
	 * @param _sid
	 * @return
	 */
	public int delete(Long _sid);
	 /**
	 * 查询用户检索次数倒叙的对象
	 * @param _userId 用户id 
	 * @return
	 */
	 IrpSelectKey  maxData(Long _userId );
	 /**
		 * 查询检索记录 
		 * @param _userId 用户id
		 * @param _sKey 检索字段
		 * @return
		 */
	List<IrpSelectKey> allData(PageUtil pageUtil, Long _userId, String _sKey);
	/**
	 *查询某范围内的用户检索记录词
	 * @param userId 用户ID
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 */
	List<String> selectByUserIdOrderbyCountDescBetweenTime(Long userId,Date starttime,Date endtime );
}
