package com.upc.ViksAdventures.iam.interfaces.rest.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserResource {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
