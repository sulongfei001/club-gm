function isFloat(value) {
    var regex = "/^[-+]?([0-9]+(\.[0-9]*)?|\.[0-9]+)$/";

    if ((eval(regex)).test(value)) {
        return true;
    } else {
        return false;
    }
}

function isInt(value) {
    var regex = "/^[1-9]+[0-9]*$/";

    if ((eval(regex)).test(value)) {
        return true;
    } else {
        return false;
    }
}

var isIP = function(str) {
    var pr = /^\/([1-9]|1[0-9]|2[0-9]|3[0-2])$/;
    var ip = /^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])((\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3})$/;
    if (str.indexOf("\/") != -1) {
        var p1 = str.substr(str.indexOf("\/"));
        var p0 = str.substr(0, str.indexOf("\/"));
        return (ip.test(p0) && pr.test(p1));
    } else {
        return ip.test(str);
    }
}

function isNetMask(value) {
    var regex = "/(254|252|248|240|224|192|128|0)\\.0\\.0\\.0|255\\.(254|252|248|240|224|192|128|0)\\.0\\.0|255\\.255\\.(254|252|248|240|224|192|128|0)\\.0|255\\.255\\.255\\.(255|254|252|248|240|224|192|128|0)/";
    if ((eval(regex)).test(value)) {
        return true;
    } else {
        return false;
    }
}

function isDest(value) {
    var regex = "/^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$/";
    if ((eval(regex)).test(value)) {
        return true;
    } else {
        return false;
    }
}

function isIPLst(value) {
    var regex="/^([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])((\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})(([//](\\d|[1-2]\\d|[3][0-2]))?)$/"
    if ((eval(regex)).test(value)) {
        return true;
    } else {
        if("0.0.0.0/0" == value){
            return true;
        }
        return false;
    }
}