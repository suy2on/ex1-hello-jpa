package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    @ManyToOne(fetch = FetchType.LAZY) // 다대일(member관점) + 프록시객체로 조회
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

    //period
    @Embedded
    private Period period;

    //address
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city",
//                    column = @Column(name = "HOME_CITY")),
//            @AttributeOverride(name="street",
//                    column = @Column(name = "HOME_STREET")),
//            @AttributeOverride(name = "zipcode",
//                    column = @Column(name = "HOME_ZIPCODE"))
//    })
//    private Address workAddress;




    public Member() {

    }

    public void setHomeAddress(Address address){
        this.homeAddress = address;
    }

    public void setPeriod(Period period){
        this.period = period;
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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity>addressHistory) {
        this.addressHistory = addressHistory;
    }
}

