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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // 알아서 pk 를 FK로 쓴다
            em.persist(member);

//          team.getMembers().add(member); // flush, clear 안하고 관계 조회할때는 꼭필요 + 객체지향적 설계
//
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members){
                System.out.println("m = " + m.getUsername());
            }

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