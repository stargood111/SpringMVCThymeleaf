package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository){
//        this.repository = itemRepository;
//    } @RequiredArgsConstructor로 기본 생성자 생략가능
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000,20));
    }
    @GetMapping("/{itemId}")
    /*@PathVariable("itemId") 스프링 3.2부턴 변수명 PathVariable어노테이션에 정확한 파라미터 변수명 붙여줘야함*/
    public String item(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

}
