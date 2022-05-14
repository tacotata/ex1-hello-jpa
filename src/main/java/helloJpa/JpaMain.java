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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member  member1 = new Member();
            member1.setUsername(("member1"));
            em.persist(member1);

            member1.setTeam(team);

            //영속성 컨텍스트 깔끔
            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());

            //member 쿼리만 나오고 team 은 프록시로 가져옴
            //m.getTeam().getClass() = class helloJpa.Team$HibernateProxy$n49B0GJN
            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());

            //실제 사용하는 시점에 쿼리
            System.out.println("===============");
            m.getTeam().getName();//초기화
            System.out.println("===============");

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




