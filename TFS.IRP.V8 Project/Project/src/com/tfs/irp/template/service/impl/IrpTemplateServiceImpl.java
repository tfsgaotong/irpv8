package com.tfs.irp.template.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.template.dao.IrpTemplateDAO;
import com.tfs.irp.template.entity.IrpTemplate;
import com.tfs.irp.template.entity.IrpTemplateExample;
import com.tfs.irp.template.service.IrpTemplateService;
import com.tfs.irp.util.PageUtil;

public class IrpTemplateServiceImpl implements IrpTemplateService {
	
		private IrpTemplateDAO irpTemplateDAO;

		public IrpTemplateDAO getIrpTemplateDAO() {
			return irpTemplateDAO;
		}

		public void setIrpTemplateDAO(IrpTemplateDAO irpTemplateDAO) {
			this.irpTemplateDAO = irpTemplateDAO;
		}

		@Override
		public List<IrpTemplate> findTemplateList(PageUtil _pageutil,
				Integer _status, Integer _isdel, String _orderstr,Long _tcateid,
				String _searchType, String _searchStr) {
			// TODO Auto-generated method stub
			List<IrpTemplate> list = null;
			IrpTemplateExample example = new IrpTemplateExample();
			if ("tvaluedesc".equals(_searchType) || "all".equals(_searchType)) {
				example.createCriteria().andTstatusEqualTo(_status)
										.andTisdelEqualTo(_isdel)
										.andTcateEqualTo(_tcateid)
										.andTvaluedescLike("%"+_searchStr+"%");
			}else{
				example.createCriteria().andTstatusEqualTo(_status)
										.andTcateEqualTo(_tcateid)
										.andTisdelEqualTo(_isdel);
			}						
			if(_orderstr==null || _orderstr.equals("")){
				_orderstr="tid desc";
			}						
			example.setOrderByClause(_orderstr);						
			try {
				list = this.irpTemplateDAO.selectByExampleWithBLOBs(example, _pageutil);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return list;
		}

		@Override
		public int findTemplateListNum(Integer _status, Integer _isdel,Long _tcateid,
				String _searchType, String _searchStr) {
			// TODO Auto-generated method stub
			int num = 0;
			IrpTemplateExample example = new IrpTemplateExample();
			if ("tvaluedesc".equals(_searchType) || "all".equals(_searchType)) {
				example.createCriteria().andTstatusEqualTo(_status)
										.andTisdelEqualTo(_isdel)
										.andTcateEqualTo(_tcateid)
										.andTvaluedescLike("%"+_searchStr+"%");
			}else{
				example.createCriteria().andTstatusEqualTo(_status)
										.andTcateEqualTo(_tcateid)
										.andTisdelEqualTo(_isdel);
			}
			try {
				num = irpTemplateDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return num;
		}

		@Override
		public int addTemplate(IrpTemplate _irptemplate) {
			// TODO Auto-generated method stub
			int status = 0;
			try {
				if (_irptemplate!=null) {
					this.irpTemplateDAO.insertSelective(_irptemplate);
					status = 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return status;
		}

		@Override
		public int updateTemplate(IrpTemplate _irptemplate) {
			// TODO Auto-generated method stub
			int status = 0;
			try {
				if (_irptemplate!=null) {
					status = this.irpTemplateDAO.updateByPrimaryKeySelective(_irptemplate);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return status;
		}

		@Override
		public IrpTemplate irpTemplateById(Long _tid) {
			// TODO Auto-generated method stub
			IrpTemplate irpTemplate = null;
			if (_tid!=null) {
				if (_tid>0) {
					try {
						irpTemplate = this.irpTemplateDAO.selectByPrimaryKey(_tid);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return irpTemplate;
		}

		@Override
		public int cateTemplate(Long _cateid, Integer _status, Integer _isdel) {
			// TODO Auto-generated method stub
			int num = 0;
			IrpTemplateExample example = new IrpTemplateExample();
			example.createCriteria().andTcateEqualTo(_cateid)
									.andTstatusEqualTo(_status)
									.andTisdelEqualTo(_isdel);
									
			try {
				num = this.irpTemplateDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return num;
		}

		@Override
		public List<IrpTemplate> findTemListByCId(Long _cateid,
				Integer _status, Integer _isdel,PageUtil _pageutil) {
			// TODO Auto-generated method stub
			List<IrpTemplate> list  = null;
			IrpTemplateExample example = new IrpTemplateExample();
			example.createCriteria().andTcateEqualTo(_cateid)
									.andTstatusEqualTo(_status)
									.andTisdelEqualTo(_isdel);
			
			try {
				list = this.irpTemplateDAO.selectByExampleWithBLOBs(example, _pageutil);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return list;
		}

		@Override
		public int findTemListByCIdNum(Long _cateid, Integer _status,
				Integer _isdel) {
			// TODO Auto-generated method stub
			int num = 0;
			IrpTemplateExample example = new IrpTemplateExample();
			example.createCriteria().andTcateEqualTo(_cateid)
									.andTstatusEqualTo(_status)
									.andTisdelEqualTo(_isdel);
			
			try {
				num = this.irpTemplateDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return num;
		}
	
	
	
	
}
