package ca.gforcesoftware.gfsdi.controllers;


import com.gargamel.pets.PetService;
import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-06
 */
@Controller
public class PetServiceController {
    private final PetService petService;

    public PetServiceController(PetService petService) {
        this.petService = petService;
    }

    public String getPetInfo() {
        return petService.getName();
    }
}
