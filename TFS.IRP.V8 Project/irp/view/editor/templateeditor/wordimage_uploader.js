
function checkJRE() {
    try {
        if (document.getElementById('wordImageApplet').isActive()) {
            return 1;
        }
    } catch (error) {
//        if (confirm('您的系统未安装图片自动上传控件所需的JAVA运行环境,是否要下载安装JDK？')) {
//            window.location.href = 'http://www.oracle.com/technetwork/java/javase/downloads/index.html';
//        }
        return 0;
    }
}


function WordImageUploader(s_url, app_name)
{
    var _this = this; //把this保存下来，以后用_this代替this，这样就不会被this弄晕了
    var sUrl = '';
    var appName = '';

    var init = function()
    {
// 构造函数代码 
        sUrl = s_url;
        appName = app_name;
        if (appName == '/') {
            appName = '';
        }

    };

    var printRequiredHtml11 = function() {
        var xx = '<div id=\"word_image_container_temp\" style=\"display:none;\"></div>';
        var yy1 = '<div id=\"wordImageAppletWrapper\" style=\"display:none;height:22px;background-color:#f2f1f1;border-top:1px solid gray;position:fixed; bottom:0;left:0; width:100%; overflow: hidden;z-index:1000;\" > ';
        var yy2 = '<applet id=\"wordImageApplet\" name=\"wordImageApplet\" code=\"com.tfs.irp.FileUpload\" codebase=\"' + appName + '/editor/applet\" archive=\"ckwordup.jar,commons-logging-1.1.3.jar,httpclient-4.2.3.jar,httpcore-4.2.2.jar,httpmime-4.2.3.jar\" style=\"display:none;\"></applet>';
        var yy3 = '</div>';

        document.write(xx);
        document.write(yy1);
        document.write(yy2);
        document.write(yy3);
    }
    init();
    printRequiredHtml11();
    
    /*var yy4 = '<span style="color:blue;font-size:12px;">&nbsp;&nbsp;未安装JAVA环境或JAVA运行不正常，“图片自动上传插件”不能运行，<a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank">点此下载JDK</a>。</span>';*/
/*    var cjj = checkJRE();*/
/*    if (cjj != 1) {
    	$('#wordImageAppletWrapper').html('');*/
        /*$('#wordImageAppletWrapper').html(yy4);*/
/*        $('#wordImageAppletWrapper').show();
    }*/
    
    _this.uploadWordImagesFromCKEditor = function(editorInstance, event) {
        var cj = checkJRE();
        if (cj != 1) {
            return 0;
        }
        var eData = event.data;
        if(eData['type']!='html'){
        	return 0;
        }
        var ed = editorInstance;
        var txt = eData['dataValue'];
        var txt0 = txt;
        $('#word_image_container_temp').html(txt);
        var i = 0;
        $('#word_image_container_temp img').each(function() {
            var src = $(this).attr('src');
            if (src.indexOf("file:///") != -1) {
                var srct = src.replace('file:///', '');
                var serverPath = _this.uploadLocalFile(srct);
                if (serverPath != 'error') {
                	$(this).attr('src', serverPath);
                }
            }
        });
        if (txt0 != $('#word_image_container_temp').html()) {
            ed.insertHtml($('#word_image_container_temp').html());
        }
    }
    _this.uploadLocalFile = function(localUrl) {
        var appletObj = document.getElementById("wordImageApplet");
        var result = appletObj.jsUploadFile(sUrl, localUrl);
        return result;
    }
}