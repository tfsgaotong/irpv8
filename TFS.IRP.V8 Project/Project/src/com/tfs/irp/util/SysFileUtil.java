package com.tfs.irp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.file.service.IrpUserFileService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;

public class SysFileUtil {
	/**
	 * 上传文件
	 */
	public static final String FILE_TYPE_UPLOAD_FILE = "UP";
	/**
	 * 用户文件
	 */
	public static final String FILE_TYPE_USER_FILE = "UF";
	/**
	 * 临时文件
	 */
	public static final String FILE_TYPE_TEMP_FILE = "TF";
	/**
	 * 文件附件
	 */
	public static final String FILE_TYPE_ATTACHED_FILE = "AF";
	/**
	 * 图片附件
	 */
	public static final String FILE_TYPE_ATTACHED_PIC = "AP";
	/**
	 * 正文文件
	 */
	public static final String FILE_TYPE_DOC_FILE = "DF";

	/**
	 * 当前系统分隔符
	 */
	private static String separator = File.separator;

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _file
	 * @param _sFileType
	 * @return 成功返回文件名，失败返回NULL
	 */
	public static String saveFile(File _file, String _sFileType) {
		return saveFile(_file, _sFileType, false);
	}

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _saveUserFile
	 * @param _file
	 * @param _sFileType
	 * @return
	 */
	public static String saveFile(boolean _saveUserFile, File _file,
			String _sFileType) {
		return saveFile(_file, _sFileType, FileUtil.findFileExt(_file), false,
				_saveUserFile);
	}

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _file
	 * @param _sFileType
	 * @return 成功返回文件名，失败返回NULL
	 */
	public static String saveFile(File _file, String _sFileType,
			boolean _createNewFile) {
		return saveFile(_file, _sFileType, FileUtil.findFileExt(_file),
				_createNewFile, true);
	}

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _file
	 * @param _sFileType
	 * @return 成功返回文件名，失败返回NULL
	 */
	public static String saveFileDoc(File _file, String _sFileType,
			boolean addUserFileLog) {
		return saveFile(_file, _sFileType, FileUtil.findFileExt(_file), true,
				addUserFileLog);
	}

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _file
	 * @param _sFileType
	 * @param _sExtName
	 * @return 成功返回文件名，失败返回NULL
	 */
	public static String saveFile(File _file, String _sFileType,
			String _sExtName) {
		return saveFile(_file, _sFileType, _sExtName, false, true);
	}

