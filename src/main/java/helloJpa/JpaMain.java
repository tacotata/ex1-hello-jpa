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

            em.persist(member1);


            //영속성 컨텍스트 깔끔
            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());

            //m1.getClass() = class helloJpa.Member
            //reference.getClass() = class helloJpa.Member
            Member reference = em.getReference(Member.class, member1.getId());
            //1. 1차 캐시에 있는데 굳이 프록시로 가져올 필요 있냐 성능최적화입장에서 원본가져오는게 나음
            //2. == 비교를 true로 만들어주기 위해서?
            System.out.println("reference.getClass() = " + reference.getClass());
            //이건 항상 true가 되야함 한 영속성안에서 pk가 같으면 true를 반환해줘야함 기본 매커니즘
            //한 트랙지션에서  같은걸 보장해줌 프록시를 가져오더라도 타입이 안맞으면 false로 나오니까
            //== 비교할 때 true를 만들어주기위해서 영속성 컨텍스트에 있으면 실제를 반환 true를 보장해줌
            System.out.println("a == a : "+ (m1 ==reference));

//
//            //refMember = class helloJpa.Member$HibernateProxy$umY121eh
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass());
//
//            //findMember = class helloJpa.Member$HibernateProxy$umY121eh
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass());
//
//            // 참을 반환 하기 위해서  find도 프록시를 가져옴
//            System.out.println("refMember == findMember : "+ (refMember ==findMember));



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




