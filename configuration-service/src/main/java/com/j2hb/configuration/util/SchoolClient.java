package com.j2hb.configuration.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "${auth-service.url}")
public interface SchoolClient {
    @GetMapping("/{id_autoEcole}/exist")
    boolean isExist(@PathVariable long id_autoEcole);

    @GetMapping("/{id_autoEcole}/validate/{email}")
    boolean isMember(@PathVariable("id_autoEcole") long id_autoEcole, @PathVariable("email") String email);
}
