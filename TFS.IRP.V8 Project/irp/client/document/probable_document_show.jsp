<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
<!--
	$(function(){
		var _id="<s:property value='id'/>";
		 $.ajax({
				url: '<%=rootPath%>site/clientrightadddoc.action',
				data:{'id':_id},
				type:'post', 
			    async: false ,
			    success:function(mes){
			    	if(mes=="success"){
			    		$('#addsubjecta').show();
		    		} 
			    }
			}) ;
		
	});
//-->
</script>
<div class="zj_title1">
  	 <div class="zj_ttj">
  	 <h1><s:property value="findChnlName(id)"/>
  	 <div style="float: right;padding-top: 6px;">
  	 <s:if test="irpChannel.chnltype!=@com.tfs.irp.channel.entity.IrpChannel@CHANNEL_TYPE_MAP">
  	 <a id="addsubjecta" style="display: none;" target="_blank" href="<%=rootPath %>site/to_add_subject.action?id=<s:property value='id'/>" title="提交知识">
		<img src="<%=rootPath %>client/images/addbuddy.gif"/>
	 </a>
  	 </s:if>
  	 </h1>
  	 </div> 
  </div>

<div class="zj_boxk">
<s:if test="chnlDocLinks!=null && chnlDocLinks.size()>0">
        <table cellpadding="0" cellspacing="0" border="0" background="<%=rootPath %>client/images/gl_r3_c1.jpg" width="671" class="boxcenter listTitle">
<tr>
	<td width="394" height="25" align="center">知识标题</td>
	<td width="71" align="center">所属栏目</td>
	<td width="74" align="center">
		<a href="javascript:void(0);" onclick="updatePageHtml('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">
	  	发布时间</a>
	</td>
	<td width="71" align="center">
		<a href="javascript:void(0);" onclick="updatePageHtml('hitscount','<s:if test="orderField=='hitscount'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">浏览量</a>
		/<a href="javascript:void(0);" onclick="updatePageHtml('commentcount','<s:if test="orderField=='commentcount'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">评论 </a>
	</td>
  <td width="61" align="center">创建者</td>
</tr>
</table>
 <table cellpadding="0" cellspacing="0" border="0" width="660" class="boxcenter gl_listTxt ellipsis">
	<s:iterator value="chnlDocLinks">
 	<tr>
     	<td width="33" valign="middle" align="center" height="26">
		 	<s:if test="docchannel>0">
		 		<img src="<%=rootPath %>client/images/jinghua.gif" width="21px" height="14px"/> 
		 	</s:if>
		 	<s:else>
		 		<img src="<%=rootPath %>client/images/gl_r5_c3.gif" />
		 	</s:else> 
 		</td>
 	<td  width="361"> 
 		<a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"> 
     		<s:property value='doctitle'/></a>
	</td>
	<td width="71"> 
		<a href="javascript:void(0);" onclick="showDoc(<s:property value='channelid'/>)">
			[<s:property value="findChnlName(channelid)"/>]
		</a>
	 </td>

         	<td width="74" align="center"><h1><s:date name="%{docpubtime}" format="yyyy-MM-dd"/></h1></td>
        
        	<td width="71" align="center"><h1><s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/> </h1></td>
           <td width="61" align="center"><h1>
              	<a  target="_blank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>">
         		<s:property value="cruser"/>
         	</a>
         	</h1></td>
         	 </tr>
    	</s:iterator>   
    </table>
    <div class="main" align="right" style="width: 650px;" >
                <div class="left" style="width: 650px;" >
                  <div class="fyh"  style="width: 650px;" >
                   <ul style="width: 650px;" >
                    <s:property value="pageHTML" escapeHtml="false" />
                    </ul>
                  </div>
                </div>
                 </div>
    </s:if>
    <s:else>
  		<span style="padding-left: 10px;">  该分类下暂时没有知识...</span>
    </s:else>
</div>

<form id="pageForm">   
		<s:hidden name="pageNum" id="pageNum" />
		<s:hidden name="pageSize" id="pageSize" />
		<s:hidden name="id" id="id"></s:hidden>  
		<s:hidden name="orderField" id="orderField"></s:hidden>
		<s:hidden name="orderBy" id="orderBy"></s:hidden>
</form>