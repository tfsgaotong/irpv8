<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>签到管理</title>
<style type="text/css">
.left .fyh{width:950px;margin:0px auto 10px 15px; clear:both;}
.left .fyh ul{width:886px;margin:10px auto 20px auto; display:block; text-align:right;}
.left .fyh ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.left .fyh ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.left .fyh ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}
.namecomm {white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
#signTable tr td {border: #eaeaea solid 1px;}
#page div input {margin-left: 5px;margin-right: 5px;}
.sign {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -168px -109px no-repeat;font-size:24px;line-height: 20px;text-align: center;}
.sign-in-al {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -13px -357px no-repeat;font-size:24px;text-align: center;line-height: 34px;}
.sign-change {cursor:pointer;width:132px;height:34px;background:url(<%=rootPath%>client/images/signPic.JPG) -168px -205px no-repeat;font-size:24px;text-align: center;}
.search {height: 27px;;background: url(<%=rootPath %>/client/images/searchbox_button.png) 0px 8px no-repeat ;}
.searchdate {background:url(<%=rootPath%>/client/images/rili.png) -2px 0px no-repeat;}
</style>

</head>

<body style="background: url()">
<form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" value="10"/>
</form>
<div style="width: 950px;margin-bottom: 2px;text-align: right;" >
	<div style="float: left;margin-top: 14px;">
		<input value="<s:property value='searchName' />" type="text" id="serachText" name="searchName" style="background-color:#FFF;color: grey;width: 120px;"/>
		<input class="searchdate" value="" type="button" onclick="showdate()" style="width: 15px;cursor:pointer;height: 18px;"/>
		<span id="timedatespan" style="display: none;">
			开始日期 : <input style="width: 90px;" value="<s:property value='beginTime' />" type="text" id="starttime" class="easyui-datebox" editable="false" />&nbsp;&nbsp; 
			结束日期 : <input style="width: 90px;" value="<s:property value='finishTime' />" type="text" id="endtime" class="easyui-datebox" editable="false" />
		</span>
		<input class="search" type="submit" value="" style="cursor:pointer;width: 20px;height: 25px;" onclick="searchcontent()">
		<a href="javascript:void(0)" onclick="exportToZip()">导出</a>
		<a  id="daochu"></a>
	</div>
	<s:if test="irpSignInfo==null">
		<input name="signinoper" class="sign" type="button" onclick="signin()" value="签&nbsp;&nbsp;&nbsp;到" id="inputin" />	
	</s:if>
	<s:else> 
		<input style="cursor:inherit;" class="sign-in-al" type="button" disabled="disabled" title="今日已签到" value="已&nbsp;签&nbsp;到"/>
	</s:else>
	<s:set var="issigout" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('IS_SIGOUT')"></s:set>
	<s:if test='#issigout=="T"'>
		<s:if test="irpSignInfo==null"><input style="cursor:inherit;" class="sign-in-al" disabled="disabled" title="今日还未签到,无法签退" value="签&nbsp;&nbsp;&nbsp;退" /></s:if>
		<s:elseif test="irpSignInfo.signoutstatus!=null"><input style="cursor:inherit;" class="sign-in-al" disabled="disabled" type="button" title="今日已签退" value="已&nbsp;签&nbsp;退" /></s:elseif>
		<s:else><input name="signoutoper" class="sign" type="button" onclick="signout()" value="签&nbsp;&nbsp;&nbsp;退" id="inputout" title="签退"/></s:else>
	</s:if>
</div>
<div id="page" class="left" style="text-align:center">
	    <div style="clear:both;width: 950px;" >
	    	<s:if test="signList==null">未找到相关信息</s:if>
	    	<s:else>
	    		<table id="signTable" align="center" style="width: 100%;table-layout: fixed;">
	    				<tr bgcolor="#D7DAF0">
	    					<td width="50px;">序号</td>
	    					<td width="85px;">姓名</td>
	    					<td width="110px;">签到时间</td>
	    					<td width="100px;">签到IP地址</td>
	    					<td width="110px;">签退时间</td>
	    					<td width="100px;">签退IP地址</td>
	    					<td width="75px;">签到/签退</td>
	    					<td width="75px;">去向</td>
	    					<td>备注</td>
	    					<td width="45px;">操作</td>
	    				</tr>
		    		<s:iterator var="ele" value="signList" status="status">
		    			<tr
		    				<s:if test="#status.count%2==0">bgcolor="#F6F6F6"</s:if>
		    			>
		    				<td><s:property value="(pageNum-1)*pageSize+#status.count"/></td>
		    				<td style="text-align: left;" title="<s:property value="nameMap.get(#ele.signid)" />" class="namecomm">
		    					<s:property value="nameMap.get(#ele.signid)" />
		    					<input type="hidden" value="${ele.signid }" name="usrId"/>
		    				</td>
		    				<td><s:date name="#ele.signintime" format="yyyy-MM-dd HH:mm"/></td>
		    				<td><s:property value="#ele.signinip" /></td>
		    				<td>
		    					<s:if test="#ele.signouttime==null">未签退</s:if>
		    					<s:else><s:date name="#ele.signouttime" format="yyyy-MM-dd HH:mm"/></s:else>
		    				</td>
		    				<td><s:property value="#ele.signoutip" default="未签退" /></td>
		    				<td>
		    					<s:property value="#ele.signinstatus"/>
		    					<s:if test="#ele.signoutstatus!=null">/<s:property value="#ele.signoutstatus" /></s:if>
		    				</td>
		    				<td style="text-align: left;" title="<s:property value="#ele.signindirection"/>" class="comm"><s:property value="#ele.signindirection"/></td>
		    				<s:if test="#ele.signcomment!=null && #ele.signcomment!=''">
		    					<td style="text-align: left;" title="<s:property value="#ele.signcomment"/>" class="comm"><s:property value="#ele.signcomment" /></td>
		    				</s:if>
		    				<s:else><td></td></s:else>
		    				<td>
		    					<s:if test="notSignOutRecord.get(#ele.id)!=null">
		    						<a href="javascript:void(0)" ><img onclick="signagain(<s:property value='#ele.id' />)" alt="补签" title="补签" src="<%=rootPath %>client/images/signagain.JPG" /></a>
		    					</s:if>
		    				</td>
		    			</tr>
		    		</s:iterator>
		    	</table>
	    	</s:else>
	    </div>
	    <div class="fyh" style="width:950px;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
	</div>
	<script type="text/javascript">
	/**
	 * 分页
	 */
	function pageNavigain(_nPageNum){
		$('#pageNum').val(_nPageNum);
		var searchName = encodeURI($('#serachText').val());
		var beginTime = $('#starttime').datebox('getValue');
		var finishTime = $('#endtime').datebox('getValue');
		if(beginTime!=null && beginTime!='' && finishTime!=null &&finishTime!=''){
			if(finishTime<=beginTime){
				$.dialog.tips('结束日期应大于开始日期',1,'32X32/fail.png');
				return false;
			}
		}
		var queryString = $('#pageForm').serialize();
		$.ajax({
			type:"post",
			url:"<%=rootPath%>sign/signInit.action?"+queryString+"&queryType=1&searchName="+searchName+"&beginTime="+beginTime+"&finishTime="+finishTime,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					$('#page').html(html);
				}
			}
		});	
	}

