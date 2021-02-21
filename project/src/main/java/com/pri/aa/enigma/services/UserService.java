package com.pri.aa.enigma.services;

import com.pri.aa.enigma.exceptions.NonCorrectUserDataException;
import com.pri.aa.enigma.models.Role;
import com.pri.aa.enigma.models.User;
import com.pri.aa.enigma.repositories.RoleRepository;
import com.pri.aa.enigma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName("ROLE_USER"));
        userRepository.save(user);
        return true;
    }

    public void update(User user) {
        String pass = user.getPassword();
        String passConfirm = user.getPasswordConfirm();

        if (!StringUtils.isEmpty(pass) && !StringUtils.isEmpty(passConfirm) &&
                pass.equals(passConfirm)) {
            user.setPassword(passwordEncoder.encode(pass));
        }

        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}