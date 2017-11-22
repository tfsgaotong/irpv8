/** 
* Project Name:TFS.IRP.V8 Project 
* File Name:IrpForumCommentAction.java 
* Package Name:com.tfs.irp.forumcomment.web 
* Date:2017年9月26日下午3:29:07 
* 
*/

package com.tfs.irp.forumcomment.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.forum.entity.IrpForum;
import com.tfs.irp.forum.service.IrpForumService;
import com.tfs.irp.forumcomment.entity.IrpForumComment;
import com.tfs.irp.forumcomment.entity.IrpForumCommentExample;
import com.tfs.irp.forumcomment.service.IrpForumCommentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

/** 
 * ClassName: IrpForumCommentAction <br/> 
 * Function: 帖子评论的Action. <br/> 
 * date: 2017年9月26日 下午3:29:07 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
@SuppressWarnings("serial")
public class IrpForumCommentAction extends ActionSupport {
    private IrpForumCommentService irpForumCommentService;
    private IrpUserService irpUserService;
    private IrpForumService irpForumService;

    public IrpForumService getIrpForumService() {
        return irpForumService;
    }

    public void setIrpForumService(IrpForumService irpForumService) {
        this.irpForumService = irpForumService;
    }

    public IrpUserService getIrpUserService() {
        return irpUserService;
    }

    public void setIrpUserService(IrpUserService irpUserService) {
        this.irpUserService = irpUserService;
    }

    public IrpForumCommentService getIrpForumCommentService() {
        return irpForumCommentService;
    }

    public void setIrpForumCommentService(IrpForumCommentService irpForumCommentService) {
        this.irpForumCommentService = irpForumCommentService;
    }

    private String forumId;
    private int pageNum;
    private String commentContent;

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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 
     * addComment:添加评论. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void addComment() {
        IrpForumComment irpForumComment = new IrpForumComment();

        // 设置主键
        Long forumCommentId = TableIdUtil.getNextId(IrpForumComment.TABLE_NAME);
        irpForumComment.setFcid(forumCommentId);

        // 设置所属帖子id、内容和日期
        irpForumComment.setFcForumid(Long.valueOf(forumId));
        irpForumComment.setFccontent(commentContent);
        irpForumComment.setFccrtime(new Date());

        // 设置回复人id和状态(1为正常)
        long loginUserId = LoginUtil.getLoginUserId();
        irpForumComment.setFccruserid(loginUserId);
        irpForumComment.setFcstatus(1);

        int rows = irpForumCommentService.addForumComment(irpForumComment);

        if (rows > 0) {
            ActionUtil.writer("{\"resultStatus\":\"success\"}");
        } else {
            ActionUtil.writer("{\"resultStatus\":\"false\"}");
        }
    }

    /**
     * 
     * getCommentList:分页获取所有评论. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getCommentList() {
        // 初始化用于封装json对象的map
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 初始化用于将ForumComment对象转换成json对象的map
        Map<String, String> commentMap = new HashMap<String, String>(16);

        // 初始化用于存放ForumComment转化后的json对象的列表
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();

        // 创建查询条件实体
        IrpForumCommentExample irpForumCommentExample = new IrpForumCommentExample();

        // 根据日期降序
        irpForumCommentExample.setOrderByClause("FCCRTIME asc");
        irpForumCommentExample.createCriteria().andFcForumidEqualTo(Long.valueOf(forumId));

        int count = irpForumCommentService.countForumComment(irpForumCommentExample);

        // 封装分页查询实体
        PageUtil pageUtil = new PageUtil(pageNum, 6, count);

        List<IrpForumComment> commentList = irpForumCommentService.findForumCommentList(irpForumCommentExample,
                pageUtil);

        // 获得分页代码
        String pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");

        // 判空，封装成json对象
        if (commentList != null) {
            for (IrpForumComment irpForumComment : commentList) {
                commentMap.put("commentContent", irpForumComment.getFccontent());

                // 获得用户名和头像
                Long userId = irpForumComment.getFccruserid();
                IrpUser irpUser = irpUserService.findUserByUserId(userId);
                commentMap.put("author", irpUser.getUsername());
                commentMap.put("authorPic", irpUser.getUserpic());

                // 格式化日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formatDate = sdf.format(irpForumComment.getFccrtime());
                commentMap.put("date", formatDate);

                commentMap.put("commentId", irpForumComment.getFcid().toString());

                jsonList.add(commentMap);
                commentMap = new HashMap<String, String>(16);
            }
        }

        // 返回封装好的json对象
        if (commentList != null && commentList.size() > 0) {
            jsonMap.put("resultStatus", "success");
            jsonMap.put("dataList", jsonList);
            jsonMap.put("pageHtml", pageHTML);
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        } else {
            jsonMap.put("resultStatus", "error");
            ActionUtil.writer(JsonUtil.map2json(jsonMap));
        }
    }

    /**
     * 
     * getCommentLog:获取最新的10条回复动态. <br/> 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getCommentLog() {
        // 初始化一个用于存放封装好的json对象列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 初始化json对象
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        // 封装查询条件
        IrpForumCommentExample irpForumCommentExample = new IrpForumCommentExample();
        irpForumCommentExample.setOrderByClause("FCCRTIME desc");
        irpForumCommentExample.createCriteria().andFcstatusEqualTo(IrpForumComment.STATUS_NORMAL);

        int count = this.irpForumCommentService.countForumComment(irpForumCommentExample);

        // 初始化分页实体
        PageUtil pageUtil = new PageUtil(1, 10, count);

        List<IrpForumComment> list = this.irpForumCommentService.findForumCommentList(irpForumCommentExample, pageUtil);

        if (list != null && list.size() > 0) {
            for (IrpForumComment irpForumComment : list) {

                // 借用map将对象以json格式封装起来
                Map<String, Object> irpCommentLogMap = new HashMap<String, Object>(16);

                // 获得用户姓名
                IrpUser irpUser = this.irpUserService.findUserByUserId(irpForumComment.getFccruserid());
                if (irpUser != null) {
                    irpCommentLogMap.put("username", irpUser.getNickname());
                }

                // 获得词条名称
                IrpForum irpForum = this.irpForumService.findForumById(irpForumComment.getFcForumid());
                if (irpForum != null) {
                    irpCommentLogMap.put("forumName", irpForum.getForumtitle());
                }

                // 获得友好日期
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formatDate = sdf.format(irpForumComment.getFccrtime());
                    String friendlyDate = DateUtils.friendlyTime(formatDate);
                    irpCommentLogMap.put("friendlyDate", friendlyDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 将对象添加到数据列表中
                jsonList.add(irpCommentLogMap);

                irpCommentLogMap = new HashMap<String, Object>(16);
            }

            jsonMap.put("resultStatus", "success");
            jsonMap.put("dataList", jsonList);
        } else {
            jsonMap.put("resultStatus", "error");
        }
        ActionUtil.writer(JsonUtil.map2json(jsonMap));
    }

}
