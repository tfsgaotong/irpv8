package com.tfs.irp.solr.service;

import java.util.List;

import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.util.PageUtil;

public interface TRSSearchService {
	
	/**
	 * 根据输入的关键字 从需要检索的字段中检索 分页
	 * @param pageutil
	 * strSources
	 * @param keyword
	 * @param strWhere
	 * @param orderBy
	 * @return
	 */
	List<IrpDocumentWithBLOBs> selectDocbypage(PageUtil pageutil,String strSources,String keyword,
			String strWhere, String orderBy);
	/**
	 * 
	 * @param pageutil
	 * @param strSources
	 * @param keyword
	 * @param strWhere
	 * @param orderBy
	 * @return
	 */
	List<IrpMicroblog> selectMicroblogbypage(PageUtil pageutil,String strSources,String keyword,
			String strWhere, String orderBy);
}
