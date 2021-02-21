package com.pri.aa.enigma.repositories;

import com.pri.aa.enigma.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
    boolean existsByName(String name);
}