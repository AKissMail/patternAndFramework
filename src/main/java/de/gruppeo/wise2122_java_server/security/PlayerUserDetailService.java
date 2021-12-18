package de.gruppeo.wise2122_java_server.security;

import de.gruppeo.wise2122_java_server.model.PlayerEntity;
import de.gruppeo.wise2122_java_server.repository.PlayerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PlayerUserDetailService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    public PlayerUserDetailService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlayerEntity player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Dieser Spieler wurde nicht gefunden."));

        return new org.springframework.security.core.userdetails.User(
                player.getUsername(),
                player.getPassword(),
                Collections.emptyList()
        );
    }
}
