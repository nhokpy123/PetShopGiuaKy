package com.example.PetShop.service;


import com.example.PetShop.models.LPetModel;
import com.example.PetShop.repository.LPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LPetService {
@Autowired
    private LPetRepository functionInService;

//pet list
public List<LPetModel> PetList(){
    return functionInService.findAll();
}
//find by id
public LPetModel getPetById(Long id){
    return functionInService.findById(id).orElseThrow(()->new RuntimeException("Pet not found"));
    }
//add pet
    public LPetModel addPet(LPetModel newPet){
    return functionInService.save(newPet);
    }
//delete pet
    public void deletePet(Long pet){
    functionInService.deleteById(pet);
    }
//update pet
    public LPetModel updatePet(LPetModel newPet){
    return functionInService.save(newPet);
    }
}
