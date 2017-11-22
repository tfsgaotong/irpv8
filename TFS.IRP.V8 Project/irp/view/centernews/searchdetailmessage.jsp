<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
	<div id="detailmessdivsearch"  class="weibo">
	<p><a  href="javascript:void(0)"style=" float: left;font-size: 15px; "class="linkc12"  onclick="receiveMessageList()" >&lt;&lt;返回</a></p>
	<br/>
	<div class=" maintext" style="float: left;">
		<textarea  onkeyup="microblogInfoControl(this.value)" id="replyMessageInfo" rows="4" style=" resize: none;  " ></textarea>
		<div style="float: left;height: 10px;width: 675px;"></div>
		<aside class="sub" style="width:704px;float: left;">
			<section id="microblogContentprompt_01"   >您还可以输入<label id="microblogContentCount_01" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;" ><s:property value="messagedetailcount" /></label>个字</section>
			<section id="microblogContentprompt_02" style="display: none;">超出了<label id="microblogContentCount_02" style="font-family:Tahoma,Arial,sans-serif; font-size: 18px;color:red;" ></label>个字</section>
			<input id="messagecountnumconf" type="text" style="display: none;" value="<s:property value="messagedetailcount" />">
			<input type="text" id="messagedetailnum" value="<s:property value='messagedetailcount' />" style="display:none; " />
							
		</aside>
		<a class="zhuanz1" href="javascript:void(0)" style=" float: left;"   onclick="messagePersonalReply(<s:property value='cruserid' />)" >回复</a>
		<div style="float: left;height:30px;width: 675px;"></div>
	</div> 
  
  	

   	    
   
    
    
    <div id="replycommentpersonaldiv" style="margin-top: 20px;">
    <s:iterator value="irpMessageContentlist"> 
     	<ul class="myDiscuss list5" id="<s:property value='MESSAGEID' />div">
     		<li style="margin-top: 154px; margin-bottom: -129px;">
    			<s:date name="CRTIME" format="yyyy-MM-dd HH:mm" />
    			<div class="userIcon" >
			         <s:if test="findIrpUserByFocusId(CRUSERID).userpic!=null">
			          	<img style="border-radius: 20px;" width="60" height="60" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findIrpUserByFocusId(CRUSERID).userpic' />" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='MESSAGEID' />)" onmouseout="personalCardOut(<s:property value='MESSAGEID' />)"   >
			         </s:if>
			         <s:elseif test="findIrpUserByFocusId(CRUSERID).userpic==null"> 
			          	<img style="border-radius: 20px;" width="60" height="60" <s:if test="findIrpUserByFocusId(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片"    onmouseover="personalCard(<s:property value='fromUid' />,<s:property value='messageid' />)" onmouseout="personalCardOut(<s:property value='messageid' />)" onmouseover="personalCard(<s:property value='CRUSERID' />,<s:property value='MESSAGEID' />)" onmouseout="personalCardOut(<s:property value='MESSAGEID' />)"  />
			         </s:elseif>
	         	</div>
		        <section>
		        	<aside></aside>
		        	<div style="width: 668px;">
		        	 	<s:property value="CONTENT" escapeHtml="false" />
		        	 	<p>
		        	 		<a target="_blank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID' /> " >
		        	 			<s:property value="getShowPageViewNameByUserId(CRUSERID)" />
		        	 		</a>
		        	 		<a style="margin-left: 569px;" class="linkc12" href="javascript:void(0)" onclick="deleteMessageInfo(<s:property value='MESSAGEID' />)">
		        	 			删除
		        	 		</a>
		        	 	</p>
		        	</div>
		        </section> 
     		</li>
   	 	</ul>
    </s:iterator>
    </div>
    
      <div class="pages" style="margin-top: 150px;"><s:property value="pageHTML" escapeHtml="false" /></div>
   	
   	</div>