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
    @GeneratedValue
    private Long id;

    private String username;

    @OneToOne(mappedBy = "member", cascade = CascadeType.PERSIST)
    private MemberDetail memberDetail;

    public Member(String username) {
        this.username = username;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
        memberDetail.setMember(this);
    }
}
