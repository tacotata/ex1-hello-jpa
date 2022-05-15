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

            Address address = new Address("city", "street", "zipcode");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //setter을 없애거나 , private를 사용해서 불변객체로 만들었으면
            //이렇게 새로 만들어서 넣어야함
            //값타입은 다 불변으로 만들어야함
            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);
 //           Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

//            Member member2 = new Member();
//            member2.setUsername("member1");
//            //이렇게 복사한 값을 사용하면 두번째 값에는 영향 없음
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            //member만 바꾸고싶어 오잉 member2도 바껴 뭐냐 부작용
//            //같이 공유하고 싶으면 엔티티로 만들어야함
//            member.getHomeAddress().setCity("newCity");

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




