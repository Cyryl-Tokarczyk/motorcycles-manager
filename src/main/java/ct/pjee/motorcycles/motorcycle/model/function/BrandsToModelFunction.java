package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.model.BrandsModel;

import java.util.List;
import java.util.function.Function;

public class BrandsToModelFunction implements Function<List<Brand>, BrandsModel> {

    @Override
    public BrandsModel apply(List<Brand> brands) {
        return BrandsModel.builder()
                .brands(brands.stream()
                        .map(brand -> BrandsModel.Brand.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .build())
                        .toList())
                .build();
    }

}
