<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
//全局变量，
var m_checked = false;

/**
 * 全选
 */
	function checkAll(){
		m_checked = !m_checked;
		$("input[name='chnlDocLinkscatach']").attr("checked",m_checked); 
        $("input[name='chnlDocLinkscatach']:checkbox").each(function(){ 
            if($(this).attr("checked")){
            	strquestion += $(this).val()+","
            }
        })
        $('#alreadyselectedknow').val(strquestion);
	} 
 	function showdocumentinfo(_docid){
   	 	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid); 
   	} 
   		//跳刀添加文档
	function toaddDocument(){
			window.open('<%=rootPath %>site/to_add_subject.action');  
	}
	function showDoc(_channelid){
		//首先判断他有没有查询他里面的文章列表权限
		 $.ajax({
 			url: '<%=rootPath%>site/clientrightdoclist.action',
 			data:{'id':_channelid},
 			type:'post', 
 		    async: false ,
 		    success:function(mes){
 		    	if(mes=="success"){
 	    			 window.open('<%=rootPath %>site/clientshowchanneldoc.action?id='+_channelid,'_parent');
 	    		 }else{
 	    			 $.messager.alert("提示信息","您没有查看知识权限","warning");
 	    		 }
 		    }
 		}) ;
	} 
	/**
	*分页
	*/
	function page(_nPageNum){
		$('#pageFormSel').find('input[name="pageNum"]').val(_nPageNum);
		var queryString = $('#pageFormSel').serialize();
		$('#selecQuestionQuotecontent').panel('refresh',"<%=rootPath %>site/clientshowchanneldoc_question.action?"+queryString);
	}
	/**
	*排序
	*/
	function orderBy(_sFiled,_sOrderBy){
		$('#orderField').val(_sFiled);
		$('#orderBy').val(_sOrderBy);
		var queryString = $('#pageFormSel').serialize();
		$('#selecQuestionQuotecontent').panel('refresh',"<%=rootPath %>site/clientshowchanneldoc_question.action?"+queryString);
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
		    	$('#selecQuestionQuotecontent').panel('refresh',"<%=rootPath %>site/clientshowchanneldoc_question.action?"+queryString);
		    },   
		    menu:'#listSearchType',   
		    prompt:'请输入检索词'  
		});
	});	
	//移动知识
	function moveSelectAlready(_docid){
          
           $("input[name='chnlDocLinkscatach']:checkbox").each(function(){ 
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
    <div data-options="name:'doctitle'">知识标题</div>
    <div data-options="name:'cruser'">创建者</div>
</div>
<table width="100%" border="0" align="center" cellpadding="10"
		cellspacing="1"  id="table1" bgcolor="#cad9ea">
		<tr class="list_th" style="position: relative;">
		  <td colspan="7" align="right" style="padding-right: 2px;"><input id='listSearchText' /></td>
		</tr>
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="50"><a href="javascript:void(0)" onclick="checkAll()">全选</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><a href="javascript:void(0)" onclick="orderBy('doctitle','<s:if test="orderField=='doctitle'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">知识标题<s:if test="orderField=='doctitle'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='doctitle'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="100"><a href="javascript:void(0)" onclick="orderBy('channelid','<s:if test="orderField=='channelid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">所属栏目<s:if test="orderField=='channelid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='channelid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="100"><a href="javascript:void(0)" onclick="orderBy('cruserid','<s:if test="orderField=='cruserid'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">创建者<s:if test="orderField=='cruserid'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='cruserid'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="74"><a href="javascript:void(0)" onclick="orderBy('hitscount','<s:if test="orderField=='hitscount'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">浏览量/评论<s:if test="orderField=='hitscount'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='hitscount'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" width="80"><a href="javascript:void(0)" onclick="orderBy('docpubtime','<s:if test="orderField=='docpubtime'&&orderBy=='desc'">asc</s:if><s:else>desc</s:else>')">发布时间<s:if test="orderField=='docpubtime'&&orderBy=='desc'"><img src="<%=rootPath %>admin/images/spinner_arrow_down.gif" /></s:if><s:elseif test="orderField=='docpubtime'&&orderBy=='asc'"><img src="<%=rootPath %>admin/images/spinner_arrow_up.gif" /></s:elseif></a></td>
		</tr>
		
		<s:iterator value="chnlDocLinks" status="chnlDocLinksstatus">
		<tr>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" style="text-align: left;"><input id="selecdocid<s:property value='docid' />" name="chnlDocLinkscatach" onclick="moveSelectAlready(<s:property value='docid' />)" type="checkbox" value="<s:property value='docid' />">&nbsp;<s:property value="(pageNum-1)*pageSize+#chnlDocLinksstatus.count"/></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" style="text-align: left;"><a href="javascript:void(0)" onclick="showdocumentinfo(<s:property value='docid'/>)" title="<s:property value='doctitle'/>"><s:property value='doctitle'/></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" style="text-align: left;"><a href="javascript:void(0);" onclick="showDoc(<s:property value='channelid'/>)">[<s:property value="findChnlName(channelid)"/>]</a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright" style="text-align: left;"><a href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='cruserid'/>" target="_blank" ><s:property value="cruser"/></a></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:property value="hitscount"/>&nbsp;/&nbsp;<s:property value="commentcount"/></td>
		 <td align="center" bgcolor="#F5FAFE" class="main_bright"><s:date name="%{docpubtime}" format="yyyy-MM-dd"/></td>
		</tr>
		</s:iterator>
		<tr bgcolor="#FFFFFF">
		 <td colspan="7" align="right" class="page"><s:property value="pageHTML" escapeHtml="false" /></td>
		</tr>
      </table>
</body>
</html>