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
function addProjectPer(userid){
	/* var addPersons=$('#teamperson').val().join(); */
	var pid=<s:property value="projectId" />;
	//alert(addPersons);
	var urlStr='<%=rootPath %>/phone/addpersontoproject.action?token='+$('#tokens').val();
	
	$.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
		data:{
			'addPersonsStr' : userid,
			'projectId' : pid
		},
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#persons').html(html);
	    }
	});
}

function delProjectPer(uesrid){
	/* var delPersons=$('#projectperson').val().join(); */
	var pid=<s:property value="projectId" />;
	
	var urlStr='<%=rootPath %>phone/delpersonfromproject.action?token='+$('#tokens').val();
	$.ajax({
		url:urlStr,
		type:"post",
		//dataType: "json", 
		data:{
			'delPersonsStr' : uesrid,
			'projectId' : pid
		},
	    async: false,
	    cache: false,
	    success:function(html){
	    	$('#persons').html(html); 
	    }
	});
}

function fixWidth(percent){  
         return document.body.clientWidth * percent;  
 }
/**
 * 添加成员  --窗口模式
 */
function  addProjectPerson(){  
	 var projectid=<s:property    value="projectId"   />;
	 var result = $.ajax({
		type: 'POST',
		url: '<%=rootPath %>phone/select_allusertask.action?token='+$('#tokens').val(), 
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
		width:fixWidth(0.8),
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
				var urlStr='<%=rootPath %>phone/addpersontoproject.action?token='+$('#tokens').val();
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
<input type="hidden" value="<s:property value='tokens'/>" id="tokens"/> 
		<div class="newForm"  style="width: 100%;float: none;margin-top: 20px;"> 
			<div class="person-con" style="margin-left: -80px;">
				<input type="button" onclick="addProjectPerson()" class=" btn-add" value="添加成员" style="color: #6d6d6d;"/>
				<!-- 
				<input type="button" class=" btn-del" value="移除成员"  style="color: #6d6d6d;"/>
				 -->
			</div>
			<!-- <div class="txt_lptaskt"  style="width: 15%;float: left;line-height: 30px;text-align: right;">项目人员：</div>
			<br/> -->
			<div style="width: 100%;">
			<div  style="float: left; width:90%;padding-left: 10%;">
	<table style="width: 70%; border: 1px;">
		<tr bgcolor="#ddedfe" style="font-weight: bold;">
			<td width="60%;"     style="font-size: 15px;">&nbsp;项目人员</td>
			<!-- <td width="20%;" align="center" style="font-size: 15px;">加入</td> -->
			<td width="40%;" align="center" style="font-size: 15px;">移除</td>
		</tr>
	<%-- <s:iterator  value="listUsers" var="user">	
		<tr bgcolor="#f6f6f6" >
			<td  <s:if test="personNameMap.get(#user.userid)!=null">style="font-size: 14px;color:red;"</s:if>  value="<s:property value="#user.userid"  />" ><s:property value="#user.truename"  /> <s:if test="personNameMap.get(#user.userid)!=null">(<s:if test="#user.userid==irpProject.cruserid">创始人</s:if><s:else>已加入</s:else>)</s:if></td>
			<td  style="font-size: 14px;" align="center"><s:if test="personNameMap.get(#user.userid)==null"><a href="javascript:void(0)" onclick="addProjectPer(<s:property  value='#user.userid' />)" class="easyui-linkbutton" id="btn" data-options="iconCls:'icon-add'" plain="true"/></s:if></td>
			<td  style="font-size: 14px;" align="center"><s:if test="personNameMap.get(#user.userid)!=null&&#user.userid!=irpProject.cruserid"><a href="javascript:void(0)" onclick="delProjectPer(<s:property  value='#user.userid' />)" class="easyui-linkbutton" id="btn" data-options="iconCls:'icon-remove'" plain="true"/></s:if></td>
		</tr>
	</s:iterator> --%>
	<s:iterator value="projectPersons" var="per">
		<s:set  var="cid"  value="#per.cruserid"   ></s:set>
		<s:set  var="pid"  value="#per.personid"  ></s:set>
		<tr bgcolor="#f6f6f6" >
		<td   value="<s:property value="#per.personid"  />" ><s:property value="personNameMap.get(#per.personid)"  /> <s:if test="#cid==#pid">(创始人)</s:if></td>
		<td  style="font-size: 14px;" align="center"><s:if test="#cid!=#pid"><a href="javascript:void(0)" onclick="delProjectPer(<s:property  value='#per.personid' />)" class="easyui-linkbutton" id="btn" data-options="iconCls:'icon-remove'" plain="true"/></s:if></td>
		</tr>
	</s:iterator>
	</table> 
	 
	</div>
			
		<%-- <div style="margin-left: 18%;">
			
		<div id="persionTitleS"><div style="width: 43%;float: left;">团队成员</div><div style="width: 43%;float: left;">已选成员</div> </div>
		<div style="float: left;width: 100%;line-height: 4px;">&nbsp;</div>
		<div class="sel" >
			<select class="selper" style="width: 30%;height: 200px;" multiple="multiple" id="teamperson">
				<s:iterator value="teamPersons" var="user">
				<option   <s:if test="personNameMap.get(#user.userid)!=null">disabled="false"</s:if>  value="<s:property value="#user.userid"  />" ><s:property value="#user.truename"  /> <s:if test="personNameMap.get(#user.userid)!=null">(已加入<s:if test="#cid==#pid">(创始人)</s:if>)</s:if></option>
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
		</div> --%>
		</div>
		</div>

</body>
</html>
