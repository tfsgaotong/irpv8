<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
 <s:if test="chnlDocLinks!=null &&  chnlDocLinks.size()!=0">
 <table style="width: 100%">
 	<tr>
 		<td align="right">
 		    按时间&nbsp;&nbsp;
 		    <a href="javascript:void(0);" onclick="documentSort('asc')">
				<img  src="<%=rootPath%>/client/images/rec_add.gif" width="16px" height="16px">
			</a> 
 		    &nbsp;&nbsp;
 		    <a href="javascript:void(0);" onclick="documentSort('desc')">
				<img  src="<%=rootPath%>/client/images/rec_subtract.gif" width="16px" height="16px">			
			</a>
 		</td>
 	</tr>
 </table>
  	<div class="fyh" id="mydoclink">
  		<s:iterator value="chnlDocLinks"> 
  		<dl id="mydocdl<s:property value='docid'/>">
  		<%--这里给文档id，然后去查询一个图片，然后显示在这里 --%>
    		<dt>	
   		    <s:if test="userPicUrl!=null"> 
				<img   src="<s:property value='userPicUrl'/>" alt="用户头像" width="55px" /> 
			</s:if>
			<s:if test="userPicUrl==null"> 
				<img  src="<%=rootPath %>client/images/touxiang.png" alt="用户头像" width="55px" /> 
			</s:if>	
	    </dt>
    		<dd> 
    			<a href="javascript:void(0)" class="linkb14" onclick="documentinfo_see(<s:property value='docid'/>)">
    			<s:property value="doctitle"/>
    			</a> 
    			  &nbsp;&nbsp;<s:date name="%{crtime}" format="yyyy-MM-dd HH:mm"/> 
    			  <br>标签： 
  			 	 <s:iterator value="document.dockeywords.split(',')"  status="st" var="as">
  				 	<a href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12"><s:property value="#as"/></a>&nbsp;&nbsp;
  				</s:iterator>
  			 
  			<br>核心提示：<s:property value="document.docabstract"/>
  			<span style="padding-bottom:30px;">
  				<p>  
   				<a href="javascript:void(0)" class="linkc12" onclick="documentinfo_see(<s:property value='docid'/>)">评论数量(<label  id="recommendlabel<s:property value='docid'/>" style="color: rgb(135, 173, 88);"> <s:property value="commentcount"/> </label>)</a> &nbsp;  &nbsp;
	  			 <s:if test="cruserid==loginuserid && @com.tfs.irp.document.entity.IrpDocument@NEW_DOCUMENT==docstatus">
	                <a target="_blank" class="linkc12" href="<%=rootPath %>site/to_update_subject.action?docid=<s:property value='docid'/>">修改</a> &nbsp;  &nbsp; 
	            </s:if>
	  			 <s:if test="cruserid==loginuserid">
	                <a href="javascript:void(0);" class="linkc12" onclick="clientdeletedocument(<s:property value='docid'/>)">删除</a> &nbsp;  &nbsp; 
	            </s:if>
  			  </p>
  			</span> 
  			</dd>
  		</dl>
  		</s:iterator> 
  	</div> 
  	<ul>
	<div align="right"><s:property value="pageHTML" escapeHtml="false" /></div>
	</ul>  
 </s:if><s:else><br>暂时没有投稿知识!</s:else>  	
<script type="text/javascript">
	//删除知识
	function clientdeletedocument(_docid){ 
		$.dialog.confirm('您确定要删除这个知识吗？',function(){
			$.ajax({
					type:'post',
					url:'<%=rootPath%>site/clientdeletedocument.action',
					data:{'docid':_docid},
					success: function(msg){
						if(msg=="1"){ //删除成功 
								//然后将这个dl隐藏
						$('#mydocdl'+_docid).fadeOut();
						$('#mydocdl'+_docid).fadeOut("slow");
						$('#mydocdl'+_docid).fadeOut(3000);
						} 
					}
	    	 });
		},function(){}); 
	}	
</script>
</html>
