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
            member1.setUsername("member1");
            em.persist(member1);
            //영속성 컨텍스트 깔끔
            em.flush();
            em.clear();

            //refMember = class helloJpa.Member$HibernateProxy$umY121eh
            Member refMember = em.find(Member.class, member1.getId());
            //프록시 클래스 확인방법  refMember.getClass()
            System.out.println("refMember = " + refMember.getClass());
           // refMember.getUsername();//강제 초기화

            //프록시 인스턴스의 초기화 여부 확인 .isLoaded(refMember
            //System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            //강제 초기화 쿼리가 나감
            Hibernate.initialize(refMember);

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
