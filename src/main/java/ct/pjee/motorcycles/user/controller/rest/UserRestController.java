package ct.pjee.motorcycles.user.controller.rest;

import ct.pjee.motorcycles.component.DtoFunctionFactory;
import ct.pjee.motorcycles.user.controller.api.UserController;
import ct.pjee.motorcycles.user.dto.GetUserResponse;
import ct.pjee.motorcycles.user.dto.GetUsersResponse;
import ct.pjee.motorcycles.user.dto.PutUserRequest;
import ct.pjee.motorcycles.user.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;

import java.util.UUID;

@Path("")
public class UserRestController implements UserController {

    private UserService userService;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public UserRestController(DtoFunctionFactory factory, @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public GetUsersResponse getUsers() {
        return factory.usersToResponse().apply(userService.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID id) {
        return factory.userToResponse().apply(userService.find(id).orElseThrow(NotFoundException::new));
    }

    @Override
    @SneakyThrows
    public void putUser(PutUserRequest request) {
        try {
            UUID id = UUID.randomUUID();
            userService.create(factory.requestToUser().apply(id, request));

            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

//    @Override
//    public byte[] getUserAvatar(UUID id) {
//        return new byte[0];
//    }
//
//    @Override
//    public void putUserAvatar(UUID id, InputStream inputStream) {
//
//    }
//
//    @Override
//    public void deleteUserAvatar(UUID id) {
//
//    }
}
