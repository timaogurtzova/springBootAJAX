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
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @RequestMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        boolean rezult = userService.saveUser(user);
        if (!rezult){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        }catch (Exception e){
            //bad girl too
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
