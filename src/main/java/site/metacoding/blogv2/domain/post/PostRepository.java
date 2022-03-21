package site.metacoding.blogv2.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

// id를 Long으로 만들었다면 <Post, Long>
public interface PostRepository extends JpaRepository<Post, Integer> {

}
