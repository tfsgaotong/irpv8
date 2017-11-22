<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

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
<div style="width: 500px;height: 200px;">
	<form id="addclassialfrm" action="<%=rootPath %>site/adddocumentclassicl.action" method="post">
	 <%--知识id --%>
    <input name="irpInform.informnameid"  value="<s:property value="docid" />" style="display: none;">
    <input id="informdesccount" value="<s:property value="informdescnum" />" style="display: none;"/>
	<p> 加精理由：<br>
	<s:iterator value="irpInformTypelist">
		<input  name="informkeykind" data="<s:property value='informvalue'/>" type="radio"  value="<s:property value='informkey'/>">
		<s:property value='informvalue'/>
		&nbsp;&nbsp;
	</s:iterator>
	</p>
	 <p>自定义加精理由：
          <span id="topicFont_01"  style="float: right;">还可以输入<label id="topicFontCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"><s:property value="informdescnum" /></label>个字</span>
		  <span id="topicFont_02" style="float: right; display: none;">已超出<label id="topicFontCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
	 </p> 
	 	<textarea id="mydes" name="irpInform.informcontent"  rows="5" cols="56" style="border: 1em;background: #F6F6F6;width: 100%"  onkeyup="informFontInfo(this.value)" ></textarea> 
	</form>
	</div>
</body>