CKEDITOR.dialog.add('flvPlayer',　function(a){
    var b = a.config;
    var escape = function(value){
    	return　value;
    };
    return {
    	title:　'插入Flv视频',
    	resizable:　CKEDITOR.DIALOG_RESIZE_BOTH,
    	minWidth: 350,
    	minHeight: 300,
    	contents:　[{
	        id: 'Upload',
	        hidden: true,
	        filebrowser: 'uploadButton',
	        label: '上传',
	        elements: [{
	            type: 'file',
	            id: 'upload',
	            label: '上传',
	            size: 38
	        },
	        {
	            type: 'fileButton',
	            id: 'uploadButton',
	            label: '发送到服务器',
	            filebrowser: 'info:src',
	            'for': ['Upload', 'upload']// 'page_id', 'element_id'
	        }]
    	},{
    		id: 'info',
    		label: '常规',
    		accessKey: 'P',
    		elements:[{
    			type: 'hbox',
	            widths : [ '80%', '20%' ],
                children:[{
                	id: 'src',
                    type: 'text',
                    label: '源文件'
                },{
                	type: 'button',
                    id: 'browse',
                    filebrowser: 'info:src',
                    hidden: true,
                    align: 'center',
                    label: '浏览服务器'
                }]
            },{
            	type: 'hbox',
	            widths : [ '35%', '35%', '30%' ],
                children:[{
                	type:　'text',
                	label:　'视频宽度',
                	id:　'mywidth',
                	'default':　'470px',
                	style:　'width:50px'
        		},{
        			type:　'text',
        			label:　'视频高度',
        			id:　'myheight',
        			'default':　'320px',
        			style:　'width:50px'
                },{
                	type:　'select',
                	label:　'自动播放',
                	id:　'myloop',
                	required:　true,
                	'default':　'false',
                	items:　[['是',　'true'],　['否',　'false']]
                }]
            },{
            	type:　'textarea',
            	style:　'width:300px;height:220px',
            	label:　'预览',
            	id:　'code'
            }]
    	}],
    	onOk:　function(){
    		mywidth　=　this.getValueOf('info',　'mywidth');
    		myheight　=　this.getValueOf('info',　'myheight');
    		myloop　=　this.getValueOf('info',　'myloop');
    		mysrc　=　this.getValueOf('info',　'src');
    		html　=　''　+　escape(mysrc)　+　'';
    		// editor.insertHtml("<pre class=\"brush:" + lang + ";\">" + html +
			// "</pre>");
    		var editHtml = "<embed width=\""+mywidth+"\" " +
    				"height=\""+myheight+"\" " +
    				"flashvars=\"MMControl=false&amp;MMout=false\" " +
    				"wmode=\"opaque\" " +
    				"allowscriptaccess=\"always\" " +
    				"allownetworking=\"internal\" " +
    				"allowfullscreen=\"true\" " +
    				"quality=\"high\" " +
    				"bgcolor=\"#ffffff\" " +
    				"name=\"acgobject\" " +
    				"id=\"acgobject\" " +
    				"style=\"undefined\" " +
    				"src=\""+b.flv_path+"jwplayer.swf?file="+html+"&amp;autostart="+myloop+"\" " +
    				"type=\"application/x-shockwave-flash\"></embed>";
    		
    		a.insertHtml(editHtml);
    	},
    	onLoad:　function(){}
    };
});
