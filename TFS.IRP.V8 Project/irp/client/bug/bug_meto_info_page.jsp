<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>


<style type="text/css">
.div_one_tab{
	float: left;
	width: 25%;
	line-height: 40px;
	text-align: center; 
	background: url("<%=rootPath%>client/images/search_bug.gif"); 
	}
 .div_one_tab_border{
	border: thin solid #F0F0F0;
}

.fyh{width:100%;margin:0px auto 10px 15px; clear:both;}
.fyh ul{width:96%;margin:10px auto 20px auto; display:block; text-align:right;}
.fyh ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.fyh ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
.fyh ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}

</style>
<script type="text/javascript">
function bugInfo(serianum){
	//alert(bugId);
	hrefStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+serianum;
	//alert(hrefStr);
	window.open(hrefStr);
}

</script>

</head>
  <body>
  <form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="projectId" id="projectId"/>
	<s:hidden name="order" id="order" value=""/>
	<s:hidden name="ordertype" id="ordertype"/>
  </form>
    <div id="infoleft" style="float: left;margin-top: 5px;width: 100%;" >
    	<div style="line-height:237%; font-size: 15px;font-weight: bold;">&nbsp;&nbsp;我创建的BUG
    	<!-- 
    		<div class="columnInfo" style="float:right;padding:2px 0 0 0 ;height: 30px;">
					<input type="button" style="width:100px;height:25px;line-height:25px;color:#fff; font-size:15px;padding-left:20px;background-position:6px;" class="create" onclick="toAddBugpage();" value="创建Bug">
			</div>
			 -->
    	</div>
			<style type="text/css">
				.xm_01{
					float: left;
					line-height: 35px; 
					font-size: 12px;
				}
			</style> 
				
				<!-- <s:url action='toAddBugPage' namespace='/bug' /> -->
			<div style="float: left;width: 100%;background:#ddedfe;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 6%;text-align: center;">序号</div>
				<div class="xm_01" style="width: 17%;text-align: left;margin-left: 10px;">标题</div>
				<div class="xm_01" style="width: 13%;text-align:center;"><a href="javascript:void(0)" onclick="selOrder(1,'priority')">优先级<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div>
				<div class="xm_01" style="width: 8%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(7,'operatorid')">处理人<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div>
				<div class="xm_01" style="width: 8%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(2,'neweststate')">状态<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div> 
				<div class="xm_01" style="width: 8%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(3,'founderid')">分配人<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div>
				<div class="xm_01" style="width: 13%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(4,'versionid')">版本<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div> 
				<div class="xm_01" style="width: 13%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(5,'moduleid')">模块<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div>
				<div class="xm_01" style="width: 12%;text-align:left;"><a href="javascript:void(0)" onclick="selOrder(6,'createtime')">时间<img src="<%=rootPath%>client/images/grayarrow.gif"></img></a></div>
			</div><br/>
			<s:iterator var="bug" value="irpBugs" status="status">
			<s:set var="pri" value="#bug.priority"></s:set>
			<div style="float: left;width: 100%;background:#f6f6f6;font-weight: bold;border-bottom: 2px solid #fff;">
				<div class="xm_01" style="width: 6%;text-align: center;"><s:property value="(pageNum-1)*pageSize+#status.count"/></div>
				<div class="xm_01" style="width: 17%;text-align: left;margin-left: 10px;color: #1077B5;"><a title="<s:property value='#bug.title' />" onclick="bugInfo(<s:property value='#bug.serianum' />)" href="javascript:void(0)"><s:property value="#bug.title" /></a></div>
				<div class="xm_01"  <s:if test=" #pri==1 ">style="width: 13%;text-align: center;color: #1077B5;"</s:if><s:elseif test=" #pri==2 ">style="width: 13%;text-align: center;color: #1077B5;"</s:elseif><s:else>style="width: 13%;text-align: center;color: red;"</s:else></div><s:property value="priorityMap.get(#bug.priority)" /></div>
				<div class="xm_01" style="width: 8%;text-align: left;color: #1077B5;"><s:property value="operatorNameMap.get(#bug.operatorid)" /></div>
				<div class="xm_01" style="width: 8%;text-align: left;color: #1077B5;"><s:property value="#bug.neweststate" /></div>
				<div class="xm_01" style="width: 8%;text-align: left;color: #1077B5;"><s:property value="founderNameMap.get(#bug.founderid)" /></div>
				<div class="xm_01" style="width: 13%;text-align: left;color: #1077B5;"><s:property value="versionNameMap.get(#bug.versionid)" default="默认版本"/>&nbsp;</div>
				<div class="xm_01" style="width: 13%;text-align: left;color: #1077B5;"><s:property value="modulNameMap.get(#bug.moduleid)"  default="默认模块"/>&nbsp;</div>
				<div class="xm_01" style="width: 12%;text-align: left;color: #1077B5;"><s:date format="yyyy-MM-dd " name="#bug.createtime" /></div>
			</div>
 			</s:iterator>
 			<div class="fyh" ><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
	<script type="text/javascript">
	/**
	 * 分页
	 */
	function pageNavigain(_nPageNum){
		//alert('进入分页方法');
		$('#pageNum').val(_nPageNum);
		var queryString = $('#pageForm').serialize();
		//alert(queryString);
		var urlStr="<%=rootPath %>bug/bugmetopage.action?"+queryString+"&queryType=1";
		//alert(urlStr);
		$.ajax({
			type:"post",
			url:urlStr,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					//alert('分页完成');
					$('#projectmaindiv').html(html);
				}
			}
		});	
	}
	
	//初始页面配置项中的单页显示数量
	$(function (){
		var size=<s:property  value="@com.tfs.irp.util.SysConfigUtil@getSysConfigValue('BUG_SHOW_ONE_COUNT')" />;
		$('#pageSize').val(size);
	});
	</script>
    </div>
  </body>
</html>
