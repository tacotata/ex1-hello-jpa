package helloJpa;

import org.hibernate.Hibernate;

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

            em.persist(member1);


            //영속성 컨텍스트 깔끔
            em.flush();
            em.clear();

            //refMember = class helloJpa.Member$HibernateProxy$umY121eh
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            //준영속성 상태
            //org.hibernate.proxy.AbstractLazyInitializer.initialize
            // 아래의 이런걸 만난순간 could not initialize proxy [helloJpa.Member#1] - no Session
            em.detach(refMember);
            //em.close();
            //em.clear();
            System.out.println("refMember.getUsername() = " + refMember.getUsername());

            //refMember.getUsername();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            //엔티티 매니저 꼭 닫아줘야함
            em.close();
        }
        emf.close();
    }


}
