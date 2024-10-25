package com.example.PetShop.repository;
import com.example.PetShop.models.LPetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LPetRepository extends JpaRepository<LPetModel, Long>{
}
