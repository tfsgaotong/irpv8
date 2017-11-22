<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>  
  </head>  
  <body>
  <script type="text/javascript">

	function informFontInfo(_info){
		if(${informdescnum}-$.trim(_info).length>=0){
			$('#topicFont_02').css({display:'none'});
			$('#topicFont_01').css({display:'block'});
			$('#topicFontCount_01').text(${informdescnum}-$.trim(_info).length);
		}else{
			$('#topicFont_01').css({display:'none'});
			$('#topicFont_02').css({display:'block'});
			$('#topicFontCount_02').text(Math.abs(${informdescnum}-$.trim(_info).length));
		}
	
}
</script>
<div style="width: 480px;height: 180px;">
    <form id="transdocument"  name="transdocument" action="<%=rootPath%>site/addtransmite.action" method="post"> 
     <s:hidden name="userIds" id="userIds" />  
     <s:hidden name="groupIds" id="groupIds"/> 
      <s:hidden name="userNames" id="userNames" />  
     <s:hidden name="groupNames" id="groupNames"/> 
	 推荐给： 
	 		<a href="javascript:void(0);" onclick="checkuserorgroup()">选择推荐人</a> 
	 <br/>
			 <div id="groupdiv"><s:property value="groupNames"/>
			 </div>
	 		<div id="userdiv">
			 	<s:property value="userNames"/>
	 		</div>
	 
     <%--最大字数 --%>  
     	<input name="" id="informdesccount" value="<s:property value="informdescnum" />" style="display: none;"/>
     	<input type="hidden"  id="docid" name="docid" value="<s:property value='docid'/>"/> 
        <p>推荐理由：
          <span id="topicFont_01"  style="float: right;">
          	还可以输入
          <label id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">
          	<s:property value="informdescnum" />
          </label>个字
          </span>
		  <span id="topicFont_02" style="float: right; display: none;">
			  已超出
		  <label id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字
		  </span>
        </p> 
         <textarea name="summary" rows="4" cols="56" style="background: #F6F6F6;"  onkeyup="informFontInfo(this.value)" ></textarea> 
    </form></div>
  </body>
</html>
