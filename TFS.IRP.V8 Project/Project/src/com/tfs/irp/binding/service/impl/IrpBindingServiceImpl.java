package com.tfs.irp.binding.service.impl;

import java.sql.SQLException;

import java.util.List;

import com.tfs.irp.binding.dao.IrpBindingDAO;
import com.tfs.irp.binding.entity.IrpBinding;
import com.tfs.irp.binding.entity.IrpBindingExample;
import com.tfs.irp.binding.entity.IrpBindingExample.Criteria;
import com.tfs.irp.binding.service.IrpBindingService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.util.PageUtil;


public class IrpBindingServiceImpl implements IrpBindingService {
	private IrpBindingDAO irpBindingDAO;
	
	public IrpBindingDAO getIrpBindingDAO() {
		return irpBindingDAO;
	}

	public void setIrpBindingDAO(IrpBindingDAO irpBindingDAO) {
		this.irpBindingDAO = irpBindingDAO;
	}

	@Override
	public int addBinding(IrpBinding binding) {
		int msg = 1;
		try {
			irpBindingDAO.insert(binding);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public int deleteBinding(Long bindingid) {
		try {
			irpBindingDAO.deleteByPrimaryKey(bindingid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int deleteBindings(Long bindingids) {
		int msg = 0;
		try {
			//IrpBinding binding=irpBindingDAO.selectByPrimaryKey(bindingids);
			msg=irpBindingDAO.deleteByPrimaryKey(bindingids);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public IrpBinding findBindingByBindingid(Long bindingid) {
		IrpBinding irpBinding=null;
		try {
			irpBinding=irpBindingDAO.selectByPrimaryKey(bindingid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpBinding;
	}

	@Override
	public int updateBindingByBindingid(IrpBinding binding) {
		int msg = 1;
		try {
			msg=irpBindingDAO.updateByPrimaryKey(binding);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<IrpBinding> listBindings(Long channelid) {
		List<IrpBinding> list = null;
		try {
			IrpBindingExample example = new IrpBindingExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidEqualTo(channelid);
			list=irpBindingDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpBinding> listBinding(PageUtil pageUtil,Long channelid) {
		List<IrpBinding> list = null;
		try {
			IrpBindingExample example = new IrpBindingExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidEqualTo(channelid);
			list=irpBindingDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpBinding> showAllBinding(PageUtil pageUtil) {
		List<IrpBinding> list = null;
		try {
			IrpBindingExample example = new IrpBindingExample();
			list=irpBindingDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int countBinding(Long channelid) {
		int msg = 0;
		try {
			IrpBindingExample example = new IrpBindingExample();
			Criteria criteria = example.createCriteria();
			criteria.andChannelidEqualTo(channelid);
			msg=irpBindingDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public int countBinding() {
		int msg = 0;
		try {
			IrpBindingExample example = new IrpBindingExample();
			msg=irpBindingDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

}
