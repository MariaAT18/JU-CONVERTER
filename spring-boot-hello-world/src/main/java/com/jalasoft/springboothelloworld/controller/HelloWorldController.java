/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package com.jalasoft.springboothelloworld.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
  private String message = "Hello World";
  
    @GetMapping("/hello-world")
    public String HelloWorld(){
      return message;
    }
    /*@PostMapping(){}
    @DeleteMapping(){}
    @PutMapping(){}*/
    
}
