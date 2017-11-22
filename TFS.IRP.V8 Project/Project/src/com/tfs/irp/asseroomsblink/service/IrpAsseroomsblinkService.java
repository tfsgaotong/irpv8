package com.tfs.irp.asseroomsblink.service;

import java.util.List;

import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroomsblink.entity.IrpAsseroomsblink;

public interface IrpAsseroomsblinkService {
	/**
	 * 添加会议室与设备之间联系
	 * @param asseroom
	 * @return
	 * @throws Exception
	 */
	public Long addSb(Long _asseroomid, String[] _asseroomsbids)throws Exception;
	/**
	 * 查询所有会议室关联
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomsblink> findAll()throws Exception;
	/**
	 * 按会议室id查询
	 * @param _asseroomid
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomsblink> findbyAsseroomid(Long _asseroomid)throws Exception;
	/**
	 * 按会议室设备id查询
	 * @param _asseroomid
	 * @return
	 * @throws Exception
	 */
	public List<IrpAsseroomsblink> findbyAsseroomsbid(Long _asseroomsbid)throws Exception;
	/**
	 * 根据设备删除
	 * @param _asseroomsbid
	 * @return
	 * @throws Exception
	 */
	public int deletebyAsseroomsbid (Long _asseroomsbid)throws Exception;
	/**
	 * 根据会议室删除
	 * @param _asseroomid
	 * @return
	 * @throws Exception
	 */
	public int deletebyAsseroomid (Long _asseroomid)throws Exception;
	/**
	 * 设备批量删除
	 * @param _asseroomsbid
	 * @return
	 * @throws Exception
	 */
	public int deletebyAsseroomsbidList (List<Long> _asseroomsbids)throws Exception;
	/**
	 * 会议室批量删除
	 * @param _asseroomid
	 * @return
	 * @throws Exception
	 */
	public int deletebyAsseroomidList (List<Long> _asseroomids)throws Exception;
}
