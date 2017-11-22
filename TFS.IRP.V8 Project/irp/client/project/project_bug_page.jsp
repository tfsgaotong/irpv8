<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
     <title>项目 </title>
   
<style type="text/css">
.searchSec .radios span {
margin-top: 0px;
}
 .fyh{width:100%;margin:0px auto 10px 15px; clear:both;}
 .fyh ul{width:97%;margin:10px auto 20px auto; display:block; text-align:right;}
 .fyh ul span{color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyh ul a {color:#999; padding:2px 8px; border:1px solid #dbdbdb;}
 .fyh ul a:hover{color:#333; padding:2px 8px; border:1px solid #c8c8c8;background:#ededed;}
	
</style>
</head>
<body > 
<script type="text/javascript">



//分页
function page(_nPageNum){
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){  
				 var sData =$(this).attr('_link');
				 var pageString=$('#pageForm').serialize(); 
				 sData=sData+"?"+pageString; 
				 showMyShareTask(sData);
			}    
	}); 
}  
//分页
function pageperson(_nPageNum){
	$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
	$('#tabMenu').find('a').each(function(){   
			if(this.className=="over"){  
				 var sData =$(this).attr('_link');
				 var pageString=$('#pageForm').serialize(); 
				 sData=sData+"?"+pageString; 
				 showMyShareTask(sData);
			}    
	}); 
} 


function bugInfo(serianum){
	//alert(bugId);
	hrefStr="<%=rootPath%>bug/simplebuginfo.action?serianum="+serianum;
	//alert(hrefStr);
	window.open(hrefStr);
}

</script>

<form id="pageForm">
	<s:hidden name="pageNum" id="pageNum" />
	<s:hidden name="pageSize" id="pageSize" />
	<s:hidden name="projectId" id="projectId"/>
</form>


<!-- 数据迭代区 -->

<div id="pagepage" style="float: left;height: 300px;width: 100%;" >
<s:if test="irpBugs==null">
	<span style="text-align: center;margin-left: 350px;" >没有符合条件的Bug信息</span>
</s:if>
<s:else>
	   <div id="infoleft" style="float: left;margin-top: 5px;width: 100%;" >
			<style type="text/css">
				.xm_01{
					float: left;
					line-height: 35px; 
					font-size: 12px;
				}
			</style> 
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
    </div>
	<div class="fyh" style="width:100%;"><ul><s:property value="pageHTML" escapeHtml="false"/></ul></div>
	<script type="text/javascript">
	/**
	 * 分页
	 */
	function pageNavigain(_nPageNum){
		//alert('进入分页方法');
		$('#pageNum').val(_nPageNum);
		var queryString = $('#form').serialize();
		//alert(queryString);
		var urlStr="<%=rootPath %>bug/simpleprojectbug.action?"+queryString+"&queryType=1";
		//alert(urlStr);
		$.ajax({
			type:"post",
			url:urlStr,
			async:false,
			cache:false,
			success:function(html){
				if(html!=null){
					//alert('分页完成');
					$('#pagepage').html(html);
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
</s:else>
</div>
</body>
</html>