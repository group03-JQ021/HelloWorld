$(function(){
	console.log("loading...");
	var storedName = localStorage.getItem('name');
	var storedPassword = localStorage.getItem('password');
	
	if (storedPassword != null && storedPassword != null){
		$("[name=username]").val(storedName);
		$("[name=pwd]").val(storedPassword);
	}
	
	// 自动登录
	$("#remember").click(function(){
		console.log($(this).attr("checked"));
		switch ($(this).attr("checked")){
		case 'checked':
			$(this).attr("checked",false);
			
			
			
			break;
		case false:
		case undefined:
			
			
			
			$(this).attr("checked","checked");
			break;
		}
	});
	console.log("done");
});