/**
 * 按条件搜索
 */
function searchcontent(){
	pageNavigain(1);
}

////签到
function signin(){
	$('input[name="signinoper"]').removeAttr("onclick");
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath %>sign/signin_set.action',
	    async: false,
	    cache: false
	}).responseText;
	//弹出对话框
	$.dialog({
		title:'签到',
		content: result ,
		max: false,
	    min: false,
		ok: function(){
			var validate = $('#signinForm').form('validate');
			$('#signinForm').form('submit',{
    			url : "<%=rootPath %>sign/signIn.action",
    			success:function(data){
    				if(data>0){
    					$.dialog.tips('签到成功',1,'32X32/succ.png',function(){
    						window.location.href='sign/signInit.action';
    					});
    				}else{
    					$.dialog.tips('签到失败',1,'32X32/fail.png');
    					$('input[name="signinoper"]').attr("onclick","signin()");
    				}
    			}
    		});
			return validate;
	    },
	    okVal:'确定',
	    cancelVal: '取消',
	    cancel: function(){
	    	$('input[name="signinoper"]').attr("onclick","signin()");
	    },
	    lock: true,
	    padding: 0
	});
	//添加验证
	$('#signinForm').find("input[name='irpSignInfo.signindirection']").validatebox();
}

//签退
function signout(){
	$('input[name="signoutoper"]').removeAttr("onclick");
	//判断是否能签退
	var issignout = $.ajax({
		url: '<%=rootPath %>sign/issignout.action',
	    async: false,
	    cache: false
	}).responseText;
	if(issignout!="ok"){	
		$.dialog({
		title:'确认信息',
		content:'现在是工作时间，您确定要签退吗？' ,
		max: false,
	    min: false,
	    icon:'prompt.gif',
		ok: function(){
			//获得内容
	var result = $.ajax({
		url: '<%=rootPath %>sign/signout_set.action',
	    async: false,
	    cache: false
	}).responseText;
	//弹出对话框
	$.dialog({
		title:'签退',
		content: result ,
		max: false,
	    min: false,
		ok: function(){
			$('#signoutForm').form('submit',{
    			url : "<%=rootPath %>sign/signOut.action",
    			success:function(data){
    				if(data>0){
    					$.dialog.tips('签退成功',1,'32X32/succ.png',function(){
    						window.location.href='<%=rootPath %>sign/signInit.action';
    					});
    				}else{
    					$.dialog.tips('签退失败',1,'32X32/fail.png');
    					$('input[name="signoutoper"]').attr("onclick","signout()");
    				}
    			}
    		});
	    },
	    okVal:'确定',
	    cancelVal: '取消',
	    cancel: function(){
	    	$('input[name="signoutoper"]').attr("onclick","signout()");
	    },
	    lock: true,
	    padding: 0
	}); 
	    },
	    okVal:'确定',
	    cancelVal:'取消',
	    cancel:function(){
	  	 $('input[name="signoutoper"]').attr("onclick","signout()");
	    },	    
	    lock: false,
	    padding: '30px 5px',
	}); 
		
	}else{
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath %>sign/signout_set.action',
	    async: false,
	    cache: false
	}).responseText;
	//弹出对话框
	$.dialog({
		title:'签退',
		content: result ,
		max: false,
	    min: false,
		ok: function(){
			$('#signoutForm').form('submit',{
    			url : "<%=rootPath %>sign/signOut.action",
    			success:function(data){
    				if(data>0){
    					$.dialog.tips('签退成功',1,'32X32/succ.png',function(){
    						window.location.href='<%=rootPath %>sign/signInit.action';
    					});
    				}else{
    					$.dialog.tips('签退失败',1,'32X32/fail.png');
    					$('input[name="signoutoper"]').attr("onclick","signout()");
    				}
    			}
    		});
	    },
	    okVal:'确定',
	    cancelVal: '取消',
	    cancel: function(){
	    	$('input[name="signoutoper"]').attr("onclick","signout()");
	    },
	    lock: true,
	    padding: 0
	}); 
	}
	
}

