package com.tutorialsdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorialsdesk.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
