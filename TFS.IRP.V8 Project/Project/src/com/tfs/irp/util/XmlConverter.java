package com.tfs.irp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;

public class XmlConverter {
	
	private File xmlFile;
	
	public XmlConverter(String _sFileName) {
		String filePath = SysFileUtil.getFilePathByFileName(_sFileName);
		xmlFile = new File(filePath);
	}
	
	public List<IrpDocumentWithBLOBs> readXmlFile() throws DocumentException {
		List<IrpDocumentWithBLOBs> irpDocuments = new ArrayList<IrpDocumentWithBLOBs>();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlFile);
		Element root = document.getRootElement();
		for (Iterator iter = root.elementIterator("REC"); iter.hasNext(); ) {
			Element element = (Element) iter.next();
			IrpDocumentWithBLOBs irpDocument = new IrpDocumentWithBLOBs();
			irpDocument.setDoctitle(element.elementText("IR_URLTITLE"));
			irpDocument.setDoccontent(element.elementText("IR_URLCONTENT"));
			irpDocument.setDochtmlcon(element.elementText("IR_CONTENT"));
			irpDocument.setDockeywords(element.elementText("IR_KEYWORDS"));
			irpDocument.setDocabstract(element.elementText("IR_ABSTRACT"));
			irpDocuments.add(irpDocument);
		}
		return irpDocuments;
	}
}
