package com.tfs.irp.file.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.file.dao.IrpUserFileDAO;
import com.tfs.irp.file.entity.IrpUserFile;
import com.tfs.irp.file.entity.IrpUserFileExample;
import com.tfs.irp.file.service.IrpUserFileService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpUserFileServiceImpl implements IrpUserFileService {
	private IrpUserFileDAO irpUserFileDAO;
	
	private IrpAttachedTypeService irpAttachedTypeService;

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public IrpUserFileDAO getIrpUserFileDAO() {
		return irpUserFileDAO;
	}

	public void setIrpUserFileDAO(IrpUserFileDAO irpUserFileDAO) {
		this.irpUserFileDAO = irpUserFileDAO;
	}
	
	@Override
	public void insertUserFile(String _fileName) {
		IrpUserFile userFile = new IrpUserFile();
		userFile.setUserfileid(TableIdUtil.getNextId(IrpUserFile.TABLE_NAME));
		userFile.setFilename(_fileName);
		//获得文件后缀名
		String sExt = FileUtil.findFileExt(_fileName);
		userFile.setFileext(sExt);
		//根据后缀名获得文件类型ID
		long nTypeId = irpAttachedTypeService.findAttachedTypeIdByFileExt(sExt);
		userFile.setFiletypeid(nTypeId);
		IrpUser loginUser = LoginUtil.getLoginUser();
		userFile.setCruserid(loginUser.getUserid());
		userFile.setCruser(loginUser.getUsername());
		userFile.setCrtime(new Date());
		try {
			irpUserFileDAO.insertSelective(userFile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertUserFile1(String _fileName) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		IrpUserFile userFile = new IrpUserFile();
		userFile.setUserfileid(TableIdUtil.getNextId(IrpUserFile.TABLE_NAME));
		userFile.setFilename(_fileName);
		//获得文件后缀名
		String sExt = FileUtil.findFileExt(_fileName);
		userFile.setFileext(sExt);
		//根据后缀名获得文件类型ID
		long nTypeId = irpAttachedTypeService.findAttachedTypeIdByFileExt(sExt);
		userFile.setFiletypeid(nTypeId);
		IrpUser loginUser = mobileAction.getlogin(token);
		userFile.setCruserid(loginUser.getUserid());
		userFile.setCruser(loginUser.getUsername());
		userFile.setCrtime(new Date());
		try {
			irpUserFileDAO.insertSelective(userFile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteUserFileByFileName(String _fileName) {
		IrpUserFileExample example = new IrpUserFileExample();
		example.createCriteria().andFilenameEqualTo(_fileName);
		try {
			irpUserFileDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<IrpUserFile> userAllFileByType(Long _userId, Long _typeId,PageUtil pageUtil) {
		List<IrpUserFile> list=null;
		IrpUserFileExample  example=new IrpUserFileExample();
		example.createCriteria().andCruseridEqualTo(_userId)
									.andFiletypeidEqualTo(_typeId);
		
		try {
			example.setOrderByClause(" userfileid desc "); 
			list=irpUserFileDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpUserFile> userAllFileByType(Long _userId, Long _typeId) {
		List<IrpUserFile> list=null;
		IrpUserFileExample  example=new IrpUserFileExample();
		example.createCriteria().andCruseridEqualTo(_userId)
									.andFiletypeidEqualTo(_typeId);
		
		try {
			example.setOrderByClause(" userfileid desc "); 
			list=irpUserFileDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int  FileAmount(Long _userId, Long _typeId) {
		int nCount=0;
		IrpUserFileExample  example=new IrpUserFileExample();
		example.createCriteria().andCruseridEqualTo(_userId)
									.andFiletypeidEqualTo(_typeId);
		try {
			nCount=irpUserFileDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
}
