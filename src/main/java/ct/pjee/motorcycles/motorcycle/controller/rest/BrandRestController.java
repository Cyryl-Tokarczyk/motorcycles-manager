package ct.pjee.motorcycles.motorcycle.controller.rest;

import ct.pjee.motorcycles.component.DtoFunctionFactory;
import ct.pjee.motorcycles.motorcycle.controller.api.BrandController;
import ct.pjee.motorcycles.motorcycle.dto.GetBrandResponse;
import ct.pjee.motorcycles.motorcycle.dto.GetBrandsResponse;
import ct.pjee.motorcycles.motorcycle.dto.PatchBrandRequest;
import ct.pjee.motorcycles.motorcycle.dto.PutBrandRequest;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import jakarta.ejb.EJB;
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
public class BrandRestController implements BrandController {

    private BrandService service;

    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public BrandRestController(
            final DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setService(BrandService service) {
        this.service = service;
    }

    @Override
    public GetBrandsResponse getBrands() {
        return factory.brandsToResponse().apply(service.findAll());
    }

    @Override
    public GetBrandResponse getBrand(UUID id) {
        return service.find(id)
                .map(factory.brandToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @SneakyThrows
    public void putBrand(PutBrandRequest request) {
        try {
            UUID id = UUID.randomUUID();
            service.create(factory.requestToBrand().apply(id, request));

            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void patchBrand(UUID id, PatchBrandRequest request) {
        service.find(id).ifPresentOrElse(
                brand -> service.update(factory.updateBrandWithRequest().apply(brand, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteBrand(UUID id) {
        service.find(id).ifPresentOrElse(
                brand -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
