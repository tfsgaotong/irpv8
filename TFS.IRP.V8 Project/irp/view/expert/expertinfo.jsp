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
<title>专家</title>
<link rel="shortcut icon" href="<%=rootPath%>favicon.ico"
	type="image/x-icon" />
<link rel="bookmark" href="<%=rootPath%>favicon.ico" />
<link href="<%=rootPath%>client/images/normalize.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>client/css/common1.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
	<%-- <link href="<%=rootPath%>view/css/common.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="<%=rootPath%>view/css/expertinfo.css" rel="stylesheet"type="text/css" />
<link href="<%=rootPath%>client/css/oacss.css" rel="stylesheet"type="text/css" />

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>

<script type="text/javascript" src="<%=rootPath %>view/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<jsp:include page="../include/client_skin.jsp" />
<style type="text/css">
.inputButton{
    border-radius: 3px;
    border: none;
    background: #5f9ddd;
    overflow: hidden;
    font-size: 15px;
    color: #fff;
    padding: 9px 20px;
    font-weight:normal;
    margin-left: 10px;

}


</style>
</head>
<script>
$(function(){ 
	myquestion();
	expert_relationship();
	//查看详情资料
	var eleBtn = document.getElementById("show"),
	eleMore = document.getElementById("detailed");

	var height = 0, display = false, timer;
	    
	var step = function() {
	    height = display? (height + 20): (height - 20);
	    if (height < 0) {
	        height = 0;    
	    } else if (height > 100) {
	        height = 100;    
	    }
	    eleMore.style.height = height + "px";
	    if (height > 0 && height < 100) {
	        timer = setTimeout(step, 30);
	    }
	};
	
	eleBtn.onclick = function() {
	    if (timer) clearTimeout(timer)
	    display = !display;
	    if(display){
	    	$("#show").html('<svg viewBox="0 0 10 6" width="10" height="16" aria-hidden="true" style="transform: rotate(180deg);fill:#BFBFBF;margin-right:10px;height: 16px; width: 10px;"><title></title><g><path d="M8.716.217L5.002 4 1.285.218C.99-.072.514-.072.22.218c-.294.29-.294.76 0 1.052l4.25 4.512c.292.29.77.29 1.063 0L9.78 1.27c.293-.29.293-.76 0-1.052-.295-.29-.77-.29-1.063 0z"></path></g></svg><!-- react-text: 2159 -->收起详细资料<!-- /react-text -->');
	    }else{
	    	$("#show").html('<svg viewBox="0 0 10 6" style="fill:#BFBFBF;margin-right:10px;height:16px;width:10px;" width="10" height="16" aria-hidden="true" data-reactid="103"><title data-reactid="104"></title><g data-reactid="105"><path d="M8.716.217L5.002 4 1.285.218C.99-.072.514-.072.22.218c-.294.29-.294.76 0 1.052l4.25 4.512c.292.29.77.29 1.063 0L9.78 1.27c.293-.29.293-.76 0-1.052-.295-.29-.77-.29-1.063 0z"></path></g></svg><!-- react-text: 106 -->查看详细资料<!-- /react-text -->');
	     }
	    step();
	    return false;
	};
});
function myquestion(){
	var userid = <s:property value="irpUser.userid" />;
	$.ajax({
		type:"post",
		cache:false,
		data:{userid:userid},
		url:"<%=rootPath%>question/questionListofexpertquestion.action",
		success:function(html){
			$('#myanswer').html(html);
		}
	});
}
//向专家提问
function askExpert(expertId) {
	window.open('<%=rootPath %>question/linkeditsubmit.action?expertId='+expertId);
}
//加关注
function onConcern(userId, imgDom) {
    $.post("<%=rootPath%>microblog/okMicroblogFocus.action", { userid: userId }, function(msg) {
        if (msg == 1) {
            $(imgDom).html("已关注");
            $(imgDom).attr("data-status", "true");
            location.reload();
        }
    });
}

