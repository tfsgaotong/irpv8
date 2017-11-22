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
   .layoutLeft{width:245px; float:left; display:inline;}
  </style>
  <script type="text/javascript">
  //修改正在参与为退出圈子
  function changestateValueOne(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue=="正在参与")){
		  $('#person'+projectid).html("<b>退出圈子</b>");
	  } 
  }
  function changestateValueTwo(projectid){  
	  var cValue=$('#person'+projectid).text(); 
	  if($.trim(cValue)=="退出圈子"){ 
		  $('#person'+projectid).html("<b>正在参与</b>");  
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
		if(_attText=="关注圈子"||_attText=="关注圈子"){
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
		$('#attention'+_projectId).html("<b>取消关注</b>"); 
	}
}
function changeTextTwo(_projectId){
	var butText=$('#attention'+_projectId).text();  
      if($.trim(butText)=="取消关注"){
		$('#attention'+_projectId).html("<b>正在关注</b>"); 
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
</script>
 <div class="leftmenu"> 
 		<s:hidden name="projectId" id="projectId" />
 		<%-- <section class="layoutLeft">
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
                <s:if test="irpProjectShareTasks!=null && irpProjectShareTasks.size()>0">
                <section>
                	<ul>
                	<s:iterator value="irpProjectShareTasks">
                    	<li><a href="<s:url namespace="/project" action="findsharetaskbyid"><s:param name="shareTaskId" value="sharetaskid" /></s:url>" title="<s:property value="sharedesc" />"><s:property value="sharedesc" /></a></li>
                    </s:iterator>
                    </ul>
                </section>
                </s:if>
            </div>
            
        </nav>
	</section> --%>
 		
 	
 	 <section class="leftContent" style="width:245px; float:left; display:inline;">

            <!-- 分类列表 -->
            <nav class="allBtns">
                <ul>
                      <li>
                           <a href="<s:url namespace="/project" action="projectIndex"></s:url>" title="<s:property value="chnldesc" />">
                             		 我的圈子
                            </a>
                           <%--  <s:if test="irpProjects!=null && irpProjects.size()>0">
                                <div class="moreBtns">
                                    <div class="line">
                                        <s:iterator value="irpProjects">
                                            <h1>
                                               <a href="<s:url namespace="/project" action="projectinfo"><s:param name="projectId" value="projectid" /></s:url>" title="<s:property value="prname" />"><s:property value="prname" /></a>
                                            </h1>
                                        </s:iterator>
                                    </div>
                                </div>
                            </s:if> --%>
                        </li>
                        
                        <li>
                           <a href="<s:url namespace="/project" action="taskIndex"></s:url>" title="<s:property value="chnldesc" />">
                             		 我的任务
                            </a>
                           <%--  <s:if test="irpProjectShareTasks!=null && irpProjectShareTasks.size()>0">
                                <div class="moreBtns">
                                    <div class="line">
                                        <s:iterator value="irpProjectShareTasks">
                                            <h1>
                                              <a href="<s:url namespace="/project" action="findsharetaskbyid"><s:param name="shareTaskId" value="sharetaskid" /></s:url>" title="<s:property value="sharedesc" />"><s:property value="sharedesc" /></a>
                                            </h1>
                                        </s:iterator>
                                    </div>
                                </div>
                            </s:if> --%>
                        </li>
                </ul>
            </nav>
        </section>
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
    </div>
  