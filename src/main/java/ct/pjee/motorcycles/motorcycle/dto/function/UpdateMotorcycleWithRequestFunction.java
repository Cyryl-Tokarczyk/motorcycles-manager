package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.PatchMotorcycleRequest;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;

import java.util.function.BiFunction;

public class UpdateMotorcycleWithRequestFunction implements BiFunction<Motorcycle, PatchMotorcycleRequest, Motorcycle> {

    @Override
    public Motorcycle apply(Motorcycle motorcycle, PatchMotorcycleRequest request) {
        return Motorcycle.builder()
                .id(motorcycle.getId())
                .name(request.getName())
                .brand(motorcycle.getBrand())
                .displacement(request.getDisplacement())
                .user(motorcycle.getUser())
                .build();
    }

}
