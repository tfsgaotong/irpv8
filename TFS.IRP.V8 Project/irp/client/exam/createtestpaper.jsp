<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.tfs.irp.util.LoginUtil,com.tfs.irp.user.entity.IrpUser"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>/client/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/client/css/icon.css" rel="stylesheet" type="text/css"  />
<link rel="stylesheet" href="<%=rootPath%>client/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
.main li input{
	height: 28px;
	line-height: 28px;
	border: 1px solid #D1D1D1;
	color: #666;
	padding: 0px 5px;
	width: 500px;
}
.main li{
	margin: 20px 0 20px 0;
	font-weight: bold;
	
}
</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
		/**
		* 全选当页
		*/	
		var checkstr = "";
		var m_checked = false;
		function checkAllByPage(){
			m_checked = !m_checked;
			$("input[name='qbankids']").attr("checked",m_checked);
			$("input[name='qbankids']").each(function(){
			var booldis = 0;
				if(m_checked==true){
						var chstrarray = checkstr.split(",");
						for(var i in chstrarray){
							if(this.value==chstrarray[i]){
								booldis = 1;
							}
						
						}
						if(booldis==0){
							checkstr +=this.value+",";
						}
						
				}else{
					var chstrarray = checkstr.split(",");
					var disstr = "";
					for(var i in chstrarray){
						if(chstrarray[i]!=''){
							if(chstrarray[i]!=this.value){
								disstr += chstrarray[i]+",";
							
							}
						}
					}
					checkstr = disstr;
				
				}
			});
		}
		//list单个选中
		function checkBC(_this){
			if(_this.checked==true){
				checkstr +=_this.value+",";
			}else{
				if(checkstr!=""){
					var chstrarray = checkstr.split(",");
					checkstr ="";
					for(var i in chstrarray){
						if(chstrarray[i]!=""){
							if(_this.value==chstrarray[i]){
								//移除
							}else{
								checkstr +=chstrarray[i]+",";
							}
							
						}
					}
				}
				
			}
		}
/**
* 选择题
*/
function addTQuestion(){
	      var result = $.ajax({
					url:"<%=rootPath%>exam/linkchoicequestioncontent.action",
					cache:false,
					async:false
				}).responseText;
		$.dialog({
  		content:result,
  		width:800,
  		height:400,
  		title:'引用试题',
  		min:false,
  		max:false,
	    lock:true,
	    ok:function(){
	    	$("#contentdiv").html("");
	    	$("#testpapercontents").val(checkstr);
	    	if(checkstr!=""){
	    		$.ajax({
	    			type:'post',
	    			url:'<%=rootPath%>exam/findqbankbyids.action',
	    			data:{
	    				checkstrids:checkstr
	    			},
	    			cache:false,
	    			async:false,
	    			success:function(content){
			    			var jsonobj=eval(content);  
							for(var i in jsonobj){
								var typsnames = "";
								var types = parseInt($.trim(jsonobj[i].type));
								switch(types){
									case 10:
										typsnames = "多选题";
										break;
									case 20:
										typsnames = "单选题";
										break;
									case 30:
										typsnames = "填空题";
										break;
									case 40:
										typsnames = "判断题";
										break;
								}
								$("#contentdiv").append("<li>"
								+"<div style=\"float:left;\">"+jsonobj[i].id+"</div>"
								+"<div style=\"float:left;margin-left:20px;\">"+typsnames+"</div>"
								+"<div style=\"float:left;margin-left:20px;\">"+jsonobj[i].text+"</div>"
								+"</li></br>");
							} 
	    			
	    			}
	    		
	    		});
	    		
	    	}
	    },
	    okVal:'确认',
//	    cancelVal: '取消',
//	    cancel: true,
	    padding: 0
  	});
	
	
}
/**
* 增加试卷
*/
function addTestPaper(){
	$("#addpaperform").submit();

}


</script>
<title>创建试卷</title>
</head>
<body>
<div class="main" style="background-image: url('');text-align: center;padding: 10px 10px 10px 10px;">
	<form id="addpaperform" action="<%=rootPath %>exam/addtestpaper.action" method="post">
	<div>
		<ul>
			<li>
				标题&nbsp;:&nbsp;<input type="text" name="irpTestPaper.papertitle"  >
			</li>
			<li>
				内容&nbsp;:&nbsp;
				<a href="javascript:void(0)" onclick="addTQuestion()">添加题目</a>
				<input id="testpapercontents" style="display: none;" name="irpTestPaper.papercontent" value="">
				<ul id="contentdiv">
				
				
				</ul>
			</li>
			<li>
				描述&nbsp;:&nbsp;<input type="text" name="irpTestPaper.paperdesc">
			</li>
			<li>
				状态&nbsp;:&nbsp;
				<select name="irpTestPaper.paperstatus">
					<option value="10">未发布</option>
					<option value="20" >已发布</option>
				</select>
			</li>
			<li>
				分类&nbsp;:&nbsp;<input type="text" name="irpTestPaper.papercate" value="1" >
			</li>
			<li>
				时间&nbsp;:&nbsp;<input type="text"  name="irpTestPaper.papertime" >
			</li>
		</ul>
	</div>	
	</form>	
	<a href="javascript:void(0)" style="cursor:pointer;" onclick="addTestPaper()"   >提交</a>
	<jsp:include page="../include/client_foot.jsp" />
</div>
</body>
</html>