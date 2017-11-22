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
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
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
function report() {
	var microblogid = document.getElementById("microblogid").value;
	var reportcontent = $('#informdescform').val();
	$('#reportwb').attr("onclick","");
	var informkeykind=$("input[name='informkeykind']:checked").val();
	if(informkeykind==undefined){
		$.dialog.tips("请选择举报类型",1.5,"alert.gif");
		$('#reportwb').attr("onclick","report()");
		return false;
	}else{
			$.ajax({
				type : "POST",
				url : '<%=rootPath%>phone/saveinformdesc.action?token='+$('#tokens').val(),
				dataType : "json",
				cache:false,
				async:false,
				data : {
					wbmsgid : microblogid,
					informkeykind:informkeykind,
					informcontent:reportcontent
				},
				success : function(nMsg) {
					if (nMsg = 1) {
						$.dialog.tips('举报成功',1,'32X32/succ.png');
						/* $('#reportwb').attr("disabled", true); */
						$('#reportwb').text("已举报");
					} else {
						$.dialog.tips('举报失败', 1, '32X32/fail.png');
					}
				}
			});
	}
}
</script>
</head>
<body>
<div style="width: 100%;">
	<form method="post" id="informform">
	<input type="hidden" id="informdesccount" value="<s:property value="informdescnum" />" />
	<input type="hidden" id="microblogid" name="irpInform.informnameid" value="<s:property value="microblogid" />" />
	<input type="hidden" id="tokens" name="token" value="<s:property value="token" />" />
	<div>
		<p>举报<span style="color: #2288cc;">@<s:property value="showname" /></span>微知：</p>
  	</div>
  	<div style="background-color: #F6F6F6;line-height: 30px;">
		<p><span style="color: #2288cc;">@<s:property value="showname" /></span>：<s:property value="wbcontext" /></p>
  	</div>
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
	    	<textarea id="informdescform" name="irpInform.informcontent" rows="5" style="background-color:rgb(246, 246, 246);resize: none;width: 98%;" onkeyup="informFontInfo(this.value)" ></textarea>
	    </p>
    </div>
    <div style="height:40px;line-height:40px;text-align: center;background-color: #2288cc;">
    <a style="color: white;text-decoration: none;" href="javascript:void(0)" id="reportwb" onclick="report()">举报</div>
	</form>
</div>
</body>
</html>