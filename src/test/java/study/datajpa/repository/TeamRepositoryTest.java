package study.datajpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.datajpa.entity.Member;
import study.datajpa.entity.Sponsor;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("다대일 양방향 연관계에서 일에서 다의 요소를 추가할 때 select 가 발생하는가")
    @Test
    void 다대일_양방향_연관관계_요소추가() {
        // given
        Team service = new Team("서비스팀");
        service.add(new Member("이요한"));
        service.add(new Member("홍길동"));
        teamRepository.save(service);
        em.flush();
        em.clear();

        // when
        Team persistedTeam = teamRepository.findById(1L).get();
        Member guest = new Member("신입개발자");
        persistedTeam.add(guest);

        // then
        em.flush();
    }

    @DisplayName("일대다 단방향 연관관계에서 일에서 다의 요소를 추가할 때 select 가 발생하는가")
    @Test
    void 일대다_단방향_연관관계_요소추가() {
        // given
        Team service = new Team("서비스팀");
        service.add(new Sponsor("배달의민족"));
        service.add(new Sponsor("NEXTSTEP"));
        teamRepository.save(service);
        em.flush();
        em.clear();

        // when
        Team persistedTeam = teamRepository.findById(1L).get();
        persistedTeam.add(new Sponsor("우아한형제들"));       // select 쿼리 발생 !?

        // then
        em.flush();
    }
}
