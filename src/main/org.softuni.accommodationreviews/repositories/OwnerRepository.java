package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findFirstByUsername(String username);

}
