package com.tfs.irp.documentmap.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.documentmap.dao.IrpDocumentMapDAO;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample.Criteria;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.util.TableIdUtil;

public class IrpDocumentMapServiceImpl implements IrpDocumentMapService{
	IrpDocumentMapDAO irpDocumentMapDao;
	@Override
	public void addDocumentMap(Long docid, Long channelid,String type) {
 		Long mid=TableIdUtil.getNextId(IrpDocumentMap.TABLE_NAME);//他的主键值
		IrpDocumentMap documentMap=new IrpDocumentMap();
		documentMap.setMid(mid);
		documentMap.setDocid(docid);
		documentMap.setChannelid(channelid);
		documentMap.setCrtime(new Date());
		documentMap.setType(type);
		try {
			irpDocumentMapDao.insertSelective(documentMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<IrpDocumentMap>  oneDocDocumentMap(Long docid,String type) {
		// TODO Auto-generated method stub
		List<IrpDocumentMap> list=null;
		try {
			IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
			documentMapExample.createCriteria().andDocidEqualTo(docid)
												.andTypeEqualTo(IrpDocumentMap.KONWLEDGE_MAP);
			list=irpDocumentMapDao.selectByExample(documentMapExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void deltetDocumentMap(Long PrimaryKey) {
		try {
			irpDocumentMapDao.deleteByPrimaryKey(PrimaryKey);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	public IrpDocumentMapDAO getIrpDocumentMapDao() {
		return irpDocumentMapDao;
	}
	public void setIrpDocumentMapDao(IrpDocumentMapDAO irpDocumentMapDao) {
		this.irpDocumentMapDao = irpDocumentMapDao;
	}
	@Override
	public List<Long> docidsByChannelid(Long channelid) {
		List<Long> docids=null;
		try {
			IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
			documentMapExample.createCriteria().andChannelidEqualTo(channelid);
			docids=irpDocumentMapDao.selectDociIdsByExample(documentMapExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docids;
	}
	@Override
	public int deleteDocumentByMapId(Long channelid, List<Long> docids) {
		// TODO Auto-generated method stub
		int nCount=0;
		IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
		Criteria criteria = documentMapExample.createCriteria();
		//处理Oracle在执行in时超过1000个报错
		if(docids!=null && docids.size()>1000){
			List<Long> tempDocids=new ArrayList<Long>();
			for (int i = 0; i < docids.size(); i++) {
				tempDocids.add(docids.get(i));
				if(tempDocids.size()==1000 || (i+1)==docids.size()){
					documentMapExample.or(documentMapExample.createCriteria().andDocidIn(tempDocids));
					tempDocids=new ArrayList<Long>();
				}
			}
		}else{
			criteria.andDocidIn(docids);
		}
		criteria.andChannelidEqualTo(channelid);
		try {
			nCount=irpDocumentMapDao.deleteByExample(documentMapExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public IrpDocumentMap findDocumentMap(Long channelid, Long docid, String type) {
		IrpDocumentMap map=null;
		IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
		documentMapExample.createCriteria().andDocidEqualTo(docid)
							.andChannelidEqualTo(channelid)
							.andTypeEqualTo(type);
		try {
			List<IrpDocumentMap> maps=irpDocumentMapDao.selectByExample(documentMapExample);
			if(maps!=null && maps.size()>0){
				map=maps.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public List<Long> docidsByChannelidS(List<Long> _channelids,String type) {
		List<Long> docids=null;
		try {
			IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
			documentMapExample.createCriteria().andChannelidIn(_channelids)
												.andTypeEqualTo(type);
			docids=irpDocumentMapDao.selectDociIdsByExample(documentMapExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docids;
	}
	@Override
	public List<Long> docidsByChannelid(Long channelid, List<Long> docids) {
		List<Long> docidsList=null;
		try {
			IrpDocumentMapExample documentMapExample=new IrpDocumentMapExample();
			documentMapExample.createCriteria().andChannelidEqualTo(channelid)
												.andDocidIn(docids);
			docidsList=irpDocumentMapDao.selectDociIdsByExample(documentMapExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docidsList;
	}
	@Override
	public List<IrpDocumentMap> selectMapByExample(IrpDocumentMapExample example) {
		try {
			return irpDocumentMapDao.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int deleteDocSubByExample(IrpDocumentMapExample example) {
		try {
			return irpDocumentMapDao.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public void addDocSubByExample(IrpDocumentMap map) {
		try {
			irpDocumentMapDao.insertSelective(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
