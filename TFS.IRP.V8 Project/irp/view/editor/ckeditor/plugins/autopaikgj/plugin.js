var ifblank = true;
(function(){
//Section 1 : 按下自定义按钮时执行的代码
var a= {
	exec:function(editor){
		FormatText(editor);
	}
},
//Section 2 : 创建自定义按钮、绑定方法
b='autopaikgj';
CKEDITOR.plugins.add(b,{
	init:function(editor){
		editor.addCommand(b,a);
		editor.ui.addButton('autopaikgj',{
			//icon: this.path + 'autopai.gif',
			//lang:['zh-cn','自动排版'],
			//label:'自动排版',
			label:'自动排版',
			command:'autopaikgj',
			icon : this.path + "autopai.gif",
			toolbar : "autoformat"
		});
	}
});
})();
function SetEditorContents(bodyname,html)
{
	// Get the editor instance that we want to interact with.
	var oEditor = bodyname;
	//var value = document.getElementById( 'htmlArea' ).value;
    // Set editor contents (replace current contents).
	// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#setData
	oEditor.setData( html );
}
function FormatText(bodyname) {
   var myeditor = bodyname;
   //alert(myeditor);
  
   if (myeditor.mode=='wysiwyg'){
        var temps = new Array();
        var srcnumber;//记录截取位置，解决格式化会自动加载路径的问题
        isPart = false; //暂时无法实现局部格式化
        if (!isPart) {
            var imgs = myeditor.document.$.images;     
            if (imgs != null && imgs.length > 0) {
                for (j = 0; j < imgs.length; j++) {      	 
                    var t = document.createElement("IMG");
                    t.alt = imgs[j].alt;
                    var isrc=imgs[j].src;
                    var srcnumber=isrc.indexOf("cms");
                    t.src=imgs[j].src;
                    t.width = imgs[j].width;
                    t.height = imgs[j].height;
                    t.align = imgs[j].align;
                    temps[temps.length] = t;
                }
                var formatImgCount = 0;
                for (j = 0; j < imgs.length;) {
                    imgs[j].outerHTML = "#FormatImgID_" + formatImgCount + "#";
                    formatImgCount++;
                }
            }
			var strongarray	= new Array();
			var strongcount = 0;
			for(var i=0;i<myeditor.document.$.body.getElementsByTagName('b').length;i++){
				strongarray[strongcount]	= myeditor.document.$.body.getElementsByTagName('b')[i].innerText.trim();
				myeditor.document.$.body.getElementsByTagName('b')[i].innerHTML	= "#FormatStrongID_" + strongcount + "#";
				strongcount++;
			}

			for(var i=0;i<myeditor.document.$.body.getElementsByTagName('strong').length;i++){
				strongarray[strongcount]	= myeditor.document.$.body.getElementsByTagName('strong')[i].innerText.trim();
				myeditor.document.$.body.getElementsByTagName('strong')[i].innerHTML	= "#FormatStrongID_" + strongcount + "#";
				strongcount++;
			}
            var html = processFormatText(myeditor.document.$.body.innerText); 
            if (temps != null && temps.length > 0) {
                for (j = 0; j < temps.length; j++) {                	
                    var imghtml = "<p align=\"center\"><img src=\"" + temps[j].src.substring(srcnumber-1) + "\" alt=\"" + temps[j].alt + "\" width=\"" + temps[j].width + "\" height=\"" + temps[j].height + "\" align=\"" + temps[j].align + "\" border=\"0\"></p>";
                    html = html.replace("#FormatImgID_" + j + "#", imghtml);
                }
            }
			for(var i=0;i<strongcount;i++){
				html = html.replace("#FormatStrongID_" + i + "#", "<strong>"+strongarray[i]+"</strong>");
			}
			while(html.indexOf("</p></p>")!=-1)	html=html.replace("</p></p>","</p>");
			while(html.indexOf('<p ><p align="center">')!=-1)	html=html.replace('<p ><p align="center">','<p align="center">');
			while(html.indexOf("<p ></p>")!=-1)	html=html.replace("<p ></p>","");
			SetEditorContents(bodyname, html);
			
        } else {

        }
   }else{
		alert('必须在设计模式下操作！');
   }
}
function processFormatText(textContext) {
    var text = DBC2SBC(textContext);
    var prefix = "　　";
    var tmps = text.split("\n");
    var html = "";
    for (i = 0; i < tmps.length; i++) {
      var tmp = tmps[i].trim();
      if (tmp.length > 0) {
		  if (ifblank)
		  {
			html += "<p >　　" + tmp + "</p>";
		   }
			else
			{
			html += "<p >" + tmp + "</p>";
			}
		  
	  }else{
			html += "<p >" + tmp + "</p>";
	  }
    }
  if(ifblank)
   ifblank = false;
  else
    ifblank = true;
  return html;
}
String.prototype.trim = function()
{
  return this.replace(/(^[\s　]*)|([\s　]*$)/g, "");
};

String.prototype.leftTrim = function()
{
  return this.replace(/(^\s*)/g, "");
};

String.prototype.rightTrim = function()
{
  return this.replace(/(\s*$)/g, "");
};
function DBC2SBC(str) {
  var result = '';
  for (var i = 0; i < str.length; i++) {
    code = str.charCodeAt(i);
    // “65281”是“！”，“65373”是“｝”，“65292”是“，”。不转换"，"

    if (code >= 65281 && code < 65373 && code != 65292 && code != 65306){
    //  “65248”是转换码距
      result += String.fromCharCode(str.charCodeAt(i) - 65248);
    } else {
      result += str.charAt(i);
    }
  }
  return result;
}

