package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import study.datajpa.entity.Member;
import study.datajpa.entity.MemberDetail;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDetailRepository memberDetailRepository;

    @Autowired
    EntityManager em;

    @Test
    public void testMember() {
        Member member = new Member("yohan");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new IllegalArgumentException("id 불일치"));

        assertThat(findMember.getId()).isEqualTo(member.getId());
        System.out.println(findMember.getId() + findMember.getUsername());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    void 일대일관계테스트_DETAIL_나중생성() {
        Member member = new Member("test");
        Member savedMember = memberRepository.save(member);

        MemberDetail memberDetail = new MemberDetail(savedMember, "01012345678", "abcd@abcd.com");
        MemberDetail savedMemberDetail = memberDetailRepository.save(memberDetail);

        // 1. 위의 save 후 flush 가 일어나지 않았는데 select 쿼리가 찍히는 이유. DB접근 후 값이 없으니 영속성컨텍스트에서 찾아온 것일까?
        MemberDetail searchedMemberDetail = memberDetailRepository.findByMember(savedMember);

        assertThat(searchedMemberDetail.getDetailId()).isEqualTo(savedMemberDetail.getDetailId());
    }

    @Test
    void 일대일관계테스트_DETAIL_먼저생성() {
        MemberDetail memberDetail = new MemberDetail("01000000000", "efgefg@abcd.com");
//        em.persist(memberDetail);
        Member member = new Member("test");
        member.setMemberDetail(memberDetail);
        Member savedMember = memberRepository.save(member);
    }

}