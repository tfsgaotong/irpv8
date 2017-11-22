<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
$(function(){
	var commentdiv = "#microCommentFontCount_01"+'<s:property value="microblogid"/>';
	$(commentdiv).text(microblogfineandlooknumber);
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
	    	$.ajax({
	    		type:"post",
	    		url:"<%=rootPath%>microblog/microblogCommentFineAndLook.action",
	    		cache:false,
	    		async:false,
	    		data:{
	    		 replyuserid:_userid,
	    		 replaymicroblogid:_microblogid,
	    		 replaycontent:commentInfo,
	    		 replycommentidpage:replycommentidpage
	    		},
	    		success:function(callback){
	    			var commentdiv = "#microCommentFontCount_01"+'<s:property value="microblogid"/>';
	    			$(commentdiv).text(microblogfineandlooknumber);
	    			if(callback!=null){
	    				$(microblogcommentcount).text(parseInt($(microblogcommentcount).text())+1);
	    				$(commentInfoid).val("");
	    				replycommentidpage=0;
	                   $('#CommentFineandlook').prepend(callback);
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
  <div class="fyh">
   <p style="width: 660px;"><s:property value="transpondIrpMicroblog(microblogid).microblogcontent" escapeHtml="false"  /></p>
   <p>
    
     <div style="width: 660px;height: 20px;text-align: right;">
      	<font style="float: left;"><s:date name="transpondIrpMicroblog(microblogid).crtime" format="yyyy-MM-dd HH:mm"   /></font>
            <a class="linkc12" href="javascript:void(0)" onclick="transpond('<s:property value="getShowPageViewNameByUserId(userid)"  />',<s:property value="microblogid"  />,<s:property value="userid"  />,<s:property value="transpondIrpMicroblog(microblogid).transpondid"  />)">转发(<label id="microblogtranspondcount<s:property value='microblogid' />" style="color: rgb(135, 173, 88);"><s:property value="transpondIrpMicroblog(microblogid).transpondcount"  /></label>)</a>
            <a class="linkc12" style="margin-left: 5px;" href="javascript:void(0)" >评论(<label id="microblogcommentcount<s:property value='microblogid' />"
					style="color: rgb(135, 173, 88);"><s:property value="transpondIrpMicroblog(microblogid).commentcount"  /></label>)</a>
					
			<s:if test="microblogid in collectionOfUseridlist">
			  <a class="linkc12" href="javascript:void(0)" id="<s:property value='microblogid' />" style="margin-left: 5px;" onclick="collect(<s:property value='microblogid' />)" >取消收藏</a>
			</s:if>
			<s:if test="microblogid not in collectionOfUseridlist">
			  <a class="linkc12" href="javascript:void(0)" id="<s:property value='microblogid' />" style="margin-left: 5px;" onclick="collect(<s:property value='microblogid' />)" >收藏</a>
			</s:if>
					
     </div>
     <span id="microCommentFont_01<s:property value='microblogid' />"  style="float: right;">还可以输入<label id="microCommentFontCount_01<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;"></label>个字</span>
	 <span id="microCommentFont_02<s:property value='microblogid' />"  style="float: right; display: none;">已超出<label id="microCommentFontCount_02<s:property value='microblogid' />" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;"></label>个字</span>
     <span>
       <textarea style="width: 676px;background-color:	rgb(246, 246, 246);" rows="4" onkeyup="microblogCommentFontInfo(<s:property value='microblogid' />)"  id="commentInfo<s:property value='microblogid' />" ></textarea>
       <br/>
       
       
       <a id="microfineandlook" class="zhuanz1" style="margin-left: 600px;" href="javascript:void(0)" onclick="commentReplyFineAndLook(<s:property value='microblogid' />,<s:property value='userid' />)">回复</a>
       
       
       
     </span>
   </p> 
   </div>
   <div id="CommentFineandlook">
   
   </div>
    <s:iterator value="irpMicrobloglist">
    <div class="fyh" id='commentReply<s:property value="COMMENTID"/>' >
   <dl id="<s:property value='COMMENTID'/>div" onmouseover="deleteCommentOverDetail(<s:property value='COMMENTID'/>)" onmouseout="deleteCommentOutDetail(<s:property value='COMMENTID'/>)">
       <dt >
        
         <s:if test="findUserByUserId(USERID).userpic!=null">
        <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> ">  
          <img  id="microblogPersonalCard<s:property value='COMMENTID' />"
          alt="用户图片" width="55px" height="55px" 
           src="<%=rootPath%>file/readfile.action?fileName=<s:property value="findUserByUserId(USERID).userpic" />" onmouseover="personalCard(<s:property value='COMMENTID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='COMMENTID' />)" />
         </a>
         </s:if>
         <s:elseif  test="findUserByUserId(USERID).userpic==null">
         <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> ">
          <img  id="microblogPersonalCard<s:property value='COMMENTID' />" 
          alt="用户图片" width="55px" height="55px"  
          <s:if test="findUserByUserId(USERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> onmouseover="personalCard(<s:property value='COMMENTID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='COMMENTID' />)"  /> 
         </a>
         
         </s:elseif>
      
         
       </dt>
       <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />"><s:property value="getShowPageViewNameByUserId(USERID)" /></a>
       <dd><s:property value="CONTENT" escapeHtml="false" /></dd>
       <dd>
       <label style="font-size: 12px;"><s:date name="CRTIME" format="yyyy-MM-dd HH:mm"/></label>
       <a class="linkc12"  style="float: right;" href="javascript:void(0)"  onclick="replyCommentReply(<s:property value="MICROBLOGID"/>,'<s:property value="getShowPageViewNameByUserId(USERID)"/>','<s:property value="CONTENT"/>',<s:property value="COMMENTID"/>)" >&nbsp;回复</a>
       
       <s:if test="getLoginUserId()==transpondIrpMicroblog(MICROBLOGID).userid">
        <a class="linkc12"  style=" visibility: hidden;float: right;" id="deletecommentdivboolDetail<s:property value='COMMENTID'/>"  href="javascript:void(0)" onclick="deleteMicroBlogComment(<s:property value='COMMENTID'/>,<s:property value='MICROBLOGID'/>)">删除&nbsp;</a>
        </s:if>
      </dd>  
      
   </dl>

   </div>  
     
    </s:iterator>
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul>
    