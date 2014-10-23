function chkbox(item,index,value,column){
    var id = this.id;
    if(value=="1"){
        return "<input type='checkbox' checked onclick='setChkValue(this,\""+id+"\","+index+",\""+column.__id+"\")'/>";
    }else{
        return "<input type='checkbox' onclick='setChkValue(this,\""+id+"\","+index+",\""+column.__id+"\")'/>";
    }
}

function setChkValue(btn,id,index,columnId){
    var cell = $.jui.get(id).getCellObj(index,columnId);
    $(cell).addClass("l-grid-row-cell-edited");
    $.jui.get(id).updateCell($.jui.get(id).getColumn(columnId),btn.checked?1:0,index);
}