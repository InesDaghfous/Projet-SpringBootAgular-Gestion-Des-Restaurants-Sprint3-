package com.ines.restaurants.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.web.bind.annotation.RestController
public class RestController {
	 @GetMapping("/auth")
	    public String getAuth() {
	        return "Security removed";
	    }


}
