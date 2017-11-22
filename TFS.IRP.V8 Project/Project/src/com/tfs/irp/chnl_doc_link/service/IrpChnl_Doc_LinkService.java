package com.tfs.irp.chnl_doc_link.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpChnl_Doc_LinkService {
	/**
	 * 前台  根据所给知识id集合查询知识
	 * @param docIds知识id集合
	 * @param infoType举报类型
	 * @param amount返回知识数量，若为0就是不限制
	 * @return
	 */
	  List<IrpChnlDocLink> chnlDocByDocIds(List<Long> docIds,Long infoType,int amount);
	  /**
		 * 前台  手机端根据所给知识id集合查询知识
		 * @param docIds知识id集合
		 * @param infoType举报类型
		 * @param amount返回知识数量，若为0就是不限制
		 * @return
		 */
		  List<IrpChnlDocLink> chnlDocByDocIds_token(List<Long> docIds,Long infoType,int amount,String token);
	  /**
	   * 查询某个栏目下的所有正常文档
	   * @param pageUtil
	   * @param map
	   * @param _sOrderBy
	   * @return
	   */
	   List<IrpChnlDocLink> chanAllDocLink(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType,Integer siteType);
	   
	   List<IrpChnlDocLink> chanAllDocLink(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType,Integer siteType,Date starttimes,Date endtimes);
	  /**
	   * 查询某个站点下的所有正常文档
	   * @param pageUtil
	   * @param map
	   * @param _sOrderBy
	   * @return
	   */
	    List<IrpChnlDocLink> siteAllDocLink(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType);
	    List<IrpChnlDocLink> siteAllDocLink(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType,Date starttimes,Date endtimes);

	  /**
	   * 根据站点类型获取站点Id集合
	   * @param siteType
	   * @return
	   */
	    public List<Long> findSiteIds(Integer siteType);
	    /**
	     * 删除到文档回收站
	     * @param chandocids
	     * @param _session
	     * @return
	     */
		 int deleteDocumentLinkToGC(List<Long> chandocids ,List<Long> docids);
		 /**
		  * 从文档回收站中回收文档
		     * @param chandocids
		     * @param _session
		     * @return
		     */
			 int huifuDocumentLinkfromGC(List<Long> chandocids,List<Long> docids);
		 /**
		  * 查询某个栏目下的栏目回收站中的所有栏目
		  * @param pageUtil
		  * @param map
		  * @param _sOrderBy
		  * @return
		  */
		 List<IrpChnlDocLink> chanAllDocLinkGC(PageUtil pageUtil,HashMap<String,Object> map,String _sOrderBy,Long infoType);
		 /**
		  * 查询某个站点下的栏目回收站中的所有栏目
		  * @param pageUtil
		  * @param map
		  * @param _sOrderBy
		  * @return
		  */
		 List<IrpChnlDocLink> siteAllDocLinkGC(PageUtil pageUtil,HashMap<String,Object> map,String _sOrderBy,Long infoType);
		    /**
		     * 彻底删除
		     * @param docids
		     * @return
		     */
		    int deleteDocumentLinkFromGC(List<Long> docids ); 
		    /**
		     * 通过id删除
		     * @param channelid
		     * @return
		     */
		    int deleteDocumentSubjectLink(Long channelid);
		    /**
		     * 根据文档标题查询文档对象
		     * @param doctitle
		     * @return
		     */
		    public Long selectChnlDocidByDoctitle(String doctitle);
		    /**
		     * 查询栏目下的所有文档对象
		     * @param channelid
		     * @return
		     */
		    public List<IrpChnlDocLink> chanAllDoctitle(Long channelid);
		   //根据id获取对象各一个chan_AllDocLinkGC
			public IrpChnlDocLink getChnlDocLinkById(Long chnldocid);
		    
			 public int selectCountBySiteIdAndDocStatus(HashMap<String, Object> map,Long infoType);
			 public int selectCountBySiteIdAndDocStatus(HashMap<String, Object> map,Long infoType,Date starttimes,Date endtimes);
			 public int selectCountBySiteIdAndDocStatusGC(HashMap<String, Object> map,Long infoType);
			 /**
			  * 查询回收站中的数量
			  * @param map
			  * @param infoType
			  * @return
			  */
			 public int selectCountByChannelidAndDocStatusGC(HashMap<String, Object> map,Long infoType);
			 /**
			  * 前台，获得栏目文档中间表的栏目id在集合里面的集合
			  * 此时，需要根据登录者id和传来的id比较，是否相同，
			  * 相同，查询所有
			  * 不同，查询状态为10 ，即为已经发布
			  * @param _channelids
			  * @return
			  */
			 List<IrpChnlDocLink> getPersonDocLink(PageUtil pageUtil, HashMap <String,Object> map,Long infoType);
			 /**
			  * 前台企业门户，，查看所有已经发布的文档
			  * @return
			  */
			 List<IrpChnlDocLink> clientShowDoc(Map<String,Object> map,Long infoType,Integer amount);
			 List<IrpChnlDocLink> clientSearchDoc(Map<String,Object> map,Long infoType,Integer pageNum,Integer amount,String searchWord);
			 /**
			  * 前台概览页面显示某个栏目的发布的文档
			  * @param _channelid
			  * @return
			  */
			List<IrpChnlDocLink> clientChannelShowDoc(Long _channelid,Long infoType);
			/**
			 * 得到当前登录者创建的所有文档数量
			 * @return
			 */
			int myDocumentCount( Long infoType);
			/**
			 * 得到当前登录这创建的所有文档
			 * @return
			 */
			List<IrpChnlDocLink> myAllDocument( Long infoType);
			/**
			 * 查询文档列表（前台企业知识）
			 * @param map
			 * @return
			 */
			List<IrpChnlDocLink> selectLikeDocumentToList(Long thisDocid,HashMap<String,Object> map,Long infoType);
			/**
			 * 根据所给条件查询用户集合
			 * @param map
			 * @return
			 */
			List<IrpUser> selectPersonLike(HashMap<String,Object> map,Long infoType);
			/**
			 * 获取前台某个栏目下的所有文档
			 * @param pageUtil
			 * @param map
			 * @param _sOrderBy
			 * @return
			 */
			List<IrpChnlDocLink> clientChanAllDocLink(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType);
		/**
		 * 前台根据所给条件拿到一级栏目的所有文档个数
		 * map里面要要有一个文档的状态
		 * @param map
		 * @return
		 */
		int clientSelectOneChannelCountByChannelidAndDocstatus( HashMap<String, Object> map,Long infoType);
		/**
		 * 根据条件查询某个栏目下的文档数量
		 * @param map
		 * @return
		 */
		int noDiGuiChannelAllDocCount(Map<String, Object> map,Long infoType);
		/**
		 * 根据条件查询返回案例
		 * @param map
		 * @return
		 */
		List<IrpChnlDocLink> selectDocumentByMap(Map<String,Object> map,Long infoType);
		/**
		 * 根据所给条件查询一个文档
		 * 栏目类型为公告，每月之星这类特殊栏目下的知识
		 */
		List<IrpChnlDocLink> selectMonthDocument(Long channelid);
		/**
		 * 求点击量
		 */
		int sumHit(List<Long>_arrChannelids,Long infoType);
		List<Long> docIds(List<Long> _arrChannelids,Long infoType);
		/**
		 * 匹配集合中是否有改对象
		 * @param list
		 * @param obj
		 * @return
		 */
		boolean contains(List<IrpChnlDocLink> list, IrpChnlDocLink obj);
		
		IrpDocument   findDocumentByDocId(Long _docId );
		/**
		 * 得到自己创建的所有docid
		 * @param map
		 * @return
		 */
		List<Long> allDocids(Map<String, Object> map,Long infoType);
		/**
		 * 查询投稿的知识
		 */
		List<IrpChnlDocLink> alltougaodocument(PageUtil pageUtil,List<Long> oldDocids,String sOrderByClause,Long infoType);
		/**
		 * 查询我投稿的知识的数量
		 * @param oldDocids
		 * @return
		 */
		int alltougaodocumentCount(List<Long> oldDocids,Long infoType);
		int alltougaoCount(List<Long> oldDocids,Long infoType,IrpUser irpuser);
		/**
		 * 根据条件查询一篇加精的知识
		 */
		public IrpChnlDocLink oneJiaJingDocumentByMap(Map<String, Object> map,Long infoType);
		/**
		 * 查询精华知识
		 */
		List<IrpChnlDocLink> typicalDocLink(Map<String,Object> map,Long infoType,Integer dateAmount,PageUtil pageUtil);
		/**
		 * 查询知识数量
		 * @param map
		 * @param infoType
		 * @param siteType
		 * @return
		 */
		int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,Long infoType,Integer siteType);
		
		int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,Long infoType,Integer siteType,Date starttimes,Date endtimes);
		
		/**
		 * 查询举报或非法知识
		 * @param sitetypeOne所属站点类型1
		 * @param sitetypeTwo所属站点类型2
		 * @param informType 知识类型
		 * @param informStatus知识状态
		 * @param pageUtil
		 * @return
		 */
		List  findJuBaoDoc(Integer sitetypeOne,Integer sitetypeTwo,String informKey,Integer informType,Integer informStatus, PageUtil pageUtil);
		List  findJuBaoDoc_app(IrpUser irpuser,Integer sitetypeOne,Integer sitetypeTwo,String informKey,Integer informType,Integer informStatus, PageUtil pageUtil);
		/**
		 * 查询举报或非法知识的数量
		 * @param sitetypeOne所属站点类型1
		 * @param sitetypeTwo所属站点类型2
		 * @param informType 知识类型
		 * @param informStatus知识状态
		 * @return
		 */
		Integer findJuBaoDocCount(Integer sitetypeOne,Integer sitetypeTwo,String informKey,Integer informType,Integer informStatus);
		Integer findJuBaoDocCount_app(IrpUser irpuser,Integer sitetypeOne,Integer sitetypeTwo,String informKey,Integer informType,Integer informStatus);
		/**
		 * 修改知识informStatus 和举报表中的状态是否通过
		 * @param _docid知识id
		 * @param informType是否为举报状态
		 * @param _informStatus 举报是否通过状态
		 * @return
		 */
		public int passOrNotInformDocument(Long _docid,Long informType,Integer _informStatus);
		/**
		 * 删除举报记录
		 * @param _docid 
		 * @return
		 */
		int deleteInformDocument(Long informId ); 
		/**
		 * （给高彤写的接口）查询一段时间范围类某站点类型下某人创建的知识数量
		 * @param userId
		 * @param startTime
		 * @param endTime
		 * @param siteType
		 * @param docStatus文档类型(可不给)
		 * @return
		 */
		public  int selectCountByExpert(Long userId,Date startTime,Date endTime,Integer siteType,Long docStatus);
		/**
		 * 批量移动某个栏目下的知识到某个栏目下面(同时修改document)
		 * @param toChannel目标栏目ID
		 * @param fromChannel原始栏目ID
		 * @return
		 */
		int updateDocumentToChannel(Long toChannel,Long fromChannel);
		/**
		 * 批量修改栏目下的知识状态为相反数
		 * @param channelid
		 * @return
		 */
		int updateDocumentDocstatusByChannel(Long channelid);
		/**
		 * 根据站点类型查询某站点下的知识
		 * @param _siteType站点类型(如若没有给类型，则查询系统中的所有知识)
		 * @return
		 */
		List<IrpDocument> allDatas(Integer _siteType);
		/**
		 *查询某范围内的用户检索记录词
		 * @param userId 用户ID
		 * @param starttime 开始时间
		 * @param endtime 结束时间
		 * @return
		 */
		List<String> selectByUserIdOrderbyCountDescBetweenTime(Long userId,Date starttime,Date endtime );
		/**
		 * 前台查看知识数量
		 * @param _sSearchWord 检索词
		 * @param _sSearchType 检索字段
		 * @param informType 是否举报状态
		 * @param siteType 站点类型
		 * @param docStatus 知识状态
		 * @param isFengMian 知识封面
		 * @param _arrChannelIds 知识所属栏目id集合
		 * @param sOrderByClause 排序
		 * @return
		 */
		int clientSelectChnlDocCount(String _sSearchWord, String _sSearchType,
				Long informType, Integer siteType, Long docStatus,
				Integer isFengMian, List<Long> _arrChannelIds,
				String sOrderByClause);
		/**
		 * 前台查看知识列表
		 * @param _sSearchWord 检索词
		 * @param _sSearchType 检索字段
		 * @param informType 是否举报状态
		 * @param siteType 站点类型
		 * @param docStatus 知识状态
		 * @param isFengMian 知识封面
		 * @param _arrChannelIds 知识所属栏目id集合
		 * @param sOrderByClause 排序
		 * @return
		 */
		List<IrpChnlDocLink> clientSelectDocChnl(String _sSearchWord, String _sSearchType,
				Long informType, Integer siteType, Long docStatus,
				Integer isFengMian, List<Long> _arrChannelIds,
				String sOrderByClause,PageUtil pageUtil);
		/**
		 * 按照时间查询知识
		 * @param startTime 开始时间
		 * @param endTime 结束时间
		 * @param orderBy 排序
		 * @return
		 */
		public List findChnlDocByTime(Date startTime,Date endTime,String orderBy);
		/**
		 * 根据用户查询知识id集合
		 * @param _ocidList
		 * @param _channelids
		 * @param _irpUser
		 * @return
		 */
		public List<Long> findDocidsByDocIdsAndChannelidAndRight(List<Long> _ocidList,List<Long> _channelids,IrpUser _irpUser,Long infoType);
		/**
		 * 查询加精知识的数量
		 * @param map
		 * @param informType
		 * @param dateAmount
		 * @return
		 */
		int typicalDocLinkAmount(Map<String, Object> map, Long informType, Integer dateAmount);
		
		/**
		 * 查询知识库首页最新评论数据
		 * @return
		 */
		List<Map<String, IrpBaseObj>> findDocComments();
		
		/**
		 * 根据知识地图的栏目ID和类型获得分类下的知识列表
		 * @param _nChannelId
		 * @param _nType
		 * @param sOrderByClause
		 * @param pageUtil
		 * @return
		 */
		List<IrpChnlDocLink> findIrpChnlDocLinksByMapChannelId(long _nChannelId, int _nType, String sOrderByClause, PageUtil pageUtil);
		
		/**
		 * 根据知识地图的栏目ID和类型获得分类下的知识数量
		 * @param _nChannelId
		 * @param _nType
		 * @return
		 */
		int findIrpChnlDocLinksCountByMapChannelId(long _nChannelId, int _nType);
		
		/**
		 * 精华知识V8
		 * @param _nChannelId
		 * @param _nEssenceType
		 * @param pageUtil
		 * @return
		 */
		List<IrpChnlDocLink> findEssenceDocs(Long _nChannelId, int _nEssenceType, PageUtil pageUtil);
		
		/**
		 * 获得专家推荐知识列表V8
		 * @param _nChannelId
		 * @param pageUtil
		 * @return
		 */
		List<IrpChnlDocLink> findExpertRecommendDocs(Long _nChannelId, PageUtil pageUtil);
		
		/**
		 * 获得专家推荐知识总数量
		 * @param _nChannelId
		 * @return
		 */
		int countExpertRecommendDocs(Long _nChannelId);
		/**按条件查询数量
		 * @param example 
		 * @return
		 */
		int selectCountExample(IrpChnlDocLinkExample example);
		/**
		 * 按条件查询
		 * @param pageUtil
		 * @param example
		 * @return
		 */
		List<IrpChnlDocLink> selectByExample(PageUtil pageUtil,
				IrpChnlDocLinkExample example);
		/**将文档信息导出excel
		 * @param chnlDocLinks
		 * @param path
		 * @param fileName
		 */
		void exportAllDocInfoToZip(List<IrpChnlDocLink> chnlDocLinks,
				String path, String fileName);
		/**多个栏目下的共同的文章数量
		 * @param channelidss
		 * @param i
		 * @return
		 */
		int findIrpChnlDocLinksCountByMapChannelIds(String channelidss, int i);
		/**多个栏目下的共同的文章
		 * @param channelidss
		 * @param i
		 * @param object
		 * @param pageUtil
		 * @return
		 */
		List<IrpChnlDocLink> findIrpChnlDocLinksByMapChannelIds(
				String channelidss, int i, String object, PageUtil pageUtil);
		/** 获取父节点下的所有文章数
		 * @param idString
		 * @param i
		 * @return
		 */
		int findIrpChnlDocLinksCountByMapParentIds(String idString, int i);
		/** 获取父节点下的所有文章
		 * @param idString
		 * @param i
		 * @param object
		 * @param pageUtil
		 * @return
		 */
		List<IrpChnlDocLink> findIrpChnlDocLinksByMapParentIds(String idString,
				int i, String object, PageUtil pageUtil);
}
