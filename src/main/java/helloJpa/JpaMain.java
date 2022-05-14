package helloJpa;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //설정파일의 이름이 hello
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member  member1 = new Member();
            member1.setUsername(("member1"));
            Member  member2 = new Member();
            member2.setUsername(("member2"));

            em.persist(member1);
            em.persist(member2);

            //영속성 컨텍스트 깔끔
            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

           // true
            System.out.println(" m1== m2" +(m1 instanceof Member));

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            //엔티티 매니저 꼭 닫아줘야함
            em.close();
        }
        emf.close();
    }


}




