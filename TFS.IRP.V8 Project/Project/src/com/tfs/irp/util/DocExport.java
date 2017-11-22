package com.tfs.irp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocExport {
	
	private static Configuration configuration = null;  
    //这里注意的是利用DocExport的类加载器动态获得模板文件的位置   
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        String path = SysConfigUtil.getSysConfigValue("WORDTEMPLATEOPATH");
        try {
            configuration.setDirectoryForTemplateLoading(new File(path));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
   }   
    private DocExport() {  
        throw new AssertionError();  
    }  
  
    public static InputStream exportMillCertificateWord(Map map) throws IOException {  
        Template freemarkerTemplate = configuration.getTemplate("doc.ftl");  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类的createDoc方法生成Word文档  
            file = createDoc(map,freemarkerTemplate);
            InputStream is = null;
            is = new FileInputStream(file);
            return is; 
        } finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();
            if(file!= null) file.delete();
        }  
    }  
  
    private static File createDoc(Map<?, ?> dataMap, Template template) {  
        String name =  "test.doc";  
        File f = new File(name);  
        Template t = template;  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
}
