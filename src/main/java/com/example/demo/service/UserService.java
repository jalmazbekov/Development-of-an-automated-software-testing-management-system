package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveTeacher(String username, String password, String fullName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);

        Role teacherRole = roleRepository.findByName("TEACHER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("TEACHER");
                    return roleRepository.save(role);
                });

        user.setRoles(Arrays.asList(teacherRole));
        userRepository.save(user);
    }

    public void saveStudent(String username, String password, String fullName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);

        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("STUDENT");
                    return roleRepository.save(role);
                });

        user.setRoles(Arrays.asList(studentRole));
        userRepository.save(user);
    }
}