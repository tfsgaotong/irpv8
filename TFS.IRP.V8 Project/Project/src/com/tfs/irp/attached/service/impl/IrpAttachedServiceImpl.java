package com.tfs.irp.attached.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.attached.dao.IrpAttachedDAO;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedExample;
import com.tfs.irp.attached.entity.IrpAttachedExample.Criteria;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.dao.IrpAttachedTypeDAO;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpAttachedServiceImpl implements IrpAttachedService {
	private IrpAttachedDAO irpAttachedDAO;
	private IrpAttachedTypeDAO irpAttachedTypeDAO;
	
	
	@Override
	public IrpAttached getIrpATttachedByDocIDFIle(Long _docid) {
		// TODO Auto-generated method stub
		 
		IrpAttached attached=null;
		if(_docid==null ||_docid==0L)return attached;
		IrpAttachedExample attachedExample=new IrpAttachedExample();
		attachedExample.createCriteria().andAttdocidEqualTo(_docid)
										.andEditversionsEqualTo(IrpAttached.IS_FENGMIAN).andAttdocidtypeEqualTo(0);
		try {
			List<IrpAttached> attacheds=irpAttachedDAO.selectByExampleWithBLOBs(attachedExample);
			if(attacheds!=null &&attacheds.size()>0){
				attached=attacheds.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return attached;
	}
	@Override
	public int findFileAmountByDocid(Long docid) {
		// TODO Auto-generated method stub
		int nCount=0;
		IrpAttachedExample example=new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(docid);
		try {
			nCount=irpAttachedDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public void deleteAttachedByIdNotInList(List<Long> _arrAttchedid, Long _docid) { 
		 try {
			 IrpAttachedExample example=new IrpAttachedExample();
				example.createCriteria().andAttdocidEqualTo(_docid)
										.andAttachedidNotIn(_arrAttchedid);
			int nC=irpAttachedDAO.deleteByExample(example); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	} 
	@Override
	public IrpAttached getAttachedByPrimary(Long _attachedid) {
		// TODO Auto-generated method stub
		IrpAttached attached=null;
		try {
			attached = irpAttachedDAO.selectByPrimaryKey(_attachedid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attached;
	}
	@Override
	public int deleteAttachedByAttidAndDocid(Long _attdocid, String[] _arrAttid) { 
		List<Long> _arrAttids=new ArrayList<Long>();
		int nCount=0;
		if(_arrAttid!=null && _arrAttid.length>0){
			for (int i = 0; i < _arrAttid.length; i++) {
				_arrAttids.add(new Long(_arrAttid[i]));
			}
		}
		IrpAttachedExample example=new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_attdocid)
								.andAttachedidIn(_arrAttids);
		try {
			nCount=irpAttachedDAO.deleteByExample(example);
		} catch (SQLException e) { 
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Integer _attdocidtype) {
		List<IrpAttached> arrIrpAttacheds=null;
		try {
			arrIrpAttacheds=irpAttachedDAO.getAttacehedByAttDocId(_attDocId,_attdocidtype);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return arrIrpAttacheds;
	}
	@Override
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Integer _attdocidtype,PageUtil pageUtil) {
		List<IrpAttached> arrIrpAttacheds=null;
		try {
			IrpAttachedExample attachedExample=new IrpAttachedExample();
			Criteria criteria = attachedExample.createCriteria();
			criteria.andAttdocidEqualTo(_attDocId);
			criteria.andAttdocidtypeEqualTo(_attdocidtype);
			arrIrpAttacheds=irpAttachedDAO.getAttachedByExampleSelf(attachedExample, pageUtil);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return arrIrpAttacheds;
	} 
	@Override
	public int delAttachedByPrimaryKey(Long _attachedid) {
		int nCount=0;
		try {
			nCount=irpAttachedDAO.deleteByPrimaryKey(_attachedid);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return nCount;
	}
	
	
	public IrpAttachedDAO getIrpAttachedDAO() {
		return irpAttachedDAO;
	}

	public void setIrpAttachedDAO(IrpAttachedDAO irpAttachedDAO) {
		this.irpAttachedDAO = irpAttachedDAO;
	}
	public IrpAttachedTypeDAO getIrpAttachedTypeDAO() {
		return irpAttachedTypeDAO;
	}
	public void setIrpAttachedTypeDAO(IrpAttachedTypeDAO irpAttachedTypeDAO) {
		this.irpAttachedTypeDAO = irpAttachedTypeDAO;
	}

	@Override
	public List<IrpAttached> getPortionAttached(Long _attDocid,Integer _attdocidtype) {
		// TODO Auto-generated method stub
		List<IrpAttached> list = null;
		
		try {
			list = this.irpAttachedDAO.getPortionAttached(_attDocid,_attdocidtype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Long  addFile(Long _attdocid, Long _attprop, String _attFile,String _attlintalt, String _attDesc, Integer attdocidType,String newFilePath,Integer _sEditversions,Long TypeId,Integer
			_attflag) {

		 Long _attachedid=TableIdUtil.getNextId(IrpAttached.TABLE_NAME);
		try {
			IrpAttached irpAttached=new IrpAttached();
			IrpUser irpUser=LoginUtil.getLoginUser();
			String _lastName=FileUtil.findFileExt(_attFile); //得到后缀名
			List<IrpAttachedType> _arrSufflx=null;
			 irpAttached.setAttprop(_attprop);//任务或者项目id或者为0	L；
			 irpAttached.setAttachedid(_attachedid);// ;//他的主键值   
		     irpAttached.setAttdocid(_attdocid);//文档编号
			 irpAttached.setAtttime(new Date());//设置上传时间
			 irpAttached.setCrtime(new Date());//创建时间 
			 irpAttached.setCruser(LoginUtil.getUserNameString(irpUser)); 
			 irpAttached.setCruserid(irpUser.getUserid());   
		     irpAttached.setAttfile(_attFile);//文件名称
		     irpAttached.setAttdesc(_attDesc);//附件描述
		     irpAttached.setAttlinkalt(_attlintalt);//附件alt
		     irpAttached.setSrcfile(newFilePath);///源文件名完整路径
		     irpAttached.setFileext(_lastName);//文件的扩展名 
		     irpAttached.setTypeid(TypeId);
		     irpAttached.setAttflag(_attflag);
			_arrSufflx = irpAttachedTypeDAO.getSuffixExits(_lastName);
			if(_sEditversions!=null){
				irpAttached.setEditversions(_sEditversions);
			}
			if(_arrSufflx!=null && _arrSufflx.size()>0){//存在就查看临时表中的文件是否存在    
				irpAttached.setTypeid(_arrSufflx.get(0).getTypeid());
			}else{//如果没有查到就是其他类型5
				irpAttached.setTypeid(IrpAttachedType.ID_FIELD_NAMEOTHER);
			}  
			irpAttached.setAttdocidtype(attdocidType);
			int nCount=countFile(_attdocid,attdocidType);
			irpAttached.setAttsern(Long.valueOf(nCount+1));//添加的附件排序默认为最大的
			irpAttachedDAO.insertSelective(irpAttached);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _attachedid;
	}
	@Override
	public int deleteFileByExpt(Long _attdocid, Integer _attdocidType) {
		int nCount=0;
		IrpAttachedExample example=new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_attdocid)
								.andAttdocidtypeEqualTo(_attdocidType) ;
		try {
			List<IrpAttached> listFile=irpAttachedDAO.selectByExampleWithoutBLOBs(example);
			if(listFile!=null){
				for (IrpAttached irpAttached : listFile) {
					SysFileUtil.deleteFileByFileName(irpAttached.getAttfile());
				}
				nCount=irpAttachedDAO.deleteByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	} 
	@Override
	public int deleteFileByExpt(Long _attdocid, Integer _attdocidType,Long _attprop) {
		int nCount=0;
		IrpAttachedExample example=new IrpAttachedExample();
		Criteria criteria = example.createCriteria();
		criteria.andAttdocidEqualTo(_attdocid)
								.andAttdocidtypeEqualTo(_attdocidType);
		if(_attprop!=null){
			criteria.andAttpropEqualTo(_attprop);
		}
		try {
			List<IrpAttached> listFile=irpAttachedDAO.selectByExampleWithoutBLOBs(example);
			if(listFile!=null){
				for (IrpAttached irpAttached : listFile) {
					boolean b=SysFileUtil.deleteFileByFileName(irpAttached.getAttfile());
				}
				nCount=irpAttachedDAO.deleteByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int countFile(Long _attDocid, Integer _attDocidType) {
		int nCount=0;
		IrpAttachedExample attachedExample = new IrpAttachedExample();
		attachedExample.createCriteria().andAttdocidEqualTo(_attDocid)
										.andAttdocidtypeEqualTo(_attDocidType);
		try {
			nCount=irpAttachedDAO.countByExample(attachedExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<IrpAttached> getAttachedByAttDocId(Long _attDocId,Long _attProp, Integer _attdocidtype) {
		List<IrpAttached> attacheds=null;
		IrpAttachedExample attachedExample=new IrpAttachedExample();
		attachedExample.createCriteria().andAttdocidEqualTo(_attDocId)
											.andAttpropEqualTo(_attProp)
											.andAttdocidtypeEqualTo(_attdocidtype);
		try {
			attacheds=irpAttachedDAO.selectByExampleWithBLOBs(attachedExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attacheds;
	}
	@Override
	public int udpateAttachedByExprt(Long docid, Long attId, Integer _sEditversions,Integer _attflag) {
		// TODO Auto-generated method stub
		int nCount=0;
		//修改这个附件（根据这个附件id，修改附件是否为封面）
		IrpAttached record=new IrpAttached();
		if(_sEditversions.intValue()==IrpAttached.IS_FENGMIAN.intValue()){ 
			record.setEditversions(IrpAttached.IS_FENGMIAN) ;//是否为封面 1为封面，
	     }
	     if(_sEditversions.intValue()==IrpAttached.NO_FENGMIAN.intValue()){ 
	    	 record.setEditversions(IrpAttached.NO_FENGMIAN) ;// 否为封面 
	     }
	     if(_sEditversions.intValue()==IrpAttached.NULL_FENGMIAN.intValue()){ 
	    	 record.setEditversions(IrpAttached.NULL_FENGMIAN) ;// 否为封面 标示他是文件附件，不是图片附件
	     }
	     record.setAttflag(_attflag);
		IrpAttachedExample example=new IrpAttachedExample();
		example.createCriteria().andAttachedidEqualTo(attId)
								.andAttdocidEqualTo(docid);	
		try {
			nCount=irpAttachedDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nCount;
	}
	@Override
	public int deleteFileNotInList(List<Long> _arrAttachedid, Long docid,
			Integer attDocid) {
		int nCount=0;
	    IrpAttachedExample attexample=new IrpAttachedExample();
	    attexample.createCriteria().andAttdocidEqualTo(docid).
								    andAttachedidNotIn(_arrAttachedid)
								    .andAttdocidtypeEqualTo(attDocid);
	    try {
			List<IrpAttached> listFile=irpAttachedDAO.selectByExampleWithoutBLOBs(attexample);
			if(listFile!=null){
				for (IrpAttached irpAttached : listFile) {
					boolean b=SysFileUtil.deleteFileByFileName(irpAttached.getAttfile());
				}
				nCount=irpAttachedDAO.deleteByExample(attexample);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int deleteFileInList(List<Long> _arrAttachedid, Long docid,
			Integer attDocid) {
		// TODO Auto-generated method stub
		int nCount=0;
	    IrpAttachedExample attexample=new IrpAttachedExample();
	    attexample.createCriteria().andAttdocidEqualTo(docid).
								    andAttachedidIn(_arrAttachedid)
								    .andAttdocidtypeEqualTo(attDocid);
	    try {
			List<IrpAttached> listFile=irpAttachedDAO.selectByExampleWithoutBLOBs(attexample);
			if(listFile!=null){
				for (IrpAttached irpAttached : listFile) {
					boolean b=SysFileUtil.deleteFileByFileName(irpAttached.getAttfile());
				}
				nCount=irpAttachedDAO.deleteByExample(attexample);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	public List<IrpAttached> getSysAllExtractAttacheds() {
		List<IrpAttached> list = null;
		IrpAttachedExample example = new IrpAttachedExample();
		example.createCriteria().andAppendixcontentIsNull();
		try {
			list = irpAttachedDAO.selectByExampleWithBLOBs(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Long addFileForForm(Long _attdocid, String _realname,
			String _attDesc, Integer projectDocidtype, String newFilePath) {
		 Long _attachedid=TableIdUtil.getNextId(IrpAttached.TABLE_NAME);
			try {
				IrpAttached irpAttached=new IrpAttached();
				IrpUser irpUser=LoginUtil.getLoginUser();
				String _lastName=FileUtil.findFileExt(newFilePath); //得到后缀名
				 irpAttached.setAttachedid(_attachedid);// ;//他的主键值   
			     irpAttached.setAttdocid(_attdocid);//文档编号
				 irpAttached.setAtttime(new Date());//设置上传时间
				 irpAttached.setCrtime(new Date());//创建时间 
				 irpAttached.setAttdocidtype(projectDocidtype);
				 irpAttached.setCruser(LoginUtil.getUserNameString(irpUser)); 
				 irpAttached.setCruserid(irpUser.getUserid());   
			     irpAttached.setAttfile(_realname);//文件名称
			     irpAttached.setAttdesc(_attDesc);//附件描述
			     irpAttached.setSrcfile(newFilePath);///源文件名完整路径
			     irpAttached.setFileext(_lastName);//文件的扩展名 
				
			
				
				irpAttachedDAO.insertSelective(irpAttached);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return _attachedid;
	}
	@Override
	public int deleteByFormExcel(long _attdocid, Integer _attdocidType) {
		int nCount=0;
		IrpAttachedExample example=new IrpAttachedExample();
		example.createCriteria().andAttdocidEqualTo(_attdocid)
								.andAttdocidtypeEqualTo(_attdocidType) ;
		try {
			List<IrpAttached> listFile=irpAttachedDAO.selectByExampleWithoutBLOBs(example);
			if(listFile!=null){
				for (IrpAttached irpAttached : listFile) {
					// 删除目录下所有的文件;
					File file = new File(irpAttached.getSrcfile());
					// 删除文件;
					if (file.isFile() && file.exists()) {
						 file.delete();
					}
				}
				nCount=irpAttachedDAO.deleteByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List getAttachedByAttprop(Long taskid, Integer projectDocidtype) {
		List<IrpAttached> arrIrpAttacheds=null;
		try {
			arrIrpAttacheds=irpAttachedDAO.getAttachedByAttprop(taskid,projectDocidtype);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return arrIrpAttacheds;
	}
}
