package com.tfs.irp.solr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.solr.service.TRSSearchService;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.trs.client.TRSConnection;
import com.trs.client.TRSConstant;
import com.trs.client.TRSException;
import com.trs.client.TRSResultSet;

public class TRSSearchServiceImpl implements TRSSearchService{
	
	private TRSConnection conn = null;
	private TRSResultSet rs = null;

	/**
	 * 根据输入的关键字 从需要检索的字段中检索 分页
	 * 
	 * @param pageutil
	 *            分页类
	 * @param keyword
	 *            检索关键词
	 * @param strWhere
	 *            检索表或视图
	 * @param sessionid
	 *            wcm登录的
	 * @param orderBy
	 *            显示排序
	 * @return
	 */
	@Override
	public List<IrpDocumentWithBLOBs> selectDocbypage(PageUtil pageutil,String strSources,String keyword, String strWhere, String orderBy) {
		List<IrpDocumentWithBLOBs> list = null;
		try {
			String HOSE = SysConfigUtil.getSysConfigValue("strServerHost");
			String POST = SysConfigUtil.getSysConfigValue("strServerPort");
			String UNAME = SysConfigUtil.getSysConfigValue("strUserName");
			String UPWD = SysConfigUtil.getSysConfigValue("strPassWord");
			String OPTIONS = SysConfigUtil.getSysConfigValue("strOptions");
			conn = new TRSConnection();
			conn.connect(HOSE, POST, UNAME, UPWD, OPTIONS);
			if (orderBy.trim().equalsIgnoreCase("") || orderBy == null) {
				orderBy = "RELEVANCE";
			}
			rs = conn.executeSelect(strSources, keyword, orderBy, "", strWhere, 0,TRSConstant.TCE_OFFSET, false);
			rs.setReadOptions(TRSConstant.TCE_OFFSET,
					"DOCID2,DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRTIME,CRUSER,CRUSERID", ";");
			if (rs.getRecordCount() > 0) {
				//加载list
				list = loadTRSResultSet(rs, pageutil);
			}
		} catch (TRSException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			rs = null;
			conn = null;
		}
		return list;
	}
	/***
	 * 检索微知
	 * @param pageutil
	 * @param strSources
	 * @param keyword
	 * @param strWhere
	 * @param orderBy
	 * @return
	 */
	@Override
	public List<IrpMicroblog> selectMicroblogbypage(PageUtil pageutil,String strSources,String keyword, String strWhere, String orderBy) {
		List<IrpMicroblog> list = null;
		try {
			//String SOURCE = SysConfigUtil.getSysConfigValue("strSources");
			String HOSE = SysConfigUtil.getSysConfigValue("strServerHost");
			String POST = SysConfigUtil.getSysConfigValue("strServerPort");
			String UNAME = SysConfigUtil.getSysConfigValue("strUserName");
			String UPWD = SysConfigUtil.getSysConfigValue("strPassWord");
			String OPTIONS = SysConfigUtil.getSysConfigValue("strOptions");
			conn = new TRSConnection();
			conn.connect(HOSE, POST, UNAME, UPWD, OPTIONS);
			if (orderBy.trim().equalsIgnoreCase("") || orderBy == null) {
				orderBy = "RELEVANCE";
			}
			rs = conn.executeSelect(strSources, keyword, orderBy, "", "microblogcontent", 0,
					TRSConstant.TCE_OFFSET, false);
			rs.setReadOptions(TRSConstant.TCE_OFFSET,
					"MICROBLOGID,USERID,microblogcontent,CRTIME,FROMDATA,ISDEL", ";");
			if (rs.getRecordCount() > 0) {
				//加载list
				list = loadTRSMicResultSet(rs, pageutil);
			}
		} catch (TRSException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			rs = null;
			conn = null;
		}
		return list;
	}
	//微知
	private List<IrpMicroblog> loadTRSMicResultSet(TRSResultSet _trsRs, PageUtil pageutil){
		//重新构造pageUtil
		pageutil.setDataCount(Long.valueOf(_trsRs.getRecordCount()).intValue());
		pageutil.init();
		List<IrpMicroblog> list = new ArrayList<IrpMicroblog>();
		try {
			for(int i=pageutil.getPageIndex(); i<(pageutil.getPageIndex()+pageutil.getPageSize())&& i<pageutil.getDataCount();i++){
				_trsRs.moveTo(0, i);
				IrpMicroblog doc = new IrpMicroblog();
				//MICROBLOGID,USERID,MICROBLOGCONTENT,CRTIME,FROMDATA,ISDEL from IRP_MICROBLOGISDEL
				doc.setMicroblogid(Long.parseLong(_trsRs.getString("MICROBLOGID")));
				doc.setUserid(Long.parseLong(_trsRs.getString("USERID")));
				doc.setMicroblogcontent(_trsRs.getString("microblogcontent", "red"));
				doc.setFromdata(_trsRs.getStringWithCutsize("FROMDATA", 30,"red"));
				String sCrTime = _trsRs.getString("crtime");
				if (sCrTime != null && sCrTime.length() > 0) {
					doc.setCrtime(DateUtils.getDateByStrToFormat("yyyy.MM.dd HH:mm:ss", sCrTime));
				}
				list.add(doc);
			}
		} catch (TRSException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private List<IrpDocumentWithBLOBs> loadTRSResultSet(TRSResultSet _trsRs, PageUtil pageutil){
		//重新构造pageUtil
		pageutil.setDataCount(Long.valueOf(_trsRs.getRecordCount()).intValue());
		pageutil.init();
		List<IrpDocumentWithBLOBs> list = new ArrayList<IrpDocumentWithBLOBs>();
		try {
			for(int i=pageutil.getPageIndex(); i<(pageutil.getPageIndex()+pageutil.getPageSize())&& i<pageutil.getDataCount();i++){
				_trsRs.moveTo(0, i);
				IrpDocumentWithBLOBs doc = new IrpDocumentWithBLOBs();
				//DOCID2,DOCTITLE,DOCCONTENT,DOCKEYWORDS,CRTIME,CRUSER,CRUSERID
				doc.setDocid(Long.parseLong(_trsRs.getString("docid2")));
				doc.setDoctitle(_trsRs.getStringWithCutsize("doctitle", 30,"red"));
				doc.setDoccontent(_trsRs.getStringWithCutsize("DOCCONTENT", 30,"red"));
				doc.setDockeywords(_trsRs.getString("DOCKEYWORDS"));
				doc.setCruser(_trsRs.getString("CRUSER"));
				doc.setCruserid(Long.parseLong(_trsRs.getString("CRUSERID")));
				String sCrTime = _trsRs.getString("crtime");
				if (sCrTime != null && sCrTime.length() > 0) {
					doc.setCrtime(DateUtils.getDateByStrToFormat(
							"yyyy.MM.dd HH:mm:ss", sCrTime));
				}
				list.add(doc);
			}
		} catch (TRSException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public static void main(String[] args) {
		 TRSConnection conn = null;
		  TRSResultSet  rs   = null;  
		  try
		  {
		      conn = new TRSConnection();
		      conn.connect("localhost", "8888", "system", "manager", "T10");
		      rs = conn.executeSelect("IRP_MIC", "%2", "microblogcontent", false);
		      System.out.println("共检索到 " + rs.getRecordCount() + "条记录");
		      for (int i = 0; i < 10 && i < rs.getRecordCount(); i++)
		      {
		          rs.moveTo(0, i);
		      }
		  }
		  catch (TRSException e)
		  {
		      System.out.println("ErrorCode: " + e.getErrorCode());
		      System.out.println("ErrorString: " + e.getErrorString());
		  }
		  finally
		  {
		      if (rs != null) rs.close();
		      rs = null;
		       
		      if (conn != null) conn.close();
		      conn = null;
		  }
		 
	}
	

}
