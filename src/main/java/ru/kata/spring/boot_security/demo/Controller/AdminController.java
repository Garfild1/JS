package ru.kata.spring.boot_security.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.Service.UserServiceImp;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById (@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/user/add")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {

        log.warn("add /////" + user);

        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<HttpStatus> updateUser (@PathVariable("id") Long id, @RequestBody @Valid User user) {

        log.warn("update /////" + user);



        userService.update(id,user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping ("/user/{id}")
    public ResponseEntity<HttpStatus>  deleteUser ( @PathVariable ("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
