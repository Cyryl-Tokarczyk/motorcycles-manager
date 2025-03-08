package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.GetMotorcycleResponse;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;

import java.util.function.Function;

public class MotorcycleToResponseFunction implements Function<Motorcycle, GetMotorcycleResponse> {

    @Override
    public GetMotorcycleResponse apply(Motorcycle motorcycle) {
        return GetMotorcycleResponse.builder()
                .id(motorcycle.getId())
                .name(motorcycle.getName())
                .brand(GetMotorcycleResponse.Brand.builder()
                        .id(motorcycle.getBrand().getId())
                        .name(motorcycle.getBrand().getName())
                        .build())
                .displacement(motorcycle.getDisplacement())
                .user(GetMotorcycleResponse.User.builder()
                        .id(motorcycle.getUser().getId())
                        .username(motorcycle.getUser().getUsername())
                        .build())
                .build();
    }

}
