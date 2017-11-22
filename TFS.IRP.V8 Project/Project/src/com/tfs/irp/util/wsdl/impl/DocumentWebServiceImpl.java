package com.tfs.irp.util.wsdl.impl;

import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.wsdl.IDocumentWebService;

public class DocumentWebServiceImpl implements IDocumentWebService {
	
	private IrpDocumentService irpDocumentService;

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	@Override
	public long addDocument(IrpDocumentWithBLOBs document) {
		long nDocId = TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);
		document.setDocid(nDocId);
		int nCount = irpDocumentService.addDocument(document, "", false, null, true, null, IrpDocument.DOCTYPE_DOCUMENT);
		if(nCount>0){
			return nDocId;
		}else{
			return 0L;
		}
	}
}
