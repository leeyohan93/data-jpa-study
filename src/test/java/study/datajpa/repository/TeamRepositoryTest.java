package study.datajpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.datajpa.entity.Member;
import study.datajpa.entity.Sponsor;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
        Team teamA = new Team("A팀", Arrays.asList(new Member("yohan"), new Member("hmin")));
        teamRepository.save(teamA);
        em.flush();

        // when / then
        Team persistedTeam = teamRepository.findById(teamA.getId()).get();
        Member guest = new Member("guest");
        persistedTeam.add(guest);

        // Q. select 쿼리가 발생할까 ?
        // A. 발생하지 않는다.
        em.flush();
        assertThat(persistedTeam.contain(guest)).isTrue();
    }

    @DisplayName("일대다 단방향 연관관계에서 일에서 다의 요소를 추가할 때 select 가 발생하는가")
    @Test
    void 일대다_단방향_연관관계_요소추가() {
        // given
        Team teamA = new Team("A팀", new ArrayList<>(), Arrays.asList(new Sponsor("SKT"), new Sponsor("KT")));
        teamRepository.save(teamA);
        em.flush();

        // when / then
        Team persistedTeam = teamRepository.findById(teamA.getId()).get();
        Sponsor lg = new Sponsor("LG");
        persistedTeam.add(lg);

        // Q. select 쿼리가 발생할까 ?
        // A. 발생하지 않는다.
        em.flush();
        assertThat(persistedTeam.contain(lg)).isTrue();
    }

}
