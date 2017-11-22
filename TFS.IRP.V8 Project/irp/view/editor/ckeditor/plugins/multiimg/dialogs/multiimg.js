(function() {  
	var zh_w=0;
    CKEDITOR.dialog.add("multiimg",  
        function(a) { 
        	return {  
            	title: "批量上传图片",  
            	minWidth: "980px;",  
            	minHeight:"400px",  
            	contents: [{  
                    id: "tab1",  
                    label: "",  
                    title: "",  
                    expand: true,  
                    width: "980px",  
                    height: "300px",  
                    padding: 0,  
                    elements: [{  
                    	type: "html",  
                   		style: "width:980px;;height:400px",  
                    	html: '<iframe id="uploadFrame" src="loadMultiImg.action" frameborder="0"></iframe>'  
                    }]  
                }],  
            	onOk: function() {
                    var ins = a;  
                    var num = window.imgs.length;
                    var imgHtml = "";
                    //解决在次上传图片图片重复的问题
                    if(zh_w == 0){
                		for(var i=0;i<num;i++){  
                    		imgHtml += "<p><img src=\""
                    		 		+ window.imgs[i] +
                    		 		"\""+
                    		 		" style=\""+
                    		 		"width:"+
                    		 		window.sty[i]+";"+
                    		 		"\""+
                    		 		" /></p> ";        
                    	}
                    	ins.insertHtml(imgHtml);   
                    	zh_w=num;
                	}else{
                		for(var i=num-zh_w;i<num;i++){  
                    		imgHtml += "<p><img src=\""
                    		 		+ window.imgs[i] +
                    		 		"\""+
                    		 		" style=\""+
                    		 		"width:"+
                    		 		window.sty[i]+";"+
                    		 		"\""+
                    		 		" /></p> ";        
                    	}
                    	ins.insertHtml(imgHtml); 
                	}
            	}
        	}
        })  
})();  
