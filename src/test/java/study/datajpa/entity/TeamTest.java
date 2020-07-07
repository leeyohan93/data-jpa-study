package study.datajpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.application.TeamService;
import study.datajpa.repository.TeamRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Test
    @DisplayName("Team : Member OneToMany create Test")
    void createOneToManyTest() {
        Team bestTeam = new Team("bestTeam");
        Member pobi = new Member("pobi");
        pobi.settingTeam(bestTeam);
        Member yohan = new Member("yohan");
        yohan.settingTeam(bestTeam);

        Team savedTeam = teamRepository.save(bestTeam);

        assertThat(savedTeam.getMembers()).containsExactly(pobi, yohan);
    }

    @Test
    @DisplayName("Team : Member OneToMany update Test")
    void updateOneToManyTest() {
        List<Member> updatedMembers = teamService.updateTeamMember("bestTeam");

        assertThat(updatedMembers).contains(new Member("newPobi"));
    }
}
