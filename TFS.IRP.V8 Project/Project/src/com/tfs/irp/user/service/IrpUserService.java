package com.tfs.irp.user.service;

import java.util.Date;
import java.util.List;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.entity.IrpUserValue;
import com.tfs.irp.util.PageUtil;

public interface IrpUserService {
	/**
	 * 注册用户
	 * @param _irpUser
	 * @return
	 */
	int regUser(IrpUser _irpUser);
	
	/**
	 * 用户登录
	 * @param _irpUser
	 * @param _Session
	 * @return
	 */
	int login(IrpUser _irpUser);
	/**
	 * 二维码登录
	 * @param _irpUser
	 * @return
	 */
	int LoginEW(IrpUser _irpUser);
	
	/**
	 * 用户注销
	 * @param m_oSession
	 */
	void logout();
	
	/**
	 * 根据用户Id获得用户对象
	 * @param _nUserId
	 * @return
	 */
	IrpUser findUserByUserId(Long _nUserId);
	
	/**
	 * 修改用户
	 * @param _irpUser
	 */
	Long userEdit(IrpUser _irpUser);

	/**
	 * 根据用户装填获得用户总数
	 * @param _nStatus
	 * @return
	 */
	int findUsersCountByStatus(int _nStatus);
	
	/**
	 * 根据用户状态分页获得用户集合
	 * @param pageUtil
	 * @param _nStatus
	 * @param _sOrderBy
	 * @return
	 */
	List<IrpUser> findUsersOfPageByStatus(PageUtil pageUtil, int _nStatus, String _sOrderBy);
	
	/**
	 * 根据用户ID集合修改用户状态
	 * @param _sUserIds
	 * @param _nStatus
	 * @return
	 */
	int updateStatusByUserids(String _sUserIds, int _nStatus);
	
	/**
	 * 根据用户ID集合修改用户密码
	 * @param _sUserIds
	 * @param _sPassWord
	 * @return
	 */
	int updatePassWordByUserids(String _sUserIds, String _sPassWord);
	
	/**
	 * 验证用户名是否存在
	 * @param _sUserName
	 * @param _nUserId
	 * @return
	 */
	boolean checkUserName(String _sUserName, Long _nUserId);
	
	/**
	 * 根据用户状态和检索条件分页获得用户集合
	 * @param pageUtil
	 * @param _nStatus
	 * @param _sOrderBy
	 * @param _sSearchType
	 * @param _sSearchWord
	 * @return
	 */
	List<IrpUser> findUsersOfPageByStatus(PageUtil pageUtil, int _nStatus, String _sOrderBy, String _sSearchWord, String _sSearchType);
	
	/**
	 * 根据用户状态和检索条件获得用户总数
	 * @param _nStatus
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int findUsersCountByStatus(int _nStatus, String _sSearchWord, String _sSearchType);
	/**
	 * 根据对象获取用户对象集合
	 * @param _username
	 * @return
	 */
	List<IrpUser> findUserByUsername(String _username);
	/**
	 * 根据用户的 用户名，来获取用户集
	 * @param _name
	 * @return
	 */
	String findUsernameByNicknameTruenameUsername(String _name);
	
