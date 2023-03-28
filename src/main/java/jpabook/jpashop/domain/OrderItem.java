package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // 연관관계의 주인임을 명시
    private Order order;
    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //===생성 메서드===//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //===비즈니스 로직===//
    public void cancel() {
        getItem().addStock(count);
    }
    //===조회 로직===//
    public int getTotalPrice() { // 수량과 가격이 따로 있기 때문에 둘을 곱해야한다.
        return getOrderPrice()*getCount();
    }
}
