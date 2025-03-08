package ct.pjee.motorcycles.motorcycle.model.function;

import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleEditModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateMotorcycleWithModelFunction implements BiFunction<Motorcycle, MotorcycleEditModel, Motorcycle>, Serializable {

    @Override
    @SneakyThrows
    public Motorcycle apply(Motorcycle motorcycle, MotorcycleEditModel editModel) {
        return Motorcycle.builder()
                .id(motorcycle.getId())
                .name(editModel.getName())
                .displacement(editModel.getDisplacement())
                .brand(motorcycle.getBrand())
                .user(motorcycle.getUser())
                .version(editModel.getVersion())
                .creationDateTime(motorcycle.getCreationDateTime())
                .build();
    }

}
