<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	
	<title>图片投票细览</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/vote.css"/>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
	<jsp:include page="../../view/include/client_skin.jsp" />
	<style type="text/css">
	body {
		behavior: url(hover.htc);
	}
	
	.STYLE1 {
		color: #0066FF;
		font-weight: bold;
	}
	
	.bar {
		background: none repeat scroll 0 0 #F0F0F0;
		float: left;
		height: 11px;
		margin-top: 2px;
		width: 100px;
		border: 1px solid red;
	}
	</style>
</head>
<body onload="selected('votepage')" style="background-color:#fff;background:none">
<script type="text/javascript">
 $(function(){
	 var starttime='<s:date name="irptitle.starttime"/>';
	 var endtime='<s:date name="irptitle.endtime"/>';
	 var msg="" ; 
	 var startDate =  new  Date(starttime.replace(/-/g,"/"));
	 var endDate =  new  Date(endtime.replace(/-/g,"/"));  
	 var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
	 if(endDate<todayTime){ 
	 	msg="<span style=\"color:red\">已经结束</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="irptitle.count==null">0</s:if><s:else><s:property value="irptitle.count" /> </s:else> 人 参与";
	 	 $("body div[class='touPiao_btn']").hide();
	 	$('#voteprocessend').html(msg);
	  }else if(todayTime<startDate){
	 	 msg="<span style=\"color:green\">敬请期待</span>"; 
	 	 $("body div[class='touPiao_btn']").hide();
	 	$('#voteprocess').html(msg);
	  }else{
	 	 msg="<span style=\"color:orange\">正在调查中</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="irptitle.count==null">0</s:if><s:else><s:property value="irptitle.count" /> </s:else> 人 参与";
	 	$('#voteprocess').html(msg);
	  }
	 showzoomimg();
 });
 
 function checkchose(_value){
	var ischeck= $(_value).attr("checked");
	if(ischeck=="checked"){
		$(_value).attr("checked","checked");
	}else{
		$(_value).attr("checked",false);
	}
 }
 //投票
 function subvote(){
	 var fag= checkislength();
	 if(fag){
		 var voteid='<s:property value="irptitle.voteid" />';
		 var $rock= $("#rock input");
		 var optionid="";
		 var len= $("div[class='touPiao']").find("input:checked").length;
		 for(var i=0;i<len;i++){
			 var ck= $("div[class='touPiao']").find("input:checked:eq("+i+")").val();
			 optionid+=ck+",";
		 }
		 $.ajax({
				type:"post",
				dataType: "json",
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_senvote.action?voteid="+voteid+"&soptionstr="+optionid,
				success:function(date){
					$.dialog.tips(date,1,'32X32/succ.png');
				}
			});
		 showresult();
	 }else{
		 $.dialog.tips("您没选全",1.5,"32X32/fail.png");
	 }
 }
 function showresult(){
	 var voteid=$("#voteid").val();
	 $.ajax({
			type:"post",
			dataType: "json",
		    async: false,
		    cache: false,
			url:"<%=rootPath%>menu/vote_checkresult.action?voteid="+voteid,
			success:function(date){
				if(date=="true"){
					var urlvote="<%=rootPath%>menu/vote_detilresult.action?voteid="+voteid;
					window.location.href=urlvote;
				}else{
					$.dialog.tips("请先投票",1.5,"32X32/fail.png");
				}
			}
		});
 }
 function checkislength(){
	 var fag=false;
	 var arr=$("div[class='touPiao']").children("div");
	 for(var i=1;i<arr.length-1;i++){
		 var choselength="0";
		 var fondvalue=0;
		 var len=$("div[class='touPiao']").children("div:eq("+i+")").find("input:checked").length;
		 //lesscheck
		 var lesslength=$("div[class='touPiao']").children("div:eq("+i+")").find("font[id='lesscheck']").html();
		 var morelength=$("div[class='touPiao']").children("div:eq("+i+")").find("font[id='morecheck']").html();
		 if($("div[class='touPiao']").children("div:eq("+i+")").find("font[id='lesscheck']").html()){
			 var lesslength=$("div[class='touPiao']").children("div:eq("+i+")").find("font[id='lesscheck']").html();
			 if($("div[class='touPiao']").children("div:eq("+i+")").find("font[id='morecheck']").html()){
				 var morelength=$("div[class='touPiao']").children("div:eq("+i+")").find("font[id='morecheck']").html();
				 if(len>=lesslength&&len<=morelength){
					 fag=true;
				 }else{
					 fag=false;
				 }
			 }else{
				 if(len>=lesslength){
					 fag=true;
				 }else{
					 fag=false;
				 }
			 }
		 }else{
			 if($("div[class='touPiao']").children("div:eq("+i+")").find("font[id='morecheck']").html()){
				 var morelength=$("div[class='touPiao']").children("div:eq("+i+")").find("font[id='morecheck']").html();
				 if(len>=1&&len<=morelength){
					 fag=true;
				 }else{
					 fag=false;
				 }
			 }else{
				 if(len>=1){
					 fag=true;
				 }else{
					 fag=false;
				 }
			 }
		 }
	 }
	 return fag;
 }
 function showzoomimg(){
	 var token = $("#token").val();
	 var len=$("body div[class='photo']").length;
	 for(var i=0;i<len;i++){
		 var imgname=$("body div[class='photo']:eq("+i+")").find("img").attr("name");
		 var index=imgname.lastIndexOf("."); 
		 var newimgname=imgname.substring(0,index)+"_150X150"+imgname.substring(index,imgname.length);
		 $("body div[class='photo']:eq("+i+")").find("img").attr("src","<%=rootPath%>file/readfile.action?fileName="+newimgname+"&token="+token);
	 }
 }
