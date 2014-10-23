function isNumber(str){
    var strTemp="0123456789";
    var len=str.length;
    var isNum=true;
    for(i=0;i<len;i++){
        if(strTemp.indexOf(str.charAt(i))<0){
            isNum=false;
            break;
        }
    }
    return isNum;
}

function submitPageForm(formName,pageNo,pageSize){
    if(pageNo==null||pageNo=="")
        return;
    if(!isNumber(pageNo))
        return;

    var frm = $("#" + formName);
    var elmPageNo;
    if (typeof(frm['page']) == 'undefined')
    {
        elmPageNo  = $("<input type='hidden' id='page' name='page' value='" + pageNo + "' />");
    }else{
        elmPageNo  = frm['page'];
    }
    var	elmPageSize;
    if (typeof(frm['pageSize']) == 'undefined')
    {
        elmPageSize  = $("<input type='hidden' id='pageSize' name='pageSize' value='" + pageSize + "' />");
    }else{
        elmPageSize  = frm['pageSize'];
    }
//    elmPageNo.val(pageNo);
//    elmPageSize.val(pageSize);
    frm.append(elmPageNo);
    frm.append(elmPageSize);
    debugger;
    frm.submit();
}