package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.BrandModel;

import java.util.List;
import java.util.function.BiFunction;

public class BrandToModelFunction  implements BiFunction<Brand, List<Motorcycle>, BrandModel> {

    @Override
    public BrandModel apply(Brand brand, List<Motorcycle> motorcycles) {
        return BrandModel.builder()
                .name(brand.getName())
                .dateOfFounding(brand.getDateOfFounding())
                .numberOfEmployees(brand.getNumberOfEmployees())
                .motorcycles(motorcycles.stream()
                        .map(motorcycle -> BrandModel.Motorcycle.builder()
                                .id(motorcycle.getId())
                                .name(motorcycle.getName())
                                .version(motorcycle.getVersion())
                                .creationDateTime(motorcycle.getCreationDateTime())
                                .updateDateTime(motorcycle.getUpdateDateTime())
                                .build())
                        .toList())
                .build();
    }

}
