package com.hellocat.springBootAJAX.repository;

import com.hellocat.springBootAJAX.domen.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleType(String roleType);

}
