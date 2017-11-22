/** 
 * Project Name:TFS.IRP.V8 Project 
 * File Name:IrpForumService.java 
 * Package Name:com.tfs.irp.forum.service 
 * Date:2017年9月26日下午3:18:59 
 * 
*/

package com.tfs.irp.forum.service;

import java.util.List;

import com.tfs.irp.forum.entity.IrpForum;
import com.tfs.irp.forum.entity.IrpForumExample;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpForumService <br/> 
 * Function: 帖子的Service类. <br/> 
 * date: 2017年9月26日 下午3:18:59 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
public interface IrpForumService {
    /**
     * 
     * addForum:添加帖子. <br/> 
     * @author Administrator 
     * @param irpForum 帖子对象实体
     * @return 返回数据库变动行数
     * @since JDK 1.8
     */
    int addForum(IrpForum irpForum);

    /**
     * 
     * deleteForumById:通过id删除帖子. <br/> 
     * @author Administrator 
     * @param irpForumId 帖子id
     * @return 返回数据库变动行数
     * @since JDK 1.8
     */
    int deleteForumById(Long irpForumId);

    /**
     * 
     * updateForum:更新帖子. <br/> 
     * @author Administrator 
     * @param irpForum 帖子对象实体
     * @return 返回数据库变动行数
     * @since JDK 1.8
     */
    int updateForum(IrpForum irpForum);

    /**
     * 
     * findForumById:通过id查找帖子. <br/> 
     * @author Administrator 
     * @param irpForumId 帖子id
     * @return  返回帖子对象
     * @since JDK 1.8
     */
    IrpForum findForumById(Long irpForumId);

    /**
     * 
     * findForumList:通过条件获取帖子列表. <br/> 
     * @author Administrator 
     * @param irpForumexample ibatis封装的查询实体
     * @param pageUtil 分页实体
     * @return 
     * @since JDK 1.8
     */
    List<IrpForum> findForumList(IrpForumExample irpForumexample, PageUtil pageUtil);

    /**
     * 
     * countForum:通过条件获取帖子数量. <br/> 
     * @author Administrator 
     * @param irpForumexample ibatis封装的查询实体
     * @return 返回帖子数量
     * @since JDK 1.8
     */
    int countForum(IrpForumExample irpForumexample);

    /**
     * 
     * findForumListByCategory:根据分类分页获取帖子列表. <br/> 
     * @author Administrator 
     * @param categoryId 分类id
     * @param pageUtil 分页实体
     * @return 帖子列表
     * @since JDK 1.8
     */
    List<IrpForum> findForumListByCategory(Long categoryId, PageUtil pageUtil);

    /** 
     * countForumListByCategory:根据分类获取帖子数量. <br/> 
     * @author Administrator 
     * @param categoryId 分类id
     * @return 帖子数量
     * @since JDK 1.8 
     */
    int countForumListByCategory(Long categoryId);

    /** 
     * getCreateForumUserIdList:加载创建帖子的所有用户列表Id. <br/> 
     * 
     * @author Administrator 
     * @return 用户id
     * @since JDK 1.8 
     */
    List<Integer> getCreateForumUserIdList();

    /** 
     * countForumListByAllCategoryId:加载当前所有分类列表下的帖子数量. <br/> 
     * 
     * @author Administrator 
     * @param categoryIdList 分类Id列表
     * @return 帖子数量
     * @since JDK 1.8 
     */
    int countForumListByAllCategoryId(List<Long> categoryIdList);

    /**
     * 
     * findForumListByAllCategoryId:加载当前所有分类列表下的帖子. <br/> 
     * 
     * @author Administrator 
     * @param categoryIdList 分类Id列表
     * @param pageUtil 分页实体
     * @return 帖子列表
     * @since JDK 1.8
     */
    List<IrpForum> findForumListByAllCategoryId(List<Long> categoryIdList, PageUtil pageUtil);

}
