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

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);


            //보통 타입쿼리로 하지않고 체이닝으로 엮음 메서드 체이닝을 활용하도로 설계가 되어있음
            Member result = em.createQuery("select m from Member m where m.username =:username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + result.getUsername());


            //타입 정보 명확함 결과가 하나 이상일 때getResultList
            //결과 없으면 빈 리스트 반환
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query1.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
            //결과가 정확히 하나getSingleResult
            //결과가 없으면: javax.persistence.NoResultException
            //둘 이상이면: javax.persistence.NonUniqueResultException
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id =10", Member.class);
//            Member result = query.getSingleResult();
//            System.out.println("result = " + result);

            //타입 정보 명확함
            //TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);

            //username , age 타입정보 다름 반환 타입 명확하지 않음
           //Query query3 = em.createQuery("select m.username, m.age from Member m");



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




