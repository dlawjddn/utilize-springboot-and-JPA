package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded // 내장타입이 포함됨
    private Address address;
    @OneToMany(mappedBy = "member") // member 기준으로 orders는 일대다 관계이기 때문
    private List<Order> orders = new ArrayList<>();

}
