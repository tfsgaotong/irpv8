<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<style type="text/css">
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
  </style>
  	<style type="text/css">
.ztree * {font-size: 9pt;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:300px;height:30px;padding-top: 0px;}
.ztree li a:hover {text-decoration:none; background-color: #BDFBF8;}
.ztree li a span.button.switch {visibility:hidden}
.ztree.showIcon li a span.button.switch {visibility:visible}
.ztree li a.curSelectedNode {background-color:#ADE5EF;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li a.level0 span {font-size: 150%;font-weight: bold;}
.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}
	</style>
  <script type="text/javascript">
  //修改正在参与为退出圈子
  function changestateValueOne(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue=="正在参与")){
		  $('#person'+projectid).html("<b style='margin-left: 27px;'>退出项目</b>");
	  } 
  }
  function changestateValueTwo(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue)=="退出项目"){ 
		  $('#person'+projectid).html("<b style='margin-left: 27px;'>正在参与</b>");  
	  }  
  }
  //退出圈子
  function deletePerson(projectid){
	$.dialog.confirm('确定退出项目？再次加入需要与项目负责人联系',function(){
			$.ajax({
				type:'post',
				url:'<%=rootPath%>project/deleteattention.action',
				data:{ 
					'projectId':projectid
				},
				success: function(msg){
				 	if(msg=="1"){
				 		$.dialog.tips('退出成功',1,'32X32/succ.png',function(){  
				 			 showoneproject();  
				 		 });	 
				 	} else{
						$.dialog.tips('退出失败',0.5,'32X32/fail.png');	
					}
				}
			 });
	});  
  }  //关注圈子 /取消关注
