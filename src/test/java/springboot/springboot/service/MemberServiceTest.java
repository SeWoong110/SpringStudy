package springboot.springboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springboot.springboot.domain.Member;
import springboot.springboot.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // 테스트 시작하기 전 각각에 새로운 리포지토리를 생성 후 주입.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given (무언가가 주어지고)
        Member member = new Member();
        member.setName("hello");

        // when (그 무언가로 어떤걸 실행했을 때)
        Long saveId = memberService.join(member);

        // then (결과가 이렇게 나와야 한다.)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // try-catch문을 사용해도 되지만 이 예외 검증을 위해서는 assertThrows()를 사용하는 것이 좋다.
        // assertThrows()는 Exception 메세지를 반환해주기 때문에 이를 활용한 검증도 가능하다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}