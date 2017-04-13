/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	 
    config.filebrowserUploadUrl = "/attachment/import-attachment-ckeditor.do";

	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	config.filebrowserImageUploadUrl = '/attachment/import-attachment-ckeditor.do'; // 固定路径
	config.height = 400;
	config.width = 'auto';
	config.removeButtons = 'Flash';
};
