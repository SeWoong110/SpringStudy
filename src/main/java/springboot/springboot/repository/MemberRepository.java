package springboot.springboot.repository;

import springboot.springboot.domain.Member;

import java.util.List;
import java.util.Optional;

// 저장소가 아직 정해지지 않았다는 가정이기 때문에 interface를 통해 리포지토리를 만든다.
public interface MemberRepository {
    Member save(Member member);
    // Optional은 null이 반환될 수 있음을 감안해 한번 감싸서 보내는 것.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
