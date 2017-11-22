<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<s:if test="irpMicroblogs.size()>0">
    <s:iterator value="irpMicroblogs">
	   <dl>
           <dt>
           <s:set var="user" value="getIrpUserByUsername(userid)" />
            <s:if test="#user.userpic!=null">
			    <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
				    <img  src="<%=rootPath %>file/readfile.action?fileName=<s:property value='#user.userpic' />" alt="用户头像" width="55px" />
		        </a>						
            </s:if>
            <s:if test="#user.userpic==null">			
			     <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> " >
			        <s:if test="#user.sex==2">
			            <img  src="<%=rootPath %>client/images/female.jpg" alt="用户头像" width="55px"  />
			        </s:if>
				   <s:else>
				        <img src="<%=rootPath %>client/images/male.jpg" alt="用户头像" width="55px" />
				   </s:else>
			    </a>
			</s:if>  
          </dt>
          <dd>
           <a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='userid' /> "  class="linkb14"><s:property value="#user.username" /></a>：
                 <s:property value="microblogcontent" escapeHtml="false" />
                <span><s:date name="crtime" format="yyyy-MM-dd"/>&nbsp;来自<s:property value="fromdata" /> <br/></span>
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
