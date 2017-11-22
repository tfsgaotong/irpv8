<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
 <title>所有Bug</title>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
<style type="text/css">

.lab{margin-left:25px;}
.term{margin-left:10px;}
</style>
<script type="text/javascript">

var stateType="0";
function selectState(stateTypeValue){
	
	stateType=stateTypeValue.value;
	//alert(stateType);
	$('#a4').show();
	if(stateType==3){
		$('#a4').hide();
	}
	searchbyiterm(0);

}

/**
 * 查询
 */
function searchbyiterm(_type){
	if($('#bugstateselect').val()==3){
		$('#a4').hide();
	}
	var itermsStr=$('#form').serialize();
	
	var urlStr="<%=rootPath%>bug/simpleprojectbug.action?"+itermsStr+"&seltype="+_type;
	//alert(urlStr);
	if(_type==0){
		var result = $.ajax({
			url: urlStr,
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText;  
		$('#pagepage').html(result);
	}else if(_type==1){
		window.location.href=urlStr;
	}
}

$(function(){
	if($('#bugstateselect').val()==3){
		$('#a4').hide();
	}
	$('#nstate').val('');
});

/**
 * 刷新
 */
function refreshIterm(obj){
	$('#bugfounderselect').val('');
	$('#bugpriorityselect').val('');
	$('#bugstateselect').val('0');
	$('#bugoperatorselect').val('');
	$('#keyword').val('');
	$('#a4').show();
	searchbyiterm(0);
}

/**
 * 按照不同条件排序
 */
 
function selOrder(type,orderStr){
	if($('#ordertype').val()==type){
		$('#order').val(orderStr+'  desc');
		$('#ordertype').val('');
	}else{
		$('#order').val(orderStr);
		$('#ordertype').val(type);
	}
	//alert($('#order').val());
	searchbyiterm(0);
}
</script>
</head>
<body > 
<div id="page" style="height: 600px;">
<div class="bg01">

<div class="xuanxiang" style="border-bottom:1px  solid #efefef;width: 100%;">
	<s:form id="form" method="post" enctype="multipart/form-data" >
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" value="10"/>
	<s:hidden name="headType" id="headType"  value="1"/>
	<s:hidden name="projectId" id="projectId" />
	<s:hidden name="order" id="order"/>
	<s:hidden name="ordertype" id="ordertype"/>
	<div style="margin-top: 10px;margin-bottom: 10px;">
		
	</div>
	<div id="searchList" style="background: url('<%=rootPath%>client/images/search_bug.gif')repeat-x;width:100%;border: thin solid #BBB; ">
	<div id="searchList-first">
	<label class="lab" style="margin-left: 10px;line-height: 50px;">优先级</label>
	<select id="bugpriorityselect"  onchange="searchbyiterm(0)"  style="width: 100px;"  class="term" name="priority">
              	<option  value="" selected="selected" >==不限==</option> 
                <option  value="1" >低</option>
                <option  value="2" >中</option>
             	<option  value="3" >高</option>
             	<option  value="4" >紧急</option>
             	<option  value="5" >严重</option>
    </select>
        <label class="lab">分配人</label>
    <select id="bugfounderselect"  onchange="searchbyiterm(0)"  style="width: 100px;"   class="term" name="founderId">
              	<option  value="" selected="selected">==不限==</option> 
               <s:iterator var="user" value="projectUsers">
                <option value="<s:property value='#user.userid'/>" ><s:property value="#user.truename"/> </option>
               </s:iterator>
    </select>
       <label class="lab">版本 &nbsp;&nbsp;&nbsp;</label>
    <select  onchange="searchbyiterm(0)"  style="width: 100px;"   class="term" name="versionid">
              	<option  value="" selected="selected" >==不限==</option> 
             <s:iterator var="version" value="bugversions">
             	<option value="<s:property value='#version.bugconfigid'/>"><s:property value="#version.versionname" /></option>
             </s:iterator>
    </select>
       <label class="lab">模块&nbsp;&nbsp;&nbsp;</label>
    <select id="bugprojectselect" onchange="searchbyiterm(0)"   style="width: 100px;"  class="term"  name="modulid">
              	<option  value="" >==不限==</option> 
             <s:iterator var="modul" value="bugmoduls">
             	<option value="<s:property value="#modul.bugconfigid"/>"><s:property value="#modul.modulname" /></option>
             </s:iterator>
    </select>
    </div>
    <div id="search-second">
    <label class="lab" style="margin-left: 10px;line-height: 50px;"> 状态&nbsp;&nbsp;&nbsp;</label>
    <select id="bugstateselect" onchange="searchbyiterm(0)" style="width: 100px;"   class="term" name="stateType">
              	<s:set  var="nstaten" value="nstate"></s:set>
              	<option onclick="selectState(this)" value="0" >==不限==</option> 
                <option onclick="selectState(this)" value="1" <s:if test="#nstaten==1 ">selected='selected'</s:if>>未完成</option>
                <option onclick="selectState(this)" value="2" <s:if test="#nstaten==2 ">selected='selected'</s:if>>待审核</option>
             	<option onclick="selectState(this)" value="3" <s:if test="#nstaten==3 ">selected='selected'</s:if> id="stateWan">已完成</option>
    </select>
    <label class="lab">处理人</label>
    <select id="bugoperatorselect"  onchange="searchbyiterm(0)"  style="width: 100px;"   class="term" name="operatorId">
              	<option  value="" selected="selected">==不限==</option> 
               <s:iterator var="user" value="projectUsers">
                <option value="<s:property value="#user.userid"/>"><s:property value="#user.truename"/> </option>
               </s:iterator>
    </select>
       <label class="lab">关键字</label>
    <input type="text"  style="width: 150px;" class="term" name="keyword" id="keyword"/>
    <a type="text" onclick="searchbyiterm(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true">搜索</a>
    <a type="text" id="chong" class="easyui-linkbutton" onclick="refreshIterm()"  link="<%=rootPath %>bug/simpleprojectbug.action?projectId=53"  plain="true">重置</a>
    <script type="text/javascript">
    //更改回车事件
    $(function(){
        $('#keyword').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	setTimeout(function(){
            		searchbyiterm(0);
           		 },0);
            	
            	event.preventDefault ? event.preventDefault() : (event.returnValue = false);
            }
        });
    });
    </script>
    </div>
	</div>
	</s:form>
<br/>
<a type="text" onclick="searchbyiterm(0)" class="easyui-linkbutton"  plain="true">刷新</a>
<!-- 
	<div class="columnInfo" style="float:right;padding:2px 0 0 0 ;height: 30px;">
		<input type="button" style="width:100px;height:25px;line-height:25px;color:#fff; font-size:15px;padding-left:20px;background-position:6px;" class="create" onclick="toAddBugpage();" value="创建Bug">
	</div>
 -->	
<!-- 数据迭代区 -->
<jsp:include page="project_bug_page.jsp" />
</div>
</div>
</div>
</body>
</html>