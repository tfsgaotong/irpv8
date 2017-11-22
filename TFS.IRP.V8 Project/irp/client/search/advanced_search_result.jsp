<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<script type="text/javascript">
//高级检索
function advSearch(){
	var str=$.ajax({
		 type:'get',
		 url:'<%=rootPath%>client/search/advanced_search.jsp', 
		 async: false,
		 cache: false
	}).responseText;
	$.dialog({
		title:'高级搜索',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:615,
		height:372,
		content:str,
		okVal:'搜索',
		ok:function(){
			var jqSearchObj = $('#advSearch');
			var allTxt = jqSearchObj.find('input[name="all"]').val();
			var oneTxt = jqSearchObj.find('input[name="one"]').val();
			var noqTxt = jqSearchObj.find('input[name="noq"]').val();
			var treeNode = jqSearchObj.find('#col').combotree('tree').tree('getSelected');
			var chnlid=0;
			if(treeNode){
				chnlid=treeNode.id;
			}
			var pageSize = jqSearchObj.find('#num').combobox('getValue');
			var crtime = jqSearchObj.find('#time').combobox('getValue');
			var startTime=null,endTime=null;
			if(crtime=='custom'){
				if(!$('#e_time').datebox('isValid')){
					return false;
				}
				startTime = $('#s_time').datebox('getValue');
				endTime = $('#e_time').datebox('getValue');
			}
			var searchFiled = jqSearchObj.find('#range').combobox('getValue');
			typenum = $('#searchorder option:selected').val();
			$.ajax({
				type: 'POST',
				url: '<%=rootPath%>solr/advanced_search.action',
				async: false,
				cache: false,
				data:{
					'advancedParam.allTxt':allTxt,
					'advancedParam.oneTxt':oneTxt,
					'advancedParam.noqTxt':noqTxt,
					'advancedParam.chnlid':chnlid,
					'advancedParam.pageSize':pageSize,
					'advancedParam.crtime':crtime,
					'advancedParam.startTime':startTime,
					'advancedParam.endTime':endTime,
					'advancedParam.searchFiled':searchFiled,
					'searchsort':typenum,
					'pageNum':1
				},
				success: function(data){
					$('#microblogdocumentview').html(data);
					pageNumsort = 1;
				}
			});
		},
		cancelVal:'关闭',
		cancel:function(){}
	});
}

function advPage(pageNum){
	typenum = $('#searchorder option:selected').val();
	var sDataParam = $('#advSearchForm').serialize()+"&searchsort="+typenum+"&pageNum="+pageNum;
	$.ajax({
		type: 'POST',
		url: '<%=rootPath%>solr/advanced_search.action',
		async: false,
		cache: false,
		data: sDataParam,
		success: function(data){
			$('#microblogdocumentview').html(data);
			pageNumsort = pageNum;
		}
	});
}

function saveAdvSearch(){
	var str=$.ajax({
		 type:'get',
		 url:'<%=rootPath%>personalsearch/personalsearch_add.action', 
		 async: false,
		 cache: false
	}).responseText;
	$.dialog({
		title:'保存个人专题',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:280,
		height:120,
		content:str,
		okVal:'保存',
		init: function(){
			$('#inputSearchName').validatebox();
		},
		ok:function(){
			var isValid = $('#inputSearchName').validatebox('isValid');
			if(!isValid){
				return isValid;
			}
			var searchForm = $('#advSearchForm');
			$.ajax({
				type: 'POST',
				url: '<%=rootPath%>personalsearch/personalsearch_add_dowith.action',
				async: false,
				cache: false,
				data:{
					'personalSearch.searchname' : $('#inputSearchName').val(),
					'personalSearch.searchdesc' : $('#inputSearchDesc').val(),
					'personalSearch.searchdalltxt' : searchForm.find('input[name="advancedParam.allTxt"]').val(),
					'personalSearch.searchdonetxt' : searchForm.find('input[name="advancedParam.oneTxt"]').val(),
					'personalSearch.searchdnoqtxt' : searchForm.find('input[name="advancedParam.noqTxt"]').val(),
					'personalSearch.searchdchnlid' : searchForm.find('input[name="advancedParam.chnlid"]').val(),
					'personalSearch.searchdpagesize' : searchForm.find('input[name="advancedParam.pageSize"]').val(),
					'personalSearch.searchdcrtime' : searchForm.find('input[name="advancedParam.crtime"]').val(),
					'personalSearch.searchdstarttime' : searchForm.find('input[name="advancedParam.startTime"]').val(),
					'personalSearch.searchdendtime' : searchForm.find('input[name="advancedParam.endTime"]').val(),
					'personalSearch.searchfiled' : searchForm.find('input[name="advancedParam.searchFiled"]').val(),
					'personalSearch.searchsort' : $('#searchorder option:selected').val()
				},
				success: function(data){
					if(data=='1'){
						$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
							advPage(1);
						});
					}else{
						$.dialog.tips('保存失败',1,'32X32/fail.png');
						return false;
					}
				}
			});
		},
		cancelVal:'关闭',
		cancel:function(){}
	});
}
</script>
<form id="advSearchForm">
<s:hidden name="advancedParam.allTxt" />
<s:hidden name="advancedParam.oneTxt" />
<s:hidden name="advancedParam.noqTxt" />
<s:hidden name="advancedParam.chnlid" />
<s:hidden name="advancedParam.pageSize" />
<s:hidden name="advancedParam.crtime" />
<s:hidden name="advancedParam.startTime" />
<s:hidden name="advancedParam.endTime" />
<s:hidden name="advancedParam.searchFiled" />
</form>
<div style="position:relative;top:-23px;height:0px;float:right;right:100px;"><a href="javascript:void(0)" onclick="saveAdvSearch()">保存个人专题</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="advSearch()">高级搜索</a></div>
<s:if test="documentlist.size()>0">
<s:iterator value="documentlist">
<s:set var="doccruser" value="findUserByUserId(CRUSERID)" />
<dl>
	<dt>
	<s:if test="#doccruser.userpic!=null">
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' />">
			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='#doccruser.userpic' />" alt="用户头像" width="55px" />
		</a>						
	</s:if>
	<s:else>			
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' /> " >
			<img <s:if test="#doccruser.sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px" />
		</a>
	</s:else>
	</dt>
	<dd>
		<a href="javascript:void(0)" onclick="documentinfo_see(<s:property value="DOCID" />)" class="linkb14"><s:property value="DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)" escapeHtml="false" /></a>
		.
		<s:property value="CRUSER.toString().substring(1,CRUSER.toString().length()-1)" escapeHtml="false" />
		.
		<s:date name="CRTIME" format="yyyy-MM-dd" /><br/>
		标签:&nbsp;
		<s:set var="start" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_START)" />
		<s:set var="end" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_END)" />
		<s:iterator value="DOCKEYWORDS.toString().substring(1,DOCKEYWORDS.toString().length()-1).split(',')"  status="st" var="as">
		<a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as.replaceAll(#start,'').replaceAll(#end,'')" />')" class="linkc12"><s:property value="#as" escapeHtml="false" /></a>&nbsp;
		</s:iterator>
		<span>
			<s:property value="DOCCONTENT.toString().substring(1,DOCCONTENT.toString().length()-1)" escapeHtml="false" />
			<p>&nbsp;</p>
		</span>
	</dd>
</dl>
</s:iterator> 
<ul>
	<s:property value="pageHTML" escapeHtml="false" />
</ul> 
</s:if>
<s:else>
<span>没有找到相关记录</span>
</s:else>