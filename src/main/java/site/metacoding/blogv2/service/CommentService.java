package site.metacoding.blogv2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.comment.Comment;
import site.metacoding.blogv2.domain.comment.CommentRepository;
import site.metacoding.blogv2.domain.post.Post;
import site.metacoding.blogv2.domain.post.PostRepository;
import site.metacoding.blogv2.domain.user.User;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void 댓글삭제(Integer id, User principal) {
        // 세션의 id와 comment의 userId와 비교
        Optional<Comment> commentOp = commentRepository.findById(id);

        if (commentOp.isPresent()) {
            Comment commentEntity = commentOp.get();

            if (principal.getId() != commentEntity.getUser().getId()) {
                throw new RuntimeException("권한이 없습니다");
            }

        } else {
            throw new RuntimeException("해당 댓글이 없습니다");
        }

        commentRepository.deleteById(id);
    }

    @Transactional
    public void 댓글쓰기(Comment comment, Integer postId) {
        Optional<Post> postOp = postRepository.findById(postId);

        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            comment.setPost(postEntity);

        } else {
            throw new RuntimeException("이미 삭제된 게시글입니다");
        }

        commentRepository.save(comment);
    }
}
