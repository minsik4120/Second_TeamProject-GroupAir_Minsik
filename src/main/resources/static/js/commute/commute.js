$('#workIn').on('click', workInFn);
$('#workOut').on('click', workOutFn);



function workInFn() {

    var time = new Date();

    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    $('#workIn').text(
        `${month + 1}월 ${date}일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds} 출근`);
}

function workOutFn() {
    var time = new Date();

    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    $('#workOut').text(
        `${month + 1}월 ${date}일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds} 퇴근`);
}