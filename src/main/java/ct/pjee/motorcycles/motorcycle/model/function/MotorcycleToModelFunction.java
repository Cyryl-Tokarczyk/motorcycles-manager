package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleModel;

import java.util.function.BiFunction;

public class MotorcycleToModelFunction implements BiFunction<Motorcycle, Brand, MotorcycleModel> {

    @Override
    public MotorcycleModel apply(Motorcycle motorcycle, Brand brand) {
        return MotorcycleModel.builder()
                .name(motorcycle.getName())
                .brand(brand.getName())
                .displacement(motorcycle.getDisplacement())
                .build();
    }

}
