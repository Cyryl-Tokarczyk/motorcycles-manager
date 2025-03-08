package ct.pjee.motorcycles.motorcycle.dto.function;

import ct.pjee.motorcycles.motorcycle.dto.GetMotorcyclesResponse;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;

import java.util.List;
import java.util.function.Function;

public class MotorcyclesToResponseFunction implements Function<List<Motorcycle>, GetMotorcyclesResponse> {

    @Override
    public GetMotorcyclesResponse apply(List<Motorcycle> motorcycles) {
        return GetMotorcyclesResponse.builder()
                .motorcycles(motorcycles.stream()
                        .map(motorcycle -> GetMotorcyclesResponse.Motorcycle.builder()
                                .id(motorcycle.getId())
                                .name(motorcycle.getName())
                                .build())
                        .toList())
                .build();
    }

}
