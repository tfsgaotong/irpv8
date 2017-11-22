<%@page import="com.tfs.irp.util.LoginUtil"%>
<%@page import="com.tfs.irp.user.entity.IrpUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="irpDocument.doctitle" /></title>

<link rel="Bookmark" href="<%=rootPath %>client/images/24pinico.ico" />
<link rel="Shortcut Icon" href="<%=rootPath %>client/images/24pinico.ico" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>client/css/icon.css" rel="stylesheet" type="text/css" />
 
<jsp:include page="../include/client_skin.jsp" /> 
<style type="text/css">
.dttitle {
    font-family: "微软雅黑","黑体";
    font-size: 16px;
    line-height: 32px;
    margin-bottom: 17px;
}
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 50px;
}
.div dd{
    background-color: #F6F6F6;
    border: 1px solid #EDEDED;
    margin: 10px 0 0px;
    padding: 15px;
    position: relative;
    width: 92%;
} 
body{
	behavior:url(<%=rootPath%>client/js/hover.htc);
}
.STYLE1 {
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
.STYLE1 a:hover{
	color: #0000FF;
	font-weight: bold;
	font-size:18px;
}
/*文章分页*/
#page_break {

}
#page_break .collapse {
display: none;
}
#page_break .num {
	padding: 10px 0;
	text-align: center;
}
#page_break .num li{
	display: inline;
	margin: 0 2px;
	padding: 3px 5px;
	border: 1px solid #72BBE6;
	background-color: #fff;
	
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	font-family: Arial;
	font-size: 12px;
	overflow: hidden;
}
#page_break .num li.on{
	background-color: #72BBE6;
	color: #fff;
	font-weight: bold;
} 
</style>

<script type="text/javascript" src="<%=rootPath%>/client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/client/js/easyui-lang-zh_CN.js"></script>
</head>

<body> 
<div class="bg01">
<!--头部菜单-->
<s:include value="../include/client_head.jsp"></s:include>
<!--头部菜单end-->
<div class="main-gr">
<!--左侧内容-->
<div class="left" >
	<div class="leftxx"> 
		<dl id="personinfo">
			<h1>个人资料</h1>
			<img src="<s:property value='irpDocument.userPicUrl'/>"  width="50" height="50" />
			<h2>  
				<s:if test="irpDocument.createUser.nickname!=null"><s:property value="irpDocument.createUser.nickname"/></s:if>
				<s:elseif test="irpDocument.createUser.truename!=null"><s:property value="irpDocument.createUser.truename"/></s:elseif>
				<s:else><s:property value="irpDocument.createUser.username"/></s:else>
			</h2>
			<dl class="xinx2 xuxian_down">
				<dt>
					<!-- 如果他是自己，就没有关注这个显示了 --> 
		            <s:if test="loginUserId!=irpDocument.cruserid"> 
					<a onclick="messageContentView('<s:property value="irpDocument.cruser"/>',<s:property value="irpDocument.cruserid"/>)" href="javascript:void(0);" class="xinxibg">发私信</a>
					<!-- 调用静态方法，查看他是不是关注者， -->
						<s:if test="@com.tfs.irp.document.web.DocumentAction@findFocusByUserid(irpDocument.cruserid)!=0">
						<a id="focusa" onclick="cancelFocus(<s:property value="irpDocument.cruserid" />)"  href="javascript:void(0);" class="xinxibg">已关注</a>
						</s:if>
						<s:else>
						<a id="focusa" onclick="okFocus(<s:property value="irpDocument.cruserid" />)" href="javascript:void(0);" class="xinxibg">未关注</a>
						</s:else>  
					</s:if> 
		        </dt>
	        	<dd>
					知识等级：<font class="da"><s:property value="getRanknameBySumscore(getIrpUser(irpDocument.cruserid).sumscore)" /></font><br />
					知识积分：<font><s:property value="getIrpUser(irpDocument.cruserid).sumscore" /></font><br />
					知识访问量：<font> <s:property value="@com.tfs.irp.document.web.DocumentAction@countSumHit(irpDocument.cruserid)"/>  </font><br />
					关注人气：<font><s:property value="countMicroblogFocusFocusUserid(irpDocument.cruserid)" /></font>
        		</dd>
			</dl>
        	<%--相关知识 --%>
			<dl class="duo2" id="xiangguandocument"></dl> 
			<%--相关专题 --%>
        	<dl class="duo2" id="xiangguansubject"></dl>
        </dl>
	</div>
