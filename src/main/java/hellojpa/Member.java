package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity // JPA가 인식
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {
    @Id // 직접할당
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 pk설정 위임, DB에 들어갈때 pk결정 따라서 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name") // DB컬럼명은 name
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum대신 : STRING써야 순서에 제약받지 않음
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // Time, Date, TimeStamp
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // 큰 컨텐츠
    private String description;

    public Member() {

    }
}