function addOrDeleteAttaction(_projectId){ 
		var _att=$('#attention'+_projectId).text(); 
		var _attText=$.trim(_att); 
		if(_attText=="关注项目"||_attText=="关注圈子"){
			$.ajax({
				url: '<%=rootPath%>project/addattention.action',
				data:{'projectId':_projectId},
				type:"post",
				dataType: "json", 
			    async: false ,
			    success : function(msg){
			    	if(msg!="0"){
			    		showoneproject();  
			    	}
			    }
			});
		}else if(_attText=="取消关注"){
			 $.ajax({
					url: '<%=rootPath%>project/deleteattention.action',
					data:{'projectId':_projectId},
					type:"post",
					dataType: "json", 
				    async: false ,
				    success : function(msg){
				    	if(msg!="0"){
				    		showoneproject();  
				    	}
				    }
				});
		} 	
}
//更换按钮文本
function changeTextOne(_projectId){
	var butText=$('#attention'+_projectId).text();  
	if($.trim(butText)=="正在关注"){
		$('#attention'+_projectId).html("<b style='margin-left: 27px;'>取消关注</b>"); 
	}
}
function changeTextTwo(_projectId){
	var butText=$('#attention'+_projectId).text();  
      if($.trim(butText)=="取消关注"){
		$('#attention'+_projectId).html("<b style='margin-left: 27px;'>正在关注</b>"); 
	  }
}	
function toaddproject(){
		var result = $.ajax({
			url: '<%=rootPath%>project/toaddproject.action',
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$.dialog({
		 	title:'新建圈子',
			max:false,
			min:false,
			width:'300px',
			height:'250px',
			lock:false,
			resize: false, 
			init:function(){
				 $('#prnameinput').validatebox({required: true});  
				 $('#prdescinput').validatebox({required: true});  
				 $('#keyprint').validatebox({required:true});
			}, 
			content:result,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){},
			ok:function(){
					var isSubmit=$('#addprojectfrm').form('validate');   
				      $('#addprojectfrm').form('submit',{
							url:'<%=rootPath%>project/addproject.action',
							success:function(callback){  
									if(callback!=""){ 
										$.dialog.tips('新建圈子成功',1,'32X32/succ.png',function(){ 
										  showoneproject();
										});						
										}else{
											$.dialog.tips('新建圈子失败',0.5,'32X32/fail.png');	
										}
								},
								error:function(){
									$.dialog.tips('新建圈子失败',0.5,'32X32/fail.png');
								}
							});  
							return isSubmit;
			} 
		 });
	}
	
		var curMenu = null, zTree_Menu = null;
		var setting = {
			view: {
				showLine: false,
				showIcon: true,
				selectedMulti: true,
				dblClickExpand: false,
				addDiyDom: addDiyDom
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};

	
var zTree;
		var zNodes=${json};
		function addDiyDom(treeId, treeNode) {
			var spaceWidth = 10;
			var switchObj = $("#" + treeNode.tId + "_switch"),
			icoObj = $("#" + treeNode.tId + "_ico");
			switchObj.remove();
			icoObj.before(switchObj);

			if (treeNode.level >= 1) {
				var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
				switchObj.before(spaceStr);
			}
		}

		function beforeClick(treeId, treeNode) {
		
		if(treeNode.id=="1"){//总项目
			window.location.href="<%=rootPath%>project/projectIndex.action";
		}else if(treeNode.id=="2"){//总任务
			window.location.href="<%=rootPath%>project/taskIndex.action";
		}
		if(treeNode.pId=="1"){//单个项目projectinfo.action?projectId
		openPage("<%=rootPath%>project/projectinfo.action?projectId="+Math.abs(treeNode.id));
		
		}
		if(treeNode.pId=="2"){//单个任务
		
		openPage("<%=rootPath%>project/findsharetaskbyid.action?shareTaskId="+Math.abs(treeNode.id));
		}
		if(treeNode.pId>2){//单个任务
			openPage("<%=rootPath%>project/findsharetaskbyid.action?shareTaskId="+Math.abs(treeNode.id));
		}
		
			if (treeNode.level == 0 ) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandNode(treeNode);
				return false;
			}
			return true;
		}

			var type=${protype};
		$(document).ready(function(){
			var treeObj = $("#treeDemo");
			$.fn.zTree.init(treeObj, setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			if(type=="0"){
				curMenu = zTree_Menu.getNodes()[0];
			}else{
				curMenu = zTree_Menu.getNodes()[1];
			}
			zTree_Menu.selectNode(curMenu);
			
			treeObj.hover(function () {
				if (!treeObj.hasClass("showIcon")) {
					treeObj.addClass("showIcon");
				}
			}, function() {
				treeObj.removeClass("showIcon");
			});
			
			
			
			
		});
		function openPage(url) {
		window.open(url);
		}
		
		
</script>
 <div class="leftmenu"> 
 		<s:hidden name="projectId" id="projectId" />
 		<ul id="treeDemo" class="ztree"></ul>
     <%--    <dl> 
       	  	<dt><em class="c"></em><a href="<s:url action="projectIndex" namespace="/project" />" <s:if test="taskorproject=='project'">class="x"</s:if>>项目</a></dt>
       	  		<dd class="ellipsis"> 
       	  		<s:iterator value="irpProjects">
       	  			<a target="_blank" class="x" title="<s:property value="prname"/>" href="<%=rootPath %>project/projectinfo.action?projectId=<s:property value='projectid'/>">
       	  		</s:iterator> 
       	  		</dd>
       	 	<dt><em class="b"></em><a href="<s:url action="taskIndex" namespace="/project" />" <s:if test="taskorproject=='task'">class="x"</s:if>>任务</a></dt>  
        	<dd class="ellipsis">
        	<s:iterator value="irpProjectShareTasks">
       	  			<a target="_blank" class="x" title="<s:property value="sharedesc"/>" href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>">
       	  			<img src="<%=rootPath %>client/images/arrow_right.gif" alt="" width="7px;" height="7px;"  /><s:property value="sharedesc"/></a>
       	  		</s:iterator> 
        	</dd>
        </dl> --%>
    </div>
  