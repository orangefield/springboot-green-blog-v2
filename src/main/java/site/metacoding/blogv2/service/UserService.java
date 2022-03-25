package site.metacoding.blogv2.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.domain.user.UserRepository;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@Service // 컴포넌트 스캔시에 IoC 컨테이너에 등록됨 // 트랜잭션 관리하는 오브젝트 (기능의 모임)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinDto joinDto) {
        // save하면 DB에 insert하고 insert된 결과를 다시 return 해줄 수 있다
        userRepository.save(joinDto.toEntity());
    }

    public User 로그인(LoginDto loginDto) {
        // 로그인 처리 쿼리를 JPA에서 제공하지 않으니 직접 만들어야 한다
        User userEntity = userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
        return userEntity;
    }

    public User 회원정보(Integer id) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다.");
        }

    }

}
