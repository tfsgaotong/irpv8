package com.tfs.irp.goods.service.impl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.goods.dao.IrpGoodsDAO;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpGoodsServiceImpl implements IrpGoodsService {
	private IrpGoodsDAO irpGoodsDAO;
	
	public IrpGoodsDAO getIrpGoodsDAO() {
		return irpGoodsDAO;
	}

	public void setIrpGoodsDAO(IrpGoodsDAO irpGoodsDAO) {
		this.irpGoodsDAO = irpGoodsDAO;
	}

	@Override
	public int addGoods(IrpGoods goods) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("GOODS_ADD",goods);
		try {
			irpGoodsDAO.insert(goods);
			msg=1;
			logUtil.successLogs( "添加商品["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "添加商品["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteGoods(Long goodsid) {
		int msg=0;
		IrpGoods irpGoods=null;
		LogUtil logUtil=null;
		try {
			irpGoods=irpGoodsDAO.selectByPrimaryKey(goodsid);
			logUtil=new LogUtil("GOODS_DELETE",irpGoods);
			irpGoodsDAO.deleteByPrimaryKey(goodsid);
			msg=1;
			logUtil.successLogs( "删除商品["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除商品["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteMoreGoods(String goodsids) {
		int msg = 0;
		IrpGoods irpGoods=new IrpGoods();
		LogUtil logUtil=null;
		String[] array =goodsids.split(",");
		for(int j=0;j<array.length;j++){
			try {
				irpGoods=irpGoodsDAO.selectByPrimaryKey(Long.parseLong(array[j]));
				logUtil=new LogUtil("GOODS_DELETE",irpGoods);
				irpGoodsDAO.deleteByPrimaryKey(Long.parseLong(array[j]));
				msg++;
				logUtil.successLogs( "删除商品["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "删除商品["+logUtil.getLogUser()+"]失败",e);
			}
		}
		return msg;
	}

	@Override
	public IrpGoods findGoodsByGoodsid(Long goodsid) {
		IrpGoods irpGoods=null;
		try {
			irpGoods=irpGoodsDAO.selectByPrimaryKey(goodsid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpGoods;
	}

	@Override
	public int updateGoodsByGoodsid(IrpGoods goods) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("GOODS_UPDATE",goods);
		try {
			msg=irpGoodsDAO.updateByPrimaryKey(goods);
			logUtil.successLogs( "修改商品["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "修改商品["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public List<IrpGoods> listAllGoods(PageUtil pageUtil, String _orderby,String searchType,String searchWord,String orderField) {
		List<IrpGoods> list = null;
		try {
			IrpGoodsExample example = new IrpGoodsExample();
			if(searchWord!=null&&searchWord.trim().length()>0){
				Long salestate =IrpGoods.NOSALE;
				Long covertstate =IrpGoods.NOCOVERT;
				if("all".equals(searchType)){
					if(searchWord.contains("已上架")){
						salestate =IrpGoods.SALE;
						example.createCriteria().andSalestateEqualTo(salestate);
					}else if(searchWord.contains("是积分兑换商品")){
						covertstate =IrpGoods.COVERT;
						example.createCriteria().andCoverstateEqualTo(covertstate);
					}else{
						example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%"));
						example.or(example.createCriteria().andGoodsdescLike("%"+searchWord+"%"));
						//example.or(example.createCriteria().andSalestateEqualTo(salestate));
						//example.or(example.createCriteria().andCoverstateEqualTo(covertstate));
					}
				}else if("goodsname".equals(searchType)){
					example.createCriteria().andGoodsnameLike("%"+searchWord+"%");
				}else if("goodsdesc".equals(searchType)){
					example.createCriteria().andGoodsdescLike("%"+searchWord+"%");
				}else if("salestate".equals(searchType)){
					if(searchWord.contains("已上架")){
						salestate =IrpGoods.SALE;
					}
					example.createCriteria().andSalestateEqualTo(salestate);
				}else if("coverstate".equals(searchType)){
					if(searchWord.contains("是积分兑换商品")){
						covertstate =IrpGoods.COVERT;
					}
					example.createCriteria().andCoverstateEqualTo(covertstate);
				}
			}
			if(orderField!=null&&_orderby!=null&&!orderField.equals("")&&!_orderby.equals("")){
				example.setOrderByClause(orderField+" "+_orderby);
			}else{
				example.setOrderByClause("reorder desc,crtime desc");
			}
			//example.setOrderByClause(_orderby);
			list=irpGoodsDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpGoods> showAllGoods(PageUtil pageUtil,Long coverstate,String searchWord,String _orderby) {
		List<IrpGoods> list = null;
		try {
			IrpGoodsExample example = new IrpGoodsExample();
			if(coverstate==null||coverstate.equals("")){
				if(searchWord!=null&&searchWord.trim().length()>0){
					
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE).andGoodsnameLike("%"+searchWord+"%");
				}else{
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE);
				}
			}else{
				if(searchWord!=null&&searchWord.trim().length()>0){
					
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE).andGoodsnameLike("%"+searchWord+"%")
					.andCoverstateEqualTo(coverstate);
				}else{
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE)
					.andCoverstateEqualTo(coverstate);
				}
			}
			example.setOrderByClause(_orderby);
			list=irpGoodsDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpGoods> showAllGoodsByUserid(PageUtil pageUtil,Long userid,String searchWord,String _orderby) {
		List<IrpGoods> list = null;
		IrpMedal medal=null;
		List<String> liststring=new ArrayList<String>();
		List<IrpMedal> list1=new ArrayList<IrpMedal>();
		try {
			IrpGoodsExample example = new IrpGoodsExample();
			IrpUser irpUser = irpUserService.findUserByUserId(userid);
			List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),null,0l);
			for(int i=0;i<listmedal.size();i++){
				medal=irpMedalService.findMedalByMedalid(listmedal.get(i).getMedalid());
				list1.add(medal);
				liststring.add(listmedal.get(i).getMedalid().toString());
			}
			if(searchWord!=null&&searchWord.trim().length()>0){
				example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
						.andNeedscoreLessThanOrEqualTo(irpUser.getSumscore()).andCoverstateEqualTo(20l)
						.andSalestateEqualTo(IrpGoods.SALE_STATE));
				if(liststring.size()>0){
					example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
							.andMedalidNotEqualTo(0l)
							.andMedalidIn(liststring)
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}else{
				}
				for(int j=0;j<list1.size();j++){
					example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
							.andNeedscoreBetween(list1.get(j).getMinworth(), list1.get(j).getMaxworth())
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}
				
			}else{
				example.or(example.createCriteria().andNeedscoreLessThanOrEqualTo(irpUser.getSumscore()).andCoverstateEqualTo(20l)
						.andSalestateEqualTo(IrpGoods.SALE_STATE));
				if(liststring.size()>0){
					example.or(example.createCriteria().andMedalidNotEqualTo(0l)
							.andMedalidIn(liststring)
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}else{
				}
				for(int j=0;j<list1.size();j++){
					example.or(example.createCriteria().andNeedscoreBetween(list1.get(j).getMinworth(), list1.get(j).getMaxworth())
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}
			}
			example.setOrderByClause(_orderby);
			list=irpGoodsDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countGoods(String searchType,String searchWord) {
		int msg = 0;
		try {
			IrpGoodsExample example = new IrpGoodsExample();
			if(searchWord!=null&&searchWord.trim().length()>0){
				Long salestate =IrpGoods.NOSALE;
				Long covertstate =IrpGoods.NOCOVERT;
				if("all".equals(searchType)){
					if(searchWord.contains("已上架")){
						salestate =IrpGoods.SALE;
						example.createCriteria().andSalestateEqualTo(salestate);
					}else if(searchWord.contains("是积分兑换商品")){
						covertstate =IrpGoods.COVERT;
						example.createCriteria().andCoverstateEqualTo(covertstate);
					}else{
						example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%"));
						example.or(example.createCriteria().andGoodsdescLike("%"+searchWord+"%"));
						//example.or(example.createCriteria().andSalestateEqualTo(salestate));
						//example.or(example.createCriteria().andCoverstateEqualTo(covertstate));
					}
				}else if("goodsname".equals(searchType)){
					example.createCriteria().andGoodsnameLike("%"+searchWord+"%");
				}else if("goodsdesc".equals(searchType)){
					example.createCriteria().andGoodsdescLike("%"+searchWord+"%");
				}else if("salestate".equals(searchType)){
					if(searchWord.contains("已上架")){
						salestate =IrpGoods.SALE;
					}
					example.createCriteria().andSalestateEqualTo(salestate);
				}else if("coverstate".equals(searchType)){
					if(searchWord.contains("是积分兑换商品")){
						covertstate =IrpGoods.COVERT;
					}
					example.createCriteria().andCoverstateEqualTo(covertstate);
				}
				//example.createCriteria().andGoodsnameLike("%"+searchWord+"%");
			}
			/*if(searchWord!=null&&searchWord.trim().length()>0){
				example.createCriteria().andGoodsnameLike("%"+searchWord+"%");
			}*/
			msg=irpGoodsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public int countGoods(Long coverstate,String searchWord) {
		int msg = 0;
		try {
			IrpGoodsExample example = new IrpGoodsExample();
			if(coverstate==null||coverstate.equals("")){
				if(searchWord!=null&&searchWord.trim().length()>0){
					
					example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
					.andSalestateEqualTo(IrpGoods.SALE_STATE);
				}else{
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE);
				}
			}else{
				if(searchWord!=null&&searchWord.trim().length()>0){
					
					example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
					.andSalestateEqualTo(IrpGoods.SALE_STATE).andCoverstateEqualTo(coverstate);
				}else{
					example.createCriteria().andSalestateEqualTo(IrpGoods.SALE_STATE)
					.andCoverstateEqualTo(coverstate);
				}
			}
			msg=irpGoodsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	private IrpUserService irpUserService;
	private IrpUserValueLinkService irpUserValueLinkService;
	private IrpUserMedalService irpUserMedalService;
	private IrpMedalService irpMedalService;
	
	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}

	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}

	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}

	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	
	
	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}

	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}

	@Override
	public int countGoodsByUserid(Long userid,String searchWord) {
		int msg = 0;
		IrpMedal medal=null;
		try {
			List<String> liststring=new ArrayList<String>();
			List<IrpMedal> list=new ArrayList<IrpMedal>();
			IrpUser irpUser = irpUserService.findUserByUserId(userid);
			List<IrpUserMedal> listmedal = irpUserMedalService.listUserMedal(LoginUtil.getLoginUserId(),null,0l);
			for(int i=0;i<listmedal.size();i++){
				medal=irpMedalService.findMedalByMedalid(listmedal.get(i).getMedalid());
				list.add(medal);
				liststring.add(listmedal.get(i).getMedalid().toString());
			}
			IrpGoodsExample example = new IrpGoodsExample();
			if(searchWord!=null&&searchWord.trim().length()>0){
				example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
						.andNeedscoreLessThanOrEqualTo(irpUser.getSumscore())
						.andSalestateEqualTo(IrpGoods.SALE_STATE));
				if(liststring.size()>0){
					example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
							.andMedalidNotEqualTo(0l)
							.andMedalidIn(liststring)
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}else{
				}
				for(int j=0;j<list.size();j++){
					example.or(example.createCriteria().andGoodsnameLike("%"+searchWord+"%")
							.andNeedscoreBetween(list.get(j).getMinworth(), list.get(j).getMaxworth())
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}
				
			}else{
				example.or(example.createCriteria().andNeedscoreLessThanOrEqualTo(irpUser.getSumscore())
						.andSalestateEqualTo(IrpGoods.SALE_STATE));
				if(liststring.size()>0){
					example.or(example.createCriteria().andMedalidNotEqualTo(0l)
							.andMedalidIn(liststring)
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}else{
				}
				for(int j=0;j<list.size();j++){
					example.or(example.createCriteria().andNeedscoreBetween(list.get(j).getMinworth(), list.get(j).getMaxworth())
							.andSalestateEqualTo(IrpGoods.SALE_STATE));
				}
			}
			msg=irpGoodsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public boolean isexitcode(String goodsno) {
		boolean msg = true;
		int count =0;
		 count=this.irpGoodsDAO.selectgoodsno(goodsno);
		 if(count>0){
			 msg = false; 
		 }
		return msg;
	}

	@Override
	public List<IrpGoods> selectByReorder(int reorder) {
		List<IrpGoods> list=null;
		IrpGoodsExample example = new IrpGoodsExample();
		example.createCriteria().andReorderGreaterThanOrEqualTo(reorder);
		try {
			list=irpGoodsDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpGoods selectByeqReorder(int newreorder) {
		List<IrpGoods> list=null;
		IrpGoodsExample example = new IrpGoodsExample();
		example.createCriteria().andReorderEqualTo(newreorder);
		try {
			list=irpGoodsDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}
}
