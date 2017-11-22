/** 
 * Project Name:TFS.IRP.V8 Project 
 * File Name:IrpTermEditLogServiceImpl.java 
 * Package Name:com.tfs.irp.termeditlog.service.impl 
 * Date:2017年10月10日下午5:47:24 
 * 
 */

package com.tfs.irp.termeditlog.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.termeditlog.dao.IrpTermeditLogDAO;
import com.tfs.irp.termeditlog.entity.IrpTermeditLog;
import com.tfs.irp.termeditlog.entity.IrpTermeditLogExample;
import com.tfs.irp.termeditlog.service.IrpTermEditLogService;
import com.tfs.irp.util.PageUtil;

/**
 * 
 * ClassName: IrpTermEditLogServiceImpl <br/> 
 * Function: 词条编辑日志记录的Service实现类. <br/> 
 * date: 2017年10月10日 下午5:48:13 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8
 */
public class IrpTermEditLogServiceImpl implements IrpTermEditLogService {
    private IrpTermeditLogDAO irpTermeditLogDAO;

    public IrpTermeditLogDAO getIrpTermeditLogDAO() {
        return irpTermeditLogDAO;
    }

    public void setIrpTermeditLogDAO(IrpTermeditLogDAO irpTermeditLogDAO) {
        this.irpTermeditLogDAO = irpTermeditLogDAO;
    }

    @Override
    public int addTermEditLog(IrpTermeditLog irpTermeditLog) {
        int row = 0;
        try {
            this.irpTermeditLogDAO.insertSelective(irpTermeditLog);
            row = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int countTermEditLog() {
        int count = 0;
        
        // 创建ibatis封装查询实体
        IrpTermeditLogExample irpTermeditLogExample = new IrpTermeditLogExample();
        try {
            count = this.irpTermeditLogDAO.countByExample(irpTermeditLogExample);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<IrpTermeditLog> findTermEditLogList(PageUtil pageUtil) {
        List<IrpTermeditLog> list = null;
        
        // 创建ibatis封装查询实体
        IrpTermeditLogExample irpTermeditLogExample = new IrpTermeditLogExample();
        
        // 根据日期降序
        irpTermeditLogExample.setOrderByClause("TERMEDITDATE desc");
        
        try {
            list = this.irpTermeditLogDAO.selectByExample(irpTermeditLogExample, pageUtil);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
