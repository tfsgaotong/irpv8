package com.tfs.irp.document.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;
import com.tfs.irp.util.PageUtil;

public interface IrpDocumentService {
	 
	public List<Long> selectDocIds(IrpDocumentExample example);
	/**
	 * 添加文档
	 * @param document
	 * @param docorder
	 * @param _session
	 */
	public int addDocument(IrpDocumentWithBLOBs document ,String jsonFile,boolean isClient, Long _toChannelid,Boolean addDocument,String[] documentmaps,Integer _docsource);
	public int addDocument(IrpDocument document);
	
//////付燕妮 2017-4-19
	public ArrayList<Long> addAttFile(Integer _attflag,String _sAttFile,Long TypeId,IrpDocumentWithBLOBs document,String _sAttDesc,String _sAttLinkAlt,String _sEditversions,boolean isClient, Long _touChannelid,Boolean addUserFile);
	/**
	 * 根据id获得document对象
	 * @param docid
	 * @return
	 */
	public IrpDocumentWithBLOBs findDocumentByDocId(Long docid);
	/**
	 * 修改文档
	 * @param irpDocument
	 * @param _session
	 * @param isClient
	 * @param _touChanneild
	 * @return
	 */
	public int updateDocumentByDocId(IrpDocumentWithBLOBs irpDocument,String jsonFile,boolean isClient,Long _touChannelid,Boolean addUserFileLog,String[] documentmaps,String[] subjects,String flag); 
	//根据id获取对象
	public IrpDocumentWithBLOBs getIrpDocumentById(Long docid);
	/**
	 * 前台，，添加文档
	 * @param document
	 * @param jsonFile
	 * @param 是否添加微知
	 * @return
	 */
	int clientAddDocument(IrpDocumentWithBLOBs document, String jsonFile,Long _channelid,Long isTrue,boolean isClient,String[] documentmaps,String[] subjects,String flag);
	/**
	 * 前台，，修改文档
	 * @param document
	 * @param jsonFile
	 * @param _channelid
	 * @return
	 */
	int clientUpdateDocument(IrpDocumentWithBLOBs document, String jsonFile,boolean isClient,Long _touChanneild,String[] documentmaps,String[] subjects,String flag);
	/**
	 * 前台，，删除文档
	 * @param _docid
	 * @return
	 */
	public int clientDeleteDocument(Long _docid);
	/**
	 * 彻底删除文章
	 * @return
	 */
	public int deleteDocument(Long _docid);
	/***
	 * 增加点击量
	 * @param _docid
	 */
	public void addHitScount(Long _docid);
	
	/**
	 * 根据DocId修改文章状态
	 * @param _nDocId
	 * @param _nDocStatus
	 */
	int updateDocStatusByDocId(Long _nDocId, Long _nDocStatus);
	/**
	 *根据所给文档对象，来修改文档所属栏目id
	 * @param document
	 * @return
	 */
	int clientUpdateDocumentChannelId(IrpDocumentWithBLOBs document);
	
	int adminUpdateDocumentChannelId(long _nChannelId, List<Long> _arrDocIds, List<Long> _arrChnlDocIds);
	
	IrpDocumentWithBLOBs updateHitScountJia(IrpDocumentWithBLOBs irpDocument); 
	 /**
	  * 查看知识，根据他的olddocid
	  * @param _oldId
	  * @return
	  */
	IrpDocumentWithBLOBs findDoucumentByOldId(Long _oldId);

	/**
	 * 求文章的点击量
	 */
	int sumTouHit(List<Long>_docids);
	
