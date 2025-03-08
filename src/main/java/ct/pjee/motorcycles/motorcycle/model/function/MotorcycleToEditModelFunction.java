package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class MotorcycleToEditModelFunction implements Function<Motorcycle, MotorcycleEditModel>, Serializable {

    @Override
    public MotorcycleEditModel apply(Motorcycle motorcycle) {
        return MotorcycleEditModel.builder()
                .id(motorcycle.getId())
                .name(motorcycle.getName())
                .displacement(motorcycle.getDisplacement())
                .version(motorcycle.getVersion())
                .build();
    }

}
