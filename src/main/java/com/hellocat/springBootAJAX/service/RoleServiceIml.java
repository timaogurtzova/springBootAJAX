package com.hellocat.springBootAJAX.service;

import com.hellocat.springBootAJAX.domen.Role;
import com.hellocat.springBootAJAX.domen.RoleType;
import com.hellocat.springBootAJAX.domen.User;
import com.hellocat.springBootAJAX.repository.RoleRepository;
import com.hellocat.springBootAJAX.util.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceIml implements RoleService {

    @Autowired
    private UserService userService;

    private RoleRepository roleRepository;

    @PostProxy
    public void dbRequest() {
        Role adminRole = new Role(RoleType.ROLE_ADMIN);
        Role userRole = new Role(RoleType.ROLE_USER);
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        User admin = new User("admin", 100, "1", "Msk", roles);

        Set<Role> rolesForUser = new HashSet<>();
        rolesForUser.add(userRole);
        User user = new User("user", 20, "1", "Spb",rolesForUser);

        userService.saveUser(admin);
        userService.saveUser(user);
    }

    @Autowired
    public RoleServiceIml(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleWithName(String roleTypeName) {
        return roleRepository.getRoleByRoleType(roleTypeName);
    }
}
