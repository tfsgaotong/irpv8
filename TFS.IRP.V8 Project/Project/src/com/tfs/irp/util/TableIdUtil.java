package com.tfs.irp.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.tableid.dao.IrpTableidDAO;
import com.tfs.irp.tableid.entity.IrpTableid;
import com.tfs.irp.tableid.entity.IrpTableidExample;

public class TableIdUtil implements ApplicationListener<ApplicationEvent>{
	
	private static Map<String, AtomicLong> sysTableIds;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("==========加载数据库表主键==========");
		ApplicationContext ac = (ApplicationContext) event.getSource();
		initTableId(ac);
	}
	
	/**
	 * 初始化TABLEID到缓存中
	 * @param ac
	 */
	private void initTableId(ApplicationContext ac){
		if(sysTableIds==null){
			sysTableIds = new HashMap<String, AtomicLong>();
		}else{
			sysTableIds.clear();
		}
		IrpTableidDAO tableidDao = (IrpTableidDAO)ac.getBean("irpTableIdDAO");
		List<IrpTableid> tableids = null;
		try {
			IrpTableidExample example = new IrpTableidExample();
			example.createCriteria().andTablenameIsNotNull().andFieldidnameIsNotNull();
			tableids = tableidDao.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(tableids!=null){
			for (IrpTableid irpTableid : tableids) {
				if(irpTableid==null)
					continue;
				long nMaxId = tableidDao.selectMaxIdByTableid(irpTableid);
				if(nMaxId>0){
					sysTableIds.put(irpTableid.getTablename().toUpperCase(), new AtomicLong(nMaxId));
				}
			}
		}
	}
	
	/**
	 * 根据tablename获得下一个主键值
	 * @param tablename
	 * @return
	 */
	public static Long getNextId(String tablename){
		return sysTableIds.get(tablename.toUpperCase()).incrementAndGet();
	}
	
	/**
	 * 根据IrpBaseObj获得下一个主键值
	 * @param baseObj
	 * @return
	 */
	public static Long getNextId(IrpBaseObj baseObj){
		Long nNextId = null;
		String sTableName = baseObj.getTableName();
		AtomicLong al = sysTableIds.get(sTableName.toUpperCase());
		if(al!=null){
			nNextId = al.incrementAndGet();
		}else{
			try {
				initTableField(baseObj);
				al = new AtomicLong(50L);
				sysTableIds.put(sTableName, al);
				nNextId = al.incrementAndGet();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nNextId;
	}
	
	/**
	 * 初始化BaseObj到TableId表中
	 * @param baseObj
	 * @throws SQLException 
	 */
	private static void initTableField(IrpBaseObj baseObj) throws SQLException{
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpTableidDAO tableidDao = (IrpTableidDAO)ac.getBean("irpTableIdDAO");
		IrpTableid irpTableid = null;
		irpTableid = tableidDao.selectByPrimaryKey(baseObj.getTableName());
		if(irpTableid==null){
			irpTableid = new IrpTableid();
			irpTableid.setTablename(baseObj.getTableName());
			irpTableid.setFieldidname(baseObj.getIdFieldName());
			tableidDao.insertSelective(irpTableid);
		}else if(irpTableid.getFieldidname()==null || irpTableid.getFieldidname().length()==0){
			irpTableid.setFieldidname(baseObj.getIdFieldName());
			tableidDao.updateByPrimaryKeySelective(irpTableid);
		}
	}
}
