<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
     <title><s:property value="irpProject.prname"/> </title>
	<jsp:include page="../include/client_skin.jsp" />  
    <title>版本/模块</title>
<style type="text/css">

 table {   border-collapse:    separate;   border-spacing:  1px 3px;  } 
 </style>
 
 	<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
  </head>
  <body>
   <script type="text/javascript">
   //全局变量
	var editstate=0;
    var addstate=0;
	$(function(){
		$('#modularea').hide();
		$('#versionarea').hide();
	});
    function showmodularea(){
    	if(editstate==0){
	    	if($('#modularea').is(':hidden') && $('#versionarea').is(':hidden')){
	    		$('#modularea').fadeIn();
	    	}else{
	    		$.dialog.tips('请保存！',1,'alert.gif',function(){ 
	    			
	    		});
	    	}
	    	addstate=1;
    	}else{
			$.dialog.tips('请完成编辑！',1,'alert.gif',function(){ 
    			
    		});
		}
    }	
    function showversionarea(){
    	if(editstate==0){
	    	if($('#versionarea').is(':hidden') && $('#modularea').is(':hidden')){
	    		$('#versionarea').fadeIn();
	    	}else{
	    		$.dialog.tips('请保存！',1,'alert.gif',function(){ 
	    			
	    		});
	    	}
	    	addstate=1;
    	}else{
			$.dialog.tips('请完成编辑！',1,'alert.gif',function(){ 
    			
    		});
		}
    }
    function hideversionarea(){
    	$('#versionarea').fadeOut();
    	addstate=0;
    }
    function hidemodularea(){
    	$('#modularea').fadeOut();
    	addstate=0;
    }
    
    function addversion(){
    	var pid=<s:property value="projectId" />;
    	var versionname=encodeURI($('#versionname').val());
    	if(versionname ==''){
			$.dialog.tips('请输入内容！',1,'alert.gif',function(){ 
    			
    		});
    	}else{
	    	urlStr="<%=rootPath%>phone/addversion.action?proid="+pid+"&projectId="+pid+"&versionname="+versionname+"&token="+$('#tokens').val();
	    	var result = $.ajax({
				url:urlStr,
				type:"post",
				dataType: "json", 
			    async: false,
			    success:function(){
			    	$('#versionarea').fadeOut();
			    }
			}).responseText;   
			 $('#bugconfig').html(result);
			 addstate=0;
    	}
    }
    function addmodul(){
    	var pid=<s:property value="projectId" />;
    	var modulname=encodeURI($('#modulname').val());
    	if(modulname ==''){
			$.dialog.tips('请输入内容！',1,'alert.gif',function(){ 
    			
    		});
    	}else{
	    	urlStr="<%=rootPath%>phone/addmodul.action?proid="+pid+"&projectId="+pid+"&modulname="+modulname+"&token="+$('#tokens').val();
	    	var result = $.ajax({
				url:urlStr,
				type:"post",
				//dataType: "json", 
			    async: false,
			    success:function(html){
			    	$('#bugconfig').html(html); 
			    }
			});  
	    	addstate=0;
    	}
    }
    
	//编辑模块    
    function moduledit(objj){
		if(editstate==0){
			if(addstate==0){
		    	var t_this = $(objj);
		    	var list=$(t_this).parent().parent().find("td:lt(1)");
		    	var bugconfigid=$(t_this).parent().parent().find("td:lt(1)").find('input[name=bugconfigid]').val();
		    	$.each(list,function(i,obj){
		    		$(obj).html("<input type='text' name='modulname' style='width:100px;' value='"+$(obj).text()+"'/>	<a href='javascript:void(0)' onclick='editsavemodul(this)'>保存</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='quxiaoedit(this)'>取消</a><input  type='hidden' name='oldname' value='"+$(obj).text()+"' /><input type='hidden' value='"+bugconfigid+"' name='bugconfigid' />");
		    	});
		    	editstate=1;
				}else{
					$.dialog.tips('请完成添加！',1,'alert.gif',function(){ 
		    			
		    		});
				}
		}else{
			$.dialog.tips('请完成编辑！',1,'alert.gif',function(){ 
    			
    		});
		}
    	
    }
   
	
	//编辑版本
    function versionedit(objj){
    	if(editstate==0){
    		if(addstate==0){
		    	var t_this = $(objj);
		    	var list=$(t_this).parent().find("td:lt(1)");
		    	var bugconfigid=$(t_this).parent().find("td:lt(1)").find('input[name=bugconfigid]').val();
		    	$.each(list,function(i,obj){
		    		$(obj).html("<input type='text' name='versionname' style='width:100px;' value='"+$(obj).text()+"'/>	<a href='javascript:void(0)' onclick='editsaveversion(this)'>保存</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='quxiaoedit(this)'>取消</a><input  type='hidden' name='oldname' value='"+$(obj).text()+"' /><input type='hidden' value='"+bugconfigid+"' name='bugconfigid' />");
		    	});
		    	editstate=1;
    		}else{
				$.dialog.tips('请完成添加！',1,'alert.gif',function(){ 
	    			
	    		});
			}
    	}else{
			$.dialog.tips('请完成编辑！',1,'alert.gif',function(){ 
    			
    		});
		}
    	
    }
 	
	//取消编辑
    function quxiaoedit(objj){
    	var t_this = $(objj);
    	var list=$(t_this).parent().parent().find("td:lt(1)");
    	var thisname=$(objj).parent().find('input[name=oldname]').val();
    	var bugconfigid=$(objj).parent().find('input[name=bugconfigid]').val();
    	$.each(list,function(i,obj){
    		$(obj).html(thisname+"<input type='hidden' value='"+bugconfigid+"' name='bugconfigid' />");
    	});
    	editstate=0;
    }
	
	//编辑保存版本
	function editsaveversion(objj){
		var projectId=<s:property value="projectId" />;
		var t_this = $(objj);
    	var list=$(t_this).parent().find("td:lt(1)");
    	var versionname=$(t_this).parent().parent().find("td:lt(1)").find('input[name=versionname]').val();
    	var bugconfigid=$(t_this).parent().parent().find("td:lt(1)").find('input[name=bugconfigid]').val();
    	var urlStr=("<%=rootPath%>phone/editsaveversion.action?versionname="+versionname+"&projectId="+projectId+"&bugconfigid="+bugconfigid+"&token="+$('#tokens').val()).replace(" ","");
    	var result = $.ajax({
			url:urlStr,
			type:"post",
			dataType: "json", 
		    async: false,
		    success:function(){
		    	
		    }
		}).responseText;   
		 $('#bugconfig').html(result);
	}
	
	//编辑保存模块
	function editsavemodul(objj){
		var projectId=<s:property value="projectId" />;
		var t_this = $(objj);
    	var list=$(t_this).parent().find("td:lt(1)");
    	var modulname=encodeURI($(t_this).parent().parent().find("td:lt(1)").find('input[name=modulname]').val());
    	var bugconfigid=$(t_this).parent().parent().find("td:lt(1)").find('input[name=bugconfigid]').val();
    	var urlStr=("<%=rootPath%>phone/editsavemodul.action?modulname="+modulname+"&projectId="+projectId+"&bugconfigid="+bugconfigid+"&token="+$('#tokens').val()).replace(" ","");
    	//alert(urlStr);
    	var result = $.ajax({
			url:urlStr,
			type:"post",
			dataType: "json", 
		    async: false,
		    success:function(){
		    }
		}).responseText;   
		 $('#bugconfig').html(result);
	}
	
	
	/**
    	删除版本
	*/
	function delversion(objj){
		var isempty=$(objj).parent().find('#isempty').val();
		var bugconfigid=$(objj).parent().find('#versiondelid').val();
		var pid=<s:property value="projectId" />;
		var urlStr=("<%=rootPath%>phone/delversion.action?projectId="+pid+"&bugconfigid="+bugconfigid+"&token="+$('#tokens').val()).replace(" ","");
		//alert(urlStr);
		$.dialog.confirm("您确认要删除这个版本?",function(data){
			if (data) {
				if(isempty==0){
					//alert('可以删除!');
					var result = $.ajax({
						url:urlStr,
						type:"post",
						dataType: "json", 
					    async: false,
					}).responseText;
					 $('#bugconfig').html(result);
				}else {
					$.dialog.tips('您不可以删除这个版本！',1,'alert.gif',function(){ 
		    			
		    		});
				}
			}
		});
	}
	
	/**
	*  删除模块
	*/
	function delmodul(objj){
		var isempty=$(objj).parent().find('#isempty').val();
		//alert(isempty);
		var bugconfigid=$(objj).parent().find('#moduldelid').val();
		var pid=<s:property value="projectId" />;
		var urlStr=("<%=rootPath%>phone/delmodul.action?projectId="+pid+"&bugconfigid="+bugconfigid+"&token="+$('#tokens').val()).replace(" ","");
		$.dialog.confirm("您确认要删除这个模块?",function(data){
			if (data) {
				if(isempty==0){
					var result = $.ajax({
						url:urlStr,
						type:"post",
						dataType: "json", 
					    async: false,
					}).responseText;
					 $('#bugconfig').html(result);
				}else {
					$.dialog.tips('您不可以删除这个模块！',1,'alert.gif',function(){ 
		    			
		    		});
				}
			}
		});
	}
    </script>
   <input type="hidden" value="<s:property value='tokens'/>" id="tokens"/> 
  <div >
    <div  style="float: left; width:90%;padding-left: 10%;">
	<table style="width: 70%; border: 1px;">
		<tr bgcolor="#ddedfe" style="font-weight: bold;">
			<td width="60%;"     style="font-size: 15px;">&nbsp;版本</td>
			<td width="20%;" align="center" style="font-size: 15px;">修改</td>
			<td width="20%;" align="center" style="font-size: 15px;">删除</td>
		</tr>
	<s:iterator var="version" value="bugversions">	
		<tr bgcolor="#f6f6f6" >
			<td style="font-size: 14px;"  ><s:property  value="#version.versionname"/><input type="hidden" value="<s:property  value='#version.bugconfigid' /> " name="bugconfigid" /></td>
			<td  style="font-size: 14px;" onclick="versionedit(this)" align="center"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true"/></td>
			<td  style="font-size: 14px;" align="center"><a href="javascript:void(0)" onclick="delversion(this)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" plain="true"/><input type="hidden" value="<s:property  value='#version.bugconfigid' /> "  id="versiondelid" /><s:if test="versionHaveBug.get(#version.bugconfigid)==1"><input type="hidden" value="1" id="isempty" /></s:if><s:else><input type="hidden" value="0" id="isempty" /></s:else></td>
		</tr>
	</s:iterator>
	</table> 
	<div  id="versionarea" style="font-size: 14px;"  >
	
	<input style="border: solid 1px #a0a0a0" type="text"  name="versionname"  id="versionname"/>
	<a  href="javascript:void(0)" onclick="addversion()">保存</a>
	<a href="javascript:void(0)"  onclick="hideversionarea()">取消</a><br />
	</div>
	 
	<a  id="addversion" onclick="showversionarea()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true">添加</a>
	</div>
	
	
	
	<div style="float: left;width: 100%;">&nbsp;</div><br/>
	<div style="float: left; width:90%;padding-left: 10%;">
	<table style="width: 70%; border: 1px;">
		<tr bgcolor="#ddedfe"  style="font-weight: bold;">
			<td width="60%;"    style="font-size: 15px;">&nbsp;模块</td>
			<td width="20%;" align="center" style="font-size: 15px;">修改</td>
			<td width="20%;" align="center" style="font-size: 15px;">删除</td>
		</tr>
	<s:iterator var="modul" value="bugmoduls">	
		<tr bgcolor="#f6f6f6">
			<td style="font-size: 14px;" ><s:property  value="#modul.modulname"/><input type="hidden" value="<s:property  value='#modul.bugconfigid' /> " name="bugconfigid" /></td>
			<td style="font-size: 14px;"  align="center"><a id="edit" onclick="moduledit(this)" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true"/></td>
			<td style="font-size: 14px;"  align="center"><a href="javascript:void(0)" onclick="delmodul(this)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" plain="true"/><input type="hidden" value="<s:property  value='#modul.bugconfigid' /> "  id="moduldelid" /><s:if test="modulHaveBug.get(#modul.bugconfigid)==1"><input type="hidden" value="1" id="isempty" /></s:if><s:else><input type="hidden" value="0" id="isempty" /></s:else></td>
		</tr>
	</s:iterator>
	</table> 
	<div id="modularea" style="font-size: 14px;">
	<input  type="text" style="border: solid 1px #a0a0a0" name="versionname"  id="modulname"/>
	<a href="javascript:void(0)" onclick="addmodul()">保存</a>
	<a href="javascript:void(0)" onclick="hidemodularea()">取消</a><br />
	</div>
	<a  id="addmodul" onclick="showmodularea()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true">添加</a>
	</div>
  </div>

  </body>
</html>
