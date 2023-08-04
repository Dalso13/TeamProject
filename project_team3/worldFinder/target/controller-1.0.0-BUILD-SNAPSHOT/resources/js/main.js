$(function () {
    document.getElementById("logo").onclick = function () {
        location.href = "/";
    }
})

document.getElementById("adminGo").onclick = function () {
    location.href = "/adminPage";
}
document.getElementById("requestGo").onclick = function () {
    location.href = "/request";
}
document.getElementById("login").onclick = function () {
    location.href = "/user/loginPage";
}
document.getElementById("joinPage").onclick = function () {
    location.href = "/user/joinPage";
}
document.getElementById("writeGO").onclick = function () {
    location.href = "/countryWrite";
}