//发布
 function publishvote(){
	 $.dialog.confirm('你确定要发布投票吗',function(){
		 var vid=$("#voteid").val();
	 	 $.ajax({
	 			type:'get',
	 			url:'<%=rootPath%>menu/vote_publish.action?voteid='+vid,
	 			dataType: "json",
	 			async: false,
	 	   		cache: false,
	 	   		success:function(data){
	 	   		    $.dialog.tips(data,1.5,"32X32/succ.png");
	 	   		    var votetitle=$.trim($("#title").val());
	 	   		    var vid=$.trim($("#voteid").val());
	 	   		    var context="我刚发起了 "+votetitle+" 的投票，大家快来看看吧";
		   	   		var rsmic="<a class=\"linkbh14\" target=\"_blank\" href=\"<%=rootPath%>menu/vote_detil.action?voteid="+vid+"\" >"+votetitle+"</a>";
		   			sendmic($("#sendmictext").val(),rsmic);
	 	   		}
	 		});
	 });
 }
 
 function closewindow(){
	 $.dialog.confirm('你确定要离开这个页面吗',function(){
		 setTimeout("window.close()",0);
	 });
 }
</script>
<s:hidden name="voteid" id="voteid"/>
	<div class="bg01">
		<!--头部菜单-->
		<input type="hidden" value="<s:property value='tokens'/>" id="token"/>
		<!--头部菜单end-->
		<div class="main-gr" style="background-image: none;width:100%;">
		<!--右侧内容结束  -->
			<div>
		        <div class="touPiao">
				      <div class="group_list">
					       <h1><s:property value="irptitle.title" /></h1>
					        <span>开始日期：<s:date name="irptitle.starttime"  format="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;&nbsp;&nbsp;
					        <span id="voteprocess"></span><br/>
					        <span>结束日期：<s:date name="irptitle.endtime"  format="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;&nbsp;&nbsp;
					        <span id="voteprocessend"></span></span></span>
					        <s:if test="irptitle.description==null"></s:if><s:else>
						        <ul style="font-size: 14px;">
						          <li>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="irptitle.description"/> </li>
						        </ul>
					        </s:else>
				      </div>
				       <!--分组内容-->
			        	<s:iterator value = "maplist" status="mapliststatus">    <!-- 跌代第一个map -->
						<s:set var="votekey" value="key"/>
				        <div>
				        	<div style="font-size: 14px;line-height: 200%;color: #444444;">
				            	<span >
									 &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#votekey.votetitle"/>&nbsp;&nbsp;&nbsp;&nbsp;
									 <s:if test="#votekey.checktype==1"></s:if><s:else>  
										 <s:if test="#votekey.lesscheck>1">  
											 &nbsp;&nbsp;&nbsp;&nbsp;至少选<font color="red">&nbsp;<s:property value="#votekey.lesscheck"/></font> 项
										 </s:if>
										 <s:if test="#votekey.morecheck>1">  
											 &nbsp;&nbsp;&nbsp;&nbsp;至多选<font color="red">&nbsp;<s:property value="#votekey.morecheck"/></font> 项
										 </s:if>
									 </s:else>
								 </span>
				            </div>
				            <div>
				             <table style="width:100%;border:1px solid #000;">
				               <tr valign="top">
				               <s:iterator value = "value" status="status">  
				                   <td style="border:1px solid #000;">
						        	<div class="px_list">
						            	<div class="photo"><img src="" name="<s:property value='attimg'/>"/></div>
						                <div id="rock" class="bt" style="width:100%;">&nbsp;&nbsp;&nbsp;
										       <s:if test="#votekey.checktype==1">
										          &nbsp;&nbsp;&nbsp;&nbsp;  <input name="optionid<s:property value='#mapliststatus.index'/>" type="radio" value="<s:property value='optionid'/>" onclick="checkchose(this)"/>
										       </s:if><s:else>
										           &nbsp;&nbsp;&nbsp;&nbsp; <input name="optionid<s:property value='#mapliststatus.index'/>" type="checkbox" value="<s:property value='optionid'/>" onclick="checkchose(this)" />
										       </s:else>
									      	   <s:if test="optionurl.length()>0">
									      	   		<s:if test="optionurl!='null'">
										         		 <a target="blank"  href="<s:property value='optionurl' />"><s:property value="voteoption" escapeHtml="false"/></a>
										      		</s:if>
										      		<s:else>
										      			 <s:property value="voteoption" escapeHtml="false"/>
										      		</s:else>
										       </s:if>
										       <s:else>
										          <s:property value="voteoption" escapeHtml="false"/>
										       </s:else> 
										 </div>
						            </div>
						            </td>
						            <s:if test="#status.count%4==0"><tr valign="top"></s:if>
				 			</s:iterator>
				 			 </tr>
				 			 </table>
				 			</div>
				 			<div class="clear"></div>
				 		</div>
			 		  </s:iterator>
			 		  <s:if test="irptitle.ispublish==2">
		                  <div class="touPiao_btn"><input onclick="subvote()" style="background-color: lightblue;" class="btn_touPiao" name="" type="button" value="投票" /><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="showresult()">查看结果>></a></span></div>
		             </s:if>
		             <s:else>
				          <s:if test="@com.tfs.irp.util.LoginUtil@getLoginUserId()==irptitle.cruserid">
				           <div  style="margin-left: 300px;"><input class="btn_touPiao" onclick="publishvote()"  type="button" value="发布"/>&nbsp;&nbsp;&nbsp;&nbsp; <input class="btn_touPiao" onclick="closewindow()"  type="button" value="关闭"/> </div>
				          </s:if>
		            </s:else>
				</div>
			</div>	
		<!-- end -->
		
		</div>
	</div>
</body>
</html>