<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>


<script type="text/javascript">

/**
 * 修改项目名称
 */
function editorPrname(){
	var pid=<s:property value="projectId"  />;
	var nprname=$('#nprname').val().replace(/(^\s*)|(\s*$)/g,'');;
	var urlStr="<%=rootPath %>project/updateprname.action";
	if(nprname==null || ""==nprname ){
		$.dialog.tips('请输入项目名!',1,'32X32/succ.png',function(){ 
		});
	}else{
		$.ajax({
			url:urlStr,
			type:"post",
			data:{
				'projectId':pid,
				'prname':nprname
			},
			cache:false,
			async:false,
			success:function (html){
				$.dialog.tips('修改成功!',1,'32X32/succ.png',function(){ 
					$('#proinfo').html(html);
					allprojectLeft();
					initleftsel();
	    		});
			}
		});
	}
}
</script>

<style type="text/css">

.abc{
width: 90px;
float: right;
margin-right:180px;

}

.abc a {
	display: inline-block;
	background: url(../images/editor/editor_btn.gif) no-repeat left top;
	width: 50px;
	height: 36px;
	color: #fff;
	font-size: 15px;
	font-family: "微软雅黑";
	margin: 0 8px;
	line-height: 36px;
	text-decoration: none;
	text-align: center;
	border-radius:3px;
}
</style>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
</head>
<body>
	<div id="proinfo">
	
	
	 	<div style="float: left;width: 100%;">&nbsp;</div><br/>
	 	<div  class="newForm" style="width: 100%;float: none;margin-top: 30px;"> 
			<div style="width: 100%;"> 
				<div class="txt_lptaskt"  style="width: 15%;float: left;line-height: 30px;text-align: right;">项目名称：</div>
				&nbsp;&nbsp;<input style="width: 55%;" name="nprname" value="<s:property value='irpProject.prname'/>" class="artTitle easyui-validatebox" required="true" validtype="length[2,300]" placeholder="添加项目名称"  id="nprname"   />
				  
			<div class="abc">
				<a class="btn" href="javascript:void(0)" onclick="editorPrname()" >保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</a>
			</div>
		</div>  
		 
        </div> 
	</div>
</body>
</html>
