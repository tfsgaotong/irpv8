<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>知识审核</title>
<link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=rootPath%>view/css/discuz.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath %>view/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>view/css/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=rootPath %>view/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/modernizr.custom.js"></script>
<script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>view/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	//初始化分页
	initConnPage();
	
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator: function(value, param){   
	            return value.length < param[0];   
	        },
	        message: '输入内容长度不能超过{0}'  
		}
	});
});

//初始化文章正文中的分页
function initConnPage(){
	$('#page_break .num li:first').addClass('on');
	$('#page_break .num a').click(function(){
		var _text= $.trim($(this).text());
		var _num=parseInt(0);
		 if(_text=="上一页"){
			 //获取当前选中的li ,然后将它小于1的点击一下，如果当前选中的是1就不执行
			 $('#page_break').find('li').each(function(){ 
					if(this.className=="on"){
						var _id=this.id;
						if(_id==1)return;
						_num=parseInt(_id)-1;
					}	 
		      });  
		 }else if(_text=="下一页"){
			 var len=$('#page_break').find('li').length;
		    $('#page_break').find('li').each(function(){ 
		    	if(this.className=="on"){
					var _id=this.id;
					if(_id==len)return;
					 _num=parseInt(_id)+1;
				}	 
	        });  
		 }
		 $("#page_break").find('#'+_num).click();return ;
	});
	$('#page_break .num li').click(function(){
		//隐藏所有页内容
		$("#page_break div[id='page_1']").hide(); 
		//显示当前页内容。 
		  var li=$('#page_break').find('li');
	      var len= li.length ;
		if ($(this).hasClass('on')) { 
			$('#page_break #page_' + $(this).text()).show();
		} else {
			$('#page_break .num li').removeClass('on');
			$(this).addClass('on');
			$('#page_break #page_' + $(this).text()).fadeIn('normal');
			for(i=1;i<=len;i++){
				if(parseInt($(this).text())==i){}else{
					$("#page_break div[id='page_"+i+"']").hide(); 
				}
			}
		}
	});
}

//显示全部正文数据
function showAllData(){
	$('.collapse').each(function(){
		$(this).show();
	});
	$('.num').hide();
}

function transferProcess(transferType){
	var jqPostDesc = $('#postDesc');
	var validate = jqPostDesc.validatebox('isValid');
	if(!validate){
		jqPostDesc.focus();
		return false;
	}else{
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>flow/transfer_process.action',
			data : {
				flowObjId: ${flowObj.flowobjid},
				postDesc: jqPostDesc.val(),
				transferType: transferType
			},
			success : function(callback) {
				if(callback=='1'){
					$.dialog.tips('审核成功',1,'32X32/succ.png',function(){
						if (window.opener && !window.opener.closed) {
							try{
								window.opener.findFlowConn();
							}catch(e){}
	    				}
	    			   	window.close();
					});
				}else{
					$.dialog.tips('审核失败',1,'32X32/fail.png');
				}
			}
		});
	}
}
//搜索名字
function searchInfo1(searchInfo){  
		searchtype = 5;  
		if(searchInfo.length>38){
			searchInfo = searchInfo.substring(0,37);	
		}
		var eacapeInfo = encodeURI(searchInfo);
	    window.location.href="<%=rootPath%>solr/searchcontentofsolr.action?searchInfo="+eacapeInfo+"&searchtype="+searchtype; 
}

//修改文章
function updateDocument(_docid){
	window.open('<%=rootPath%>site/to_update_subject.action?docid='+_docid);
}

//删除文档
function deleteDocument(_docid){
	$.dialog.confirm('您确定要删除这篇知识吗？',function(){
		$.ajax({
			type:'post',
			url:'<%=rootPath%>site/clientdeletedocument.action',
			data:{'docid':_docid},
			success: function(msg){
				if(msg=='1'){
					$.dialog.tips('删除知识成功',0.5,'32X32/succ.png',function(){
						if (window.opener && !window.opener.closed) {
							try{
								window.opener.findFlowConn();
							}catch(e){}
	    				}
						window.close();
					});  
				} 
			}
		});
	},function(){}); 
}

//查看流转轨迹
function showFlowPath(_nObjId){
	$.dialog({
		title:'流转轨迹',
		max:false,
		min:false,
		lock:true,
		resize: false,
		width:'600px',
		height:'300px',
		content:'<iframe width="600" height="300" frameborder="0" marginheight="0" marginwidth="0" src="<%=rootPath %>flow/flow_path.action?docId='+_nObjId+'" scrolling="auto" ></iframe>',
		cancelVal:'关闭',
		okVal:'转发',
		cancel:function(){
		}
	});
}
</script>
</head>

<body>
<s:include value="../include/client_head.jsp" />
<section class="mainBox">
	<nav class="privateNav">
    	<dl></dl>
    </nav>
