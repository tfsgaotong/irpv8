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
	<title>结果</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css" />
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
	 loadNum();
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
 
 //赞成
 function approveitAtt(_att,_value,_title,_option,_optionid,_titleid){
		//获得内容
		var voteid='<s:property value="voteid"/>';
		var result = $.ajax({
			url: '<%=rootPath%>menu/add_votetf.action?myagree='+_att+'&optionid='+_optionid+'&titleid='+voteid,
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
	   			var microbloggroup = "公开";
	   			$.ajax({
	   				type:"POST",
	   				url:'<%=rootPath%>microblog/microblogShare.action',
	   				cache:false,
	   				data:{
	   					publishInfo:microblog_text,
	   					microblogType:microblog_type,
	   					votegoupid:_optionid
	   				},
	   				success:function(callback){
	   					if(callback!=null){
	   						$.dialog.tips('发布成功',1,'32X32/succ.png');
	   						hitMicroblogTab();
	   					}else{
	   						$.dialog.tips('发布失败',1,'32X32/fail.png'); 
	   					}
	   				}
	   			});
		    },
		    okVal:'发布',
		    cancel: true,
		    padding: 0
		});
	$(_value).attr("class","deliver");
	
	
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
</script>
<body onload="selected('votepage')">
	<s:hidden name="voteid" id="voteid" />
	<div class="bg01">
		<!--头部菜单-->
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> <nav class="privateNav"> </nav> </section>
		<!--头部菜单end-->
		<div class="main">
			<div class="main-gr left" style="background-image: none;">
				<div class="touPiao" style="background-color: white;">
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
													<textarea id="otherpl" class="message_info W8_area" style="width: 550px;height: 50px;"></textarea>
												</div>
												<div class="message_op" style="padding: 5px;">
													<div class="btn" style="margin-left: 440px;">
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
														<div id='option<s:property value="value[0].optionid"/>' class="for">
															<div class="info clearfix no_avatar" style="height: 93px;">
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
														<div id='option<s:property value="value[1].optionid"/>' class="against">
															<div class="info clearfix no_avatar" style="height: 93px;">
																<div class="text">
																	<p>
																		<s:property value="value[1].voteoption" />
																	</p>
																</div>
																<div class="btn">
																	<a class="approveit"
																		onclick="approveitAtt(2,this,'<s:property value="#votekey.votetitle"/>','<s:property value="value[0].voteoption"/>',<s:property value="value[1].optionid"/>,<s:property value="#votekey.voteid"/>)">支持</a>
																</div>
															</div>
														</div>
													</s:if>
												</s:iterator>
											</div>
											<div id="showres" class="pk_progress_update clearfix">
												<div class="for"></div>
												<div class="info">
													<span class="for_num" style="" title="(<s:property value="value[0].count"/>/<s:property value="#votekey.count"/>)">
														<s:property value="value[0].count" />
													</span>
													<span class="against_num" style="" title="(<s:property value="value[1].count"/>/<s:property value="#votekey.count"/>)">
														<s:property value="value[1].count" />
													</span>
												</div>
												<div class="against"></div>
											</div>
										</div>
									</s:iterator>
									<div class="list_feed_box">
										<div class="list_feed_pk_box clearfix">
											<div class="list_feed_pk_f list_feed_pk_fl">
												<div class="pk_title_new">
													<h2>
														共有
														<s:property value="datacountr" />
														红方意见
													</h2>
													<div class="arrow_d">
														<p class="inner">
															<i class="W8_fonticons W8_linecolor_fc">s</i> <i class="W8_fonticons W8_bgcolorb_fc">s</i>
														</p>
													</div>
												</div>
												<ul class="list_feed list_feed_pk W8_linecolor">
													<li id="redli" class="list_feed_li W8_linecolor clearfix">
														<s:iterator value="irpMicroblogr">
															<div class="main_con clearfix">
																<div class="face">
																	<dl id="<s:property value='microblogid'/>div">
																		<s:if test="findUserByUserId(userid).userpic!=null">
																			<dt>
																				<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																					<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />" alt="用户头像" width="48px"
																						onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																				</a>
																			</dt>
																		</s:if>
																		<s:if test="findUserByUserId(userid).userpic==null">
																			<dt>
																				<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																					<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if> <s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像"
																						width="48px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																				</a>
																			</dt>

																		</s:if>
																	</dl>
																</div>
																<div class="content">
																	<p class="con_txt">
																		<s:property value="getShowPageViewNameByUserId(userid)" />
																		： <em> <s:property value="microblogcontent" escapeHtml="false" />
																		</em>
																	</p>
																	<div class="con_opt clearfix">
																		<p class="fl W_linkb">
																			<span class="time W_textc">
																				<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
																			</span>
																		</p>
																	</div>
																</div>
															</div>
														</s:iterator>
													</li>
												</ul>
											</div>
											<div class="list_feed_pk_f list_feed_pk_fr">
												<div class="pk_title_new">
													<h2>
														共有
														<s:property value="datacountb" />
														蓝方意见
													</h2>
													<div class="arrow_d">
														<p class="inner">
															<i class="W8_fonticons W8_linecolor_fc">s</i> <i class="W8_fonticons W8_bgcolorb_fc">s</i>
														</p>
													</div>
												</div>
												<ul class="list_feed list_feed_pk W8_linecolor">
													<li id="blueli" class="list_feed_li W8_linecolor clearfix">
														<s:iterator value="irpMicroblogb">
															<div class="main_con clearfix">
																<div class="face">
																	<dl id="<s:property value='microblogid'/>div">
																		<s:if test="findUserByUserId(userid).userpic!=null">
																			<dt>
																				<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																					<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />" alt="用户头像" width="48px"
																						onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																				</a>
																			</dt>
																		</s:if>
																		<s:if test="findUserByUserId(userid).userpic==null">
																			<dt>
																				<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																					<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if> <s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像"
																						width="48px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																				</a>
																			</dt>
																		</s:if>
																	</dl>
																</div>
																<div class="content">
																	<p class="con_txt">
																		<s:property value="getShowPageViewNameByUserId(userid)" />
																		： <em> <s:property value="microblogcontent" escapeHtml="false" />
																		</em>
																	</p>
																	<div class="con_opt clearfix">
																		<p class="fl W_linkb">
																			<span class="time W_textc">
																				<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
																			</span>
																		</p>
																	</div>
																</div>
															</div>
														</s:iterator>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="margin-left: 290px;">
							<a href="javascript:void(0)" onclick="pageappend()">更多</a>
						</div>
						<s:if test="#votekey.checktype==2"></s:if>
						<s:else>
							<div>
								<div class="title_default_mod title_default_mod_s clearfix">
									<h2 class="title">
										<i class="W8_imgicons ico_title_compere"></i> <b class="tit1">个性观点</b>
									</h2>
								</div>
								<div>
									<ul class="list_feed list_feed_pk W8_linecolor">
										<li id="blueli" class="list_feed_li W8_linecolor clearfix">
											<div class="main_con clearfix" id="differentatt">
												<s:if test="irpMicroblogs.size>0">
													<s:iterator value="irpMicroblogs">
														<div>
															<div class="face">
																<dl id="<s:property value='microblogid'/>div">
																	<s:if test="findUserByUserId(userid).userpic!=null">
																		<dt>
																			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																				<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(userid).userpic' />" alt="用户头像" width="48px"
																					onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																			</a>
																		</dt>
																	</s:if>
																	<s:if test="findUserByUserId(userid).userpic==null">
																		<dt>
																			<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> ">
																				<img <s:if test="findUserByUserId(userid).sex==2">src="<%=rootPath%>view/images/female.jpg"</s:if> <s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像"
																					width="48px" onmouseover="personalCard(<s:property value='userid' />,<s:property value='microblogid' />)" onmouseout="personalCardOut(<s:property value='microblogid' />)" />
																			</a>
																		</dt>
																	</s:if>
																</dl>
															</div>
															<div class="content" style="width: 550px;padding-bottom: 5px;">
																<p class="con_txt">
																	<s:property value="getShowPageViewNameByUserId(userid)" />
																	：<em><s:property value="microblogcontent" escapeHtml="false" /></em>
																</p>
																<div class="con_opt clearfix">
																	<p class="fl W_linkb">
																		<span class="time W_textc">
																			<s:date name="crtime" format="yyyy-MM-dd HH:mm" />
																		</span>
																	</p>
																</div>
															</div>
														</div>
													</s:iterator>
													<div>
														<ul>
															<s:property value="pageHTML" escapeHtml="false" />
														</ul>
													</div>
												</s:if>
												<s:else>
										暂时没有其他观点
										</s:else>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</s:else>
						<s:if test="irptitle.ispublish==2">
						</s:if>
						<s:else>
							<s:if test="@com.tfs.irp.util.LoginUtil@getLoginUserId()==irptitle.cruserid">
								<div style="margin-left: 300px;">
									<input class="btn_touPiao" onclick="publishvote()" type="button" value="发布" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="btn_touPiao" onclick="closewindow()" type="button" value="关闭" />
								</div>
							</s:if>
						</s:else>
					</div>
				</div>
			</div>
			<div class="right">
				<div class="duo">
					<%--热门投票 --%>
					<dl id="hotvote"></dl>

				</div>
			</div>
			<jsp:include page="../../view/include/client_foot.jsp" />
		</div>
		<!--左侧内容结束-->
	</div>
</body>
</html>