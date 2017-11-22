<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发送私信</title>
</head>
<script type="text/javascript">

function microblogMessageFontInfo(_microblogcontent){
	
	if(${messagecount}-$.trim(_microblogcontent).length>=0){
		$('#microMessageFont_02').css({display:'none'});
		$('#microMessageFont_01').css({display:'block'});
		$('#microMessageFontCount_01').text(${messagecount}-$.trim(_microblogcontent).length);
	}else{
		$('#microMessageFont_01').css({display:'none'});
		$('#microMessageFont_02').css({display:'block'});
		$('#microMessageFontCount_02').text(Math.abs(${messagecount}-$.trim(_microblogcontent).length));
	}
	
}

</script>
<body>
 <div>
 <form method="post" id="messageContentForm" class="uiConnForm">
 		<input type="hidden" name="docid" value='<s:property value="docid"/>'>
        <input type="text" style="display: none;" name="messageid" value="${messageid}" />
        <input type="hidden" id="messagecount" value='<s:property value="messagecount"/>'>
        <s:if test="messageid==0">
        <p>收信人:(发送多人时用户名用中英文逗号、分号、冒号或者空格区分)&nbsp;&nbsp;<a href="javascript:void(0)" class="linkbh14" onclick="addUser()">选择用户</a></p>        
        <p><textarea id="messageuserinfo"   name="fromidAll" cols="50"  rows="1" style="background-color:rgb(246, 246, 246);resize: none;"></textarea></p>        
        </s:if>
        
        <s:if test="messageid!=0">
        <p>收信人:</p>
        <p><textarea id="messageuserinfo"  name="" cols="50"  rows="1" style="background-color:rgb(246, 246, 246);;resize: none;" disabled="disabled" ><s:property value="messageuser" /></textarea></p>
        <p style="display: none;"><input type="text" name="irpMessageContent.fromUid" value="<s:property value='messageid' />"></p>        
        </s:if>        
        
        <p><font style="float: left;">内&nbsp;&nbsp;&nbsp;容:</font>
          <span id="microMessageFont_01"  style="float: left;margin-left: 260px;">还可以输入<label id="microMessageFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="messagecount" /></label>个字</span>
		  <span id="microMessageFont_02"  style="float: left;margin-left: 260px; display: none;">已超出<label id="microMessageFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
        </p>
        <p> <textarea id="messageInfo" name="irpMessageContent.content" cols="50"  rows="4" style="background-color:rgb(246, 246, 246);;resize: none;" onkeyup="microblogMessageFontInfo(this.value)"></textarea></p>   
  </form> 
 </div>
</body>
</html>