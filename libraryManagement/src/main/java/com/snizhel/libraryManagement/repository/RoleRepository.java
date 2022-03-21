package com.snizhel.libraryManagement.repository;


import com.snizhel.libraryManagement.model.ERole;
import com.snizhel.libraryManagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional< Role > findByName( ERole name);
}
