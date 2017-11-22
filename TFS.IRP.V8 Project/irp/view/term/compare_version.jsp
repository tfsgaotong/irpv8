<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 历史版本比较 -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:property value="getIrpTermById(cponeterm.termqid).termname" />_百科词条</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/asx.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/js/compare/diffview.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/compare/difflib.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/compare/diffview.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/compare/dojo.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	.wtgy {
		height: 28px;
		line-height: 28px;
		border: 1px solid rgb(209, 209, 209);
		color: rgb(102, 102, 102);
		padding: 0px 5px;
		width: 300px;
		margin: 0 0 0 5px;
	}
	
	.btn_ccw {
		background: none repeat scroll 0 0 #63C7E6;
		color: #FFFFFF;
		display: block;
		width: 100px;
		float: left;
		line-height: 28px;
		font-size: 18px;
		padding: 0px 5px;
		font-style: normal;
		height: 30px;
		text-align: center;
	}
	
	body {
		behavior: url(hover.htc);
	}
	
	.searchSec .radios span {
		margin-top: 0px;
	}
	</style>
</head>
 
<script type="text/javascript">
var numonehistory = "";
var numtwohistory = "";
$(function(){
	//选择头部标签
//	selected('wordterm');
	
	disposeCPare();
});


HTMLElement.prototype.__defineGetter__("innerText",function (){
    var anyString = "";
    var childS = this.childNodes;
    for(var i=0; i<childS.length; i++) {
        if(childS[i].nodeType==1)
            anyString += childS[i].tagName=="BR" ? '\n' : childS[i].innerText;
        else if(childS[i].nodeType==3)
            anyString += childS[i].nodeValue;
    }
    return anyString;
}); 

	function disposeCPare(){
		numonehistory = document.getElementById("numonehistory").innerText;
		numtwohistory = document.getElementById("numtwohistory").innerText;
		diffUsingJS();
	}
	function diffUsingJS() {

		var $ = dojo.byId;
		dojo.require("dojo.io");

		var url = window.location.toString().split("#")[0];
		var base = difflib.stringAsLines(numonehistory);
		var newtxt = difflib.stringAsLines(numtwohistory);
		
		var sm = new difflib.SequenceMatcher(base, newtxt);
		var opcodes = sm.get_opcodes();
		var diffoutputdiv = $("diffoutput");
		while (diffoutputdiv.firstChild)
			diffoutputdiv.removeChild(diffoutputdiv.firstChild);
		diffoutputdiv.appendChild(diffview.buildView({
			baseTextLines : base,
			newTextLines : newtxt,
			opcodes : opcodes,
			baseTextName : '<s:property value="getIrpTermById(cponeterm.termqid).termname" />(历史版本<s:property value="cponeterm.termversion" />)',
			newTextName : '<s:property value="getIrpTermById(cptwoterm.termqid).termname" />(历史版本<s:property value="cptwoterm.termversion" />)',
			contextSize : null,
			viewType :0
		}));
		window.location = url + "#diff";
	}
	function diffUsingPython() {
		var $ = dojo.byId;
		dojo.require("dojo.io");
		var url = window.location.toString().split("#")[0];
		dojo.io.bind({
					url : "/diff/postYieldDiffData",
					method : "POST",
					content : {
						baseText : $("baseText").value,
						newText : $("newText").value,
						ignoreWhitespace : "Y"
					},
					load : function(type, data, evt) {
						try {
							data = eval('(' + data + ')');
							while (diffoutputdiv.firstChild)
								diffoutputdiv.removeChild(diffoutputdiv.firstChild);
							$("output").appendChild(diffview.buildView({
								baseTextLines : data.baseTextLines,
								newTextLines : data.newTextLines,
								opcodes : data.opcodes,
								baseTextName : data.baseTextName,
								newTextName : data.newTextName,
								contextSize : contextSize
							}));
						} catch (ex) {
							alert("An error occurred updating the diff view:\n" + ex.toString());
						}
					},
					error : function(type, evt) {
						alert('Error occurred getting diff data.  Check the server logs.');
					},
					type : 'text/javascript'
				});
	}


