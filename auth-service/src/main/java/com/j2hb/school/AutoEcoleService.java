package com.j2hb.school;

import com.j2hb.auth.RegisterRequest;
import com.j2hb.config.JwtService;
import com.j2hb.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AutoEcoleService {
    static int countImg = 0;
    private final AutoEcoleRepository autoEcoleRepository;
    private final JwtService jwtService;

    @Value("${upload.path}")
    String FOLDER_PATH;

    public String uploadImage(MultipartFile imageFile, User user) {
        String filePath = FOLDER_PATH + ++countImg + "_" + user.getUser_id() + "_" + LocalDate.now();
        try {
            imageFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    @Transactional
    public void saveAutoEcole(@NotNull RegisterRequest request, User user) {
        var autoEcole = AutoEcole.builder()
                .logo(uploadImage(request.getLogo(), user))
                .name(request.getName())
                .dateOuverture(request.getDateOuverture())
                .numRegistreCom(request.getNumRegistreCom())
                .idFiscale(request.getIdFiscale())
                .dateAutorisation(request.getDateAutorisation())
                .agrement(request.getAgrement())
                .nPatente(request.getNPatente())
                .imageRC(uploadImage(request.getImageRC(), user))
                .imageAgrement(uploadImage(request.getImageAgrement(), user))
                .numCnss(request.getNumCnss())
                .ice(request.getICE())
                .numCmpt(request.getNumCmpt())
                .tva(request.getTVA())
                .pays(request.getPays())
                .tel(request.getTel())
                .ville(request.getVille())
                .adresse(request.getAdresse())
                .fax(request.getFax())
                .build();
        autoEcole.getUsers().add(user);
        autoEcoleRepository.save(autoEcole);
    }

    public boolean userHasAccessToAutoEcole(String token, long idAutoEcole) {
        Optional<AutoEcole> autoEcoleOptional = autoEcoleRepository.findById(idAutoEcole);
        if (autoEcoleOptional.isPresent()) {
            AutoEcole autoEcole = autoEcoleOptional.get();
            return autoEcole.getUsers()
                    .stream()
                    .noneMatch(user -> user.getRole().name().equals("ADMIN") && Objects.equals(user.getEmail(), jwtService.extractUsername(token)));
        }
        return false;
    }
}
