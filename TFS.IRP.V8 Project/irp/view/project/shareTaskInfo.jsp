<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
     <title><s:property value="irpProjectShareTask.sharedesc"/> </title>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
	<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
    <link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<link rel="Bookmark" href="images/24pinico.ico" />
	<!-- 新加链接 -->
	<link rel="Shortcut Icon" href="images/24pinico.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<jsp:include page="../include/client_skin.jsp" />  
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>   
	<style type="text/css">body{behavior:url(hover.htc);}  textarea { resize:none; }</style> 
		<link rel="Bookmark" href="images/24pinico.ico" />
	<link rel="Shortcut Icon" href="images/24pinico.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/icon.css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=rootPath%>/client/css/oacss.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	
	.in{
	width: 118px;
	height: 36px;
	background: #5f9ddd;
	border: none;
	border-radius: 3px;
	overflow: hidden;
	text-align: center;
	line-height: 36px;
	font-size: 15px;
	color: #fff;
	float: right;
	display: inline;
	margin-left: 15px;
	}
	.num{
	color: #225491;
	font-size: 13px;
	}
	.inputTitle{
	font-size:16px;
	height:35px;
		border-radius: 3px;
	border: 1px solid #cecece;;
	margin-bottom: 20px;
	padding-left: 5px;
	
	}
	</style>
