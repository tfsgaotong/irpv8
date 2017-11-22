package com.tfs.irp.category_file_link.service;

import java.util.List;

public interface IrpCategoryFileLinkService {
	
	/**
	 * 根据例子删除对象
	 * @param irpCategoryFileLinkExample
	 */
	int deleteCategoryFileLinkByFid(List<Long> fileIds);
}
