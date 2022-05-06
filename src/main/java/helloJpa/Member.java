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

    public void setTeam(Team team) {
        this.team = team;
    }
}
