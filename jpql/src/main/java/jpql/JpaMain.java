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
            // 멤버 조인하긴 하는데  조인해가지고 한번에 팀 끌고와! 프록시 아니라 실제 데이터
           // String query  = "select  m From Member m  join fetch m.team ";
            //컬렉션 페치 조인
            //디비에서 일대다는 데이터 뻥튀기함 팀A|2 팀A|2 팀B|1
            //String query  = "select t From Team t  join fetch t.members ";
            //중복제거  sql은 distinct 은 완전히 똑같아야만  데이터 줄어짐
            //jpa가 걸러줌
            String query  = "select distinct t From Team t  join fetch t.members ";
            //일반조인은 쿼리에 join문은 있는데 select에 team밖에 없음 데이터 올리는건 team에 대한것만 있음
            //컬렉션이 로딩시점에 로딩이 안됐음
            //String query  = "select  t From Team t  join  t.members m ";
            List<Team> result =  em.createQuery(query, Team.class)
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




