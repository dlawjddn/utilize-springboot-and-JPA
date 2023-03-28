package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){ // 변경감지

        // 변경 감지를 이용해서 따로 저장을 하거나 머지를 할 필요가 없음
        // itemRepository.findOne()을 사용해서 영속성 컨텍스트를 가져와서 JPA가 그 컨텍스트에 대한 변경감지를 하고 업데이트 쿼리를 날리기 때문이다.
        //더 좋은 설계
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);


    }
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
    public List<Item> findItems(){
         return itemRepository.findAll();
    }
}
