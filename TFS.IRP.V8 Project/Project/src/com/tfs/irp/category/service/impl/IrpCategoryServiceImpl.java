package com.tfs.irp.category.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.category.dao.IrpCategoryDAO;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.entity.IrpCategoryExample;
import com.tfs.irp.category.entity.IrpCategoryExample.Criteria;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.category_file_link.dao.IrpCategoryFileLinkDAO;
import com.tfs.irp.category_file_link.entity.IrpCategoryFileLinkExample;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.service.IrpExpertClassifyLinkService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpCategoryServiceImpl implements IrpCategoryService {
    private IrpCategoryDAO irpCategoryDAO;
    private IrpCategoryFileLinkDAO irpCategoryFileLinkDAO;
    private IrpExpertClassifyLinkService irpExpertClassifyLinkService;

    public IrpExpertClassifyLinkService getIrpExpertClassifyLinkService() {
        return irpExpertClassifyLinkService;
    }

    public void setIrpExpertClassifyLinkService(IrpExpertClassifyLinkService irpExpertClassifyLinkService) {
        this.irpExpertClassifyLinkService = irpExpertClassifyLinkService;
    }

    public IrpCategoryFileLinkDAO getIrpCategoryFileLinkDAO() {
        return irpCategoryFileLinkDAO;
    }

    public void setIrpCategoryFileLinkDAO(IrpCategoryFileLinkDAO irpCategoryFileLinkDAO) {
        this.irpCategoryFileLinkDAO = irpCategoryFileLinkDAO;
    }

    public IrpCategoryDAO getIrpCategoryDAO() {
        return irpCategoryDAO;
    }

    public void setIrpCategoryDAO(IrpCategoryDAO irpCategoryDAO) {
        this.irpCategoryDAO = irpCategoryDAO;
    }

    @Override
    public List<IrpCategory> findCategoryByConditions(Map<String, Object> conditions) {
        IrpCategoryExample irpCategoryExample = new IrpCategoryExample();
        Criteria criteria = irpCategoryExample.createCriteria();
        if (conditions != null) {
            if (conditions.get("parentid") != null) {
                criteria.andCparentidEqualTo(Long.valueOf(conditions.get("parentid").toString()));
            }
            if (conditions.get("type") != null) {
                criteria.andCtypeEqualTo(Integer.valueOf(conditions.get("type").toString()));
            }
            if (conditions.get("status") != null) {
                criteria.andStatusEqualTo(Integer.valueOf(conditions.get("status").toString()));
            }
        }
        irpCategoryExample.setOrderByClause(" categoryorder asc ");
        List<IrpCategory> categoryList = null;
        try {
            categoryList = irpCategoryDAO.selectByExample(irpCategoryExample);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return categoryList;
    }

    @Override
    public int countCategoryByConditions(Map<String, Object> conditions) {
        IrpCategoryExample irpCategoryExample = new IrpCategoryExample();
        Criteria criteria = irpCategoryExample.createCriteria();
        if (conditions != null) {
            if (conditions.get("parentid") != null) {
                criteria.andCparentidEqualTo(Long.valueOf(conditions.get("parentid").toString()));
            }
            if (conditions.get("type") != null) {
                criteria.andCtypeEqualTo(Integer.valueOf(conditions.get("type").toString()));
            }
            if (conditions.get("status") != null) {
                criteria.andStatusEqualTo(Integer.valueOf(conditions.get("status").toString()));
            }
        }
        int resultNum = 0;
        try {
            resultNum = irpCategoryDAO.countByExample(irpCategoryExample);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return resultNum;
    }

    @Override
    public List<IrpCategory> findCategoryByExample(IrpCategoryExample example) {
        try {
            return irpCategoryDAO.selectByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<IrpCategory> findCategoryByPage(Map<String, Object> conditions, PageUtil page) {
        IrpCategoryExample irpCategoryExample = new IrpCategoryExample();
        Criteria criteria = irpCategoryExample.createCriteria();
        if (conditions != null) {
            if (conditions.get("parentid") != null) {
                criteria.andCparentidEqualTo(Long.valueOf(conditions.get("parentid").toString()));
            }
            if (conditions.get("type") != null) {
                criteria.andCtypeEqualTo(Integer.valueOf(conditions.get("type").toString()));
            }
            if (conditions.get("status") != null) {
                criteria.andStatusEqualTo(Integer.valueOf(conditions.get("status").toString()));
            }
        }
        irpCategoryExample.setOrderByClause("categoryOrder asc");
        List<IrpCategory> categoryList = null;
        categoryList = irpCategoryDAO.findCategoryByPage(irpCategoryExample, page);
        return categoryList;
    }

    @Override
    public IrpCategory findCategoryByPrimaryKey(Long categoryId) {
        try {
            return irpCategoryDAO.selectByPrimaryKey(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int checkCategoryName(Map<String, Object> conditions) {
        IrpCategoryExample example = new IrpCategoryExample();
        Criteria criteria = example.createCriteria();
        if (conditions.get("name") != null && !"".equals(conditions.get("name").toString())) {
            criteria.andCnameEqualTo(conditions.get("name").toString());
        }
        if (conditions.get("id") != null) {
            criteria.andCategoryidNotEqualTo(Long.valueOf(conditions.get("id").toString()));
        }
        if (conditions.get("parentid") != null) {
            criteria.andCparentidEqualTo(Long.valueOf(conditions.get("parentid").toString()));
        }
        try {
            return irpCategoryDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int updateCategoryByObject(IrpCategory category) {
        if (category.getCategoryorder() != null) {
            Map<String, Long> map = new HashMap<String, Long>();
            map.put("categoryOrder", category.getCategoryorder());
            map.put("parentid", category.getCparentid());
            if (category.getCategoryorder() == 0) {
                category.setCategoryorder(1L);
            } else {
                category.setCategoryorder(category.getCategoryorder() + 1);
            }
            updateCategoryOrder(map);
        }
        LogUtil logUtil = new LogUtil("CATEGORY_UPDATE", category);
        try {
            logUtil.successLogs("修改分类[" + logUtil.getLogUser() + "]成功");
            int s = irpCategoryDAO.updateByPrimaryKeySelective(category);
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            logUtil.errorLogs("修改分类[" + logUtil.getLogUser() + "]失败", e);
            return 0;
        }
    }

    @Override
    public int deleteCategoryById(String[] categoryIds, int parentId) {
        List<Long> cateIds = new ArrayList<Long>();
        for (String string : categoryIds) {
            cateIds.add(Long.valueOf(string));
        }
        //查找出所有包括其子类的类别id
        cateIds = AllNeedDeletedCategoryIds(cateIds);
        // 删除对应的关系
        IrpCategoryFileLinkExample linkExample = new IrpCategoryFileLinkExample();
        IrpCategoryExample example = new IrpCategoryExample();
        if (cateIds.size() > 1000) {
            List<Long> tempCategoryIds = new ArrayList<Long>();
            for (int i = 0; i < cateIds.size(); i++) {
                tempCategoryIds.add(cateIds.get(i));
                if (tempCategoryIds.size() == 1000) {
                    linkExample.or(linkExample.createCriteria().andCidIn(tempCategoryIds));
                    example.or(example.createCriteria().andCategoryidIn(tempCategoryIds));
                    tempCategoryIds = new ArrayList<Long>();
                }
            }
            if (tempCategoryIds.size() > 0) {
                linkExample.or(linkExample.createCriteria().andCidIn(tempCategoryIds));
                example.or(example.createCriteria().andCategoryidIn(tempCategoryIds));
            }
        } else {
            linkExample.createCriteria().andCidIn(cateIds);
            example.or(example.createCriteria().andCategoryidIn(cateIds));
        }
        List<IrpCategory> s = null;
        try {
            s = irpCategoryDAO.selectByExample(example);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        LogUtil logUtil = null;
        if (s != null) {
            logUtil = new LogUtil("CATEGORY_DEL", s.get(0));
        }
        try {
            //查找出分类下面所有fid,删除之后对所有fid去掉重复的,新增关系,将这些fid与parentId建立联系
            //List<IrpCategoryFileLink> icflList = irpCategoryFileLinkDAO.selectByExample(linkExample);
            irpCategoryFileLinkDAO.deleteByExample(linkExample);
            irpCategoryDAO.deleteByExample(example);
            if (logUtil != null) {
                logUtil.successLogs("删除分类[" + logUtil.getLogUser() + "]成功");
            }
            /*if(icflList!=null && icflList.size()>0){
            	List<Long> fids = new ArrayList<Long>();
            	for(IrpCategoryFileLink ele : icflList){
            		fids.add(ele.getFid());
            	}
            	*//**
               * 重复剔除
               */
            /*
            if(fids!=null && fids.size()>0){
            for ( int i = 0 ; i < fids.size() - 1 ; i ++ ) {
                for ( int j = fids.size() - 1 ; j > i; j -- ) {
                   if (fids.get(j).toString().equals(fids.get(i).toString())) {
                	   fids.remove(j);
                   }
                }
            }
            *//**
              * 根据parentId查出所有拥有的关系的fid
              */
            /*
            linkExample = new IrpCategoryFileLinkExample();
            linkExample.createCriteria().andCidEqualTo(Long.valueOf(parentId));
            List<IrpCategoryFileLink> parentCategoryFileLinkList = irpCategoryFileLinkDAO.selectByExample(linkExample);
            if(parentCategoryFileLinkList!=null && parentCategoryFileLinkList.size()>0){
            List<Long> beforeFidList = new ArrayList<Long>();
            for(IrpCategoryFileLink icflBefore : parentCategoryFileLinkList){
            	beforeFidList.add(icflBefore.getFid());
            }
            *//**
              * 之前的fid和现在的fid进行比较,将已经存在的数据的id剔除,从fids中剔除
              */
            /*
            for(Long after : fids){
            for(Long before : beforeFidList){
            	if(after.intValue()==before.intValue()){
            		fids.remove(after);
            		break;
            	}
            }
            }
            }
            
            *//**
              * 新增
              */
            /*
            if(fids!=null && fids.size()>0){
            for(int k = 0 ;k<fids.size();k++){
            	IrpCategoryFileLink goryFileLink = new IrpCategoryFileLink();
            	goryFileLink.setId(TableIdUtil.getNextId(IrpCategoryFileLink.TABLE_NAME));
            	goryFileLink.setCid(Long.valueOf(parentId));
            	goryFileLink.setFid(fids.get(k));
            	goryFileLink.setCruserid(LoginUtil.getLoginUserId());
            	goryFileLink.setCrtime(new Date());
            	irpCategoryFileLinkDAO.insertSelective(goryFileLink);
            }
            }
            }
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
            if (logUtil != null) {
                logUtil.errorLogs("删除分类[" + logUtil.getLogUser() + "]失败", e);
            }
            return 0;
        }
        return cateIds.size();
    }

    @Override
    public int addCategory(IrpCategory category) {
        category.setCategoryid(TableIdUtil.getNextId(IrpCategory.TABLE_NAME));
        category.setCrtime(new Date());
        category.setCruserid(LoginUtil.getLoginUserId());
        if (category.getCategoryorder() != null) {
            HashMap<String, Long> map = new HashMap<String, Long>();
            map.put("parentid", category.getCparentid());
            map.put("categoryOrder", category.getCategoryorder());
            //如果添加默认
            if (category.getCategoryorder() == 0) {
                category.setCategoryorder(1L);//排在第一位 
            } else {
                category.setCategoryorder(category.getCategoryorder() + 1);
            }
            updateCategoryOrder(map);//调用它的修改方法将比他大的全部加1
        }
        LogUtil logUtil = new LogUtil("CATEGORY_ADD", category);
        try {
            irpCategoryDAO.insertSelective(category);
            logUtil.successLogs("添加分类[" + logUtil.getLogUser() + "]成功");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            logUtil.errorLogs("添加分类[" + logUtil.getLogUser() + "]失败", e);
            return 0;
        }
    }

    /**
     * 调序
     * @param map
     */
    public void updateCategoryOrder(Map<String, Long> map) {
        irpCategoryDAO.updateCategoryOrder(map);
    }

    /**
     * 查询出所有包括自身的子类ID
     * @param categoryIds
     * @return
     */
    public List<Long> AllNeedDeletedCategoryIds(List<Long> categoryIds) {
        List<Long> categoryIdList = new ArrayList<Long>();
        for (Long element : categoryIds) {
            categoryIdList.add(element);
            categoryIdList = childCategoryIds(element, categoryIdList);
        }
        return categoryIdList;
    }

    public List<Long> childCategoryIds(Long categoryId, List<Long> categoryIdList) {
        IrpCategoryExample example = new IrpCategoryExample();
        example.createCriteria().andCparentidEqualTo(categoryId);
        try {
            List<IrpCategory> cateList = irpCategoryDAO.selectByExample(example);
            if (cateList != null && cateList.size() > 0) {
                for (IrpCategory element : cateList) {
                    categoryIdList.add(element.getCategoryid());
                    childCategoryIds(element.getCategoryid(), categoryIdList);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return categoryIdList;
    }

    @Override
    public int findQuestionCountByCategoryId(long _nCategoryId) {
        int nCount = 0;
        IrpCategoryFileLinkExample example = new IrpCategoryFileLinkExample();
        example.createCriteria().andCidEqualTo(_nCategoryId);
        try {
            nCount = irpCategoryFileLinkDAO.countByExample(example);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nCount;
    }

    @Override
    public Map<Long, String> findCategoryByExpertId(Long _nExpertId) {
        Map<Long, String> map = new HashMap<Long, String>();
        List<IrpExpertClassifyLink> list = irpExpertClassifyLinkService.findCategorysByUserId(_nExpertId);
        for (IrpExpertClassifyLink irpExpertClassifyLink : list) {
            if (irpExpertClassifyLink == null)
                continue;
            Long nClassifyId = irpExpertClassifyLink.getClassifyid();
            String sCategoryAllName = findALlCategoryDescByCategoryId(nClassifyId, "");
            map.put(nClassifyId, sCategoryAllName);
        }
        return map;
    }

    /**
     * 根据分类ID获得分类的全名称
     * @param _nGroupId
     * @param _sGroupName
     * @return
     */
    private String findALlCategoryDescByCategoryId(Long _nClassifyId, String _sClassifyName) {
        if (_nClassifyId <= 0 && (_sClassifyName == null || _sClassifyName.length() == 0)) {
            return "";
        }
        try {
            IrpCategory irpCategory = irpCategoryDAO.selectByPrimaryKey(_nClassifyId);
            if (irpCategory == null) {
                return _sClassifyName.substring(1);
            }
            _sClassifyName = "\\" + irpCategory.getCdesc() + _sClassifyName;
            if (_nClassifyId.intValue() <= SysConfigUtil.getSysConfigNumValue("CATEGORY_QUESTION").intValue()) {
                return _sClassifyName.substring(1);
            } else {
                _sClassifyName = findALlCategoryDescByCategoryId(irpCategory.getCparentid(), _sClassifyName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return _sClassifyName;
    }

    @Override
    public int isBoolNext(Long _cparenpid) {
        // TODO Auto-generated method stub

        int status = 0;

        IrpCategoryExample example = new IrpCategoryExample();
        example.createCriteria().andCparentidEqualTo(_cparenpid);

        try {
            status = this.irpCategoryDAO.selectByExample(example).size();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<IrpCategory> currentCategory(Long categoryId, List<IrpCategory> categoryList, long _nRootId) {
        IrpCategory irpCategory = null;
        if (categoryList == null)
            categoryList = new ArrayList<IrpCategory>();
        try {
            irpCategory = irpCategoryDAO.selectByPrimaryKey(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (irpCategory == null) {
            return categoryList;
        } else if (irpCategory.getCparentid().intValue() == _nRootId) {
            categoryList.add(0, irpCategory);
            return categoryList;
        }
        categoryList.add(0, irpCategory);
        return currentCategory(irpCategory.getCparentid(), categoryList, _nRootId);
    }

    @Override
    public List<IrpCategory> findChildCategoryList(List<Long> conditionIdList) {
        List<IrpCategory> list = new ArrayList<IrpCategory>();
        try {
            list = this.irpCategoryDAO.selectChildCategoryList(conditionIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public List<IrpCategory> findAllCategoryByExpertId(Long expertId) {
		String  sql="select * from IRP_CATEGORY where CATEGORYID in (select classifyid from  IRP_EXPERT_CLASSIFY_LINK where userid="+expertId+")";
		List<IrpCategory> list = new ArrayList<IrpCategory>();
	    list = this.irpCategoryDAO.findAllCategoryByExpertId(sql);
	    return list;
	}
}
