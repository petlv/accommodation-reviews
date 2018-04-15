package org.softuni.accommodationreviews.areas.towns;

import org.softuni.accommodationreviews.areas.towns.models.TownProjectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = TownProjectionModel.class)
public interface TownRepository extends JpaRepository<Town, Long> {

    //TownProjectionModel findByTitle(@Param("title") String name);

    Town findByTitle(String name);

    List<Town> findAll();
}
