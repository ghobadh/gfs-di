package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gavinhashemi on 2024-10-01
 */
class PropertyInjectControllerTest {

    PropertyInjectController controller;

    @BeforeEach
    void setUp() {

        // This is mimicking the framework do, but we did it manually
        controller = new PropertyInjectController();

        controller.greetingService = new GreetingServiceImpl();
    }

    @Test
    void getGreeting(){
        System.out.println(controller.getGreeting());
    }

}