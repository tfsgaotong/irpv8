<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>

<style type="text/css">
.person-con{
	

}

.btn-add ,.btn-del{
	width: 120px;
	text-align: center;
	text-shadow: black;
	font-size: 20px;
	
}



.btn-add{
	margin-left:220px;
	
}

.btn-del{
	margin-left:200px;
}
</style>
	<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
function addProjectPer(){
	var addPersons=$('#teamperson').val().join();
	var pid=<s:property value="projectId" />;
	//alert(addPersons);
	var urlStr="<%=rootPath %>/project/addpersontoproject.action";
	
	$.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
		data:{
			'addPersonsStr' : addPersons,
			'projectId' : pid
		},
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#persons').html(html); 
	    }
	});
}

function delProjectPer(){
	var delPersons=$('#projectperson').val().join();
	var pid=<s:property value="projectId" />;
	
	var urlStr="<%=rootPath %>/project/delpersonfromproject.action";
	$.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
		data:{
			'delPersonsStr' : delPersons,
			'projectId' : pid
		},
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#persons').html(html); 
	    }
	});
}
/**
 * 添加成员  --窗口模式
 */
function  addProjectPerson(){  
	 var projectid=<s:property    value="projectId"   />;
	 var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>project/select_allusertask.action', 
		data:{'isPerson':true,
				'projectId':projectid,
				'pageNum':1,
				},
	    async: false,
	    cache: false
	}).responseText; 
	 ProjectPersonDialog= $.dialog({
		id: 'selectUser',
		title:'添加项目成员',
		max:false,
		min:false,
		lock:true, 
		init : function(){  
			$('#sWord').searchbox({}); 
			 initDialogShow();
		},
		resize: false,
		width:'600px',
		height:'150px',
		content:result,
		cancelVal:'关闭',
		okVal:'确定',
		cancel:true,
		ok:function(){    
			var _personIds=$('#userIds').val();//以逗号分割的所有用户id   
			var _loginUserId=<s:property value="loginUserId"/>;
			if(_personIds==null ||_personIds=="" ||_personIds==_loginUserId){}
			else{
				var urlStr="<%=rootPath %>/project/addpersontoproject.action";
				$.ajax({
					type:'post',
					url:urlStr,
					data:{
						'addPersonsStr': _personIds,
						'projectId': projectid
					},
					success: function(html){ 
						$('#persons').html(html); 
					} 
				}); 
			}
		},
		close:function(){
		 $('#userIds').val('');
		} 
	});	
} 

function initDialogShow(){
	$('#dialogPageForm').find('searchWord').val('');
	$('#dialogPageForm').find('searchType').val('');
	$('#dialogPageForm').find('orderField').val('');
	$('#dialogPageForm').find('pageNum').val(1);  
}
</script>
</head>
<body>
		<div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
			<div class="person-con">
				<input type="button" onclick="addProjectPerson()" class=" btn-add" value="添加成员" style="color: #6d6d6d;"/>
				<!-- 
				<input type="button" class=" btn-del" value="移除成员"  style="color: #6d6d6d;"/>
				 -->
			</div>
			<div class="txt_lptaskt"  style="width: 15%;float: left;line-height: 30px;text-align: right;">项目人员：</div>
			<br/>
			<div style="width: 100%;">
			
		<div style="margin-left: 18%;">
			
		<div id="persionTitleS"><div style="width: 43%;float: left;">团队成员</div><div style="width: 43%;float: left;">已选成员</div> </div>
		<div style="float: left;width: 100%;line-height: 4px;">&nbsp;</div>
		<div class="sel" >
			<select class="selper" style="width: 30%;height: 200px;" multiple="multiple" id="teamperson">
				<s:iterator value="teamPersons" var="user">
				<option   <s:if test="personNameMap.get(#user.userid)!=null">disabled="false"</s:if>  value="<s:property value="#user.userid"  />" ><s:property value="#user.truename"  /> <s:if test="personNameMap.get(#user.userid)!=null">(已加入)</s:if></option>	
				</s:iterator>		
			</select>
		</div>
		<div class="bot" style="margin: 70px 15px 0 15px;"> 
			<a id="btn" href="javascript:void(0)" onclick="addProjectPer();" class="easyui-linkbutton"  >添加＞</a><br/>  
			<a id="btn" href="javascript:void(0)" onclick="delProjectPer();" class="easyui-linkbutton" style="margin-top: 5px;" >＜删除</a>  
		</div>
		<div class="sel">
			<select class="selper" style="width:30%;height: 200px;" multiple="multiple" id="projectperson">
				<s:iterator value="projectPersons" var="per">
				<s:set  var="cid"  value="#per.cruserid"   ></s:set>
				<s:set  var="pid"  value="#per.personid"  ></s:set>
				<option  <s:if test="#cid==#pid">disabled="false"</s:if>  value="<s:property value="#per.personid"  />" ><s:property value="personNameMap.get(#per.personid)"  /><s:if test="#cid==#pid">(创始人)</s:if></option>	
				</s:iterator>
			</select>
		</div>
		</div>
		</div>
		</div>

</body>
</html>
