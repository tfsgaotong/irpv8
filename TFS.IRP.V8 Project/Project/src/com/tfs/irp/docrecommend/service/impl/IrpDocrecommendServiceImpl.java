package com.tfs.irp.docrecommend.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.docrecommend.dao.IrpDocrecommendDAO;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docrecommend.entity.IrpDocrecommendExample;
import com.tfs.irp.docrecommend.entity.IrpDocrecommendExample.Criteria;
import com.tfs.irp.docrecommend.service.IrpDocrecommendService;
import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpDocrecommendServiceImpl implements IrpDocrecommendService {
	private IrpDocrecommendDAO irpDocrecommendDAO;
	private IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO;
	private IrpDocumentDAO irpDocumentDAO; 
	private IrpUserService irpUserService;
	@Override
	 public int countRecommend(Long _docid, Long _replyUserid) {
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				IrpDocrecommendExample example=new IrpDocrecommendExample();
				int recomms=0;
				Criteria criteria = example.createCriteria();
				criteria.andDocidEqualTo(_docid).andIsdelEqualTo(IrpDocrecommend.ISDEL_PRO)
												.andCruseridEqualTo(LoginUtil.getLoginUser().getUserid());
				if(_replyUserid!=null && _replyUserid!=0L){
					criteria.andReplayuseridEqualTo(_replyUserid);
				}else{
					// criteria.andReplayuseridEqualTo(0L);
				} 
				try {
					recomms=irpDocrecommendDAO.countByExample(example);
				} catch (SQLException e) { 
					e.printStackTrace();
				}
				return recomms;  
	};
	@Override
	 public int countRecommend_app(Long _docid, Long _replyUserid,String token) {
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				IrpDocrecommendExample example=new IrpDocrecommendExample();
				int recomms=0;
				Criteria criteria = example.createCriteria();
				criteria.andDocidEqualTo(_docid).andIsdelEqualTo(IrpDocrecommend.ISDEL_PRO)
												.andCruseridEqualTo(mobileAction.getlogin(token).getUserid());
				if(_replyUserid!=null && _replyUserid!=0L){
					criteria.andReplayuseridEqualTo(_replyUserid);
				}else{
					// criteria.andReplayuseridEqualTo(0L);
				} 
				try {
					recomms=irpDocrecommendDAO.countByExample(example);
				} catch (SQLException e) { 
					e.printStackTrace();
				}
				return recomms;  
	};
	@Override
	public int deleteDocReommend(Long _docid,Long _recommendid) {
		// TODO Auto-generated method stub
		int nCount=0;
		/**
		 * 来一个查询，判断，他的replayuserid如果大于0.则不减去文档表的评论次数
		 */
		
		
		try {
			IrpDocrecommend irpDocrecommend=irpDocrecommendDAO.selectByPrimaryKey(_recommendid);
			if(irpDocrecommend!=null ){
				if(irpDocrecommend.getReplayuserid()>0L){ 
				}else{
					//修改文档表的评论次数
					IrpDocumentExample documentExample=new IrpDocumentExample();
					documentExample.createCriteria().andDocidEqualTo(_docid);
					List<IrpDocument> docs=irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
					if(docs!=null && docs.size()>0){ 
						IrpDocument document=docs.get(0);
						document.setRecommendcounts(document.getRecommendcounts()-1);
						irpDocumentDAO.updateByPrimaryKey(document);
					}
					//修改中间表的评论次数 
					IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
					chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
					List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
					if(docLinks!=null && docLinks.size()>0){
						IrpChnlDocLink chnlDocLink=docLinks.get(0);
						chnlDocLink.setCommentcount(chnlDocLink.getCommentcount()-1);
						irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
					}
				} 
			}
			/**
			 * 查询他的字回复，也一起删除
			 */
			IrpDocrecommendExample example=new IrpDocrecommendExample();
			example.createCriteria().andReplayuseridEqualTo(_recommendid);
			irpDocrecommendDAO.deleteByExample(example); 
			nCount=irpDocrecommendDAO.deleteByPrimaryKey(_recommendid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<IrpDocrecommend> findDocReommend(Long _docid) {
		// TODO Auto-generated method stub
		IrpDocrecommendExample example=new IrpDocrecommendExample();
		List<IrpDocrecommend> recomms=null;
		example.createCriteria().andDocidEqualTo(_docid);
		//按照日期倒序
		example.setOrderByClause(" crtime desc ");
		try {
			recomms=irpDocrecommendDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recomms;
	}
	@Override
	public IrpDocrecommend findRecommendByPrimaryKey(Long recommendid) {
		// TODO Auto-generated method stub
		IrpDocrecommend docrecommend=null;
		try {
			docrecommend=irpDocrecommendDAO.selectByPrimaryKey(recommendid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docrecommend;
	}
	@Override
	public List<IrpDocrecommend> findDocReommend(Long _docid,Long _replyUserid,Integer isdel ,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpDocrecommend> recomms=null;
		try {
			IrpDocrecommendExample example=new IrpDocrecommendExample(); 
			Criteria criteria = example.createCriteria();
			criteria.andDocidEqualTo(_docid).andIsdelEqualTo(isdel);
			if(_replyUserid!=null && _replyUserid!=0L){
				criteria.andReplayuseridEqualTo(_replyUserid);
			}else{
				// criteria.andReplayuseridEqualTo(0L);
			}
		   	example.setOrderByClause(" crtime desc ");	//按照日期倒序
			recomms=irpDocrecommendDAO.selectByExample(pageUtil,example);
			/**
			 * 查询他的子回复
			 */
			//拿到他的parentid，如果他的parentid不等于0，就说明，他是某个动态的回复
			if(recomms!=null && recomms.size()>0){
				for (int i = 0; i < recomms.size(); i++) { 
					IrpUser user = irpUserService.findUserByUserId(recomms.get(i).getCruserid());
					recomms.get(i).setUserPicUrl(user.getDefaultUserPic());
					if((recomms.get(i).getReplayuserid()!=null && Long.valueOf(recomms.get(i).getReplayuserid())!=Long.valueOf(0L))){
						IrpDocrecommend docrecommend=findRecommendByPrimaryKey(recomms.get(i).getReplayuserid());
						recomms.get(i).setParentDocRecommend(docrecommend);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recomms;
	}
	/**
	 * 查询字回复对象集合
	 */
	public List<IrpDocrecommend> findReplyRecommend(Long _recommendid){ 
		IrpDocrecommendExample docrecommendExample=new IrpDocrecommendExample();
		/**
		 * 查询replayuserid=主键的回复对象就是他的字回复对象
		 */
	
		List<IrpDocrecommend> replaycommendsList=null;
		try {
			docrecommendExample.createCriteria().andReplayuseridEqualTo(_recommendid)
												.andIsdelEqualTo(IrpDocrecommend.ISDEL_PRO);
			docrecommendExample.setOrderByClause(" crtime desc ");
			replaycommendsList = irpDocrecommendDAO.selectByExample(docrecommendExample);
			/**
			 * 初始化用户的头像
			 */
			if(replaycommendsList!=null && replaycommendsList.size()>0){
				for (int i = 0; i < replaycommendsList.size(); i++) {
					IrpUser user = irpUserService.findUserByUserId(replaycommendsList.get(i).getCruserid());
					replaycommendsList.get(i).setUserPicUrl(user.getDefaultUserPic());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return replaycommendsList;
	}
	/**
	 * _replyUserid存放上一个回复的主键
	 */
	 @Override
	public int addDocRrecommend(Long _docid, Long _replyUserid,String recommend) {
		int nCount=0;
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			
			IrpDocrecommend record=new IrpDocrecommend();
			record.setRecommendid(TableIdUtil.getNextId(IrpDocrecommend.TABLE_NAME)); 
			record.setCruser(irpUser.getUsername());
			record.setCruserid(irpUser.getUserid());
			record.setCrtime(new Date());
			record.setDocid(_docid);
			record.setIsdel(1); 
			record.setReplayuserid(_replyUserid);
			record.setRecommend(recommend);
			irpDocrecommendDAO.insertSelective(record);
			if(_replyUserid==null || _replyUserid==0L){
				//将文档表评论数量 加1 
				IrpDocumentExample documentExample=new IrpDocumentExample();
				documentExample.createCriteria().andDocidEqualTo(_docid);
				List<IrpDocument> docs=irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
				if(docs!=null && docs.size()>0){ 
					IrpDocument document=docs.get(0);
					document.setRecommendcounts(document.getRecommendcounts()+1);
					irpDocumentDAO.updateByPrimaryKey(document);
				}
				// 文档栏目中间表的评论数量 加1 
				IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
				chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
				List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
				if(docLinks!=null && docLinks.size()>0){
					IrpChnlDocLink chnlDocLink=docLinks.get(0);
					chnlDocLink.setCommentcount(chnlDocLink.getCommentcount()+1);
					irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
				}
			} 
			nCount=1;
		} catch (SQLException e) { 
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	 
	 /**
		 * _replyUserid存放上一个回复的主键
		 */
		 @Override
		public int addDocRrecommend_app(Long _docid, Long _replyUserid,String recommend,String token) {
			int nCount=0;
			try {
				IrpUser irpUser=mobileAction.getlogin(token);
				//用户id为1(admin用户)的默认设置成系统管理员
				if(irpUser.getUserid()==1L){
					irpUser.setAdministrator(true);
				}
				IrpDocrecommend record=new IrpDocrecommend();
				record.setRecommendid(TableIdUtil.getNextId(IrpDocrecommend.TABLE_NAME)); 
				record.setCruser(irpUser.getUsername());
				record.setCruserid(irpUser.getUserid());
				record.setCrtime(new Date());
				record.setDocid(_docid);
				record.setIsdel(1); 
				record.setReplayuserid(_replyUserid);
				record.setRecommend(recommend);
				irpDocrecommendDAO.insertSelective(record);
				if(_replyUserid==null || _replyUserid==0L){
					//将文档表评论数量 加1 
					IrpDocumentExample documentExample=new IrpDocumentExample();
					documentExample.createCriteria().andDocidEqualTo(_docid);
					List<IrpDocument> docs=irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
					if(docs!=null && docs.size()>0){ 
						IrpDocument document=docs.get(0);
						document.setRecommendcounts(document.getRecommendcounts()+1);
						irpDocumentDAO.updateByPrimaryKey(document);
					}
					// 文档栏目中间表的评论数量 加1 
					IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
					chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
					List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
					if(docLinks!=null && docLinks.size()>0){
						IrpChnlDocLink chnlDocLink=docLinks.get(0);
						chnlDocLink.setCommentcount(chnlDocLink.getCommentcount()+1);
						irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
					}
				} 
				nCount=1;
			} catch (SQLException e) { 
				nCount=0;
				e.printStackTrace();
			}
			return nCount;
		}
	
	 @Override
	public int selectCountByExpert(Long userId, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		 int nCount=0;
		 try {
			IrpDocrecommendExample example=new IrpDocrecommendExample();
			
			Criteria criteria = example.createCriteria();
			criteria.andCruseridEqualTo(userId);
			criteria.andCrtimeBetween(startTime, endTime);
			irpDocrecommendDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	public IrpDocrecommendDAO getIrpDocrecommendDAO() {
		return irpDocrecommendDAO;
	}

	public void setIrpDocrecommendDAO(IrpDocrecommendDAO irpDocrecommendDAO) {
		this.irpDocrecommendDAO = irpDocrecommendDAO;
	}
	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}
	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}
	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	@Override
	public long getAllCount() {
		 long nCount=0;
		 try {
			IrpDocrecommendExample example=new IrpDocrecommendExample();
			nCount=irpDocrecommendDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	
	
	
}
