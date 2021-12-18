package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.request.AuthRequest;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PlayerRepository playerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(PlayerRepository playerRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PlayerEntity> register(@RequestBody AuthRequest authRequest) {
        Optional<PlayerEntity> userOptional = playerRepository.findByUsername(authRequest.getUsername());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        PlayerEntity player = new PlayerEntity();
        player.setUsername(authRequest.getUsername());
        player.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        player.setCurrentscore(0);
        player.setCurrentstatus("online");

        PlayerEntity created = playerRepository.save(player);
        return ResponseEntity.ok(created);
    }
}
