package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsRepository extends JpaRepository<Accommodation, Long> {

    Accommodation findByName(String name);

}
