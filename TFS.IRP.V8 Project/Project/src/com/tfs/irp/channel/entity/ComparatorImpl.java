package com.tfs.irp.channel.entity;

import java.util.Comparator;

/**
 * 按照知识数量进行排序
 * @author admin
 *
 */
public class ComparatorImpl implements Comparator<IrpChannel>{

	@Override
	public int compare(IrpChannel o1, IrpChannel o2) {
		if(o1.getKnowledgeNum()>o2.getKnowledgeNum()){
			return -1;
		}else if(o1.getKnowledgeNum()<o2.getKnowledgeNum()){
			return 1;
		}else {
			return 0;
		}
	}

}
