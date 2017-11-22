package com.tfs.irp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;

import com.tfs.irp.user.entity.IrpUser;

public class ExcelConverter {
	
	private File excelFile;
	
	private String excelExt;
	
	public ExcelConverter(String _sFileName) {
		String filePath = SysFileUtil.getFilePathByFileName(_sFileName);
		excelFile = new File(filePath);
		excelExt = FileUtil.findFileExt(_sFileName).toLowerCase();
	}
	
	/**
	 * 读取Excel转化为IrpUser集合
	 * Excel列：
	 * 	用户名：1列
	 * 	密码：2列
	 * 	真是姓名：3列
	 * 	用户昵称：4列
	 * 	性别:5列
	 * 	地址：6列
	 * 	电话：7列
	 * 	手机：8列
	 * 	邮箱：9列
	 * 	描述：10列
	 * @param _nUserStatus
	 * @return
	 * @throws DocumentException
	 */
	public List<IrpUser> readUserFile(int _nUserStatus) throws DocumentException {
		List<IrpUser> irpUsers = new ArrayList<IrpUser>();
		InputStream is = null;
		Workbook workbook = null; 
		try {
			is = new FileInputStream(excelFile);
			if(excelExt.toLowerCase().equals("xls")){
				workbook = new HSSFWorkbook(is);  
			}else if(excelExt.toLowerCase().equals("xlsx")){
				workbook = new XSSFWorkbook(is);
			}
			Sheet sheet = workbook.getSheetAt(0);
			IrpUser irpUser = null;
			for (Row row : sheet) {  
	           if(row==null || row.getRowNum()==0)
	        	   continue;
	           irpUser = new IrpUser();
	           irpUser.setStatus(_nUserStatus);
	           
	           try {
				irpUser.setUsername(getCellValue(row.getCell(0)).toString());
				String passwd = getCellValue(row.getCell(1)).toString();
				if (!passwd.trim().isEmpty()) {
					irpUser.setPassword(passwd);
				} else {
					irpUser.setPassword("tfsadmin");
				}
				irpUser.setTruename(getCellValue(row.getCell(2)).toString());
				irpUser.setNickname(getCellValue(row.getCell(3)).toString());
				String sSex = getCellValue(row.getCell(4)).toString();
				if (sSex.equals("男") || sSex.toLowerCase().equals("m")) {
					irpUser.setSex(1);
				} else if (sSex.equals("女") || sSex.toLowerCase().equals("f")) {
					irpUser.setSex(2);
				}
				irpUser.setLocation(getCellValue(row.getCell(5)).toString());
				irpUser.setTel(getCellValue(row.getCell(6)).toString());
				irpUser.setMobile(getCellValue(row.getCell(7)).toString());
				irpUser.setEmail(getCellValue(row.getCell(8)).toString());
				irpUser.setExpertintro(getCellValue(row.getCell(9)).toString());
			} catch (Exception e) {
			}
			irpUsers.add(irpUser);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is!=null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return irpUsers;
	}
	
	private Object getCellValue(Cell _cell){
		Object value = null;
		if(_cell==null)
			return "";
		switch (_cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = _cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value = new Double(_cell.getNumericCellValue()).longValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = _cell.getBooleanCellValue();
			break;
		default:
			break;
		}
		return value;
	}
}
