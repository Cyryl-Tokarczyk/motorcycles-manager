package ct.pjee.motorcycles.motorcycle.controller.api;

import ct.pjee.motorcycles.motorcycle.dto.GetMotorcycleResponse;
import ct.pjee.motorcycles.motorcycle.dto.GetMotorcyclesResponse;
import ct.pjee.motorcycles.motorcycle.dto.PatchMotorcycleRequest;
import ct.pjee.motorcycles.motorcycle.dto.PutMotorcycleRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface MotorcycleController {

    @GET
    @Path("/motorcycles")
    @Produces(MediaType.APPLICATION_JSON)
    GetMotorcyclesResponse getMotorcycles();

    @GET
    @Path("/motorcycles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetMotorcycleResponse getMotorcycle(@PathParam("id") UUID id);

    @GET
    @Path("/brands/{id}/motorcycles")
    @Produces(MediaType.APPLICATION_JSON)
    GetMotorcyclesResponse getMotorcyclesByBrand(@PathParam("id") UUID id);

    @PUT
    @Path("/brands/{id}/motorcycles")
    @Consumes(MediaType.APPLICATION_JSON)
    void putMotorcycle(@PathParam("id") UUID brandId, PutMotorcycleRequest request);

    @PATCH
    @Path("/brands/{brandId}/motorcycles/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchMotorcycle(@PathParam("brandId") UUID brandId, @PathParam("id") UUID id, PatchMotorcycleRequest request);

    @DELETE
    @Path("/motorcycles/{id}")
    void deleteMotorcycle(@PathParam("id") UUID id);

}
