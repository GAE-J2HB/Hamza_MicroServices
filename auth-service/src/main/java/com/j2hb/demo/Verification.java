package com.j2hb.demo;

import com.j2hb.school.AutoEcole;
import com.j2hb.school.AutoEcoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class Verification {
    private final AutoEcoleRepository autoEcoleRepository;

    @GetMapping("/{id_autoEcole}/validate/{email}")
    public boolean isMember(@PathVariable("id_autoEcole") long id_autoEcole, @PathVariable("email") String email) {
        Optional<AutoEcole> autoEcoleOptional = autoEcoleRepository.findById(id_autoEcole);
        if (autoEcoleOptional.isPresent()) {
            AutoEcole autoEcole = autoEcoleOptional.get();
            return autoEcole.getUsers().stream().anyMatch(user -> Objects.equals(user.getEmail(), email));
        } else {
            return false;
        }
    }

    @GetMapping("/{id_autoEcole}/exist")
    public boolean isExist(@PathVariable("id_autoEcole") long id_autoEcole) {
        return autoEcoleRepository.existsById(id_autoEcole);
    }


}
