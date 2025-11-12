package springboot.springboot.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import springboot.springboot.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 각 테스트 메서드가 실행이 되고 난 후에 자동으로 실행되는 메서드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // member객체를 "test"라는 이름으로 만든다.
        Member member = new Member();
        member.setName("test");

        // 이를 저장한다.
        repository.save(member);

        // 저장한 이름을 통해 찾아서 따로 저장한다.
        Member result = repository.findByName(member.getName()).get();

        // member가 기대하는 값이고, result가 우리가 넣은 값이다.
        // 이 둘이 같으면 test가 정상적으로 진행된다.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        Member result = repository.findByName("test1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

        // 테스트를 하는건 순서에 의존해선 안된다.
        // 순서에 상관없이 모든 메서드가 잘 실행되어야만 한다.
        // 다같이 돌렸을 때 findByName 테스트가 오류가 생기는건 findAll이 먼저 실행되고 그 안의 객체가 findByName에서 사용되기 때문이다.
    }
}
