package org.softuni.accommodationreviews.areas.users.services;

import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.roles.Role;
import org.softuni.accommodationreviews.areas.roles.RoleRepository;
import org.softuni.accommodationreviews.areas.users.User;
import org.softuni.accommodationreviews.areas.users.models.UserBindingModel;
import org.softuni.accommodationreviews.areas.users.UserRepository;
import org.softuni.accommodationreviews.areas.users.models.UserServiceModel;
import org.softuni.accommodationreviews.areas.users.models.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
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
//@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel findByIdCustom(Long id) {
        User user = this.userRepository.getOne(id);
        UserServiceModel serviceModel = this.mapper.map(user, UserServiceModel.class);
        return serviceModel;
    }

    @Override
    public UserServiceModel findByUsernameCustom(String username) {
        User user = this.userRepository.findFirstByUsername(username);
        UserServiceModel serviceModel = this.mapper.map(user, UserServiceModel.class);
        return serviceModel;
    }

    @Override
    public void checkIfOwner(UserBindingModel userModel, String optionsRadios, User user) {
        if (optionsRadios.equals("tourist")) {
            user.setIsOwner(false);
        } else if (optionsRadios.equals("owner")) {
            user.setIsOwner(true);
        }
    }

    @Override
    public void assignRole(User user, String optionsRadios) {

        Role role = new Role();

        switch (optionsRadios) {
            case "tourist":
                if (this.roleRepository.findFirstByAuthority("TOURIST") == null) {
                    role.setAuthority("TOURIST");
                    role.setRoleUsers(new HashSet<>());
                } else {
                    role = this.roleRepository.findFirstByAuthority("TOURIST");
                }; break;
            case "owner":
                if (this.roleRepository.findFirstByAuthority("OWNER") == null) {
                    role.setAuthority("OWNER");
                    role.setRoleUsers(new HashSet<>());
                } else {
                    role = this.roleRepository.findFirstByAuthority("OWNER");
                }; break;
            default: throw new InputMismatchException();
        }

        role.getRoleUsers().add(user);
        user.getSimpleAuthorities().add(role);
        this.roleRepository.save(role);
    }

    @Override
    public boolean register(UserBindingModel model, String optionsRadios) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(this.passwordEncoder.encode(model.getPassword()));
        user.setFullName(model.getFullName());
        user.setEmail(model.getEmail());

        checkIfOwner(model, optionsRadios, user);
        assignRole(user, optionsRadios);

        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean edit(UserViewModel model, String username) {
        User user = this.userRepository.findFirstByUsername(username);
        if (!model.getPassword().trim().equals(user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(model.getPassword()));
        }
        user.setUsername(model.getUsername());
        user.setFullName(model.getFullName());
        user.setEmail(model.getEmail());
        user.setIsOwner(model.getIsOwner());
        if(user.getIsOwner()) {
            user.setUserAccommodations(model.getUserAccommodations());
        } else {
            user.setUserComments(model.getUserComments());
        }
        user.setAuthorities(model.getAuthorities());

        this.userRepository.save(user);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        Set<GrantedAuthority> roles = user.getSimpleAuthorities()
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getAuthority()))
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
