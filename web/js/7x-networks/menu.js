
/**
 * 实例化form，完善校验功能
 * @param formId
 * @returns {jQuery|HTMLElement}
 */
var initForm = function (formId) {
    var form = $("#" + formId);

    kendo.init(form);
    form.kendoValidator({
        messages: {
            required: function (input) {
                return input.data("message");
            },
            verifyPasswords: "两次输入的密码不匹配!"
        },
        rules: {
            verifyPasswords: function (input) {
                var ret = true;
                if (input.is("[name=targetPasswd_2]")) {
                    ret = input.val() === $("#targetPasswd").val();
                }
                return ret;
            },
            editor: function (input) {
                // 文本编辑器长度校验
                if (input.is("[data-editor-msg]")) {
                    var defaultLength = 1000;
                    var value = $("#" + input.attr("id")).data("kendoEditor").value();
                    var maxLength = isNaN(input.attr("maxLength")) ? defaultLength : input.attr("maxLength");
                    return value.length < maxLength;
                }

                return true;
            }
        }
    });

    return form;
};

var myConfirm = function (content, doneFn, failFn) {
    $(".ht_tk_zg").show();
    $("body").append($('<div id="confirm_dialog" class="ht_r_div1 ht_tk_dw ">\n' +
        '\t<ul>\n' +
        '\t\t<li class="ht_r_bt font_18 color_2 pa_dd text_center">警告</li>\n' +
        '\t\t<li class="pa_dd text_center">\n' +
        '\t\t\t<p class="ht_qrts"><img src="../image/index_8.png"><span class="ztcolor_h">' + content + '</span></p>\n' +
        '\t\t</li>\n' +
        '\t\t<li class="ht_r_aniu text_center" style="padding:10px 120px; ">\n' +
        '\t\t\t<button id="confirm_ok" class="k-button k-primary mar_gin" id="get">确定</button>\n' +
        '\t\t\t<button id="confirm_cancel" class="k-button mar_gin ht_qux">取消</button>\n' +
        '\t\t</li>\n' +
        '\t</ul>\n' +
        '</div>'));

    $("#confirm_ok").on("click", function () {
        $(".ht_tk_zg").hide();
        $("#confirm_dialog").remove()
        if (typeof doneFn === "function") {
            doneFn.call(this);
        }
    });

    $("#confirm_cancel").on("click", function () {
        $(".ht_tk_zg").hide();
        $("#confirm_dialog").remove()
        if (typeof failFn === "function") {
            failFn.call(this);
        }
    });
}