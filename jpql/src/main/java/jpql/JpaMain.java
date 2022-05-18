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
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

          //  member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            //String query  = "select 'a' || 'b' From Member m ";
            //String query  = "select substring(m.username, 2, 3) From Member m ";
            //s = 4
            //String query  = "select locate('de','abcdef') From Member m ";
            //컬렉션의 크기를 돌려줌 s=0
            //String query  = "select size(t.members) From Team t";
            //추천안함 INDEX

            String query  = "select size(t.members) From Team t";
            List<Integer> result =  em.createQuery(query, Integer.class)
            .getResultList();


            for (Integer s : result) {
                System.out.println("s = " + s);

            }

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




