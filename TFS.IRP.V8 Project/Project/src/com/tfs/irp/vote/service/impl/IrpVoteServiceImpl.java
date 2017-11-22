package com.tfs.irp.vote.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.vote.dao.IrpVoteDAO;
import com.tfs.irp.vote.entity.IrpVote;
import com.tfs.irp.vote.entity.IrpVoteExample;
import com.tfs.irp.vote.service.IrpVoteService;
import com.tfs.irp.voteoptions.dao.IrpVoteOptionsDAO;
import com.tfs.irp.voteoptions.entity.IrpVoteOptions;
import com.tfs.irp.voteoptions.entity.IrpVoteOptionsExample;
import com.tfs.irp.voterecords.dao.IrpVoteRecordsDAO;
import com.tfs.irp.voterecords.entity.IrpVoteRecords;
import com.tfs.irp.voterecords.entity.IrpVoteRecordsExample;

public class IrpVoteServiceImpl implements IrpVoteService {

	private IrpVoteDAO irpVoteDAO;

	private IrpVoteOptionsDAO irpVoteOptionsDAO;

	private IrpVoteRecordsDAO irpVoteRecordsDAO;

	public IrpVoteRecordsDAO getIrpVoteRecordsDAO() {
		return irpVoteRecordsDAO;
	}

	public void setIrpVoteRecordsDAO(IrpVoteRecordsDAO irpVoteRecordsDAO) {
		this.irpVoteRecordsDAO = irpVoteRecordsDAO;
	}

	public IrpVoteOptionsDAO getIrpVoteOptionsDAO() {
		return irpVoteOptionsDAO;
	}

	public void setIrpVoteOptionsDAO(IrpVoteOptionsDAO irpVoteOptionsDAO) {
		this.irpVoteOptionsDAO = irpVoteOptionsDAO;
	}

	public IrpVoteDAO getIrpVoteDAO() {
		return irpVoteDAO;
	}

	public void setIrpVoteDAO(IrpVoteDAO irpVoteDAO) {
		this.irpVoteDAO = irpVoteDAO;
	}

	@Override
	public List<IrpVote> findAllVote(PageUtil pageUtil, String _oOrderby) {
		IrpVoteExample example = new IrpVoteExample();
		example.createCriteria().andVoteparentidIsNull().andIspublishEqualTo(IrpVote.ISPUBLISH_YES);
		List<IrpVote> list = null;
		if (_oOrderby == null) {
			example.setOrderByClause("crtime desc");
		} else {
			example.setOrderByClause(_oOrderby);
		}
		list = irpVoteDAO.selectByExample(example, pageUtil);
		return list;
	}

