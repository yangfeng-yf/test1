/**
 * 日期转换函数
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}


/**
 * post方式的Ajax请求 dateType默认json
 * @param dataType
 */
function postAjax(requstUrl ,dataType,data,successFn,errFn){
    if (dataType == null){
        dataType = "json";
    }
    $.ajax({
        url:requstUrl,
        type:"post",
        dateType: dataType,
        data:data,
        success: successFn,
        error:errFn
    })
}

/**
 *  获取从另外页面传递的属性
 * @param name
 * @returns {null}
 */
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
