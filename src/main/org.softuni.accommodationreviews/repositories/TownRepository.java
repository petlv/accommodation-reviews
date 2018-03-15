package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, String> {
    Town findByName(String name);
}
