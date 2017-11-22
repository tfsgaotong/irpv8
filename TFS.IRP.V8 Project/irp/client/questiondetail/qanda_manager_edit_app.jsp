<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script type="text/javascript">
function hits(questionid){
	$.ajax({
		type:"post",
		url:"<%=rootPath%>question/hits.action",
		cache:false,
		async:false,
		data:{
			questionid:questionid
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

function exportToZip(){
	var now=new Date();
	var date=1900+now.getYear()+""+(now.getMonth()+1)+""+now.getDate()+""+now.getHours()+""+now.getMinutes()+""+now.getSeconds();
	var fileName=date;
	document.getElementById("daochu").href="ques"+fileName+".zip";
	
	var searchName = encodeURI($('#serachText').val());
	//var beginTime = $('#starttime').datebox('getValue');
	//var finishTime = $('#endtime').datebox('getValue');
	$.ajax({
		type:"post",
		url:"<%=rootPath%>question/exportToZip.action?searchName="+searchName+"&fileName="+fileName,
		async:false,
		success:function(){
			document.getElementById("daochu").click();
		}
        
	});	
	 
	
}
</script>
<s:if test="listQuestion.size()>0">

<s:iterator value="listQuestion">
<s:set var="attachList" value="findPicList(questionid)" />
	<table width="100%" bordercolor="#000000"
		style="border:0;border-bottom:1px dashed #eee; padding-bottom:5px; padding-top:5px;font-size:13px;"
		onmousemove="javascript:this.style.background = '#f6f6f6'"
		onmouseout="javascript:this.style.background = '#fff'"
		id="questionDiv<s:property value="questionid"/>" >
		<tbody>
			<tr>
				<!-- 头像 -->
				<td rowspan="2" valign="top" width="60px" cellpadding="10px">
				<s:if test="getIrpUserByUserid(cruserid).userpic!=null">
				  <img  style="margin-left:5px" src="<%=rootPath %>file/readfile.action?fileName=<s:property value="getIrpUserByUserid(cruserid).userpic" />&token=<s:property value="tokens" />" width="40px" alt="用户图片" />
				</s:if>
				<s:else>
				 <s:if test="getIrpUserByUserid(cruserid).sex==2">
				   <img  style="margin-left:5px" src="<%=rootPath%>client/images/female.jpg" alt="用户图片" width="40px" />
				 </s:if>
				 <s:else>
				   <img  style="margin-left:5px" src="<%=rootPath%>client/images/male.jpg" alt="用户图片" width="40px" />
				 </s:else>
				</s:else>
				</td>
				<!-- 标题 -->
				<s:if test="title.trim().length()>0">
				<td align="left" style="font-size: 13px;"><a  title="<s:property value="title"/>"
					href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>" target="_blank" onclick="hits(<s:property value="questionid"/>)" >
						<s:if test="title!=null && title.length()>questionShowNum">
							<s:property value="title.substring(0,questionShowNum)+'......'" />
						</s:if> <s:else>
							<s:property value="title" />
						</s:else> </a>
						<s:iterator value="getCategoryList(questionid)" status="status">
							<a class="linkbh14" style="font-size:12px;cursor:pointer;" title="所属类别" href="javascript:void(0)" onclick="gotocategory('<s:property value='id' />')"><s:if test="#status.count==1">(</s:if><s:property value="cname"/><s:if test="#status.count!=getCategoryList(questionid).size()">,</s:if><s:else>)</s:else></a>
						</s:iterator>
						</td>
				
				</s:if>
				<s:else>
				<td width="100%" align="left" style="font-size: 13px;"><a  title="<s:property value="htmlcontent"/>"
					href="<%=rootPath%>question/questionDetail.action?questionid=<s:property value="questionid"/>" target="_blank" onclick="hits(<s:property value="questionid"/>)" >
						<s:if test="htmlcontent!=null && htmlcontent.length()>questionShowNum">
							<s:property value="htmlcontent.substring(0,questionShowNum)+'......'" />
						</s:if> <s:else>
							<s:property value="htmlcontent" />
						</s:else> </a>
							<s:iterator value="getCategoryList(questionid)" status="status">
								<a class="linkbh14" style="font-size:12px;cursor:pointer;" title="所属类别" href="javascript:void(0)" onclick="gotocategory('<s:property value='id' />')"><s:if test="#status.count==1">(</s:if><s:property value="cname"/><s:if test="#status.count!=getCategoryList(questionid).size()">,</s:if><s:else>)</s:else></a>
							</s:iterator>
						</td>
				
				</s:else>
				
			</tr>
			
			<tr>
			<s:if test="#attachList!=null && #attachList.size()>0">
			   <td align="left">
			   </td>
			   <td style="color: black;margin:2px;">
			        <font class="linkc12" >
			        <s:if test="status==0">未解决</s:if> <s:else>已解决</s:else> </font>&nbsp;.&nbsp;
			        <s:property value="getShowPageViewNameByUserId(cruserid)" />
			          &nbsp;.&nbsp; <s:date name="crtime" format="yyyy-MM-dd HH:mm" />
			   </td>
			   <td>
				   	<div style="width: 30px;" id="deletefontquestion<s:property value="questionid"/>" style="visibility: hidden;width: 30px;" >
						  <s:if test="loginUsername==cruser">	
							  <a href="javascript:void(0)" onclick="delQuestion(<s:property value="questionid"/>);">删除</a>
						  </s:if>
					</div>  	
			   </td>
			</s:if>
			<s:else>
				<td  style="color: black;">
				    <font class="linkc12"> <s:if test="status==0">未解决</s:if> <s:else>已解决</s:else> </font>&nbsp;.&nbsp;
			        <s:property value="getShowPageViewNameByUserId(cruserid)" />
				      &nbsp;.&nbsp; <s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
				<s:if test="loginUsername==cruser">
				<td>
					<div id="deletefontquestion<s:property value="questionid"/>"  style="visibility: hidden;width: 30px;">
					    <a href="javascript:void(0)" onclick="delQuestion(<s:property value="questionid"/>);">删除</a><br/>
				    </div>
				</td>
				</s:if>
			</s:else>	
			</tr>
			<tr style="color: black;">
			     <td colspan="3">
	                 <div style="text-align: right;">共<font color="#CC0000"><s:property value='findReplyCountByQuestionId(questionid)' default="0" /></font>条回答 共<font color="#EF8E0C"><s:property value='browsecount' default="0" /></font>人浏览</div>		 
			     </td>
			</tr>
		</tbody>
	</table>
</s:iterator>
<!--分页开始 -->
<div bgcolor="#FFFFFF">
	<div colspan="9" align="right" class="clientpage" style="text-align: center;">
		<ul>
			<s:property value="pageHTML" escapeHtml="false" />
		</ul>
	</div>
</div>
</s:if>
<s:else>

暂时没有数据
</s:else>
<!--分页结束 -->