package com.j2hb.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreationRequest {
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
}
