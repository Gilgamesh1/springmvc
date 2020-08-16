package guru.springframework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping
public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