</head>
<body onload="selected('projectli')" style="background-color: white;">  
<script type="text/javascript"> 
var OffPersonDialog;//任务负责人 //圈子负责人 
var ProjectPersonDialog;//圈子成员/圈子关注者///任务成员
//回复框显示
function showreplayTexarea(_sharetaskid,_personname){ 
	if($('#replayTexarea'+_sharetaskid).css("display")=='none'){
		$('#replayTexarea'+_sharetaskid).show();  
	} 
}
//回复框隐藏
function hidereplayTexarea(_sharetaskid){
	$('#replayTexarea'+_sharetaskid).hide(); 
}
//回复框的值不等于“”的时候显示回复按钮
function showreplayShareDescButton(_sharetaskid){
 	var sharetask=$('#replayShareDesc'+_sharetaskid).val(); 
	if($.trim(sharetask)==""){
			$('#replayShareDescBut'+_sharetaskid).addClass("disable_pub_aprfed"); 
			$('#replayShareDescBut'+_sharetaskid).unbind('click');
	}else{
			$('#replayShareDescBut'+_sharetaskid).removeClass("disable_pub_aprfed"); 
			$('#replayShareDescBut'+_sharetaskid).unbind('click').bind('click',function(){replayShareText(_sharetaskid);});
			inputhowmuchs(_sharetaskid);
	}
}
//回复方法
function replayShareText(_sharetaskid){
		$('#replayShareDescBut'+_sharetaskid).unbind('click');
		 var sharetaskValue=$('#replayShareDesc'+_sharetaskid).val();  
		 $.ajax({
			type:'post',
			url:'<%=rootPath%>project/addreplay.action',
			data:{
				'irpProjectShareTask.sharedesc':sharetaskValue,
				'irpProjectShareTask.parentid' : _sharetaskid
			},
			success: function(msg){
			 	if(msg=="1"){
			 		$.dialog.tips('回复成功',1,'32X32/succ.png',function(){
			 		 window.location.reload(true);
			 		});	 
			 	} else{
					$.dialog.tips('回复失败',0.5,'32X32/fail.png');	
				}
			}
		 });  
}
//删除动态
function deleteShareTask(_sharetaskid){
	$.dialog.confirm('您确定要删除这条动态吗',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deletesharetask.action',
				data:{
					'irpProjectShareTask.sharetaskid':_sharetaskid 
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$.dialog.tips('删除成功',1,'32X32/succ.png',function(){ 
				 			 window.location.reload(true); 
				 		});	 
				 	} else{
						$.dialog.tips('删除失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	}); 
}
//删除任务(负责人操作的)
function deleteShareTaskOff(_sharetaskid){ 
	var projectId=<s:property value='irpProjectShareTask.projectid'/>;
	$.dialog.confirm('您确定要删除这条动态吗',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deletesharetask.action',
				data:{
					'irpProjectShareTask.sharetaskid':_sharetaskid 
				},
				success: function(msg){
				 	if(msg=="0"){
						$.dialog.tips('删除失败',0.5,'32X32/fail.png');	
				 	} else{
				 		$.dialog.tips('删除成功',1,'32X32/succ.png',function(){ 
				 			window.location.replace("project/projectinfo.action?projectId="+projectId);
				 		});	 
					}
				}
			 });
	}); 
}
//选择任务负责人
function checkTaskOffpersonId(projectid){  
	var _offPersonId= $('#taskPersonId').val();
	var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>project/select_user.action',
		data : {  'userIds':_offPersonId,
				  'projectId':projectid,
				  'pageNum':1},
	    async: false,
	    cache: false
	}).responseText; 
	OffPersonDialog=$.dialog({
		id: "selectUser",
		title:'选择任务负责人',
		max:false,
		min:false,
		lock:true, 
		init : function(){ 
			$('#sWord').searchbox(); 
		},
		resize: false,
		width:'500px',
		height:'150px',
		content:result,
		cancelVal:'关闭',
		okVal:'确定',
		cancel:true,
		ok:function(){    
			 var _personId=$('#userIds').val();//以逗号分割的所有用户id  
			 var _personNames=$('#userNames').val(); 
			 $('#offPersonName').val(_personNames);
			 $('#taskPersonId').val(_personId);
		} 
	});  	 
}
//修改任务
function  updateTask(){
	 var textValue=$.trim($('#TaskInput').val());
	 var isvisibleselect=$('#isvisibleselecttask').val(); 
	 var projectid='<s:property value="irpProjectShareTask.projectid"/>'; 
	 var projectpersonidString=$('#taskPersonIds').val();
	 var taskDescTextArea=$('#taskDescTextArea').val();
	 var starttime=$('#taskStart').datebox('getValue');
	 var endtime=$('#taskEnd').datebox('getValue');
	var isstate= "<s:property value='irpProjectShareTask.isstate'/>";
	 var offpersonid=$('#taskPersonId').val(); 
	 var sharetaskid="<s:property value='irpProjectShareTask.sharetaskid'/>";
	 $.ajax({
		type: 'POST',
		url: '<%=rootPath %>project/updatetask.action',
		data : {
				'irpProjectShareTask.sharetaskid': sharetaskid,
				'irpProjectShareTask.sharedesc':textValue,
				'irpProjectShareTask.isvisible':isvisibleselect,
				'irpProjectShareTask.projectid': projectid,
				'irpProjectShareTask.taskdesc':taskDescTextArea,
				'irpProjectShareTask.starttime':starttime,
				'irpProjectShareTask.endtime':endtime,
			  	'irpProjectShareTask.offpersonid':offpersonid,
				'projectPersonIdString':projectpersonidString ,
				'irpProjectShareTask.isstate':isstate
		},
	    async: false,
	    cache: false,
	    success: function(msg){ 
	    		 if(msg=="1"){ 
	    			 $.dialog.tips('修改成功',1,'32X32/succ.png' );
	    		 }else{
	    			 $.dialog.tips('修改失败',1,'32X32/fail.png');	
	    		 }
	    }
	});
}
function startOrClosed(_sharetaskid,closestate){
	 var sharetaskid="<s:property value='irpProjectShareTask.sharetaskid'/>"; 
	 $.ajax({
			type: 'POST',
			url: '<%=rootPath %>project/updatetaskstate.action',
			data : {
					'irpProjectShareTask.sharetaskid': sharetaskid,
					 'irpProjectShareTask.isclosed' : closestate
			},
		    async: false,
		    cache: false,
		    success: function(msg){ 
		    		 if(msg=="1"){ 
		    			 $.dialog.tips('修改成功',1,'32X32/succ.png',function(){
		    				 window.location.reload(true);
		    			 } );
		    		 }else{
		    			 $.dialog.tips('修改失败',1,'32X32/fail.png');	
		    		 }
		    }
		});
}
//初始化dialog弹框选择人
function initDialogShow(){
	$('#dialogPageForm').find('searchWord').val('');
	$('#dialogPageForm').find('searchType').val('');
	$('#dialogPageForm').find('orderField').val('');
	$('#dialogPageForm').find('pageNum').val(1);  
}
//弹出选择 任务人员框
function  toCheckTaskPerson(projectid){ 
		var _names=$('#taskPersonNames').val();
		var _ids=$('#taskPersonIds').val();  
		var usernames=$('#taskPersonNames').val();
		var result = $.ajax({
			type: 'POST',
			url: '<%=rootPath %>project/select_usertask.action',
			data : {'userIds':_ids,
					'projectId': projectid ,
					'userNames':usernames,
					'pageNum':1},
		    async: false,
		    cache: false
		}).responseText;
	 
		ProjectPersonDialog=$.dialog({
			id: 'selectUser',
			title:'选择任务成员',
			max:false,
			min:false,
			lock:true, 
			init : function(){  
				$('#sWord').searchbox({}); 
				 initDialogShow();
			},
			resize: false,
			width:'500px',
			height:'150px',
			content:result,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:true,
			ok:function(){  
				var _personIds=$('#userIds').val();//以逗号分割的所有用户id
				var _personNames=$('#userNames').val();//这是用，分割的用户名称
				if(_personIds==null ||_personIds.length==0){
					$('#taskPersonNames').val("");
					$('#taskPersonIds').val("");
				}else{
					$('#taskPersonNames').val(_personNames);
					$('#taskPersonIds').val(_personIds); 
				}
			} ,close:function(){
				$('#userIds').val('');
			}
		});  	 
}
	function jump(_form){  
		var queryString = _form.serialize(); 
		var result = $.ajax({
							type: 'post',
							url: '<%=rootPath %>project/select_usertask.action', 
							data:queryString,
						    async: false,
						    cache: false
						}).responseText;    
		ProjectPersonDialog.get('selectUser',1).content(result); 
	}
 
