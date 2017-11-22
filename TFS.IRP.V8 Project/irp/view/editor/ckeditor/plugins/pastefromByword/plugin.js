(function() {
    CKEDITOR.plugins.add("pastefromByword", {
        requires: ["dialog"],
        init: function(a) {
            a.addCommand("pastefromByword", new CKEDITOR.dialogCommand("pastefromByword"));
            a.ui.addButton("pastefromByword", {
                label: "pastefromByword",//调用dialog时显示的名称
                command: "pastefromByword",
                icon: this.path + "word.png"//在toolbar中的图标
 
            });
            CKEDITOR.dialog.add("pastefromByword", this.path + "dialogs/pastefromByword.js")
 
        }
 
    })
 
})();