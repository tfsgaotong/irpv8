package com.tfs.irp.subscribe.service;

import java.util.HashMap;
import java.util.List;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.util.PageUtil;

public interface IrpSubscribeService {

	/**
	 * 当前用户订阅对象
	 * @param baseObj
	 * @return
	 */
	long addSubscribe(IrpBaseObj baseObj);

	/**
	 * 根据对象查询订阅信息
	 * @param baseObj
	 * @return
	 */
	List<IrpSubscribe> findSubscribeByBaseObj(IrpBaseObj baseObj);

	/**
	 * 根据对象和用户id删除订阅信息
	 * @param baseObj
	 * @param UserId
	 * @return
	 */
	int deleteSubscribeByBaseObjAndUserId(IrpBaseObj baseObj, long UserId);

	/**
	 * 根据对象删除订阅信息
	 * @param baseObj
	 * @return
	 */
	int deleteSubscribeByBaseObj(IrpBaseObj baseObj);

	/**
	 * 向订阅的对象发送私信
	 * @param baseObj
	 */
	void sendSubscribeMessage(IrpBaseObj baseObj);

	/**查询当前用户订阅
	 * @param map
	 * @param pageUtil
	 * @return
	 */
	List<IrpChnlDocLink> myAllDocSubscribe(HashMap<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 查询当前用户订阅数量
	 * @param map
	 * @return
	 */
	int count(HashMap<String, Object> map);

	/**查询当前用户订阅
	 * @param map
	 * @param pageUtil
	 * @return
	 */
	List<IrpSubscribe> selectSubscribeByPage(HashMap<String, Object> map,
			PageUtil pageUtil);

	/**修改当前用户的阅读状态
	 * @param irpDocument
	 * @param status
	 * @return
	 */
	int updateStatus(IrpBaseObj baseObj, Long status);

	/**
	 * 当前人的订阅信息
	 * @param irpDocument
	 * @return
	 */
	List<IrpSubscribe> findSubscribeByBaseObjLogin(
			IrpBaseObj baseObj);

	/**根据主键修改
	 * @param subscribe
	 * @return
	 */
	int updateByPrimary(IrpSubscribe subscribe);
}
