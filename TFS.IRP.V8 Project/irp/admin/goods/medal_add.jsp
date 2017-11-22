<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
java.util.UUID uuid  = java.util.UUID.randomUUID();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单增加</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.file {
    position: relative;
    display: inline-block;
    background: #ccc;
    border: 1px solid #333;
    padding: 4px 20px;
    overflow: hidden;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    border-radius: 20px;
    color: #333;
    font-size: 13px;

}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.gradient{
   
    filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=#fff,endcolorstr=#ccc,gradientType=0);
    -ms-filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=#fff,endcolorstr=#ccc,gradientType=0);/*IE8*/    
    background:#ccc; /* 一些不支持背景渐变的浏览器 */  
    background:-moz-linear-gradient(top, #fff, #ccc);  
    background:-webkit-gradient(linear, 0 0, 0 bottom, from(#fff), to(#ccc));  
    background:-o-linear-gradient(top, #fff, #ccc); 
}
</style>
</head>

<body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	integer : {// 验证整数
		validator : function(value) {
		return /^[+]?[0-9]+\d*$/i.test(value);
		},
		message : '请输入整数。'
	},
	 number: {
          validator: function (value, param) {
            return /^[0-9]+.?[0-9]*$/.test(value);
          },
          message: '请输入数字'
        },
	isExitCode : {
        validator:function(value){ 
        var msg = false;
     	$.ajax({
						url: '<%=rootPath%>goods/isexitcode.action',
					    async: false,
					    cache: false,
					    data:{
					    	goodsno:value
					  
					    },
					    success:function(data){
					    if(data=="0"){
							msg= true;
							}else{
							$.fn.validatebox.defaults.rules.isExitCode.message = '请输入唯一编码';
							msg = false;
						}
					    }
					});
				
		return msg;		
    	},
    	message:''
        },
        isExitformtablename : {
     validator:function(value){ 
     		 var msg = false;
     	$.ajax({
						url: '<%=rootPath%>form/isexitformtablename.action',
					    async: false,
					    cache: false,
					    data:{
					    	formtablename:value
					    },
					    success:function(data){
					    	  if(data=="0"){
									msg= true;
									}else if(data=='1'){
									$.fn.validatebox.defaults.rules.isExitformtablename.message = '该字段数据库名称已存在';
									msg = false;
								}else if(data=='2'){
									$.fn.validatebox.defaults.rules.isExitformtablename.message = '该字段不能为关键字';
									msg = false;
								}
					    }
					});					
		return msg;		 
    	},
    	message:''
        }      
});

$("#file").on("change","input[type='file']",function(){
    var filePath=$(this).val();
    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
        $(".fileerrorTip1").html("").hide();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName1").html(fileName);
    }else{
        $(".showFileName1").html("");
        $(".fileerrorTip1").html("您未上传文件，或者您上传文件类型有误！").show();
        return false
    }
})
var attid="<s:property value='irpMedal.medalimage'/>";
var irpMedal="<s:property value='irpMedal'/>";
$(function(){
	if(irpMedal!=null){
	toUpdate(attid);
	}
});
///添加附件
  function tosaveAttacthed(){  
		var saveattdiv=document.createElement("div");
		saveattdiv.id="saveattdiv1";
		document.body.appendChild(saveattdiv);  
		$('#saveattdiv1').dialog({
	        modal:true,
		    cache:false,
	        href:'<%=rootPath %>site/to_saveattached.action',
			title:'附件管理',
			width:600,
			height:350,
			resizable:true,
			maximizable:false,	
			closable:false,
			buttons:[{
						    	text: '确定',
						    	iconCls: 'icon-ok', 
						        handler: function(){ 
							        if(addJsonFileList){  
							       		 var id=$('input:radio[name="editversions"]:checked').attr("id"); 
							       		 for(var i=0;i<addJsonFileList.length;i++){
							       			if(addJsonFileList[i].attfile==id){
							       				addJsonFileList[i].editversions=1; 
							       			}else{ 
							       				  if(addJsonFileList[i].editversions=="2"){//一种就是附件，
							       				 	 addJsonFileList[i].editversions=2;
							       				  }else{
							       				    addJsonFileList[i].editversions=0;
							       				  }
							       			}
							     		  }
							     	   } 
						       $('#saveattdiv1').dialog('destroy');
						     }
						    }], 
			onClose:function(){
			    	$('#saveattdiv1').dialog('destroy');
			}
		});   
  } 
  var addJsonFileList = new Array();
function saveAttacthedByLeave(){ 
var dialogDiv = document.createElement("div");
		dialogDiv.id="fileinfo1";
		document.body.appendChild(dialogDiv);
		$('#fileinfo1').dialog({
			modal:true,
			href:'<%=rootPath %>site/client_to_save_attached.action?isqusertionordoc=2',
			title:'文件上传',
			resizable:true,
		    width:600,
		    height:300,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {
		    	$('input[name="jsonFile"]').val(JSON.stringify(addJsonFileList));
		    	if(addJsonFileList){ 				
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				$('#fileName').text('');
				$('#imgshow1').text('');
				for(var i=0;i<addJsonFileList.length;i++){
					filename=addJsonFileList[i].attfile;
				  	 $.ajax({
		 					type:"post",
		 					data : {'fileName' :filename},
		 					async: false,
		 					url:"<%=rootPath%>site/isradio.action" ,
		 					success:function(msg){
		 						if(msg=="1"){//说明他是图片
		 							
		 							$("#imgshow1").css("display","block");
		 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\"/>");
		 			    	     }else{
		 			    	    	$('#fileName').append(addJsonFileList[i].attdesc);
		 			    	     }
		 					}
		 				});
					
					
					if(addJsonFileList[i].attfile==id){
						addJsonFileList[i].editversions=1; 
					}else{ 
						if(addJsonFileList[i].editversions=="2"){
						}else{
							addJsonFileList[i].editversions=0;
						}
					}
				}
				$('#fileinfo1').dialog('destroy');
			}
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#fileinfo1').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#fileinfo1').dialog('destroy');
		    }
		});
}

