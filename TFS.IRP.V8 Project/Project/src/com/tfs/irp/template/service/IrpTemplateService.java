package com.tfs.irp.template.service;

import java.util.List;

import com.tfs.irp.template.entity.IrpTemplate;
import com.tfs.irp.util.PageUtil;

public interface IrpTemplateService {
	
	/**
	 * 根据条件查询模版集合
	 * @param _pageutil
	 * @param _status
	 * @param _isdel
	 * @param _orderstr
	 * @param _searchType
	 * @param _searchStr
	 * @return
	 */
	List<IrpTemplate> findTemplateList(PageUtil _pageutil,Integer _status,Integer _isdel,String _orderstr,Long _tcateid,String _searchType,String _searchStr);
	/**
	 * 根据条件查询模版集合数量
	 * @param _status
	 * @param _isdel
	 * @param _searchType
	 * @param _searchStr
	 * @return
	 */
	int findTemplateListNum(Integer _status,Integer _isdel,Long _tcateid,String _searchType,String _searchStr);
	/**
	 * 添加分类模版
	 * @param _irptemplate
	 * @return
	 */
	int addTemplate(IrpTemplate _irptemplate);
	/**
	 * 根据id修改分类模版
	 * @param _irptemplate
	 * @return
	 */
	int updateTemplate(IrpTemplate _irptemplate);
	/**
	 * 根据id查对象
	 * @param _tid
	 * @return
	 */
	IrpTemplate irpTemplateById(Long _tid);
	
	/**
	 * 获取分类个数
	 * @param _cateid
	 * @param _status
	 * @param _isdel
	 * @return
	 */
	int cateTemplate(Long _cateid,Integer _status,Integer _isdel);
	/**
	 * 根据分类获得相应的模版
	 * @param _cateid
	 * @param _status
	 * @param _isdel
	 * @return
	 */
	List<IrpTemplate> findTemListByCId(Long _cateid,Integer _status,Integer _isdel,PageUtil _pageutil);
	/**
	 * 根据分类获得相应的模版个数
	 * @param _cateid
	 * @param _status
	 * @param _isdel
	 * @return
	 */
	int findTemListByCIdNum(Long _cateid,Integer _status,Integer _isdel);

}
