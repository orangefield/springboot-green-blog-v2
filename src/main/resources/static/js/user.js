// 1. 이벤트 리스너

$("#btn-join").click(() => {
join();
});

$("#btn-login").click(() => {
login();
});

$("#btn-update").click(() => {
    update();
});


// 2. 기능

// 회원정보 수정 함수
async function update() {
    let id = $("#id").val();
    let updateDto = {
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    let response = await fetch(`/s/api/user/${id}`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });

    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("업데이트 성공");
        location.href = `/s/user/${id}`;
    } else {
        alert("업데이트 실패");
    }
}

// 유저네임 기억하기 함수
function usernameRemember() {
    let cookies = document.cookie.split("=");
    // console.log(cookies[1]);
    $("#username").val(cookies[1]);
}
usernameRemember();



// 회원가입 함수
let join = async () => {
    // (1) username, password, email, addr을 찾아서 JS 오브젝트로 만든다
    let joinDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    // (2) JSON 변환과 fetch 요청
    let response = await fetch("/join", {
        method: "POST",
        body: JSON.stringify(joinDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        }
    });

    let responseParse = await response.json();

    // (3) 회원가입 성공시 알림창 띄우고 로그인 페이지로 이동
    if (responseParse.code == 1) {
        alert("회원가입이 완료되었습니다");
        location.href = "/loginForm";
    } else {
        alert("회원가입 실패");
    }
}


// 로그인 요청 함수
let login = async () => {

    let checked = $("#remember").is(":checked");

    // (1) username, password 찾아서 JS 오브젝트로 만들기
    let loginDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        remember: checked ? "on" : "off" 
    }

    // (2) JSON 변환과 fetch 요청
    let response = await fetch("/login", {
        method: "POST",
        body: JSON.stringify(loginDto),
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        }
    });

    let responseParse = await response.json();

    // (3) 로그인 성공시 메인페이지로 이동
    if (responseParse.code == 1) {
        alert("로그인 성공");
       location.href = "/";
    } else {
        alert("로그인 실패");
    }
}


