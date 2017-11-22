package com.tfs.irp.attached.service;

import java.util.List;

import org.apache.fop.layout.Page;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.util.PageUtil;

public interface IrpAttachedService {
	/**
	 * 获取文档封面
	 */
	public IrpAttached getIrpATttachedByDocIDFIle(Long _docid);
	/**
	 * 根据文档id获得他所有的附件
	 * @param _attDocId
	 * @return
	 */
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Integer _attdocidtype);
	/**
	 * 根据文档id获得他所有的附件
	 * @param _attDocId
	 * @return
	 */
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Integer _attdocidtype,PageUtil pageUtil);
	/**
	 * 根据主键删除当前附件
	 * @param _attachedid
	 * @return
	 */
	public int delAttachedByPrimaryKey(Long _attachedid);
	/***
	 * 删除掉仅有文档id，并且附件id没有在列表里面的数据
	 * @return
	 */
	public int deleteAttachedByAttidAndDocid(Long _attdocid,String[] _arrAttid);
	  
	/***
	 * 删除主键不在这里面的对象
	 * @param _arrAttchedid主键
	 * @param _docid文档id
	 */
	public void deleteAttachedByIdNotInList(List<Long> _arrAttchedid,Long _docid);
	/**
	 * 根据主键查询附件对象
	 * @param _attachedid
	 * @return
	 */
	public IrpAttached getAttachedByPrimary(Long _attachedid);
	/**
	 * 问答转知识 附件
	 * @param _attDocid
	 * @return
	 */
	public List<IrpAttached> getPortionAttached(Long _attDocid,Integer _attdocidtype);
	/**
	 * 获取某个知识的数量
	 * @param docid
	 * @return
	 */
	public int findFileAmountByDocid(Long docid);
	 /**
	  * 添加附件
	  * @param _attdocid引用ID
	  * @param _attprop 任务或者动态ID
	  * @param _attFile附件名称转换之后名称
	  * @param _attDesc真实名称描述
	  * @_attlintalt 真是名称
	  * @param projectDocidtype 附件所属类型(项目，文档，知识)
	  * @param createNewFile 是否创建新文件
	  * @param _sEditversions 是否封面
	  * @param _attflag 排序字段
	  * @return 当前附件主键
	  */
	public Long  addFile(Long _attdocid, Long _attprop, String _attFile,String _attlintalt, String _attDesc, Integer projectDocidtype,String newFilePath,Integer _sEditversions,Long TypeId,Integer _attflag);
	/**
	 * 删除附件
	 * @param _attdocid 引用ID
	 * @param _attdocidType 引用类型
	 * @return
	 */
	public int deleteFileByExpt(Long _attdocid,Integer _attdocidType);
	/**
	 * 删除附件
	 * @param _attdocid引用ID
	 * @param _attdocidType引用类型ID
	 * @param _attprop任务或者动态ID,直属引用id这里给值为0L;
	 * @return
	 */
	public int deleteFileByExpt(Long _attdocid,Integer _attdocidType,Long _attprop);
	/**
	 * 查询附件的数量
	 * @param _attDocid引用ID
	 * @param _attDocidType 引用类型
	 */
	public int  countFile(Long _attDocid,Integer _attDocidType);
	/**
	 * 根据文档id获得他所有的附件
	 * @param _attDocId
	 * @param _attProp
	 * @param _attdocidtype
	 * @return
	 */
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Long _attProp,Integer _attdocidtype);
	/**
	 * 修改附件
	 * @param docid 
	 * @param attId
	 * @param Editversions
	 * @param _attflag 排序字段
	 * @return
	 */
	public int udpateAttachedByExprt(Long docid, Long attId,Integer Editversions,Integer _attflag);
	/**
	 *  删除不在集合中的附件
	 * @param _arrAttachedid 附件主键集合
	 * @param docid 引用id
	 * @param attDocid 附件类型 知识，问答和项目附件三种类型
	 * @return
	 */
	public int deleteFileNotInList(List<Long> _arrAttachedid,Long docid,Integer attDocid);
	/**
	 *  删除在集合中的附件
	 * @param _arrAttachedid 附件主键集合
	 * @param docid 引用id
	 * @param attDocid 附件类型 知识，问答和项目附件三种类型
	 * @return
	 */
	public int deleteFileInList(List<Long> _arrAttachedid,Long docid,Integer attDocid);

	/**
	 * 为表单模版添加附件
	 * @param _attdocid
	 * @param _realname
	 * @param _attDesc
	 * @param projectDocidtype
	 * @param newFilePath
	 * @return
	 */
	public Long  addFileForForm(Long _attdocid,  String _realname, String _attDesc, Integer projectDocidtype,String newFilePath);
	public int deleteByFormExcel(long parseLong, Integer formexcel);
	/**
	 * @param taskid
	 * @param projectDocidtype
	 * @return
	 */
	public List getAttachedByAttprop(Long taskid, Integer projectDocidtype);
}
