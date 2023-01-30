package com.LC.LaraCulturaCommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LC.LaraCulturaCommunity.model.Contact;

@Controller
public class HomeController {

    @RequestMapping(value={"", "/", "index"})
    public String displayHomePage(Model model) {
        model.addAttribute("contact", new Contact());
        return "index.html";
    }
    
}
