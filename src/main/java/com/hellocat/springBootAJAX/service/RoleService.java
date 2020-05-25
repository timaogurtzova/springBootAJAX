package com.hellocat.springBootAJAX.service;

import com.hellocat.springBootAJAX.domen.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findRoleWithName(String roleTypeName);

}