	/**
	 * 添加用户的个人组织信息
	 * @param irpUser
	 */
	void addUserGroup(IrpUser irpUser);
	/**
	 * 张银珠  根据example里的用户显示
	 */
	List<IrpUser> findUserByExample(IrpUserExample example);
	/**
	 * 张银珠  根据example里的用户带分页功能的
	 * @param example
	 * @param pageUtil
	 * @return
	 */
	List<IrpUser> findUserByExample(IrpUserExample example,PageUtil pageUtil);
	/**
	 * 根据用户id更新用户的总分数和总经验
	 * @param _userid
	 * @return
	 */
	int updateSumScoreExperience(IrpUser _irpUser);
	/**
	 * 根据用户id更新用户的剩余年假
	 * @param _userid
	 * @return
	 */
	int updateLeaveYearDays(IrpUser _irpUser);
	/**
	 * 一键更新所有的剩余年假
	 * @param _userid
	 * @return
	 */
	int updateAllLeaveYearDays(IrpUser _irpUser);
	/**
	 * 根据example查询用户的数量
	 * @return
	 */
	int countUserByExample(IrpUserExample example);
	/**
	 * 找到系统所有的用户
	 * @return
	 */
	List<IrpUser> findAllIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	//添加时间参数   付燕妮2017-5-27
	List<IrpUserValue> findAllIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType,Date _starttime,Date _endtime);
	/**
	 * 找到系统所有的用户的年假
	 * @return
	 */
	List<IrpUser> findAllIrpUserHoloday(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 找到系统所有特殊的用户用户
	 * @param pageUtil
	 * @param _oOrderby
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	List<IrpUser> findSpecialIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType);
	/**
	 * 系统中共有多少个用户
	 * @return
	 */
	long findAllUserCountByStatus(int _nStatus);
	/**
	 * 根据用户id修改用户的分数和经验
	 * @param _userid
	 * @return
	 */
	int updateUserSumScoreEx(Long _userid,long _sumscore,long _sumexperience);
	/**
	 * 根据用户id把用户的积分和经验清零
	 * @return
	 */
	int emptyUserScoreExperInfo(long _userid,long _sumscore,long _sumexperience);
	/**
	 * 查询处的数据长度
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int searchUserValueLinkSize(String _sSearchWord,String _sSearchType);
	//添加时间参数  付燕妮 2017-5-27
	int searchUserValueLinkSize(String _sSearchWord,String _sSearchType,Date _starttime,Date _endtime);
	/**
	 * 查询年假的数量
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int searchYearDays(String _sSearchWord,String _sSearchType);
	/**
	 * 查询特殊用户的处的数据长度
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int searchSpecialUserValueLinkSize(String _sSearchWord,String _sSearchType);
	/**
	 * 获得分享排行（积分由高到低）
	 * @return
	 */
	List shareRank(PageUtil pageUtil );
	/**
	 * 获得赞同某个问题的用户的集合
	 * @param _status
	 * @param _docid
	 * @param _statusmost
	 * @param _mosttype
	 * @return
	 */
	public List findUsernamebyquestionid(Integer _status,Long _docid,Integer _statusmost,Integer _mosttype);

	/****
	 * 根据邮箱和用户名找到用户
	 * @param email
	 * @return List<IrpUser>
	 */
	List<IrpUser> finds(String _email, String _username);
	/**
	 * 获得用户的积分
	 * @param _userid
	 * @param _type
	 * @return
	 */
	public long findSumScoreByUserId(Long _userid);
	/**
	 * 改变用户状态
	 * @param _userid
	 * @param _specialtype
	 * @return
	 */
	public int changeUserSpecialType(Long _userid,Integer _specialtype);
	/**
	 * 根据用户输入的字母模糊查询存在的用户
	 * @param sname
	 * @return
	 */
	public List<IrpUser> likeUser(String sname);
	
	
	public List<Long> findAllSystemUserIds();
	
	/**
	 * 根据用户的生母查询用户
	 * @param sname
	 * @param searchWord
	 * @return
	 */
	public List<IrpUser> findUserbySM(String sm,String searchWord,PageUtil pageUtil);
	/**
	 * 根据用户的生母查询用户 数量
	 * @param sname
	 * @return
	 */
	public int findUserbySMcount(String sm,String searchWord);
	/**
	 * 通过传入的id获得显示在页面的名字
	 * @param _userid
	 * @return
	 */
	public String findShowNameByUserid(Long _userid);
	/**
	 * 通过传入的用户名获得显示在页面的名字
	 * @param _username
	 * @return
	 */
	public String findShowNameByUsername(String _username);
	/**
	 * 查询系统中的总分数和总经验
	 * @return
	 */
	public IrpUser findSumScoreAndSumExperence();
	
	/**
	 * 根据QQ登录信息获得用户
	 * @param accessToken
	 * @param qqUserId
	 * @return
	 */
	public IrpUser findIrpUserByQQLogin(String accessToken, String qqUserId);
	
	/**
	 * 根据微知登录信息获得用户
	 * @param accessToken
	 * @param qqUserId
	 * @return
	 */
	public IrpUser findIrpUserByWeiboLogin(String accessToken, String weiboUserId);
	
	/**
	 * 根据用户名登录系统，不需要密码验证
	 * @param _sUserName
	 * @return
	 */
	public int loginByName(String _sUserName);
	
	/**
	 * 更新用户的QQ互联绑定信息
	 * @param irpuser
	 * @return
	 */
	public int userBindingEdit(IrpUser irpuser);
	
	/**
	 * 跟据条件获得专家数量
	 * @param _nCategoryId
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int findExpertCount(Long _nCategoryId, String _sSearchWord,
			String _sSearchType);
	
	/**
	 * 跟据条件获得专家列表
	 * @param _nCategoryId
	 * @param pageUtil
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	List<IrpUser> findExpertList(Long _nCategoryId, PageUtil pageUtil,
			String _sSearchWord, String _sSearchType);

	/**
	 * 根据日期类型获得用户排行
	 * @param _nDateType
	 * @return
	 */
	List<IrpUser> userRankByDate(int _nDateType);

	/**
	 * 获得系统中所有注册用户集合
	 * @return
	 */
	List<IrpUser> findAllRegUsers();
	/**
	 * 通过组织和角色查询用户
	 */
	List<IrpUser> findUsersByGroupIdAndRoleId(String _userid,Long _groupId,Long _roleId,PageUtil page,String _sSearchWord, String _sSearchType);
	/**
	 * 通过组织和角色查询用户数量
	 */
	int countByByGroupIdAndRoleId(String _userid,Long _groupId,Long _roleId,String _sSearchWord, String _sSearchType);
    /**
     * 有选择修改用户
     * @param valueOf
     * @param holiday
     * @return
     */
	int updateUserWithChoose(Long valueOf, Integer holiday);
	/**
	 * 手机端
	 * 根据token查询用户
	 * @param token
	 * @return
	 */
	List<IrpUser> getByToken(String token);
	/**
	 * 手机端查看用户角色
	 * @param user
	 * @return
	 * @author   npz
	 * @date 2017年8月9日
	 */
	IrpUser getRolebyUser(IrpUser user);
	/**
	 * 根据token和用户名获取用户对象
	 * @param _token
	 * @param _uname
	 * @return
	 */
	IrpUser boolUserTokenAndUser(String _token);
	List<IrpUser> findHotExpertList(Long _nCategoryId,PageUtil _pageUtil);
	
	List<IrpUser> findRecExpertList(PageUtil _pageUtil);
	void setExpertRecommend(Long nUserid,String experttype);

	/**
	 * 按日期查新同时不包括一些用户
	 * @param i
	 * @param listArraylist
	 * @param mustFocus
	 * @return
	 */
	List<IrpUser> userRankByDateNotInList(int i, List<Long> listArraylist,
			long mustFocus);

	/**按分类id获取用户
	 * @param categoryId
	 * @param expertId
	 * @return
	 */
	List<IrpUser> getUserBycategoryid(Long categoryId, Long expertId);
}
