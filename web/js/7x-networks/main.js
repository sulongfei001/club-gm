var myPanelBar;
var kendoWidgets = new Array();
var setIntervals = new Array();

var destroyWidget = function () {
    // 释放上一张页面的内存
    for (var i = 0; i < kendoWidgets.length; i++) {
        kendoWidgets[i].destroy();
    }
    kendoWidgets.splice(0, kendoWidgets.length);
}

var clearIntervals = function () {
    // 释放上一张页面的定时任务
    for (var i = 0; i < setIntervals.length; i++) {
        clearInterval(setIntervals[i]);
    }
    setIntervals.splice(0, setIntervals.length);
}

var loading=function (display) {
    if(display==true){
        $(".ht_tk_zg").show();
        $("#ht_progressbar").show();
    }else{
        $(".ht_tk_zg").hide();
        $("#ht_progressbar").hide();
    }
}

var loadPage = function (path, elementId) {
    loading(true);

    /*$(".ht_cont_suml a").each(function () {
        $(this).removeClass("ht_active");
        $(this).find("span").removeClass("ztcolor_zi");
        $(this).css({"background-image": "url(../image/index_" + $(this).attr("data-imgnumber") + ".png)"});
    });*/

    /*destroyWidget();
    clearIntervals();*/

    // load 新页面
    $("#content").load(path, null, function () {
        /*if(elementId!=null){
            var a_element = $("#"+elementId);
            a_element.find("span").addClass("ztcolor_zi");
            a_element.css({"background-image": "url(../image/index_" + a_element.attr("data-imgnumber") + "a.png)"});
            a_element.addClass("ht_active");
        }*/
        // 关闭loading框
        loading(false);
    });
};

var logout = function () {
    destroyWidget();
    clearCookie();
    location.href = "login.html";
}


var renderKendoWidget = function () {

    initPanelBarHeight();


    dialog = $('#dialog');
    dialog.kendoDialog({
        width: "240px",
        title: false,
        closable: false,
        visible: false,
        modal: true,
        content: function () {
            $("#progressbar").kendoProgressBar({
                value: false
            });
        }
    });
    myKendoDialog = $('#dialog').data("kendoDialog");

    $("#horizontal").kendoSplitter({
        panes: [
            {
                collapsible: true,
                resizable: false,
                size: "256px"
            },
            {collapsible: false}
        ]
    });
    mykendoSplitter = $('#horizontal').data("kendoDialog");

    $("#panelbar").kendoPanelBar({
        dataSource: new kendo.data.HierarchicalDataSource({
            data: [
                {
                    text: "加速业务",
                    items: [
                        {
                            text: "盒子管理",
                            url: "javascript:loadPage(\"box/enterprise/list.html\");"
                        },
                        {
                            text: "入口管理",
                            url: "javascript:loadPage(\"enterprise/entry/list.html\");"
                        },
                        {
                            text: "加速业务管理",
                            url: "javascript:loadPage(\"acceleration/enterprise/list.html\");"
                        },
                        {
                            text: "加速集合管理",
                            url: "javascript:loadPage(\"acceleration/enterprise/collection/list.html\");"
                        },
                        {
                            text: "升级管理",
                            url: "javascript:loadPage(\"enterprise/upgrade/list.html\");"
                        }/*,
                        {
                            text: "应用识别管理",
                            url: "javascript:loadPage(\"enterprise/app/list.html\");"
                        }*/
                    ],
                    expanded: true
                },
                {
                    text: "组网业务",
                    items: [
                        {
                            text: "盒子管理",
                            url: "javascript:loadPage(\"box/network/list.html\");"
                        },
                        {
                            text: "入口管理",
                            url: "javascript:loadPage(\"network/entry/list.html\");"
                        },
                        {
                            text: "业务管理",
                            url: "javascript:loadPage(\"network/business/list.html\");"
                        },
                        {
                            text: "升级管理",
                            url: "javascript:loadPage(\"network/upgrade/list.html\");"
                        }
                    ],
                    expanded: true
                },
                {
                    text: "混合云业务",
                    items: [
                        {
                            text: "盒子管理",
                            url: "javascript:loadPage(\"box/mine/list.html\");"
                        },
                        {
                            text: "入口管理",
                            url: "javascript:loadPage(\"mine/entry/list.html\");"
                        },
                        {
                            text: "混合云服务器",
                            url: "javascript:loadPage(\"mine/hybrid/list.html\");"
                        },
                        {
                            text: "公有云管理",
                            url: "javascript:loadPage(\"mine/public_cloud/list.html\");"
                        },
                        {
                            text: "业务管理",
                            url: "javascript:loadPage(\"mine/business/list.html\");"
                        },
                        {
                            text: "升级管理",
                            url: "javascript:loadPage(\"mine/upgrade/list.html\");"
                        }
                    ],
                    expanded: true
                },
                {
                    text: "云链业务",
                    items: [
                        {
                            text: "区域管理",
                            url: "javascript:loadPage(\"adn/zone/list.html\");"
                        },
                        {
                            text: "入口管理",
                            url: "javascript:loadPage(\"adn/entry/list.html\");"
                        },
                        {
                            text: "服务管理",
                            url: "javascript:loadPage(\"adn/service/list.html\");"
                        },
                        {
                            text: "域名管理",
                            url: "javascript:loadPage(\"adn/domain/list.html\");"
                        }
                    ],
                    expanded: true
                },
                {
                    text: "网吧业务",
                    items: [
                        {
                            text: "盒子管理",
                            url: "javascript:loadPage(\"box/internet_cafe/list.html\");"
                        },
                        {
                            text: "入口管理",
                            url: "javascript:loadPage(\"IPIP/entry/list.html\");"
                        },
                        {
                            text: "加速业务管理",
                            url: "javascript:loadPage(\"acceleration/internet_cafe/list.html\");"
                        },
                        {
                            text: "加速地址管理",
                            url: "javascript:loadPage(\"acceleration/target/list.html\");"
                        }
                    ],
                    expanded: true
                },
                {
                    text: "客户关系",
                    items: [
                        {
                            text: "企业客户管理",
                            url: "javascript:loadPage(\"customer/enterprise/list.html\");"
                        },
                        {
                            text: "企业订单管理",
                            url: "javascript:loadPage(\"order/enterprise/list.html\");"
                        },
                        {
                            text: "网吧客户管理",
                            url: "javascript:loadPage(\"customer/internet_cafe/list.html\");"
                        },
                        {
                            text: "网吧订单管理",
                            url: "javascript:loadPage(\"order/internet_cafe/list.html\");"
                        }
                    ],
                    expanded: true
                },
            ]
        })
    });
    myPanelBar = $("#panelbar").data("kendoPanelBar");
}


