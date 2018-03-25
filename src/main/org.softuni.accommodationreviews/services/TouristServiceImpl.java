package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Role;
import org.softuni.accommodationreviews.entities.Tourist;
import org.softuni.accommodationreviews.models.binding.UserBindingModel;
import org.softuni.accommodationreviews.repositories.RoleRepository;
import org.softuni.accommodationreviews.repositories.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
@Primary
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TouristServiceImpl(TouristRepository touristRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.touristRepository = touristRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Tourist register(UserBindingModel model) {
        Tourist tourist = new Tourist();
        tourist.setUsername(model.getUsername());
        tourist.setPassword(this.passwordEncoder.encode(model.getPassword()));
        tourist.setFullName(model.getFullName());
        tourist.setEmail(model.getEmail());

        Role role = new Role();

        if (this.roleRepository.findFirstByName("TOURIST") == null) {
            role.setName("TOURIST");
            role.setTourists(new HashSet<>());
        } else {
            role = this.roleRepository.findFirstByName("TOURIST");
        }

        role.getTourists().add(tourist);
        tourist.getTouristRoles().add(role);
        this.roleRepository.save(role);

        return this.touristRepository.save(tourist);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tourist tourist = this.touristRepository.findFirstByUsername(username);

        if (tourist == null) {
            throw new UsernameNotFoundException("Tourist not found");
        }

        Set<GrantedAuthority> roles = tourist.getTouristRoles()
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toSet());

        UserDetails touristDetails;
        touristDetails = new User(
                tourist.getUsername(),
                tourist.getPassword(),
                roles
        );
        return touristDetails;
    }
}
