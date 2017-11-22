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
<title>请假申请明细</title>

<style type="text/css">
a {
    color: #333;
    text-decoration: none;
}
a:hover {
    color: #5f9ddd;
    text-decoration: underline;
}
.title {
    color: #004566;
    font-size: large;
    font-weight: bold;
    line-height: 25px;
    text-align: center;
}
.btn {
    background-image: url("images/btn.gif");
    border: 1px solid #91bdfe;
    border-radius: 2px;
    color: #002864;
    cursor: pointer;
    height: 25px;
    line-height: 22px;
    min-width: 70px;
    padding: 0;
}
.btn_over {
    background-image: url("images/btn_over.gif");
    border: 1px solid #91bdfe;
    border-radius: 2px;
    color: #002864;
    cursor: pointer;
    height: 25px;
    line-height: 22px;
    min-width: 70px;
    padding: 0;
    text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}
.btn a:hover {
    background-color: #ff00ff;
}
.btnDiv {
    padding-bottom: 5px;
}
</style>
<!-- 这是打印需要的js -->
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/lhgdialog.min.js"></script>
<script type="text/javascript">
function closeWin(){
	var ismangcenter = <s:property value="isMangcenter"/>;
if(ismangcenter==1){
	window.location.href='../user/user_manage.action'; 
}else{
	window.location.href='leave/leaveList.action?type=0';
}
}
function printForm(){

		console.info($('#appaddform232').html());
		$('#appaddform232').printArea({
			extraCss : '',
			retainAttr : ["class", "id", "style", "on"],
			extraHead: '<meta charset="utf-8" />,<meta http-equiv="X-UA-Compatible" content="IE=edge"/>'
		});
	
}
function expPdf(){
	
}
function expDoc(){
	
	
}
function chooseHigher(flag){
	if(flag==1){
		
	$("#opin").show();
	$("#jingli").show();
	$("#checkerAuvise").hide();
	$("#notice").show();
	}else if(flag==0){
	$("#jingli").hide();
	$("#checkerAuvise").show();
	$("#notice").hide();
	$("#opin").hide();
	}
	
}



function handel(_leaveapplyid,_emergency){
	$.dialog.confirm("您确定要处理这个请假申请么？",function(){
	
	var flag=$("input[name=checkmore]:checked").val();
		var checker=$("#checker").val();
		var bool=false;
	if(flag==1){
		if(checker.trim()==''){
			$.messager.alert("提示","请选择审核人");
			
		}else{
			
			bool=true;
		}
		
		
	}else{
		bool=true;
	}
if(bool){
	var _auditinfo = $('#auditinfo').val();
	var _opinion = $('#opinion').val();
			 $.ajax({
					url:'<%=rootPath%>leave/upStatusLeave.action',
					type:'post',
					data:{
					change:$("#checkmore").val(),
					leaveconfigid:_leaveapplyid,
					emergency:_emergency,
					auditinfo:_auditinfo,
					checkmore:$("input[name=checkmore]:checked").val(),
					userids:$("input[name=userids]").val(),
					opinion:_opinion
					
					},
					cache : false,
					success:function(data){
						if(data=='1'){
				        	$.dialog.tips('申请处理成功',1,'32X32/succ.png',function(){
				        	window.location.href='../user/user_manage.action';
    						});			    
				        }else if(data=='0'){
				        	$.dialog.tips('申请者重新提交了表单，请刷新',1,'32X32/fail.png');	
				        }else if(data=='2'){
				        	$.dialog.tips('申请者已经撤销了此表单',1,'32X32/fail.png');
				        }  
					}
		});
}	
	},function(){});
}

function checkOffpersonId(){
	 var result = $.ajax({
				url: '<%=rootPath %>leave/selectchecker.action?type=10',
			    async: false,
			    cache: false,
			     data:{
			     userids:$('#checkeruserids').val(),
			      cruserid:$('#cruserid').val(),
			     }
			}).responseText;
		$.dialog({		 
				title:'请选择审核人',
				content: result ,
				max: false,
			    min: false,
				ok: function(){
				 $("#checker").val(usernamechecker);
				$("#checkeruserids").val(useridchecker);
				$("#typeadddiv1").dialog("close"); 
			    },
			    okVal:'提交',
			    cancelVal: '取消',
			    cancel: function(){
			    	$("#typeadddiv1").dialog("close");
			    },
			    lock: true,
			    padding: 0,
		});		
		
		
		
	}

</script>


</head>
<body style="">
<form id="appaddform232" method="post" style="text-align: center; margin: 0 auto;">
<s:hidden name="irpLeaveapplyInfo.cruseridl" id="cruserid"></s:hidden>
<input name="irpLeaveapplyInfo.checkmore" style="display:none;" id="checkmore" value="<s:property value="irpLeaveapplyInfo.checkmore" />" />
<div id="formDiv">
<div id="formTitleDiv" style="padding-left: 80px;">

<span id="formTitle" class="title"><s:property value="irpLeaveapplyInfo.title" /></span>
</div>
</div>
<div class="sysdesc">&nbsp;</div>
		    <table width="980px" height="80%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea" style="font-size: 9pt !important;">
    	 <input type="hidden" name="jsonFile"/>
    	  <tr>
            <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假人：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.cruserid" />
            
           </td>
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">申请日期：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:date name="irpLeaveapplyInfo.crtime" format="yyyy-MM-dd HH:mm"/>
</td>
        </tr>
    	  <tr>
            <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">部门：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:if test="userGroups.size()>0 && (irpDocument==null || irpDocument.isdraft==0)">

<s:iterator  value="userGroups">
	<s:property value="value" />
