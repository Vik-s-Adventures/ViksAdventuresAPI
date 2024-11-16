package com.upc.ViksAdventures.iam.interfaces.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response resource for an authenticated user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserResource {
    private String email;
    private String token;
}
