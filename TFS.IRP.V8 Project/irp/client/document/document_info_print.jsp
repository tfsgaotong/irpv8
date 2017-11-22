<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title><s:property value="irpDocument.doctitle"/> </title>  
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" /> 
</head>     
   <link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
   <link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
 <body id="pringbody"> 
  <s:include value="../include/enterprise_head.jsp"></s:include>
    <div class="area1"></div>
    <div class="zj_wBox">
        <div class="zj_xl_txt"> 
          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="olbg7" height="300">
      <tr>
        <td valign="top" class="olbg8">
        <!-- 开始打印部分 -->
      <div id="doprint">
        <table width="874" border="0" cellspacing="0" cellpadding="0" class="box doctitle boxcenter dttitle ">
            <tr>
              <td width="874" align="center" class="box doctitle boxcenter dttitle " >
              <s:property value="irpDocument.doctitle "/>
               </td>
            </tr>
          </table> 
          <div class="date">
            <center>发布时间：<s:date name="%{irpDocument.docpubtime}" format="yyyy-MM-dd HH:mm"/>
            <%--
            &nbsp;&nbsp;到期时间： 
             --%>
            <s:date name="%{irpDocument.docvalid}" format="yyyy-MM-dd HH:mm"/></center>
          </div>
          <div class="intro">核心提示：<s:property value="irpDocument.docabstract"/> </div>
      
          <div class="dttxt12">  
	   		  <s:property value="irpDocument.attachedcontent" escapeHtml="false" /> 
   		  </div>
      </div>
   		<!-- 结束打印 -->
	        <s:if test="attacheds!=null && attacheds.size()!=0">
          <ul class="dtfj1">
           <table>
         <s:iterator value="attacheds">
          <tr>
          <td>
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
         	  	 <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -11px -10px;"></span> 
         	  </s:if>
         	  <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
         	  <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -60px -10px;"></span> 
         	  </s:elseif>
         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -110px -10px;"></span> 
         	   </s:elseif>
         	    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -160px -10px;"></span> 
         	    </s:elseif>
         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid" >
         	       <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span> 
         	  </s:elseif> 
          </td>
          <td style="padding-left: 20px;"><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value="attdesc"/></a>
          	</td>
          <td style="padding-left: 20px;"> 
          <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                 	| <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:if>
	                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	               	|  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:elseif></td>
          </tr>
         </s:iterator>
          </table>
          </ul>
          </s:if>
          
          <div class="dtgjc">标签：  
                <s:iterator value="irpDocument.dockeywords.split(',')"  status="st" var="as">
  				 <a href="javascript:void(0);" onclick="searchInfo1('<s:property value="#as"/>')" ><s:property value="#as"/></a>&nbsp;&nbsp;
  				</s:iterator>
          </div>
       <%--推荐精华 --%>
       <s:if test="@com.tfs.irp.document.web.DocumentAction@countByDocId(irpDocument.docid)>0">
        <div id="jinghuaimg" style="z-index: 100;position: absolute; top: 160px;right:200px;"><img src="<%=rootPath%>client/images/001.gif"/></div>
       </s:if>
          </td>
      </tr>
    </table> </div>  </div>   
</body>
</html>
 <script type="text/javascript">
 $(function(){
	 var printhtml=$('#pringbody').html();
	 window.document.body.innerHTML=printhtml;
	 window.print();
 });
</script>
