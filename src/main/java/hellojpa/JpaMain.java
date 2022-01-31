package hellojpa;


import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //em은 사용하고 바로바로 버려야한다
        EntityManager em = emf.createEntityManager();
        // 모든데이터 변경은 transaction 안에서 실행해야한다
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {


            Member member = new Member();
            member.setUsername("hi");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("회");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("city1", "street1", "zipcode1"));
            member.getAddressHistory().add(new AddressEntity("city2", "street2", "zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("-----------------start-----------------");
            Member findmember = em.find(Member.class, member.getId()); // element collection은 지연로딩

            Address add = member.getHomeAddress(); // 완전히 교체
            findmember.setHomeAddress(new Address("newcity", add.getStreet(), add.getZipcode()));

            //String(기본)값타입 : 단지 set이여서 최적화 되었음
            findmember.getFavoriteFoods().remove("치킨");
            findmember.getFavoriteFoods().add("한식");

            //Address(복합)값타입 -> 지우면 관련 객체 다 지우고 다시 추가
//            findmember.getAddressHistory().remove(new Address("city1", "street1", "zipcode1"));
//            findmember.getAddressHistory().add(new Address("city3", "street3", "zipcode3"));


//            // == 은 참조값을 비교
//            System.out.println("m1 == m2 :" + (findMember.getClass() == findMember2.getClass()));
//
//            // 타입 비교시에는 instancOf
//            System.out.println("m1 class :" + (findMember instanceof Member ) );
//            System.out.println("m2 class :" + (findMember2 instanceof Member ) );




            System.out.println("==========");

            tx.commit(); // DB에 쿼리가 날라가는 순간
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}