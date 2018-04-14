package org.softuni.accommodationreviews.areas.accommodations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, String> {

    Accommodation findByName(String name);

}
