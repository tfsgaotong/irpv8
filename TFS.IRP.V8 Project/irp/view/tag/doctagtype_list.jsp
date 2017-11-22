<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<style type="text/css">
.main-gr .right .combo input{
  border: 0px;
}
.edit_usergourp{
  display: none;
}
.index_title span {font-size:13px;}
.school_info_title, .school_info_title .title a {background-color:#f7f7f7;}
.list6{}
.list6 li{margin-bottom:20px; float:left; display:inline; margin-right:20px; height:28px; overflow:hidden;}
.list6 li a{line-height:28px; height:28px; float:left; display:inline-block; padding:0 15px; background:#5f9ddd; color:#fff; text-decoration:none; font-size:13px;}
.list6 li span{float:left; display:inline-block; height:0; width:0; border-left:6px solid #5f9ddd; border-top:3px solid transparent; border-bottom:3px solid transparent; margin-top:11px;}
.list6 li aside{position:inherit;margin-left:5px; float:left; display:inline-block; font-size:15px; color:#999; line-height:28px; height:28px; cursor:pointer; overflow:hidden; display:none;}
.list6 li:hover aside{display:inline-block;}
.list6 li aside:hover{ color:#5f9ddd;}
.list6 li a:hover{background:#79b6f6;}
</style>

<script type="text/javascript" >
<!--
var lhbDialog;
//保存标签类型
function saveTagType(){
	$("#tagTypeFrom").form('submit',{
		url : "<%=rootPath %>tag/tag_type_edit_dowith.action",
		success:function(data){
			if(data==1){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					findTagConn();
				});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}

function editTagType(){
	$("#tagTypeEditFrom").form('submit',{
		url : "<%=rootPath %>tag/tag_type_edit_dowith.action",
		success:function(data){
			if(data>0){
				$.dialog.tips('保存成功',1,'32X32/succ.png',function(){
					findTagConn();
				});
			}else{
				$.dialog.tips('保存失败',1,'32X32/fail.png');
			}
		}
	});
}

//编辑标签类型
function showTagTypeEdit(_nTypeId){
	var editEdu = undefined;
	var editEdus = $(".edit_usergourp");
	editEdus.each(function(index, domEle){
		var jDom = $(domEle);
		if(jDom){
			if(jDom.attr('id')==_nTypeId){
				$.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>tag/tag_type_edit.action',
					data : {
						typeId: _nTypeId
					},
					success : function(callback) {
						jDom.css("display","block");
						jDom.html(callback);
						jDom.find("input:text[name='irpTagType.typename']").validatebox();
					}
				});
			}else{
				jDom.html('');
				jDom.css("display","none");
			}
			if($('#oper_'+jDom.attr('id')).find('a').length>2){
				$('#oper_'+jDom.attr('id')).find('a:first').remove();
			}
		}
	});
}

//删除标签类型
function deleteTagType(_nTypeId){
	$.dialog.confirm('是否删除此标签类型？', function(){
		$.ajax({
			cache : false,
			type : 'post',
			url : '<%=rootPath%>tag/delete_tagtype_dowith.action',
			data : {
				typeId: _nTypeId
			},
			success : function(callback) {
				if(callback>0){
					$.dialog.tips('删除成功',1,'32X32/succ.png',function(){
						findTagConn();
					});
				}else{
					$.dialog.tips('删除失败',1,'32X32/fail.png');
				}
			}
		});
	});
}

//关闭编辑页面
function cancelEdit(_nTypeId){
	var jDom = $(".edit_usergourp[id='"+_nTypeId+"']");
	if(jDom){
		jDom.html('');
		jDom.css("display","none");
	}
}

function showTagList(_nTypeId){
	var editEdu = undefined;
	var editEdus = $(".edit_usergourp");
	editEdus.each(function(index, domEle){
		var jDom = $(domEle);
		if(jDom){
			if(jDom.attr('id')==_nTypeId){
				$.ajax({
					cache : false,
					type : 'post',
					url : '<%=rootPath%>tag/tag_list.action',
					data : {
						typeId: _nTypeId
					},
					success : function(callback) {
						jDom.css("display","block");
						jDom.html(callback);
						if($('#oper_'+jDom.attr('id')).find('a').length<=2){
							$('#oper_'+jDom.attr('id')).prepend('<a id="add" title="添加标签" href="javascript:void(0)" onclick="selectTag('+_nTypeId+')"></a>');
						}
						jDom.find("input:text[name='irpTagType.typename']").validatebox();
					}
				});
			}else{
				jDom.html('');
				if($('#oper_'+jDom.attr('id')).find('a').length>2){
					$('#oper_'+jDom.attr('id')).find('a:first').remove();
				}
				jDom.css("display","none");
			}
		}
	});
}

//选择用户
function selectTag(_typeId){
	//获得内容
	var result = $.ajax({
		type: 'GET',
		url: '<%=rootPath %>tag/select_tag.action',
	    data: "typeId="+_typeId,
	    async: false,
	    cache: false
	}).responseText; 
	//初始化弹出框
	lhbDialog = $.dialog({
		id: 'selectTag',
		title:'选择标签',
		content: result,
		max: false,
	    min: false,
		ok: function(){
			$('#dialogPageForm').form('submit',{
    			url : "<%=rootPath %>tag/select_tag_dowith.action",
    			success:function(data){
    				if(data=="1"){
    					$.dialog.tips('添加成功',1,'32X32/succ.png',function(){
    						showTagList(_typeId);
    					});
    				}else{
    					$.dialog.tips('添加失败',1,'32X32/fail.png');
    				}
    			}
    		});
	    },
	    okVal:'保存',
	    cancelVal: '关闭',
	    cancel: true,
	    lock: true,
	    padding: 0
	});
}

$(function(){
	/* 扩展验证  */
	$.extend($.fn.validatebox.defaults.rules, {   
		isExist: {/* 定义最小长度 */
	        validator: function(value, param){   
	            if(value.length < param[0] || value.length > param[1]){
	            	$.fn.validatebox.defaults.rules.isExist.message = $.fn.validatebox.defaults.rules.length.message;
					return false;
	            }else{
	            	var result = $.ajax({
	            		url:'<%=rootPath %>tag/check_typename.action',
	            		type: "POST",
	            		data: {
	            			typeId: param[2],
	            			typeName: value
	            		},
	            		dataType : "json",
	            		async: false,
	            		cache : false
	            	}).responseText;
	            	if(result=='0'){
	            		$.fn.validatebox.defaults.rules.isExist.message = '输入的标签类型名称已存在';
						return false;
	            	}else{
	            		return true;
	            	}
	            }
	        },
	        message: ''  
	    }
	});
	
	$('#typenameTxt').validatebox();
});
//-->
</script>
<section class="setUp">
	<div class="pan">
		<form id="tagTypeFrom" method="post" onsubmit="return false;">
            <table>
                <tr>
                    <td class="inputText">
                    	<input id="typenameTxt" name="irpTagType.typename" value="" style="height:24px;width:240px;" class="easyui-validatebox" required="true" validType="isExist[2,30,0]" missingMessage="请填写标签类型名称" />
                   	</td>
                   	<td width="3"></td>
                   	<td width="20">
                    	<font color="red">*</font>
                   	</td>
                   	<td class="sub">
                   		<input onclick="saveTagType()" type="button" value="添加标签类型"/>
					</td>
                </tr>
            </table>
		</form>
	</div>
	<div class="area20"></div>
	<div class="pan title"><span>标签类型</span></div>
	<div class="pan">
		<ul class="setList type1">
		<s:iterator value="irpTagTypes">
           	<li style="width:830px;">
           		<a href="javascript:void(0)" onclick="showTagList(<s:property value="typeid" />)"><s:property value="typename" /></a>
           		<aside id="oper_<s:property value="typeid" />">
           			<a id="edit" title="修改" href="javascript:void(0)" onclick="showTagTypeEdit(<s:property value="typeid" />)"></a><a id="del" title="删除" href="javascript:void(0)" onclick="deleteTagType(<s:property value="typeid" />)"></a>
      			</aside>
       		</li>
       		<div id="<s:property value="typeid" />" class="edit_usergourp"></div>
		</s:iterator>
           </ul>
	</div>
</section>
