package lt.liudas.sbs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootSecurityController {
    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }
}