package com.tfs.irp.microblog.dao.impl;

import com.tfs.irp.microblog.dao.IrpMicroblogDAO;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogExample;
import com.tfs.irp.microblog.entity.IrpMicroblogInform;
import com.tfs.irp.microblog.entity.IrpMicroblogView;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpMicroblogDAOImpl  extends SqlMapClientDaoSupport implements IrpMicroblogDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int countByExample(IrpMicroblogExample example) throws SQLException {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int deleteByExample(IrpMicroblogExample example) throws SQLException {
        int rows = getSqlMapClientTemplate().delete("IRP_MICROBLOG.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int deleteByPrimaryKey(Long microblogid) throws SQLException {
        IrpMicroblog key = new IrpMicroblog();
        key.setMicroblogid(microblogid);
        int rows = getSqlMapClientTemplate().delete("IRP_MICROBLOG.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public void insert(IrpMicroblog record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_MICROBLOG.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public void insertSelective(IrpMicroblog record) throws SQLException {
        getSqlMapClientTemplate().insert("IRP_MICROBLOG.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpMicroblog> selectByExampleWithBLOBs(IrpMicroblogExample example) throws SQLException {
        List<IrpMicroblog> list = getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<IrpMicroblog> selectByExampleWithoutBLOBs(IrpMicroblogExample example) throws SQLException {
        List<IrpMicroblog> list = getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public IrpMicroblog selectByPrimaryKey(Long microblogid) throws SQLException {
        IrpMicroblog key = new IrpMicroblog();
        key.setMicroblogid(microblogid);
        IrpMicroblog record = (IrpMicroblog) getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByExampleSelective(IrpMicroblog record, IrpMicroblogExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByExampleWithBLOBs(IrpMicroblog record, IrpMicroblogExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByExampleWithBLOBs", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByExampleWithoutBLOBs(IrpMicroblog record, IrpMicroblogExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByPrimaryKeySelective(IrpMicroblog record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByPrimaryKeyWithBLOBs(IrpMicroblog record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    public int updateByPrimaryKeyWithoutBLOBs(IrpMicroblog record) throws SQLException {
        int rows = getSqlMapClientTemplate().update("IRP_MICROBLOG.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table IRP_MICROBLOG
     *
     * @ibatorgenerated Sat Nov 15 11:31:18 CST 2014
     */
    private static class UpdateByExampleParms extends IrpMicroblogExample {
        private Object record;

        public UpdateByExampleParms(Object record, IrpMicroblogExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }



@Override
	public List findMicroblogOfFocus( long isdelid, long fuserid,PageUtil pageUtil,long _loginid)
			throws Exception {
		// TODO Auto-generated method stub
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("fuserid", fuserid);
	     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocus",map,pageUtil.getPageIndex(),pageUtil.getPageSize());
	     //处理隐私以及公开
	     List list_01 = new ArrayList();
	     Iterator iterator = list.iterator();
	     while(iterator.hasNext()){
	    	 IrpMicroblogView map_01 = (IrpMicroblogView) iterator.next();
	    	if(Long.parseLong(map_01.getUSERID().toString())!=_loginid){
	    		if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.PUBLICMICROBLOG){
	    			list_01.add(map_01);
	    			
	    		}else if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.GROUPMICROBLOG){
	    	  	boolean flag  =	this.findMicroblogOfGroupId(Long.parseLong(map_01.getUSERID().toString()), Long.parseLong(map_01.getGROUPID().toString()));
		    		if (flag==true) {
		    			list_01.add(map_01);
					}
	    		}
	    	}else{
	    		if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.PINGMICROBLOG||Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.TJNGMICROBLOG){
	    		}else{
	    			list_01.add(map_01);
	    		}
	    	}
	     } 
		return list_01;
	}
	@Override
	public List findMicroblogOfAll( long isdelid,PageUtil pageUtil,long _loginid)
			throws Exception {
		// TODO Auto-generated method stub	
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("misdel", isdelid);
	     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusManager",map,pageUtil.getPageIndex(),pageUtil.getPageSize());
	     //处理隐私以及公开
	     List list_01 = new ArrayList();
	     Iterator iterator = list.iterator();
	     while(iterator.hasNext()){
	    	 IrpMicroblogView map_01 = (IrpMicroblogView) iterator.next();
	    	if(Long.parseLong(map_01.getUSERID().toString())!=_loginid){
	    		if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.PUBLICMICROBLOG){
	    			list_01.add(map_01);
	    		}else if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.GROUPMICROBLOG){
	    			//此微知为分组微知   则list_01.add() 只加组内的并且关注我的人
	    		boolean flag  =	this.findMicroblogOfGroupId(Long.parseLong(map_01.getUSERID().toString()), Long.parseLong(map_01.getGROUPID().toString()));
		    		if (flag==true) {
		    			list_01.add(map_01);
					}
	    		}
	    	}else{
	    		list_01.add(map_01);
	    	}
	     } 
		return list_01;
	}
	@Override
	public List findMicroblogOfFocus(long isdelid, long fuserid,
			long _microblogid) throws Exception {
		    List<IrpMicroblogView> list = null;
			Map map = new HashMap();
			map.put("misdel", isdelid);
			map.put("microblogid", _microblogid);
			map.put("fuserid", fuserid);
		     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusByMicroblogid",map);
		     
			return list;
	}
	@Override
	public List findMicroblogOfUseridInCard(long _userid,Integer _isdel) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("userid", _userid);
		map.put("isdel",_isdel);
		list.add(this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistforUseridCard", map).get(0));
		return list;
	}
	@Override
	public List findMicroblogOfUserid(long _userid,Integer _isdel) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("userid", _userid);
		map.put("isdel",_isdel);
		list=this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistforUserid", map);
		return list;
	}

	@Override
	public int findMicroblogOfFocusCount(long isdelid, long fuserid) throws SQLException {
		Integer microblogCount= 0;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("fuserid",fuserid);
		microblogCount =this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocus",map).size(); 
	               
		return microblogCount;
	}
	@Override
	public int findMicroblogOfAllCount(long isdelid) throws SQLException {
		Integer microblogCount= 0;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		microblogCount =this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusManager",map).size(); 
	               
		return microblogCount;
	}
	

	@Override
	public List<IrpMicroblog> selectByExample(IrpMicroblogExample example,
			PageUtil pageUtil) throws Exception {
		List<IrpMicroblog> list = getSqlMapClientTemplate().queryForList(
				"IRP_MICROBLOG.ibatorgenerated_selectByExampleWithBLOBs", example,pageUtil.getPageIndex(),pageUtil.getPageSize());
		return list;
		
	}

	@Override
	public List findLoginUserInfo(long _docstatus, long _isdel, long _userid)
			throws SQLException {
		// TODO Auto-generated method stub
	
		
		List list = new ArrayList();
		
		Map map = new HashMap();
		map.put("docstatus", _docstatus);
		map.put("isdel", _isdel);
		map.put("userid", _userid);
		list=this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_loginUserInfo",map);
		
		return list;
	}

	@Override
	public List findMicroblogOfFocusCollect(long isdelid, PageUtil pageUtil,
			long _loginid) throws Exception {
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("userid",_loginid);
	     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusCollect",map,pageUtil.getPageIndex(),pageUtil.getPageSize());
		return list;
	}
	@Override
	public int findMicroblogOfFocusCollectCount(long isdelid,
			long _loginid) throws SQLException {
		int collectcount = 0;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("userid",_loginid);
		collectcount =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusCollect",map).size();  
		return collectcount;
	}
	@Override
	public int findMicroblogOfFocusCollectCount(long _loginid) throws SQLException {
		int collectcount = 0;
		Map map = new HashMap();
		map.put("userid",_loginid);
		collectcount =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusnotuseridCollect",map).size();  
		return collectcount;
	}

	@Override
	public List findMicroblogOfFocusCollect(PageUtil pageUtil,long _loginid) throws Exception {
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("userid",_loginid);
	     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistoffocusnotuseridCollect",map,pageUtil.getPageIndex(),pageUtil.getPageSize());
		return list;
	}
	@Override
	public List findDefaultRecommend(long _userid) throws SQLException {
		List RecommendList = new ArrayList();
		Map map = new HashMap();
		map.put("userid",_userid);
		map.put("status",IrpUser.USER_STATUS_REG);
		map.put("notuserid","not in("+SysConfigUtil.getSysConfigValue(IrpMicroblog.SYSTEMRECOMMENDNOTUSER)+")");
	  RecommendList = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_defaultRecommendUser", map,IrpMicroblog.IRPMICROBLOGRECOMMENDFIRST ,IrpMicroblog.IRPMICROBLOGRECOMMENDEND);
		return RecommendList;
	}
	@Override
	public List findDefaultRecommendMicrNum(long _userid) throws SQLException {
		List RecommendList = new ArrayList();
		Map map = new HashMap();
		map.put("userid",_userid);
		map.put("status",IrpUser.USER_STATUS_REG);
		map.put("notuserid","not in("+SysConfigUtil.getSysConfigValue(IrpMicroblog.SYSTEMRECOMMENDNOTUSER)+")");
	  RecommendList = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_defaultRecommendUserOfMicNum", map,IrpMicroblog.IRPMICROBLOGRECOMMENDFIRST ,IrpMicroblog.IRPMICROBLOGRECOMMENDEND);
		return RecommendList;
	}

	@Override
	public List findDefaultRecommendUserCrtime(long _userid)
			throws SQLException {
		List RecommendList = new ArrayList();
		Map map = new HashMap();
		map.put("userid",_userid);
		map.put("status",IrpUser.USER_STATUS_REG);
		map.put("notuserid","not in("+SysConfigUtil.getSysConfigValue(IrpMicroblog.SYSTEMRECOMMENDNOTUSER)+")");
	  RecommendList = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_defaultRecommendUserOfUserCrtime", map,IrpMicroblog.IRPMICROBLOGRECOMMENDFIRST ,IrpMicroblog.IRPMICROBLOGRECOMMENDEND);
		return RecommendList;
	}
	@Override
	public Integer searchMicrCountOfUserid(long _userid) throws SQLException {
		int micrcount = 0;
		Map map = new HashMap();
		map.put("userid", _userid);
		micrcount =Integer.parseInt(this.getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_searchMicrCountOfUserid", map).toString());
		return micrcount;
	}

	@Override
	public Integer searchFocusCountOfUserid(long _userid) throws SQLException {
		int focuscount = 0;
		Map map = new HashMap();
		map.put("userid", _userid);
		focuscount =Integer.parseInt(this.getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_searchFocusCountOfUserid", map).toString());
		return focuscount;
	}

	@Override
	public Integer searchFusCountOfUserid(long _userid) throws SQLException {
		int fuscount = 0;
		Map map = new HashMap();
		map.put("focususerid", _userid);
		fuscount =Integer.parseInt(this.getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_searchFusCountOfUserid", map).toString());
		return fuscount;
	}

	@Override
	public List getAllTopicValue(Integer _isdel,Integer _blogtype) {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("isdel", _isdel);
		map.put("blogtype", _blogtype);
		list = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_searchAllTopic",map);
		return list;
	}

	@Override
	public int findMicroblogOfInformCount(long isdelid,Integer informtype,Integer informstatus,String _infromkey) throws SQLException {
		int num = 0;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("informtype", informtype);
		map.put("informstatus", informstatus);
		if(_infromkey!=null && _infromkey.equals("all")==false){
			map.put("infromkeys","and i.informkey = '"+_infromkey+"'");	
		}
		num =(Integer) this.getSqlMapClientTemplate().queryForObject("IRP_MICROBLOG.ibatorgenerated_countmicrobloglistofinform", map);
		return num;
	}

	@Override
	public List findMicroblogOfInform(long isdelid,Integer informtype,Integer informstatus,PageUtil pageUtil,String _infromkey) throws Exception {
		List<IrpMicroblogInform> list = null;
		Map map = new HashMap();
		map.put("misdel", isdelid);
		map.put("informtype", informtype);
		map.put("informstatus", informstatus);
		if(_infromkey!=null && _infromkey.equals("all")==false){
			map.put("infromkeys","and i.informkey = '"+_infromkey+"'");	
		}
		list = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloglistofinform", map, pageUtil.getPageIndex(),pageUtil.getPageSize());
		return list;
	}

	@Override
	public boolean findMicroblogOfGroupId(Long _userid,Long _groupid)
			throws Exception {
		boolean flag = false;
		List list = new ArrayList();
		Long userid = LoginUtil.getLoginUserId();
		Map map = new HashMap();
		map.put("userid",_userid);
		map.put("groupid",_groupid);
		list = this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_microbloguserofbygroupid",map);
  		Iterator iteratorgroup = list.iterator();
		while (iteratorgroup.hasNext()) {
			Map mapgroup = (Map) iteratorgroup.next();
			if(mapgroup.get("FOCUSUSERID").toString().equals(userid.toString())){
				flag = true;
				break;
			}else{
				flag = false;
			}
		}
		return flag;
	}
	
	public List<IrpMicroblog> selectByExampleWithBLOBsbypage(
			IrpMicroblogExample example,int start,int end){
		List<IrpMicroblog> list=null;
		if(start>=0){
			list = getSqlMapClientTemplate().queryForList(
					"IRP_MICROBLOG.ibatorgenerated_selectByExampleWithBLOBs",
					example,start,end);
		}else{
			list = getSqlMapClientTemplate().queryForList(
					"IRP_MICROBLOG.ibatorgenerated_selectByExampleWithBLOBs",
					example);
		}
		
		return list;
	}

	@Override
	public List searchMicroblog(String _searchWord, Integer _isDel,PageUtil pageUtil,long _loginid) throws Exception{
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("misdel", _isDel);
		map.put("searchword",_searchWord);
	     list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_searchmicroblog",map,pageUtil.getPageIndex(),pageUtil.getPageSize());
	     //处理隐私以及公开
	     List list_01 = new ArrayList();
	     Iterator iterator = list.iterator();
	     while(iterator.hasNext()){
	    	 IrpMicroblogView map_01 = (IrpMicroblogView) iterator.next();
	    	if(Long.parseLong(map_01.getUSERID().toString())!=_loginid){
	    		if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.PUBLICMICROBLOG){
	    			list_01.add(map_01);
	    		}else if(Integer.parseInt(map_01.getBLOGTYPE().toString())==IrpMicroblog.GROUPMICROBLOG){
	    			//此微知为分组微知   则list_01.add() 只加组内的并且关注我的人
	    		boolean flag  =	this.findMicroblogOfGroupId(Long.parseLong(map_01.getUSERID().toString()), Long.parseLong(map_01.getGROUPID().toString()));
		    		if (flag==true) {
		    			list_01.add(map_01);
					}
	    		}
	    	}else{
	    		list_01.add(map_01);
	    	}
	     } 
		return list_01;
	}
	
	public int searchMicroblogcount(String _searchWord, Integer _isDel) throws Exception{
		List<IrpMicroblogView> list = null;
		Map map = new HashMap();
		map.put("misdel", _isDel);
		map.put("searchword",_searchWord);
        list =	this.getSqlMapClientTemplate().queryForList("IRP_MICROBLOG.ibatorgenerated_searchmicroblog",map);
        return list.size();
	}







}