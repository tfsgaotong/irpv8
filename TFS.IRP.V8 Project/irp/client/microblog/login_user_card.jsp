<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<script type="text/javascript">
/**
 * 链接
 */
function linkPersonalTabMicro(_tabvalue){
	window.location.href="<%=rootPath%>site/client_to_index_person.action?personid=<%=LoginUtil.getLoginUserId()%>&personaltabval="+_tabvalue;	
}
</script>
　<s:iterator value="loginUserList">   
    <div class="rw">
    	<dl>
    	  <dt>
    	   <s:if test="USERPIC==null">
    	    <img alt="用户图片" <s:if test="SEX==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> />
    	   </s:if>
    	   <s:elseif test="USERPIC!=null">
    	    <img alt="用户图片" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='USERPIC' />" width="55px" />
    	   </s:elseif>
    	    
    	  </dt>
    	  <dd>
    	    <span class="linkb14" title="<s:property value='SHOWNAME' />">
    	    <s:if test="SHOWNAME.length()>7">
    	    	<s:property value='SHOWNAME.toString().substring(0,7)' />
    	    </s:if>
    	    <s:else>
    	    	<s:property value='SHOWNAME' />
    	    </s:else>
    	    </span><br/>
    	    <span>
    	    <s:if test="getIrpGroup(USERID).size==0">
    	    
    	  &nbsp;<br/>&nbsp;
    	    </s:if>
    	    <s:if test="getIrpGroup(USERID).size>=2">
    	    <s:iterator value="getIrpGroup(USERID)" var="array" status="stat" > 
               <a title="<s:property value="getIrpGroup(USERID).toString().replace('[',' ').replace(']',' ').replace(',',' ')" escapeHtml='false' />" >
               <s:if test="%{(#stat.index<2)}">
                <s:if test="#array.length()>8">
                 <s:property value="#array.toString().substring(0,7)" />...&nbsp;<br/>
                </s:if>
                 <s:else>
                  <s:property value="#array" />&nbsp;<br/>
                 </s:else>
               </s:if>
                </a>
               </s:iterator>
               </s:if> 
               <s:if test="2>getIrpGroup(USERID).size>=1">
               <s:iterator value="getIrpGroup(USERID)" var="array" status="stat" > 
               <a title="<s:property value="getIrpGroup(USERID).toString().replace('[',' ').replace(']',' ').replace(',',' ')" escapeHtml='false' />" >
               <s:if test="%{(#stat.index<2)}">             
                 <s:if test="#array.length()>8">
                 <s:property value="#array.toString().substring(0,7)" />...&nbsp;<br/>&nbsp;
                </s:if>
                 <s:else>
                  <s:property value="#array" />&nbsp;<br/>&nbsp;
                 </s:else>
               </s:if>
                </a>
               </s:iterator>                             
               </s:if> 
    	    </span>
          </dd>
    	</dl>
        <ul style="margin-bottom: 5px;">
        	<li style="cursor: pointer;" onmouseover="this.style.cursor='hand'"  id="mytougao" _data="crtimesort=desc" onclick="tabs(this)" _href="<%=rootPath%>site/myalltougaodocument.action"><span class="fontbiaoti_m" ><label id="mydocumentcount"><s:property value='KNOWLEDGECOUNT' /></label></span><br/>投稿</li>
            <li style="cursor: pointer;" onmouseover="this.style.cursor='hand'"   onclick="tabsPersonalLink()"><span class="fontbiaoti_m" ><label id="mymicroblogcount"><s:property value='MICROBLOGCOUNT' /></label></span><br/>微知</li>
            <li style="cursor: pointer;" onmouseover="this.style.cursor='hand'"  id="document_collection" onclick="tabs(this)"  _href="<%=rootPath%>site/myalldoccollection.action"  _data="crtimesort=desc"><span class="fontbiaoti_m" ><label id="mydoccollectioncount"><s:property value='MICROBLOGCOLLECTCOUNT+KNOWLEDGECOLLECCOUNT' /></label></span><br/>收藏</li>
        </ul>
    </div>
    </s:iterator>