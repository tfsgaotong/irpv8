package com.tfs.irp.examanswer.web;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.examanswer.service.IrpExamAnswerService;

public class IrpExamAnswerAction extends ActionSupport {
	
	private IrpExamAnswerService irpExamAnswerService;

	public IrpExamAnswerService getIrpExamAnswerService() {
		return irpExamAnswerService;
	}

	public void setIrpExamAnswerService(IrpExamAnswerService irpExamAnswerService) {
		this.irpExamAnswerService = irpExamAnswerService;
	}

}
