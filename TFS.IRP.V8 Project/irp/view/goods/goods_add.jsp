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
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.combo{
	background: none repeat scroll 0 0 #f7f7f7;
	border: 1px solid #d1d1d1;
	border-radius: 3px;
}
.combo .combo-text{
	font-size: 15px;
}
</style>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/json2.js"></script>
</head>

<body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, { 
	integer : {// 验证整数
		validator : function(value) {
		return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入数字。'
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
var irpGoods="<s:property value='irpGoods'/>";
var attid="<s:property value='irpGoods.goodsimage'/>"
$(function(){
if(irpGoods!=null){
toUpdate(attid);
}
});
///添加附件
  function tosaveAttacthed(){  
		var saveattdiv=document.createElement("div");
		saveattdiv.id="saveattdiv";
		document.body.appendChild(saveattdiv);  
		$('#saveattdiv').dialog({
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
						       $('#saveattdiv').dialog('destroy');
						     }
						    }], 
			onClose:function(){
			    	$('#saveattdiv').dialog('destroy');
			}
		});   
  } 
  var addJsonFileList = new Array();
function saveAttacthedByLeave(){ 
var dialogDiv = document.createElement("div");
		dialogDiv.id="fileinfo";
		document.body.appendChild(dialogDiv);
		$('#fileinfo').dialog({
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
				$('#fileinfo').dialog('destroy');
			}
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    		$('#fileinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#fileinfo').dialog('destroy');
		    }
		});
}

