package helloJpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends  BaseEntity {

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;
    //member 입장에서 many고 team은 one 관계뭔지
    //조인하는 컬럼뭐야
    //연관관계 주인

    @ManyToOne
    @JoinColumn(name ="TEAM_ID",insertable=false, updatable=false)
    private Team team;
//    @Column(name="TEAM_ID")
//    private Long teamId;

    @OneToOne
    @JoinColumn(name ="LOCKER_ID")
    private Locker locker ;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }
//연관관계 여러개면 문제를 일으킬 수 있다 한쪽은 지워주세요
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
