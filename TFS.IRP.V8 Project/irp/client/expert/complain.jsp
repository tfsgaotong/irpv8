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

<title>意见反馈</title>
<script type="text/javascript">
var tranmicronumberwrod="300";
$(function(){
	$("#complaindesclength").text(tranmicronumberwrod);
})

//控制反馈内容的长度
function complainInfoControl(_microblogcontent){
	if(tranmicronumberwrod-$.trim(_microblogcontent).length>=0){
		$('#moreli').css({display:'none'});
		$('#goodli').css({display:'block'});
		$('#complaindesclength').html(tranmicronumberwrod-$.trim(_microblogcontent).length);
	}else{
		$('#goodli').css({display:'none'});
		$('#moreli').css({display:'block'});
		$('#complaindescmorelength').html(Math.abs(tranmicronumberwrod-$.trim(_microblogcontent).length));
	}
}
</script>
</head>
<body>

     
  <div style="padding: 10px;">
     <s:form action="" id="addComform" theme="simple">	
         <ul> 
               <li><div class="jz4">反馈类型：<s:select id="complaintypeid" name="irpComplain.complaintypeid" list="irpInformTypelist" listKey="informkey" listValue="informvalue" headerKey="0" headerValue="--请选择建议类型--"></s:select></div></li>
               <li id="goodli">
                 <div class="jz4">反馈内容：<em style="float: right;">还可以输入<label id="complaindesclength" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</em></div>
              </li>
              <li id="moreli" style="text-align: right;display: none;">超出了<label id="complaindescmorelength" style="font-family:Tahoma,Arial,sans-serif; font-size: 12px;color:red;" ></label>个字</li>
	          <li>
	             <textarea id="complaindesc" name="irpComplain.complaindesc" onkeyup="complainInfoControl(this.value)"  rows="6"  style="font-size: 14px; width: 480px;background: rgb(250, 250, 250);height: 120px;" ></textarea>
	          </li>
	    </ul>    
    </s:form>	
  </div>
</body>
</html>