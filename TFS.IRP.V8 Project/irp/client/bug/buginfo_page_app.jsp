<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="irpBugs.get(0).title"/></title>

<style type="text/css">
#bugPage {
	width: 100%;
	
	margin: auto;
	margin-top: 20px;
}

#bugPage #headInfo .title {
	font-size: 15px;
	text-align: left;
	margin-left: 30px;
	width: 80px;
	display: inline;
	color:blue;
	font-family: '华文中宋';
}

#bugPage #headInfo .comment {
	margin-left: 50px;
}

#flow {
	margin: auto;
	margin-top: 50px;
	width: 100%;
	min-height: 100px;
	
}
#state {
	margin: auto;
	margin-top: 50px;
	width: 100%;
	margin-left:170px;
}
#beizhu{height: 300px;margin-left:170px;}
#btn{height: 30px;margin-top:10px;margin-left:170px;}
table 
  {   border-collapse:    separate;   border-spacing:  0 20px;  } 
#describe{margin-top:30px;} 
#form{margin-bottom:20px;}
a{font-size: 14px;   }
</style>
<script type="text/javascript">
var editor =null;
function transpond(){
	//var isSubmit=$('#form').form('validate');
	var submitStr=$('#form').serialize();
	var serianum=<s:property value="irpBugs.get(0).serianum" />;
	//var bugComment=encodeURI(editor.getData());
	var bugComment=$('#editor').val();
	var urlStr='<%=rootPath %>phone/dispose.action?'+submitStr+'&token='+$('#tokens').val();
	var refreshStr='<%=rootPath%>phone/simplebuginfo.action?serianum='+serianum+'&token='+$('#tokens').val();
	//alert(urlStr);
	/***********************************ajax***************************************/
	$.ajax({
		type:'post',
		url:urlStr,
		cache:false,
		async: false,
		data:{'serianum':serianum,'bugComment':bugComment},
		success:function(){
			$.dialog.tips('操作成功!',1,'32X32/succ.png',function(){ 
				window.location.href='<%=rootPath %>phone/simplebuginfo.action?serianum='+serianum+'&token='+$('#tokens').val();
				});						
		}
	});
	/***********************************ajax***************************************/
}
 $(function(){
	 		/*
	 		alert('');
	 		var des=""+<s:property value="irpBugs.get(0).describe" escapeHtml='false' />+"&nbsp;";
	 		alert(des);
	 		*/
	 		//$('#descr').html(des);
	 		/* editor= CKEDITOR.replace('editor',{
				//uiColor: sColor
	 			width:290
			}); */
});

 //删除Bug
function delBug(){
	var serianum=<s:property  value="serianum"  />;
	var token=<s:property  value="tokens"  />;
	var urlStr="<%=rootPath %>bug/bugdel.action";
	//alert(serianum);
	$.dialog.confirm("您确认要删除这个Bug吗?",function(data){
		if(data){
				$.ajax({
					type:'post',
					data:{
						'serianum':serianum,
						'token':token
					},
					url:urlStr,
					cache:false,
					async: false,
					success:function(){
						$.dialog.tips('删除成功!',1,'32X32/succ.png',function(){ 
							//window.location.href=document.referrer;
							window.location.href='<%=rootPath%>phone/simplebuginfo.action?serianum='+serianum+'&token='+$('#tokens').val();
							//window.close();
							});						
					}
				});
			
		};
		
	});
}
</script>
</head>

<!-- submit tijiao
   $('#form').form('submit',{
			url:urlStr,
			success:function(callback){  
					if(callback!=""){ 
						$.dialog.tips('操作成功!',1,'32X32/succ.png',function(){ 
						 // showoneproject();
							$('#bugPage').html(callback);
							$('form').hide();
						});						
						}else{
							$.dialog.tips('处理失败!',0.5,'32X32/fail.png');	
						}
				},
				error:function(){
					$.dialog.tips('处理失败!',0.5,'32X32/fail.png');
				}
				
			});  
			return isSubmit;
		
 -->
