package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Owner;
import org.softuni.accommodationreviews.entities.Role;
import org.softuni.accommodationreviews.models.view.OwnerRegisterRequestModel;
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
    public Owner register(OwnerRegisterRequestModel model) {
        Owner owner = new Owner();
        owner.setUsername(model.getUsername());
        owner.setPassword(this.passwordEncoder.encode(model.getPassword()));
        owner.setFullName(model.getFullName());
        owner.setEmail(model.getEmail());

        Role role = this.roleRepository.findFirstByName("USER");
        role.getOwners().add(owner);
        owner.getRoles().add(role);
        this.roleRepository.save(role);

        return this.ownerRepository.saveAndFlush(owner);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = this.ownerRepository.findFirstByUsername(username);

        if (owner == null) {
            throw new UsernameNotFoundException("Owner not found");
        }

        Set<GrantedAuthority> roles = owner.getRoles()
                .stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toSet());

        UserDetails ownerDetails = new User(
                owner.getUsername(),
                owner.getPassword(),
                roles
        );
        return ownerDetails;
    }
}
