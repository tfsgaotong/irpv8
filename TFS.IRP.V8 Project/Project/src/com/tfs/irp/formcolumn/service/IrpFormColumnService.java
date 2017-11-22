package com.tfs.irp.formcolumn.service;

import java.util.List;
import java.util.Map;

import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.util.PageUtil;

public interface IrpFormColumnService {

	Long saveColumn(IrpFormColumn irpFormColumn);

	/**
	 * 是否存在同名显示字段名称
	 * @param _columnname
	 * @param _columntablaname
	 * @return
	 */
	int isExitColumnName(String _columnname,String _columntablaname);
	/**
	 * 是否存在同名数据库字段名称
	 * @param _columnname
	 * @param _columntablaname
	 * @return
	 */
	int isExitTableNamecol(String columntablenamecol,String _columntablaname);
	int updateTablenameByIds(String ids, String formtablename);
    /**
     * 根据用户名查找字段集合
     * @param formtablename
     * @return
     */
	List<IrpFormColumn> getListBytablename(String formtablename);
	/**
	 * 根据id删除数据
	 * @param _id
	 * @return
	 */
	int deleteColumn(Long _id);
	/**
	 * 通过id查询实体
	 * @param _id
	 * @return
	 */
	IrpFormColumn selectIrpFromColumnById(Long _id);
	/**
	 * 更新实体
	 * @param _id
	 * @return
	 */
	int updateIrpFromColumnById(IrpFormColumn _column);
	/**
	 * 编辑验证字段同名
	 * @param _id
	 * @param _columnname
	 * @param _columntablaname
	 * @return
	 */
	int isEditColumnName(Long _id,String _columnname,String _columntablaname);
	/**
	 * 编辑验证数据库字段名同名
	 * @param _id
	 * @param columntablenamecol
	 * @param _columntablaname
	 * @return
	 */
	int isEditTableNamecol(Long _id,String columntablenamecol,String _columntablaname);
	/**
	 * 整形数据类型集合
	 */
	List<String> intType();
	/**
	 * 字符串数据类型集合
	 */
	List<String> stringType();
	/**
	 * 日期数据类型集合
	 */
	List<String> dateType();

	int updateTablenameBytablenamens(String oldname, String formtablename);

	/**
	 * 转换字段展现形式
	 * @param _irpFormColumn
	 * @return
	 */
	String conversion(Map _map,List<IrpFormColumn> _irpFormColumn);
	
	/**
	 * 转换字段展现形式  复制页面是用   2017-6-7  付燕妮
	 * @param _irpFormColumn
	 * @return
	 */
	String newconversion(Map _map,List<IrpFormColumn> _irpFormColumn);
     /**
      * 显示字段需要默认值的集合
      * @return
      */
	List<String> displayValueType();

	void saveColumnForUpdate(IrpFormColumn irpFormColumn);
	
	/**
	 * 添加默认字段
	 * @param formtablename
	 * @param columnname
	 * @param columntablenamecol
	 * @param columntype
	 * @param columnlongtext
	 * @param columnstatus
	 * @param displaytype
	 * @return
	 */
	Long addSystemColumn(String formtablename,String columnname, String columntablenamecol,
			String columntype, int columnlongtext,String columndefval, int columnstatus,
			String displaytype,int columninliststatus,int columnisreadonly,int columnempty,String checktype);
	
	
	List<IrpFormColumn> getListBytablename(PageUtil pageUtil,String formtablename);
	
	List<IrpFormColumn> getListBytablename(int columninliststatus,String formtablename);

}
