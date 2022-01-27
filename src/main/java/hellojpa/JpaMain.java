package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.time.LocalDateTime;
import java.util.List;

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

            Movie movie = new Movie();
            movie.setDirector("A");
            movie.setActor("BBB");
            movie.setName("바함사");
            movie.setPrice(10000);
            em.persist(movie);
            em.flush();

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = "+ findMovie );

            Member member = new Member();
            member.setUsername("A");
//            member.setCreatedBy("B");
//            member.setCreatedDate(LocalDateTime.now());
            Member member2 = new Member();
            member2.setUsername("B");

            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member findMember2 = em.find(Member.class, member2.getId());

            // 프록시객체 : 실제 클래스와 겉모양이 같음
            Member findMember = em.getReference(Member.class, member.getId()); // 홀로는 쿼리 안날림


            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.getId = " + findMember.getId());

            //Hibernate.initialize(findMember); 강제 초기화 가능

            // 쓰일 때 쿼리날림 (초기화)
            System.out.println("findMember.getUsername = " + findMember.getUsername());
            // 초기화 되서 초기화 요청 안함
            System.out.println("findMember.getUsername = " + findMember.getUsername());

//            // == 은 모든것을 다 비교
//            System.out.println("m1 == m2 :" + (findMember.getClass() == findMember2.getClass()));
//
//            // 타입 비교시에는 instancOf
//            System.out.println("m1 class :" + (findMember instanceof Member ) );
//            System.out.println("m2 class :" + (findMember2 instanceof Member ) );




            System.out.println("==========");

            tx.commit(); // DB에 쿼리가 날라가는 순간
        }catch (Exception e){
            //e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}