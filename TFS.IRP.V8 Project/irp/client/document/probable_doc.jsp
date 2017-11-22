<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title><s:property value="findChnlName(id)"/></title>
	 <link rel="stylesheet" type="text/css" href="<%=rootPath%>/client/css/easyui.css" />
	 <link href="<%=rootPath %>client/css/css.css" rel="stylesheet" type="text/css" />
	 <link href="<%=rootPath %>client/images/css.css" rel="stylesheet" type="text/css" />
	 <link href="<%=rootPath%>/client/js/skins/iblue.css" rel="stylesheet" type="text/css" />
 	 <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
  <style>
.ellipsis {
    table-layout: fixed;
}
.ellipsis td{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap; 
}
</style>  
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>client/js/easyui-lang-zh_CN.js"></script> 
</head>

<body>
	<s:include value="../include/enterprise_head.jsp"></s:include> 
    <div class="area1"></div>
    <div class="zj_wBox">
     <div class="zj_fl">
     	<s:action name="allrightchannelshow" namespace="/site" executeResult="true">
     		<s:param name="id"><s:property value="id" /></s:param>
     	</s:action>
     </div>  
      <div class="zj_fl zj_w4" id="documentlistdiv">
        	 <div class="zj_title1">
            	<div class="zj_tt"><h1>精华知识</h1></div><div class="zj_more">
            	 <a href="javascript:void(0);" onclick="showmorejinghuadocument()"> 
	                 <img src="<%=rootPath %>client/images/_30.gif" />
	                 </a>  
            	<a href="javascript:void(0);"> 
            	<s:if test="documentFileName==null"></s:if>
        			<s:else>
        				<img src="<%=rootPath %>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName("AP2013102331467.gif","_150X150")'/>" width="160"  height="120"/>
        			</s:else>
            	</a></div>
            </div> 
            <div class="zj_box3">
            	<s:if test="chnlDocLink!=null">
            	<table cellpadding="0" cellspacing="0"  border="0" class="boxcenter mod_text " >
                	<tr>
                    	<td width="133">
                    	  <h1> <a onclick="showdocumentinfo(<s:property value='chnlDocLink.docid'/>)" title="<s:property value='chnlDocLink.doctitle'/>"  href="javascript:void(0)" style="text-decoration: none;">
 								<img src=" <s:property value='docCoverPath(chnlDocLink.docid,chnlDocLink.docflag)'/>" width="160"  height="120" border="0" />
 							 </a>
 							 </h1>
        			    </td>
                        <td width="20"></td>
                        <td width="527" valign="top">
                        <div style="width:520px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"> 
	                        	<a onclick="showdocumentinfo(<s:property value='chnlDocLink.docid'/>)" title="<s:property value='chnlDocLink.doctitle'/>"  href="javascript:void(0)" style="text-decoration: none;">
	                        	<h3 >
	                        		<s:property value="chnlDocLink.doctitle"/>
	                        	</h3>
	                        	</a>
                        	</div>
                            <h4>
                            	创建者：<a  target="_blank" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='chnlDocLink.cruserid'/>"><s:property value="chnlDocLink.cruser"/></a>
                         	   	发布时间：<s:date name="%{chnlDocLink.crtime}" format="yyyy年MM月dd日" />      
                         	   	（已浏览<b><s:property value="chnlDocLink.hitscount"/> </b>次）<br />
                         	   	标签：  
                         	 	<s:iterator value="chnlDocLink.document.dockeywords.split(',')"  status="st" var="as">
                         	 	<a   target="_blank" href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')" class="linkc12">
                         	 		<s:property value="#as"/>
                         	 	</a>&nbsp;&nbsp;
  								</s:iterator>
  								<br />
  								推荐专家： 
  								<s:iterator value="irpRoles" status="rs">
  									<b><a target="_blank" href="<%=rootPath %>site/client_to_index_person.action?personid=<s:property value='userid'/>"><s:property value="username"/></a></b>
  									<s:if test="#rs.count!=irpRoles.size()">、 </s:if>
  								</s:iterator>
							</h4> 
							<h4>
								<p title="<s:property value='chnlDocLink.document.docabstract'/>" ><%--18*5=90 --%> <%--style="text-indent:1em;" --%> 
									<div style="width:400px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"> 
									<a  onclick="showdocumentinfo(<s:property value='chnlDocLink.docid'/>)"  href="javascript:void(0)" style="text-decoration: none;">
									    <s:property value='chnlDocLink.document.docabstract'/> 
									 </a>
									 </div>
								</p>
							</h4>
						 </td>
                    </tr>
                </table>
                </s:if>
            </div> 
            <div class="area1"></div>
        	<div id="probabledocumentshow" ></div>
      </div> 
       <div class="zj_cl"></div>
    </div>
     <div class="area2"></div>
    <s:include value="../include/enterprise_foot.jsp"></s:include>
    <script type="text/javascript">
    //列表更多精华知识
    function showmorejinghuadocument(){
    	 str=$.ajax({
				type:'post',
				url:'<%=rootPath%>site/jinghuadocumentlist.action?id=<s:property value="id"/>', 
				dataType: "json",
				async: false,
			    cache: false 
				}).responseText;  
    	   $('#documentlistdiv').html(str);  
    }
    //列表
    function  probabledocumentshow(querystring){
    	 var str =""; 
    	if(querystring!="" && querystring!= undefined){
    		   str=$.ajax({
 				type:'post',
 				url:"<%=rootPath%>site/probabledocumentshow.action?"+querystring, 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}else{
    		   str=$.ajax({
 				type:'post',
 				url:'<%=rootPath%>site/probabledocumentshow.action?id=<s:property value="id"/>', 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}
		    $('#probabledocumentshow').html(str);  
    }
  //搜索名字
    function searchInfo1(searchInfo){  
    		searchtype = 5;  
			if(searchInfo.length>38){
				searchInfo = searchInfo.substring(0,37);	
			}
    		var eacapeInfo = encodeURI(searchInfo);
    	    window.open('<%=rootPath%>solr/searchcontentofsolr.action?searchInfo='+eacapeInfo+'&searchtype='+searchtype); 
    }
  //点击排行的时候
  function updatePageHtml(_sFiled,_sOrderBy){
	  //修改表单里面的值
	  $('#orderField').val(_sFiled);
	  $('#orderBy').val(_sOrderBy);
	  var  _nPageNum=$('#pageForm').find('input[name="pageNum"]').val();
	  page(_nPageNum);
  }
    //分页
	function page(_nPageNum){ 
		$('#pageForm').find('input[name="pageNum"]').val(_nPageNum);
		// $('#pageForm').find('input[name="searchWord"]').val($('#pageForm').find('input[name="searchWord"]').val());
		var queryString=$('#pageForm').serialize();  
	    //window.location= "<%=rootPath%>site/clientshowchanneldoc.action?"+queryString;
	    //分页将查询出来的东西更换到页面就行了
	    var selfont =  $('#viewcheckone').val();
	    if(selfont=='viewcheckone'){
	    switchOverView();
	    
	    }else{
	    
	    probabledocumentshow(queryString);
	    }
	    
	}
    	function showdocumentinfo(_docid){
    	 	window.open('<%=rootPath %>site/showdocumentinfo.action?refrechstatus=1&docid='+_docid );  
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
$(function(){ 
	probabledocumentshow();
});
/**
* 切换视图
*/
function switchOverView(){

var queryString=$('#pageForm').serialize(); 
var str =""; 
    	if(queryString!="" && queryString!= undefined){
    		   str=$.ajax({
 				type:'post',
 				url:"<%=rootPath%>site/probabledocumentshowview.action?"+queryString, 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}else{
    		   str=$.ajax({
 				type:'post',
 				url:'<%=rootPath%>site/probabledocumentshowview.action?id=<s:property value="id"/>', 
 				dataType: "json",
 				async: false,
 			    cache: false 
 				}).responseText;  
    	}
		    $('#probabledocumentshow').html(str);  
 
	 
}

function switchOverViewSel(){
	var selfont =  $('#viewcheckone').val();
	var queryString=$('#pageForm').serialize(); 
if(selfont=='viewcheckone'){
  probabledocumentshow(queryString);
}else{

switchOverView();

}
	
}

</script>
</body>
</html>
