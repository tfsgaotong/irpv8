package com.tfs.irp.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public final class FileUtil {

	public static String OS_SEPARATOR = File.separator;

	private static boolean found = false;

	/**
	 * 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null, 这时应当查询属性
	 */
	private static String encoding = null;

	/**
	 * 复制文件或者目录,复制前后文件完全一样。
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void copyFile(String resFilePath, String distFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		if (resFile.isDirectory()) {
			FileUtils.copyDirectoryToDirectory(resFile, distFile);
		} else if (resFile.isFile()) {
			FileUtils.copyFileToDirectory(resFile, distFile, true);
		}
	}

	/**
	 * 删除一个文件或者目录
	 * 
	 * @param targetPath
	 *            文件或者目录路径
	 * @IOException 当操作发生异常时抛出
	 */
	public static void deleteFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		if (targetFile.isDirectory()) {
			FileUtils.deleteDirectory(targetFile);
		} else if (targetFile.isFile()) {
			targetFile.delete();
		}
	}

	/**
	 * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void moveFile(String resFilePath, String distFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		if (resFile.isDirectory()) {
			FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
		} else if (resFile.isFile()) {
			FileUtils.moveFileToDirectory(resFile, distFile, true);
		}
	}

	/**
	 * 重命名文件或文件夹
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param newFileName
	 *            重命名
	 * @return 操作成功标识
	 */
	public static boolean renameFile(String resFilePath, String newFileName) {
		String newFilePath = StringUtil.formatPath(StringUtil
				.getParentPath(resFilePath) + "/" + newFileName);
		File resFile = new File(resFilePath);
		File newFile = new File(newFilePath);
		return resFile.renameTo(newFile);
	}

	/**
	 * 读取文件或者目录的大小
	 * 
	 * @param distFilePath
	 *            目标文件或者文件夹
	 * @return 文件或者目录的大小，如果获取失败，则返回-1
	 */
	public static long genFileSize(String distFilePath) {
		File distFile = new File(distFilePath);
		if (distFile.isFile()) {
			return distFile.length();
		} else if (distFile.isDirectory()) {
			return FileUtils.sizeOfDirectory(distFile);
		}
		return -1L;
	}

	/**
	 * 判断一个文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 存在返回true，否则返回false
	 */
	public static boolean isExist(String filePath) {
		return new File(filePath).exists();
	}

	/**
	 * 本地某个目录下的文件列表（不递归）
	 * 
	 * @param folder
	 *            ftp上的某个目录
	 * @param suffix
	 *            文件的后缀名（比如.mov.xml)
	 * @return 文件名称列表
	 */
	public static String[] listFilebySuffix(String folder, String suffix) {
		IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
		IOFileFilter fileFilter2 = new NotFileFilter(
				DirectoryFileFilter.INSTANCE);
		FilenameFilter filenameFilter = new AndFileFilter(fileFilter1,
				fileFilter2);
		return new File(folder).list(filenameFilter);
	}

	/**
	 * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
	 * 
	 * @param res
	 *            原字符串
	 * @param filePath
	 *            文件路径
	 * @return 成功标记
	 */
	public static boolean string2File(String res, String filePath) {
		boolean flag = true;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			File distFile = new File(filePath);
			if (!distFile.getParentFile().exists())
				distFile.getParentFile().mkdirs();
			bufferedReader = new BufferedReader(new StringReader(res));
			bufferedWriter = new BufferedWriter(new FileWriter(distFile));
			char buf[] = new char[1024]; // 字符缓冲区
			int len;
			while ((len = bufferedReader.read(buf)) != -1) {
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 把文件压缩成zip格式
	 * 
	 * @param files
	 *            需要压缩的文件
	 * @param zipFilePath
	 *            压缩后的zip文件路径 ,如"D:/test/aa.zip";
	 */
	public static void compressFiles2Zip(File file, String zipFilePath) {
		compressFiles2Zip(file.listFiles(), zipFilePath);
	}

	/**
	 * 把文件压缩成zip格式
	 * 
	 * @param files
	 *            需要压缩的文件
	 * @param zipFilePath
	 *            压缩后的zip文件路径 ,如"D:/test/aa.zip";
	 */
	public static void compressFiles2Zip(File[] files, String zipFilePath) {
		if (files != null && files.length > 0) {
			if (isEndsWithZip(zipFilePath)) {
				ZipArchiveOutputStream zaos = null;
				try {
					File zipFile = new File(zipFilePath);
					zaos = new ZipArchiveOutputStream(zipFile);
					// Use Zip64 extensions for all entries where they are
					// required
					zaos.setUseZip64(Zip64Mode.AsNeeded);

					// 将每个文件用ZipArchiveEntry封装
					// 再用ZipArchiveOutputStream写到压缩文件中
					for (File file : files) {
						if (file != null) {
							ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
									file, file.getName());
							zaos.putArchiveEntry(zipArchiveEntry);
							InputStream is = null;
							try {
								is = new BufferedInputStream(
										new FileInputStream(file));
								byte[] buffer = new byte[1024 * 5];
								int len = -1;
								while ((len = is.read(buffer)) != -1) {
									// 把缓冲区的字节写入到ZipArchiveEntry
									zaos.write(buffer, 0, len);
								}
								// Writes all necessary data for this entry.
								zaos.closeArchiveEntry();
							} catch (Exception e) {
								throw new RuntimeException(e);
							} finally {
								if (is != null)
									is.close();
							}
						}
					}
					zaos.finish();
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (zaos != null) {
							zaos.close();
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	/**
	 * 把zip文件解压到指定的文件夹
	 * 
	 * @param zipFilePath
	 *            zip文件路径, 如 "D:/test/aa.zip"
	 * @param saveFileDir
	 *            解压后的文件存放路径, 如"D:/test/"
	 */
	public static void decompressZip(String zipFilePath, String saveFileDir) {
		if (isEndsWithZip(zipFilePath)) {
			File file = new File(zipFilePath);
			if (file.exists()) {
				InputStream is = null;
				// can read Zip archives
				ZipArchiveInputStream zais = null;
				try {
					is = new FileInputStream(file);
					zais = new ZipArchiveInputStream(is);
					ArchiveEntry archiveEntry = null;
					// 把zip包中的每个文件读取出来
					// 然后把文件写到指定的文件夹
					while ((archiveEntry = zais.getNextEntry()) != null) {
						// 获取文件名
						String entryFileName = archiveEntry.getName();
						// 构造解压出来的文件存放路径
						String entryFilePath = saveFileDir + entryFileName;
						byte[] content = new byte[(int) archiveEntry.getSize()];
						zais.read(content);
						OutputStream os = null;
						try {
							// 把解压出来的文件写到指定路径
							File entryFile = new File(entryFilePath);
							os = new BufferedOutputStream(new FileOutputStream(
									entryFile));
							os.write(content);
						} catch (IOException e) {
							throw new IOException(e);
						} finally {
							if (os != null) {
								os.flush();
								os.close();
							}
						}

					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (zais != null) {
							zais.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	/**
	 * 判断文件名是否以.zip为后缀
	 * 
	 * @param fileName
	 *            需要判断的文件名
	 * @return 是zip文件返回true,否则返回false
	 */
	private static boolean isEndsWithZip(String fileName) {
		boolean flag = false;
		if (fileName != null && !"".equals(fileName.trim())) {
			if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 获得文件扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String findFileExt(File file) {
		String sExtName = "";
		if (file.isFile() && file.exists()) {
			sExtName = findFileExt(file.getName());
		}
		return sExtName;
	}

	/**
	 * 获得文件扩展名
	 * 
	 * @param _sFileName
	 * @return
	 */
	public static String findFileExt(String _sFileName) {
		return _sFileName.substring((_sFileName.lastIndexOf('.') + 1));
	}

	/**
	 * 获得文件名
	 * 
	 * @param _sFileName
	 * @return
	 */
	public static String findFileName(String _sFileName) {
		String sFileName = "";
		if (_sFileName.indexOf(File.pathSeparatorChar) >= 0) {
			if (_sFileName.indexOf('?') >= 0) {
				sFileName = _sFileName.substring(
						(_sFileName.lastIndexOf(OS_SEPARATOR) + 1),
						_sFileName.lastIndexOf('?'));
			} else {
				sFileName = _sFileName.substring((_sFileName
						.lastIndexOf(OS_SEPARATOR) + 1));
			}
		} else if (_sFileName.indexOf('/') >= 0) {
			if (_sFileName.indexOf('?') >= 0) {
				sFileName = _sFileName.substring(
						(_sFileName.lastIndexOf('/') + 1),
						_sFileName.lastIndexOf('?'));
			} else {
				sFileName = _sFileName
						.substring((_sFileName.lastIndexOf('/') + 1));
			}
		}
		return sFileName;
	}

	/**
	 * 缩放图片
	 * 
	 * @param _file
	 * @param _x
	 * @param _y
	 * @param _width
	 * @param _height
	 */
	public static boolean zoomImg(File _file, int _x, int _y, int _width,
			int _height) {
		return zoomImg(_file, _file, _x, _y, _width, _height);
	}

	public static InputStream zoomImg(File _file, int _width, int _height) {
		ByteOutputStream bos = new ByteOutputStream();
		String sExtName = findFileExt(_file);
		try {
			Image img = ImageIO.read(_file);
			BufferedImage tag = new BufferedImage(_width, _height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(
					img.getScaledInstance(_width, _height, Image.SCALE_SMOOTH),
					0, 0, null);
			ImageIO.write(tag, sExtName, bos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bos.close();
		}
		return bos.newInputStream();
	}

	/**
	 * 缩放图片
	 * 
	 * @param _file
	 * @param zoomImg
	 * @param _x
	 * @param _y
	 * @param _width
	 * @param _height
	 */
	public static boolean zoomImg(File _file, File zoomImg, int _x, int _y,
			int _width, int _height) {
		boolean success = false;
		try {
			String sExtName = findFileExt(_file);
			// 对文件进行比例缩放
			BufferedImage bi = ImageIO.read(_file);
			Image image = bi.getScaledInstance(_width, _height,
					Image.SCALE_DEFAULT);
			ImageFilter cropFilter = new CropImageFilter(_x, _y, _width,
					_height);
			Image img = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(160, 160,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			// 输出为文件
			success = ImageIO.write(tag, sExtName, zoomImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * 
	 * @param _file
	 * @param _width
	 * @param _height
	 * @return
	 */
	public static boolean zoomImg(File _file, File _zoomFile, int _width,
			int _height) {
		boolean success = false;
		String sExtName = findFileExt(_file);
		try {
			Image img = ImageIO.read(_file);
			BufferedImage tag = new BufferedImage(_width, _height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(
					img.getScaledInstance(_width, _height, Image.SCALE_SMOOTH),
					0, 0, null);
			success = ImageIO.write(tag, sExtName, _zoomFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * 得到文件的MD5码,用于校验
	 * 
	 * @param file
	 * @param jpb
	 * @return
	 */
	public static String getMD5(File file, JProgressBar jpb) {
		FileInputStream fis = null;
		jpb.setMaximum((int) file.length());
		jpb.setValue(0);
		jpb.setString("正在计算:" + file.getName() + "的MD5值");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length = -1;
			int value = 0;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
				value += length;
				jpb.setValue(value);
			}
			return bytesToString(md.digest());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(File file) {
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return bytesToString(md.digest());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 得到文件的SHA码,用于校验
	 * 
	 * @param file
	 * @return
	 */
	public static String getSHA(File file) {
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			return bytesToString(md.digest());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @param jpb
	 * @return
	 */
	public static String getSHA(File file, JProgressBar jpb) {
		FileInputStream fis = null;
		jpb.setMaximum((int) file.length());
		jpb.setValue(0);
		jpb.setString("正在计算:" + file.getName() + "的MD5值");
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length = -1;
			int value = 0;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
				value += length;
				jpb.setValue(value);
			}
			return bytesToString(md.digest());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static String bytesToString(byte[] data) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] temp = new char[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
			temp[i * 2 + 1] = hexDigits[b & 0x0f];
		}
		return new String(temp);
	}

	public static String file2String(InputStream is, String en) {
		StringBuilder sb = new StringBuilder();
		String readline = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is, en));
			while (br.ready()) {
				readline = br.readLine();
				sb.append(readline);
			}
			br.close();
			is.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * wangduo
	 * 
	 * @param simgname
	 */
	public static void zoomImg(String simgname) {
		// ZOOMIMGRULER
		String imgafter = SysConfigUtil.getSysConfigValue("ZOOMIMGRULER");
		String[] imgarr = null;
		if (imgafter.contains("；")) {
			imgarr = imgafter.replaceAll(" ", "").replace("；", ";").split(";");
		} else {
			imgarr = imgafter.replaceAll(" ", "").split(";");
		}
		// 获得文件路径// 缩略图存放路径
		String sFilePath = SysFileUtil.getFilePathByFileName(simgname);
		// 获取临时文件目录
		String temppath = SysConfigUtil
				.getSysConfigValue(SysFileUtil.FILE_TYPE_TEMP_FILE);
		// 文件附件目录
		// String
		// filepath=SysConfigUtil.getSysConfigValue(SysFileUtil.FILE_TYPE_ATTACHED_FILE);
		if (sFilePath.contains(temppath)) {
			// 不压缩
		} else {
			//BufferedImage sourceImg = null;
			Image sourceImg=null;
			int width=0;
			int hight=0;
			try {
				//sourceImg = ImageIO.read(new FileInputStream(sFilePath));
				File input=new File(sFilePath);
				sourceImg=ImageIO.read(input);
				width	=sourceImg.getWidth(new ImageObserver() {
					
					@Override
					public boolean imageUpdate(Image img, int infoflags, int x, int y,
							int width, int height) {
						// TODO Auto-generated method stub
						return false;
					}
				});
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//			sourceImg.g
			 hight=sourceImg.getHeight(new ImageObserver() {
				
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y,
						int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			if (imgarr.length > 0) {
				for (int i = 0; i < imgarr.length; i++) {
					// 150X150
					String[] widthandheight = imgarr[i].split("X");
					if (width > Integer
							.parseInt(widthandheight[0])
							|| hight> Integer
									.parseInt(widthandheight[0])) {
						int index = sFilePath.lastIndexOf(".");
						StringBuffer zoomfile = new StringBuffer();
						zoomfile.append(sFilePath.substring(0, index) + "_"
								+ imgarr[i]);
						// 后缀1111.jpg 111_150X150.jpg
						zoomfile.append(sFilePath.substring(index,
								sFilePath.length()));
						File zoom = new File(zoomfile.toString());
						File _file = new File(sFilePath);
						String sExtName = FileUtil.findFileExt(_file);
						try {
							Image img = ImageIO.read(_file);
							BufferedImage tag = new BufferedImage(
									Integer.parseInt(widthandheight[0]),
									Integer.parseInt(widthandheight[0]),
									BufferedImage.TYPE_INT_RGB);
							tag.getGraphics()
									.drawImage(
											img.getScaledInstance(
													Integer.parseInt(widthandheight[0]),
													Integer.parseInt(widthandheight[0]),
													Image.SCALE_SMOOTH), 0, 0,
											null);
							ImageIO.write(tag, sExtName, zoom);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						// 如果图片尺寸小于150X150simgname
						// 新文件
						int index = sFilePath.lastIndexOf(OS_SEPARATOR);
						StringBuffer zoomfile = new StringBuffer();
						zoomfile.append(sFilePath.substring(0, index));
						// 新名字
						int indexname = simgname.lastIndexOf(".");
						StringBuffer zoomname = new StringBuffer();
						zoomname.append(simgname.substring(0, indexname) + "_"
								+ imgarr[i]);
						zoomname.append(simgname.substring(indexname,
								simgname.length()));
						// 创建 复制 重命名 移除
						try {
							FileUtil.copyFile(sFilePath, temppath
									+ File.separator + "zoom");
							FileUtil.renameFile(temppath + File.separator
									+ "zoom" + File.separator + simgname,
									zoomname.toString());
							FileUtil.copyFile(
									temppath + File.separator + "zoom"
											+ File.separator
											+ zoomname.toString(),
									zoomfile.toString());
							FileUtil.deleteFile(temppath + File.separator
									+ "zoom");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				// 如果配置为空生成_150X150
				int index = sFilePath.lastIndexOf(".");
				StringBuffer zoomfile = new StringBuffer();
				zoomfile.append(sFilePath.substring(0, index) + "_150X150");
				// 后缀1111.jpg 111_150X150.jpg
				zoomfile.append(sFilePath.substring(index, sFilePath.length()));
				File zoom = new File(zoomfile.toString());
				File _file = new File(sFilePath);
				String sExtName = FileUtil.findFileExt(_file);
				try {
					Image img = ImageIO.read(_file);
					BufferedImage tag = new BufferedImage(150, 150,
							BufferedImage.TYPE_INT_RGB);
					tag.getGraphics()
							.drawImage(
									img.getScaledInstance(150, 150,
											Image.SCALE_SMOOTH), 0, 0, null);
					ImageIO.write(tag, sExtName, zoom);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getFileEncoding(File file, nsDetector det)
			throws FileNotFoundException, IOException {
		det.Init(new nsICharsetDetectionObserver() {
			@Override
			public void Notify(String charset) {
				found = true;
				encoding = charset;
			}
		});
		BufferedInputStream imp = new BufferedInputStream(new FileInputStream(
				file));
		byte[] buf = new byte[1024];
		int len;
		boolean done = false;
		boolean isAscii = true;
		while ((len = imp.read(buf, 0, buf.length)) != -1) {
			if (isAscii)
				isAscii = det.isAscii(buf, len);
			if (!isAscii && !done)
				done = det.DoIt(buf, len, false);
		}
		det.DataEnd();
		if (isAscii) {
			encoding = "ISO-8859-1";
			found = true;
		}
		if (!found) {
			String prob[] = det.getProbableCharsets();
			if (prob.length > 0) {
				// 在没有发现情况下，则取第一个可能的编码
				encoding = prob[0];
			} else {
				return null;
			}
		}
		if ("GB2312".equalsIgnoreCase(encoding)) {
			encoding = "GBK";
		}
		return encoding;
	}

	/**
	 * 传入一个文件(File)对象，检查文件编码
	 * 
	 * @param file
	 *            File对象实例
	 * @return 文件编码，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileEncoding(File file)
			throws FileNotFoundException, IOException {
		return getFileEncoding(file, new nsDetector());
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param file
	 *            File对象实例
	 * @param languageHint
	 *            语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
	 *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
	 * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileEncoding(File file, int languageHint)
			throws FileNotFoundException, IOException {
		return getFileEncoding(file, new nsDetector(languageHint));
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileEncoding(String path)
			throws FileNotFoundException, IOException {
		return getFileEncoding(new File(path));
	}

	/**
	 * 获取文件的编码
	 * 
	 * @param path
	 *            文件路径
	 * @param languageHint
	 *            语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
	 *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileEncoding(String path, int languageHint)
			throws FileNotFoundException, IOException {
		return getFileEncoding(new File(path), languageHint);
	}
}