/**
 * 查看历史版本细览
 */
function findVerFL(_termid){
	window.open('<%=rootPath%>term/gohistoryvcontent.action?historyid='+_termid);
}
</script>
<body style="background: url()">
    <jsp:include page="../../view/include/client_head.jsp" />
    <section class="mainBox">
	   <nav class="privateNav"></nav>
	</section>
    <div class="main" style="background: url(''); width:1200px">
	    <div style="min-height: 58vh">
	        <div style="margin: 20px 10px 10px 10px;">
	            <div style="width: 50%;float: left;">
	                <div>
			        	<ul>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 25%;float:left;" >
								更新时间
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
								版本
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
								贡献者
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;" >
								状态
							</li>
							
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;" >
								修改原因
							</li>	
						</ul>
						<ul>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 25%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" >
								<s:date name="cponeterm.crtime" format="yyyy-MM-dd HH:mm" />
								
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="cponeterm.termversion" />(查看)">
								
								<a class="linkb14" style="font-size: 12px;" href="javascript:void(0)" onclick="findVerFL(<s:property value="cponeterm.termid" />)"><s:property value="cponeterm.termversion" />(查看)</a>
							
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="getShowPageViewNameByUserId(cponeterm.cruserid)" />">
								<a class="linkb14" style="font-size: 12px;" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value="cponeterm.cruserid" />" target="_blank">
									<s:property value="getShowPageViewNameByUserId(cponeterm.cruserid)" />
								</a>
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="" >
								<s:if test="cponeterm.termstatus==10">
									通过
								</s:if>
								<s:elseif test="cponeterm.termstatus==20">
									未审核
								</s:elseif>
								<s:else>
									非法
								</s:else>
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="cponeterm.eidtcause" />" >
								<s:property value="cponeterm.eidtcause" />&nbsp;
							</li>
						</ul>
	                </div>
	            </div>
				<div style="width: 45%;float: left;margin-left: 5%;">
					<div>
			        	<ul>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 25%;float:left;" >
								更新时间
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
								版本
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;" >
								贡献者
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;" >
								状态
							</li>
							
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;" >
								修改原因
							</li>	
						</ul>
						<ul>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 25%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" >
								<s:date name="cptwoterm.crtime" format="yyyy-MM-dd HH:mm" /> 
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="cptwoterm.termversion" />(查看)">
								
								<a class="linkb14" style="font-size: 12px;" href="javascript:void(0)" onclick="findVerFL(<s:property value="cptwoterm.termid" />)"><s:property value="cptwoterm.termversion" />(查看)</a>
							
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 15%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="getShowPageViewNameByUserId(cptwoterm.cruserid)" />">
								<a class="linkb14" style="font-size: 12px;" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value="cptwoterm.cruserid" />" target="_blank" >
									<s:property value="getShowPageViewNameByUserId(cptwoterm.cruserid)" />
								</a>
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 10%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="" >
								<s:if test="cptwoterm.termstatus==10">
									通过
								</s:if>
								<s:elseif test="cptwoterm.termstatus==20">
									未审核
								</s:elseif>
								<s:else>
									非法
								</s:else>
							</li>
							<li style="border-style: none none solid none;border-width: thin; border-color: #E0E0E0;font-size: 12px;padding: 2px 0 2px 0;line-height: 28px;width: 35%;float:left;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;" title="<s:property value="cptwoterm.eidtcause" />" >
								<s:property value="cptwoterm.eidtcause" />&nbsp;
							</li>
						</ul>
					</div>
				</div>
	        </div>
		    <div>
				<input type="text">
				<div id="numonehistory" style="display: none;" ><s:property value="cponeterm.termexplain" escapeHtml="false"  /></div>
				<div id="numtwohistory" style="display: none;" ><s:property value="cptwoterm.termexplain" escapeHtml="false" /></div>
				<input type="text">
				<br/>
				<div id="diffoutput" style="width:100%"> </div>
			</div>
		</div>
        <jsp:include page="../../view/include/client_foot.jsp" />
    </div>
</body>
</html>