</div>
<!--左侧内容结束-->
<!--右侧内容-->
<div class="right">
	<table width="700 border="0" cellspacing="0" cellpadding="0" class="">
		<tr>
			<td width="700" align="center" class="box doctitle boxcenter dttitle "><s:property value="irpDocument.doctitle "/></td>
		</tr>
	</table> 
	<div class="gr">
		<h1>
			<p>
				<%--<a href="javascript:void(0);" onclick="showAllData()" id="show">查看全文</a> --%>
				字号：<a href="javascript:void(0);" class="f16" onclick="upxx(this.className)">大</a> 
				<a href="javascript:void(0);" class="f14" onclick="upxx(this.className)">中</a>
				<a href="javascript:void(0);" class="f12" onclick="upxx(this.className)">小</a>
			</p>
		<s:if test="irpDocument.isdraft!=0">
			<s:if test="   irpDocument.docstatus==@com.tfs.irp.document.entity.IrpDocument@PUBLISH_STATUS">
			 	<a class="linkc12" href="javascript:void(0);" onclick="window.open('<%=rootPath %>site/showdocumentinfotou.action?docid=<s:property value="irpDocument.docid"/>' );">（已投稿） </a>
			</s:if>
			<s:else><a class="linkc12" href="javascript:void(0);" style="cursor:default;">（已投稿）</a></s:else>
		</s:if>
			<em>(&nbsp;<s:date name="%{irpDocument.crtime}" format="yyyy-MM-dd HH:mm"/>&nbsp;)</em>&nbsp;&nbsp;&nbsp;
		</h1>
		<p style="float: left; width:100%; border-bottom:1px solid #e9e9e9;">
			标签：<a href="javascript:void(0);">   
					<s:iterator value="irpDocument.dockeywords.split(',')"  status="st" var="as">
						<a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;&nbsp;
	  				</s:iterator>
				</a>&nbsp; 
		</p>
	</div>
    <div class="zwen">    
        <div id="doc_content" style="margin-top:5px;" class="f14">
   			<s:property value="irpDocument.dochtmlcon" escapeHtml="false"/>
 		</div>  
 		<div  id="doc_attach" style="display: none;" class="f14">
   			<s:property value="irpDocument.attachedcontent" escapeHtml="false" />
   		</div>  
	<!--标签切换end-->
	</div> 
	<div class="fenxiang"> 
        <span>&nbsp;</span> 
        <s:if test="attacheds!=null && attacheds.size()!=0">
         <ul class="dtfj ">
                   <table>
         <s:iterator value="attacheds">
          <tr>
          <td>
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
         	  	 <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:25px; height:43px; background-position: -11px -10px;"></span> 
         	  </s:if>
         	  <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
         	  <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:25px; height:43px; background-position: -60px -10px;"></span> 
         	  </s:elseif>
         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -110px -10px;"></span> 
         	   </s:elseif>
         	    <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
         	    <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -160px -10px;"></span> 
         	    </s:elseif>
         	   <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid" >
         	       <span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:25px; height:43px; background-position: -210px -10px;"></span> 
         	  </s:elseif> 
          </td>
          <td style="padding-left: 20px;"><a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" ><s:property value="attdesc"/></a>
          	</td>
          <td style="padding-left: 20px;"> 
          <a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>" >下载</a>
                		&nbsp;&nbsp;
          <s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
	                 	|&nbsp;&nbsp; <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:if>
	                <s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
	               	|&nbsp;&nbsp; <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a></dd>
	               </s:elseif></td>
          </tr>
         </s:iterator>
          </table> 
          </ul>
        </s:if>   
        <div class="fenxiang2">
	         <s:if test="%{irpDocument.cruserid==loginUserId}">
		        	<span>
		        		<a target="_blank"  href="<%=rootPath %>site/client_toupdate_document.action?docid=<s:property value='irpDocument.docid'/>" class="linkc12" >
		        			修改
		        		</a>
		        	</span>
		        	<span>
		        		<a  href="javascript:void(0);"  onclick="clientdeletedocument(<s:property value='irpDocument.docid'/>)" class="linkc12" >
		        			删除
		        		</a>
		        	</span>
	        </s:if>
	        <span>
	        <s:if test="irpDocument.informtype>0">
	        	<a href="javascript:void(0);">已举报</a>
	       </s:if>
	       <s:else>
	      <a  href="javascript:void(0);"  onclick="clientjubaodocument(<s:property value='irpDocument.docid'/>)" class="linkc12" >
        			举报</a>
         </s:else>
		    </span>
		    <span><a href="javascript:void(0);" class="linkc12" onclick="catchknowto()">引用</a></span>
        </div>
      <%--
        <div class="fenxiang2">
        <span>  <a href="javascript:void(0)" class="linkc12" >阅读</a>
        ( 	<s:property value="@com.tfs.irp.document.web.DocumentAction@countReadByDocid(irpDocument.docid)"/>
        )</span>
        <span>
     	     <a href="javascript:void(0)" class="linkc12" >评论</a>
        	(<label id="recommendlabel"><s:property value="irpDocument.recommendcounts"/> </label>)</span>
        <span> 
       	   		 <s:if test="%{irpDocument.irpDocumentCollection!=null}">
	             <a href="javascript:void(0)" class="linkc12" id="focus" onclick="addfocusdoclink(<s:property value='irpDocument.docid'/>)">取消收藏</a>
	             </s:if><s:else>
	             	 <a href="javascript:void(0)" class="linkc12" id="focus" onclick="addfocusdoclink(<s:property value='irpDocument.docid'/>)">收藏</a> 
	            </s:else>
	     (<label id="doccollectcountlabel"><s:property value="irpDocument.collectioncount"/> </label>) 
        </span>
        <span>
        	  <a href="javascript:void(0)" class="linkc12" onclick="transdocument(<s:property value='irpDocument.docid'/>)">推荐</a>
        	  (<label id="translabel" style="color: rgb(135, 173, 88);"><s:property value="irpDocument.transcount"/> </label>)</span>
        <span><a href="javascript:void(0)" class="linkc12" >
      	  <s:if test="%{irpDocument.irpMostTreadDing!=null}">
	             <a href="javascript:void(0)" id="ding" class="linkc12"  >已顶</a>
	             </s:if><s:else>
	             	 <a href="javascript:void(0)"  id="ding"  class="linkc12"  onclick="ding(<s:property value='docid'/>)">顶</a> 
	            </s:else>
        </a>(<label id="dinglabel"> <s:property value="irpDocument.isvalue" /></label>)</span>
        <span><a href="javascript:void(0)" class="linkc12" >
        	     <s:if test="%{irpDocument.irpMostTreadCai!=null}">
	                   <a href="javascript:void(0)" id="cai"  class="linkc12" >已踩</a>
	             </s:if><s:else>
	             	 <a href="javascript:void(0)" id="cai"   class="linkc12" onclick="cai(<s:property value='docid'/>)">踩</a> 
	            </s:else>
        </a>(<label id="cailabel"><s:property value='irpDocument.novalue'/></label>)</span>
       
        <span>
        <a href="javascript:void(0)" class="linkc12" >打印</a> </span>
        </div>
        
         <div class="dtzjly mag12" id="speek"></div> 
        
        <div class="pinglun3">
         <br />
        	<li id="trueAmount" style="text-align: right; display: block;">
							您还可以输入
							<label id="maxAmount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;">
							<s:property value="maxAmount"/>
							 </label>
							个字</li> 
							
			<li id="falseAmount" style="text-align: right;display: none;">
							超出了
							<label id="errorAmount" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red; " ></label>个字</li>
							
        	<textarea name="textarea" cols="" rows="" id="recommend" onkeyup="countAmount(<s:property value='maxAmount'/>)"></textarea>
        	<p>
        	<input name="isMicro" id="isMicro" type="checkbox" value="1" />&nbsp; 
        	分享到微知
        	&nbsp;&nbsp; 
        	 <s:if test="%{irpDocument.irpDocumentCollection==null}">
       				<span id="divcoll"><input name="isCollectAdnMicro" id="isCollectAdnMicro" type="checkbox" value="1"/>
        			 &nbsp;评论并收藏</span> 
       	 	  </s:if>  
       	 	  </p>
        	<p style="width:100%;">
        	
        	<a href="javascript:void(0);" onclick="adddocrecommend(<s:property value='irpDocument.docid'/>,<s:property value='irpDocument.cruserid'/>)" id="submit" class="newabtngrn" style=" margin:0 auto;">
        		发评论
        	</a></p> 
       	</div>
       --%>
    </div> 
