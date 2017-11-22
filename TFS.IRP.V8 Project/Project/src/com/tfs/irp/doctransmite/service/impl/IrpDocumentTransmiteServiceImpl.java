package com.tfs.irp.doctransmite.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.entity.IrpConfigExample;
import com.tfs.irp.doctransmite.dao.IrpDocumentTransmiteDAO;
import com.tfs.irp.doctransmite.entity.IrpDocumentTransmite;
import com.tfs.irp.doctransmite.service.IrpDocumentTransmiteService;
import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpDocumentTransmiteServiceImpl implements IrpDocumentTransmiteService {
	private IrpDocumentDAO irpDocumentDAO;
	private IrpChnlDocLinkDAO  irpChnl_Doc_LinkDAO;
	private IrpDocumentTransmiteDAO  irpDocumentTransmiteDAO;
	private IrpMessageContentService irpMessageContentService;
	 
	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}
	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}
	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}
	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}
	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}
	public IrpDocumentTransmiteDAO getIrpDocumentTransmiteDAO() {
		return irpDocumentTransmiteDAO;
	}
	public void setIrpDocumentTransmiteDAO(
			IrpDocumentTransmiteDAO irpDocumentTransmiteDAO) {
		this.irpDocumentTransmiteDAO = irpDocumentTransmiteDAO;
	}
	
	@Override
	public int addDocTrans(List<Long> _focusids, String _summary,Long _docid) {
		// TODO Auto-generated method stub
		int nCount=0; 
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpDocumentTransmite documentTransmite=new IrpDocumentTransmite();
			documentTransmite.setFromuserid(irpUser.getUserid());
			documentTransmite.setCrtime(new Date());
			documentTransmite.setSummary(_summary);
			documentTransmite.setType(1L);//默认同事转发
			documentTransmite.setDocid(_docid);
			for (int i = 0; i < _focusids.size(); i++) {  
				//修改文档表的转发次数
				documentTransmite.setTouserid(_focusids.get(i)); 
				IrpDocumentWithBLOBs documentWithBLOBs=irpDocumentDAO.selectByPrimaryKey(_docid);
				if(documentWithBLOBs!=null ){
					documentWithBLOBs.setTranscount(documentWithBLOBs.getTranscount()+1);
					irpDocumentDAO.updateByPrimaryKey(documentWithBLOBs);
				}
				//修改文档连接表的转发次数
				IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
				chnlDocLinkExample.createCriteria().andDocidEqualTo(_docid);
				List<IrpChnlDocLink> chnlDocLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
				if(chnlDocLinks!=null && chnlDocLinks.size()>0){
					for (int j = 0; j < chnlDocLinks.size(); j++) {
						IrpChnlDocLink chnlDocLink=chnlDocLinks.get(j);
						chnlDocLink.setTranscount(chnlDocLink.getTranscount().longValue()+1);
						irpChnl_Doc_LinkDAO.updateByPrimaryKeySelective(chnlDocLink);
					} 
				} 
				//给这些转发的人缘发送私信
				String cValue=SysConfigUtil.getSysConfigValue("TRANSMITE_ADD_BOLG"); 
				if(cValue!=null && cValue.length()>0){ 
					cValue=cValue.replace("user", LoginUtil.getUserNameString(irpUser));
                	Date date=new Date();
                	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                	cValue=cValue.replace("#time#",dateFormat.format(date)); 
                	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
                	String href="<a href='"+path+"/document/document_detail.action?docid="+documentWithBLOBs.getDocid()+"'  class='linkbh14' ><strong>”"+documentWithBLOBs.getDoctitle()+"“</strong></a>";
                	cValue=cValue.replace("#doctitle#",href);  
        	    	cValue=cValue.replace("#content#",_summary); 
                	IrpMessageContent content=new IrpMessageContent();  
                	content.setFromUid(_focusids.get(i));   
                	content.setContent(cValue);
                	irpMessageContentService.addMessageContent(content);	//私信， 
                	
                	 irpDocumentTransmiteDAO.insertSelective(documentTransmite); 
				}
				nCount++;
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return nCount;
	}
	
}
