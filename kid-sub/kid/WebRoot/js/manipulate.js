
function delete_() {

    // 获取所有被选中的幼儿信息的id
    //将其v用逗号隔开拼接成一个字符串
    var chks = document.getElementsByName(chk_name);


    var checkedIds = "";
    for (var i = 0; i < chks.length - 1; i++) {
        if (chks[i].checked) checkedIds = checkedIds + "," + chks[i].value;
    }

    if (checkedIds == ""){
        alert("请先选择要删除的数据!");
        return;
    }
    // 去掉开头的逗号 ","
    checkedIds = checkedIds.substring(1);
    console.log("select: " + checkedIds);

    //上面的字符串赋值给隐藏域表单
    document.getElementById(input_ids_id).value=checkedIds;

    var theForm = document.getElementById(formId);

    // 删除前确认
    if (!confirm("确定删除?")){ return;}
    theForm.setAttribute("action", "DeleteKidsServlet");

    //提交隐藏域表单，后台才能获取隐藏域表单的值
    theForm.submit();
    console.log("submit:");
    console.log("done");
}
