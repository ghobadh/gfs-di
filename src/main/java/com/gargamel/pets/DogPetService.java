package com.gargamel.pets;

/**
 * @author gavinhashemi on 2024-10-06
 */
//@Profile("dog")
//@Service
public class DogPetService implements PetService {

    @Override
    public String getName() {
        return "Hello Dog Pet";
    }
}
