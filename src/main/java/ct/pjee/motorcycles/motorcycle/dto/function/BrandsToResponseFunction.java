package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.GetBrandsResponse;
import ct.pjee.motorcycles.motorcycle.entity.Brand;

import java.util.List;
import java.util.function.Function;

public class BrandsToResponseFunction implements Function<List<Brand>, GetBrandsResponse> {

    @Override
    public GetBrandsResponse apply(List<Brand> brands) {
        return GetBrandsResponse.builder()
                .brands(brands.stream()
                        .map(brand -> GetBrandsResponse.Brand.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .build())
                        .toList())
                .build();
    }

}
