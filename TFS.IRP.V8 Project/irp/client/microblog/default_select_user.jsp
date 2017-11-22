<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script type="text/javascript">
//增加关注
function okFocus(_userid){
	var focusdiv = "#focus"+_userid;
	
    if($(focusdiv).text()=='+关注'){	
		$.ajax({
			type:"post",
			url:"<%=rootPath%>microblog/okMicroblogFocus.action",
			data:{
				userid:_userid
			},
			success:function(msg){
				if(msg==1){
				   	$(focusdiv).text("-已关注");
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
				   	$(focusdiv).text("+关注");
				}
			}
		});
		
	}	
}
   
</script>   
<s:if test="RecommentList.size!=0">
  <s:iterator value="RecommentList">
  <input type="text" name="defaultallfocusid" value="<s:property value="USERID" />" style="display: none;" />
  <div  class="jinzhao2" style="width: 210px;height: 110px;float: left;" >
  <p>
   <span>
   
 <a target="_bank"	 href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
    <s:if test="findUserByUserId(USERID).userpic!=null">
      <img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='findUserByUserId(USERID).userpic' />" alt="用户图片" width="60px" style="margin-left: 5px; margin-top: 5px;float: left;"  />
    </s:if>
    <s:elseif test="findUserByUserId(USERID).userpic==null">
      <img <s:if test="findUserByUserId(USERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户图片" width="60px" style="margin-left: 5px; margin-top: 5px;float: left;" />
    </s:elseif>
     </a>
     
   </span>
   <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> " >
     <span style="margin-left: 5px;float: none; ">
       <s:property value="getShowPageViewNameByUserId(USERID)" />
     </span>
     </a>
     <br/>
     <span style="margin-left: 5px;float: none;">
        <s:if test="findUserByUserId(USERID).expertintro.length()>=10"> 
       <a title="<s:property value="findUserByUserId(USERID).expertintro" />">
       <s:property value="findUserByUserId(USERID).expertintro.substring(0,10)" />...&nbsp;
       </a>
       </s:if>
       <s:else>
          <a title="<s:property value="findUserByUserId(USERID).expertintro" />">
       <s:property value="findUserByUserId(USERID).expertintro" />&nbsp;
       </a>
       </s:else>
     </span><br/>
     <span style="float: none;margin-left: 5px;">
                              <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> ">微知</a><s:property value="getMicrCountOfUserid(USERID)" />
                              <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=2">粉丝</a><s:property value="getFusCountOfUserid(USERID)" />
                              <a target="_bank" class="linkc12" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=1">关注</a><s:property value="getFocusCountOfUserid(USERID)" />
     </span>
   </p>
   <div style="float: right;margin-right: 10px;">
    <p style="background-color:#EAFDF5; text-align: center; width: 100px;">
    <s:if test="USERID not in allUseridByFocusUserId">
      <a class="STYLE1" style="font-size: 15px;margin-right: 10px;" href="javascript:void(0)" onclick="okFocus(<s:property value="USERID" />)" id="focus<s:property value="USERID" />" >+关注</a>
    </s:if>  
    <s:elseif test="USERID in allUseridByFocusUserId">
      <a class="STYLE1" style="font-size: 15px;margin-right: 10px;" href="javascript:void(0)" onclick="okFocus(<s:property value="USERID" />)" id="focus<s:property value="USERID" />" >-已关注</a>
    </s:elseif>
     </p>
   </div>
  </div>
  </s:iterator>
</s:if>
<s:else>
<div>暂无好友信息</div>

</s:else>