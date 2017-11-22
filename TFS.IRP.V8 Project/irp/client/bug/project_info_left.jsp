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
  <script type="text/javascript">
  //修改正在参与为退出圈子
  function changestateValueOne(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue=="正在参与")){
		  $('#person'+projectid).html("<b><i>退出圈子</i></b>");
	  } 
  }
  function changestateValueTwo(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue)=="退出圈子"){ 
		  $('#person'+projectid).html("<b><i>正在参与</i></b>");  
	  }  
  }
  //退出圈子
  function deletePerson(projectid){
	$.dialog.confirm('确定退出圈子？再次加入需要与圈子负责人联系',function(){
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
		if(_attText=="关注圈子"){
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
		$('#attention'+_projectId).html("<b><i>取消关注</i></b>"); 
	}
}
function changeTextTwo(_projectId){
	var butText=$('#attention'+_projectId).text();  
      if($.trim(butText)=="取消关注"){
		$('#attention'+_projectId).html("<b><i>正在关注</i></b>"); 
	  }
}	
function toaddproject(){
		var result = $.ajax({
			url: '<%=rootPath%>project/toaddbug.action',
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$.dialog({
		 	title:'新建圈子',
			max:false,
			min:false,
			width:'300px',
			height:'150px',
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
							url:'<%=rootPath%>bug/addBug.action',
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
</script>
 <div class="leftmenu"> 
 	<s:hidden name="projectId"  id="projectId" />
        <dl> 
       	  	<dt><em class="c"></em><a id="task_page"<s:if test="taskorproject=='project'">class="x"</s:if>>任务</a></dt>
       	  		<!-- 原来现实的下部信息   <%=rootPath %>project/projectinfo.action?projectId=
       	  		<dd class="ellipsis"> 
       	  		<s:iterator value="irpProjects">
       	  			<a target="_blank" class="x" title="<s:property value="prname"/>" href="<%=rootPath %>project/projectinfo.action?projectId=<s:property value='projectid'/>">
       	  			<img src="<%=rootPath %>client/images/arrow_right.gif" alt="" width="7px;" height="7px;"  /><s:property value="prname"/></a>
       	  		</s:iterator> 
       	  		</dd>
       	  		 -->
       	 	<dt><em class="b"></em><a id="bug_page"  <s:if test="taskorproject=='task'">class="x"</s:if>>Bug</a></dt> 
       	 	 							
        	<!-- 原来现实的下部信息              <s:url action="simpleprojectbug" namespace="/project"  />      <%=rootPath %>project/simpleprojectbug.action
        	<dd class="ellipsis">
        	<s:iterator value="irpProjectShareTasks">
       	  			<a target="_blank" class="x" title="<s:property value="sharedesc"/>" href="<%=rootPath %>project/findsharetaskbyid.action?shareTaskId=<s:property value='sharetaskid'/>">
       	  			<img src="<%=rootPath %>client/images/arrow_right.gif" alt="" width="7px;" height="7px;"  /><s:property value="sharedesc"/></a>
       	  		</s:iterator> 
        	</dd>
        	-->
        </dl>
    </div>
  