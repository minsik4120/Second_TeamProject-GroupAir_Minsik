$('#ShowPw').on('click', ShowPwFn);


function ShowPwFn() {
    if ($("#userPw").attr("type") == "password") {
        $("#userPw").attr("type", "text");
    } else {
        $("#userPw").attr("type", "password");
    }
}