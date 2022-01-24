package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // JPA가 인식
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {
    @Id // 직접할당
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 pk설정 위임, DB에 들어갈때 pk결정 따라서 
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne // 다대일(member관점)
    @JoinColumn(name= "TEAM_ID") // FK는 뭔지
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany //다대다
//    @JoinTable(name = "MEMBER_PRODUCT") // 중개테이블
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

//    private Integer age;
//
//    @Enumerated(EnumType.STRING) // enum대신 : STRING써야 순서에 제약받지 않음
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP) // Time, Date, TimeStamp
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob // 큰 컨텐츠
//    private String description;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 연관관계 편의 메서드
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
