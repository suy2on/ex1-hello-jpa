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
            Member member = new Member();
            member.setUsername("hi");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
            em.persist(member);


            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            // CASCADE : 연관된 엔티티 같이 영속화 (하나의 객체와 연관있을 때만 사용)
//            em.persist(child1);
//            em.persist(child2);
            em.flush();
            em.clear();

            // orphanRemoval : 하나만 연관되어있을 때만 사용 , 고아가 된 자식객체 삭제
            // CASCADE : 부모가 삭제되면 같이 삭제 , 관계가 끊어지는 것으로는 삭제 안된다
            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);



//            Member member = new Member();
//            member.setUsername("a");
//
//
//            Team team = new Team();
//            team.setName("b");
//            member.setTeam(team);
//
//
//
//            em.persist(member);
//            em.persist(team);
//
//
//
//            em.flush();
//            em.clear();

            //jpa 촤적화 -> 조인
//            Member findMember = em.find(Member.class, member.getId()); // team이랑 조인하지않음 / 즉시로딩이면 조인하고 team select은 안날림
//            System.out.println("member = " + findMember.getClass()); // 멤버객체
//            System.out.println("team = " + findMember.getTeam().getClass()); // 프록시객체
//            System.out.println("team = " + findMember.getTeam().getName()); // 초기화하면서 select문날림

//
//            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//            System.out.println(members); // 즉시면 member 날리고 팀날림  / 지연이면 member만 날림



//            System.out.println("===========");
//            findMember.getTeam().getName(); // 초기화
//            System.out.println("===========");





//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = "+ findMovie );

//            Member member = new Member();
//            member.setUsername("A");
//            member.setCreatedBy("B");
//            member.setCreatedDate(LocalDateTime.now());
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            em.persist(member);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();

//            Member findMember2 = em.find(Member.class, member2.getId());

            // 프록시객체 : 실제 클래스와 겉모양이 같음
//            Member findMember = em.getReference(Member.class, member.getId()); // 홀로는 쿼리 안날림


//            System.out.println("findMember = " + findMember.getClass());
//            System.out.println("findMember.getId = " + findMember.getId());
//
//            //Hibernate.initialize(findMember); 강제 초기화 가능
//
//            // 쓰일 때 쿼리날림 (초기화)
//            System.out.println("findMember.getUsername = " + findMember.getUsername());
//            // 초기화 되서 초기화 요청 안함
//            System.out.println("findMember.getUsername = " + findMember.getUsername());

//            // == 은 모든것을 다 비교
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