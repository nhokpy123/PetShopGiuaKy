package com.example.PetShop.repository;

import com.example.PetShop.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username); // Tìm user theo username
}
