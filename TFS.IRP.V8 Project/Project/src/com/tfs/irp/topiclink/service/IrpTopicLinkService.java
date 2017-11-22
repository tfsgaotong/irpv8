package com.tfs.irp.topiclink.service;

import java.util.List;

import com.tfs.irp.topiclink.entity.IrpTopicLink;

public interface IrpTopicLinkService {

	
	/**
	 * 增加话题记录
	 * @param _topicname
	 * @param _microblogid
	 * @param _topictype
	 * @return
	 */
	int addTopicLink(String _topicname,long _microblogid,Integer _topictype);
    /**
     * 删除和专题相联系的微知信息
     * @param _topicname
     * @return
     */
	int updateTopicLink(String _topicname);
	/**
	 * 找出微知id下所有的专题微知
	 * @param _microblogid
	 * @return
	 */
	IrpTopicLink getTopicListLink(Long _microblogid);
	/**
	 * 找到此专题下所有的微知
	 * @param _topicname
	 * @return
	 */
	List<IrpTopicLink> getIrpTopicLinkByName(String _topicname);
	/**
	 *  根据话题名字找到它所关联的微知的id
	 * @param _topiclinkname
	 * @return
	 */
	List selectTopicNumByName(String _topiclinkname);
	/**
	 * 改变专题的状态
	 * @param _topiclinkname
	 * @param _topicLinkType
	 * @return
	 */
    int updateIrpTopicLink(String _topiclinkname,Long _topicLinkType);
    /**
     * 更新专题联系表的id
     * @param _topicid
     * @param _id
     * @return
     */
    int updateTopicIdById(Long _topicid,Long _id);
	
	
}
