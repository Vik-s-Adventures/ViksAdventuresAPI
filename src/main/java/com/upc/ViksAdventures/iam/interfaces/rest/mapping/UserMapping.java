package com.upc.ViksAdventures.iam.interfaces.rest.mapping;

import com.upc.ViksAdventures.iam.domain.model.User;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.SignInUserResource;
import com.upc.ViksAdventures.iam.interfaces.rest.resource.SignUpUserResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    private final ModelMapper modelMapper;

    public UserMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Mapea SignUpUserResource a User para creación de usuario.
     *
     * @param resource SignUpUserResource con datos de usuario para sign-up.
     * @return User mapeado.
     */
    public User toModel(SignUpUserResource resource) {
        return modelMapper.map(resource, User.class);
    }

    /**
     * Mapea SignInUserResource a User para autenticación.
     *
     * @param resource SignInUserResource con datos de usuario para sign-in.
     * @return User mapeado.
     */
    public User toModel(SignInUserResource resource) {
        return modelMapper.map(resource, User.class);
    }

    /**
     * Mapea User a SignUpUserResource (en caso sea necesario para retornarlo).
     *
     * @param user User entidad a mapear.
     * @return SignUpUserResource mapeado.
     */
    public SignUpUserResource toSignUpResource(User user) {
        return modelMapper.map(user, SignUpUserResource.class);
    }

    /**
     * Mapea User a SignInUserResource (en caso sea necesario para retornarlo).
     *
     * @param user User entidad a mapear.
     * @return SignInUserResource mapeado.
     */
    public SignInUserResource toSignInResource(User user) {
        return modelMapper.map(user, SignInUserResource.class);
    }
}