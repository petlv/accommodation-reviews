package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.Town;
import org.softuni.accommodationreviews.models.service.TownServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = TownServiceModel.class)
public interface TownRepository extends JpaRepository<Town, Long> {
    TownServiceModel findByTitle(@Param("title") String name);

    List<Town> findAll();
}
