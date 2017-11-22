<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script type="text/javascript">

function personalCardMicr(_microblogid){
	var personalcard = '#microblogCard'+_microblogid;
	$(personalcard).css({
		display:"block"
		
	});
}
function personalCardMicrOut(_microblogid){  
	var personalcardout ='#microblogCard'+_microblogid;
	$(personalcardout).css({
		display:"none"
	});
}

//取消关注
function cancelFocus(_userid,_microblogid){
	var alreadyFocus_01="#alreadyFocus_01"+_microblogid;
	var alreadyFocus_02="#alreadyFocus_02"+_microblogid;
    if($(alreadyFocus_01).text()=='未关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(alreadyFocus_01).text("已关注");
				   	$(alreadyFocus_02).text("(取消)");
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(alreadyFocus_01).text("未关注");
				   	$(alreadyFocus_02).text("(关注)");
				}
			}
		});
		});
	}
	
	
	
}
//增加关注
function okFocus(_userid,_microblogid){
	var notFocus_01="#notFocus_01"+_microblogid;
	var notFocus_02="#notFocus_02"+_microblogid;
	
    if($(notFocus_01).text()=='未关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(notFocus_01).text("已关注");
				   	$(notFocus_02).text("(取消)");
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(notFocus_01).text("未关注");
				   	$(notFocus_02).text("(关注)");
				}
			}
		});
		});
	}	
	
}
//互相关注
function cancelEachFocus(_userid,_microblogid){
	var eachFocus_01="#eachFocus_01"+_microblogid;
	var eachFocus_02="#eachFocus_02"+_microblogid;
	
 if($(eachFocus_01).text()=='未关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(eachFocus_01).text("互相关注");
				   	$(eachFocus_02).text("(取消)");
				}
			}
		});
	}else{
		$.dialog.confirm('您确定要取消关注此人吗',function(){
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/cancelMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(eachFocus_01).text("未关注");
				   	$(eachFocus_02).text("(关注)");
				}
			}
		});
		});
	}
}


