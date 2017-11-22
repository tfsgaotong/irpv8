<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="java.util.*,com.tfs.irp.question.entity.IrpQuestion"%>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
java.util.UUID uuid  = java.util.UUID.randomUUID();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单数据增加</title>
<link href="../css/css_body.css" rel="stylesheet" type="text/css" />


<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>admin/css/css_body.css" />
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>editor/applet/ckwordup.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/json2.js"></script> 
</head>

<body>
<script type="text/javascript">
var tablename = '<s:property value="formtablename"/>';
var channelid = '<s:property value="channelid"/>';
$(function(){
var str = $.ajax({
			type:'post',
			url:'<%=rootPath %>form/toaddforminfo.action?formtablename='+tablename,
			async: false,
			cache: false
		}).responseText;
$('#tbinfo').html(str);
testForm = $('#addForm');  
	//初始化表单中的验证器  
			$('input[type!="hidden"],select,textarea',testForm ).each(function(){  
			    $(this).validatebox(); 
			});
});
$.extend($.fn.validatebox.defaults.rules, {   
	integer : {// 验证整数
		validator : function(value) {
		return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入数字。'
	},
	intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '请输入数字，并确保格式正确' 
    }, 
    number: {
		validator: function (value, param) {
		return /^[0-9]+.?[0-9]*$/.test(value);
		},
		message: '请输入数字'
	}, 
	age : {// 验证年龄
        validator : function(value) { 
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value); 
        }, 
        message : '年龄必须是0到120之间的整数' 
    }, 
     zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确' 
    }, 
     length:{//验证长度
     	validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
            message:"输入内容长度必须介于{0}和{1}之间." 
        }, 
    phoneNum: { //验证手机号  
        validator: function(value, param){
         return /^1(3|4|5|7|8)+\d{9}$/.test(value);
        },   
        message: '请输入正确的手机号码。'  
    },
   
    telNum:{ //既验证手机号，又验证座机号
      validator: function(value, param){
          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
         },   
         message: '请输入正确的电话号码。'
    },
    checkEmail: { //验证电子邮件 
        validator: function(value, param){
         return /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/.test(value);
        },   
        message: '请输入有效的电子邮件。'  
    },  
});
aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
function isCardID(sId){ 
	var iSum=0;  
	var info="";  
	if(!/^\d{17}(\d|x)$/i.test(sId)) 
		return "你输入的身份证长度或格式错误"; 
		sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null) 
		return "你的身份证地区非法"; 
		sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/"))  
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))
		return "身份证上的出生日期非法"; 
		for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11)  
			if(iSum%11!=1) return "你输入的身份证号非法"; 
				return true;
} 

$.extend($.fn.validatebox.defaults.rules, { 
	idcard: { 
		validator: function(value,param){ 
		var flag= isCardID(value); 
		return flag==true?true:false; 
	}, 
	message: '不是有效的身份证号码'
} 

});


//全局变量
var addJsonFileList = new Array();
function saveAttacthedByLeave(){ 
var dialogDiv = document.createElement("div");
		dialogDiv.id="fileinfo";
		document.body.appendChild(dialogDiv);
		$('#fileinfo').dialog({
			modal:true,
			href:'<%=rootPath %>site/client_to_save_attached.action?check=1&isqusertionordoc=2',
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
function setCheckValue(obj,_name){
	var id ="input[name='"+_name+"']";
	var ninfo ="";
	$("input:checkbox[name='"+obj.name+"']:checked").each(function(){
	ninfo+=','+this.value;
	});
	$(id).val(ninfo.substring(1));
}
 function channel(rec){   
            alert(1); 
            //var irpChannel=$(_obj).combobox('getValues');
        }   
        
function addDocument(tablename){
	$.messager.confirm('提示信息','您确定要保存知识吗？',function(r){   
	    if(r){
			var isValid = $('#addForm').form('validate');
			if(isValid){
				submitAddForm();
			}
   		}
   		})
	}
	function submitAddForm(){
	$.messager.progress({text : '数据提交中...'});
	$('#addForm').form('submit',{
		url : '<%=rootPath  %>form/addforminfo.action?formtablename='+tablename+'&channelid='+channelid,
		success : function(data){
			$.messager.progress('close'); 
			if(data>0){    
				window.opener.$('#mb').panel('refresh');  
				window.close();
			}else{
				$.messager.alert('提示信息','添加失败','warning');
			}
		} 
	});
}
//关闭页面
	function closeWindow(){
		$.messager.confirm('提示信息','您确定要关闭当前页面吗？',function(r){
				if(r){
				   window.close();
				} 
		}); 
	}	 
</script>
<form id="addForm" method="post">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#cad9ea" id="tbinfo">		
<s:property value="showinfo" escapeHtml="false"/>
</table>
<a onclick="addDocument()"></a>
<a onclick="closeWindow()"></a>
</form>
</body>
</html>
