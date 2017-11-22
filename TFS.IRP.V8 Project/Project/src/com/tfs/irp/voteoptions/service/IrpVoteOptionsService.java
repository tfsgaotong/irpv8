package com.tfs.irp.voteoptions.service;

import java.util.List;

import com.tfs.irp.voteoptions.entity.IrpVoteOptions;

public interface IrpVoteOptionsService {

	
	/**
	 * 根据pid找选项
	 * @param pid
	 * @return
	 */
	public List<IrpVoteOptions> findOptionBypid(Long pid,String orderby);
	
}
