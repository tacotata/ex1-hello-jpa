package helloJpa;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //cascade 사용 언제?
    //1.라이프사이클이 유사할때
    //2.단일 소유자
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    //양방향 연관관계 걸려려려ㅕ>?
    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }

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
}
