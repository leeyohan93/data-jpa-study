package study.datajpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(of = "username")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public void settingTeam(Team team) {
        if (Objects.nonNull(this.team)) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.add(this);
    }

    public void join(final Team team) {
        this.team = team;
    }
}
