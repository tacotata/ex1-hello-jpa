package helloJpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //양방향 만들고 싶다. 이건 읽기 전용이됨
    @OneToOne(mappedBy ="locker")
    private Member member;


}