//任务中的聚焦
function focusTaskInput(){
	var textValue=$.trim($('#TaskInput').val());
	if(textValue=='要做什么事？') $('#TaskInput').val(''); 
}

function changeTaskTextArea(){ 
	var textVlaue=$('#taskDescTextArea').val();
	inputhowmuchtask();
	if($.trim(textVlaue)=="更详细的说明"){
		$('#taskDescTextArea').val('');
		$('#inputcounttask').html(0);
		
	}else if($.trim(textVlaue)==""){
		$('#taskDescTextArea').val('更详细的说明');
		$('#inputcounttask').html(0);
	}
} 
//任务中的失去焦点
function blurTaskInput(){
	var textValue=$.trim($('#TaskInput').val());
	if(textValue=='') {
		$('#TaskInput').val('要做什么事？'); 
		$('#addtaskbut').unbind('click');
	}else{
		$('#addtaskbut').unbind('click');
	}
	if(textValue!='要做什么事？' && textValue!=''){
		$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
	}
} 

function inputhowmuchs(_sharetaskid){
	$(".input_waprfed").keyup(function(){
	    var inputlen=	$(this).val().length;
	    $("#inputcounts").text(inputlen);
	  	var ss ='#replayShareDescBut'+_sharetaskid;
	    var inputlab=$("#inputcounts").text();
	    var maxinput=$("#maxinputs").text();
	    if(parseInt(maxinput-inputlab)<=0){
	    	$(".input_waprfed").attr("style","border-color: red");
	    	$(ss).addClass("disable_pub_cpsw"); 
			$(ss).unbind('click');
	    }else{
	    	$(".input_waprfed").removeAttr("style");
	    }
	}); 
}


//任务详细信息的说明值得判断
function inputhowmuchtask(){
	$("#taskDescTextArea").keyup(function(){
	    var inputlen=$.trim($(this).val()).length;
	    $("#inputcounttask").text(inputlen);
	  
	    var inputlab=$.trim($("#inputcounttask").text());
	   
	    var maxinput=$.trim($("#maxinputtask").text());
	     
	    if((maxinput-inputlab)<=0){ 
	    	$("#taskDescTextArea").attr("style","border-color: red");  
	    	$('#addtaskbut').unbind('click');
	    }else{
	    	$("#sharedesctextarea").removeAttr("style");
	    	var textValue=$.trim($('#TaskInput').val());
	    	if(textValue!='要做什么事？' && textValue!=''){
				$('#addtaskbut').unbind('click').bind('click',function(){addTask();});
			}  
	    }
	}); 
}
function allprojectLeft(projectid){
	var result = $.ajax({
		url: '<%=rootPath%>project/allproject.action',
		type:"post",
		data:{'taskorproject':projectid},
		dataType: "json", 
	    async: false 
	}).responseText;  
	$('#leftMenu').html(result);
} 

