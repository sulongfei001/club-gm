// 全局对象
var oss = oss || {};
oss.async = false;

// 系统全局常量
const host = "../";
const OAUTH = "oauth";
const USER_NAME = "username";

// 启用 cookie存放json
$.cookie.json = true;

var clearCookie = function () {
    $.removeCookie(OAUTH, {path: '/'});
    $.removeCookie(USER_NAME, {path: '/'});
}


var generateToken = function (username, password) {
    var url = "oauth/token"
        + "?client_id=browser"
        + "&client_secret=EAYXNjF65EAtdajSjX7NDrHKehqcrN5J"
        + "&grant_type=password"
        + "&password=" + password
        + "&username=" + username;

    request(url, null, "post", function (res, status) {
        if (status == 200) {
            clearCookie();

            // cookie过期时间设置
            res.expires_date = new Date(new Date().getTime() + res.expires_in * 1000);

            delete res.expires_in;
            $.cookie(OAUTH, res, {expires: res.expires_date, path: '/'});
            $.cookie(USER_NAME, username, {expires: res.expires_date, path: '/'});

            location.href = "index.html";
        }
        else if (status == 400) {
            layer.msg("账户或密码错误");
        }
    });
}


var refreshToken = function (refreshToken) {
    var url = "oauth/token"
        + "?client_id=browser"
        + "&client_secret=EAYXNjF65EAtdajSjX7NDrHKehqcrN5J"
        + "&grant_type=refresh_token"
        + "&refresh_token=" + refreshToken;
    request(url, null, "post", function (res, status) {
        if (status == 200) {
            // 清除access token
            $.removeCookie(OAUTH, {path: '/'});

            // 重设access token
            res.expires_date = new Date(new Date().getTime() + res.expires_in * 1000);

            delete res.expires_in;
            $.cookie(OAUTH, res, {expires: res.expires_date, path: '/'});
            $.cookie(USER_NAME, $.cookie(USER_NAME), {expires: res.expires_date, path: '/'});
        }
        else {
            location.href = "login.html";
        }
    });
}


/**
 * http 请求
 * @param url       请求url
 * @param data      请求数据
 * @param type      请求方式 get|post|put|patch|delete
 * @param callback  回调函数
 */
var request = function (url, data, type, callback) {
    $.ajax({
        url: host + url,
        data: data,
        type: type,
        async: oss.async,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        cache: false,
        dataType: "json",
        // 120s超时
        timeout: 120000,
        success: function (res, ts, xhr) {
            if (typeof callback === "function") {
                callback.call(this, res, xhr.status);
            }
        },
        error: function (xhr, ts, thrown) {
            if (typeof callback === "function") {
                callback.call(this, JSON.parse(xhr.responseText ? xhr.responseText : "{}"), xhr.status);
            }
        }
    });
}

var appendAccessTokenAfterUrl = function (url) {
    var oauth = $.cookie(OAUTH);
    var accessToken = oauth ? oauth.access_token : "";

    if (url.indexOf("?") == -1) {
        url = url + "?access_token=" + accessToken;
    } else {
        url = url + "&access_token=" + accessToken;
    }

    return url;
}


var get = function (url, success, error) {
    request(appendAccessTokenAfterUrl(url), null, "get", function (res, status) {
        handleRes(this, res, status, success, error);
    });
}


var post = function (url, data, success, error) {
    request(appendAccessTokenAfterUrl(url), data, "post", function (res, status) {
        handleRes(this, res, status, success, error);
    })
}

var put = function (url, data, success, error) {
    request(appendAccessTokenAfterUrl(url), data, "put", function (res, status) {
        handleRes(this, res, status, success, error);
    });
}

var patch = function (url, data, success, error) {
    request(appendAccessTokenAfterUrl(url), data, "patch", function (res, status) {
        handleRes(this, res, status, success, error);
    });
}

var del = function (url, success, error) {
    request(appendAccessTokenAfterUrl(url), null, "delete", function (res, status) {
        handleRes(this, res, status, success, error);
    });
}


var handleRes = function (_this, res, status, success, error) {
    var refreshTokenIfLack = function () {
        var oauth = $.cookie(OAUTH);
        // 过期时间<=10分钟 刷新token
        if (Date.parse(oauth.expires_date) - Date.now() <= 6e+5) {
            refreshToken(oauth.refresh_token);
        }
    }

    var successCallback = function () {
        if (typeof success === "function") {
            success.call(_this, res);
        }
    }

    var errorCallback = function () {
        if (typeof error === "function") {
            error.call(_this, res, status);
        } else {
            layer.msg(status == 500 ? "服务器内部错误" : res.message);
        }
    }

    switch (status) {
        case 200:
            refreshTokenIfLack();
            successCallback();
            break;
        case 401:
            location.href = "login.html";
            break;
        default:
            refreshTokenIfLack();
            errorCallback();
            break;
    }
};

var ajax = function (url, type, data, async, success, error) {
    $.ajax({
        url: host + appendAccessTokenAfterUrl(url),
        type: type,
        data: data,
        async: async,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        dataType: "json",
        timeout: 12000,
        success: function (res, ts, xhr) {
            if (typeof success === "function") {
                success.call(this, res, xhr.status);
            }
        },
        error: function (xhr) {
            if (typeof error === "function") {
                error.call(this, JSON.parse(xhr.responseText ? xhr.responseText : "{}"), xhr.status);
            }
        }
    });
};