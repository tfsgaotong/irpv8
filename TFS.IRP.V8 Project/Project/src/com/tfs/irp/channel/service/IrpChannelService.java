package com.tfs.irp.channel.service;

import java.util.HashMap;
import java.util.List; 

import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpChannelService {
	/**
	 * 后台验证栏目唯一性
	 * @param map
	 * @return
	 */
	public boolean checkChnlNameByName(HashMap<String,Object> map);
	/**
	 * 后期可能被使用clientfindRightChannelId方法替换掉
	 */
	/**带有权限（前后台都调用了）
	 * 查看子栏目的id 
	 * @param _nParnetId
	 * @param _arrChannelIds
	 * @param siteType
	 * @param chnlStatus
	 * @return
	 */
	public List<Long> findChannelIdsByParentId(Long _nParnetId, List<Long> _arrChannelIds,Integer siteType,Integer chnlStatus,IrpUser user);
	/**
	 * 查询所有的一级栏目
	 * @param pageUtil
	 * @param map
	 * @return
	 */
	public List<IrpChannel> siteAllChannel(PageUtil pageUtil,HashMap<String,Object> map,List<Integer> chnlType);
	/**
	 * 不包括他自己
	 * @param pageUtil
	 * @param map
	 * @return
	 */
	public List<IrpChannel> siteAllChannel(PageUtil pageUtil,HashMap<String,Object> map,Long _channelid,int mapId);
	/**
	 * 查看正常子栏目数量
	 * @param channelid
	 * @return
	 */
	public int findChild_Channl_CountByParentId(Long channelid); 
	/**
	 * 根据栏目状态查询该状态下的子栏目数量
	 * @param channelid
	 * @param _status
	 * @return
	 */
	public int findChild_Channl_CountByParentId(Long channelid,Integer _status); 
	/**
	 * 添加栏目
	 * @param channel
	 * @return
	 */
	public int adminAddChannel(IrpChannel channel); 
	/**
	 * 查看回收站中的栏目
	 * @param pageUtil
	 * @param map
	 * @return
	 */
    public List<IrpChannel> GCtoAllChannel( PageUtil pageUtil,HashMap<String,Object> map);
     /**
     * 将选中的栏目删除到垃圾回收站里面去
     * @param _nParentId
     * @param _nStatus
     * @param _session
     * @return
     */
	int updateChannelStatusByChannelIds(Long[] _nParentId, int _nStatus,Integer siteType); 
	/**
	 * 将选中的栏目 从垃圾回收站里面恢复回来
	 * @param _nParentId
	 * @param _nStatus
	 * @return
	 */
	int updateChannelStatusByChannelIds2(Long[] _nParentId, int _nStatus,Integer siteType); 
	/**
	 *彻底删除栏目
	 * @param channelids
	 * @param siteType
	 * @return
	 */
	public int deleteChannelFromGC(Long[] channelids, Long _nParentId, Long _nSiteId, Integer siteType);
	/**
	 * 各家栏目ID查询栏目信息
	 * @param channelid
	 * @return
	 */
	public IrpChannel finChannelByChannelid(Long channelid);
	/**
	 * 修改栏目
	 * @param channel
	 * @return
	 */
	public int updateChannelByChannelid(IrpChannel channel);
	/**
	 * 根据栏目名称查询栏目ID
	 * @param chnlname
	 * @return
	 */
	public Long selectChannelIdsByChnlName(String chnlname);
	/**
	 * 根据所给条件查询栏目数量
	 * @param map
	 * @return
	 */
	public int channelCountMap(HashMap<String,Object> map);
	/**
	 * 根据例子查找对象
	 * @param example
	 * @return
	 */
	public List<IrpChannel> findChannelByExample(IrpChannelExample example);
	/**
	 * 根据站点Id获得一级栏目的个数
	 * @param _sSiteId
	 * @return
	 */
	int findChannelCountBySiteId(long _sSiteId);
	
	/**
	 * 根据站点Id获得一级栏目对象集合
	 * @param _sSiteId
	 * @return
	 */
	List<IrpChannel> findChannelsBySiteId(long _sSiteId);
	 
	 /**
	  * 根据站点Id获得一级栏目对象集合
	  * @param _sSiteId
	  * @param isChnlType true查询特殊集合 false不查询特殊集合
	  * @return
	  */
	List<IrpChannel> findChannelsBySiteId(long _sSiteId, int _nChnlType);
	 
	/**
	 * 查询登录用户的所有一级栏目列表
	 * @param pid
	 * @return
	 */
	List<IrpChannel> findChanlsByParentid(Long pid);
	/**
	 * 前台，查看登录用户的所有栏目(包括根栏目和子栏目)
	 * @param _nParnetId
	 * @param _arrChannel
	 * @return
	 */
	List<IrpChannel> findChannelIdsByParent(Long _nParnetId,List<IrpChannel> _arrChannel);
	/**
	 * 前台,获取所有企业专题
	 * @param _nParnetId
	 * @param _arrChannel
	 * @return
	 */
	List<IrpChannel> findSubIdsByParent(Long _nParnetId,List<IrpChannel> _arrChannel);
	/**
	 * 前台，查看当前用户所有有权限的栏目进行显示用于创建案例
	 */
     List<IrpChannel> allRightChannel(); 
     List<IrpChannel> allRightChannel(IrpUser irpUser); 
	/**
	 * 获取用户的所有栏目对象
	 * @param _personId
	 * @param _nParnetId
	 * @param _arrChannel
	 * @return
	 */
	List<IrpChannel> findChannelIdsByPerson(Long _personId, Long _nParnetId, List<IrpChannel> _arrChannel);
	/**
	 * 前台企业门户，显示所有站点的栏目，但是不包括个人站点下面的栏目(内部加权限)
	 * @return
	 */
	List<IrpChannel> findChannelsByParentId(Long _parentid,Integer status,String orderBy);
	/**
	 * 前台添加栏目
	 * @param channel
	 * @return
	 */
	IrpChannel clientAddChannel(IrpChannel channel);
	/**
	 * 前台删除栏目
	 * @param _channelid
	 */
	public int clientDeleteChannel(Long _channelid);
	
	/**
	 * 添加栏目
	 * @param channel
	 * @param cruser
	 * @return
	 */
	int addChannel(IrpChannel channel, IrpUser cruser);
	/**
	 * 根据主键获取栏目名称
	 * @param _channelid
	 * @return
	 */
	 String findChannelNameByChannelid(Long _channelid);
	 /**
	  * 查询该用户的默认栏目
	  * @param _PersonId
	  * @return
	  */
	 IrpChannel findChannelByPerson(Long _PersonId);
	/**
	 * 前台用户查询有权限的栏目投稿使用
	 * @param map
	 * @param chnlTypes给定所能查看的栏目类型集合，若不给则默认不查询知识地图
	 * @return
	 */
	List<IrpChannel> clientRightChannel(HashMap<String, Object> map,List<Integer> chnlTypes);
	/**
	 * 根据栏目类型查询栏目
	 * @param _chnlType
	 * @return
	 */
	List<IrpChannel> findChannelByChnlType(Integer _chnlType);
	/**
	 * 后台得到所有的有权限的栏目进行移动
	 * @param map
	 * @return
	 */
	List<IrpChannel> jsonRightAllChannelAdmin(HashMap<String, Object> map);
	/***
	 * 递归得到自己父类的父类，一直到父类的parentid=0
	 * @param _channelid
	 * @return
	 */ 
 	List<Long> allparentidList(Long _channelid, List<Long> channelidList,Integer channelStatus,IrpUser irpUser);
	/**
	 * 获取有权限的栏目
	 * @param map
	 * @return
	 */
	List<IrpChannel> JsonRightAllChannelMore(HashMap<String, Object> map);
	/**
	 *  得到分类法下面的所有分类
	 * @param channelStatus栏目状态
	 * @return
	 */
	List<IrpChannel> allDocumentMap(Integer channelStatus);
	/**
	 * 获取知识分类所在的一条栏目tree line(和栏目不同的是知识地图没有权限控制)
	 * @param _channelid
	 * @param channelidList
	 * @param chnlStatus
	 * @return
	 */
	List<Long> mapParentLine(Long _channelid, List<Long> channelidList,Integer chnlStatus);
	/**
	 * 前台验证栏目唯一性
	 * @param siteId
	 * @param parentId
	 * @param channelId
	 * @param chnlName
	 * @return
	 */
	boolean clientCheckChnlNameByName(Long siteId, Long parentId, Long channelId, String chnlName);
	/**
	 * 查询权限
	 * @param irpUser用户
	 * @param channeLid栏目
	 * @param rightOpertion权限名称
	 * @param chnlPublishPro 栏目状态
	 * @return
	 */
	public boolean findRightToChannel(IrpUser irpUser,Long channeLid,String rightOpertion,Integer chnlPublishPro);
	/**
	 * 专题查询权限
	 * @param irpUser
	 * @param rightOpertion
	 * @param chnlPublishPro
	 * @return
	 */
	public boolean findRightSubToChannel(IrpUser irpUser, String rightOpertion, Integer chnlPublishPro,String tableName ,String channelid);
	/**
	 * 前台查询用户所有有权限的栏目ID
	 * @param _arrChannelIds 有权限的栏目集合ID
	 * @param chnlTypes 栏目类型集合，有值查询在集合内的栏目，若没有值默认不查询知识地图下的栏目
	 * @param chnlStatus 栏目是否正常状态
	 * @param _nParentId 栏目的父ID
	 * @param siteType 站点ID
	 * @param irpUser 用户对象
	 * @return
	 */
	List<Long> clientfindRightChannelId(List<Long> _arrChannelIds,
			List<Integer> chnlTypes, Integer chnlStatus, Long _nParentId,
			Integer siteType, IrpUser irpUser);
	
	List<Long> mapChildrenIds(Long _mapParentId,Integer _status,List<Long>_arrChannelIds);
	/**
	 * 查看用户有权限查看知识的栏目所有id
	 */
	List<Long> seeDocumentListRightChannel(IrpUser irpUser);
	/**
	 * 根据例子查询
	 * @param page
	 * @param example
	 * @return
	 */
	List<IrpChannel> selectByExample(PageUtil page,IrpChannelExample example);
	
	/**
	 * 根据实例新增对象
	 * @param irpChannel
	 */
	void insertChannelByExample(IrpChannel irpChannel);
	
	/**
	 * 根据channelid查询出所有父id
	 * @param channelid
	 * @return
	 */
	List<Long> findAllSubjectFatherId(IrpChannel channel,List<Long> fatherIds);
	
	/**
	 * 根据channelid查询出所有父id
	 * @param channel
	 * @param fatherIds
	 * @return
	 */
	List<Long> findAllMapFatherId(IrpChannel channel,List<Long> fatherIds);
	
	List<IrpChannel> findAllChannel(Long _printId);
	
	List<IrpChannel> findChannelsBySiteIds(long _sSiteId);
	
	/**
	 * 新版-通过父ID查找出所有指定type的专题
	 * @author sjz
	 */
	List<IrpChannel> findChannelsByPAndT(Long parentId,List<IrpChannel> list, Long type);
	
	/**
	 * 递归栏目ID的所有父栏目。
	 * @param _channelid
	 * @param channelList
	 * @return
	 */
	List<IrpChannel> currentChannels(Long _channelid, List<IrpChannel> channelList, long _nRootId);
	
	/**
	 * 根据栏目类型和父ID查询有权限的子栏目
	 * @param _parentid
	 * @param _nStatus
	 * @param _nChnltype
	 * @param _nOperId
	 * @param orderBy
	 * @return
	 */
	List<IrpChannel> findRightChannelsByParentId(long _parentid, int _nStatus, int _nChnltype, long _nOperId, String orderBy);
	/**
	 * 前台企业门户，显示所有站点的栏目，但是不包括个人站点下面的栏目(内部加权限)
	 * 手机端
	 * @return
	 */
	List<IrpChannel> findChannelsByParentId(Long _parentid,Integer status,String orderBy,String token);
	
	/**
	 * 手机端
	 * 根据栏目类型和父ID查询有权限的子栏目
	 * @param _parentid
	 * @param _nStatus
	 * @param _nChnltype
	 * @param _nOperId
	 * @param orderBy
	 * @return
	 */
	List<IrpChannel> findRightChannelsByParentId(long _parentid, int _nStatus, int _nChnltype, long _nOperId, String orderBy,String token);
	/**按照地图类型查找地图栏目
	 * @param channelTypeMapOne
	 * @return
	 */
	public List<IrpChannel> documentMapByMaptype(Long channelTypeMapOne);
	/**按权限查询不同类型的地图栏目
	 * @param maptype
	 * @param nMapRootId
	 * @param isDocstatius
	 * @param channelTypeMap
	 * @param nOperTypeId
	 * @param string
	 * @return
	 */
	public List<IrpChannel> findRightChannelsByMaptype(Long maptype,
			long nMapRootId, int isDocstatius, int channelTypeMap,
			long nOperTypeId, String string);
	/**按照栏目类型和id查找栏目
	 * @param channelid
	 * @param channelTypeMap
	 * @return
	 */
	public IrpChannel finChannelByChannelidAndChalType(Long channelid,
			int channelTypeMap);
} 
