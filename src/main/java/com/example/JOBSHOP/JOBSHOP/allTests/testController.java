package com.example.JOBSHOP.JOBSHOP.allTests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("/hello")
    public String hello(Model model) {
        // Add attributes to the model
        model.addAttribute("name", "Mostafa");
        return "index"; // This will resolve to hello.html in src/main/resources/templates
    }
}
