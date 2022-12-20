package com.crud.crud_api_rest.Resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping
    public String getHello(){
        return "Hello Word!";
    }
    
}
