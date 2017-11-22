<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
   String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>    
<script type="text/javascript">
/**
 * 添加导航地址
 */
function addUserNavigation(){
	//获取弹出框内容
	var htmlcontent = $.ajax({
		type:'post',
		url:'<%=rootPath%>user/usernavigationaddedit.action',
		cache:false,
		async:false
	}).responseText;
	$.dialog({
		title:'添加网址',
		max:false,
		min:false,
		resize:false,
		lock:true,
		width:520,
		height:90,
		content:htmlcontent,
		okVal:'确定',
		ok:function(){
			var isvaliddate = $('#navigationform').form('validate');
			if(isvaliddate){
				var navigationnameofmine = $('#navigationnameofmine').val();
				var navigationvalueofmine = $('#navigationvalueofmine').val();
				$.ajax({
					type:'post',
					url:'<%=rootPath %>user/addusernavigation.action',
					data:{
						navigationname:navigationnameofmine,
						navigationvalue:navigationvalueofmine
					},
					cache:false,
					async:false,
					success:function(msg){
						if(msg==1){
							findMyNavigation();
						}else{
							$.dialog.tips('添加失败',1,'32X32/fail.png');
						}
					}
				});
			}else{
				return false;
			}
		},
		cancelVal:'取消',
		cancel:function(){
			
		}
	});
}
/**
 * 编辑导航地址
 */
function compileUserNavigation(){
	var htmlcontent = $.ajax({
		url:'<%=rootPath%>user/editpersonalnavigation.action',
		data:{
			pageNum:1
		},
		type:'post',
		cache:false,
		async:false
	}).responseText;
	$.dialog({
		title:'编辑网址',
		max:false,
		min:false,
		resize:false,
		lock:true,
		width:350,
		height:420,
		content:htmlcontent,
		okVal:'关闭',
		ok:function(){
			findMyNavigation();
		},
		cancel:function(){
			findMyNavigation();
		}
	});
}
/**
 * 修改网址
 */
function updateUserNavigation(_navigationid){
	var dialogdiv=document.createElement("div");
	dialogdiv.id="dialognavigan";
	document.body.appendChild(dialogdiv);
	$('#dialognavigan').dialog({   
	    title: '修改网址',   
	    width: 540,   
	    height: 168,   
	    closed: false,   
	    cache: false,   
	    href:'<%=rootPath%>user/usernavigationupdateedit.action?navigationid='+_navigationid,   
	    modal:true,
	    buttons:[{
	    	text:'确定',
	    	handler:function(){
				var isvaliddate = $('#navigationform').form('validate');
				if(isvaliddate){	
					var navigationnameofmine = $('#navigationnameofmine').val();
					var navigationvalueofmine = $('#navigationvalueofmine').val();
					var navigationidofmine = $('#navigationidofmine').val();
					$.ajax({
						type:'post',
						url:'<%=rootPath %>user/updateusernavigation.action',
						data:{
							navigationid:navigationidofmine,
							navigationname:navigationnameofmine,
							navigationvalue:navigationvalueofmine
						},
						cache:false,
						async:false,
						success:function(msg){
							if(msg==1){
								var queryString = $('#pageFormofnavigian').serialize();
								$.ajax({
									type:"post",
									url:"<%=rootPath%>user/editpersonalnavigation.action?"+queryString,
									async:false,
									cache:false,
									success:function(html){
										if(html!=null){
											$('#navigaineditdiv').html(html);
										}
									}
								});	
								$('#dialognavigan').dialog('destroy');
							}else{
								$.dialog.tips('修改失败',1,'32X32/fail.png');
								$('#dialognavigan').dialog('destroy');
							}
						}
					});
				}else{
					return false;
				}
	    	}
	    },{
	    	text:'取消',
	    	handler:function(){
	    		$('#dialognavigan').dialog('destroy');
	    	}
	    }],
        onClose:function(){
	    	$('#dialognavigan').dialog('destroy');
	    }  
		    
	});   
}
/**
 * 删除网址
 */
function deleteUserNavigation(_navigationid){
	$.messager.confirm('确认', '您确定要删除吗?', function(r){
		if (r){
			$.ajax({
				type:'post',
				url:'<%=rootPath %>user/deleteusernavigation.action',
				data:{
					navigationid:_navigationid
				},
				cache:false,
				async:false,
				success:function(msg){
					if(msg==1){
						var queryString = $('#pageFormofnavigian').serialize();
						$.ajax({
							type:"post",
							url:"<%=rootPath%>user/editpersonalnavigation.action?"+queryString,
							async:false,
							cache:false,
							success:function(html){
								if(html!=null){
									$('#navigaineditdiv').html(html);
								}
							}
						});	
					}else{
						$.dialog.tips('删除失败',1,'32X32/fail.png');
					}
				}
			});
		}
	});
}

</script>
<style type="text/css">
.ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
<dt>
	<a href="javascript:void()" class="linkbh14">我的导航</a>&nbsp;
	
	
	<a href="javascript:void(0)" onclick="addUserNavigation()" class="linkc12"><img src="<%=rootPath%>client/images/icons/edit_add.png" style="width: 12px;">&nbsp;添加</a>&nbsp;
	
	<a href="javascript:void(0)" onclick="compileUserNavigation()" class="linkc12"><img src="<%=rootPath%>client/images/icons/mini_edit.png" style="width: 12px;">&nbsp;编辑</a>
</dt>
<s:iterator value="irpUserNavigationlist">
	<dd class="ellipsis">
		<a href="<s:property value="navigationvalue" />" title="<s:property value="navigationname" />" target="_bank">
			<s:property value="navigationname" />
		</a>		
	</dd>
</s:iterator>
