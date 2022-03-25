// 1. 이벤트 리스너
$("#btn-logout").click(()=>{
    logout();
});

// 2. 기능
async function logout(){
    let response = await fetch("/logout");
    let responseParse = await response.json(); // json을 JS오브젝트로 파싱

    if(responseParse.code == 1){
        location.href = "/";
    }
}