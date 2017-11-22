package com.tfs.irp.chnl_doc_link.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.Random;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder; 

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;

public class SvgImageAction extends ActionSupport {
	private float width;
	private float scale;
	private String filename;
	private String type;
	private String svg;
	private InputStream inputStream;

	public String  downImage() {
		try {
			// 获得当前时间
			Date date = new Date();
			String sSysDirectory = SysConfigUtil.getSysConfigValue(SysFileUtil.FILE_TYPE_TEMP_FILE);//临时文件夹D:\apache\tempfile
			if (sSysDirectory == null || sSysDirectory.length() == 0) {
				return ERROR;
			}
			//将文件重命名为用户名年月日时分秒形式的
			StringBuffer sSaveDirectory = new StringBuffer();
			sSaveDirectory.append(LoginUtil.getLoginUser().getUsername())
				.append(DateUtils.getDateByFormat(date, "yyyyMM"))
				.append(DateUtils.getDateByFormat(date, "dd"))
				.append(new Random().nextInt(10000000));//随机
			filename=sSaveDirectory.toString();
			File file = null;    
			if (svg != null && type != null) {
				svg = svg.replaceAll(":rect", "rect");
				String ext = "";
				Transcoder t = null;
				if (type.equals("image/png")) {
					ext = "png";
					String pngFileName=sSysDirectory+"//"+filename+"."+ext;     
					t = new PNGTranscoder();
					file=new File(pngFileName);
				} else if (type.equals("image/jpeg")) {
					ext = "jpeg";
					String jpegFileName=sSysDirectory+"//"+filename+"."+ext;
					t = new JPEGTranscoder();
					file=new File(jpegFileName);
				} else if (type.equals("application/pdf")) {
					ext = "pdf";
					String jpegFileName=sSysDirectory+"//"+filename+"."+ext;
					file=new File(jpegFileName);
					t=(Transcoder) new PDFTranscoder();
				}  
				filename=filename+"."+ext;
				if (t != null && ext!="svg") {
					OutputStream out=new FileOutputStream(file);
					inputStream=new FileInputStream(file);
					TranscoderInput input = new TranscoderInput(new StringReader(svg));
					TranscoderOutput output = new TranscoderOutput(out);
					t.addTranscodingHint(JPEGTranscoder.KEY_WIDTH, width); // 设置宽度
					try {
						t.transcode(input, output);
					} catch (TranscoderException e) {
						e.printStackTrace();
					}
					out.flush();
					out.close();
				}else{
					OutputStream output = new FileOutputStream(file);
					output.write(svg.getBytes());   
					inputStream=new FileInputStream(file);
		            output.flush();     
		            output.close(); 
				}
			} else {
				type = "text/html";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public float getWidth() {
		return width;
	}

	public float getScale() {
		return scale;
	}

	public String getFilename() {
		return filename;
	} 

	public void setWidth(float width) {
		this.width = width;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getType() {
		return type;
	}

	public String getSvg() {
		return svg;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSvg(String svg) {
		this.svg = svg;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
