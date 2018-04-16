package org.softuni.accommodationreviews.areas.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

}
