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

<title>举报内容</title>
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

function otherconndp(rdo){
	if(rdo.id!='other'){
		$('#otherConn').css('display','none');
		$('#informdescform').val('');
		$('#topicFontCount_01').text(${informdescnum});
	}else{
		$('#otherConn').css('display','');
	}
}
</script>
</head>
<body>
<div style="width: 480px;">
	<form method="post" id="informform">
	<input type="hidden" id="informdesccount" value="<s:property value="informdescnum" />" />
	<input type="hidden" name="irpInform.informnameid" value="<s:property value="microblogid" />" />
	<div>
		<p>请选择举报类型：</p>
	    <s:iterator value="irpInformTypelist">
		<p><input name="informkeykind" onclick="otherconndp(this)" id="<s:property value="informkey" />" type="radio" value="<s:property value="informkey" />" title="<s:property value="informvalue" />"><label for="<s:property value="informkey" />">&nbsp;<s:property value="informvalue" /></label></p>
	    </s:iterator>
	    <p><input name="informkeykind" onclick="otherconndp(this)" id="other" type="radio" value="<s:property value="com.tfs.irp.informtype.entity.IrpInformType@INFORMKEY_OTHER" />" title="其他类型"><label for="other">&nbsp;其他</label></p>
  	</div>
  	<div id="otherConn" style="display:none;">
	    <p>
	    	请填写举报内容:
	    	<span id="topicFont_01"  style="float: right;">还可以输入<label id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="informdescnum" /></label>个字</span>
			<span id="topicFont_02"  style="float: right; display: none;">已超出<label id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
		</p>
	    <p>
	    	<textarea id="informdescform" name="irpInform.informcontent" cols="56"  rows="5" style="background-color:rgb(246, 246, 246);resize: none;" onkeyup="informFontInfo(this.value)" ></textarea>
	    </p>
    </div>
	</form>
</div>
</body>
</html>