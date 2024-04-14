package ru.kata.spring.boot_security.demo.Service;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional ()
@Slf4j
public class UserServiceImp implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final RoleService roleService;
    @Autowired
    public UserServiceImp(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        Gson gson = new Gson();
        for (User thisUser : users){
            log.info(gson.toJson(thisUser));
        }
        return users;
    }
    @Override
    public User getUserById (Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        //log.info(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        log.info(user.toString());

        User getUser = getUserById(id);

        getUser.setName(user.getName());

        Set<Role> editRoles = new HashSet<>();

        for (Role thisRole : user.getRoles()){
            editRoles.add(roleService.getById(thisRole.getId()));
        }

        getUser.setRoles(editRoles);
        getUser.setLastName(user.getLastName());
        getUser.setEmail(user.getEmail());
        getUser.setUsername(user.getUsername());

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            getUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(getUser);
    }


}