	@Override
	public int findAllVotecount() {
		int count = 0;
		IrpVoteExample example = new IrpVoteExample();
		example.createCriteria().andVoteparentidIsNull().andIspublishEqualTo(IrpVote.ISPUBLISH_YES);
		try {
			count = irpVoteDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Long addVoteandOption(IrpVote irptitle, IrpVote irpvotetitle,
			String options,String urloption,Long wordorurl) {
		Long nTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
		try {
			irptitle.setVoteid(nTempId);
			irptitle.setCruserid(LoginUtil.getLoginUserId());
			irptitle.setCrtime(new Date());
			irptitle.setCount(0L);
			if(wordorurl==3){
				irptitle.setVotetype(IrpVote.VOTETYPE_URL);
			}else{
				irptitle.setVotetype(IrpVote.VOTETYPE_WORD);
			}

			// 添加标题
			irpVoteDAO.insertSelective(irptitle);
			Long lTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
			irpvotetitle.setVoteid(lTempId);
			irpvotetitle.setVoteparentid(nTempId);
			irpvotetitle.setCount(0L);
			if(options!=null&&options.length()>0){
				// 添加分组
				irpVoteDAO.insertSelective(irpvotetitle);
				String[] optionarr = options.split(",");
				String[] urlarr = urloption.split(",");
				for (int i = 0; i < optionarr.length; i++) {
					Long loptionid = TableIdUtil
							.getNextId(IrpVoteOptions.TABLE_NAME);
					// 添加option
					IrpVoteOptions Options = new IrpVoteOptions();
					Options.setOptionid(loptionid);
					Options.setParentid(lTempId);
					Options.setVoteoption(optionarr[i]);
					if(urlarr[i]!=null && urlarr[i].length()>0 && !urlarr[i].equals("null")){
						Options.setOptionurl(urlarr[i]);
					}
					Options.setCount(0L);
					irpVoteOptionsDAO.insertSelective(Options);
				}
			}else{
				throw new RuntimeException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nTempId;
	}

	@Override
	public void addTitleandOption(IrpVote irpvotetitle, String options,String urloptions,Long vid) {
		try {
			Long lTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
			irpvotetitle.setVoteid(lTempId);
			irpvotetitle.setVoteparentid(vid);
			irpvotetitle.setCount(0L);
			// 添加分组
			irpVoteDAO.insertSelective(irpvotetitle);
			String[] optionarr = options.split(",");
			String[] urlarr = urloptions.split(",");
			for (int i = 0; i < optionarr.length; i++) {
				Long loptionid = TableIdUtil
						.getNextId(IrpVoteOptions.TABLE_NAME);
				// 添加option
				IrpVoteOptions Options = new IrpVoteOptions();
				Options.setOptionid(loptionid);
				Options.setParentid(lTempId);
				Options.setVoteoption(optionarr[i]);
				if(urlarr[i]!=null && urlarr[i].length()>0 && !urlarr[i].equals("null")){
					Options.setOptionurl(urlarr[i]);
				}
				Options.setCount(0L);
				irpVoteOptionsDAO.insertSelective(Options);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long addPicVoteandOption(IrpVote irptitle, IrpVote irpvotetitle,
			String options,String urloption, String attlist) {
		Long nTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
		try {
			irptitle.setVoteid(nTempId);
			irptitle.setCruserid(LoginUtil.getLoginUserId());
			irptitle.setCrtime(new Date());
			irptitle.setCount(0L);
			irptitle.setVotetype(IrpVote.VOTETYPE_PIC);
			// 添加标题
			irpVoteDAO.insertSelective(irptitle);
			Long lTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
			irpvotetitle.setVoteid(lTempId);
			irpvotetitle.setVoteparentid(nTempId);
			irpvotetitle.setCount(0L);
			// 添加分组
			irpVoteDAO.insertSelective(irpvotetitle);
			String[] optionarr = options.split(",");
			String[] attrimgarr = attlist.split(",");
			String[] urlarr = urloption.split(",");
			for (int i = 0; i < optionarr.length; i++) {
				Long loptionid = TableIdUtil.getNextId(IrpVoteOptions.TABLE_NAME);
				// 添加option
				IrpVoteOptions Options = new IrpVoteOptions();
				Options.setOptionid(loptionid);
				Options.setParentid(lTempId);
				//
				String filePath=SysFileUtil.getFilePathByFileName(attrimgarr[i]); 
			    File newFile=new File(filePath);  
				String newFileName=SysFileUtil.saveFile(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC);
				Options.setAttimg(newFileName);
				Options.setVoteoption(optionarr[i]);
				if(urlarr[i]!=null && urlarr[i].length()>0 && !urlarr[i].equals("null")){
					Options.setOptionurl(urlarr[i]);
				}
				Options.setCount(0L);
				irpVoteOptionsDAO.insertSelective(Options);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nTempId;
	}

	@Override
	public IrpVote findbyvoteid(Long voteid) {
		IrpVote irpVote = null;
		try {
			irpVote = irpVoteDAO.selectByPrimaryKey(voteid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpVote;
	}

	@Override
	public List<?> findquestionandoption(Map<String, Object> map) {
		return irpVoteDAO.findquestionandoption(map);
	}

	@Override
	public List<IrpVote> findVotetitleBypid(Long pid) {
		List<IrpVote> list = null;
		IrpVoteExample example = new IrpVoteExample();
		example.createCriteria().andVoteparentidEqualTo(pid);
		example.setOrderByClause("voteid");
		try {
			list = irpVoteDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addVoterecords(Long voteid, String optionids) {
		String[] optionarr = optionids.split(",");
		for (int i = 0; i < optionarr.length; i++) {
			Long reid = TableIdUtil.getNextId(IrpVoteRecords.TABLE_NAME);
			IrpVoteRecords record = new IrpVoteRecords();
			record.setVoterecordid(reid);
			record.setCruserid(LoginUtil.getLoginUserId());
			record.setVoteid(voteid);
			record.setOptionid(Long.parseLong(optionarr[i]));
			try {
				irpVoteRecordsDAO.insert(record);
				IrpVoteOptions ivtitle = irpVoteOptionsDAO
						.selectByPrimaryKey(Long.parseLong(optionarr[i]));
				if (ivtitle.getCount() == null) {
					ivtitle.setCount(1L);
				} else {
					ivtitle.setCount((ivtitle.getCount() + 1));
				}
				irpVoteOptionsDAO.updateByPrimaryKey(ivtitle);
				IrpVoteExample example = new IrpVoteExample();
				example.createCriteria().andVoteidEqualTo(ivtitle.getParentid());
				List<IrpVote> ivlist = irpVoteDAO.selectByExample(example);
				for (IrpVote ivt : ivlist) {
					if (ivt.getCount() == null) {
						ivt.setCount(1L);
					} else {
						ivt.setCount((ivt.getCount() + 1));
					}
					irpVoteDAO.updateByPrimaryKey(ivt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			// find
			IrpVote iv = irpVoteDAO.selectByPrimaryKey(voteid);
			// update
			if (iv.getCount() == null) {
				iv.setCount(1L);
			} else {
				iv.setCount((iv.getCount() + 1));
			}
			irpVoteDAO.updateByPrimaryKey(iv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isuservote(Long voteid) {
		boolean fag = false;
		IrpVoteRecordsExample example = new IrpVoteRecordsExample();
		example.createCriteria().andVoteidEqualTo(voteid)
				.andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			List<IrpVoteRecords> list = irpVoteRecordsDAO
					.selectByExample(example);
			if (list.size() > 0) {
				fag = false;
			} else {
				fag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fag;
	}

	@Override
	public int findAllMyvotecount() {
		int count = 0;
		IrpVoteExample example = new IrpVoteExample();
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId()).andIspublishNotEqualTo(IrpVote.ISPUBLISH_DEL);
		try {
			count = irpVoteDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<IrpVote> findAllMyvote(PageUtil pageUtil, String _oOrderby) {
		List<IrpVote> list = null;
		IrpVoteExample example = new IrpVoteExample();
		example.createCriteria().andCruseridEqualTo(LoginUtil.getLoginUserId()).andIspublishNotEqualTo(IrpVote.ISPUBLISH_DEL);
		if (_oOrderby == null) {
			example.setOrderByClause("crtime desc");
		} else {
			example.setOrderByClause(_oOrderby);
		}
		list = irpVoteDAO.selectByExample(example, pageUtil);
		return list;
	}

	@Override
	public List<IrpVote> findPartmevote(Map<String, Object> map,PageUtil pageUtil) {
		List<IrpVote> list = irpVoteDAO.selectByCruserid(map, pageUtil);
		return list;
	}

	@Override
	public int findPartmevotecount(Map<String, Object> map) {
		int count=irpVoteDAO.selectByCruseridcount(map);
		return count;
	}

	public void updatevotePublish(Long voteid, int ispub) {
		IrpVote record=new IrpVote();
		record.setVoteid(voteid);
		record.setIspublish(ispub);
		try {
			irpVoteDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void votetitleUpdate(IrpVote iv) {
		try {
			irpVoteDAO.updateByPrimaryKeySelective(iv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void voteandoptionsUpdate(IrpVote iv ,String jsonString) {
		try {
			irpVoteDAO.updateByPrimaryKeySelective(iv);
			JSONArray jsonarray = JSONArray.fromObject(jsonString);
			for (int i = 0; i < jsonarray.size(); i++) {  
				JSONObject temp = (JSONObject) jsonarray.get(i);  
			    String id = temp.getString("id");  
			    String value = temp.getString("value"); 
			    String urlvalue = temp.getString("urlvalue");
			    String picnewname="";
			    if(temp.containsKey("picname")){
			    	String picname = temp.getString("picname"); 
		    	    //判断图片是否存在   
		    	    String filePath=SysFileUtil.getFilePathByFileName(picname); 
			    	boolean fag=  picname.substring(0, 2).equals(SysFileUtil.FILE_TYPE_TEMP_FILE);
			    	if(fag){
			    		 File newFile=new File(filePath);  
			    		 String newFileName=SysFileUtil.saveFile(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC);
			    		 picnewname=newFileName;
			    		
			    	}else{
			    		picnewname=picname;
			    	}
			    }
			    IrpVoteOptions ivo=new IrpVoteOptions();
			    ivo.setOptionid(Long.parseLong(id));
			    ivo.setVoteoption(value.trim());
			    if(picnewname.trim().length()>0||picnewname.trim()!=null){
			    	ivo.setAttimg(picnewname);
			    }
			    if(urlvalue.trim().length()>0&&!urlvalue.trim().equals("null")){
			    	ivo.setOptionurl(urlvalue.trim());
			    }else{
			    	ivo.setOptionurl("");
			    }
			    irpVoteOptionsDAO.updateByPrimaryKeySelective(ivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void voteoptionDel(Long optionsid) {
		try {
			irpVoteOptionsDAO.deleteByPrimaryKey(optionsid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void optionAdd(String optiostr,String urloption,String jsonoptionimg,Long pid) {
		IrpVoteOptions record=new IrpVoteOptions();
		Long opid = TableIdUtil.getNextId(IrpVoteOptions.TABLE_NAME);
		record.setOptionid(opid);
		record.setVoteoption(optiostr.trim());
		if(urloption.trim().length()>0&&!urloption.trim().equals("null")){
			record.setOptionurl(urloption.trim());
		}
		if(jsonoptionimg!=null&&jsonoptionimg.length()>0){
			String filePath=SysFileUtil.getFilePathByFileName(jsonoptionimg.trim()); 
		    File newFile=new File(filePath);  
			String newFileName=SysFileUtil.saveFile(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC);
			record.setAttimg(newFileName);
		}
		record.setParentid(pid);
		record.setCount(0L);
		try {
			irpVoteOptionsDAO.insertSelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void voteTitleDel(Long voteid) {
		try {
			//删除分组
			irpVoteDAO.deleteByPrimaryKey(voteid);
			//删除选项
			IrpVoteOptionsExample example=new IrpVoteOptionsExample();
			example.createCriteria().andParentidEqualTo(voteid);
			irpVoteOptionsDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void voteDel(Long voteid) {
		try {
			//删除标题
			irpVoteDAO.deleteByPrimaryKey(voteid);
			IrpVoteExample ive=new IrpVoteExample();
			ive.createCriteria().andVoteparentidEqualTo(voteid);
			List<IrpVote> list=  irpVoteDAO.selectByExample(ive);
			for(IrpVote iv:list){
				irpVoteDAO.deleteByPrimaryKey(iv.getVoteid());
				//删除选项
				IrpVoteOptionsExample example=new IrpVoteOptionsExample();
				example.createCriteria().andParentidEqualTo(iv.getVoteid());
				irpVoteOptionsDAO.deleteByExample(example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPicVotesecond(Long pid,IrpVote irpvotetitle, String options,String urloption,
			String imgs) {
		try {
			Long lTempId = TableIdUtil.getNextId(IrpVote.TABLE_NAME);
			irpvotetitle.setVoteid(lTempId);
			irpvotetitle.setVoteparentid(pid);
			irpvotetitle.setCount(0L);
			// 添加分组
			irpVoteDAO.insertSelective(irpvotetitle);
			String[] optionarr = options.split(",");
			String[] attrimgarr = imgs.split(",");
			String[] urlarr = urloption.split(",");
			for (int i = 0; i < optionarr.length; i++) {
				Long loptionid = TableIdUtil
						.getNextId(IrpVoteOptions.TABLE_NAME);
				// 添加option
				IrpVoteOptions Options = new IrpVoteOptions();
				Options.setOptionid(loptionid);
				Options.setParentid(lTempId);
				String filePath=SysFileUtil.getFilePathByFileName(attrimgarr[i]); 
			    File newFile=new File(filePath);  
				String newFileName=SysFileUtil.saveFile(newFile, SysFileUtil.FILE_TYPE_ATTACHED_PIC);
				Options.setAttimg(newFileName);
				Options.setVoteoption(optionarr[i].trim());
				if(urlarr[i]!=null && urlarr[i].length()>0 && !urlarr[i].equals("null")){
					Options.setOptionurl(urlarr[i]);
				}
				Options.setCount(0L);
				irpVoteOptionsDAO.insertSelective(Options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IrpVote findVotebyid(Long voteid) {
		IrpVote irpvote=new IrpVote();
		try {
			irpvote=irpVoteDAO.selectByPrimaryKey(voteid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpvote;
	}

	@Override
	public List<IrpVote> findVotehot() {
		List<IrpVote> list=null;
		IrpVoteExample example=new IrpVoteExample();
		
		example.createCriteria().andResultshowEqualTo((short)2).andIspublishEqualTo(IrpVote.ISPUBLISH_YES).andEndtimeGreaterThan(new Date());
		example.setOrderByClause("count desc");
		try {
			list=irpVoteDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long isuservoteid(Long voteid) {
		Long fag = null;
		IrpVoteRecordsExample example = new IrpVoteRecordsExample();
		example.createCriteria().andVoteidEqualTo(voteid)
				.andCruseridEqualTo(LoginUtil.getLoginUserId());
		try {
			List<IrpVoteRecords> list = irpVoteRecordsDAO
					.selectByExample(example);
			if (list.size() > 0) {
				fag = list.get(0).getOptionid();
			} else {
				fag = 0L;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fag;
	}
	
}