/**
 * 实例化城市控件
 * @param countryIndex  国家索引
 * @param provinceIndex 省份索引
 * @see dsy
 */
var initCity = function (countryIndex, provinceIndex) {
    $("#city").kendoDropDownList();
    city = $("#city").data("kendoDropDownList");
    city.setDataSource(new kendo.data.DataSource({
        data: dsy.Items["0_" + countryIndex + "_" + provinceIndex]
    }));
    city.select(0);
    kendoWidgets.push(city);

    return city;
};


/**
 * 实例化省|州控件
 * @param countryIndex 国家索引
 * @see dsy
 */
var initProvince = function (countryIndex) {
    $("#province").kendoDropDownList({
        change: function (e) {
            if (typeof(city) !== "undefined") {
                city.setDataSource(new kendo.data.DataSource({
                    data: dsy.Items["0_" + countryIndex + "_" + this.selectedIndex]
                }));
                city.select(0);
            }
        }
    });
    province = $("#province").data("kendoDropDownList");
    province.setDataSource(new kendo.data.DataSource({
        data: dsy.Items["0_" + countryIndex]
    }));
    province.select(0);
    kendoWidgets.push(province);

    return province;
};

//查询初始化省
var initProvinces = function (countryIndex) {
    var provincesData = new Dsy();
    provincesData.add("0_0", ["","北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西", "海南省", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆", "香港", "澳门", "台湾地区"]);
    $("#provinces").kendoDropDownList({

    });

    provinces = $("#provinces").data("kendoDropDownList");
    provinces.setDataSource(new kendo.data.DataSource({
        data: provincesData.Items["0_" + countryIndex]
    }));
    provinces.select(0);
    kendoWidgets.push(provinces);

    return provinces;
};


/**
 * 实例化城市控件
 * @see dsy
 */
var initCountry = function () {
    $("#country").kendoDropDownList({
        change: function (e) {
            if (typeof(province) !== "undefined") {
                province.setDataSource(new kendo.data.DataSource({
                    data: dsy.Items["0_" + this.selectedIndex]
                }));
                province.select(0);
            }

            if (typeof(city) !== "undefined") {
                city.setDataSource(new kendo.data.DataSource({
                    data: dsy.Items["0_" + this.selectedIndex + "_0"]
                }));
                city.select(0);
            }
        }
    });
    country = $("#country").data("kendoDropDownList");
    country.setDataSource(new kendo.data.DataSource({
        data: dsy.Items["0"]
    }));
    country.select(0);
    kendoWidgets.push(country);

    return country;
};

/**
 * 实例化角色控件
 */
var initRole = function () {
    $("#role").kendoDropDownList();
    role = $("#role").data("kendoDropDownList");
    role.setDataSource(new kendo.data.DataSource({
        data: ["ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"]
    }));
    role.select(0);
    kendoWidgets.push(role);

    return role;
};

var zone = [
    {text: "华东"},
    {text: "华南"},
    {text: "华北"}
];

var pay = [
    {text: "付费", value: true},
    {text: "免费", value: false},
];

var cipherMode = [
    {text: "RC4", value: "rc4"},
    {text: "RC4-MD5", value: "RC4-MD5"},
    {text: "AES-128-CFB", value: "aes-128-cfb"},
    {text: "AES-192-CFB", value: "aes-192-cfb"},
    {text: "AES-256-CFB", value: "aes-256-cfb"},
    {text: "AES-128-CTR", value: "aes-128-ctr"},
    {text: "AES-192-CTR", value: "aes-192-ctr"},
    {text: "AES-256-CTR", value: "aes-256-ctr"},
    {text: "BF-CFB", value: "bf-cfb"},
    {text: "CAMELLIA-128-CFB", value: "camellia-128-cfb"},
    {text: "CAMELLIA-192-CFB", value: "camellia-192-cfb"},
    {text: "CAMELLIA-256-CFB", value: "camellia-256-cfb"},
    {text: "CAST5-CFB", value: "cast5-cfb"},
    {text: "DES-CFB", value: "des-cfb"},
    {text: "IDEA-CFB", value: "idea-cfb"},
    {text: "RC2-CFB", value: "rc2-cfb"},
    {text: "SEED-CFB", value: "seed-cfb"},
    {text: "SALSA20", value: "salsa20"},
    {text: "CHACHA20", value: "chacha20"},
    {text: "CHACHA20-IETF", value: "chacha20-ietf"}
];

var bandwidthDisplay = function (data) {
    if (data < 1100) {
        return kendo.toString(data, "#.##") + " bps";
    } else if (data < 1100000) {
        return kendo.toString(data / 1000, "#.##") + " Kbps";
    } else if (data < 1100000000) {
        return kendo.toString(data / 1000 / 1000, "#.##") + " Mbps";
    } else {
        return kendo.toString(data / 1000 / 1000 / 1000, "#.##") + " Gbps";
    }
};

var throughputDisplay = function (data) {
    if (data < 1200) {
        return kendo.toString(data, "#.##") + " B";
    } else if (data < 1200 * 1024) {
        return kendo.toString(data / 1024, "#.##") + " KB";
    } else if (data < 1200 * 1024 * 1024) {
        return kendo.toString(data / 1024 / 1024, "#.##") + " MB";
    } else if (data < 1200 * 1024 * 1024 * 1024) {
        return kendo.toString(data / 1024 / 1024 / 1024, "#.##") + " GB";
    } else {
        return kendo.toString(data / 1024 / 1024 / 1024 / 1024, "#.##") + " TB";
    }
};

var lineChart = function (id, title, period) {
    $("#" + id).kendoChart({
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            type: "line",
            width: 2,
            markers: {
                visible: false
            }
        },
        series: [{
            field: "bandwidthTX",
            categoryField: "samplingTime",
            name: "上行带宽",
            color: "rgb(73, 254, 130)"
        }, {
            field: "bandwidthRX",
            categoryField: "samplingTime",
            name: "下行带宽",
            color: "rgb(8, 159, 238)"
        }],
        categoryAxis: {
            labels: {
                template: function (data) {
                    var time = new Date(data.value);
                    if (period == "day") {
                        if (time.getHours() % 3 == 0 && time.getMinutes() == 0 && time.getSeconds() == 0) {
                            return kendo.toString(time, "HH:mm");
                        } else {
                            return "";
                        }
                    } else if (period == "week") {
                        if (time.getHours() == 0 && time.getMinutes() == 0) {
                            return kendo.toString(time, "MM-dd");
                        } else {
                            return "";
                        }
                    } else if (period == "month") {
                        if (time.getDay() == 0 && time.getHours() == 0) {
                            return kendo.toString(time, "yyyy-MM-dd");
                        } else {
                            return "";
                        }
                    } else if (period == "halfYear") {
                        if (time.getDate() == 1 && time.getHours() == 0) {
                            return kendo.toString(time, "yyyy-MM-dd");
                        } else {
                            return "";
                        }
                    }

                    return "";
                }
            },
            majorGridLines: {
                visible: false
            },
            line: {
                visible: false
            }
        },
        valueAxis: {
            labels: {
                template: function (data) {
                    return bandwidthDisplay(data.value);
                }
            },
            line: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            shared: true,
            background: "rgb(73, 124, 254)",
            sharedTemplate: function (data) {
                var text = "<p>" + data.category + "</p>";
                text += "<p>RX:" + bandwidthDisplay(data.points[1].value) + "</p>";
                text += "<p>TX:" + bandwidthDisplay(data.points[0].value) + "</p>";
                return text;
            }
        }
    });
};

var subStringByLength = function  (length, text) {
    if (text != null && text.length > length) {
        return text.substring(0, length) + "...";
    } else {
        return text;
    }
};