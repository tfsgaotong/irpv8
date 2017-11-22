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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>日程安排</title>
<link rel="Bookmark" href="images/24pinico.ico" />
<link rel="Shortcut Icon" href="images/24pinico.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
body{
	behavior:url(js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
#daytable{
border: 1px solid ;
border-collapse:collapse;
}
#daytable tr th{
font-size: 20px;
}
#daytable tr td,th{
border: 1px solid ;
}
#daytable tr td font{
margin-left: 3px;
}
.today{
background-color: lightblue;
}
.handcss{
cursor: hand;
}
#daything table{
border-collapse:collapse;
}

</style>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>

</head>
<body onload="selected('votepage');loadmonththing()">
<script type="text/javascript">
function loadricheng(){
	var result = $.ajax({
		url: '<%=rootPath%>client/schedule/schedule_menu.jsp',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#richeng").html(result);
	
}
function hotVote(){
	$('#hotvote').empty();
	var sUrl="<%=rootPath%>menu/find_allgothing.action";
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		dataType: "json",
		async: false,
	    cache: false  
 	}).responseText;
	$('#hotvote').html(sHtmlConn);
}

function loadmonththing(){
	var cyear=$.trim($("#selyear").val());
	var cmonth=$.trim($("#selmonth").val());
	$.ajax({ 
 		type:'post', 
 		url: '<%=rootPath%>menu/find_monthgothing.action?cyear='+cyear+'&cmonth='+cmonth,
 		dataType: "json",
		async: false,
	    cache: false,
	    success:function(map){
	    	var len=$("#daytable").find("td").length;
	    	for(var i=0;i<len;i++){
	    		var ctext=$("#daytable").find("td:eq("+i+")").children("font").text();
	    		if($.trim(ctext)!=null&&$.trim(ctext).length>0){
	    			  for (var key in map) {
	    				  if($.trim(ctext)==key){
		    	    			$("#daytable").find("td:eq("+i+")").find("span").html(map[key]);
		    	    		}
	    			  }
	    		}
	    	}
	    }
 	});
}

$(function(){
	loadricheng();
	hotVote();
	
});

function dongdiv(_value){
	var cyear=$.trim($("#selyear").val());
	var cmonth=$.trim($("#selmonth").val());
	var cday=$.trim($(_value).find("font").text());
	var result = $.ajax({
		url: '<%=rootPath%>menu/schedule_floatday.action?cyear='+cyear+'&cmonth='+cmonth+'&cday='+cday,
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	$("#daything").html(result);
	$("#daything").show();
	var position =$(_value).position();
	document.getElementById("carddiv").style.marginLeft=(position.left-200)+"px";
	document.getElementById("carddiv").style.marginTop=(-900+position.top)+"px";
	
};
var handle=null;
function lookthing(_value){
	if ($.trim($(_value).find("span").text())!=null&&$.trim($(_value).find("span").text()).length>0) {
		handle=setTimeout(function(){
			if($("#carddiv").length>0){
				
			}else{
				dongdiv(_value);
			}
		},1000);
	}else{
		$("#daything").hide();
		$("#daything").html("");
	}
}

function hidedaything(){
	clearTimeout(handle);
	$("#daything").hide();
	$("#daything").html("");
}


function cNewthing(myElement){
	var dCurDate = new Date();
	var choseday="";
	if(myElement==null){
		choseday=dCurDate.getFullYear()+"-"+dCurDate.getMonth()+"-"+dCurDate.getDate()+" "+dCurDate.getHours()+":"+dCurDate.getMinutes();
	}else{
		choseday=$("#selyear").val()+"-"+$("#selmonth").val()+"-"+($(myElement).find("font").html())+" "+dCurDate.getHours()+":"+dCurDate.getMinutes();
	}
	var result = $.ajax({
		url: '<%=rootPath%>menu/jumb_to.action',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	//初始化弹出框
	$.dialog({
		title:'添加事项',
		content: result,
		max: false,
	    min: false, 
	    lock:true,
	    width:500,
	    height:200,
	    ok: function(){
	    	var queryString = $('#addScheduleform').serialize();
	    	$.ajax({
	    		type:"post",
	    		url: '<%=rootPath%>menu/add_schedule.action?'+queryString,
	    	    async: false,
	    	    cache: false,
	    	    success:function(date){
	    	    	if(date=="ok"){
	    	    		$.dialog.tips('添加成功',1,'32X32/succ.png');
	    	    	}
	    	    }
	    	})
	    },
	    okVal:'添加',
	    cancelVal: '关闭',
	    cancel: true,
	    padding: 0
	});
	$('#starttime').datetimebox({   
	    value: choseday,   
	    required: true   
	}); 
	$('#endtime').datetimebox({   
	    value: choseday,   
	    required: true   
	});
			
}
/**
 * 选择用户
 */
 function jump(_form){ 
		var sContent = findSelectUserContent(_form.serialize());  
		lhbDialog.get('selectUser',1).content(sContent); 
	}
function findSelectUserContent(_postData){
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>user/select_user.action',
		    data: _postData,
		    async: false,
		    cache: false
		}).responseText;
		return result;
	} 
function addUser(){
	//获得内容
	var result = findSelectUserContent('init=0&ismaxamount=false'); 
	//初始化弹出框
	lhbDialog = $.dialog({
		id: 'selectUser',
		title:'选择用户',
		content: result,
		max: false,
	    min: false,
		ok: function(){
			$('#dialogPageForm').form('submit',{
    			url : "<%=rootPath %>microblog/sendmessageselect.action",
    			success:function(usernames){
    				if(usernames!=2){
    					$('#messageuserinfo').val($('#messageuserinfo').val()+usernames);
    				}
    			}
			});
	    },
	    okVal:'保存',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
}


  
</script>
	<form id="pageForm">
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize"/>
	</form>
	<div class="bg01">
  <!--头部菜单-->
  <jsp:include page="../include/client_head.jsp" />
<div class="main">
<!--左侧内容-->
<div class="left">
	<div style="float: right;margin-right: 50px;font-size: 16px;cursor: pointer; "> <a onclick="cNewthing(null)"> <font class="linkbh14">添加日程</font>  </a></div>
	<div id="richeng" style=""></div>
	<div id="daything"></div>
</div>
<div class="right">
	<div class="duo">
		<%--热门投票 --%>
		<dl id="hotvote"></dl> 
		
    </div>
</div>
   <jsp:include page="../include/client_foot.jsp" />
</div>
<!--左侧内容结束-->
</div>
</body>
</html>