//补签
function signagain(id){
	//获得内容
	var result = $.ajax({
		url: '<%=rootPath %>sign/signout_set.action',
	    async: false,
	    cache: false
	}).responseText;
	//弹出对话框
	$.dialog({
		title:'补签',
		content: result ,
		max: false,
	    min: false,
		ok: function(){
			$('#signoutForm').form('submit',{
    			url : "<%=rootPath%>sign/signAgain.action?irpSignInfo.signinfoid="+id,
    			success:function(data){
    				if(data>0){
    					$.dialog.tips('补签成功',1,'32X32/succ.png',function(){
    						window.location.href='sign/signInit.action';
    					});
    				}else{
    					$.dialog.tips('补签失败',1,'32X32/fail.png');
    				}
    			}
    		});
	    },
	    okVal:'确定',
	    cancelVal: '取消',
	    cancel: function(){
	    },
	    lock: true,
	    padding: 0
	});
	
}

$(function(){
	$('#inputin,#inputout').mouseover(function(){
		$(this).removeClass("sign");
		$(this).addClass("sign-change");
	});
	$('#inputin,#inputout').mouseout(function(){
		$(this).removeClass("sign-change");
		$(this).addClass("sign");
	});
	
	$('.namecomm').mouseover(function(){
		$(this).css({"text-decoration":"underline"});
		$(this).css({"cursor":"pointer","color":"green"});
	});
	$('.namecomm').mouseout(function(){
		$(this).css({"text-decoration":"none"});
		$(this).css({"cursor":"inherit","color":""});
	});
	
	$('.namecomm').click(function(){
		var userid = $(this).find("input[name='usrId']").val();
		window.open("<%=rootPath%>site/client_to_index_person.action?personid="+userid);
	});
});

/**
**回车提交查询
*/
$(function(){
	 $('#serachText').keyup(function(event){
			var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
			if(keyCode==13){
				 var se=$('#serachText').val();
				 searchcontent();
			}
	 });
 });

//显示日期搜索
function showdate(){
	var displaystatus = $('#timedatespan').css('display');
	if(displaystatus=='none'){
		$('#timedatespan').show();
	}else{
		$('#timedatespan').hide();
		
	}
}

function exportToZip(){
	var now=new Date();
	var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var fileName=date;
	document.getElementById("daochu").href="sign"+fileName+".zip";
	
	var searchName = encodeURI($('#serachText').val());
	var beginTime = $('#starttime').datebox('getValue');
	var finishTime = $('#endtime').datebox('getValue');
	$.ajax({
		type:"post",
		url:"<%=rootPath%>sign/exportToZip.action?searchName="+searchName+"&beginTime="+beginTime+"&finishTime="+finishTime+"&fileName="+fileName,
		async:false,
		success:function(){
			document.getElementById("daochu").click();
		}
        
	});	
	 
	
}


$(function(){

	var nameContent  = $('#serachText').val();
	var beginTime = $('#starttime').datebox('getValue');
	var finishTime = $('#endtime').datebox('getValue');
	if((beginTime!=null && beginTime!='') || (finishTime!=null && finishTime!='')){
		$('#timedatespan').show();
	}
	if(nameContent==null || nameContent ==''){
		$('#serachText').val('用户姓名');
	}
	$('#serachText').focusin(function(){
		var name  = $('#serachText').val();
		if(name=='用户姓名'){
			$(this).val("");
		}
	});
	$('#serachText').focusout(function(){
		var name  = $('#serachText').val();
		if(name==null || name==''){
			$(this).val("用户姓名");
		}
	});
});

</script>
</body>
</html>
