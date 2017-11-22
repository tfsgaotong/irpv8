/** 
 * Project Name:TFS.IRP.V8 Project 
 * File Name:IrpForumServiceImpl.java 
 * Package Name:com.tfs.irp.forum.service.impl 
 * Date:2017年9月26日下午3:19:32 
 * 
*/

package com.tfs.irp.forum.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tfs.irp.forum.dao.IrpForumDAO;
import com.tfs.irp.forum.entity.IrpForum;
import com.tfs.irp.forum.entity.IrpForumExample;
import com.tfs.irp.forum.service.IrpForumService;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpForumServiceImpl <br/> 
 * Function: 帖子的Service实现类. <br/> 
 * date: 2017年9月26日 下午3:19:32 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
public class IrpForumServiceImpl implements IrpForumService {
    private IrpForumDAO irpForumDAO;

    public IrpForumDAO getIrpForumDAO() {
        return irpForumDAO;
    }

    public void setIrpForumDAO(IrpForumDAO irpForumDAO) {
        this.irpForumDAO = irpForumDAO;
    }

    @Override
    public int addForum(IrpForum irpForum) {
        int rows = 0;
        try {
            irpForumDAO.insertSelective(irpForum);
            rows = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int deleteForumById(Long irpForumId) {
        int rows = 0;
        try {
            rows = irpForumDAO.deleteByPrimaryKey(irpForumId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateForum(IrpForum irpForum) {
        int rows = 0;
        try {
            rows = irpForumDAO.updateByPrimaryKeySelective(irpForum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public IrpForum findForumById(Long irpForumId) {
        IrpForum irpForum = null;
        try {
            irpForum = irpForumDAO.selectByPrimaryKey(irpForumId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return irpForum;
    }

    @Override
    public List<IrpForum> findForumList(IrpForumExample irpForumexample, PageUtil pageUtil) {
        List<IrpForum> list = null;
        try {
            list = irpForumDAO.selectByExample(irpForumexample, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countForum(IrpForumExample irpForumexample) {
        int count = 0;
        try {
            count = irpForumDAO.countByExample(irpForumexample);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<IrpForum> findForumListByCategory(Long categoryId, PageUtil pageUtil) {
        List<IrpForum> list = null;

        // 创建查询实体
        IrpForumExample irpForumExample = new IrpForumExample();
        irpForumExample.setOrderByClause("FORUMCRTIME desc");
        irpForumExample.createCriteria().andForumcategoryidEqualTo(categoryId)
                .andForumstatusEqualTo(IrpForum.STATUS_NORMAL);

        try {
            list = irpForumDAO.selectByExample(irpForumExample, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countForumListByCategory(Long categoryId) {
        int count = 0;

        // 创建查询实体
        IrpForumExample irpForumExample = new IrpForumExample();
        irpForumExample.createCriteria().andForumcategoryidEqualTo(categoryId)
                .andForumstatusEqualTo(IrpForum.STATUS_NORMAL);
        try {
            count = irpForumDAO.countByExample(irpForumExample);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Integer> getCreateForumUserIdList() {
        // 初始化用于存放排序之后的用户id列表
        List<Integer> userIdList = new ArrayList<Integer>();
        try {

            // 获得所有的创建词条的用户Id列表
            List<Integer> tempList = this.irpForumDAO.selectCreateForumUserIdList();

            // 利用map来统计用户Id出现的次数
            Map<Integer, Integer> userIdMap = new HashMap<Integer, Integer>(16);
            for (Integer integer : tempList) {
                if (userIdMap.containsKey(integer)) {
                    Integer value = userIdMap.get(integer);
                    userIdMap.put(integer, value + 1);
                } else {
                    userIdMap.put(integer, 1);
                }
            }

            // 将userIdMap转换为List，以便排序
            List<Map.Entry<Integer, Integer>> sortList = new ArrayList<Map.Entry<Integer, Integer>>(
                    userIdMap.entrySet());

            // 通过比较器来实现排序(降序)  
            Collections.sort(sortList, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            // 将排好序的Id放入userIdList
            for (Entry<Integer, Integer> entry : sortList) {
                int userId = entry.getKey();
                userIdList.add(userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }

    @Override
    public int countForumListByAllCategoryId(List<Long> categoryIdList) {
        int count = 0;
        try {
            count = irpForumDAO.countForumListByAllCategoryId(categoryIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<IrpForum> findForumListByAllCategoryId(List<Long> categoryIdList, PageUtil pageUtil) {
        List<IrpForum> list = new ArrayList<IrpForum>();
        try {
            list = this.irpForumDAO.selectForumListByAllCategoryId(categoryIdList, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
