package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import de.gruppeo.wise2122_java_server.request.AuthRequest;
import de.gruppeo.wise2122_java_server.request.UpdatePasswordRequest;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static de.gruppeo.wise2122_java_server.model.Currentstatus.ONLINE;

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
        player.setCurrentstatus(ONLINE);

        PlayerEntity created = playerRepository.save(player);
        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }

    @PostMapping("/updatepassword")
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    @ResponseBody
    public ResponseEntity<String> updatePlayerPassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        String playername = updatePasswordRequest.getPlayername();
        String oldPassword = updatePasswordRequest.getOldpassword();
        String newPassword = updatePasswordRequest.getNewpassword();
        Optional<PlayerEntity> userOptional = playerRepository.findByUsername(updatePasswordRequest.getPlayername());

        // Prüfen ob Spieler existiert
        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Spieler existiert nicht!");
        }

        // Passwortüberprüfung erfolgt, indem der Nutzer eingeloggt wird
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(playername, oldPassword));

        // Ändere das Passwort nur, wenn der Spieler authentifiziert werden konnte
        if (authentication.isAuthenticated()) {
            // Nutzer mit dem neuen Passwort authentifizieren
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(playername, newPassword));

            PlayerEntity updatedPlayer = userOptional.get();
            updatedPlayer.setPassword(passwordEncoder.encode(newPassword));
            playerRepository.save(updatedPlayer);

            return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
        }

        return ResponseEntity.badRequest().build();
    }
}