var _allattacheds=null;
function toUpdate(_attachedid){ 
	//发送ajax请求获得所有附件
	$.ajax({ 
		type: "GET", 
		url: "<%=rootPath %>medal/findmedalsallattache.action?attachedids="+_attachedid, 
		success: function(msg){
			_allattacheds=eval(msg);//转换成附件集合    
		}
	});   
}
//修改附件
function upAttacthedByLeave(){ 
 var dialogDiv1 = document.createElement("div");
		dialogDiv1.id="edfileinfo1";
		document.body.appendChild(dialogDiv1);		
		$('#edfileinfo1').dialog({
			modal:true,
			href:'<%=rootPath %>form/client_to_update_attached.action?isqusertionordoc=2',
			title:'文件修改',
			resizable:true,
		    width:600,
		    height:300,
		    buttons: [{
		    	text: '提交', 
		    	iconCls: 'icon-ok', 
		    	handler: function() {		    	
		    	$('input[name="jsonFile"]').val(JSON.stringify(_allattacheds));
		    	if(_allattacheds){
				if(_allattacheds.length!=0){
					$('#fileName').html('');
					$('#imgshow1').html('');
				for(var i=0;i<_allattacheds.length;i++){
					filename=_allattacheds[i].attfile;
						 $.ajax({
		 					type:"post",
		 					data : {'fileName' :filename},
		 					async: false,
		 					url:"<%=rootPath%>site/isradio.action" ,
		 					success:function(msg){
		 						if(msg=="1"){//说明他是图片		 								
		 							 $("#imgshow1").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+filename+"\" id=\"mypic\" width=\"100\" height=\"100\" />");
		 			    	     }else{
		 			    	    	$('#fileName').append(_allattacheds[i].attdesc);
		 			    	     }
		 					}
		 				});
				}					
		    	
		    	
				}
				var id=$('input:radio[name="editversions"]:checked').attr("id"); 
				for(var i=0;i<_allattacheds.length;i++){
					if(_allattacheds[i].attfile==id){
						_allattacheds[i].editversions=1; 
					}else{ 
						if(_allattacheds[i].editversions=="2"){//一种就是附件，
						}else{
							_allattacheds[i].editversions=0;
						}
					}
				}
				$('#edfileinfo1').dialog('destroy');
			}  
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    	$('#edfileinfo1').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#edfileinfo1').dialog('destroy');
		    }
		});   
}
</script>
<form id="addMedal" method="post" enctype="multipart/form-data">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
<input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
	<input type="hidden" name="irpMedal.medalid" value="<s:property value='irpMedal.medalid'/>"> 
	<input type="hidden" name="irpMedal.crtime" value="<s:date name="irpMedal.crtime" format="yyyy-MM-dd HH:mm:ss" />"> 
	<tr> 
		<td align="right" bgcolor="#f5fafe" class="main_bleft">勋章名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpMedal.medalname"  validType="length[2,60]" class="easyui-validatebox" required="true" missingMessage="请输入勋章名称" type="text" value="<s:property value='irpMedal.medalname'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">最低兑换值：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpMedal.minworth" validType="integer" class="easyui-validatebox" required="true" missingMessage="请输入最低兑换值" type="text" value="<s:property value='irpMedal.minworth'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">最高兑换值：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpMedal.maxworth" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请输入最高兑换值" type="text" value="<s:property value='irpMedal.maxworth'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">勋章库存：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpMedal.medalnum" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请输入勋章库存" type="text" value="<s:property value='irpMedal.medalnum'/>" /></td>
	</tr>
	<tr style="display: none;">
		<td align="right" bgcolor="#f5fafe" class="main_bleft">勋章类别：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<input name="irpMedal.categoryid" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请输入勋章库存" type="text" value="<s:property value='irpMedal.categoryid'/>" />
		<input name="irpMedal.categoryname" validType="length[2,60]"  class="easyui-validatebox" required="true" missingMessage="请输入勋章类型" type="hidden" value="<s:property value='irpMedal.categoryname'/>" />
		
		</td>
	</tr>
<%-- 	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">勋章类别：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<input disabled="disabled" name="categoryname" validType="length[2,60]"  class="easyui-validatebox" required="true" missingMessage="请输入勋章类型" type="text" value="<s:property value='irpMedal.categoryname'/>" />
		</td>
	</tr> --%>
	<%-- <tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">勋章图片：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="file"  class="easyui-validatebox" missingMessage="请选择勋章图片" type="file" value="<s:property value='irpMedal.medalimage'/>" />
		<input type="hidden" name="irpMedal.medalimage" value="<s:property value='irpMedal.medalimage'/>" >
		<!-- <td bgcolor="#FFFFFF" class="main_bright">
		<a id="file" href="javascript:;" class="file gradient">
		<input id="file" name="file" type="file" />
		</a> -->
		</td>
	</tr> --%>
	
	<tr>
	<s:if test="irpMedal.medalid==null||irpMedal.medalid==''">
		<td align="right" bgcolor="#f5fafe" class="main_bleft">上传图片：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<%-- <input id="ssss" name="file" validType="length[2,60]"  class="easyui-validatebox" required="true" missingMessage="上传图片" type="file" value="<s:property value='irpGoods.goodsimage'/>" /> --%>
		<a href="javascript:void(0)" onclick="saveAttacthedByLeave()">选择图片</a>
		</td>
	</s:if>
	<s:else>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">修改图片：</td>
		<td bgcolor="#FFFFFF" class="main_bright">
		<%-- <input id="ssss" name="file" validType="length[2,60]"  class="easyui-validatebox" required="true" missingMessage="上传图片" type="file" value="<s:property value='irpGoods.goodsimage'/>" /> --%>
		<a href="javascript:void(0)" onclick="upAttacthedByLeave()">选择图片</a>
		</td>
	</s:else>
	</tr>
	<tr><td colspan="2">
	<div>
        	<textarea id="editor" name="irpMedal.medaldesc"><s:property value="irpMedal.medaldesc"/></textarea>
        	<script>
	        	CKEDITOR.replace('editor',{
					filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
					width:650
				});
				//构建图片上传地址
	            var sUrl = '<%=rootPath%>file/ck_word_upload.action;jsessionid=<%=session.getId()%>';
	            //构建应用名称，如本系统名称为‘wordimg’，如果为根应用，请写为空字符串''        
	            var appName = '<%=rootPath%>';
	            //创建WordImageUploader对象
	            /* var uploader = new CK_WordImageUploader(sUrl, appName); */
	            //当ckeditor内容改变时，自动检测并上传内容中的本地图片
	            /* CKEDITOR.instances.editor.on('paste', function(event) {
	            	uploader.uploadWordImagesFromCKEditor(CKEDITOR.instances.editor, event);
	            }); */
		    </script>
        </div></td></tr>
</table>
</form>
</body>
</html>
