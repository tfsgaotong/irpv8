package com.tfs.irp.category.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.exam.service.IrpExamService;
import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.service.IrpExpertClassifyLinkService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.question.service.IrpQuestionService;
import com.tfs.irp.questionbank.entity.IrpQuestionBank;
import com.tfs.irp.questionbank.service.IrpQuestionBankService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.template.entity.IrpTemplate;
import com.tfs.irp.template.service.IrpTemplateService;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.testpaper.service.IrpTestpaperService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.util.filter.LoginCheckFilter;

public class CategoryAction extends ActionSupport {
    private IrpCategoryService irpCategoryService;
    private IrpExpertClassifyLinkService irpExpertClassifyLinkService;
    private IrpUserService irpUserService;
    private String categoryorfile;
    private int cid = 0;
    private List<IrpCategory> categoryList = new ArrayList<IrpCategory>();
    private int pageNum = 1;
    private int pageSize = 10;
    private String pageHTML = "";
    private int parentId = 0;
    private IrpCategory category;
    private String categoryName;
    private String[] categoryIds;
    private String selfId;
    private Long id;
    private Long userId;
    private int showExpertList;
    private IrpTermService irpTermService;
    private IrpTemplateService irpTemplateService;
    private IrpQuestionBankService irpQuestionBankService;
    private IrpExamService irpExamService;

    public IrpExamService getIrpExamService() {
        return irpExamService;
    }

    public void setIrpExamService(IrpExamService irpExamService) {
        this.irpExamService = irpExamService;
    }

    private IrpTestpaperService irpTestpaperService;

    public IrpTestpaperService getIrpTestpaperService() {
        return irpTestpaperService;
    }

    public void setIrpTestpaperService(IrpTestpaperService irpTestpaperService) {
        this.irpTestpaperService = irpTestpaperService;
    }
    public IrpQuestionBankService getIrpQuestionBankService() {
        return irpQuestionBankService;
    }

    public void setIrpQuestionBankService(IrpQuestionBankService irpQuestionBankService) {
        this.irpQuestionBankService = irpQuestionBankService;
    }

    public IrpTemplateService getIrpTemplateService() {
        return irpTemplateService;
    }

    public void setIrpTemplateService(IrpTemplateService irpTemplateService) {
        this.irpTemplateService = irpTemplateService;
    }

    public IrpTermService getIrpTermService() {
        return irpTermService;
    }

    public void setIrpTermService(IrpTermService irpTermService) {
        this.irpTermService = irpTermService;
    }

    public int getShowExpertList() {
        return showExpertList;
    }

    public void setShowExpertList(int showExpertList) {
        this.showExpertList = showExpertList;
    }

