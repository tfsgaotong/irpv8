package com.tfs.irp.complain.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.complain.dao.IrpComplainDAO;
import com.tfs.irp.complain.entity.IrpComplain;
import com.tfs.irp.complain.entity.IrpComplainExample;
import com.tfs.irp.complain.service.IrpComplainService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpComplainServiceImpl implements IrpComplainService{

	private IrpComplainDAO irpComplainDAO;
	
	public IrpComplainDAO getIrpComplainDAO() {
		return irpComplainDAO;
	}

	public void setIrpComplainDAO(IrpComplainDAO irpComplainDAO) {
		this.irpComplainDAO = irpComplainDAO;
	}

	@Override
	public int saveComplain(IrpComplain irpc) {
		try {
			Long tableid = TableIdUtil.getNextId(IrpComplain.TABLE_NAME);
			irpc.setComplainid(tableid);
			//SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			irpc.setCreattime(new Date());
			irpComplainDAO.insertSelective(irpc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<?> findAllComplainBystatus(PageUtil pageUtil,String orderby,String searchWord,List<Short> sids) {
		List<IrpComplain> list=new ArrayList<IrpComplain>();
		Map<String,Object> map=new HashMap<String,Object>();
		if(searchWord!=null){
		   map.put("searchWord", "'%"+searchWord+"%'");
		}
		if(orderby!= null || orderby.length()>0){
			
			orderby="irp_complain."+orderby.toUpperCase();
			map.put("field", orderby);
		}
		list = this.irpComplainDAO.selectByExample(map, pageUtil);
		return list;
	}

	@Override
	public int findAllComplainBystatuscount(String searchWord, List<Short> sids) {
		int num=0;
		IrpComplainExample example=new IrpComplainExample();
		if(searchWord!=null){
			example.or(example.createCriteria().andAnsquesIn(sids).andComplaindescLike("%" + searchWord + "%"));
		}
		try {
			num = this.irpComplainDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
