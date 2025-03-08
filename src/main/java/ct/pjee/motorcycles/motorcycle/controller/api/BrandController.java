package ct.pjee.motorcycles.motorcycle.controller.api;

import ct.pjee.motorcycles.motorcycle.dto.GetBrandResponse;
import ct.pjee.motorcycles.motorcycle.dto.GetBrandsResponse;
import ct.pjee.motorcycles.motorcycle.dto.PatchBrandRequest;
import ct.pjee.motorcycles.motorcycle.dto.PutBrandRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface BrandController {

    @GET
    @Path("/brands")
    @Produces(MediaType.APPLICATION_JSON)
    GetBrandsResponse getBrands();

    @GET
    @Path("/brands/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetBrandResponse getBrand(@PathParam("id") UUID id);

    @PUT
    @Path("/brands")
    @Consumes({MediaType.APPLICATION_JSON})
    void putBrand(PutBrandRequest request);

    @PATCH
    @Path("/brands/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchBrand(@PathParam("id") UUID id, PatchBrandRequest request);

    @DELETE
    @Path("/brands/{id}")
    void deleteBrand(@PathParam("id") UUID id);

}
