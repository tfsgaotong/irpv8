<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
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
	<title>编辑投票</title>
	<link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common1.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/oacss.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css"/>
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
	
	input,select,textarea {
		border: 1px solid #CCCCCC;
	}
	
	.inputvote {
		height: 30px;
		width: 300px;
	}
	
	.optoninpt {
		height: 30px;
		width: 150px;
	}
	</style>
</head>

<script type="text/javascript">
var strRegex = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"   
    + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"   
    + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"   
    + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"   
    + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"   
    + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"   
    + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"   
    + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$"; 
var re = new RegExp(strRegex);
//再加一项
function addoption(){
	//获取li
	var index=$("#optionul li").length;
	$("#optionul").append("<li><span>"+(index+1)+"</span>.&nbsp;<input name=\"option\" type=\"text\" class=\"optoninpt\" required/>&nbsp;&nbsp;&nbsp;URL:&nbsp;&nbsp;<input name=\"urltext\" type=\"text\" class=\"optoninpt\"/></li>");
	$("#optionul li a").remove();
	$("#optionul li").append("<a onclick=\"deleteoption(this)\" > X</a>");
}
//删除选项
function deleteoption(value){
	//删除
	$(value).parent().remove();
	//判断
	var index=$("#optionul li").length;
	if(index<=2){
		$("#optionul li a").remove();
	}
	//排序
	for(var i=0;i<index;i++){
		$("#optionul li span:eq("+i+")").html(i+1);
	}
}
 $(function(){
	$("#starttime").datetimebox({editable:false});  
	$("#endtime").datetimebox({editable:false});  
	//引入扩展验证
	$.extend($.fn.validatebox.defaults.rules, {
	    EndTime:{
	    	validator:function(){
	    		try {
	    			if($('#endtime').datetimebox('getValue')>$('#starttime').datetimebox('getValue')){
		    			return true;
		    		}else{	  
		    			return false;
		    		}
				} catch (e) {
				}
	    	},
	    	message:'结束日期必须在开始日期之后'
		 }
	}); 
 });
 
 function checkchose(_value){
	var ischeck= $(_value).attr("checked");
	if(ischeck=="checked"){
		$(_value).attr("checked","checked");
	}else{
		$(_value).attr("checked",false);
	}
 }
 function checkoneormore(_value){
		if($("#"+_value).val()==2){
			if($("#optionul li").length<3){
				addoption();
			}
			$("#"+_value).next().show();
			$("#"+_value).next().next().find("input").val($("#optionul li").length);
			$("#"+_value).next().next().show();
		}else{
			$("#"+_value).next().hide();
			$("#"+_value).next().next().hide();
		}
	}
 //update
 function updatetitle(){
	var fag=false;
	var startd=$("#starttime").datetimebox('getValue');
	var endd=$("#endtime").datetimebox('getValue');
	var startDate =  new  Date(startd.replace(/-/g,"/"));
	var endDate =  new  Date(endd.replace(/-/g,"/"));  
	var todayTime= new Date(<%=new java.util.Date().getTime()%>); 
	var title=$.trim($("#title").val());
	var desc=$.trim($("#votedescription").val());
	if(title==""){
		fag=false;
		$.dialog.tips('投票标题或投票问题为空',1,'32X32/fail.png');
	}else if(title.length>100||title.length<2||desc.length>200){
		fag=false;
		$.dialog.tips('投票标题或说明长度不符合',1,'32X32/fail.png');
	}else{
		if($.trim(startd)==""||$.trim(endd)==""){
			fag=false;
			$.dialog.tips('日期不能为空',1,'32X32/fail.png');
		}else if(todayTime>startDate){
			fag=false;
			$.dialog.tips('开始不能小于今天',1,'32X32/fail.png');
		}else if(startd>endd){
			fag=false;
			$.dialog.tips('结束时间不能小于开始时间',1,'32X32/fail.png');
		}else{
			fag=true;
		}
	}
	if(fag){
		 var queryString = $('#titleform').serialize();
		 var voteid=$("#voteid").val();
		 $.ajax({
				type:'get',
				url:"<%=rootPath%>menu/vote_updatetitle.action?irptitle.voteid="+voteid+"&"+queryString,
				dataType: "json",
				async: false,
		   		cache: false,
		   		success:function(data){
		   		    $.dialog.tips(data,1.5,"32X32/succ.png");
		   		}
			});
	}
 }
 //取值
 function updateoptions(_index){
	var optionlength=$("#"+_index).children("div[id='rock']").length;
	var jsonoption="[";
	var fag=false;
	for(var i=0;i<optionlength;i++){
		var vid=$("#"+_index).children("div[id='rock']:eq("+i+")").find("input[name='voteoption']").attr("id");
		var value=$("#"+_index).children("div[id='rock']:eq("+i+")").find("input[name='voteoption']").val();
		var urlvalue=$("#"+_index).children("div[id='rock']:eq("+i+")").find("input[name='optionurl']").val();
		if($.trim(value)==null||$.trim(value)==""){
			$.dialog.tips('请填写选项',1,'32X32/fail.png');
			fag=false;
		}else if($.trim(value).length>70||$.trim(value).length<1){
			$.dialog.tips('选项长度符合',1,'32X32/fail.png');
			fag=false;
		}else{
			if($.trim(urlvalue)==null||$.trim(urlvalue)==""){
				urlvalue=null;
				if(i==0){
					jsonoption+="{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\"}";
				}else{
					jsonoption+=",{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\"}";
				}
				fag=true;
			}else{
				if(re.test($.trim(urlvalue))){
					if(i==0){
						jsonoption+="{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\"}";
					}else{
						jsonoption+=",{\"id\":\""+vid+"\",\"value\":\""+value+"\",\"urlvalue\":\""+urlvalue+"\"}";
					}
					fag=true;
				}else{
					fag=false;
					$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
				}	
			}
		}
	}
	jsonoption+="]";
	if(fag){
		 var queryString = $('#votetitleform'+_index).serialize();
		 $.ajax({
				type:'post',
				data:{'soptionstr':jsonoption},
				url:"<%=rootPath%>menu/vote_titleupdate.action?irpvotetitle.voteid="+_index+"&"+queryString,
				dataType: "json",
				data:{'soptionstr':jsonoption},
				async: false,
		   		cache: false,
		   		success:function(data){
		   		    $.dialog.tips(data,1.5,"32X32/succ.png");
		   		    refesh();
		   		}
			});
	}
 }
 //delete
 function deleteoptions(_index){
	 var len=$("#"+_index+"[type='text']").parent().parent().find("div[id='rock']").length;
	 var minsize=2;
	 if(len>minsize){
		 $.dialog.confirm('你确定要删除这个投票选项吗',function(){
			 $.ajax({
					type:'post',
					url:"<%=rootPath%>menu/voteoption_delete.action?voteid="+_index,
					dataType: "json",
					async: false,
			   		cache: false,
			   		success:function(data){
			   		    $.dialog.tips(data,1.5,"32X32/succ.png");
			   		   //刷新
			   		   $("#"+_index+"[type='text']").parent().remove();
			   		}
				});
		 });
	 }else{
		 $.dialog.tips("至少要有两个选项",1.5,"32X32/fail.png");
	 }
 }
 
 //添加选项
 function addoptions(_index){
	 var result="选项: <input id=\"addoption\" style=\"width: 200px;height: 40px;\" name=\"option\" type=\"text\"/><br/>&nbsp; URL:<input id=\"addurl\" style=\"width: 200px;height: 40px;\" name=\"urltext\" type=\"text\"/>";
	 var jsonoption="";
	 var urloption="";
	 $.dialog({
			title:'添加选项',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:200,
		    ok: function(){
		    	var optionurl=$("#addurl").val();
		    	var option=$("#addoption").val();
		    	var fag=false;
		    	if($.trim(option)==null||$.trim(option)==""){
					$.dialog.tips('请填写选项',1,'32X32/fail.png');
					fag=false;
				}else if($.trim(option).length>200||$.trim(option).length<1){
					$.dialog.tips('选项长度符合',1,'32X32/fail.png');
					fag=false;
				}else{
					if($.trim(optionurl)==""||$.trim(optionurl)==null){
						optionurl=null;
						jsonoption+=option;
						urloption+=optionurl;
						fag=true;
					}else{
						if(re.test($.trim(optionurl))){
							optionurl=null;
							jsonoption+=option;
							urloption+=optionurl;
							fag=true;
						}else{
							fag=false;
							$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
						}	
					}
				}
		    	if(fag){
		    		$.ajax({
			    		type:"post",
			    		dataType: "json",
			    		data:{'soptionstr':jsonoption,'urloption':urloption,'voteid':_index},
			    	    async: false,
			    	    cache: false,
			    		url:"<%=rootPath%>menu/option_add.action",
			    		success:function(data){
			    			$.dialog.tips(data,1,'32X32/succ.png');
			    		}
			    	});
			    	refesh();	
		    	}
		    	return fag;
		    },
		    okVal:'提交',
		    cancelVal: '关闭',
		    cancel: true,
		    padding: 0
		});
 }
 //添加分组
 function addGroup(){
	 var result = $.ajax({
			url: '<%=rootPath%>view/vote/vote_word_addgroup.jsp',
			dataType: "json",
		    async: false,
		    cache: false
		}).responseText;
	 $.dialog({
			title:'添加分组',
			content: result,
			max: false,
		    min: false, 
		    lock:true,
		    width:500,
		    height:240,
		    ok: function(){
		    	return submitaddGroup();
		    },
		    okVal:'提交',
		    cancelVal: '关闭',
		    cancel: true,
		    padding: 0
		});
 }
 //add
 function submitaddGroup(){
	    var queryString = $('#addgroupform').serialize();
		var opvalue=$("#optionul li input[name='option']");
		var jsonoption="";
		var urloption="";
		var fag=false;
		for(var i=0;i<opvalue.length;i++){
			var option=$("#optionul li:eq("+i+") input[name='option']").val();
			var optionurl=$("#optionul li:eq("+i+") input[name='urltext']").val();
	    	if($.trim(option)==null||$.trim(option)==""){
				$.dialog.tips('请填写选项',1,'32X32/fail.png');
				fag=false;
				break;
			}else if($.trim(option).length>200||$.trim(option).length<1){
				$.dialog.tips('选项长度应该在1到200之间',1,'32X32/fail.png');
				fag=false;
				break;
			}else{
				if($.trim(optionurl)==""||$.trim(optionurl)==null){
					optionurl=null;
					jsonoption =jsonoption+ option+",";
					urloption =urloption+ optionurl+",";
					fag=true;
				}else{
					if(re.test($.trim(optionurl))){
						jsonoption =jsonoption+ option+",";
						urloption =urloption+ optionurl+",";
						fag=true;
					}else{
						fag=false;
						$.dialog.tips('请输入有效网址',1,'32X32/fail.png');
					}	
				}
			}
		 }
		if(fag){
			var vid=$("#voteid").val();
			$.ajax({
				type:"post",
				dataType: "json",
				data:{'soptionstr':jsonoption,'urloption':urloption,'voteid':vid},
			    async: false,
			    cache: false,
				url:"<%=rootPath%>menu/save_votesecond.action?"+queryString,
				success:function(html){
					refesh();
					$.dialog.tips('添加投票分组成功',1,'32X32/succ.png');
				}
			});
		}
		return fag;
 }
 //del
 function voteDel(_index){
	var leng= $("body").find("div[class='clear']").length;
	 if(leng>1){
		 $.dialog.confirm('你确定要删除这个投票分组吗',function(){
			 $.ajax({
					type:'post',
					url:"<%=rootPath%>menu/votetitle_del.action?voteid="+_index,
					dataType: "json",
					async: false,
			   		cache: false,
			   		success:function(data){
			   		    $.dialog.tips(data,1.5,"32X32/succ.png");
			   		    //刷新
			   		    refesh();
			   		}
				});
		 });
	 }else{
		 $.dialog.tips("至少要有一个分组",1.5,"32X32/fail.png");
	 }
	
 }
