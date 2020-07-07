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

    private String alias;

    @OneToOne(mappedBy = "member", cascade = CascadeType.PERSIST)
    private MemberDetail memberDetail;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, null, null);
    }

    public Member(String username, MemberDetail memberDetail, Team team) {
        this.username = username;
        this.memberDetail = memberDetail;
        this.team = team;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
        memberDetail.setMember(this);
    }

    public void settingTeam(Team team) {
        if (Objects.nonNull(this.team)) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.add(this);
    }
}
