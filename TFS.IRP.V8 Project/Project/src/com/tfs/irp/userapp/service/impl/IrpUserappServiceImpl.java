package com.tfs.irp.userapp.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tfs.irp.userapp.dao.IrpUserappDAO;
import com.tfs.irp.userapp.entity.IrpUserapp;
import com.tfs.irp.userapp.entity.IrpUserappExample;
import com.tfs.irp.userapp.service.IrpUserappService;

public class IrpUserappServiceImpl implements IrpUserappService{

	
	private IrpUserappDAO irpUserappDAO;
	
	public IrpUserappDAO getIrpUserappDAO() {
		return irpUserappDAO;
	}

	public void setIrpUserappDAO(IrpUserappDAO irpUserappDAO) {
		this.irpUserappDAO = irpUserappDAO;
	}

	@Override
	public void updateAppdisplay(Map<String,Long> map) {
		irpUserappDAO.updateAppdisplay(map);
	}

	@Override
	public int findApphas(Map<String,Long> map) {
		List<IrpUserapp> list=null;
		try {
			IrpUserappExample example=new IrpUserappExample();
			example.createCriteria().andUseridEqualTo(map.get("userid")).andApplistidEqualTo(map.get("applistid"));
			list=irpUserappDAO.selectByExample(example);
			if(list.size()>0){
				return 1;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addAppuse(IrpUserapp irpUserapp) {
		 try {
			irpUserappDAO.insert(irpUserapp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
