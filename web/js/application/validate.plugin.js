/**
 *
 */
$.validator.addMethod("isNumber", function(value,element) {
    var length = value.length;
    var regx = /^\d+.?\d+$/;
    return this.optional(element) || (regx.test(value)|| (/^\d+$/).test(value));
}, "请输入数字");