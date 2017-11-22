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
<title>用户贡献排行</title>
</head>
<body>

<script type="text/javascript" src="js/log/log-format-time.js"></script>
<script type="text/javascript">
/*全局变量*/
var m_checked = false;

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
		    }
	
}); 
/**
 * 刷新本页面
 */
function refreshContribute(){
	$('#contributeranking').panel('refresh');
}
/**
 * 全选
 */
function checkRankAll(){
	m_checked = !m_checked;
	$("input[name='irpvalueconfigid']").attr("checked",m_checked); 
	} 
/**
*分页
*/
function page(_nPageNum){
	$('#pageNum').val(_nPageNum);
	var queryString = $('#pageForm').serialize();
	var date_start_time=$('#start_time').datebox('getValue');   
    var date_end_time=$('#end_time').datebox('getValue');                      
	var start_end=$('#Irp_coverttime').combobox('getValue');
	$('#contributeranking').panel('refresh','<%=rootPath %>set/contributelistofranking.action?'+queryString+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time);
}
/**
*排序
*/
function orderByOf(_sFiled,_sOrderBy){
	$('#orderField').val(_sFiled);
	$('#orderBy').val(_sOrderBy);
	var queryString = $('#pageForm').serialize();
	var date_start_time=$('#start_time').datebox('getValue');   
    var date_end_time=$('#end_time').datebox('getValue');                      
	var start_end=$('#Irp_coverttime').combobox('getValue');
	$('#contributeranking').panel('refresh','<%=rootPath %>set/contributelistofranking.action?'+queryString+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time);
}	
/**	
 * 修改
 */
function updateRankEdit(userid){
	var dialogdiv=document.createElement("div");
  	dialogdiv.id="updateconfig";
  	document.body.appendChild(dialogdiv);
  	$('#updateconfig').dialog({
  		modal:true,
  		href:'<%=rootPath%>set/loadupdateranking.action?userid='+userid,
  		height:300,
  		width:400,
  		title:'修改用户贡献',
  		resizable:true,
  		cache:false,
  		buttons:[{
  			text:'提交',
  			iconCls: 'icon-ok',
  			handler:function(){
  				$('#rankupdate').form('submit',{
  					url:'<%=rootPath%>set/updateRanking.action?userid='+userid,
  					onSubmit:function(){
  						var isValid = $(this).form('validate');
  	    				if (isValid){
  	    					$.messager.progress({
  	    	    				text:'提交数据中...'
  	    	    			});
  	    				}
  	    				return isValid;
  					},		
  					success:function(_nStatus){
  						$.messager.progress('close');
  						$('#updateconfig').dialog('destroy');
  						if (_nStatus==1) {
  							refreshContribute();
  						}else{
  							$.messager.alert("失败","修改用户贡献失败了");
  							refreshContribute();
  						}
  					}
  				});
  			}
  		},{
  			text:'取消',
  			iconCls:'icon-cancel',
  			handler:function(){
  				$('#updateconfig').dialog('destroy');
  			}
  		}],
  		onClose:function(){
  			$('#updateconfig').dialog('destroy');
  		}
  		
  	});
	
}

