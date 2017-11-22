package com.tfs.irp.attachedtype.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.util.ActionUtil;

public class IrpAttachedTypeAction extends ActionSupport{
	
		private IrpAttachedTypeService irpAttachedTypeService;
		
		private List<IrpAttachedType> attTypeList;
		
		private IrpAttachedType attachedType;
		
		private Long attachedTypeId;
		/**
		 * 列表文件类型
		 * @return
		 */
		public String allAttachedType(){
			attTypeList= irpAttachedTypeService.allAttachedTypes();
			return SUCCESS;
		}
		/**
		 * 查询某个类型
		 * @return
		 */
		public String attachedTypeInfo(){
			attachedType=irpAttachedTypeService.findDataByPrimaryKey(attachedTypeId);
			return SUCCESS;
		}
		/**
		 * 修改类型
		 * @return
		 */
		public void updateType(){
			int nCount=irpAttachedTypeService.updateType(attachedType);
			ActionUtil.writer(String.valueOf(nCount));
		}
		public IrpAttachedTypeService getIrpAttachedTypeService() {
			return irpAttachedTypeService;
		}

		public List<IrpAttachedType> getAttTypeList() {
			return attTypeList;
		}

		public IrpAttachedType getAttachedType() {
			return attachedType;
		}

		public void setIrpAttachedTypeService(
				IrpAttachedTypeService irpAttachedTypeService) {
			this.irpAttachedTypeService = irpAttachedTypeService;
		}

		public void setAttTypeList(List<IrpAttachedType> attTypeList) {
			this.attTypeList = attTypeList;
		}

		public void setAttachedType(IrpAttachedType attachedType) {
			this.attachedType = attachedType;
		}
		public Long getAttachedTypeId() {
			return attachedTypeId;
		}
		public void setAttachedTypeId(Long attachedTypeId) {
			this.attachedTypeId = attachedTypeId;
		}
		
		
}
