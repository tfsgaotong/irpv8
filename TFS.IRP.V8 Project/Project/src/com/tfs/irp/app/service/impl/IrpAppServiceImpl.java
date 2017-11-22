package com.tfs.irp.app.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tfs.irp.app.dao.IrpAppDAO;
import com.tfs.irp.app.entity.IrpApp;
import com.tfs.irp.app.entity.IrpAppExample;
import com.tfs.irp.app.service.IrpAppService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

/**
 * 
 * @author Administrator
 *
 */
public class IrpAppServiceImpl implements IrpAppService {

	
	private IrpAppDAO irpAppDAO;
	
	public IrpAppDAO getIrpAppDAO() {
		return irpAppDAO;
	}

	public void setIrpAppDAO(IrpAppDAO irpAppDAO) {
		this.irpAppDAO = irpAppDAO;
	}

	@Override
	public List<IrpApp> findAllIrpapp(PageUtil pageUtil, String _sSearchWord,
			String _sSearchType,String _oOrderby,List<Integer> status) {
		List<IrpApp> list = null;
		IrpAppExample example = new IrpAppExample();
		example=toollike(example,_sSearchWord,_sSearchType,status);
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="applistid desc";
		}
		example.setOrderByClause(_oOrderby);
		list = this.irpAppDAO.selectByExample(example, pageUtil);
		return list;
	}

	private IrpAppExample toollike(IrpAppExample example,String _sSearchWord, String _sSearchType,List<Integer> status){
		if(_sSearchType!=null){
			if ("all".equals(_sSearchType)) {
				example.or(example.createCriteria().andStatusIn(status).andApplistnameLike(
						"%" + _sSearchWord + "%"));
				example.or(example.createCriteria().andStatusIn(status).andApplistaliasLike(
						"%" + _sSearchWord + "%"));
				example.or(example.createCriteria().andStatusIn(status).andDescriptionLike(
						"%" + _sSearchWord + "%"));
			} else if ("ckey".equals(_sSearchType)) {
				example.or(example.createCriteria().andStatusIn(status).andApplistnameLike(
						"%" + _sSearchWord + "%"));
			} else if ("cvalue".equals(_sSearchType)) {
				example.or(example.createCriteria().andStatusIn(status).andApplistaliasLike(
						"%" + _sSearchWord + "%"));
			} else {
				example.or(example.createCriteria().andStatusIn(status).andDescriptionLike(
						"%" + _sSearchWord + "%"));
			}
		}
		return example;
	}
	@Override
	public int findAllIrpappCount(String _sSearchWord, String _sSearchType,List<Integer> status) {
		int num = 0;
		IrpAppExample example = new IrpAppExample();
		example=toollike(example,_sSearchWord,_sSearchType,status);
		try {
			num = this.irpAppDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public void addNewApp(IrpApp ctype) {
		try {
			ctype.setApplistid(TableIdUtil
					.getNextId(IrpApp.TABLE_NAME));
			ctype.setCrtime(new Date());
			this.irpAppDAO.insertSelective(ctype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	@Override
	public IrpApp findAppByid(Long aid) {
		return irpAppDAO.findappbyid(aid);
	}

	@Override
	public List<IrpApp> findAllIrpappbytype(List<Integer> status, String type) {
		List<IrpApp> list = null;
		IrpAppExample example = new IrpAppExample();
		example.createCriteria().andCategoryEqualTo(type).andStatusIn(status);
		list = this.irpAppDAO.selectByExample(example, null);
		return list;
	}

	@Override
	public int updateAppStatus(IrpApp ctype) {
		return irpAppDAO.updateByExamplebyStatus(ctype);
	}

	@Override
	public int updateApp(IrpApp irpapp) {
		irpapp.setLastupdatetime(new Date());
		return irpAppDAO.updateByPrimaryKeySelective(irpapp);
	}

	@Override
	public List<IrpApp> findUserappbystuts(Long userid) {
		return irpAppDAO.findappbyuserid(userid);
	}

	@Override
	public List<IrpApp> findappuserdisplay(PageUtil pageutil,Map<String, Long> map) {
		return irpAppDAO.findappuserdisplay(pageutil,map);
	}

	@Override
	public List<IrpApp> findappusernotdisplay(PageUtil pageutil,Map<String, Long> map) {
		return irpAppDAO.findappusernotdisplay(pageutil,map);
	}

	@Override
	public int findappuserdisplaycount(Map<String, Long> map) {
		return irpAppDAO.findappuserdisplaycount(map);
	}

	@Override
	public int findappusernotdisplaycount(Map<String, Long> map) {
		return irpAppDAO.findappusernotdisplaycount(map);
	}

}
