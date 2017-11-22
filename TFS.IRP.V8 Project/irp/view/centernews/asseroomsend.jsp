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
	
	if(150-$.trim(_microblogcontent).length>=0){
		$('#microMessageFont_02').css({display:'none'});
		$('#microMessageFont_01').css({display:'block'});
		$('#microMessageFontCount_01').text(150-$.trim(_microblogcontent).length);
	}else{
		$('#microMessageFont_01').css({display:'none'});
		$('#microMessageFont_02').css({display:'block'});
		$('#microMessageFontCount_02').text(Math.abs(150-$.trim(_microblogcontent).length));
	}
	
}

</script>
<body>
 <div>
 <form method="post" id="asseroomsend" class="uiConnForm">
 		<input type="hidden" name="attachsendcontent" value='<s:property value="attachsendcontent"/>'>
        <input type="hidden" name="sendid" value='<s:property value="sendid"/>' />
        <input type="hidden" id="messagecount" value='150'>
        <p>收信人:<s:property value="sendusername"/></p>
        <p><font style="float: left;">对此会议的看法:</font>
          <span id="microMessageFont_01"  style="float: left;margin-left: 260px;">还可以输入<label id="microMessageFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">150</label>个字</span>
		  <span id="microMessageFont_02"  style="float: left;margin-left: 260px; display: none;">已超出<label id="microMessageFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
        </p>
        <p> <textarea id="sendcontent" name="sendcontent" cols="50"  rows="4" style="background-color:rgb(246, 246, 246);;resize: none;" onkeyup="microblogMessageFontInfo(this.value)"></textarea></p>   
  </form> 
 </div>
</body>
</html>