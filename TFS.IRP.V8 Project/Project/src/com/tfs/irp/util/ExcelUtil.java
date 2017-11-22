package com.tfs.irp.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tfs.irp.question.entity.IrpQuestion;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ExcelUtil {

	/**
	 * 参数为 List<Map> 的工具类
	 * @param path      生成xls保存的路径
	 * @param xlsname   生成xls的文件名
	 * @param sheetName 生成xls的sheet名称  为空默认为 firstsheet
	 * @param listmap   xls数据
	 */
	private static void createxlsFilelistmap(String xlsFile, List<Map> listmap) {
		HSSFWorkbook workbook = new HSSFWorkbook(); // 产生工作簿对象
		HSSFSheet sheet = workbook.createSheet(); // 产生工作表对象
		// 设置第一个工作表的名称为firstSheet
		// 为了工作表能支持中文，设置字符编码为UTF_16,HSSFWorkbook.ENCODING_UTF_16
		workbook.setSheetName(0, "firstsheet");
		for (int i = 0; i <= listmap.size(); i++) {
			// 产生一行
			HSSFRow row = sheet.createRow((short) i);
			for (int j = 0; j < listmap.get(0).size(); j++) {
				// 产生第一个单元格
				@SuppressWarnings("deprecation")
				HSSFCell cell = row.createCell((short) j);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 往第一个单元格中写入信息
				if (i == 0) {
					String resultkey = listmap.get(0).keySet().toString();
					String[] keyvalue = resultkey.substring(1,
							resultkey.length() - 1).split(",");
					cell.setCellValue(keyvalue[j]);
				} else {
					Map<String, Object> map = listmap.get(i - 1);
					String resultvalue = map.entrySet().toString();
					String[] value = resultvalue.substring(1,
							resultvalue.length() - 1).split(",");
					cell.setCellValue(value[j].substring(value[j]
							.lastIndexOf("=") + 1));
				}
			}
		}
		try {
			FileOutputStream fOut = new FileOutputStream(xlsFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成xls 工具类 提供List<Map>接口
	 * @param listmap
	 * @return
	 */
	public static String createxlsUtil(List<Map> listmap){
		// 获得当前时间
		Date date = new Date();
		//获取临时文件目录
		String path=SysConfigUtil.getSysConfigValue(SysFileUtil.FILE_TYPE_TEMP_FILE);
		// 获得保存文件夹路径
		StringBuffer sSaveDirectory = new StringBuffer(path);
		sSaveDirectory.append(File.separator)
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(File.separator)
				.append(DateUtils.getDateByFormat(date, "dd"));
		// 判断文件夹是否存在，如果不存在创建
		File directory = new File(sSaveDirectory.toString());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 生成文件名
		StringBuffer sFileName = new StringBuffer();
		sFileName.append(SysFileUtil.FILE_TYPE_TEMP_FILE)
				.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
				.append(new Random().nextInt(10000000)).append(".").append("xls");
		String xlsFile = sSaveDirectory + File.separator + sFileName;
		createxlsFilelistmap(xlsFile,listmap);
		return sFileName.toString();
	}
	/**
	 * 生成问答xsl 提供list 接口
	 * @param list
	 * @return
	 */
	public static String createquestionxlsUtil(List<IrpQuestion> list){
		// 获得当前时间
		Date date = new Date();
		//获取临时文件目录
		String path=SysConfigUtil.getSysConfigValue(SysFileUtil.FILE_TYPE_TEMP_FILE);
		// 获得保存文件夹路径
		StringBuffer sSaveDirectory = new StringBuffer(path);
		sSaveDirectory.append(File.separator)
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(File.separator)
				.append(DateUtils.getDateByFormat(date, "dd"));
		// 判断文件夹是否存在，如果不存在创建
		File directory = new File(sSaveDirectory.toString());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 生成文件名
		StringBuffer sFileName = new StringBuffer();
		sFileName.append(SysFileUtil.FILE_TYPE_TEMP_FILE)
				.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
				.append(new Random().nextInt(10000000)).append(".").append("xls");
		String xlsFile = sSaveDirectory + File.separator + sFileName;
		createxlsFilename(xlsFile,"firstsheet",list);
		return sFileName.toString();
	}
	
	/**
	 * 已经写好的
	 * @param path      生成xls保存的路径
	 * @param xlsname   生成xls的文件名
	 * @param sheetName 生成xls的sheet名称  为空默认为 firstsheet
	 * @param listmap   xls数据
	 */
	private static void createxlsFilename(String xlsFile,String sheetName, List<IrpQuestion> list) {
		HSSFWorkbook workbook = new HSSFWorkbook(); // 产生工作簿对象
		HSSFSheet sheet = workbook.createSheet(); // 产生工作表对象
		if(sheetName!=null||sheetName.trim().length()>0){
			workbook.setSheetName(0, sheetName);
		}else{
			workbook.setSheetName(0, "firstsheet");
		}
		for (int i = 0; i <= list.size(); i++) {
			// 产生一行
			HSSFRow row = sheet.createRow((short) i);
			for(int j=0;j<4;j++){
				// 产生第一个单元格
				HSSFCell cell = row.createCell((short) j);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 往第一个单元格中写入信息
				if(i==0){
					if(j==0){
						cell.setCellValue("问题");
					}
					if(j==1){
						cell.setCellValue("回答");
					}
					if(j==2){
						cell.setCellValue("创建者");
					}
				} else {
					IrpQuestion q=list.get(i-1);
					if(j==0){
						cell.setCellValue(q.getTitle());
					}
					if(j==1){
						cell.setCellValue(q.getTextcontent());
					}
					if(j==2){
						cell.setCellValue(q.getCruser());
					}
				}
			}
		}
		try {
			FileOutputStream fOut = new FileOutputStream(xlsFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  int who(String word)
    {
		int count=0;
        char[] str=word.toCharArray();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length-1;i++){
        	if(str[i]<str[i+1]){
        		count=count+1;
        	}else{
        		sb.append(word.substring(0, i+1));
        		sb.append(word.substring(i+2, str.length));
        		break;
        	}
        }
        if(count==word.length()-1){
        	return 1;
        }else{
        	if(sb.toString().length()>=2){
        		return who(sb.toString());
        	}else{
        		return 0;
        	}
        }
    }
}