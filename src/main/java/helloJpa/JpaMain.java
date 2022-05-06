package helloJpa;

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
            //저장

            Team team = new Team();
            team.setName( "TeamA" );//**
            //team.getMembers().add(member);
            em.persist(team);


            Member member = new Member();
            member.setUsername( "member1" );
            //연관관계 주인에 값을 세팅해야함!!!
            //set 아니고  중요하게 뭔가 하구나 그래서 set 안하고 이름 바꿨음
           //member.changeTeam(team);
            em.persist(member);
            //이런식으로 짜기도 해 둘 중에 정함 멤버기준으로 팀 넣을거야? 팀을 기준으로 멤버를 넣을ㄱ야?
            team.addMember(member);

            //객체 지향적으로 생각하면 양쪽에 값을 다 걸어야함
            //team에도 member에도 다 세팅해야함
            // 테스트 문제랑  , 1차캐시
           // team.getMembers().add(member);//**team.getMembers().add(this); 멤버에 set teamdp만들어줘

//            em.flush();
//            em.clear();


            Team findTeam = em.find(Team.class, team.getId());//1차 캐시
            List<Member> members = findTeam.getMembers();
            System.out.println("=============================");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("=============================");



           /* //조회 연관관계
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }*/

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