</div> 
<!--右侧内容结束-->
<!--尾部信息-->
<s:include value="../include/client_foot.jsp"></s:include>
<!--尾部信息end-->
</div> 
</div>
</body>
<script type="text/javascript">
$(function(){
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text());
		var _num=parseInt(0);
		 if(_text=="上一页"){
			 //获取当前选中的li ,然后将它小于1的点击一下，如果当前选中的是1就不执行
			 $('#page_break').find('li').each(function(){ 
					if(this.className=="on"){
						var _id=this.id;
						if(_id==1)return;
						_num=parseInt(_id)-1;
					}	 
		      });  
		 }else if(_text=="下一页"){
			 var len=$('#page_break').find('li').length;
		    $('#page_break').find('li').each(function(){ 
		    	if(this.className=="on"){
					var _id=this.id;
					if(_id==len)return;
					 _num=parseInt(_id)+1;
				}	 
	        });  
		 }
		 $("#page_break").find('#'+_num).click();return ;
	});
	$('#page_break .num li').click(function(){
		//隐藏所有页内容
		$("#page_break div[id='page_1']").hide(); 
		//显示当前页内容。 
		  var li=$('#page_break').find('li');
	      var len= li.length ;
		if ($(this).hasClass('on')) { 
			$('#page_break #page_' + $(this).text()).show();
		} else {
			$('#page_break .num li').removeClass('on');
			$(this).addClass('on');
			$('#page_break #page_' + $(this).text()).fadeIn('normal');
			for(i=1;i<=len;i++){
				if(parseInt($(this).text())==i){}else{
					$("#page_break div[id='page_"+i+"']").hide(); 
				}
			}
		}
	});
	xiangguandocument(<s:property value='irpDocument.docid'/>);
	xiangguansubject(<s:property value='irpDocument.docid'/>);
	// findmydocrecommend(<s:property value='irpDocument.docid'/>,<s:property value='irpDocument.cruserid'/>);
});

