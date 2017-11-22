<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>问答达人</title>  
  </head>
<%--   <s:if test="illquestionlist.size()>0">
  <s:iterator value="illquestionlist">
  	   <dd> <span><s:property value="USERCOUNT"/></span>
  	   <a href="javascript:void(0)"  onclick="client_to_index_person(<s:property value='CRUSERID'/>)" >
  	   <s:if test="getIrpUser(CRUSERID).userpic!=null">
  	   	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="getIrpUser(CRUSERID).userpic"/>" alt="" width="50px;" height="50px;"/>
  	   </s:if>
  	   <s:else>
  	   	 <img <s:if test="getIrpUser(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="" width="50px;" height="50px;"/>
  	   </s:else>
  	 <s:property value="getShowPageViewNameByUserId(CRUSERID)" />
	   </a>
	   
	  </dd>
	   <dd> 
  </s:iterator> 
  </s:if> --%>
  
  <s:if test="illquestionlist.size()>0">
  <s:iterator value="illquestionlist">
    <li class="swiper-slide">
            <div class="brain-item">
                 <a href="javascript:void(0)" class="brain-img-con"  onclick="client_to_index_person(<s:property value='CRUSERID'/>)" >
                   <div class="brain-img">
                      <s:if test="getIrpUser(CRUSERID).userpic!=null">
				  	   	<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value="getIrpUser(CRUSERID).userpic"/>" alt="" width="50px;" height="50px;"/>
				  	   </s:if>
				  	   <s:else>
				  	   	 <img <s:if test="getIrpUser(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="" width="50px;" height="50px;"/>
				  	   </s:else>
                   </div>
                </a>
                <div class="brain-info" style="text-align: center;">
                    <h3 class="brain-name">	 <s:property value="getShowPageViewNameByUserId(CRUSERID)" /></h3>
                    <div class="brain-info-txt">
                       <%--  <p><span class="c999">擅长领域：</span><span class="czly-brain" czly="[1040, 839, 1105]"></span></p> --%>
                       <s:set var="count" value="USERCOUNT+''"></s:set>
                        <p style="padding-top: 10px;"><span class="c999">好评数：</span><s:property value="#count"/></p>
                    </div>
                  <!--   <a href="/user/pcsee/51b1ab17e4b0f59475d2daed-IA-1.html" class="btn-brain" target="_blank">已给 2110 名用户提供帮助</a>
            -->     </div>
              <!--   <div class="brain-question">
                    <p><a href="/b/4HK64EG5u6.html" target="_bank">今天刚升级的91桌面，打开功能大全怎么是空的？没有内容跳出来</a></p>
                    <p><a href="/b/4HUbffMAzc.html" target="_bank">谁能教我手机截屏👉👈</a></p>
                </div> -->
            </div>
        </li>
         </s:iterator> 
  	</s:if>
  <script>
	function client_to_index_person(_personid){
		window.open('<%=rootPath%>/site/client_to_index_person.action?personid='+_personid,'_parent');
	}
  </script>
