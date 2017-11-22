<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业社会化智力资源管理系统</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
<jsp:include page="../include/client_skin.jsp" />

<style type="text/css">
body{
	behavior:url(<%=rootPath %>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.main-gr .right .combo input{
  border: 0px;
}
.edit_career{
  display: none;
  padding-left: 50px;
}
</style>

<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jsAddress.js"></script>
<script type="text/javascript">
<!--
//添加职业信息
function addCareer(){
	$("#careerFrom").form('submit',{
		url : "<%=rootPath %>user/career_edit_dowith.action",
		success:function(data){
			if(data>0){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}

//修改职业信息
function editCareer(){
	$("#careerEditFrom").form('submit',{
		url : "<%=rootPath %>user/career_edit_dowith.action",
		success:function(data){
			if(data>0){
				$.dialog.tips('修改成功',1,'32X32/succ.png',function(){
					location.reload(true);
				});
			}else{
				$.dialog.tips('修改失败',1,'32X32/fail.png');
			}
		}
	});
}

//删除职业信息
function deleteCareer(_nCareerId){
	$.dialog.confirm('确认是否删除此职业信息？', function(){
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>/user/delete_career.action',
			data : {
				careerId: _nCareerId
			},
			success : function(callback) {
				if(callback=='1'){
					location.reload(true);
					$.dialog.tips('删除成功',1,'32X32/succ.png');
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');
				}
			}
		});
	});
}

//编辑教育信息
function showCareerEdit(_nCareerId){
	var editCareers = $(".edit_career");
	editCareers.each(function(index, domEle){
		var jDom = $(domEle);
		if(jDom){
			if(jDom.attr('id')==_nCareerId){
				$.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>/user/career_edit.action',
					data : {
						careerId: _nCareerId
					},
					success : function(callback) {
						jDom.css("display","block");
						jDom.html(callback);
						jDom.find("select[name='irpCareer.endyear']").validatebox();
						jDom.find("input:text[name='irpCareer.companyname']").validatebox();
						jDom.find("input:text[name='irpCareer.department']").validatebox();
					}
				});
			}else{
				jDom.html('');
				jDom.css("display","none");
			}
		}
	});
}

function cancelEdit(_nCareerId){
	var jDom = $(".edit_career[id='"+_nCareerId+"']");
	if(jDom){
		jDom.html('');
		jDom.css("display","none");
	}
}

$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		},
		year : {
			validator : function(value, param) {
				return value >= $(param[0]).val();
			},
			message : '请选择正确的离职年份'
		}
	});
});
//-->
</script>
</head>

<body>
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left">
	<div class="leftmenu">
    	<h1>账号设置</h1>
        <dl>
        	<dt><em class="a"></em><a href="<%=rootPath %>user/user_set.action" class="x">个人资料</a></dt>
            	<dd><a href="<%=rootPath %>user/user_set.action">基本信息</a>
            	<a href="<%=rootPath %>user/user_edu.action">教育信息</a>
            	<a href="<%=rootPath %>user/user_career.action">职业信息</a></dd>
       	 	<dt><em class="b"></em><a href="<%=rootPath %>user/user_pic.action">修改头像</a></dt>
       	  	<dt><em class="c"></em><a href="<%=rootPath %>user/user_tag.action">个人标签</a></dt>
            <dt><em class="d"></em><a href="<%=rootPath %>user/user_privacy.action">隐私设置</a></dt>
	
			<dt><em class="g"></em><a href="<%=rootPath %>user/user_group.action">个人分组</a></dt>
			<dt><em class="i"></em><a href="<%=rootPath %>site/user_subject.action">个人专题</a></dt>
			<dt><em class="h"></em><a href="<%=rootPath%>site/personallchanneltoshow.action?personid=<s:property value="@com.tfs.irp.util.LoginUtil@getLoginUserId()" />">个人分类</a></dt>
        	<dt><em class="e"></em><a href="<%=rootPath %>user/user_binding.action">账号绑定</a></dt>
        </dl>
    </div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<div class="gr"><h1>职业信息</h1></div>
	<div>
		<s:iterator value="irpCareers">
		<div class="school_info">
			<div class="school_info_title">
				<span style="float: right;"><a href="javascript:void(0)" onclick="showCareerEdit(<s:property value="careerid" />)">修改</a> <a href="javascript:void(0)" onclick="deleteCareer(<s:property value="careerid" />)">删除</a></span>
				<span><b><s:property value="companyname" /></b>&nbsp;&nbsp;<font color="#999999">(<s:property value="province" />，<s:property value="city" />，<s:property value="area" />)</font>&nbsp;<s:property value="startyear" />年 — <s:if test="endyear==9999">至今</s:if><s:else><s:property value="endyear" />年</s:else></span>
			</div>
			<div id="<s:property value="careerid" />" class="edit_career">
			</div>
		</div>
		</s:iterator>
	</div>
	<div style="border-top:1px solid #E9E9E9;">
		<div style="font-size: 14px;font-weight: bold;">添加职业信息</div>
		<div id="addCareer" style="padding-left: 50px;"><form id="careerFrom" method="post" onsubmit="return false;">
			<table border="0" cellpadding="0" cellspacing="10">
				<tr>
					<td align="right">所在地：</td>
					<td>
						<select name="irpCareer.province" id="province"></select> 
				    	<select name="irpCareer.city" id="city"></select>
				    	<select name="irpCareer.area" id="area"></select>
				    	<script type="text/javascript">
							addressInit('province', 'city', 'area', '', '', '');
						</script>
				    </td>
				</tr>
				<tr>
					<td align="right">工作时间：</td>
					<td><select name="irpCareer.startyear" id="startyear">
						<s:iterator value="years" var="year">
						<option value="<s:property value="year" />"><s:property value="year" /></option>
						</s:iterator>
					</select>&nbsp;至&nbsp;<select name="irpCareer.endyear" class="easyui-validatebox" validType="year['#careerFrom [id=startyear]']">
						<option value="9999">至今</option>
						<s:iterator value="years" var="year">
						<option value="<s:property value="year" />"><s:property value="year" /></option>
						</s:iterator>
					</select></td>
				</tr>
				<tr>
					<td align="right">单位名称：</td>
					<td><input type="text" name="irpCareer.companyname" value="" class="easyui-validatebox" required="true" validType="length[2,60]" missingMessage="请填写单位名称" />&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td align="right">部门/职位：</td>
					<td><input type="text" name="irpCareer.department" value="" class="easyui-validatebox" validType="maxLength[60]" /></td>
				</tr>
				<tr>
					<td colspan="2"><a href="javascript:void(0);" onclick="addCareer()" class="zhuanz1">保存</a></td>
				</tr>
			</table>
		</form></div>
	</div>
</div>
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div>
</div>
</body>
</html>
