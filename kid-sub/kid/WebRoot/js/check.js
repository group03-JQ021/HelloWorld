// 全选全不选效果

// 每次切换复选框选项后, 都判断并刷新一次全选按钮状态
function select_deselect(){

    var chks = document.getElementsByName(chk_name);
    var allSelected = true; // 是否全选

    // 遍历, 是否全选
    for (var i = chks.length - 1; i >= 0; i--) {
        if (!chks[i].checked){
            allSelected = false;
            break;
        }
    }

    // 更新全选框状态
    var chk_all = document.getElementById(chk_all_id);
    if(allSelected) chk_all.checked=true;
    else chk_all.checked=false;
}

// 全选/全不选效果
// chkbox_all 是全选全不选的复选框对象
function select_deselect_all(chkbox_all){

    // console.log("seelct all/")
    var chks = document.getElementsByName(chk_name);
    // console.log(chkbox_all)
    // console.log(chk_name)
    for (var i = chks.length - 1; i >= 0; i--) {
        chks[i].checked = chkbox_all.checked;
    }
}
