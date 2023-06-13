package com.j2hb.demo;

import com.j2hb.cException.UserExistException;
import com.j2hb.school.AutoEcole;
import com.j2hb.school.AutoEcoleRepository;
import com.j2hb.school.AutoEcoleService;
import com.j2hb.user.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/e")
@PreAuthorize("hasRole('ADMIN')")
@Transactional
public class TeamController {
    private final AutoEcoleRepository autoEcoleRepository;
    private final AutoEcoleService autoEcoleService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id_auto_ecole}/team")
    public ResponseEntity<List<User>> getTeam(
            HttpServletRequest request,
            @PathVariable long id_auto_ecole
    ) {
        String token = request.getHeader("Authorization").substring(7);
        if (autoEcoleService.userHasAccessToAutoEcole(token, id_auto_ecole)) {
            return ResponseEntity.status(403).build();
        } else {
            Optional<AutoEcole> autoEcoleOptional = autoEcoleRepository.findById(id_auto_ecole);
            return autoEcoleOptional.map(
                            autoEcole -> ResponseEntity.ok(autoEcole.getUsers()))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    @PostMapping("/{id_auto_ecole}/create-team-member")
    @Transactional
    public ResponseEntity<String> createTeamMember(
            HttpServletRequest request,
            @PathVariable long id_auto_ecole,
            @RequestBody TeamCreationRequest creationRequest
    ) {
        String token = request.getHeader("Authorization").substring(7);
        if (autoEcoleService.userHasAccessToAutoEcole(token, id_auto_ecole)) {
            return ResponseEntity.status(403).build();
        } else {
            if (userService.UserExists(creationRequest.getEmail())) {
                throw new UserExistException("User already exists");
            } else {
                User user = User.builder()
                        .firstName(creationRequest.getFirstName())
                        .lastName(creationRequest.getLastName())
                        .email(creationRequest.getEmail())
                        .password(passwordEncoder.encode(creationRequest.getPassword()))
                        .role(Role.MANAGER)
                        .build();
                userRepository.save(user);
                AutoEcole autoEcole = autoEcoleRepository.findById(id_auto_ecole).get();
                autoEcole.getUsers().add(user);
                autoEcoleRepository.save(autoEcole);
                return ResponseEntity.ok("Successfully created team member");
            }
        }
    }

    @DeleteMapping("/{id_auto_ecole}/delete-team-member/{id_user}")
    public ResponseEntity<String> deleteTeam(
            HttpServletRequest request,
            @PathVariable("id_auto_ecole") long id_auto_ecole,
            @PathVariable("id_user") long id_user) {
        String token = request.getHeader("Authorization").substring(7);
        if (autoEcoleService.userHasAccessToAutoEcole(token, id_auto_ecole)) {
            return ResponseEntity.status(403).build();
        } else {
            User user = userRepository.findById(id_user).get();
            Optional<AutoEcole> autoEcoleOptional = autoEcoleRepository.findById(id_auto_ecole);
            if (autoEcoleOptional.isPresent()) {
                AutoEcole autoEcole = autoEcoleOptional.get();
                autoEcole.getUsers().remove(user);
                userRepository.delete(user);
                autoEcoleRepository.save(autoEcole);
                return ResponseEntity.ok("Successfully deleted team");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}