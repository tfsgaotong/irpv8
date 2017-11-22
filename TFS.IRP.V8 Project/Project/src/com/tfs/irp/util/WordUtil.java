package com.tfs.irp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.POITextExtractor;
import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;

public class WordUtil {
	
	private File docFile;
	
	private String wordExt;
	
	//初始化构造
	public WordUtil(String _sFileName){
		//String filePath = SysFileUtil.getFilePathByFileName(_sFileName);
		docFile = new File(_sFileName);
		wordExt = FileUtil.findFileExt(_sFileName).toLowerCase();
	}
	
	/**
	 * 读取word里的属性到IrpDocument
	 * @param document
	 */
	public void readProperties2IrpDocument(IrpDocumentWithBLOBs document) {
		if("doc".equals(wordExt)){
			readWord03Properties(document);
		}else if ("docx".equals(wordExt)) {
			readWord07Properties(document);
		}
	}
	
	private void readWord03Properties(IrpDocumentWithBLOBs document){
		InputStream is = null;
		WordExtractor extractor = null;
		try {
			is = new FileInputStream(docFile);
			extractor = new WordExtractor(is);
			SummaryInformation si = extractor.getSummaryInformation();
			
			//读取word文件属性到IrpDocument
			//纯文本正文
			document.setDoccontent(extractor.getText());
			//标题
			document.setDoctitle(si.getTitle());
			//关键词
			String sKeyWords = si.getKeywords();
			document.setDockeywords(sKeyWords==null?"":sKeyWords.replaceAll(" ", ""));
			//摘要
			document.setDocabstract(si.getComments());
			//文章字数
			document.setDocwordscount(new Long(si.getCharCount()));
			extractor.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(extractor!=null){
				try {
					extractor.close();
					extractor = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void readWord07Properties(IrpDocumentWithBLOBs document){
		InputStream is = null;
		XWPFDocument docx = null;
		XWPFWordExtractor extractor = null;
		try {
			is = new FileInputStream(docFile);
			docx = new XWPFDocument(is);
			extractor = new XWPFWordExtractor(docx);
			CoreProperties cp = extractor.getCoreProperties();

			//读取word文件属性到IrpDocument
			//纯文本正文
			document.setDoccontent(extractor.getText());
			//标题
			document.setDoctitle(cp.getTitle());
			//关键词
			String sKeyWords = cp.getKeywords();
			document.setDockeywords(sKeyWords==null?"":sKeyWords.replaceAll(" ", ""));
			//摘要
			document.setDocabstract(cp.getDescription());
			//文章字数
			document.setDocwordscount(new Long(extractor.getExtendedProperties().getUnderlyingProperties().getCharacters()));
			
			extractor.close();
			is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(extractor!=null){
				try {
					extractor.close();
					extractor = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String readWordConn() {
		String sWordText = null;
		InputStream is = null;
		POITextExtractor extractor = null;
		try {
			is = new FileInputStream(docFile);
			if("doc".equals(wordExt)){
				extractor = new WordExtractor(is);
			}else if ("docx".equals(wordExt)) {
				extractor = new XWPFWordExtractor(new XWPFDocument(is));
			}
			if(extractor!=null)
				sWordText = extractor.getText();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(extractor!=null){
				try {
					extractor.close();
					extractor = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sWordText;
	}
	
}