//判断删除事件 鼠标移上
function mouseinrow(sharetaskid){
	var deletealable ="#deletealable"+sharetaskid; 
	$(deletealable).css({visibility:"visible"});
}
	//判断删除事件 鼠标移出
	function mouseoutrow(sharetaskid){
		var deletealable ="#deletealable"+sharetaskid; 
		$(deletealable).css({visibility:"hidden"});
	}
</script>  
<div class="bg01">
<!--头部菜单-->

 <jsp:include page="../include/client_head.jsp" />
 <section class="mainBox">
	<nav class="privateNav">
	</nav>
	</section>

<!--头部菜单end--> 
<div class="mainBox" style=""> 
<!--左侧内容
<div class="left" id="leftMenu">
		<div id="xiangguandocument"></div>
</div> 
-->
<!--左侧内容结束--> 
  <div class="body" style="width: 835px;float: left;margin-right:20px;margin-top: 20px;">  
    <%--动态 --%>
    <div id="shareTaskDiv<s:property value='irpProjectShareTask.sharetaskid'/>" style="margin-bottom: 20px;"><%--class="itemfeed"  --%>
		<div>
		<%--如果是任务就显示任务的样式，如果是动态就显示动态的样式 --%>
 <s:if test="irpProjectShareTask.isstate==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@IS_TASK_STATE">
   <%--如果他是负责人 就可以进行修改--%>  
		  <s:if test="longuserid==irpProjectShareTask.offpersonid">
		<s:if test="@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(irpProjectShareTask.projectid)==@com.tfs.irp.project.entity.IrpProject@IS_CLOSED">
			 <div  id="addTaskDiv"   style="overflow:hidden; background-color: white;border: 0px solid #e9e9e9;">
		<div >
			<input  class="inputTitle" style="width: 800px;color: #cecece;" class="txt_ptaskt"  value="<s:property value='irpProjectShareTask.sharedesc'/>" disabled="disabled"/>   
		<span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
		<div  >
			<input  class="inputTitle" style="width: 800px;color: #cecece;"value="<s:property value='irpProjectShareTask.userName'/>" readonly="readonly"   class="txt_ptaskt"  /> 
			<span class="txt_lptaskt" style="color:red;">*</span>
	    </div>  
	    <div  >
			<input  class="inputTitle" style="width: 800px;color: #cecece;" placeholder="还有谁参与？" style="width: 800px;color: #cecece;" class="txt_ptaskt"  readonly="readonly"   id="taskPersonNames" value="<s:property value='taskPersonNames'/>" type="text" /> 
			<span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
	    <div >
	     <div ></div>
			<textarea class="inputTitle" class="txt_ptaskt" style="width: 800px;height: 60px;color: #cecece;" cols="20" rows="2" readonly="readonly"><s:property value="irpProjectShareTask.taskdesc" /></textarea> 
		</div> 
	   <div >  
		   	    <span style="float: left;">
		   	    	<input disabled="disabled" class="txt_ptaskt day_ptask" value='<s:date name="irpProjectShareTask.starttime" format="yyyy-MM-dd" />' re title="何时开始" type="text" alt="何时开始"/>
		   	    	<span class="line_dptaskt" style="float: none;">——</span>
		        	<input disabled="disabled" class="txt_ptaskt day_ptask" value='<s:date name="irpProjectShareTask.endtime" format="yyyy-MM-dd" />' title="何时完成" type="text" alt="何时完成"/> 
		        </span> 
	   </div> 
	     <s:if test="irpProjectShareTask.isclosed==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED&&@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(irpProjectShareTask.projectid)==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"> 
	     <span style="float: right;padding-right: 5px;">
	   	<a onclick="showreplayTexarea(<s:property value='irpProjectShareTask.sharetaskid'/>,'<s:property value="irpProjectShareTask.userName"/>')" class="pub_aptaskt" title="回复" href="javascript:void(0);">
		 回复</a>
	   </span>
	   </s:if>
 	</div>
		</s:if>
		<s:else>
		 <%--task --%>  
 		<div  id="addTaskDiv"   style="overflow:hidden; background-color: white;border: 0px solid #e9e9e9;">
		<div>
		<input class="inputTitle" style="width: 800px;color: #cecece;"id="TaskInput" class="txt_ptasktinfo" onfocus="focusTaskInput()" onblur="blurTaskInput()" title="" value="<s:property value='irpProjectShareTask.sharedesc'/>" type="text" />  
		 <span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
		<div >
			<input  class="inputTitle" placeholder="负责人"  style="width: 800px;color: #cecece;" id="offPersonName" value="<s:property value='irpProjectShareTask.userName'/>" readonly="readonly" onclick="checkTaskOffpersonId(<s:property value='irpProjectShareTask.projectid'/>)"   class="txt_ptaskt"  type="text"/> 
			<input id="taskPersonId" type="hidden" value="<s:property value='irpProjectShareTask.offpersonid'/>"/> 
			 <span class="txt_lptaskt" style="color:red;">*</span>
	    </div>  
	     
	    <div >
	    	<input    id="taskPersonIds" name="taskPersonIds" value="<s:property value='projectPersonIdString'/>" type="hidden"/>
			<input class="inputTitle" placeholder="还有谁参与？" style="width: 800px;color: #cecece;" class="txt_ptaskt" name="taskPersonNames" value="<s:property value='taskPersonNames'/>" readonly="readonly"   id="taskPersonNames"  onclick="toCheckTaskPerson(<s:property value='irpProjectShareTask.projectid'/>)" type="text" /> 
		 <span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
	    <div >
	     <div style="float: right;">
            <span class="num_ccpsw" id="inputcounttask">0</span>/<label id="maxinputtask"><s:property value="maxAmount"/> </label>
        </div>
			<textarea class="inputTitle"style="width: 800px;height: 60px;color: #cecece;"onfocus="changeTaskTextArea()" onblur="changeTaskTextArea()"  id="taskDescTextArea" class="txt_ptaskt" cols="20" rows="2" ><s:property value="irpProjectShareTask.taskdesc"/> </textarea> 
		</div> 
		
		<div class="">
	   <s:iterator value="irpProjectShareTask.attachedList">
 			 	<li class="item_dl">
					<div class="ico_idl">
							<div class="ico_ffed">
							<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
					     		 <img  src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(attfile,"_150X150")'/>"  style="width: 48px;height: 48px;"/>  
					     	</s:if> <s:else>
					     	<img src="<%=rootPath%>client/images/doc.jpg"/>
					     	</s:else> 
							</div>
					</div>
					<div class="right_idl">
						<div class="name_ridl">
							<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
			                 	 <a  target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>"><s:property value="attdesc"/></a> 
			               </s:if>
			                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
			               <a  target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>"><s:property value="attdesc"/></a> 
	               			</s:elseif>
	               			<s:else>
	               			<s:property value="attdesc"/>
	               			</s:else>
						</div>
						<div class="action_ridl">
							<div class="who_aridl">
								<a  target="_blank" class="user_waridl" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
									<%--用户名称 --%>
									<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(cruserid)"/>
								</a>
								<span>上传于</span>
								<s:date name="crtime" format="yyyy-MM-dd HH:mm"/>
							</div>
							<div class="fun_aridl"> 
							<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
			                 	 <a  class="preview_faridl"  target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">预览</a> 
			               </s:if>
			                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
			               <a  class="preview_faridl"  target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">预览</a> 
	               			</s:elseif>
									<a class="down_faridl" title="" href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
		 					</div>
	 					</div>
					</div>
				</li>
 			  </s:iterator>
	   
	   
	   </div>
		
		
		<div > 
		<div>   
		   	    <span  style="float: left;">
		   	     <input title="何时开始" name="irpProjectShareTask.starttime" value='<s:date name="irpProjectShareTask.starttime" format="yyyy-MM-dd" />' type="text"   id="taskStart" class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 
		   	     —— 
		         <input title="何时完成" name="irpProjectShareTask.endtime" value='<s:date name="irpProjectShareTask.endtime" format="yyyy-MM-dd" />' type="text" id="taskEnd" class="easyui-datebox" editable="false" validType="EndTime">
				</span> 
	   </div> 
	
			</div>
			
			
	<div>
	
	  <span style="float: left;padding-right: 5px;padding-top: 20px;width: 550px;">
				<s:if test="irpProjectShareTask.isclosed==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED">
				 <a  class="in" onclick="startOrClosed(<s:property value='irpProjectShareTask.sharetaskid'/>,<s:property value='@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@IS_CLOSED'/>)" class="pub_aptaskt"href="javascript:void(0);">
				关闭任务</a></s:if> 
				<s:else> <a class="in" onclick="startOrClosed(<s:property value='irpProjectShareTask.sharetaskid'/>,<s:property value='@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED'/>)" class="pub_aptaskt" href="javascript:void(0);">
				 重启任务</a></s:else> 
		         <a class="in" onclick="deleteShareTaskOff(<s:property value='irpProjectShareTask.sharetaskid'/>)" class="pub_aptaskt" title="删除" href="javascript:void(0);">
				 删除</a>
				<a class="in" onclick="updateTask()" class="pub_aptaskt" title="修改" href="javascript:void(0);">
				 修改 </a>
			</span>
	   <s:if test="irpProjectShareTask.isclosed==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED&&@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(irpProjectShareTask.projectid)==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED">
	   <span style="float: left;padding-right: 90px;padding-top: 20px;">
	   	<a class="in" onclick="showreplayTexarea(<s:property value='irpProjectShareTask.sharetaskid'/>,'<s:property value="irpProjectShareTask.userName"/>')" class="pub_aptaskt" title="回复" href="javascript:void(0);">
		 回复</a>
	   </span>
				</s:if> 
	
	</div>		
	    </div> 
	    </s:else>
	</s:if> 
 	<s:else> 
 	<%--task --%>  
 <div  id="addTaskDiv"   style="overflow:hidden;background:white;border: 0px solid #e9e9e9;">
		<div>
			<input class="inputTitle"  style="width: 800px;color: #cecece;"class="txt_ptaskt"  value="<s:property value='irpProjectShareTask.sharedesc'/>" disabled="disabled"/>   
		<span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
		<div >
			<input class="inputTitle" placeholder="负责人" style="width: 800px;color: #cecece;" value="<s:property value='irpProjectShareTask.userName'/>" readonly="readonly"   class="txt_ptaskt" /> 
			<span class="txt_lptaskt" style="color:red;">*</span>
	    </div>  
	    <div >
			<input class="inputTitle"  placeholder="还有谁参与？" style="width: 800px;color: #cecece;"  class="txt_ptaskt"  readonly="readonly"   id="taskPersonNames" value="<s:property value='taskPersonNames'/>" type="text" /> 
			<span class="txt_lptaskt" style="color:red;">*</span>
		</div> 
	    <div >
	     <div ></div>
			<textarea class="inputTitle" style="width: 800px;height: 60px;color: #cecece;" class="txt_ptaskt" cols="20" rows="2" readonly="readonly"><s:property value="irpProjectShareTask.taskdesc" /></textarea> 
		</div> 
		
		
		
	   <div>  
		   	    <span style="float: left;margin-bottom: 20px;">
		   	    	<input disabled="disabled" class="txt_ptaskt day_ptask" value='<s:date name="irpProjectShareTask.starttime" format="yyyy-MM-dd" />' re title="何时开始" type="text" alt="何时开始"/>
		   	    	<span class="line_dptaskt" style="float:none">——</span>
		        	<input disabled="disabled" class="txt_ptaskt day_ptask" value='<s:date name="irpProjectShareTask.endtime" format="yyyy-MM-dd" />' title="何时完成" type="text" alt="何时完成"/> 
		        </span> 
	   </div> 
	     <s:if test="irpProjectShareTask.isclosed==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@NOT_CLOSED&&@com.tfs.irp.project.web.ProjectAction@findIsClosedByProjectId(irpProjectShareTask.projectid)==@com.tfs.irp.project.entity.IrpProject@NOT_CLOSED"> 
	     <span style="float: right;padding-right: 5px;margin-bottom: 20px;">
	   	<a class="in" onclick="showreplayTexarea(<s:property value='irpProjectShareTask.sharetaskid'/>,'<s:property value="irpProjectShareTask.userName"/>')" class="pub_aptaskt" title="回复" href="javascript:void(0);">
		 回复</a>
	   </span>
	   </s:if>
 </div>
  <%--task --%> 	
   </s:else>
