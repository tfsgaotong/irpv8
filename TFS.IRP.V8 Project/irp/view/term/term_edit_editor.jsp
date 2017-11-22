<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 编辑词条页面 -->
<html>
<head>
    <title>编辑词条</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%=rootPath%>favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="<%=rootPath%>favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/css/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>view/ztree/css/zTreeStyle/zTreeStyle.css" />
    <script type="text/javascript" src="<%=rootPath%>view/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/lhgdialog.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/editor/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/swfobject.js"></script>
    <script type="text/javascript" src="<%=rootPath%>view/js/jquery.uploadify.min.js"></script>
    <jsp:include page="../../view/include/client_skin.jsp" />
    <style type="text/css">
    .wtgy {
        height: 28px;
        line-height:28px;
        border: 1px solid rgb(209, 209, 209);
        color: rgb(102, 102, 102);
        padding: 0px 5px;
        width:300px;
        margin:20px 0 20px 5px;
    }
    
    body{
        behavior:url(hover.htc);
    }
    
    .searchSec .radios span {
        margin-top: 0px;
    }   
    </style> 
</head>
<script type="text/javascript">
$(function(){
	var termnumsize = '<s:property value="ternamenum" />';
	var termcsize =  '<s:property value="updatevalsize" />';
	var twname = '<s:property value="termwordname" escapeHtml="false" />';
	var twcontent = '<s:property value="termwordcontent" escapeHtml="false" />';
	if(twname!=''){
		$('#termwordsname').val(twname);
		$('#termwordsname').attr("disabled","disabled");
	}
	$('#termexplaincontent').val(twcontent);
	
	// 获得所有附件
	toUpdate(<s:property value="edittermid" />);
	
	//提交页面词条释义
	$('#termwordssubmit').click(function(){
		//标题
		var tname = $('#termwordsname').val();
		//html内容
		var htmlcontent = editor.document.getBody().getHtml();
		//文本内容
		var textcontent = editor.document.getBody().getText();
		//修改原因
		var upcausevalue = $('#upcause').val();
		//分类
		var editclassifyid = $('#cateIds').val();
		
		var sdata = {
			termname:tname,
			thtmlcontent:htmlcontent,
			upcausevalue:upcausevalue,
			editclassifyid:editclassifyid,
			attachedFile: JSON.stringify(_allattacheds),
			termcontent: textcontent
		};
		
		if($.trim(tname)!=''){
			if(tname.length>termnumsize){
				$.dialog.tips('词条名称字数不能超过'+termnumsize+'字',1,'alert.gif');
				return false;	
			}else{
				if($.trim(upcausevalue)==''){
					$.dialog.tips('请填写词条修改原因',1,'alert.gif');
					return false;	
				}
				if(upcausevalue.length>termcsize){
					$.dialog.tips('修改原因字数不能超过'+termcsize+'字',1,'alert.gif');
					return false;	
				}
				
				$.ajax({
					type:'post',
					url:'<%=rootPath%>term/addtermcontent.action',
					data:sdata,
					async:false,
					caceh:false,
					success:function(msg){
						
						if(msg=='1'){
							$('#termwordssubmit').unbind("click");
							
							$.dialog.tips('您构建的词条已提交成功,需等待管理员审核!',3,'success.gif',function(){
								window.close();
							});
						}else{
							
							$.dialog.alert('提交错误');
						}
						
					}
				});
			}
		}else{
			$.dialog.tips('请填写词条名字',1,'alert.gif');
			return false;
		}
	});
	//关闭本页面
	$('#closethispage').click(function(){
	    $.dialog.confirm('您确定要关闭当前页面吗？',function(){
	        window.close();
	    });
	});
});

/**
 * 添加这个空函数，编辑器就可以执行加粗等一系列操作了
 */
function clearImages(){}

/**
 * 
 *选择分类
 */
