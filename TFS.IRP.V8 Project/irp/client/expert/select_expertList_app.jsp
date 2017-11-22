<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!-- 
.cardList li table td{
	border: solid 1px #ffffff;
}
 -->
<style>
.ellipsis {
    table-layout: fixed;
}

 td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.cardList {
	width: 460px;
	padding-top:10px;
}
.cardList li {
	width: 210px;
	float: left;
	display: inline;
	margin: 5px 10px;
	position: relative;
}
.cardList li table {
	background-color: #ffffff;
	border:1px solid #c1ddf2;
	height:100px;
	width:210px;
	cursor: pointer;
}

.cardList .selectedExpert {
	border:1px dashed #000000;
}

</style>
<script type="text/javascript">
function checkExpert(_domObj){
	var tabObjs = $('.cardList').find('table');
	$.each(tabObjs,function(i,n){
		if(n==_domObj){
			$(n).attr('class','selectedExpert ellipsis olbg11');
		}else{
			$(n).attr('class','ellipsis olbg11');
		}
	});
}
</script>
<form id="selectExportForm">
<s:hidden name="categoryId" />
<s:hidden name="pageNum" />
<s:hidden name="pageSize" />
<s:hidden name="searchWord" />
<s:hidden name="searchType" />
</form>
<s:if test="expertList.size!=0">
<ul class="cardList" style="width: 200px">
<s:iterator value="expertList">
	<li>
		<table onclick="checkExpert(this)" align="center" cellpadding="10" cellspacing="0" _eid="<s:property value='userid' />" _ename="<s:property value='showName'/>" class="ellipsis olbg11">
			<tr style="width:210px;">
				<td rowspan="3" width="67px" style="padding-top: 5px; padding-left: 2px;">
			   	<a  target="_blank" title="查看专家详细信息">
					<s:if test="userpic!=null">
					<img style="width:67px" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='userpic' />" alt="用户图片" />
					</s:if>
					<s:else>
						<s:if test="sex==2">
					<img src="<%=rootPath %>client/images/female.jpg" alt="用户图片" width="67px" />
						</s:if>
						<s:else>
					<img src="<%=rootPath %>client/images/male.jpg" alt="用户图片" width="67px" />
						</s:else>
					</s:else>		
				</a>
			    </td>
				<td valign="top" >
					<font class="linkc12" title="<s:property value='showName' />" style="padding-top: 2px;">
						&nbsp;姓名:&nbsp;&nbsp;<s:property value="showName" />
					</font>
				</td>
			</tr>
			<tr>
				<td>
				   <font class="linkc12" >&nbsp;性别: &nbsp;<s:if test="sex==2">女</s:if><s:else>男</s:else> </font>
				</td>
			</tr>
			<tr>
				<td>
				   <font class="linkc12" >&nbsp;电话: &nbsp;<s:property value="tel"/></font>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:left;margin:0px 5px;padding:0px 5px;margin-top:0px;">
				   <font class="linkc12" title="<s:property value='email'/>">邮箱: &nbsp;<s:property value="email"/></font>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:left;margin:5px;padding:5px;margin-top:0px;">
					<font class="linkc12" title="<s:property value='expertintro'/>">简介: &nbsp;<s:property value="expertintro"/></font>
				</td>
			</tr>
		</table>
	 </li>
</s:iterator>
</ul>
<div style="clear:both" ></div>
<div class="main">
	<div class="left">
		<div class="fyh" style="width: 450px;">
			<ul style="width: 450px;"><s:property value="pageHTML" escapeHtml="false" /></ul>
		</div>
	</div>
</div>
</s:if>
<s:else>
     暂无专家
</s:else>		    
    