package com.tfs.irp.microblog.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.microblog.dao.IrpMicroblogDAO;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogExample;
import com.tfs.irp.microblog.entity.IrpMicroblogExample.Criteria;
import com.tfs.irp.microblog.entity.IrpMicroblogView;
import com.tfs.irp.microblog.entity.IrpUserInfoView;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.entity.MicroblogForSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.topiclink.dao.IrpTopicLinkDAO;
import com.tfs.irp.util.AtmeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.TopicUtil;

public class IrpMicroblogServiceImpl implements IrpMicroblogService {

	private IrpMicroblogDAO irpMicroblogDAO;
	
	private IrpTopicLinkDAO irpTopicLinkDAO;
	
	private SolrService solrService;
	
	public SolrService getSolrService() {
		return solrService;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}
	public IrpTopicLinkDAO getIrpTopicLinkDAO() {
		return irpTopicLinkDAO;
	}
	public void setIrpTopicLinkDAO(IrpTopicLinkDAO irpTopicLinkDAO) {
		this.irpTopicLinkDAO = irpTopicLinkDAO;
	}
	public IrpMicroblogDAO getIrpMicroblogDAO() {
		return irpMicroblogDAO;
	}
	public void setIrpMicroblogDAO(IrpMicroblogDAO irpMicroblogDAO) {
		this.irpMicroblogDAO = irpMicroblogDAO;
	}
	private IrpConfigDAO irpConfigDAO;
	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}
	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}
	
	@Override
	public int addMicroBlog(String _sContent,int _nMicrotype,IrpMicroblog _irpMicroblog,String _contentimg) {
		// TODO Auto-generated method stub
		int nStatus = 0;
		TopicUtil topicUtil = new TopicUtil();
		
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		Long microblogid = TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME);
		irpMicroblog.setMicroblogid(microblogid);
		String content = topicUtil.getTopicStr(_sContent, topicUtil.getSameTopicId(_sContent),microblogid);
		irpMicroblog.setMicroblogcontent(content);
		
		irpMicroblog.setMicroblogcontentimg(_contentimg);
		
	    irpMicroblog.setUserid(LoginUtil.getLoginUserId());
	    if(_irpMicroblog.getGroupid()!=null){
	    	irpMicroblog.setGroupid(_irpMicroblog.getGroupid());	
	    }
	    Date date = Calendar.getInstance().getTime();
		irpMicroblog.setCrtime(date); 
		irpMicroblog.setBlogtype(_nMicrotype);
		irpMicroblog.setIsdel(IrpMicroblog.ISDELFALSE);
		irpMicroblog.setFromdata(IrpMicroblog.FROMDATA); 
		
	    try {
			this.irpMicroblogDAO.insertSelective(irpMicroblog);
			nStatus = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nStatus = 0;
		}
	    if(nStatus==1){
	    	//增加索引
		    	MicroblogForSolr mfs = new MicroblogForSolr();
		    	mfs.setMICROBLOGID(microblogid);
		    	mfs.setMICROBLOGCONTENT(content);
		    	mfs.setFROMDATA(IrpMicroblog.FROMDATA);
		    	mfs.setCRTIME(date);
		    	mfs.setUSERID(LoginUtil.getLoginUserId());
		    	this.solrService.addMicroblogIndex(mfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));				
	    }
		return nStatus;
	}
	@Override
	public int addMicroblogByMobile(String _sContent,int _nMicrotype,IrpMicroblog _irpMicroblog){
		// TODO Auto-generated method stub
		int nStatus = 0;
		TopicUtil topicUtil = new TopicUtil();
		
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		Long microblogid = TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME);
		irpMicroblog.setMicroblogid(microblogid);
		String content = topicUtil.getTopicStr(_sContent, topicUtil.getSameTopicId(_sContent),microblogid);
		irpMicroblog.setMicroblogcontent(content);
		if(_irpMicroblog.getUserid()!=null){
			irpMicroblog.setUserid(_irpMicroblog.getUserid());
		}else{
			irpMicroblog.setUserid(LoginUtil.getLoginUserId());
		}
	    if(_irpMicroblog.getGroupid()!=null){
	    	irpMicroblog.setGroupid(_irpMicroblog.getGroupid());	
	    }
	    Date date = Calendar.getInstance().getTime();
		irpMicroblog.setCrtime(date); 
		irpMicroblog.setBlogtype(_nMicrotype);
		irpMicroblog.setIsdel(IrpMicroblog.ISDELFALSE);
		irpMicroblog.setFromdata(IrpMicroblog.FROMMOBILE); 
		irpMicroblog.setMicroblogcontentimg(_irpMicroblog.getMicroblogcontentimg());
	    try {
			this.irpMicroblogDAO.insertSelective(irpMicroblog);
			nStatus = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nStatus = 0;
		}
	    if(nStatus==1){
	    	//增加索引
		    	MicroblogForSolr mfs = new MicroblogForSolr();
		    	mfs.setMICROBLOGID(microblogid);
		    	mfs.setMICROBLOGCONTENT(content);
		    	mfs.setFROMDATA(IrpMicroblog.FROMDATA);
		    	mfs.setCRTIME(date);
		    	mfs.setUSERID(LoginUtil.getLoginUserId());
		    	this.solrService.addMicroblogIndex(mfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));				
	    }
		return nStatus;
	}
	@Override
	public int deleteMicroblogOfMicroblogid(long _microblogid) {
		//0：未删除状态   1：已删除状态
		int _nStatus = 0;
		IrpMicroblogExample example = new IrpMicroblogExample(); 
		Criteria criteria = example.createCriteria();
		criteria.andMicroblogidEqualTo(_microblogid);
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		irpMicroblog.setIsdel(IrpMicroblog.ISDELTRUE);
		try {
			_nStatus =this.irpMicroblogDAO.updateByExampleSelective(irpMicroblog, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_nStatus==1) {
			//同时删除索引
			this.solrService.deleteSolrIndex(_microblogid,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
		}
		return _nStatus;
	}
	@Override
	public int updateMicroblogCommentCount(long _microblogid,long _commentcount) {
		// TODO Auto-generated method stub
		int _nStatus = 0;
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		irpMicroblog.setMicroblogid(_microblogid);
		irpMicroblog.setCommentcount(_commentcount);
		try {
			_nStatus =	this.irpMicroblogDAO.updateByPrimaryKeySelective(irpMicroblog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _nStatus;
	}
	@Override
	public IrpMicroblog irpMicroblog(Long _microblogid) {
		// TODO Auto-generated method stub
		IrpMicroblog irpMicroblog = null;
		try {
			irpMicroblog = this.irpMicroblogDAO.selectByPrimaryKey(_microblogid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblog;
	}
	@Override
	public int addMicroBlogTranSpond(IrpMicroblog _irpMicroblog) {
		// TODO Auto-generated method stub
		int _nStatus = 0;
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		Long microblogid = TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME);
		irpMicroblog.setMicroblogid(microblogid);
		irpMicroblog.setUserid(LoginUtil.getLoginUserId());
		String content = new AtmeUtil().getAtmeStr(_irpMicroblog.getMicroblogcontent());
		irpMicroblog.setMicroblogcontent(content);
		Date date = Calendar.getInstance().getTime();
		irpMicroblog.setCrtime(date);
		irpMicroblog.setTranspondid(_irpMicroblog.getTranspondid());
		irpMicroblog.setFromdata(IrpMicroblog.FROMDATA);
		try {
			this.irpMicroblogDAO.insertSelective(irpMicroblog);
			_nStatus = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			_nStatus = 0;
		}
		if(_nStatus==1){
			MicroblogForSolr mfs = new MicroblogForSolr();
			mfs.setMICROBLOGID(microblogid);
			mfs.setMICROBLOGCONTENT(content);
			mfs.setCRTIME(date);
			mfs.setFROMDATA(IrpMicroblog.FROMDATA);
			mfs.setUSERID(LoginUtil.getLoginUserId());
			this.solrService.addMicroblogIndex(mfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
		}
		return _nStatus;
	}
	@Override
	public int addMicroBlogTranSpondMobile(IrpMicroblog _irpMicroblog) {
		// TODO Auto-generated method stub
		int _nStatus = 0;
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		Long microblogid = TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME);
		irpMicroblog.setMicroblogid(microblogid);
		irpMicroblog.setUserid(_irpMicroblog.getUserid());
		String content = new AtmeUtil().getAtmeStr(_irpMicroblog.getMicroblogcontent());
		irpMicroblog.setMicroblogcontent(content);
		Date date = Calendar.getInstance().getTime();
		irpMicroblog.setCrtime(date);
		irpMicroblog.setTranspondid(_irpMicroblog.getTranspondid());
		irpMicroblog.setFromdata(IrpMicroblog.FROMMOBILE);
		try {
			this.irpMicroblogDAO.insertSelective(irpMicroblog);
			_nStatus = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			_nStatus = 0;
		}
		if(_nStatus==1){
			MicroblogForSolr mfs = new MicroblogForSolr();
			mfs.setMICROBLOGID(microblogid);
			mfs.setMICROBLOGCONTENT(content);
			mfs.setCRTIME(date);
			mfs.setFROMDATA(IrpMicroblog.FROMDATA);
			mfs.setUSERID(LoginUtil.getLoginUserId());
			this.solrService.addMicroblogIndex(mfs, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
		}
		return _nStatus;
	}
	@Override
	public int updateMicroblogTranspondByMicroblogid(long _microblogid) {
		// TODO Auto-generated method stub
		int _nStatus = 0;
		IrpMicroblog irpMicroblog = new IrpMicroblog();
		irpMicroblog.setMicroblogid(_microblogid);
		irpMicroblog.setTranspondcount(this.irpMicroblog(_microblogid).getTranspondcount()+1);
		try {
		 _nStatus =	this.irpMicroblogDAO.updateByPrimaryKeySelective(irpMicroblog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _nStatus;
	}
	@Override
	public IrpMicroblog findFirstMicroblog(long _userid) {
		// TODO Auto-generated method stub
		IrpMicroblog irpMicroblog = null; 
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andBlogtypeEqualTo(IrpMicroblogFocus.OPENBLOGTYPE);
		example.setOrderByClause("crtime desc");
		try {
			List<IrpMicroblog> list = this.irpMicroblogDAO.selectByExampleWithBLOBs(example);
			if(list.size()>0){
				irpMicroblog= list.get(0);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpMicroblog;
	}
	@Override
	public int coutnMicroblogOfUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		int _microblogcount = 0;
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		try {
			_microblogcount =	this.irpMicroblogDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _microblogcount;
	}
	@Override
	public int coutnMicroblogOfUserid(long _userid, Integer _isdel,Integer _blogtype) {
		// TODO Auto-generated method stub
		int _microblogcount = 0;
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		criteria.andBlogtypeEqualTo(_blogtype);
		try {
			_microblogcount =	this.irpMicroblogDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _microblogcount;
	}
	@Override
	public List findMicroblogListByUserid(long _userid, Integer _isdel,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List microblogList = null;
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		example.setOrderByClause("crtime desc");
		try {
			microblogList =	this.irpMicroblogDAO.selectByExample(example,pageUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return microblogList;
	}
	@Override
	public List findMicroblogListByUserid(long _userid, Integer _isdel,PageUtil pageUtil,Integer _blogtype) {
		// TODO Auto-generated method stub
		List microblogList = null;
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		criteria.andBlogtypeEqualTo(_blogtype);
		example.setOrderByClause("crtime desc");
		try {
			microblogList =	this.irpMicroblogDAO.selectByExample(example,pageUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return microblogList;
	}
	@Override
	public List findLoginUserPersonalInfo(long _docstatus, long _isdel,
			long _userid) {
		// TODO Auto-generated method stub
		List userList = null;
		try {
		userList =	this.irpMicroblogDAO.findLoginUserInfo(_docstatus, _isdel, _userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}
	@Override
	public String findIrpConfigCvalue(String _cKey) {
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
	public List findMicroblogOfFocusCollect(long isdelid, PageUtil pageUtil,
			long _loginid) {
		// TODO Auto-generated method stub
		List collectlist = null;
		
		try {
			collectlist = this.irpMicroblogDAO.findMicroblogOfFocusCollect(isdelid,pageUtil,_loginid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return collectlist;
	}
	@Override
	public int findMicroblogOfFocusCollectCount(long isdelid,long _loginid) {
		// TODO Auto-generated method stub
		int collectcount = 0;
		try {
			collectcount = this.irpMicroblogDAO.findMicroblogOfFocusCollectCount(isdelid,_loginid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return collectcount;
	}
	@Override
	public List findDefaultRecommend(long _userid){
		// TODO Auto-generated method stub
		List RecommentidList = null;
		try {
			RecommentidList = this.irpMicroblogDAO.findDefaultRecommend(_userid);
			if(RecommentidList.size()<=0){
				RecommentidList = this.irpMicroblogDAO.findDefaultRecommendMicrNum(_userid);
				if(RecommentidList.size()<=0){
					RecommentidList = this.irpMicroblogDAO.findDefaultRecommendUserCrtime(_userid);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RecommentidList;
	}
	@Override
	public Integer searchMicrCountOfUserid(long _userid) {
		// TODO Auto-generated method stub
		int micrcount = 0;
		try {
			micrcount =	this.irpMicroblogDAO.searchMicrCountOfUserid(_userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return micrcount;	
		
		
	}
	@Override
	public Integer searchFocusCountOfUserid(long _userid) {
		// TODO Auto-generated method stub
		int focuscount = 0;
		try {
			focuscount=this.irpMicroblogDAO.searchFocusCountOfUserid(_userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return focuscount;
	}
	@Override
	public Integer searchFusCountOfUserid(long _userid) {
		// TODO Auto-generated method stub
		int fuscount = 0;
		try {
			fuscount=this.irpMicroblogDAO.searchFusCountOfUserid(_userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fuscount;
	}
	@Override
	public List getAllMicroblobByUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		List MicrList = null;
		IrpMicroblogExample example = new IrpMicroblogExample(); 
		Criteria criteria =example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andIsdelEqualTo(_isdel);
		try {
			MicrList = this.irpMicroblogDAO.selectByExampleWithBLOBs(example);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MicrList;
	}
	@Override
	public boolean microblogBelongUserid(long _microblog) {
		// TODO Auto-generated method stub
		
		//判断此微知 是否属于自己
		List micrList = this.getAllMicroblobByUserid(LoginUtil.getLoginUserId(),IrpMicroblog.ISDELFALSE);
		
		if(LoginUtil.getLoginUser().isMicroblogManager()){
		   	
			return true;
		}
		Iterator iteriator = micrList.iterator();
		while (iteriator.hasNext()) {
			IrpMicroblog microblog = (IrpMicroblog) iteriator.next();
		   if(_microblog == microblog.getMicroblogid()){
			return true;
		  }
		}
		return false;
	}
	@Override
	public List<IrpMicroblog> getIrpMicroblogOfTopicValue(String _topicvalue,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		
		List<IrpMicroblog> list = null;
		
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		
		List topiclist =  this.irpTopicLinkDAO.selectTopicNumByName(_topicvalue);
		  if(topiclist.size()>0){
		List topiclistArray = new ArrayList();
		for (int i = 0; i < topiclist.size(); i++) {
			Map map = (Map)topiclist.get(i);
			
			topiclistArray.add(map.values().toString().replace("[","").replace("]",""));			
		}
		
		criteria.andMicroblogidIn(topiclistArray);
		criteria.andBlogtypeEqualTo(IrpMicroblog.PUBLICMICROBLOG);
		criteria.andIsdelEqualTo(IrpMicroblog.ISDELFALSE);
		example.setOrderByClause("crtime desc");
		try {
			list = 	this.irpMicroblogDAO.selectByExample(example,pageUtil);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }
		return list;
	}
	@Override
	public int getIrpMicroblogOfTopicValueCount(String _topicvalue) {
		// TODO Auto-generated method stub
		int size = 0;
		List<IrpMicroblog> list = null;
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		
		List topiclist =  this.irpTopicLinkDAO.selectTopicNumByName(_topicvalue);
	
        if(topiclist.size()>0){
		List topiclistArray = new ArrayList();
		for (int i = 0; i < topiclist.size(); i++) {
			Map map = (Map)topiclist.get(i);
			
			topiclistArray.add(map.values().toString().replace("[","").replace("]",""));			
		}
		
		criteria.andMicroblogidIn(topiclistArray);
		criteria.andBlogtypeEqualTo(IrpMicroblog.PUBLICMICROBLOG);
		criteria.andIsdelEqualTo(IrpMicroblog.ISDELFALSE);
		example.setOrderByClause("crtime desc");
		try {
			size = 	this.irpMicroblogDAO.countByExample(example);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        
		return size;
	}
	@Override
	public int changeMicroblogTypeByids(Long _microblogid,Integer _status) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpMicroblog record = new IrpMicroblog();
		record.setIsdel(_status);
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andMicroblogidEqualTo(_microblogid);
		try {
			status = this.irpMicroblogDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status == 1) {
			//同时删除索引
			if(_status == IrpMicroblog.ISDELFALSE){
				try {
					IrpMicroblog irpMicroblog = this.irpMicroblogDAO.selectByPrimaryKey(_microblogid);
					MicroblogForSolr mfs = new MicroblogForSolr();
					mfs.setMICROBLOGID(irpMicroblog.getMicroblogid());
					mfs.setMICROBLOGCONTENT(irpMicroblog.getMicroblogcontent());
					mfs.setCRTIME(irpMicroblog.getCrtime());
					mfs.setUSERID(irpMicroblog.getUserid());
					mfs.setFROMDATA(irpMicroblog.getFromdata());
					this.solrService.addMicroblogIndex(mfs,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(_status == IrpMicroblog.ISDELTRUE){
				this.solrService.deleteSolrIndex(_microblogid, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
			}else if(_status == IrpMicroblog.ISDELINFORM){
				this.solrService.deleteSolrIndex(_microblogid, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_MICROBLOG));
			}
		}
		return status;
	}
	@Override
	public List findMicroblogOfFocusCollect(PageUtil pageUtil, long _loginid) {
	List collectlist = null;
		
		try {
			collectlist = this.irpMicroblogDAO.findMicroblogOfFocusCollect(pageUtil,_loginid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return collectlist;
	}
	@Override
	public int findMicroblogOfFocusCollectCount(long _loginid) {
		// TODO Auto-generated method stub
		int collectcount = 0;
		try {
			collectcount = this.irpMicroblogDAO.findMicroblogOfFocusCollectCount(_loginid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return collectcount;
	}
	@Override
	public int getMicroNumByUseridAndDate(Long _userid, Integer _type,
			Date _starttime, Date _endStart) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andUseridEqualTo(_userid)
								.andIsdelEqualTo(_type)
								.andCrtimeBetween(_starttime, _endStart);
		try {
			num = this.irpMicroblogDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	@Override
	public int deleteMicroblogByIdTrue(Long _microblogid) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			status = this.irpMicroblogDAO.deleteByPrimaryKey(_microblogid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public int deleteMicroblogAllillegality(Integer _isdel) {
		// TODO Auto-generated method stub
		int msg = 0;
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(_isdel);
		try {
			msg = this.irpMicroblogDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public void deleteMicroblogContentPic(Long _questiongid) {
		// TODO Auto-generated method stub
		if(_questiongid!=null){
			try {
				IrpMicroblog irpMicroblog = this.irpMicroblogDAO.selectByPrimaryKey(_questiongid);
				if(irpMicroblog!=null){
					String findimgs =  "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
					Pattern pattern = Pattern.compile(findimgs);
					Matcher matcher = pattern.matcher(irpMicroblog.getMicroblogcontent());
					while (matcher.find()) {
						String images = matcher.group(1);
						int disposenum =	images.indexOf("=");
						String disposepicstr =  images.substring(disposenum+1);
						SysFileUtil.deleteFileByFileName(disposepicstr);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	@Override
	public List findMicroblogOfFocus(long isdelid, long fuserid,
			PageUtil pageUtil, long _loginid) {
		// TODO Auto-generated method stub
		List list = null;
		try {
			list = this.irpMicroblogDAO.findMicroblogOfFocus(isdelid, fuserid, pageUtil, _loginid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public List findMicroblogOfUseridInCard(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		List list = null;
		
		list = this.irpMicroblogDAO.findMicroblogOfUseridInCard(_userid, _isdel);
		
		return list;
	}
	@Override
	public List<IrpUserInfoView> findMicroblogOfUserid(long _userid, Integer _isdel) {
		// TODO Auto-generated method stub
		List<IrpUserInfoView> list = null;
		IrpUserInfoView irpUserInfo=new IrpUserInfoView();
		list = this.irpMicroblogDAO.findMicroblogOfUserid(_userid, _isdel);
		return list;
	}
	@Override
	public int findMicroblogOfFocusCount(long isdelid, long fuserid) {
		// TODO Auto-generated method stub
		int status = 0;
		
		try {
			status = this.irpMicroblogDAO.findMicroblogOfFocusCount(isdelid, fuserid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	@Override
	public int findMicroblogOfAllCount(long isdelid) {
		// TODO Auto-generated method stub
		int status = 0;
		
		try {
			status = this.irpMicroblogDAO.findMicroblogOfAllCount(isdelid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	@Override
	public List findMicroblogOfAll(long isdelid, PageUtil pageUtil,
			long _loginid) {
		// TODO Auto-generated method stub
		List list = null;
		try {
			list = this.irpMicroblogDAO.findMicroblogOfAll(isdelid, pageUtil, _loginid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List findMicroblogOfFocus(long isdelid, long fuserid,
			long _microblogid) {
		// TODO Auto-generated method stub
		List list = null;
		
		try {
			list = this.irpMicroblogDAO.findMicroblogOfFocus(isdelid, fuserid, _microblogid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public int findMicroblogOfInformCount(long isdelid, Integer informtype,
			Integer informstatus, String _infromkey) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			status = this.irpMicroblogDAO.findMicroblogOfInformCount(isdelid, informtype, informstatus, _infromkey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public List findMicroblogOfInform(long isdelid, Integer informtype,
			Integer informstatus, PageUtil pageUtil, String _infromkey) {
		// TODO Auto-generated method stub
		List list = null;
		try {
			list = this.irpMicroblogDAO.findMicroblogOfInform(isdelid, informtype, informstatus, pageUtil, _infromkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void deleteMicroblogContentPicAll() {
		// TODO Auto-generated method stub
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(IrpMicroblog.ISDELTRUE);
		try {
			List<IrpMicroblog> list = this.irpMicroblogDAO.selectByExampleWithBLOBs(example);
			if(list.size()>0){
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					IrpMicroblog irpMicroblog = (IrpMicroblog) iterator.next();
					if(irpMicroblog.getMicroblogcontent()!=null){
						String findimgs =  "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
						Pattern pattern = Pattern.compile(findimgs);
						Matcher matcher = pattern.matcher(irpMicroblog.getMicroblogcontent());
						while (matcher.find()) {
							String images = matcher.group(1);
							int disposenum =	images.indexOf("=");
							String disposepicstr =  images.substring(disposenum+1);
							SysFileUtil.deleteFileByFileName(disposepicstr);
						}
					}
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}
	@Override
	public List<IrpMicroblog> findMicBytype(Long gid,Integer pingtype,int start,int end) {
		List<IrpMicroblog> list=null;
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(IrpMicroblog.ISDELFALSE).andBlogtypeEqualTo(pingtype).andGroupidEqualTo(gid);
		example.setOrderByClause("crtime desc");
		list=irpMicroblogDAO.selectByExampleWithBLOBsbypage(example,start,end);
			
		return list;
	}
	@Override
	public IrpMicroblog findFirstMicroblogbytype(long _userid, Integer pingtype) {
		IrpMicroblog irpMicroblog = null; 
		IrpMicroblogExample example = new IrpMicroblogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andBlogtypeEqualTo(pingtype);
		example.setOrderByClause("crtime desc");
		try {
			PageUtil pageUtil = new PageUtil(1, 1, 1);
			List<IrpMicroblog> list = this.irpMicroblogDAO.selectByExample(example, pageUtil);
			if(list.size()>0){
				irpMicroblog= list.get(0);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return irpMicroblog;
	}
	@Override
	public List<IrpMicroblog> findMicBytypeandvoteid(Long vid, Integer pingtype,PageUtil pageUtil) {
		List<IrpMicroblog> list=null;
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(IrpMicroblog.ISDELFALSE).andBlogtypeEqualTo(pingtype).andGroupidEqualTo(vid);
		example.setOrderByClause("crtime desc");
		try {
			if(pageUtil==null){
				list=irpMicroblogDAO.selectByExampleWithBLOBs(example);
			}else{
				list=irpMicroblogDAO.selectByExampleWithBLOBsbypage(example, pageUtil.getPageIndex(),pageUtil.getPageSize());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int findMicBytypeandvoteidcount(Long vid,
			Integer pingtype) {
		int count=0;
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(IrpMicroblog.ISDELFALSE).andBlogtypeEqualTo(pingtype).andGroupidEqualTo(vid);
		example.setOrderByClause("crtime desc");
		try {
			count=irpMicroblogDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int getMicroblogcount(Date _starttime, Date _endtime) {
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andCrtimeGreaterThan(_starttime).andCrtimeLessThan(_endtime);
		int micronum = 0;
		try {
			micronum= irpMicroblogDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return micronum;
	}
	@Override
	public List searchMicroBlog(String _searchword, Integer _isDel,PageUtil pageUtil,long _loginid) {
		IrpMicroblogExample example = new IrpMicroblogExample();
		example.createCriteria().andIsdelEqualTo(_isDel).andMicroblogcontentLike("%"+_searchword+"%");
		List list = new ArrayList();
		try {
			list = irpMicroblogDAO.searchMicroblog(_searchword, _isDel, pageUtil, _loginid);
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int searchMicroBlogCount(String _searchword, Integer _isDel){
		int num = 0 ;
		try {
			num = irpMicroblogDAO.searchMicroblogcount(_searchword, _isDel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return num;
	}
	@Override
	public List findMicroblogByTime(Date startTime, Date endTime, String orderBy) {
		List microblogList = null;
		IrpMicroblogExample irpMicroblogExample = new IrpMicroblogExample();
		Criteria criteria = irpMicroblogExample.createCriteria();
		criteria.andCrtimeBetween(startTime, endTime);
		irpMicroblogExample.setOrderByClause(orderBy);
		try {
			microblogList = irpMicroblogDAO.selectByExampleWithBLOBs(irpMicroblogExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return microblogList;
	}
}
