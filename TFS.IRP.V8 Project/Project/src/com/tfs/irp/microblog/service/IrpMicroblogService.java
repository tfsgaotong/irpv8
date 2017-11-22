package com.tfs.irp.microblog.service;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpUserInfoView;
import com.tfs.irp.util.PageUtil;
public interface IrpMicroblogService {

	/**
	 * 增加微知信息
	 * @param session
	 * @param _sContent
	 * @param _irpMicroblog
	 * @return
	 */
	public int addMicroBlog(String _sContent,int _nMicrotype,IrpMicroblog _irpMicroblog,String _contentimg);
	/**
	 * 增加微知信息 mobile
	 * @param _sContent
	 * @param _nMicrotype
	 * @param _irpMicroblog
	 * @return
	 */
	public int addMicroblogByMobile(String _sContent,int _nMicrotype,IrpMicroblog _irpMicroblog);
	
	
	/**
	 * 增加转发的微知信息
	 * @return
	 */
	public int addMicroBlogTranSpond(IrpMicroblog _irpMicroblog);
	
	public int addMicroBlogTranSpondMobile(IrpMicroblog _irpMicroblog);
	/**
	 * 逻辑删除微知
	 * @return
	 */
	public int deleteMicroblogOfMicroblogid(long _microblogid);
	/**
	 * 根据微知的id更新评论的条数
	 * @param _microblogid
	 * @return
	 */
	public int updateMicroblogCommentCount(long _microblogid,long _commentcount);
	/**
	 * 通过微知id找到相对应的微知信息
	 * @param _microblogid
	 * @return
	 */
	public IrpMicroblog irpMicroblog(Long _microblogid);
	/**
	 * 通过微知id更新源文件转发的次数
	 * @return
	 */
	public int updateMicroblogTranspondByMicroblogid(long _microblogid);
	/**
	 * 获得用户最新的一条微知
	 * @param _userid
	 * @return
	 */
	public IrpMicroblog findFirstMicroblog(long _userid);
	/**
	 * 通过用户的ID求出用户共有多少条未删除的微知
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	public int coutnMicroblogOfUserid(long _userid,Integer _isdel);
	public int coutnMicroblogOfUserid(long _userid,Integer _isdel,Integer _blogtype);
	/**
	 * 通过id得到用户个人的微知列表
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	public List findMicroblogListByUserid(long _userid,Integer _isdel,PageUtil pageUtil);
	/**
	 * 通过id得到用户个人的微知列表
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	public List findMicroblogListByUserid(long _userid,Integer _isdel,PageUtil pageUtil,Integer _blogtype);
	/**
	 *  获取登录的人的信息 
	 * @param _docstatus 删除知识的状态
	 * @param _isdel 删除微知的状态
	 * @param _userid 当前操作用户
	 * @return
	 */
	public List findLoginUserPersonalInfo(long _docstatus, long _isdel, long _userid);
	
	/**
	 * 查找 config 表的value  通过 ckey
	 * @param _cKey
	 * @return
	 */
	public String findIrpConfigCvalue(String _cKey);
	/**
	 * 收藏列表
	 * @return
	 */
	public List findMicroblogOfFocusCollect(long isdelid, PageUtil pageUtil,long _loginid);
	/**
	 * 收藏列表（重载1）
	 * @return
	 */
	public List findMicroblogOfFocusCollect(PageUtil pageUtil,long _loginid);
	/**
	 * 收藏列表(长度)
	 * @return
	 */
	public int findMicroblogOfFocusCollectCount(long isdelid,long _loginid);
	/**
	 * 重载1收藏列表(长度)
	 * @return
	 */
	public int findMicroblogOfFocusCollectCount(long _loginid);
	 /**
	  * 默认推荐关注
	  * @param _userid
	  * @return
	  * @throws SQLException
	  */
	 List findDefaultRecommend(long _userid);	
	 /**
	  * 根据用户查看它有多少微知
	  * @param _userid
	  * @return
	  */
	 public Integer searchMicrCountOfUserid(long _userid);
	 /**
	  * 根据用户查看它有多少关注
	  * @param _userid
	  * @return
	  */
	 public Integer searchFocusCountOfUserid(long _userid);
	 /**
	  * 根据用户查看它有多少粉丝
	  * @param _userid
	  * @return
	  */
	 public Integer searchFusCountOfUserid(long _userid);
	 /**
	  * 根据用户查出此用户有多少未删除的微知
	  * @param _userid
	  * @param _isdel
	  * @return
	  */
	 public List getAllMicroblobByUserid(long _userid, Integer _isdel);
	 /**
	  * 判断要删除的用户是否属于登录当前的微知
	  * @param _microblog
	  * @return
	  */
	 public boolean microblogBelongUserid(long _microblog);
	 
