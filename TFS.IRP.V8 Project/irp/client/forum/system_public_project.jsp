<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!-- 论坛首页 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
<head>
    <title>论坛</title>
    <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/oacss.css" />
    <link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
	<jsp:include page="../include/client_skin.jsp" />
	<style type="text/css">
		body{behavior:url(hover.htc);}
		.right{ border-right: 1px solid #E2E2E2;}
		.createworkwrap{padding:20px 300px;}
		.name_twl{line-height:30px;}
		.btn_ccw{ padding: 10px 50px 10px 45px;}	
	</style> 
</head>

<body> 
	<div class="bg01">
	    <jsp:include page="../../view/include/client_head.jsp" />
	    <section class="mainBox">
	        <nav class="privateNav"></nav>
	    </section>
	    
	    <div class="main-gr">
	 
	        <!--右侧内容-->
	        <div class="right" style="width:100%">
	            <h1 id="projectH" class="zl3" style="font-size:15px; font-weight:normal; line-height:50px; border-bottom:1px  solid #efefef; text-align:left; font-family:'微软雅黑'; margin:0 0 0px 5px; color:#121212;">
	                <a href="javascript:void(0);"  onclick="tabs(this)" id="allProject"  link="<%=rootPath%>project/allforumlist.action"  class="over">所有版块</a>
	            </h1> 
	            <div class="createworkwrap">
	                <div class="box_cw" id="boxHover">
	                    <div class="top_cw">
	                        <b><i></i></b>
	                    </div>
	                    <div class="content_cw clearfix">
	                        <a href="javascript:void(0)" onclick="toaddproject()" class="btn_ccw" id="getCworkPopup"><i>新建版块</i></a>
	                    </div>
	                    <div class="bottom_cw">
	                        <b><i></i></b>
	                    </div>
	                </div>
	            </div> 
	            <div id="project_listDiv"></div> 
	        </div>
	    </div>
	    
		<!--尾部信息-->
		<jsp:include page="../include/client_foot.jsp" />
    </div>
</body>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript" src="<%=rootPath %>client/js/swfobject.js"></script>
<script type="text/javascript" src="<%=rootPath %>client/js/jquery.uploadify.min.js"></script>
<script type="text/javascript"> 
//添加成员
function addProjectPerson(projectid,userIds,loginUserid){  
    var result = $.ajax({
	        type: 'POST',
	        url: '<%=rootPath %>project/select_allusertask.action', 
	        data:{
	        	'isPerson':true,
	            'projectId':projectid,
	            'pageNum':1,
	            'userIds':userIds
	        },
	        async: false,
	        cache: false
	    }).responseText; 
    
    ProjectPersonDialog= $.dialog({
        id: 'selectUser',
        title:'添加版块成员',
        max:false,
        min:false,
        lock:true, 
        init : function(){  
            $('#sWord').searchbox({}); 
            initDialogShow();
        },
        resize: false,
        width:'500px',
        height:'150px',
        content:result,
        cancelVal:'关闭',
        okVal:'确定',
        cancel:true,
        ok:function(){    
            var _personIds=$('#userIds').val();//以逗号分割的所有用户id   
            var _loginUserId=loginUserid;
            if(_personIds==null ||_personIds=="" ||_personIds==_loginUserId){}
            else{
                $.ajax({
                    type:'post',
                    url:'<%=rootPath%>project/addprojectperson.action',
                    data:{
                        'personIds': _personIds,
                        'projectId': projectid
                    },
                    success: function(msg){ 
                        if(msg!="0"){
                            $.dialog.tips('添加成员成功',1,'32X32/succ.png',function(){  
                                refrechProjectList();
                            });  
                        }else{
                            $.dialog.tips('添加成员失败',0.5,'32X32/fail.png');   
                        }
                    } 
                }); 
            }
        },
        close:function(){
            $('#userIds').val('');
        } 
    }); 
}   

/* 修改版块 */
function toupdateproject(projectid){
    var result = $.ajax({
        url: '<%=rootPath%>project/projectinfotoupdates.action',
        type:"post",
        data:{
            'projectId':projectid
        },
        dataType: "json", 
        async: false 
    }).responseText; 
    
    $.dialog({
        title:'修改版块',
        max:false,
        min:false,
        width:'300px',
        height:'150px',
        lock:true,
        resize: false, 
        init:function(){
        	   var allChannelIds = "<s:property value='allChannelIds'/>";

        	   var arrChannelIds = allChannelIds.split(',');
        	   $('#projectStatus').combotree({
        		    url: '<%=rootPath%>site/findprojecttree.action',
        		    required: 'true'
        		}); 
        	   $('#prnameinput').validatebox({required: true});  
        	   $('#prdescinput').validatebox({required: true});  
        	   $('#keyprint').validatebox({required:true});
        }, 
        content:result,
        cancelVal:'关闭',
        okVal:'确定',
        cancel:function(){},
        ok:function(){
            var isSubmit=$('#addprojectfrm').form('validate'); 
            $('#addprojectfrm').form('submit',{
                url:'<%=rootPath%>project/updateproject.action',
                success:function(callback){  
                    if(callback!=""){ 
                        $.dialog.tips('修改版块成功',1,'32X32/succ.png',function(){ 
                            window.location.reload(true);
                        });                     
                    }else{
                        $.dialog.tips('修改版块失败',0.5,'32X32/fail.png');   
                    }
                },
                error:function(){
                    $.dialog.tips('修改版块失败',0.5,'32X32/fail.png');
                }
            });  
            return isSubmit;
        } 
    });
}   
    
//初始化dialog弹框选择人
function initDialogShow(){
    $('#dialogPageForm').find('searchWord').val('');
    $('#dialogPageForm').find('searchType').val('');
    $('#dialogPageForm').find('orderField').val('');
    $('#dialogPageForm').find('pageNum').val(1);  
}

var OffPersonDialog;//任务负责人 //圈子负责人 
var ProjectPersonDialog;//圈子成员/圈子关注者///任务成员
    
function projectList(_href){  
    var result = $.ajax({
        url: _href,
        type:"post",
        dataType: "json", 
        async: false 
    }).responseText;  
    $('#project_listDiv').html(result);
}


function tabs(lidom){ 
    $('#projectH').find('a').each(function(){
        if(this.id ==lidom.id){ 
            this.className="over"; 
            var _href=  $(this).attr('link'); 
            projectList(_href+"?pageNum=1&protype=1"); 
        }else{
            this.className="";
        }
    });  
}
    
    
//删除版块
function deleteproject(_projectId){
    $.dialog.confirm('您确定删除这个版块吗？',function(){
        $.ajax({
            url: '<%=rootPath%>project/deleteproject.action',
            data:{'projectId':_projectId},
            type:"post",
            dataType: "json", 
            async: false 
        }); 
        showoneproject(); 
    }); 
}

function showoneproject(){ 
    refrechProjectList();//刷新列表
//  allprojectLeft();//刷新左边
}


function allprojectLeft(){
    var result = $.ajax({
        url: '<%=rootPath%>project/allforumlist.action?protype=1',
        data:{'taskorproject':'project'},
        type:"post",
        dataType: "json", 
        async: false 
    }).responseText;
    $('#leftMenu').html(result);
} 

 /** 首先进入，查询自己的负责的圈子  */
$(function(){
    projectList('<%=rootPath%>project/allforumlist.action?protype=1');
}); 
 
 
function refrechProjectList(){
    var project_state=0;
    $('#projectH').find('a').each(function(){
        if(this.className=="over"){  
            var _href=  $(this).attr('link');
            _href=_href+"?isClosed="+project_state+"&protype="+1; 
            var pageForm=$('#pageForm');
            if(pageForm!=null){
                var pageString=$('#pageForm').serialize(); 
                _href=_href+"&"+pageString;
            } 
            projectList(_href);//刷新列表 
        }
    }); 
}

/* 新建版块 */
function toaddproject(){
    var result = $.ajax({
        url: '<%=rootPath%>project/toaddforum.action',
        type:"post",
        dataType: "json", 
        async: false 
    }).responseText;
    
    $.dialog({
        title:'新建版块',
        max:false,
        min:false,
        width:'300px',
        height:'150px',
        lock:false,
        resize: false, 
        init:function(){
             $('#prnameinput').validatebox({required: true});  
             $('#prdescinput').validatebox({required: true});  
             $('#keyprint').validatebox({required:true});
        }, 
        content:result,
        cancelVal:'关闭',
        okVal:'确定',
        cancel:function(){},
        ok:function(){
            var isSubmit=$('#addprojectfrm').form('validate');   
            $('#addprojectfrm').form('submit',{
                url:'<%=rootPath%>project/addproject.action',
                success:function(callback){  
                	   if(callback!=""){ 
                		    $.dialog.tips('新建版块成功',1,'32X32/succ.png',function(){ 
                		    	  showoneproject();
                		    });                     
                       }else{
                    	    $.dialog.tips('新建版块失败',0.5,'32X32/fail.png');   
                       }
                },
                error:function(){
                    $.dialog.tips('新建版块失败',0.5,'32X32/fail.png');
                }
            });  
            return isSubmit;
        } 
    });
}
</script> 
</html>

