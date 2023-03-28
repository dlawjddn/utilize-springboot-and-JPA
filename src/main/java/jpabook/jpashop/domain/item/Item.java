package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //===비즈니스 로직===//
    // 데이터를 갖고 있는 쪽에 비즈니스 로직을 포함시키는 것이 응집성이 좋아서 객체지향적 관점에서 더 좋다!

    //1. 재고수량 증가
    public void addStock(int quantity){ // 이럴 때 Setter를 사용하는 것이 아니라 비즈니스 로직을 사용해서 값을 변경하는 것이다.
        this.stockQuantity += quantity;
    }
    //2. 재고수량 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) throw new NotEnoughStockException("not enough stock");
        this.stockQuantity=restStock;
    }
}
