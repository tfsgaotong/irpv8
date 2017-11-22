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
</script>
<div class="reorder">
		<section class="classify">
	       	<!-- <div class="title"><p><em style="padding:7px 10px;">主板</em>&nbsp;-&nbsp;详细筛选</p></div> -->
	           <div class="items">
	               <table width="100%">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">时间：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       	<strong <s:if test="paramMap!='WEEK' & paramMap !='MONTH' & paramMap !='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>')">全部</strong><strong <s:if test="paramMap=='WEEK'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>','WEEK')">最近一周</strong><strong <s:if test="paramMap=='MONTH'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>','MONTH')">最近一月</strong><strong <s:if test="paramMap=='YEAR'">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>','YEAR')">最近一年</strong>
	                       </td>
	                   </tr>
	                    <input type="hidden" id="time" value="<s:property value="paramMap"/>">
	                    <input type="hidden" id="sort" value="<s:property value="searchsort"/>">
	                   <tr>
	                   	<td width="120" align="right" valign="top" class="itemClass">排序：</td>
	                       <td width="10"></td>
	                       <td valign="top" class="itemList">
	                       <strong <s:if test="searchsort!=1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>','2')">相关度</strong>	<strong <s:if test="searchsort==1">class="checked"</s:if> onclick="searchVal('<s:property value="searchInfo"/>','<s:property value="searchtype"/>','1')">时间</strong>
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
<s:if test="documentlist!=null && documentlist.size()>0">
  	 <section class="resultList">
	  	 <%-- <div class="reorder"><span>排序字段：</span><em class="current">时间</em><em>相关度</em></div> --%>
	  	 <section class="discussions">
			<s:iterator value="documentlist">
				<s:set var="cruser" value="getIrpUserByUsername(CRUSERID)" />
				<s:set var="document" value="getIrpDocumentById(DOCID)" />
				<article class="item">
		          	<h1 class="ellipsis nowrap"><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="DOCID" /><s:param name="id" value="CHANNELID" /></s:url>" class="linkb14" target="_blank"><s:property value="DOCTITLE.toString().substring(1,DOCTITLE.toString().length()-1)" escapeHtml="false" /></a></h1>
		          	<aside><a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="DOCID" /><s:param name="id" value="CHANNELID" /></s:url>" class="linkb14" target="_blank">
					<img width="220" height="150" src="<s:property value='docCoverPath(DOCID,DOCFLAG)' />" />
		          	</a>
		          	</aside>
		              <section>
		              	<p style="max-height:123px;" class="ellipsis">
							             <strong style="color: black;">标签:&nbsp;</strong> 
							<s:set var="start" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_START)" />
							<s:set var="end" value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue(@com.tfs.irp.solr.entity.IrpSolr@STAYLE_END)" />
							<s:iterator value="DOCKEYWORDS.toString().substring(1,DOCKEYWORDS.toString().length()-1).split(',')"  status="st" var="as">
							<a href="javascript:void:(0)" onclick="searchInfo1('<s:property value="#as.replaceAll(#start,'').replaceAll(#end,'')" />')" class="linkc12"><s:property value="#as" escapeHtml="false" /></a>&nbsp;
							</s:iterator>
						</p>
		              	<p style="max-height:123px;" class="ellipsis">
		              		<a href="<s:url action="document_detail" namespace="/document"><s:param name="docid" value="DOCID" /><s:param name="id" value="CHANNELID" /></s:url>" class="linkb14" target="_blank"><strong style="color: black;">内容：&nbsp;</strong><s:property value="DOCCONTENT.toString().substring(1,DOCCONTENT.toString().length()-1)" escapeHtml="false" /></a>
		             		</p>
	              		<div class="date">
	              			<s:property value="#document.hitscount"/>&nbsp;/&nbsp;<s:property value="#document.recommendcounts"/>
							<s:date name="CRTIME" format="yyyy-MM-dd" />
							<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a>
						</div>
	          	    </section>
	      	        <div class="clear"></div>
				</article>
			</s:iterator>
			<div class="area20"></div>
			<div class="pages"><s:property value="pageHTML" escapeHtml="false" /></div>
		</section>
	</section>
	</s:if>
		<s:else>
			<div style="height: 250px;" >没有找到相关记录</div>
</s:else>