package springboot.springboot.repository;

import springboot.springboot.domain.Member;

import java.util.*;

// 리포지토리 구현체
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // sequence는 키값을 생성해주는 변수이다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id는 시스템에서 sequence값을 할당하고 name은 사용자에게 입력받음.
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
