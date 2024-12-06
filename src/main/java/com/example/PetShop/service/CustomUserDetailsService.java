package com.example.PetShop.service;

import com.example.PetShop.models.UserModel;
import com.example.PetShop.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Inject UserRepository vào CustomUserDetailsService
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm người dùng dựa trên username
        UserModel user = userRepository.findByUsername(username);

        // Trả về đối tượng UserDetails (do Spring Security cung cấp)
        return new User(
                user.getUsername(), // Tên đăng nhập
                user.getPassword(), // Mật khẩu đã mã hóa
                new ArrayList<>()   // Danh sách quyền hạn (authorities), hiện tại để trống
        );
    }
}

