package com.tfs.irp.util.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;

public class ImportFileDoc {
	
	private String FILE_ENCODE = "UTF-8";
	
	private File docFile;
	
	IrpDocumentService irpDocumentService;
	
	public ImportFileDoc(String _sFile){
		docFile = new File(_sFile);
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		irpDocumentService = (IrpDocumentService) ac.getBean("irpDocumentService");
	}
	
	public void importDoc() {
		System.out.println("开始导入...");
		if(docFile==null || !docFile.exists() || !docFile.isFile())
			return;
		BufferedReader br = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(docFile);
			isr = new InputStreamReader(fis, FILE_ENCODE);
			br = new BufferedReader(isr);
			String sLine;
			long nLineNum = 1;
			IrpDocumentWithBLOBs document;
			while ((sLine=br.readLine())!=null) {
				if(sLine==null || sLine.isEmpty())
					continue;
				document = new IrpDocumentWithBLOBs();
				document.setDoctitle(sLine);
				document.setDocabstract(sLine);
				document.setDoccontent(sLine+"\n"+sLine+"\n"+sLine);
				document.setDochtmlcon("<p>"+sLine+"</p>\n<p>"+sLine+"</p>\n<p>"+sLine+"</p>");
				if(nLineNum<2500000)
					document.setChannelid(11L);
				else if(nLineNum<5000000)
					document.setChannelid(12L);
				else if(nLineNum<7500000)
					document.setChannelid(13L);
				else
					document.setChannelid(14L);
				document.setCruser("admin");
				document.setCruserid(1L);
				document.setDocstatus(IrpDocument.PUBLISH_STATUS);
				try {
					irpDocumentService.addDocument(document, "", false, null, true, null, IrpDocument.DOCTYPE_DOCUMENT);
					System.out.println("第"+nLineNum+"条");
				} catch (Exception e) {
					e.printStackTrace();
				}
				nLineNum++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("导入完成...");
	}
	
	public static void main(String[] args) {
		ImportFileDoc ifd = new ImportFileDoc("F:\\数据\\xai.txt");
		ifd.importDoc();
	}
}
