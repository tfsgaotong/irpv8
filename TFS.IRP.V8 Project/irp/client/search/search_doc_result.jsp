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

function searchInfo1(searchInfo){  
		searchtype = 51;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
}
</script>
<div style="position:relative;top:-23px;height:0px;float:right;right:100px;"><a href="javascript:void(0)" onclick="advSearch()">高级搜索</a></div>
<s:if test="documentlist.size()>0">
<s:iterator value="documentlist">
<dl>
	<dt>
	<s:if test="getIrpUserByUsername(CRUSERID).userpic!=null">
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' />">
			<img src="<%=rootPath%>file/readfile.action?fileName=<s:property value='getIrpUserByUsername(CRUSERID).userpic' />" alt="用户头像" width="55px" />
		</a>						
	</s:if>
	<s:else>			
		<a target="_bank" href="<%=rootPath%>site/client_to_index_person.action?personid=<s:property value='CRUSERID.toString().substring(1,CRUSERID.toString().length()-1)' /> " >
			<img <s:if test="getIrpUserByUsername(CRUSERID).sex==2">src="<%=rootPath%>client/images/female.jpg"</s:if><s:else>src="<%=rootPath%>client/images/male.jpg"</s:else> alt="用户头像" width="55px" />
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
<div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
</s:if>
<s:else>
<span>没有找到相关记录</span>
</s:else>