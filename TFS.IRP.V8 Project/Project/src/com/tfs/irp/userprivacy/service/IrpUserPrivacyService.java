package com.tfs.irp.userprivacy.service;


import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;

public interface IrpUserPrivacyService {

	/**
	 * 增加新的隐私配置
	 * @param _userid
	 * @param _privacytype
	 * @param _privacyvalue
	 * @return
	 */
	int addUserPrivacy(Long _userid,String _privacytype,Integer _privacyvalue);
	/**
	 * 根据用户id和隐私类型获得隐私的值
	 * @param _userid
	 * @param _privacytype
	 * @return
	 */
	IrpUserPrivacy irpUserPrivacy(Long _userid,String _privacytype);
	/**
	 * 根据用户id和隐私类型更新相对类型的值
	 * @param _userid
	 * @param _privacytype
	 * @return
	 */
	int updateIrpUserPrivacy(Long _userid,String _privacytype,Integer _privacyvalue);
	
	/**
	 * 根据id和隐私类型判断是否存在
	 * @param _userId
	 * @param type
	 */
	void judgePrivacyIfEx(Long _userId,String type);
	
	
}
