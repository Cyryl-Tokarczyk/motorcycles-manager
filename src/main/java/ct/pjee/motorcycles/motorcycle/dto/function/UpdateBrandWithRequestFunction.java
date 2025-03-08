package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.PatchBrandRequest;
import ct.pjee.motorcycles.motorcycle.entity.Brand;

import java.util.function.BiFunction;

public class UpdateBrandWithRequestFunction implements BiFunction<Brand, PatchBrandRequest, Brand> {

    @Override
    public Brand apply(Brand brand, PatchBrandRequest request) {
        return Brand.builder()
                .id(brand.getId())
                .name(request.getName())
                .dateOfFounding(request.getDateOfFounding())
                .numberOfEmployees(request.getNumberOfEmployees())
                .build();
    }

}
