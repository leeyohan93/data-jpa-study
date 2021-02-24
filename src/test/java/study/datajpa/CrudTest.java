package study.datajpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.datajpa.entity.Member;
import study.datajpa.entity.MemberDetail;
import study.datajpa.repository.MemberDetailRepository;
import study.datajpa.repository.MemberRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
public class CrudTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDetailRepository memberDetailRepository;

    @Test
    @DisplayName("Member save")
    public void saveMember() {
        Member member = new Member("yohan");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new IllegalArgumentException("id 불일치"));

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }

    /**
     * OneToOne 관계 설정에서 cascade = CascadeType.PERSIST 를 통해 Member를 저장하면서 MemberDetail 저장
     * (양방향일 경우 연관관계의 주인쪽에 데이터가 빠지지 않도록 조심해야한다)
     * CascadeType.PERSIST 는 update 시 까지는 영향을 끼치지 않는다. (CascadeType.MERGE, CascadeType.REFRESH 필요)
     * CascadeType.ALL 권장 (실제 데이터를 REMOVE 할때는 조심해야한다)
     * <p>
     * 기타 QA
     * Q. 60 line save 후 flush 가 일어나지 않았는데 61 line select 쿼리가 찍히는 이유(DB접근 후 값이 없으니 영속성컨텍스트에서 찾아온 것일까)?
     * A. JPA에서 em.find()를 제외한 모든 호출은 JPQL을 통한 호출입니다. JPQL을 통한 모든 호출은 우선 데이터베이스를 조회합니다.
     * em.find()는 정확하게 엔티티 ID로 엔티티를 찾을 수 있기 때문에 영속성 컨텍스트만 조회하면 쉽게 결과를 얻을 수 있습니다.
     * 그런데 JPQL은 복잡한 쿼리이기 때문에 기술적으로 이 JPQL을 번역해서 영속성 컨테스트를 조회하는 것은 한계가 있습니다.
     * 그래서 우선 JPQL을 SQL로 변환한 다음 데이터베이스를 먼저 조회합니다.
     */
    @Test
    @DisplayName("Member : MemberDetail 일대일 관계 , Member 생성 후 MemberDetail 생성")
    void memberDetailSaveAfterMemberSave() {
//        Member member = new Member("test");
//        MemberDetail memberDetail = new MemberDetail("01012345678", "abcd@abcd.com");
//        member.setMemberDetail(memberDetail);
//        Member savedMember = memberRepository.save(member);
//        MemberDetail searchedMemberDetail = memberDetailRepository.findByMember(savedMember);
//
//        assertThat(savedMember.getMemberDetail().getDetailId()).isEqualTo(searchedMemberDetail.getDetailId());
    }

    /**
     * MemberDetail 로 영속성을 전파하여 save 시 Member -> MemberDetail 순으로 저장한다.
     * 결국 Member를 통한 MemberDetail의 저장 방식이 플로우 상 맞으며 더 자연스러워 보인다.
     */
    @Test
    void memberSaveAfterMemberDetailSave() {
        MemberDetail memberDetail = new MemberDetail("01000000000", "efgefg@abcd.com");
        Member member = new Member("test2");
        memberDetail.setMember(member);

        MemberDetail savedMemberDetail = memberDetailRepository.save(memberDetail);

        assertThat(savedMemberDetail.getMember().getUsername()).isEqualTo(member.getUsername());
    }
}
