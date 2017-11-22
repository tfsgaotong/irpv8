<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    


<div class="zj_title1">
	<div class="zj_tt"><h1>精华知识</h1></div><div class="zj_more">
		<a href="javascript:void(0);" onclick="window.location.reload(true)">return</a>
	</div></div> 
	<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
	 <s:iterator value="chnlDocLinks">
  <div class="zj_box3">
            	 <table cellpadding="0" cellspacing="0"  border="0" class="boxcenter mod_text " >
                	<tr>
                    	<td width="133">
                    	<h1>  <a onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"  href="javascript:void(0)" style="text-decoration: none;">
                    	    <img  src="<s:property value='docCoverPath(docid,docflag)'/>" width="160"  height="120" border="0" />
        			   	</a>
        			    </h1> </td>
                        <td width="20"></td>
                        <td width="527" valign="top">
                        <div style="width:520px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"> 
	                        	<a onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"  href="javascript:void(0)" style="text-decoration: none;">
	                        	<h3 >
	                        		<s:property value="doctitle"/>
	                        	</h3>
	                        	</a>
                        	</div>
                            <h4>
                            	创建者：<a target="_blank" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='cruserid'/>"><s:property value="cruser"/></a>
                         	   	发布时间：<s:date name="%{crtime}" format="yyyy年MM月dd日" />      
                         	   	（已浏览<b><s:property value="hitscount"/> </b>次）<br />
                         	   	标签：  
                         	 	<s:iterator value="document.dockeywords.split(',')"  status="st" var="as">
                         	 	<a style="color: black;" href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12">
                         	 		<s:property value="#as"/>
                         	 	</a>&nbsp;&nbsp;
  								</s:iterator>
  								<br />
  								推荐专家：
  								<s:iterator value="irpRoles" status="rs">
  									<b><a target="_blank"  href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='userid'/>"><s:property value="username"/></a></b>
  									<s:if test="#rs.count!=irpRoles.size()">、 </s:if>
  								</s:iterator>
							</h4> 
							<h4>
								<p title="<s:property value='document.docabstract'/>" ><%--18*5=90 --%> <%--style="text-indent:1em;" --%> 
									<div style="width:400px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"> 
									<a  onclick="showdocumentinfo(<s:property value='docid'/>)"  href="javascript:void(0)" style="text-decoration: none;">
									    <s:property value='document.docabstract'/> 
									 </a>
									 </div>
								</p>
							</h4>
						 </td>
                    </tr>
                </table> 
            </div> 
	 </s:iterator>
		 <div class="main" align="right">
			    <div class="left">
				      <div class="fyh">
				       	<ul><s:property value="pageHTML" escapeHtml="false" /></ul>
				      </div>
			    </div>
		</div>
	 </s:if> 
	 <s:else>
		<div class="zj_box3" style="padding-left: 10px;">
	 		暂时没有精华知识...
	 	</div>
	 </s:else>
	 <script type="text/javascript">
<!--
function page(pagenum){
	 str=$.ajax({
			type:'post',
			url:'<%=rootPath%>site/jinghuadocumentlist.action?pageNum='+pagenum+'&id=<s:property value="id"/>', 
			dataType: "json",
			async: false,
		    cache: false 
			}).responseText;  
	   $('#documentlistdiv').html(str);  
}
//-->
</script>

