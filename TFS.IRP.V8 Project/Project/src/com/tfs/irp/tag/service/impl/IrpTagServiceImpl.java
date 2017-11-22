package com.tfs.irp.tag.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tfs.irp.tag.entity.IrpTagExample.Criteria;
import com.tfs.irp.tag.dao.IrpTagDAO;
import com.tfs.irp.tag.dao.IrpTagTypeDAO;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpTagExample;
import com.tfs.irp.tag.entity.IrpTagType;
import com.tfs.irp.tag.entity.IrpTagTypeExample;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTagServiceImpl implements IrpTagService {
	private IrpTagDAO irpTagDAO;
	
	private IrpTagTypeDAO irpTagTypeDAO;

	public IrpTagTypeDAO getIrpTagTypeDAO() {
		return irpTagTypeDAO;
	}

	public void setIrpTagTypeDAO(IrpTagTypeDAO irpTagTypeDAO) {
		this.irpTagTypeDAO = irpTagTypeDAO;
	}

	public IrpTagDAO getIrpTagDAO() {
		return irpTagDAO;
	}

	public void setIrpTagDAO(IrpTagDAO irpTagDAO) {
		this.irpTagDAO = irpTagDAO;
	}
	
	@Override
	public List<IrpTag> findTagsByUserId(long _nUserId) {
		List<IrpTag> list = null;
		try {
			list = irpTagDAO.selectTagByUserId(_nUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public IrpTag findTagByTagName(String _sTagName) {
		IrpTag irpTag = null;
		IrpTagExample example = new IrpTagExample();
		example.createCriteria().andTagnameEqualTo(_sTagName);
		try {
			List<IrpTag> list = irpTagDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				irpTag = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpTag;
	}
	
	@Override
	public long addTag(IrpTag _irpTag) {
		long nTagId = 0L;
		try {
			long nTempId = TableIdUtil.getNextId(IrpTag.TABLE_NAME);
			_irpTag.setTagid(nTempId);
			irpTagDAO.insertSelective(_irpTag);
			nTagId = nTempId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nTagId;
	}
	@Override
	public int updateTagByTagId(IrpTag irpTag) {
		int nCount=0;
		try {
			IrpTagExample example=new IrpTagExample();
			example.createCriteria().andTagidEqualTo(irpTag.getTagid());
			nCount=irpTagDAO.updateByExample(irpTag, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<IrpTag> findAllTag(int amount) {
		List<IrpTag> tags=null;
		try {
			IrpTagExample example=new IrpTagExample();
			example.setOrderByClause(" ncount desc ");
			int aDataCount = irpTagDAO.countByExample(example);
			PageUtil pageUtil = new PageUtil(1,amount, aDataCount);// 第一页，显示amount条  
			tags=irpTagDAO.selectByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tags;
	}
	//添加标签
	@Override
		public String  addTag(String sTags){
			if(sTags==null)
				return null;
			sTags = sTags.trim();
			//替换所有的分隔符为逗号
			sTags = sTags.replaceAll(" ", ",");
			sTags = sTags.replaceAll("；", ",");
			sTags = sTags.replaceAll(";", ",");
			sTags = sTags.replaceAll("，", ","); 
			try { 
				String[] arrTags = sTags.split(",");
				for(int i=0;i<arrTags.length;i++){
					String sTag = arrTags[i].trim();
					if(sTag==null || sTag.length()==0){
						continue;
					}
					IrpTagExample example = new IrpTagExample();
					example.createCriteria().andTagnameEqualTo(sTag);
					List<IrpTag> list = irpTagDAO.selectByExample(example);  
					if(list==null ||list.size()==0){
						IrpTag record=new IrpTag();
						record.setTagid(TableIdUtil.getNextId(IrpTag.TABLE_NAME));
						record.setTagname(sTag);
						irpTagDAO.insertSelective(record);
					}else{//若果有 
						IrpTag tag=list.get(0);
						tag.setNcount(tag.getNcount()+1);
						irpTagDAO.updateByPrimaryKey(tag);
					}
				}
			} catch (Exception e) {
					e.printStackTrace();
			} 
			/**
			 * 将字符串中的重复词语给去掉
			 */
			HashSet<String> onlyStr=new HashSet<String>();
			if(sTags!=null && sTags.length()>0){
				String[] strs=sTags.split(",");
				for (int i = 0; i < strs.length; i++) {
					onlyStr.add(strs[i]);
				}
			}
			/**
			 * 如果标签不为null
			 */
			String sKey="";
			if(onlyStr.size()>0){
				Iterator<String> key=onlyStr.iterator(); 
				while(key.hasNext()){
					String value=key.next();
					if(value!=null && !value.trim().equals("")){
						sKey+=value+",";
					}
				} 
			} 
			return sKey.trim();
		}

	@Override
	public List<IrpTag> findHotIndexWord(Integer _pagenum) {
		// TODO Auto-generated method stub
		List<IrpTag> list = null;
		IrpTagExample example = new IrpTagExample();
		example.setOrderByClause("ncount desc");
		PageUtil pageUtil = new PageUtil(1, _pagenum, 0);
		try {
			list = this.irpTagDAO.selectByExample(pageUtil, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<String> findTagName() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList();
		IrpTagExample example = new IrpTagExample();
		 try {
			List<IrpTag> taglist = this.irpTagDAO.selectByExample(example);
			if(taglist.size()>0 && taglist!=null){
				for (int i = 0; i < taglist.size(); i++) {
					IrpTag irpTag = taglist.get(i);
					list.add(irpTag.getTagname());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<IrpTag> findIrpTagsByTagType(long _nTagType) {
		List<IrpTag> list = null;
		IrpTagExample example = new IrpTagExample();
		example.createCriteria().andTagtypeidEqualTo(_nTagType);
		try {
			list = irpTagDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int clearTagTypeByTypeId(long _nTagType) {
		int nCount = 0;
		IrpTag tag = new IrpTag();
		tag.setTagtypeid(0L);
		IrpTagExample example = new IrpTagExample();
		example.createCriteria().andTagtypeidEqualTo(_nTagType);
		try {
			nCount = irpTagDAO.updateByExampleSelective(tag, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int updateTagSelectiveByTagId(IrpTag irpTag) {
		int nCount=0;
		try {
			nCount=irpTagDAO.updateByPrimaryKeySelective(irpTag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int countNotSelectIrpTagsLikeTagName(String _sTagName) {
		int nCount = 0;
		IrpTagExample example = new IrpTagExample();
		Criteria criteria = example.createCriteria();
		criteria.andTagtypeidEqualTo(0L);
		if(_sTagName!=null && !_sTagName.isEmpty()){
			criteria.andTagnameLike("%"+_sTagName+"%");
		}
		try {
			nCount = irpTagDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpTag> findNotSelectIrpTagsLikeTagName(String _sTagName, PageUtil pageUtil, String _sOrderByStr) {
		List<IrpTag> list = null;
		IrpTagExample example = new IrpTagExample();
		Criteria criteria = example.createCriteria();
		criteria.andTagtypeidEqualTo(0L);
		if(_sTagName!=null && !_sTagName.isEmpty()){
			criteria.andTagnameLike("%"+_sTagName+"%");
		}
		example.setOrderByClause(_sOrderByStr);
		try {
			list = irpTagDAO.selectByExample(pageUtil, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int importTagsByTypeId(long _nTypeId, String _sTagIds) {
		int nCount=0;
		IrpTag tag = new IrpTag();
		tag.setTagtypeid(_nTypeId);
		
		IrpTagExample example = new IrpTagExample();
		List<Long> values = new ArrayList<Long>();
		String[] arrTagIds = _sTagIds.split(",");
		for (String sTagId : arrTagIds) {
			if(sTagId==null || sTagId.isEmpty())
				continue;
			try {
				values.add(Long.parseLong(sTagId));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		example.createCriteria().andTagidIn(values);
		try {
			nCount = irpTagDAO.updateByExampleSelective(tag, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public Map<String, List<IrpTag>> findDocIndexTagList() {
		Map<String, List<IrpTag>> map = new HashMap<String, List<IrpTag>>();
		int nTagTypeNum = SysConfigUtil.getSysConfigNumValue("DOCINDEX_TAGTYPE_NUM");
		int nTagNum = SysConfigUtil.getSysConfigNumValue("DOCINDEX_TAG_NUM");
		PageUtil pageUtil = new PageUtil(1,nTagTypeNum,nTagTypeNum);
		List<IrpTagType> hotTagTypes = null;
		try {
			//获得最热的标签类型
			hotTagTypes = irpTagTypeDAO.selectHotByExample(new IrpTagTypeExample(), pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(hotTagTypes==null || hotTagTypes.isEmpty()){
			return null;
		}
		//查询标签类型下的标签集合
		pageUtil = new PageUtil(1, nTagNum, nTagNum);
		IrpTagExample example = new IrpTagExample();
		List<IrpTag> tags = null;
		for (IrpTagType irpTagType : hotTagTypes) {
			if(irpTagType==null)
				continue;
			example.clear();
			example.createCriteria().andTagtypeidEqualTo(irpTagType.getTypeid());
			example.setOrderByClause(" ncount desc,tagid desc ");
			try {
				tags = irpTagDAO.selectByExample(pageUtil, example);
				if(tags!=null && !tags.isEmpty()){
					map.put(irpTagType.getTypename(), tags);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
