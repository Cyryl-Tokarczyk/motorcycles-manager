package ct.pjee.motorcycles.user.controller.api;

import ct.pjee.motorcycles.user.dto.GetUserResponse;
import ct.pjee.motorcycles.user.dto.GetUsersResponse;
import ct.pjee.motorcycles.user.dto.PutUserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface UserController {

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    GetUsersResponse getUsers();

    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUserResponse getUser(@PathParam("id") UUID id);

    @PUT
    @Path("/users")
    void putUser(PutUserRequest request);

//    byte[] getUserAvatar(UUID id);
//
//    void putUserAvatar(UUID id, InputStream inputStream);
//
//    void deleteUserAvatar(UUID id);

}
