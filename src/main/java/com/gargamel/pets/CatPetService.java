package com.gargamel.pets;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-06
 */
@Profile("cat")
@Service
public class CatPetService implements PetService {

    @Override
    public String getName() {
        return "Hello Kitty";
    }
}