    /**
     * 初始化分类ZTREE
     */
    public void getAllCategory() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));

    }

    private List<IrpCategory> list;
    private List<IrpCategory> listCategory;
    private List<IrpUser> listExpert;
    private List<IrpUser> recExpert;
    private IrpRoleService irpRoleService;
    private IrpQuestionService irpQuestionService;
    private List<IrpQuestion> connqlist;

    public List<IrpCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<IrpCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<IrpQuestion> getConnqlist() {
        return connqlist;
    }

    public void setConnqlist(List<IrpQuestion> connqlist) {
        this.connqlist = connqlist;
    }

    public IrpQuestionService getIrpQuestionService() {
        return irpQuestionService;
    }

    public void setIrpQuestionService(IrpQuestionService irpQuestionService) {
        this.irpQuestionService = irpQuestionService;
    }

    public IrpRoleService getIrpRoleService() {
        return irpRoleService;
    }

    public void setIrpRoleService(IrpRoleService irpRoleService) {
        this.irpRoleService = irpRoleService;
    }

    public List<IrpCategory> getList() {
        return list;
    }

    public void setList(List<IrpCategory> list) {
        this.list = list;
    }

    public List<IrpUser> getListExpert() {
        return listExpert;
    }

    public void setListExpert(List<IrpUser> listExpert) {
        this.listExpert = listExpert;
    }

    public List<IrpUser> getRecExpert() {
        return recExpert;
    }

    public void setRecExpert(List<IrpUser> recExpert) {
        this.recExpert = recExpert;
    }

    private String searchWord;

    private String searchType;
    private Long categoryId;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private Long channelid;
    private List<IrpChnlDocLink> chnlDocLinks27;
    private List<IrpChnlDocLink> chnlDocLinks26;
    private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;// 文档栏目中间表service

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public List<IrpChnlDocLink> getChnlDocLinks27() {
        return chnlDocLinks27;
    }

    public void setChnlDocLinks27(List<IrpChnlDocLink> chnlDocLinks27) {
        this.chnlDocLinks27 = chnlDocLinks27;
    }

    public List<IrpChnlDocLink> getChnlDocLinks26() {
        return chnlDocLinks26;
    }

    public void setChnlDocLinks26(List<IrpChnlDocLink> chnlDocLinks26) {
        this.chnlDocLinks26 = chnlDocLinks26;
    }

    public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
        return irpChnl_Doc_LinkService;
    }

    public void setIrpChnl_Doc_LinkService(IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
        this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
    }

    private IrpAttachedService irpAttachedService;// 附件表service
    private List<IrpChnlDocLink> chnlDocLinks50;

    public List<IrpChnlDocLink> getChnlDocLinks50() {
        return chnlDocLinks50;
    }

    public void setChnlDocLinks50(List<IrpChnlDocLink> chnlDocLinks50) {
        this.chnlDocLinks50 = chnlDocLinks50;
    }

    public IrpAttachedService getIrpAttachedService() {
        return irpAttachedService;
    }

    public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
        this.irpAttachedService = irpAttachedService;
    }

    /**
     * 获得知识的封面
     * @param _docid
     * @return
     */
    public String docCoverPath(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "_150X150");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }

    public String getAllExpertCategory() {
        int nDataCount = irpRoleService.findUsersCountByRoleId((long) 2, null, null);
        PageUtil pageUtil = new PageUtil(this.pageNum, 4, nDataCount);
        HashMap<String, Object> map = new HashMap<String, Object>();
        String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
        map.put("parentid", categoryQuestionId);
        list = irpCategoryService.findCategoryByConditions(map);
        listExpert = irpUserService.findHotExpertList(categoryId, pageUtil);
        recExpert = irpUserService.findRecExpertList(pageUtil);
        connqlist = this.irpQuestionService.getConnQList();
        String s = "";
        List<Long> _arrChannelIds = new ArrayList<Long>();
        s = SysConfigUtil.getSysConfigValue("EXPORT_PEIXUN");
        _arrChannelIds.add(Long.parseLong(s));//27推荐培训
        List<Long> _channelIds = new ArrayList<Long>();
        s = SysConfigUtil.getSysConfigValue("EXPORT_WEIKE");
        _channelIds.add(Long.parseLong(s));//26
        String sOrderByClause = "docpubtime desc";
        List<Long> _channelIds4 = new ArrayList<Long>();
        s = SysConfigUtil.getSysConfigValue("EXPORT_BIG_IMAGE");
        _channelIds4.add(Long.parseLong(s));//首页元数据50
        chnlDocLinks50 = irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM,
                IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds4,
                sOrderByClause, pageUtil);

        chnlDocLinks27 = irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM,
                IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _arrChannelIds,
                sOrderByClause, pageUtil);
        chnlDocLinks26 = irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM,
                IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, _channelIds,
                sOrderByClause, pageUtil);
        return SUCCESS;
    }

    public List<IrpCategory> findChildCategoryByParentId(long _nParentId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", _nParentId);
        List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
        return childCategory;
    }

    public String listCategory() {
        String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
        HashMap<String, Object> map = new HashMap<String, Object>();
        IrpCategory cate = irpCategoryService.findCategoryByPrimaryKey(categoryId);
        map.put("parentid", cate.getCparentid());
        list = irpCategoryService.findCategoryByConditions(map);
        listCategory = irpCategoryService.currentCategory(categoryId, listCategory, Long.parseLong(categoryQuestionId));
        return SUCCESS;
    }

    public String getAllExpert() {
        String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");
        int nDataCount = irpRoleService.findExpertCountByRoleId(categoryId, (long) 2, null, null);
        PageUtil pageUtil = new PageUtil(this.pageNum, 8, nDataCount);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", categoryQuestionId);
        list = irpCategoryService.findCategoryByConditions(map);
        listExpert = irpUserService.findHotExpertList(categoryId, pageUtil);
        this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
        return SUCCESS;
    }

    /**
     * 初始化分类ZTREE
     */
    public void getAllCategoryQuote() {

        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);

        if (this.id == null) {
            conditions.put("parentid", SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION"));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(SysConfigUtil
                    .getSysConfigValue("CATEGORY_QUESTION")));
        } else {
            conditions.put("parentid", this.id);
        }
        conditions.put("status", IrpCategory.STATUS_START);

        treeNode = getTreeNodeQuote(conditions, treeNode);

        ActionUtil.writer(JsonUtil.list2json(treeNode));

    }

    public void getAllCategoryEncy() {

        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);

        if (this.id == null) {
            conditions.put("parentid", SysConfigUtil.getSysConfigValue("CALEGORYTERM"));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(SysConfigUtil
                    .getSysConfigValue("CALEGORYTERM")));
        } else {
            conditions.put("parentid", this.id);
        }
        conditions.put("status", IrpCategory.STATUS_START);

        treeNode = getTreeNodeEncy(conditions, treeNode);

        ActionUtil.writer(JsonUtil.list2json(treeNode));

    }

    /**
     * 百科词条分类
     * @return
     */
    public void getAllCategoryTerm() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CALEGORYTERM);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    /**
     * 
     * getLeftCategoryByForum:跳转到生成分类的页面. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public String getLeftCategoryByForum() {
        return SUCCESS;
    }

    /**
     * 论坛分类
     * @return
     */
    public void getAllCategoryForum() {
        // 初始化一个转化成json列表的list
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 初始化一个存放查询条件的map
        Map<String, Object> conditions = new HashMap<String, Object>();

        // 获得论坛分类Id
        String categoryForumId = SysConfigUtil.getSysConfigValue("CATEGORY_FORUM");

        // 判空
        if (categoryForumId != null && !"".equals(categoryForumId)) {
            conditions.put("parentid", Long.valueOf(categoryForumId));
            conditions.put("status", IrpCategory.STATUS_START);
            // 根据论坛分类Id获取论坛下的分类列表 
            jsonList = getTreeNode(conditions, jsonList);
        }

        ActionUtil.writer(JsonUtil.list2json(jsonList));
    }

    /**
     * 百科模版分类
     * @return
     */
    public void getAllCateTemplate() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CALEGORYTMPLATE);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    /**
     * 题库分类
     * @return
     */
    public void getAllCateTemplateBank() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CALEGORYQBANK);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    /**
     * 试卷分类
     * @return
     */
    public void getAllCateTemplateTpaper() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.CATETESTPAPER);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    /**
     * 考试分类
     * @return
     */
    public void getAllCateTemplateExam() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.EXAMCATEMANAGER);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    /**
     * 表单分类
     * @return
     */
    public void getFormCate() {

        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.FORMCATE);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));

    }

    /**
     * 知识模版
     * @return 
     */
    public void getAllCateTemplateKnow() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> conditions = new HashMap<String, Object>();
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        String categoryQuestionId = SysConfigUtil.getSysConfigValue(IrpCategory.KNOWLEDGECATEMB);
        if (categoryQuestionId != null && !"".equals(categoryQuestionId.trim())) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryQuestionId));
            if (rootCategory == null) {
                return;
            }
        } else {
            return;
        }
        conditions.put("status", IrpCategory.STATUS_START);

        treeNode = getTreeNode(conditions, treeNode);
        ActionUtil.writer(JsonUtil.list2json(treeNode));

    }

    /**
     * 获得配置信息
     * @param mobile
     * @return 
     */
    public void getCfyQidByMobile() {
        ActionUtil.writer(SysConfigUtil.getSysConfigValue(IrpCategory.CATEGORY_QUESTION));
    }

    /**
     * 遞歸獲取分類
     * @param conditions
     * @param treeNode
     * @return
     */
    public List<Map<String, Object>> getTreeNode(Map<String, Object> conditions, List<Map<String, Object>> treeNode) {
        List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(conditions);
        if (categoryName != null && !"".equals(categoryName.trim())) {
            categoryIds = categoryName.split(",");
        }
        if (categories != null) {
            for (IrpCategory category : categories) {
                Map<String, Object> selfCategory = new HashMap<String, Object>();
                long nCategoryId = category.getCategoryid();
                int nCount = 0;
                if (showExpertList == 1) {
                    nCount = irpUserService.findExpertCount(nCategoryId, null, null);
                } else if (showExpertList == 2) {
                    nCount = irpCategoryService.findQuestionCountByCategoryId(nCategoryId);
                } else if (showExpertList == 3) {
                    nCount = this.irpTermService.findCountByTCate(nCategoryId, IrpTerm.TERMSTATUSWORDS);
                } else if (showExpertList == 4) {
                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSTATUSISCITIAO,
                            IrpTemplate.TISDELNORMAL);

                } else if (showExpertList == 5) {

                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSKNOWSISCIAO,
                            IrpTemplate.TISDELNORMAL);

                } else if (showExpertList == 7) {
                    nCount = irpQuestionBankService.findQBankByCate(nCategoryId, IrpQuestionBank.NORMALSTATUS);
                } else if (showExpertList == 8) {
                    //test paper cate
                    nCount = irpTestpaperService.getPCountByCate(nCategoryId);
                } else if (showExpertList == 9) {
                    nCount = this.irpExamService.getExamNumByCate(nCategoryId);
                } else if (showExpertList == 10) {

                    System.out.println("###" + LoginCheckFilter.DBNAME);

                    nCount = 100;

                }
                selfCategory.put("id", nCategoryId);
                selfCategory.put("name", category.getCname() + " (" + nCount + ")");
                selfCategory.put("pId", category.getCparentid());
                conditions.put("parentid", category.getCategoryid());
                int rootCount = irpCategoryService.countCategoryByConditions(conditions);
                if (rootCount > 0) {
                    selfCategory.put("nocheck", true);
                    treeNode = getTreeNode(conditions, treeNode);
                }
                if (categoryIds != null && categoryIds.length > 0) {
                    for (int i = 0; i < categoryIds.length; i++) {
                        if (category.getCategoryid().intValue() == Integer.parseInt(categoryIds[i])) {
                            selfCategory.put("checked", true);
                        }
                    }
                }
                treeNode.add(selfCategory);
            }
        }
        return treeNode;
    }

    public List<Map<String, Object>> getTreeNodeQuote(Map<String, Object> conditions, List<Map<String, Object>> treeNode) {

        List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(conditions);
        if (categoryName != null && !"".equals(categoryName.trim())) {
            categoryIds = categoryName.split(",");
        }
        if (categories != null) {
            for (IrpCategory category : categories) {
                Map<String, Object> selfCategory = new HashMap<String, Object>();
                long nCategoryId = category.getCategoryid();
                int nCount = 0;
                if (showExpertList == 1) {
                    nCount = irpUserService.findExpertCount(nCategoryId, null, null);
                } else if (showExpertList == 2) {
                    nCount = irpCategoryService.findQuestionCountByCategoryId(nCategoryId);
                } else if (showExpertList == 3) {
                    nCount = this.irpTermService.findCountByTCate(nCategoryId, IrpTerm.TERMSTATUSWORDS);
                } else if (showExpertList == 4) {
                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSTATUSISCITIAO,
                            IrpTemplate.TISDELNORMAL);

                } else if (showExpertList == 5) {

                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSKNOWSISCIAO,
                            IrpTemplate.TISDELNORMAL);

                }
                selfCategory.put("id", nCategoryId);
                selfCategory.put("text", category.getCname());
                selfCategory.put("status", category.getCparentid());
                selfCategory.put("parentid", nCategoryId);

                int num = this.irpCategoryService.isBoolNext(nCategoryId);
                if (num > 0) {
                    selfCategory.put("state", "closed");
                } else {

                    selfCategory.put("state", "open");

                }

                conditions.put("parentid", category.getCategoryid());
                int rootCount = irpCategoryService.countCategoryByConditions(conditions);
                if (rootCount > 0) {
                    selfCategory.put("nocheck", true);
                    //treeNode = getTreeNodeQuote(conditions,treeNode);
                }
                if (categoryIds != null && categoryIds.length > 0) {
                    for (int i = 0; i < categoryIds.length; i++) {
                        if (category.getCategoryid().intValue() == Integer.parseInt(categoryIds[i])) {
                            selfCategory.put("checked", true);
                        }
                    }
                }
                treeNode.add(selfCategory);
            }
        }
        return treeNode;
    }

    /**
     * 初始化TREE(后台)
     */
    public void getAllCategoryBack() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(1L);
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("id", rootCategory.getCategoryid());//即将作为下一节点的parentid
        item.put("name", rootCategory.getCname());
        item.put("open", true);
        item.put("pId", 0L);
        treeNode.add(item);
        this.cid = rootCategory.getCategoryid().intValue();
        HashMap<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("parentid", cid);
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);

        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }
    
    /**
     * 加载勋章分类（后台）
     */
    public void getMedalCategoryBack() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
		String categoryMedalId = SysConfigUtil.getSysConfigValue(IrpCategory.MEDAL);
        IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(categoryMedalId));
        Map<String, Object> item = new HashMap<String, Object>();
      /*  item.put("id", rootCategory.getCategoryid());//即将作为下一节点的parentid
        item.put("name", rootCategory.getCname());
        item.put("open", true);
        item.put("pId", 0L);
        treeNode.add(item);*/
        this.cid = rootCategory.getCategoryid().intValue();
        HashMap<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("parentid", cid);
        conditions.put("status", IrpCategory.STATUS_START);
        treeNode = getTreeNode(conditions, treeNode);

        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    public List<Map<String, Object>> getTreeNode(HashMap<String, Object> conditions, List<Map<String, Object>> treeNode) {
        List<IrpCategory> irpCategories = irpCategoryService.findCategoryByConditions(conditions);
        if (irpCategories != null && irpCategories.size() > 0) {
            for (int j = 0; j < irpCategories.size(); j++) {
                IrpCategory cate = irpCategories.get(j);
                Map<String, Object> selfitem = new HashMap<String, Object>();
                selfitem.put("id", cate.getCategoryid());//即将作为下一节点的parentid
                selfitem.put("name", cate.getCname());
                selfitem.put("pId", cate.getCparentid());
                conditions.put("parentid", cate.getCategoryid());
                int rootCount = irpCategoryService.countCategoryByConditions(conditions);
                if (rootCount > 0) {
                    selfitem.put("open", false);
                    treeNode = getTreeNode(conditions, treeNode);
                } else {
                    selfitem.put("open", true);
                }
                treeNode.add(selfitem);
            }
        }
        return treeNode;
    }

    public List<Map<String, Object>> getTreeNodeEncy(Map<String, Object> conditions, List<Map<String, Object>> treeNode) {
        List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(conditions);
        if (categoryName != null && !"".equals(categoryName.trim())) {
            categoryIds = categoryName.split(",");
        }
        if (categories != null) {
            for (IrpCategory category : categories) {
                Map<String, Object> selfCategory = new HashMap<String, Object>();
                long nCategoryId = category.getCategoryid();
                int nCount = 0;
                if (showExpertList == 1) {
                    nCount = irpUserService.findExpertCount(nCategoryId, null, null);
                } else if (showExpertList == 2) {
                    nCount = irpCategoryService.findQuestionCountByCategoryId(nCategoryId);
                } else if (showExpertList == 3) {
                    nCount = this.irpTermService.findCountByTCate(nCategoryId, IrpTerm.TERMSTATUSWORDS);
                } else if (showExpertList == 4) {
                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSTATUSISCITIAO,
                            IrpTemplate.TISDELNORMAL);

                } else if (showExpertList == 5) {

                    nCount = irpTemplateService.cateTemplate(nCategoryId, IrpTemplate.TSKNOWSISCIAO,
                            IrpTemplate.TISDELNORMAL);

                }
                selfCategory.put("id", nCategoryId);
                selfCategory.put("text", category.getCname());
                selfCategory.put("status", category.getCparentid());
                selfCategory.put("parentid", nCategoryId);

                int num = this.irpCategoryService.isBoolNext(nCategoryId);
                if (num > 0) {
                    selfCategory.put("state", "closed");
                } else {

                    selfCategory.put("state", "open");

                }

                conditions.put("parentid", category.getCategoryid());
                int rootCount = irpCategoryService.countCategoryByConditions(conditions);
                if (rootCount > 0) {
                    selfCategory.put("nocheck", true);
                    //treeNode = getTreeNodeQuote(conditions,treeNode);
                }
                if (categoryIds != null && categoryIds.length > 0) {
                    for (int i = 0; i < categoryIds.length; i++) {
                        if (category.getCategoryid().intValue() == Integer.parseInt(categoryIds[i])) {
                            selfCategory.put("checked", true);
                        }
                    }
                }
                treeNode.add(selfCategory);
            }
        }

        return treeNode;
    }

    /**
     * 查询子类别
     * @return
     */
    public String childCategory() {
        Map<String, Object> conditions = new HashMap<String, Object>();
        if (this.cid < 1) {
            cid = 0;
        }
        conditions = new HashMap<String, Object>();
        conditions.put("parentid", this.cid);
        //	conditions.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        int dataCount = irpCategoryService.countCategoryByConditions(conditions);
        if (dataCount > 0) {
            PageUtil page = new PageUtil(this.pageNum, this.pageSize, dataCount);
            categoryList = irpCategoryService.findCategoryByPage(conditions, page);
            this.pageHTML = page.getPageHtml("page(#PageNum#)");
        }
        return SUCCESS;
    }

    /**
     * 子类别下的关系
     * @return
     */
    public String childCategoryFile() {
        return SUCCESS;
    }

    /**
     * 跳到修改分类页面
     * @return
     */
    public String toUpdateCategory() {
        category = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(selfId));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", this.cid);
        //	map.put("type", IrpCategory.CATEGORY_TYPE_QUESTION);
        categoryList = irpCategoryService.findCategoryByConditions(map);
        if (categoryList != null && categoryList.size() > 0) {
            for (IrpCategory ele : categoryList) {
                if (ele.getCategoryid().intValue() == category.getCategoryid().intValue()) {
                    categoryList.remove(ele);
                    break;
                }
            }
        }
        return SUCCESS;
    }

    /**
     * 跳到新增分類頁面
     * @return
     */
    public String toAddCategory() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", this.cid);
        categoryList = irpCategoryService.findCategoryByConditions(map);
        return SUCCESS;
    }

    /**
     * 新增分类
     */
    public void addCategory() {
        if (category.getStatus() == null || "".equals(category.getStatus())) {
            category.setStatus(IrpCategory.STATUS_START.intValue());
        }
        category.setCparentid(Long.valueOf(cid));
        int result = irpCategoryService.addCategory(category);
        ActionUtil.writer(result + "");
    }

    /**
     * 检查类别在同一级别下是否重复
     */
    public void checkCategoryName() {
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("name", this.categoryName);
        if (selfId != null && !"-1".equals(selfId) && !"".equals(selfId)) {
            conditions.put("id", selfId);
        }
        conditions.put("parentid", this.parentId);
        int result = irpCategoryService.checkCategoryName(conditions);
        ActionUtil.writer(result + "");
    }

    /**
     * 修改类别
     */
    public void updateCategory() {
        int result = 0;
        result = irpCategoryService.updateCategoryByObject(category);
        ActionUtil.writer(result + "");
    }

    /**
     * 删除类别
     */
    public void deletecategory() {
        int result = irpCategoryService.deleteCategoryById(categoryIds, this.parentId);
        ActionUtil.writer(result + "");
    }

    /**
     * 更新右侧内容
     * @return
     */
    public String toRightPart() {
        return SUCCESS;
    }

    /**
     * 跳转到分类首页
     * @return
     */
    public String categoryMenu() {
        return SUCCESS;
    }

    public String getLeftCategory() {
        return SUCCESS;
    }

    private String tokens;
	private Long expertId;
	private IrpUser irpUser;
	private List<IrpCategory> irpCategorieList;
	

    public List<IrpCategory> getIrpCategorieList() {
		return irpCategorieList;
	}

	public void setIrpCategorieList(List<IrpCategory> irpCategorieList) {
		this.irpCategorieList = irpCategorieList;
	}

	public IrpUser getIrpUser() {
		return irpUser;
	}

	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}

	public Long getExpertId() {
		return expertId;
	}

	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}

	public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getLeftCategoryApp() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getParameter("token");
        tokens = token;
        return SUCCESS;
    }

    /**
     * 
     * getAllCategoryForApp:手机端获取所有分类
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getAllCategoryForApp() {
        // 初始化一个转化成json列表的list
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 初始化一个存放查询条件的map
        Map<String, Object> conditions = new HashMap<String, Object>();

        // 获得问答Id
        String categoryQuestionId = SysConfigUtil.getSysConfigValue("CATEGORY_QUESTION");

        // 判空
        if (categoryQuestionId != null && !"".equals(categoryQuestionId)) {
            conditions.put("parentid", Long.valueOf(categoryQuestionId));

            // 根据问答id获取问答下的分类列表 
            jsonList = getSubCategorys(conditions, jsonList);
        }

        ActionUtil.writer(JsonUtil.list2json(jsonList));
    }

    /**
     * 
     * getSubCategorys:获取父类下的所有分类
     * @author Administrator 
     * @param conditions
     * @param treeNode
     * @return 
     * @since JDK 1.8
     */
    public List<Map<String, Object>> getSubCategorys(Map<String, Object> conditions, List<Map<String, Object>> treeNode) {
        List<IrpCategory> categories = irpCategoryService.findCategoryByConditions(conditions);
        if (categories != null) {
            for (IrpCategory category : categories) {
                Map<String, Object> selfCategory = new HashMap<String, Object>();
                selfCategory.put("categoryId", category.getCategoryid());
                selfCategory.put("categoryName", category.getCname());
                treeNode.add(selfCategory);
            }
        }
        return treeNode;
    }

    /**
     * 连接到选择分类页面
     * @return
     */
    public String getLeftCategoryByTerm() {

        return SUCCESS;
    }

    /**
     * 获得分类树节点数据
     */
    public void findTreeNode() {
        List<Map<String, Object>> treeNode = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", IrpCategory.STATUS_START);
        //判断是否是根节点
        List<IrpCategory> categories = new ArrayList<IrpCategory>();
        if (this.id == null || this.id == 0L) {
            IrpCategory rootCategory = irpCategoryService.findCategoryByPrimaryKey(Long.valueOf(SysConfigUtil
                    .getSysConfigValue(IrpCategory.CATEGORY_QUESTION)));
            categories.add(rootCategory);
        } else {
            map.put("parentid", this.id);
            categories = irpCategoryService.findCategoryByConditions(map);
        }

        for (IrpCategory irpCategory : categories) {
            if (irpCategory == null) {
                continue;
            }
            Map<String, Object> item = new HashMap<String, Object>();
            Long nId = irpCategory.getCategoryid();
            item.put("id", nId);
            item.put("text", irpCategory.getCdesc());
            map.remove("parentid");
            map.put("parentid", nId);
            int nCount = irpCategoryService.countCategoryByConditions(map);
            if (nCount > 0) {
                item.put("state", "closed");
            } else {
                item.put("state", "open");
            }
            if (userId > 0L) {
                nCount = irpExpertClassifyLinkService.countExpertClassifyByUIdAndCId(userId, nId);
                if (nCount > 0) {
                    item.put("checked", true);
                } else {
                    item.put("checked", false);
                }
            }
            treeNode.add(item);
        }
        ActionUtil.writer(JsonUtil.list2json(treeNode));
    }

    public IrpCategoryService getIrpCategoryService() {
        return irpCategoryService;
    }

    public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
        this.irpCategoryService = irpCategoryService;
    }

    public String getCategoryorfile() {
        return categoryorfile;
    }

    public void setCategoryorfile(String categoryorfile) {
        this.categoryorfile = categoryorfile;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<IrpCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<IrpCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageHTML() {
        return pageHTML;
    }

    public void setPageHTML(String pageHTML) {
        this.pageHTML = pageHTML;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public IrpCategory getCategory() {
        return category;
    }

    public void setCategory(IrpCategory category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public IrpExpertClassifyLinkService getIrpExpertClassifyLinkService() {
        return irpExpertClassifyLinkService;
    }

    public void setIrpExpertClassifyLinkService(IrpExpertClassifyLinkService irpExpertClassifyLinkService) {
        this.irpExpertClassifyLinkService = irpExpertClassifyLinkService;
    }

    public IrpUserService getIrpUserService() {
        return irpUserService;
    }

    public void setIrpUserService(IrpUserService irpUserService) {
        this.irpUserService = irpUserService;
    }

    /**
     * 原图
     * @param _docid
     * @return
     */
    public String docCoverPathSource(Long _docid, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(_docid);
        if (attached != null) {
            String myFileName = attached.getAttfile();
            //获得文件路径 
            filePath = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="
                    + ThumbnailPic.searchFileName(myFileName, "");
        } else if (docflag != null && docflag > 0) {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand" + docflag + ".jpg";
        } else {
            filePath = ServletActionContext.getRequest().getContextPath() + "/view/images/rand/rand1.jpg";
        }
        return filePath;
    }
    
    public String expertRelationship() {
    	//根据id 获取所有分类
    	irpUser = irpUserService.findUserByUserId(expertId);
    	 irpCategorieList=irpCategoryService.findAllCategoryByExpertId(expertId);
        return SUCCESS;
    }
    
    public List<IrpUser> getUserBycateforyid(Long categoryId,Long  expertId) {
    	List<IrpUser>listExpert=null;
        listExpert = irpUserService.getUserBycategoryid(categoryId, expertId);
        return listExpert;
    }
}
