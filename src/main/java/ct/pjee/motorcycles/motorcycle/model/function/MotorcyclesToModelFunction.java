package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcyclesModel;

import java.util.List;
import java.util.function.Function;

public class MotorcyclesToModelFunction implements Function<List<Motorcycle>, MotorcyclesModel> {

    @Override
    public MotorcyclesModel apply(List<Motorcycle> motorcycles) {
        return MotorcyclesModel.builder()
                .motorcycles(motorcycles.stream()
                        .map(motorcycle -> MotorcyclesModel.Motorcycle.builder()
                                .id(motorcycle.getId())
                                .name(motorcycle.getName())
                                .version(motorcycle.getVersion())
                                .creationDateTime(motorcycle.getCreationDateTime())
                                .build())
                        .toList())
                .build();
    }

}
