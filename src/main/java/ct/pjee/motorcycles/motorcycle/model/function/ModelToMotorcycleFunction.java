package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleCreateModel;

import java.util.function.Function;

public class ModelToMotorcycleFunction implements Function<MotorcycleCreateModel, Motorcycle> {

    @Override
    public Motorcycle apply(MotorcycleCreateModel motorcycle) {
        return Motorcycle.builder()
                .id(motorcycle.getId())
                .name(motorcycle.getName())
                .displacement(motorcycle.getDisplacement())
                .brand(Brand.builder()
                        .id(motorcycle.getBrand().getId())
                        .name(motorcycle.getBrand().getName())
                        .dateOfFounding(motorcycle.getBrand().getDateOfFounding())
                        .numberOfEmployees(motorcycle.getBrand().getNumberOfEmployees())
                        .build()
                ).build();
    }

}
