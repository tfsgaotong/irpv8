/** 
 * Project Name:TFS.IRP.V8 Project 
 * File Name:IrpTermEditLogService.java 
 * Package Name:com.tfs.irp.termeditlog.service 
 * Date:2017年10月10日下午5:46:42 
 * 
 */

package com.tfs.irp.termeditlog.service;

import java.util.List;

import com.tfs.irp.termeditlog.entity.IrpTermeditLog;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpTermEditLogService <br/> 
 * Function: 词条编辑日志记录的Service类. <br/> 
 * date: 2017年10月10日 下午5:46:42 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
public interface IrpTermEditLogService {
    
    /**
     * 
     * addTermEditLog:添加词条编辑记录日志. <br/> 
     * 
     * @author Administrator 
     * @param irpTermeditLog 词条编辑日志实体 
     * @return 数据库变动条目数
     * @since JDK 1.8
     */
    public int addTermEditLog(IrpTermeditLog irpTermeditLog);
    
    /**
     * 
     * countTermEditLog:计算所有词条编辑日志数量. <br/> 
     * 
     * @author Administrator 
     * @return 返回查询到的数量
     * @since JDK 1.8
     */
    public int countTermEditLog();
    
    /**
     * 
     * findTermEditLogList:查找所有词条编辑日志列表. <br/> 
     * 
     * @author Administrator 
     * @param pageUtil 分页实体
     * @return 返回查询列表
     * @since JDK 1.8
     */
    public List<IrpTermeditLog> findTermEditLogList(PageUtil pageUtil);
}
