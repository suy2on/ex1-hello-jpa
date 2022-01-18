package hellojpa;

import javax.persistence.*;
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
//            생성
//             > 비영속
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//             > 영속
//            em.persist(member);
//            > 영속해지
//            em.detach(member);

//            조회
//            Member findMember = em.find(Member.class, 1L);
//            삭제
//            em.remove(findMember);
//            수정
//            findMember.setName("HelloJPA");

//              전체조회 : jpql
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//            for (Member member : result){
//                System.out.println("member.name = " + member.getName());
//            }

            // buffer
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("google");
//
//            em.flush();

            System.out.println("==========");

            tx.commit(); // DB에 쿼리가 날라가는 순간
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}