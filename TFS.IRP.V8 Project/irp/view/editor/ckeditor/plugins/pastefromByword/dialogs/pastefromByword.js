(function() {
	var content = "";
    CKEDITOR.dialog.add("pastefromByword", 
    function(a) {
        return {
            title: "Word路径导入",
            minWidth: "500px",
            minHeight:"500px",
            contents: [{
                id: "tab1",
                label: "",
                title: "",
                expand: true,
                width: "980px",
                height: "400px",
                padding: 0,
                elements: [{
                    type: "html",
                    style: "width:980px;height:400px",
                    html: '<iframe id="WorduploadFrame" src="loadpasteByWord.action" frameborder="0"></iframe>'
                }]
            }],
            onOk: function() {
                //点击确定按钮后的操作
                a.insertHtml(window.content);
            }
        }
    })
})();