/**
 * 删除
 */
 function deleteRankEdit(userid,username){
	 	$.messager.confirm("操作提示","您确定重置"+username+"的积分和经验信息吗",function(r){
	 		if(r){
	 			$.messager.progress({
	 				text:'提交数据中...'
	 			});
	 			$.ajax({
	 				type:'post',
	 				url:'<%=rootPath%>set/emptyUserInfo.action?userid='+userid,
	 				success:function(_nStatus){
	 					 $.messager.progress('close');
	 					if (_nStatus==1) {
	 						$.messager.alert("操作提示","数据重置成功");
	 						refreshContribute();
	 					}else{
	 						$.messager.alert("操作提示","数据重置失败");
	 						refreshContribute();
	 					}
	 				},
	 				error:function(){
	 					 $.messager.progress('close');
	 				}
	 			});
	 		}
	 	});
	 }
    /**
	*多选清空
	*/
	function deleteAll(){
	 var _configid;	
	 $("input[name='irpvalueconfigid']:checked").each(
			 function(){
				 _configid+=$(this).val() + ',';
			 }
			 );
			 if (_configid==null) {
			 	$.messager.alert("操作提示","请您先选择要重置的对象");
			 	return false;
			 } 	
			 var _configcatalength=$("input[name='irpvalueconfigid']:checked").length;
			 $.messager.confirm("操作提示","您确定要重置选中的"+_configcatalength+"条数据吗",function(r){
				 if(r){
				       $.messager.progress({
							text:'提交数据中...'
						});
				   $.ajax({
				    type:'post',
				    url:'<%=rootPath  %>set/emptyUserInfoAll.action?userids='+_configid,
				    success:function(_nStatus){
				    	 $.messager.progress('close');
				        if (_nStatus==1) {
							$.messager.alert("操作提示","重置成功了");
							refreshContribute();
						}else{
							$.messager.alert("操作提示","重置失败了");
							refreshContribute();
						}
				    },
				    error:function(){
				    	 $.messager.progress('close');
				    }
				 });
				 }
				 }
				 );
	}
	var start_end;
	$(function(){
		if('${c_start_end}'=="covert_appoint_date"){
 			$('#start_time').datebox({
 				value:'${c_date_start_time}'
 			});
 			$('#end_time').datebox({
 				value:'${c_date_end_time}'
 			});
		$('#_time_date').show();
		
		 }
	$('#Irp_coverttime').combobox({
	 	 panelHeight:'130',
	 	 editable:false,
	 	 onSelect:function(){
	 		start_end = $(this).combobox('getValue');
	 		if (start_end=="covert_appoint_date") {
	 			var testDate = new Date();
	 			$('#start_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
	 			$('#end_time').datebox('setValue',testDate.format("yyyy-MM-dd"));
	 			$('#_time_date').show();
	 		}
	 		if (start_end=="covert_week") {
	 			$('#_time_date').hide();
	 		}
	 		if (start_end=="covert_month") {
	 			$('#_time_date').hide();
	 		}
	 		if (start_end=="covert_quarter") {
	 			$('#_time_date').hide();
	 		}
	 		if (start_end=="covert_moren") {
	 			$('#_time_date').hide();
	 		}
	 	 }
	 	 
	  });
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){
		    
		    	var date_start_time=$('#start_time').datebox('getValue');   
       			var date_end_time=$('#end_time').datebox('getValue');                      
	  			var start_end=$('#Irp_coverttime').combobox('getValue');
	  			if (date_start_time>date_end_time) {
		   			$.messager.alert("操作提示","结束日期必须在开始日期之后");
		   			return false;
	   			}   
		    	$('#pageForm').find('input[name="searchWord"]').val(value);
		    	$('#pageForm').find('input[name="searchType"]').val(name);
		    	$('#pageForm').find('input[name="pageNum"]').val('1');
		    	$('#pageForm').find('input[name="orderField"]').val('');
		    	$('#pageForm').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageForm').serialize();
		    	$('#contributeranking').panel('refresh','<%=rootPath %>set/contributelistofranking.action?'+queryString+'&c_start_end='+start_end+'&c_date_start_time='+date_start_time+'&c_date_end_time='+date_end_time);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	}); 
	
/**
*查看某个用户的详细信息
**/
function searchDetail(userid,username){
 	var dialogdiv=document.createElement("div");
 	dialogdiv.id="userContributeDetail";
 	document.body.appendChild(dialogdiv);
 	$('#userContributeDetail').dialog({
 		modal:true,
 		cache:false,
 		href:"<%=rootPath%>set/loadUserContributeDetail.action?useridsearch="+userid+"&datanumid=2&orderField&orderBy&pageNum=1",
 		height:500,
 		width:700,
 		title:'用户['+username+']详细积分信息',
 		resizable:true,
 		buttons:[{
  			text:'关闭',
  			iconCls: 'icon-cancel',
  			handler:function(){
  				$('#userContributeDetail').dialog('destroy');
  		}
  		
  		}],
  		onClose:function(){
  			$('#userContributeDetail').dialog('destroy');
  		}
 		
 		
 		
 		
 	});
	
}
//查看统计
function searchStatistics(){
$('#container').css("display","block");
$('#common').css("display","none");

$('#pageNum').val(${PageNum});
var queryString = $('#pageForm').serialize();

var userarray = $.ajax({
	  url: "<%=rootPath%>set/contributelistofrankingchart.action?"+queryString+"&chartype=1",
      cache:false, 
	  async: false
	 }).responseText;
      var chardata = userarray.split(".");
      var nameArray;
      var scoreArray;
      var experArray;

      for(var i = 0;i<chardata.length;i++){
    	  nameArray = chardata[i].toString().substring(0,chardata[i].toString().length-1).split(",");
    	  scoreArray = chardata[i+1].toString().substring(0,chardata[i+1].toString().length-1).split(",");
    	  experArray = chardata[i+2].toString().substring(0,chardata[i+2].toString().length-1).split(",");
    	  break;
      }
     var scoreArrayNum = new Array();
      for(var i = 0;i<scoreArray.length;i++){
    	  scoreArrayNum[i] = parseInt(scoreArray[i]);
    	  
      }
      var experArrayNum = new Array();
      for(var i = 0;i<experArray.length;i++){
    	  experArrayNum[i] = parseInt(experArray[i]);
    	  
      }
	 $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '积分经验统计图'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories:nameArray
            },
            yAxis: {
                min: 0,
                title: {
                    text: '用户积分'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '积分',
                data: scoreArrayNum
    
            }, {
                name: '经验',
                data: experArrayNum
    
            }]
        });
	
	
}
</script>
<form id="pageForm">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="common">
<div id="listSearchType" style="width:120px;">  
  
    <div data-options="name:'username'">用户名&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div data-options="name:'score'">积分</div>
    <div data-options="name:'experience'">经验</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10" cellspacing="1" bgcolor="#cad9ea">