//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}

/**
 * 引用到专题
 */
var subdialong ;
function catchknowto(){
	var idi = <s:property value="irpDocument.docid" />;
	var contentresult = $.ajax({
		url:"<%=rootPath%>site/catchknowto.action?irpDocument.docid="+<s:property value='irpDocument.docid'/>,
		async: false,
	    cache: false
	}).responseText;
	subdialong = $.dialog({
		id:'subjectdia',
		title:'我的专题',
		max:false,
		min:false,
		lock:true,
		width:'350px',
		resize: false,
		content:contentresult,
		okVal:'引用',
		ok:function(){
			$('#addsubform').form('submit',{
				url:"<%=rootPath%>site/addqiyesub.action?irpDocument.docid="+<s:property value='irpDocument.docid' />,
				data:{"irpDocument.docid":idi},
				async: false,
			    cache: false,
			    success:function(data){
			    	if(data==1){
			    		$.dialog.tips('引用成功',1,'32X32/succ.png');		
			    	}else{
			    		$.dialog.tips('引用失败',1,'32X32/fail.png');	
			    	}
			    }
			});
		},
		cancelVal:"取消",
		cancel:function(){
			
		}
	});
}

//删除知识
function clientdeletedocument(_docid){ 
	$.dialog.confirm('您确定要删除这个知识吗？',function(){
		$.ajax({
				type:'post',
				url:'<%=rootPath%>site/clientdeletedocument.action',
				data:{'docid':_docid},
				success: function(msg){
					if(msg=="1"){ //删除成功 
					 window.close();
					} 
				}
    	 });
	},function(){}); 
}	
 