/*构建私信框*/
function messageContentView(_messageuser,_messageid){
	//构建页面结果
	
	var result = $.ajax({
		url: '<%=rootPath%>microblog/messageContentPage.action',
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
		width:'450px',
		height:'180px',
		content:result,
		cancelVal:'关闭',
		okVal:'发送',
		cancel:true,
		ok:function(){
		//获取隐私设置
        var messagelimits = $.ajax({
	    	     type:"post",
	    	     url:"<%=rootPath%>microblog/getprivacyofmessage.action",
	    	     data:{
	    	        	userid:_messageid
	            	},
	            	cache:false,
	            	async:false
	             }).responseText;
	    	if(messagelimits=="false"){
	    		 $.dialog.tips("由于用户设置，您无法发送私信。",1,"alert.gif");	
	    		return false;
	    	}else{
	    		
	    		 if($('#messageInfo').val().trim().length>${messagecount}){
	 		    	return false;
	 		    }else if($('#messageInfo').val().trim().length<=0){
	 		    	
	 		    	 $.dialog.tips("私信内容不能为空",0.3,"alert.gif");	
	 		    	
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
	$('#messageInfo').focus();
    
}

//微知设置分组
function  MicroblogSetGroup(_userid){
	var str=$.ajax({
		url: '<%=rootPath%>microblog/microblogSetGroup.action', 
		type:"post", 
	    async: false,
	    cache: false
	}).responseText;   
	 $.dialog({
		 	title:'设置分组',
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
			 cancel: true,
			okVal:'确定',
			ok:function(){    
			  var groupIds=$('#groupIds').val();
		  	  var groupNames=$('#groupNames').val();
			  $.ajax({
				  type:"post",
				  url:"<%=rootPath%>microblog/addMicroblogUserOfGroups.action",
				  data:{
					selectuser:_userid,
					selectgroups:groupIds
				  },
				  cache:false,
				  success:function(msg){
					 if(msg==1){
					   $.dialog.tips('设置成功',0.5,'success.gif');
					 }else{
					   $.dialog.tips('设置失败',0.5,'error.gif');  
					 } 
				  }
			  });
			}
	});  
}
/**
 * 链接到主题
 */
function getInfoTopicPage(_info){
	/*判断此话题是否通过审核*/
	var linkstatu = $.ajax({
    	type:"post",
    	url:"<%=rootPath%>microblog/booleanaudit.action",
    	data:{
    		topicname:_info
    	},
    	cache:false,
    	async:false
    	 }).responseText;

if(linkstatu==10){
	$.dialog.alert('此专题尚未通过管理员审核',function(){});
	return false;
}



var status = $.ajax({
	type:"post",
	url:"<%=rootPath%>microblog/searchtopicnum.action",
	data:{
		topicname:_info
	},
	cache:false,
	async:false
	 }).responseText;

if(status>=1){
	window.location.href="<%=rootPath%>microblog/searchTopic.action?topicname="+_info;	
}else{
	$.dialog.alert('此专题已被管理员删除',function(){});
}

	return true;
}
 
 
</script>
<div>
<div style="position: absolute;  margin-top: -238px;border-radius: 2px;width: 370px;font-size: 13px;">
	<s:iterator value="irpMicrobloglist">
		<div class="jinzhao1">
			<div id="microblogCard<s:property value="microblogidCard" />"
				class="jinzhao2"
				onmouseout="personalCardMicrOut(<s:property value="microblogidCard" />)"
				onmouseover="personalCardMicr(<s:property value="microblogidCard" />)">
				<span class="b222"></span>
				<div class="jz3">
					<p style="margin:5px 0 0 0; ">
	            
						<s:if test="USERPIC!=null">
							<span style="float: left;">
							<a	target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
							<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="USERPIC" />"
								alt="用户图片" style="width: 55px;border-radius: 30px;">
								</a>
							</span>
						</s:if>
						<s:if test="USERPIC==null">
							<span style="float: left;">
							<a	target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
							<img  <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户图片"
								style="width: 55px;border-radius: 30px;">
								 </a>
							</span>
						</s:if>
                
						<span><a target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> "
							style="color: rgb(32, 108, 124);">&nbsp;&nbsp;<s:property
									value="SHOWNAME" />
						</a>
						</span>
						<span style="width:270px;height:20px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="PROVINCE" />&nbsp;<s:property value="CITY" />&nbsp;<s:property value="AREA" />">&nbsp;
							<s:property value="PROVINCE" />&nbsp;<s:property value="CITY" />&nbsp;<s:property value="AREA" />
						</span>
						&nbsp;
						<span style="width:270px;height:20px;margin-top:-20px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="LOCATION" />">
							<s:property value="LOCATION" />
						</span> <span><a style="color: #5F9DDD;" target="_blank"
							href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=1" >关注</a>
						<s:property value="MICROBLOGFOCUSID" /> 
						<a target="_blank"	style="color: #5F9DDD;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=2" >粉丝</a>
						<s:property value="MICROBLOGUSERID" />
						 <a target="_blank"	style="color: #5F9DDD;" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >微知</a>
						<s:property value="MICROBLOGCOUNT" /> </span>
					</p>
					<p style="width:330px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="EXPERTINTRO" />">
					 <s:if test="EXPERTINTRO.trim().length()>0">
					 	<s:property value="EXPERTINTRO" />
					 </s:if>
					 <s:else>
						 简介:暂无介绍
					 </s:else>
					
					</p>
					
					<p>
					  
					  
					  
					  
					  
					  
					  
					  
					  
               	<div style="float: left;width: 320px;margin-left: 20px;text-align: center;height: 60px;overflow: auto;">
              
               	<s:if test="listmedal.size()>0">
                	<s:iterator value="listmedal" status="listStat">
                	 <s:if test="medalimage!=null">
                	 	<s:iterator value="medalimage.split(',')" var="attid"  status="index1">
		                  <s:if test="#index1.first">
		                  <div class="medal"  style="width: 70px;float: left;">
		                   <a title="<s:property value='medalname' />:<s:property value="userCount" />" style="border-radius: 0px;height: auto;width: 60px;"href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
		                    <img src="<s:property value='coverPath(#attid)' />"  width="40"   style="cursor: pointer;" title="<s:property value='medalname' />:<s:property value="userCount" />">
		                    <br/>  
		                    <span><s:property value='medalname' /></span> 
		                    </a>
		                  </div>
		                    </s:if>
		                   </s:iterator>
                	 </s:if>
                	  <s:else>
                	  <div  class="medal" >
                	   <a title="<s:property value='medalname' />:<s:property value="userCount" />"style="border-radius: 0px;height: auto;width: 60px;" href="<s:url action="medalDetial" namespace="/medal"><s:param name="medalid" value="medalid" /></s:url>"  target="_blank">
                    <img src="<%=rootPath %>client/images/bjt.png"  width="15" height="15" style="border-radius: 10px;cursor: pointer;" title="<s:property value='medalname' />:<s:property value="userCount" />"> 
                    <span><s:property value='medalname' /></span>
                     </a>
               	</div>
                 </s:else> 
                	</s:iterator>
                	</s:if>
                	<s:else>
                	
                		暂无勋章！
                	</s:else>
                	</div>
					  
					  
					  
					  
					  
					  
					  
					  
					</p>
					
					<div class="jz4">
						<s:if test="loginuserid!=userid">
							<img src="<%=rootPath%>view/images/ico29.gif" align="absmiddle">
							<font><a href="javascript:void(0)"
								onclick="messageContentView('<s:property value='SHOWNAME' />',<s:property value='MESSAGEUSERID' />)">私信</a>
							</font> |&nbsp;
      
                       <img src="<%=rootPath%>view/images/ico28.gif" align="absmiddle">
							<font><a href="javascript:void(0)" onclick="MicroblogSetGroup(<s:property value='USERID' />)">设置分组</a>
							</font>
							<s:if
								test="userid in allUseridByFocusUserId && loginuserid in allUseridByUserid">
								<span><img src="<%=rootPath%>view/images/ico30.gif"
									align="absmiddle"><label
									id="eachFocus_01<s:property value="microblogid" />">互相关注</label>
									<s:if test="userid!=2">| <font><a href="javascript:void(0)"
										onclick="cancelEachFocus(<s:property value="userid" />,<s:property value="microblogid" />)"><label
											id="eachFocus_02<s:property value="microblogid" />">(取消)</label>
									</a>
								</font></s:if>
								</span>
							</s:if>
							<s:elseif test="userid in allUseridByFocusUserId">
								<span><img src="<%=rootPath%>view/images/ico30.gif"
									align="absmiddle"><label
									id="alreadyFocus_01<s:property value="microblogid" />">已关注</label>
									<s:if test="userid!=2">| <font><a href="javascript:void(0)"
										onclick="cancelFocus(<s:property value="userid" />,<s:property value="microblogid" />)"><label
											id="alreadyFocus_02<s:property value="microblogid" />">(取消)</label>
									</a>
								</font></s:if>
								</span>
							</s:elseif>
							<s:elseif test="userid not in allUseridByFocusUserId">
								<span><img src="<%=rootPath%>view/images/ico30.gif"
									align="absmiddle"><label
									id="notFocus_01<s:property value="microblogid" />">未关注</label>
									| <font><a href="javascript:void(0)"
										onclick="okFocus(<s:property value="userid" />,<s:property value="microblogid" />)"><label
											id="notFocus_02<s:property value="microblogid" />">(关注)</label>
									</a>
								</font>
								</span>
							</s:elseif>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</s:iterator>
</div>

</div>