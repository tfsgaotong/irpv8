package com.tfs.irp.util;

public class PageUtil {
	
	//共显示多少个页码
	private final int m_nNumCount = 5;
	//共显示多少个页码(前台)
	private final int m_clientnNumCount = 3;
	
	public PageUtil() {
	}

	public PageUtil(int _nPageNum, int _nPageSize, int _nDataCount) {
		this.pageNum = _nPageNum;
		this.pageSize = _nPageSize;
		this.dataCount = _nDataCount;
		init();
	}

	private int pageNum;

	private int pageSize;
	
	private int dataCount;
	
	private int pageCount;

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public int getPageIndex() {
		int nPageIndex = ((pageNum-1)*pageSize);
		if(nPageIndex<0)nPageIndex=0;
		return nPageIndex;
	}
	
	/**
	 * 初始化分页
	 */
	public void init() {
		// 计算总页数
		pageCount = (dataCount%pageSize)>0?(dataCount/pageSize+1):(dataCount/pageSize);
		// 处理当前页数范围
		if(pageNum<1){
			pageNum = 1;
		}else if(pageNum>pageCount){
			pageNum = pageCount;
		}		
	}
	
	
	/**
	 * 获得页面显示的分页代码(使用#PageNum#占位符)
	 * @param _sPageUrl
	 * @return 分页HTML代码
	 */
	public String getPageHtml(String _sPageUrl) {
		StringBuffer sPageHtml = new StringBuffer();
		//定义变量
		int nPrePageNum = (pageNum-1)<1?1:(pageNum-1);
		int nNextPageNum = (pageNum+1)>pageCount?pageCount:(pageNum+1);
		
		//上一页处理
		if(pageNum<=1){
			sPageHtml.append("<strong>上一页</strong>");
		}else{
			sPageHtml.append("<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nPrePageNum))+"\">上一页</a>");
		}
		
		//页码处理
		int nStartNum = pageNum-(m_nNumCount/2);
		if(nStartNum<1){
			nStartNum = 1;
		}
		int nEndNum = pageNum+(m_nNumCount/2);
		if(nEndNum>pageCount){
			nEndNum = pageCount;
		}
		
		if(nStartNum>1){
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(1))+"\">1</a>");
		}
		if(nStartNum>2){
			sPageHtml.append("\n<label>...</label>");
		}
		
		while (nStartNum<=nEndNum) {
			if(nStartNum==pageNum){
				sPageHtml.append("\n<strong>"+nStartNum+"</strong>");
			}else{
				sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nStartNum))+"\">"+nStartNum+"</a>");
			}
			nStartNum++;
		}
		
		if(nStartNum<pageCount){
			sPageHtml.append("\n<label>...</label>");
		}
		if(nStartNum<=pageCount){
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(pageCount))+"\">"+pageCount+"</a>");
		}
		
		//下一页处理
		if(pageNum==pageCount){
			sPageHtml.append("\n<strong>下一页</strong>");
		}else{
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nNextPageNum))+"\">下一页</a>");
		}
		sPageHtml.append("\n<a href=\"javascript:void(0)\">共"+dataCount+"条</a>");
		return sPageHtml.toString();
	}
	
	public String getClientPageHtml(String _sPageUrl) {
		StringBuffer sPageHtml = new StringBuffer();
		//定义变量
		int nPrePageNum = (pageNum-1)<1?1:(pageNum-1);
		int nNextPageNum = (pageNum+1)>pageCount?pageCount:(pageNum+1);
		
		//上一页处理
		if(pageNum<=1){
			sPageHtml.append("<span>上一页</span>");
		}else{
			sPageHtml.append("<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nPrePageNum))+"\">上一页</a>");
		}
		
		//页码处理
		int nStartNum = pageNum-(m_clientnNumCount/2);
		if(nStartNum<1){
			nStartNum = 1;
		}
		int nEndNum = pageNum+(m_clientnNumCount/2);
		if(nEndNum>pageCount){
			nEndNum = pageCount;
		}
		
		if(nStartNum>1){
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(1))+"\">1</a>");
		}
		if(nStartNum>2){
			sPageHtml.append("\n<label>...</label>");
		}
		
		while (nStartNum<=nEndNum) {
			if(nStartNum==pageNum){
				sPageHtml.append("\n<span style=\"background-color:#F0F0F0;\">"+nStartNum+"</span>");
			}else{
				sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nStartNum))+"\">"+nStartNum+"</a>");
			}
			nStartNum++;
		}
		
		if(nStartNum<pageCount){
			sPageHtml.append("\n<label>...</label>");
		}
		if(nStartNum<=pageCount){
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(pageCount))+"\">"+pageCount+"</a>");
		}
		
		//下一页处理
		if(pageNum==pageCount){
			sPageHtml.append("\n<span>下一页</span>");
		}else{
			sPageHtml.append("\n<a href=\"javascript:void(0)\" onclick=\""+_sPageUrl.replaceAll("#PageNum#", String.valueOf(nNextPageNum))+"\">下一页</a>");
		}
		
		return sPageHtml.toString();
	}
}