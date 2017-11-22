package com.tfs.irp.microblogfocus.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocusExample;
import com.tfs.irp.util.PageUtil;

public interface IrpMicroblogFocusService {

	/**
	 * 查出登录用户所关注的人的id
	 * @return
	 */
	public List<IrpMicroblogFocus> findMicroblogFocusUserId(long _focususerid,PageUtil pageUtil);
	public int findMicroblogFocusUserIdCount(long _focususerid);
	public List<IrpMicroblogFocus> findMicroblogFocusUserId(long _focususerid);
	/**
	 * 查出与登录用户互相关注的人的id
	 * @return
	 */
	public List<IrpMicroblogFocus> findEachMicroblogFocusUserId(long _focususerid,PageUtil pageUtil);
	public int findEachMicroblogFocusUserIdCount(long _focususerid);
	
	/**
	 * 查看用户所有已关注的人
	 * @return
	 */
	public List<IrpMicroblogFocus> findMicroblogFocusUserIdById(long _focususerid,PageUtil pageUtil);
	public int findEachMicroblogFocusUserIdCountById(long _focususerid);
	/**
	 * 查出当前用户有多少个粉丝
	 * @param _userid
	 * @return
	 */
	public List<IrpMicroblogFocus> findMicroblogUserId(long _userid,PageUtil pageUtil);
	public List<IrpMicroblogFocus> findMicroblogUserId(long _userid);
	public int findMicroblogUserIdCount(long _userid);
	/**
	 * 通过被关注用户的id求出用户共有多少粉丝
	 * @return
	 */
	public int countMicroblogFocusFocusUserid(long _userid);
	/**
	 * 通过用户的id求出用户关注了多少人
	 * @return
	 */
	public int countMicroblogFocusUserid(long _focususerid);
	/**
	 * 删除我关注的用户
	 * @param _focususerid
	 * @return
	 */
	public int deleteMicroblogFocusUserid(long _focususerid,long _userid);
	/**
	 * 添加关注用户
	 * @param _focususerid
	 * @param _userid
	 * @return
	 */
	public int addMicroblogFocusUserid(long _focususerid,long _userid);
	/**
	 * 根据条件查询 用户
	 * @param _info
	 * @return
	 */
	public List findSearchUser(String _info,long _userid,PageUtil pageUtil);
	/**
	 * 根据条件查询 用户(新增检索条件)
	 * @param _info
	 * @return
	 */
	public List findSearchUserByParam(String _info,long _userid,PageUtil pageUtil,Map<String,String> paramMap);
	/**
	 *  根据用户id查看这个人是不是我关注的对象
	 * @param _userId
	 * @return
	 */
	 public List<IrpMicroblogFocus> findFocusByUserId(long _userId);
	
	 /**
	  * 总页数
	  * @param _info
	  * @param _userid
	  */
	 int findSearchUserNum(String _info,long _userid);
	 /**
	  * 总页数(新增检索条件)
	  * @param _info
	  * @param _userid
	  */
	 int findSearchUserNumByParam(String _info,long _userid,Map<String,String> paramMap);
	 /**
     * 查看当前登录用户所关注的用户id
     * @param _loginuserid
     * @return
     * @throws SQLException
     */
    List selectUseridByFocuserId(long _loginuserid);
    List selectUseridByLoginuserId(long _loginuserid);
    /**
     * 查看当前登录用户的粉丝
     * @param _loginuserid
     * @return
     * @throws SQLException
     */
    List selectFansByLoginuserId(long _loginuserid);
    /**
     * 获得登录用户所互相关注的人的id
     * @return
     * @throws SQLException
     */
    List selectUseridByFocususerId(long _loginuserid);
    /**
     * 分页显示
     * @return
     */
    List<IrpMicroblogFocus> selectByExample(IrpMicroblogFocusExample example,PageUtil pageUtil);
}
