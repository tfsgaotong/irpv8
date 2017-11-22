package com.tfs.irp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocConverter {
	private String PDF2SWF_PATH;
	private String OPENOFFICE_URI;
	private int OPENOFFICE_POST;
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	private File htmlFile;
	private String readFileUri;

	public DocConverter(String _sFileName) {
		init(_sFileName);
		PDF2SWF_PATH = SysConfigUtil.getSysConfigValue("PDF2SWF_PATH")
				+ FileUtil.OS_SEPARATOR + "pdf2swf";
		OPENOFFICE_URI = SysConfigUtil.getSysConfigValue("OPENOFFICE_URI");
		OPENOFFICE_POST = SysConfigUtil.getSysConfigNumValue("OPENOFFICE_POST");
	}

	/*
	 * 初始化 @param fileString
	 */
	private void init(String _sFileName) {
		String filePath = SysFileUtil.getFilePathByFileName(_sFileName);
		fileName = filePath.substring(0, filePath.lastIndexOf("."));
		docFile = new File(filePath);
		pdfFile = new File(fileName + ".pdf");
		swfFile = new File(fileName + ".swf");
		htmlFile = new File(fileName + ".html");
		readFileUri = ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName=";
	}

	public String doc2html(int _nIsRemove) {
		String sHtmlContent = null;
		if (docFile.exists()) {
			if (!htmlFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(
						OPENOFFICE_URI, OPENOFFICE_POST);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, htmlFile);
					sHtmlContent = getHtmlContent();
				} catch (ConnectException e) {
					e.printStackTrace();
				} finally{
					if (connection != null) {
						connection.disconnect();
						connection = null;
					}
				}
			} else {
				sHtmlContent = getHtmlContent();
				htmlFile.delete();
			}
			sHtmlContent = clearFormat(sHtmlContent, _nIsRemove==1);
		}
		return sHtmlContent;
	}

	private String getHtmlContent() {
		StringBuffer htmlSb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					htmlFile), "GBK"));
			while (br.ready()) {
				htmlSb.append(br.readLine());
			}
			br.close();
			// 删除临时文件
			if (htmlFile.exists()) {
				htmlFile.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String htmlStr = htmlSb.toString();
		return htmlStr;
	}

	/**
	 * 清除一些不需要的html标记
	 * 
	 * @param htmlStr
	 *            带有复杂html标记的html语句
	 * @return 去除了不需要html标记的语句
	 */
	private String clearFormat(String htmlStr, boolean removeStyle) {
		// 获取body内容的正则
		String bodyReg = "<BODY .*</BODY>";
		Pattern bodyPattern = Pattern.compile(bodyReg);
		Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
		if (bodyMatcher.find()) {
			// 获取BODY内容，并转化BODY标签为DIV
			htmlStr = bodyMatcher.group().replaceFirst("<BODY", "<DIV")
					.replaceAll("</BODY>", "</DIV>");
		}
		// 调整图片地址
		htmlStr = htmlStr.replaceAll("<IMG SRC=\"", "<IMG SRC=\"" + readFileUri);
		
		//是否清楚样式
		if(removeStyle){
			// 把<P></P>转换成</div></div>并删除样式
			htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
			// 删除不需要的标签
			htmlStr = htmlStr.replaceAll("<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>","");
			// 删除不需要的属性
			htmlStr = htmlStr.replaceAll("<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>","<$1$2>");
		}
		return htmlStr;
	}

	/*
	 * 转为PDF @param file
	 */
	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(
						OPENOFFICE_URI, OPENOFFICE_POST);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(
							connection);
					if (FileUtil.findFileExt(docFile).equalsIgnoreCase("txt")) {
						String sCharset = FileUtil.getFileEncoding(docFile);
						if (sCharset.equalsIgnoreCase("gbk")) {
							DocumentFormat txt = new DocumentFormat(
									"Plain Text", DocumentFamily.TEXT,
									"text/plain", "txt");
							txt.setImportOption("FilterName", "Text (encoded)");
							txt.setImportOption("FilterOptions", sCharset
									+ ",CRLF");
							txt.setExportFilter(DocumentFamily.TEXT,
									"Text (encoded)");
							txt.setExportOption(DocumentFamily.TEXT,
									"FilterOptions", sCharset + ",CRLF");
							DocumentFormat pdf = new DocumentFormat(
									"Portable Document Format",
									"application/pdf", "pdf");
							pdf.setExportFilter(DocumentFamily.DRAWING,
									"draw_pdf_Export");
							pdf.setExportFilter(DocumentFamily.PRESENTATION,
									"impress_pdf_Export");
							pdf.setExportFilter(DocumentFamily.SPREADSHEET,
									"calc_pdf_Export");
							pdf.setExportFilter(DocumentFamily.TEXT,
									"writer_pdf_Export");
							converter.convert(docFile, txt, pdfFile, pdf);
						} else {
							converter.convert(docFile, pdfFile);
						}
					} else {
						converter.convert(docFile, pdfFile);
					}
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					if (connection != null) {
						connection.disconnect();
						connection = null;
					}
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}

	/*
	 * 转换成swf
	 */
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				try {
					Process p = r
							.exec(PDF2SWF_PATH
									+ " "
									+ pdfFile.getPath()
									+ " -o "
									+ swfFile.getPath()
									+ " -T 9 -t -z -s storeallcharacters -s poly2bitmap");
					loadStream(p.getInputStream());
					if (pdfFile.exists()
							&& !FileUtil.findFileExt(docFile).equalsIgnoreCase(
									"pdf")) {
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				System.out.println("****pdf不存在，无法转换****");
			}
		} else {
			System.out.println("****swf已存在不需要转换****");
		}
	}

	private void loadStream(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try {
			while ((reader.read()) != -1)
				;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 转换主方法
	 */
	public boolean conver() {
		if (swfFile.exists()) {
			return true;
		}
		try {
			doc2pdf();
			pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 返回文件路径 @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}
	}

	/*
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath) {
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"),
					fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}
}