<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	<title>投票之<s:property value="irptitle.title" /></title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/vote.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/votet.css"/>
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
	
	.bg01 {
		background-color: white;
	}
	</style>
</head>

<script type="text/javascript">
 $(function(){
	 var starttime='<s:date name="irptitle.starttime"/>';
	 var endtime='<s:date name="irptitle.endtime"/>';
	 var msg="" ; 
	 var startDate =  new  Date(starttime.replace(/-/g,"/"));
	 var endDate =  new  Date(endtime.replace(/-/g,"/"));  
	 var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
	 if(todayTime>endDate){ 
	 	msg="<span style=\"color:red\">已经结束</span>";
	 	 $("body div[class='touPiao_btn']").hide();
	  }else if(todayTime<startDate){
	 	 msg="<span style=\"color:green\">敬请期待</span>"; 
	 	 $("body div[class='touPiao_btn']").hide();
	  }else{
		  msg="<span style=\"color:orange\">正在调查中</span>";
	  }
	 $('#voteprocess').html(msg);
	 checklook();
 });
 function checklook(){
	 var token = $("#token").val();
	 var voteid=$("#voteid").val();
	 $.ajax({
			type:"post",
			dataType: "json",
		    async: false,
		    cache: false,
			url:"<%=rootPath%>phone/vote_checkresultmore.action?voteid="+voteid+"&token="+token,
			success:function(date){
				if(date>4){
					var forora=$("#option"+date).attr("class");
					if(forora=="for"){
						$("#option"+date).find(".approveit").attr("class","deliver");
						$("#option"+date).parent().find(".against").find(".approveit").removeAttr("onclick");
						$("#option"+date).parent().find(".against").find(".approveit").attr("class","disabled");
					}else{
						$("#option"+date).find(".approveit").attr("class","deliver");
						$("#option"+date).parent().find(".for").find(".approveit").removeAttr("onclick");
						$("#option"+date).parent().find(".for").find(".approveit").attr("class","disabled");
						
					}
					 loadNum();
				}else if(date=="2"){
					loadNum();
				}else if(date=="3"){
					loadNum();
				}else if(date=="4"){
					 $("#showres").remove();
				}
			}
		});
 }
 
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
		 $.dialog.tips("您没选全或多选了",1.5,"32X32/fail.png");
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
 
 function sendmic(microblog_text,urltext){
	 var result=" <textarea rows=\"1.5\" cols=\"36\" style=\"width: 400px;height: 100px;\" id=\"votemictext\">"+microblog_text+"</textarea>";
	 $.dialog({
			title:'发投票微知',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    cancel:function(){ 
	   		},
		    cancelVal: '关闭',
		    ok: function(){
		    	if($.trim($("#votemictext").val())==""){
		    		$.dialog.tips('微知不能为空',1,'32X32/fail.png');
		    	}else{
		    		var microblog_type=0;
		    		var microblog_text= $("#votemictext").val()+urltext;
	    			var microbloggroup = "公开";
	    			$.ajax({
	    				type:"POST",
	    				url:'<%=rootPath%>microblog/microblogShare.action',
	    				cache:false,
	    				data:{
	    					publishInfo:microblog_text,
	    					microblogType:microblog_type,
	    					microbloggroup:microbloggroup
	    				},
	    				success:function(callback){
	    					if(callback!=null){
	    						$.dialog.tips('分享成功',1,'32X32/succ.png');
	    					}else{
	    						$.dialog.tips('分享失败',1,'32X32/fail.png'); 
	    					}
	    				}
	    			});
		    	}
		    },
		    okVal:'发投票微知',
		    padding: 0
		});
}

 
 function closewindow(){
	 $.dialog.confirm('你确定要离开这个页面吗',function(){
		 setTimeout("window.close()",0);
	 });
 }
 
 function toOtherpl(){
	   var token = $("#token").val();
	   var otherpl=$("#otherpl").val();
	   var microblog_type=6;
	   var voteid='<s:property value="voteid" />';
		$.ajax({
			type:"POST",
			url:'<%=rootPath%>phone/microblogSharepl.action'+'?token='+token,
			cache:false,
			data:{
				publishInfo:otherpl,
				microblogType:microblog_type,
				votegoupid:voteid
			},
			success:function(callback){
				if(callback!=null){
					$.dialog.tips('发布成功',1,'32X32/succ.png');
					var urlvote="<%=rootPath%>phone/vote_detil.action?voteid="+voteid+"&token="+token;
					window.location.href=urlvote;
				}else{
					$.dialog.tips('发布失败',1,'32X32/fail.png'); 
				}
			}
		});
}
 
 //赞成
 function approveitAtt(_att,_value,_title,_option,_optionid,_titleid){
		var token = $("#token").val();
		//获得内容
		var voteid='<s:property value="voteid"/>';
		var cla=$(_value).attr("class");
		if(cla=="deliver"){
			_att="-"+_att;
		}
		//不准继续点了
    	$(_value).attr("class","disabled");
		var result = $.ajax({
			url: '<%=rootPath%>phone/add_votetf.action?myagree='+_att+'&optionid='+_optionid+'&titleid='+voteid+'&token='+token,
			dataType: "json",
		    async: false,
		    cache: false,
		    type:'post',
		    data:{
	    					title:_title,
	    					option:_option
	    				}
		}).responseText;
		//初始化弹出框
		$.dialog({
			title:'发表观点',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    ok: function(){
		        var mic=$("#complaindesc").val();
		        var microblog_type=5;
	    		var microblog_text= mic;
	   			$.ajax({
	   				type:"POST",
	   				url:'<%=rootPath%>phone/microblogSharepl.action'+"?token="+token,
	   				cache:false,
	   				data:{
	   					publishInfo:microblog_text,
	   					microblogType:microblog_type,
	   					votegoupid:_optionid
	   				},
	   				success:function(callback){
	   					if(callback!=null){
	   						$.dialog.tips('发布成功',1,'32X32/succ.png');
	   						//刷新voteid
	   						var vtid='<s:property value="voteid"/>';
	   						var urlvote="<%=rootPath%>phone/vote_detil.action?voteid="+vtid+"&token="+token;
	   						window.location.href=urlvote;
	   					}else{
	   						$.dialog.tips('发布失败',1,'32X32/fail.png'); 
	   					}
	   				}
	   			});
		    },
		    okVal:'发布',
		    cancel: function(){
		    	var vtid='<s:property value="voteid"/>';
				var urlvote="<%=rootPath%>phone/vote_detil.action?voteid="+vtid+"&token="+token;
				window.location.href=urlvote;
		    },
		    padding: 0
		});
	
	
 }
 function loadNum(){
	 var barr= $(".for_num").attr("title");
	 var barb= $(".against_num").attr("title");
	 var withresr=eval(barr)*100;
	 var withresb=eval(barb)*100;
	 withresr+="%";
	 withresb+="%";
	 if(withresr=="NaN%"){
		 withresr="0%";
	 }
	 if(withresb=="NaN%"){
		 withresb="0%";
	 }
	 $(".for_num").attr("style","width:"+withresr);
	 $(".against_num").attr("style","width:"+withresb);
 }

