package helloJpa;

import javax.persistence.*;
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
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "zipcode"));

            member.getFavoriteFoods().add("타코");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressesHistory().add(new AddressEntity("old1", "street", "zipcode"));
            member.getAddressesHistory().add(new AddressEntity("old2", "street", "zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========START===============");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity  이 코드는 안됨 값타입은 불변해야함
            //findMember.getHomeAddress().setCity("newCity");

            //새로 넣어야함  address 인스턴스 자체를 갈아끼워야함
            //값타입 안에 필드 하나만 바꾼다 아예 교체를 해줘야함
           // Address a = findMember.getHomeAddress();
            //findMember.setHomeAddress(new Address("newCity",a.getStreet(), a.getZipcode()));


            //값타입 컬렉션 업데이트
            //타코 -> 한식
            //findMember.getFavoriteFoods().remove("타코");
            //findMember.getFavoriteFoods().add("한식");

            //멤버에 의존관계를 다 맡김 멤버만 라이프사이클 관리되는거임


            //equls로 됨 그래서 hashcode 제대로 해야함
            //기대한대로 값은 들어갔으나 address통으로 삭제하고 insert 2번함 왜?
            //전체 삭제하고 남아있는 데이터 다시 insert함
            // findMember.getAddressesHistory().remove(new Address("old1", "street", "zipcode"));
            // findMember.getAddressesHistory().remove(new Address("newCity1", "street", "zipcode"));

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




