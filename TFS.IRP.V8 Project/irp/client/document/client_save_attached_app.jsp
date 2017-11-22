<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
String check = request.getParameter("check");
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>上传附件</title>  
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
<link href="<%=rootPath %>admin/js/jquery.uploadify/uploadify.css" rel="stylesheet" type="text/css" />
</head> 
<body>  

<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>admin/js/jquery.uploadify/jquery.uploadify.js"></script> 
<script type="text/javascript" src="<%=rootPath %>admin/js/json2.js"></script> 

<script type="text/javascript" >
var isdoc=<s:property value="isqusertionordoc"/>;
var token=<s:property value="token"/>;
function createTable(){
	  var tableStr = "<table class='ellipsis'  id=updateatttab width=100%  border=0 align=center cellpadding=10 cellspacing=1 bgcolor=#cad9ea >"; 
	  tableStr += "<thead><tr ><td align=center bgcolor=#F5FAFE class=main_bleft>原文件名称</td><td align=center bgcolor=#F5FAFE class=main_bleft>显示名称</td>";
      if(isdoc=="2"){
    	 tableStr +="<td  align=center bgcolor=#F5FAFE class=main_bleft>是否封面</td>";
      }
	  tableStr +="<td align='center' bgcolor=#F5FAFE class=main_bleft>排序</td><td align='center' bgcolor=#F5FAFE class=main_bleft>操作</td></tr></thead>";
	  var len = addJsonFileList.length;  
	  if(len>0){
		  tableStr+="<tbody id=\"datatbody\" >"; 
	  }
	  for(var i=0 ;i<len ; i++){   
	 	 if(addJsonFileList[i].editversions=="1"){//选择的文件
	 	     tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\"  style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" id=\"lookimg\" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
	 	      if(isdoc=="2"){
	 		      tableStr +="<td bgcolor=#F5FAFE class=main_bleft align='center' ><input type='radio' value=1 name='editversions' checked id='"+addJsonFileList[i].attfile+"'></td>";
	 	      }
	    }
	    if(addJsonFileList[i].editversions=="0"){//一种就是图片
	       tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\" id=\"lookimg\" style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE  ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
	       if(isdoc=="2"){
		 		tableStr +="<td align=center bgcolor=#F5FAFE class=main_bleft align='center' ><input type='radio' value=1 name='editversions'  id='"+addJsonFileList[i].attfile+"'></td>";
		 	}
	    }
	    if(addJsonFileList[i].editversions=="2"){//一种就不是图片的附件
	        tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\" id=\"lookimg\" style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE  ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
	        if(isdoc=="2"){
		 		tableStr +="<td align=center bgcolor=#F5FAFE class=main_bleft></td>";
		 	}
	   }
	    tableStr +="<td  align='center' bgcolor=#F5FAFE class=main_bleft><input type='text' style='width:20px;' onkeyup='updateorderby(this)' onbeforepaste='updateorderby(this)' value='"+addJsonFileList[i].attflag+"'></td>"; 
	    tableStr +="<td  align='center' bgcolor=#F5FAFE class=main_bleft><img onclick=delJsonAtt(this,'"+addJsonFileList[i].attfile+"') border='0' src='<%=rootPath %>admin/images/icons/cancel.png' title=删除  style='cursor:pointer; margin: 0 3px;'' /></td></tr>"; 
	  }
	  if(len>0){
		  tableStr+="</tbody>"; 
	  }
	  tableStr = tableStr + "<tbody id=tbody1  ></tbody></table>"; 
	  //将动态生成的table添加的事先隐藏的div中. 
	  $("#dataTable").html(tableStr);   
 }  
   function createTable1(){
		  var tableStr = "<table class='ellipsis'  id=updateatttab width=100%  border=0 align=center cellpadding=10 cellspacing=1 bgcolor=#cad9ea >"; 
		  tableStr += "<thead><tr ><td align=center bgcolor=#F5FAFE class=main_bleft>原文件名称</td><td align=center bgcolor=#F5FAFE class=main_bleft>显示名称</td>";
	      if(isdoc=="-3"){
	    	 tableStr +="<td  align=center bgcolor=#F5FAFE class=main_bleft>是否封面</td>";
	      }
		  tableStr +="<td align='center' bgcolor=#F5FAFE class=main_bleft>排序</td><td align='center' bgcolor=#F5FAFE class=main_bleft>操作</td></tr></thead>";
		  var len = addJsonFileList.length;  
		  if(len>0){
			  tableStr+="<tbody id=\"datatbody\" >"; 
		  }
		  for(var i=0 ;i<len ; i++){   
		 	 if(addJsonFileList[i].editversions=="1"){//选择的文件
		 	     tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\"  style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" id=\"lookimg\" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
		 	      if(isdoc=="-3"){
		 		      tableStr +="<td bgcolor=#F5FAFE class=main_bleft align='center' ><input type='radio' value=1 name='editversions' checked id='"+addJsonFileList[i].attfile+"'></td>";
		 	      }
		    }
		    if(addJsonFileList[i].editversions=="0"){//一种就是图片
		       tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\" id=\"lookimg\" style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE  ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
		       if(isdoc=="-3"){
			 		tableStr +="<td align=center bgcolor=#F5FAFE class=main_bleft align='center' ><input type='radio' value=1 name='editversions'  id='"+addJsonFileList[i].attfile+"'></td>";
			 	}
		    }
		    if(addJsonFileList[i].editversions=="2"){//一种就不是图片的附件
		        tableStr = tableStr + "<tr><td onmouseout=\"hidepic()\" onmouseover=\"showpic('"+addJsonFileList[i].attfile+"')\" id=\"lookimg\" style=\"cursor:pointer; margin: 0 3px;\" name="+addJsonFileList[i].attfile+" bgcolor=#F5FAFE class=main_bleft>"+ addJsonFileList[i].attdesc +"</td><td bgcolor=#F5FAFE  ondblclick=\"updatename(this)\" class=main_bleft>"+addJsonFileList[i].attlinkalt+"</td>";
		        if(isdoc=="-3"){
			 		tableStr +="<td align=center bgcolor=#F5FAFE class=main_bleft></td>";
			 	}
		   }
		    tableStr +="<td  align='center' bgcolor=#F5FAFE class=main_bleft><input type='text' style='width:20px;' onkeyup='updateorderby(this)' onbeforepaste='updateorderby(this)' value='"+addJsonFileList[i].attflag+"'></td>"; 
		    tableStr +="<td  align='center' bgcolor=#F5FAFE class=main_bleft><img onclick=delJsonAtt(this,'"+addJsonFileList[i].attfile+"') border='0' src='<%=rootPath %>admin/images/icons/cancel.png' title=删除  style='cursor:pointer; margin: 0 3px;'' /></td></tr>"; 
		  }
		  if(len>0){
			  tableStr+="</tbody>"; 
		  }
		  tableStr = tableStr + "<tbody id=tbody1  ></tbody></table>"; 
		  //将动态生成的table添加的事先隐藏的div中. 
		  $("#dataTable").html(tableStr);  
 }  
 $(function() { 
 var isTable1 = <%=check %>;
 if(isTable1==1){	
	createTable1();  
 }else{
 	createTable();  
} 
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
	 //alert(allfileext);
	 
   	$("#uploadify").uploadify({
        //开启调试
        'debug' : false,
        //是否自动上传
        'auto':true,
        //是否多文件上传
        'multi' : true,
        //超时时间
        'successTimeout':99999, 
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
        'buttonText' : '选择文件',//浏览按钮
        //上传数量
        'queueSizeLimit' : 3,  
		'fileSizeLimit' : maxamount, 
		'fileTypeExts': allfileext, 
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
				$('#uploadify').uploadify('upload');  
   	            //当table加一行 
   	            var rowtr="<tr>";
   	      		 //源文件名
   	         	rowtr=rowtr+"<td id='lookimg' name='"+data+"' onmouseover=showpic('"+data+"') onmouseout='hidepic()' class='main_bleft'  bgcolor='#F5FAFE' style='cursor:pointer; margin: 0 3px;'>"+file.name+"</td>";
   	       		//显示名
   	         	rowtr=rowtr+"<td ondblclick='tr' bgcolor='#F5FAFE' class='main_bleft'>"+file.name+"</td>";
   	     		var kValue=0;
   	     		if(isTable1==1){
   	     		 //是否为封面按钮
   	         	if(isdoc=="-3"){
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
		        }
   	     		}else{
   	     	 	if(isdoc=="2"){
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
		        }
   	     			
   	     		}	   	       
   	        	 //排序
	   	        var  tablerowAmount=$('#updateatttab').find('tr');
	   	        rowtr=rowtr+"<td bgcolor='#F5FAFE' class='main_bleft' align='center'><input type='text' style='width:20px;' onkeyup='updateorderby(this)' onbeforepaste='updateorderby(this)' value='"+tablerowAmount.length+"'></td>";
   	         	//操作
   	      		rowtr=rowtr+"<td bgcolor='#F5FAFE' class='main_bleft' align='center'>";
   	      		//添加操作图片
   	      		rowtr=rowtr+"<img onclick= delJsonAtt(this,'"+data+"')  src='<%=rootPath %>admin/images/icons/cancel.png' border='0' title='删除' style='cursor:pointer; margin: 0 3px;'/>";
   	      		rowtr=rowtr+"</td>";
   	            rowtr=rowtr+"</tr>";
   	         	$('#updateatttab').append(rowtr);
    	     	 ///发送一次请求，看是不是图片类型的 
   	        	 //将刚刚上传的对象添加到json数组里
   	        	 var kValue=2;//有单选按钮为0没有单选按钮为2
   	        	 var irpAttached={
   	        	 		"attachedid" :0,
   	        	 		"attfile": data,
   	        	 		"attdesc":file.name, 
   	        	 		"editversions": kValue,
   	        	 		"attlinkalt":file.name ,
   	        	 		"attflag":tablerowAmount.length
   	        	 }; 
   	        	 addJsonFileList.push(irpAttached); //经测试放入数组成功
   	          }   
   	   	 }); 
   	   	 
   	   	 
   	   	 
   	   	 
   	    ///注册页面关闭时间
   		$(window).unload(function(){
			$.ajax({
				async : false,//同步的
				cache : false,
				type : 'post',
			  	url : '<%=rootPath%>site/deletejsonfilelist.action',
				data : { jsonFile: JSON.stringify(addJsonFileList)}
			});
		}); 
  });
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
	 		 if(isdoc=="2"){
	 			$(cesupi[3]).find("input").val(i+1);
	 		 }else{
	 			$(cesupi[2]).find("input").val(i+1);
	 		 }
			
			$($('#updateatttab').find('tr')[parseInt(i+1)]).html(cesupi); 
		}
	 	$($('#updateatttab').find('tr')[parseInt(lastValue)]).html(ces);
	}else if(index<lastValue){//向后移动
		//获取当前行
		var thisTr= $(thisobj).parent().parent() ; //当前所在行
		var ces=$(thisTr).find('td');
	 	for(var i=index+1;i<=parseInt(lastValue);i++){
	 		var cesupi=$(thisTri=$(trs[i])).find('td');
	 		if(isdoc=="2"){
	 			$(cesupi[3]).find("input").val(i-1);
	 		 }else{
	 			$(cesupi[2]).find("input").val(i-1);
	 		 }
			if(i-1>0){
				$($('#updateatttab').find('tr')[parseInt(i-1)]).html(cesupi); 
			}
	 	}  
		$($('#updateatttab').find('tr')[parseInt(lastValue)]).html(ces);
		if(isdoc=="2"){
			$($($('#updateatttab').find('tr')[parseInt(lastValue)]).find('td')[3]).find("input").val(lastValue);
		}else{
			$($($('#updateatttab').find('tr')[parseInt(lastValue)]).find('td')[2]).find("input").val(lastValue);
		}
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
				if(isdoc=="2"){
					if($(cells[2]).find('input:radio') &&$(cells[2]).find('input:radio').val()!="1" ){
						kValue=2;
					}
				}
				var flag=0;
				if(isdoc=="2"){ 
					flag=$($($('#updateatttab').find('tr')[parseInt(j)]).find('td')[3]).find("input").val();
				}else{
					flag=$($($('#updateatttab').find('tr')[parseInt(j)]).find('td')[2]).find("input").val();
				}
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
	 addJsonFileList=newFileArray;
  }
    var handle=null;
    //显示
	function showpic(picname){
		handle=setTimeout(function(){
			///发送一次请求，看是不是图片类型的 
	  		 $.ajax({
					type:"post",
					data : {'fileName' :picname},
					async: false,
					url:"<%=rootPath%>site/isradio.action" ,
					success:function(msg){
						if(msg=="1"){//说明他是图片
							 $("#imgshow").css("display","block");
							 $("#imgshow").append("<img alt=\"显示图片\" src=\"<%=rootPath %>file/readfile.action?fileName="+picname+"\" id=\"mypic\" width=\"120\" height=\"120\"/>");
			    	     }
					}
				});  
		},200);
	}
	function hidepic(){
		clearTimeout(handle);
		$("#imgshow").empty();
		$("#imgshow").css("display","none");
		
	}
	//点击删除按钮的时候，删除json数组里面的对象 
	function delJsonAtt(obj,_attfile){  
	 $.messager.confirm('提示信息','您确定要删除吗？',function(r){
             if(r){ 
					var vTr= $(obj).parent().parent() ; 
	         		 vTr.remove(); //移除这一行  
	          	 for(var i=0;i<addJsonFileList.length;i++){ 
					 	var _attobj= addJsonFileList[i];//对象   
				        if(_attobj.attfile ==_attfile){//如果删除的时候传过来的相同  
				            addJsonFileList.splice(i,1);//删除这个元素 
				           //同时发送请求，删除服务器里面的文件
				          $.ajax({
				          	type : 'POST',
				          	url : '<%=rootPath %>site/deletejsonfile.action', 
				          	data : {myFileName:_attfile} 
				          	
				          });
				      
			            } 
				 	}
	         		
	         		$('#fileName').text('');
	         		$('#imgshow1').text("");
	         }		
	         		 },function(){}); 
	}   
  function updatename(_element){
	  var sinputtext= $(_element).text();
	  var inputtext="<input type=\"text\" value=\""+sinputtext+"\"/>";
	  $(_element).html(inputtext);
  }
</script>  
    <div id="fileQueue"></div>
    <input type="file" name="uploadify" id="uploadify" multiple="true"/> 
    <div id="dataTable" style="width: 586px;padding-top: 5px;"></div>
    <div id="imgshow" style="display:none;position:absolute;"></div>
</body>
</html>