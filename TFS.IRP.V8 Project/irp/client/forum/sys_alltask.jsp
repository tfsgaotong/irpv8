<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
<head>
    <title>论坛模块</title>
    <link rel="shortcut icon" href="<%=rootPath %>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath %>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>client/css/icon.css" />
	<link href="<%=rootPath %>client/css/oacss.css" rel="stylesheet" type="text/css" />
    <link href="<%=rootPath %>client/css/asx.css" rel="stylesheet" type="text/css" /> 
	<link href="<%=rootPath %>client/css/workhome-3482.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>client/css/lib-3322.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=rootPath %>view/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="<%=rootPath %>view/css/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>client/js/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>client/js/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>client/js/easyui-lang-zh_CN.js"></script> 
	<style type="text/css">
	body{behavior:url(hover.htc);}
	.main_ltali{ margin-left:10px;}
	.fn{ color: blue;}
	</style>
	<jsp:include page="../include/client_skin.jsp" /> 
</head>

<body> 
    <div class="bg01">
	    <jsp:include page="../../view/include/client_head.jsp" />
	    <section class="mainBox">
	        <nav class="privateNav"></nav>
	    </section>
        
        <div class="main-gr">
        
	        <!--左侧内容-->
		    <div class="left" id="leftMenu"></div>
	        <!--左侧内容结束-->
	         
	        <!--右侧内容-->
		    <div class="right"> 
			    <div>
			        <h1 id="ProjectTaskH" class="zl3" style="font-size:18px; width:400px; font-weight:normal; line-height:50px; border-bottom:1px  solid #efefef; text-align:left; font-family:'微软雅黑'; margin:0 0 0px 5px; color:#121212;">
		                <a href="javascript:void(0);" onclick="tabs(this)" class="over" _link="<%=rootPath%>project/alltheme.action">主题</a>
		                <a href="javascript:void(0);" onclick="toaddDocument()">新建</a>
	                </h1>
	            </div> 
			
	            <div class="clearfix" style="width:705px; margin-left:5px;">
	                <input name="projectId" type="hidden" value="<s:property value='projectId'/>"/>
	                <div class="left_wbi" style="width:700px;" id="TaskShowDiv"></div>
	            </div>  
	        </div> 
	    </div>
	    
	    <!--尾部信息-->
	    <jsp:include page="../include/client_foot.jsp" />
    </div>
</body>

<script type="text/javascript">
// 分页
function page(_nPageNum){
    $('#pageForm').find('input[name="pageNum"]').val(_nPageNum);   
    $('#ProjectTaskH').find('a').each(function(){   
        if(this.className=="over"){  
            var sData =$(this).attr('_link');  
            var pageForm=$('#pageForm'); 
            if(pageForm!=null){
                var pageString=$('#pageForm').serialize(); 
                sData=sData+"?"+pageString;
            }  
            showTask(sData);
        } 
    });  
}
    
// 得到左边菜单
function allprojectLeft(){ 
    var result = $.ajax({
        url: '<%=rootPath%>project/allthemeleft.action?protype=1&projectId='+<s:property value="projectId"/>,
        type:"post",
        data:{'taskorproject':'task'},
        dataType: "json", 
        async: false 
    }).responseText; 
    $('#leftMenu').html(result);
}
    
    
function tabs(adom){  
    $('#ProjectTaskH').find('a').each(function(){  
        if(adom==null ||adom=='undifined'){ 
            if(this.className=="over"){  
                adom=this; 
            } 
        }
        this.className=""; 
    }); 
    if(adom!=null){  
        adom.className="over";  
        var sData =$(adom).attr('_link');    
        showTask(sData+"?pageNum=1&projectId=<s:property value='projectId'/>");
    }  
}
    
// 显示任务列表
function showTask(sData){
    var result = $.ajax({
        url:sData,
        type:"post",
        dataType: "json", 
        async: false
    }).responseText;   
    $('#TaskShowDiv').html(result);  
}
    
    /** 首先进入，   */
    $(function(){
        allprojectLeft();//得到左边    
        $('#tabMenu').find('a').each(function(){   
            if(this.className=="c"){  
                this.className='';
                $(this).removeClass("c"); 
            }else{ 
                $(this).addClass("c"); 
            }
        }); 
        showTask("<%=rootPath%>project/alltheme.action?pageNum=1&projectId="+<s:property value="projectId"/>);    
    });
    
    /* 打开新建话题页面 */
    function toaddDocument(){
        window.open('<%=rootPath %>project/client_toadd_theme.action?projectId='+<s:property value='projectId' />);  
    }
        
    function toupdateDocument(sharetaskId){
        window.open('<%=rootPath %>project/client_update_theme.action?shareTaskId='+sharetaskId);  
    }
        
    //删除任务
    function deleteShareTaskOff(_sharetaskid){ 
        var projectId='<s:property value="projectId" />';
        $.messager.confirm('提示信息','是否要删除这条数据？',function(r){
            if(r){
                $.ajax({
                    type:'post',
                    url:'<%=rootPath%>project/deletesharetask.action',
                    data:{
                        'irpProjectShareTask.sharetaskid':_sharetaskid 
                    },
                    success: function(msg){
                        if(msg>0){
                            $.dialog.tips('删除成功',1,'32X32/succ.png',function(){ 
                                window.location.replace("project/foruminfo.action?projectId="+projectId);
                            });  
                        } else{
                            $.dialog.tips('删除失败',0.5,'32X32/fail.png'); 
                        }
                    }
                });
            }
        }); 
    }
</script>
</html>