function showAllData(){
	var _show=$.trim($('#show').html());
	if(_show=="查看全文"){
		$('#doc_attach').show();
		$('#doc_content').hide();
		$('#show').html('取消查看全文');
	}
	/*
	else{
		$('#doc_content').show();
		$('#doc_attach').hide();
		$('#show').html('查看全文');
	}
	*/
}
function upxx(cls){
	document.getElementById("doc_attach").className=cls;
	document.getElementById("doc_content").className=cls;
	//
	//显示当前页内容。 
     var li=$('#page_break').find('li');
	if(li){
		   var len= li.length ;
			for(i=1;i<=len;i++){
			// $("#page_break div[id='page_"+i+"']").hide(); 
				document.getElementById("page_"+i).className=cls;
			}
	}
}
function cancelFocus(_userid){ 
  if($.trim($('#focusa').text())=='未关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$('#focusa').text("已关注");  
				}
			}
		});
	}else{
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				 	$('#focusa').text("未关注");  
				}
			}
		}); 
	} 
}
//增加关注
function okFocus(_userid){ 
    if($.trim($('#focusa').text())=='未关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$('#focusa').text("已关注");  
				}
			}
		});
	}else{
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
					$('#focusa').text("未关注");  
				}
			}
		});
		
	}	
}

function countAmount(maxLength){
	var val=$('#recommend').val(); 
	var len=val.length;
	var errorLength=$('#maxAmount').html(); 
	 if(maxLength-parseInt(len)>=0){
	 		$('#trueAmount').css({display:'block'});
			$('#falseAmount').css({display:'none'}); 
			$('#maxAmount').text(maxLength-parseInt(len)); 	
		}else{
			$('#trueAmount').css({display:'none'});  
			$('#falseAmount').css({display:'block'});  
			$('#errorAmount').text(parseInt(len)-maxLength);
		}
}
// 相关知识
function xiangguandocument(_docid){
	var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/xiangguandocument.action',
			dataType: "json",
			data:{'docid':_docid},
			async: false,
	   		cache: false  
			}).responseText;
			$('#xiangguandocument').html(str);
}
//相关专题
function xiangguansubject(_docid){
	var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/xiangguansubject.action',
			data:{'docid':_docid},
			dataType: "json",
			async: false,
	   		cache: false  
			}).responseText;
			$('#xiangguansubject').html(str);
}
//对某个文档发出评论
function adddocrecommend(_docid,_cruserid){ 
	$.dialog.confirm('您确定要评论这个知识吗？',function(){
			var val=$('#recommend').val();
			var len=val.length;
			var maxLength='<s:property value="maxAmount"/>'; 
			if(parseInt(maxLength)-parseInt(len)<0){
				return ;
			}
			var value=$('#recommend').val();  
			//拿到回复的回复id 
			//拿到两个复选框的值 
			var replayUserId=$('#replayUserId').val();
			var isCollectAdnMicro=0; 
		  		$('input[name="isCollectAdnMicro"]:checked').each(
		  			function(){
		  				isCollectAdnMicro=$(this).val();  
		  			}
		  		);
		  		var isMicro=0;
		  		$('input[name="isMicro"]:checked').each(
		  			function(){
		  				isMicro=$(this).val(); 
		  			}
		  		); 
			if(value.length>0){
				$.ajax({
					type:'post',
					url:'<%=rootPath%>site/addrecommend.action',
					data:{'docid':_docid, 
						'recommend':value,
						'isCollectAdnMicro': isCollectAdnMicro,
						'isMicro': isMicro,
						'replayUserId': replayUserId
					 },
					success:function(msg){   //msg为返回的那条评论 
						   if(replayUserId==''){
						       $('#recommendlabel').text(parseInt($('#recommendlabel').text())+1); 
						   }  
						   if(isCollectAdnMicro==1){
							   $('#doccollectcountlabel').text(parseInt($('#doccollectcountlabel').text())+1); 	
							   $('#focus').html('取消收藏');	
							    //隐藏div
						       $('#divcoll').css({ display:"none"});  
						   } 
						   $('#recommend').val(''); 
						   $('#replayUserId').val(''); 
						   findmydocrecommend(_docid,_cruserid);//调用，刷新
						  countAmount(maxLength);
					}
				});
			} else{
				$.dialog.alert('评论不能为空',function(){
					$('#recommend'+_docid).focus();   
				});
			} 
	},function(){}); 
}
//顶
function ding(_docid){
	var tValue=$('#ding').text();
		if(tValue=="顶"){
		$.ajax({
				type:'post',
				url:'<%=rootPath%>site/dingdocument.action',
				data:{'docid':_docid},
				dataType:'json',
				async:false,
				cache:false,
				success:function(msg){ 
				if(msg=="1"){
					$('#dinglabel').text(parseInt($('#dinglabel').text()+1));
						$('#ding').html('已顶');
					} 
				}  	
		 });
	}else{
		$.dialog.alert("已顶",function(){});
	} 
}
//踩
function cai(_docid){
	var tValue=$('#cai').text();
		if(tValue=="踩"){
		$.ajax({
				type:'post',
				url:'<%=rootPath%>site/caidocument.action',
				data:{'docid':_docid},
				dataType:'json',
				async:false,
				cache:false,
				success:function(msg){ 
					if(msg=="1"){
					$('#cailabel').text(parseInt($('#cailabel').text()+1));
						$('#cai').html('已踩');
					} 
				} 
		 });
	}else{
		$.dialog.alert("已踩",function(){});
	} 
}

