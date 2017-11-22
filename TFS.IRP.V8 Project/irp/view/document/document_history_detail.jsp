<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title><s:property value="irpDocument.doctitle"/>-<s:property value="irpDocument.docversion" />.0版</title>
<link href="<%=rootPath%>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#partOfDoc *{
	font-size:16px;
	text-indent:1em;
}
#partOfDoc img{
	max-width:860px;
	autoimg:expression(onload=function(){
		this.style.width=(this.offsetWidth>860)?"860px":"auto"
	});
}

/*文章分页*/
#page_break .collapse{
	display: none;
}
#page_break .num {
	margin:10px auto;
	text-align:center;
	font-size:14px;
	color: #999;
}
#page_break .num a{
	font-size:14px;
	color: #999;
}
#page_break .num li a{
	font-size:14px;
	text-decoration:none;
}
#page_break .num li{
	text-indent:0px;
	display: inline-block;
	border: 1px solid #dbdbdb;
	background-color: #fff;
	color: #72BBE6;
	text-align: center;
	cursor: pointer;
	height: 20px;
    line-height: 20px;
    margin: 0 3px;
    padding: 0 7px;
}
#page_break .num li:hover{
	background:#5f9ddd;
	text-decoration:none;
}
#page_break .num li:hover a{
	color:#333;
}

#page_break .num li.on{
	background-color: #f0f0f0;
}
#page_break .num li.on a{
	color: #999;
} 
</style>

<script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript">
$(function(){
	initConnPage();
});

//初始化文章正文中的分页
function initConnPage(){
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
}

//显示全部正文数据
function showAllData(){
	$('.collapse').each(function(){
		$(this).show();
	});
	$('.num').hide();
}

/**
 * history version replay
 * 历史版本恢复
 */
function restoreHistory(_docid,_docvid){
	$.dialog.confirm("您确定要恢复此版本吗?",function(){
		$.ajax({
			type:'post',
			url:'<%=rootPath %>site/recoverhistory.action',
			data:{
				docidh:_docid,
				docvid:_docvid
			},
			async:false,
			cache:false,
			success:function(content){
				if(content==2){
					$.dialog.tips('恢复版本成功',1.5,'32X32/succ.png',function(){
						window.close();
					});
				}else{
					$.dialog.tips('恢复版本失败',1,'32X32/fail.png');
				}
				
			}
		});
	},function(){});
}
</script>
</head>

<body>
<s:include value="../include/client_head.jsp"></s:include>
<section class="mainBox">
	<nav class="privateNav">
    	<dl></dl>
    </nav>
</section>
<s:set var="cruser" value="getIrpUser(irpDocument.cruserid)" />
<section class="mainBox">
	<section class="details">
    	<article class="location">
   		<s:iterator value="rootChannels" status="index">
    		<s:if test="#index.first">
			<strong><a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a></strong>
			</s:if>
			<s:else>
			 > <a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
			</s:else>
		</s:iterator>
    	</article>
    	<a id="documenttop" name="documenttop"></a>
        <section class="text">
        	<article class="straight">
	       		<div id="printConn">
	            	<h1><s:property value="irpDocument.doctitle" /> [<s:property value="irpDocument.docversion" />.0版]</h1>
	                <table class="textInfo">
	                	<tr>
	                		<td>创建时间：<s:date name="new java.util.Date()" format="yyyy-MM-dd HH:mm" /></td>
	                		<td width="10"></td>
	                		<td>创建者：<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a></td>
	                	</tr>
	                </table>
	                <ul class="list4">
	                	<li>
	                		<section style="padding-left:0px;">
	                			<p style="font-size:14px;line-height:24px;color:#666;text-indent:0px;"><s:property value="irpDocument.docabstract" /></p>
	                		</section>
	                		<div class="clear"></div>
	                	</li>
	                </ul>
	                <div id="partOfDoc" class="documenttxt">
						<s:property value="irpDocument.dochtmlcon" escapeHtml="false" />
					</div>
				</div>
            </article>
            <div class="tags">
            	<font>标签：</font>
            	<s:iterator value="irpDocument.dockeywords.split(',')" status="st" var="as">
            	<s:if test="!#as.isEmpty()">
            	<a href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')"><s:property value="#as" /></a>&nbsp;&nbsp;
            	</s:if>
  				</s:iterator>
            </div>
            
            <div class="sociality">
            	<a href="javascript:void(0)" onclick="restoreHistory(<s:property value="irpDocument.knowledgeid"/>,<s:property value="irpDocument.docid"/>)"><span>恢复</span><label>恢复</label></a>
            </div>
        </section>
        <section id="commentView" class="discussions">
        	<div class="discussing">
        		<div class="sub" style="text-align: center;"><input type="button" onclick="window.close()" value="关闭"/></div>
        	</div>
        </section>
    </section>
    <section class="layoutRight">
    	<div class="area20"></div>
    	<div class="owner">
        	<dl class="info">
           	  	<dt class="leftBox">
           	  		<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><img src="<s:property value="#cruser.defaultUserPic" />" width="63" height="63" /></a>
				</dt>
                <dd class="leftBox">
                	<p><a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a></p>
                	<p>等级：<em><s:property value="getRanknameBySumscore(#cruser.sumscore)" /></em></p>
                	<p>经验：<em><s:property value="#cruser.sumexperience" /></em>&nbsp;&nbsp;&nbsp;&nbsp;积分：<em><s:property value="#cruser.sumscore" /></em></p>
               	</dd>
                <dd class="clear"></dd>
            </dl>
        </div>
    </section>
</section>
<s:include value="../include/client_foot.jsp"></s:include>
</body>
</html>
