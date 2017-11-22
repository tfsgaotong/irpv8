package com.tfs.irp.channel.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.channel.dao.IrpChannelDAO;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample; 
import com.tfs.irp.channel.entity.IrpChannelExample.Criteria;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.opertype.dao.IrpOpertypeDAO;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpChannelServiceImpl implements IrpChannelService {

	private IrpChannelDAO irpChannelDAO;
	
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	
	private IrpOpertypeDAO irpOpertypeDAO;
	
	private IrpSiteService irpSiteService;
	
	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}
	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}
	
	@Override
	public List<IrpChannel> findChannelsByParentId(Long _parentid,Integer status,String orderBy) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			RightUtil rightUtil=new RightUtil(); 
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(!irpUser.isAdministrator()){ 
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null &&nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}
			
			criteria.andParentidEqualTo(_parentid)//栏目
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andStatusEqualTo(status)//是否删除状态
					.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_PUBLIC);
			
			if(orderBy!=null && orderBy.length()>0){
				channelExample.setOrderByClause(orderBy);
			}
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public List<IrpChannel> allRightChannel() { 
		List<IrpChannel>  channels=null; 
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			criteria.andParentidEqualTo(0L)
					.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS)//是否删除状态
					.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_PUBLIC)
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态的栏目
			
			if (!irpUser.isAdministrator()){
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null && nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句  
					criteria.extSQL(sSql);
				 }
			} 
			
			channelExample.setOrderByClause("chnlorder asc");
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public List<IrpChannel> allRightChannel(IrpUser irpUser) { 
		List<IrpChannel>  channels=null; 
		//IrpUser irpUser=LoginUtil.getLoginUser(); 
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			criteria.andParentidEqualTo(0L)
			.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS)//是否删除状态
			.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_PUBLIC)
			.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态的栏目
			
			if (irpUser.getSpecialtype()!=10){
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null && nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句  
					criteria.extSQL(sSql);
				}
			} 
			
			channelExample.setOrderByClause("chnlorder asc");
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public IrpChannel findChannelByPerson(Long _PersonId) { 
		IrpChannel channel=null;
		IrpChannelExample example=new IrpChannelExample();
		example.createCriteria().andSiteidEqualTo(IrpSite.PRIVATE_SITE_ID)
								.andParentidEqualTo(0L)
								.andChnlnameEqualTo(_PersonId.toString());
		 try {
			List<IrpChannel> channels=irpChannelDAO.selectByExample(example);
			if(channels!=null && channels.size()>0){
				channel=channels.get(0);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		 return channel;
	}
	@Override
	public  boolean clientCheckChnlNameByName(Long siteId,Long parentId,Long channelId,String chnlName) { 
		boolean b=true;  
		try {
			IrpChannel _selfChannel=null;
			if(channelId!=null){
				_selfChannel=irpChannelDAO.selectByPrimaryKey(channelId);
			}
			if(_selfChannel!=null){
				if(_selfChannel.getChnlname().equals(chnlName+"")) return true; 
			} 
			IrpChannelExample example=new IrpChannelExample();
			Criteria criteria=example.createCriteria();
			if(siteId!=null && siteId.longValue()!=0L){
				criteria.andSiteidEqualTo(siteId);
			} 
			criteria.andParentidEqualTo(_selfChannel.getParentid()); 
			if(channelId!=null && channelId.longValue()!=0L){
				criteria.andChannelidNotEqualTo(channelId);
			}
			if(chnlName!=null && chnlName.length()>0){
				criteria.andChnlnameEqualTo(chnlName);
			}
			List<IrpChannel> list=irpChannelDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				b=false;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return b;
	}
	@Override
	public boolean checkChnlNameByName(HashMap<String, Object> map) {
		IrpChannelExample example=new IrpChannelExample();
		Criteria criteria=example.createCriteria();
		boolean b=true;
		if(map.get("siteid")!=null){
			criteria.andSiteidEqualTo((Long)map.get("siteid"))
					.andParentidEqualTo((Long)map.get("parentid"));
		} 
		if(map.get("chnlname")!=null){
			criteria.andChnlnameEqualTo(map.get("chnlname").toString());
		}
		try {
			List<IrpChannel> list=irpChannelDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				b=false;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return b;
	}
	@Override
	public Long selectChannelIdsByChnlName(String chnlname) {
		Long chnlid=null;
		try {
			chnlid = irpChannelDAO.selectChannelIdsByChnlName(chnlname);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return chnlid;
	}

	@Override 
	public List<IrpChannel> siteAllChannel(PageUtil pageUtil,HashMap<String,Object> map,List<Integer> chnlTypes) {
		List<IrpChannel> chans=null; 
		try { 
			IrpChannelExample example=new IrpChannelExample(); 
			Criteria criteria=example.createCriteria();
			if(map.get("siteid")!=null){
				criteria.andSiteidEqualTo((Long)map.get("siteid"));
			}
			if(map.get("parentid")!=null){
				criteria .andParentidEqualTo((Long)map.get("parentid"));
			}
			if(map.get("status")!=null){
				criteria.andStatusEqualTo((Integer)map.get("status"));
			}
			if(map.get("publishpro")!=null){
				criteria.andPublishproEqualTo((Integer)map.get("publishpro"));
			}
			if(chnlTypes!=null && chnlTypes.size()>0){
				criteria.andChnltypeIn(chnlTypes);
			}else{
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
			}
			if(map.get("searchWord")!=null && map.get("searchType")!=null ){
				String _sSearchWord=map.get("searchWord").toString(); 
				String _sSearchType=map.get("searchType").toString();  
				if("all".equals(_sSearchType)){
					example=new IrpChannelExample();
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status")).
														andChnlnameLike("%"+_sSearchWord+"%"));
					
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status")).
														andChnldescLike("%"+_sSearchWord+"%"));
						
				} else if("chnlname".equals(_sSearchType)){
					criteria.andChnlnameLike("%"+_sSearchWord+"%");
				} else if("chnldesc".equals(_sSearchType)){
					criteria.andChnldescLike("%"+_sSearchWord+"%");
				} 
			} 
			String _sOrderBy=" chnlorder asc " ; 
		 	example.setOrderByClause(_sOrderBy);  
			chans=irpChannelDAO.site_allChannel(pageUtil, example); 
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return chans;
	}  
	@Override 
	public List<IrpChannel> siteAllChannel(PageUtil pageUtil,HashMap<String,Object> map,Long _channelid,int mapId) {
		List<IrpChannel> chans=null; 
		try { 
			IrpChannelExample example=new IrpChannelExample(); 
			Criteria criteria=example.createCriteria();
			criteria.andSiteidEqualTo((Long)map.get("siteid"))
					.andParentidEqualTo((Long)map.get("parentid"))
					.andStatusEqualTo((Integer)map.get("status"))
					.andChannelidNotEqualTo(_channelid);
			
			if(map.get("searchWord")!=null && map.get("searchType")!=null ){
				String _sSearchWord=map.get("searchWord").toString(); 
				String _sSearchType=map.get("searchType").toString();  
				if("all".equals(_sSearchType)){
					example=new IrpChannelExample();
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status"))
														.andChnlnameLike("%"+_sSearchWord+"%"));
					
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status")).
														andChnldescLike("%"+_sSearchWord+"%"));
						
				} else if("chnlname".equals(_sSearchType)){
					criteria.andChnlnameLike("%"+_sSearchWord+"%");
				} else if("chnldesc".equals(_sSearchType)){
					criteria.andChnldescLike("%"+_sSearchWord+"%");
				} 
			} 
			String _sOrderBy=" chnlorder asc " ; 
			example.setOrderByClause(_sOrderBy);  
		 	//所给类型不是知识分类法就不添加约束
			if(IrpChannel.CHANNEL_TYPE_MAP!=mapId){
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
			} 
			chans=irpChannelDAO.site_allChannel(pageUtil, example);   
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return chans;
	} 
	@Override
	public List<IrpChannel> GCtoAllChannel(PageUtil pageUtil, HashMap<String,Object> map) {
		List<IrpChannel> list=new ArrayList<IrpChannel>();
		try { 
			IrpChannelExample example=new IrpChannelExample(); 
			Criteria criteria=example.createCriteria();
			criteria.andSiteidEqualTo((Long)map.get("siteid"))
												.andParentidEqualTo((Long)map.get("parentid"))
												.andStatusEqualTo((Integer)map.get("status"));
			
			if(map.get("searchWord")!=null && map.get("searchType")!=null ){
				String _sSearchWord=map.get("searchWord").toString(); 
				String _sSearchType=map.get("searchType").toString();  
				if("all".equals(_sSearchType)){
					example=new IrpChannelExample();
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status")).
														andChnlnameLike("%"+_sSearchWord+"%"));
					
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status"))
														.andChnldescLike("%"+_sSearchWord+"%"));
				} else if("chnlname".equals(_sSearchType)){
					criteria.andChnlnameLike("%"+_sSearchWord+"%");
				} else if("chnldesc".equals(_sSearchType)){
					criteria.andChnldescLike("%"+_sSearchWord+"%");
				}
			}  
			list= irpChannelDAO.site_allChannel(pageUtil, example);   
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return list;
	} 
	@Override
	public int adminAddChannel(IrpChannel channel) {  //里面已经包含了栏目id
		int nRows = 1;
		LogUtil logUtil=null;
		try {
			IrpUser cruser=LoginUtil.getLoginUser();
			if(channel.getChannelid()==null){
				channel.setChannelid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
			}
			channel.setCruser(cruser.getUsername());
			channel.setCruserid(cruser.getUserid());
			channel.setCrtime(new Date()); //当前站点创建时间
			channel.setStatus(IrpChannel.IS_DOCSTATIUS);//默认为正常状态为0就是栏目回收站中的栏目
			irpChannelDAO.insertSelective(channel);
			
			logUtil=new LogUtil("CHANNEL_ADD", channel);
			logUtil.successLogs("添加栏目["+logUtil.getLogUser()+"]成功"); 
		} catch (SQLException e) { 
			e.printStackTrace();
			 logUtil.errorLogs("添加栏目"+logUtil.getLogUser()+"失败" ,e);
			nRows = 0;
		}
		return nRows;
	}
	@Override
	public int addChannel(IrpChannel channel, IrpUser cruser) {
		int nRows = 1;
		try {
			channel.setChannelid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
			channel.setCruser(cruser.getUsername());
			channel.setCruserid(cruser.getUserid());
			channel.setCrtime(new Date()); //当前站点创建时间
			channel.setStatus(IrpChannel.IS_DOCSTATIUS);//默认为正常状态为0就是栏目回收站中的栏目

			irpChannelDAO.insertSelective(channel);
		} catch (SQLException e) { 
			e.printStackTrace();
			nRows = 0;
		}
		return nRows;
	} 
	@Override
	public IrpChannel clientAddChannel(IrpChannel channel) { 
		try {
			channel.setChannelid(TableIdUtil.getNextId(IrpChannel.TABLE_NAME));
			IrpUser irpUser=LoginUtil.getLoginUser(); //获得当前登录用户
			List<Long> siteidList= irpSiteService.findSiteIds(IrpSite.SITE_TYPE_PRIVATE);
			
			if(siteidList!=null && siteidList.size()>0){
				IrpChannelExample irpChannelExample=new IrpChannelExample();
				irpChannelExample.createCriteria().andChnlnameEqualTo(irpUser.getUserid().toString())
													.andParentidEqualTo(0L)
													.andSiteidIn(siteidList);
				
				List<IrpChannel> rootChannels=irpChannelDAO.selectByExample(irpChannelExample);
				
				channel.setCruser(irpUser.getUsername());
				channel.setCruserid(irpUser.getUserid());
				channel.setCrtime(new Date()); //当前站点创建时间
				channel.setStatus(IrpChannel.IS_DOCSTATIUS);//默认为正常状态为1
				if(rootChannels!=null && rootChannels.size()>0){
					channel.setSiteid(rootChannels.get(0).getSiteid());
					if(channel.getParentid()==null){
						channel.setParentid(rootChannels.get(0).getChannelid());
					}
				} else{//rootChannel为null
					channel.setChnlname(irpUser.getUserid().toString());
					channel.setChnldesc(irpUser.getUsername());
					channel.setSiteid(siteidList.get(0));
					channel.setParentid(0L);
				}
				irpChannelDAO.insertSelective(channel);
			}
			} catch (SQLException e) { 
				channel= null;
				e.printStackTrace(); 
		} 
		return channel;
	} 
	
	//前台  递归得到所有子栏目对象 
	public  List<IrpChannel> findChannelIdsByParent(Long _nParnetId,List<IrpChannel> _arrChannel){  
		IrpChannel channel=new IrpChannel();
		channel.setParentid(_nParnetId);  
		IrpUser irpUser=LoginUtil.getLoginUser(); 
		IrpChannelExample example = new IrpChannelExample();  
		if(_arrChannel!=null &&_arrChannel.size()>0){//这是循环进来第二次以后的。
			example.createCriteria().andCruseridEqualTo(irpUser.getUserid())//查询当前用户自己创建的栏目
									.andParentidEqualTo(_nParnetId); 
			String _sOrderBy=" channelid desc " ; 
			example.setOrderByClause(_sOrderBy);  
		}else{
			example.createCriteria().andChnlnameEqualTo(irpUser.getUserid().toString())
									.andParentidEqualTo(_nParnetId);
		}
		try {
			List<IrpChannel> channelIds = irpChannelDAO.selectChannelIdsByExample(example); 
			if(channelIds == null || channelIds.size()==0){
				return _arrChannel;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				IrpChannel irpChannel=channelIds.get(i);  
				if(irpChannel==null){
					continue;
				}
				_arrChannel.add(irpChannel);
				_arrChannel = findChannelIdsByParent(irpChannel.getChannelid(),_arrChannel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _arrChannel;
	} 
	//前台  递归得到所有子栏目对象 (专题)
	public  List<IrpChannel> findSubIdsByParent(Long _nParnetId,List<IrpChannel> _arrChannel){  
		IrpChannelExample example = new IrpChannelExample();  
		example.createCriteria().andParentidEqualTo(_nParnetId); 
		String _sOrderBy=" channelid asc " ; 
		example.setOrderByClause(_sOrderBy);
		try {
			List<IrpChannel> channelIds = irpChannelDAO.selectChannelIdsByExample(example); 
			if(channelIds == null || channelIds.size()==0){
				return _arrChannel;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				IrpChannel irpChannel=channelIds.get(i);  
				if(irpChannel==null){
					continue;
				}
				_arrChannel.add(irpChannel);
				_arrChannel = findSubIdsByParent(irpChannel.getChannelid(),_arrChannel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _arrChannel;
	} 
	//前台  递归得到当前关注对象的所有栏目包括子栏目
	@Override
	public  List<IrpChannel> findChannelIdsByPerson(Long _personId, Long _nParnetId,List<IrpChannel> _arrChannel){
		IrpChannel channel=new IrpChannel();
		channel.setParentid(_nParnetId);   
		IrpChannelExample example = new IrpChannelExample();  
		if(_arrChannel!=null &&_arrChannel.size()>0){//这是循环进来第二次以后的。
			example.createCriteria().andCruseridEqualTo(_personId)//查询关注用户自己创建的栏目
									.andParentidEqualTo(_nParnetId); 
		}else{
			example.createCriteria().andChnlnameEqualTo(_personId.toString())//第一次进来查看栏目名称等于关注用户的栏目
									.andParentidEqualTo(_nParnetId);
		}
		try {
			List<IrpChannel> channelIds = irpChannelDAO.selectChannelIdsByExample(example);
			if(channelIds == null || channelIds.size()==0){
				return _arrChannel;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				IrpChannel irpChannel=channelIds.get(i);  
				if(irpChannel==null){
					continue;
				}
				_arrChannel.add(irpChannel);
				_arrChannel = findChannelIdsByPerson( _personId,irpChannel.getChannelid(),_arrChannel);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _arrChannel;
	}   
	@Override
	public List<IrpChannel> findChanlsByParentid(Long pid) {
		List<IrpChannel> chans=null;
		try {
			IrpChannelExample example=new IrpChannelExample();
			example.createCriteria().andParentidEqualTo(pid);
			example.createCriteria().andStatusEqualTo(IrpChannel.IS_DOCSTATIUS);//查询只查询状态为1的，状态为0的删除到垃圾回收站中了
			example.setOrderByClause("chnlorder asc");
			chans=irpChannelDAO.selectByExample(example); 
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return chans;
	}
	
	@Override
	public int findChild_Channl_CountByParentId(Long channelid) {
		int nCount=0;
		IrpChannelExample example =new IrpChannelExample();
		example.createCriteria().andParentidEqualTo(channelid)
								.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS);	 //他的状态要为1
		try {
			nCount = irpChannelDAO.countByExample(example); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	} 
	@Override
	public int findChild_Channl_CountByParentId(Long channelid,Integer _status) {
		int nCount=0;
		IrpChannelExample example =new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(channelid);
		 //他的状态要为1
		criteria.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS).andPublishproEqualTo(_status);//发布状态的栏目
		
		IrpUser irpUser=LoginUtil.getLoginUser();
		if (!irpUser.isAdministrator()){
			RightUtil rightUtil=new RightUtil();
			Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId=rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");//查看栏目下文的列表权限
			if(nOperId!=null && nOperId>0L &&nOperDocId!=null && nOperDocId>0){
				String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
				String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句  
				criteria.extSQL(sSql).extSQL(sDocSql);
			 }
		} 
		
		try {
			nCount = irpChannelDAO.countByExample(example); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	public IrpChannelDAO getIrpChannelDAO() {
		return irpChannelDAO;
	}
	
	public void setIrpChannelDAO(IrpChannelDAO irpChannelDAO) {
		this.irpChannelDAO = irpChannelDAO;
	} 
	@Override
	public int deleteChannelFromGC(Long[] channelids, Long _nParentId, Long _nSiteId, Integer siteType) {
		int nCount=0;
		LogUtil logUtil=null;
		List<Long> arrChannelIds = new ArrayList<Long>();
		for (Long pid : channelids) { 
			arrChannelIds = findChannelIdsByParentId(pid, arrChannelIds,siteType,IrpChannel.NO_DOCSTATUS,LoginUtil.getLoginUser());
		}
		IrpChannelExample example = new IrpChannelExample();
		//处理Oracle在执行in时超过1000个报错
		if(arrChannelIds!=null && arrChannelIds.size()>1000){
			if(arrChannelIds.size()>1000){
				List<Long> tempChannelids=new ArrayList<Long>();
				for (int i = 0; i <arrChannelIds.size(); i++) {
					tempChannelids.add(arrChannelIds.get(i));
					if(tempChannelids.size()==1000 || (i+1)==arrChannelIds.size()){
						example.or(example.createCriteria().andChannelidIn(tempChannelids));
						tempChannelids=new ArrayList<Long>();
					}
				}
			}
		}else{
			example.createCriteria().andChannelidIn(arrChannelIds);
		}
		try {
			IrpChannel _irpChannel=irpChannelDAO.selectByPrimaryKey(arrChannelIds.get(0));
			logUtil=new LogUtil("CHANNEL_DELETE",_irpChannel);
			HashMap<String,Long> map=new HashMap<String, Long>();
			map.put("siteid", _irpChannel.getSiteid());
			List<Long> otherChannelid=new  ArrayList<Long>();
			for (int i = 0; i < arrChannelIds.size(); i++) {
				otherChannelid.add(-arrChannelIds.get(i));
			} 
			List<Long> docids=irpChnl_Doc_LinkService.docIds(otherChannelid, null);
			if(docids!=null && docids.size()>0){
				irpChnl_Doc_LinkService.deleteDocumentLinkFromGC(docids);
			}
			nCount = irpChannelDAO.deleteByExample(example);
			if(nCount>0){
				Map<String, Object> mParam = new HashMap<String, Object>();
				mParam.put("parentid", _nParentId);
				mParam.put("siteid", _nSiteId);
				irpChannelDAO.reorderByChannel(mParam);
			}
			logUtil.successLogs( "彻底删除栏目["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) { 
			e.printStackTrace();
			logUtil.errorLogs( "彻底删除栏目"+logUtil.getLogUser()+"失败", e);
		}
		return nCount;
	} 
	@Override
	public int updateChannelStatusByChannelIds(Long[] _nParentId, int _nStatus,Integer siteType) {
		int nCount = 0;
		List<Long> arrChannelIds = new ArrayList<Long>();
		LogUtil logUtil=null;
		for (Long pid : _nParentId) {
			arrChannelIds = findChannelIdsByParentId(pid, arrChannelIds,siteType,_nStatus,LoginUtil.getLoginUser());
		}  
		IrpChannelExample example = new IrpChannelExample();
		
        //处理Oracle在执行in时超过1000个报错
		if(arrChannelIds!=null && arrChannelIds.size()>1000){
			if(arrChannelIds.size()>1000){
				List<Long> tempChannelids=new ArrayList<Long>();
				for (int i = 0; i <arrChannelIds.size(); i++) {
					tempChannelids.add(arrChannelIds.get(i));
					if(tempChannelids.size()==1000 || (i+1)==arrChannelIds.size()){
						example.or(example.createCriteria().andChannelidIn(tempChannelids));
						tempChannelids=new ArrayList<Long>();
					}
				}
			}
		}else{
			example.createCriteria().andChannelidIn(arrChannelIds);
		}
		try { 
			IrpChannel _irpChannel=irpChannelDAO.selectByPrimaryKey(arrChannelIds.get(0));//查询第一个栏目
			logUtil=new LogUtil("CHANNEL_STATUS_UPDATE",_irpChannel);
			IrpChannel channel = new IrpChannel();
			channel.setStatus(_nStatus);
			if(_nStatus==0){
				IrpUser irpUser=LoginUtil.getLoginUser();//获得当前登录用户 
				channel.setOperuser(irpUser.getUsername());
				channel.setOpertime(new Date());  
				nCount = irpChannelDAO.updateByExampleSelective(channel, example); 
				for (Long pid : arrChannelIds){//修改栏目下的文档栏目id 
					IrpChannel channelChild=irpChannelDAO.selectByPrimaryKey(pid);
						if(channelChild!=null){
							Long oldChannelId=channelChild.getChannelid();
							if(oldChannelId.longValue()>0){//将正常的栏目删除到回收站，本身在回收站中的不去管
								Long chanid_=-oldChannelId; 
								irpChnl_Doc_LinkService.updateDocumentDocstatusByChannel(pid);
								irpChnl_Doc_LinkService.updateDocumentToChannel(chanid_, channelChild.getChannelid());
							}
						}
				} 
			}  
			logUtil.successLogs( "删除栏目["+logUtil.getLogUser()+"]到回收站，成功");
		} catch (SQLException e) {
			logUtil.errorLogs( "删除栏目["+logUtil.getLogUser()+"]到回收站，失败",e);
			e.printStackTrace();
		} 
		return nCount;
	} 
	public int updateChannelStatusByChannelIds2(Long[] _nParentId, int _nStatus,Integer siteType) {
		//获得当前传入的父栏目Id下的所有子栏目ID集合
		int nCount = 0;
		List<Long> arrChannelIds = new ArrayList<Long>();
		for (Long pid : _nParentId) {
			arrChannelIds = findChannelIdsByParentId(pid, arrChannelIds,siteType,IrpChannel.NO_DOCSTATUS,LoginUtil.getLoginUser());
		} 
		//初始化参数
		IrpChannelExample example = new IrpChannelExample();
		//处理Oracle在执行in时超过1000个报错
		if(arrChannelIds!=null && arrChannelIds.size()>1000){
			if(arrChannelIds.size()>1000){
				List<Long> tempChannelids=new ArrayList<Long>();
				for (int i = 0; i <arrChannelIds.size(); i++) {
					tempChannelids.add(arrChannelIds.get(i));
					if(tempChannelids.size()==1000 || (i+1)==arrChannelIds.size()){
						example.or(example.createCriteria().andChannelidIn(tempChannelids));
						tempChannelids=new ArrayList<Long>();
					}
				}
			}
		}else{
			example.createCriteria().andChannelidIn(arrChannelIds);
		}
		LogUtil logUtil=null;
		try {
			IrpChannel channel = new IrpChannel();
			channel.setStatus(_nStatus);
			nCount = irpChannelDAO.updateByExampleSelective(channel, example);
			 //查询第一个栏目
			IrpChannel _IrpChannel=irpChannelDAO.selectByPrimaryKey(arrChannelIds.get(0));
			logUtil=new LogUtil("CHANNEL_RESTORE", _IrpChannel); 
			for (Long pid : arrChannelIds){//修改栏目下的文档栏目id 
				//先查出来他的栏目id 
				IrpChannel channelChild=irpChannelDAO.selectByPrimaryKey(pid);
				if(channelChild!=null){
					Long oldChannelId=channelChild.getChannelid();
					if(oldChannelId.longValue()>0){//将正常的栏目删除到回收站，本身在回收站中的不去管
						Long chanid_=-oldChannelId; 
						//批量修改栏目下的知识状态为相反数
						irpChnl_Doc_LinkService.updateDocumentDocstatusByChannel(-pid);//调用修改中间表和document的docstatus方法
						irpChnl_Doc_LinkService.updateDocumentToChannel(channelChild.getChannelid(),chanid_);//修改中间表和docuemnt的channelid为相反数方法
					}
				}
			} 
			logUtil.successLogs( "恢复栏目["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			logUtil.errorLogs( "恢复栏目["+logUtil.getLogUser()+"]失败",e);
			e.printStackTrace();
		} 
		return nCount;
	}
	@Override
	public IrpChannel finChannelByChannelid(Long channelid) {
		IrpChannel irpChannel=null; 
		try {//目前我查询了一个栏目的所有信息，如果考虑速度的话， 可以适当减少需要查询的字段
			irpChannel = irpChannelDAO.selectByPrimaryKey(channelid);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return irpChannel;
	}
	@Override
	public int updateChannelByChannelid(IrpChannel channel) {  
		int nCount=0; 
		LogUtil logUtil=null;
		try {
			logUtil=new LogUtil("DOCUMENT_UPDATE", channel);  
			nCount=irpChannelDAO.updateByPrimaryKeySelective(channel);
			logUtil.successLogs( "修改栏目["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) { 
			logUtil.errorLogs( "修改栏目["+logUtil.getLogUser()+"]失败",e);
			e.printStackTrace();
		} 
		return nCount;
	}
	 
	public int channelCountMap(HashMap<String,Object> map){
		int nCount=0;  
		try {
			IrpChannelExample example=new IrpChannelExample(); 
			Criteria criteria=example.createCriteria();
			Long parentid=(Long)map.get("parentid");
			if(parentid==null || parentid.longValue()==0L){//说明查询的是站点下的以及栏目，因此不查询知识地图
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			}
			criteria.andSiteidEqualTo((Long)map.get("siteid"))
												.andParentidEqualTo(parentid)
												.andStatusEqualTo((Integer)map.get("status"));
			if(map.get("searchWord")!=null && map.get("searchType")!=null ){
				String _sSearchWord=map.get("searchWord").toString(); 
				String _sSearchType=map.get("searchType").toString();  
				if("all".equals(_sSearchType)){
					example=new IrpChannelExample();
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status"))
														.andChnlnameLike("%"+_sSearchWord+"%"));
					
					example.or(example.createCriteria().andSiteidEqualTo((Long)map.get("siteid"))
														.andParentidEqualTo((Long)map.get("parentid"))
														.andStatusEqualTo((Integer)map.get("status"))
														.andChnldescLike("%"+_sSearchWord+"%"));
						
				} else if("chnlname".equals(_sSearchType)){
					criteria.andChnlnameLike("%"+_sSearchWord+"%");
				} else if("chnldesc".equals(_sSearchType)){
					criteria.andChnldescLike("%"+_sSearchWord+"%");
				}
			}  
			nCount=irpChannelDAO.countByMap(example);  
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int findChannelCountBySiteId(long _sSiteId) {
		int nCount = 0;
		IrpChannelExample example = new IrpChannelExample();
		example.createCriteria().andSiteidEqualTo(_sSiteId).andParentidEqualTo(0L).andStatusGreaterThan(IrpChannel.NO_DOCSTATUS);
		try {
			nCount = irpChannelDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpChannel> findChannelsBySiteId(long _sSiteId) {
		List<IrpChannel> channels = new ArrayList<IrpChannel>();;
		IrpChannelExample example = new IrpChannelExample();
		example.createCriteria().andSiteidEqualTo(_sSiteId).andParentidEqualTo(0L).andStatusGreaterThan(0);
		example.setOrderByClause("chnlorder asc");
		try {
			channels = irpChannelDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channels;
	}

	@Override
	public List<IrpChannel> findChannelsBySiteId(long _sSiteId, int _nChnlType) {
		List<IrpChannel> channels = new ArrayList<IrpChannel>();;
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andSiteidEqualTo(_sSiteId).andParentidEqualTo(0L).andChnltypeEqualTo(_nChnlType).andStatusGreaterThan(0);
		example.setOrderByClause("chnlorder asc");
		try {
			channels = irpChannelDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channels;
	}
	public IrpOpertypeDAO getIrpOpertypeDAO() {
		return irpOpertypeDAO;
	}
	public void setIrpOpertypeDAO(IrpOpertypeDAO irpOpertypeDAO) {
		this.irpOpertypeDAO = irpOpertypeDAO;
	} 
	 @Override
	public int clientDeleteChannel(Long _channelid) {
		// TODO Auto-generated method stub
		 int nCount=0;
		 try {
			 if(_channelid!=null ){ 
				 IrpChannel  Channel=irpChannelDAO.selectByPrimaryKey(_channelid);
				 if(Channel!=null ){
					 IrpChnlDocLinkExample example=new IrpChnlDocLinkExample(); 
					 IrpChnlDocLink record=new IrpChnlDocLink();
					 record.setChannelid(Channel.getParentid());//修改之后的channelid
					 irpChnl_Doc_LinkService.updateDocumentToChannel(Channel.getParentid(), Channel.getChannelid());
					 nCount=irpChannelDAO.deleteByPrimaryKey(_channelid);
				 }
			} 
		} catch (SQLException e) {
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	 public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}
	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}
	@Override
	public String findChannelNameByChannelid(Long _channelid) {
		String chnlName=null;
		try {
			chnlName=irpChannelDAO.findChannelNameByChannelid(_channelid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlName;
	}
	@Override
	public List<Long> clientfindRightChannelId(List<Long> _arrChannelIds,List<Integer> chnlTypes,Integer chnlStatus,Long _nParentId,Integer siteType,IrpUser irpUser){
		//递归得到所有子栏目的主键id 
		_arrChannelIds.add(_nParentId);
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andParentidEqualTo(_nParentId); 
		if(chnlStatus!=null){
			criteria.andStatusEqualTo(chnlStatus);	
		}
		//是企业站点类型
		if(siteType.intValue()==IrpSite.SITE_TYPE_PUBLISH){
			//限制栏目类型
			if(chnlTypes!=null && chnlTypes.size()>0){
				criteria.andChnltypeIn(chnlTypes);
			}else{
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
				criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			}
			RightUtil rightUtil=new RightUtil(); 
			if(!irpUser.isAdministrator()){ 
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null &&nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}  
		}
		try {
			List<Long> channelIds = irpChannelDAO.selectIdsByExample(example);
			if(channelIds == null || channelIds.size()==0){
				return _arrChannelIds;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				Long channelId = channelIds.get(i);
				if(channelId==null){
					continue;
				} 
				_arrChannelIds = clientfindRightChannelId(_arrChannelIds, chnlTypes, chnlStatus, channelId, siteType, irpUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return _arrChannelIds;
	}
	/**
	 *后期可能被使用clientfindRightChannelId方法替换掉
	 *两者区别就是栏目类型限制区别
	 */
	@Override
	public List<Long> findChannelIdsByParentId(Long _nParnetId, List<Long> _arrChannelIds,Integer siteType,Integer chnlStatus,IrpUser irpUser) {
		//递归得到所有子栏目的主键id 
		_arrChannelIds.add(_nParnetId);
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(_nParnetId); 
		if(chnlStatus!=null){
			criteria.andStatusEqualTo(chnlStatus);	
		}
		if(siteType==IrpSite.SITE_TYPE_PUBLISH){//是企业站点类型
			RightUtil rightUtil=new RightUtil(); 
			if(!irpUser.isAdministrator()){ 
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null &&nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(irpUser, new IrpChannel(),nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}  
		}
		try {
			List<Long> channelIds = irpChannelDAO.selectIdsByExample(example);
			if(channelIds == null || channelIds.size()==0){
				return _arrChannelIds;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				Long channelId =channelIds.get(i);
				if(channelId==null){
					continue;
				} 
				_arrChannelIds = findChannelIdsByParentId(channelId,_arrChannelIds,siteType,chnlStatus,irpUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	return _arrChannelIds;
	}
	@Override
	public List<IrpChannel> clientRightChannel(HashMap<String,Object> map, List<Integer> chnlTypes) {
		List<IrpChannel> chans=null; 
		IrpUser irpUser=LoginUtil.getLoginUser();
		try {
			 String _sOrderBy=" chnlorder asc " ; 
			 IrpChannelExample channelExample=new IrpChannelExample();
			 channelExample.setOrderByClause(_sOrderBy);  
			 Criteria criteria = channelExample.createCriteria();
			 if(chnlTypes!=null && chnlTypes.size()>0){
				criteria.andChnltypeIn(chnlTypes);
			 }else{
				 criteria.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
			 }
			criteria .andSiteidNotEqualTo(IrpSite.PRIVATE_SITE_ID)
								.andParentidEqualTo((Long)map.get("parentid"))
								.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS)//正常状态的
								.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态下的
			if (!irpUser.isAdministrator()) { 
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");//查看栏目的权限
				Long nOperDocId=rightUtil.findOperTypeIdByKey("DOCUMENT_ADD");//查看栏目下文的列表权限
				if(nOperId!=null && nOperId>0L &&nOperDocId!=null && nOperDocId>0){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
					criteria.extSQL(sSql).extSQL(sDocSql);
				}
			} 
			chans=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) { 
			e.printStackTrace();
		}  
		return chans;
	}
	@Override
	//获取站点下面所有有权限的栏目集合
	public List<IrpChannel> JsonRightAllChannelMore(HashMap<String,Object> map) {
		List<IrpChannel> chans=null; 
		IrpUser irpUser=LoginUtil.getLoginUser();
		try {
			List<IrpSite> siteList=irpSiteService.findSitesBySiteType(IrpSite.SITE_TYPE_PUBLISH);
			List<Long> siteIdLis=new ArrayList<Long>();
			if(siteList!=null && siteList.size()>0){
				for (int i = 0; i <siteList.size(); i++) {
					siteIdLis.add(siteList.get(i).getSiteid());
				} 
			} 
			 String _sOrderBy=" chnlorder asc " ; 
			 IrpChannelExample channelExample=new IrpChannelExample();
			 channelExample.setOrderByClause(_sOrderBy);  
			Criteria criteria = channelExample.createCriteria();
			criteria .andSiteidIn(siteIdLis);
			Object objectId = map.get("parentid");
			if(objectId!=null){
				criteria .andParentidEqualTo((Long)map.get("parentid"));	
			}
			criteria .andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_GONGGAO)
				.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP)
				.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MONTH_TOP)
				.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT)
				.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT)
				.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS)//正常状态的
				.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态下的
			/**
			 * 加权限
			 */
			if (!irpUser.isAdministrator()) { 
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");//查看栏目的权限
				Long nOperDocId=rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");//查看栏目下文的列表权限
				if(nOperId!=null && nOperId>0L &&nOperDocId!=null && nOperDocId>0){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
					if(siteIdLis!=null && siteIdLis.size()>0){
						criteria.extSQL(sSql).andSiteidIn(siteIdLis).extSQL(sDocSql);
					}
				}
			}
			/**
			 * 加权限
			 */
			chans=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) { 
			e.printStackTrace();
		}  
		return chans;
	}
	@Override
	//查询某个站点下的所有栏目
	public List<IrpChannel> jsonRightAllChannelAdmin(HashMap<String,Object> map) {
		List<IrpChannel> chans=null; 
		IrpUser irpUser=LoginUtil.getLoginUser();
		try {
			List<IrpSite> siteList=irpSiteService.findSitesBySiteType(IrpSite.SITE_TYPE_PUBLISH);
			List<Long> siteIdLis=new ArrayList<Long>();
			if(siteList!=null && siteList.size()>0){
				for (int i = 0; i <siteList.size(); i++) {
					siteIdLis.add(siteList.get(i).getSiteid());
				} 
			} 
			if (irpUser.isAdministrator()) {
				/**
				 * 管理员，查询所有的企业站点下面的一级栏目
				 */
				if(siteIdLis!=null && siteIdLis.size()>0){
					IrpChannelExample channelExample=new IrpChannelExample();
					channelExample.createCriteria().andSiteidIn(siteIdLis)
													.andParentidEqualTo((Long)map.get("parentid"))
													.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_GONGGAO)
													.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MONTH_TOP) ;
//													.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态下的
					channelExample.setOrderByClause(" chnlorder asc ");
					chans=irpChannelDAO.selectByExample(channelExample);
				} 
			}else{
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null && nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					IrpChannelExample channelExample=new IrpChannelExample(); 
					if(siteIdLis!=null && siteIdLis.size()>0){
						channelExample.createCriteria().extSQL(sSql).andSiteidIn(siteIdLis)
							.andParentidEqualTo((Long)map.get("parentid"))
							.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_GONGGAO)
							.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MONTH_TOP) 
							.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MAP);
						channelExample.setOrderByClause(" chnlorder asc ");
						chans=irpChannelDAO.selectByExample(channelExample);
					}
				}
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}  
		return chans;
	}  
	@Override
	public List<IrpChannel> findChannelByChnlType(Integer _chnlType) {
		
		List<IrpChannel> channels=null;
		
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			
			Criteria criteria = channelExample.createCriteria();
			
			criteria.andChnltypeEqualTo(_chnlType);
			criteria.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态下的栏目
			/**
			 * 加权限
			 */ 
				RightUtil rightUtil=new RightUtil(); 
				IrpUser irpUser=LoginUtil.getLoginUser();
				
				if(!irpUser.isAdministrator()){ 
					Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
					if(nOperId!=null &&nOperId>0L){
						String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
						 criteria.extSQL(sSql); 
					}
				} 
			/**
			 * 以上为加权限
			 */ 
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channels;
	}
	@Override
	public List<Long> mapParentLine(Long _channelid,List<Long> channelidList,Integer chnlStatus) {
		try { 
			IrpChannelExample  channelExample=new IrpChannelExample();
			
			Criteria criteria = channelExample.createCriteria();
		 
			criteria.andChannelidEqualTo(_channelid);
			criteria.andStatusEqualTo(chnlStatus);//栏目是否删除状态
			List<IrpChannel> channels=irpChannelDAO.selectByExample(channelExample);//查询他自己，
			if(channels!=null && channels.size()>0){
				channelidList.add(_channelid);
				if(channels.get(0).getParentid()==0L){//如果他的父类的parentid=0，说明是一级栏目终止递归
					return channelidList;
				}
				_channelid=channels.get(0).getParentid();//查询他的父栏目
				mapParentLine(_channelid, channelidList,chnlStatus);
			}else{ 
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channelidList;
	}
	@Override
	public List<Long> allparentidList(Long _channelid,List<Long> channelidList,Integer chnlStatus,IrpUser irpUser) {
		try {
			IrpChannelExample  channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			if (!irpUser.isAdministrator()){
				
				RightUtil rightUtil=new RightUtil();
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				Long nOperDocId=rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");//查看栏目下文的列表权限
				if(nOperId!=null && nOperId>0L &&nOperDocId!=null && nOperDocId>0){
					
					String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
					//System.out.println(sDocSql);
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句  
					//System.out.println(sSql);
					criteria.extSQL(sSql).extSQL(sDocSql);
				 }
			}
			 
			criteria.andChannelidEqualTo(_channelid);
			criteria.andStatusEqualTo(chnlStatus);//栏目是否删除状态
			List<IrpChannel> channels=irpChannelDAO.selectByExample(channelExample);//查询他自己，
			if(channels!=null && channels.size()>0){
				channelidList.add(_channelid);
				if(channels.get(0).getParentid().longValue()==0L){//如果他的父类的parentid=0，说明是一级栏目终止递归
					return channelidList;
				}
				_channelid=channels.get(0).getParentid();//查询他的父栏目
				allparentidList(_channelid, channelidList,chnlStatus,irpUser);
			}else{ 
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channelidList;
	}
	@Override
	public List<IrpChannel> allDocumentMap(Integer channelStatus) {
		// TODO Auto-generated method stub
		List<IrpChannel> list=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			channelExample.createCriteria().andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_MAP)
											.andParentidEqualTo(0L)
											.andStatusEqualTo(channelStatus);
			List<IrpChannel> channelis=irpChannelDAO.selectByExample(channelExample);
			if(channelis!=null && channelis.size()>0){
				IrpChannel rootChannel=channelis.get(0);
				//查询他的所有一级分类
				IrpChannelExample channelExample2=new IrpChannelExample();
				channelExample2.createCriteria().andParentidEqualTo(rootChannel.getChannelid())
												.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
												.andStatusEqualTo(channelStatus);//发布状态下的栏目
				channelExample2.setOrderByClause(" chnlorder asc");
				list=irpChannelDAO.selectByExample(channelExample2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean findRightToChannel(IrpUser irpUser, Long channeLid, String rightOpertion, Integer chnlPublishPro) {
		boolean b=false;
		RightUtil rightUtil=new RightUtil();
		Long nOperDocId=rightUtil.findOperTypeIdByKey(rightOpertion);//查看栏目下文的列表权限
		IrpChannelExample channelExample=new IrpChannelExample();
		if(nOperDocId!=null && nOperDocId>0){
			String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
			channelExample.createCriteria().extSQL(sDocSql).andPublishproEqualTo(chnlPublishPro)
											.andChannelidEqualTo(channeLid);
			try {
				List list = irpChannelDAO.selectByExample(channelExample);
				if(list!=null && list.size()>0){
					b=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		 } 
		return b;
	}
	
	@Override
	public boolean findRightSubToChannel(IrpUser irpUser, String rightOpertion, Integer chnlPublishPro,String tableName,String channelid) {
		boolean b=false;
		RightUtil rightUtil=new RightUtil();
		Long nOperDocId=rightUtil.findOperTypeIdByKey(rightOpertion);//查看栏目下文的列表权限
		IrpChannelExample channelExample=new IrpChannelExample();
		if(nOperDocId!=null && nOperDocId>0){
			String sDocSql=rightUtil.getRightExistsSQL(new IrpChannel(), nOperDocId);
			channelExample.createCriteria().extSQL(sDocSql).andPublishproEqualTo(chnlPublishPro);
			try {
				List list = irpChannelDAO.selectByExample(channelExample);
				if(list!=null && list.size()>0){
					b=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		 } 
		return b;
	}
	
	@Override
	public List<Long> mapChildrenIds(Long _mapParentId, Integer _status,List _arrChannelIds) {
		//递归得到所有子栏目的主键id 
		_arrChannelIds.add(_mapParentId);
		IrpChannelExample example = new IrpChannelExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(_mapParentId); 
		if(_status!=null){
			criteria.andStatusEqualTo(_status);	
		}
		try {
			List<Long> channelIds = irpChannelDAO.selectIdsByExample(example);
			if(channelIds == null || channelIds.size()==0){
				return _arrChannelIds;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				Long channelId =channelIds.get(i);
				if(channelId==null){
					continue;
				} 
				_arrChannelIds = mapChildrenIds(channelId,_status,_arrChannelIds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _arrChannelIds;
	}
	@Override
	public List<Long> seeDocumentListRightChannel(IrpUser irpUser) {
		List<Long> rightList=null;
		if(irpUser==null)
			return rightList;
		IrpChannelExample channelExample = new IrpChannelExample();
		Criteria criteria = channelExample.createCriteria();
		criteria.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH);//发布状态下的栏目
		if(!irpUser.isAdministrator()){
			RightUtil rightUtil=new RightUtil();
			Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
			Long nOperDocId=rightUtil.findOperTypeIdByKey("DOCUMENT_LIST");//知识列表权限
			if(nOperId!=null && nOperId.longValue()>0L&& nOperDocId!=null &&nOperDocId.longValue()>0L){
				String sSql=rightUtil.getRightExistsSQL(irpUser, new IrpChannel(), nOperId);//得到是否有权限语句
				String sDocSql=rightUtil.getRightExistsSQL(irpUser, new IrpChannel(), nOperDocId);//得到是否有权限语句
				criteria.extSQL(sSql).extSQL(sDocSql);
			}
		}
		
		try {
			rightList=irpChannelDAO.selectIdsByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rightList;
	}
	@Override
	public List<IrpChannel> findChannelByExample(IrpChannelExample example) {
		try {
			return irpChannelDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<IrpChannel> selectByExample(PageUtil page,
			IrpChannelExample example) {
		return irpChannelDAO.selectByExample(page, example);
	}
	
	/**
	 * 根据例子新增
	 * @param irpChannel
	 */
	public void insertChannelByExample(IrpChannel irpChannel){
		try {
			irpChannelDAO.insertSelective(irpChannel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<IrpChannel> findChannelsByPAndT(Long _nParnetId,List<IrpChannel> _arrChannel, Long type) {
		IrpChannelExample example = new IrpChannelExample(); 
		example.createCriteria().andParentidEqualTo(_nParnetId).andChnltypeEqualTo(type.intValue()); 
		String _sOrderBy=" channelid asc " ; 
		example.setOrderByClause(_sOrderBy);
		try {
			List<IrpChannel> channelIds = irpChannelDAO.selectChannelIdsByExample(example); 
			if(channelIds == null || channelIds.size()==0){
				return _arrChannel;
			}
			for (int i = 0; i < channelIds.size(); i++) {
				IrpChannel irpChannel=channelIds.get(i);  
				if(irpChannel==null){
					continue;
				}
				_arrChannel.add(irpChannel);
				_arrChannel = findSubIdsByParent(irpChannel.getChannelid(),_arrChannel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return _arrChannel;
	}
	
	@Override
	public List<Long> findAllSubjectFatherId(IrpChannel channel,List<Long> fatherIds) {
		if(fatherIds==null || fatherIds.size()==0){
			//调用方法前已经判断了父类id不是企业和知识专题的id
			fatherIds.add(channel.getParentid());
		}
		try {
			IrpChannel fatherChannel = irpChannelDAO.selectByPrimaryKey(channel.getParentid());
			if(SysConfigUtil.getSysConfigNumValue("DOCUMENT_SUBJECT_ID")!=fatherChannel.getParentid().intValue() && SysConfigUtil.getSysConfigNumValue("DOCUMENT_QIYE_SUBJECT_ID")!= fatherChannel.getParentid().intValue()){
				fatherIds.add(fatherChannel.getParentid());
				fatherChannel = irpChannelDAO.selectByPrimaryKey(fatherChannel.getParentid());
				findAllSubjectFatherId(fatherChannel,fatherIds);
			}else{
				return fatherIds;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fatherIds;
	}
	
	@Override
	public List<Long> findAllMapFatherId(IrpChannel channel,List<Long> fatherIds) {
		if(fatherIds==null || fatherIds.size()==0){
			fatherIds.add(channel.getParentid());
		}
		try {
			IrpChannel fatherChannel = irpChannelDAO.selectByPrimaryKey(channel.getParentid());
			if(SysConfigUtil.getSysConfigNumValue("DOCUMENT_MAP_ID")!=fatherChannel.getChannelid().intValue() && SysConfigUtil.getSysConfigNumValue("DOCUMENT_MAP_ID")!= fatherChannel.getParentid().intValue()){
				fatherIds.add(fatherChannel.getParentid());
				fatherChannel = irpChannelDAO.selectByPrimaryKey(fatherChannel.getParentid());
				findAllMapFatherId(fatherChannel,fatherIds);
			}else{
				return fatherIds;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fatherIds;
	}
	@Override
	public List<IrpChannel> findAllChannel(Long _printId) {
		List<IrpChannel> irpchannel=null;
		IrpChannelExample example = new IrpChannelExample();
		example.createCriteria().andParentidEqualTo(_printId).andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_GONGGAO)
		.andChnltypeNotEqualTo(IrpChannel.CHANNEL_TYPE_MONTH_TOP);
		try {
			irpchannel=this.irpChannelDAO.selectChannelIdsByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpchannel;
	}
	
	@Override
	public List<IrpChannel> findChannelsBySiteIds(long _sSiteId) {
		List<IrpChannel> channels = new ArrayList<IrpChannel>();;
		IrpChannelExample example = new IrpChannelExample();
		example.createCriteria().andSiteidEqualTo(_sSiteId).andParentidEqualTo(0L).andStatusEqualTo(1);
		
		try {
			channels = irpChannelDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channels;
	}
	
	@Override
	public List<IrpChannel> currentChannels(Long _channelid, List<IrpChannel> channelList, long _nRootId) {
		IrpChannel channel = null;
		if(channelList==null)
			channelList = new ArrayList<IrpChannel>();
		try {
			channel = irpChannelDAO.selectByPrimaryKey(_channelid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(channel==null){
			return channelList;
		}else if(channel.getParentid().intValue()==_nRootId){
			channelList.add(0,channel);
			return channelList;
		}
		channelList.add(0,channel);
		return currentChannels(channel.getParentid(),channelList,_nRootId);
	}
	
	@Override
	public List<IrpChannel> findRightChannelsByParentId(long _parentid, int _nStatus, int _nChnltype, long _nOperId, String orderBy) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			RightUtil rightUtil=new RightUtil(); 
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(!irpUser.isAdministrator()){ 
				if(_nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(), _nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}
			
			criteria.andParentidEqualTo(_parentid)//栏目
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andStatusEqualTo(_nStatus)//是否删除状态
					.andChnltypeEqualTo(_nChnltype);
			
			if(orderBy!=null && orderBy.length()>0){
				channelExample.setOrderByClause(orderBy);
			}
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public List<IrpChannel> findChannelsByParentId(Long _parentid,Integer status,String orderBy,String token) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			RightUtil rightUtil=new RightUtil(); 
			IrpUser irpUser=mobileAction.getlogin(token);
			//用户id为1(admin用户)的默认设置成系统管理员
			if(irpUser.getUserid()==1L){
				irpUser.setAdministrator(true);
			}
			if(!irpUser.isAdministrator()){ 
				Long nOperId=rightUtil.findOperTypeIdByKey("CHANNEL_SELECT");
				if(nOperId!=null &&nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(),nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}
			
			criteria.andParentidEqualTo(_parentid)//栏目
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andStatusEqualTo(status)//是否删除状态
					.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_PUBLIC);
			
			if(orderBy!=null && orderBy.length()>0){
				channelExample.setOrderByClause(orderBy);
			}
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public List<IrpChannel> findRightChannelsByParentId(long _parentid, int _nStatus, int _nChnltype, long _nOperId, String orderBy,String token) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			RightUtil rightUtil=new RightUtil(); 
			IrpUser irpUser=mobileAction.getlogin(token);
			if(!irpUser.isAdministrator()){ 
				if(_nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(), _nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}
			
			criteria.andParentidEqualTo(_parentid)//栏目
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andStatusEqualTo(_nStatus)//是否删除状态
					.andChnltypeEqualTo(_nChnltype);
			
			if(orderBy!=null && orderBy.length()>0){
				channelExample.setOrderByClause(orderBy);
			}
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels;
	}
	@Override
	public List<IrpChannel> documentMapByMaptype(Long maptype) {
		// TODO Auto-generated method stub
		List<IrpChannel> list=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			channelExample.createCriteria().andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_MAP)
											.andParentidEqualTo(0L)
											.andStatusEqualTo(1);
			List<IrpChannel> channelis=irpChannelDAO.selectByExample(channelExample);
			if(channelis!=null && channelis.size()>0){
				IrpChannel rootChannel=channelis.get(0);
				//查询他的所有一级分类
				IrpChannelExample channelExample2=new IrpChannelExample();
				channelExample2.createCriteria().andParentidEqualTo(rootChannel.getChannelid())
												.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
												.andStatusEqualTo(1).andMaptypeEqualTo(maptype);//发布状态下的栏目
				channelExample2.setOrderByClause(" chnlorder asc");
				list=irpChannelDAO.selectByExample(channelExample2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpChannel> findRightChannelsByMaptype(Long maptype,
			long _parentid, int _nStatus, int _nChnltype, long _nOperId, String orderBy) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			
			RightUtil rightUtil=new RightUtil(); 
			IrpUser irpUser=LoginUtil.getLoginUser();
			if(!irpUser.isAdministrator()){ 
				if(_nOperId>0L){
					String sSql=rightUtil.getRightExistsSQL(new IrpChannel(), _nOperId);//得到是否有权限语句
					criteria.extSQL(sSql); 
				}
			}
			
			criteria.andParentidEqualTo(_parentid)//栏目
					.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andStatusEqualTo(_nStatus)//是否删除状态
					.andChnltypeEqualTo(_nChnltype).andMaptypeEqualTo(maptype);
			
			if(orderBy!=null && orderBy.length()>0){
				channelExample.setOrderByClause(orderBy);
			}
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels;
	}
	
	@Override
	public IrpChannel finChannelByChannelidAndChalType(Long channelid,
			int channelTypeMap) {
		List<IrpChannel> channels=null;
		try {
			IrpChannelExample channelExample=new IrpChannelExample();
			Criteria criteria = channelExample.createCriteria();
			criteria.andPublishproEqualTo(IrpChannel.CHANNEL_IS_PUBLISH)
					.andChnltypeEqualTo(channelTypeMap).andChannelidEqualTo(channelid);
			
			channels=irpChannelDAO.selectByExample(channelExample);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return channels==null||channels.isEmpty()?null:channels.get(0);
	}
}
