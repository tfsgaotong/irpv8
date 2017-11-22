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
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<title>导出筛选</title>
</head>
<body>
 <script type="text/javascript">
  //创建者提示
  function findcreateUserAll(sname){
	  $('#creater').combobox({   
		    url:"<%=rootPath %>menu/find_userlike.action?sname="+sname,    
		    valueField:'username',   
		    textField:'username'  
		});
  }
  //刷新
  function refech(){
	  var selectvalue=$("table").find("input[type='radio'][name='type'][checked='checked']").val();
	  var queryString = $('#questionsearchform').serialize();
	  var  _url="<%=rootPath%>menu/find_question.action?selectvalue="+selectvalue+"&pageNum=1&"+queryString;
	  var result=$.ajax({
			type:'post',
			url:_url,
			dataType: "json",
			async: false,
	   		cache: false
	  }).responseText;
	 $("#resultdata").html(result);
  }
  //查询
  function selectQuestion(){
	  var selectvalue=$("table").find("input[type='radio'][name='type'][checked='checked']").val();
	  var queryString = $('#questionsearchform').serialize();
	  var  _url="<%=rootPath%>menu/find_question.action?selectvalue="+selectvalue+"&"+queryString;
	  var result=$.ajax({
			type:'post',
			url:_url,
			dataType: "json",
			async: false,
	   		cache: false
	  }).responseText;
	 $("#resultdata").html(result);
  }
  //点击选中
  function chose(thisid){
	 $("#"+thisid).parent().parent().find("input[type='radio']").removeAttr("checked");
	 $("#"+thisid).attr("checked","checked");
	 if($("#"+thisid).val()==0){
		 //不能输入关机字
		  $("#keyword").val("");
		 $("#keyword").attr("readOnly",true);
	 }else{
		 $("#keyword").attr("readOnly",false);
	 }
  }
  	//查看详细
 	function lookdetil(_value){
  	   $.messager.alert('查看详细信息',_value);
 	}
 	//导出
	 function sureexport(){
		//获取保存路径
		 var selectvalue=$("table").find("input[type='radio'][name='type'][checked='checked']").val();
		 var queryString = $('#questionsearchform').serialize();
		 var  _url="<%=rootPath%>menu/excel_findquestion.action?selectvalue="+selectvalue+"&"+queryString;
		 $.ajax({
				type:'post',
				url:_url,
				dataType: "json",
				async: false,
		   		cache: false,
		   		success:function(message){
		   			$("#search").attr("href","<%=rootPath%>file/readfile.action?fileName="+message+"&fileTrueName="+message);
		   		    // IE
		   			if(document.all) {
		   				document.getElementById("search").click();
		   			}
		   			// 其它浏览器
		   			else {
		   				var e = document.createEvent("MouseEvents");
		   				e.initEvent("click", true, true);
		   				document.getElementById("search").dispatchEvent(e);
		   			}
		   		}
		  })
		   //关闭
		   $('#addreportconfig').dialog('destroy');
 	}
    /**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderFieldq').val(_sFiled);
		$('#orderByq').val(_sOrderBy);
		var selectvalue=$("table").find("input[type='radio'][name='type'][checked='checked']").val();
		var queryString = $('#questionsearchform').serialize();
		var sString = $('#pageFormq').serialize();
		var _url="<%=rootPath%>menu/find_question.action?orderField="+_sFiled+"&orderBy="+_sOrderBy+"&selectvalue="+selectvalue+"&"+queryString;
		var result=$.ajax({
			type:'post',
			url:_url,
			dataType: "json",
			async: false,
	   		cache: false
	     }).responseText;
	    $("#resultdata").html(result);
	}
	/**
	*分页
	*/
	function page(_nPageNum){
		$('#pageNumq').val(_nPageNum);
		  var selectvalue=$("table").find("input[type='radio'][name='type'][checked='checked']").val();
		  var queryString = $('#questionsearchform').serialize();
		  var pageString=$('#pageFormq').serialize();
		  var  _url="<%=rootPath%>menu/find_question.action?selectvalue="+selectvalue+"&"+queryString+"&"+pageString;
		  var result=$.ajax({
					type:'post',
					url:_url,
					dataType: "json",
					async: false,
			   		cache: false
		  }).responseText;
		    $("#resultdata").html(result);
	} 
	//验证日期
	$(function(){ 
		$.extend($.fn.validatebox.defaults.rules, {
			dateformat : {
				validator: function(value, param){  
					if(value.length<param[0] || value.length>param[1]){
						$.fn.validatebox.defaults.rules.checkName.message = $.fn.validatebox.defaults.rules.length.message;
		            	return false;
		            }else{ 
		            	 var reg="^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";  
		                 if(!value.match(reg)){
		                	 return false;
		                 }
		                 return true;  
		             }
	            },
				message : '请输入YYYY-MM-dd格式'
	        }
		});
		 findcreateUserAll("");
	});
	
 </script>

 <s:form id="questionsearchform" method="post" theme="simple">
     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
        <tr>
             <td bgcolor="#F5FAFE" class="main_bright">创建者</td>
             <td bgcolor="#F5FAFE" class="main_bright">	<input type="text" name="irpQuestion.cruser" id="creater" onkeydown="findcreateUserAll(this.value)">   </td>
        	 <td bgcolor="#F5FAFE" class="main_bright">从</td>
       	     <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="irpQuestion.crtime" class="easyui-datebox" validType="dateformat[10,10]"/></td>
             <td bgcolor="#F5FAFE" class="main_bright">到</td>
       	     <td bgcolor="#F5FAFE" class="main_bright"><input type="text" name="endtime" class="easyui-datebox" validType="dateformat[10,10]"/></td>
             
             </tr>
        <tr>
             <td colspan="2" bgcolor="#F5FAFE" class="main_bright" colspan="5"><input onclick="chose('typeall')" id="typeall" type="radio" name="type" value="0" checked="checked"/> 所&nbsp;有&nbsp;&nbsp;&nbsp;
                  <input onclick="chose('typeque')" id="typeque" type="radio" name="type" value="1" /> 搜问题&nbsp;&nbsp;&nbsp;
                  <input onclick="chose('typeans')" id="typeans" type="radio" name="type" value="2"/> 搜回答</td>
             <td bgcolor="#F5FAFE" class="main_bright">关键词</td>
             <td bgcolor="#F5FAFE" class="main_bright"><input id="keyword" readonly="readonly" name="irpQuestion.textcontent" ></td>
        
             <td colspan="2" bgcolor="#F5FAFE" class="main_bright" height="30px;">
	            <a id="log_search"  class="easyui-linkbutton l-btn" onclick="selectQuestion()" data-options="iconCls:'icon-search'" href="javascript:void(0)">
			              查询
			    </a>
			     <a id="log_search" style="float: right" class="easyui-linkbutton l-btn" onclick="refech()" data-options="iconCls:'icon-reload'" href="javascript:void(0)">
			              刷新
			    </a>
		     </td>
        </tr>
     </table>
    </s:form>
    <div id="resultdata"></div>
</body>
</html>