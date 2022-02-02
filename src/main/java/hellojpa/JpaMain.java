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