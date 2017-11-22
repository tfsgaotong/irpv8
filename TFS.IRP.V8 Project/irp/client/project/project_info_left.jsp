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
       <section class="layoutLeft">
        <nav class="folders">
        	<div class="folder">
            	<p><a href="<s:url namespace="/project" action="projectIndex"></s:url>" title="<s:property value="chnldesc" />">我的圈子</a></p>
                <s:if test="irpProjects!=null && irpProjects.size()>0">
                <section>
                	<ul>
                	<s:iterator value="irpProjects">
                    	<li><a href="<s:url namespace="/project" action="projectinfo"><s:param name="projectId" value="projectid" /></s:url>" title="<s:property value="prname" />"><s:property value="prname" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
            
            <div class="folder">
            	<p><a href="<s:url namespace="/project" action="taskIndex"></s:url>" title="<s:property value="chnldesc" />">我的任务</a></p>
                <s:if test="irpProjectAndShareTasks!=null && irpProjectAndShareTasks.size()>0">
                <section>
                	<ul>
                	<s:iterator value="irpProjectAndShareTasks">
                    	<li><a href="<s:url namespace="/project" action="findsharetaskbyid"><s:param name="shareTaskId" value="sharetaskid" /></s:url>" title="<s:property value="sharedesc" />"><s:property value="sharedesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
            
        </nav>
	</section>
       
       
       
    </div>
  