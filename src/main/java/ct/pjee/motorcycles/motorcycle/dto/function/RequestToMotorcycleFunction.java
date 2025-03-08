package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.PutMotorcycleRequest;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToMotorcycleFunction implements BiFunction<UUID, PutMotorcycleRequest, Motorcycle> {

    @Override
    public Motorcycle apply(UUID id, PutMotorcycleRequest request) {
        return Motorcycle.builder()
                .id(id)
                .name(request.getName())
                .displacement(request.getDisplacement())
                .build();
    }

}
