package com.tfs.irp.category_file_link.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.category_file_link.dao.IrpCategoryFileLinkDAO;
import com.tfs.irp.category_file_link.entity.IrpCategoryFileLinkExample;
import com.tfs.irp.category_file_link.service.IrpCategoryFileLinkService;

public class IrpCategoryFileLinkServiceImpl implements IrpCategoryFileLinkService{
	private IrpCategoryFileLinkDAO irpCategoryFileLinkDAO;

	public IrpCategoryFileLinkDAO getIrpCategoryFileLinkDAO() {
		return irpCategoryFileLinkDAO;
	}

	public void setIrpCategoryFileLinkDAO(
			IrpCategoryFileLinkDAO irpCategoryFileLinkDAO) {
		this.irpCategoryFileLinkDAO = irpCategoryFileLinkDAO;
	}

	@Override
	public int deleteCategoryFileLinkByFid(List<Long> fileIds) {
		IrpCategoryFileLinkExample irpCategoryFileLinkExample = new IrpCategoryFileLinkExample();
		if(fileIds!=null && fileIds.size()>0){
			irpCategoryFileLinkExample.createCriteria().andFidIn(fileIds);
			try {
				return irpCategoryFileLinkDAO.deleteByExample(irpCategoryFileLinkExample);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}
	
}
