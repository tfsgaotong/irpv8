<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<style>
.linkc12{
margin: 0px;
padding: 0px;
list-style: none;
line-height: 190%;
font-size: 12px;
color: #666;
font-family: "微软雅黑", "黑体";
}
.linkc13{
font-size: 12px;
line-height: 190%;
color: #666;
font-family: "微软雅黑", "黑体";
}

.linkc14{
line-height: 190%;
color: #666;
font-family: "微软雅黑", "黑体";
text-decoration: none;
color: #333;

}
.linkc14:hover{
line-height: 190%;
color: #666;
font-family: "微软雅黑", "黑体";
text-decoration: underline;
	color: #5f9ddd;
}
</style>
<div class="reorder">
		<section class="classify">
	           <div class="items">
	               <table width="100%">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">时间：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="paramMap!='WEEK' & paramMap !='MONTH' & paramMap !='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que')">全部</strong>	<strong <s:if test="paramMap=='WEEK'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que','WEEK')">最近一周</strong><strong <s:if test="paramMap=='MONTH'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que','MONTH')">最近一月</strong><strong <s:if test="paramMap=='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que','YEAR')">最近一年</strong>
	                       </td>
	                   </tr>
	                   <input type="hidden" id="time" value="<s:property value="paramMap"/>">
	                    <input type="hidden" id="sort" value="<s:property value="searchsort"/>">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">排序：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="searchsort!=1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que','2')">相关度</strong>	<strong <s:if test="searchsort==1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','rad_que','1')">时间</strong>
	                       </td>
	                   </tr>
	               </table>
	               <section class="hidden">
	               </section>
	           </div>
	       </section>
       <div class="area20"></div>
  	 </div>     
 <script type="text/javascript">
function hits(QUESTIONID){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>question/hits.action",
		cache:false,
		async:false,
		data:{
			questionid:QUESTIONID
		}
	});
}
/**
 *展开知识
 */
function showdocumentinfo(_docid){
	 	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid); 
	} 
//-->
</script>


<s:if test="questionlist.size()>0">
<s:iterator value="questionlist">
<s:set var="attachList" value="findPicList(QUESTIONID)" />
	<table width="100%" bordercolor="#000000"
		style="border:0;border-bottom:1px dashed #eee; padding-bottom:5px; padding-top:5px;"
		onmousemove="javascript:this.style.background = '#f6f6f6'"
		onmouseout="javascript:this.style.background = '#fff'"
		id="questionDiv<s:property value="QUESTIONID"/>" >
		<tbody onmouseover="questionDeleteJudge(<s:property value='QUESTIONID' />)" onmouseout="questionDeleteJudgeOut(<s:property value='QUESTIONID' />)">
			<tr>
				<!-- 头像 -->
				<td rowspan="2" valign="top" align="center" width="80" cellpadding="10px">
				<s:if test="getIrpUserByUserid(CRUSERID).userpic!=null">
				  <img src="<%=rootPath %>file/readfile.action?fileName=<s:property value="getIrpUserByUserid(CRUSERID).userpic" />" width="55px" alt="用户图片" />
				</s:if>
				<s:else>
				 <s:if test="getIrpUserByUserid(CRUSERID).sex==2">
				   <img src="<%=rootPath%>client/images/female.jpg" alt="用户图片" width="55px" />
				 </s:if>
				 <s:else>
				   <img src="<%=rootPath%>client/images/male.jpg" alt="用户图片" width="55px" />
				 </s:else>
				</s:else>
				</td>
				<!-- 标题 -->
				<td width="550" align="left" style="font-size: 14px;">
				 <a class="linkc14" onclick="hits(<s:property value="QUESTIONID" />)" target="_blank" href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="QUESTIONID" />">
                             <s:if test="TITLE!=null">
                             	<s:property value="TITLE.toString().substring(1,TITLE.toString().length()-1)" escapeHtml="false" />
                             </s:if>
                             <s:else>
                             	<s:property value="HTMLCONTENT.toString().substring(1,HTMLCONTENT.toString().length()-1)" escapeHtml="false" />
                             </s:else>
                           	 </a>
						</td>
			</tr>
			<tr>
				<td >
				<s:set var="que" value="findQuestionById(QUESTIONID)"></s:set>
				    <font class="linkc12"> <s:if test="#que.status==0">未解决</s:if> <s:else>已解决</s:else> &nbsp;.&nbsp;
			       <s:property value="getIrpUserByUsername(CRUSERID).username" />
				      &nbsp;.&nbsp; <s:date name="#que.crtime" format="yyyy-MM-dd" /></font></td>
			</tr>
			<tr>
			     <td colspan="3" class="linkc13">
	                 <div style="text-align: right;padding-right: 6%;">共<font color="#CC0000"><s:property value='findReplyCountByQuestionId(QUESTIONID)' default="0" /></font>条回答 共<font color="#EF8E0C"><s:property value='#que.browsecount' default="0" /></font>人浏览</div>		 
			     </td>
			</tr>
		</tbody>
	</table>
</s:iterator>
 <div class="area20"></div>
<!--分页开始 -->
<div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
</s:if>
<s:else>
<div style="height: 250px;" >没有找到相关记录</div>
</s:else> 	 
