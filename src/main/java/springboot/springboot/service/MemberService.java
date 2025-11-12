package springboot.springboot.service;

import springboot.springboot.domain.Member;
import springboot.springboot.repository.MemberRepository;
import springboot.springboot.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    // 같은 이름의 회원은 안된다는 규칙이 있다는 가정
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복회원 검증 메서드
        memberRepository.save(member);
        return member.getId();
    }

    // 이런 경우에는 메서드로 뽑는 것이 좋다.
    private void validateDuplicateMember(Member member) {
        // Optional이라서 값이 있으면 로직이 실행되도록 하는 코드.
        // orElseGet는 같이 있으면 꺼내고 없으면 메서드를 실행하도록 하게 함.
        // Optional를 반환해서 사용하는건 많이 안쓰고 이런식으로 많이 사용함.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
