package com.tfs.irp.form.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.util.PageUtil;

public interface IrpFormService {
	/**
	 * 根据条件分页查询
	 */
	List<IrpForm> getAllNormal(PageUtil _pageUtil,String _searchType,String _searchWord,Integer _formType,String _orderField,String _orderBy);
	/**
	 * 根据检索词查询数据条数
	 */
	int countBySearchtypeAndWords(String _searchType,String _searchWord,Integer _formType);
	/**
	 * 增加form
	 * @param irpForm
	 * @return
	 */
	int saveForm(IrpForm irpForm);
	/**
	 * 导入拼接的sql
	 * @param sql
	 * @return 
	 */
	int createtable(String sql);
	/**
	 * 表单显示名称是否重复
	 * @param formname
	 * @return
	 */
	boolean isExitFormName(String formname);
	/**
	 * 验证数据库表名是否重复
	 * @param formtablename
	 * @return
	 */
	boolean isExitFormTableName(String formtablename);
	/**
	 * 删除表单到回收站
	 * @param columnids
	 * @return
	 */
	int deleteToRecover(String columnids);
	/**
	 * 从回收站恢复表单
	 * @param columnids
	 * @return
	 */
	int recoverForm(String columnids);
	/**
	 * 从回收站删除表单
	 * @param columnids
	 * @return
	 */
	int realDelete(String columnids);
	/**
	 * 根据ID获得表单
	 * @param parseLong
	 * @return
	 */
	IrpForm getFormById(long parseLong);
	/**
	 * 查询表单数据通过表名
	 * @param tablename
	 * @return
	 */
	List<Object> selectFormInfo(PageUtil _pageUtil,String _searchType,String _searchWord,String tablename,String _orderField,String _orderBy,Long id);
	List<Object> selectFormInfo1(PageUtil _pageUtil,String _searchType,String _searchWord,String tablename,String _orderField,String _orderBy);
	/**
	 * 查询数据总数
	 * @param tablename
	 * @param _searchType
	 * @param _searchWord
	 * @return
	 */
	int countFormInfo(String tablename,String _searchType,String _searchWord,Long id);
   /**
    * 更新表单
    * @param irpForm
    * @return
    */
	int updateForm(IrpForm irpForm);
	/**
	 * 根据名称删除数据库中的表
	 * @param formtablename
	 * @return
	 */
	int realeTableByTableName(String formtablename);
	/**
	 * 添加自定义表单数据
	 * @param _insertValue
	 * @param _tablename
	 * @return
	 */
	int addFormInfo(String _jsonFile,String _insertValue,String _tablename);
	/**
	 * 删除表单数据
	 * @param _forminfoid
	 * @return
	 */
	int deleteFormInfoById(Long _forminfoid,String _tablename);
	/**
	 * 查询object
	 * @param _forminfoid
	 * @param _tablename
	 * @return
	 */
	Object findObjectById(Long _forminfoid,String _tablename);
	/**
	 * 更新数据
	 * @param _forminfoid
	 * @param _tablename
	 * @return
	 */
	int updateFromInfoByID(String _jsonFile,Long _forminfoid,String _tablename,String _insertValue);
	/**
	 * object转map
	 * @param _obj
	 * @return
	 */
	Map object2map(List<IrpFormColumn> _column,Object _obj);
	/**
	 * 根据表名获得
	 * @param parseLong
	 * @return
	 */
	IrpForm getFormByFormtablename(String formtablename);
	
	/**
	 * 查询字段总数
	 * @param tablename
	 * @param _searchType
	 * @param _searchWord
	 * @return
	 */
	int countFormColumn1(IrpFormColumnExample example);
	/**通过数据库表名查询
	 * @param formtablename
	 * @return
	 */
	IrpForm selectformByTableName(String formtablename);
	
	void copyFile(String content, String path, String formtablename);
	void copyUpdatejsp(String content, String path, String formtablename);
	
	List<Object> selectFormInfo(PageUtil _pageUtil,String _searchType,String _searchWord,int columninliststatus,String tablename,String _orderField,String _orderBy);
	int findFormInfoById(Long _docid, String _tablename);
	/**
	 * 查询所有表单
	 */
	List<IrpForm> getAllForm(Integer _formisdel,Integer _formstatus,Integer _formisexitindb);
	/**根据数据库名称查询
	 * @param formtablename
	 * @return
	 */
	List<IrpForm> selectByformtablename(String formtablename);
}
