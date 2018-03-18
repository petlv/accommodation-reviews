package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.Tourist;
import org.softuni.accommodationreviews.models.view.TouristRegisterRequestModel;
import org.softuni.accommodationreviews.repositories.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@Transactional
@Primary
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TouristServiceImpl(TouristRepository touristRepository, BCryptPasswordEncoder passwordEncoder) {
        this.touristRepository = touristRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Tourist register(TouristRegisterRequestModel model) {
        Tourist tourist = new Tourist();
        tourist.setUsername(model.getUsername());
        tourist.setPassword(this.passwordEncoder.encode(model.getPassword()));
        tourist.setFullName(model.getFullName());
        tourist.setEmail(model.getEmail());

        return this.touristRepository.saveAndFlush(tourist);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tourist tourist = this.touristRepository.findFirstByUsername(username);

        if (tourist == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails touristDetails = new User(
                tourist.getUsername(),
                tourist.getPassword(),
                new HashSet<>()
        );
        return touristDetails;
    }
}
