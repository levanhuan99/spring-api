package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm người dùng từ cơ sở dữ liệu

        if (username == null || username.isBlank() || username.isEmpty())   {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Chuyển đổi User entity thành UserDetails object
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(password)
//                .roles(user.getRoles().split(","))  // Giả sử các role được lưu dưới dạng chuỗi phân tách bởi dấu phẩy
                .build();
    }
}
