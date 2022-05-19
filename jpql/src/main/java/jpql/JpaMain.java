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

            Team teamA = new Team();
            teamA.setName("팀A");
             em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

            //페치조인은 이렇게 별칭을 주면 안됨 join fetch를 몇단계로 가져가서 사용할 때
            // 둘 이상의 컬렉션은 페치 조인 할 수 없다.
            // 컬렉션을 페치 조인하면 페이징 API(setFirstResult,setMaxResults)를 사용할 수 없다.
            //
            //일대일, 다대일 같은 단일 값 연관 필드들은 페치 조인해도 페이징 가능
           // String query  = "select  t From Team t  join fetch t.members m ";

            String query  = "select  t From Team t ";

            List<Team> result =  em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            System.out.println("result = " + result.size());

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "|" + team.getMembers().size());
                for(Member member : team.getMembers()){
                    //팀A에 멤버가 두명이라서 두번 나옴 똑같은 값이
                    System.out.println("member = " + member);
                }
                //String query  = "select  m From Member m  "; 쿼리 4번
                //회원1, 팀A(SQL)
                //회원2, 팀A(1차캐시)
                //회원3, 팀B(SQL)

                //회원 100명 -> N + 1
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




