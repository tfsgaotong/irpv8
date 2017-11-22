<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<style type="text/css">
#s{
overflow: auto;
margin: 10px;	
</style>
</head>
<body>
<div id="s">
    <form id="sbid" method="post">
      <table width="600px" height="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright" width="70px" >会议名称</td>
         <td bgcolor="#F5FAFE" class="main_bright" >
         <s:property value="asseroomapply.assename" />
         </td>
         <td bgcolor="#F5FAFE" class="main_bright" width="70px">会议类型</td>
         <td bgcolor="#F5FAFE" class="main_bright" >
        	<s:property value="assetype.assetypename" />
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">召集人</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroomapply.convenerusername" />
         </td>
         <td bgcolor="#F5FAFE" class="main_bright">联系人</td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroomapply.linkmanusername" />
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议内容</td>
         <td bgcolor="#F5FAFE" colspan="3" class="main_bright">
         <s:property
					value="asseroomapply.asseroomcontent" />
          </td>
        </tr>
        <tr>
          <td bgcolor="#F5FAFE"  class="main_bright">备注</td>
         <td bgcolor="#F5FAFE" colspan="3"class="main_bright">
         <s:property value="asseroomapply.asseroomapplydemo" />
         </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">会议室 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroom.asseroomname" />
         </td>
          <td bgcolor="#F5FAFE" class="main_bright">地址 </td>
         <td bgcolor="#F5FAFE" class="main_bright">
         <s:property value="asseroom.asseroomaddr"/>
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">设备 </td>
         <td bgcolor="#F5FAFE" colspan="3"  class="main_bright">
         <s:property value="asseroomapply.asseroomapplysbnames" />
         </td>
        </tr>
        <tr>
         <td bgcolor="#F5FAFE" class="main_bright">开始时间</td>
          <td bgcolor="#F5FAFE" colspan="3" class="main_bright">
	          <s:property value="asseroomapply.start" />
          </td>
        </tr>
        <tr>
           <td bgcolor="#F5FAFE" class="main_bright">结束时间</td>
          <td bgcolor="#F5FAFE" colspan="3" class="main_bright">
          	<s:property value="asseroomapply.end" />
          </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">参会人员</td>
          <td bgcolor="#F5FAFE" colspan="3" class="main_bright">
	          <s:property value="asseroomapply.fromidAll" />
          </td>
        </tr>
         <tr>
         <td bgcolor="#F5FAFE" class="main_bright">通知类型</td>
          <td bgcolor="#F5FAFE" class="main_bright">
	          <s:property value="asseroomapply.warnMenthodString" />
          </td>
           <td bgcolor="#F5FAFE" class="main_bright">参会人数</td>
          <td bgcolor="#F5FAFE" class="main_bright">
          	<s:property value="asseroomapply.shouldman" />
          </td>
        </tr>
        
        <tr>
           <td bgcolor="#F5FAFE" class="main_bright">通知时间</td>
          <td bgcolor="#F5FAFE" colspan="3" class="main_bright">
          <s:if test='asseroomapply.warnData==null'><s:property value="asseroomapply.crtimes" /></s:if><s:property value="asseroomapply.warnCalender" />
          </td>
        </tr>
        <tr>
        	<td bgcolor="#F5FAFE" class="main_bright">相关附件</td>
          <td colspan="3" bgcolor="#F5FAFE" class="main_bright">
	          <s:iterator value="attacheds">
				<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><s:property
							value="attdesc" />
					</a> &nbsp;&nbsp; <a
						href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
			<br>
			</s:iterator>
          </td>
        </tr>
      </table>
    </form>
   </div>
</body>
</html>