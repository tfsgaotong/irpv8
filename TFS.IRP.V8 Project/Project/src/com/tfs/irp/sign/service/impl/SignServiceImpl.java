package com.tfs.irp.sign.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.tfs.irp.sign.dao.IrpSignInfoDAO;
import com.tfs.irp.sign.entity.IrpSignInfo;
import com.tfs.irp.sign.entity.IrpSignInfoExample;
import com.tfs.irp.sign.entity.IrpSignInfoExample.Criteria;
import com.tfs.irp.sign.service.SignService;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SignUtil;

public class SignServiceImpl implements SignService {
	private IrpSignInfoDAO signDao;

	/**
	 * userId状态值
	 */
	private Long SignidState;

	/**
	 * sheet计数器
	 */
	private int sheetNum = 0;

	public Long getSignidState() {
		return SignidState;
	}

	public void setSignidState(Long signidState) {
		SignidState = signidState;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public IrpSignInfoDAO getSignDao() {
		return signDao;
	}

	public void setSignDao(IrpSignInfoDAO signDao) {
		this.signDao = signDao;
	}

	@Override
	public List<IrpSignInfo> signInfo(PageUtil page, IrpSignInfoExample example) {
		return signDao.signInfo(page, example);
	}

	@Override
	public int getDataCount(List<Long> longList, String beginTime,
			String finishTime) {
		try {
			IrpSignInfoExample example = new IrpSignInfoExample();
			Criteria criteria = example.createCriteria();
			if (longList != null && longList.size() > 0) {
				criteria.andSignidIn(longList);
			}
			if (beginTime != null && !"".equals(beginTime)
					&& finishTime != null && !"".equals(finishTime)) {
				criteria.andSignintimeBetween(
						DateUtils.getDateByStrToYMD(beginTime),
						DateUtils.getDateByStrToYMD(finishTime));
			} else if (beginTime != null && !"".equals(beginTime)) {
				criteria.andSignintimeGreaterThanOrEqualTo(DateUtils
						.getDateByStrToYMD(beginTime));
			} else if (finishTime != null && !"".equals(finishTime)) {
				criteria.andSignintimeLessThanOrEqualTo(DateUtils
						.getDateByStrToYMD(finishTime));
			}
			return signDao.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<IrpSignInfo> selectByExample(IrpSignInfoExample example) {
		try {
			return signDao.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertSelect(IrpSignInfo userSignInfo) {
		try {
			signDao.insertSelective(userSignInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateByPrimaryKeySelective(IrpSignInfo irpSignInfo) {
		try {
			signDao.updateByPrimaryKeySelective(irpSignInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateByExampleSelective(IrpSignInfo irpSignInfo,
			IrpSignInfoExample example) {
		try {
			signDao.updateByExampleSelective(irpSignInfo, example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 创建文件夹;
	 * 
	 * @param path
	 * @return
	 */
	public String createFile(String path) {
		File file = new File(path);
		// 判断文件是否存在;
		if (!file.exists()) {
			// 创建文件;
			boolean bol = file.mkdirs();
			if (bol) {
				// System.out.println(path+" 路径创建成功!");
			} else {
				// System.out.println(path+" 路径创建失败!");
			}
		} else {
			// System.out.println(path+" 文件已经存在!");
		}
		return path;
	}

	

	/**
	 * 生成.zip文件;
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void craeteZipPath(String path,String zipName) throws IOException {
		//System.out.println("进入生成zip方法");
		ZipOutputStream zipOutputStream = null;
		File file = new File(path
				+ zipName
				+ ".zip");
		zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(file)));
		File[] files = new File(path).listFiles();
		FileInputStream fileInputStream = null;
		byte[] buf = new byte[1024];
		int len = 0;
		if (files != null && files.length > 0) {
			for (File excelFile : files) {
				// 测试使用 记得删除
				// System.out.println("哈哈哈我在第314行");
				String fileName = excelFile.getName();
				fileInputStream = new FileInputStream(excelFile);
				// 放入压缩zip包中;
				zipOutputStream.putNextEntry(new ZipEntry("/" + fileName));

				// 读取文件;
				while ((len = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, len);
				}
				// 关闭;
				zipOutputStream.closeEntry();
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			}
		}

		if (zipOutputStream != null) {
			zipOutputStream.close();
		}
	}

	/**
	 * 删除目录下所有的文件;
	 * 
	 * @param path
	 */
	public boolean deleteExcelPath(File file) {
		String[] files = null;
		if (file != null) {
			files = file.list();
		}

		if (file.isDirectory()) {
			for (int i = 0; i < files.length; i++) {
				boolean bol = deleteExcelPath(new File(file, files[i]));
				if (bol) {
					// System.out.println("删除成功!");
				} else {
					// System.out.println("删除失败!");
				}
			}
		}
		return file.delete();
	}

	/**
	 * 将签到信息写入Excel并压缩为Zip文件
	 */
	@Override
	public void exportAllSignInfoToZip(List<IrpSignInfo> list,String getPath,String fileName) {
		try {
			//创建文件路径
			String path=getPath+"/sign";
			// 创建文件夹;
			createFile(path);
			// 创建excel文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path+"/"
					+ fileName+"_"+".xls"));
			// 接收返回的isend,用来判断是否循环到最后
			int count = 0;
			// 初始化用户id
			this.SignidState = list.get(0).getSignid();
			if (list != null && list.size() != 0) {
				do {
					count = createAllSignToExcel(list, workbook);
				} while (count != -1);
			}
			// 写入Excel表格中;
			workbook.write();
			// 关闭流;
			workbook.close();
			// 生成.zip文件;
			craeteZipPath(path,fileName);
			// 删除目录下所有的文件;
			File file = new File(path);
			// 删除文件;
			deleteExcelPath(file);
			// 重新创建文件;
			// file.mkdirs();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 循环写入Excel
	 * 
	 * @param signInfo
	 * @return
	 */
	private int createAllSignToExcel(List<IrpSignInfo> signInfo,
			WritableWorkbook workbook) throws Exception {
		// 获得当前用户的trueName
		Map uid = new HashMap();
		uid.put("userid", SignidState);
		String userName = signDao.findUserTrueNameById(uid);
		// 循环中计数器
		int count = 0;
		// 判断是不是最后一个 1代表不是最后一个, -1代表是最后一个
		int isend = 1;
		// 是否循环过数据
		boolean isexport = false;
		// 创建第一个sheet文件;
		WritableSheet sheet = workbook.createSheet(userName, sheetNum);
		// 设置默认宽度;
		sheet.getSettings().setDefaultColumnWidth(20);
		// 设置字体,正常状态(黑色);
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat1.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat1.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 设置字体,异常状态(红色)
		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);

		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
		// 设置背景颜色;
		// cellFormat1.setBackground(Colour.BLUE_GREY);
		// 设置边框;
		cellFormat2.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置自动换行;
		cellFormat2.setWrap(true);
		// 设置文字居中对齐方式;
		cellFormat2.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
		Label label = new Label(0, 0, "日期", cellFormat1);
		Label label1 = new Label(1, 0, "签到时间", cellFormat1);
		Label label2 = new Label(2, 0, "签退时间", cellFormat1);
		Label label3 = new Label(3, 0, "是否迟到", cellFormat1);
		Label label4 = new Label(4, 0, "是否早退", cellFormat1);
		Label label5 = new Label(5, 0, "去向", cellFormat1);
		Label label6 = new Label(6, 0, "备注", cellFormat1);

		sheet.addCell(label);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		sheet.addCell(label4);
		sheet.addCell(label5);
		sheet.addCell(label6);
		// 行数计数器
		int i = 0;

		for (; count < signInfo.size(); count++) {
			IrpSignInfo isi = signInfo.get(count);
			if (isi.getSignid().equals(SignidState) && !isexport) {

				Label lab1 = new Label(0, i + 1, SignUtil.getStringDay(isi
						.getSignintime()), cellFormat1);
				Label lab2 = new Label(1, i + 1, SignUtil.getStringDate(isi
						.getSignintime()), cellFormat1);
				Label lab3 = new Label(2, i + 1, SignUtil.getStringDate(isi
						.getSignouttime()), cellFormat1);
				Label lab4 = new Label(3, i + 1, isi.getSigninstatus(),
						cellFormat1);
				Label lab5 = new Label(4, i + 1, isi.getSignoutstatus(),
						cellFormat1);
				Label lab6 = new Label(5, i + 1, isi.getSignindirection(),
						cellFormat1);
				Label lab7 = new Label(6, i + 1, isi.getSigncomment(),
						cellFormat1);
				sheet.addCell(lab1);
				sheet.addCell(lab2);
				sheet.addCell(lab3);
				sheet.addCell(lab4);
				sheet.addCell(lab5);
				sheet.addCell(lab6);
				sheet.addCell(lab7);
				i++;
				if (count == (signInfo.size() - 1)) {
					isend = -1;
				}
				if (isend != -1
						&& !signInfo.get(count + 1).getSignid()
								.equals(SignidState)) {
					isexport = true;
				}

			} else if (isexport && !isi.getSignid().equals(SignidState)) {
				SignidState = isi.getSignid();
				break;
			}
			sheetNum++;
		}
		return isend;
	}

	@Override
	public List<IrpSignInfo> signInfo(IrpSignInfoExample example) {
		List<IrpSignInfo> list=signDao.signInfo(example);
		return list;
	}

}
