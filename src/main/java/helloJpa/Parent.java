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

    //고아객체 orphanRemoval
    @OneToMany(mappedBy = "parent",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    //양방향 연관관계 걸려려려ㅕ>?
    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
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
