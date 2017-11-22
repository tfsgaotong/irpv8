<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.tfs.irp.util.LoginUtil"%>
<%@ page import="com.tfs.irp.user.entity.IrpUser"%>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	IrpUser loginUser = LoginUtil.getLoginUser();
	long loginuserid = LoginUtil.getLoginUserId();
%>
<div class="reorder">
		<section class="classify">
	       	<!-- <div class="title"><p><em style="padding:7px 10px;">主板</em>&nbsp;-&nbsp;详细筛选</p></div> -->
	           <div class="items">
	               <table width="100%">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">时间：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="paramMap!='WEEK' & paramMap !='MONTH' & paramMap !='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user')">全部</strong>	<strong <s:if test="paramMap=='WEEK'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user','WEEK')">最近一周</strong><strong <s:if test="paramMap=='MONTH'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user','MONTH')">最近一月</strong><strong <s:if test="paramMap=='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user','YEAR')">最近一年</strong>
	                       </td>
	                   </tr>
	                    <input type="hidden" id="time" value="<s:property value="paramMap"/>">
	                    <input type="hidden" id="sort" value="<s:property value="searchsort"/>">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">排序：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="searchsort!=1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user','2')">相关度</strong>	<strong <s:if test="searchsort==1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_user','1')">时间</strong>
	                       </td>
	                   </tr>
	               </table>
	               <section class="hidden">
	               </section>
	               <%-- <div class="getMore"><span>更多选项<em>（AMD芯片，板型/接口，性能，大家说)</em></span></div> --%>
	               <script>
					/* $(document).ready(function(){
						$(".getMore").click(function(){
							$(".hidden").slideToggle("slow");
						  });
					}); */
				</script>
	           </div>
	       </section>
       <div class="area20"></div>
  	 </div>
<s:if test="userlist.size()>0">
  	 <%-- <section class="resultList"> --%>
  	<%--  <div class="reorder"><span>排序字段：</span><em class="current">时间</em><em>相关度</em></div> --%>
	   <section class="weibo" style="margin-left:15px;">
		<section class="discussions" id="microblogcontent">
			<s:iterator value="userlist">
				<%-- <article class="item" id="<s:property value='USERID' />div">
		          	<aside>
						<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString()' /> "  >
                			<s:if test="getIrpUserByUserid(USERID.toString()).userpic!=null">
               				<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString()' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUserid(USERID.toString()).userpic' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString()' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID.toString()' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='MICROBLOGID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
               			</a>
		          	</aside>
		              <section>
		              	<p style="max-height:123px;" class="ellipsis">
		              		<span style="float:left"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID.toString()' /> "  > <s:property value="showPageName(USERID.toString())" /></a>
		              		<s:if test="USERID.toString()==2"><img style="float: left;margin-top: 1px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if></span><br/>
		              		邮箱:&nbsp;&nbsp;<s:property value="EMAIL"/>
		             		</p>
	              		<div class="date">
						</div>
	          	    </section>
	      	        <div class="clear"></div>
				</article> --%>
				<div id="<s:property value='USERID'/>div" class="labs" >
            	<dl>
                	<dt>
                		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID'/> ">
                			<s:if test="getIrpUserByUserid(USERID).userpic!=null">
               				<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='USERID' />" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUserid(USERID).userpic' />" alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" />
                			</s:if>
                			<s:else>
                			<img onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" id="microblogPersonalCard<s:property value='USERID' />" <s:if test="SEX==2">src="<%=rootPath%>view/images/female.jpg"</s:if><s:else>src="<%=rootPath%>view/images/male.jpg"</s:else> alt="用户头像" onmouseover="personalCard(<s:property value='MICROBLOGID' />,<s:property value='USERID' />)" onmouseout="personalCardOut(<s:property value='MICROBLOGID' />)" >
                			</s:else>
               			</a>
                		<aside></aside>
               		</dt>
                    <dd class="text" style="height:80px;">
                    	<div class="user"><a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> "  > <s:property value="showPageName(USERID)" /></a><s:if test="USERID==2"><img style="float: left;margin-top: 5px;" alt="微知助手" src="<%=rootPath %>view/images/mobile-attach-4.png"></s:if><aside style="right: 11px;"></aside>
                    		<span style="float:right;">
				             <s:if test="USERID in allUseridByUserid(loginUserid) && loginUserid in allUseridByUserid(USERID)">
				                <label  id="eachFocus_01<s:property value="USERID"/>" >互相关注</label><s:if test="USERID!=2">(<a href="javascript:void(0)" onclick="cancelEachFocus(<s:property value="USERID" />,<s:property value="loginUserid" />)"  ><label id="eachFocus_02<s:property value="USERID"/>">取消</label></a>)</s:if>                                         
				             </s:if>
				             <s:elseif test="USERID in allUseridByUserid(loginUserid)">
				               <label  id="alreadyFocus_01<s:property value="USERID"/>" >已关注</label><s:if test="USERID!=2">(<a href="javascript:void(0)" onclick="cancelFocus(<s:property value="USERID" />,<s:property value="loginUserid" />)"  ><label id="alreadyFocus_02<s:property value="USERID"/>" >取消</label></a>)</s:if>
				             </s:elseif>
				             <s:elseif test="USERID not in allUseridByUserid(loginUserid)">
				               <label  id="notFocus_01<s:property value="USERID"/>" >未关注</label>(<a href="javascript:void(0)" onclick="okFocus(<s:property value="USERID" />,<s:property value="loginUserid" />)" ><label id="notFocus_02<s:property value="USERID"/>">关注</label></a>)
				             </s:elseif>
    		       	 	</span>
    		       	 	<p style="text-align:left;margin-right:11px;">&nbsp;<s:property value="findUserInfoById(USERID,1)"/></p>
    		       	 	<div style="margin-top:3px;">
  			        	<span>
				          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=1 ">关注(<s:property value="MicroblogFocusCountUserid(USERID)" />)</a> 
				          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' />&focusonfus=2 ">粉丝 (<s:property value="MicroblogFocusCountFocusUserid(USERID)" />)</a> 
				          <a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='USERID' /> ">微知(<s:property value="MicroblogCountByUserid(USERID)" />)</a>     				          
				       </span>  
				       </div>
                    	</div>
                    	<div>
                    	</div>
                    </dd>
                    <dd class="clear"></dd>
                </dl>
            </div>
		</s:iterator>
	<div class="area20"></div>
	<div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
	</section>
	</section>
	<%-- </section> --%>
</s:if>
<s:else>
	<div style="height: 250px;" >没有找到相关记录</div>
</s:else>