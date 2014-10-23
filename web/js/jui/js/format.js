/**
 * User: tcg
 * Date: 14-6-29
 * Time: 下午9:53
 */

    //扩展currency类型的格式化函数
$.juiceDefaults.Grid.formatters['currency'] = function (num, column) {
    //num 当前的值
    //column 列信息
    if (!num) return  (column.prefix||"") + "0.00";
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0.00";
    var sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    var cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return  (column.prefix||"") + (((sign) ? '' : '-') + '' + num + '.' + cents);
}

$.juiceDefaults.Grid.formatters['percent'] = function (num, column) {
    //num 当前的值
    //column 列信息
   if(!num||isNaN(num))  {
       return  "0.00%";
   }else{
       return (100*num)+"%"
   }
}