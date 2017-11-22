<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript">
 
	 </script>
<form id="pageFormq">
<s:hidden name="pageNum" id="pageNumq" />
<s:hidden name="pageSizeshow" id="pageSizeq" />
<s:hidden name="orderField" id="orderFieldq" />
<s:hidden name="orderBy" id="orderByq" />
</form>
<s:if test="irpQuestions.size>0">
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1"  id="table1" bgcolor="#cad9ea">
	<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><a href="javascript:void(0)" onclick="checkAll()">序号</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('title','<s:if test="orderField=='title'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">问题<s:if test="orderField=='title'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='title'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('textcontent','<s:if test="orderField=='textcontent'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">回答<s:if test="orderField=='textcontent'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='textcontent'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('status','<s:if test="orderField=='status'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">状态<s:if test="orderField=='status'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='status'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderField=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('cruser','<s:if test="orderField=='cruser'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建者<s:if test="orderField=='cruser'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruser'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
   </tr> 
		<s:iterator value="irpQuestions" status="irpInformTypeliststatus">
   <tr>
		 <td bgcolor="#F5FAFE" class="main_bright" width="6%" align="center"><s:property value="(pageNum-1)*pageSizeshow+#irpInformTypeliststatus.count"/></td>
		 <td bgcolor="#F5FAFE" class="main_bright" width="30%">
		       <s:if test="title.length()>18"><span style="cursor:pointer;" title="双击查看更多" ondblclick="lookdetil('<s:property value="title"/>')"><s:property value="title.substring(0,18)"/></span>…
               </s:if> <s:else><s:property value="title"  /></s:else>
		 <td bgcolor="#F5FAFE" class="main_bright" width="30%">
		      <s:if test="textcontent.length()>18"><span style="cursor:pointer;" title="双击查看更多" ondblclick="lookdetil('<s:property value="textcontent"/>')"><s:property value="textcontent.substring(0,18)"/></span>…
               </s:if> <s:else><s:property value="textcontent"  /></s:else>
		 <td bgcolor="#F5FAFE" class="main_bright" width="10%"><s:if test="status==0">未解决</s:if><s:elseif test="status==1">已解决</s:elseif><s:else>回答</s:else>  </td>
		 <td bgcolor="#F5FAFE" class="main_bright" width="14%"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /> </td>
		 <td bgcolor="#F5FAFE" class="main_bright" width="10%"><s:property value="cruser"/> </td>
  </tr>
 </s:iterator>
   <tr bgcolor="#FFFFFF">
		 <td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
   </tr>
   <tr>
        <td align="center" colspan="6" bgcolor="#F5FAFE" class="main_bright">
	         <a id="" class="l-btn" href="javascript:void(0)" onclick="sureexport()">
				<span class="l-btn-left">
				    <span class="l-btn-text icon-ok l-btn-icon-left"> 确定导出</span>
				</span>
		     </a>
		     <a href="" id="search"></a>
        </td>
  </tr>
</table>
</s:if>
<s:else>
没有数据，不能导出
</s:else>