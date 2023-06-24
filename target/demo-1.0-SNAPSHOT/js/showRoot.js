window.onload = function() {

    // 初始化ajax对象
    let xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    } else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    // 绑定删除按钮
    let delete_btns = document.getElementsByClassName("delete-btn");
    for (let i = 0; i < delete_btns.length; i++) {
        delete_btns[i].onclick = function() {
            let grandparent_tag = delete_btns[i].parentElement.parentElement;
            let sid = grandparent_tag.getElementsByClassName("sid-tag")[0].textContent;
            let courseName = grandparent_tag.getElementsByClassName("courseName-tag")[0].textContent;
            let url = getContentPath() + "/delete?" +
                    "deleteSid=" + sid +
                    "&deleteCourseName=" + courseName;

            // 确认弹窗
            let r = confirm("确认删除" +
                            " 学号:" + sid +
                            " 科目:" + courseName +
                            " 的成绩");

            if (r) {
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState===4 && xmlhttp.status===200) {
                        if (xmlhttp.responseText === "success") {
                            grandparent_tag.remove();
                        } else {
                            alert("删除失败");
                        }
                    }
                }
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
        }
    }

    // 绑定修改按钮
    let update_btns = document.getElementsByClassName("update-btn");
    for (let i = 0; i < update_btns.length; i++) {
        update_btns[i].onclick = function() {
            let grandparent_tag = update_btns[i].parentElement.parentElement;
            let sid = grandparent_tag.getElementsByClassName("sid-tag")[0].textContent;
            let courseName = grandparent_tag.getElementsByClassName("courseName-tag")[0].textContent;
            let score_tag = grandparent_tag.getElementsByClassName("score-tag")[0];

            let url;
            let newScore = prompt("请输入修改后分数","");
            if (newScore != null && newScore !== "") {
                if (isNaN(newScore)) {
                    alert("输入不合法");
                    return;
                }
                url = getContentPath() + "/update?" +
                    "updateSid=" + sid +
                    "&updateCourseName=" + courseName +
                    "&updateScore=" + newScore;

                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState===4 && xmlhttp.status===200) {
                        if (xmlhttp.responseText === "success") {
                            score_tag.textContent = String(parseFloat(newScore).toFixed(1));
                        } else {
                            alert("修改失败")
                        }
                    }
                }
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }


        }
    }

    /**
     * 获取项目根目录
     * @returns {string}
     */
    function getContentPath() {
        let curWwwPath = document.location.href;
        let pathName = document.location.pathname;
        let localhostPath = curWwwPath.substring(0, curWwwPath.indexOf(pathName));
        let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPath + projectName);
    }

}