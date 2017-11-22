package com.tfs.irp.util.wsdl;

import javax.jws.WebService;

import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;

@WebService
public interface IDocumentWebService {
	public long addDocument(IrpDocumentWithBLOBs document);
}