</s:iterator>

</s:if>
            
           </td>
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">事情紧急程度：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.emergency" />

</td>
        </tr>
    	 
    	  <tr>
            <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假开始时间：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:date name="irpLeaveapplyInfo.starttime" format="yyyy-MM-dd HH:mm"/>            
           </td>
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假结束时间：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:date name="irpLeaveapplyInfo.endtime" format="yyyy-MM-dd HH:mm"/>
</td>
        </tr>
        <tr>
            <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假类型：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
         <s:property value='irpLeaveapplyInfo.applytypeid'/>
            
           </td>
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假天数：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value='irpLeaveapplyInfo.leavedays'/>
</td>
        </tr>
 <tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">通知方式：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
		<s:property value="irpLeaveapply.warnMenthodString" />
</td>
</tr>
        <s:if test="irpLeaveapply.applytypeid==10">
       <tr >
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">剩余年假天数：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.userleavedays" />
 
</td>
</tr>
      </s:if> 
        <tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">请假事由：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value='irpLeaveapplyInfo.applyreason'/>
</td>
</tr>


<s:elseif test="irpLeaveapplyInfo.isManager==0&&irpLeaveapply.applystatus!=20">
<tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">部门领导审核意见：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.auditinfo"/>
</td>
</tr>
</s:elseif>





        <tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">流程说明：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('LEAVEAPPLY_FLOW')"/>
</td>
</tr>

<tr>
            <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">审核人：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
       <s:property value="irpLeaveapplyInfo.checker" />     
           </td>
           <s:if test="irpLeaveapplyInfo.isManager==1">
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">审核状态：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.advise" />
</td> 
</s:if>
 <s:if test="irpLeaveapplyInfo.isManager==0">
           <td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">审核状态：</td>
<td colspan="2" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<s:property value="irpLeaveapplyInfo.applystatus" />
</td> 
</s:if>
        </tr>



 <s:iterator value="attachedinfos" >
 
 
<s:if test="flag=='true'">
   	 
        <tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">附件：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><img alt="显示图片" src="<%=rootPath %>file/readfile.action?fileName=<s:property value="attfile" />" id="mypic" width="100" height="100"/></a>
				&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
</td>
</tr>
       </s:if>
       <s:else>
               <tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">附件：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>"><s:property value="attdesc" /></a>
				&nbsp;&nbsp; 
				<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
</td>
</tr>
       
       </s:else>
     </s:iterator>  
     <s:if test="irpLeaveapplyInfo.isManager==1&&irpLeaveapplyInfo.opinion!='暂无'">
 <tr  >
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">备注意见</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
          <s:property value="irpLeaveapplyInfo.opinion" />
            
</td>
</tr>      
</s:if>
<s:if test="irpLeaveapplyInfo.isManager==1&&irpLeaveapplyInfo.advise=='未审核'">
<tr>
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">是否需要上一级审批</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
            <input name="checkmore"    type="radio" value="1" onclick="chooseHigher(1)"> 是
            <input name="checkmore"    type="radio" checked="checked" value="0" onclick="chooseHigher(0)"> 否
            <div id="jingli" style="display:none">
            <input name="checker"  id="checker" type="text"  onclick="checkOffpersonId()" validType="length[2,50]" class="easyui-validatebox"  style="border:1px solid #999999;"  missingMessage="请选择审核人"   data-options="required:true"  >
            <input name="userids"  id="checkeruserids"   type="text" style="display:none" value="" >
            </div>
</td>
</tr>
<tr  id="opin" style="display:none">
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">备注</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
            <from id="backinfo">
	<textarea id="opinion" name="opinion"  class="easyui-validatebox"   missingMessage="请填写备注信息"  validType="length[2,50]"  style="width:90%;height:40px;"></textarea>*
</from>
            
</td>
</tr>
<tr id="checkerAuvise" >
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">领导审核意见：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
<from id="backinfo">
	<textarea id="auditinfo" name="auditinfo"  class="easyui-validatebox"   validType="length[2,100]"  style="width:90%;height:40px;"></textarea>*
</from>
</td>
</tr>
<!-- 

 <tr id="notice" style="display:none">
<td style="background-color:#fff; text-align: right; background-color: #f1f9fe; padding: 3px; border: 1px solid #6faadb; ">通知方式：</td>
<td colspan="5" style="background-color:#fff; text-align: left; padding: 3px; border: 1px solid #6faadb; ">
		<s:property value="irpLeaveapply.warnMenthodString" />
</td>
</tr>

 -->
 </s:if>




	
      </table>
      </br></br>
<div id="mainBtnDiv" class="noprint" style="text-align: center; margin: 0 auto;">
<s:if test="irpLeaveapplyInfo.isManager==1&&irpLeaveapplyInfo.advise=='未审核'">
<button class="btn" onclick="handel(<s:property value="irpLeaveapply.leaveapplyid" />,20)" onmouseout="this.className='btn'" onmouseover="this.className='btn_over'" style="margin-right: 3px;" type="button">通过申请</button>
<button class="btn" onclick="handel(<s:property value="irpLeaveapply.leaveapplyid" />,10)" onmouseout="this.className='btn'" onmouseover="this.className='btn_over'" style="margin-right: 3px;" type="button">拒绝申请</button>
</s:if>

<button class="btn" onclick="printForm()" onmouseout="this.className='btn'" onmouseover="this.className='btn_over'" style="margin-right: 3px;" type="button">打印</button>

<button class="btn" onclick="closeWin()" onmouseout="this.className='btn'" onmouseover="this.className='btn_over'" type="button">返回</button>
</div>
</form>	




</body>
</html>