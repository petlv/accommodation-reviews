package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Owner;
import org.softuni.accommodationreviews.entities.Role;
import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.softuni.accommodationreviews.repositories.OwnerRepository;
import org.softuni.accommodationreviews.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Owner register(UserBindingModel userModel) {
        Owner owner = new Owner();
        owner.setUsername(userModel.getUsername());
        owner.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        owner.setFullName(userModel.getFullName());
        owner.setEmail(userModel.getEmail());

        Role role = new Role();

        if(this.roleRepository.findFirstByName("USER") == null) {
            role.setName("OWNER");
            role.setTourists(new HashSet<>());
        } else {
            role = this.roleRepository.findFirstByName("USER");
        }

        role.getOwners().add(owner);
        owner.getOwnerRoles().add(role);
        this.roleRepository.save(role);

        return this.ownerRepository.saveAndFlush(owner);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = this.ownerRepository.findFirstByUsername(username);

        if (owner == null) {
            throw new UsernameNotFoundException("Owner not found");
        }

        Set<GrantedAuthority> roles = owner.getOwnerRoles()
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toSet());

        UserDetails ownerDetails = new User(
                owner.getUsername(),
                owner.getPassword(),
                roles
        );
        return ownerDetails;
    }
}
