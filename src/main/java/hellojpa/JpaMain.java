package hellojpa;

import javax.persistence.*;
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

//            Movie movie = new Movie();
//            movie.setDirector("A");
//            movie.setActor("BBB");
//            movie.setName("바함사");
//            movie.setPrice(10000);

            Member member = new Member();
            member.setUsername("A");
            member.setCreatedBy("B");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = "+ findMovie );

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