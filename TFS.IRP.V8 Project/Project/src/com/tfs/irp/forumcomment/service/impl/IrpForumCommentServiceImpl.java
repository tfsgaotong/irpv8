/** 
* Project Name:TFS.IRP.V8 Project 
* File Name:IrpForumCommentServiceImpl.java 
* Package Name:com.tfs.irp.forumcomment.service.impl 
* Date:2017年9月26日下午3:28:17 
* 
*/

package com.tfs.irp.forumcomment.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.forumcomment.dao.IrpForumCommentDAO;
import com.tfs.irp.forumcomment.entity.IrpForumComment;
import com.tfs.irp.forumcomment.entity.IrpForumCommentExample;
import com.tfs.irp.forumcomment.service.IrpForumCommentService;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpForumCommentServiceImpl <br/> 
 * Function: 帖子回复的Service实现类. <br/> 
 * date: 2017年9月26日 下午3:28:17 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
public class IrpForumCommentServiceImpl implements IrpForumCommentService {
    private IrpForumCommentDAO irpForumCommentDAO;

    public IrpForumCommentDAO getIrpForumCommentDAO() {
        return irpForumCommentDAO;
    }

    public void setIrpForumCommentDAO(IrpForumCommentDAO irpForumCommentDAO) {
        this.irpForumCommentDAO = irpForumCommentDAO;
    }

    @Override
    public int addForumComment(IrpForumComment irpForumComment) {
        int rows = 0;
        try {
            irpForumCommentDAO.insertSelective(irpForumComment);
            rows = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteForumCommentById(Long irpForumCommentId) {
        int rows = 0;
        try {
            rows = irpForumCommentDAO.deleteByPrimaryKey(irpForumCommentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateForumComment(IrpForumComment irpForumComment) {
        int rows = 0;
        try {
            rows = irpForumCommentDAO.updateByPrimaryKeySelective(irpForumComment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public IrpForumComment findForumCommentById(Long irpForumCommentId) {
        IrpForumComment irpForumComment = null;
        try {
            irpForumComment = irpForumCommentDAO.selectByPrimaryKey(irpForumCommentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpForumComment;
    }

    @Override
    public List<IrpForumComment> findForumCommentList(IrpForumCommentExample irpForumCommentExample, PageUtil pageUtil) {
        List<IrpForumComment> list = null;
        try {
            list = irpForumCommentDAO.selectByExample(irpForumCommentExample, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countForumComment(IrpForumCommentExample irpForumCommentExample) {
        int count = 0;
        try {
            count = irpForumCommentDAO.countByExample(irpForumCommentExample);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
