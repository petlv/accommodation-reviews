package org.softuni.accommodationreviews.areas.towns;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(excerptProjection = TownProjectionModel.class)
@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    //TownProjectionModel findByTitle(@Param("title") String name);

    Town findByTitle(String name);

    List<Town> findAll();
}
