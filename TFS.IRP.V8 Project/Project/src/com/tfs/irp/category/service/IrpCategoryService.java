package com.tfs.irp.category.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.entity.IrpCategoryExample;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.util.PageUtil;

public interface IrpCategoryService {
	/**
	 * 根据条件查询类别集合
	 * @param criteria
	 * @return
	 */
	public List<IrpCategory> findCategoryByConditions(Map<String,Object> conditions);
	
	/**
	 * 根据条件统计数量
	 * @param conditions
	 * @return
	 */
	public int countCategoryByConditions(Map<String,Object> conditions);
	
	/**
	 * 根据例子查询
	 * @param example
	 * @return
	 */
	public List<IrpCategory> findCategoryByExample(IrpCategoryExample example);
	
	/**
	 * 分页查询
	 * @param conditions
	 * @param page
	 * @return
	 */
	public List<IrpCategory> findCategoryByPage(Map<String,Object> conditions,PageUtil page);
	
	/**
	 * 根据主键查询
	 * @param categoryId
	 * @return
	 */
	public IrpCategory findCategoryByPrimaryKey(Long categoryId);
	
	/**
	 * 检查类别名称在同一级别下是否重复
	 * @param conditions
	 * @return
	 */
	public int checkCategoryName(Map<String,Object> conditions);
	
	/**
	 * 根据对象修改对象
	 * @return
	 */
	int updateCategoryByObject(IrpCategory category);
	
	/**
	 * 根据id删除类别
	 * @param categoryIds
	 * @return
	 */
	int deleteCategoryById(String[] categoryIds,int parentId);
	
	/**
	 * 新增分类
	 * @param category
	 * @return
	 */
	int addCategory(IrpCategory category);
	
	/**
	 * 根据分类ID获得分类下问答的数量
	 * @param _nCategoryId
	 * @return
	 */
	int findQuestionCountByCategoryId(long _nCategoryId);
	
	/**
	 * 获得专家的分类集合
	 * @param _nExpertId
	 * @return
	 */
	public Map<Long, String> findCategoryByExpertId(Long _nExpertId);
	
	/**
	 * 通过ID查询子节点
	 * @param _cparenpid
	 * @return
	 */
	public int isBoolNext(Long  _cparenpid);
	
	List<IrpCategory> currentCategory(Long categoryId, List<IrpCategory> categoryList, long _nRootId);

    /** 
     * findCategoryByConditions:查询当前分类下的子分类. <br/> 
     * 
     * @author Administrator 
     * @param conditionIdList 当前的分类Id列表
     * @return 所有子分类
     * @since JDK 1.8 
     */  
    public List<IrpCategory> findChildCategoryList(List<Long> conditionIdList);

	/**根据专家id获取其擅长领域
	 * @param expertId
	 * @return
	 */
	public List<IrpCategory> findAllCategoryByExpertId(Long expertId);
}
