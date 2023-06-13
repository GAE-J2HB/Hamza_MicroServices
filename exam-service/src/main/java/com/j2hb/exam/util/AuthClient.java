package com.j2hb.exam.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service",url = "${auth-service.url}") // url = "http://localhost:8082
public interface AuthClient {
    @GetMapping("/{id_autoEcole}/validate/{email}")
    boolean validateOperation(@PathVariable("id_autoEcole") Long id_autoEcole, @PathVariable("email") String email);
}
