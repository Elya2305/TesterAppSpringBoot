package com.company.brainstorm.controllers;

import com.company.brainstorm.services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JsonController {
    private static final String URL = "https://lip2.xyz/api/millionaire.php?q=2";
    private static final String URL2 = "https://gturnquist-quoters.cfapps.io/api/random";

    @Autowired
    private ParsingService parsingService;

    @GetMapping(value = "/json")
    public String getQvs(Model model){
        System.out.println(parsingService.parse(URL));
        return "test";
    }

}
