package com.example.PetShop.controller;


import com.example.PetShop.models.LPetModel;
import com.example.PetShop.service.LPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/pets")
public class LPetController {
@Autowired
    private LPetService functionInController;
    public LPetController() {}

//danh sach' thu cung
@GetMapping("danhsach")
@ResponseBody
    public List<LPetModel> danhSachThuCung() {
    return functionInController.PetList();
}
//them thu cung
    @PostMapping("themthucung")
    @ResponseBody
    public ResponseEntity<LPetModel> themThuCung(LPetModel newPet){
    functionInController.addPet(newPet);
    return new ResponseEntity<>(newPet, HttpStatus.OK);
    }
    //xoa thu cung theo id
    @DeleteMapping("xoa/{id}")
    @ResponseBody
    public ResponseEntity<LPetModel> xoaThuCung(@PathVariable("id") Long inPutId){
    functionInController.deletePet(inPutId);
    return new ResponseEntity<>(null, HttpStatus.OK);
    }

     //cap nhat. gia' thu cung
    @PutMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<LPetModel> capnhatPet(@PathVariable("id") Long inPutId, @RequestBody LPetModel updateUser){
        LPetModel pet = functionInController.getPetById(inPutId);
        if (pet != null) {
            pet.setGia(updateUser.getGia());
            pet.setMau(updateUser.getMau());
            functionInController.updatePet(pet);
            return ResponseEntity.status(200).body(pet);
        }
        return ResponseEntity.status(404).body(null);
    }
}

