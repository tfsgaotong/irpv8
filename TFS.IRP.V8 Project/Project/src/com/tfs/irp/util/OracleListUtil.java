package com.tfs.irp.util;

import java.util.ArrayList;
import java.util.List;

public class OracleListUtil {
	/**
	 * 
	 * @param inName  in 之前的名称 比如：groupid in 这个时候传进来groupid
	 * @param str  in括号里面的字符串
	 * @return
	 */
	public static String getInSql(String inName,String str){
		String result = "";//inName + " IN (";
		if(str.endsWith(","))str = str.substring(0,str.length()-1);
			if(str!=null&& str.indexOf(",")!=-1){
				String[] idsStr = str.split(",");
				if(idsStr.length > 1000){
					for(int i=0;i<idsStr.length;i++){
						result += idsStr[i]+",";
						if((i+1)%1000==0){
							result = result.substring(0,result.length()-1);
							result += ") "; 
							if(i!=idsStr.length){
								result += "OR " + inName + " IN (";
							}
						}
					}
					result = result.substring(0,result.length()-1);
					result += " )";
				}else{
					result = str +" )";
				}
			}else{
				result = str +" )";
			}
			//if(result.endsWith(")"))result = result.substring(0,result.length()-1);
			return result;
		} 
	/**
	 *处理Oracle在执行in时超过1000个报错
	 * @param option 限制字段
	 * @param isAnd 该限制之前已经有别的where条件，如果使用了就true 否则就false
	 * @return
	 */
	public static String ListGengerMaX(List<Long> IdList,String option,Boolean isAnd){
		String returnStr="";
		int tempChu=IdList.size()/1000;
		for (int i = 0; i < (tempChu+1); i++) {
			int index=i*1000; 
			if(index>(IdList.size()-1000)){
				 List<Long> tempTable=IdList.subList(index,IdList.size());
				 String tempStr=tempTable.toString().replace('[', '(').replace(']', ')');
				 if(index==0)  returnStr+=" "+option+" in "+tempStr;
				 else	 returnStr+=" or "+option+" in "+tempStr;
			}else{
				 List<Long> tempTable=IdList.subList(index, index+1000);
				 String tempStr=tempTable.toString().replace('[', '(').replace(']', ')');
				 if(isAnd){
					 if(index==0)  returnStr+=" and " ;
					 else  returnStr+=" or " ;
				 }else{
					 if(index!=0)  returnStr+=" or " ;
				 }
				 returnStr+=" "+option+" in "+tempStr;
			} 
		}
		return returnStr;
	}
	public static String listto(String option ,Boolean isAnd){
		String returnStr="";
		List<String> tableIds=new ArrayList<String>();
		for (int i = 0; i <13; i++) {
			tableIds.add(i+"");
		}
		int tempChu=tableIds.size()/10;
		for (int i = 0; i < (tempChu+1); i++) {
			int index=i*10; 
			if(index>(tableIds.size()-10)){
				 List<String> tempTable=tableIds.subList(index,tableIds.size());
				 String tempStr=tempTable.toString().replace('[', '(').replace(']', ')');
				 if(index==0)  returnStr+=" "+option+" in "+tempStr;
				 else	 returnStr+=" or "+option+" in "+tempStr;
				  
				
			}else{
				 List<String> tempTable=tableIds.subList(index, index+10);
				 String tempStr=tempTable.toString().replace('[', '(').replace(']', ')');
				 if(isAnd){
					 if(index==0)  returnStr+=" and " ;
					 else  returnStr+=" or " ;
				 }else{
					 if(index!=0)  returnStr+=" or " ;
				 }
				 returnStr+=" "+option+" in "+tempStr;
			} 
		}
		return returnStr;
	}
	public static void  test(){
		List<Integer> list=new ArrayList<Integer>();
		for (int i = 0; i <9999; i++) {
			list.add(i); 
		}
		Long time1=System.currentTimeMillis();
		List<Integer> temp=new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(i);
			if(temp.size()==1000 || (i+1)==list.size()){

				System.out.println(temp.toString());
				temp=new ArrayList<Integer>();
			}
		}
		Long time2=System.currentTimeMillis();
	
		Long time3=System.currentTimeMillis();
		int tempChu=list.size()/1000;//剩余多少个数字没有循环
		for (int i = 0; i < (tempChu+1); i++) {
			int index=i*1000; 
			if(index>(list.size()-1000)){
				 List<Integer> tempTable=list.subList(index,list.size());
				 System.out.println(tempTable.toString());
			}else{
				List<Integer> tempTable=list.subList(index, index+1000);
				System.out.println(tempTable.toString());
			} 
		}
		Long time4=System.currentTimeMillis();
		System.out.println("===============");
		System.out.println(time2-time1);
		System.out.println(time4-time3);
		System.out.println("=="+((time4-time3)<(time2-time1)));
	}

//	public static void main(String[] args) {
////		String returnStr = listto("channelid",true);
////		System.out.println(returnStr);
////		//test();
//		List<Long > tableIds=new ArrayList<Long>();
//		for (int i = 0; i <100000; i++) {
//			tableIds.add(new Long(i));
//		}
////		String ret=ListGengerMaX(tableIds,"channelid",true);
////		System.out.println(ret);
//		Long time1=System.currentTimeMillis();
//		for (int i = 0; i < tableIds.size(); i++) {
//			 tableIds.get(i);
//		}
//		Long time2=System.currentTimeMillis();
//		System.out.println("循环所用时间"+(time2-time1));
//		Long time3=System.currentTimeMillis();
//		tableIds.toString();
//		Long time4=System.currentTimeMillis();
//		System.out.println("toString所用时间"+(time4-time3));
//	} 
}
