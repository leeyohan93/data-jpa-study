package study.datajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "team_id")
//    @OrderColumn(name = "position")
    private List<Sponsor> sponsors = new ArrayList<>();

    public Team(String title) {
        this.title = title;
    }

    public Team(final String title, final List<Member> members) {
        this(title, members, new ArrayList<>());
    }

    public Team(final String title, final List<Member> members, final List<Sponsor> sponsors) {
        this.title = title;
        this.members.addAll(members);
        members.forEach(member -> member.join(this));
        this.sponsors.addAll(sponsors);
    }

    public void add(final Member member) {
        member.join(this);
        this.members.add(member);
    }

    public void add(final Sponsor sponsor) {
        this.sponsors.add(sponsor);
    }
}
