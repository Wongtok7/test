function check() {
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	if (username.value == '' || password.value == '') {
		alert("密码不能为空");
		return false;
	}
	return true;
}