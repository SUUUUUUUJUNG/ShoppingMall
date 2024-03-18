package com.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InquiryController {

    @GetMapping("/inquiry")
    public String inquiry(){
        return "member/Inquiry";
    }

}
