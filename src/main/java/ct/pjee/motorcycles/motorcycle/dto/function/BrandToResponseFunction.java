package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.GetBrandResponse;
import ct.pjee.motorcycles.motorcycle.entity.Brand;

import java.util.function.Function;

public class BrandToResponseFunction implements Function<Brand, GetBrandResponse> {

    @Override
    public GetBrandResponse apply(Brand brand) {
        return GetBrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .dateOfFounding(brand.getDateOfFounding())
                .numberOfEmployees(brand.getNumberOfEmployees())
                .build();
    }

}
