package com.tfs.irp.site.service;

import java.io.File;
import java.util.List;

import com.tfs.irp.site.entity.IrpSite;

public interface IrpSiteService {
		/**
		 * 根据状态查询所有站点
		 * @param status某个状态
		 * @param _siteid不查询的站点ID
		 * @return
		 */
		public List allSite(Long status,Long _siteid) ;
		/**
		 * 根据状态查询所有站点
		 * @param status站点状态
		 * @return
		 */
		public List allSite(Long status ) ;
		/**
		 * 站点详细信息
		 * @param siteid 站点ID
		 * @return
		 */
		public IrpSite siteInfo(Long siteid);
		/**
		 * 站点名称
		 * @param siteid站点ID
		 * @return
		 */
		public String findSiteName(Long siteid);
		/**
		 * 添加站点信息
		 * @param site
		 * @return
		 */
		public int addSite(IrpSite site);
		/**
		 * 根据站点ID删除站点
		 * @param siteid 站点ID
		 * @return
		 */
		public int deleteSiteBySiteid(Long siteid);
		/**
		 * 修改站点
		 * @param irpSite
		 * @return
		 */
		public  int updateSite(IrpSite irpSite);
		/**
		 * 查询站点名称是否存在
		 * @param sitename 站点名称
		 * @return
		 */
		public boolean  findSiteBySiteName(String sitename);
	    /**
	     * 将文件写入指定路径
	     */
		public void saveLogoAndBaner(File _src,File _dst);
	    /**
	     * 删除指定文件
	     */
		public Boolean deleteLogoAndBaner(String sPath);
	    /**
	     * 根据站点类型获得站点集合
	     * @param _nSiteType
	     * @return
	     */
		public List<IrpSite> findSitesBySiteType(int _nSiteType);
		/**
		 * 根据站点类型获取站点id集合
		 * @param siteType
		 * @return
		 */
		public List<Long> findSiteIds(Integer siteType);
}
