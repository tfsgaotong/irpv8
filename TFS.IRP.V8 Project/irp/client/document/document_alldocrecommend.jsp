<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
 <%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 %> 
 <form id="document_docrecommendfrm"> 
	<s:hidden name="pageNum" id="pageNum" /> 
	<s:hidden name="pageSize" id="pageSize"/> 
 </form> 
<s:if test="irpDocrecommends==null || irpDocrecommends.size==0">&nbsp;&nbsp;暂时没有评论...<br></s:if>
<s:else>
<s:iterator value="irpDocrecommends" var="docs">
<div class="pinglun"><%--onmouseover="mouseinrow(<s:property value='recommendid'/>)" onmouseout="mouseoutrow(<s:property value='recommendid'/>)" --%>
	<span style="float: left; width: 65px;padding-left: 20px;">
		<span style="float: left;padding-left: 10px;">
			<img src="<s:property value='userPicUrl'/>" alt="用户头像" width="55px" />
		</span>
	</span>
	<span style="float: left;width:890px;padding-left: 10px; padding-right: 5px;">
	<span style="padding-left: 10px;"><%--操作span --%>
		<s:date name="crtime" format="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;
		<a  href="#001" class="linkc12" onclick="appendReplayUsername(<s:property value='recommendid'/>,'<s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(cruserid)"/>')">[回复]</a>
		<%--
		<s:if test="%{loginuserid==cruserid||loginuserid==createuserid}"> 
			<a  style="visibility: hidden;" id="deletealable<s:property value='recommendid'/>"   href='javascript:void(0)' style="text-decoration: none;"  class='linkc12' onclick='deletedocuemntdocrecommend(<s:property value="docid"/>,<s:property value="recommendid"/>,<s:property value="cruserid"/>,<s:property value="replayuserid"/>)'>[删除]</a> 
		</s:if>
		--%>
	</span>
        <h3>
	       <a shref="javascript:void(0);" class="linkbh14" onclick="client_to_index_person(<s:property value='cruserid'/>)">
	       	 <s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(cruserid)"/>
	        </a>
        </h3>
   		
   		<s:if test="parentDocRecommend!=null">
	   		<%--回复的回复 --%>
	   		<div style="margin:5px 20px 6px 0px;background: #F6F6F6; border: #575 1px solid;">
	   			<div style="margin:5px 10px 0px 15px;">
					<span><s:date name="parentDocRecommend.crtime" format="yyyy-MM-dd HH:mm"/></span>
					<h3>
						 <a href="javascript:void(0);" class="linkbh14" onclick="client_to_index_person(<s:property value='parentDocRecommend.cruserid'/>)">
						 <s:property value="@com.tfs.irp.projectperson.web.IrpProjectPersonAction@findUserNameByUserId(parentDocRecommend.cruserid)"/>
					</a></h3>
					<s:property value="parentDocRecommend.recommend" escapeHtml="false"/>
				</div>
	   		</div>
   		</s:if>
   		<div style="border-bottom:1px dotted grey;margin-bottom: 20px;"><s:property value="recommend" escapeHtml="false"/></div>
	</span>
</div>  
</s:iterator> 
	 <div class="main" align="right">
	    <div class="left" >
	      <div class="fyh" style="width: 1200px">
	       <ul>
	        <s:property value="pageHTML" escapeHtml="false" />
	        </ul>
	      </div>
	    </div>
     </div>
</s:else> 
<%--隐藏域 --%>
<input type="hidden" name="replayUserId" id="replayUserId"/> 
 
 <%--下面这个方法，留着这个页面共用 --%>
<script type="text/javascript">
//判断删除事件 鼠标移上
function mouseinrow(recommendid){
	var deletealable ="#deletealable"+recommendid; 
	$(deletealable).css({visibility:"visible"});
}
	//判断删除事件 鼠标移出
	function mouseoutrow(recommendid){
		var deletealable ="#deletealable"+recommendid; 
		$(deletealable).css({visibility:"hidden"});
	}
 
//鼠标悬浮到某条私信框
function  showDelete(_recommendid){ 
	$("#delete"+_recommendid).css("visibility","visible");
}
//鼠标移出到某条私信框
function hiddenDelete(_recommendid){ 
	$("#delete"+_recommendid).css("visibility","hidden");
	
}
	function page(pageNum){ 
		$('#pageNum').val(pageNum);
		var sData=$('#document_docrecommendfrm').serialize(); 
		var sHtmlConn = $.ajax({ 
	 		type:'post', 
	 		url: '<%=rootPath%>site/findmydocrecommend.action',
	 		data: sData,
	 		dataType: "json",
			async: false,
		    cache: false  
	 	}).responseText;
		$('#speek').html(sHtmlConn);
}
//点击人名跳刀他的个人主页
function client_to_index_person(_personid){
	window.open('<%=rootPath%>/site/client_to_index_person.action?personid='+_personid,'_blank');
}
		//删除对文档的某一条评论。。。_cruserid创建文档的人
	function deletedocuemntdocrecommend(_docid,_recommendid,_cruserid,_replayUserid){  
	$.dialog.confirm('您确定要删除这个评论吗？',function(){
		$.ajax({
			url:'<%=rootPath%>site/deletedocuemntdocrecommend.action',
			data:{
				'recommendid':_recommendid,
				'docid': _docid
			},
			type:'post',
			success: function(msg){
				 	if(msg=="1"){
				 	//将这个评论数量减去1
				 	if(_replayUserid==0){  
				 		 $('#recommendlabel').text(parseInt($('#recommendlabel').text())-1); 
				 	} 
				 	  findmydocrecommend(_docid,_cruserid);//调用，刷新
				 	}else{
				 		$.dialog.alert('删除失败',function(){});
				 	}
			} 
		});
	},function(){}); 
	}
 </script>
