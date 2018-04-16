package org.softuni.accommodationreviews.areas.roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByAuthority(String name);
}