<body style="background-image: url('');">
	<div id="bugPage">
		<div id="headInfo">
			<div align="center"><span style="font-size: 20px;">Bug处理</span></div> <br /> <br />
			<div style="float: left;width: 85%;padding-left: 15%">
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 40px;">项目名称</div>
				<div style="float: left;width: 30%;color: #666;font-size: 13px;line-height: 40px;"><s:property value="project.prname"/></div>
			<s:if test="loginuserid == irpBugs.get(0).founderid">
				<div style="float: left;width: 30%;color: #1077B5;font-size: 13px;line-height: 40px;text-align: right;">
					<a href="loadtoeditor.action?serianum=<s:property value = 'irpBugs.get(0).bugid '/>&token=<s:property value='tokens'/>" style="color: #1077B5;" onclick="">修改</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" style="color: #1077B5;" onclick="delBug()">删除</a>
				</div>
			</s:if>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 40px;">项目名称</div>
				<div style="float: left;width: 30%;color: #666;font-size: 13px;line-height: 40px;"><s:property value="project.prname"/></div>
			<s:elseif test="isprojectcre == 1">
				<div style="float: left;width: 30%;color: #1077B5;font-size: 13px;line-height: 40px;text-align: right;">
					<a href="loadtoeditor.action?serianum=<s:property value = 'irpBugs.get(0).bugid '/>&token=<s:property value='tokens'/>" style="color: #1077B5;" onclick="">修改</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" style="color: #1077B5;" onclick="delBug()">删除</a>
				</div>
			</s:elseif>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">bug标题</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="irpBugs.get(0).title"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">版本</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="versionNameMap.get(irpBugs.get(0).versionid)" default="默认版本"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">模块</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="modulNameMap.get(irpBugs.get(0).moduleid)" default="默认模块"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">创建时间</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:date name="irpBugs.get(0).createtime" format="yyyy-MM-dd"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">发起人</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="founderNameMap.get(irpBugs.get(0).founderid)"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">处理人</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property default="无" value="founderNameMap.get(irpBugs.get(irpBugs.size()-1).founderid)"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">优先级</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="priorityMap.get(irpBugs.get(0).priority)"/></div>
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">状态</div><div style="float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="lastBugState"   /></div>
				
				<div style="float: left;width: 40%;color: #010101;font-size: 13px;line-height: 30px;">描述</div><div id="descr" style="margin-top:20px;float: left;width: 60%;color: #666;font-size: 13px;line-height: 30px;"><s:property value="irpBugs.get(0).describe" escapeHtml='false' /></div>
				<div style="width: 100%;float: left;">&nbsp;</div>
				<div style="width: 20%;color: #010101;font-size: 13px;line-height: 20px;">相关附件：</div>
					<div style="width: 80%;color: #666;font-size: 13px;line-height: 20px;">
					 <s:iterator value="attacheds" >
						<s:set var="tid" value="typeid"></s:set>
							<s:if test="@com.tfs.irp.attached.entity.IrpAttached@TYPEIDOTHER==#tid">
								<div style="margin-left:;">
									<span style="width:40px;float:left;margin:10px 0 0 0px; display: block;text-align: center;background: url(<%=rootPath%>client/images/att.png) no-repeat left center;background-position:  -60px -10px; height:47px; "></span>
									<div style="float:left;margin:20px 0 0 20px; width:230px; "><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value='attdesc'/></a></div>
									<div style="float:left;margin-top:20px;margin-left:10px;">
										<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
									</div>
								</div>
								<div style="clear: both;"></div>
							</s:if>
							<s:else>
								<div style="margin-left:30px;">
									<span style="width:40px;float:left;margin:10px 0 0 40px; display: block;text-align: center;background: url(<%=rootPath%>client/images/att.png) no-repeat left center;background-position:  -10px -10px; height:47px; "></span>
									<div style="float:left;margin:20px 0 0 40px; width:250px; "><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value='attdesc'/></a></div>
									<div style="float:left;margin-top:20px;margin-left:30px;">
										<a  href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
									</div>
								</div>
								<div style="clear: both;"></div>
							</s:else>
						</s:iterator>
					</div>
				<div style="clear:both;"></div>
				<!-- 
				<div>
					<img id="DF2016021840266050" width="140px" height="150px"  style="float: none; margin: 3px; cursor: url("http://127.0.0.1:8080/irp/view/images/maxpic_03.cur"), auto;" name="DF201602184026605.jpg," src="http://127.0.0.1:8080/irp/file/readfile.action?fileName=DF201602184026605_150X150.jpg">
				</div>
				 -->
				<div style="float: left;width: 30%;color: #010101;font-size: 13px;line-height: 40px;">&nbsp;</div>	
			
			</div>  
			 
		 

		</div>
		<div style="width: 100%;float: left;">&nbsp;</div>
		<div id="flow" >
			<div display="inline" style="width: 80px;display: inline;">
			<span style="font-size: 15px;text-align: left;padding-left: 15%">处理过程</span>
			</div>
			<div id="cols" style="display: inline;width: 80%;padding-left: 15%;">
				<!-- 
				<div style=" display: inline;">
					<span style="background-color: #0066FF;color: #FFFFFF;width: 60px;height: 40px;">已分配</span>
					<span style="margin-left: 60px;"><s:property value="founderNameMap.get(bug.founderid)"/></span> →<span><s:property default="无" value="founderNameMap.get(bug.operatorid)"/></span> 
					<span></span>
					
				</div>
				 -->
				 <div style="display: inline;">
				 <table style="padding-left: 10%;width: 90%;">
				 	<s:iterator value="irpBugs" var="bug">
				 	<tr height="40px" bgcolor="#ddedfe" align="center">
				 	<s:set var="sta" value="#bug.state"></s:set>
				 		<td width="25%"><span <s:if test="#sta=='已创建'">style="background-color: #0066FF;color: #FFFFFF;width: 80px;height: 50px;padding: 3px 5px 3px 5px;"</s:if><s:elseif test="#sta=='待审核'">style="background-color: #ffa900;color: #FFFFFF;width: 80px;height: 50px;padding: 3px 5px 3px 5px;"</s:elseif><s:elseif test="#sta=='未完成'">style="background-color: #cc3300;color: #FFFFFF;width: 80px;height: 50px;padding: 3px 5px 3px 5px;"</s:elseif><s:elseif test="#sta=='已完成'">style="background-color: #339900;color: #FFFFFF;width: 80px;height: 50px;padding: 3px 5px 3px 5px;"</s:elseif><s:elseif test="#sta=='已转发'">style="background-color: #cc3300;color: #FFFFFF;width: 80px;height: 50px;padding: 3px 5px 3px 5px;"</s:elseif> ><s:property value="#bug.state"  /></span></td>
				 		<td width="15%" align="left"><s:property value="founderNameMap.get(#bug.founderid)"/><s:if test="#bug.freshness !=2">→<s:property  value="operatorNameMap.get(#bug.operatorid)"/></s:if></td>
				 		<td width="20%" align="left"><s:property value="#bug.bugcomment" escapeHtml="false" /></td>
				 		<td width="30%"><s:date name="#bug.createtime" format="yyyy-MM-dd  HH:mm:ss"/></td>
				 	</tr>
				 	</s:iterator>
				 </table>
				 </div>
			</div>
		</div>
		</div>

	
</body>
</html>