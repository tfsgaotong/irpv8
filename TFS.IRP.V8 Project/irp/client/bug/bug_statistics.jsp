<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<style type="text/css">
#item{
	padding: 10px 10px;
}	
</style>

	<%-- <script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>   --%>
<script type="text/javascript">
function searchByDate(){
	var stime=$('#stime').parent().find('input[type=hidden]').val();
	var etime=$('#etime').parent().find('input[type=hidden]').eq(1).val();
	var projectId=<s:property  value="projectId"  />;
	$.ajax({
		type:"post",
		url:"<%=rootPath%>bug/searchbugstatistics.action",
		data:{
			'startDate':stime,
			'endDate':etime,
			'projectId':projectId
		},
		async:false,
		cache:false,
		success:function(data){
			$('#statisticsPage').html(data);
		}
	});
	//alert(projectId);
	//alert(projectId);
}
$(function(){
	var now=new Date();
	var dateNow=1900+now.getYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
	var dateLast=1900+now.getYear()+"-"+now.getMonth()+"-"+now.getDate();
	$('#stime').val(dateLast);
	$('#etime').val(dateNow);
	//alert($('#stime').val());
	var projectId=<s:property  value="projectId"  />;
	$.ajax({
		type:"post",
		url:"<%=rootPath%>bug/searchbugstatistics.action",
		data:{
			'startDate':dateLast,
			'endDate':dateNow,
			'projectId':projectId
		},
		async:false,
		cache:false,
		success:function(data){
			$('#statisticsPage').html(data);
		
		}
	});
	
	//alert(dateLast);
});

</script>
 
<script type="text/javascript" src="<%=rootPath%>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/zyz_easydatecheck.js"></script>
<script type="text/javascript" src="<%=rootPath%>admin/js/jquery.easyui.min.js"></script>
</head>
  <body>

  	<div id="item" style="background: url('<%=rootPath%>client/images/search_bug.gif') repeat-x;width:98%;border: thin solid #BBB; ">
  		<!-- 
  		<input style="width: 130px;"  type="text" id="starttime" class="easyui-datebox" editable="false" />
  		 -->
  		 <span>
  		 时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		 <input id="stime" type="text" editable="false" class="easyui-datebox"></input> 
  		 &nbsp;&nbsp;&nbsp;
  		 至
  		 &nbsp;&nbsp;&nbsp;
  		 <input id="etime" type="text" editable="false" class="easyui-datebox"></input>
  		 </span>  
  		 <a style="margin-left:30px;"  href="javascript:void(0)" onclick="searchByDate()"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true">查询</a>
  	</div>
  	<div id="statisticsPage" >
		
  	</div>
  </body>
</html>
