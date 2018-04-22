package org.softuni.accommodationreviews;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.softuni.accommodationreviews.areas.roles.Role;
import org.softuni.accommodationreviews.areas.roles.RoleRepository;
import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.softuni.accommodationreviews.areas.users.models.UserBindingModel;
import org.softuni.accommodationreviews.areas.users.services.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
    private static final String PASSWORD_HASH = "myCustomHash";
    private static final String USERNAME = "pesho";
    private static final String PASSWORD = "pesho123";
    private static final String FULLNAME = "Petar Petrov";
    private static final String EMAIL = "pesho@abv.bg";
    private UserBindingModel userBindingModel;
    private User user;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTests() {
        this.userBindingModel = new UserBindingModel();
        this.userBindingModel.setUsername(USERNAME);
        this.userBindingModel.setPassword(PASSWORD);
        this.userBindingModel.setFullName(FULLNAME);
        this.userBindingModel.setEmail(EMAIL);
        this.user = new User();
    }

    @Before
    public void setUp() {
        when(this.passwordEncoder.encode(anyString()))
                .thenReturn(PASSWORD_HASH);
    }

    @Test
    public void testCheckIfOwner_withOptionRadiosTourist_userSetOwnerFalse() {
        when(this.userRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));


        this.userService.checkIfOwner(userBindingModel, "tourist", user);

        assertFalse(
            "User was not assigned the correct value to the field isOwner!",
                user.getIsOwner()
        );
    }

    @Test
    public void testAssignRole_withOptionRadiosOwner_userGetSimpleAuthoritiesOwner() {
        when(this.userRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));
        when(this.roleRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));


        this.userService.assignRole(this.user, "owner");
        Role role = new Role();
        role.setAuthority("OWNER");
        List<Role> userRole = new ArrayList<>(this.user.getSimpleAuthorities());

        assertEquals(
                "User was not assigned the correct role!",
                role.getAuthority(),
                userRole.get(0).getAuthority()
        );
    }
}
