package org.softuni.accommodationreviews.areas.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    @Query("select u from User u where substring(u.username, 3, 2) = :substr")
    List<User> findBySubstring(@Param("substr") String substr);

}