</s:if>
  <!--<s:else>  -->
   <%--左边人头 --%>
   <div class="left_ifed"> 
  <s:if test="irpProjectShareTask.isstate==@com.tfs.irp.projectsharetask.entity.IrpProjectShareTask@IS_TASK_STATE">
   <a hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpProjectShareTask.offpersonid'/>">
  	<img alt="" src="<s:property value='irpProjectShareTask.userPicUrl'/>" width="50" height="50" />
  </a>
   (负责人 :<s:property value="irpProjectShareTask.userName"/>)
   </s:if>
   <s:else>
    <a  target="_blank" hideFocus="true" title="" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='irpProjectShareTask.cruserid'/>">
  	<img alt="" src="<s:property value='irpProjectShareTask.userPicUrl'/>" width="50" height="50" />
  </a>
   </s:else>
   </div>
<%--左边人头 --%>
  <div class="right_ifed">
  <div class="main_fed"> 
   <!-- </s:else>  -->
		</div>
  </div> 
  <div class="comment_fed">
  <div class="corner_lfed">  
	  <!-- qq -->
	  <div id="replayTexarea<s:property value='irpProjectShareTask.sharetaskid'/>"  class="pubreply_fed">
		  <div class="notice_prfed clearfix">
			<div >
				<span class="num_ccpsw" id="inputcounts">0</span>/<label id="maxinputs"> <s:property  value="maxAmount"/> </label>
			</div>
		  </div>
		  <div class="area_prfed">
			  <div class="wrap_aprfed">
			  	<textarea onkeyup="showreplayShareDescButton(<s:property value='irpProjectShareTask.sharetaskid'/>)" id="replayShareDesc<s:property value='irpProjectShareTask.sharetaskid'/>" class="input_waprfed" cols="20" rows="2" asff="true" defaultVal=""></textarea>
			  </div>
		  </div>
		<div class="action_prfed clearfix" style="padding-top:10px;padding-bottom:10px;">
			<a class="in"  onclick="hidereplayTexarea(<s:property value='irpProjectShareTask.sharetaskid'/>)" hideFocus="true" class="cancel_aprfed" href="javascript:void(0);"> 取消</a>
			<a class="in" id="replayShareDescBut<s:property value='irpProjectShareTask.sharetaskid'/>" hideFocus="true" class="pub_aprfed disable_pub_aprfed" href="javascript:void(0);"> 回复</a>
		</div> 
	  </div>
	  <!-- qq --> 
  </div>
  <!-- replay -->
  <s:iterator value="irpProjectShareTask.replayShareTaskList">
  <div class="list_fed">
	  <div class="cmt_fed">
		  	<div class="item_cfed">
			  <div class="left_icfed">
				  <a hideFocus="true">
				  	<img src="<s:property value='userPicUrl'/>" width="30" height="30"/>
				  </a>
			  </div>
			  <div class="right_icfed">
				  <div class="itemfeed"  onmouseover="mouseinrow(<s:property value='sharetaskid'/>)" onmouseout="mouseoutrow(<s:property value='sharetaskid'/>)">
				  <a  target="_blank" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='cruserid'/>" hideFocus="true" class="avatar_mcfed skip_cmfed linkbh14" >
				  	<s:property value="userName"/></a><span>： <s:property value="sharedesc"/></span>
				  <div class="action_cfed clearfix" >
					  <div class="btn_acfed">
					  <ul> 
						  <s:if test="cruserid==longuserid">
							  <li class="more_bacfed">
							  <a  style="visibility: hidden;" id="deletealable<s:property value='sharetaskid'/>"   onclick="deleteShareTask(<s:property value='sharetaskid'/>)" hideFocus="true" title="" href="javascript:void(0);">删除</a> 
						  </li>
						  </s:if>  
					  </ul>
					  </div>
				  <span class="time_acfed"><s:date name="crtime" format="yyyy-MM-dd :HH:mm"/></span>
				  </div>
				  </div>
				  
			  </div>
		  </div>
	  </div> 
  </div> 
  </s:iterator>
  <!-- replay -->
  </div>
  </div>
  </div> 
  <%--动态 --%>
  </div>
  
  
  <div style="width: 345px;float: left;background: #eee;height: 500px;">
  	 <s:iterator value="irpProjects">
        	<ul class="listQz01">
            	<li>
                	<section class="picQz">
                    	 <s:if test="projectfile!=null && projectfile!=''">
                            	 <img style="width: 100px;height: 100px;border-radius: 50px;margin: 10px;float: left;" src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(projectfile,"_150X150")'/>" />
                            </s:if>
                            <s:else> 
                             <img style="width: 100px;height: 100px;border-radius: 50px;margin: 10px;float: left;" src="<%=rootPath %>client/images/rl.jpg"></img>
                            </s:else>
                    </section>
                    <section class="infoQz" style="width: 220px;float: left;margin-top: 10px;">
	                    <div style="float: right;font-size: 13px;">
	                    		<b>
								<s:date name="irpProject.starttime" format="yyyy-MM-dd" />
	                                   		</b></span>—<span id="endData"><b>
						    		<s:date name="irpProject.endtime" format="yyyy-MM-dd"/> 
						    	</b>
	                    	</div>
	                    <div style="float: right;display: inline;font-size: 13px;" id="dayLable<s:property value='projectid'/>"></div>
	                                       &nbsp;&nbsp; 
	                                        	<script type="text/javascript">  
														$(function(){ 
															var msg=' <div class="ending"><span style="font-size: 13px;">剩余<font> ' ;
															var _projectId='<s:property value="projectid"/>';  
															var starttime='<s:date name="starttime" format="yyyy-MM-dd"/>';
															var endtime='<s:date name="endtime" format="yyyy-MM-dd"/> '; 
															var startDate =  new  Date(starttime.replace(/-/g,"/"));
															var endDate =  new  Date(endtime.replace(/-/g,"/"));   
															var todayTime=new Date(<%=new java.util.Date().getTime()%>); 
															var dayTime=window.parseInt( Math.abs((todayTime.getTime() - endDate.getTime())/(1000*60*60*24)))+1;  
																msg+=dayTime+'</font>天</span>'; 
																msg+='</div>';
														 	if(todayTime>=endDate){  msg='  <div class="ended"><span style="color:#e69521;font-size: 13px;">圈子已到期，请尽快处理</span></div>'; }
															
														   $('#dayLable'+_projectId).html(msg);
														});
												</script>   
                    	
                    
                        <div class="lineDown" style="font-size: 13px;margin-top: 20px;">
                        		<div class="lineUp">
                        
		                            <h1>  <a target="_blank" style="color: #225491;font-size: 20px;"  href="<%=rootPath %>project/projectinfo.action?projectId=<s:property value='projectid'/>"> <s:property value="prname"/>  </a></h1>
		                        </div>
                            <div class="info">
                            <section style="margin-top: 10px;font-size: 13px;">
                                    <span>私密性：<font class="num">
                                    <s:if test="@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH==ispublish">公开</s:if>
                   					<s:if test="@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH==ispublish">私密</s:if>
                                    
                                    </font></span>
                                </section>
                            	<section style="margin-top: 10px;font-size: 13px;">
                                    <span>动态：<font class="num"><s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countShareTaskCollectByShareTaskId(projectid)"/> </font></span>
                                    &nbsp;&nbsp;&nbsp;<span>任务：<font class="num"><s:property value="@com.tfs.irp.projectsharetask.web.IrpProjectShareTaskAction@countTaskCollectByShareTaskId(projectid)"/></font></span>
                                   &nbsp;&nbsp;&nbsp; <span>成员：<font class="num"><s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findProjectPersonCount(projectid)"/>
                                    </font></span>
                                </section>
                            </div>
                        </div>
                    </section>
                    
                    <div style="margin-left:10px;font-size: 13px;">
                    <span>描述：<font class="num">
                                  <s:property value='irpProject.prdesc'/>
                                  
                                    </font></span>
                    
                    </div>
                </li>
            </ul>
           </s:iterator>
  </div>
</div> 
</div> 
<jsp:include page="../include/client_foot.jsp" /> </body>
</html> 