	List<IrpDocumentMap> docDocumentMap(Long docid,String type);
	
	
	/**
	 * 根据id获取文档对象
	 * @param docid
	 * @return
	 */
	IrpDocument irpDocument(Long docid);
	/**
	 * 修改知识的举报状态
	 * @param docid
	 * @param infromType
	 * @return
	 */
	int updateDocumentInformType(Long docid,Long infromType);
	/**
	 * 将某个栏目下的知识移动到某个栏目下面
	 * @param toChannel 目标栏目ID
	 * @param fromChannel 原始栏目ID
	 * @return
	 */
	int updateDocumentToChannel(Long toChannel,Long fromChannel);
	/**
	 * 根据条件获取知识集合
	 */
	List<IrpDocument> findAllDatas(IrpDocumentExample documentExample);
	/**
	 * 给知识添加加精数量
	 * @param _docid 知识ID
	 * @param _amount 加精数量
	 * @return
	 */
	int addChisslAmount(Long _docid,Integer _amount);
	/**
	 * 查询分数处于某个分数范围内的知识数量
	 * @param minScore
	 * @param maxScore
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int countDocument(BigDecimal minScore, BigDecimal maxScore,Date startTime,Date endTime);
	/**
	 * 获取知识的积分，发布时间
	 * @param startTime 开始时间	
	 * @param endTime 结束时间
	 * @return
	 */
	List<IrpDocument> sctterDocument(Date startTime,Date endTime,Long siteId);
	
	/**
	 * 添加document到Solr索引
	 * @param document
	 */
	void addSolrIndex(IrpDocumentWithBLOBs document);
	
	/**
	 * 修改document到Solr索引
	 * @param document
	 * @param oldDocument
	 */
	void updateSolrIndex(IrpDocumentWithBLOBs document);
	
	/**
	 * 根据例子查询
	 * @param page
	 * @param example
	 * @return
	 */
	List<IrpDocument> selectByExample(PageUtil page ,IrpDocumentExample example);
	/**
   * 查询某个栏目下的所有正常文档
   * @param pageUtil
   * @param map
   * @param _sOrderBy
   * @return
   */
   List<IrpDocument> checkAllDocSub(PageUtil pageUtil,HashMap<String,Object> map ,String _sOrderBy,Long infoType);
   /**
	 * 查询知识数量
	 * @param map
	 * @param infoType
	 * @param siteType
	 * @return
	 */
	int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,Long infoType);
	
	int selectCountByGroupidDoc(Long siteid,List<Long> groupid);
		
	public	List<IrpDocument> findAllDocByuser(List<Long> stuts);
	
	/**
	 * 新增知识
	 * @param document
	 */
	void addDocument(Map<String,Object> map);
	/**
	 * 判断文章标题的相似
	 * @param _title
	 * @param infortype
	 * @param doctype
	 * @param _docstatus
	 * @return
	 */
	List<IrpDocument> boolSimilByTitle(String _title,int infortype,int _docstatus);
	
	
	/**
	 * 通过docoutupid修改document表中数据
	 * @param _docoutupid
	 * @return
	 */
	int updateDocument(long _docoutupid,IrpDocversionWithBLOBs _irpDoument);
	
	
	/**
	 * 根据DOCID给知识本身添加分数
	 * @param _nDocId
	 * @param _nScore
	 * @return
	 */
	int updateDocscoreByPrimaryKey(long _nDocId, long _nScore);
	
	int selectCountByChannelDoc(Long siteid,List<Long> channelId);
	
	/**
	 * 根据ID集合更新知识状态
	 * @param _sDocId
	 * @param _nDocStatus
	 * @return
	 */
	int updateDocStatusByDocIds(String _sDocId, Long _nDocStatus);
	
	/**
	 * 系统中企业知识库的知识数量
	 * @return
	 */
	long findAllDocumentCount();
	
	/**
	 * 根据知识ID修改知识
	 * @param _irpDoument
	 * @return
	 */
	int updateDocumentByDocId(IrpDocumentWithBLOBs _irpDoument);
	
	/**
	 * 根据ID查询相似的知识图数据
	 * @param _nDocId
	 * @return
	 */
	List<IrpDocument> similarityDocByDocId(long _nDocId);
	
	/**
	 * 根据id获得document对象 手机端
	 * @param docid、token
	 * @return
	 */
	public IrpDocumentWithBLOBs findDocumentByDocId(Long docid,String token);
}
