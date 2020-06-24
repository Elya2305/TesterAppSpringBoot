package com.company.brainstorm.services;

import com.company.brainstorm.domains.Role;
import com.company.brainstorm.domains.User;
import com.company.brainstorm.repositories.GameRepository;
import com.company.brainstorm.repositories.RoleRepository;
import com.company.brainstorm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    GameRepository gameRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        System.out.println(userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if(userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setRole(roleRepository.getOne(1L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public int totalGame(User user){
        System.out.println("user: " + user);
        return gameRepository.findByUser(user).size();
    }

    public int totalWin(User user){
        return gameRepository.findByUserAndWin(user, true).size();
    }

    public int maxScore(User user){
        return gameRepository.findMaxScore(user);
    }

    public double avgScore(User user){
        return gameRepository.findAvrScore(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
