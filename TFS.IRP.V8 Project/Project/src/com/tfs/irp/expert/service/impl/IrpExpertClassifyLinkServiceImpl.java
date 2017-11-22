package com.tfs.irp.expert.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.tfs.irp.expert.entity.IrpExpertClassifyLinkExample.Criteria;
import com.tfs.irp.expert.dao.IrpExpertClassifyLinkDAO;
import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.entity.IrpExpertClassifyLinkExample;
import com.tfs.irp.expert.service.IrpExpertClassifyLinkService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;

public class IrpExpertClassifyLinkServiceImpl implements IrpExpertClassifyLinkService {
	private IrpExpertClassifyLinkDAO irpExpertClassifyLinkDAO;

	public IrpExpertClassifyLinkDAO getIrpExpertClassifyLinkDAO() {
		return irpExpertClassifyLinkDAO;
	}

	public void setIrpExpertClassifyLinkDAO(
			IrpExpertClassifyLinkDAO irpExpertClassifyLinkDAO) {
		this.irpExpertClassifyLinkDAO = irpExpertClassifyLinkDAO;
	}

	@Override
	public int importExpertClassifyByUserId(Long _nUserId, String _sClassifyIds) {
		int nResult = 1;
		//获得组织下的所有用户
		IrpExpertClassifyLinkExample example = new IrpExpertClassifyLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		List<IrpExpertClassifyLink> irpIrpExpertClassifyLinks = null;
		IrpUser user = new IrpUser();
		user.setUserid(_nUserId);
		try {
			irpIrpExpertClassifyLinks = irpExpertClassifyLinkDAO.selectByExample(example);
			if(irpIrpExpertClassifyLinks==null){
				irpIrpExpertClassifyLinks = new ArrayList<IrpExpertClassifyLink>();
			}
			
			String[] arrClassifyIds = _sClassifyIds.split(",");
			//如果当前组织下有用户，则筛选出已有的用户数组
			if(irpIrpExpertClassifyLinks.size()>0){
				for(int i=0;i<irpIrpExpertClassifyLinks.size();i++){
					IrpExpertClassifyLink irpExpertClassifyLink = irpIrpExpertClassifyLinks.get(i);
					if(irpExpertClassifyLink==null){
						continue;
					}
					//在数组中删除元素
					arrClassifyIds =(String[]) ArrayUtils.removeElement(arrClassifyIds, irpExpertClassifyLink.getClassifyid().toString());
				}
			}
			//添加筛选后的用户到组织中
			insertExpertClassifyByUserId(_nUserId, arrClassifyIds);
			//删除数据库中未选择的用户
			deleteNotInExpertClassifyByUserId(_nUserId, _sClassifyIds);
		} catch (SQLException e) {
			e.printStackTrace();
			nResult = 0;
		}
		return nResult;
	}
	
	/**
	 * 添加专家分类关系
	 * @param _nUserId
	 * @param _arrClassifyIds
	 */
	private void insertExpertClassifyByUserId(Long _nUserId, String[] _arrClassifyIds){
		IrpUser loginUser = LoginUtil.getLoginUser();
		for(int i=0;i<_arrClassifyIds.length;i++){
			String sClassifyId = _arrClassifyIds[i];
			if(sClassifyId==null || "".equals(sClassifyId)){
				continue;
			}
			IrpExpertClassifyLink record = new IrpExpertClassifyLink();
			record.setUserid(_nUserId);
			record.setClassifyid(Long.parseLong(sClassifyId));
			record.setCrtime(new Date());
			record.setCruseid(loginUser.getUserid());
			record.setCruse(loginUser.getUsername());
			try {
				irpExpertClassifyLinkDAO.insertSelective(record);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除没选中的专家分类
	 * @param _nUserId
	 * @param _sClassifyIds
	 */
	private void deleteNotInExpertClassifyByUserId(Long _nUserId, String _sClassifyIds){
		String[] arrClassifyIds = _sClassifyIds.split(",");
		List<Long> values = new ArrayList<Long>();
		for(int i=0;i<arrClassifyIds.length;i++){
			String sClassifysId = arrClassifyIds[i];
			if(sClassifysId==null || "".equals(sClassifysId)){
				continue;
			}
			values.add(Long.parseLong(sClassifysId));
		}
		IrpExpertClassifyLinkExample example = new IrpExpertClassifyLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_nUserId);
		if(values.size()>0){
			criteria.andClassifyidNotIn(values);
		}
		try {
			irpExpertClassifyLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int countExpertClassifyByUIdAndCId(Long _nUserId, Long _nClassifyId) {
		int nCount = 0;
		IrpExpertClassifyLinkExample example = new IrpExpertClassifyLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId).andClassifyidEqualTo(_nClassifyId);
		try {
			nCount = irpExpertClassifyLinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpExpertClassifyLink> findCategorysByUserId(Long _nUserId){
		List<IrpExpertClassifyLink> list = null;
		IrpExpertClassifyLinkExample example = new IrpExpertClassifyLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		try {
			list = irpExpertClassifyLinkDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
