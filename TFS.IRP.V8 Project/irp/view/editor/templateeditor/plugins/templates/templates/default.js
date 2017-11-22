/*
 Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.html or http://ckeditor.com/license
*/
CKEDITOR.addTemplates("default",{
	imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
	templates:[{
		title:"图片和标题",
		image:"template1.gif",
		description:"一幅图片，一个主题，文本环绕着图片。",
		html:'<h3><img style="margin-right: 10px" height="100" width="100" align="left"/>请在此输入主题</h3><p>请在此输入文本</p>'
	},
	{
		title:"文字和表格",
		image:"template3.gif",
		description:"一幅图片，一个主题，文本环绕着图片。",
		html:'<div style="width: 80%"><h3>请在此输入标题</h3><table style="width:150px;float: right" cellspacing="0" cellpadding="0" border="1"><caption style="border:solid 1px black"><strong>表格标题</strong></caption></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table><p>请在此输入文本</p></div>'
	},
	{
		title:"奇怪的模板",
		image:"template2.gif",
		description:"一个模板，定义了两个列 ，每一列有一个标题和文本。",
		html:'<table cellspacing="0" cellpadding="0" style="width:100%" border="0"><tr><td style="width:50%"><h3>标题1</h3></td><td></td><td style="width:50%"><h3>标题2</h3></td></tr><tr><td>文本1</td><td></td><td>文本2</td></tr></table><p>更多文本</p>'
	}]
});