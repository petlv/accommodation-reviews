package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByTitle(String name);

    List<Town> findAll();
}
