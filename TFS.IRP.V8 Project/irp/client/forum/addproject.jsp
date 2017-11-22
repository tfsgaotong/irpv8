<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!-- 新建版块弹出框 --> 
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>
 
<body> 
  	<form id="addprojectfrm" action="project/addproject.action" method="post">
        <input type="hidden" name="irpProject.projectid"  value="<s:property value='irpProject.projectid'/>"/>
                        板块名称：
        <input  style="background-color: rgb(246, 246, 246); line-height:18px;"  class="easyui-validatebox txt_tclwbia" validType="length[0,20]"   required="true" id="prnameinput" name="irpProject.prname" value="<s:property value='irpProject.prname'/>"/>
	  	<font color="red">*</font>&nbsp;最多不超过20个字<br/><br/>
		板块描述：   不超过100个字  <Br/>
        <textarea style="background-color: rgb(246, 246, 246);"  required="true" id="prdescinput" name="irpProject.prdesc"   rows="2" cols="8" class="easyui-validatebox description_clwbi" validType="length[0,100]"   ><s:property value="irpProject.prdesc"/></textarea>
        <input id="projectfile" name="irpProject.projectfile" value="<s:property value='irpProject.projectfile'/>" type="hidden" />
        <input id="protype" name="protype" value="1" type="hidden" />
        <input id="ispublish" name="irpProject.ispublish" type="hidden" value="<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH'/>">
    </form>
</body>

<script type="text/javascript"> 
	$(function(){
	    initImg();
	});
	
	
	function initImg(){
	    $('#projectImg').css({
	        display:'none'
	    });
	} 
	
	
	$(function(){
	    $('#projectStatus').combotree({
	        url: '<%=rootPath%>site/findprojecttree.action',
	        required: 'true',
	        panelWidth: 200,
	        onLoadSuccess:function(){
	            var pid='<s:property value="@com.tfs.irp.document.web.DocumentAction@findProjectId()"/>';
	            var tree = $('#projectStatus').combotree('tree');
	            var node = tree.tree('find',parseInt(pid));
	            if(node){
	                tree.tree('expand',node.target);
	            }
	        }
	    });
	    
	    $('#projectStart').datebox({   
	        required:true 
	    }); 
	    
	    $('#projectEnd').datebox({   
	        required:true  
	    });
	}); 
</script>
</html>
