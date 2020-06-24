package com.company.brainstorm.repositories;

import com.company.brainstorm.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
