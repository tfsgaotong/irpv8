/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	config.language = 'zh-cn';
	config.skin = 'moonocolor';
	// For the complete reference:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config
	config.uiColor = '#F1F1F1';
	// The toolbar groups arrangement, optimized for a single toolbar row.
	config.toolbarGroups = [
		{ name: 'basicstyles', groups: [ 'basicstyles' ] }
	];

	config.width = 383;
	config.height = 155;
	// The default plugins included in the basic setup define some buttons that
	// we don't want too have in a basic editor. We remove them here.
	config.removeButtons = 'Cut,Copy,Paste,Undo,Redo,Anchor,Underline,Strike,Subscript,Superscript';

	// Let's have it basic on dialogs as well.
	config.removeDialogTabs = 'link:advanced';
	config.removePlugins = 'elementspath,iframe';
};
