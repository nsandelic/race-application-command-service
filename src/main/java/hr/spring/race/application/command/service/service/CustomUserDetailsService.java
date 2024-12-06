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

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        hr.spring.race.application.command.service.model.entity.User userEntity;
        if(userRepository.existsUserByEmail(email)){
            userEntity = userRepository.findByEmail(email);
        }else
            throw new UsernameNotFoundException("User not found: " + email);

        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getLabel());
        authorities.add(authority);

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
