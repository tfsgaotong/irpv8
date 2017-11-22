<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>投票结果</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
	<link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
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
	}
	</style>
</head>
<body onload="selected('votepage')" style="background: url();">
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

</script>
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
		   <section class="mainBox">
			<nav class="privateNav">
			</nav>
			</section>
		<!--头部菜单end-->
		<div class="main-gr" style="background-image: none;min-height: 55vh">
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
							<span>
								 &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#votekey.votetitle"/>&nbsp;&nbsp;&nbsp;&nbsp;
								 <s:if test="#votekey.checktype==1"></s:if><s:else></s:else>
							 </span>
							 <s:iterator value = "value" status="status">     <!-- 跌代map 中的 value 值是list  -->
								    <s:iterator value = "value[#status.index]">  <!-- 跌代list 中的 map  -->
								       <div id="rock" class="bt" style="width: 450px;margin-left: 20px;">
									       <s:if test="#votekey.checktype==1">
									            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="optionid<s:property value='#mapliststatus.index'/>" type="radio" value="<s:property value='optionid'/>" onclick="checkchose(this)"/>
									       </s:if>
									       <s:else>
									            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="optionid<s:property value='#mapliststatus.index'/>" type="checkbox" value="<s:property value='optionid'/>" onclick="checkchose(this)" />
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
								           <div class="sign" style="margin-left: 500px;margin-top: -20px;width: 220px;" id="resultbar">
								                <span class="bar" title="(<s:property value="count"/>/<s:property value="#votekey.count"/>)"></span>
												<div style="width: 100px;">
													<span title="colorbar" style="width:100px" class="colors sgn_<s:property value='#status.index'/>">&nbsp;</span>
												</div>
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="ct"></span>
												 <div style="float: right;">
													 &nbsp;&nbsp;&nbsp;<strong><s:property value="count"/>票</strong>
								        		 </div>
								           </div>
								       </div>
								   </s:iterator>
				 			 </s:iterator>
				 			 <div class="clear"></div>
						 </s:iterator>
				 </div>
			</div>
		</div>
		<!-- end -->
		<jsp:include page="../../view/include/client_foot.jsp" />
	</div>
</body>
</html>