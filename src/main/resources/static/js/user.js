// 1. 이벤트 리스너
    $("#btn-join").click(() => {
    join();
    });

    $("#btn-login").click(() => {
    login();
    });


// 2. 기능
// 회원가입 메서드
let join = async () => {
    // (1) username, password, email, addr을 찾아서 JS 오브젝트로 만든다
    let joinDto = {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        addr: $("#addr").val()
    }

    // (2) JSON 변환과 fetch 요청
    let response = await fetch("/api/join", {
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


// 로그인 요청 메서드
let login = async () => {
    // (1) username, password 찾아서 JS 오브젝트로 만들기
    let loginDto = {
        username: $("#username").val(),
        password: $("#password").val()
    }

    // (2) JSON 변환과 fetch 요청
    let response = await fetch("/api/login", {
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

