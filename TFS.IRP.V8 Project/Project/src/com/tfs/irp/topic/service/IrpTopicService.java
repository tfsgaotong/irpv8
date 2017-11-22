package com.tfs.irp.topic.service;

import java.util.List;

import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.util.PageUtil;

public interface IrpTopicService {
	/**
	 * 增加话题列表
	 * @param _topicname
	 * @return
	 */
	int addTopic(String _topicname);
	/**
	 *  根据话题名字获得话题对象
	 * @param _topicname
	 * @return
	 */
	IrpTopic getIrpTopic(String _topicname);
	/**
	 * 查找所有的话题
	 * @return
	 */
	List<IrpTopic> getAllIrpTopic(PageUtil pageUtil,Integer _topictype,String _orderby);
	/**
	 * 查找所有的话题的长度
	 * @return
	 */
    int getAllIrpTopicLength(Integer _topictype,String _orderby);
    /**
     * 删除专题
     * @return
     */
    int deleteTopicById(Long _topicid);
    /**
     * 根据id查某个专题对象
     * @param _topicid
     * @return
     */
    IrpTopic getIrpTopicById(Long _topicid);
    /**
     * 修改专题描述
     * @param irpTopic
     * @return
     */
    int updateTopicDesc(IrpTopic irpTopic);
    /**
     * 根据话题的名字更新相应的话题热度
     * @param _topicname
     * @return
     */
    int updateTopicHotNum(String _topicname);
    /**
     * 增加新专题
     * @param _topicname
     * @param _topicdesc
     * @return
     */
    long addTopic(IrpTopic irpTopic);
    /**
     * 改变专题的状态
     * @param _topicid
     * @param _topictype
     * @return
     */
    int updateTopicType(Long _topicid,Integer _topictype);
    /**
     * 查找我的专题
     * @param pageUtil
     * @param _userid
     * @return
     */
	List<IrpTopic> getAllIrpTopicByMine(PageUtil pageUtil,Long _userid,String _orderby);
	/**
	 * 查找我的专题一共多少个
	 * @param _userid
	 * @return
	 */
	int getAllIrpTopicByMineCount(Long _userid,String _orderby);
     
	/**
	 * 张银珠写
	 */
	List<IrpTopic> findAllToPic(List<Long> topicIds);
}
