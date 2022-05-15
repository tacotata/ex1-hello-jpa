package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends  BaseEntity{

    @Id
    @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;
    //이게 관례  ArrayList<>() 초기화하는거 add 할 때 nullpoint 안뜨니가요
//    @OneToMany(mappedBy ="team")
//    //나의 team으로 매핑되어 있는 아이야
//    //mappedBy  이거 조회만 ! 뭔가 바꾸고 그런거 아님
//    private List<Member> members = new ArrayList<>();
//
//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Member> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<Member> members) {
//        this.members = members;
//    }

}
