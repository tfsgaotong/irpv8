package com.tfs.irp.subscribe.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.subscribe.dao.IrpSubscribeDAO;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.entity.IrpSubscribeExample;
import com.tfs.irp.subscribe.service.IrpSubscribeService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpSubscribeServiceImpl implements IrpSubscribeService {
	private IrpSubscribeDAO irpSubscribeDAO;
	private IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO;
	private IrpMessageContentService irpMessageContentService;

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}

	public IrpSubscribeDAO getIrpSubscribeDAO() {
		return irpSubscribeDAO;
	}

	public void setIrpSubscribeDAO(IrpSubscribeDAO irpSubscribeDAO) {
		this.irpSubscribeDAO = irpSubscribeDAO;
	}
	
	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}

	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}

	@Override
	public long addSubscribe(IrpBaseObj baseObj) {
		long nSubscribeId = 0;
		if(baseObj==null)
			return nSubscribeId;
		long loginUserId = LoginUtil.getLoginUserId();
		IrpSubscribe subscribe = new IrpSubscribe();
		subscribe.setSubscribeid(TableIdUtil.getNextId(subscribe));
		subscribe.setSubscribeobjid(baseObj.getId());
		subscribe.setSubscribeobjtype(baseObj.getTableName());
		subscribe.setSubscribeuserid(loginUserId);
		subscribe.setCruserid(loginUserId);
		subscribe.setCrtime(new Date());
		try {
			irpSubscribeDAO.insertSelective(subscribe);
			nSubscribeId = subscribe.getSubscribeid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nSubscribeId;
	}
	
	@Override
	public List<IrpSubscribe> findSubscribeByBaseObj(IrpBaseObj baseObj) {
		List<IrpSubscribe> list = null;
		if(baseObj==null)
			return list;
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andSubscribeobjidEqualTo(baseObj.getId()).andSubscribeobjtypeEqualTo(baseObj.getTableName());
		try {
			list = irpSubscribeDAO.selectByExample(example,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int deleteSubscribeByBaseObj(IrpBaseObj baseObj) {
		int nCount = 0;
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andSubscribeobjidEqualTo(baseObj.getId()).andSubscribeobjtypeEqualTo(baseObj.getTableName());
		try {
			nCount = irpSubscribeDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int deleteSubscribeByBaseObjAndUserId(IrpBaseObj baseObj, long UserId) {
		int nCount = 0;
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andSubscribeuseridEqualTo(UserId).andSubscribeobjidEqualTo(baseObj.getId()).andSubscribeobjtypeEqualTo(baseObj.getTableName());
		try {
			nCount = irpSubscribeDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public void sendSubscribeMessage(IrpBaseObj baseObj) {
		List<IrpSubscribe> arrSubscribe = findSubscribeByBaseObj(baseObj);
		if(arrSubscribe==null || arrSubscribe.size()==0)
			return;
		IrpMessageContent messageContent = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String redirectURI = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String sContent = "您订阅的知识&nbsp;["+baseObj.getName()+"]&nbsp;有更新，<a href=\""+redirectURI+"/document/document_detail.action?refrechstatus=1&docid="+baseObj.getId()+"\" target=\"_blank\"><b>点击查看</b></a>。";
		for (IrpSubscribe irpSubscribe : arrSubscribe) {
			messageContent = new IrpMessageContent();
			messageContent.setFromUid(irpSubscribe.getSubscribeuserid());
			messageContent.setContent(sContent);
			irpMessageContentService.addMessageContent(messageContent);
		}
		for (IrpSubscribe irpSubscribe : arrSubscribe) {
			irpSubscribe.setSubReadStatus(1L);
			try {
				irpSubscribeDAO.updateByPrimaryKey(irpSubscribe);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<IrpChnlDocLink> myAllDocSubscribe(HashMap<String, Object> map,
			PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks=null;
		List<IrpSubscribe> list = null;
		IrpSubscribeExample example = new IrpSubscribeExample();
		
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			example.setOrderByClause(map.get("sOrderByClause").toString());
			list = irpSubscribeDAO.selectByExample(example,pageUtil);
			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			if(list!=null&&list.size()>0){
				List<Long> docids=new ArrayList<Long>();
				for (IrpSubscribe irpSubscribe : list) {
					docids.add(irpSubscribe.getSubscribeobjid());
				}
				chnlDocLinkExample.createCriteria().andDocidIn(docids);
				chnlDocLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}

	@Override
	public int count(HashMap<String, Object> map) {
		int count=0;
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			example.setOrderByClause(map.get("sOrderByClause").toString());
			count = irpSubscribeDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<IrpSubscribe> selectSubscribeByPage(
			HashMap<String, Object> map, PageUtil pageUtil) {
		List<IrpSubscribe> list = null;
		IrpSubscribeExample example = new IrpSubscribeExample();
		
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			example.setOrderByClause(map.get("sOrderByClause").toString());
			list = irpSubscribeDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateStatus(IrpBaseObj baseObj, Long status) {
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andSubscribeobjidEqualTo(baseObj.getId()).andSubscribeobjtypeEqualTo(baseObj.getTableName()).andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			 List<IrpSubscribe> list=irpSubscribeDAO.selectByExample(example, null);
			 if(list!=null&&list.size()>0){
				 IrpSubscribe irpSubscribe=list.get(0);
				 irpSubscribe.setSubReadStatus(status);
				 irpSubscribeDAO.updateByPrimaryKey(irpSubscribe);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<IrpSubscribe> findSubscribeByBaseObjLogin(
			IrpBaseObj baseObj) {
		List<IrpSubscribe> list= new ArrayList<IrpSubscribe>();
		IrpSubscribeExample example = new IrpSubscribeExample();
		example.createCriteria().andSubscribeobjidEqualTo(baseObj.getId()).andSubscribeobjtypeEqualTo(baseObj.getTableName()).andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			list=irpSubscribeDAO.selectByExample(example, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateByPrimary(IrpSubscribe subscribe) {
		 try {
			irpSubscribeDAO.updateByPrimaryKey(subscribe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
