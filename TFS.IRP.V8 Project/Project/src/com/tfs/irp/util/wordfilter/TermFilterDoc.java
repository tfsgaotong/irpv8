package com.tfs.irp.util.wordfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tfs.irp.timedtask.entity.TimedTask;
import com.tfs.irp.timedtask.service.TimedTaskTermsImport;
import com.tfs.irp.util.SysConfigUtil;

public class TermFilterDoc {
	
	private  String imaddress;
	
	public TermFilterDoc(){
		String linstr = TimedTaskTermsImport.class.getClassLoader().getResource("").getPath();
		imaddress = linstr.substring(1, linstr.length()-1);
		if(imaddress.indexOf(":")==-1){
			imaddress=linstr;
		}else{
			imaddress = linstr.substring(1, linstr.length()-1);
		}
	}
	/**
	 * 返回处理后的字符串
	 * @return
	 */
	public String returnDisposeStr(String _filename){
		
		String str = _filename;
		
		File file = new File(imaddress+SysConfigUtil.getSysConfigValue(TimedTask.TERMADDRESS));
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			String tempstr = null;
			//一次读入一行，null结束
			while ((tempstr = br.readLine()) != null) {
				if (tempstr!=null) {
					if (tempstr.length()>0) {
						str = str.replace(tempstr,"<a href=\"javascript:void(0)\" style=\"color:red;\" id=\"key_words\" onclick=\"linkTerms('"+tempstr+"',this)\" >"+tempstr+"</a>");
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
			
		}finally{
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return str;
		
	}
	
	
	
	
	
	

}
