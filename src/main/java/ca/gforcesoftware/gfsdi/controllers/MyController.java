package ca.gforcesoftware.gfsdi.controllers;

import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-01
 */
@Controller
public class MyController {

    public String sayHello(){
        System.out.println("Hello World");

        return "Hello Gargamel";
    }
}
