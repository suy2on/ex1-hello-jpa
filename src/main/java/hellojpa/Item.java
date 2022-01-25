package hellojpa;

import javax.persistence.*;

@Entity
// JOIN : 조인전략, SINGLE_TABLE: 싱글테이블,
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // DTYPE : 조인전략에서는 써줘야함, 단일테이블에서는 알아서 생성
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
