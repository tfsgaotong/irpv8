/** 
* Project Name:TFS.IRP.V8 Project 
* File Name:IrpForumCommentService.java 
* Package Name:com.tfs.irp.forumcomment.service 
* Date:2017年9月26日下午3:27:48 
* 
*/

package com.tfs.irp.forumcomment.service;

import java.util.List;

import com.tfs.irp.forumcomment.entity.IrpForumComment;
import com.tfs.irp.forumcomment.entity.IrpForumCommentExample;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpForumCommentService <br/> 
 * Function: 帖子回复的Service类. <br/> 
 * date: 2017年9月26日 下午3:27:48 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
public interface IrpForumCommentService {
    /**
     * 
     * addForumComment:添加回复. <br/> 
     * @author Administrator 
     * @param irpForumComment 帖子回复实体
     * @return 数据库变动行数
     * @since JDK 1.8
     */
    int addForumComment(IrpForumComment irpForumComment);

    /**
     * 
     * deleteForumCommentById:通过id删除帖子回复. <br/> 
     * @author Administrator 
     * @param irpForumCommentId 帖子回复id
     * @return 数据库变动行数
     * @since JDK 1.8
     */
    int deleteForumCommentById(Long irpForumCommentId);

    /**
     * 
     * updateForumComment:更新帖子回复. <br/> 
     * @author Administrator 
     * @param irpForumComment 帖子回复实体
     * @return 数据库变动行数
     * @since JDK 1.8
     */
    int updateForumComment(IrpForumComment irpForumComment);

    /**
     * 
     * findForumCommentById:通过id查找回复. <br/> 
     * @author Administrator 
     * @param irpForumCommentId 帖子回复id
     * @return 帖子回复实体
     * @since JDK 1.8
     */
    IrpForumComment findForumCommentById(Long irpForumCommentId);

    /**
     * 
     * findForumCommentList:通过条件查询回复列表. <br/> 
     * @author Administrator 
     * @param irpForumCommentExample ibatis封装的查询条件实体
     * @param pageUtil 分页实体
     * @return 返回回复列表
     * @since JDK 1.8
     */
    List<IrpForumComment> findForumCommentList(IrpForumCommentExample irpForumCommentExample, PageUtil pageUtil);

    /**
     * 
     * countForumComment:通过条件查询回复数量. <br/> 
     * @author Administrator 
     * @param irpForumCommentExample ibatis封装的查询条件实体
     * @return 回复数量
     * @since JDK 1.8
     */
    int countForumComment(IrpForumCommentExample irpForumCommentExample);
}
