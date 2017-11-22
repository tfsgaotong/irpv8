<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
/**
 * 链接
 */
function linkPersonalTabMicro(_tabvalue){
	window.location.href="<%=rootPath%>site/client_to_index_person.action?personid=<%=LoginUtil.getLoginUserId()%>&personaltabval="+_tabvalue;	
}
</script>
<s:iterator value="loginUserList" var="loginUserListvar">  
<div class="userInfo" style="border-bottom: 1px dashed #BDBDBD;">
   	<dl class="info" style="border-bottom: none;">
      	<dt class="leftBox"><a href="#" target="_blank"><s:if test="USERPIC==null"><img style="width: 55px;margin: 5px;border-radius: 30px;" alt="用户图片" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> /></s:if><s:elseif test="USERPIC!=null"><img style="width: 55px;margin: 5px;border-radius: 30px;" alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />"  /></s:elseif></a></dt>
        <dd class="leftBox"><p><a href="#" target="_blank"><s:property value="SHOWNAME" /></a>
        <p>   
                           知识&nbsp;:&nbsp;<em><s:property value='KNOWLEDGECOUNT.toString().replace(".0","")'/></em>&nbsp;
                            微知&nbsp;:&nbsp;<em id="mymicroblogcount"><s:property value='MICROBLOGCOUNT.toString().replace(".0","")' /></em>&nbsp;
           <s:set value="MICROBLOGCOLLECTCOUNT+KNOWLEDGECOLLECCOUNT" var="scnums"></s:set>                 
       	       收藏&nbsp;:&nbsp;<em id="mydoccollectioncount"><s:property value='#scnums.toString().replace(".0","")' /></em></p>
       
        	<s:iterator value="getIrpGroup(USERID)" var="array" status="stat" > 		
      			 <p><a title="<s:property value="#array" />" >

        			[<s:property value="#array" />]
        		
        		</a></p>
        	</s:iterator>  
        </dd>
        <dd class="clear"></dd>
    </dl>
</div>
</s:iterator>