//取消关注方法
function cancelConcern(userId, imgDom) {
	$.dialog.confirm('您确定要取消关注吗？',function(){
		$.post("<%=rootPath%>microblog/cancelMicroblogFocus.action", { userid: userId }, function(msg) {
	        if (msg == 1) {
	        	$(imgDom).html("加关注");
	        	$(imgDom).attr("data-status", "false");
	        	location.reload();
	        }
	    });
	},function(){}); 
}

//发送私信
function messageContentViews(_messageuser,_messageid){
	//构建页面结果
	var result = $.ajax({
		url: '<%=rootPath%>microblog/toExpertMessage.action',
		type:"post",
		dataType: "json",
		cache:false,
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
		cache:false,
		width:'400px',
		height:'180px',
		content:result,
		cancelVal:'关闭',
		okVal:'发送',
		cancel:true,
		ok:function(){
		
			if($.trim($('#messageuserinfo').val())==""){			
				$.dialog.tips("收信人不能为空",1,"alert.gif");	
				return false;
			}else{	
			   if($.trim($('#messageInfo').val()).length>$('#messagecount').val()){
			    	return false;
			    }else if($.trim($('#messageInfo').val()).length<=0){
			    	  $.dialog.tips("私信内容不能为空",1,"alert.gif");	
			    	return false;
			    }else{
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
		}
		}
	});
	if(_messageid==0){
		$('#messageuserinfo').focus();
	}else{
		  $('#messageInfo').focus();	
	}	
}


/*
	
	专家关系图
	*/
	function expert_relationship(){
	 	var expertId=$("#expertid").val();
		var sUrl = "<%=rootPath%>expert/expert_relationship.action?expertId="+expertId;
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: sUrl,
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$("#expertRelationship").html(sHtmlConn);
	}
</script>
<body style="background: url()">
	<div class="bg01">
		<jsp:include page="../../view/include/client_head.jsp" />
		<section class="mainBox"> 
			<nav class="privateNav" style="background:url(../images/bg-04.gif) repeat-x">
			</nav> 
		</section>
                	<input type="hidden" id="expertid" value="<s:property value='irpUser.userid' />"/>
		<div class="w1200">
        <div class="zjjs">
            <div class="zj_l">
                <div class="zj_tx">
                	<s:if test="irpUser.userpic!=null">
						<img
							src="<%=rootPath%>file/readfile.action?fileName=<s:property value='irpUser.userpic' />" style="width:180px;height:180px;position:static;"
							alt="用户图片" class="jnexpert_bg ex-img " />
					</s:if> <s:else>
						<s:if test="sex==2">
							<img src="<%=rootPath%>client/images/female.jpg" style="width:180px;height:180px;position:static;"
								alt="用户图片" class="jnexpert_bg ex-img " />
						</s:if>
						<s:else>
							<img src="<%=rootPath%>client/images/male.jpg" style="width:180px;height:180px;position:static;"
								alt="用户图片" class="jnexpert_bg ex-img " />
						</s:else>
					</s:else>
                </div>
                
                
            <%--     <ul style="font-size:12px;">
                    <li><s:property value="userfocuscount" /><br />关注</li>
                    <li style="border: none"><s:property value="userfanscount" /><br />粉丝</li>
                </ul> --%>
                
            </div>
            <div class="zj_r">
            	<div>
		               
		                <s:if test="isuser=='false'">
		                <ul class="zj_btns">
		                    <li>
		                    	
		                    <input type="button" class="inputButton" onclick="askExpert(<s:property value="irpUser.userid"/>)" value="提&nbsp;&nbsp;&nbsp;问"  />
		                    </li>
		                    <li>
			                    <a href="#">
			                    	<s:if test="focusStatus=='true'">
						           <input data-id="<s:property value='irpUser.userid' />" type="button" class="inputButton" onclick="cancelConcern(<s:property value='irpUser.userid' />,this)" value="取消关注"  data-status="true"/>
						           </s:if>
						           <s:else>
						            <input data-id="<s:property value='irpUser.userid' />" type="button" class="inputButton" onclick="onConcern(<s:property value='irpUser.userid' />,this)" value="加关注"  data-status="false"/>
						           </s:else>
			                    </a>
		                    </li>
		                    <li>
		                       <input type="button" class="inputButton" onclick="messageContentViews('<s:property value="irpUser.showName" />','<s:property value="irpUser.userid"/>')" value="发私信"  />
		                    </li>
		                </ul>
		                </s:if>
            	</div>
                <div class="zj_name" style="margin-top:15px;margin-bottom:0px;"><s:property value="irpUser.showName" />
                	<s:if test="irpUser.sex==2">
	                	<div title="女" class="gj" style="display:inline-block;margin-left:9px;padding-left:30px;background-image:url('<%=rootPath%>view/images/txl_icon_04.jpg');background-repeat: no-repeat;background-size: 15px 15px;background-position: 0 10px;">
	                	&nbsp;
                		</div>
                	</s:if>
                	<s:else>
	                	<div title="男" class="gj" style="display:inline-block;margin-left:9px;padding-left:30px;background-image:url('<%=rootPath%>view/images/txl_icon_03.jpg');background-repeat: no-repeat;background-size: 15px 15px;background-position: 0 10px;">
	                	&nbsp;
	                	</div>
                	</s:else>
                </div>
                <div class="zj_name" style="font-size:15px;margin-bottom:0;">
                	<ul class="expertintro">
	                <s:iterator value="cateGorylist" status="cg">
	                	<li>
	                	<a><s:property value="cname" />专家&nbsp;</a>
	                	</li>
	                </s:iterator>
	                </ul>
	                <div class="clear"></div>
                </div>
                <div>
                	<%-- <div>
		               
		                <s:if test="isuser=='false'">
		                <ul class="zj_btns">
		                    <li>
			                    <a href="#" onclick="askExpert(<s:property value="irpUser.userid"/>)">
			                    	<div style="background:#27993c;color:#fff;padding:3px;border-radius:3px;">向TA咨询</div>
			                    </a>
		                    </li>
		                    <li>
			                    <a href="#">
			                    	<s:if test="focusStatus=='true'">
						              	<div data-id="<s:property value='irpUser.userid' />" style="background:#27993c;color:#fff; padding:3px; border-radius:3px;" data-status="true" onclick="cancelConcern(<s:property value='irpUser.userid' />,this)">取消关注</div>
						           </s:if>
						           <s:else>
						           		<div data-id="<s:property value='irpUser.userid' />" style="background:#27993c;color:#fff; padding:3px; border-radius:3px;" data-status="false" onclick="onConcern(<s:property value='irpUser.userid' />,this)">加关注</div>
						           </s:else>
			                    </a>
		                    </li>
		                    <li>
			                    <a href="#">
			                    	<div style="background:#27993c;color:#fff;padding:3px;border-radius:2px;" onclick="messageContentViews('<s:property value="irpUser.showName" />','<s:property value="irpUser.userid"/>')">发私信</div>
			                    </a>
		                    </li>
		                </ul>
		                </s:if>
            	</div> --%>
            	<button id="show" style="background:transparent;margin-top:10px;" type="button" data-reactid="102"><svg viewBox="0 0 10 6" style="fill:#BFBFBF;margin-right:10px;height:16px;width:10px;" width="10" height="16" aria-hidden="true" data-reactid="103"><title data-reactid="104"></title><g data-reactid="105"><path d="M8.716.217L5.002 4 1.285.218C.99-.072.514-.072.22.218c-.294.29-.294.76 0 1.052l4.25 4.512c.292.29.77.29 1.063 0L9.78 1.27c.293-.29.293-.76 0-1.052-.295-.29-.77-.29-1.063 0z"></path></g></svg><!-- react-text: 106 -->查看详细资料<!-- /react-text --></button>
		        <button id="hidden" style="display:none;background:transparent;margin-top:10px;" style="background:transparent;margin-top:10px;" type="button"><svg viewBox="0 0 10 6" width="10" height="16" aria-hidden="true" style="transform: rotate(180deg);fill:#BFBFBF;margin-right:10px;height: 16px; width: 10px;"><title></title><g><path d="M8.716.217L5.002 4 1.285.218C.99-.072.514-.072.22.218c-.294.29-.294.76 0 1.052l4.25 4.512c.292.29.77.29 1.063 0L9.78 1.27c.293-.29.293-.76 0-1.052-.295-.29-.77-.29-1.063 0z"></path></g></svg><!-- react-text: 2159 -->收起详细资料<!-- /react-text --></button>       
            	<div id="detailed" style="height:0;overflow:hidden;position:relative;">
           		 	<p>
           		 	<span class="gj" style="display:inline-block;word-spacing:normal;background-image:url('<%=rootPath%>view/images/telephone.png');background-repeat: no-repeat;background-size: 22px 22px;background-position: 0 5px;">
		                	<s:if test="irpUser.mobile!=null">
		                	<s:property value="irpUser.mobile" />
		                	</s:if>
		                	<s:else>
		                	暂无手机号
		                	</s:else>
		              </span>
		              &nbsp; &nbsp;
		                <span class="gj" style="margin-right:9px;display:inline-block;background-image:url('<%=rootPath%>view/images/address.png');background-repeat: no-repeat;background-size: 22px 22px;background-position: 0 5px;">
		                	<s:if test="irpUser.province!=null || irpUser.city!=null || irpUser.area!=null">
		                	<s:property value="irpUser.province" /><s:property value="irpUser.city" /><s:property value="irpUser.area" />
		                	</s:if>
		                	<s:else>
		                	暂无地址
		                	</s:else>
		                </span>
		                     &nbsp; &nbsp;
		                <span class="gj" style="margin-right:9px;display:inline-block;background-image:url('<%=rootPath%>view/images/email.png');background-repeat: no-repeat;background-size: 22px 22px;background-position: 0 8px;">
		                	<s:if test="irpUser.email!=null">
		                	<s:property value="irpUser.email" />
		                	</s:if>
		                	<s:else>
		                	暂无邮箱
		                	</s:else>
		                </span>
		                <br/>
		                     &nbsp; &nbsp;     &nbsp; &nbsp;
	                	<span><font style="color: gray;">简介：</font><s:if test="irpUser.expertintro!=null">
	                		<s:property value="irpUser.expertintro" />
	                	</s:if>
	                	<s:else>
	                		暂无简介
	                	</s:else>
	                	</span>
                	</p>
	                <div class="clear"></div>
	               
            	</div>
            </div>
            </div>
        </div>
        <div class="clear"></div>
        <div style="width:856px;float: left;">
        <div class="tdwb" style="width:856px;">
            <div class="tabTitle" style="margin-left: 0;border-top:none;">
                <div class="current">
	                <label>
	                	<s:if test="isuser=='true'">
	                		我的微知
	                	</s:if>
	                	<s:else>
	                		他的微知
	                	</s:else>
	                </label>
	                <p class="labBg"></p>
                </div>
            </div>
            <div class="clear"></div>
            <div>
            	<s:iterator value="irpMicrobloglist">
            	<ul class="wz">
            		<li>
                        <div class="act_img" style="border-radius:40px;">
                       <s:if test="USERPIC!=NULL&&USERPIC!=''">
               				<img style="width:80px;height:80px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img style="width:80px;height:80px" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
                        <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank">
                			
               			</a>
                        </div>
                    </li>
                    <div style="margin-left:100px;color:#225491;font-weight:bold;"><s:property value='irpUser.showName'/></div>
                    <li class="cont_h"><s:property value='microblogcontent' escapeHtml="false" /></li>
                    <li class="act_msg">
                        <ul class="zfpldz">
                        	<li class="rq"><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></li>
                            <li class="zf">转发(<s:property value='transpondcount' />)</li>
                            <li class="pl">评论(<s:property  value='commentcount' />)</li>
                        </ul>
                    </li>
            	</ul>
            	</s:iterator>
            </div>
            <!-- 
            <div class="wb_tx">
            	<s:if test="userpic!=null">
					<img
						src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />" style="width:168px;height:168px;position:static;"
						alt="用户图片" class="jnexpert_bg ex-img " />
				</s:if> <s:else>
					<s:if test="sex==2">
						<img src="<%=rootPath%>client/images/female.jpg" style="width:168px;height:168px;position:static;"
							alt="用户图片" class="jnexpert_bg ex-img " />
					</s:if>
					<s:else>
						<img src="<%=rootPath%>client/images/male.jpg" style="width:168px;height:168px;position:static;"
							alt="用户图片" class="jnexpert_bg ex-img " />
					</s:else>
				</s:else>
            </div>
            <div class="wb_cont">
                <div class="wb_name"><s:property value="irpUser.showName" /></div>
                <s:if test="focusStatus=='true'">
                	<div data-id="<s:property value='irpUser.userid' />" class="wb_btn" data-status="true" onclick="cancelConcern(<s:property value='irpUser.userid' />,this)">已关注</div>
	            </s:if>
	            <s:else>
	            	<div class="wb_btn" data-id="<s:property value='irpUser.userid' />" data-status="false" onclick="onConcern(<s:property value='irpUser.userid' />,this)">加关注</div>
	            </s:else>
                
                <div class="wb_ot">
                	<a href="#" style="color:#5f9ddd">
                		<s:if test="irpUser.email!=null">
		               	<s:property value="irpUser.email" />
		               	</s:if>
		               	<s:else>
		               	暂无邮箱
		               	</s:else>
                	</a>
                </div>
                <div class="wb_ot">
                	其他&nbsp;|&nbsp;
                	<s:if test="irpUser.location!=null">
                		<s:property value="irpUser.location" />
                	</s:if>
                	<s:else>
                	暂无公司
                	</s:else>
                </div>
                <p>简介：
                	<s:if test="irpUser.expertintro!=null">
                		<s:property value="irpUser.expertintro" />
                	</s:if>
                	<s:else>
                		暂无简介
                	</s:else>
				</p>
            </div>
            <div class="clear"></div>
            -->
        </div>
        <div class="tdwb" style="width:856px;">
            <div class="tabTitle" style="margin-left: 0;border-top:none;">
                <div class="current">
	                <label>
	                	<s:if test="isuser=='true'">
	                		我的回答
	                	</s:if>
	                	<s:else>
	                		他的回答
	                	</s:else>
	                </label>
	                <p class="labBg"></p>
                </div>
            </div>
            <div class="clear"></div>
            <div class="fyh" style="width:856px;">
                <ul id="myanswer" style="margin-top:10px;">
            	</ul>
            </div>
            <div class="zxzs">
                <div class="tabTitle" style="margin-left: 0;border-top:none;">
                    <div class="current"><label>最新知识</label><p class="labBg"></p></div>
                </div>
                <div class="clear"></div>
                <section class="listpan1">
                    <ul class="list1">
                    	<s:iterator value="newChnlDocs">
                        <s:set var="cruser" value="findUserByUserId(cruserid)" />
                        <li>
                            <em style="width:56px;" class="ellipsis">
                                <a href="<s:url namespace="/document" action="document_list"><s:param name="channelid" value="channelid" /></s:url>" title="<s:property value="chnldesc" />"><s:property value="chnldesc" /></a>
                            </em>
                            <label class="ellipsis">|</label>
                            <span style="width:400px;" class="ellipsis">
                                <a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="docid" /></s:url>" title="<s:property value='doctitle'/>" target="_blank"><s:property value='doctitle'/></a>
                            </span>
                            <aside style=";" class="ellipsis">
                                <s:date name="docpubtime" format="MM.dd"/>&nbsp;&nbsp;
                                <a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="cruserid" /></s:url>" target="_blank"><s:property value="#cruser.showName"/></a>
                            </aside>
                        </li>
                    	</s:iterator>
                    </ul>
                </section>
            </div>
             <div class="tdrjt">
                <div class="tabTitle" style="margin-left: 0;border-top:none;">
                	<s:if test="isuser=='true'">
	                		<div class="current"><label>我的人际图</label><p class="labBg"></p></div>
                	</s:if>
                	<s:else>
                		<div class="current"><label>他的人际图</label><p class="labBg"></p></div>
                	</s:else>
                </div>
                <div id="expertRelationship">
                	
                </div>
            </div>
        </div>
        <div class="clear"></div>
        </div>
        <div style="width:300px;margin-top:40px;float: right;">
        	<!-- 
            <div class="gxqdr">
                <div class="tabTitle" style="margin-left: 0;border-top:none;">
                    <div class="current"><label>对ta感兴趣的人</label><p class="labBg"></p></div>
                </div>
                <ul>
                    <li><img src="<%=rootPath%>view/expert/images/pic_03.jpg" /></li>
                    <li><img src="<%=rootPath%>view/expert/images/pic_03.jpg" /></li>
                    <li><img src="<%=rootPath%>view/expert/images/pic_03.jpg" /></li>
                    <li><img src="<%=rootPath%>view/expert/images/pic_03.jpg" /></li>
                    <div class="clear"></div>
                </ul>
                <div class="fwcs">最近一周被访问了<span>&nbsp;41&nbsp;</span>次</div>
            </div>
             -->
            <div class="gxqdr">
                <div class="tabTitle" style="margin-left: 0;border-top:none;">
                	<s:if test="isuser=='true'">
	                		<div class="current"><label>我的关系</label><p class="labBg"></p></div>
                	</s:if>
                	<s:else>
                		<div class="current"><label>TA的关系</label><p class="labBg"></p></div>
                	</s:else>
                    
                </div>
                <div class="fwcs">关注：<span>&nbsp;<s:property value="userfocuscount" />&nbsp;</span>人</div>
                <ul>
                	<s:iterator value="microblogFocus">
                    <s:set var="cruser" value="findUserByUserId(userid)" />
                    <li>
                    	<s:if test="#cruser.userpic!=null">
							<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#cruser.userpic' />" style="width:50px;height:50px;position:static;"
								 alt="用户图片" title="<s:property value='focususername' />" class="jnexpert_bg ex-img " />
							<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;"><s:property value='focususername' /></p>
						</s:if> <s:else>
						<s:if test="#cruser.sex==2">
							<img src="<%=rootPath%>client/images/female.jpg" style="width:50px;height:50px;position:static;"
								alt="用户图片" title="<s:property value='focususername' />" class="jnexpert_bg ex-img " />
								<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;overflow:hidden;"><s:property value='focususername' /></p>
						</s:if>
						<s:else>
							<img src="<%=rootPath%>client/images/male.jpg" style="width:50px;height:50px;position:static;"
								alt="用户图片" title="<s:property value='focususername' />" class="jnexpert_bg ex-img " />
								<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;"><s:property value='focususername' /></p>
						</s:else>
					</s:else>
                    </li>
					</s:iterator>
                    <div class="clear"></div>
                </ul>
                <div class="fwcs">粉丝：<span>&nbsp;<s:property value="userfanscount" />&nbsp;</span>人</div>
                <ul>
                    <s:iterator value="microblogFans">
                    <s:set var="cruser" value="findUserByUserId(focususerid)" />
                    <li>
                    	<s:if test="#cruser.userpic!=null">
							<img
								src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#cruser.userpic' />" style="width:50px;height:50px;position:static;"
								alt="用户图片" title="<s:property value='#cruser.showName' />" class="jnexpert_bg ex-img " />
								<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;"><s:property value='#cruser.showName' /></p>
						</s:if> <s:else>
						<s:if test="#cruser.sex==2">
							<img src="<%=rootPath%>client/images/female.jpg" style="width:50px;height:50px;position:static;"
								alt="用户图片" title="<s:property value='#cruser.showName' />" class="jnexpert_bg ex-img " />
								<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;"><s:property value='#cruser.showName' /></p>
						</s:if>
						<s:else>
							<img src="<%=rootPath%>client/images/male.jpg" style="width:50px;height:50px;position:static;"
								alt="用户图片" title="<s:property value='#cruser.showName' />" class="jnexpert_bg ex-img " />
								<p style="text-align:center;width:50px; text-overflow:ellipsis;  white-space:nowrap;   overflow:hidden;"><s:property value='#cruser.showName' /></p>
						</s:else>
					</s:else>
                    </li>
					</s:iterator>
                    <div class="clear"></div>
                </ul>
            </div>

        </div>
    </div>
	</div>
	
	<div>
		<jsp:include page="../include/client_foot.jsp" />
	</div>
</body>
</html>