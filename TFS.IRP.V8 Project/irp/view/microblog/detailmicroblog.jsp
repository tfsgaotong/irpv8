<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<%-- <style>
.cke_reset {
    margin: 83px 0px 0px 0px;}
</style> --%>

<script type="text/javascript">

var formatapi;


$(function(){
	var commentdiv = "#microCommentFontCount_01"+'<s:property value="microblogid"/>';
	$(commentdiv).text(microblogfineandlooknumber);
	var tabname='<s:property value="microblogid"/>';
	 
});
//鼠标悬浮到某条私信框
function  deleteCommentOverDetail(_commentid){
	var commentdeletediv = "#deletecommentdivboolDetail"+_commentid;
	$(commentdeletediv).css("visibility","visible");
}
//鼠标移出到某条私信框
function deleteCommentOutDetail(_commentid){
	var commentdeletediv = "#deletecommentdivboolDetail"+_commentid;
	$(commentdeletediv).css("visibility","hidden");
	
}

//评论回复 ------细览
function commentReplyFineAndLook(_microblogid,_userid){ 
	$('#microfineandlook').attr("onclick","");
	var commentInfoid ="#commentInfo"+_microblogid;
	var twoname="commentInfo"+_microblogid; 
	var commentInfo =  $(commentInfoid).val();
 
	
	
	
	var appandspancomment = "#apendSpanMicroComment"+_microblogid;
	var microblogcommentcount ="#microblogcommentcount"+_microblogid;
	
	//获取隐私设置
  var commentlimits = $.ajax({
  	     type:"post",
  	     url:"<%=rootPath%>microblog/getprivacyofcomment.action",
  	     data:{
  	        	userid:_userid
          	},
          	cache:false,
          	async:false
           }).responseText;
  	if(commentlimits=="false"){
		$('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
  		 $.dialog.tips("由于用户设置，您无法回复评论。",1,"alert.gif");	
  		return false;
  	}else{
	 if($.trim(commentInfo).length>microblogfineandlooknumber){
			$('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
	    	return false;
	    }else if($.trim(commentInfo).length<=0){
			$('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
	    	$.dialog.tips("评论内容不能为空",0.3,"alert.gif");
	    	return false;
	    }else{	
	    	
	    	commentInfo =  commentInfo.replace(/</g,"&lt;").replace(/>/g,"&gt;"); 
	    	
	    	
	    	$.ajax({
	    		type:"post",
	    		url:"<%=rootPath%>microblog/microblogCommentFineAndLook.action",
	    		cache:false,
	    		async:false,
	    		data:{
	    		 replyuserid:_userid,
	    		 replaymicroblogid:_microblogid,
	    		 replaycontent:formatapi.format(commentInfo),
	    		 replycommentidpage:replycommentidpage
	    		},
	    		success:function(callback){
	    			var commentdiv = "#microCommentFontCount_01"+'<s:property value="microblogid"/>';
	    			$(commentdiv).text(microblogfineandlooknumber);
	    			if(callback!=null){
	    				$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
	    				$(commentInfoid).val(""); 
	    				replycommentidpage=0;
	    				
	                   
	                   $("section[class='discussions']").prepend(callback);
	                   
	                   $('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
	    			}else{
	    				$('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
	    			}
	    		  },
	    		error:function(){
	    			var commentdiv = "#microCommentFontCount_01"+'<s:property value="microblogid"/>';
	    			$(commentdiv).text(microblogfineandlooknumber);
	    			$('#microfineandlook').attr("onclick","commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)");
	    			$.dialog.tips('评论失败',1,'32X32/fail.png');
	    		}
	    	}); 
	    }
 }
}
</script>
<s:set var="microobj" value="transpondIrpMicroblog(microblogid)"></s:set>
<div class="area20"></div>
<div class="titlezt">
	<s:property value="#microobj.microblogcontent" escapeHtml="false"  /><br/>
	<s:property value="#microobj.microblogcontentimg" escapeHtml="false"  />
</div>
 <div class="titler" style="margin-bottom: 0px;">
 	<aside>
 		<a href="javascript:void(0)" onclick="transpond('<s:property value="getShowPageViewNameByUserId(userid)"  />',<s:property value="microblogid"  />,<s:property value="userid"  />,<s:property value="transpondIrpMicroblog(microblogid).transpondid"  />)">转发(<label id="microblogtranspondcount<s:property value='microblogid' />"><s:property value="transpondIrpMicroblog(microblogid).transpondcount"  /></label>)</a>
 		<a href="javascript:void(0)"   >&nbsp;&nbsp;评论(<label id="microblogcommentcount<s:property value='microblogid' />"  ><s:property value="transpondIrpMicroblog(microblogid).commentcount"  /></label>)&nbsp;&nbsp;</a>
 		<a href="javascript:void(0)" onclick="" >
 			<s:if test="microblogid in collectionOfUseridlist">
 			
 				<a  href="javascript:void(0)" id="<s:property value='microblogid' />"  onclick="collect(<s:property value='microblogid' />)" >取消收藏</a>
 			</s:if>
 			<s:else>
 				<a  href="javascript:void(0)" id="<s:property value='microblogid' />"  onclick="collect(<s:property value='microblogid' />)" >收藏</a>
 			</s:else>
 		</a>
 	</aside>
 </div>
 <div class="maintext">
 
 
 	<section id="microCommentFont_01<s:property value='microblogid' />" class="textCount" style="float:right;">
 		您还可以输入<font id="microCommentFontCount_01<s:property value='microblogid' />"></font>字
 	</section>
 	<section id="microCommentFont_02<s:property value='microblogid' />"  style="display: none;float:right;"  class="textCount">
 		已超出<font id="microCommentFontCount_02<s:property value='microblogid' />"></font>字
 	</section> 
 	
 	
 	<div style="margin-top:80px;" class="publishercss"> 
 		<textarea style="font-size: 14px;" onkeyup="microblogCommentFontInfo(<s:property value='microblogid' />)"  id="commentInfo<s:property value='microblogid' />"></textarea>
 	</div> 
 	
 	
 	
		<script type="text/javascript">
		$(function(){
			$.emoticons({
				'activeCls':'trigger-active', 
				'publisherCls':'publishercss',
				'path':'<%=rootPath%>view/css/emoticons/public/image/',
			},function(api){ 
				formatapi = api; 
			}); 
		});
		</script>
		<style type="text/css">  
			.widget-layer{
				position: relative;
				width: 410px;
				margin-top: -25px;
				margin-left:6px;
				background: #fff;
				border: 1px solid #dbdbdb;
				border-radius: 2px; 
				z-index: 111111;
			}
			.widget-layer:before{
				position: absolute;
				top: -16px;
				left: 2px;
				display: block;
				content: '';
				width: 0;
				height: 0;
				border: 8px solid transparent;
				border-bottom-color: #dbdbdb;
				z-index: 111111;
			}
			.widget-layer:after{
				position: absolute;
				top: -15px;
				left: 2px;
				display: block;
				content: '';
				width: 0;
				height: 0;
				border: 8px solid transparent;
				border-bottom-color: #f0f0f0;
				z-index: 111111;
			}
			.widget-layer .widget-tool{
				height: 28px;
				background: #f0f0f0;
				z-index: 111111;
			}
			.widget-layer .widget-close{
				float: right;
				width: 28px;
				height: 28px;
				line-height: 28px;
				text-align: center;
				font-family: Arial;
				z-index: 111111;
			}
			.widget-layer ul{
				width: 372px;
				margin: 0 auto;
				padding: 15px 5px 20px;
				overflow: hidden;
				z-index: 111111;
			}
			.widget-layer li{
				position: relative;
				z-index: 8;
				float: left;
				width: 22px;
				height: 22px;
				padding: 4px;
				margin-left: -1px;
				margin-top: -1px;
				border: 1px solid #e8e8e8;
				cursor: pointer;
				z-index: 111111;
			}
			.widget-layer li:hover{
				z-index: 111111;
				border-color: #eb7350;
			} 	
	</style> 
 	<a class="trigger"   comid="commentInfo<s:property value='microblogid' />" href="javascript:;" style="font-size: 30px;">☺</a>
 	

 	
 	
 	<aside class="sub"><input type="submit" onclick="commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)" value="发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;布"/>
 	</aside>
 	<div class="clear"></div>
 </div>
 <section class="discussions">
	 <s:iterator value="irpMicrobloglist">
	            <div id='commentReply<s:property value="COMMENTID"/>' class="labs">
	            	<dl id="<s:property value='COMMENTID'/>div">
	                	<dt>
	                		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank">
	                			<s:if test="findUserByUserId(USERID).userpic!=null">
	                				<img id="microblogPersonalCard<s:property value='COMMENTID' />" alt="用户图片" width="55px" src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(USERID).userpic" />"   />
	                			</s:if>
	                			<s:else>
	                				<img id="microblogPersonalCard<s:property value='COMMENTID' />"  alt="用户图片" width="55px" <s:if test="findUserByUserId(USERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else>    />
	                			</s:else>
	                		</a>
	                		
	                	</dt>
	                	
	                    <dd class="text">
	                    	<div class="user">
	                    		<a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />" target="_blank"><s:property value="getShowPageViewNameByUserId(USERID)" /></a>
                    			<aside>
                    				<span><s:date name="CRTIME" format="yyyy-MM-dd HH:mm"/></span>
                    			
                    			</aside>
                    		</div>
	
	                        <p><s:property value="CONTENT" escapeHtml="false" /></p>
	                        <div class="share">
	                        	<aside>
	                        		<s:if test="getLoginUserId()==transpondIrpMicroblog(MICROBLOGID).userid">
	                        		<a href="javascript:void(0)" onclick="deleteMicroBlogComment(<s:property value='COMMENTID'/>,<s:property value='MICROBLOGID'/>)" id="deletecommentdivboolDetail<s:property value='COMMENTID'/>" >删除</a>
	                        		</s:if>
	                        		<a href="javascript:void(0)" onclick="replyCommentReply(<s:property value="MICROBLOGID"/>,'<s:property value="getShowPageViewNameByUserId(USERID)"/>','<s:property value="CONTENT.replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('/', '//'+'/');"/>',<s:property value="COMMENTID"/>)">回复</a>
	                        	</aside>
	                        </div>
	                    </dd>
	                    <dd class="clear"></dd>
	                </dl>
	            </div>
	 </s:iterator>
 </section>
 <div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
 
 
 
 