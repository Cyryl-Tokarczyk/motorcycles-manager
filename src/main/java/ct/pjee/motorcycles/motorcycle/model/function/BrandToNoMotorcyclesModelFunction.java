package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.model.NoMotorcyclesBrandModel;

import java.io.Serializable;
import java.util.function.Function;

public class BrandToNoMotorcyclesModelFunction implements Function<Brand, NoMotorcyclesBrandModel>, Serializable {

    @Override
    public NoMotorcyclesBrandModel apply(Brand brand) {
        return NoMotorcyclesBrandModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .dateOfFounding(brand.getDateOfFounding())
                .numberOfEmployees(brand.getNumberOfEmployees())
                .build();
    }

}
