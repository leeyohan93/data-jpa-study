package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;
import study.datajpa.entity.MemberDetail;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
    MemberDetail findByMember(Member member);
}
