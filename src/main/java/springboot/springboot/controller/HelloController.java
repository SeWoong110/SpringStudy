package springboot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("hello-mvc")
    // @RequestParam을 사용하면 url?name=~~ 이런 식으로 파라미터를 수 있음
    // required라는 옵션이 있는데 true가 디폴트기 때문에 이를 유념해야 함
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {

        model.addAttribute("name", name);

        return "hello-template";
    }
}
