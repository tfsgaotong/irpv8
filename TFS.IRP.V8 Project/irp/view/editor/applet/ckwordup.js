function CK_WordImageUploader(_sUpUri,_sAppName){
	var _this = this;
	var sUrl='';
	var appName='';
	
	var init = function() {
        sUrl = _sUpUri;
        appName = _sAppName;
        if (appName == '/') {
            appName = '';
        }
    };
	var createApplet = function(_code,_codebase,_archive){
		document.writeln('<div id=\"word_content_temp\" style=\"display:none;\"></div>');
		document.writeln('<div id=\"word_applet_wrapper\" style=\"display:none;height:22px;background-color:#f2f1f1;border-top:1px solid gray;position:fixed;bottom:0;left:0;width:100%;overflow:hidden;z-index:1000;\"></div>');
		var _info = navigator.userAgent; 
		var _ie = (_info.indexOf("MSIE") > 0 && _info.indexOf("Win") > 0 && _info.indexOf("Windows 3.1") < 0);
		if(_ie){
			document.writeln('<OBJECT style="position:absolute;top:-10000px;left:-10000px;" classid="clsid:CAFEEFAC-0016-0000-0045-ABCDEFFEDCBA" width="0" height="0" codebase="http://java.sun.com/update/1.6.0/jinstall-6u45-windows-i586.cab#Version=6,0,45,6">');
			document.writeln('<PARAM name="code" value="'+_code+'" />');
			document.writeln('<PARAM name="codebase" value="'+_codebase+'" />');
			document.writeln('<PARAM name="archive" value="'+_archive+'" />');
			document.writeln('<PARAM name="type" value="application/x-java-applet;version=1.6" />');
			document.writeln('<PARAM name="scriptable" value="true" />');
			document.writeln('</OBJECT>');
		}else{
			document.writeln('<EMBED style="position:absolute;top:-10000px;left:-10000px;" type="application/x-java-applet;version=1.6" width="0" height="0" code="'+_code+'" codebase="'+_codebase+'" archive="'+_archive+'" pluginspage="'+_codebase+'/jre-6u45-windows-i586.exe">');
			document.writeln('<NOEMBED>');
			document.writeln('</NOEMBED>');
			document.writeln('</EMBED>');
		}
	};
	var getApplet = function(){  
		if (document.applets.length > 0){  
			return document.applets[0];
		}else if(document.embeds.length > 0){  
			return document.embeds[0];
		}else{  
			return undefined;
		}
	};
	
	var checkJRE = function() {
	    try {
	    	var cj = getApplet();
	        if (cj!= undefined && cj.isActive()) {
	            return 1;
	        }
	    } catch (error) {
	        return 0;
	    }
	}
	
	init();
	var codebase = appName+"editor/applet";
	createApplet("com.tfs.irp.FileUpload.class", codebase, "ckwordup.jar");
	
/*	setTimeout(function(){
		if(checkJRE()!=1){
			var wrapper = $('#word_applet_wrapper');
			wrapper.html('<span style="color:blue;font-size:12px;">&nbsp;&nbsp;未安装JAVA环境或JAVA运行不正常，“图片自动上传插件”不能运行，<a href="'+codebase+'/jre-6u45-windows-i586.exe" target="_blank">点此下载JRE</a>。</span>');
			wrapper.show();
		}
	}, 500);*/
	
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
        $('#word_content_temp').html(txt);
        $('#word_content_temp img').each(function() {
            var src = $(this).attr('src');
            if (src.indexOf("file:///") != -1) {
                var srct = src.replace('file:///', '');
                var serverPath = _this.uploadLocalFile(srct);
                if (serverPath != 'error') {
                	$(this).attr('src', serverPath);
                }
            }
        });
        if (txt != $('#word_content_temp').html()){
        	event.data['dataValue']=$('#word_content_temp').html();
        }
    };
	_this.uploadLocalFile = function(localUrl){
		var cj = getApplet();
        if (cj == undefined) {
            return 0;
        }
        var result = cj.jsUploadFile(sUrl, localUrl);
        return result;
    };
}