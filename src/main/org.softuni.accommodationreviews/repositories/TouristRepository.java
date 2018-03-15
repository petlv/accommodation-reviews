package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, String> {

}
