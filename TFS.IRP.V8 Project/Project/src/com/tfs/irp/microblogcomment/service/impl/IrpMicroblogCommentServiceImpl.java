package com.tfs.irp.microblogcomment.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.If;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.entity.IrpMessageContentExample;
import com.tfs.irp.microblogcomment.dao.IrpMicroblogCommentDAO;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentExample;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentExample.Criteria;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentView;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpMicroblogCommentServiceImpl implements
		IrpMicroblogCommentService {

	private IrpMicroblogCommentDAO irpMicroblogCommentDAO;

	public IrpMicroblogCommentDAO getIrpMicroblogCommentDAO() {
		return irpMicroblogCommentDAO;
	}
	public void setIrpMicroblogCommentDAO(
			IrpMicroblogCommentDAO irpMicroblogCommentDAO) {
		this.irpMicroblogCommentDAO = irpMicroblogCommentDAO;
	}
	private IrpConfigDAO irpConfigDAO;
	
	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}
	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}
	@Override
	public int addMicroBlogComment(long  _replyuserid,long _microblogid,String _content,long _replycommentid) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMicroblogComment irpMicroblogComment = new IrpMicroblogComment();
		irpMicroblogComment.setCommentid(TableIdUtil.getNextId(IrpMicroblogComment.TABLE_NAME));
		irpMicroblogComment.setUserid(LoginUtil.getLoginUserId());
		irpMicroblogComment.setReplyuserid(_replyuserid);
		irpMicroblogComment.setMicroblogid(_microblogid);
		irpMicroblogComment.setReplycommentid(_replycommentid);
		irpMicroblogComment.setContent(_content);
		irpMicroblogComment.setCrtime(new Date());
		if(_replyuserid==LoginUtil.getLoginUserId()){
			irpMicroblogComment.setBrowsestatus(1);
		}else{
			irpMicroblogComment.setBrowsestatus(0);
		}
		try {
			this.irpMicroblogCommentDAO.insertSelective(irpMicroblogComment);
			nStatus =1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nStatus =0;
		}
		return nStatus;
	}
	@Override
	public int addBlogComment(long  _replyuserid,long _microblogid,String _content,long _replycommentid,long commentuserid) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		IrpMicroblogComment irpMicroblogComment = new IrpMicroblogComment();
		irpMicroblogComment.setCommentid(TableIdUtil.getNextId(IrpMicroblogComment.TABLE_NAME));
		irpMicroblogComment.setUserid(commentuserid);
		irpMicroblogComment.setReplyuserid(_replyuserid);
		irpMicroblogComment.setMicroblogid(_microblogid);
		irpMicroblogComment.setReplycommentid(_replycommentid);
		irpMicroblogComment.setContent(_content);
		irpMicroblogComment.setCrtime(new Date());
		if(_replyuserid==commentuserid){
			irpMicroblogComment.setBrowsestatus(1);
		}else{
			irpMicroblogComment.setBrowsestatus(0);
		}
		try {
			this.irpMicroblogCommentDAO.insertSelective(irpMicroblogComment);
			nStatus =1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nStatus =0;
		}
		return nStatus;
	}
	@Override
	public List<String> findMicroBlogComment(Long _microblogid,Integer _isdel,PageUtil pageUtil) {
		List<String> list = null;
        try {
		list = this.irpMicroblogCommentDAO.selectMicroBlogCommentlist(_microblogid,_isdel,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int findMicroBlogCommentCount(Long _microblogid, Integer _isdel) {
		// TODO Auto-generated method stub
		int size = 0;
		IrpMicroblogCommentExample example = new  IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
	    criteria.andMicroblogidEqualTo(_microblogid);
		criteria.andIsdelEqualTo(_isdel);
		try {
		   size = this.irpMicroblogCommentDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	@Override
	public int updateMicroBlogCommentIsDel(Long _commentid){
		int _nstatus = 0;
		IrpMicroblogComment irpMicroblogComment = new IrpMicroblogComment();
		irpMicroblogComment.setCommentid(_commentid);
		irpMicroblogComment.setIsdel(irpMicroblogComment.ISDELFALSE);
		try {
	   _nstatus=this.irpMicroblogCommentDAO.updateByPrimaryKeySelective(irpMicroblogComment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return _nstatus;
	}
	@Override
	public int addMicroBlogReplyCommentReply(Long _replyCommentid,
			String _content) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpMicroblogComment   irpMicroblogComment = new IrpMicroblogComment(); 
		irpMicroblogComment.setCommentid(TableIdUtil.getNextId(IrpMicroblogComment.TABLE_NAME));
		irpMicroblogComment.setUserid(LoginUtil.getLoginUserId());
		irpMicroblogComment.setReplyuserid(IrpMicroblogComment.REPLYUSERID);
		irpMicroblogComment.setMicroblogid(IrpMicroblogComment.MICROBLOGID);
		irpMicroblogComment.setReplycommentid(_replyCommentid);
		irpMicroblogComment.setContent(_content);
		irpMicroblogComment.setCrtime(new Date());
		irpMicroblogComment.setIsdel(IrpMicroblogComment.ISDELTRUE);;
		try {
			this.irpMicroblogCommentDAO.insertSelective(irpMicroblogComment);
			_status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			_status = 0;
		}
		return _status;
	}
	@Override
	public List<IrpMicroblogComment> findMicroblogCommentOfUserid(long _userid,
			Integer _isdel,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogComment> irpMicroblogComment = null;
		IrpMicroblogCommentExample  example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andReplyuseridEqualTo(_userid);
        criteria.andUseridNotEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
		irpMicroblogComment  =	this.irpMicroblogCommentDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblogComment;
	}
	public int findMicroblogCommentOfUseridCount(long _userid,Integer _isdel){
		int _size = 0;
		IrpMicroblogCommentExample  example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andReplyuseridEqualTo(_userid);
        criteria.andUseridNotEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
			_size=this.irpMicroblogCommentDAO.selectByExample(example).size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _size;
	}
	@Override
	public List<IrpMicroblogComment> findMicroblogCommentOfSendUserid(
			long _userid, Integer _isdel,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogComment> irpMicroblogComment = null;
		IrpMicroblogCommentExample  example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);

		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
		irpMicroblogComment  =	this.irpMicroblogCommentDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblogComment;
	}
	public int findMicroblogCommentOfSendUseridCount(long _userid,Integer _isdel){
		int _size = 0;		
		IrpMicroblogCommentExample  example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
			_size= this.irpMicroblogCommentDAO.selectByExample(example).size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return _size;
	}
	
	
	@Override
	public int updateMicroblogCommentMicroblog(Long _microblogid) {
		// TODO Auto-generated method stub
		int _nstatus = 0;
		IrpMicroblogCommentExample example = new IrpMicroblogCommentExample();
		example.createCriteria().andMicroblogidEqualTo(_microblogid);
		IrpMicroblogComment irpMicroblogComment = new IrpMicroblogComment();
		irpMicroblogComment.setIsdel(irpMicroblogComment.ISDELFALSE);		
		 try {
			_nstatus=this.irpMicroblogCommentDAO.updateByExampleSelective(irpMicroblogComment, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _nstatus;
	}
	@Override
	public List findMicrCommentByUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
	   List commentlist = null;
	   IrpMicroblogCommentExample  example = new IrpMicroblogCommentExample();  
	   Criteria criteria = example.createCriteria();
	   criteria.andUseridEqualTo(_userid);
	   criteria.andIsdelEqualTo(_isdel);
	   example.setOrderByClause("commentid desc");
			try {
				 commentlist=	this.irpMicroblogCommentDAO.selectByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
		
		
		return commentlist;
	}
	@Override
	public IrpMicroblogComment irpMicroblogComment(long _commentid) {
		// TODO Auto-generated method stub
		IrpMicroblogComment irpMicroblogComment = null;
		try {
	  irpMicroblogComment =	this.irpMicroblogCommentDAO.selectByPrimaryKey(_commentid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblogComment;
	}
	@Override
	public String findMicroblogCommentNumberWord(String _cKey) {
		// TODO Auto-generated method stub
		String cValue = "";
		try {
			cValue = this.irpConfigDAO.selectCValueByCKey(_cKey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cValue;
	}
	@Override
	public IrpMicroblogComment findNewWestCommentByUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		IrpMicroblogComment irpMicroblogComment = null;
		IrpMicroblogCommentExample example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
			if(irpMicroblogCommentDAO.selectByExample(example).size()>0){
				irpMicroblogComment =this.irpMicroblogCommentDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblogComment;
	}
	@Override
	public IrpMicroblogComment findSdNewWestCommentByUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		IrpMicroblogComment irpMicroblogComment = null;
		IrpMicroblogCommentExample example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andReplyuseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
			if(irpMicroblogCommentDAO.selectByExample(example).size()>0){
				
				irpMicroblogComment =this.irpMicroblogCommentDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblogComment;
	}
	@Override
	public int getCountCommentByMicroblog(long _microblogid, Integer _isdel) {
		// TODO Auto-generated method stub
		int countcomment = 0;
		IrpMicroblogCommentExample example = new IrpMicroblogCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsdelEqualTo(_isdel);
		criteria.andMicroblogidEqualTo(_microblogid);
		try {
			countcomment =	this.irpMicroblogCommentDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return countcomment;
	}
	@Override
	public List<String> selectMicroBlogCommentlist(Long _microBlogid,
			Integer _isdel, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<String> list = null;
		
		try {
			list = this.irpMicroblogCommentDAO.selectMicroBlogCommentlist(_microBlogid, _isdel, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public List<IrpMicroblogComment> selectCommentlistByWbid(Long _microBlogid,
			Integer _isdel, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogComment> list = null;
		
		try {
			list = this.irpMicroblogCommentDAO.selectCommentlistByWbid(_microBlogid, _isdel, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public Long selectByMicroBlogComment(Long _microblogid) {
		// TODO Auto-generated method stub
		Long status = null;
		try {
			status = this.irpMicroblogCommentDAO.selectByMicroBlogComment(_microblogid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public List<IrpMicroblogComment> selectByExample(
			IrpMicroblogCommentExample example, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogComment> list = null;
		
		try {
			list =this.irpMicroblogCommentDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int selectUnReadComment(long _replyuserid, int _browsestatus, int _isdel) {
		int _readStatus = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("replyuserid", _replyuserid);
		map.put("browsestatus", _browsestatus);
		map.put("isdel", _isdel);
		try {
			_readStatus = this.irpMicroblogCommentDAO.selectUnReadComment(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int chanageCommentStatusByMsgId(long _replyuserid,Long _chatMsid) {
		int _readStatus = 0;
		try {
			IrpMicroblogComment irpMicroblogComment = irpMicroblogCommentDAO.selectByPrimaryKey(_chatMsid);
			IrpMicroblogCommentExample example = new IrpMicroblogCommentExample();
			Criteria criteria = example.createCriteria();
			criteria.andReplyuseridEqualTo(_replyuserid);	
			if(irpMicroblogComment.getBrowsestatus()==0){
				irpMicroblogComment.setBrowsestatus(1);
				_readStatus = irpMicroblogCommentDAO.updateByPrimaryKey(irpMicroblogComment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _readStatus;
	}

}
