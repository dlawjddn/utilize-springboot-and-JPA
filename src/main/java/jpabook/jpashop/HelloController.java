package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // hello라는 URL로 들어오면 이 Controller를 매칭하겠다
    //model => 뷰에 데이터를 실어서 보내는 역할,  addAttribute(키, 밸류)
    public String hello(Model model){
        model.addAttribute("data","hello!!!");
        return "hello"; // return "hello" => return "hello.html"
    }
}