</section>
<s:set var="cruser" value="@com.tfs.irp.util.LoginUtil@findUserById(irpDocument.cruserid)" />
<section class="mainBox">
	<section class="details" style="width:auto;float:none;">
    	<article class="location"><strong>知识审核</strong> > <s:property value="flowNode.nodename" /></article>
        <section class="text">
        	<article class="straight">
       		<div id="printConn">
            	<h1><s:property value="irpDocument.doctitle" /></h1>
                <table class="textInfo">
                	<tr>
                		<td>创建时间：<s:date name="irpDocument.crtime" format="yyyy-MM-dd HH:mm" /></td>
                		<td width="20"></td>
                		<td>创建者：<a href="<s:url namespace="/site" action="client_to_index_person"><s:param name="personid" value="#cruser.userid" /></s:url>" target="_blank"><s:property value="#cruser.showName" /></a></td>
                	</tr>
                	
                </table>
                <ul class="list4">
                	<li>
                		<section style="padding-left:0px;">
                			<p style="font-size:14px;line-height:24px;color:#666;text-indent:0px;"><s:property value="irpDocument.docabstract" /></p>
                		</section>
                		<div class="clear"></div>
                	</li>
                </ul>
                <div id="partOfDoc" class="documenttxt">
					<s:property value="irpDocument.dochtmlcon" escapeHtml="false" />
				</div>
			</div>
				<s:if test="attacheds!=null && attacheds.size()>0">
				<div class="annex">
                	<font>相关附件：</font>
                	<span><table>
					<s:iterator value="attacheds">
						<tr>
							<td><s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -11px -10px;"></span>
							</s:if>
							<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -60px -10px;"></span>
							</s:elseif>
							<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MENU_FIELD_NAME==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -110px -10px;"></span>
							</s:elseif>
							<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@MICUS_FIELD_NAME==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -160px -10px;"></span>
							</s:elseif>
							<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@ID_FIELD_NAMEOTHER==typeid">
								<span style="display: block;background-image: url(<%=rootPath %>client/images/att.png); width:40px; height:43px; background-position: -210px -10px;"></span>
							</s:elseif></td>
							<td style="padding-left: 20px;">
								<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">
									<s:property value="attdesc" />
								</a>
							</td>
							<td style="padding-left: 20px;">
								<s:if test="isFuJian=='true'">
								<a href="<%=rootPath%>file/readfile.action?fileName=<s:property value='attfile'/>&fileTrueName=<s:property value='attdesc'/>">下载</a>
								</s:if>
								<s:if test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@WORD_FIELD_NAME==typeid">
								| <a target="_blank" href="<%=rootPath%>file/online_preview.action?fileName=<s:property value='attfile'/>">在线浏览</a>
								</s:if>
								<s:elseif test="@com.tfs.irp.attachedtype.entity.IrpAttachedType@JPG_FIELD_NAME==typeid">
								|  <a target="_blank" href="<%=rootPath%>file/inlinereadfile.action?fileName=<s:property value='attfile'/>">在线浏览</a>
								</s:elseif>
							</td>
						</tr>
					</s:iterator>
					</table></span>
                </div>
				</s:if>
            </article>
            <div class="tags">
            	<font>标签：</font>
            	<s:iterator value="irpDocument.dockeywords.split(',')" status="st" var="as">
				<a href="javascript:void(0)" onclick="searchInfo1('<s:property value="#as"/>')"><s:property value="#as" /></a>&nbsp;&nbsp;
  				</s:iterator>
            </div>
            <div class="sociality">
            	<a href="javascript:void(0)" onclick="showFlowPath(<s:property value='irpDocument.docid'/>)"><span>轨迹</span><label>轨迹</label></a>
            	<a href="<s:url action="document_edit" namespace="/document"><s:param name="docid" value="irpDocument.docid" /></s:url>" target="_blank"><span>修改</span><label>修改</label></a>
            	<a href="javascript:void(0)" onclick="deleteDocument(<s:property value='irpDocument.docid'/>)"><span>删除</span><label>删除</label></a>
            </div>
        </section>
        <section id="commentView" class="discussions">
        	<div class="title">审核信息</div>
          	<div class="discussing">
                <div class="someText" style="text-align:center;">
                	<textarea id="postDesc" class="easyui-validatebox txta" required="true" validType="maxLength[300]" style="width: 550px; height: 200px;"></textarea>
               	</div>
               	<div class="sub" style="text-align: center;">
				<s:if test="flowNode.nodetype!='start'">
					<input type="button" onclick="transferProcess(1)" value="审核通过"/>
					<input type="button" onclick="transferProcess(2)" value="返回上一级"/>
					<input type="button" onclick="transferProcess(3)" value="返回作者"/>
				</s:if>
				<s:else>
					<input type="button" onclick="transferProcess(1)" value="继续审核"/>
				</s:else>
					<input type="button" onclick="window.close()" value="关闭"/>
				</div>
            </div>
        </section>
    </section>
</section>
<s:include value="../include/client_foot.jsp" />
</body>
</html>