<tr class="list_th" style="position: relative;">
  <td colspan="3" style="padding-left: 10px;">
  <a href="javascript:void(0)" onclick="refreshContribute()">刷新</a>
  <a href="javascript:void(0)" onclick="deleteAll()">重置</a>
  <a href="javascript:void(0)" onclick="searchStatistics()" style="display: none;">统计</a>
  &nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
  请选择时间段:<select id="Irp_coverttime" name="c_start_end"
						onchange="ck_date()">
						<option value="covert_moren" id="covert_moren" selected="selected"
							<s:if test="c_start_end=='covert_moren'">selected="true"</s:if>>默认</option>
						<option value="covert_week" id="_covert_week"
							<s:if test="c_start_end=='covert_week'">selected="true"</s:if>>本周</option>
						<option value="covert_month" id="_covert_month"  
							<s:if test="c_start_end=='covert_month'">selected="true"</s:if>>本月</option>
						<option value="covert_quarter" id="_covert_quarter"
							<s:if test="c_start_end=='covert_quarter'">selected="true"</s:if>>本季</option>
						<option value="covert_appoint_date" id="_covert_appoint_date"
							<s:if test="c_start_end=='covert_appoint_date'">selected="true"</s:if>>指定</option>
					</select>&nbsp;&nbsp; <span id="_time_date" style="display: none;">
						开始日期<input type="text" id="start_time" class="easyui-datebox"
						editable="false"  />&nbsp;&nbsp; 结束日期<input type="text"
						id="end_time" class="easyui-datebox" editable="false"
						validType="EndTime" /> </span>
   <td colspan="4" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
   
 </tr>
 
 <tr>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="checkRankAll()">全选</a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('username','<s:if test="orderField=='username'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">用户名<s:if test="orderField=='username'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='username'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('numscore','<s:if test="orderField=='numscore'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">积分<s:if test="orderField=='numscore'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='numscore'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderByOf('numexperience','<s:if test="orderField=='numexperience'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">经验<s:if test="orderField=='numexperience'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='numexperience'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
  <td align="center" width="80" bgcolor="#F5FAFE" class="main_bright">级别</td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">查看详细</td>
  <td align="center" width="60" bgcolor="#F5FAFE" class="main_bright">操作</td>
 </tr>
 
 <s:iterator value="irpUserlist" status="userliststatus" >
 <tr>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><input name="irpvalueconfigid" value="<s:property value="userid" />" type="checkbox" >&nbsp;<s:property value="(pageNum-1)*pageSize+#userliststatus.count"/></td>
  <td align="left" bgcolor="#F5FAFE" class="main_bright"><s:property value="username" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="numscore" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="numexperience" /></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a title="<s:property value="findRankdescBySumScore(sumscore)" />" style="color: rgb(78, 78, 78);font-size: 12px;"><s:property value="findRankNameByScore(sumscore)" /></a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="searchDetail(<s:property value="userid" />,'<s:property value="username" />')" >查看</a></td>
  <td align="center" bgcolor="#F5FAFE" class="main_bright">
    <img border="0" src="images/icons/pencil.png" title="修改" style="cursor:pointer; margin: 0 5px;" onclick="updateRankEdit(<s:property value="userid" />)"  />
    <img border="0" src="images/icons/reload.png" title="重置" style="cursor:pointer; margin: 0 3px;" onclick="deleteRankEdit(<s:property value="userid" />,'<s:property value="username" />')" />
  </td>
 </tr>
 </s:iterator>
 <tr bgcolor="#FFFFFF">
   <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
  </tr>
</table>
</div>


<div id="container" style="display: none;"></div>



</body>
</html>