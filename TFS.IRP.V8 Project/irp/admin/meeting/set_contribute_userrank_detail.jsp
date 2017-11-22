<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s" %>     
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详细积分信息</title>
</head>
<body>
<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
/*全局变量*/
var m_checked = false;
/**
*分页
*/
function page(_nPageNum){
	var s=	${useridsearch};
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	
	$('#loadUserContributeDetaildiv').panel('refresh',"<%=rootPath%>set/loadUserleavedaysDetail.action?"+queryString+"&useridsearch="+s);
}
/**
*排序
*/
function orderBy(_sFiled,_sOrderBy){
	var s=	${useridsearch};
	$('#orderFieldtwo').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	$('#loadUserContributeDetaildiv').panel('refresh',"<%=rootPath%>set/loadUserleavedaysDetail.action?"+queryString+"&orderFieldtwo="+_sFiled+"&useridsearch="+s);
}

	
/**
* 全选
*/
function checkAll(){
	m_checked = !m_checked;
	$("input[name='irpvalueconfigdetailid']").attr("checked",m_checked); 
} 
function searchDate(_formNum){
var s=	${useridsearch};

	var date_start_time=$('#start_time').datebox('getValue');
	 
	 if(date_start_time==""){
		 date_start_time='${c_date_start_time}';
	 }
     var date_end_time=$('#end_time').datebox('getValue'); 
     if(date_end_time==""){
    	 date_end_time='${c_date_end_time}';
    	 
     }
     
	 var _formNum = $('#datecombox').combobox('getValue');
	   if (date_start_time>date_end_time) {
		   $.messager.alert("操作提示","结束日期必须在开始日期之后");
		   return false;
	   }
	var queryString = $('#pageForm').serialize();
	
		$('#loadUserContributeDetaildiv').panel('refresh',"<%=rootPath%>set/loadUserleavedaysDetail.action?"+queryString+"&datanumid="+_formNum+"&date_start_time="+date_start_time+"&date_end_time="+date_end_time+"&useridsearch="+s);
		
}
//获得页面选择内容
var start_end;
$(function(){	

	 if('${datanumid}'=="4"){
		       
			$('#start_time').datebox();
			
			$('#end_time').datebox();
			
			$('#end_time').datebox('setValue','${c_date_end_time}');
 			$('#start_time').datebox('setValue','${c_date_start_time}');
		
			$('#_time_date').show();
		
		 }
	  $('#datecombox').combobox({
		 	 panelHeight:'160',
		 	 width:'50',
		 	 editable:false,
		 	 onSelect:function(){
		 	 start_end = $(this).combobox('getValue');
		 		if (start_end=="4") {
		 			var testDate = new Date();
		 			$('#end_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
		 			$('#start_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
		 			$('#_time_date').show();
		 		}
		 		if(start_end=="1" || start_end=="2" || start_end=="3"){
		 			
		 			$('#_time_date').hide();
		 		}

		 	 }
		  });
	
	
	
});
//引入扩展验证
$.extend($.fn.validatebox.defaults.rules, {   
	    Maxlength:{//最大输入长度
	    	validator:function(value){ 
	    		if (/^[+]?[0-9]+\d*$/i.test(value)) {
				return true;	
				}else{
				return false;
				}
	        }, 
	            message:'请输入合法整数'
	        },
	        EndTime:{
		    	validator:function(){
		    		if($('#end_time').datebox('getValue')>=$('#start_time').datebox('getValue')){
		    			return true;
		    		}else{	  
		    			return false;
		    		}	
		    	},
		    	message:'结束日期必须在开始日期之后'
		    },
		    StartTime:{
		    	validator:function(){
		    		if($('#end_time').datebox('getValue')>=$('#start_time').datebox('getValue')){
		    			
		    			return true;
		    		}else{
		    			
		    			return false;
		    		}	
		    	},
		    	message:'开始日期必须在结束日期之前'
		    }
	
}); 
</script>
<form id="pageForm">
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderFieldtwo" id="orderFieldtwo"  />
<s:hidden name="orderBy" id="orderBy"  />
<s:hidden name="useridsearch" id="useridsearch"   />
</form>

 <div id="loadUserContributeDetaildiv" class="easyui-panel">
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
 <tr class="list_th" style="position: relative;">
  <td colspan="6">
  
             请输入时间
            
             <select id="datecombox"  name="dept" >  
                 <option  value="1"  <s:if test="datanumid==1">selected="selected"</s:if>>本周</option>  
                 <option value="2"  <s:if test="datanumid==2">selected="selected"</s:if>>本月</option>  
                 <option value="3" <s:if test="datanumid==3">selected="selected"</s:if>>本季</option>  
                 <option value="4" <s:if test="datanumid==4">selected="selected"</s:if>>指定</option>  
            </select> 
            <span id="_time_date" style="display: none;">
						开始日期<input type="text" name="" id="start_time"
						class="easyui-datebox" editable="false" validType="StartTime">&nbsp;&nbsp; 结束日期<input
						type="text" name="" id="end_time" class="easyui-datebox"
						editable="false" validType="EndTime"> </span>
						
             <a href="javascript:void(0)" id="rankdetailsearch"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"
						onclick="searchDate()">查询</a>
  </td>
 </tr>
 <tr>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkAll()">序号</a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="">开始时间</a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('endtime','<s:if test="orderFieldtwo=='endtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">结束时间<s:if test="orderFieldtwo=='endtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderFieldtwo=='endtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('leavedays','<s:if test="orderFieldtwo=='leavedays'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">休假天数<s:if test="orderFieldtwo=='leavedays'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderFieldtwo=='leavedays'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('crtime','<s:if test="orderFieldtwo=='crtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">时间<s:if test="orderFieldtwo=='crtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderFieldtwo=='crtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
 </tr>
 
 <s:iterator value="irpLeaveapplies" status="irpUserValueLinkStatus" >

 <tr>
  <td align="center" bgcolor="#F5FAFE" class="main_bright" width="5%"><input type="checkbox" name="leaveapplyid">&nbsp;<s:property value="(pageNum-1)*pageSize+#irpUserValueLinkStatus.count"/></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright" width="17%"><s:date name="starttime" format="yyyy-MM-dd HH:mm" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright" width="17%"><s:date name="endtime" format="yyyy-MM-dd HH:mm" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright" width="17%"><s:property value="leavedays" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright" width="20%"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
 </tr>

 </s:iterator>
 
  <tr bgcolor="#FFFFFF">
   <td colspan="6" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
 </div>
</body>
</html>