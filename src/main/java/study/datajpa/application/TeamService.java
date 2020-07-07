package study.datajpa.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public List<Member> updateTeamMember(String teamTitle) {
        Team foundedTeam = teamRepository.findFirstByTitle(teamTitle);
        Member newPobi = new Member("newPobi");
        newPobi.settingTeam(foundedTeam);
        List<Member> members = foundedTeam.getMembers();
        return members;
    }
}
