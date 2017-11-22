package com.tfs.irp.bug_config.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.bug.entity.IrpBugExample;
import com.tfs.irp.bug.service.BugService;
import com.tfs.irp.bug_config.entity.IrpBugConfig;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample;
import com.tfs.irp.bug_config.entity.IrpBugConfigExample.Criteria;
import com.tfs.irp.bug_config.service.BugConfigService;
import com.tfs.irp.util.TableIdUtil;

public class BugConfigAction extends ActionSupport {

	
	private BugConfigService bugconfigservice;
	
	private BugService bugservice;
	
	private Long bugconfigid;//主键
	
	private Long proid;//项目Id
	
	private String versioncomment;//版本内容
	
	private String modulcomment;//模块内容
	
	private String modulname;//模块名
	
	private String versionname;//版本名
	
	private Long configtype;//类型   0:版本        1:模块
	
	private Long projectId;
	
	private List<IrpBugConfig> bugversions;
	
	private List<IrpBugConfig> bugmoduls;
	
	private Map<Long ,Integer> versionHaveBug = new HashMap<Long, Integer>(); 
	
	private Map<Long ,Integer> modulHaveBug = new HashMap<Long, Integer>(); 
	
	/**
	 * 添加版本
	 * @return
	 */
	public String addVersion(){
		try {
			IrpBugConfig bc=new IrpBugConfig();
			bc.setBugconfigid(TableIdUtil.getNextId("irp_bug_config"));
			bc.setConfigtype(IrpBugConfig.TYPE_VERSION);
			try {
				versionname=new  String(versionname.getBytes("utf-8"), "utf-8");
				//versionname=URLDecoder.decode(versionname,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bc.setVersionname(versionname);
			bc.setVersioncomment(versioncomment);
			bc.setProid(proid);
			bugconfigservice.insert(bc);
			findVersionByProid();
			findModulByProid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加模块
	 * @return
	 */
	public String addModul(){
		try {
			IrpBugConfig bc=new IrpBugConfig();
			bc.setBugconfigid(TableIdUtil.getNextId("irp_bug_config"));
			bc.setConfigtype(IrpBugConfig.TYPE_MODUL);
			bc.setModulcomment(modulcomment);
			try {
				modulname=new  String(modulname.getBytes("utf-8"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bc.setModulname(modulname);
			bc.setProid(proid);
			bugconfigservice.insert(bc);
			findVersionByProid();
			findModulByProid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 显示版本/模块页面
	 * @return
	 */
	public String projectallbugconfig() {
		try {
			findVersionByProid();
			findModulByProid();
			findBugcountByVersion();
			findBugcountByModul();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询项目下所有版本
	 */
	private void findVersionByProid(){
		try {
			IrpBugConfigExample example=new IrpBugConfigExample();
			Criteria criteria=example.createCriteria();
			criteria.andProidEqualTo(this.projectId);
			criteria.andConfigtypeEqualTo(IrpBugConfig.TYPE_VERSION);
			example.setOrderByClause(" BUGCONFIGID");
			this.bugversions=bugconfigservice.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询项目下所有模块
	 */
	private void findModulByProid(){
		try {
			IrpBugConfigExample example=new IrpBugConfigExample();
			Criteria criteria=example.createCriteria();
			criteria.andProidEqualTo(projectId);
			criteria.andConfigtypeEqualTo(IrpBugConfig.TYPE_MODUL);
			example.setOrderByClause(" BUGCONFIGID");
			this.bugmoduls=bugconfigservice.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除版本
	 * @return
	 */
	public String delVersion(){
		try {
			this.bugconfigservice.deleteByPrimaryKey(bugconfigid);
			projectallbugconfig();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除模块
	 * @return
	 */
	public String delModul(){
		try {
			this.bugconfigservice.deleteByPrimaryKey(bugconfigid);
			projectallbugconfig();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑保存版本
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String editSaveVersion() throws UnsupportedEncodingException{
		versionname = URLDecoder.decode(versionname, "utf-8");
		try {
			IrpBugConfig bc=new IrpBugConfig();
			bc.setBugconfigid(bugconfigid);
			bc.setVersionname(versionname);
			this.bugconfigservice.updateByPrimaryKeySelective(bc);
			this.findModulByProid();
			this.findVersionByProid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑保存模块
	 * @return
	 */
	public String editSaveModul(){
		try {
			IrpBugConfig bc=new IrpBugConfig();
			bc.setBugconfigid(bugconfigid);
			bc.setModulname(modulname);
			this.bugconfigservice.updateByPrimaryKeySelective(bc);
			this.findModulByProid();
			this.findVersionByProid();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询项目下每个版本下是否有Bug信息
	 * @throws Exception
	 */
	private void findBugcountByVersion() throws Exception{
		IrpBugConfigExample example=new IrpBugConfigExample();
		Criteria criteria=example.createCriteria();
		criteria.andProidEqualTo(projectId);
		criteria.andConfigtypeEqualTo(IrpBugConfig.TYPE_VERSION);
		List<IrpBugConfig> bclist=bugconfigservice.selectByExample(example);
		if(bclist.size()>0){
			for(IrpBugConfig bc:bclist){
				IrpBugExample bugexample=new IrpBugExample();
				com.tfs.irp.bug.entity.IrpBugExample.Criteria bugcriteria=bugexample.createCriteria();
				bugcriteria.andVersionidEqualTo(bc.getBugconfigid());
				if(!bugservice.countByExample(bugexample).equals(0)){
					this.versionHaveBug.put(bc.getBugconfigid(), 1);
				}else{
					this.versionHaveBug.put(bc.getBugconfigid(), 0);
				}
			}
		}
	}
	
	/**
	 * 查询项目下每个模块下是否有Bug信息
	 * @throws Exception
	 */
	private void findBugcountByModul() throws Exception{
		IrpBugConfigExample example=new IrpBugConfigExample();
		Criteria criteria=example.createCriteria();
		criteria.andProidEqualTo(projectId);
		criteria.andConfigtypeEqualTo(IrpBugConfig.TYPE_MODUL);
		List<IrpBugConfig> bclist=bugconfigservice.selectByExample(example);
		if(bclist.size()>0){
			for(IrpBugConfig bc:bclist){
				IrpBugExample bugexample=new IrpBugExample();
				com.tfs.irp.bug.entity.IrpBugExample.Criteria bugcriteria=bugexample.createCriteria();
				bugcriteria.andModuleidEqualTo(bc.getBugconfigid());
				if(!bugservice.countByExample(bugexample).equals(0)){
					this.modulHaveBug.put(bc.getBugconfigid(), 1);
				}else{
					this.modulHaveBug.put(bc.getBugconfigid(), 0);
				}
			}
		}
	}
	
	

	public BugConfigService getBugconfigservice() {
		return bugconfigservice;
	}

	public void setBugconfigservice(BugConfigService bugconfigservice) {
		this.bugconfigservice = bugconfigservice;
	}

	public Long getBugconfigid() {
		return bugconfigid;
	}

	public void setBugconfigid(Long bugconfigid) {
		this.bugconfigid = bugconfigid;
	}

	public Long getProid() {
		return proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}

	public String getVersioncomment() {
		return versioncomment;
	}

	public void setVersioncomment(String versioncomment) {
		this.versioncomment = versioncomment;
	}

	public String getModulcomment() {
		return modulcomment;
	}

	public void setModulcomment(String modulcomment) {
		this.modulcomment = modulcomment;
	}

	public String getModulname() {
		return modulname;
	}

	public void setModulname(String modulname) {
		this.modulname = modulname;
	}

	public String getVersionname() {
		return versionname;
	}

	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}

	public Long getConfigtype() {
		return configtype;
	}

	public void setConfigtype(Long configtype) {
		this.configtype = configtype;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<IrpBugConfig> getBugversions() {
		return bugversions;
	}

	public void setBugversions(List<IrpBugConfig> bugversions) {
		this.bugversions = bugversions;
	}

	public List<IrpBugConfig> getBugmoduls() {
		return bugmoduls;
	}

	public void setBugmoduls(List<IrpBugConfig> bugmoduls) {
		this.bugmoduls = bugmoduls;
	}

	public Map<Long, Integer> getVersionHaveBug() {
		return versionHaveBug;
	}

	public void setVersionHaveBug(Map<Long, Integer> versionHaveBug) {
		this.versionHaveBug = versionHaveBug;
	}

	public Map<Long, Integer> getModulHaveBug() {
		return modulHaveBug;
	}

	public void setModulHaveBug(Map<Long, Integer> modulHaveBug) {
		this.modulHaveBug = modulHaveBug;
	}

	public BugService getBugservice() {
		return bugservice;
	}

	public void setBugservice(BugService bugservice) {
		this.bugservice = bugservice;
	}


	
}
