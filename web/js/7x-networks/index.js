$(document).ready(function () {
    var _height = $(window).height();
    $(".ht_cont").css("height", _height - 64);

    $('.user_xq').find('a:last').click(function () {
        window.open('login.html', '_top');
    });

    //菜单折叠
    $(".cont_mul").each(function () {
        $(this).attr("cata", "1");
        //折叠起来
        $(this).siblings().slideUp();
        //icon方向改变
        $(this).find("i").removeClass("ht_xz1");
    });
    $(".cont_mul").click(function () {
        if ($(this).attr("cata") == "1") {

            $(".cont_mul").each(function () {
                $(this).attr("cata", "1");
                $(this).siblings().slideUp();
                $(this).find("i").removeClass("ht_xz1");
            });

            $(this).find("i").addClass("ht_xz1");
            $(this).siblings().slideDown();
            $(this).attr("cata", "0")
        } else {
            $(this).find("i").removeClass("ht_xz1");
            $(this).siblings().slideUp();
            $(this).attr("cata", "1")
        }
    });

    //图片切换
    $(".user_xq a").mouseenter(function () {
        $(this).find("span").addClass("ztcolor_zi");
        $(this).css({"background-image": "url(../image/" + $(this).attr("data-img") + "_2.png)"});
    });
    $(".user_xq a").mouseleave(function () {
        $(this).find("span").removeClass("ztcolor_zi");
        $(this).css({"background-image": "url(../image/" + $(this).attr("data-img") + "_1.png)"});
    });

    /*$(".ht_cont_suml a").mouseenter(function () {
        if (!$(this).hasClass('ht_active')) {
            $(this).find("span").addClass("ztcolor_zi");
            $(this).css({"background-image": "url(../image/index_" + $(this).attr("data-imgnumber") + "a.png)"});
        }
    });
    $(".ht_cont_suml a").mouseleave(function () {
        if (!$(this).hasClass('ht_active')) {
            $(this).find("span").removeClass("ztcolor_zi");
            $(this).css({"background-image": "url(../image/index_" + $(this).attr("data-imgnumber") + ".png)"});
        }
    });*/

    //右侧菜单隐藏
    $(".ht_cont_jt").click(function () {
        if ($(this).attr("clk") == "0") {
            $(this).find("img").addClass("ht_xz2");
            $(this).css({"left": "-16px"});
            $(".ht_cont_l").css({"left": "-260px"});
            $(".ht_cont_r").css({"width": "calc(100%)"});
            $(this).attr("clk", "1");
            setTimeout(function () {
                $(".ht_cont_jt").css({"left": "0"})
            }, 500);
        } else {
            $(this).find("img").removeClass("ht_xz2");
            $(this).css({"left": "260px"});
            $(".ht_cont_l").css({"left": "0"});
            $(".ht_cont_r").css({"width": "calc(100% - 260px)"});
            $(this).attr("clk", "0");
        }
    });

    $("#alertBtn").on("click", function () {
        kendo.alert("This is a Kendo UI Alert message.");
    });

    $("#confirmBtn").on("click", function () {
        kendo.confirm("Are you sure that you want to proceed?").then(function () {
            kendo.alert("You chose the Ok action.");
        }, function () {
            kendo.alert("You chose to Cancel action.");
        });
    });

    $("#promptBtn").on("click", function () {
        kendo.prompt("Please, enter a arbitrary value:", "any value").then(function (data) {
            kendo.alert(kendo.format("The value that you entered is '{0}'", data));
        }, function () {
            kendo.alert("Cancel entering value.");
        })
    });

    $("#size").kendoComboBox();
    $("#size1").kendoComboBox();

    $("#value").kendoDatePicker();


    $("#numeric").kendoNumericTextBox();

    $(window).resize(function () {
        var height = $(this).height();
        $('section.ht_cont_r').css({
            height: height - 118
        });
    });

});

