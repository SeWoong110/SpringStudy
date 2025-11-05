package springboot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    // @ResponsBody는 html 응답 바디에 리턴값을 바로 넣어주겠다는 애너테이션임
    // 이 전처럼 html파일이 아닌 순수 문자열이 전달됨
    // 이렇게 문자만 전달하는 방식은 거의 안씀
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        return "hello " + name; // "hello string"
    }

    @GetMapping("hello-api")
    // viewResolver가 아니라 HttpMessageConverter가 동작
    // 여기서 문자인지 객체인지에 따라 StringConverter or JsonConverter가 동작함
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name) {

        Hello hello = new Hello();
        hello.setName(name);

        // 객체 자체를 리턴
        // json 형식의 파일이 리턴됨
        return hello;
    }

    // getter/setter
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