	 /**
	  * 查找主题相同的所有的微知
	  * @return
	  */
	 public List<IrpMicroblog> getIrpMicroblogOfTopicValue(String _topicvalue,PageUtil pageUtil);
	 
	 
	 /**
	  * 查找主题相同的所有的微知(长度)
	  * @return
	  */
	 public int getIrpMicroblogOfTopicValueCount(String _topicvalue);
	 /**
	  * 把微知置为非法状态
	  * @param _microblogid
	  * @return
	  */
	 public int changeMicroblogTypeByids(Long _microblogid,Integer _status);
	 /**
	  * 获得用户的微知数量
	  * @param _userid
	  * @param _type
	  * @param _starttime
	  * @param _endStart
	  * @return
	  */
	 public int getMicroNumByUseridAndDate(Long _userid,Integer _type,Date _starttime,Date _endStart);
	 /**
	  * 删除一条微知（真删除）
	  * @param _microblogid
	  * @return
	  */
	 public int deleteMicroblogByIdTrue(Long _microblogid);
	 /**
	  * 清空所有非法状态下的微知
	  * @return
	  */
	 public int deleteMicroblogAllillegality(Integer _isdel);
	 /**
	  * 同时删除微知下所有的附件
	  * @param _questiongid
	  */
	 public void deleteMicroblogContentPic(Long _questiongid);
	 /**
	  * 删除已删除状态的微知的所有的附件
	  * @return
	  */
	 public void deleteMicroblogContentPicAll();
	/**
	 * 获得当前登录用户的微知列表
	 * @param muserid
	 * @param isdelid
	 * @param fuserid
	 * @return
	 * @throws SQLException
	 */
	List findMicroblogOfFocus(long isdelid,long fuserid,PageUtil pageUtil,long _loginid);
	/**
	 * 显示微知卡片
	 * @param _userid
	 * @param _isdel
	 * @return
	 */
	List findMicroblogOfUseridInCard(long _userid,Integer _isdel);
	List<IrpUserInfoView> findMicroblogOfUserid(long _userid,Integer _isdel);
	/**
	 * 获取微知的长度
	 * @param isdelid
	 * @param fuserid
	 * @return
	 */
	int findMicroblogOfFocusCount ( long isdelid, long fuserid);
	 /**
	  * 获得没有被删除的微知的个数
	  * @param isdelid
	  * @return
	  * @throws SQLException
	  */
	int findMicroblogOfAllCount(long isdelid);
	 /**
	  * 所遇的微知信息
	  * @param isdelid
	  * @param pageUtil
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 List findMicroblogOfAll( long isdelid,PageUtil pageUtil,long _loginid);
	/**
	 * 追加微知并显示列表
	 * @param isdelid
	 * @param fuserid
	 * @param _microblogid
	 * @return
	 * @throws SQLException
	 */
	List findMicroblogOfFocus(long isdelid,long fuserid,long _microblogid);
	 /**
	  * 遭举报的微知的个数
	  * @param isdelid
	  * @return
	  * @throws SQLException
	  */
	 int findMicroblogOfInformCount(long isdelid,Integer informtype,Integer informstatus,String _infromkey);
	 /**
	  * 获得所有被举报的微知
	  * @param isdelid
	  * @param pageUtil
	  * @param _loginid
	  * @return
	  * @throws SQLException
	  */
	 List findMicroblogOfInform( long isdelid,Integer informtype,Integer informstatus,PageUtil pageUtil,String _infromkey);
	 
	 /**
	  * 正反评论
	  * @param pingtype 查询选项的评论
	  * @return
	  */
	 public List<IrpMicroblog> findMicBytype(Long gid,Integer pingtype,int start,int end);
	 
	 public IrpMicroblog findFirstMicroblogbytype(long _userid,Integer pingtype);
	 /**
	  * 个性评论
	  * @param pingtype 查询标题的评论
	  * @return
	  */
	 public List<IrpMicroblog> findMicBytypeandvoteid(Long vid,Integer pingtype,PageUtil pageUtil);
	 public int findMicBytypeandvoteidcount(Long vid,Integer pingtype);
	 /**
	  * 获得固定时间内发布微知数量
	  * @param _starttime
	  * @param _endtime
	  * @return
	  * @author   npz
	  * @date 2017年9月7日
	  */
	 public int getMicroblogcount(Date _starttime,Date _endtime);
	 /**
	  * 搜索微知
	  * @param _searchword
	  * @param _isDel
	  * @return
	  * @author   npz
	  * @date 2017年9月11日
	  */
	 public List searchMicroBlog(String _searchword,Integer _isDel,PageUtil pageUtil,long _loginid);
	 public int searchMicroBlogCount(String _searchword,Integer _isDel);
	 /**
	  * 微知统计
	  * @param startTime
	  * @param endTime
	  * @param orderBy
	  * @return
	  * @author   npz
	  * @date 2017年10月10日
	  */
	 public List findMicroblogByTime(Date startTime,Date endTime,String orderBy);
}