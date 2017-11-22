<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.tfs.irp.util.RightUtil"%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
	IrpUser loginUser = LoginUtil.getLoginUser();
	RightUtil rightUtil = new RightUtil();
%>
<script type="text/javascript">
$(function(){
	checkuserapp();
});
//查询用户安装的应用中发布的应用
function checkuserapp(){
	   $.ajax({
			type:'post',
			url:'<%=rootPath%>menu/select_userappbystatus.action',
			dataType: "json",
			async: false,
	   		cache: false,
	   		success:function(data){
	   		  for(var i=0;i<data.length;i++){
				   if(data[i].applistname=="意见反馈"){
					   $("#complain").show();
				   }
			   }
	   		}
		});
}
//意见反馈
function clickComplain(){
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath%>menu/add_formComplain.action',
		dataType: "json",
	    async: false,
	    cache: false
	}).responseText;
	//初始化弹出框
	$.dialog({
		title:'意见反馈',
		content: result,
		max: false,
	    min: false, 
	    lock:true,
	    width:500,
	    height:200,
	    ok: function(){
	    	var complaindesc=$("#complaindesc").val();
	    	var complaintypeid=$("#complaintypeid").val();
	    	if($.trim(complaindesc)==''||complaintypeid==0){
	    		$.dialog.tips("内容为空或反馈主题没选择",1.5,"alert.gif");
	    		return false;
	    	}else if($.trim(complaindesc).length>300 || $.trim(complaindesc).length<=0){
		    	return false;
		    }else{
		       var queryString = $("#addComform").serialize();
		       $.getJSON("<%=rootPath%>menu/add_comform.action?"+queryString,function(data){
		    	   $.dialog.tips(data,1.5,"32X32/succ.png");
	            });	
	    	}
	    },
	    okVal:'提交',
	    cancelVal: '关闭',
	    cancel: true,
	    padding: 0
	});
}
</script>
<div class="area2"></div>
<div class="zj_foot">
	<div class="zj_wBox">
    	<dl>
        	<dt class="zj_fl"  style="float: left;padding-left: 10px;">
        	<%if(rightUtil.isToManagement()){ %>
        	<s:a action="index" namespace="/admin">后台管理</s:a>
			 &nbsp; &nbsp; &nbsp; &nbsp;
			<%} %>
			<span  id="complain" style="display: none;" >
			<a href="javascript:void(0);" onclick="clickComplain()">意见反馈</a>
			 &nbsp; &nbsp; &nbsp; &nbsp;
			</span>
			<s:a action="logout" namespace="/">退出</s:a>
			</dt>
            <dd class="zj_cl"></dd>
        </dl>
        <h1><font><s:text name="page.foot.info" /></font></h1>
    </div>
</div>