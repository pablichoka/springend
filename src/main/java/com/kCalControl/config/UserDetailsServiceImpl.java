package com.kCalControl.config;

import com.kCalControl.model.Role;
import com.kCalControl.model.User;
import com.kCalControl.repository.UserDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserDBRepository userDBRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        logger.debug("He pasado por aqui");
        Optional<User> userDBOptional = userDBRepository.findById(Integer.parseInt(id));
        User user;
        if(userDBOptional.isPresent()){
            user = userDBOptional.get();
        }else{
            throw new UsernameNotFoundException(id);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Role> roles = user.getRoles().stream().toList();
        for (Role role : roles){
            logger.debug("Este es el role que se añade: " + role.getId());
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getId()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
