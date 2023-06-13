package com.j2hb.demo;

import com.j2hb.auth.RegisterRequest;
import com.j2hb.config.JwtService;
import com.j2hb.school.AutoEcole;
import com.j2hb.school.AutoEcoleService;
import com.j2hb.user.User;
import com.j2hb.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/e")
@RequiredArgsConstructor
public class AutoEcoleController {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AutoEcoleService autoEcoleService;

    private User getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        var username = jwtService.extractUsername(token);
        var user = userRepository.findByEmail(username);
        return user.orElse(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AutoEcole>> getAutoEcoles(HttpServletRequest request) {
        User user = getUser(request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            var autoEcoles = user.getAutoEcoles();
            return ResponseEntity.ok(autoEcoles);
        }
    }

    @GetMapping("/{id_auto_ecole}")
    public ResponseEntity<Optional<AutoEcole>> getAutoEcole(HttpServletRequest request, @PathVariable long id_auto_ecole) {
        User user = getUser(request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            var autoEcoles = user.getAutoEcoles().stream().filter(autoEcole -> autoEcole.getAutoEcole_id() == id_auto_ecole).findFirst();
            return ResponseEntity.ok(autoEcoles);
        }
    }

    @PostMapping("/create-auto-ecole")
    public ResponseEntity<String> createAutoEcole(HttpServletRequest request, RegisterRequest schoolRequest) {
        User user = getUser(request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            autoEcoleService.saveAutoEcole(schoolRequest, user);
            return ResponseEntity.ok("Auto-ecole created successfully");
        }
    }
}