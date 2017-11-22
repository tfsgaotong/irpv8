package com.tfs.irp.chnl_doc_link.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.channel.dao.IrpChannelDAO;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample.Criteria;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.config.entity.IrpConfigExample;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentcollection.dao.IrpDocumentCollectionDAO;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollectionExample;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.opertype.dao.IrpOpertypeDAO;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.tag.dao.IrpTagDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.SignUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpChnl_Doc_LinkServiceImpl implements IrpChnl_Doc_LinkService {
	private IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO;
	private IrpDocumentCollectionDAO irpDocumentCollectionDAO;
	private IrpDocumentService irpDocumentService;
	private IrpAttachedService irpAttachedService;
	private IrpChannelDAO irpChannelDAO;
	private IrpSiteService irpSiteService;
	private IrpOpertypeDAO irpOpertypeDAO;
	private IrpTagDAO irpTagDAO;
	private IrpSelectKeyService irpSelectKeyService;
	private IrpInformService irpInformService;
	private IrpDocStatusService irpDocStatusService;
	private SolrService solrService;
	/**
	 * sheet计数器
	 */
	private int sheetNum = 0;
	/**
	 * userId状态值
	 */
	private Long SignidState;
	
	public IrpDocStatusService getIrpDocStatusService() {
		return irpDocStatusService;
	}

	public void setIrpDocStatusService(IrpDocStatusService irpDocStatusService) {
		this.irpDocStatusService = irpDocStatusService;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public Long getSignidState() {
		return SignidState;
	}

	public void setSignidState(Long signidState) {
		SignidState = signidState;
	}

	public IrpInformService getIrpInformService() {
		return irpInformService;
	}

	public SolrService getSolrService() {
		return solrService;
	}

	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}

	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}

	public IrpTagDAO getIrpTagDAO() {
		return irpTagDAO;
	}

	public void setIrpTagDAO(IrpTagDAO irpTagDAO) {
		this.irpTagDAO = irpTagDAO;
	}

	public IrpSelectKeyService getIrpSelectKeyService() {
		return irpSelectKeyService;
	}

	public void setIrpSelectKeyService(IrpSelectKeyService irpSelectKeyService) {
		this.irpSelectKeyService = irpSelectKeyService;
	}

	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	/**
	 * 拼接sql，栏目类型是公告，每月之星，地图，个人的
	 */
	public String otherRightChannelExits(Integer channelStatus) {
		String rightStr = "exists ( " + "select 1 from irp_channel where"
				+ " irp_channel.channelid=irp_chnl_doc_link.channelid ";
		if (channelStatus != null) {
			rightStr += " and irp_channel.publishpro =" + channelStatus;
		}
		rightStr += " and irp_channel.chnltype not in" + " ("
				+ IrpChannel.CHANNEL_TYPE_GONGGAO + ","
				+ IrpChannel.CHANNEL_TYPE_MAP + ","
				+ IrpChannel.CHANNEL_TYPE_MONTH_TOP + ","
				+ IrpChannel.CHANNEL_TYPE_PRIVATE + "))";
		return rightStr;
	}

	@Override
	public List<IrpChnlDocLink> chnlDocByDocIds(List<Long> docIds,
			Long infoType, int amount) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));// 不查询公告，每月之星，知识地图//企业栏目里面发布的栏目
			IrpUser irpUser = LoginUtil.getLoginUser();
			RightUtil rightUtil = new RightUtil();
			if (!irpUser.isAdministrator()) {// 不是管理员就加权限
				Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				Long nOperDocId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
				if (nOperId != null && nOperId > 0L && nOperDocId != null
						&& nOperDocId != 0L) {
					String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
							nOperId);// 得到是否有权限语句
					String sDocSql = rightUtil.getRightExistsSQL(
							new IrpChannel(), nOperDocId);// 得到是否有权限语句
					criteria.extSQL(sSql).extSQL(sDocSql);
				}
			}

			if (infoType != null) {
				criteria.andInformtypeEqualTo(infoType);
			}
			if (docIds.size() > 0 && docIds != null) {
				// 处理Oracle在执行in时超过1000个报错
				if (docIds != null && docIds.size() > 1000) {
					List<Long> tempChnlDocIds = new ArrayList<Long>();
					for (int i = 0; i < docIds.size(); i++) {
						tempChnlDocIds.add(docIds.get(i));
						if (tempChnlDocIds.size() == 1000
								|| (i + 1) == docIds.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria()
									.andDocidIn(tempChnlDocIds));
							tempChnlDocIds = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andDocidIn(docIds);
				}
				String orderIds = docIds.toString();
				orderIds = orderIds.substring(1, orderIds.length() - 1);
				chnlDocLinkExample.setOrderByClause("  INSTR('" + orderIds
						+ "',docid) ");
				if (amount > 0) {
					int aDataCount = irpChnl_Doc_LinkDAO
							.countByExample(chnlDocLinkExample);
					PageUtil pageUtil = new PageUtil(1, amount, aDataCount);// 第一页，显示十条
					list = irpChnl_Doc_LinkDAO.selectByExample(
							chnlDocLinkExample, pageUtil);
				} else {
					list = irpChnl_Doc_LinkDAO
							.selectByExample(chnlDocLinkExample);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpChnlDocLink> chnlDocByDocIds_token(List<Long> docIds,
			Long infoType, int amount,String token) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));// 不查询公告，每月之星，知识地图//企业栏目里面发布的栏目
			IrpUser irpUser = mobileAction.getlogin(token);
			//用户id为1(admin用户)的默认设置成系统管理员
			if(irpUser.getUserid()==1L){
				irpUser.setAdministrator(true);
			}
			RightUtil rightUtil = new RightUtil();
			if (!irpUser.isAdministrator()) {// 不是管理员就加权限
				Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				Long nOperDocId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
				if (nOperId != null && nOperId > 0L && nOperDocId != null
						&& nOperDocId != 0L) {
					String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
							nOperId);// 得到是否有权限语句
					String sDocSql = rightUtil.getRightExistsSQL(
							new IrpChannel(), nOperDocId);// 得到是否有权限语句
					criteria.extSQL(sSql).extSQL(sDocSql);
				}
			}

			if (infoType != null) {
				criteria.andInformtypeEqualTo(infoType);
			}
			if (docIds.size() > 0 && docIds != null) {
				// 处理Oracle在执行in时超过1000个报错
				if (docIds != null && docIds.size() > 1000) {
					List<Long> tempChnlDocIds = new ArrayList<Long>();
					for (int i = 0; i < docIds.size(); i++) {
						tempChnlDocIds.add(docIds.get(i));
						if (tempChnlDocIds.size() == 1000
								|| (i + 1) == docIds.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria()
									.andDocidIn(tempChnlDocIds));
							tempChnlDocIds = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andDocidIn(docIds);
				}
				String orderIds = docIds.toString();
				orderIds = orderIds.substring(1, orderIds.length() - 1);
				chnlDocLinkExample.setOrderByClause("  INSTR('" + orderIds
						+ "',docid) ");
				if (amount > 0) {
					int aDataCount = irpChnl_Doc_LinkDAO
							.countByExample(chnlDocLinkExample);
					PageUtil pageUtil = new PageUtil(1, amount, aDataCount);// 第一页，显示十条
					list = irpChnl_Doc_LinkDAO.selectByExample(
							chnlDocLinkExample, pageUtil);
				} else {
					list = irpChnl_Doc_LinkDAO
							.selectByExample(chnlDocLinkExample);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean contains(List<IrpChnlDocLink> list, IrpChnlDocLink obj) {
		boolean b = false;
		for (int i = 0; i < list.size(); i++) {
			IrpChnlDocLink chnlDocLink = list.get(i);
			String docid = chnlDocLink.getDocid().toString();
			String objDocid = obj.getDocid().toString();
			if (docid.equals(objDocid)) {
				b = true;
				break;
			}
		}
		return b;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpOpertypeDAO getIrpOpertypeDAO() {
		return irpOpertypeDAO;
	}

	public void setIrpOpertypeDAO(IrpOpertypeDAO irpOpertypeDAO) {
		this.irpOpertypeDAO = irpOpertypeDAO;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public IrpDocumentCollectionDAO getIrpDocumentCollectionDAO() {
		return irpDocumentCollectionDAO;
	}

	public void setIrpDocumentCollectionDAO(
			IrpDocumentCollectionDAO irpDocumentCollectionDAO) {
		this.irpDocumentCollectionDAO = irpDocumentCollectionDAO;
	}

	public IrpChannelDAO getIrpChannelDAO() {
		return irpChannelDAO;
	}

	public void setIrpChannelDAO(IrpChannelDAO irpChannelDAO) {
		this.irpChannelDAO = irpChannelDAO;
	}

	@Override
	public Long selectChnlDocidByDoctitle(String doctitle) {
		Long chnldocid = null;
		try {
			chnldocid = irpChnl_Doc_LinkDAO.selectChnlDocidByDoctitle(doctitle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnldocid;
	}

	@Override
	public int deleteDocumentLinkFromGC(List<Long> chnldocids) {
		int nCount = 0;
		LogUtil logUtil = null;
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		try {
			IrpChnlDocLink _irpChnlDocLink = irpChnl_Doc_LinkDAO
					.selectByPrimaryKey(chnldocids.get(0));
			logUtil = new LogUtil("DOCUMENT_DELETE", _irpChnlDocLink);
			// 处理Oracle在执行in时超过1000个报错
			if (chnldocids != null && chnldocids.size() > 1000) {
				List<Long> tempChnlDocIds = new ArrayList<Long>();
				for (int i = 0; i < chnldocids.size(); i++) {
					tempChnlDocIds.add(chnldocids.get(i));
					if (tempChnlDocIds.size() == 1000
							|| (i + 1) == chnldocids.size()) {
						example.or(example.createCriteria().andChnldocidIn(
								tempChnlDocIds));
						tempChnlDocIds = new ArrayList<Long>();
					}
				}
			} else {
				example.createCriteria().andChnldocidIn(chnldocids);
			}
			// 查询所有中间件
			List<IrpChnlDocLink> chnlLinkList = irpChnl_Doc_LinkDAO
					.selectByExample(example);
			// 删除知识
			if (chnlLinkList != null && chnlLinkList.size() > 0) {
				for (IrpChnlDocLink irpChnlDocLink : chnlLinkList) {
					nCount = nCount
							+ irpDocumentService.deleteDocument(irpChnlDocLink
									.getDocid());// 删除知识（方法中将删除和知识有关的所有表数据）
					// 删除solr
					if (irpChnlDocLink.getSiteid().longValue() == IrpSite.PRIVATE_SITE_ID) {
						solrService
								.deleteSolrIndex(
										irpChnlDocLink.getDocid(),
										SysConfigUtil
												.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL));
					} else {
						solrService
								.deleteSolrIndex(
										irpChnlDocLink.getDocid(),
										SysConfigUtil
												.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE));
					}
				}
			}
			irpChnl_Doc_LinkDAO.deleteByExample(example);
			logUtil.successLogs("彻底删除文档[" + logUtil.getLogUser() + "],成功");
		} catch (SQLException e) {
			logUtil.errorLogs("彻底删除文档[" + logUtil.getLogUser() + "],失败");
			e.printStackTrace();
		}
		return nCount;
	}

	// 修改文档状态(删除到文档回收站),同时也需要将它所引用的document表中的状态也修改为相反值
	@Override
	public int deleteDocumentLinkToGC(List<Long> chandocids, List<Long> docids) {
		int nCount = 0;
		LogUtil logUtil = null;
		try {
			IrpChnlDocLink _irpChnlDocLink = irpChnl_Doc_LinkDAO
					.selectByPrimaryKey(chandocids.get(0));
			logUtil = new LogUtil("DOCUMENT_DOCSTATUS_UPDATE", _irpChnlDocLink);
			for (Long docid : docids) {
				IrpDocument document = irpDocumentService
						.findDocumentByDocId(docid);
				Long docstatus = 0L;
				Long oldDocstatus = document.getDocstatus();
				if (oldDocstatus.longValue() > 0L) {// 如果是正常的知识就删除到回收站，非正常的仍在回收站
					docstatus = -oldDocstatus;
				}
				nCount = nCount
						+ irpDocumentService.updateDocStatusByDocId(
								document.getDocid(), docstatus);
				// 删除solr
				if (document.getSiteid().longValue() == IrpSite.PRIVATE_SITE_ID) {
					solrService
							.deleteSolrIndex(
									document.getDocid(),
									SysConfigUtil
											.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_PERSONAL));
				} else {
					solrService
							.deleteSolrIndex(
									document.getDocid(),
									SysConfigUtil
											.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE));
				}
			}
			logUtil.successLogs("删除文档[" + logUtil.getLogUser() + "]到回收站,成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("删除文档[" + logUtil.getLogUser() + "]到回收站,失败");
		}
		return nCount;
	}

	@Override
	public int huifuDocumentLinkfromGC(List<Long> chandocids, List<Long> docids) {
		int nCount = 0;
		LogUtil logUtil = null;
		try {
			IrpChnlDocLink _irpChnlDocLink = irpChnl_Doc_LinkDAO
					.selectByPrimaryKey(chandocids.get(0));
			logUtil = new LogUtil("DOCUMENT_RESTORE", _irpChnlDocLink);

			for (Long docid : docids) {
				IrpDocumentWithBLOBs document = irpDocumentService
						.findDocumentByDocId(docid);
				Long docstatus = 0L;
				docstatus = -document.getDocstatus();
				nCount = nCount
						+ irpDocumentService.updateDocStatusByDocId(
								document.getDocid(), docstatus);
				// 添加solr
				document.setDocstatus(docstatus);
				irpDocumentService.addSolrIndex(document);
			}
			logUtil.successLogs("恢复文档[" + logUtil.getLogUser() + "],成功");
		} catch (SQLException e) {
			logUtil.errorLogs("恢复文档[" + logUtil.getLogUser() + "],失败");
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpChnlDocLink> chanAllDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType,
			Integer siteType) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
			Criteria criteria = example.createCriteria();
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			// 排序
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			List<Long> sites = irpSiteService.findSiteIds(siteType);
			if (sites != null && sites.size() > 0) {
				criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
			}
			list = irpChnl_Doc_LinkDAO.chan_AllDocLink(pageUtil, example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					IrpChnlDocLink chnlDocLink = list.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(chnlDocLink.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						list.get(i)
								.setIrpDocumentCollection(collections.get(0));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpChnlDocLink> chanAllDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType,
			Integer siteType,Date starttimes,Date endtimes) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
			Criteria criteria = example.createCriteria();
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			//时间段   
			if(starttimes!=null && endtimes!=null){
			    criteria.andCrtimeBetween(starttimes,endtimes);	
		
			}
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			// 排序
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			List<Long> sites = irpSiteService.findSiteIds(siteType);
			if (sites != null && sites.size() > 0) {
				criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
			}
			list = irpChnl_Doc_LinkDAO.chan_AllDocLink(pageUtil, example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					IrpChnlDocLink chnlDocLink = list.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(chnlDocLink.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						list.get(i)
								.setIrpDocumentCollection(collections.get(0));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpChnlDocLink> siteAllDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusGreaterThanOrEqualTo(
							(Long) map.get("docstatus")); // 查看正常的，因此他的状态是大于等于0的
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));// docstatus是状态
				}
			}
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "docorder desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.site_AllDocLink(pageUtil, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpChnlDocLink> siteAllDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType,Date starttimes,Date endtimes) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusGreaterThanOrEqualTo(
							(Long) map.get("docstatus")); // 查看正常的，因此他的状态是大于等于0的
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));// docstatus是状态
				}
			}
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "docorder desc";
			}
			example.setOrderByClause(_sOrderBy);
			//时间段   
			if(starttimes!=null && endtimes!=null){
			    criteria.andCrtimeBetween(starttimes,endtimes);	
		
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.site_AllDocLink(pageUtil, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpChnlDocLink> chanAllDocLinkGC(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidEqualTo((Long) map.get("channelid"))
					.andDocstatusLessThan((Long) map.get("docstatus"));
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "docorder desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.chan_AllDocLinkGC(pageUtil, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpChnlDocLink> siteAllDocLinkGC(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();

			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusLessThan((Long) map.get("docstatus"));// <1
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "docorder desc";
			}
			example.setOrderByClause(_sOrderBy);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.site_AllDocLinkGC(pageUtil, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}

	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}

	@Override
	public List<IrpChnlDocLink> chanAllDoctitle(Long channelid) {
		List<IrpChnlDocLink> links = null;
		try {
			links = irpChnl_Doc_LinkDAO.chan_allDoctitle(channelid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return links;
	}

	@Override
	public IrpChnlDocLink getChnlDocLinkById(Long chnldocid) {
		IrpChnlDocLink chnlDocLink = null;
		try {
			chnlDocLink = irpChnl_Doc_LinkDAO.selectByPrimaryKey(chnldocid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLink;
	}

	// 查询站点下文档数量
	@Override
	public int selectCountBySiteIdAndDocStatus(HashMap<String, Object> map,
			Long informType) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusGreaterThanOrEqualTo(
							(Long) map.get("docstatus"));// 查看正常的，因此他的状态是大于等于0的
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");// 文档状态 docstatus的值
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo((Long.parseLong(searchModal
							.toString())));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			nCount = irpChnl_Doc_LinkDAO
					.selectCountBySiteIdAndDocStatus(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int selectCountBySiteIdAndDocStatus(HashMap<String, Object> map,
			Long informType,Date starttimes,Date endtimes) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusGreaterThanOrEqualTo(
							(Long) map.get("docstatus"));// 查看正常的，因此他的状态是大于等于0的
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");// 文档状态 docstatus的值
			if (searchModal != null) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo((Long.parseLong(searchModal
							.toString())));
				}
			}
			//时间段   
			if(starttimes!=null && endtimes!=null){
			    criteria.andCrtimeBetween(starttimes,endtimes);	
		
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			nCount = irpChnl_Doc_LinkDAO
					.selectCountBySiteIdAndDocStatus(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,
			Long informType, Integer siteType) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			List<Long> sites = irpSiteService.findSiteIds(siteType);
			if (sites != null && sites.size() > 0) {
				criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {// /閫掑綊寰楀埌浠栦笅闈㈡墍鏈夌殑鏍忕洰id
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			// 鏍规嵁鍚嶇О
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle.toString() + "%");
			}
			// 鏍规嵁鐘舵�
			Object searchModal = map.get("searchModal");
			if (searchModal != null && searchModal.toString().length()>0) {
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			nCount = irpChnl_Doc_LinkDAO
					.selectCountByChannelidAndDocStatus(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int selectCountByChannelidAndDocStatus(HashMap<String, Object> map,
			Long informType, Integer siteType,Date starttimes,Date endtimes) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			List<Long> sites = irpSiteService.findSiteIds(siteType);
			if (sites != null && sites.size() > 0) {
				criteria.andSiteidIn(sites);// 企业站点(不等于私人站点的就是企业站点)
			}
			//时间段   
			if(starttimes!=null && endtimes!=null){
			    criteria.andCrtimeBetween(starttimes,endtimes);	
		
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map
						.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			if (map.get("channelid") != null) {// /閫掑綊寰楀埌浠栦笅闈㈡墍鏈夌殑鏍忕洰id
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("docstatus") != null) {
				criteria.andDocstatusGreaterThanOrEqualTo((Long) map
						.get("docstatus"));
			}
			// 鏍规嵁鍚嶇О
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle.toString() + "%");
			}
			// 鏍规嵁鐘舵�
			Object searchModal = map.get("searchModal");
			if (searchModal != null && searchModal.toString().length()>0) {
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			/**
			 * 查询的知识docid范围设置
			 */
			if (map.get("docidsList") != null) {
				List<Long> docidsList = (List<Long>) map.get("docidsList");
				if (docidsList != null && docidsList.size() > 0) {
					criteria.andDocidIn(docidsList);
				}
			}
			nCount = irpChnl_Doc_LinkDAO
					.selectCountByChannelidAndDocStatus(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	// 查询栏目下文档数量
	@Override
	public int clientSelectChnlDocCount(String _sSearchWord,
			String _sSearchType, Long informType, Integer siteType,
			Long docStatus, Integer isFengMian, List<Long> _arrChannelIds,
			String sOrderByClause) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			if ("doctitle".equals(_sSearchType)) {
				criteria.andDoctitleLike("%" + _sSearchWord + "%");
			} else if ("cruser".equals(_sSearchType)) {
				criteria.andCruserLike("%" + _sSearchWord + "%");
			}
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));

			if (docStatus != null
					&& docStatus.intValue() == IrpDocument.PUBLISH_STATUS) {// 发布状态知识
				criteria.andDocstatusEqualTo(docStatus);
			}
			if (_arrChannelIds != null) {
				// 处理Oracle在执行in时超过1000个报错
				if (_arrChannelIds.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < _arrChannelIds.size(); i++) {
						tempChannelids.add(_arrChannelIds.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == _arrChannelIds.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(_arrChannelIds);
				}
			}
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			example.setOrderByClause(sOrderByClause);
			nCount = irpChnl_Doc_LinkDAO
					.selectCountByChannelidAndDocStatus(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpChnlDocLink> clientSelectDocChnl(String _sSearchWord,
			String _sSearchType, Long informType, Integer siteType,
			Long docStatus, Integer isFengMian, List<Long> _arrChannelIds,
			String sOrderByClause, PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			if ("doctitle".equals(_sSearchType)) {
				criteria.andDoctitleLike("%" + _sSearchWord + "%");
			} else if ("cruser".equals(_sSearchType)) {
				criteria.andCruserLike("%" + _sSearchWord + "%");
			}
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));

			if (docStatus != null
					&& docStatus.intValue() == IrpDocument.PUBLISH_STATUS) {// 发布状态知识
				criteria.andDocstatusEqualTo(docStatus);
			}
			if (_arrChannelIds != null) {
				// 处理Oracle在执行in时超过1000个报错
				if (_arrChannelIds.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < _arrChannelIds.size(); i++) {
						tempChannelids.add(_arrChannelIds.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == _arrChannelIds.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(_arrChannelIds);
				}
			}
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			example.setOrderByClause(sOrderByClause);
			chnlDocLinks = irpChnl_Doc_LinkDAO.chan_AllDocLink(pageUtil,
					example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	// 查询站点下文档数量回收站
	@Override
	public int selectCountBySiteIdAndDocStatusGC(HashMap<String, Object> map,
			Long informType) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidGreaterThan(new Long(IrpChannel.IS_DOCSTATIUS))// 正常站点
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 企业站点(不等于私人站点的就是企业站点)
			criteria.andSiteidEqualTo((Long) map.get("siteid"))
					.andDocstatusLessThan((Long) map.get("docstatus"));

			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null && searchModal.toString().length()>0) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			nCount = irpChnl_Doc_LinkDAO
					.selectCountBySiteIdAndDocStatusGC(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	// 查询栏目下文档数量回收站
	@Override
	public int selectCountByChannelidAndDocStatusGC(
			HashMap<String, Object> map, Long informType) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidEqualTo((Long) map.get("channelid"))
					.andDocstatusLessThan((Long) map.get("docstatus"));

			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 根据状态
			Object searchModal = map.get("searchModal");
			if (searchModal != null && searchModal.toString().length()>0) {// 查询处于该状态下的文档
				if (searchModal.toString().length() > 0) {
					criteria.andDocstatusEqualTo(Long.parseLong(searchModal
							.toString()));
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			nCount = irpChnl_Doc_LinkDAO
					.selectCountByChannelidAndDocStatusGC(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpChnlDocLink> getPersonDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, Long informType) {
		/**
		 * 判断当前登录这和穿过阿里的personid是不是相等， 如果相等，则是查看自己的所有文档，因此可以查看所有的文档，
		 * 如果不等，则是在查看别人的所有文档，因此只能够看对方的文档状态为10，也就是已经发布的 文档
		 */
		IrpUser irpUser = LoginUtil.getLoginUser();
		List<IrpChnlDocLink> doclinks = null;

		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Object sOrderByClause = map.get("sOrderByClause");
			if (sOrderByClause == null) {
				sOrderByClause = "docid desc";
			}
			Criteria criteria = example.createCriteria();
			example.setOrderByClause(sOrderByClause.toString());

			/*
			 * List<Long> allChannelidsList = getPersonChannelid( (Long)
			 * map.get("personid"), (Long) map.get("channelid")); if
			 * (allChannelidsList != null && allChannelidsList.size() > 0) {
			 * criteria.andChannelidIn(allChannelidsList); }
			 */
			criteria.andChannelidEqualTo((Long) map.get("channelid"));
			criteria.andDocstatusGreaterThanOrEqualTo((Long) map
					.get("docstatus"));

			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			doclinks = irpChnl_Doc_LinkDAO.getPersonLink(pageUtil, example);
			if (doclinks != null && doclinks.size() > 0) {
				for (int i = 0; i < doclinks.size(); i++) {
					IrpChnlDocLink chnlDocLink = doclinks.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(chnlDocLink.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						doclinks.get(i).setIrpDocumentCollection(
								collections.get(0));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doclinks;
	}

	@Override
	public List<IrpChnlDocLink> clientShowDoc(Map<String, Object> map,
			Long informType, Integer amount) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		int aDataCount = 0;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			/**
			 * 传过来的map里面所带的栏目集合已经是当前登陆者有权限的栏目限制了
			 * 知识列表去掉特殊的栏目状态（每月之星，公告，个人栏目，知识地图）
			 */
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));

			criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
					// 状态为10的已经发布
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
					// 不是个人站点
					.andChannelidGreaterThan(0L);// 栏目不是为负的
			Date thatdate = (Date) map.get("thatday");
			if (thatdate != null) {
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatdate);
			}
			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria().andChannelidIn(
											tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			aDataCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			/**
			 * 显示最新或者最热知识前多少条显示
			 */
			PageUtil pageUtil = new PageUtil(1, amount, aDataCount);// 第一页，显示十条
			if (map.get("sOrderByClause") != null) {
				chnlDocLinkExample.setOrderByClause(map.get("sOrderByClause")
						+ "");
			}
			
			chnlDocLinks = irpChnl_Doc_LinkDAO.site_AllDocLinkGC(pageUtil,
					chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}
	@Override
	public List<IrpChnlDocLink> clientSearchDoc(Map<String, Object> map,
			Long informType,Integer pageNum, Integer amount,String searchWord) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		int aDataCount = 0;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			if(searchWord!=null){
				criteria.andDoctitleLike("%"+searchWord+"%");
			}
			/**
			 * 传过来的map里面所带的栏目集合已经是当前登陆者有权限的栏目限制了
			 * 知识列表去掉特殊的栏目状态（每月之星，公告，个人栏目，知识地图）
			 */
			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));

			criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
					// 状态为10的已经发布
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
					// 不是个人站点
					.andChannelidGreaterThan(0L);// 栏目不是为负的
			Date thatdate = (Date) map.get("thatday");
			if (thatdate != null) {
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatdate);
			}
			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelidsList = (List<Long>) map.get("channelidsList");
				if (channelidsList.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelidsList.size(); i++) {
						tempChannelids.add(channelidsList.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelidsList.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria().andChannelidIn(
											tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelidsList);
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			aDataCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			/**
			 * 显示最新或者最热知识前多少条显示
			 */
			PageUtil pageUtil = new PageUtil(pageNum, amount, aDataCount);// 第一页，显示十条
			if (map.get("sOrderByClause") != null) {
				chnlDocLinkExample.setOrderByClause(map.get("sOrderByClause")
						+ "");
			}
			
			chnlDocLinks = irpChnl_Doc_LinkDAO.site_AllDocLinkGC(pageUtil,
					chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public List<IrpChnlDocLink> clientChannelShowDoc(Long _channelid,
			Long informType) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			String sOrderByClause = "docpubtime desc";// 按照发布时间降序,显示前
			Criteria criteria = chnlDocLinkExample.createCriteria();

			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));

			RightUtil rightUtil = new RightUtil();
			IrpUser irpUser = LoginUtil.getLoginUser();
			if (!irpUser.isAdministrator()) {// 不是管理员就加权限

				Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if (nOperId != null && nOperId > 0L) {
					String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
							nOperId);// 得到是否有权限语句
					criteria.extSQL(sSql);
				}
			}

			criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)// 状态为10的已经发布
					.andChannelidEqualTo(_channelid);

			chnlDocLinkExample.setOrderByClause(sOrderByClause);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			chnlDocLinks = irpChnl_Doc_LinkDAO
					.selectByExample(chnlDocLinkExample);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public int myDocumentCount(Long informType) {
		int nCount = 0;
		try {
			IrpUser irpUser = LoginUtil.getLoginUser();
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			criteria.andCruseridEqualTo(irpUser.getUserid())
					.andChannelidGreaterThan(new Long(IrpChannel.IS_DOCSTATIUS));

			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			nCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpChnlDocLink> myAllDocument(Long informType) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		try {
			IrpUser irpUser = LoginUtil.getLoginUser();
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();
			criteria.andCruseridEqualTo(irpUser.getUserid())
					.andChannelidGreaterThanOrEqualTo(
							new Long(IrpChannel.IS_DOCSTATIUS));

			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			chnlDocLinks = irpChnl_Doc_LinkDAO
					.selectByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	/**
	 * 这显示已经发布的文章，因此，他的状态一定是=10
	 */
	@Override
	public List<IrpChnlDocLink> selectLikeDocumentToList(Long thisDocid,
			HashMap<String, Object> map, Long informType) {
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		// 状态为10的已经发布(包含个人写的，和企业走流程之后发布的 )
		Criteria criteria = chnlDocLinkExample.createCriteria();

		criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
		/**
		 * 加权限
		 */
		RightUtil rightUtil = new RightUtil();
		IrpUser irpUser = LoginUtil.getLoginUser();
		if (!irpUser.isAdministrator()) {// 不是管理员就加权限

			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
			if (nOperId != null && nOperId > 0L && nOperDocId != null
					&& nOperDocId != 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
						nOperId);// 得到是否有权限语句
				String sDocSql = rightUtil.getRightExistsSQL(new IrpChannel(),
						nOperDocId);// 得到是否有权限语句
				criteria.extSQL(sSql).extSQL(sDocSql);
			}
		}
		/**
		 * 以上加权限
		 */

		criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
				.andChannelidGreaterThanOrEqualTo(
						new Long(IrpChannel.IS_DOCSTATIUS))
				.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 查询企业站点里面的知识(非私人站点)
		List<IrpChnlDocLink> chnlDocLinks = null;
		int aDataCount = 0;
		try {
			int amount = Integer.parseInt(map.get("amount").toString());// 显示数量

			String sOrderByClause = map.get("sOrderByClause").toString();// 按照指定排序
			chnlDocLinkExample.setOrderByClause(sOrderByClause);

			Long cruserid = (Long) map.get("cruserid");
			if (cruserid != null) {
				criteria.andCruseridEqualTo(cruserid);
			}
			criteria.andChannelidGreaterThan(0L);// 正常栏目
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			// 判断所给参数docid是否有值，有就不查询他自己
			if (thisDocid != null && thisDocid.longValue() != 0L) {
				criteria.andDocidNotEqualTo(thisDocid);
			}
			aDataCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			PageUtil pageUtil = new PageUtil(1, amount, aDataCount);// 第一页，显示amount条
			chnlDocLinks = irpChnl_Doc_LinkDAO.site_AllDocLinkGC(pageUtil,
					chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public List<IrpUser> selectPersonLike(HashMap<String, Object> map,
			Long informType) {
		List<IrpUser> irpUsers = new ArrayList<IrpUser>();
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		// 状态为10的已经发布(包含个人写的，和企业走流程之后发布的)
		Criteria criteria = chnlDocLinkExample.createCriteria();
		criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
				.andChannelidGreaterThanOrEqualTo(
						new Long(IrpChannel.IS_DOCSTATIUS));
		List<IrpChnlDocLink> chnlDocLinks = null;
		int aDataCount = 0;
		try {
			int amount = Integer.parseInt(map.get("amount").toString());// 显示数量
			String sOrderByClause = map.get("sOrderByClause").toString();// 按照指定排序

			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			aDataCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			PageUtil pageUtil = new PageUtil(1, amount, aDataCount);// 第一页，显示amount条
			chnlDocLinkExample.setOrderByClause(sOrderByClause);
			List<Map<String, Object>> cruseridsList = irpChnl_Doc_LinkDAO
					.getCruSeridsByExample(chnlDocLinkExample);
			if (cruseridsList != null && cruseridsList.size() > 0) {
				for (int i = 0; i < cruseridsList.size(); i++) {
					// 查询人名称
					Map<String, Object> usermap = cruseridsList.get(i);
					IrpUser irpUser = new IrpUser();
					irpUser.setCruserid(new Long(usermap.get("CRUSERID")
							.toString()));
					irpUser.setCruser(usermap.get("CRUSER").toString());
					irpUsers.add(irpUser);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUsers;
	}

	/**
	 * 这是前台的查询
	 */
	@Override
	public List<IrpChnlDocLink> clientChanAllDocLink(PageUtil pageUtil,
			HashMap<String, Object> map, String _sOrderBy, Long informType) {
		List<IrpChnlDocLink> list = null;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
			Criteria criteria = example.createCriteria();
			criteria.andDocstatusGreaterThanOrEqualTo((Long) map
					.get("docstatus"));
			criteria.andSiteidEqualTo(IrpSite.PRIVATE_SITE_ID);
			criteria.andCruseridEqualTo(irpUser.getUserid());// 根据用户去查询当前用户的所有文档
			// 根据名称
			Object searchDocTitle = map.get("searchDocTitle");
			if (searchDocTitle != null) {
				criteria.andDoctitleLike("%" + searchDocTitle + "%");
			}
			// 排序
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "docorder desc";
			}
			example.setOrderByClause(_sOrderBy);
			Object channelid = map.get("channelid");
			if (channelid != null && (Long) channelid != 0L) {
				criteria.andChannelidEqualTo((Long) channelid);
			}
			Object channelidsList = map.get("channelidsList");
			if (channelidsList != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelIds = (List<Long>) channelidsList;
				if (channelIds.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelIds.size(); i++) {
						tempChannelids.add(channelIds.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelIds.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelIds);
				}
			}
			// List<Long>
			// allChannelidList=getPersonChannelid((Long)map.get("personid"),(Long)map.get("channelid"));
			// criteria.andChannelidIn(allChannelidList);
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.chan_AllDocLink(pageUtil, example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					IrpChnlDocLink chnlDocLink = list.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(chnlDocLink.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						list.get(i)
								.setIrpDocumentCollection(collections.get(0));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int clientSelectOneChannelCountByChannelidAndDocstatus(
			HashMap<String, Object> map, Long informType) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
			Criteria criteria = example.createCriteria();
			List<Long> channelidsList = getPersonChannelid(
					(Long) map.get("personid"), (Long) map.get("channelid"));
			// 处理Oracle在执行in时超过1000个报错
			if (channelidsList != null && channelidsList.size() > 1000) {
				List<Long> tempChannelids = new ArrayList<Long>();
				for (int i = 0; i < channelidsList.size(); i++) {
					tempChannelids.add(channelidsList.get(i));
					if (tempChannelids.size() == 1000
							|| (i + 1) == channelidsList.size()) {
						example.or(example.createCriteria().andChannelidIn(
								tempChannelids));
						tempChannelids = new ArrayList<Long>();
					}
				}
			} else {
				criteria.andChannelidIn(channelidsList);
			}
			criteria.andDocstatusGreaterThanOrEqualTo((Long) map
					.get("docstatus"));
			if (!irpUser.isAdministrator()) {
				criteria.andCruseridEqualTo(irpUser.getUserid());// 根据用户去查询当前用户的所有栏目
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			nCount = irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	// 查询栏目的id
	public List<Long> getPersonChannelid(Long nPersonId, Long nChannelid) {
		/**
		 * 如果personid==null，就说明查询的应该是自己的栏目下的文档数量
		 * channelid=0，就查询当前用户id==chnlname的栏目，然后查出以这个channelid为perentid的所有栏目id
		 * channelid！=0 直接查询这个栏目下的文档数量 如果personid!=null 说明查询的应该是关注对象的某个栏目下的文档数量
		 * channelid就不可能为null 所有，personid==chnldesc
		 * ，查询这个栏目，如果存在，就代表他是一级栏目，然后将这个channelid为perentid查出s 所有子栏目id
		 * 放入集合，并将当前的channelid放入集合
		 */
		List<Long> allChannelidsList = new ArrayList<Long>();
		try {
			if (nPersonId != null && nPersonId != 0) {// 这个里面channelid肯定不可能等于o
				// 第一次查询
				IrpChannelExample channelExample = new IrpChannelExample();
				channelExample.createCriteria()
						.andChnlnameEqualTo(nPersonId.toString())
						.andChannelidEqualTo(nChannelid)
						.andSiteidEqualTo(IrpSite.PRIVATE_SITE_ID);
				List<IrpChannel> irpChannels = irpChannelDAO
						.selectByExample(channelExample);
				if (irpChannels != null && irpChannels.size() > 0) {// 证明他是一级栏目
					IrpChannel irpChannel = irpChannels.get(0);
					allChannelidsList.add(irpChannel.getChannelid());// 直接放入
					// 第二次查询
					IrpChannelExample channelExample2 = new IrpChannelExample();
					channelExample2.createCriteria().andParentidEqualTo(
							irpChannel.getChannelid());
					List<IrpChannel> irpChannelsList = irpChannelDAO
							.selectByExample(channelExample2);
					if (irpChannelsList != null && irpChannelsList.size() > 0) {
						for (int j = 0; j < irpChannelsList.size(); j++) {
							IrpChannel channel = irpChannelsList.get(j);
							allChannelidsList.add(channel.getChannelid());
						}
					}
				} else {// 如果没有查到，就是二级栏目，直接放入集合
					allChannelidsList.add(nChannelid);
				}
			} else {
				if (nChannelid != 0) {
					allChannelidsList.add(nChannelid);
				} else {
					IrpChannelExample channelExample = new IrpChannelExample();
					channelExample
							.createCriteria()
							.andChnlnameEqualTo(
									LoginUtil.getLoginUser().getUserid()
											.toString())// 栏目名称等于登录用户id
							.andSiteidEqualTo(IrpSite.PRIVATE_SITE_ID);// 个人站点下的
					List<IrpChannel> oneChannels = irpChannelDAO
							.selectByExample(channelExample);
					if (oneChannels != null && oneChannels.size() > 0) {
						IrpChannel irpChannel = oneChannels.get(0);// 第一个查询
						// 装入集合
						allChannelidsList.add(irpChannel.getChannelid());
						IrpChannelExample channelExample2 = new IrpChannelExample();
						channelExample2.createCriteria().andParentidEqualTo(
								irpChannel.getChannelid());
						List<IrpChannel> chIrpChannels = irpChannelDAO
								.selectByExample(channelExample2);
						if (chIrpChannels != null && chIrpChannels.size() > 0) {
							for (int i = 0; i < chIrpChannels.size(); i++) {
								allChannelidsList.add(chIrpChannels.get(i)
										.getChannelid());// 将它加入
							}
						}
					} else {
						allChannelidsList.add(nChannelid);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allChannelidsList;
	}

	/**
	 * 根据站点类型查询站点id集合
	 * 
	 * @return
	 */
	@Override
	public List<Long> findSiteIds(Integer siteType) {
		List<Long> siteIds = new ArrayList<Long>();
		List<IrpSite> sites = irpSiteService
				.findSitesBySiteType(IrpSite.SITE_TYPE_PRIVATE);
		if (sites != null && sites.size() > 0) {
			for (IrpSite irpSite : sites) {
				siteIds.add(irpSite.getSiteid());
			}
		}
		return siteIds;
	}

	@Override
	public int noDiGuiChannelAllDocCount(Map<String, Object> map,
			Long informType) {
		int nCount = 0;
		try {

			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();

			Object channelid = map.get("channelid");
			if (channelid != null && (Long) channelid != 0L) {
				criteria.andChannelidEqualTo((Long) channelid);
			}
			Object channelidsList = map.get("channelidsList");
			if (channelidsList != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) channelidsList;
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria().andChannelidIn(
											tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
				/**
				 * 附加知识是否被举报状态
				 */
				if (informType != null) {
					criteria.andInformtypeEqualTo(informType);
				}
			}
			nCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpChnlDocLink> selectDocumentByMap(Map<String, Object> map,
			Long informType) {
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		List<IrpChnlDocLink> list = null;
		try {
			Criteria criteria = example.createCriteria();

			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
			criteria.andDocstatusEqualTo((Long) map.get("docstatus"))
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 企业站点(不等于私人站点的就是企业站点)

			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) map.get("channelidsList");
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
			}
			example.setOrderByClause(map.get("sOrderByClause").toString());
			/**
			 * 查询配置，查询今天到今天减去配置之前的文章
			 */
			IrpConfigExample configExample = new IrpConfigExample();
			configExample.createCriteria().andCkeyEqualTo("");
			int day = SysConfigUtil
					.getSysConfigNumValue("SELECT_DOCUMENT_TIME_AROUND");
			if (day > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(calendar.DAY_OF_YEAR, -day);
				Date thatday = calendar.getTime();// 这个是之前的日期，
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatday);
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			list = irpChnl_Doc_LinkDAO.typicalDocLink(example, null);
			if (list == null || list.size() == 0) {// 说明按照加精数量查询没有对象，此时就按照点击量最多的倒叙显示
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, -day);
				Date thatday = calendar.getTime();// 这个是之前的日期，
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("sOrderByClause", " hitscount desc ");
				hashmap.put("thatday", thatday);
				hashmap.put("channelid", map.get("channelid"));
				hashmap.put("channelidsList", map.get("channelidsList"));
				list = clientShowDoc(hashmap, informType,
						SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));// 查询点击量排行前十的显示
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int typicalDocLinkAmount(Map<String, Object> map, Long informType,
			Integer dateAmount) {
		int aCount = 0;
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		try {
			Criteria criteria = example.createCriteria();

			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
			criteria.andDocstatusEqualTo((Long) map.get("docstatus"))
					.andChannelidGreaterThan(new Long(IrpChannel.IS_DOCSTATIUS))// 正常站点
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 企业站点(不等于私人站点的就是企业站点)

			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) map.get("channelidsList");
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
			}
			example.setOrderByClause(map.get("sOrderByClause").toString());
			/**
			 * 查询配置，查询今天到今天减去配置之前的文章
			 */
			if (dateAmount != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(calendar.DAY_OF_YEAR, -dateAmount);
				Date thatday = calendar.getTime();// 这个是之前的日期，
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatday);
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			aCount = irpChnl_Doc_LinkDAO.typicalDocLinkAmount(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aCount;
	}
	
	@Override
	public List<IrpChnlDocLink> findEssenceDocs(Long _nChannelId, int _nEssenceType, PageUtil pageUtil) {
		List<IrpChnlDocLink> list = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		//根据TYPE添加指定类型的条件。
		if(_nEssenceType==1){
			mParam.put("essInformtype", IrpInform.INFORMJIAJINGDOC);
			int amount = SysConfigUtil.getSysConfigNumValue("ESSENCE_DAY_NUM");
			mParam.put("beginDate", DateUtils.dateTimeAdd(new Date(), Calendar.DATE, amount));
			mParam.put("endDate", DateUtils.getNOWTime());
		} else if (_nEssenceType==2) {
			mParam.put("essInformtype", IrpInform.INFORMJIAJINGDOC);
		} else if (_nEssenceType==3) {
			//空着为了获得点击排序
		} else {
			return list;
		}
		//添加栏目ID条件，栏目参数为空则不加栏目权限
		if(_nChannelId!=null && _nChannelId.longValue()>0){
			mParam.put("channelid", _nChannelId);
		}
		//添加必要条件
		mParam.put("docstatus", IrpDocument.PUBLISH_STATUS);
		mParam.put("doctype", IrpDocument.DOCTYPE_DOCUMENT);
		mParam.put("siteid", IrpSite.PUBLIC_SITE_ID);
		mParam.put("notinform", IrpDocument.DOCUMENT_NOT_INFORM);
		mParam.put("chnlIdExt", otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
		//添加权限
		RightUtil rightUtil = new RightUtil();
		if (!LoginUtil.getLoginUser().isAdministrator()) {// 不是管理员就加权限
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
			if (nOperId != null && nOperId > 0L && nOperDocId != null
					&& nOperDocId != 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
						nOperId);// 得到是否有权限语句
				String sDocSql = rightUtil.getRightExistsSQL(
						new IrpChannel(), nOperDocId);// 得到是否有权限语句
				mParam.put("chnlRightExt", sSql);
				mParam.put("docRightExt", sDocSql);
			}
		}
		try {
			list = irpChnl_Doc_LinkDAO.selectEssenceDocs(mParam, pageUtil);
			if(list==null || list.isEmpty()){
				return findEssenceDocs(_nChannelId, (++_nEssenceType), pageUtil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpChnlDocLink> typicalDocLink(Map<String, Object> map,
			Long informType, Integer dateAmount, PageUtil pageUtil) {
		List<IrpChnlDocLink> list = null;
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		try {
			Criteria criteria = example.createCriteria();

			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
			criteria.andDocstatusEqualTo((Long) map.get("docstatus"))
					.andChannelidGreaterThan(new Long(IrpChannel.IS_DOCSTATIUS))// 正常站点
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 企业站点(不等于私人站点的就是企业站点)

			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) map.get("channelidsList");
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
			}
			example.setOrderByClause(map.get("sOrderByClause").toString());
			example.setOrderByClause("crtime desc ");
			/**
			 * 查询配置，查询今天到今天减去配置之前的文章
			 */
			if (dateAmount != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(calendar.DAY_OF_YEAR, -dateAmount);
				Date thatday = calendar.getTime();// 这个是之前的日期，
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatday);
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			list = irpChnl_Doc_LinkDAO.typicalDocLink(example, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询一篇知识
	 */
	@Override
	public IrpChnlDocLink oneJiaJingDocumentByMap(Map<String, Object> map,
			Long informType) {

		IrpChnlDocLink link = null;
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		try {
			Criteria criteria = example.createCriteria();

			criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
			criteria.andDocstatusEqualTo((Long) map.get("docstatus"))
					.andChannelidGreaterThan(new Long(IrpChannel.IS_DOCSTATIUS))// 正常站点
					.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID);// 企业站点(不等于私人站点的就是企业站点)

			if (map.get("channelid") != null
					&& (Long) map.get("channelid") > 0L) {
				criteria.andChannelidEqualTo((Long) map.get("channelid"));
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) map.get("channelidsList");
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							example.or(example.createCriteria().andChannelidIn(
									tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
			}
			example.setOrderByClause(map.get("sOrderByClause").toString());
			example.setOrderByClause("crtime desc ");
			/**
			 * 查询配置，查询今天到今天减去配置之前的文章
			 */
			int day = SysConfigUtil
					.getSysConfigNumValue("SELECT_DOCUMENT_TIME_AROUND");
			if (day > 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(calendar.DAY_OF_YEAR, -day);
				Date thatday = calendar.getTime();// 这个是之前的日期，
				criteria.andDocpubtimeGreaterThanOrEqualTo(thatday);
			}
			List<IrpChnlDocLink> list = null;
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			list = irpChnl_Doc_LinkDAO.typicalDocLink(example, null);
			if (list == null || list.size() == 0) {// 说明按照加精数量查询没有对象，此时就按照点击量最多的倒叙显示
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, -day);
				// Date thatday=calendar.getTime();//这个是之前的日期，
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("sOrderByClause", " hitscount desc ");
				// hashmap.put("thatday",thatday);
				hashmap.put("channelid", map.get("channelid"));
				hashmap.put("channelidsList", map.get("channelidsList"));
				list = clientShowDoc(hashmap, informType,
						SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"));// 查询点击量排行前十的显示
			}
			if (list != null && list.size() > 0) {
				link = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}

	@Override
	public List<IrpChnlDocLink> selectMonthDocument(Long channelid) {
		// TODO Auto-generated method stub
		List<IrpChnlDocLink> irpchnldocs = new ArrayList<IrpChnlDocLink>();
		try {
			// irpchnldocs = irpChnl_Doc_LinkDAO.monthDocument(_chnlType);
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();

			criteria.andChannelidEqualTo(channelid);
			criteria.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS);

			// 判断他对这个知识和栏目的权限
			IrpUser irpUser = LoginUtil.getLoginUser();
			if (!irpUser.isAdministrator()) {
				RightUtil rightUtil = new RightUtil();
				Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				Long nOperDocId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_LIST");// 查看栏目下文的列表权限
				if (nOperId != null && nOperId > 0L && nOperDocId != null
						&& nOperDocId > 0) {
					String sDocSql = rightUtil.getRightExistsSQL(
							new IrpChannel(), nOperDocId);
					String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
							nOperId);// 得到是否有权限语句
					criteria.extSQL(sSql).extSQL(sDocSql);
				}
			}
			chnlDocLinkExample.setOrderByClause(" crtime desc ");
			List<IrpChnlDocLink> tempList = irpChnl_Doc_LinkDAO
					.chan_AllDocLink(null, chnlDocLinkExample);
			if (tempList != null && tempList.size() > 0) {
				irpchnldocs.add(tempList.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpchnldocs;
	}

	@Override
	public int sumHit(List<Long> _arrChannelids, Long informType) {
		// TODO Auto-generated method stub
		int nCount = 0;
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		Criteria criteria = chnlDocLinkExample.createCriteria();
		criteria.andChannelidIn(_arrChannelids);
		try {
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			nCount = irpChnl_Doc_LinkDAO.sumByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<Long> docIds(List<Long> _arrChannelids, Long informType) {
		// TODO Auto-generated method stub
		List<Long> list = new ArrayList<Long>();
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		Criteria criteria = chnlDocLinkExample.createCriteria();
		criteria.andChannelidIn(_arrChannelids);
		try {
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}

			List<IrpChnlDocLink> channels = irpChnl_Doc_LinkDAO
					.selectByExample(chnlDocLinkExample);
			if (channels != null && channels.size() > 0) {
				for (IrpChnlDocLink irpChnlDocLink : channels) {
					list.add(irpChnlDocLink.getDocid());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询当前的栏目是否可以在前台使用
	 * 
	 * @param _channelid
	 * @param channelidList
	 * @return
	 */
	public List<Long> allparentidList(Long _channelid, List<Long> channelidList) {
		try {
			/**
			 * 添加权限
			 */
			IrpChannelExample channelExample = new IrpChannelExample();
			IrpUser irpUser = LoginUtil.getLoginUser();
			com.tfs.irp.channel.entity.IrpChannelExample.Criteria criteria = channelExample
					.createCriteria();
			if (!irpUser.isAdministrator()) {
				RightUtil rightUtil = new RightUtil();
				Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				Long nOperDocId = rightUtil
						.findOperTypeIdByKey("DOCUMENT_LIST");// 查看栏目下文的列表权限
				if (nOperId != null && nOperId > 0L && nOperDocId != null
						&& nOperDocId > 0) {
					String sDocSql = rightUtil.getRightExistsSQL(
							new IrpChannel(), nOperDocId);
					String sSql = rightUtil.getRightExistsSQL(new IrpChannel(),
							nOperId);// 得到是否有权限语句
					criteria.extSQL(sSql).extSQL(sDocSql);
				}
			}
			/***
			 * 添加权限
			 */
			criteria.andChannelidEqualTo(_channelid);
			List<IrpChannel> channels = irpChannelDAO
					.selectByExample(channelExample);// 查询他自己，或者父类
			if (channels != null && channels.size() > 0) {
				channelidList.add(_channelid);
				if (channels.get(0).getParentid() == 0L) {// 如果他的父类的parentid=0，说明是一级栏目终止递归
					return channelidList;
				}
				_channelid = channels.get(0).getParentid();// 查询他的父栏目
				allparentidList(_channelid, channelidList);
			} else {
				/**
				 * 如果在权限下，没有查到对象，代表在这个子孙链中，祖宗-爷爷-爸爸-我自己
				 * 有一个中断了，就代表他自己不可以查看，返回null对象
				 */
				channelidList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channelidList;
	}

	// 排列组合
	public List<String> getCombinations(List<String> list) {
		List<String> result = new ArrayList<String>();
		long n = (long) Math.pow(2, list.size());
		List<String> combine;
		for (long l = 0L; l < n; l++) {
			combine = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				if ((l >>> i & 1) == 1)
					combine.add(list.get(i));
			}
			// 转换成字符串
			String str = "";
			for (int i = 0; i < combine.size(); i++) {
				str += combine.get(i) + ",";
			}
			result.add(str);
		}
		return result;
	}

	@Override
	public IrpDocument findDocumentByDocId(Long _docId) {
		return irpDocumentService.findDocumentByDocId(_docId);
	}

	@Override
	public List<Long> allDocids(Map<String, Object> map, Long informType) {
		List<Long> docids = null;
		try {
			IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
			Criteria criteria = chnlDocLinkExample.createCriteria();

			Object channelid = map.get("channelid");
			if (channelid != null && (Long) channelid != 0L) {
				criteria.andChannelidEqualTo((Long) channelid);
			}
			if (map.get("channelidsList") != null) {
				// 处理Oracle在执行in时超过1000个报错
				List<Long> channelids = (List<Long>) map.get("channelidsList");
				if (channelids.size() > 1000) {
					List<Long> tempChannelids = new ArrayList<Long>();
					for (int i = 0; i < channelids.size(); i++) {
						tempChannelids.add(channelids.get(i));
						if (tempChannelids.size() == 1000
								|| (i + 1) == channelids.size()) {
							chnlDocLinkExample.or(chnlDocLinkExample
									.createCriteria().andChannelidIn(
											tempChannelids));
							tempChannelids = new ArrayList<Long>();
						}
					}
				} else {
					criteria.andChannelidIn(channelids);
				}
			}
			/**
			 * 附加知识是否被举报状态
			 */
			if (informType != null) {
				criteria.andInformtypeEqualTo(informType);
			}
			docids = irpChnl_Doc_LinkDAO
					.findDocidsByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docids;
	}

	@Override
	public List<IrpChnlDocLink> alltougaodocument(PageUtil pageUtil,
			List<Long> oldDocids, String sOrderByClause, Long informType) {
		List<IrpChnlDocLink> list = null;
		try {
			if (oldDocids != null && oldDocids.size() > 0) {
				IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
				Criteria criteria = chnlDocLinkExample.createCriteria();
				criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
				criteria.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
						// 不是个人站点
						.andChannelidGreaterThan(
								new Long(IrpChannel.IS_DOCSTATIUS))// 栏目不是为负的
						.andDocstatusGreaterThan(0L);// 正常状态的投稿都可以查看

				criteria.andDockindIn(oldDocids);
				if (sOrderByClause != null && !sOrderByClause.equals("")) {
					chnlDocLinkExample.setOrderByClause(sOrderByClause);
				}
				/**
				 * 附加知识是否被举报状态
				 */
				if (informType != null) {
					criteria.andInformtypeEqualTo(informType);
				}
				list = irpChnl_Doc_LinkDAO.chan_AllDocLink(pageUtil,
						chnlDocLinkExample);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int alltougaodocumentCount(List<Long> oldDocids, Long informType) {
		int aCount = 0;
		try {
			if (oldDocids != null && oldDocids.size() > 0) {
				IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
				Criteria criteria = chnlDocLinkExample.createCriteria();
				criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
				/**
				 * 加权限
				 */
				RightUtil rightUtil = new RightUtil();
				IrpUser irpUser = LoginUtil.getLoginUser();
				if (!irpUser.isAdministrator()) {// 不是管理员就加权限

					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						criteria.extSQL(sSql).extSQL(sDocSql);
					}
				}
				/**
				 * 以上加权限
				 */
				criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
						// 状态为10的已经发布
						.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
						// 不是个人站点
						.andChannelidGreaterThan(
								new Long(IrpChannel.IS_DOCSTATIUS));// 栏目不是为负的

				criteria.andDockindIn(oldDocids);
				/**
				 * 附加知识是否被举报状态
				 */
				if (informType != null) {
					criteria.andInformtypeEqualTo(informType);
				}
				aCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aCount;
	}
	@Override
	public int alltougaoCount(List<Long> oldDocids, Long informType,IrpUser irpuser) {
		int aCount = 0;
		try {
			if (oldDocids != null && oldDocids.size() > 0) {
				IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
				Criteria criteria = chnlDocLinkExample.createCriteria();
				criteria.extSQL(otherRightChannelExits(IrpChannel.CHANNEL_IS_PUBLISH));
				/**
				 * 加权限
				 */
				RightUtil rightUtil = new RightUtil();
				//IrpUser irpUser = LoginUtil.getLoginUser();
				if (!irpuser.isAdministrator()) {// 不是管理员就加权限
					
					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						criteria.extSQL(sSql).extSQL(sDocSql);
					}
				}
				/**
				 * 以上加权限
				 */
				criteria.andDocstatusEqualTo(IrpChnlDocLink.DOC_STATUS_PUBLISH)
				// 状态为10的已经发布
				.andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
				// 不是个人站点
				.andChannelidGreaterThan(
						new Long(IrpChannel.IS_DOCSTATIUS));// 栏目不是为负的
				
				criteria.andDockindIn(oldDocids);
				/**
				 * 附加知识是否被举报状态
				 */
				if (informType != null) {
					criteria.andInformtypeEqualTo(informType);
				}
				aCount = irpChnl_Doc_LinkDAO.countByExample(chnlDocLinkExample);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aCount;
	}

	@Override
	public List findJuBaoDoc(Integer sitetypeOne, Integer sitetypeTwo,
			String informKey, Integer informType, Integer informStatus,
			PageUtil pageUtil) {
		List lsit = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();

			String rightsql1 = "";
			String rightsql2 = "";
			if (sitetypeTwo != null
					&& sitetypeTwo.intValue() == IrpSite.SITE_TYPE_PUBLISH) {// 所属站点有企业站点
				RightUtil rightUtil = new RightUtil();
				IrpUser irpUser = LoginUtil.getLoginUser();
				if (!irpUser.isAdministrator()) {// 不是管理员就加权限
					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						String sql1 = " and " + sSql;
						String sql2 = " and " + sDocSql;
						rightsql1 = sql1 + sql2;
						rightsql2 = sql1 + sql2;
					}
				}
				if (sitetypeOne != null
						&& sitetypeOne.intValue() == IrpSite.SITE_TYPE_PRIVATE) {
					rightsql1 = "";
				}
			}
			map.put("rightsql1", rightsql1);
			map.put("rightsql2", rightsql2);
			map.put("informtype", informType);
			map.put("informstatus", informStatus);
			map.put("sitetype1", sitetypeOne);
			map.put("sitetype2", sitetypeTwo);
			map.put("expert", informKey);
			lsit = irpChnl_Doc_LinkDAO.findJuBaoDocument(map, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsit;
	}

	@Override
	public Integer findJuBaoDocCount(Integer sitetypeOne, Integer sitetypeTwo,
			String informKey, Integer informType, Integer infromStatus) {
		int count = 0;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String rightsql1 = "";
			String rightsql2 = "";
			if (sitetypeTwo != null
					&& sitetypeTwo.intValue() == IrpSite.SITE_TYPE_PUBLISH) {// 所属站点有企业站点
				RightUtil rightUtil = new RightUtil();
				IrpUser irpUser = LoginUtil.getLoginUser();
				if (!irpUser.isAdministrator()) {// 不是管理员就加权限
					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						String sql1 = " and " + sSql;
						String sql2 = " and " + sDocSql;
						rightsql1 = sql1 + sql2;
						rightsql2 = sql1 + sql2;
					}
				}
				if (sitetypeOne != null
						&& sitetypeOne.intValue() == IrpSite.SITE_TYPE_PRIVATE) {
					rightsql1 = "";
				}
			}
			map.put("rightsql1", rightsql1);
			map.put("rightsql2", rightsql2);
			map.put("informtype", informType);
			map.put("informstatus", infromStatus);
			map.put("sitetype1", sitetypeOne);
			map.put("sitetype2", sitetypeTwo);
			map.put("expert", informKey);
			count = irpChnl_Doc_LinkDAO.findJuBaoDocumentCount(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int passOrNotInformDocument(Long _docid, Long informType,
			Integer _informStatus) {

		int nCount = 0;
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
		try {
			List<IrpChnlDocLink> links = irpChnl_Doc_LinkDAO
					.selectByExample(chnlDocLinkExample);
			if (links != null && links.size() > 0) {
				IrpChnlDocLink link = links.get(0);
				link.setInformtype(informType);
				nCount = irpChnl_Doc_LinkDAO.updateByExampleSelective(link, chnlDocLinkExample);
				irpDocumentService.updateDocumentInformType(_docid, informType);
				if (nCount > 0) {// 如果知识的举报状态修改完成
					// 修改举报内容的状态
					if (_informStatus == IrpInform.INFORM_STATUS) {// 如果将其知识从举报恢复，则需要删除举报记录
						nCount = irpInformService.deleteByStatus(_docid,
								IrpInform.INFORMKNOW);
					} else {
						nCount = irpInformService
								.changeInformStatusByNameIdAndStatus(_docid,
										_informStatus, IrpInform.INFORMKNOW);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int deleteInformDocument(Long informId) {
		int nCount = 0;
		nCount = irpInformService.deleteInform(informId);
		return nCount;
	}

	@Override
	public int selectCountByExpert(Long userId, Date startTime, Date endTime,
			Integer siteType, Long docStatus) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			Criteria criteria = example.createCriteria();

			criteria.andSiteidIn(findSiteIds(siteType));// 所在站点id集合
			criteria.andCrtimeBetween(startTime, endTime);// 创建时间范围
			criteria.andCruseridEqualTo(userId);// 创建者id
			if (docStatus != null) {
				criteria.andDocstatusEqualTo(docStatus);
			}
			irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int updateDocumentToChannel(Long toChannel, Long fromChannel) {
		int nCount = 0;
		try {
			HashMap<String, Long> map = new HashMap<String, Long>();
			map.put("chanid", toChannel);
			map.put("channelid", fromChannel);
			nCount = irpChnl_Doc_LinkDAO
					.updateChan_Doc_ChannelidByChannelid(map);
			if (nCount > 0) {// 将知识表移动
				irpDocumentService.updateDocumentToChannel(toChannel,
						fromChannel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int updateDocumentDocstatusByChannel(Long Channelid) {
		int nCount = 0;
		try {
			IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
			example.createCriteria().andChannelidEqualTo(Channelid);
			List<IrpChnlDocLink> chnlDocLinks = irpChnl_Doc_LinkDAO
					.selectByExample(example);
			if (chnlDocLinks != null && chnlDocLinks.size() > 0) {
				for (int i = 0; i < chnlDocLinks.size(); i++) {
					IrpChnlDocLink chnlDocLink = chnlDocLinks.get(i);
					Long docstatus = -chnlDocLink.getDocstatus();
					Long docid = chnlDocLink.getDocid();
					nCount = +irpDocumentService.updateDocStatusByDocId(docid,
							docstatus);// 同时修改了中间表和document表
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}

	public List<IrpDocument> allDatas(Integer _siteType) {
		List<IrpDocument> allDocument = null;
		IrpDocumentExample documentExample = new IrpDocumentExample();
		com.tfs.irp.document.entity.IrpDocumentExample.Criteria criteria = documentExample
				.createCriteria();
		if (_siteType != null && _siteType.intValue() != 0) {
			List<Long> siteIds = findSiteIds(_siteType);
			if (siteIds != null && siteIds.size() > 0) {
				criteria.andSiteidIn(siteIds);
			}
		}
		allDocument = irpDocumentService.findAllDatas(documentExample);
		return allDocument;
	}

	@Override
	public List<String> selectByUserIdOrderbyCountDescBetweenTime(Long userId,
			Date starttime, Date endtime) {
		return irpSelectKeyService.selectByUserIdOrderbyCountDescBetweenTime(
				userId, starttime, endtime);
	}

	@Override
	public List findChnlDocByTime(Date startTime, Date endTime, String orderBy) {
		// TODO Auto-generated method stub
		List chnlList = null;
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		Criteria criteria = chnlDocLinkExample.createCriteria();
		criteria.andDocpubtimeBetween(startTime, endTime);
		chnlDocLinkExample.setOrderByClause(orderBy);
		try {
			chnlList = irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chnlList;
	}

	@Override
	public List<Long> findDocidsByDocIdsAndChannelidAndRight(List<Long> _ocidList, List<Long> _channelids, IrpUser _irpUser, Long infoType) {
		List<Long> docids = null;
		IrpChnlDocLinkExample chnlDocLinkExample = new IrpChnlDocLinkExample();
		Criteria criteria = chnlDocLinkExample.createCriteria();
		if (_irpUser != null && _irpUser.getUserid()>1L) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId = rightUtil
					.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(_irpUser, new IrpChannel(), nOperId);// 得到是否有权限语句
				String sSqlDoc = rightUtil.getRightExistsSQL(_irpUser, new IrpChannel(), nOperDocId);// 得到是否有权限语句
				criteria.extSQL(sSql);
				criteria.extSQL(sSqlDoc);
			}
		}
		if (_channelids != null && _channelids.size() > 0) {
			criteria.andChannelidIn(_channelids);
		}
		// 举报状态
		if (infoType != null) {
			criteria.andInformtypeEqualTo(infoType);
		}
		criteria.andDocidIn(_ocidList);
		try {
			docids = irpChnl_Doc_LinkDAO.findDocidsByExample(chnlDocLinkExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docids;
	}

	@Override
	public int deleteDocumentSubjectLink(Long channelid) {
		try {
			return irpChannelDAO.deleteByPrimaryKey(channelid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Map<String, IrpBaseObj>> findDocComments() {
		List<Map<String, IrpBaseObj>> list = new ArrayList<Map<String,IrpBaseObj>>();
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("userstatus", IrpUser.USER_STATUS_REG);
		mParam.put("siteid", IrpSite.PUBLIC_SITE_ID);
		mParam.put("docstatus", IrpDocument.PUBLISH_STATUS);
		RightUtil rightUtil = new RightUtil();
		String sExtChnlSQL = "";
		String sExtDocSQL = "";
		if (!LoginUtil.getLoginUser().isAdministrator()) {// 不是管理员就加权限
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
			if (nOperId != null && nOperId > 0L) {
				sExtChnlSQL = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId, true);// 得到是否有权限语句
				sExtDocSQL = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId, true);// 得到是否有权限语句
			}
		}
		mParam.put("extChnlSQL", sExtChnlSQL);
		mParam.put("extDocSQL", sExtDocSQL);
		try {
			List<Map<String, Object>> datas = irpChnl_Doc_LinkDAO.findNewCommentDatas(mParam, 4);
			for (Map<String, Object> map : datas) {
				if(map==null)
					continue;
				Map<String, IrpBaseObj> data = new HashMap<String, IrpBaseObj>(); 
				IrpDocrecommend comment = new IrpDocrecommend();
				IrpChnlDocLink chnl = new IrpChnlDocLink();
				IrpUser user = new IrpUser();
				
				Object oDocId = map.get("DOCID");
				if(oDocId!=null){
					long nDocId = Long.parseLong(oDocId.toString());
					comment.setDocid(nDocId);
					chnl.setDocid(nDocId);
				}
				Object oDocTitle = map.get("DOCTITLE");
				if(oDocTitle!=null){
					chnl.setDoctitle(oDocTitle.toString());
				}
				Object oReCommend = map.get("RECOMMEND");
				if(oReCommend!=null){
					comment.setRecommend(oReCommend.toString());
				}
				Object oCommentCount = map.get("COMMENTCOUNT");
				if(oCommentCount!=null){
					chnl.setCommentcount(Long.parseLong(oCommentCount.toString()));
				}
				Object oUserId = map.get("USERID");
				if(oUserId!=null){
					long nUserId = Long.parseLong(oUserId.toString());
					comment.setCruserid(nUserId);
					user.setUserid(nUserId);
				}
				Object oUserName = map.get("USERNAME");
				if(oUserName!=null){
					user.setUsername(oUserName.toString());
				}
				Object oTrueName = map.get("TRUENAME");
				if(oTrueName!=null){
					user.setTruename(oTrueName.toString());
				}
				Object oNickName = map.get("NICKNAME");
				if(oNickName!=null){
					user.setNickname(oNickName.toString());
				}
				Object oSex = map.get("SEX");
				if(oSex!=null){
					user.setSex(Integer.parseInt(oSex.toString()));
				}
				Object oUserPic = map.get("USERPIC");
				if(oUserPic!=null){
					user.setUserpic(oUserPic.toString());
				}
				
				data.put("IrpDocrecommend", comment);
				data.put("IrpChnlDocLink", chnl);
				data.put("IrpUser", user);
				list.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpChnlDocLink> findIrpChnlDocLinksByMapChannelId(long _nChannelId, int _nType, String sOrderByClause, PageUtil pageUtil){
		List<IrpChnlDocLink> chnlDocLinks = null;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_DOCUMENT_MAP WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_DOCUMENT_MAP.DOCID AND IRP_DOCUMENT_MAP.CHANNELID="+_nChannelId+" AND IRP_DOCUMENT_MAP.TYPE="+_nType+") ");
		if(sOrderByClause==null || sOrderByClause.trim().isEmpty()){
			sOrderByClause = "docpubtime desc";
		}
		example.setOrderByClause(sOrderByClause);
		try {
			chnlDocLinks = irpChnl_Doc_LinkDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}
	
	@Override
	public int findIrpChnlDocLinksCountByMapChannelId(long _nChannelId, int _nType){
		int nDataCount=0;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_DOCUMENT_MAP WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_DOCUMENT_MAP.DOCID AND IRP_DOCUMENT_MAP.CHANNELID="+_nChannelId+" AND IRP_DOCUMENT_MAP.TYPE="+_nType+") ");
		try {
			nDataCount = irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}

	@Override
	public List<IrpChnlDocLink> findExpertRecommendDocs(Long _nChannelId, PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_INFORM WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_INFORM.INFORMNAMEID AND IRP_INFORM.INFORMTYPE="+IrpInform.INFORMJIAJINGDOC+") ");
		example.setOrderByClause(" docpubtime desc ");
		try {
			chnlDocLinks = irpChnl_Doc_LinkDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public int countExpertRecommendDocs(Long _nChannelId) {
		int nDataCount = 0;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_INFORM WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_INFORM.INFORMNAMEID AND IRP_INFORM.INFORMTYPE="+IrpInform.INFORMJIAJINGDOC+") ");
		try {
			nDataCount = irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}

	@Override
	public int selectCountExample(IrpChnlDocLinkExample example) {
		int nDataCount=0;
		try {
			nDataCount = irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nDataCount;
	}

	@Override
	public List<IrpChnlDocLink> selectByExample(PageUtil pageUtil,
			IrpChnlDocLinkExample example) {
		List<IrpChnlDocLink> list = null;
		try {
			if(pageUtil==null){
				list=irpChnl_Doc_LinkDAO.selectByExample(example);
			}else{
				list=irpChnl_Doc_LinkDAO.selectByExample(example, pageUtil);
			}
			if (list != null && list.size() > 0) {
				IrpUser irpUser = LoginUtil.getLoginUser();// 获得当前登录用户
				for (int i = 0; i < list.size(); i++) {
					IrpChnlDocLink chnlDocLink = list.get(i);
					IrpDocumentCollectionExample collectionExample = new IrpDocumentCollectionExample();
					collectionExample
							.createCriteria()
							.andDocidEqualTo(
									new BigDecimal(chnlDocLink.getDocid()))
							.andUseridEqualTo(
									new BigDecimal(irpUser.getUserid()));
					List<IrpDocumentCollection> collections = irpDocumentCollectionDAO
							.selectByExample(collectionExample);
					if (collections != null && collections.size() > 0) {
						list.get(i)
								.setIrpDocumentCollection(collections.get(0));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void exportAllDocInfoToZip(List<IrpChnlDocLink> chnlDocLinks,
			String getPath, String fileName) {
		// TODO Auto-generated method stub
		try {
			//创建文件路径
			String path=getPath+"/doc"+fileName;
			//String path_1=getPath;
			// 创建文件夹;
			createFile(path);
			// 创建excel文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path+"/"
					+ fileName+".xls"));
			// 接收返回的isend,用来判断是否循环到最后
			int count = 0;
			// 初始化用户id
			
			
			if (chnlDocLinks != null && chnlDocLinks.size() != 0) {
				List<Long> list=new ArrayList<Long>();
				List<IrpChnlDocLink> list_1=new ArrayList<IrpChnlDocLink>();
				list_1.addAll(chnlDocLinks);
				list.add(list_1.get(0).getCruserid());
				String string=list_1.get(0).getCruserid()+"";
				do{
					for (int i = 0; i < list_1.size(); ) {
						if(list_1.get(i).getCruserid().toString().equals(string)){
							list_1.remove(i);
						}else{
							i++;
						}
						
					}
					if(list_1.size()>0){
						string=list_1.get(0).getCruserid()+"";
						list.add(list_1.get(0).getCruserid());
					}
				}while (list_1.size()>0);
			//	this.SignidState = chnlDocLinks.get(0).getCruserid();
				/*do {
					
				} while (count != -1);*/
				Long SignidState=null;
				for (int i = 0; i < list.size(); i++) {
					SignidState=list.get(i);
					count = createAllSignToExcel(chnlDocLinks, workbook,SignidState,i);
				}
				
			}
			// 写入Excel表格中;
			workbook.write();
			// 关闭流;
			workbook.close();
			// 生成.zip文件;
			craeteZipPath(getPath,fileName);
			// 删除目录下所有的文件;
			File file = new File(path);
			// 删除文件;
			deleteExcelPath(file);
			// 重新创建文件;
			// file.mkdirs();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean deleteExcelPath(File file) {

		String[] files = null;
		if (file != null) {
			files = file.list();
		}

		if (file.isDirectory()) {
			for (int i = 0; i < files.length; i++) {
				boolean bol = deleteExcelPath(new File(file, files[i]));
				if (bol) {
					// System.out.println("删除成功!");
				} else {
					// System.out.println("删除失败!");
				}
			}
		}
		return file.delete();
	
	}

	/**
	 * userId状态值
	 * @throws FileNotFoundException 
	 */
//	private Long SignidState;

	private void craeteZipPath(String path, String zipName) throws IOException {
		ZipOutputStream zipOutputStream = null;
		File file = new File(path+"/doc"
				+ zipName
				+ ".zip");
		zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		File[] files = new File(path+"/doc"+zipName).listFiles();
		FileInputStream fileInputStream = null;
		byte[] buf = new byte[1024];
		int len = 0;
		if (files != null && files.length > 0) {
			for (File excelFile : files) {
				// 测试使用 记得删除
				// System.out.println("哈哈哈我在第314行");
				String fileName = excelFile.getName();
				fileInputStream = new FileInputStream(excelFile);
				// 放入压缩zip包中;
				zipOutputStream.putNextEntry(new ZipEntry("/" + fileName));

				// 读取文件;
				while ((len = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, len);
				}
				// 关闭;
				zipOutputStream.closeEntry();
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			}
		}

		if (zipOutputStream != null) {
			zipOutputStream.close();
		}
		
	}


	/**
	 * 循环写入excel
	 * @param chnlDocLinks
	 * @param workbook
	 * @return
	 * @throws WriteException 
	 */
	private int createAllSignToExcel(List<IrpChnlDocLink> chnlDocLinks,
			WritableWorkbook workbook,Long SignidState,int sheetNum ) throws WriteException {

		// 获得当前用户的trueName
	//	Map uid = new HashMap();
		//uid.put("userid", SignidState);
		String userName = LoginUtil.findUserById(SignidState).getShowName();
		// 循环中计数器
		int count = 0;
		// 判断是不是最后一个 1代表不是最后一个, -1代表是最后一个
		int isend = 1;
		// 是否循环过数据
		boolean isexport = false;
		// 创建第一个sheet文件;
		WritableSheet sheet = workbook.createSheet(userName, sheetNum);
		// 设置默认宽度;
		sheet.getSettings().setDefaultColumnWidth(20);
		// 设置字体,正常状态(黑色);
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat1.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat1.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 设置字体,异常状态(红色)
		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);

		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat2.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat2.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat2.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
		//Label label = new Label(0, 0, "日期", cellFormat1);
		Label label1 = new Label(0, 0, "文档标题", cellFormat1);
		Label label2 = new Label(1, 0, "所属栏目", cellFormat1);
		Label label3 = new Label(2, 0, "用户名", cellFormat1);
		Label label4 = new Label(3, 0, "时间", cellFormat1);
		Label label5 = new Label(4, 0, "状态", cellFormat1);

		//sheet.addCell(label);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		// 行数计数器
		int i = 0;

		for (; count < chnlDocLinks.size(); count++) {
			IrpChnlDocLink isi = chnlDocLinks.get(count);

			if (isi.getCruserid().equals(SignidState) && !isexport) {

				//Label lab1 = new Label(0, i + 1, SignUtil.getStringDay(isi
			//			.getCrtime()), cellFormat1);
				Label lab2 = new Label(0, i + 1, isi
						.getDoctitle(), cellFormat1);
				Label lab3 = new Label(1, i + 1,isi.getChnldesc(),  cellFormat1);
				Label lab4 = new Label(2, i + 1, isi.getCruser(),
						cellFormat1);
				Label lab5 = new Label(3, i + 1, SignUtil.getStringDate(isi.getCrtime()),
						cellFormat1);
				Label lab6 = new Label(4, i + 1, findStatusNameByStatusId(isi.getDocstatus()),cellFormat1);
				//sheet.addCell(lab1);
				sheet.addCell(lab2);
				sheet.addCell(lab3);
				sheet.addCell(lab4);
				sheet.addCell(lab5);
				sheet.addCell(lab6);
				i++;
			/*	if (count == (chnlDocLinks.size() - 1)) {
					isend = -1;
				}
				if (isend != -1
						&& !chnlDocLinks.get(count + 1).getCruserid()
								.equals(SignidState)) {
					isexport = true;
				}*/

			} /*else if (isexport && !isi.getCruserid().equals(SignidState)) {
				SignidState = isi.getCruserid();
				break;
			}*/
			//sheetNum++;
		}
		return isend;
	
	}
	public String findStatusNameByStatusId(long _nStatusId) {
		String sStatusName =  "";
		if(_nStatusId>0L){
			IrpDocstatus status = irpDocStatusService.finDocstatusByStatusId(_nStatusId);
			if(status!=null){
				sStatusName = status.getSname();
			}
		}
		return sStatusName;
	}
	/**
	 * 创建文件路径
	 * @param path
	 */
	private String createFile(String path) {
		File file = new File(path);
		// 判断文件是否存在;
		if (!file.exists()) {
			// 创建文件;
			boolean bol = file.mkdirs();
			if (bol) {
				// System.out.println(path+" 路径创建成功!");
			} else {
				// System.out.println(path+" 路径创建失败!");
			}
		} else {
			// System.out.println(path+" 文件已经存在!");
		}
		return path;
		
	}

	@Override
	public List findJuBaoDoc_app(IrpUser irpUser, Integer sitetypeOne,
			Integer sitetypeTwo, String informKey, Integer informType,
			Integer informStatus, PageUtil pageUtil) {
		List lsit = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();

			String rightsql1 = "";
			String rightsql2 = "";
			if (sitetypeTwo != null
					&& sitetypeTwo.intValue() == IrpSite.SITE_TYPE_PUBLISH) {// 所属站点有企业站点
				RightUtil rightUtil = new RightUtil();
				if (!irpUser.isAdministrator()) {// 不是管理员就加权限
					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						String sql1 = " and " + sSql;
						String sql2 = " and " + sDocSql;
						rightsql1 = sql1 + sql2;
						rightsql2 = sql1 + sql2;
					}
				}
				if (sitetypeOne != null
						&& sitetypeOne.intValue() == IrpSite.SITE_TYPE_PRIVATE) {
					rightsql1 = "";
				}
			}
			map.put("rightsql1", rightsql1);
			map.put("rightsql2", rightsql2);
			map.put("informtype", informType);
			map.put("informstatus", informStatus);
			map.put("sitetype1", sitetypeOne);
			map.put("sitetype2", sitetypeTwo);
			map.put("expert", informKey);
			lsit = irpChnl_Doc_LinkDAO.findJuBaoDocument(map, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsit;
	}

	@Override
	public Integer findJuBaoDocCount_app(IrpUser irpUser, Integer sitetypeOne,
			Integer sitetypeTwo, String informKey, Integer informType,
			Integer informStatus) {
		int count = 0;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String rightsql1 = "";
			String rightsql2 = "";
			if (sitetypeTwo != null
					&& sitetypeTwo.intValue() == IrpSite.SITE_TYPE_PUBLISH) {// 所属站点有企业站点
				RightUtil rightUtil = new RightUtil();
				if (!irpUser.isAdministrator()) {// 不是管理员就加权限
					Long nOperId = rightUtil
							.findOperTypeIdByKey("CHANNEL_SELECT");
					Long nOperDocId = rightUtil
							.findOperTypeIdByKey("DOCUMENT_LIST");// 知识列表权限
					if (nOperId != null && nOperId > 0L && nOperDocId != null
							&& nOperDocId != 0L) {
						String sSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperId);// 得到是否有权限语句
						String sDocSql = rightUtil.getRightExistsSQL(
								new IrpChannel(), nOperDocId);// 得到是否有权限语句
						String sql1 = " and " + sSql;
						String sql2 = " and " + sDocSql;
						rightsql1 = sql1 + sql2;
						rightsql2 = sql1 + sql2;
					}
				}
				if (sitetypeOne != null
						&& sitetypeOne.intValue() == IrpSite.SITE_TYPE_PRIVATE) {
					rightsql1 = "";
				}
			}
			map.put("rightsql1", rightsql1);
			map.put("rightsql2", rightsql2);
			map.put("informtype", informType);
			map.put("informstatus", informStatus);
			map.put("sitetype1", sitetypeOne);
			map.put("sitetype2", sitetypeTwo);
			map.put("expert", informKey);
			count = irpChnl_Doc_LinkDAO.findJuBaoDocumentCount(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int findIrpChnlDocLinksCountByMapChannelIds(String _nChannelId, int _nType) {
		int nDataCount=0;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		int length=_nChannelId.split(",").length-1;
		length=((length-1)*length)-1;
		if(length<=0){
			criteria.extSQL(" exists(select * from (select a4.docid, count(1) from (SELECT a1.docid as docid ,a1.channelid as chid FROM IRP_DOCUMENT_MAP a1,IRP_DOCUMENT_MAP a2  where a1.TYPE="+_nType+" and a2.TYPE="+_nType+"  and a1.CHANNELID in("+_nChannelId+")  and a2.CHANNELID in("+_nChannelId+") AND a1.DOCID=a2.DOCID ORDER BY a1.DOCID ) a4  group by a4.docid having count(1)>"+length+" ) a3  where IRP_CHNL_DOC_LINK.DOCID=a3.DOCID )");
		}else{
			criteria.extSQL(" exists(select * from (select a4.docid, count(1) from (SELECT a1.docid as docid ,a1.channelid as chid FROM IRP_DOCUMENT_MAP a1,IRP_DOCUMENT_MAP a2  where a1.TYPE="+_nType+" and a2.TYPE="+_nType+" and a1.CHANNELID!=a2.CHANNELID  and a1.CHANNELID in("+_nChannelId+")  and a2.CHANNELID in("+_nChannelId+") AND a1.DOCID=a2.DOCID ORDER BY a1.DOCID ) a4  group by a4.docid having count(1)>"+length+" ) a3  where IRP_CHNL_DOC_LINK.DOCID=a3.DOCID )");
		}
		try {
			//nDataCount = irpChnl_Doc_LinkDAO.countByExample(example);
			List<IrpChnlDocLink> chnlDocLinks = irpChnl_Doc_LinkDAO.selectByExample(example, null);
			nDataCount=chnlDocLinks.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}

	@Override
	public List<IrpChnlDocLink> findIrpChnlDocLinksByMapChannelIds(
			String _nChannelId, int _nType, String sOrderByClause, PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		int length=_nChannelId.split(",").length;
		length=((length-1)*length)-1;
		//添加知识扩展分类条件
		//criteria.extSQL(" exists(SELECT 1 FROM IRP_DOCUMENT_MAP WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_DOCUMENT_MAP.DOCID AND IRP_DOCUMENT_MAP.CHANNELID in ("+_nChannelId+") AND IRP_DOCUMENT_MAP.TYPE="+_nType+") ");
		//criteria.extSQL(" exists(select * from (SELECT distinct(a1.docid) FROM IRP_DOCUMENT_MAP a1,IRP_DOCUMENT_MAP a2 WHERE a1.CHANNELID in("+_nChannelId+") AND a1.TYPE="+_nType+" and a2.TYPE="+_nType+" and a1.CHANNELID!=a2.CHANNELID and a2.CHANNELID in("+_nChannelId+") AND a1.DOCID=a2.DOCID) a3  where IRP_CHNL_DOC_LINK.DOCID=a3.DOCID)");
		if(length<=0){
			criteria.extSQL(" exists(select * from (select a4.docid, count(1) from (SELECT a1.docid as docid ,a1.channelid as chid FROM IRP_DOCUMENT_MAP a1,IRP_DOCUMENT_MAP a2  where a1.TYPE="+_nType+" and a2.TYPE="+_nType+"  and a1.CHANNELID in("+_nChannelId+")  and a2.CHANNELID in("+_nChannelId+") AND a1.DOCID=a2.DOCID ORDER BY a1.DOCID ) a4  group by a4.docid having count(1)>"+length+" ) a3  where IRP_CHNL_DOC_LINK.DOCID=a3.DOCID )");
		}else{
			criteria.extSQL(" exists(select * from (select a4.docid, count(1) from (SELECT a1.docid as docid ,a1.channelid as chid FROM IRP_DOCUMENT_MAP a1,IRP_DOCUMENT_MAP a2  where a1.TYPE="+_nType+" and a2.TYPE="+_nType+" and a1.CHANNELID!=a2.CHANNELID  and a1.CHANNELID in("+_nChannelId+")  and a2.CHANNELID in("+_nChannelId+") AND a1.DOCID=a2.DOCID ORDER BY a1.DOCID ) a4  group by a4.docid having count(1)>"+length+" ) a3  where IRP_CHNL_DOC_LINK.DOCID=a3.DOCID )");
		}

		if(sOrderByClause==null || sOrderByClause.trim().isEmpty()){
			sOrderByClause = "docpubtime desc";
		}
		example.setOrderByClause(sOrderByClause);
		try {
			chnlDocLinks = irpChnl_Doc_LinkDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public int findIrpChnlDocLinksCountByMapParentIds(String _nChannelId, int _nType) {
		int nDataCount=0;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_DOCUMENT_MAP WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_DOCUMENT_MAP.DOCID AND IRP_DOCUMENT_MAP.CHANNELID in ("+_nChannelId+") AND IRP_DOCUMENT_MAP.TYPE="+_nType+") ");
		try {
			nDataCount = irpChnl_Doc_LinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}

	@Override
	public List<IrpChnlDocLink> findIrpChnlDocLinksByMapParentIds(
			String _nChannelId, int _nType, String sOrderByClause, PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks = null;
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChnlDocLinkExample example = new IrpChnlDocLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andChannelidGreaterThan(0L)
			.andSiteidEqualTo(IrpSite.PUBLIC_SITE_ID)
			.andDocstatusEqualTo(IrpDocument.PUBLISH_STATUS)
			.andInformtypeEqualTo(IrpDocument.DOCUMENT_NOT_INFORM)
			.andModalEqualTo(IrpDocument.DOCTYPE_DOCUMENT);
		
		//添加权限
		if (!loginUser.isAdministrator()) {
			RightUtil rightUtil = new RightUtil();
			Long nOperId = rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			if (nOperId != null && nOperId > 0L) {
				String sSql = rightUtil.getRightExistsSQL(new IrpChannel(), nOperId);
				criteria.extSQL(sSql);
			}
			Long nOperDocId = rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");
			if (nOperDocId != null && nOperDocId > 0L) {
				String sSqlDoc = rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				criteria.extSQL(sSqlDoc);
			}
		}
		//添加知识扩展分类条件
		criteria.extSQL(" exists(SELECT 1 FROM IRP_DOCUMENT_MAP WHERE IRP_CHNL_DOC_LINK.DOCID=IRP_DOCUMENT_MAP.DOCID AND IRP_DOCUMENT_MAP.CHANNELID in ("+_nChannelId+") AND IRP_DOCUMENT_MAP.TYPE="+_nType+") ");
		if(sOrderByClause==null || sOrderByClause.trim().isEmpty()){
			sOrderByClause = "docpubtime desc";
		}
		example.setOrderByClause(sOrderByClause);
		try {
			chnlDocLinks = irpChnl_Doc_LinkDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}


}
