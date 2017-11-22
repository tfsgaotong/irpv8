/** 
 * Project Name:TFS.IRP.V8 Project 
 * File Name:IrpTermEditLogAction.java 
 * Package Name:com.tfs.irp.termeditlog.web 
 * Date:2017年10月10日下午5:49:25 
 * 
 */

package com.tfs.irp.termeditlog.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.termeditlog.entity.IrpTermeditLog;
import com.tfs.irp.termeditlog.service.IrpTermEditLogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.PageUtil;

/** 
 * ClassName: IrpTermEditLogAction <br/> 
 * Function: 词条编辑日志记录的Action类. <br/> 
 * date: 2017年10月10日 下午5:49:25 <br/> 
 * 
 * @author Administrator 
 * @version  
 * @since JDK 1.8 
 */
@SuppressWarnings("serial")
public class IrpTermEditLogAction extends ActionSupport {
    private IrpTermEditLogService irpTermEditLogService;
    private IrpUserService irpUserService;
    private IrpTermService irpTermService;

    public IrpTermService getIrpTermService() {
        return irpTermService;
    }

    public void setIrpTermService(IrpTermService irpTermService) {
        this.irpTermService = irpTermService;
    }

    public IrpUserService getIrpUserService() {
        return irpUserService;
    }

    public void setIrpUserService(IrpUserService irpUserService) {
        this.irpUserService = irpUserService;
    }

    public IrpTermEditLogService getIrpTermEditLogService() {
        return irpTermEditLogService;
    }

    public void setIrpTermEditLogService(IrpTermEditLogService irpTermEditLogService) {
        this.irpTermEditLogService = irpTermEditLogService;
    }

    /**
     * 
     * getEditCount:加载词条的编辑次数. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getEditCount() {
        int count = this.irpTermEditLogService.countTermEditLog();
        ActionUtil.writer("{\"editCount\":" + count + "}");
    }

    /**
     * 
     * getTermEditLogList:加载词条编辑日志信息列表. <br/> 
     * 
     * @author Administrator  
     * @since JDK 1.8
     */
    public void getTermEditLogList() {
        // 初始化一个用于存放封装好的json对象列表
        List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();

        // 初始化json对象
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);

        int count = this.irpTermEditLogService.countTermEditLog();

        // 初始化分页实体
        PageUtil pageUtil = new PageUtil(1, 10, count);

        List<IrpTermeditLog> list = this.irpTermEditLogService.findTermEditLogList(pageUtil);

        if (list != null && list.size() > 0) {
            for (IrpTermeditLog irpTermeditLog : list) {

                // 借用map将对象以json格式封装起来
                Map<String, Object> irpTermEditLogMap = new HashMap<String, Object>(16);

                // 获得用户姓名
                IrpUser irpUser = this.irpUserService.findUserByUserId(irpTermeditLog.getTermedituserid());
                if (irpUser != null) {
                    irpTermEditLogMap.put("username", irpUser.getNickname());
                }

                // 获得词条名称
                IrpTerm irpTerm = this.irpTermService.irpTermById(irpTermeditLog.getTermid());
                if (irpTerm != null) {
                    irpTermEditLogMap.put("termName", irpTerm.getTermname());
                }
                
                // 获得友好日期
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formatDate = sdf.format(irpTermeditLog.getTermeditdate());
                    String friendlyDate = DateUtils.friendlyTime(formatDate);
                    irpTermEditLogMap.put("friendlyDate", friendlyDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 将对象添加到数据列表中
                jsonList.add(irpTermEditLogMap);

                irpTermEditLogMap = new HashMap<String, Object>(16);
            }

            jsonMap.put("resultStatus", "success");
            jsonMap.put("data", jsonList);
        } else {
            jsonMap.put("resultStatus", "error");
        }
        ActionUtil.writer(JsonUtil.map2json(jsonMap));
    }
}
