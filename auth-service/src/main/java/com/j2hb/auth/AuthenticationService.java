package com.j2hb.auth;

import com.j2hb.cException.UserExistException;
import com.j2hb.config.JwtService;
import com.j2hb.school.AutoEcoleService;
import com.j2hb.token.TokenService;
import com.j2hb.user.User;
import com.j2hb.user.UserRepository;
import com.j2hb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AutoEcoleService autoEcoleService;
    private final TokenService tokenService;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userService.UserExists(request.getEmail())) {
            throw new UserExistException("User already exists");
        } else {
            User user = userService.createUser(request);
            autoEcoleService.saveAutoEcole(request, user);
            var jwt = jwtService.generateToken(user);
            tokenService.saveUserToken(user, jwt);
            return AuthenticationResponse.builder().token(jwt).build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));
        var jwt = jwtService.generateToken(user);
        tokenService.revokeAllUserToken(user);
        tokenService.saveUserToken(user, jwt);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
