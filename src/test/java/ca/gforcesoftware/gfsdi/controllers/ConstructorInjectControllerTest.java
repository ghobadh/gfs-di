package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gavinhashemi on 2024-10-01
 */
class ConstructorInjectControllerTest {

    private ConstructorInjectController controller;
    @BeforeEach
    void setUp() {

        // This is exactly mimicking when framework do injecting the bean when constructor is used.
        controller = new ConstructorInjectController(new GreetingServiceImpl());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}