//查看这个文档的所有评论
function findmydocrecommend(_docid,_cruserid){
	
	//将当前文档的评论显示出来   
	  var str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/findmydocrecommend.action',
			data:{'docid':_docid,'createuserid':_cruserid},
			dataType: "json",
			async: false,
		    cache: false 
			}).responseText;   
	    $('#speek').html(str); 
	    $('#speekdiv').css({ display:"block"}); 
			 
}
	
//对文档进行关注以及取消关注
function addfocusdoclink(_documentid){ 
	var tValue=$('#focus').text();
	if(tValue=="收藏"){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/addfocusdoclink.action',
			data:{'documentid': _documentid},
			success:function(msg){
				if(msg=="1"){   
					 $('#doccollectcountlabel').text(parseInt($('#doccollectcountlabel').text())+1); 	
					   $('#focus').html('取消收藏');	
					    //隐藏div
				       $('#divcoll').css({ display:'none'});  
				} 
			}
		}); 
	}else{
			$.ajax({
			type:'post',
			url:'<%=rootPath%>site/deletefocusdoclink.action',
			data:{'documentid': _documentid},
			success:function(msg){
				if(msg=="1"){  
				    $('#doccollectcountlabel').text(parseInt($('#doccollectcountlabel').text())-1); 	
					$('#focus').html('收藏');
				    $('#divcoll').css({ display:'block'});  
				} 
			}
		}); 
	} 
} 
//转发某个文档给某些人
var _focusperson=null;
function transdocument(_docid){ 
	//弹出框，显示所有人。。。然后选择 
		var result = $.ajax({
			url: '<%=rootPath%>site/findallmicroblogforcusjson.action',
			type:"post",
			dataType: "json", 
		    async: false 
		}).responseText; 
		$.dialog({
			title:'推荐',
			max:false,
			min:false,
			lock:true, 
			resize: false,
			width:'500px',
			height:'200px',
			content:result,
			cancelVal:'关闭',
			okVal:'推荐',
			cancel:function(){ 
			},
			ok:function(){ 
				$('#transdocument').find('#docid').val(_docid);
				$('#transdocument').form('submit',{
					url:'<%=rootPath%>site/addtransmite.action',
					success:function(callback){ 
						var num=parseInt(callback) ;
						if(num>0){
							$.dialog.tips('推荐成功',1,'32X32/succ.png',function(){ 
								//将转发的里面数字加 
								 $('#translabel'+_docid).text(parseInt($('#translabel'+_docid).text())+num); 	
							});						
						}else{
							$.dialog.tips('推荐失败',1,'32X32/fail.png');	
						}
					},
					error:function(){
						$.dialog.tips('推荐失败',1,'32X32/fail.png');
					}
				});
			}
		}); 
	} 
