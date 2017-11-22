/** 
* Project Name:TFS.IRP.V8 Project 
* File Name:IrpForumAction.java 
* Package Name:com.tfs.irp.forum.web 
* Date:2017年9月26日下午3:21:09 
* 
*/

package com.tfs.irp.forum.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.forum.entity.IrpForum;
import com.tfs.irp.forum.entity.IrpForumExample;
import com.tfs.irp.forum.service.IrpForumService;
import com.tfs.irp.forumcomment.entity.IrpForumComment;
import com.tfs.irp.forumcomment.entity.IrpForumCommentExample;
import com.tfs.irp.forumcomment.service.IrpForumCommentService;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

/** 
 * ClassName: IrpForumAction <br/> 
 * Function: 帖子的Action类. <br/> 
 * date: 2017年9月26日 下午3:21:09 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
@SuppressWarnings("serial")
public class IrpForumAction extends ActionSupport {
    private IrpForumService irpForumService;
    private IrpForumCommentService irpForumCommentService;
    private IrpUserService irpUserService;
    private IrpCategoryService irpCategoryService;
    private List<IrpChnlDocLink> chnlDocLinks;
    private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
    private IrpRoleService irpRoleService;
    private IrpAttachedService irpAttachedService;
    private IrpAttachedTypeService irpAttachedTypeService;

    public IrpAttachedTypeService getIrpAttachedTypeService() {
        return irpAttachedTypeService;
    }

    public void setIrpAttachedTypeService(IrpAttachedTypeService irpAttachedTypeService) {
        this.irpAttachedTypeService = irpAttachedTypeService;
    }

    public IrpAttachedService getIrpAttachedService() {
        return irpAttachedService;
    }

    public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
        this.irpAttachedService = irpAttachedService;
    }

    public IrpRoleService getIrpRoleService() {
        return irpRoleService;
    }

    public void setIrpRoleService(IrpRoleService irpRoleService) {
        this.irpRoleService = irpRoleService;
    }

    public List<IrpChnlDocLink> getChnlDocLinks() {
        return chnlDocLinks;
    }

    public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
        this.chnlDocLinks = chnlDocLinks;
    }

    public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
        return irpChnl_Doc_LinkService;
    }

    public void setIrpChnl_Doc_LinkService(IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
        this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
    }

    public IrpCategoryService getIrpCategoryService() {
        return irpCategoryService;
    }

    public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
        this.irpCategoryService = irpCategoryService;
    }

    public IrpForumCommentService getIrpForumCommentService() {
        return irpForumCommentService;
    }

    public void setIrpForumCommentService(IrpForumCommentService irpForumCommentService) {
        this.irpForumCommentService = irpForumCommentService;
    }

    public IrpUserService getIrpUserService() {
        return irpUserService;
    }

    public void setIrpUserService(IrpUserService irpUserService) {
        this.irpUserService = irpUserService;
    }

    public IrpForumService getIrpForumService() {
        return irpForumService;
    }

    public void setIrpForumService(IrpForumService irpForumService) {
        this.irpForumService = irpForumService;
    }

    private String postContent;
    private String categoryId;
    private int pageNum;
    private String forumId;
    private IrpForum irpForum;
    private IrpUser irpUser;
    private String postTitle;
    private int commentCount;
    private String categoryName;
    private List<IrpCategory> listCategory;
    private String attachedFile;
    private List<IrpAttached> attachedList;

    public List<IrpAttached> getAttachedList() {
        return attachedList;
    }

    public void setAttachedList(List<IrpAttached> attachedList) {
        this.attachedList = attachedList;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    public List<IrpCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<IrpCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public IrpUser getIrpUser() {
        return irpUser;
    }

    public void setIrpUser(IrpUser irpUser) {
        this.irpUser = irpUser;
    }

    public IrpForum getIrpForum() {
        return irpForum;
    }

    public void setIrpForum(IrpForum irpForum) {
        this.irpForum = irpForum;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * toForumIndex:加载轮播图,跳转到论坛首页. <br/> 
     * @author Administrator 
     * @return 
     * @since JDK 1.8
     */
    public String toForumIndex() {
        // 加载后台配置的轮播图
        int nDataCount = irpRoleService.findUsersCountByRoleId((long) 2, null, null);
        PageUtil pageUtil = new PageUtil(this.pageNum, 4, nDataCount);
        List<Long> channelIds = new ArrayList<Long>();
        String sOrderByClause = "docpubtime desc";
        String s = SysConfigUtil.getSysConfigValue("FORUM_BIG_IMAGE");
        channelIds.add(Long.parseLong(s));
        chnlDocLinks = irpChnl_Doc_LinkService.clientSelectDocChnl("", "", IrpDocument.DOCUMENT_NOT_INFORM,
                IrpSite.SITE_TYPE_PUBLISH, IrpDocument.PUBLISH_STATUS, IrpAttached.IS_FENGMIAN, channelIds,
                sOrderByClause, pageUtil);

        // 获取分类数据
        String categoryForumId = SysConfigUtil.getSysConfigValue("CATEGORY_FORUM");
        HashMap<String, Object> map = new HashMap<String, Object>(16);
        map.put("parentid", categoryForumId);
        listCategory = irpCategoryService.findCategoryByConditions(map);

        return SUCCESS;
    }

    /**
     * 
     * addForum:创建帖子. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void addForum() {
        IrpForum irpForum = new IrpForum();
        // 设置主键
        Long irpForumId = TableIdUtil.getNextId(IrpForum.TABLE_NAME);
        irpForum.setForumid(irpForumId);

        // 设置内容、日期、标题和浏览量
        irpForum.setForumcontent(postContent);
        irpForum.setForumcrtime(new Date());
        irpForum.setForumtitle(postTitle);
        irpForum.setForumviewcount(new Long(0));

        // 获得登录用户并取得id
        IrpUser loginUser = LoginUtil.getLoginUser();

        // 设置作者id和状态
        irpForum.setForumcruserid(loginUser.getUserid());
        irpForum.setForumstatus(IrpForum.STATUS_NORMAL);

        // 设置分类Id
        irpForum.setForumcategoryid(Long.valueOf(categoryId));

        // 传入的附件判空
        if (attachedFile != null && !"[]".equals(attachedFile)) {
            getAttachedId(attachedFile, irpForumId);
        }

        // 更新
        int forumRows = irpForumService.addForum(irpForum);

        if (forumRows > 0) {
            ActionUtil.writer("{\"resultStatus\":\"success\"}");
        } else {
            ActionUtil.writer("{\"resultStatus\":\"false\"}");
        }
    }

    /**
     * 
     * getForumList:获得帖子列表. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getForumList() {
        // 创建存放帖子的list和封装成json对象数组的list
        List<IrpForum> forumList = null;
        List<Object> jsonList = new ArrayList<Object>();

        // 创建一个封装成json对象的map和一个存放帖子信息的map
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 初始化一个用于存放分页html的字符串
        String pageHTML = "";

        int count = irpForumService.countForumListByCategory(Long.valueOf(categoryId));

        PageUtil pageUtil = new PageUtil(this.pageNum, 6, count);

        forumList = irpForumService.findForumListByCategory(Long.valueOf(categoryId), pageUtil);

        pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");

        // 判空，封装成json格式
        if (forumList != null) {

            for (IrpForum irpForum : forumList) {
                Map<String, Object> forumMap = new HashMap<String, Object>(16);
                forumMap.put("title", irpForum.getForumtitle());
                forumMap.put("id", irpForum.getForumid());
                forumMap.put("viewCount", irpForum.getForumviewcount());

                // 通过作者id查找用户姓名
                Long authorId = irpForum.getForumcruserid();
                IrpUser irpUser = irpUserService.findUserByUserId(authorId);
                forumMap.put("username", irpUser.getUsername());

                // 格式化日期显示
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdf.format(irpForum.getForumcrtime());
                forumMap.put("date", formatDate);

                // 封装查询条件，获得回复量
                IrpForumCommentExample irpForumCommentExample = new IrpForumCommentExample();
                irpForumCommentExample.createCriteria().andFcForumidEqualTo(irpForum.getForumid())
                        .andFcstatusEqualTo(IrpForumComment.STATUS_NORMAL);
                int commentCount = this.irpForumCommentService.countForumComment(irpForumCommentExample);
                forumMap.put("commentCount", commentCount);

                jsonList.add(forumMap);
                forumMap = new HashMap<String, Object>(16);
            }
        }

        // 返回封装好的json对象
        if (forumList != null) {
            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);
            jsonMap.put("pageHtml", pageHTML);
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * showForumInfo:通过id查询贴子详情. <br/> 
     * @author Administrator 
     * @return SUCCESS
     * @since JDK 1.8
     */
    public String showForumInfo() {
        irpForum = irpForumService.findForumById(Long.valueOf(forumId));

        // 更新浏览量 
        int forumviewcount = irpForum.getForumviewcount().intValue();
        irpForum.setForumviewcount(new Long(forumviewcount + 1));
        irpForumService.updateForum(irpForum);

        // 获得发帖用户
        Long authorId = irpForum.getForumcruserid();
        irpUser = irpUserService.findUserByUserId(authorId);

        // 获得回复数
        IrpForumCommentExample irpForumCommentExample = new IrpForumCommentExample();
        irpForumCommentExample.createCriteria().andFcstatusEqualTo(IrpForumComment.STATUS_NORMAL)
                .andFcForumidEqualTo(irpForum.getForumid());
        commentCount = this.irpForumCommentService.countForumComment(irpForumCommentExample);

        // 获得分类名称
        IrpCategory irpCategory = this.irpCategoryService.findCategoryByPrimaryKey(irpForum.getForumcategoryid());
        categoryName = irpCategory.getCname();

        attachedList = irpAttachedService.getAttachedByAttDocId(Long.valueOf(forumId), IrpAttached.FORUM_ATTACHED);

        return SUCCESS;
    }

    /**
     * 
     * getNewForumList:根据时间获得最新的帖子列表. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getNewForumList() {
        // 初始化存放json数据的列表和封装成json对象的Map
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 展示5条最新帖子
        PageUtil pageUtil = new PageUtil(1, 5, 5);

        // 创建查询实体
        IrpForumExample irpForumExample = new IrpForumExample();

        // 根据日期降序
        irpForumExample.setOrderByClause("FORUMCRTIME desc");

        // 查询状态正常并未发帖的类型的帖子
        irpForumExample.createCriteria().andForumstatusEqualTo(IrpForum.STATUS_NORMAL);

        List<IrpForum> forumList = this.irpForumService.findForumList(irpForumExample, pageUtil);

        for (IrpForum irpForum : forumList) {
            Map<String, Object> irpForumMap = new HashMap<String, Object>(16);
            irpForumMap.put("id", irpForum.getForumid());
            irpForumMap.put("title", irpForum.getForumtitle());
            irpForumMap.put("viewnum", irpForum.getForumviewcount());

            jsonList.add(irpForumMap);
            irpForumMap = new HashMap<String, Object>(16);
        }

        // 返回封装好的json对象
        if (forumList.size() != 0) {
            jsonMap.put("resultStatus", "success");
            jsonMap.put("dataList", jsonList);
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getHotForumList:根据浏览量获得最热的帖子列表. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getHotForumList() {
        // 初始化存放json数据的列表和封装成json对象的Map
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 展示5条最热帖子
        PageUtil pageUtil = new PageUtil(1, 5, 5);

        // 创建查询实体
        IrpForumExample irpForumExample = new IrpForumExample();

        // 根据浏览量降序
        irpForumExample.setOrderByClause("FORUMVIEWCOUNT desc");

        // 查询状态正常并未发帖的类型的帖子
        irpForumExample.createCriteria().andForumstatusEqualTo(IrpForum.STATUS_NORMAL);

        List<IrpForum> forumList = this.irpForumService.findForumList(irpForumExample, pageUtil);

        for (IrpForum irpForum : forumList) {
            Map<String, Object> irpForumMap = new HashMap<String, Object>(16);
            irpForumMap.put("id", irpForum.getForumid());
            irpForumMap.put("title", irpForum.getForumtitle());
            irpForumMap.put("viewnum", irpForum.getForumviewcount());

            jsonList.add(irpForumMap);
            irpForumMap = new HashMap<String, Object>(16);
        }

        // 返回封装好的json对象
        if (forumList.size() != 0) {
            jsonMap.put("resultStatus", "success");
            jsonMap.put("dataList", jsonList);
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getCreateForumUserList:加载创建帖子的次数前四的用户. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getCreateForumUserList() {
        // 初始化json对象
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 初始化存放封装成json格式的用户对象的列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        List<Integer> list = this.irpForumService.getCreateForumUserIdList();

        // 判断list集合个数，为空则返回error
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> userMap = new HashMap<String, Object>(16);

                IrpUser irpUser = this.irpUserService.findUserByUserId(new Long(list.get(i)));

                userMap.put("userId", irpUser.getUserid());
                userMap.put("username", irpUser.getUsername());

                // 用户头像判空
                if (irpUser.getUserpic() == null) {
                    userMap.put("userImage", "");
                } else {
                    userMap.put("userImage", irpUser.getUserpic());
                }

                jsonList.add(userMap);

                userMap = new HashMap<String, Object>(16);
            }

            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);

            ActionUtil.writer(JsonUtil.map2json(jsonMap));

        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 初始化一个用于存放分页html的字符串
     */
    private String pageHTML;

    /**
     * 初始化一个前端用于获取数据的list
     */
    private List<Map<String, Object>> dataList;
    private List<IrpCategory> list;

    public List<IrpCategory> getList() {
        return list;
    }

    public void setList(List<IrpCategory> list) {
        this.list = list;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public String getPageHTML() {
        return pageHTML;
    }

    public void setPageHTML(String pageHTML) {
        this.pageHTML = pageHTML;
    }

    /**
     * 
     * toForumListByCategoryId:加载分类，根据分类id获得帖子列表，并跳转到帖子列表页. <br/> 
     * @author Administrator 
     * @return SUCCESS 
     * @since JDK 1.8
     */
    public String toForumListByCategoryId() {
        // 获得所有分类
        String categoryForumId = SysConfigUtil.getSysConfigValue("CATEGORY_FORUM");
        HashMap<String, Object> map = new HashMap<String, Object>(16);
        IrpCategory cate = irpCategoryService.findCategoryByPrimaryKey(Long.parseLong(categoryId));
        map.put("parentid", cate.getCparentid());
        list = irpCategoryService.findCategoryByConditions(map);
        listCategory = irpCategoryService.currentCategory(Long.parseLong(categoryId), listCategory,
                Long.parseLong(categoryForumId));

        /*
         * 获取当前分类下的子孙分类Id列表，需要传入一个父分类id集合
         * 和一个空集合，空集合用来保存子孙分类id
         */
        List<Long> conditionIdList = new ArrayList<Long>();
        conditionIdList.add(Long.parseLong(categoryId));
        List<Long> categoryIdList = new ArrayList<Long>();
        getAllChildCategoryList(conditionIdList, categoryIdList);

        categoryIdList.add(Long.parseLong(categoryId));

        dataList = new ArrayList<Map<String, Object>>();

        int count = irpForumService.countForumListByAllCategoryId(categoryIdList);

        PageUtil pageUtil = new PageUtil(this.pageNum, 6, count);

        List<IrpForum> forumList = irpForumService.findForumListByAllCategoryId(categoryIdList, pageUtil);

        pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");

        if (forumList != null) {
            for (IrpForum irpForum : forumList) {
                Map<String, Object> dataMap = new HashMap<String, Object>(16);

                // 获取id、标题、浏览量和日期
                dataMap.put("id", irpForum.getForumid());
                dataMap.put("title", irpForum.getForumtitle());
                dataMap.put("viewCount", irpForum.getForumviewcount());
                dataMap.put("date", irpForum.getForumcrtime());

                // 获取用户名
                IrpUser irpUser = this.irpUserService.findUserByUserId(irpForum.getForumcruserid());
                dataMap.put("username", irpUser.getUsername());

                // 获得回复量
                IrpForumCommentExample irpForumCommentExample = new IrpForumCommentExample();
                irpForumCommentExample.createCriteria().andFcForumidEqualTo(irpForum.getForumid())
                        .andFcstatusEqualTo(IrpForumComment.STATUS_NORMAL);
                int commentCount = this.irpForumCommentService.countForumComment(irpForumCommentExample);
                dataMap.put("commentCount", commentCount);

                dataList.add(dataMap);
                dataMap = new HashMap<String, Object>(16);
            }
        }
        return SUCCESS;
    }

    /**
     * 
     * docCoverPathSource:前端用来获取轮播图的原图. <br/> 
     * @author Administrator 
     * @param docId 知识Id
     * @param docflag 知识标签
     * @return 
     * @since JDK 1.8
     */
    public String docCoverPathSource(Long docId, Integer docflag) {
        String filePath = "";
        IrpAttached attached = irpAttachedService.getIrpATttachedByDocIDFIle(docId);
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

    /**
     * 
     * findChildCategoryByParentId:前端页面加载子分类. <br/> 
     * @author Administrator 
     * @param parentId 父类Id
     * @return 子分类实体集合
     * @since JDK 1.8
     */
    public List<IrpCategory> findChildCategoryByParentId(long parentId) {
        HashMap<String, Object> map = new HashMap<String, Object>(16);
        map.put("parentid", parentId);
        List<IrpCategory> childCategory = irpCategoryService.findCategoryByConditions(map);
        return childCategory;
    }

    /**
     * 
     * getAllChildCategoryList:获得当前分类下的所有子孙分类Id. <br/> 
     * 
     * @author Administrator 
     * @param conditionIdList 传入的父Id的集合
     * @param categoryIdList 所有子孙分类Id
     * @since JDK 1.8
     */
    public void getAllChildCategoryList(List<Long> conditionIdList, List<Long> categoryIdList) {
        List<IrpCategory> list = irpCategoryService.findChildCategoryList(conditionIdList);
        if (list.size() > 0) {
            List<Long> idlList = new ArrayList<Long>();
            for (IrpCategory c : list) {
                categoryIdList.add(c.getCategoryid());
                idlList.add(c.getCategoryid());
            }
            getAllChildCategoryList(idlList, categoryIdList);
        }
    }

    /**
     * 
     * getAllChildCategoryList:获得所有大分类、帖子数及最新发帖日期. <br/> 
     * 
     * @author Administrator 
     * @since JDK 1.8
     */
    public void getAllCategoryForum() {
        // 初始化一个转化成json列表的list
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 初始化一个存放查询条件的map
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 获得论坛分类Id
        String categoryForumId = SysConfigUtil.getSysConfigValue("CATEGORY_FORUM");

        // 创建查询条件
        HashMap<String, Object> conditionMap = new HashMap<String, Object>(16);
        conditionMap.put("parentid", Long.valueOf(categoryForumId));
        List<IrpCategory> categoryList = irpCategoryService.findCategoryByConditions(conditionMap);

        // 封装json数据
        for (IrpCategory irpCategory : categoryList) {
            // 初始化一个存放json数据的map
            Map<String, Object> map = new HashMap<String, Object>(16);

            Long categoryId = irpCategory.getCategoryid();
            map.put("categoryId", categoryId);
            map.put("categoryName", irpCategory.getCname());
            map.put("categoryInfo", irpCategory.getCdesc());

            // 初始化两个参数集合
            List<Long> categoryIdList = new ArrayList<Long>();
            List<Long> conditionIdList = new ArrayList<Long>();

            conditionIdList.add(categoryId);
            getAllChildCategoryList(conditionIdList, categoryIdList);
            categoryIdList.add(categoryId);

            int forumNum = this.irpForumService.countForumListByAllCategoryId(categoryIdList);
            map.put("forumNum", forumNum);

            PageUtil pageUtil = new PageUtil(1, 1, forumNum);
            List<IrpForum> forumList = this.irpForumService.findForumListByAllCategoryId(categoryIdList, pageUtil);

            // 得到最新帖子的日期
            if (forumList.size() > 0) {
                Date date = forumList.get(0).getForumcrtime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = format.format(date);

                map.put("date", formatDate);
            } else {
                map.put("date", "暂无");
            }

            jsonList.add(map);

            map = new HashMap<String, Object>(16);
        }

        if (jsonList.size() > 0) {
            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getAttachedId:获得附件Id组成的字符串. <br/> 
     * 
     * @author Administrator 
     * @param file json格式的文件字符串
     * @param tableid 主键
     * @return 逗号拼接的id字符串
     * @since JDK 1.8
     */
    private String getAttachedId(String file, Long tableid) {
        // 如果存在附件，则添加附件
        JSONArray _Array = JSONArray.fromObject(file);
        String attachedIdStr = "";
        for (int i = 0; i < _Array.size(); i++) {
            JSONObject jsonObject = (JSONObject) _Array.getJSONObject(i);
            String sattfile = jsonObject.getString("attfile");
            String sattdesc = jsonObject.getString("attdesc");
            String sattlinkalt = jsonObject.getString("attlinkalt");
            String seditversions = jsonObject.getString("editversions");
            String attflag = jsonObject.getString("attflag");
            // 获得文件类型
            Long typeid = irpAttachedTypeService.findAttachedTypeIdByFileExt(FileUtil.findFileExt(sattfile));
            // 入库
            Long attachedId = addAttachedFileForForum(Integer.parseInt(attflag), sattfile, typeid, tableid, sattdesc,
                    sattlinkalt, seditversions, false, null, false);
            attachedIdStr = attachedId + "," + attachedIdStr;
        }
        return attachedIdStr;
    }

    /**
     * 
     * addAttachedFileForForum:保存添加的附件. <br/> 
     * 
     * @author Administrator 
     * @param _attflag
     * @param _sAttFile
     * @param TypeId
     * @param _docid
     * @param _sAttDesc
     * @param _sAttLinkAlt
     * @param _sEditversions
     * @param isClient
     * @param _touChannelid
     * @param addUserFile
     * @return 附件id
     * @since JDK 1.8
     */
    private Long addAttachedFileForForum(Integer _attflag, String _sAttFile, Long TypeId, Long _docid,
            String _sAttDesc, String _sAttLinkAlt, String _sEditversions, boolean isClient, Long _touChannelid,
            Boolean addUserFile) {
        String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
        File newFile = new File(filePath);
        String newFileName = "";
        Long attachedId = null;
        if (newFile.exists()) {
            // 这是创建文档之后的附件名字
            if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(TypeId.toString())) {
                newFileName = SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
            } else {
                newFileName = SysFileUtil.saveFileDoc(newFile, SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
            }
            // 删除临时表中的文件
            String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

            attachedId = irpAttachedService.addFile(_docid, 0L, newFileName, _sAttLinkAlt, _sAttDesc,
                    IrpAttached.FORUM_ATTACHED, newFilePath, Integer.parseInt(_sEditversions), TypeId, _attflag);
        }
        return attachedId;
    }
}
