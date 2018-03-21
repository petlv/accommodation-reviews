package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findFirstByName(String name);
}