var _allattacheds=null;
function toUpdate(_attachedid){ 
	//发送ajax请求获得所有附件
	$.ajax({ 
		type: "GET", 
		url: "<%=rootPath %>goods/findgoodsallattache.action?attachedids="+_attachedid, 
		success: function(msg){
			_allattacheds=eval(msg);//转换成附件集合    
		}
	});   
}
//修改附件
function upAttacthedByLeave(){ 
 var dialogDiv1 = document.createElement("div");
		dialogDiv1.id="edfileinfo";
		document.body.appendChild(dialogDiv1);		
		$('#edfileinfo').dialog({
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
				$('#edfileinfo').dialog('destroy');
			}  
		    	} 
		    	},{ 
		    	text: '取消', 
		    	iconCls: 'icon-cancel', 
		    	handler: function() { 
		    	$('#edfileinfo').dialog('destroy');
		    	} 
		    }],
		    onClose:function(){
		    	$('#edfileinfo').dialog('destroy');
		    }
		});   
}
/**
*添加基本模版
*/
function addTemplate(){
	var editor = CKEDITOR.instances.editor;
	var result = $.ajax({
		url:"<%=rootPath%>set/quotedittemplateknow.action",
		cache:false,
		async:false
	}).responseText;
	$.dialog({
  		content:result,
  		width:800,
  		height:600,
  		title:'引用模版',
  		min:false,
  		max:false,
	    lock:true,
	    ok:function(){
	    	var temhval = $('#temphiddenval').val();
	    	if(temhval!=''){
	    		$.ajax({
	    			type:'post',
	    			url:'<%=rootPath%>term/findqtemcate.action',
	    			async:false,
	    			cache:false,
	    			data:{
	    				tid:temhval
	    			},
	    			success:function(content){
	    				if(content!=''){
	    					editor.document.getBody().setHtml(editor.document.getBody().getHtml()+"<br/>"+content);
	    				}
	    			}
	    		});
	    	}
	    },
	    okVal:'确认',
//	    cancelVal: '取消',
//	    cancel: true,
	    padding: 0
  	});
}
</script>
<form id="addGoods" method="post" enctype="multipart/form-data">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea">
<input type="hidden" name="jsonFile" /><%-- 源文件名称 --%>
	<input type="hidden" name="irpGoods.goodsid" value="<s:property value='irpGoods.goodsid'/>"> 
	<input type="hidden" name="irpGoods.crtime" value="<s:date name="irpGoods.crtime" format="yyyy-MM-dd HH:mm:ss" />"> 
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">商品编码：</td>
		<s:if test="irpGoods!=null">
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.goodsno" id="aa" type="hidden" value="<s:property value='irpGoods.goodsno'/>"  /><s:property value='irpGoods.goodsno'/></td>
		</s:if>
		<s:else>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.goodsno" id="aa"  validType="isExitCode" class="easyui-validatebox" required="true" missingMessage="请填写商品编码" type="text" value="<s:property value='irpGoods.goodsno'/>" /></td>
		</s:else>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">商品名称：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.goodsname"  validType="length[2,60]" class="easyui-validatebox" required="true" missingMessage="请填写商品名称" type="text" value="<s:property value='irpGoods.goodsname'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">商品描述：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.goodsdesc" validType="length[2,100]" class="easyui-validatebox" required="true" missingMessage="请填写商品描述" type="text" value="<s:property value='irpGoods.goodsdesc'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">所需积分：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.needscore" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请填写所需兑换积分" type="text" value="<s:property value='irpGoods.needscore'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">价格：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.price" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请填写商品价格" type="text" value="<s:property value='irpGoods.price'/>" /></td>	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">库存量：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.stocknum" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请填写商品库存量" type="text" value="<s:property value='irpGoods.stocknum'/>" /></td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否上架：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.salestate" <s:if test="irpGoods.salestate==20">checked="checked"</s:if> validType="length[2,60]"   type="radio" value="20" checked="checked" />是
		<input name="irpGoods.salestate" <s:if test="irpGoods.salestate==10">checked="checked"</s:if> validType="length[2,60]"   type="radio" value="10" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">是否为积分兑换商品：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.coverstate" <s:if test="irpGoods.coverstate==20">checked="checked"</s:if> validType="length[2,60]"   type="radio" value="20" checked="checked" />是
		<input name="irpGoods.coverstate"  <s:if test="irpGoods.coverstate==10">checked="checked"</s:if>  validType="length[2,60]"   type="radio" value="10" />否</td>
	</tr>
	<tr>
		<td align="right" bgcolor="#f5fafe" class="main_bleft">商品序号：</td>
		<td bgcolor="#FFFFFF" class="main_bright"><input name="irpGoods.reorder" validType="integer"  class="easyui-validatebox" required="true" missingMessage="请填写商品序号" type="text" value="<s:property value='irpGoods.reorder'/>" /></td>
	</tr>
	<tr>
	        <td align="right" bgcolor="#f5fafe" class="main_bleft">勋章名称：</td>
	        <td bgcolor="#FFFFFF" class="main_bright">
				<select id="chnlorder" name="irpGoods.medalid" style=" width: 200px;" >
			   	     <option value="0" title="选择勋章">--选择勋章--</option>
				   	   <s:iterator value="listMedal">
				   		 <option <s:if test="irpGoods.medalid==medalid">selected="selected"</s:if> value="<s:property value='medalid'/>">
							<s:property value='medalname'/>
						</option>
				       </s:iterator>
			   </select>
		    </td>
	     </tr>
	<tr>
	<s:if test="irpGoods==null">
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
        	<textarea id="editor" name="irpGoods.contentdetial"><s:property value="irpGoods.contentdetial"/></textarea>
        	<script>
	        	CKEDITOR.replace('editor',{
					filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
					width:850
				});
				//构建图片上传地址
	            var sUrl = '<%=rootPath%>file/ck_word_upload.action;jsessionid=<%=session.getId()%>';
	            //构建应用名称，如本系统名称为‘wordimg’，如果为根应用，请写为空字符串''        
	            var appName = '<%=rootPath%>';
	            //创建WordImageUploader对象
	            var uploader = new CK_WordImageUploader(sUrl, appName);
	            //当ckeditor内容改变时，自动检测并上传内容中的本地图片
	            CKEDITOR.instances.editor.on('paste', function(event) {
	            	uploader.uploadWordImagesFromCKEditor(CKEDITOR.instances.editor, event);
	            });
		    </script>
        </div></td></tr>
</table>
</form>
</body>
</html>
