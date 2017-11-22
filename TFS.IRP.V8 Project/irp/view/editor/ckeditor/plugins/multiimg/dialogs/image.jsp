<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String rootPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<script type="text/javascript"	src="<%=rootPath%>admin/js/jquery-2.1.1.min.js"></script>
 	<script type="text/javascript"	src="<%=rootPath%>admin/js/jquery.fileupload.js"></script>
	<script type="text/javascript"	src="<%=rootPath%>admin/js/jquery.ui.widget.js"></script>
    <style>
    body,div{
        margin:0;
        padding:0;
    }
    .panel-body{
        padding:5px;
    }

    .upFileBtn {
        cursor: pointer;
        width: 40px;
        height: 40px;
        background: url("../btn.jpg") no-repeat;
        background-size: 100%;
    }
    #fileupload_input_color{
        display: none;
    }
    .form-group{
        padding:5px;
    }
    .control-label{
        text-align:right;
        padding: 10px;
        font-weight: bold;
        line-height:2;
    }
    .col-label{
        width:15%;
        float:left;
    }
    .col-div{
        width:80%;
        padding:5px;
        overflow:hidden;
    }
    .thumbnail{
        display: inline-block;
        position: relative;
        width: 20%;
        margin:5px;
    }

    </style>
</head>
<body>
    <div class="panel-body">
        <form enctype="multipart/form-data" id="form-color" class="form-horizontal" action="javascript:AddGallery();" method="post" action="" autocomplete="off" >
            <div class="form-group">
                <label class="col-label control-label">上传图片</label>
                <div class="col-div">
                    <input type="file" name="files" multiple id="fileupload_input_color" />
                    <input class="upFileBtn" type="button" onclick="document.getElementById('fileupload_input_color').click()" />
                                <br />
                </div>
            </div>
            <div class="form-group">
                <label class="col-label control-label">图片列表</label>
                <div class="col-sm-6">
                    <table>
                        <tr id="Tr3">
                            <td class="text-left">
                                <span id="button-color-field"></span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
         </form>
    </div>

    <script>
        $("#fileupload_input_color").fileupload({
            url: "image_upload.php", //文件上传地址，当然也可以直接写在input的data-url属性内  
            async: false,
            add: function (e, data) {
                data.submit();
            },
            done: function (e, data) {
                data = JSON.parse(data.result);
                if (data.result == "200") {
                    parent.top.imgs.push(data.imgurl);
                    $("#button-color-field").append("<span class='thumbnail'><img width='100%' alt='' src='../../../../" + data.imgurl + "'><input name='product_color_img_ipnut' type='hidden' value='" + data.imgurl + "' /></span>");
                } else {
                    alert(data.msg);
                }
            }
        });
    </script>
</body>
</html>