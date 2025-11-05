package springboot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // 매핑되는 주소
    @GetMapping("hello")
    public String hello(Model model) {

        // model이라는 객체에 감싸서 보낸다고 생각하기
        // data가 이름이고 Hello!!가 실제 값
        // viewResolver가 찾아서 처리해줌
        model.addAttribute("data", "Hello!!");

        // 랜더링할 파일
        return "hello";
    }
}
