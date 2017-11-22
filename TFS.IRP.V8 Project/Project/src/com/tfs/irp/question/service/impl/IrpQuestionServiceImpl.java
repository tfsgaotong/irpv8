package com.tfs.irp.question.service.impl;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

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

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tfs.irp.attached.dao.IrpAttachedDAO;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedExample;
import com.tfs.irp.attachedtype.dao.IrpAttachedTypeDAO;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.category.dao.IrpCategoryDAO;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.entity.IrpCategoryExample;
import com.tfs.irp.category_file_link.dao.IrpCategoryFileLinkDAO;
import com.tfs.irp.category_file_link.entity.IrpCategoryFileLink;
import com.tfs.irp.category_file_link.entity.IrpCategoryFileLinkExample;
import com.tfs.irp.expert.dao.IrpExpertQuestionDAO;
import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.expert.service.IrpExpertQuestionService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.question.dao.IrpQuestionDAO;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.entity.IrpQuestionExample;
import com.tfs.irp.question.entity.IrpQuestionExample.Criteria;
import com.tfs.irp.question.entity.IrpQuestionExcel;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.role.dao.IrpUserroleLinkDAO;
import com.tfs.irp.sign.dao.IrpSignInfoDAO;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.entity.QuestionForSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SignUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpQuestionServiceImpl implements IrpQuestionService {
	private IrpQuestionDAO irpQuestionDAO;
	private List<IrpQuestion> irpQuestions;
	private Long loginuserid;
	private String loginUsername;
	private IrpExpertQuestionDAO irpExpertQuestionDAO;
	private IrpAttachedTypeDAO irpAttachedTypeDAO;
	private IrpAttachedDAO irpAttachedDAO;
	private IrpExpertQuestionService irpExpertQuestionService;
	private IrpMessageContentService irpMessageContentService;
	private SolrService solrService;
	private IrpCategoryDAO irpCategoryDAO;
	private IrpCategoryFileLinkDAO irpCategoryFileLinkDAO;

	public IrpCategoryDAO getIrpCategoryDAO() {
		return irpCategoryDAO;
	}

	public void setIrpCategoryDAO(IrpCategoryDAO irpCategoryDAO) {
		this.irpCategoryDAO = irpCategoryDAO;
	}

	public IrpCategoryFileLinkDAO getIrpCategoryFileLinkDAO() {
		return irpCategoryFileLinkDAO;
	}

	public void setIrpCategoryFileLinkDAO(
			IrpCategoryFileLinkDAO irpCategoryFileLinkDAO) {
		this.irpCategoryFileLinkDAO = irpCategoryFileLinkDAO;
	}

	public SolrService getSolrService() {
		return solrService;
	}

	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpExpertQuestionService getIrpExpertQuestionService() {
		return irpExpertQuestionService;
	}

	public void setIrpExpertQuestionService(
			IrpExpertQuestionService irpExpertQuestionService) {
		this.irpExpertQuestionService = irpExpertQuestionService;
	}

	public IrpAttachedDAO getIrpAttachedDAO() {
		return irpAttachedDAO;
	}

	public void setIrpAttachedDAO(IrpAttachedDAO irpAttachedDAO) {
		this.irpAttachedDAO = irpAttachedDAO;
	}

	public IrpAttachedTypeDAO getIrpAttachedTypeDAO() {
		return irpAttachedTypeDAO;
	}

	public void setIrpAttachedTypeDAO(IrpAttachedTypeDAO irpAttachedTypeDAO) {
		this.irpAttachedTypeDAO = irpAttachedTypeDAO;
	}

	public IrpExpertQuestionDAO getIrpExpertQuestionDAO() {
		return irpExpertQuestionDAO;
	}

	public void setIrpExpertQuestionDAO(
			IrpExpertQuestionDAO irpExpertQuestionDAO) {
		this.irpExpertQuestionDAO = irpExpertQuestionDAO;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Long getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}

	public List<IrpQuestion> getIrpQuestions() {
		return irpQuestions;
	}

	public void setIrpQuestions(List<IrpQuestion> irpQuestions) {
		this.irpQuestions = irpQuestions;
	}

	public IrpQuestionDAO getIrpQuestionDAO() {
		return irpQuestionDAO;
	}

	public void setIrpQuestionDAO(IrpQuestionDAO irpQuestionDAO) {
		this.irpQuestionDAO = irpQuestionDAO;
	}

	public void addCategoryFileLink(Long questionId,String categories){
		if(categories!=null && !"".equals(categories)){
			// 同时插入irp_category_file_link表中
			IrpCategoryFileLink irpCategoryFileLink =  null;
			String[] allCategory = categories.split(",");
			List<Long> categoryIdList = new ArrayList<Long>();
			for(String string : allCategory){
				categoryIdList.add(Long.valueOf(string));
			}
			// 查询出名称对应的id
			IrpCategoryExample categoryExample = new IrpCategoryExample();
			categoryExample.createCriteria().andCategoryidIn(categoryIdList);
			List<IrpCategory> categoryList;
			try {
				categoryList = irpCategoryDAO.selectByExample(categoryExample);
				for(IrpCategory category : categoryList){
					irpCategoryFileLink = new IrpCategoryFileLink();
					irpCategoryFileLink.setCategoryfileid(TableIdUtil.getNextId(IrpCategoryFileLink.TABLE_NAME));
					irpCategoryFileLink.setCid(category.getCategoryid());
					irpCategoryFileLink.setFid(questionId);
					irpCategoryFileLink.setCrtime(new Date());
					irpCategoryFileLink.setCruserid(LoginUtil.getLoginUserId());
					irpCategoryFileLinkDAO.insertSelective(irpCategoryFileLink);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public long addQuestion(String title, String _jsonFile,String _questiontitle,String categories) {
		Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionId);
		Date date = Calendar.getInstance().getTime();
		irpQuestion.setCrtime(date);
		irpQuestion.setStatus(0);
		irpQuestion.setTitle(_questiontitle);
		irpQuestion.setHtmlcontent(title);
		irpQuestion.setCruserid(LoginUtil.getLoginUserId());
		irpQuestion.setCruser(LoginUtil.getLoginUser().getName());
		irpQuestion.setBrowsecount(0l);
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			addCategoryFileLink(questionId,categories);
			// 保存附件
			if (_jsonFile != null && _jsonFile.length() > 0
					&& !"[]".equals(_jsonFile)) {
				JSONArray _Array = JSONArray.fromObject(_jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String _sAttFile = jsonObject.getString("attfile");
					String _sAttDesc = jsonObject.getString("attdesc");
					String _sAttLinkAlt = jsonObject.getString("attlinkalt");
					String _sEditversions = jsonObject
							.getString("editversions");
					String _lastName = FileUtil.findFileExt(_sAttFile);
					List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
							.getSuffixExits(_lastName);// 查询所附件类型是否存在

					if (_arrSufflx != null && _arrSufflx.size() > 0) {
						addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
								questionId, _sAttDesc, _sAttLinkAlt,
								_sEditversions);
					} else {// 就是其他类型
						addAttFile(_sAttFile,
								IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
								_sAttDesc, _sAttLinkAlt, _sEditversions);
					}
				}
			}
			//增加索引
			QuestionForSolr qfs = new QuestionForSolr();
			qfs.setQUESTIONID(questionId);
			qfs.setCRTIME(date);
			qfs.setCRUSER(LoginUtil.getLoginUser().getName());
			qfs.setCRUSERID(LoginUtil.getLoginUserId());
			qfs.setPARENTID(null);
			qfs.setTEXTCONTENT(null);
			qfs.setTITLE(_questiontitle);
			this.solrService.addQuestionIndex(qfs,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
		} catch (SQLException e) {
			e.printStackTrace();
			questionId = 0L;
		}
		return questionId;
	}
	
	
	@Override
	public long addQuestion(String title, String _jsonFile,String _questiontitle,String categories,IrpUser irpUser) {
		Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionId);
		Date date = Calendar.getInstance().getTime();
		irpQuestion.setCrtime(date);
		irpQuestion.setStatus(0);
		irpQuestion.setTitle(_questiontitle);
		irpQuestion.setHtmlcontent(title);
		irpQuestion.setCruserid(irpUser.getUserid());
		irpQuestion.setCruser(irpUser.getUsername());
		irpQuestion.setBrowsecount(0l);
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			addCategoryFileLink(questionId,categories);
			// 保存附件
			if (_jsonFile != null && _jsonFile.length() > 0
					&& !"[]".equals(_jsonFile)) {
				JSONArray _Array = JSONArray.fromObject(_jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String _sAttFile = jsonObject.getString("attfile");
					String _sAttDesc = jsonObject.getString("attdesc");
					String _sAttLinkAlt = jsonObject.getString("attlinkalt");
					String _sEditversions = jsonObject
							.getString("editversions");
					String _lastName = FileUtil.findFileExt(_sAttFile);
					List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
							.getSuffixExits(_lastName);// 查询所附件类型是否存在

					if (_arrSufflx != null && _arrSufflx.size() > 0) {
						addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
								questionId, _sAttDesc, _sAttLinkAlt,
								_sEditversions);
					} else {// 就是其他类型
						addAttFile(_sAttFile,
								IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
								_sAttDesc, _sAttLinkAlt, _sEditversions);
					}
				}
			}
			//增加索引
			QuestionForSolr qfs = new QuestionForSolr();
			qfs.setQUESTIONID(questionId);
			qfs.setCRTIME(date);
			qfs.setCRUSER(irpUser.getUsername());
			qfs.setCRUSERID(irpUser.getUserid());
			qfs.setPARENTID(null);
			qfs.setTEXTCONTENT(null);
			qfs.setTITLE(_questiontitle);
			this.solrService.addQuestionIndex(qfs,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
		} catch (SQLException e) {
			e.printStackTrace();
			questionId = 0L;
		}
		return questionId;
	}
	private void addAttFile(String _sAttFile, Long TypeId, long ObjId,
			String _sAttDesc, String _sAttLinkAlt, String _sEditversions) {
		IrpUser irpUser = LoginUtil.getLoginUser();
		String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
		File newFile = new File(filePath);
		String newFileName = "";
		if (newFile.exists()) {
			String _lastName = FileUtil.findFileExt(_sAttFile);
			if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
					TypeId.toString())) { // 这是创建文档之后的附件名字
				newFileName = SysFileUtil.saveFile(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_PIC, true);
			} else {
				newFileName = SysFileUtil.saveFile(newFile,
						SysFileUtil.FILE_TYPE_ATTACHED_FILE, true);
			}
			String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);
			IrpAttached irpAttached = new IrpAttached();
			Long _attachedid = TableIdUtil.getNextId(IrpAttached.TABLE_NAME);
			irpAttached.setAttachedid(_attachedid);// ;//他的主键值
			irpAttached.setAttdocid(ObjId);// 文档编号
			irpAttached.setAtttime(new Date());// 设置上传时间
			irpAttached.setCrtime(new Date());// 创建时间
			irpAttached.setCruser(irpUser.getUsername());
			irpAttached.setCruserid(irpUser.getUserid());
			irpAttached.setAttfile(newFileName);// 文件名称
			irpAttached.setAttdesc(_sAttDesc);// 附件描述
			irpAttached.setAttlinkalt(_sAttLinkAlt);// 附件alt
			irpAttached.setSrcfile(newFilePath);// /源文件名完整路径
			irpAttached.setAttflag(3);// 问答附件标记为3
			irpAttached.setTypeid(TypeId);// 将这个t设置为文件类型id
			irpAttached.setFileext(_lastName);// 文件的扩展名

			irpAttached.setAttdocidtype(IrpAttached.QUESTION_DOCIDTYPE);// 问题文件标识

			if (_sEditversions.toString().equals("1")) {
				irpAttached.setEditversions(IrpAttached.IS_FENGMIAN);// 是否为封面
																		// 1为封面，
			}
			if (_sEditversions.toString().equals("0")) {
				irpAttached.setEditversions(IrpAttached.NO_FENGMIAN);// 否为封面
			}
			if (_sEditversions.toString().equals("2")) {
				irpAttached.setEditversions(IrpAttached.NULL_FENGMIAN);// 否为封面
																		// 标示他是文件附件，不是图片附件
			}
			try {
				irpAttachedDAO.insertSelective(irpAttached);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<IrpQuestion> listAllQuestion() {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria();
		List<IrpQuestion> list = new ArrayList<IrpQuestion>();
		try {
			list = irpQuestionDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delQuestion(long questionid) throws Exception {
		// 0 表示未删除 1 表示以删除
		int _nStatus = 0;
		try {
			_nStatus = this.irpQuestionDAO.deleteByPrimaryKey(questionid);
			// 删除答案
			IrpQuestionExample example = new IrpQuestionExample();
			example.createCriteria().andParentidEqualTo(questionid);
			this.irpQuestionDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (_nStatus == 1) {
			this.solrService.deleteSolrIndex(questionid,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
		}
		
		return _nStatus;
	}

	@Override
	public int findAllQuestions() {
		int nCount = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusIsNotNull()
								.andTextcontentIsNull();
		try {
			nCount = irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpQuestion> findQuestions(PageUtil pageUtil, String _sOrderBy)
			throws Exception {
		List<IrpQuestion> userList = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusIsNotNull().andTextcontentIsNull();
		if (_sOrderBy == null || _sOrderBy.equals("")) {
			_sOrderBy = "questionid desc";
		}
		example.setOrderByClause(_sOrderBy);
		irpQuestions = irpQuestionDAO.selectByExample(example);
		return userList;
	}

	@Override
	public List<IrpQuestion> findQuestions(PageUtil pageUtil) throws Exception {
		List<IrpQuestion> questionList = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria();
		questionList = irpQuestionDAO.selectByExample(example, pageUtil);
		return questionList;
	}

	@Override
	public List<IrpQuestion> findQuestionsOfNoResoule(PageUtil pageUtil)
			throws Exception {
		List<IrpQuestion> questions = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusEqualTo(0l).andStatusIsNotNull().andTextcontentIsNull();
		example.setOrderByClause("crtime desc");
		questions = irpQuestionDAO.selectByExample(example, pageUtil);
		return questions;
	}

	@Override
	public int findQuestionsCount() throws Exception {
		int nCount = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusEqualTo(0l).andStatusIsNotNull().andTextcontentIsNull();
		try {
			nCount = irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int findQuestionMineCount() throws Exception {

		int nCount = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusIsNotNull()
				.andCruseridEqualTo(LoginUtil.getLoginUserId()).andTextcontentIsNull();
		try {
			nCount = irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int findAnwserQuestionMineCount() throws Exception {
		int nCount = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andTextcontentIsNotNull()
				.andCruseridEqualTo(LoginUtil.getLoginUserId());
		Set set = new HashSet();
		List list = this.irpQuestionDAO.selectByExample(example);
		if (list.size() <= 0) {
			return nCount;
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
			set.add(irpQuestion.getParentid());

		}
		nCount = set.size();
		return nCount;

	}

	@Override
	public List<IrpQuestion> findAnwserQuestionMine(PageUtil pageUtil)
			throws Exception {

		List<IrpQuestion> questionResoule = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andTextcontentIsNotNull()
				.andCruseridEqualTo(LoginUtil.getLoginUserId());
		example.setOrderByClause("crtime desc");
		Set set = new HashSet();
		List list = this.irpQuestionDAO.selectByExample(example);
		if (list.size() <= 0) {
			return questionResoule;
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
			set.add(irpQuestion.getParentid());

		}

		List<Long> listlong = new ArrayList<Long>(set);
		IrpQuestionExample examplelist = new IrpQuestionExample();
		examplelist.createCriteria().andQuestionidIn(listlong);
		questionResoule = this.irpQuestionDAO.selectByExample(examplelist,
				pageUtil);

		return questionResoule;
	}

	@Override
	public int findQuestionsResoule() throws Exception {
		int nCount = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusEqualTo(1l).andStatusIsNotNull().andTextcontentIsNull();
		try {
			nCount = irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpQuestion> findQuestionOfResoule(PageUtil pageUtil)
			throws Exception {
		List<IrpQuestion> questionResoule = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusEqualTo(1l).andStatusIsNotNull().andTextcontentIsNull();
		example.setOrderByClause("crtime desc");
		questionResoule = irpQuestionDAO.selectByExample(example, pageUtil);
		return questionResoule;
	}

	@Override
	public List<IrpQuestion> findQuestionMine(PageUtil pageUtil)
			throws Exception {
		List<IrpQuestion> questionResoule = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andStatusIsNotNull()
				.andCruseridEqualTo(LoginUtil.getLoginUserId()).andTextcontentIsNull();
		example.setOrderByClause("crtime desc");
		questionResoule = irpQuestionDAO.selectByExample(example, pageUtil);
		return questionResoule;
	}

	@Override
	public IrpQuestion findQuestionDetail(Long questionid) {
		IrpQuestion irpQuestion = null;
		try {
			if(questionid!=null){
				irpQuestion = irpQuestionDAO.selectByPrimaryKey(questionid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpQuestion;
	}

	@Override
	public List<IrpQuestion> findQuestionsByPage(PageUtil pageUtil,
			String orderBy) {
		List<IrpQuestion> questionList = null;
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			if (orderBy == null || orderBy.equals("")) {
				orderBy = "crtime desc";
			}
			example.setOrderByClause(orderBy);
			example.createCriteria().andStatusIsNotNull().andTextcontentIsNull();
			questionList = irpQuestionDAO.selectByExample(example, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public int addTextAnswer(String _jsonFile,Long questionid, String text,String _contentanswer,Long browercount,Integer _status,Long _replyqid)
			throws Exception {
		// 回答该问题的人，当前登录用户
		IrpUser loginUser = LoginUtil.getLoginUser();
		loginuserid = loginUser.getUserid();
		loginUsername = loginUser.getUsername();
		int nStatus = 0;
		// 答案
		Date date = Calendar.getInstance().getTime();
		Long nextquestionid = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);
		
		
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(nextquestionid);
		irpQuestion.setParentid(questionid);
		irpQuestion.setTextcontent(_contentanswer);
        irpQuestion.setHtmlcontent(text);
		irpQuestion.setIsbestanswer((short) 0);
		

		irpQuestion.setReplyquestionid(_replyqid);
		irpQuestion.setIsexpertanswer((short) 0);
		irpQuestion.setStatus(_status);
		irpQuestion.setCrtime(date);
		irpQuestion.setCruserid(LoginUtil.getLoginUserId());
		irpQuestion.setCruser(loginUsername);
		irpQuestion.setBrowsecount(0l);
		// 如果当前登陆用户为专家,将isexpertAnswer设置为1

		if (SysConfigUtil.getSysConfigValue(IrpQuestion.ENABLEEXPERTANSWER)
				.equals("false")) {
			if (loginUser.isExpert()) {
				irpQuestion.setIsexpertanswer((short) 1);
			}

		} else {
			IrpExpertQuestion irpExpertQuestion = irpExpertQuestionService
					.irpExpertQuestion(questionid);
			if (irpExpertQuestion == null) {
				nStatus = 0;

			} else if (irpExpertQuestion.getExpertid().equals(
					LoginUtil.getLoginUserId())) {
				irpQuestion.setIsexpertanswer((short) 1);
			}

		}

		// 将回答者，问题的id插入irp_expert_question表中
		IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
		irpExpertQuestion.setExpertquestionid(TableIdUtil
				.getNextId(IrpExpertQuestion.TABLE_NAME));
		irpExpertQuestion.setQuestionerid(loginuserid);
		irpExpertQuestion.setQuestionid(questionid);

		if (loginUser.isExpert()) {
			irpExpertQuestion.setExpertid(loginuserid);
		} else {
			irpExpertQuestion.setExpertid(0L);
		}

		IrpQuestion irpQuestion1 = new IrpQuestion();
		irpQuestion1.setQuestionid(questionid);

		if (loginUser.isExpert()) {
			irpQuestion1.setExpertanswerid(loginuserid);
			irpQuestion1.setIsexpertanswer((short) 1);
		}

		irpQuestion1.setExpertanswerid(loginuserid);
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			//更新楼层
			try{
					if (_status==IrpQuestion.REPLYCOMMENTSTATUS) {
					int rootid = replyCommentNum(questionid)-1;
					long rootupdatrnums = 1;	
					if(rootid>0){	
						rootupdatrnums = rootid+1;
					}
					
					updateRIdByQId(nextquestionid,rootupdatrnums);
					
				}
			}finally{
				this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
				this.irpQuestionDAO.updateByPrimaryKeySelective(irpQuestion1);
				nStatus = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//增加索引
		QuestionForSolr qfs = new QuestionForSolr();
		qfs.setCRTIME(date);
		qfs.setCRUSER(loginUsername);
		qfs.setCRUSERID(LoginUtil.getLoginUserId());
		qfs.setPARENTID(questionid);
		qfs.setQUESTIONID(nextquestionid);
		qfs.setTEXTCONTENT(_contentanswer);
		qfs.setTITLE(null);
		if(_status!=IrpQuestion.REPLYCOMMENTSTATUS){
			this.solrService.addQuestionIndex(qfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
		}
		
		// 保存附件
		if (_jsonFile != null && _jsonFile.length() > 0
				&& !"[]".equals(_jsonFile)) {
			JSONArray _Array = JSONArray.fromObject(_jsonFile);
			for (int i = 0; i < _Array.size(); i++) {
				JSONObject jsonObject = (JSONObject) _Array
						.getJSONObject(i);
				String _sAttFile = jsonObject.getString("attfile");
				String _sAttDesc = jsonObject.getString("attdesc");
				String _sAttLinkAlt = jsonObject.getString("attlinkalt");
				String _sEditversions = jsonObject
						.getString("editversions");
				String _lastName = FileUtil.findFileExt(_sAttFile);
				List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
						.getSuffixExits(_lastName);// 查询所附件类型是否存在

				if (_arrSufflx != null && _arrSufflx.size() > 0) {
					addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
							nextquestionid, _sAttDesc, _sAttLinkAlt,
							_sEditversions);
				} else {// 就是其他类型
					addAttFile(_sAttFile,
							IrpAttachedType.ID_FIELD_NAMEOTHER, nextquestionid,
							_sAttDesc, _sAttLinkAlt, _sEditversions);
				}
			}
		}
		return nStatus;
	}
	private IrpUserroleLinkDAO irpUserroleLinkDAO;

	public IrpUserroleLinkDAO getIrpUserroleLinkDAO() {
		return irpUserroleLinkDAO;
	}

	public void setIrpUserroleLinkDAO(IrpUserroleLinkDAO irpUserroleLinkDAO) {
		this.irpUserroleLinkDAO = irpUserroleLinkDAO;
	}
	@Override
	public int addTextAnswerMobile(String _jsonFile,Long questionid, String text,String _contentanswer,Long browercount,Integer _status,Long _replyqid)
			throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		try {
			long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
			List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(irpuser.getUserid());
			for (long nRoleId : roleIds) {
				if(nRoleId==expertRoleId){
					irpuser.setExpert(true);
				}
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		// 回答该问题的人，当前登录用户
		//IrpUser loginUser = LoginUtil.getLoginUser();
		loginuserid = irpuser.getUserid();
		loginUsername = irpuser.getUsername();
		int nStatus = 0;
		// 答案
		Date date = Calendar.getInstance().getTime();
		Long nextquestionid = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);
		
		
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(nextquestionid);
		irpQuestion.setParentid(questionid);
		irpQuestion.setTextcontent(_contentanswer);
        irpQuestion.setHtmlcontent(text);
		irpQuestion.setIsbestanswer((short) 0);
		

		irpQuestion.setReplyquestionid(_replyqid);
		irpQuestion.setIsexpertanswer((short) 0);
		irpQuestion.setStatus(_status);
		irpQuestion.setCrtime(date);
		irpQuestion.setCruserid(LoginUtil.getLoginUserId());
		irpQuestion.setCruser(loginUsername);
		irpQuestion.setBrowsecount(0l);
		// 如果当前登陆用户为专家,将isexpertAnswer设置为1

		if (SysConfigUtil.getSysConfigValue(IrpQuestion.ENABLEEXPERTANSWER)
				.equals("false")) {
			if (irpuser.isExpert()) {
				irpQuestion.setIsexpertanswer((short) 1);
			}

		} else {
			IrpExpertQuestion irpExpertQuestion = irpExpertQuestionService
					.irpExpertQuestion(questionid);
			if (irpExpertQuestion == null) {
				nStatus = 0;

			} else if (irpExpertQuestion.getExpertid().equals(
					LoginUtil.getLoginUserId())) {
				irpQuestion.setIsexpertanswer((short) 1);
			}

		}

		// 将回答者，问题的id插入irp_expert_question表中
		IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
		irpExpertQuestion.setExpertquestionid(TableIdUtil
				.getNextId(IrpExpertQuestion.TABLE_NAME));
		irpExpertQuestion.setQuestionerid(loginuserid);
		irpExpertQuestion.setQuestionid(questionid);

		if (irpuser.isExpert()) {
			irpExpertQuestion.setExpertid(loginuserid);
		} else {
			irpExpertQuestion.setExpertid(0L);
		}

		IrpQuestion irpQuestion1 = new IrpQuestion();
		irpQuestion1.setQuestionid(questionid);

		if (irpuser.isExpert()) {
			irpQuestion1.setExpertanswerid(loginuserid);
			irpQuestion1.setIsexpertanswer((short) 1);
		}

		irpQuestion1.setExpertanswerid(loginuserid);
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			//更新楼层
			try{
					if (_status==IrpQuestion.REPLYCOMMENTSTATUS) {
					int rootid = replyCommentNum(questionid)-1;
					long rootupdatrnums = 1;	
					if(rootid>0){	
						rootupdatrnums = rootid+1;
					}
					
					updateRIdByQId(nextquestionid,rootupdatrnums);
					
				}
			}finally{
				this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
				this.irpQuestionDAO.updateByPrimaryKeySelective(irpQuestion1);
				nStatus = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//增加索引
		QuestionForSolr qfs = new QuestionForSolr();
		qfs.setCRTIME(date);
		qfs.setCRUSER(loginUsername);
		qfs.setCRUSERID(LoginUtil.getLoginUserId());
		qfs.setPARENTID(questionid);
		qfs.setQUESTIONID(nextquestionid);
		qfs.setTEXTCONTENT(_contentanswer);
		qfs.setTITLE(null);
		if(_status!=IrpQuestion.REPLYCOMMENTSTATUS){
			this.solrService.addQuestionIndex(qfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
		}
		
		// 保存附件
		if (_jsonFile != null && _jsonFile.length() > 0
				&& !"[]".equals(_jsonFile)) {
			JSONArray _Array = JSONArray.fromObject(_jsonFile);
			for (int i = 0; i < _Array.size(); i++) {
				JSONObject jsonObject = (JSONObject) _Array
						.getJSONObject(i);
				String _sAttFile = jsonObject.getString("attfile");
				String _sAttDesc = jsonObject.getString("attdesc");
				String _sAttLinkAlt = jsonObject.getString("attlinkalt");
				String _sEditversions = jsonObject
						.getString("editversions");
				String _lastName = FileUtil.findFileExt(_sAttFile);
				List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
						.getSuffixExits(_lastName);// 查询所附件类型是否存在

				if (_arrSufflx != null && _arrSufflx.size() > 0) {
					addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
							nextquestionid, _sAttDesc, _sAttLinkAlt,
							_sEditversions);
				} else {// 就是其他类型
					addAttFile(_sAttFile,
							IrpAttachedType.ID_FIELD_NAMEOTHER, nextquestionid,
							_sAttDesc, _sAttLinkAlt, _sEditversions);
				}
			}
		}
		return nStatus;
	}
	@Override
	public int modifyQuestion(Long questionid, String _modifyTexttitle,String _modifyTextcontent)
			throws Exception {
		// 0 表示没有修改 ,1 表示修改了1条数据
		int _nStatus = 0;
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionid);
		irpQuestion.setTitle(_modifyTexttitle);
		irpQuestion.setHtmlcontent(_modifyTextcontent);
		try {
			_nStatus = this.irpQuestionDAO
					.updateByPrimaryKeySelective(irpQuestion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (_nStatus==1) {
			//修改索引
			QuestionForSolr qfs = new QuestionForSolr();
			qfs.setQUESTIONID(questionid);
			qfs.setTITLE(_modifyTexttitle);
			this.solrService.updateQuestionIndex(qfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
			
		}
		return _nStatus;
	}

	@Override
	public List<IrpQuestion> findAnswers(Long parentid) throws Exception {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(parentid)
				.andIsbestanswerEqualTo((short) 0)
				.andIsexpertanswerEqualTo((short) 0);
		irpQuestions = irpQuestionDAO.selectByExample(example);
		return irpQuestions;
	}

	@Override
	public List<IrpQuestion> findExpertAnswers(Long parentid) throws Exception {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(parentid)
				.andIsexpertanswerEqualTo((short) 1);
		example.setOrderByClause("crtime desc");
		irpQuestions = irpQuestionDAO.selectByExample(example);
		return irpQuestions;
	}

	@Override
	public List<IrpQuestion> findBestAnswer(Long parentid) throws Exception {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(parentid)
				.andIsbestanswerEqualTo((short) 1);
		irpQuestions = irpQuestionDAO.selectByExample(example);
		return irpQuestions;
	}

	@Override
	public int countAnswers(Long parentid) throws Exception {
		int num = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(parentid)
				.andIsbestanswerEqualTo((short) 0)
				.andIsexpertanswerEqualTo((short) 0);
		num = irpQuestionDAO.countByExample(example);
		return num;
	}

	@Override
	public List<IrpQuestion> findAnswersByPage(PageUtil pageUtil,
			String orderBy, Long parentid) throws Exception {
		List<IrpQuestion> answerList = null;
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			if (orderBy == null || orderBy.equals("")) {
				orderBy = "crtime desc";
			}
			example.setOrderByClause(orderBy);
			example.createCriteria().andParentidEqualTo(parentid)
					.andIsbestanswerEqualTo((short) 0)
					.andIsexpertanswerEqualTo((short) 0);
			answerList = irpQuestionDAO.selectByExample(example, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answerList;
	}

	@Override
	public int modifyStatus(Long answerid, Long questionid, String answer,
			Long browsecount, Long cruserid) throws Exception {
		int _nStatus = 0;
		int _nStatus1 = 0;
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(answerid);
		irpQuestion.setStatus(1);
		irpQuestion.setTextcontent(answer);
		irpQuestion.setCruserid(cruserid);
		irpQuestion.setIsbestanswer((short) 1);
		IrpQuestion irpQuestion1 = new IrpQuestion();
		irpQuestion1.setQuestionid(questionid);
		irpQuestion1.setStatus(1);
		irpQuestion1.setBrowsecount(browsecount);
		try {
			_nStatus = this.irpQuestionDAO
					.updateByPrimaryKeySelective(irpQuestion);
			_nStatus1 = irpQuestionDAO
					.updateByPrimaryKeySelective(irpQuestion1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _nStatus;
	}

	@Override
	public int modifyBestAnswer(Long questionid, String answer)
			throws Exception {
		int _nStatus = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(questionid)
				.andIsbestanswerEqualTo((short) 1);
		irpQuestions = irpQuestionDAO.selectByExample(example);
		// 如果有最佳答案，则update，如没有的话insert
		if (irpQuestions == null) {
			int _nStatus1 = 0;
			IrpQuestion irpQuestion = new IrpQuestion();
			Long questionId1 = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
			irpQuestion.setQuestionid(questionId1);
			irpQuestion.setTextcontent(answer);
			irpQuestion.setIsbestanswer((short) 1);
			irpQuestion.setParentid(questionid);
			irpQuestion.setBrowsecount(0l);
			irpQuestionDAO.insertSelective(irpQuestion);
			// 将该问题置为已解决
			IrpQuestion irpQuestion1 = new IrpQuestion();
			irpQuestion1.setQuestionid(questionid);
			irpQuestion1.setStatus(1);
			_nStatus1 = this.irpQuestionDAO.updateByPrimaryKey(irpQuestion1);
		} else {
			// 把最佳答案取出来，并置为新选的最佳答案
			for (int i = 0; i < irpQuestions.size(); i++) {
				IrpQuestion irpQuestion = irpQuestions.get(i);
				irpQuestion.setTextcontent(answer);
				irpQuestionDAO.updateByPrimaryKeySelective(irpQuestion);
				_nStatus = 1;
			}
		}
		return _nStatus;
	}

	@Override
	public int replyTotal(Long questionid) throws Exception {
		int total = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(questionid)
				.andTextcontentIsNotNull();
		total = irpQuestionDAO.countByExample(example);
		return total;
	}

	@Override
	public int addQuestionAndExpert(String title, String expert, Long expertId,String _questiontitle, String _jsonFile,String categories)
			throws Exception {
		int nStatus = 0;
		Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionId);
		irpQuestion.setCrtime(new Date());
		irpQuestion.setStatus(0);
		irpQuestion.setTitle(_questiontitle);
		irpQuestion.setHtmlcontent(title);
		irpQuestion.setCruserid(LoginUtil.getLoginUserId());
		irpQuestion.setCruser(LoginUtil.getLoginUser().getName());
		irpQuestion.setBrowsecount(0l);
		// 同时将插入irp_expert_question表中
		IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
		Long expertQuestionid = TableIdUtil
				.getNextId(IrpExpertQuestion.TABLE_NAME);// 获得主键
		loginUsername = LoginUtil.getLoginUser().getUsername();
		loginuserid = LoginUtil.getLoginUser().getId();
		irpExpertQuestion.setCrtime(new Date());
		irpExpertQuestion.setExpertquestionid(expertQuestionid);
		irpExpertQuestion.setCruser(loginUsername);
		irpExpertQuestion.setCruserid(loginuserid);
		irpExpertQuestion.setExpertName(expert);
		irpExpertQuestion.setQuestionid(questionId);
		irpExpertQuestion.setExpertid(expertId);
		irpExpertQuestion.setQuestionerid(0L);
		
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
			addCategoryFileLink(questionId,categories);
			// 发送提醒 专家 私信
			IrpMessageContent _irpMessageContent = new IrpMessageContent();
			_irpMessageContent.setFromUid(expertId);
			String sendcontent = "";
			if (_questiontitle.equals("")) {
				sendcontent = title;
			}else{
				sendcontent = _questiontitle;
			}
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    	String sQuestionInfo = "<a class='linkbh14'  href=\""+redirectURI+"/question/questionDetail.action?questionid="+questionId+"\" target=\"_blank\">"+sendcontent+"</a>";

			_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(LoginUtil.getLoginUser()
					.getUsername()) + sQuestionInfo);
			
			/*_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(LoginUtil.getLoginUser()
					.getUsername()) + "向您提了一个问题“<a class=\"linkbh14\" href=\"javascript:void(0)\" onclick=\"connectQuestion("+questionId+")\" >"+sendcontent+"</a>”");
			*/
			irpMessageContentService.addMessageContent(_irpMessageContent);
			// 保存附件
			if (_jsonFile != null && _jsonFile.length() > 0
					&& !"[]".equals(_jsonFile)) {
				JSONArray _Array = JSONArray.fromObject(_jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String _sAttFile = jsonObject.getString("attfile");
					String _sAttDesc = jsonObject.getString("attdesc");
					String _sAttLinkAlt = jsonObject.getString("attlinkalt");
					String _sEditversions = jsonObject
							.getString("editversions");
					String _lastName = FileUtil.findFileExt(_sAttFile);
					List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
							.getSuffixExits(_lastName);// 查询所附件类型是否存在

					if (_arrSufflx != null && _arrSufflx.size() > 0) {
						addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
								questionId, _sAttDesc, _sAttLinkAlt,
								_sEditversions);
					} else {// 就是其他类型
						addAttFile(_sAttFile,
								IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
								_sAttDesc, _sAttLinkAlt, _sEditversions);
					}
				}
			}
			
			
			
			nStatus = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nStatus;
	}
	
	@Override
	public Long addQuestionAndExpert(String title, String expert, Long expertId,String _questiontitle, String _jsonFile,String categories,IrpUser irpUser)
			throws Exception {
		int nStatus = 0;
		Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionId);
		irpQuestion.setCrtime(new Date());
		irpQuestion.setStatus(0);
		irpQuestion.setTitle(_questiontitle);
		irpQuestion.setHtmlcontent(title);
		irpQuestion.setCruserid(irpUser.getUserid());
		irpQuestion.setCruser(irpUser.getUsername());
		irpQuestion.setBrowsecount(0l);
		// 同时将插入irp_expert_question表中
		IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
		Long expertQuestionid = TableIdUtil
				.getNextId(IrpExpertQuestion.TABLE_NAME);// 获得主键
		loginUsername = irpUser.getUsername();
		loginuserid = irpUser.getUserid();
		irpExpertQuestion.setCrtime(new Date());
		irpExpertQuestion.setExpertquestionid(expertQuestionid);
		irpExpertQuestion.setCruser(loginUsername);
		irpExpertQuestion.setCruserid(loginuserid);
		irpExpertQuestion.setExpertName(expert);
		irpExpertQuestion.setQuestionid(questionId);
		irpExpertQuestion.setExpertid(expertId);
		irpExpertQuestion.setQuestionerid(0L);
		
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
			addCategoryFileLink(questionId,categories);
			// 发送提醒 专家 私信
			IrpMessageContent _irpMessageContent = new IrpMessageContent();
			_irpMessageContent.setFromUid(expertId);
			String sendcontent = "";
			if (_questiontitle.equals("")) {
				sendcontent = title;
			}else{
				sendcontent = _questiontitle;
			}
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    	String sQuestionInfo = "<a class='linkbh14'  href=\""+redirectURI+"/question/questionDetail.action?questionid="+questionId+"\" target=\"_blank\">"+sendcontent+"</a>";

			_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(loginUsername) + sQuestionInfo);
			
			/*_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(LoginUtil.getLoginUser()
					.getUsername()) + "向您提了一个问题“<a class=\"linkbh14\" href=\"javascript:void(0)\" onclick=\"connectQuestion("+questionId+")\" >"+sendcontent+"</a>”");
			*/
			irpMessageContentService.addMessageContent(_irpMessageContent);
			// 保存附件
			if (_jsonFile != null && _jsonFile.length() > 0
					&& !"[]".equals(_jsonFile)) {
				JSONArray _Array = JSONArray.fromObject(_jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String _sAttFile = jsonObject.getString("attfile");
					String _sAttDesc = jsonObject.getString("attdesc");
					String _sAttLinkAlt = jsonObject.getString("attlinkalt");
					String _sEditversions = jsonObject
							.getString("editversions");
					String _lastName = FileUtil.findFileExt(_sAttFile);
					List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
							.getSuffixExits(_lastName);// 查询所附件类型是否存在

					if (_arrSufflx != null && _arrSufflx.size() > 0) {
						addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
								questionId, _sAttDesc, _sAttLinkAlt,
								_sEditversions);
					} else {// 就是其他类型
						addAttFile(_sAttFile,
								IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
								_sAttDesc, _sAttLinkAlt, _sEditversions);
					}
				}
			}
			
			
			
			nStatus = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionId;
	}
	
	@Override
	public int addQuestion(String askcontext,String expert,Long expertId, IrpUser irpUser,String categories) {
		int nStatus = 0;
		Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionId);
		irpQuestion.setCrtime(new Date());
		irpQuestion.setStatus(0);
		irpQuestion.setHtmlcontent(askcontext);
		irpQuestion.setCruserid(irpUser.getUserid());
		irpQuestion.setCruser(irpUser.getUsername());
		irpQuestion.setBrowsecount(0l);
		// 同时将插入irp_expert_question表中
		IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
		Long expertQuestionid = TableIdUtil
				.getNextId(IrpExpertQuestion.TABLE_NAME);// 获得主键
		loginUsername = irpUser.getUsername();
		loginuserid = irpUser.getUserid();
		irpExpertQuestion.setCrtime(new Date());
		irpExpertQuestion.setExpertquestionid(expertQuestionid);
		irpExpertQuestion.setCruser(loginUsername);
		irpExpertQuestion.setCruserid(loginuserid);
		irpExpertQuestion.setExpertName(expert);
		irpExpertQuestion.setQuestionid(questionId);
		irpExpertQuestion.setExpertid(expertId);
		irpExpertQuestion.setQuestionerid(0L);
		try {
			this.irpQuestionDAO.insertSelective(irpQuestion);
			this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
			addCategoryFileLink(questionId,categories);
			// 发送提醒 专家 私信
			IrpMessageContent _irpMessageContent = new IrpMessageContent();
			_irpMessageContent.setFromUid(expertId);
			String sendcontent = "";
			sendcontent = askcontext;
			HttpServletRequest request = ServletActionContext.getRequest();
	    	String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    	String sQuestionInfo = "<a class='linkbh14'  href=\""+redirectURI+"/question/questionDetail.action?questionid="+questionId+"\" target=\"_blank\">"+sendcontent+"</a>";

			_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(loginUsername) + sQuestionInfo);
			
			/*_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(LoginUtil.getLoginUser()
					.getUsername()) + "向您提了一个问题“<a class=\"linkbh14\" href=\"javascript:void(0)\" onclick=\"connectQuestion("+questionId+")\" >"+sendcontent+"</a>”");
			*/
			irpMessageContentService.addMessageContent(_irpMessageContent);
			
			nStatus = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nStatus;
	}
	
	@Override
	public List<IrpQuestion> findMeAskQuestion(PageUtil pageUtil,
			Long personid, String orderBy) throws Exception {
		List<IrpQuestion> askList = null;
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			if (orderBy == null || orderBy.equals("")) {
				orderBy = "crtime desc";
			}
			example.setOrderByClause(orderBy);
			example.createCriteria().andCruseridEqualTo(personid)
					.andStatusIsNotNull().andTextcontentIsNull();
			askList = irpQuestionDAO.selectByExample(example, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return askList;
	}

	@Override
	public int countMeAskQuestion(Long personid) throws Exception {
		int count = 0;
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			example.createCriteria().andCruseridEqualTo(personid)
					.andStatusIsNotNull().andTextcontentIsNull();
			count = irpQuestionDAO.countByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<IrpQuestion> findMeAnswerQuestion(PageUtil pageUtil,
			Long personid, String orderBy) throws Exception {
		List<IrpQuestion> questions = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("loginUserId", personid);
		mParam.put("orderStr", "crtime desc");
		try {
			questions = irpQuestionDAO
					.findQuestionsOfMeAnswer(mParam, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public int countMeAnswerQuestion(Long personid) throws Exception {
		int count = 0;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("loginUserId", personid);
		mParam.put("orderStr", "crtime desc");
		try {
			count = irpQuestionDAO.countQuestionsOfMeAnswer(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int modifyBrowCount(Long questionid, Long browseCount)
			throws Exception {
		int nStatus = 0;
		IrpQuestion irpQuestion = new IrpQuestion();
		irpQuestion.setQuestionid(questionid);
		irpQuestion.setBrowsecount(browseCount);
		nStatus = irpQuestionDAO.updateByPrimaryKeySelective(irpQuestion);
		return nStatus;
	}

	@Override
	public List<IrpQuestion> findExpertAnswerThree(Long parentid)
			throws Exception {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(parentid)
				.andIsexpertanswerEqualTo((short) 1);
		irpQuestions = irpQuestionDAO.selectByExample(example);
		return irpQuestions;
	}

	@Override
	public List<IrpAttached> findQuestionAttached(Long _questionid) {
		List<IrpAttached> list = null;
		IrpAttachedExample example = new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_questionid)
				.andAttflagEqualTo(3);
		try {
			list = irpAttachedDAO.selectByExampleWithBLOBs(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpAttached> findQuestionAttached(Long _questionid,Long _typeid) {
		List<IrpAttached> list = null;
		IrpAttachedExample example = new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_questionid)
								.andAttflagEqualTo(3)
								.andTypeidEqualTo(_typeid);
		try {
			list = irpAttachedDAO.selectByExampleWithBLOBs(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpAttached> findQuestionPic(Long _questionid) {
		List<IrpAttached> list = null;
		IrpAttachedExample example = new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_questionid)
				.andAttflagEqualTo(3).andTypeidEqualTo(2L);
		try {
			list = irpAttachedDAO.selectByExampleWithBLOBs(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpQuestion findQuestionByParentid(Long _parentid) {
		// TODO Auto-generated method stub
		IrpQuestion irpQuestion = null;
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidEqualTo(_parentid)
			                    .andStatusEqualTo((long)IrpQuestion.STATUS_YES);
		try {
			List list = this.irpQuestionDAO.selectByExample(example);
			if (list.size()>0 && list!=null) {
				irpQuestion = this.irpQuestionDAO.selectByExample(example).get(0);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpQuestion;
	}

	@Override
	public List<IrpQuestion> findNewestOrHotQuestion(String _orderby,
			Integer _pageSize, Integer type) {
		// TODO Auto-generated method stub
		List<IrpQuestion> list = new ArrayList();
		IrpQuestionExample example = new IrpQuestionExample();
		if (type == 1) {
			example.createCriteria().andStatusIsNotNull().andTextcontentIsNull();
		} else {
			example.createCriteria().andStatusIsNotNull()
					.andBrowsecountIsNotNull().andTextcontentIsNull();
		}
		example.setOrderByClause(_orderby);
		PageUtil pageUtil = new PageUtil(1, _pageSize, 0);
		try {
			list = this.irpQuestionDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List findIntelligentUser() {
		// TODO Auto-generated method stub
		List list = new ArrayList();

		try {
			list = this.irpQuestionDAO.findIntelligentUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpQuestion> findALlQuesAnsbyway(PageUtil pageUtil,
			Map<String, Object> map) {
		List<IrpQuestion> list = null;
		IrpQuestionExample example = new IrpQuestionExample();
		Criteria cri=example.createCriteria();
		if(map.get("selectvalue").equals("0")){
			//搜所有
		}else if(map.get("selectvalue").equals("1")){
			//搜问题
			cri.andParentidIsNull();
			if(map.get("keyword")!=null){
				cri.andTitleLike(map.get("keyword").toString());
			}
		}else if(map.get("selectvalue").equals("2")){
			//搜答案
			cri.andParentidIsNotNull();
			if(map.get("keyword")!=null){
				cri.andTextcontentLike(map.get("keyword").toString());
			}
		}
		if (map.get("cruser") != null) {
			cri.andCruserEqualTo(map.get("cruser").toString());
		}
		if(map.get("time")!=null){
			cri.andCrtimeGreaterThanOrEqualTo((Date) map.get("time"));
		}
		if(map.get("endtime")!=null){
			cri.andCrtimeLessThanOrEqualTo((Date) map.get("endtime"));
			
		}
		if(map.get("sOrderByClause")!=null){
			example.setOrderByClause(map.get("sOrderByClause").toString());
		}
		try {
			list = this.irpQuestionDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int findALlQuesAnsbywaycount(Map<String, Object> map) {
		int count = 0;
		IrpQuestionExample example = new IrpQuestionExample();
		Criteria cri=example.createCriteria();
		if(map.get("selectvalue").equals("0")){
			//搜所有
		}else if(map.get("selectvalue").equals("1")){
			//搜问题
			cri.andParentidIsNull();
			if(map.get("keyword")!=null){
				cri.andTitleLike(map.get("keyword").toString());
			}
		}else if(map.get("selectvalue").equals("2")){
			//搜答案
			cri.andParentidIsNotNull();
			if(map.get("keyword")!=null){
				cri.andTextcontentLike(map.get("keyword").toString());
			}
		}
		if (map.get("cruser") != null) {
			cri.andCruserEqualTo(map.get("cruser").toString());
		}
		if(map.get("time")!=null){
			cri.andCrtimeGreaterThanOrEqualTo((Date) map.get("time"));
		}
		if(map.get("endtime")!=null){
			cri.andCrtimeLessThanOrEqualTo((Date) map.get("endtime"));
		}
		try {
			count = this.irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Set<Long> findNnewstAnswer(int _length) {
		// TODO Auto-generated method stub
		List<IrpQuestion> list = null;
		List<IrpQuestion> listanwser = null;
		List<Long> quesitonlonglist = new ArrayList();
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria().andParentidIsNotNull();
		example.setOrderByClause("crtime desc");
		Set set = new LinkedHashSet();
		try {
			 list = this.irpQuestionDAO.selectByExample(example);
			 if (list.size()>0 && list!=null) {
				 for (int i = 0; i < list.size(); i++) {
						if(set.size()<_length){
							set.add(list.get(i).getParentid());	
							quesitonlonglist.add(list.get(i).getParentid());
						}else{
							break;
						}
					}
			 }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return set;
	}

	@Override
	public int deleteAnswerByQuestionId(Long _questionid) {
		// TODO Auto-generated method stub
		int status = 0;
		if(_questionid!=null){
			try {
				status = this.irpQuestionDAO.deleteByPrimaryKey(_questionid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return status;
	}

	@Override
	public int updateQuestionStatus(Long _questionid, Integer _status) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpQuestion record = new IrpQuestion();
		record.setQuestionid(_questionid);
		record.setStatus(_status);
		try {
			status = this.irpQuestionDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteQuestionAndAnswers(Long _questionid) {
		// TODO Auto-generated method stub
		int status = 0;
		if(_questionid!=null){
			IrpQuestionExample example = new IrpQuestionExample();
			example.createCriteria().andParentidEqualTo(_questionid);
			
			try {
				status += this.irpQuestionDAO.deleteByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return status;
	}
	@Override
	public List<IrpQuestion> highChartsByExprt(Date startTime, Date endTime,
			Long _status, Long parentId) {
		List<IrpQuestion> questions=null;
		IrpQuestionExample example= new IrpQuestionExample();
		Criteria criteria=example.createCriteria();
		criteria.andCrtimeBetween(startTime, endTime);
//		if(parentId!=null && parentId!=0L){// 回答
//			criteria.andParentidNotEqualTo(0L);
//		}else{//问题
//			criteria.andParentidEqualTo(0L);
//		}
		criteria.andStatusEqualTo(_status);
		try {
			questions=irpQuestionDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public long getQuqestionTotalByCondition(Integer _titlestatus,Integer _status,Date _starttime,Date _endtime) {
		// TODO Auto-generated method stub
		long totalnum = 0l;
		IrpQuestionExample example = new IrpQuestionExample();
		if(_titlestatus==IrpQuestion.ISQUESTION){
			if(_status==IrpQuestion.ISBESTQUESTION){
				example.createCriteria().andParentidIsNull()
										.andStatusEqualTo((long)IrpQuestion.STATUS_YES)
										.andCrtimeGreaterThanOrEqualTo(_starttime)
										.andCrtimeLessThan(_endtime);
			}else{
				example.createCriteria().andParentidIsNull()
										.andCrtimeGreaterThanOrEqualTo(_starttime)
										.andCrtimeLessThan(_endtime);
			}
		}else if(_titlestatus==IrpQuestion.ISANSWER){
			example.createCriteria().andParentidIsNotNull()
									.andCrtimeGreaterThanOrEqualTo(_starttime)
									.andCrtimeLessThan(_endtime);
		}
		try {
			totalnum = this.irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalnum;
	}

	@Override
	public List<IrpQuestion> getQuestionTotalByTime(Date _starttime,
			Date _endtime, Integer _titlestatus, Integer _status) {
		// TODO Auto-generated method stub
		List<IrpQuestion> list = null;
		IrpQuestionExample example = new IrpQuestionExample();
		if(_titlestatus==IrpQuestion.ISQUESTION){
			if(_status==IrpQuestion.ISBESTQUESTION){
				example.createCriteria().andParentidIsNull()
										.andStatusEqualTo((long)IrpQuestion.STATUS_YES)
										.andCrtimeBetween(_starttime, _endtime);
				
			}else{
				example.createCriteria().andParentidIsNull()
										.andCrtimeBetween(_starttime, _endtime);
			}
		}else if(_titlestatus==IrpQuestion.ISANSWER){
			example.createCriteria().andParentidIsNotNull()
									.andCrtimeBetween(_starttime, _endtime);
		}
		
		try {
			list = this.irpQuestionDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public long getQuestionNumOfDate(Date _time, Integer _titlestatus,
			Integer _status) {
		// TODO Auto-generated method stub
		long num =0;
		Date date = _time;
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		Calendar cale = Calendar.getInstance();
		cale.setTime(_time);
		cale.add(Calendar.DAY_OF_MONTH,1);
		Date date2 = cale.getTime();
		date2.setHours(0);
		date2.setMinutes(0);
		date2.setSeconds(0);
		IrpQuestionExample example = new IrpQuestionExample();
		if(_titlestatus==IrpQuestion.ISQUESTION){
			if(_status==IrpQuestion.ISBESTQUESTION){
				example.createCriteria().andParentidIsNull()
										.andStatusEqualTo((long)IrpQuestion.STATUS_YES)
										.andCrtimeGreaterThanOrEqualTo(date)
										.andCrtimeLessThan(date2);
				
			}else{
				example.createCriteria().andParentidIsNull()
										.andCrtimeGreaterThanOrEqualTo(date)
										.andCrtimeLessThan(date2);
			}
		}else if(_titlestatus==IrpQuestion.ISANSWER){
			example.createCriteria().andParentidIsNotNull()
									.andCrtimeGreaterThanOrEqualTo(date)
									.andCrtimeLessThan(date2);
		}
		try {
			num = this.irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<IrpCategory> getCategoryByQuestionId(Long questionId) {
		List<Long> categoryIds = new ArrayList<Long>();
		List<Long> childrenIds = new ArrayList<Long>();
		try {
			Long categoryQuestionId = Long.valueOf(SysConfigUtil.getSysConfigNumValue("CATEGORY_QUESTION"));
			if(categoryQuestionId.intValue()<1){
				return null;
			}else{
				childrenIds = getCategoryFileLinkIds(categoryQuestionId.intValue(), childrenIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(childrenIds==null || childrenIds.size()==0){
			return null;
		}
		categoryIds = getCategoryIds(questionId);
		List<Long> newcategoryIds = new ArrayList<Long>();
		if(categoryIds!=null && categoryIds.size()>0){
			for(Long categoryId : categoryIds){
				if(childrenIds.contains(categoryId)){
					newcategoryIds.add(categoryId);
					continue;
				}
			}
			if(newcategoryIds==null || newcategoryIds.size()==0){
				return null;
			}
			IrpCategoryExample ice = new IrpCategoryExample();
			ice.createCriteria().andStatusEqualTo(IrpCategory.STATUS_START.intValue())
								.andCategoryidIn(newcategoryIds);
			try {
				List<IrpCategory> categoryList = irpCategoryDAO.selectByExample(ice);
				return categoryList;
			} catch (SQLException e) {
				e.printStackTrace();
				return null ;
			}
		}
		return null;
	}
	
	public List<Long> getCategoryIds(Long questionId){
		IrpCategoryFileLinkExample example = new IrpCategoryFileLinkExample();
		List<Long> cid = new ArrayList<Long>();
		example.createCriteria().andFidEqualTo(questionId);
		List<IrpCategoryFileLink> icflList;
		try {
			icflList = irpCategoryFileLinkDAO.selectByExample(example);
			if(icflList!=null && icflList.size()>0){
				for(IrpCategoryFileLink icflEle : icflList){
					if(cid.contains(icflEle.getCid())){
						continue;
					}
					cid.add(icflEle.getCid());
				}
				return cid;
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Long> getCategoryFileLinkIds(int categoryId,List<Long> categoryFileLinkIds){
		if(categoryFileLinkIds==null || categoryFileLinkIds.size()==0){
			categoryFileLinkIds.add(Long.valueOf(categoryId));
		}
		IrpCategoryExample categoryExample = new IrpCategoryExample();
		categoryExample.createCriteria().andCparentidEqualTo(Long.valueOf(categoryId))
									//	.andCtypeEqualTo(IrpCategory.CATEGORY_TYPE_QUESTION.intValue())
										.andStatusEqualTo(IrpCategory.STATUS_START.intValue());
		try {
			List<IrpCategory> categoryList = irpCategoryDAO.selectByExample(categoryExample);
			if(categoryList!=null && categoryList.size()>0){
				for(IrpCategory ele : categoryList){
					categoryFileLinkIds.add(ele.getCategoryid());
					getCategoryFileLinkIds(ele.getCategoryid().intValue(),categoryFileLinkIds);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return categoryFileLinkIds;
	}

	@Override
	public int findAllQuestionsInCategory(Map<String, Object> map)
			throws Exception {
		int nCount = 0;
		List<Long> questionIds = new ArrayList<Long>();
		IrpQuestionExample example = new IrpQuestionExample();
		Criteria c = example.createCriteria();
		String cateId = map.get("categoryId").toString();
		if(cateId!=null && !"".equals(cateId.trim()) && Integer.parseInt(cateId.trim())!=0){
			List<Long> categoryFileLinkIds = new ArrayList<Long>();
			categoryFileLinkIds = getCategoryFileLinkIds(Integer.parseInt(cateId.trim()), categoryFileLinkIds);
			//通过类别id查找出相应的questionids
			IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
			if(categoryFileLinkIds!=null && categoryFileLinkIds.size()>0){
				if(categoryFileLinkIds.size()>1000){
					List<Long> tempCategoryFileLinkIds = new ArrayList<Long>();
					for(int i = 0 ;i<categoryFileLinkIds.size();i++){
						tempCategoryFileLinkIds.add(categoryFileLinkIds.get(i));
						if(tempCategoryFileLinkIds.size()==1000){
							irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
							tempCategoryFileLinkIds = new ArrayList<Long>();
						}
					}
					if(tempCategoryFileLinkIds!=null && tempCategoryFileLinkIds.size()>0){
						irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
					}
				}else{
					irpCategoryFileLinkExample.createCriteria().andCidIn(categoryFileLinkIds);
				}
				try {
					List<IrpCategoryFileLink> irpCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(irpCategoryFileLinkExample);
					if(irpCategoryFileLinkList!=null && irpCategoryFileLinkList.size()>0){
						for(IrpCategoryFileLink icfl : irpCategoryFileLinkList){
							questionIds.add(icfl.getFid());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				/**
				 * 去掉重复项
				 */
				if(questionIds!=null && questionIds.size()>0){
					for ( int i = 0 ; i < questionIds.size() - 1 ; i ++ ) {
					    for ( int j = questionIds.size() - 1 ; j > i; j -- ) {
					       if (questionIds.get(j).toString().equals(questionIds.get(i).toString())) {
					    	   questionIds.remove(j);
					       }
					    }
					}
					if(questionIds.size()>1000){
						List<Long> tempQuestionIds = new ArrayList<Long>();
						for(int i = 0 ;i<questionIds.size();i++){
							tempQuestionIds.add(questionIds.get(i));
							if(tempQuestionIds.size()==1000){
								example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
								tempQuestionIds = new ArrayList<Long>();
							}
						}
						if(tempQuestionIds!=null && tempQuestionIds.size()>0){
							example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
						}
					}else{
						c.andQuestionidIn(questionIds);
					}
				}
			}else{
				return 0;
			}
		}
		c.andStatusIsNotNull().andTextcontentIsNull();
		String qstatus = map.get("needDeal")+"";
		String myQuestion = map.get("myQuestion")+"";
		String myAnswer = map.get("myAnswer")+"";
		if((myQuestion!=null && "true".equals(myQuestion.trim())) || (myAnswer!=null && "true".equals(myAnswer.trim()))){
			c.andCruseridEqualTo(LoginUtil.getLoginUserId());
		}
		if(qstatus!=null && !"".equals(qstatus.trim())){
			if("needDeal".equals(qstatus)){
				c.andStatusEqualTo(0L);
			}else if("complete".equals(qstatus.trim())){
				c.andStatusEqualTo(1L);
			}
		}
		try {
			if(myAnswer!=null && "true".equals(myAnswer.trim())){
				Set set = new HashSet();
				List list = this.irpQuestionDAO.selectByExample(example);
				if (list.size() <= 0) {
					return nCount;
				}
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
					set.add(irpQuestion.getParentid());

				}
				nCount = set.size();
			}else{
				nCount = irpQuestionDAO.countByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpQuestion> findQuestionsInCategoryByPage(PageUtil pageUtil,
			String _sOrderBy, Map<String, Object> map) throws Exception {
		List<IrpQuestion> questionList = null;
		List<Long> questionIds = new ArrayList<Long>();
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			Criteria c = example.createCriteria();
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			String cateId = map.get("categoryId").toString();
			if(cateId!=null && !"".equals(cateId.trim()) && Integer.parseInt(cateId.trim())!=0){
				List<Long> categoryFileLinkIds = new ArrayList<Long>();
				categoryFileLinkIds = getCategoryFileLinkIds(Integer.parseInt(cateId.trim()), categoryFileLinkIds);
				//通过类别id查找出相应的questionids
				IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
				if(categoryFileLinkIds.size()>1000){
					List<Long> tempCategoryFileLinkIds = new ArrayList<Long>();
					for(int i = 0 ;i<categoryFileLinkIds.size();i++){
						tempCategoryFileLinkIds.add(categoryFileLinkIds.get(i));
						if(tempCategoryFileLinkIds.size()==1000){
							irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
							tempCategoryFileLinkIds = new ArrayList<Long>();
						}
					}
					if(tempCategoryFileLinkIds!=null && tempCategoryFileLinkIds.size()>0){
						irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
					}
				}else{
					irpCategoryFileLinkExample.createCriteria().andCidIn(categoryFileLinkIds);
				}
				try {
					List<IrpCategoryFileLink> irpCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(irpCategoryFileLinkExample);
					if(irpCategoryFileLinkList!=null && irpCategoryFileLinkList.size()>0){
						for(IrpCategoryFileLink icfl : irpCategoryFileLinkList){
							questionIds.add(icfl.getFid());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if(questionIds!=null && questionIds.size()>0){
					for ( int i = 0 ; i < questionIds.size() - 1 ; i ++ ) {
					    for ( int j = questionIds.size() - 1 ; j > i; j -- ) {
					       if (questionIds.get(j).toString().equals(questionIds.get(i).toString())) {
					    	   questionIds.remove(j);
					       }
					    }
					}
					if(questionIds.size()>1000){
						List<Long> tempQuestionIds = new ArrayList<Long>();
						for(int i = 0 ;i<questionIds.size();i++){
							tempQuestionIds.add(questionIds.get(i));
							if(tempQuestionIds.size()==1000){
								example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
								tempQuestionIds = new ArrayList<Long>();
							}
						}
						if(tempQuestionIds!=null && tempQuestionIds.size()>0){
							example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
						}
					}else{
						c.andQuestionidIn(questionIds);
					}
				}else{
					return null;
				}
			}
			example.setOrderByClause(_sOrderBy);
			c.andStatusIsNotNull().andTextcontentIsNull();
			String status = map.get("needDeal")+"";
			String myQuestion = map.get("myQuestion")+"";
			String myAnswer = map.get("myAnswer")+"";
			if((myQuestion!=null && "true".equals(myQuestion.trim()))){
				c.andCruseridEqualTo(LoginUtil.getLoginUserId());
			}
//			if((myQuestion!=null && "true".equals(myQuestion.trim())) || (myAnswer!=null && "true".equals(myAnswer.trim()))){
//				c.andCruseridEqualTo(LoginUtil.getLoginUserId());
//			}
			if(status!=null && "needDeal".equals(status.trim())){
				c.andStatusEqualTo(0L);
			}else if(status!=null && "complete".equals(status.trim())){
				c.andStatusEqualTo(1L);
			}
			if(myAnswer!=null && "true".equals(myAnswer.trim())){
				Set set = new HashSet();
				List list = this.irpQuestionDAO.selectByExample(example);
				if (list.size() <= 0) {
					return null;
				}
				Iterator iterator = list.iterator();
				IrpQuestionExample examplelisttem;
				while (iterator.hasNext()) {
					IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
					examplelisttem = new IrpQuestionExample();
					examplelisttem.createCriteria().andParentidEqualTo(irpQuestion.getQuestionid())
												.andCruseridEqualTo(LoginUtil.getLoginUserId())
												.andTextcontentIsNotNull();
					List listtem = irpQuestionDAO.selectByExample(examplelisttem);
					if(listtem!=null && listtem.size()>0){
						set.add(irpQuestion.getQuestionid());
					}
				}

				List<Long> listlong = new ArrayList<Long>(set);
				if(listlong!=null && listlong.size()>0){
					IrpQuestionExample examplelist = new IrpQuestionExample();
					examplelist.createCriteria().andQuestionidIn(listlong);
					questionList = this.irpQuestionDAO.selectByExample(examplelist,
							pageUtil);
				}else{
					return null;
				}
			}else{
				questionList = irpQuestionDAO.selectByExample(example, pageUtil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}
	
	@Override
	public List<IrpQuestion> findQuestionsInCategoryByPage1(PageUtil pageUtil,
			String _sOrderBy, Map<String, Object> map) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		List<IrpQuestion> questionList = null;
		List<Long> questionIds = new ArrayList<Long>();
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			Criteria c = example.createCriteria();
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			String cateId = map.get("categoryId").toString();
			if(cateId!=null && !"".equals(cateId.trim()) && Integer.parseInt(cateId.trim())!=0){
				List<Long> categoryFileLinkIds = new ArrayList<Long>();
				categoryFileLinkIds = getCategoryFileLinkIds(Integer.parseInt(cateId.trim()), categoryFileLinkIds);
				//通过类别id查找出相应的questionids
				IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
				if(categoryFileLinkIds.size()>1000){
					List<Long> tempCategoryFileLinkIds = new ArrayList<Long>();
					for(int i = 0 ;i<categoryFileLinkIds.size();i++){
						tempCategoryFileLinkIds.add(categoryFileLinkIds.get(i));
						if(tempCategoryFileLinkIds.size()==1000){
							irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
							tempCategoryFileLinkIds = new ArrayList<Long>();
						}
					}
					if(tempCategoryFileLinkIds!=null && tempCategoryFileLinkIds.size()>0){
						irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
					}
				}else{
					irpCategoryFileLinkExample.createCriteria().andCidIn(categoryFileLinkIds);
				}
				try {
					List<IrpCategoryFileLink> irpCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(irpCategoryFileLinkExample);
					if(irpCategoryFileLinkList!=null && irpCategoryFileLinkList.size()>0){
						for(IrpCategoryFileLink icfl : irpCategoryFileLinkList){
							questionIds.add(icfl.getFid());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if(questionIds!=null && questionIds.size()>0){
					for ( int i = 0 ; i < questionIds.size() - 1 ; i ++ ) {
					    for ( int j = questionIds.size() - 1 ; j > i; j -- ) {
					       if (questionIds.get(j).toString().equals(questionIds.get(i).toString())) {
					    	   questionIds.remove(j);
					       }
					    }
					}
					if(questionIds.size()>1000){
						List<Long> tempQuestionIds = new ArrayList<Long>();
						for(int i = 0 ;i<questionIds.size();i++){
							tempQuestionIds.add(questionIds.get(i));
							if(tempQuestionIds.size()==1000){
								example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
								tempQuestionIds = new ArrayList<Long>();
							}
						}
						if(tempQuestionIds!=null && tempQuestionIds.size()>0){
							example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
						}
					}else{
						c.andQuestionidIn(questionIds);
					}
				}else{
					return null;
				}
			}
			example.setOrderByClause(_sOrderBy);
			c.andStatusIsNotNull().andTextcontentIsNull();
			String status = map.get("needDeal")+"";
			String myQuestion = map.get("myQuestion")+"";
			String myAnswer = map.get("myAnswer")+"";
			if((myQuestion!=null && "true".equals(myQuestion.trim())) || (myAnswer!=null && "true".equals(myAnswer.trim()))){
				c.andCruseridEqualTo(irpuser.getUserid());
			}
			if(status!=null && "needDeal".equals(status.trim())){
				c.andStatusEqualTo(0L);
			}else if(status!=null && "complete".equals(status.trim())){
				c.andStatusEqualTo(1L);
			}
			if(myAnswer!=null && "true".equals(myAnswer.trim())){
				Set set = new HashSet();
				List list = this.irpQuestionDAO.selectByExample(example);
				if (list.size() <= 0) {
					return null;
				}
				Iterator iterator = list.iterator();
				IrpQuestionExample examplelisttem;
				while (iterator.hasNext()) {
					IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
					examplelisttem = new IrpQuestionExample();
					examplelisttem.createCriteria().andParentidEqualTo(irpQuestion.getQuestionid())
												.andCruseridEqualTo(irpuser.getUserid())
												.andTextcontentIsNotNull();
					List listtem = irpQuestionDAO.selectByExample(examplelisttem);
					if(listtem!=null && listtem.size()>0){
						set.add(irpQuestion.getQuestionid());
					}
				}

				List<Long> listlong = new ArrayList<Long>(set);
				if(listlong!=null && listlong.size()>0){
					IrpQuestionExample examplelist = new IrpQuestionExample();
					examplelist.createCriteria().andQuestionidIn(listlong);
					questionList = this.irpQuestionDAO.selectByExample(examplelist,
							pageUtil);
				}else{
					return null;
				}
			}else{
				questionList = irpQuestionDAO.selectByExample(example, pageUtil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public int replyCommentNum(long _questionid) {
		// TODO Auto-generated method stub
		int count = 0;
		IrpQuestionExample  example = new  IrpQuestionExample();
		example.createCriteria().andTitleIsNull()
								.andStatusEqualTo((long)IrpQuestion.REPLYCOMMENTSTATUS)
								.andParentidEqualTo(_questionid);	
		try {
			count = this.irpQuestionDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public List<IrpQuestion> getReplyCList(long _questionid,PageUtil _pageutil){
		// TODO Auto-generated method stub
		List<IrpQuestion>  list = null;
		IrpQuestionExample  example = new  IrpQuestionExample();
		example.createCriteria().andTitleIsNull()
								.andStatusEqualTo((long)IrpQuestion.REPLYCOMMENTSTATUS)
								.andParentidEqualTo(_questionid);
		example.setOrderByClause("crtime desc");
		try {
			list = this.irpQuestionDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	@Override
	public int updateRIdByQId(long _questionid,long _num) {
		// TODO Auto-generated method stub
		int status  = 0;
		if(_questionid>0){
			IrpQuestion iq = new IrpQuestion();
			iq.setQuestionid(_questionid);
			iq.setRootid(_num);
			try {
				status = this.irpQuestionDAO.updateByPrimaryKeySelective(iq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public List<IrpQuestion> getConnQList(PageUtil pageutil) {
		// TODO Auto-generated method stub
		List<IrpQuestion>  list = null;
		
	   IrpQuestionExample example = new IrpQuestionExample();
	   example.createCriteria().andParentidIsNull()
	   							.andStatusEqualTo((long)IrpQuestion.STATUS_YES);
	   example.setOrderByClause("browsecount desc");
	   
	   
	   
		try {
			list = this.irpQuestionDAO.selectByExample(example, pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getConnQListCount() {
		// TODO Auto-generated method stub
		List<IrpQuestion>  list = null;
		
	   IrpQuestionExample example = new IrpQuestionExample();
	   example.createCriteria().andParentidIsNull()
	   							.andStatusEqualTo((long)IrpQuestion.STATUS_YES);
	   example.setOrderByClause("browsecount desc");
	   
	   try {
			list = this.irpQuestionDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list.size();
	}
	
	@Override
	public List<IrpQuestion> getConnQList() {
		// TODO Auto-generated method stub
		List<IrpQuestion>  list = null;
		
	   IrpQuestionExample example = new IrpQuestionExample();
	   example.createCriteria().andParentidIsNull()
	   							.andStatusEqualTo((long)IrpQuestion.STATUS_YES);
	   example.setOrderByClause("browsecount desc");
	   PageUtil pageutil = new PageUtil(1,5,0);
	   try {
			list = this.irpQuestionDAO.selectByExample(example, pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * sheet计数器
	 */
	private int sheetNum = 0;
	private Long SignidState;
	
	public Long getSignidState() {
		return SignidState;
	}

	public void setSignidState(Long signidState) {
		SignidState = signidState;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	@Override
	public void exportAllQuestionToZip(List<IrpQuestionExcel> list,String getPath,String fileName) {
		try {
			//创建文件路径
			String path=getPath+"/ques";
			// 创建文件夹;
			createFile(path);
			// 创建excel文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path+"/"
					+ fileName+"_"+".xls"));
			// 接收返回的isend,用来判断是否循环到最后
			int count = 0;
			
			// 初始化用户id
			this.SignidState = list.get(0).getCruserid();
			if (list != null && list.size() != 0) {
				do {
					count = createAllSignToExcel(list, workbook);
				} while (count != -1);
			}
			// 写入Excel表格中;
			workbook.write();
			// 关闭流;
			workbook.close();
			// 生成.zip文件;
			craeteZipPath(path,fileName);
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
	private IrpSignInfoDAO signDao;
	
	public IrpSignInfoDAO getSignDao() {
		return signDao;
	}

	public void setSignDao(IrpSignInfoDAO signDao) {
		this.signDao = signDao;
	}

	/**
	 * 循环写入Excel
	 * 
	 * @param signInfo
	 * @return
	 */
	private int createAllSignToExcel(List<IrpQuestionExcel> question,
			WritableWorkbook workbook) throws Exception {
		// 获得当前用户的trueName
		//Map uid = new HashMap();
		//uid.put("userid", SignidState);
		//String userName = signDao.findUserTrueNameById(uid);
		// 循环中计数器
		int count = 0;
		// 判断是不是最后一个 1代表不是最后一个, -1代表是最后一个
		int isend = 1;
		// 是否循环过数据
		boolean isexport = false;
		// 创建第一个sheet文件;
		WritableSheet sheet = workbook.createSheet("sheet", sheetNum);
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
		Label label = new Label(0, 0, "问题", cellFormat1);
		Label label1 = new Label(1, 0, "最佳答案", cellFormat1);
		Label label2 = new Label(2, 0, "专家答案", cellFormat1);
		Label label3 = new Label(3, 0, "其他答案", cellFormat1);

		sheet.addCell(label);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		// 行数计数器
		int i = 0;
		System.out.println("=========="+question.size()+"============");
		for (; count < question.size(); count++) {
			IrpQuestionExcel isi = question.get(count);
			//if (isi.getCruserid().equals(SignidState) && !isexport) {

				Label lab1 = new Label(0, i + 1, isi.getTitle(), cellFormat1);
				Label lab2 = new Label(1, i + 1, isi.getBestanswer(), cellFormat1);
				Label lab3 = new Label(2, i + 1, isi.getExperanswer(), cellFormat1);
				Label lab4 = new Label(3, i + 1, isi.getTextcontent(),cellFormat1);
				sheet.addCell(lab1);
				sheet.addCell(lab2);
				sheet.addCell(lab3);
				sheet.addCell(lab4);
				i++;
				if (count == (question.size() - 1)) {
					isend = -1;
				}
				/*if (isend != -1 && !question.get(count + 1).getCruserid().equals(SignidState)) {
					isexport = true;
				}*/

			/*} else if (isexport && !isi.getCruserid().equals(SignidState)) {
				SignidState = isi.getCruserid();
				break;
			}*/
			sheetNum++;
		}
		return isend;
	}
	/**
	 * 创建文件夹;
	 * 
	 * @param path
	 * @return
	 */
	public String createFile(String path) {
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
	/**
	 * 生成.zip文件;
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void craeteZipPath(String path,String zipName) throws IOException {
		//System.out.println("进入生成zip方法");
		ZipOutputStream zipOutputStream = null;
		File file = new File(path
				+ zipName
				+ ".zip");
		zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		File[] files = new File(path).listFiles();
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
	 * 删除目录下所有的文件;
	 * 
	 * @param path
	 */
	public boolean deleteExcelPath(File file) {
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
	
	@Override
    public int addQuestionAndExpertForApp(String title, String expert, Long expertId,String _questiontitle, String _jsonFile,String categories, IrpUser user)
            throws Exception {
        int nStatus = 0;
        Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
        IrpQuestion irpQuestion = new IrpQuestion();
        irpQuestion.setQuestionid(questionId);
        irpQuestion.setCrtime(new Date());
        irpQuestion.setStatus(0);
        irpQuestion.setTitle(_questiontitle);
        irpQuestion.setHtmlcontent(title);
        irpQuestion.setCruserid(user.getUserid());
        irpQuestion.setCruser(user.getName());
        irpQuestion.setBrowsecount(0l);
        // 同时将插入irp_expert_question表中
        IrpExpertQuestion irpExpertQuestion = new IrpExpertQuestion();
        Long expertQuestionid = TableIdUtil
                .getNextId(IrpExpertQuestion.TABLE_NAME);// 获得主键
        loginUsername = user.getUsername();
        loginuserid = user.getId();
        irpExpertQuestion.setCrtime(new Date());
        irpExpertQuestion.setExpertquestionid(expertQuestionid);
        irpExpertQuestion.setCruser(loginUsername);
        irpExpertQuestion.setCruserid(loginuserid);
        irpExpertQuestion.setExpertName(expert);
        irpExpertQuestion.setQuestionid(questionId);
        irpExpertQuestion.setExpertid(expertId);
        irpExpertQuestion.setQuestionerid(0L);
        
        try {
            this.irpQuestionDAO.insertSelective(irpQuestion);
            this.irpExpertQuestionDAO.insertSelective(irpExpertQuestion);
            addCategoryFileLink(questionId,categories);
            // 发送提醒 专家 私信
            IrpMessageContent _irpMessageContent = new IrpMessageContent();
            _irpMessageContent.setFromUid(expertId);
            String sendcontent = "";
            if (_questiontitle.equals("")) {
                sendcontent = title;
            }else{
                sendcontent = _questiontitle;
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String sQuestionInfo = "<a class='linkbh14'  href=\""+redirectURI+"/question/questionDetail.action?questionid="+questionId+"\" target=\"_blank\">"+sendcontent+"</a>";

            _irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(user
                    .getUsername()) + sQuestionInfo);
            
            /*_irpMessageContent.setContent(new LoginUtil().getShowNameByUsername(LoginUtil.getLoginUser()
                    .getUsername()) + "向您提了一个问题“<a class=\"linkbh14\" href=\"javascript:void(0)\" onclick=\"connectQuestion("+questionId+")\" >"+sendcontent+"</a>”");
            */
            irpMessageContentService.addMessageContent(_irpMessageContent);
            // 保存附件
            if (_jsonFile != null && _jsonFile.length() > 0
                    && !"[]".equals(_jsonFile)) {
                JSONArray _Array = JSONArray.fromObject(_jsonFile);
                for (int i = 0; i < _Array.size(); i++) {
                    JSONObject jsonObject = (JSONObject) _Array
                            .getJSONObject(i);
                    String _sAttFile = jsonObject.getString("attfile");
                    String _sAttDesc = jsonObject.getString("attdesc");
                    String _sAttLinkAlt = jsonObject.getString("attlinkalt");
                    String _sEditversions = jsonObject
                            .getString("editversions");
                    String _lastName = FileUtil.findFileExt(_sAttFile);
                    List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
                            .getSuffixExits(_lastName);// 查询所附件类型是否存在

                    if (_arrSufflx != null && _arrSufflx.size() > 0) {
                        addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
                                questionId, _sAttDesc, _sAttLinkAlt,
                                _sEditversions);
                    } else {// 就是其他类型
                        addAttFile(_sAttFile,
                                IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
                                _sAttDesc, _sAttLinkAlt, _sEditversions);
                    }
                }
            }
            nStatus = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nStatus;
    }
	
	@Override
    public long addQuestionForApp(String title, String _jsonFile,String _questiontitle,String categories, IrpUser user) {
        Long questionId = TableIdUtil.getNextId(IrpQuestion.TABLE_NAME);// 获得主键
        IrpQuestion irpQuestion = new IrpQuestion();
        irpQuestion.setQuestionid(questionId);
        Date date = Calendar.getInstance().getTime();
        irpQuestion.setCrtime(date);
        irpQuestion.setStatus(0);
        irpQuestion.setTitle(_questiontitle);
        irpQuestion.setHtmlcontent(title);
        irpQuestion.setCruserid(user.getUserid());
        irpQuestion.setCruser(user.getName());
        irpQuestion.setBrowsecount(0l);
        try {
            this.irpQuestionDAO.insertSelective(irpQuestion);
            addCategoryFileLink(questionId,categories);
            // 保存附件
            if (_jsonFile != null && _jsonFile.length() > 0
                    && !"[]".equals(_jsonFile)) {
                JSONArray _Array = JSONArray.fromObject(_jsonFile);
                for (int i = 0; i < _Array.size(); i++) {
                    JSONObject jsonObject = (JSONObject) _Array
                            .getJSONObject(i);
                    String _sAttFile = jsonObject.getString("attfile");
                    String _sAttDesc = jsonObject.getString("attdesc");
                    String _sAttLinkAlt = jsonObject.getString("attlinkalt");
                    String _sEditversions = jsonObject
                            .getString("editversions");
                    String _lastName = FileUtil.findFileExt(_sAttFile);
                    List<IrpAttachedType> _arrSufflx = irpAttachedTypeDAO
                            .getSuffixExits(_lastName);// 查询所附件类型是否存在

                    if (_arrSufflx != null && _arrSufflx.size() > 0) {
                        addAttFile(_sAttFile, _arrSufflx.get(0).getTypeid(),
                                questionId, _sAttDesc, _sAttLinkAlt,
                                _sEditversions);
                    } else {// 就是其他类型
                        addAttFile(_sAttFile,
                                IrpAttachedType.ID_FIELD_NAMEOTHER, questionId,
                                _sAttDesc, _sAttLinkAlt, _sEditversions);
                    }
                }
            }
            //增加索引
            QuestionForSolr qfs = new QuestionForSolr();
            qfs.setQUESTIONID(questionId);
            qfs.setCRTIME(date);
            qfs.setCRUSER(user.getName());
            qfs.setCRUSERID(user.getUserid());
            qfs.setPARENTID(null);
            qfs.setTEXTCONTENT(null);
            qfs.setTITLE(_questiontitle);
            this.solrService.addQuestionIndex(qfs,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_QUESTION));
        } catch (SQLException e) {
            e.printStackTrace();
            questionId = 0L;
        }
        return questionId;
    }

	@Override
	public int findAllExpertAnswersCount() {
		IrpQuestionExample example = new IrpQuestionExample();
		example.createCriteria()
				.andIsexpertanswerEqualTo((short) 1).andStatusEqualTo(1L);
		example.setOrderByClause("crtime desc");
		try {
			irpQuestions = irpQuestionDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;
		if(irpQuestions!=null){
			i=irpQuestions.size();
		}
		return i;
	}

	@Override
	public List<IrpQuestion> findQuestionById(Long userid,String _sOrderBy,Map<String,Object> map,PageUtil pageUtil) {
		List<IrpQuestion> questionList = null;
		List<Long> questionIds = new ArrayList<Long>();
		try {
			IrpQuestionExample example = new IrpQuestionExample();
			Criteria c = example.createCriteria();
			if (_sOrderBy == null || _sOrderBy.equals("")) {
				_sOrderBy = "crtime desc";
			}
			String cateId = map.get("categoryId").toString();
			if(cateId!=null && !"".equals(cateId.trim()) && Integer.parseInt(cateId.trim())!=0){
				List<Long> categoryFileLinkIds = new ArrayList<Long>();
				categoryFileLinkIds = getCategoryFileLinkIds(Integer.parseInt(cateId.trim()), categoryFileLinkIds);
				//通过类别id查找出相应的questionids
				IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
				if(categoryFileLinkIds.size()>1000){
					List<Long> tempCategoryFileLinkIds = new ArrayList<Long>();
					for(int i = 0 ;i<categoryFileLinkIds.size();i++){
						tempCategoryFileLinkIds.add(categoryFileLinkIds.get(i));
						if(tempCategoryFileLinkIds.size()==1000){
							irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
							tempCategoryFileLinkIds = new ArrayList<Long>();
						}
					}
					if(tempCategoryFileLinkIds!=null && tempCategoryFileLinkIds.size()>0){
						irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
					}
				}else{
					irpCategoryFileLinkExample.createCriteria().andCidIn(categoryFileLinkIds);
				}
				try {
					List<IrpCategoryFileLink> irpCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(irpCategoryFileLinkExample);
					if(irpCategoryFileLinkList!=null && irpCategoryFileLinkList.size()>0){
						for(IrpCategoryFileLink icfl : irpCategoryFileLinkList){
							questionIds.add(icfl.getFid());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if(questionIds!=null && questionIds.size()>0){
					for ( int i = 0 ; i < questionIds.size() - 1 ; i ++ ) {
					    for ( int j = questionIds.size() - 1 ; j > i; j -- ) {
					       if (questionIds.get(j).toString().equals(questionIds.get(i).toString())) {
					    	   questionIds.remove(j);
					       }
					    }
					}
					if(questionIds.size()>1000){
						List<Long> tempQuestionIds = new ArrayList<Long>();
						for(int i = 0 ;i<questionIds.size();i++){
							tempQuestionIds.add(questionIds.get(i));
							if(tempQuestionIds.size()==1000){
								example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
								tempQuestionIds = new ArrayList<Long>();
							}
						}
						if(tempQuestionIds!=null && tempQuestionIds.size()>0){
							example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
						}
					}else{
						c.andQuestionidIn(questionIds);
					}
				}else{
					return null;
				}
			}
			example.setOrderByClause(_sOrderBy);
			c.andStatusIsNotNull().andTextcontentIsNull();
			String status = map.get("needDeal")+"";
			String myQuestion = map.get("myQuestion")+"";
			String myAnswer = map.get("myAnswer")+"";
			if((myQuestion!=null && "true".equals(myQuestion.trim()))){
				c.andCruseridEqualTo(userid);
			}
			if(status!=null && "needDeal".equals(status.trim())){
				c.andStatusEqualTo(0L);
			}else if(status!=null && "complete".equals(status.trim())){
				c.andStatusEqualTo(1L);
			}
			if(myAnswer!=null && "true".equals(myAnswer.trim())){
				Set set = new HashSet();
				List list = this.irpQuestionDAO.selectByExample(example);
				if (list.size() <= 0) {
					return null;
				}
				Iterator iterator = list.iterator();
				IrpQuestionExample examplelisttem;
				while (iterator.hasNext()) {
					IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
					examplelisttem = new IrpQuestionExample();
					examplelisttem.createCriteria().andParentidEqualTo(irpQuestion.getQuestionid())
												.andCruseridEqualTo(userid)
												.andTextcontentIsNotNull();
					List listtem = irpQuestionDAO.selectByExample(examplelisttem);
					if(listtem!=null && listtem.size()>0){
						set.add(irpQuestion.getQuestionid());
					}
				}

				List<Long> listlong = new ArrayList<Long>(set);
				if(listlong!=null && listlong.size()>0){
					IrpQuestionExample examplelist = new IrpQuestionExample();
					examplelist.createCriteria().andQuestionidIn(listlong);
					questionList = this.irpQuestionDAO.selectByExample(examplelist,
							pageUtil);
				}else{
					return null;
				}
			}else{
				questionList = irpQuestionDAO.selectByExample(example, pageUtil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
	}

	@Override
	public int findQuestioncountById(Map<String,Object> map,Long userid) {
		int nCount = 0;
		List<Long> questionIds = new ArrayList<Long>();
		IrpQuestionExample example = new IrpQuestionExample();
		Criteria c = example.createCriteria();
		String cateId = map.get("categoryId").toString();
		if(cateId!=null && !"".equals(cateId.trim()) && Integer.parseInt(cateId.trim())!=0){
			List<Long> categoryFileLinkIds = new ArrayList<Long>();
			categoryFileLinkIds = getCategoryFileLinkIds(Integer.parseInt(cateId.trim()), categoryFileLinkIds);
			//通过类别id查找出相应的questionids
			IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
			if(categoryFileLinkIds!=null && categoryFileLinkIds.size()>0){
				if(categoryFileLinkIds.size()>1000){
					List<Long> tempCategoryFileLinkIds = new ArrayList<Long>();
					for(int i = 0 ;i<categoryFileLinkIds.size();i++){
						tempCategoryFileLinkIds.add(categoryFileLinkIds.get(i));
						if(tempCategoryFileLinkIds.size()==1000){
							irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
							tempCategoryFileLinkIds = new ArrayList<Long>();
						}
					}
					if(tempCategoryFileLinkIds!=null && tempCategoryFileLinkIds.size()>0){
						irpCategoryFileLinkExample.or(irpCategoryFileLinkExample.createCriteria().andCidIn(tempCategoryFileLinkIds));
					}
				}else{
					irpCategoryFileLinkExample.createCriteria().andCidIn(categoryFileLinkIds);
				}
				try {
					List<IrpCategoryFileLink> irpCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(irpCategoryFileLinkExample);
					if(irpCategoryFileLinkList!=null && irpCategoryFileLinkList.size()>0){
						for(IrpCategoryFileLink icfl : irpCategoryFileLinkList){
							questionIds.add(icfl.getFid());
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				/**
				 * 去掉重复项
				 */
				if(questionIds!=null && questionIds.size()>0){
					for ( int i = 0 ; i < questionIds.size() - 1 ; i ++ ) {
					    for ( int j = questionIds.size() - 1 ; j > i; j -- ) {
					       if (questionIds.get(j).toString().equals(questionIds.get(i).toString())) {
					    	   questionIds.remove(j);
					       }
					    }
					}
					if(questionIds.size()>1000){
						List<Long> tempQuestionIds = new ArrayList<Long>();
						for(int i = 0 ;i<questionIds.size();i++){
							tempQuestionIds.add(questionIds.get(i));
							if(tempQuestionIds.size()==1000){
								example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
								tempQuestionIds = new ArrayList<Long>();
							}
						}
						if(tempQuestionIds!=null && tempQuestionIds.size()>0){
							example.or(example.createCriteria().andQuestionidIn(tempQuestionIds));
						}
					}else{
						c.andQuestionidIn(questionIds);
					}
				}
			}else{
				return 0;
			}
		}
		c.andStatusIsNotNull().andTextcontentIsNull();
		String qstatus = map.get("needDeal")+"";
		String myQuestion = map.get("myQuestion")+"";
		String myAnswer = map.get("myAnswer")+"";
		if((myQuestion!=null && "true".equals(myQuestion.trim()))){
			c.andCruseridEqualTo(userid);
		}
		if(qstatus!=null && !"".equals(qstatus.trim())){
			if("needDeal".equals(qstatus)){
				c.andStatusEqualTo(0L);
			}else if("complete".equals(qstatus.trim())){
				c.andStatusEqualTo(1L);
			}
		}
		try {
			if(myAnswer!=null && "true".equals(myAnswer.trim())){
				Set set = new HashSet();
				List list = this.irpQuestionDAO.selectByExample(example);
				if (list.size() <= 0) {
					return nCount;
				}
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					IrpQuestion irpQuestion = (IrpQuestion) iterator.next();
					set.add(irpQuestion.getParentid());

				}
				nCount = set.size();
			}else{
				nCount = irpQuestionDAO.countByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	

}
