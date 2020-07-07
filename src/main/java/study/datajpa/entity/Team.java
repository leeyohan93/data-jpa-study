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

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Member> members = new ArrayList<>();

    public Team(String title) {
        this.title = title;
    }

    public void add(Member member) {
        this.members.add(member);
    }
}
