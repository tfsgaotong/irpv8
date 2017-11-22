package com.tfs.irp.timedtask.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.timedtask.entity.TimedTask;
import com.tfs.irp.util.ApplicationContextHelper;

public  class TimedTaskImportDic implements Job {
	private IrpTagService irpTagService;
	private IrpConfigDAO irpConfigDAO;
	public TimedTaskImportDic(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpTagService = (IrpTagService) ac.getBean("irpTagService");
		irpConfigDAO = (IrpConfigDAO) ac.getBean("irpConfigDAO");
	}
	/**
	 * 导入数据
	 * @throws IOException
	 * @throws SQLException 
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		 File filename;
		try {
			filename = new File(irpConfigDAO.selectCValueByCKey(TimedTask.DICLOCATION));
			if(!filename.exists()){
				//没有 进行创建
				filename.createNewFile();
			}
			StringBuffer tabSb = new StringBuffer();
			//tag 表所有关键词
			 List<String> listStr = irpTagService.findTagName();
			 if(listStr.size()>0){
				 for (int i = 0; i < listStr.size(); i++) {
					 tabSb.append(listStr.get(i)+"\r\n");
				}
			 }
				//已经创建好了
			 TimedTaskImportDic.writeTxtFile("\r\n"+tabSb,filename);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入文件
	 * @return
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void writeTxtFile(String newStr,File filename) throws UnsupportedEncodingException, FileNotFoundException{
		 String filein = newStr + "\r\n";
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8");
        BufferedWriter writer=new BufferedWriter(write);          
        try {
			writer.write(filein);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
