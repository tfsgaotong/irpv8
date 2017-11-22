package com.tfs.irp.timedtask.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.tfs.irp.term.entity.IrpTerm;
import com.tfs.irp.term.service.IrpTermService;
import com.tfs.irp.timedtask.entity.TimedTask;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.SysConfigUtil;



public class TimedTaskTermsImport implements Job {
	
	private IrpTermService irpTermService;
	
	private String imaddress;
	
	public IrpTermService getIrpTermService() {
		return irpTermService;
	}

	public void setIrpTermService(IrpTermService irpTermService) {
		this.irpTermService = irpTermService;
	}
	public  TimedTaskTermsImport(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpTermService = (IrpTermService) ac.getBean("irpTermService");
		String linstr = TimedTaskTermsImport.class.getClassLoader().getResource("").getPath();
		if(imaddress.indexOf(":")==-1){
			imaddress=linstr;
		}else{
			imaddress = linstr.substring(1, linstr.length()-1);
		}

	}
	

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		 File filename;
		 filename = new File(imaddress+SysConfigUtil.getSysConfigValue(TimedTask.TERMADDRESS));
				//没有 进行创建
				try {
					if(!filename.exists()){
						filename.createNewFile();
					}
					StringBuffer tabSb = new StringBuffer();
					List<String> listStr = this.irpTermService.selectTNameTask(IrpTerm.TERMSTATUSWORDS,IrpTerm.TERMISDELNORMAL);
					 if(listStr.size()>0){
						 for (int i = 0; i < listStr.size(); i++) {
							 tabSb.append(listStr.get(i)+"\r\n");
						}
					 }
					 TimedTaskImportDic.writeTxtFile("\r\n"+tabSb,filename);
					
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
