package study.datajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@ToString
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
    private List<Sponsor> sponsors = new ArrayList<>();

    public Team(String title) {
        this(title, new ArrayList<>());
    }

    public Team(final String title, final List<Member> members) {
        this(title, members, new ArrayList<>());
    }

    public Team(final String title, final List<Member> members, final List<Sponsor> sponsors) {
        this.title = title;
        this.members.addAll(members);
        this.sponsors.addAll(sponsors);
    }

    public void add(final Member member) {
        this.members.add(member);
    }

    public void add(final Sponsor sponsor) {
        this.sponsors.add(sponsor);
    }

    public boolean contain(final Member member) {
        return members.contains(member);
    }

    public boolean contain(final Sponsor sponsor) {
        return sponsors.contains(sponsor);
    }
}
