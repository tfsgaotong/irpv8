package com.tfs.irp.user.dao;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.entity.IrpUserValue;
import com.tfs.irp.util.PageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IrpUserDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int countByExample(IrpUserExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int deleteByExample(IrpUserExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int deleteByPrimaryKey(Long userid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    void insert(IrpUser record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    void insertSelective(IrpUser record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    List<IrpUser> selectByExample(IrpUserExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    IrpUser selectByPrimaryKey(Long userid) throws SQLException;
    IrpUserValue selectByPrimaryKey1(Long userid) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int updateByExampleSelective(IrpUser record, IrpUserExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int updateByExample(IrpUser record, IrpUserExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int updateByPrimaryKeySelective(IrpUser record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRPV8_V1.IRP_USER
     *
     * @ibatorgenerated Sat Sep 10 15:23:13 CST 2016
     */
    int updateByPrimaryKey(IrpUser record) throws SQLException;
    List<IrpUser> selectByExample(IrpUserExample example, PageUtil pageUtil) throws SQLException;
	List<Map<String, Object>> selectByExample(PageUtil pageUtil,Map<String, Object> _mParam) throws SQLException;
	List<IrpUserValue> selectByExample(String _sql) throws SQLException;
	/**
     * 根据用户ID集合更新用户状�?
     * @param param
     * @return 更新的行�?
     * @throws SQLException
     */
	int updateStatusByUserids(Map<String, Object> _mParam) throws SQLException;
	
	/**
     * 根据用户ID集合更新用户密码
     * @param param
     * @return 更新的行�?
     * @throws SQLException
     */
	int updatePassWordByUserids(Map<String, Object> _mParam) throws SQLException;
    /**
     * 根据用户的昵称和真实姓名取出相应的集�?
     * @return
     * @throws SQLException
     */
	List findUserByNickNameTrueName(String _info,Long _userid,PageUtil pageUtil)throws SQLException;
	/**
	 * 根据用户名，返回用户的用户名
	 * @param _name
	 * @return 返回用户�?
	 * @throws SQLException
	 */
	String findUsernameByNickNameTrueNameUsername(String _name)throws SQLException;
	/**
	 * 重载返回一共多少个用户
	 * @param _info
	 * @param _userid
	 * @return
	 * @throws SQLException
	 */
	public List findUserByNickNameTrueName(String _info, Long _userid)
			throws SQLException ;
	/**
	 * 获得赞同某个问题的用户的集合
	 * @param _status
	 * @param _docid
	 * @param _statusmost
	 * @param _mosttype
	 * @return
	 */
	public List findUsernamebyquestionid(Integer _status,Long _docid,Integer _statusmost,Integer _mosttype);
	/**
	 * 获得所有正常用户的集合
	 * @param _status
	 * @return
	 */
	public List findAllUserid(Integer _status);
	/**
	 * 获得所有正常用户的集合(定时任务)
	 * @param _status
	 * @return
	 */
	public List findAllUseridTask(Integer _status);
	/**
	 * 获得特殊用户
	 * @param _status
	 * @param _specialtype
	 * @return
	 */
	public List<Long> findAllUseridSpecialType(Integer _status,Integer _specialtype);
	/**
	 * 获得某个人的名次(按照积分来判定名次的大小)
	 * @reutrn
	 */
	public int findRankingByUserid(Long _userid);
	
	/**
	 * 通过传入的用户名获得显示在页面的名字
	 * @param _username
	 * @return
	 */
	public String findShowNameByUsername(String _username);
	/**
	 * 通过传入的id获得显示在页面的名字
	 * @param _userid
	 * @return
	 */
	public String findShowNameByUserid(Long _userid);
	public IrpUser findSumScoreAndSumExperence() ;
	
	/**
	 * 跟据条件获得专家列表
	 * @param _mParam
	 * 	roleId:角色ID
	 * 	status:用户状态
	 * 	classifyid:专家分类ID
	 * 	username:检索用户名
	 * 	truename:检索真实姓名
	 * @param pageUtil
	 * @return
	 */
	List<IrpUser> findExpertList(Map<String, Object> _mParam, PageUtil pageUtil);
	
	/**
	 * 跟据条件获得专家数量
	 * @param _mParam
	 * 	roleId:角色ID
	 * 	status:用户状态
	 * 	classifyid:专家分类ID
	 * 	username:检索用户名
	 * 	truename:检索真实姓名
	 *  orderStr:排序字段
	 * @return
	 */
	int findExpertCount(Map<String, Object> _mParam);
	
	/**
	 * 根据条件查询，返回带有用户觉得ID的集合
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	public List<IrpUser> selectByExampleWithRoleId(IrpUserExample example) throws SQLException;
	
	/**
	 * 检索用户
	 * @param map
	 * @return
	 */
	public List findUserByNickNameTrueNameUserNameDate(Map map, PageUtil pageUtil);
	/**
	 * 根据组织角色查询用户
	 */
	public List<IrpUser> findUserByGroupIdAndRoleId(Map map, PageUtil pageUtil)throws SQLException;
	
	/**
	 * 根据组织角色查询用户数量
	 */
	public int findCountByGroupIdAndRoleId(Map map)throws SQLException;
	/**
	 * 修改剩余年假
	 */
	 public int updateYearDays(IrpUser record)throws SQLException;
	 /**
		 * 一键修改剩余年假天数
		 */
	 public int updateAllYearDays(IrpUser record)throws SQLException;
	 /**
		 * 一键修改剩余年假天数
		 */
	 List<Long> selectUserIdsByExample(IrpUserExample example)throws SQLException;
	 
	 List<IrpUser> findUsersByRoleId(Map<String, Object> _mParam,PageUtil _pageUtil) throws SQLException;
	 List<IrpUser> findRecExpertByRoleId(Map<String, Object> _mParam,PageUtil _pageUtil) throws SQLException;

	List<IrpUser> getUserBycategoryid(String sql);
}