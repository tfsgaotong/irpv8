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
<title>统计信息</title>
</head>
<body>
<script type="text/javascript">
function jump(_sUrl){
	$('body').layout('panel','center').panel('refresh',_sUrl);
}
$(function (){
	var isInit = false;
	$('#menu').accordion({
		onSelect : function(title){
			if(isInit&&title=="用户统计"){
				jump('<%=rootPath %>stat/statuserscoreexperience.action?pageNum=1&orderField');
			}else if(title=="微知统计"){
				jump('<%=rootPath %>site/everydaypublishmicroblog.action?timeLimit=thismonth');
				isInit = true;
			}else if(title=="知识统计"){
				jump('<%=rootPath %>site/everydaypublishdocumentamount.action?timeLimit=thismonth');
				isInit = true;
			}else if(title=="问答统计"){
				jump('<%=rootPath %>stat/statquestiontrend.action?timeLimit=thismonth');
				isInit = true;
			}else if(title=="系统统计"){
				jump('<%=rootPath %>site/systemvitality.action?timeLimit=thisday');
				isInit = true;
			}else if(title=="签到统计"){
				jump('<%=rootPath %>sign/signPersonStatement.action?timeLimit=thisday');
			}else if(title=="部门统计"){
				jump('<%=rootPath %>site/tongjiceshi.action?itype=1&groupId=1');
			}else if(title=="栏目统计"){
				jump('<%=rootPath %>site/channelstatis.action?itype=1&groupId=2');
			}else if(title=="请假统计"){
				jump('<%=rootPath %>admin/stat/static_leaveyeardaysByEn.jsp');
			}else if(title=="会议室统计"){
				jump('<%=rootPath %>asseroomapply/roomdate.action?timeLimit=thismonth&searchWord=all');

			}else if(title=="加班统计"){
				jump('<%=rootPath %>admin/stat/static_worktime_menu.jsp');

			}else if(title=="统计报表"){
				jump('<%=rootPath %>stat/statisticalreport.action');
				isInit = true;
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
function addDepart(){
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
	    		if(sGroupIds=='a'){		    		
		    			$.messager.alert("失败","至少选择一个部门");
		    		}else{
		    			$('#createUser').dialog('destroy');    			    			
	    				jump('<%=rootPath %>leave/deptongji.action?type=1&groupIds='+sGroupIds+'&c_start_end=logs_month&ischoose=1');   			
	    		
		    		}	    		
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

}
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
	/*
	选会议室
	*/
	
	
	function selectAsseroom(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="createasseroom";
		document.body.appendChild(dialogDiv);
		$('#createasseroom').dialog({   
		    modal:true,
		    href:'<%=rootPath%>asseroom/asseroominfo.action?pageNum=1&orderField&searchWord&searchType',
		    title:'会议室选择',
		    width:615,
		    height:531,
		    resizable:true,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    		var _asseroomid = "";   
		    		$("input[name='asseroomcatach']:checked").each(
						 function(){
							 _asseroomid+=$(this).val() + ',';
						 }
					 );
					if(_asseroomid==""){
						$.messager.alert("提示","请选择会议室");
						return false;
					}
		    		$('#createasseroom').dialog('destroy');
		    		jump('<%=rootPath%>asseroomapply/roomcount.action?pageNum=1&orderField&timeLimit=thismonth&asseroomids='+_asseroomid);	    			
		    		
		    	} 
		    },{ 
		    	text: '取消',
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#createasseroom').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#createasseroom').dialog('destroy');
		    }
		});
	}
	function chooseDepartment(){
		var dialogDiv = document.createElement("div");
		dialogDiv.id="createUser";
		document.body.appendChild(dialogDiv);
		$('#createUser').dialog({   
		    modal:true,
		    href:'<%=rootPath %>user/userByleave.action',
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
		    	
		    		if(sGroupIds=='a'){		    		
		    			$.messager.alert("失败","至少选择一个部门");
		    		}else{
		    			jump('<%=rootPath %>leave/compareLeaves.action?type=1&orderField&c_start_end=logs_month&groupIds='+sGroupIds);	    			
			    		$('#createUser').dialog('destroy');
		    		}
		    		
		    		
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
		
	}
</script>
<div id="menu" class="easyui-accordion" fit="true" border="false">
	<div title="用户统计" style="padding:0px;" selected="true" class="arrowsidemenu">
	 	<ul class="menucontents">
		  <li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>stat/statuserscoreexperience.action?pageNum=1&orderField')">积分经验统计</a></li>
		  <li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>stat/systemthequantum.action?timeLimit=thismonth')">访问时间段统计</a></li>
			<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>site/personvalueexperience.action?timeLimit=thismonth')">用户积分经验统计</a></li>
	    </ul>
	</div>
	<div title="问答统计" style="padding:0px;"  class="arrowsidemenu"></div>
	
	<div title="微知统计" style="padding:0px;"></div>
	<div title="系统统计" style="padding:0px;"  class="arrowsidemenu">
		<ul class="menucontents">
		  <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/systemvitality.action?timeLimit=thisday')">系统活跃度</a></li>
		  <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/systemvisitedamount.action?timeLimit=thismonth')">系统访问量</a></li>
	     <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/systemvaluelink.action?timeLimit=thismonth')">系统积分、经验</a></li>
	     <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/hotselectkey.action?keyAmount=10')">热门检索</a></li>
	    </ul>
	</div>
	<div title="知识统计" style="padding:0px;"  class="arrowsidemenu">
		<ul class="menucontents">
		  <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/everydaypublishdocumentamount.action?timeLimit=thismonth')">每天知识发布量</a></li>
	     <%-- <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/everydaypublishdocumentamount.action?timeLimit=thismonth')">按日知识访问量</a></li>
		  --%>
	     <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/documentquality.action?timeLimit=thismonth')">知识质量统计图</a></li>
	     <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>site/documentsctter.action?timeLimit=thismonth')">知识分数分布图</a></li>
	    </ul>
	</div>
	<div title="签到统计" style="padding:0px;"  class="arrowsidemenu">
		<ul class="menucontents">
			<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>sign/signPersonStatement.action?pageNum=1')">个人概况统计</a></li>
	    	<li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>sign/checkByBing.action?timeLimit=thismonth')">企业概况统计</a></li>
	    	<li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>sign/signPersonSpecific.action?timeLimit=thismonth')">个人详细统计</a></li>
	    </ul>
	</div>
	<div title="部门统计" style="padding:0px;"  class="arrowsidemenu">
		<ul class="menucontents">
			<li><a href="javascript:void(0);"  onclick="tfs();">选择部门</a></li></ul>
	</div>
	<div title="栏目统计" style="padding:0px;"  class="arrowsidemenu">
	<ul class="menucontents">
			<li><a href="javascript:void(0);"  onclick="addDocument(2,-1);">选择栏目</a></li></ul>
			</div>
	<div title="会议室统计" style="padding:0px;"  class="arrowsidemenu">
	<ul class="menucontents">
			<li><a href="javascript:void(0)" onclick="jump('<%=rootPath %>asseroomapply/roomdate.action?timeLimit=thismonth&searchWord=all')">会议室时间统计</a></li>
			<li><a href="javascript:void(0);"  onclick="selectAsseroom();">选择会议室</a></li></ul>
	</div>
	<div title="请假统计" style="padding:0px;"  class="arrowsidemenu">
	<ul class="menucontents">
			
			<li><a href="javascript:void(0);"  onclick="jump('<%=rootPath %>admin/stat/static_leaveyeardaysByEn.jsp')">企业请假概况统计</a></li>
			<li><a href="javascript:void(0);"  onclick="chooseDepartment()">选择部门</a></li></ul>
	</div>
	
		<div title="加班统计" style="padding:0px;"  class="arrowsidemenu">
	<ul class="menucontents">	
	<li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>admin/stat/static_worktime_menu.jsp');" >企业加班概况统计</a></li>	
			<%-- <li><a href="javascript:void(0);" onclick="jump('<%=rootPath %>leave/workTimePersonStatement.action?c_start_end=logs_month');" >个人加班统计</a></li>
			<li><a href="javascript:void(0);"  onclick="jump('<%=rootPath %>leave/deptongji.action?type=1&groupIds=1&c_start_end=logs_month');">部门加班统计</a></li> --%>
			<li><a href="javascript:void(0);"  onclick="addDepart()">选择部门</a></li>
	</ul>
	</div>
	<div title="统计报表" style="padding:0px;"  class="arrowsidemenu"></div>
</div>
</body>
</html>