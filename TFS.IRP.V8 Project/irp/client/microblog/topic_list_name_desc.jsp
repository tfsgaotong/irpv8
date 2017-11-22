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

<title>增加专题描述</title>
<script type="text/javascript">
/**
 * 控制名字字数
 */
function topicNameFontInfo(_info){
	
	if(${topicnamenum}-$.trim(_info).length>=0){
		$('#topicnameFont_02').css({display:'none'});
		$('#topicnameFont_01').css({display:'block'});
		$('#topicnameFontCount_01').text(${topicnamenum}-$.trim(_info).length);
	}else{
		$('#topicnameFont_01').css({display:'none'});
		$('#topicnameFont_02').css({display:'block'});
		$('#topicnameFontCount_02').text(Math.abs(${topicnamenum}-$.trim(_info).length));
	}
}
/**
 * 控制描述字数
 */
function topicFontInfo(_info){
	
	if(${topicdescnum}-$.trim(_info).length>=0){
		$('#topicFont_02').css({display:'none'});
		$('#topicFont_01').css({display:'block'});
		$('#topicFontCount_01').text(${topicdescnum}-$.trim(_info).length);
	}else{
		$('#topicFont_01').css({display:'none'});
		$('#topicFont_02').css({display:'block'});
		$('#topicFontCount_02').text(Math.abs(${topicdescnum}-$.trim(_info).length));
	}
	
}
</script>
</head>
<body>
<div>
 <form method="post" id="topicform">
        <input name="" id="topicdesccount" value="<s:property value="topicdescnum" />" style="display: none;">
        <input name="" id="topicnamecount" value="<s:property value="topicnamenum" />" style="display: none;">
        <p>专题:(每次只能创建一个专题)
           <span id="topicnameFont_01"  style="float: right;">还可以输入<label id="topicnameFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="topicnamenum" /></label>个字</span>
		   <span id="topicnameFont_02"  style="float: right; display: none;">已超出<label id="topicnameFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
        </p>
        <p><textarea id="topicnameform"  name="irpTopic.topicname" cols="80"  rows="1" style="background-color:rgb(246, 246, 246);resize: none;" onkeyup="topicNameFontInfo(this.value)" ></textarea></p>        
        <p>描述:
          <span id="topicFont_01"  style="float: right;">还可以输入<label id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="topicdescnum" /></label>个字</span>
		  <span id="topicFont_02"  style="float: right; display: none;">已超出<label id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
        </p>
    
        <p><textarea id="topicdescform" name="irpTopic.topicdesc" cols="80"  rows="3" style="background-color:rgb(246, 246, 246);resize: none;" onkeyup="topicFontInfo(this.value)" ><s:property value="irpTopic.topicdesc" /></textarea></p>   
  </form> 
 </div>
</body>
</html>