function choiceCate(){
	var categoryName = $('#cateIds').val();
	var result = $.ajax({
		url:"<%=rootPath%>category/getLeftCategoryByTerm.action?categoryName="+categoryName,
		cache:false,
		async:false
	}).responseText;
	$.dialog({
  		content:result,
  		title:'选择分类',
  		min:false,
  		max:false,
	    lock:true,
	    ok:function(){},
	    okVal:'确认',
	    padding: 0
  	});
}

/**
*添加基本模版
*/
function addTemplate(){
	
var result = $.ajax({
					url:"<%=rootPath%>set/quotedittemplate.action",
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
	    					editor.document.getBody().setHtml(content);
	    				}
	    			}
	    		});
	    	}
	    },
	    okVal:'确认',
	    padding: 0
  	});
}


var _allattacheds=null;
/**
 * 获得所有附件
 */
function toUpdate(termId){ 
    //发送ajax请求获得所有附件
    $.ajax({ 
        type: "GET", 
        url: "<%=rootPath %>term/getAllAttachedForTerm.action?termid="+termId,
        dataType:"json",
        success: function(json){
            _allattacheds=json; 
        }
    });
}
 
/**
 * 修改附件
 */
function toupdateAttacthed(docid){ 
    var str=$.ajax({
        type:'GET',
        url:'<%=rootPath %>site/client_to_update_attached.action?docid='+docid, 
        async: false,
        cache: false
    }).responseText; 
    $.dialog({
        title:'附件管理',
        max:false,
        min:false,
        lock:true,
        resize: false, 
        content:str,
        cancelVal:'确定', 
        cancel:function(){
            if(_allattacheds){
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
            }  
        }
    });  
}
</script>

<body style="background: url()">
    <s:include value="../../view/include/client_head.jsp"></s:include>
    <section class="mainBox">
        <nav class="privateNav"></nav>
    </section>
    <section class="mainBox">
        <section class="newForm">
            <article class="location">
                <strong>创建词条（<span>*</span>必须填写项）</strong>
            </article>
            <table>
                <tr>
                    <td>
                        <input type="text" placeholder="请输入词条名称..." id="termwordsname" class="artTitle easyui-validatebox" required="true" validType="length[2,300]" />
                    </td>
                    <td width="20"><span class="must">*</span></td>
                    <td>
                        <input id="cate"  type="text" class="wtgy" placeholder="点击选择分类" readonly="readonly" value="<s:property value="irpCategoryById(termwordclassifyid).cname" />" style="width: 190px;" disabled="disabled">
                    </td>
                    <td width="20"><span class="must">*</span></td>
                    <input id="cateIds" type="hidden" value="<s:property value="termwordclassifyid" />"/>
                </tr>
            </table>
            <div>
                <textarea id="termexplaincontent" name="editor"></textarea>
                <script>
                    var editor = CKEDITOR.replace('editor',{
                        filebrowserUploadUrl: '<%=rootPath%>file/ck_upload.action',
                        uiColor: '#EAF2FF',
                        height: 200
                    });
                </script>
            </div>
            <div>
                <lable class="linkbh14">修改原因:</lable>
                <input id="upcause"  type="text" class="wtgy" style="width: 784px;" placeholder="请说明修改原因...">
            </div>
            <div style="text-align: center; margin-top: 20px;">
                <input type="button" class="btn" id="termwordssubmit" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存" />
                <input type="button" class="btn" id="closethispage" value="关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;闭" />
            </div>
        </section>
        <section class="layoutRight formInfo" style="height: 458px;">
            <section>  
                <div class="title" style="margin-left:10px">附件管理</div>
                <input type="button" class="artFile" value="修改附件" style="width:100px; margin-left:8px;" onclick="toupdateAttacthed(<s:property value="edittermid" />)"/>
            </section>
        </section>
    </section>
    <s:include value="../../view/include/client_foot.jsp"></s:include>
</body>
</html>