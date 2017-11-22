<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<script type="text/javascript">
//全局变量，
var m_checked = false;

/**
 * 全选
 */
	function checkOnly(){
		m_checked = !m_checked;
		$("input[name='chnlDocLinkscatach']").attr("checked",m_checked); 
        $("input[name='chnlDocLinkscatach']:radio").each(function(){ 
            if($(this).attr("checked")){
            	strquestion += $(this).val()+","
            }
        })
        $('#alreadyselectedknow').val(strquestion);
	} 
 	function questionDetail(_questionid){
   	 	window.open('<%=rootPath %>question/questionDetail.action?refrechstatus=1&questionid='+_questionid); 
   	} 
   	
	$(function(){
		//已经选中的id
	$("input[name='chnlDocLinkscatach']").each(function(i){
			var pageid = $(this).val();
			  $('#hideknowselected').find('a').each(function(i){
				  if($(this).attr('id')==pageid){
					  $("#selecdocid"+pageid).attr("checked",true);
				  }
			  });
		});
		var docids='<s:property value="docidlist"/>';
		if(docids!="[]"){
			$("input[name='chnlDocLinkscatach']").each(function(i){
				var docidStr=docids.substring(1, docids.length-1);
			 	var ids=docidStr.split(",");
				for(j=0;j<ids.length;j++){
					 var idstr="selecdocid"+$.trim(ids[j]);
					 if($.trim($(this).attr('id'))==idstr){
						  $(this).attr("checked",true);
					  }
				}
		    });
		}
		$('#listSearchText').searchbox({
			width:220,
		    searcher:function(value,name){   
		    	$('#pageFormSel').find('input[name="searchWord"]').val(value);
		    	$('#pageFormSel').find('input[name="searchType"]').val(name);
		    	$('#pageFormSel').find('input[name="pageNum"]').val('1');
		    	$('#pageFormSel').find('input[name="pageSize"]').val(<s:property value="pageSize" />);
		    	$('#pageFormSel').find('input[name="id"]').val(<s:property value="id" />);
		    	$('#pageFormSel').find('input[name="orderField"]').val('');
		    	$('#pageFormSel').find('input[name="orderBy"]').val('');
		    	var queryString = $('#pageFormSel').serialize();
		    	$('#selecQuestionQuotecontent').panel('refresh',"<%=rootPath %>site/questionshowquestion_know.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});	
	//移动知识
	function moveSelectAlready(_docid){
          
           $("input[name='chnlDocLinkscatach']:radio").each(function(){ 
               if($(this).attr("checked")){
            	   strquestion += $(this).val()+","
               }
           })
           $('#alreadyselectedknow').val(strquestion);
	}
	
</script>
<form id="pageFormSel">
<s:hidden name="searchWord" id="searchWord" />
<s:hidden name="searchType" id="searchType" />
<s:hidden name="pageNum" id="pageNum" />
<s:hidden name="pageSize" id="pageSize" />
<s:hidden name="orderField" id="orderField" />
<s:hidden name="id" id="id" />
<s:hidden name="orderBy" id="orderBy" />
</form>
<div id="listSearchType" style="width:120px;">  
    <div data-options="name:'title'">问题标题</div>
    <div data-options="name:'cruser'">创建者</div>
</div>
<input type="hidden" id="types" value="1"/>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		  <td colspan="7" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><a href="javascript:void(0)" onclick="checkOnly()">单选</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="62%"><a href="javascript:void(0)" onclick="orderBy('title','<s:if test="orderField=='title'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">问题标题<s:if test="orderField=='title'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='title'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="15%"><a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建时间<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"  width="13%"> <a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建者<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		</tr>
		
		<s:iterator value="listQuestion" status="listQuestionstatus">
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="10%"><input id="selecdocid<s:property value='questionid' />" name="chnlDocLinkscatach" onclick="moveSelectAlready(<s:property value='questionid' />)" type="radio" value="<s:property value='questionid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#listQuestionstatus.count"/></td>
		 <td align="left" bgcolor="#F5FAFE" class="main_bright" style="padding-left: 10px"  ><a href="javascript:void(0)" onclick="questionDetail(<s:property value='questionid'/>)" title="<s:property value='title'/>"><s:property value='title'/></a></td>
		 <!--<td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0);" onclick="showDoc(<s:property value='cruserid'/>)">[<s:property value="findQuestionName(cruserid)"/>]</a></td>  -->
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"  ><s:property value='cruserid'/><s:date name="crtime" format="yyyy-MM-dd HH:mm" /></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"  ><s:property value="cruser"/></td>
		</tr>
		</s:iterator>
		
		<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>