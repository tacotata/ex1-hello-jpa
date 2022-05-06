package helloJpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;
    @Column(name ="USERNAME")
    private String username;
    //member 입장에서 many고 team은 one 관계뭔지
    //조인하는 컬럼뭐야
    @ManyToOne
    //연관관계 주인
    @JoinColumn(name ="TEAM_ID")
    private Team team;
//    @Column(name="TEAM_ID")
//    private Long teamId;


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
