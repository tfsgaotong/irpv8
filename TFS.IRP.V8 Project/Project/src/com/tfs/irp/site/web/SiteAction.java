package com.tfs.irp.site.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;

public class SiteAction extends ActionSupport {

	private IrpSiteService irpSiteService;

	private IrpDocStatusService irpDocstatusService;// 文档状态service

	private IrpAttachedTypeService irpAttachedTypeService;// 附件类型service

	private List<IrpSite> allsiteList;

	private IrpSite irpSite;

	private Long siteid;

	private String siteNameConfim;

	private String siteName;

	private List<IrpDocstatus> irpDocstatus;
	/**
	 * 用于添加或修改给当前站点排序的对象 用于接收选择排序的siteid
	 */
	private Long siteIdToOrder;

	private IrpChannelService irpChannelService;

	private IrpChannel irpChannel;
	/**
	 * myFile是logo myFile1是baner
	 */
	private File myFile;// 上传文件

	private List<String> myFilecontentType = new ArrayList<String>();// 文件类型

	private String myFileFileName;// 文件名

	private String caption;

	private File myFile1;// 上传文件

	private List<String> myFile1contentType = new ArrayList<String>();// 文件类型

	private String myFile1FileName;// 文件名
	private IrpChannel irpChannelSub;

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public IrpChannel getIrpChannelSub() {
		return irpChannelSub;
	}

	public void setIrpChannelSub(IrpChannel irpChannelSub) {
		this.irpChannelSub = irpChannelSub;
	}

	public IrpChannel getIrpChannel() {
		return irpChannel;
	}

	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public String toSiteInfo() {
		return SUCCESS;
	}

	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public List<IrpSite> getAllsiteList() {
		return allsiteList;
	}

	public void setAllsiteList(List<IrpSite> allsiteList) {
		this.allsiteList = allsiteList;
	}

	public IrpSite getIrpSite() {
		return irpSite;
	}

	public void setIrpSite(IrpSite irpSite) {
		this.irpSite = irpSite;
	}

	public Long getSiteid() {
		return siteid;
	}

	public void setSiteid(Long siteid) {
		this.siteid = siteid;
	}

	public String getSiteNameConfim() {
		return siteNameConfim;
	}

