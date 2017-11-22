package com.tfs.irp.sign.service;

import java.util.List;

import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.sign.entity.IrpSignInfoExample;
import com.tfs.irp.util.PageUtil;

public interface SignService {
	/**
	 * 初始化签到用户
	 * @param page
	 * @return
	 */
	public List<IrpSignInfo> signInfo(PageUtil page,IrpSignInfoExample example);
	
	/**
	 * 按照条件查询签到记录(不分页)
	 * @param page
	 * @param example
	 * @return
	 */
	public List<IrpSignInfo> signInfo(IrpSignInfoExample example);
	
	/**
	 * 获得总数量
	 * @return
	 */
	public int getDataCount(List<Long> longList,String beginTime,String finishTime);
	
	/**
	 * 通过例子查询
	 * @param example
	 * @return
	 */
	public List<IrpSignInfo> selectByExample(IrpSignInfoExample example);
	
	/**
	 * 新增签到信息
	 * @param userSignInfo
	 */
	public void insertSelect(IrpSignInfo userSignInfo);
	
	/**
	 * 通过主键修改
	 * @param example
	 * @return
	 */
	public void updateByPrimaryKeySelective(IrpSignInfo irpSignInfo);
	
	/**
	 * 通过例子修改
	 * @param example
	 */
	public void updateByExampleSelective(IrpSignInfo irpSignInfo ,IrpSignInfoExample example);
	
	/**
	 * 导出信息到zip文件
	 */
	public void exportAllSignInfoToZip(List<IrpSignInfo> list,String path,String fileName);
	
}
