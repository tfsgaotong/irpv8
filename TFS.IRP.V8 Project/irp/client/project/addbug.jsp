<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
  <body> 
  	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
	<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script> 
  <script type="text/javascript"> 
  $(function(){
		initImg();
		loadUploadPic();
  });
	function initImg(){
		$('#projectImg').css({
			display:'none'
		});
	} 
	//加载上传组件
	function loadUploadPic(){
		var maxamount = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findsize.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
    	var allfileext = $.ajax({
	    	type:"get",
	    	url:"<%=rootPath%>file/findsavepicext.action",
	    	cache:false,
	    	async:false
    	 }).responseText;
		$("#projectFileBut").uploadify({
			'auto': true,
			'multi' : false,
			'swf' : '<%=rootPath%>client/js/uploadify.swf',
			'uploader' : '<%=rootPath%>project/projectfilelist.action;jsessionid=<%=session.getId() %>',  //  上传的是临时文件 
			'queueID' : 'fileList',
			'fileObjName' : 'picFile',
			'buttonText' : 'Bug图标',  
			'removeCompleted': true,
			'width' : "60",
			'height' : "16",
			'removeTimeout': 1,
			'queueSizeLimit': 1,      //允许同时上传文件数量
			'fileSizeLimit' :maxamount,  
		    'fileTypeDesc': 'Bug图标',
		    'fileTypeExts': allfileext,
			'onUploadSuccess' : function(file, data, response){	
				  var imgSrc = data.split("#")[0];
				  $('#projectfile').val(imgSrc);
				  imgStr = '<%=rootPath%>file/readfile.action?fileName='+imgSrc;	
				  $('#projectimage').html("<img src='"+imgStr+"' width='50px'  height='50px'/>");
			},
			'onUploadError': function (file, errorCode, errorMsg, errorString) {    //错误提示 
	            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	        }
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
	//比较两个日期的大小
	function  compareTime(startDate,endDate){ 
		startDate=startDate.replace(/-/g,"/");
		endDate=endDate.replace(/-/g,"/"); 
		return Date.parse(startDate)<Date.parse(endDate);
	}
	//引入扩展验证
	$.extend($.fn.validatebox.defaults.rules, {   
	    EndTime:{
	    	validator:function(){
	    		var startDate=$('#projectStart').datebox('getValue');
	    		var endDate=$('#projectEnd').datebox('getValue');
	    		return  compareTime(startDate,endDate);
	    	},
	    	message:'结束日期必须在开始日期之后'
	    },
	    StartTime:{
	    	validator:function(){
	    		var startDate=$('#projectStart').datebox('getValue');
	    		var endDate=$('#projectEnd').datebox('getValue');
	    		return  compareTime(startDate,endDate);
	    	},
	    	message:'开始日期必须在结束日期之前'
	    }
		}); 
</script> 
  	<form id="addprojectfrm" action="project/addproject.action" method="post">
 		<input type="hidden" name="irpProject.projectid"  value="<s:property value='irpProject.projectid'/>"/>
	  	Bug名称：<input  style="background-color: rgb(246, 246, 246); line-height:18px;"  class="easyui-validatebox txt_tclwbia" validType="length[0,20]"   required="true" id="prnameinput" name="bugTitle" value="<s:property value='irpProject.prname'/>"/>
	  	<font color="red">*</font>&nbsp;最多不超过20个字<br/><br/>
		Bug描述：   不超过100个字  <Br/>
	<!-- <textarea style="background-color: rgb(246, 246, 246);"  required="true" id="prdescinput" name="describe"   rows="" cols="6" class="easyui-validatebox description_clwbi" validType="length[0,100]"   ><s:property value="irpProject.prdesc"/></textarea>
	  -->
	 <span style="float: right; padding-right: 10px;padding-top:5px;">
	 	<a href="javascript:void(0);">
	 	<label id="projectImg">Bug图标</label>
	 	  <a href="javascript:void(0);"  title=""><input type="file" id="projectFileBut" /></a>
	 	</a>
	 </span>
	 <input id="projectfile" name="irpProject.projectfile" value="<s:property value='irpProject.projectfile'/>" type="hidden" />
	 <span style="float: right;" id="projectimage">
	 	<s:if test="irpProject.projectfile!=null"> 
	 	<img width="50px" height="50px" src="<%=rootPath%>file/readfile.action?fileName=<s:property value='@com.tfs.irp.util.ThumbnailPic@searchFileName(irpProject.projectfile,"_150X150")'/>"/>
	 	</s:if>
	 </span>
	<br/>
	Bug私密性：<select  name="irpProject.ispublish">
		<option value="<s:property value='@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH'/>" <s:if test="@com.tfs.irp.project.entity.IrpProject@IS_PUBLISH==irpProject.ispublish">checked</s:if>>公开</option>
		<option value="<s:property value='@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH'/>"  <s:if test="@com.tfs.irp.project.entity.IrpProject@NOT_PUBLISH==irpProject.ispublish">checked</s:if>>私密</option>
	</select>
	   	&nbsp;&nbsp;   &nbsp;&nbsp;   &nbsp;&nbsp;   &nbsp;&nbsp;   &nbsp;&nbsp;   
	 Bug阶段：
	  <select id="projectStatus"   name="irpProject.prostatus" class="easyui-combotree" style="width: 100px;" ></select>
	  <br/>
	开始时间：<span id="createTime">
				<b>
				<input name="irpProject.starttime" type="text"  id="projectStart"  class="easyui-datebox" editable="false" validType="StartTime" value='<s:date name="irpProject.starttime" format="yyyy-MM-dd" />'>&nbsp;&nbsp; 
				</span>
	结束时间：<span id="endData">
				<b>
				<input name="requiredTime" type="text" id="projectEnd" class="easyui-datebox" editable="false" validType="EndTime" value='<s:date name="irpProject.endtime" format="yyyy-MM-dd"/>'>
				</span>
				<input type="radio"  />是否发送私信
		<br/>
	  Bug标签：
	  <input class=" txt_tclwbia easyui-validatebox" style="background-color: rgb(246, 246, 246); line-height:18px;" validType="length[0,150]" id="keyprint" name="irpProject.prokey" value="<s:property value='irpProject.prokey'/>"/>
	 </form>
  </body>
</html>