//刷新
 function refesh(){
 	var vid=$("#voteid").val();
 	var urlvote="<%=rootPath%>menu/vote_updatevote.action?voteid="+vid;
  	 window.location.href=urlvote;
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
	    					setTimeout("window.close()", 1500);
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
			   		sendmic(context,rsmic);
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
<body onload="selected('votepage')">
	<s:hidden name="voteid" id="voteid" />
	<div class="bg01">
		<!--头部菜单-->
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> <nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x"> </nav> </section>
		<!--头部菜单end-->
		<div class="main-gr" style="background-image: none;min-height: 55vh;">
			<!--右侧内容结束  -->
			<div>
				<div class="touPiao">
					<form id="titleform">
						<div class="group_list">
							<h1>
								<input style="width: 610px;height: 40px;" id="title" name="irptitle.title" type="text" class="inputvote" value="<s:property value="irptitle.title" />" />
							</h1>
							<span>
								创建日期：
								<s:date name="irptitle.crtime" format="yyyy-MM-dd HH:mm" />
								&nbsp;&nbsp;&nbsp;&nbsp; 开始日期：
								<input id="starttime" name="irptitle.starttime" value="<s:date name="irptitle.starttime"  format="yyyy-MM-dd HH:mm"/>" style="width:150px" />
								结束日期：
								<input id="endtime" validType="EndTime" name="irptitle.endtime" value="<s:date name="irptitle.endtime"  format="yyyy-MM-dd HH:mm"/>" style="width:150px" />
							</span>
							<ul style="font-size: 14px;">
								<li>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<textarea id="votedescription" style="font-size: 12px;width: 860px;height: 80px;" name="irptitle.description" rows="2" cols="140"><s:property value="irptitle.description" /></textarea>
								</li>
							</ul>
							<div style="margin-left: 680px;">
								<input style="background-color: lightblue;" class="btn_touPiao" type="button" value="修改/保存" onclick="updatetitle()" />
								&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</form>
					<!--分组内容-->
					<s:iterator value="maplist" status="mapliststatus">
						<!-- 跌代第一个map -->
						<s:set var="votekey" value="key" />
						<form id="votetitleform<s:property value='#votekey.voteid'/>">
							<span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:property value="#mapliststatus.index+1" />
								.
								<input style="width: 500px;height: 35px;" type="text" name="irpvotetitle.votetitle" value="<s:property value='#votekey.votetitle'/>" />
								<s:if test="#votekey.checktype==1">允许其他评论</s:if>
								<s:else>
								     不允许其他评论
								 </s:else>
								<select name="irpvotetitle.checktype" onchange="checkoneormore(this.id)" id='select<s:property value="#mapliststatus.index+1"/>'>
									<option value='<s:property value="#votekey.checktype"/>'>请选择</option>
									<option value="1">允许其他评论</option>
									<option value="2">不允许其他评论</option>
								</select>
							</span>
						</form>
						<div id="<s:property value='#votekey.voteid'/>">
							<s:iterator value="value" status="status">
								<!-- 跌代map 中的 value 值是list  -->
								<s:iterator value="value[#status.index]">
									<!-- 跌代list 中的 map  -->
									<div id="rock" class="bt" style="width: 900px;height:30px; ">
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;观点
										<input style="width: 473px;height: 30px;" type="text" id="<s:property value='optionid'/>" name="voteoption" value=" <s:property value='voteoption'/>" />
									</div>
								</s:iterator>
							</s:iterator>
						</div>
						<div class="clear"></div>
						<div style="margin-left: 700px;">
							<s:if test="typeindex==5">
								<input style="background-color: lightblue;" class="btn_touPiao" onclick="updateoptions(<s:property value='#votekey.voteid'/>)" type="button" value="修改/保存" /> &nbsp;<a style="cursor: pointer;"
									href="javascript:void(0)" title="删除分组" onclick="voteDel(<s:property value='#votekey.voteid'/>)">删除</a>
							</s:if>
						</div>
					</s:iterator>
					<div style="margin-left: 300px;">
						<input class="btn_touPiao" onclick="publishvote()" type="button" value="发布" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="btn_touPiao" onclick="closewindow()" type="button" value="关闭" />
					</div>
				</div>
			</div>
		</div>
		<!-- end -->
		<s:include value="../../view/include/client_foot.jsp"></s:include>
	</div>
</body>
</html>