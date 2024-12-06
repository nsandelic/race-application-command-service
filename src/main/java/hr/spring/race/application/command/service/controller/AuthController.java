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
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    private final JwtUtil jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginCred loginCred) {
        logger.info("AuthController - > login()");
        String role;
        try {
            logger.info("AuthController - > Authenticating user");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginCred.getUsername(), loginCred.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

             role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        } catch (Exception e) {
            logger.info("AuthController - > Authentication failed");
            throw new RuntimeException(e);
        }
        logger.info("AuthController - > User authenticated");
        String token = jwtUtils.generateToken(loginCred.getUsername(), role);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", loginCred.getUsername());
        response.put("role", role);
        logger.info("AuthController - > Returning auth data ->  " + response);
        return ResponseEntity.ok(response);
    }
}
