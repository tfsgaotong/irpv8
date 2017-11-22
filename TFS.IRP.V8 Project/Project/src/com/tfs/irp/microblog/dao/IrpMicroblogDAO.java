package com.tfs.irp.microblog.dao;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogExample;
import com.tfs.irp.util.PageUtil;

public interface IrpMicroblogDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int countByExample(IrpMicroblogExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int deleteByExample(IrpMicroblogExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int deleteByPrimaryKey(Long microblogid) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	void insert(IrpMicroblog record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	void insertSelective(IrpMicroblog record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	List<IrpMicroblog> selectByExampleWithBLOBs(IrpMicroblogExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	List<IrpMicroblog> selectByExampleWithoutBLOBs(IrpMicroblogExample example)
			throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	IrpMicroblog selectByPrimaryKey(Long microblogid) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByExampleSelective(IrpMicroblog record,
			IrpMicroblogExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByExampleWithBLOBs(IrpMicroblog record,
			IrpMicroblogExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByExampleWithoutBLOBs(IrpMicroblog record,
			IrpMicroblogExample example) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByPrimaryKeySelective(IrpMicroblog record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByPrimaryKeyWithBLOBs(IrpMicroblog record) throws SQLException;
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table irp_microblog
	 * @ibatorgenerated  Sat Nov 15 11:31:18 CST 2014
	 */
	int updateByPrimaryKeyWithoutBLOBs(IrpMicroblog record) throws SQLException;




	List<IrpMicroblog> selectByExample(IrpMicroblogExample example,PageUtil pageUtil)
			throws Exception;
	/**
	 * 获得当前登录用户的微知列表
	 * @param muserid
	 * @param isdelid
	 * @param fuserid
	 * @return
	 * @throws SQLException
	 */
	List findMicroblogOfFocus(long isdelid,long fuserid,PageUtil pageUtil,long _loginid)throws Exception;
	/**
	 * 获得当前用户收藏的微知列表
	 * @param isdelid
	 * @param fuserid
	 * @param pageUtil
	 * @param _loginid
	 * @return
	 * @throws SQLException
	 */
	List findMicroblogOfFocusCollect(long isdelid,PageUtil pageUtil,long _loginid)throws Exception;
	
	
	
	
	
	/**
	 * 追加微知并显示列表
	 * @param isdelid
	 * @param fuserid
	 * @param _microblogid
	 * @return
	 * @throws SQLException
	 */
	List findMicroblogOfFocus(long isdelid,long fuserid,long _microblogid)throws Exception;
	/**
	 * 显示微知卡片
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	List findMicroblogOfUseridInCard(long _userid,Integer _isdel);
	List findMicroblogOfUserid(long _userid,Integer _isdel);
	/**
	 * 获取微知的长度
	 * @param isdelid
	 * @param fuserid
	 * @return
	 */
	int findMicroblogOfFocusCount ( long isdelid, long fuserid) throws SQLException ;
	
	/**
	 * 获取登录的人的信息 
	 * @param _docstatus  删除知识的状态
	 * @param _isdel   删除微知的状态
	 * @param _userid  当前操作用户
	 * @return
	 * @throws SQLException
	 */
	List findLoginUserInfo(long _docstatus,long _isdel,long _userid)throws SQLException;
	/**
	 * 获取收藏微知的长度
	 * @param isdelid
	 * @param _loginid
	 * @return
	 * @throws SQLException
	 */
	 int findMicroblogOfFocusCollectCount(long isdelid,long _loginid) throws SQLException;
	 /**
	  * 默认推荐关注(按照粉丝数)  正常状态的
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 List findDefaultRecommend(long _userid)throws SQLException;
	 /**
	  * 默认推荐关注(按照微知数)
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 List findDefaultRecommendMicrNum(long _userid)throws SQLException;
	 /**
	  * 默认推荐关注(按照用户创建时间)
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 List findDefaultRecommendUserCrtime(long _userid)throws SQLException;
	
	 /**
	  * 查看某个用户有多少条微知
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 Integer searchMicrCountOfUserid(long _userid)throws SQLException;
	 /**
	  * 查看某个用户有多少个关注
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 Integer searchFocusCountOfUserid(long _userid)throws SQLException;
	 /**
	  * 查看某个用户有多少个粉丝
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 Integer searchFusCountOfUserid(long _userid)throws SQLException;
	 
	 /***
	  * 所有的主题
	  * @return
	  */
	 List getAllTopicValue(Integer _isdel,Integer _blogtype);
	 /**
	  * 获得没有被删除的微知的个数
	  * @param isdelid
	  * @return
	  * @throws SQLException
	  */
	 int findMicroblogOfAllCount(long isdelid) throws SQLException;
	 /**
	  * 所遇的微知信息
	  * @param isdelid
	  * @param pageUtil
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 List findMicroblogOfAll( long isdelid,PageUtil pageUtil,long _loginid) throws Exception;
	 /**
	  * 遭举报的微知的个数
	  * @param isdelid
	  * @return
	  * @throws SQLException
	  */
	 int findMicroblogOfInformCount(long isdelid,Integer informtype,Integer informstatus,String _infromkey)throws SQLException;
	 /**
	  * 获得所有被举报的微知
	  * @param isdelid
	  * @param pageUtil
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 List findMicroblogOfInform( long isdelid,Integer informtype,Integer informstatus,PageUtil pageUtil,String _infromkey) throws Exception;
	 /**
	  * 重载微知收藏个数
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 public int findMicroblogOfFocusCollectCount(long _loginid) throws SQLException;
	 /**
	  * 重载微知收藏列表
	  * @param pageUtil
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 public List findMicroblogOfFocusCollect(PageUtil pageUtil,long _loginid) throws Exception;
	 /**
	  * 组内可见的微知
	  * @param _userid
	  * @param _groupid
	  * @return
	  * @throws Exception
	  */
	 public boolean findMicroblogOfGroupId(Long _userid,Long _groupid) throws Exception;
	//分页
	 public List<IrpMicroblog> selectByExampleWithBLOBsbypage(IrpMicroblogExample example,int start,int end);
	 /**
	  * 搜索微知
	  * @param _searchWord
	  * @param _isDel
	  * @param pageutil
	  * @param _loginid
	  * @return
	  * @throws Exception
	  * @author   npz
	  * @date 2017年9月12日
	  */
	 public List searchMicroblog(String _searchWord,Integer _isDel,PageUtil pageutil,long _loginid) throws Exception;
	 public int searchMicroblogcount(String _searchWord,Integer _isDel) throws Exception;









}