	public void setSiteNameConfim(String siteNameConfim) {
		this.siteNameConfim = siteNameConfim;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public IrpDocStatusService getIrpDocstatusService() {
		return irpDocstatusService;
	}

	public void setIrpDocstatusService(IrpDocStatusService irpDocstatusService) {
		this.irpDocstatusService = irpDocstatusService;
	}

	public List<IrpDocstatus> getIrpDocstatus() {
		return irpDocstatus;
	}

	public void setIrpDocstatus(List<IrpDocstatus> irpDocstatus) {
		this.irpDocstatus = irpDocstatus;
	}

	public Long getSiteIdToOrder() {
		return siteIdToOrder;
	}

	public void setSiteIdToOrder(Long siteIdToOrder) {
		this.siteIdToOrder = siteIdToOrder;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public List<String> getMyFilecontentType() {
		return myFilecontentType;
	}

	public void setMyFilecontentType(List<String> myFilecontentType) {
		this.myFilecontentType = myFilecontentType;
	}

	public List<String> getMyFile1contentType() {
		return myFile1contentType;
	}

	public void setMyFile1contentType(List<String> myFile1contentType) {
		this.myFile1contentType = myFile1contentType;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public File getMyFile1() {
		return myFile1;
	}

	public void setMyFile1(File myFile1) {
		this.myFile1 = myFile1;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getMyFile1FileName() {
		return myFile1FileName;
	}

	public void setMyFile1FileName(String myFile1FileName) {
		this.myFile1FileName = myFile1FileName;
	}

	// 检查站点的唯一
	public void checkSiteNameIsOnly() {
		boolean boo = false;
		siteName = ActionUtil.decode(siteName);
		if (siteName!=null && siteName.length()>0) {
			if (siteid != null && siteid != 0L) {
				if (siteNameConfim != null && siteNameConfim.length() > 0) {
					siteNameConfim = ActionUtil.decode(siteNameConfim);
					if (siteName.equals(siteNameConfim)) {
						boo = true;
					} else {
						boo = irpSiteService.findSiteBySiteName(siteName);
					}
				}
			} else {
				boo = irpSiteService.findSiteBySiteName(siteName);
			}
		}
		ActionUtil.writer(String.valueOf(boo));
	}

	// 删除某个站点
	public void deleteSiteBySiteid() {
		int nCount = irpSiteService.deleteSiteBySiteid(irpSite.getSiteid());// 彻底删除某个站点
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 修改某个站点的信息
	public void updateSite() {
		int nCount = 0;
		if (myFile != null && myFile.exists()) {
			if (irpSite.getLogo() != null && irpSite.getLogo().length() > 0) {// 如果原来有值就删除附件
				SysFileUtil.deleteFileByFileName(irpSite.getLogo());
			}
			String logoName = SysFileUtil.saveFile(myFile,
					SysFileUtil.FILE_TYPE_ATTACHED_PIC,
					FileUtil.findFileExt(myFileFileName));
			irpSite.setLogo(logoName);
		}
		if (myFile1 != null && myFile1.exists()) {
			if (irpSite.getBaner() != null && irpSite.getBaner().length() > 0) {
				SysFileUtil.deleteFileByFileName(irpSite.getBaner());
			}
			String banerName = SysFileUtil.saveFile(myFile1,
					SysFileUtil.FILE_TYPE_ATTACHED_PIC,
					FileUtil.findFileExt(myFile1FileName));
			irpSite.setBaner(banerName);
		}
		nCount = irpSiteService.updateSite(irpSite);
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 添加站点
	public void addSite() {
		int nCount = 0;
		if (myFile != null) {
			String logoName = SysFileUtil.saveFile(myFile,
					SysFileUtil.FILE_TYPE_ATTACHED_PIC,
					FileUtil.findFileExt(myFileFileName));
			irpSite.setLogo(logoName);
		}
		if (myFile1 != null) {
			String banerName = SysFileUtil.saveFile(myFile1,
					SysFileUtil.FILE_TYPE_ATTACHED_PIC,
					FileUtil.findFileExt(myFile1FileName));
			irpSite.setBaner(banerName);
		}
		nCount = irpSiteService.addSite(irpSite);// 如果文件都保存完毕，然后再执行站点的保存
		ActionUtil.writer(String.valueOf(nCount));
	}

	// 转到添加站点页面
	public String toAddSite() {
		Long status = 1L;
		allsiteList = irpSiteService.allSite(status); // 查询站点
		irpDocstatus = irpDocstatusService.findAllStatus();// 查询所有文档状态
		// Object suffix=irpAttachedTypeService.AttachedTypeSuffixImage(2L);
		// //查询可以上传的文件类型 目前不用，以后用的时候再说
		return SUCCESS;
	}

	// 查询所有站点名称
	public String allSiteName() {
		Long status = 1L;
		allsiteList = irpSiteService.allSite(status);
		// 得到知识分类
		irpChannel = irpChannelService.finChannelByChannelid(Long
				.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID")));
		irpChannelSub = irpChannelService.finChannelByChannelid(Long
				.parseLong(SysConfigUtil
						.getSysConfigValue("DOCUMENT_SUBJECT_ID")));
		return SUCCESS;
	}

	// 某一个站点详细信息
	public String siteInfo() {
		Long status = 1L;
		allsiteList = irpSiteService.allSite(status, irpSite.getSiteid());// 查询站点以供排序
		irpDocstatus = irpDocstatusService.findAllStatus();// 查询所有文档状态
		irpSite = irpSiteService.siteInfo(irpSite.getSiteid());
		return SUCCESS;
	}

	// 某一个站点详细信息
	public static String findSiteTypeBySiteId(Long _siteId) {
		String siteType = "";
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpSiteService Service = (IrpSiteService) ac.getBean("irpSiteService");
		IrpSite site = Service.siteInfo(_siteId);
		if (site != null) {
			Integer siteTypeId = site.getSitetype();
			if (siteTypeId == IrpSite.SITE_TYPE_PRIVATE)
				siteType = "个人站点";
			else
				siteType = "企业站点";
		}
		return siteType;
	}

}
