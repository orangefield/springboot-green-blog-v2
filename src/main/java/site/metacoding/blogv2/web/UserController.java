package site.metacoding.blogv2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // 앱은 얘 요청 X
    // 웹은 얘 요청 O
    @GetMapping("/s/user/{id}")
    public String userInfo(@PathVariable Integer id /* , Model model */) {
        // User userEntity = userService.회원정보(id);
        // model.addAttribute("user", userEntity);
        return "user/updateForm";
    }

    // @GetMapping("/logout")
    // public String logout() {
    // session.invalidate(); // 세션 무효화 (세션 아이디 영역의 데이터를 삭제)
    // return "redirect:/";
    // }

    // 웹브라우저 : "회원가입 페이지 주세요"
    // 앱 : "회원가입 페이지 주세요"(X)
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request, Model model) {

        return "user/loginForm";
    }

}
