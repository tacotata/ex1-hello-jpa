package helloJpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        //설정파일의 이름이 hello
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            //jpql
//            List<Member> result = em.createQuery(
//                    "select  m From Member m where m.username like '%kim%'",
//                    Member.class).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }

            //Criteria 사용 준비
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//            //장점  1.컴파일 오류 2.동적쿼리
//            //단점 1.sql스럽지 않다. 실무에서 안써요 유지보수가 안되서
//            //그래서 QueryDSL  사용하세요
//            CriteriaQuery<Member> cq = query.select(m);
//            String  username ="sss";
//            if(username != null){
//               cq  = cq.where(cb.equal(m.get("username"), "kim"));
//            }
//
//            List<Member> resultList = em.createQuery(cq).getResultList();

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush 언제 호출되냐면 -> 1.commit될때도 , 2.query가 날라갈때도
            //결과가 0 됨 이럴때는 강제로 em.flush()해줘야함
            //dbconn.excuteQuery("select * from member"); ->이건 jpa랑 관련없어서
            //네이티브 SQL
            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city , street , zipcode, USERNAME from MEMBER", Member.class)
                            .getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
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




