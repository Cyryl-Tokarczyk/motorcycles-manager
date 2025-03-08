package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.PutBrandRequest;
import ct.pjee.motorcycles.motorcycle.entity.Brand;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToBrandFunction implements BiFunction<UUID, PutBrandRequest, Brand> {

    @Override
    public Brand apply(UUID id, PutBrandRequest request) {
        return Brand.builder()
                .id(id)
                .name(request.getName())
                .dateOfFounding(request.getDateOfFounding())
                .numberOfEmployees(request.getNumberOfEmployees())
                .build();
    }

}
