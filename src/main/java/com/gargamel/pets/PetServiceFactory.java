package com.gargamel.pets;

/**
 * @author gavinhashemi on 2024-10-06
 */

public class PetServiceFactory {
    public PetService getPetService(String petType) {
        switch (petType) {
            case "dog":
                return new DogPetService();
            case "cat":
                return new CatPetService();
            default:
                return new DogPetService();


        }

    }
}
