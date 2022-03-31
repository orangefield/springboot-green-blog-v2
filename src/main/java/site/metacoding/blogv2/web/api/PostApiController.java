package site.metacoding.blogv2.web.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.PostService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.post.WriteDto;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;
    private final HttpSession session;

    @GetMapping("/api/post")
    public ResponseDto<?> list(Integer page) {
        Page<Post> posts = postService.게시글목록(page);
        // 응답의 DTO를 만들어서 <- posts를 옮김 (라이브러리 있음)
        return new ResponseDto<>(1, "성공", posts);
    }

    @PostMapping("/s/post")
    public ResponseDto<?> write(@RequestBody WriteDto writeDto) {

        User principal = (User) session.getAttribute("principal");
        Post post = writeDto.toEntity(principal);
        // 원래는 그냥 dto를 바로 넘겼는데, 지금은 dto를 넘기면 세션값 2개 넘겨야 해서 하나로 합쳐서 넘김
        postService.글쓰기(post);

        return new ResponseDto<>(1, "성공", null);
    }
}
