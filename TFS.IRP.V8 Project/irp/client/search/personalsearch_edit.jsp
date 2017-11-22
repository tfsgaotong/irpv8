<%@page import="com.tfs.irp.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style type="text/css">
.adv_search table, td{ margin: 0; padding: 0; }
.adv_search input, select, textarea { margin:0;}
.adv_search th{ font-style: normal; font-weight: normal; }
.adv_search select, input {vertical-align:middle;}
.adv_search select, input, textarea{font-size:12px;}
.adv_search {padding:10px 20px;background:#fff;color:#000;font:13px/20px "arial";}
.adv_search table {width:100%; margin:20px auto;border-collapse:collapse; border-spacing:0 }
.adv_search th, .adv_search td { border:dashed 1px #E4E4E4; border-width:1px 0; padding:10px }
.adv_search th { text-align:left; font-size:14px; width:160px; padding-right:15px }
.adv_search td p { padding-bottom:10px ;margin:5px;}
.adv_search .btns { text-align:center; border-width:0; padding:20px }
.adv_search .btns input { font-size:14px }
.adv_search label { margin-right:2em; vertical-align:middle }
.adv_search .inp_txt_long {border:solid 1px #E4E4E4; width:220px;padding:3px 4px ;float:right;margin-right:120px;}
.time_txt{width:90px;}
</style>
<script type="text/javascript">
$(function(){
	$('#col').combotree({
		url: '<%=rootPath%>site/rightchanneltotougao.action',
		panelWidth:160
	});
	$('#num').combobox({
		editable:false,
		panelHeight:118
	});
	
	$('#s_time').datebox({
		editable:false
	});
	$('#e_time').datebox({
		editable:false,
		validType:'endTime'
	});
	$('#s_time').datebox('setValue','<s:property value="@com.tfs.irp.util.DateUtils@getDateByFormat('yyyy-MM-dd')" />');
	$('#e_time').datebox('setValue','<s:property value="@com.tfs.irp.util.DateUtils@getDateByFormat('yyyy-MM-dd')" />');
	
	$('#time').combobox({
		editable:false,
		panelHeight:176,
		onSelect:function(rec){
			if(rec.value=='custom'){
				$('#customdate').show();
			}else{
				$('#customdate').hide();
			}
		}
	});
	$('#range').combobox({
		editable:false,
		panelHeight:176
	});
	$('#sort').combobox({
		editable:false,
		panelHeight:60
	});
	$('#subjectName').validatebox({
		required: true,
		validType: 'length[1,100]',
		missingMessage: '请填写专题名称'
	});
});
//引入扩展验证
$.extend($.fn.validatebox.defaults.rules, {   
	endTime:{
		validator:function(){
			if($('#e_time').datebox('getValue')>=$('#s_time').datebox('getValue')){
				return true;
			}else{	  
				return false;
			}	
		},
		message:'您指定的日期范围有误'
	}
}); 
</script>
<form id="personalForm" method="post">
<s:hidden name="personalSearch.personalsearchid" />
<table id="advSearch" class="adv_search" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<th scope="row">专题名称：</th>
		<td>
			<s:textfield id="subjectName" name="personalSearch.searchname" cssClass="inp_txt_long" theme="simple" />
		</td>
	</tr>
	<tr>
		<th scope="row">专题描述：</th>
		<td>
			<s:textfield name="personalSearch.searchdesc" cssClass="inp_txt_long" theme="simple" />
		</td>
	</tr>
	<tr>
		<th scope="row">设定关键词：</th>
		<td>
			<div style="clear:both;">
				<span> 	包含以下<strong>全部</strong>的关键词 </span>
			</div>
			<div style="clear:both;">
				<s:textfield name="personalSearch.searchdalltxt" cssClass="inp_txt_long" theme="simple" />
			</div>
			<div style="clear:both;">
				<span> 	包含以下<strong>任意一个</strong>的关键词 </span>
			</div>
			<div style="clear:both;">
				<s:textfield name="personalSearch.searchdonetxt" cssClass="inp_txt_long" theme="simple" />
			</div>
			<div style="clear:both;">
				<span> 	<strong>不包含</strong>以下关键词 </span>
			</div>
			<div style="clear:both;">
				<s:textfield name="personalSearch.searchdnoqtxt" cssClass="inp_txt_long" theme="simple" />
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">限定栏目分类：</th>
		<td>
			<input name="personalSearch.searchdchnlid" id="col" type="text" value="<s:if test="personalSearch==null || personalSearch.searchdchnlid==null || personalSearch.searchdchnlid==0">不限</s:if><s:else><s:property value="personalSearch.searchdchnlid" /></s:else>" style="width: 120px;" />
		</td>
	</tr>
	<tr>
		<th scope="row">选择搜索结果显示条数：</th>
		<td>
			<s:select list="#{5:'每页5条',10:'每页10条',15:'每页15条',20:'每页20条'}" id="num" name="personalSearch.searchdpagesize" theme="simple" />
		</td>
	</tr>
	<tr id="limit_time">
		<th scope="row">限定要搜索内容的时间：</th>
		<td class="date_pick">
			<div style="float:left;margin-right:5px;">
				<s:select list="#{'no':'不限','custom':'指定日期范围','d':'1天内','w':'1周内','m':'1个月内','y':'1年内'}" id="time" name="personalSearch.searchdcrtime" theme="simple" />
			</div>
			<div id="customdate" <s:if test="personalSearch.searchdcrtime=='custom'"></s:if><s:else>style="display:none;"</s:else>>
			    <div style="float:left;">
			    	<span class="fgray">从 <input id="s_time" name="personalSearch.searchdstarttime" value="<s:property value="" />" class="time_txt" maxlength="10" type="text" /></span>
			    </div>
			    <div style="float:left;">
					<span class="fgray">到 <input id="e_time" name="personalSearch.searchdendtime" value="<s:property value="" />" class="time_txt" maxlength="10" type="text" /></span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">限定关键词位于：</th>
		<td>
			<s:select list="#{'ALL':'全部','DOCTITLE':'标题','DOCKEYWORDS':'标签','DOCABSTRACT':'核心提示','DOCCONTENT':'正文','CRUSER':'创建者'}" id="range" name="personalSearch.searchfiled" theme="simple" />
		</td>
	</tr>
	<tr>
		<th scope="row">排序方式：</th>
		<td>
			<s:select list="#{0:'相关度',1:'时间'}" id="sort" name="personalSearch.searchsort" theme="simple" />
		</td>
	</tr>
</table>
</form>