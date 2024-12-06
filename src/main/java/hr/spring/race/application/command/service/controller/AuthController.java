package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String role;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

             role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jwtUtils.generateToken(username, role);
    }
}
