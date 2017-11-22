<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
	<!--下拉列表开始-->
	<div class="ub uba1 uc-a1 c-wh b-gra us-i sel" style="width:50%;float: right; ">  
	   <div class="ub-f1 ut-s uinn ulev-1 tx-l" ids="0" id="firstprivacyset">请选择举报类型</div>
	   <div class="ubl b-gra c-bla c-m2 umw2 ub ub-pc uc-r1 ub-ac"> 
	    <div class="ub-img umw1 umh1 res3"></div> 
	  </div> 
	  <select name="sel0" id="reportkeys"  selectedindex="0" id="sel2" onchange="zy_selectmenu(this.id)" style="margin:0 0 0 0;"> 
		   <s:if test="irpInformTypelist.size()>0">
		   		<s:iterator value="irpInformTypelist">
		   		 	<option  value="<s:property value="informkey" />" ><s:property value="informvalue" /></option>
		   		</s:iterator> 
		   </s:if> 
	  </select>
	</div>
	<!--下拉列表结束-->