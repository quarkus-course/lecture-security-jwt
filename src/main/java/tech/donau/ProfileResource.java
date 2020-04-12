package tech.donau;

import tech.donau.service.TokenService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/profile")
@RequestScoped
public class ProfileResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"User"})
    public String hello() {
        return "hello";
    }

    @Inject
    TokenService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public HashMap<String, String> register(@QueryParam("username") String username,
                           @QueryParam("email") String email,
                           @QueryParam("birthdate") String birthdate) {

        final String token = service.generateToken(email, username, birthdate);
        return new HashMap<String, String>() {{
            put("token", token);
        }};
    }
}