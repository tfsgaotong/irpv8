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
	<title>图片投票结果</title>
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
		height: 11px;
		width: 100px;
		position: absolute;
		z-index: -1;
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
	 showresult();
	 showzoomimg();
 });

function showresult(){
	 var len=$("body").find("div[class='sign']").length;
	 for(var i=0;i<len;i++){
		 //计算
		 var res= $("body").find("div[class='sign']:eq("+i+")").find("span[class='bar']").attr("title");
		 var withres=eval(res)*100;
		 withres+="%";
		 if(withres=="NaN%"){
			 withres="0%";
		 }
		$("body").find("div[class='sign']:eq("+i+")").find("span[title='colorbar']").attr("style","width:"+withres);
		 if(withres.length>5){
			 $("body").find("span[id='ct']:eq("+i+")").html(withres.substring(0,5)+"%");
		 }else{
			 $("body").find("span[id='ct']:eq("+i+")").html(withres);
		 }
	 }
}
function showzoomimg(){
	 var len=$("body div[class='photo']").length;
	 for(var i=0;i<len;i++){
		 var imgname=$("body div[class='photo']:eq("+i+")").find("img").attr("name");
		 var index=imgname.lastIndexOf("."); 
		 var newimgname=imgname.substring(0,index)+"_150X150"+imgname.substring(index,imgname.length);
		 $("body div[class='photo']:eq("+i+")").find("img").attr("src","<%=rootPath%>file/readfile.action?fileName="+newimgname);
	 }
}
</script>
<s:hidden name="voteid" id="voteid"/>
	<div class="bg01">
		<!--头部菜单-->
		
		<!--头部菜单end-->
		<div class="main-gr" style="background-image: none;">
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
						        <ul>
						          <li>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="irptitle.description"/> </li>
						        </ul>
					        </s:else>
				      </div>
				       <!--分组内容-->
			        	<s:iterator value = "maplist" status="mapliststatus">    <!-- 跌代第一个map -->
						<s:set var="votekey" value="key"/>
				        	<div style="font-size: 14px;line-height: 200%;color: #444444;">
				            	<span >
									 &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#votekey.votetitle"/>&nbsp;&nbsp;&nbsp;&nbsp;
									 <s:if test="#votekey.checktype==1"></s:if><s:else>    &nbsp;&nbsp;&nbsp;&nbsp;至少选<font color="red">&nbsp;<s:property value="#votekey.lesscheck"/></font> 项</s:else>
								 </span>
				            </div>
				            <table>
				               <tr valign="top">
				            <s:iterator value = "value" status="status">  
				              <td>
					        	<div class="px_list">
					            	<div class="photo"><img src=" " name="<s:property value='attimg'/>"/></div>
					                <div id="rock" class="bt">&nbsp;&nbsp;&nbsp;
									       <s:if test="#votekey.checktype==1">
									          &nbsp;&nbsp;&nbsp;&nbsp;  <input name="optionid<s:property value='#mapliststatus.index'/>" type="radio" value="<s:property value='optionid'/>" onclick="checkchose(this)"/>
									       </s:if><s:else>
									           &nbsp;&nbsp;&nbsp;&nbsp; <input name="optionid<s:property value='#mapliststatus.index'/>" type="checkbox" value="<s:property value='optionid'/>" onclick="checkchose(this)" />
									       </s:else>
								      	   <s:if test="optionurl!=''">
									          <a target="blank"  href="<s:property value='optionurl' />"><s:property value="voteoption" escapeHtml="false"/></a>
									       </s:if>
									       <s:else>
									          <s:property value="voteoption" escapeHtml="false"/>
									       </s:else> 
									 </div>
									 <div class="sign" style="width: 170px;" id="resultbar">
						                <div style="width: 100px;margin-top: 0px;">
						               		 <span class="bar" title="(<s:property value="count"/>/<s:property value="#votekey.count"/>)"></span>
						                </div>
						                <div style="margin-left: 130px;"><span id="ct" style=""></span></div>
										<div style="width: 100px; margin-top: -24px;margin-left: 100px;">
										    <span title="colorbar" class="colors sgn_<s:property value='#status.index'/>">&nbsp;</span>
										</div>
									 </div>
									 <div style="margin-top: 12px;margin-left: 10px;">
										   <strong><s:property value="count"/>票</strong>
									 </div>
									  </div>
									 </td>
						            <s:if test="#status.count%4==0"><tr valign="top"></s:if>
				 			</s:iterator>
				 			 </tr>
				 			 </table>
				 			<div class="clear"></div>
			 		  </s:iterator>
				</div>
			</div>	
		<!-- end -->
		
		</div>
	</div>
</body>
</html>