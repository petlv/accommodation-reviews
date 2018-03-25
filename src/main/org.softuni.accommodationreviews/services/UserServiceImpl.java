package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Role;
import org.softuni.accommodationreviews.entities.User;
import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.softuni.accommodationreviews.repositories.RoleRepository;
import org.softuni.accommodationreviews.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void checkIfOwner(UserBindingModel userModel, String optionsRadios, User user) {
        if (optionsRadios.equals("tourist")) {
            user.setOwner(false);
        } else if (optionsRadios.equals("owner")) {
            user.setOwner(true);
        }
    }

    @Override
    public void assignRole(User user, String optionsRadios) {

        Role role = new Role();

        switch (optionsRadios) {
            case "tourist":
                if (this.roleRepository.findFirstByName("TOURIST") == null) {
                    role.setName("TOURIST");
                    role.setRoleUsers(new HashSet<>());
                } else {
                    role = this.roleRepository.findFirstByName("TOURIST");
                }; break;
            case "owner":
                if (this.roleRepository.findFirstByName("OWNER") == null) {
                    role.setName("OWNER");
                    role.setRoleUsers(new HashSet<>());
                } else {
                    role = this.roleRepository.findFirstByName("OWNER");
                }; break;
            default: throw new InputMismatchException();
        }

        role.getRoleUsers().add(user);
        user.getUserRoles().add(role);
        this.roleRepository.save(role);
    }

    @Override
    public User register(UserBindingModel model, String optionsRadios) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));
        user.setFullName(model.getFullName());
        user.setEmail(model.getEmail());

        checkIfOwner(model, optionsRadios, user);
        assignRole(user, optionsRadios);

        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> roles = user.getUserRoles()
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toSet());

        UserDetails userDetails;
        userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
        return userDetails;
    }
}
