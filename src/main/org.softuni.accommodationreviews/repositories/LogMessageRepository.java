package org.softuni.accommodationreviews.repositories;

import org.softuni.accommodationreviews.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Long> {

}
