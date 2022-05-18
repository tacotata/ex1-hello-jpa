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
//            team.setName("teamA");
           em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            //============묵시적 조인 사용하지마 명시적 조인 사용해============


            //일 대 다 관계 어떤거 선택해서 꺼냄?
            //컬렉션 연관관계 컬렉션에서 사용할 ㅅ 있는거 size밖에 없다 t.member.size
            //result = 2
            // Collection result =  em.createQuery(query, Collection.class) 이렇게는 안씀
            //탐색이 안됨 그니까 명시적 조인을 사용해야함 명시적 조인에서 별칭을 얻어서 원하는걸 가져오면 됨
            // String query  = "select m.username From Team t join t.members m ";
            String query  = "select t.members.size From Team t ";
            Integer result =  em.createQuery(query, Integer.class)
            .getSingleResult();

            System.out.println("result = " + result);

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




