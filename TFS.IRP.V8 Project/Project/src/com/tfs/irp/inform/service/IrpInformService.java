package com.tfs.irp.inform.service;

import java.util.List;

import com.tfs.irp.inform.entity.IrpInform;

public interface IrpInformService {


	/**
	 * 增加举报内容
	 * @param _irpInform
	 * @param _informtype
	 * @return
	 */
    int addInform(IrpInform _irpInform,Integer _informtype,String _informkey);
    /**
     * 逻辑删除举报内容
     * @param _informid
     * @return
     */
    int changeInformStatus(Long _informid);
    /**
     * 把此记录改成非法状态
     * @param _microblog
     * @return
     */
    int changeInformStatusByillegality(Long _microblog,Integer _status);
    /**
     * 修改记录的状态
     * @param nameid 知识id或者微知id
     * @param informType  该记录的正常或者非法状态
     * @param _status 知识状态 或者微知状态
     * @return
     */
	int changeInformStatusByNameIdAndStatus(Long nameid, Integer _status, Integer informType);
	 /**
	  * 删除记录 
	  * informId主键
	  * @return
	  */ 
	int deleteInform(Long informId);
	/**
	 * 删除记录
	 * @param nameid 知识或微知id
	 * @param _status知识或微知状态
	 * @return
	 */
	int deleteByStatus(Long nameid, Integer _status);
	/**
	 * 张银珠
	 * 根据所给条件获取用户id的集合
	 * @param informType
	 * @param infromNameId
	 * @return
	 */
	List<Long> findAllUserIdsByExpert(Integer informType,Long infromNameId);
	/**
	 * 张银珠 查询用户操作对象
	 * @param informType
	 * @param informNameId
	 * @param userId
	 * @return
	 */
	 IrpInform findInformByExpert(Integer informType,Long informNameId,Long userId);
	 /**
	  *张银珠 查询记录集合
	  * @param informType
	  * @param infromNameId
	  * @return
	  */
	 List<IrpInform> findAllInformByExpert(Integer informType,Long infromNameId);
	 /**
	  * 张银珠 查询记录数量
	  */
	 int findCountByExpert(Integer informType,Long infromNameId);
	 
	 /**
	  * 根据微知ID删除微知的举报信息
	  * @param _microblog
	  * @return
	  */
	int deleteInformByMicroblogId(Long _microblog);
}
