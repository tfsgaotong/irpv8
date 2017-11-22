<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>修改上传附件</title> 
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
</head> 
  <body>   
<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/jquery.uploadify.js"></script> 
<script type="text/javascript" src="<%=rootPath %>admin/js/json2.js"></script>
<script type="text/javascript"> 
  //此处需要让其动态的生成一个table并填充数据 
function createTable(){
	  var tableStr = "<table class='ellipsis' id='updateatttab' width=100%  border=0 align=center cellpadding=10 cellspacing=1 bgcolor=#cad9ea >"; 
	  tableStr = tableStr + "<thead><tr><td align=center bgcolor=#F5FAFE class=main_bleft>原文件名称</td><td  align=center bgcolor=#F5FAFE class=main_bleft>显示名称</td><td  align=center bgcolor=#F5FAFE class=main_bleft>是否封面</td><td   align='center' bgcolor=#F5FAFE class=main_bleft>排序</td><td   align='center' bgcolor=#F5FAFE class=main_bleft>操作</td></tr></thead>"; 
	  var len = _allattacheds.length;
	  for(var i=0 ;i<len ; i++){ 
      	if(_allattacheds[i].editversions=="1"){//选择的文件
			tableStr = tableStr + "<tr><td name='"+_allattacheds[i].attfile+"' style=\"cursor:pointer; margin: 0 3px;\" onmouseout=\"hidepic()\" onmouseover=\"showpic('"+_allattacheds[i].attfile+"')\" bgcolor=#F5FAFE class=main_bleft>"+ _allattacheds[i].attdesc +"</td><td   bgcolor=#F5FAFE class=main_bleft>"+_allattacheds[i].attlinkalt+"</td><td align=center bgcolor=#F5FAFE class=main_bleft><input type='radio' value=1 name='editversions' checked id='"+_allattacheds[i].attfile+"'></td>"; 
		}
		if(_allattacheds[i].editversions=="0"){//一种就是没有选择的文件
		    tableStr = tableStr + "<tr><td name='"+_allattacheds[i].attfile+"' style=\"cursor:pointer; margin: 0 3px;\" onmouseout=\"hidepic()\" onmouseover=\"showpic('"+_allattacheds[i].attfile+"')\"  bgcolor=#F5FAFE class=main_bleft>"+ _allattacheds[i].attdesc +"</td><td  bgcolor=#F5FAFE class=main_bleft>"+_allattacheds[i].attlinkalt+"</td><td align=center bgcolor=#F5FAFE class=main_bleft><input type='radio' value=1 name='editversions'  id='"+_allattacheds[i].attfile+"'></td>"; 
	    }
	    if(_allattacheds[i].editversions=="2"){//一种就是附件，
		    tableStr = tableStr + "<tr><td name='"+_allattacheds[i].attfile+"' style=\"cursor:pointer; margin: 0 3px;\" onmouseout=\"hidepic()\" onmouseover=\"showpic('"+_allattacheds[i].attfile+"')\"  bgcolor=#F5FAFE class=main_bleft>"+ _allattacheds[i].attdesc +"</td><td   bgcolor=#F5FAFE class=main_bleft>"+_allattacheds[i].attlinkalt+"</td><td align=center bgcolor=#F5FAFE class=main_bleft></td>"; 
		}	
	    tableStr+="<td align='center' bgcolor=#F5FAFE class=main_bleft><input  onkeyup='updateorderby(this)' onbeforepaste='updateorderby(this)'  value='"+_allattacheds[i].attflag+"'></td>";
	    tableStr+="<td  align='center' bgcolor=#F5FAFE class=main_bleft><img onclick=delRow(this,'"+_allattacheds[i].attfile+"') border='0' src='<%=rootPath %>admin/images/icons/cancel.png' title=删除  style='cursor:pointer; margin: 0 3px;'' /></td></tr>";
	  } 
	  tableStr = tableStr + "</table>"; 
	  //将动态生成的table添加的事先隐藏的div中. 
      $("#dataTable").html(tableStr);   
} 
    	$(document).ready(function() {  
			 createTable(); 
			 
				var maxamount = $.ajax({
			    	type:"get",
			    	url:"<%=rootPath%>file/findsize.action",
			    	cache:false,
			    	async:false
				 }).responseText;
				var allfileext = $.ajax({
			    	type:"get",
			    	url:"<%=rootPath%>file/findfileext.action",
			    	cache:false,
			    	async:false
				 }).responseText; 
			 
			 
    	     $("#uploadify").uploadify({
    	        //开启调试
    	        'debug' : false,
    	        //是否自动上传
    	        'auto':true,
    	        //是否多文件上传
    	        'multi' : true,
    	        //超时时间
    	        'method': 'POST',
    	        'successTimeout':99999, 
    	        //附带值
    	        'formData':{
    	            "attAchedId":"",
    	            "docid":""
    	        },
    	        //flash
    	        'swf': "<%=rootPath%>admin/js/jquery.uploadify/uploadify.swf",
    	        //不执行默认的onSelect事件
    	        'overrideEvents' : ['onDialogClose'],
    	        //文件选择后的容器ID
    	        'queueID':'fileQueue',
    	        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
    	        'fileObjName':'myFile',
    	        //上传处理程序
    	        'uploader':'<%=rootPath%>site/upload.action;jsessionid=<%=session.getId()%>',
    	        //浏览按钮的宽度
    	        'width':'100',
    	        //浏览按钮的高度
    	        'height':'20', 
    	        //在浏览窗口底部的文件类型下拉菜单中显示的文本
    	        'fileTypeDesc':'支持的格式：', 
    	        //上传文件的大小限制
    	        'buttonText' : '选择文件',//浏览按钮
    	        //上传数量
    	        'queueSizeLimit' : 3,  
    			'fileSizeLimit' : maxamount, 
    			'fileTypeExts': allfileext, 
    	        'removeCompleted': true,
    	        'removeTimeout':1, 
    	        'onSelectError':function(file, errorCode, errorMsg){
    	            switch(errorCode) {
    	                case -100:
    	                    alert("上传的文件数量已经超出系统限制的"+$('#uploadify').uploadify('settings','queueSizeLimit')+"个文件！");
    	                    break;
    	                case -110:
    	                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#uploadify').uploadify('settings','fileSizeLimit')+"大小！");
    	                    break;
    	                case -120:
    	                    alert("文件 ["+file.name+"] 大小异常！");
    	                    break;
    	                case -130:
    	                    alert("文件 ["+file.name+"] 类型不正确！");
    	                    break;
    	            }
    	        }, 
    	        'onFallback':function(){
    	            $.messager.alert("提示信息","您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。","warning");
    	        }, 
    	        'onUploadSuccess':function(file, data, response){
    	        	//当table加一行 
	   	            var rowtr="<tr>";
	   	      		 //源文件名
	   	         	rowtr=rowtr+"<td id='lookimg' name='"+data+"' onmouseover=showpic('"+data+"') onmouseout='hidepic()' class='main_bleft'  bgcolor='#F5FAFE' style='cursor:pointer; margin: 0 3px;'>"+file.name+"</td>";
	   	       		//显示名
	   	         	rowtr=rowtr+"<td ondblclick='tr' bgcolor='#F5FAFE' class='main_bleft'>"+file.name+"</td>";
	   	         	var kValue=2;
		   	        //是否为封面按钮
   	         		var radiostr="";
	   	         	 $.ajax({
	 					type:"post",
	 					data : {'fileName' :data},
	 					async: false,
	 					url:"<%=rootPath%>site/isradio.action" ,
	 					success:function(msg){
	 						if(msg=="1"){//说明他是图片
 						        radiostr="<input id="+data+" type='radio' name='editversions' value='1'>";
	 					        kValue=0; 
	 			    	     }else{kValue=2;}
	 					}
	 				});  
	   	         	rowtr=rowtr+"<td   bgcolor='#F5FAFE' class='main_bleft' align='center'>"+radiostr+"</td>";
	   	       			//排序
		   	         var  tablerowAmount=$('#updateatttab').find('tr');
	   	         	 rowtr=rowtr+"<td bgcolor='#F5FAFE' class='main_bleft' align='center'><input onkeyup='updateorderby(this)' onbeforepaste='updateorderby(this)' value='"+tablerowAmount.length+"'></td>";
	   	       		//操作
	   	      		rowtr=rowtr+"<td bgcolor='#F5FAFE' class='main_bleft' align='center'>";
	   	      		//添加操作图片
	   	      		rowtr=rowtr+"<img onclick= delJsonAtt(this,'"+data+"')  src='<%=rootPath %>admin/images/icons/cancel.png' border='0' title='删除' style='cursor:pointer; margin: 0 3px;'/>";
	   	      		rowtr=rowtr+"</td>";
	   	            rowtr=rowtr+"</tr>";
	   	            $('#updateatttab').append(rowtr);
    	        	   
    	        	 //将刚刚上传的对象添加到json数组里 
    	        	 var irpAttached={
    	        	 		"attachedid" :0,
    	        	 		"attfile": data,
    	        	 		"attdesc":file.name,
    	        	 		"editversions": kValue,
    	        	 		"attlinkalt":file.name,
    	        	 		"attflag":tablerowAmount.length
    	        	 }; 
    	        	  _allattacheds.push(irpAttached); //经测试放入数组成功
    	        } 
    	    });  
    	     ///注册页面关闭时间
    		$(window).unload(function(){
				$.ajax({
					async : false,//同步的
					cache : false,
					type : 'post',
				  	url : '<%=rootPath%>site/deletejsonfilelist.action',
					data : { jsonFile: JSON.stringify(_allattacheds)}
				});
			}); 
    	    
    	}); 
   	   //本来跳入该页面就有的元素删除这一行调用
   	  function  delRow(obj,_attfile){  
         $.messager.confirm('提示信息','您确定要删除吗？',function(r){
             if(r){  
             var vTr= $(obj).parent().parent() ; 
             vTr.remove(); //移除这一行 
             delJsonAtt(_attfile);//移除json数组里面的这一个元素  
        	 }
         });
   	   } 
   	 var handle=null;
   	//显示
  	function showpic(picname){
  		handle=setTimeout(function(){
  		 ///发送一次请求，看是不是图片类型的 
  		 $.ajax({
				type:"post",
				data : {'fileName' :picname},
				cache: false,
				async: false,
				url:"<%=rootPath%>site/isradio.action" ,
				success:function(msg){
					if(msg=="1"){//说明他是图片
						 $("#imgshow").attr("style","display: block");
						 var index= picname.lastIndexOf(".");
						 var pnamea=picname.substring(0,index);
						 var pnameb=picname.substring(index,picname.length);
						 var pname= pnamea+"_150X150"+pnameb;
						 $.ajax({
								type:"post",
								data : {'sname' :pname},
								cache: false,
								async: false,
								url:"<%=rootPath%>menu/exit_zoom.action" ,
								success:function(msg){
									if("yes"==msg){
										$("#mypic").attr("src","<%=rootPath %>file/readfile.action?fileName="+pname);
									}else{
										$("#mypic").attr("src","<%=rootPath %>file/readfile.action?fileName="+picname);
									}
								}
						 })
					}
				}
			}); 
  		},200);
  	}
   	
  	function hidepic(){
  		clearTimeout(handle);
  		$("#imgshow").attr("style","display: none");
  	}
   //点击删除按钮的时候，删除json数组里面的对象 
  function delJsonAtt(_attfile){   
  	 		for(var i=0;i<_allattacheds.length;i++){ 
			 	var _attobj= _allattacheds[i];//对象   
		        if(_attobj.attfile == _attfile){//如果删除的时候传过来的相同  
		            _allattacheds.splice(i,1);//删除这个元素 
		             $.ajax({
			          	type : 'POST',
			          	url : '<%=rootPath %>site/deletejsonfile.action', 
			          	data : { myFileName: _attfile} 
			          });
	            } 
			 }  
  } 

  
	//修改排序字段
	  function updateorderby(thisobj){
	 	//验证格式是否为数字 
	 	thisobj.value=thisobj.value.replace(/\D/g,'');//确保当前的输入的值为数字
	 	//确保所填的最大值是列表最大的行数，最小值为最1
	 	var  trs=$('#updateatttab').find('tr');
	 	var tramount=trs.length-1; 
	 	if(parseInt($(thisobj).val())<1 ||$(thisobj).val()==""){$(thisobj).val(1);}
	 	if(parseInt($(thisobj).val())>tramount){$(thisobj).val(tramount);}
	 	var lastValue=$(thisobj).val(); //得到最终的那个数值
	  	var index=$(thisobj).parent().parent().index()+1; //原来所在位置
	 	if(index>lastValue){//向前移动 
	 	 	var thisTr= $(thisobj).parent().parent() ; //当前所在行
	 	 	var ces=$(thisTr).find('td');
	 	 	for(var i=index-1;i>=lastValue;i--){//将他前面的所有就向后移动一行
	 	 		var cesupi=$(thisTri=$(trs[i])).find('td');
	 	 		$(cesupi[3]).find("input").val(i+1);
	 			$($('#updateatttab').find('tr')[parseInt(i+1)]).html(cesupi); 
	 		}
	 	 	$($('#updateatttab').find('tr')[parseInt(lastValue)]).html(ces);
	 	}else if(index<lastValue){//向后移动
	 		//获取当前行
	 		var thisTr= $(thisobj).parent().parent() ; //当前所在行
	 		var ces=$(thisTr).find('td');
	 	 	for(var i=index+1;i<=parseInt(lastValue);i++){
	 	 		var cesupi=$(thisTri=$(trs[i])).find('td');
	 	 		$(cesupi[3]).find("input").val(i-1);
	 			if(i-1>0){
	 				$($('#updateatttab').find('tr')[parseInt(i-1)]).html(cesupi); 
	 			}
	 	 	}  
	 		$($('#updateatttab').find('tr')[parseInt(lastValue)]).html(ces);
	 		$($($('#updateatttab').find('tr')[parseInt(lastValue)]).find('td')[3]).find("input").val(lastValue);
	 	} 
	   	//每一次排序完之后修改json的顺序
	 	var filetable=$('#updateatttab');
	 	if(filetable && addJsonFileList){
	 		var newFileArray=new Array(); 
	 		var rows=$(filetable).find('tr');
	 		if(rows){
	 			for( var j=1;j<rows.length;j++){
	 				var cells=$(rows[j]).find('td'); 
	 				var filenameStr=$(cells[0]).attr("name"); 
	 				var kValue=0;//有单选按钮为0没有单选按钮为2
					if($(cells[2]).find('input:radio') &&$(cells[2]).find('input:radio').val()!="1" ){
						kValue=2;
					}
	 				var flag=$($($('#updateatttab').find('tr')[parseInt(j)]).find('td')[3]).find("input").val();
	 				var irpAttached={
	         	 		"attachedid" :0,
	         	 		"attfile": filenameStr,
	         	 		"attdesc":$(cells[0]).html(), 
	         	 		"editversions": kValue,
	         	 		"attlinkalt":$(cells[1]).html(), 
	         	 		"attflag":flag
	    	        	 }; 
	 				newFileArray.push(irpAttached); //经测试放入数组成功	
	 			}
	 		}
	 	}
	 	_allattacheds=newFileArray;
	   } 
</script>  
     <div id="fileQueue" style="width:300px"></div>
    <input type="file" name="uploadify" id="uploadify" multiple="true"/>  
   <div  id="dataTable" style="width: 586px;padding-top: 5px;"></div> 
    <div id="imgshow" style="display: none; position:relative;">
        <img alt="显示图片" src="" id="mypic"  width="120" height="120"/>
    </div>
</body>
</html>