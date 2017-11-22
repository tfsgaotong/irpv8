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
 

  
$(function(){
	backInit();//判断添加前的状态,自动选择项目
})

function backInit(){
	var pid=<s:property  value="tabPid" />;
	//alert(pid);
	if(pid != 0){
		//alert(pid);
		initMeu();
		$('#projectmaindiv').show();
		$("#dlmenutabs").find("a").each(function(_index,_this2){
			if($(_this2).find('input[type=hidden]').val()==pid){
				$(_this2).css("background-color","DFF2F8");
			}else{
				$(_this2).css("background-color","");
			}
		});
			//项目模块 
			$('#projectId').val(pid);
			$('#projectmaindiv').show();
			$("#projectH").css("display","block");
			$("#newprojectdiv").css("display","none");
			//ajax刷新
			$.ajax({
				url: '<%=rootPath%>bug/topagestatistics.action?projectId='+pid,
				type:"post",
			    async: false ,
			    cache: false,
			    success:function(data){
			    	$('#projectmaindiv').html(data);
			    },
			}); 
			$('#addbugbtn').show();
		} 
}

function toaddproject(){
		var result = $.ajax({
			url: '<%=rootPath%>project/toaddprojecttobug.action',
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$.dialog({
		 	title:'新建项目',
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
							url:'<%=rootPath%>project/addproject.action?protype=9',
							success:function(callback){  
									if(callback!=""){ 
										$.dialog.tips('新建项目成功',1,'32X32/succ.png',function(){ 
											window.location.href='<%=rootPath%>bug/tobugmanage.action';
										});						
										}else{
											$.dialog.tips('新建项目失败',0.5,'32X32/fail.png');	
										}
								},
								error:function(){
									$.dialog.tips('新建项目失败',0.5,'32X32/fail.png');
								}
							});  
							return isSubmit;
			} 
		 });
	}
	
	
	function initMeu(){
		$('#topagestatistics').attr("class","over");
		$('#tomebug').attr("class","");
		$('#bugmeto').attr("class","");
		$('#allProject').attr("class","");
		$('#bugStatistics').attr("class","");
	}
	
	/***
	*	项目菜单
	*/
	function projectMenuTab(_this,_num){ 
		initMeu();
		$('#projectmaindiv').show();
		$("#dlmenutabs").find("a").each(function(_index,_this2){
			$(_this2).css("background-color","");
		});
		$(_this).css("background-color","#DFF2F8");
		if(_num==0){
			//项目总体
			$('#projectmaindiv').hide();
			$("#projectH").css("display","none");
			$("#newprojectdiv").css("display","block");
			//ajax刷新
		}else{
			//项目模块 
			$('#projectId').val(_num);
			$('#projectmaindiv').show();
			$("#projectH").css("display","block");
			$("#newprojectdiv").css("display","none");
			//ajax刷新
			$.ajax({
				url: '<%=rootPath%>bug/topagestatistics.action?projectId='+_num,
				type:"post",
			    async: false ,
			    cache: false,
			    success:function(data){
			    	$('#projectmaindiv').html(data);
			    },
			}); 
			$('#addbugbtn').show();
		}  
	}
	
	function gotoproject(objj){
		initMeu();
		var pid=$(objj).find('input[type=hidden]').val();
		
			//项目模块 
			$('#projectId').val(pid);
			$('#projectmaindiv').show();
			$("#projectH").css("display","block");
			$("#newprojectdiv").css("display","none");
			//ajax刷新
			$.ajax({
				url: '<%=rootPath%>bug/topagestatistics.action?projectId='+pid,
				type:"post",
			    async: false ,
			    cache: false,
			    success:function(data){
			    	$('#projectmaindiv').html(data);
			    },
			}); 
	}
	
</script>
 <div class="leftmenu" > 
 		<s:hidden name="projectId" id="projectId" />
        <dl id="dlmenutabs"> 
      	  	<dt><em class="c"></em><a href="javascript:void(0);" onclick="window.location.href='<%=rootPath%>bug/tobugmanage.action'"  style="background-color:#DFF2F8; ">项目</a></dt>
   	  		<dd class="ellipsis"> 
   	  		<s:iterator value="irpProjects"  > 
   	  			<a href="javascript:void(0);"  onclick="projectMenuTab(this,<s:property value="projectid" />)" > 
   	  				<img src="<%=rootPath %>client/images/arrow_right.gif" alt="" width="7px;" height="7px;"  />
   	  				<s:property value="prname"/>
   	  				<input type="hidden" value="<s:property value="projectid" />">
   	  			</a> 
   	  		</s:iterator> 
   	  		</dd>
        </dl>
    </div>
  