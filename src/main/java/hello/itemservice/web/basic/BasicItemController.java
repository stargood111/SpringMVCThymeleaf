package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
//    @PostMapping("/add")
//    public String save (@RequestParam("itemName") String itemName, /*스프링 3.2부턴 어노테이션에 변수명 정획히 붙여줘야함*/
//                        @RequestParam("price") int price,
//                        @RequestParam("quantity") int quantity,
//                        Model model){
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item",item);
//
//        return "basic/item";
//    }
//    @PostMapping("/add")
//    public String saveItemV1(@ModelAttribute("item") Item item, Model model){
//
//
//        itemRepository.save(item);
//        model.addAttribute("item",item);
//
//
//        return "basic/item";
//    }
//    @PostMapping("/add")
//    public String saveItemV2(@ModelAttribute("item") Item item){
//
//        itemRepository.save(item);
////        model.addAttribute("item",item); /*@ModelAttribute사용시 생략가능 */
//
//        return "basic/item";
//    }
//    @PostMapping("/add")
//    public String saveItemV3(@ModelAttribute Item item){
//
//        itemRepository.save(item);
//
//        return "basic/item";

    @PostMapping("/add")
    public String saveItemV4(Item item){

        itemRepository.save(item);

        return "basic/item";
    }

}
