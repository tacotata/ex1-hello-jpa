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

            em.flush();
            em.clear();
            //프로젝션 - 여러 값 조회
            /*1.Query  - 타입을 명시 못하니까 오브젝트로 돌려주는거야
            List resultList =  em.createQuery("select m.username, m.age from Member m")
                            .getResultList();
            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("result[0] = " + result[0]);
            System.out.println("result[0] = " + result[1]);*/

            /*2.제네릭에 선언 Object[] 타입으로 조회
            List<Object[]>  resultList =  em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object[] result = resultList.get(0);
            System.out.println("result[0] = " + result[0]);
            System.out.println("result[0] = " + result[1]);*/

            //3.제일 깔끔 new 명령어로 조회
            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO( m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());


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




