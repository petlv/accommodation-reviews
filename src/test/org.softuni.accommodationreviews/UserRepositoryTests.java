package org.softuni.accommodationreviews;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindBySubstring_givenSubstring_shouldFindTwoUsers() {
        // arrange
        User pesho = new User();
        pesho.setUsername("pesho");
        pesho.setPassword("1");

        User mincho = new User();
        mincho.setUsername("mincho");
        mincho.setPassword("2");

        User gosho = new User();
        gosho.setUsername("gosho");
        gosho.setPassword("3");
        this.testEntityManager.persistAndFlush(pesho);
        this.testEntityManager.persistAndFlush(mincho);
        this.testEntityManager.persistAndFlush(gosho);

        // act
        List<User> result = this.userRepository.findBySubstring("sh");

        // assert
        assertEquals("pesho,gosho", String.join(",",result.stream()
                .map(User::getUsername).collect(Collectors.toList())));
    }
}
