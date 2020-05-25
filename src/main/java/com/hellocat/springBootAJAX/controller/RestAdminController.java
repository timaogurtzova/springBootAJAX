package com.hellocat.springBootAJAX.controller;

import com.hellocat.springBootAJAX.domen.Role;
import com.hellocat.springBootAJAX.domen.User;
import com.hellocat.springBootAJAX.service.RoleService;
import com.hellocat.springBootAJAX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/*")
public class RestAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllRoles", method = RequestMethod.POST)
    public ResponseEntity<List<Role>> getRoleList() {
        return new ResponseEntity<List<Role>>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @RequestMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
        }catch (Exception e){
            //bad girl
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        }catch (Exception e){
            //bad girl too
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