//追加分页
function pageappend(){
	var voteid='<s:property value="voteid" />';
	var pagestart=parseInt(parseInt($("#pagestart").val())+1);
	var sUrl="<%=rootPath%>menu/find_tfatti.action";
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		dataType: 'json',
 		data:{'myagree':1,'voteid':voteid,'pagestart':pagestart},
		async: false,
	    cache: false  
 	}).responseText;
	var sHtmlConnb = $.ajax({ 
 		type:'post', 
 		url: "<%=rootPath%>menu/find_tfattib.action",
 		dataType: 'json',
 		data:{'myagree':2,'voteid':voteid,'pagestart':pagestart},
		async: false,
	    cache: false  
 	}).responseText;
	$('#redli').append(sHtmlConn);
	$('#blueli').append(sHtmlConnb);
	$("#pagestart").val(pagestart)
}
//分页
function page(_num){
	$('#find_different').empty();
	var voteid='<s:property value="voteid" />';
	var sUrl="<%=rootPath%>menu/find_different.action?pageNum="+_num+"&voteid="+voteid;
	var sHtmlConn = $.ajax({ 
 		type:'post', 
 		url: sUrl,
 		dataType: 'json',
		async: false,
	    cache: false  
 	}).responseText;
	$('#differentatt').html(sHtmlConn);
}
</script>
<body onload="selected('votepage')" style="background-color:#fff">
	<s:hidden name="voteid" id="voteid" />
	<s:hidden name="pagestart" id="pagestart" />
	<div class="bg01">

		<input type="hidden" value="<s:property value='tokens'/>" id="token" />
		<div class="main" style="width:100%;">
			<div class="main-gr left" style="background-image: none;border:none;width:100%;">
				<div class="touPiao">
					<div class="group_list">
						<h1>
							<s:property value="irptitle.title" />
						</h1>
						<span>
							开始日期：
							<s:date name="irptitle.starttime" format="yyyy-MM-dd HH:mm" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								结束日期：
								<s:date name="irptitle.endtime" format="yyyy-MM-dd HH:mm" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<span id="voteprocess"></span>
								<br />
								<span id="voteprocessend"></span>
							</span>
						</span>
						<s:if test="irptitle.description==null"></s:if>
						<s:else>
							<ul style="font-size: 14px;">
								<li>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="irptitle.description" />
								</li>
							</ul>
						</s:else>
					</div>
					<div style="margin-left: 20px;">
						<div id="pl_content_topicModule">
							<div>
								<div class="md_list_feed_pk W8_linecolor">
									<s:iterator value="maplist" status="mapliststatus">
										<div class="title_default_mod clearfix">
											<h2 class="title">
												<s:set var="votekey" value="key" />
												<i class="W8_imgicons icon_topic_3"></i> <b class="tit1"><s:property value="#votekey.votetitle" /></b>
											</h2>
										</div>
										<s:if test="#votekey.checktype==2"></s:if>
										<s:else>
											<div class="area">
												<div class="area_info clearfix">
													<span class="arrow"></span>
													<textarea id="otherpl" class="message_info W8_area" style="width:95%;height: 100px;"></textarea>
												</div>
												<div class="message_op" style="padding: 5px;margin-bottom:50px;">
													<div class="btn" style="float:right">
														<a class="W_btn_c" onclick="toOtherpl()">
															<span>发布观点</span>
														</a>
													</div>
												</div>
											</div>
										</s:else>
										<div class="update_pkwrap">
											<div class="update_pk clearfix">
												<s:iterator value="value" status="status">
													<s:if test="#status.count==1">
														<div id='option<s:property value="value[0].optionid"/>' class="for" style="width:40%;background:none;">
															<div class="info clearfix no_avatar" style="height: 93px;border-bottom:1px solid #D9D8D8">
																<div class="text">
																	<p>
																		<s:property value="value[0].voteoption" />
																	</p>
																</div>
																<div class="btn">
																	<a class="approveit"
																		onclick="approveitAtt(1,this,'<s:property value="#votekey.votetitle"/>','<s:property value="value[0].voteoption"/>',<s:property value="value[0].optionid"/>,<s:property value="#votekey.voteid"/>)">支持</a>
																</div>
															</div>
														</div>
													</s:if>
													<s:if test="#status.count==2">
														<div id='option<s:property value="value[1].optionid"/>' class="against" style="width:40%;background:none">
															<div class="info clearfix no_avatar" style="height: 93px;border-bottom:1px solid #D9D8D8">
																<div class="text">
																	<p>
																		<s:property value="value[1].voteoption" />
																	</p>
																</div>
																<div class="btn">
																	<a class="approveit"
																		onclick="approveitAtt(2,this,'<s:property value="#votekey.votetitle"/>','<s:property value="value[1].voteoption"/>',<s:property value="value[1].optionid"/>,<s:property value="#votekey.voteid"/>)">支持</a>
																</div>
															</div>
														</div>
													</s:if>
												</s:iterator>
											</div>
											<div id="showres" class="pk_progress_update clearfix">
												<div class="info" style="width:90%;">
													<span class="for_num" style="" title="(<s:property value="value[0].count"/>/<s:property value="#votekey.count"/>)">
														<s:property value="value[0].count" />
													</span>
													<span style="display:block;" class="against_num" style="" title="(<s:property value="value[1].count"/>/<s:property value="#votekey.count"/>)">
														<s:property value="value[1].count" />
													</span>
												</div>
											</div>
										</div>
									</s:iterator>

								</div>
							</div>
						</div>
						<s:if test="#votekey.checktype==2"></s:if>

						<s:if test="irptitle.ispublish==2">
						</s:if>

					</div>
				</div>
			</div>

		</div>
		<!--左侧内容结束-->
	</div>
</body>
</html>