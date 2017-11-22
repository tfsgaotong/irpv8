<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<!-- 
<script type="text/javascript" src="admin/js/jquery-1.8.0.min.js"></script>
 -->
<title>会议室管理</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var isInit = false;
	$('#meeting').accordion({
		onSelect : function(title){ 
			if(isInit&& title=="会议管理"){ 
				jump('<%=rootPath %>admin/meeting/asseroomsb.jsp'); 
			}else if(title=="年假管理"){
				jump('<%=rootPath %>admin/meeting/user_leaveyeardays.jsp');
				isInit = true;
				
			}else if(title=="请假加班"){
				jump('<%=rootPath %>admin/set/set_leaveconfig.jsp');
				isInit = true;
			}else if(title=="请假列表"){
			jump('<%=rootPath %>leave/getAllLeaveQuery.action?pageNum=1&orderField&searchWord&searchType');	
			}else if(title=="加班列表"){			
			jump('<%=rootPath %>leave/getAllWorkTimeInfo.action?c_start_end=logs_month');	
			}
				
		}
	});  
});
function tfs(){
	var dialogDiv = document.createElement("div");
	dialogDiv.id="createUser";
	document.body.appendChild(dialogDiv);
	$('#createUser').dialog({   
	    modal:true,
	    href:'<%=rootPath %>user/userus.action',
	    title:'部门选择',
	    width:500,
	    height:531,
	    resizable:true,
	    buttons: [{
	    	text: '提交', 
	    	iconCls: 'icon-ok', 
	    	handler: function() {
	    		var sGroupIds = "";   
	    		sGroupIds=$('#addUserForm').find("input:hidden[name='groupId']").val();
				
	    		$('#createUser').dialog('destroy');
	    		jump('<%=rootPath %>site/tongjiceshi.action?itype=1&groupId='+sGroupIds);	    			
	    		
	    	} 
	    },{ 
	    	text: '取消',
	    	iconCls: 'icon-cancel', 
	    	handler: function() { 
	    		$('#createUser').dialog('destroy');
	    	} 
	    }],
	    onClose:function(){
	    	$('#createUser').dialog('destroy');
	    }
	});

};

function addDocument(_siteid,_parentid){ 

	var _checkChannelid=0;
	//在这里判断跳到添加文档还是选择栏目页面
	if(_parentid==-1){
	 		//跳到选择栏目页面
			var checkchnneldiv=document.createElement("div");
			checkchnneldiv.id="checkchnneldiv";
			document.body.appendChild(checkchnneldiv);

			$('#checkchnneldiv').dialog({
			        modal:true,
				    cache:false,
			        href:'<%=rootPath  %>site/to_stat_document.action?siteid='+_siteid+'&id='+_parentid,
					title:'选择栏目',
					width:400,
					height:500,
					resizable:true,
					maximizable:false,
					 buttons:[{
					    	text: '确定', 
					    	iconCls: 'icon-ok', 
					    	handler: function(){
					    		var sGroupIds = ""; 
					    		sGroupIds=$('#addUserForm').find("input:hidden[name='channelid']").val();
					    	    $('#checkchnneldiv').dialog('destroy');  
					    		jump('<%=rootPath %>site/channelstatis.action?itype=1&groupId='+sGroupIds);	 // 引用跳添加页面
					    	 } 
					    },{
					    	text: '取消',
					    	iconCls: 'icon-no', 
					        handler: function(){	
					        $('#checkchnneldiv').dialog('destroy');
					     }
					    }],
			       onClose:function(){
			    	$('#checkchnneldiv').dialog('destroy');
			      }  
			});
	} 
	}
	//<div title="会议室管理" style="padding:0px;"  class="asseroommenu"></div>
</script>
<div id="meeting" class="easyui-accordion" fit="true" border="false" >
	<div title="会议管理" style="padding:0px;"  class="asseroomsbmenu"></div>
	<div title="请假加班" style="padding:0px;"  class="asseroomsbmenu"></div>
	<div title="年假管理" style="padding:0px;"  class="asseroomsbmenu"></div>
	<div title="请假列表" style="padding:0px;"  class="asseroomsbmenu"></div>
	<div title="加班列表" style="padding:0px;"  class="asseroomsbmenu"></div>
	 
</div>
</body>
</html>