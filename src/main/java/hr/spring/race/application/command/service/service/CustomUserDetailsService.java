package hr.spring.race.application.command.service.service;

import hr.spring.race.application.command.service.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("CustomUserDetailsService - > loadUserByUsername(), username: " + email);
        hr.spring.race.application.command.service.model.entity.User userEntity;
        logger.info("CustomUserDetailsService - > Getting user from repository");
        if(userRepository.existsUserByEmail(email)){
            logger.info("CustomUserDetailsService - > User found, Fetching....");
            userEntity = userRepository.findByEmail(email);
        }else {
            logger.info("CustomUserDetailsService - > User not found");
            throw new UsernameNotFoundException("User not found: " + email);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getLabel());
        authorities.add(authority);
        logger.info("CustomUserDetailsService - > Returning authenticated user");
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
