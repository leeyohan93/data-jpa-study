package study.datajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberDetail {

    @Id
    @GeneratedValue
    private Long detailId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private Member member;

    private String userPhone;

    private String userEmail;

    public MemberDetail(String userPhone, String userEmail) {
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public MemberDetail(Member member, String userPhone, String userEmail) {
        this.member = member;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