	/**
	 * 根据文件类型保存文件
	 * 
	 * @param _file
	 * @param _sFileType
	 * @param _sExtName
	 * @param _isCreate
	 * @return 成功返回文件名，失败返回NULL
	 */
	public static String saveFile(File _file, String _sFileType,
			String _sExtName, boolean _createNewFile, boolean _saveUserFile) {
		// 获得当前时间
		Date date = new Date();
		// 获得文件类型存储目录
		String sSysDirectory = SysConfigUtil.getSysConfigValue(_sFileType);

		if (sSysDirectory == null || sSysDirectory.length() == 0) {
			return null;
		}
		// 获得保存文件夹路径
		StringBuffer sSaveDirectory = new StringBuffer(sSysDirectory);
		sSaveDirectory.append(separator)
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(separator)
				.append(DateUtils.getDateByFormat(date, "dd"));
		// 判断文件夹是否存在，如果不存在创建
		File directory = new File(sSaveDirectory.toString());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 生成文件名
		StringBuffer sFileName = new StringBuffer();
		sFileName.append(_sFileType)
				.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
				.append(randomNum()).append(".").append(_sExtName);
		String sNewFilePath = sSaveDirectory + separator + sFileName.toString();
		File newFile = new File(sNewFilePath);
		// 保存文件到目录中
		boolean succeed = false;
		// 是否创建新的文件
		if (_createNewFile) {
			try {
				FileUtils.copyFile(_file, newFile);
				succeed = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			succeed = _file.renameTo(newFile);
		}

		if (succeed) {
			// 判断是否是图片后缀BMP、JPG、JPEG、PNG、GIF。
			int index = sFileName.lastIndexOf(".");
			String hz = sFileName.substring(index + 1, sFileName.length());
			if (hz.equalsIgnoreCase("BMP") || hz.equalsIgnoreCase("JPG")
					|| hz.equalsIgnoreCase("JPEG")
					|| hz.equalsIgnoreCase("PNG") || hz.equalsIgnoreCase("GIF")) {
				// 生成压缩图
				FileUtil.zoomImg(sFileName.toString());
			}
		}

		if (succeed) {
			// 成功后保存文件类型信息,不保存临时文件信息
			if (_sFileType != null && !_sFileType.equals(FILE_TYPE_TEMP_FILE)
					&& _saveUserFile) {
				ApplicationContext ac = ApplicationContextHelper.getContext();
				IrpUserFileService irpUserFileService = (IrpUserFileService) ac
						.getBean("irpUserFileService");
				irpUserFileService.insertUserFile(sFileName.toString());
			}
			return sFileName.toString();
		} else {
			return null;
		}
	}
	
	/**
	 * word文件上传（知识库提取word内容）
	 * 
	 * @param _file
	 * @param _sFileType
	 * @param _sExtName
	 * @param _isCreate
	 * @return 成功返回路径，失败返回NULL
	 */
	public static List<Map<String,String>> saveWordFile(File _file, String _sFileType,
			String _sExtName, boolean _createNewFile, boolean _saveUserFile) {
		// 获得当前时间
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		Date date = new Date();
		// 获得文件类型存储目录
		String sSysDirectory = SysConfigUtil.getSysConfigValue(_sFileType);
		if (sSysDirectory == null || sSysDirectory.length() == 0) {
			return null;
		}
		// 获得保存文件夹路径
		StringBuffer sSaveDirectory = new StringBuffer(sSysDirectory);
		sSaveDirectory.append(separator)
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(separator)
				.append(DateUtils.getDateByFormat(date, "dd"));
		// 判断文件夹是否存在，如果不存在创建
		File directory = new File(sSaveDirectory.toString());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 生成文件名
		StringBuffer sFileName = new StringBuffer();
		sFileName.append(_sFileType)
				.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
				.append(randomNum()).append(".").append(_sExtName);
		String sNewFilePath = sSaveDirectory + separator + sFileName.toString();
		File newFile = new File(sNewFilePath);
		map.put("filepath", sNewFilePath);
		map.put("filename", sFileName.toString());
		list.add(map);
		// 保存文件到目录中
		boolean succeed = false;
		// 是否创建新的文件
		if (_createNewFile) {
			try {
				FileUtils.copyFile(_file, newFile);
				succeed = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			succeed = _file.renameTo(newFile);
		}
		if (succeed) {
			// 成功后保存文件类型信息,不保存临时文件信息
			if (_sFileType != null && !_sFileType.equals(FILE_TYPE_TEMP_FILE)
					&& _saveUserFile) {
				ApplicationContext ac = ApplicationContextHelper.getContext();
				IrpUserFileService irpUserFileService = (IrpUserFileService) ac
						.getBean("irpUserFileService");
				irpUserFileService.insertUserFile(sFileName.toString());
			}
			return list;
		} else {
			return null;
		}
	}
	
	public static String saveFile1(File _file, String _sFileType,
			String _sExtName, boolean _createNewFile, boolean _saveUserFile) {
		// 获得当前时间
		Date date = new Date();
		// 获得文件类型存储目录
		String sSysDirectory = SysConfigUtil.getSysConfigValue(_sFileType);

		if (sSysDirectory == null || sSysDirectory.length() == 0) {
			return null;
		}
		// 获得保存文件夹路径
		StringBuffer sSaveDirectory = new StringBuffer(sSysDirectory);
		sSaveDirectory.append(separator)
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(separator)
				.append(DateUtils.getDateByFormat(date, "dd"));
		// 判断文件夹是否存在，如果不存在创建
		File directory = new File(sSaveDirectory.toString());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		// 生成文件名
		StringBuffer sFileName = new StringBuffer();
		sFileName.append(_sFileType)
				.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
				.append(randomNum()).append(".").append(_sExtName);
		String sNewFilePath = sSaveDirectory + separator + sFileName.toString();
		File newFile = new File(sNewFilePath);
		// 保存文件到目录中
		boolean succeed = false;
		// 是否创建新的文件
		if (_createNewFile) {
			try {
				FileUtils.copyFile(_file, newFile);
				succeed = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			succeed = _file.renameTo(newFile);
		}

		if (succeed) {
			// 判断是否是图片后缀BMP、JPG、JPEG、PNG、GIF。
			int index = sFileName.lastIndexOf(".");
			String hz = sFileName.substring(index + 1, sFileName.length());
			if (hz.equalsIgnoreCase("BMP") || hz.equalsIgnoreCase("JPG")
					|| hz.equalsIgnoreCase("JPEG")
					|| hz.equalsIgnoreCase("PNG") || hz.equalsIgnoreCase("GIF")) {
				// 生成压缩图
				FileUtil.zoomImg(sFileName.toString());
			}
		}

		if (succeed) {
			// 成功后保存文件类型信息,不保存临时文件信息
			if (_sFileType != null && !_sFileType.equals(FILE_TYPE_TEMP_FILE)
					&& _saveUserFile) {
				ApplicationContext ac = ApplicationContextHelper.getContext();
				IrpUserFileService irpUserFileService = (IrpUserFileService) ac
						.getBean("irpUserFileService");
				irpUserFileService.insertUserFile1(sFileName.toString());
			}
			return sFileName.toString();
		} else {
			return null;
		}
	}
	/**
	 * 根据文件名获得文件本地路径
	 * 
	 * @param _sFileName
	 * @return 文件绝对路径,失败返回NULL
	 */
	public static String getFilePathByFileName(String _sFileName) {
		if (_sFileName == null || _sFileName.length() <= 2) {
			return "";
		}
		// 获得文件类型
		String sFileType = _sFileName.substring(0, 2);
		// 获得文件类型的路径
		String sSaveDirectory = SysConfigUtil.getSysConfigValue(sFileType);
		if (sSaveDirectory == null || sSaveDirectory.length() == 0) {
			return null;
		}
		// 获得文件绝对路径
		StringBuffer sFilePath = new StringBuffer();
		sFilePath.append(sSaveDirectory).append(separator)
				.append(_sFileName.substring(2, 8)).append(separator)
				.append(_sFileName.substring(8, 10)).append(separator)
				.append(_sFileName);
		return sFilePath.toString();
	}
	public static Long  getTypeIdByLastName(String sFileName) {
		String lastName=FileUtil.findFileExt(sFileName);
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpAttachedTypeService irpAttachedTypeService = (IrpAttachedTypeService) ac.getBean("irpAttachedTypeService");
		Long typeId = irpAttachedTypeService.findAttachedTypeIdByFileExt(lastName);
		return typeId;
	}
	public static boolean deleteZoomFile(String _sFileName){
		boolean success = false;
		String[] lastNames=null;
		String zoomStr=SysConfigUtil.getSysConfigValue("ZOOMIMGRULER");
		if(zoomStr!=null && zoomStr.length()>0){
			lastNames=zoomStr.split(";");
		}
		if(lastNames!=null && lastNames.length>0){
			for (int i = 0; i < lastNames.length; i++) {
				String zoomFile=ThumbnailPic.searchFileName(_sFileName, "_"+lastNames[i]);
				String sFileType = _sFileName.substring(0, 2);
				String sSaveDirectory = SysConfigUtil.getSysConfigValue(sFileType);
				if (sSaveDirectory == null || sSaveDirectory.length() == 0) {
					return success;
				}
				StringBuffer sFilePath = new StringBuffer();
				sFilePath.append(sSaveDirectory).append(separator)
						.append(_sFileName.substring(2, 8)).append(separator)
						.append(_sFileName.substring(8, 10)).append(separator)
						.append(zoomFile);
				File file = new File(sFilePath.toString());
				if (file.isFile() && file.exists()) {
					success = file.delete();
				} else {
					success = true;
				}
			}
		}
		return success;
	}
	public static boolean deleteFileByFileName(String _sFileName) {
		boolean success = false;
		// 获得文件类型
		String sFileType = _sFileName.substring(0, 2);
		// 获得文件类型的路径
		String sSaveDirectory = SysConfigUtil.getSysConfigValue(sFileType);
		if (sSaveDirectory == null || sSaveDirectory.length() == 0) {
			return success;
		}
		// 获得文件绝对路径
		StringBuffer sFilePath = new StringBuffer();
		sFilePath.append(sSaveDirectory).append(separator)
				.append(_sFileName.substring(2, 8)).append(separator)
				.append(_sFileName.substring(8, 10)).append(separator)
				.append(_sFileName);
		//图片的缩略图 
		Long typeId=getTypeIdByLastName(_sFileName);
		if(typeId.intValue()==IrpAttachedType.JPG_FIELD_NAME.intValue()){
			deleteZoomFile(_sFileName);
		}
		// 判断并删除文件
		File file = new File(sFilePath.toString());
		if (file.isFile() && file.exists()) {
			success = file.delete();
		} else {
			success = true;
		}
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpUserFileService irpUserFileService = (IrpUserFileService) ac
				.getBean("irpUserFileService");
		irpUserFileService.deleteUserFileByFileName(_sFileName);
		return success;
	}

	private static IrpSite findIrpSite() {
		ApplicationContext ac = ApplicationContextHelper.getContext();
		IrpSiteService irpSiteService = (IrpSiteService) ac
				.getBean("irpSiteService");
		List<IrpSite> sites = irpSiteService
				.findSitesBySiteType(IrpSite.SITE_TYPE_PUBLISH);
		if (sites == null || sites.size() == 0) {
			return null;
		}
		return sites.get(0);
	}

	public static String getSiteLogo() {
		String sPath = ServletActionContext.getRequest().getContextPath();
		String sLogo = "";
		IrpSite site = findIrpSite();
		if (site == null || site.getLogo() == null || site.getLogo().length() == 0) {
			sLogo = sPath + "/view/images/logo.png";
		} else {
			sLogo = sPath + "/file/readfile.action?fileName="+site.getLogo();
		}
		return sLogo;
	}

	public static String getSiteBanner() {
		String sPath = ServletActionContext.getRequest().getContextPath();
		String sBanner = "";
		IrpSite site = findIrpSite();
		if (site == null || site.getBaner() == null || site.getBaner().length() == 0) {
			sBanner = sPath + "/view/images/logo_head.png";
		} else {
			sBanner = sPath + "/file/readfile.action?fileName="+site.getBaner();
		}
		return sBanner;
	}

	/**
	 * 生成6位的随机数
	 * 
	 * @return
	 */
	private static int randomNum() {
		return new Random().nextInt(10000000);
	}

	/**
	 * 保存HTML正文中的网络图片到本地
	 * 
	 * @param _sHTMLCon
	 * @return
	 */
	public static String saveImgSrcPic(String _sHTMLCon) {
		//转换IMAGE
		_sHTMLCon = _sHTMLCon.replaceAll("<IMAGE", "<IMG");
		
		Pattern PATTERN = Pattern.compile(
				"<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = PATTERN.matcher(_sHTMLCon);
		while (matcher.find()) {
			String group = matcher.group(1);
			if (group == null) {
				continue;
			} else if (group.startsWith("'")) {
				group = group.substring(1, group.indexOf("'", 1));
			} else if (group.startsWith("\"")) {
				group = group.substring(1, group.indexOf("\"", 1));
			} else {
				group = group.split("\\s")[0];
			}
			if (group.toLowerCase().indexOf("http")==0) {
				String sFileName = saveNetFile(group, FILE_TYPE_DOC_FILE);
				String sImgUrl = ServletActionContext.getRequest()
						.getContextPath()
						+ "/file/readfile.action?fileName="
						+ sFileName;
				_sHTMLCon = _sHTMLCon.replaceAll(group, sImgUrl);
			}
		}
		return _sHTMLCon;
	}
	
	/**
	 * 保存网络图片到本地
	 * @param _sUrl
	 * @param _sFileType
	 * @return
	 */
	public static String saveNetFile(String _sUrl, String _sFileType) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(_sUrl);
		String sSaveFileName = "";
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null && entity.isStreaming()) {
					String sExtName = null;
					if(entity.getContentType()!=null){
						String[] sCType = entity.getContentType().getValue().split("/");
						if(sCType.length>=2){
							sExtName = sCType[1];
						}else{
							sExtName = FileUtil.findFileExt(_sUrl);
						}
					}else{
						sExtName = FileUtil.findFileExt(_sUrl);
					}
					// 获得当前时间
					Date date = new Date();
					// 获得文件类型存储目录
					String sSysDirectory = SysConfigUtil
							.getSysConfigValue(_sFileType);

					if (sSysDirectory == null || sSysDirectory.length() == 0) {
						return null;
					}
					// 获得保存文件夹路径
					StringBuffer sSaveDirectory = new StringBuffer(
							sSysDirectory);
					sSaveDirectory.append(separator)
							.append(DateUtils.getDateByFormat(date, "yyyyMM"))
							.append(separator)
							.append(DateUtils.getDateByFormat(date, "dd"));
					// 判断文件夹是否存在，如果不存在创建
					File directory = new File(sSaveDirectory.toString());
					if (!directory.exists()) {
						directory.mkdirs();
					}
					// 生成文件名
					StringBuffer sFileName = new StringBuffer();
					sFileName
							.append(_sFileType)
							.append(DateUtils.getDateByFormat(date, "yyyyMMdd"))
							.append(randomNum()).append(".").append(sExtName);
					String sNewFilePath = sSaveDirectory + separator
							+ sFileName.toString();
					sSaveFileName = sFileName.toString();
					File saveFile = new File(sNewFilePath);
					FileOutputStream output = new FileOutputStream(saveFile);
					InputStream input = entity.getContent();
					byte b[] = new byte[4096];
					int j = 0;
					while ((j = input.read(b)) != -1) {
						output.write(b, 0, j);
					}
					output.flush();
					output.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return sSaveFileName;
	}
}