//选择人或者组织
function checkuserorgroup(){
	var str=$.ajax({
		url: '<%=rootPath%>site/to_check_group_or_user.action', 
		type:"post", 
	    async: false,
	    cache: false
	}).responseText;   
	 $.dialog({
		 	title:'推荐',
			max:false,
			min:false,
			lock:true,
			resize: false, 
			init : function(){
				$('#tab').tabs({ 
					  border:false,   
  					  onSelect:function(title,index){ 
  					  		tabs(); 
  				      }   
				});
			},
			content:str,
			cancelVal:'关闭',
			okVal:'确定',
			cancel:function(){ 
			},
			ok:function(){  
		    	   var userIds= $('#transdocument').find('#userIds').val();
		        	var userNames= $('#transdocument').find('#userNames').val();
		  	 		$('#transdocument').find('#userdiv').html(userNames);
		  	 		$('#transdocument').find('#userIds').val(userIds);
		    	   var groupIds=$('#transdocument').find('#groupIds').val();
		  	 	   var groupNames= $('#transdocument').find('#groupNames').val();
		  	 	   $('#transdocument').find('#groupdiv').html(groupNames);
		  	 	   $('#transdocument').find('#groupIds').val(groupIds);
				}
	});  
}
//回复的回复操作
function appendReplayUsername(replayUserId,_replyUsername,_replayUserid){
	var str="回复 "+_replyUsername+" :"; 
	$('#replayUserId').val(replayUserId);
	$('#recommend').val(str);
} 
/*构建私信框*/
function messageContentView(_messageuser,_messageid){
	//构建页面结果 
	var result = $.ajax({
		url: '<%=rootPath%>microblog/messageContentPage.action',
		type:"post",
		dataType: "json",
	    data: {
	    	messageuser:_messageuser,
	    	messageid:_messageid
	    },
	    async: false
	}).responseText; 
	$.dialog({
		title:'发私信',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:'450px',
		height:'180px',
		content:result,
		cancelVal:'关闭',
		okVal:'发送',
		cancel:function(){ 
		},
		ok:function(){
			$('#messageContentForm').form('submit',{
				url:'<%=rootPath%>microblog/sendMessageContent.action',
				cache:false,
				success:function(msg){
					if(msg==0){
						$.dialog.tips('发送成功',1,'32X32/succ.png');		
					}else if(msg==1){	
						$.dialog.tips('部分发送成功,另一部分由于用户的设置未能发送成功',2,'32X32/hits.png');	
					}else{
						$.dialog.tips('发送失败,由于用户的设置',2,'32X32/fail.png');	
					}	
				}
			});
		}
	}); 
} 
//举报
function clientjubaodocument(_docid){
	 var loadPage=$.ajax({
					url: '<%=rootPath%>microblog/documentinformrame.action', 
					data:{
						microblogid:_docid
					},
					type:"post", 
				    async: false,
				    cache: false
				}).responseText; 
				$.dialog({
				title:'举报',
				max:false,
				min:false,
				lock:true,
				resize: false,
				width:500,
				height:200,
				content:loadPage,
				okVal:'举报',
				ok:function(){
					var informkeykind=$("input[name=informkeykind]:checked").val();
					if(informkeykind==undefined){
						$.dialog.tips("请选择举报类型",1.5,"alert.gif");
						return false;
					}	
					var informdescs = $("#informdescform").val();
					 if($.trim(informdescs).length>$('#informdesccount').val()){
					    	return false;
					    }else{
					    	$('#informform').form('submit',{
									url:'<%=rootPath%>microblog/savedocuentinformdesc.action',
									cache:false,
									success:function(msg){
										if(msg==1){
											
											$.dialog.tips('举报成功',1,'32X32/succ.png');					
										}else{	
											$.dialog.tips('举报失败',1,'32X32/fail.png');	
										}	
									}
								});	
					    
					    }
				},
				cancelVal:"关闭",
				cancel:function(){
					
				}
			}); 
}
</script>
</html>
    