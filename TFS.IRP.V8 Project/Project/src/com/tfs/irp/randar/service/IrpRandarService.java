package com.tfs.irp.randar.service;

import java.util.List;

import com.tfs.irp.randar.entity.IrpRandar;
import com.tfs.irp.randar.entity.TableType;
import com.tfs.irp.util.PageUtil;

public interface IrpRandarService {
	
	/**
	 * 鏌ヨ鎵�湁鏁版嵁骞跺垎椤垫帓搴�
	 * @param pageUtil
	 * @param _orderByctime
	 * @return
	 */
	List<IrpRandar> queryRadar( PageUtil pageUtil,String _orderByctime);
	/**
	 * 鑾峰緱鍏ㄩ儴鎬绘暟
	 */
	int countRadar();
	
	List<String> sits();
	/**
	 * 鏍规嵁siteName鑾峰緱鏉℃暟
	 */
	int countRadars(String sitename);
	/**
	 * 鏍规嵁sitename鏌ヨ鏁版嵁鍒嗛〉骞舵帓搴�
	 * @param pageUtil
	 * @param sitname
	 * @param _orderByctime
	 * @return
	 */
	List<IrpRandar> queryRadars(PageUtil pageUtil, String sitname,String _orderByctime);
	/**
	 * 鏍规嵁鏍囬寰楀埌鏁版嵁瀵硅薄
	 * @param stitle
	 * @return
	 */
	List<IrpRandar> querydoc(String stitle);
	/**
	 * 鏍规嵁title淇敼satus鍊�
	 * @param title
	 * @return
	 */
	int ups(String title);
	/**
	 * 鏍规嵁title鍜屼慨鏀规暟鎹�
	 * @param titles
	 * @param satus
	 * @return
	 */
	int upRadar(String titles,int status,String sid,String doccontent);
	
	int delRandar(String sid);
	
	String importRadarData();
	/**获取源表类型
	 * @return
	 */
	List<TableType> selectResoureColumn();
	/**获取目标表类型
	 * @return
	 */
	List<TableType> selectDestinationColumn();
	String importRadarData(String columnString);

}
