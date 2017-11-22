package com.tfs.irp.util.importdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;

public class ImportData {

	private IrpDocumentDAO irpDocumentDAO;
	private IrpChnlDocLinkDAO irpChnlDocLinkDAO;
	
	public ImportData(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		irpDocumentDAO = (IrpDocumentDAO) ac.getBean("irpDocumentDAO");
		irpChnlDocLinkDAO = (IrpChnlDocLinkDAO) ac.getBean("irpChnl_Doc_LinkDAO");
	}

	public void findImportData() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select docid,docchannel,doctitle,doccontent,dochtmlcon,docpubhtmlcon,docabstract,dockeywords,subdoctitle,docpeople,docauthor,docpubtime,docreltime,crtime,docwordscount,docsourcename from wcmdocument where doctype=20 and dockind <> 39 and docstatus>0 order by docid asc";
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("开始导入数据...");
			while (rs.next()) {
				System.out.println("导入第[" + rs.getRow() + "]条数据");
				IrpDocumentWithBLOBs doc = new IrpDocumentWithBLOBs();
				doc.setDocid(rs.getLong("docid"));
				doc.setDoctitle(rs.getString("doctitle"));
				doc.setDoccontent(rs.getString("doccontent"));
				doc.setDochtmlcon(rs.getString("dochtmlcon"));
				doc.setAttachedcontent(rs.getString("docpubhtmlcon"));
				doc.setDocabstract(rs.getString("docabstract"));
				doc.setDockeywords(rs.getString("dockeywords"));
				doc.setSubdoctitle(rs.getString("subdoctitle"));
				doc.setDocpeople(rs.getString("docpeople"));
				doc.setDocauthor(rs.getString("docauthor"));
				doc.setDocpubtime(rs.getTimestamp("docpubtime") == null ? rs
						.getTimestamp("crtime") : rs.getTimestamp("docpubtime"));
				doc.setDocreltime(rs.getTimestamp("docreltime"));
				doc.setCrtime(rs.getTimestamp("crtime"));
				doc.setDocwordscount(rs.getLong("docwordscount"));
				doc.setDocsourcename(rs.getString("docsourcename"));

				// 固定值
				doc.setChannelid(64L);
				doc.setSiteid(2L);
				doc.setCruser("admin");
				doc.setCruserid(1L);
				doc.setDocstatus(IrpDocumentWithBLOBs.PUBLISH_STATUS);
				doc.setDoctype(IrpDocumentWithBLOBs.DOCTYPE_DOCUMENT);
				doc.setTransformname("A");
				try {
					irpDocumentDAO.insertSelective(doc);
					addChnlDocLink(doc);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("数据导入结束...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void addChnlDocLink(IrpDocumentWithBLOBs document)
			throws SQLException {
		IrpChnlDocLink link = new IrpChnlDocLink();
		link.setChnldocid(document.getDocid());
		link.setChannelid(document.getChannelid());
		link.setDocid(document.getDocid());
		link.setDocpubtime(document.getDocpubtime());
		link.setDoctitle(document.getDoctitle());
		link.setDocstatus(document.getDocstatus());
		link.setCruser(document.getCruser());
		link.setCruserid(document.getCruserid());
		link.setCrtime(document.getCrtime());
		link.setModal(document.getDoctype());
		link.setCruser(document.getCruser());
		link.setSiteid(document.getSiteid());
		link.setDockind(document.getDocoutupid());// 投稿 知识id
		link.setCompletescore(document.getCompletescore());// 完整度
		link.setCryscore(document.getCryscore());// 系数
		link.setDocchannel(document.getFlowpreoperationmark());// 加精数量
		irpChnlDocLinkDAO.insertSelective(link);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImportData importData = new ImportData();
		importData.findImportData();
	}
}
