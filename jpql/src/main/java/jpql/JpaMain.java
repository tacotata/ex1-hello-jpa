package jpql;

import javax.persistence.*;
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

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            //inner 생략 가능함
            //String query  = "select m from Member m inner join m.team where t" ;
           //outer 생략 가능함
            //String query  = "select m from Member m left join m.team  t" ;
            //세타조인
            //String query  = "select m from Member m, Team t where m.username = t.name" ;
            //조인 대상 필터링
            //String query  = "select m from Member m left join  m.team  t on t.name = 'teamA'" ;
            //연관관계 없는 엔티티 외부 조인
            String query  = "select m from Member m left join  Team t on  m.username = t.name" ;
            //이렇게 하면 파라미터 받을 수 있는거야
            //String query  = "select m from Member m inner join m.team  t where t.name = :teamName" ;
            List<Member> result = em.createQuery(query, Member.class)

                .getResultList();

            System.out.println("result = " + result.size());

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




