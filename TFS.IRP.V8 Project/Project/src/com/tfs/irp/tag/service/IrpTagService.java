package com.tfs.irp.tag.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.util.PageUtil;

public interface IrpTagService {
	
	/**
	 * 根据用户ID获得个人标签
	 * @param _nUserId
	 * @return
	 */
	List<IrpTag> findTagsByUserId(long _nUserId);
	
	/**
	 * 添加个人标签
	 * @param _irpTag
	 * @return
	 */
	long addTag(IrpTag _irpTag);
	
	/**
	 * 根据标签名获得标签对象
	 * @param _sTagName
	 * @return
	 */
	IrpTag findTagByTagName(String _sTagName);
	/**
	 * 修改tag  （主要修改数量）
	 * @param irpTag
	 */
	int updateTagByTagId( IrpTag irpTag);
	/**
	 * 查询热门检索
	 * @param map
	 * @return
	 */
	List<IrpTag> findAllTag(int anount);
/**
 * 保存搜索词
 * @param sTags
 * @return
 */
	String addTag(String sTags);
	/**
	 * 获得热门检索词
	 * @return
	 */
	List<IrpTag> findHotIndexWord(Integer _pagenum);
	
	/**
	 * 获得所有的热门检索词
	 * @return
	 */
	List<String> findTagName();

	/**
	 * 根据标签类型获得标签列表
	 * @param _nTagType
	 * @return
	 */
	List<IrpTag> findIrpTagsByTagType(long _nTagType);

	/**
	 * 清除标签的指定类型
	 * @param _nTagType
	 * @return
	 */
	int clearTagTypeByTypeId(long _nTagType);

	/**
	 * 根据主键ID修改标签
	 * @param irpTag
	 * @return
	 */
	int updateTagSelectiveByTagId(IrpTag irpTag);

	/**
	 * 获得所有未选择标签类型的标签集合
	 * @param _sTagName
	 * @param pageUtil
	 * @param _sOrderByStr
	 * @return
	 */
	List<IrpTag> findNotSelectIrpTagsLikeTagName(String _sTagName, PageUtil pageUtil, String _sOrderByStr);

	/**
	 * 获得所有未选择标签类型的数量
	 * @param _sTagName
	 * @return
	 */
	int countNotSelectIrpTagsLikeTagName(String _sTagName);

	/**
	 * 导入标签到标签类型
	 * @param _nTypeId
	 * @param _sTagIds
	 * @return
	 */
	int importTagsByTypeId(long _nTypeId, String _sTagIds);

	/**
	 * 获得知识库首页的标签类型和标签集合
	 * @return
	 */
	Map<String, List<IrpTag>> findDocIndexTagList();
	
	
}
