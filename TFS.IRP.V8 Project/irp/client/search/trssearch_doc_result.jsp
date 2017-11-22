<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
 <s:if test="bloBs.size()>0">
   <s:iterator value="bloBs">
        <s:set var="user" value="getIrpUserByUsername(cruserid)" />
	    <dl>
        	<dt>
        	  <s:if test="#user.userpic!=null">
			      <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid' /> " >
				     <img  src="<%=rootPath %>file/readfile.action?fileName=<s:property value='#user.userpic' />" alt="用户头像" width="55px" />
		          </a>						
		      </s:if>
		      <s:if test="#user.userpic==null">			
			      <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid' /> " >
			        <s:if test="#user.sex==2">
			            <img  src="<%=rootPath %>client/images/female.jpg" alt="用户头像" width="55px"  />
			        </s:if>
				   <s:else>
				        <img src="<%=rootPath %>client/images/male.jpg" alt="用户头像" width="55px" />
				   </s:else>
			    </a>
		      </s:if></dt>
            <dd>
            <a href="javascript:void(0)" onclick="documentinfo_see(<s:property value="docid2" />)" class="linkb14"><s:property value="doctitle" escapeHtml="false" /></a>&nbsp;.&nbsp;<s:property value="cruser" />&nbsp;.&nbsp;<s:date name="crtime" format="yyyy-MM-dd"/> 
                           <br/>
                          标签:&nbsp;<s:iterator value="dockeywords.split(',')"  status="st" var="as">
  				 	     <a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;
  				     </s:iterator>
                <span>
                     	 <s:property value="doccontent" escapeHtml="false" />
                   <p>&nbsp;</p>
                </span>
             </dd>
	   </dl>
</s:iterator> 
<ul>
       <s:property value="pageHTML" escapeHtml="false" />
</ul> 
</s:if>
<s:else>
       <span>没有找到相关记录</span>
</s:else>