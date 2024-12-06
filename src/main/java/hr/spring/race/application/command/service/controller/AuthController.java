package hr.spring.race.application.command.service.controller;

import hr.spring.race.application.command.service.model.entity.LoginCred;
import hr.spring.race.application.command.service.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginCred loginCred) {
        String role;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginCred.getUsername(), loginCred.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

             role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String token = jwtUtils.generateToken(loginCred.getUsername(), role);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", loginCred.getUsername());
        response.put("role", role);
        return ResponseEntity.ok(response);
    }
}
