package ct.pjee.motorcycles.motorcycle.controller.rest;

import ct.pjee.motorcycles.component.DtoFunctionFactory;
import ct.pjee.motorcycles.motorcycle.controller.api.MotorcycleController;
import ct.pjee.motorcycles.motorcycle.dto.GetMotorcycleResponse;
import ct.pjee.motorcycles.motorcycle.dto.GetMotorcyclesResponse;
import ct.pjee.motorcycles.motorcycle.dto.PatchMotorcycleRequest;
import ct.pjee.motorcycles.motorcycle.dto.PutMotorcycleRequest;
import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import ct.pjee.motorcycles.motorcycle.service.MotorcycleService;
import ct.pjee.motorcycles.user.entity.UserRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("")
@RolesAllowed(UserRoles.USER)
public class MotorcycleRestController implements MotorcycleController {

    private MotorcycleService motorcycleService;

    private BrandService brandService;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(final HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public MotorcycleRestController(
            final DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setMotorcycleService(final MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @EJB
    public void setBrandService(final BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public GetMotorcyclesResponse getMotorcycles() {
        return factory.motorcyclesToResponse().apply(motorcycleService.findAll());
    }

    @Override
    public GetMotorcycleResponse getMotorcycle(UUID id) {
        return factory.motorcycleToResponse().apply(motorcycleService.find(id)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public GetMotorcyclesResponse getMotorcyclesByBrand(UUID id) {
        return factory.motorcyclesToResponse().apply(motorcycleService.findMotorcyclesByBrand(
                brandService.find(id).orElseThrow(NotFoundException::new)
        ));
    }

    @Override
    public void putMotorcycle(UUID brandId, PutMotorcycleRequest request) {
        try {
            Brand brand = brandService.find(brandId).orElseThrow(NotFoundException::new);
            UUID id = UUID.randomUUID();
            Motorcycle motorcycle = factory.requestToMotorcycle().apply(id, request);

            motorcycle.setBrand(brand);
            brand.getMotorcycles().add(motorcycle);

            motorcycleService.createForCallerPrincipal(motorcycle);
            brandService.update(brand);

            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void patchMotorcycle(UUID brandId, UUID id, PatchMotorcycleRequest request) {
        motorcycleService.find(id).ifPresentOrElse(
                motorcycle -> {
                    if(motorcycle.getBrand().getId().equals(brandId)) {
                        try {
                            motorcycleService.update(factory.updateMotorcycleWithRequest().apply(motorcycle, request));
                        } catch (EJBAccessException e) {
                            throw new ForbiddenException(e.getMessage());
                        }
                    } else {
                        throw new NotFoundException();
                    }
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteMotorcycle(UUID id) {
        motorcycleService.find(id).ifPresentOrElse(
                motorcycle -> {
                    try {
                        motorcycleService.delete(id);
                    } catch (EJBAccessException e) {
                        throw new ForbiddenException(e.getMessage());
                    }
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
