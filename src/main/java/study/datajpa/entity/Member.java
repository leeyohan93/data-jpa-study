package study.datajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

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
}
