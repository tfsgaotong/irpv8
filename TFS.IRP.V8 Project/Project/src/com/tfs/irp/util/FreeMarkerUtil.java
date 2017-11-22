package com.tfs.irp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.tfs.irp.document.entity.IrpDocument;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public final class FreeMarkerUtil {
	public static File readDocument2HTML(IrpDocument document) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		
		cfg.setDefaultEncoding("gbk"); 
		//加载freemarker模板文件
		String sTemplateDir = FreeMarkerUtil.class.getResource("/template").getPath();
		cfg.setDirectoryForTemplateLoading(new File(sTemplateDir.replaceAll("%20", " ")));
		//设置对象包装器  
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		//设计异常处理器  
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		//获取指定模板文件  
		Template template = cfg.getTemplate("exportDoc.html");
		
		//将数据写入到临时文件
		File file = File.createTempFile(document.getDoctitle(), ".html");
		Writer out = new OutputStreamWriter(new FileOutputStream(file),"gbk");
		
		//最后开始生成 
		template.process(document, out);
		file.deleteOnExit();
		return file;
	}
}
