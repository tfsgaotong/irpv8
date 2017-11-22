﻿﻿/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	config.language = 'zh-cn';
	config.skin = 'moonocolor';
	//config.uiColor = '#AADC6E';
	config.image_previewText = '<br />&nbsp;&nbsp;&nbsp;&nbsp;北京睿思鸣信息技术有限公司（简称：睿思鸣）是成立于关村国家自主创新示范区的软件企业。'
		+'<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;公司拥有智力资源管理、企业知识管理、智能搜索技术，自然语言智能处理等核心技术。'
		+'<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;睿思鸣整合了国内智力资源管理领域优秀的人力资源，致力于知识管理和组织智力资源[2]的研发与应用，核心员工均参与过多个国内大型企业的知识管理系统开发和实施工作，具有丰富的知识管理经验。可以提供知识管理系统产品和整体解决方案以及为其他公司提供高端的技术顾问与咨询。'
		+'<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;睿思鸣凭借优秀的技术、产品和服务表现，在知识管理软件市场成功树立了专业的高端品牌形象。正在成为业内实用性最强、口碑最好的知识管理软件供应商。';
	
	config.removePlugins = 'elementspath,iframe,smiley';
	config.extraPlugins = 'irpext,flvPlayer,autopaikgj,lineheight,multiimg';//,hotword,hotwordclose,pastefromByword
	
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	config.height = 360;

	config.toolbarGroups = [
	    {name:"basicstyles",groups:["basicstyles","cleanup"]},
	    {name:"insert"},
	    {name: "others"}, 
	    {name:"clipboard",groups:["clipboard","undo"]},
	    {name:"mode", groups: ['doctools','mode']},
	    {name:"paragraph",groups:["list","indent","align"]},
	    {name:"links"},
	    {name:"hotwrd"},
	    {name:"hotwordclose"},
	    {name:"autoformat"},
	    {name:"styles"},
	    {name:"colors"},
	    {name:"editing",groups:["find","selection","spellchecker"]}
    ];
    //工具栏可伸缩
	config.toolbarCanCollapse = true;
};