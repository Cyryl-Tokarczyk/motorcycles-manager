package ct.pjee.motorcycles.component;

import ct.pjee.motorcycles.motorcycle.model.function.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelFunctionFactory {

    public MotorcyclesToModelFunction motorcyclesToModel() {
        return new MotorcyclesToModelFunction();
    }

    public BrandsToModelFunction brandsToModel() {
        return new BrandsToModelFunction();
    }

    public BrandToModelFunction brandToModel() {
        return new BrandToModelFunction();
    }

    public BrandToNoMotorcyclesModelFunction brandToNoMotorcyclesModel() {
        return new BrandToNoMotorcyclesModelFunction();
    }

    public MotorcycleToModelFunction motorcycleToModel() {
        return new MotorcycleToModelFunction();
    }

    public ModelToMotorcycleFunction modelToMotorcycle() {
        return new ModelToMotorcycleFunction();
    }

    public UpdateMotorcycleWithModelFunction updateMotorcycleWithModel() {
        return new UpdateMotorcycleWithModelFunction();
    }

    public MotorcycleToEditModelFunction motorcycleToEditModel() {
        return new MotorcycleToEditModelFunction();
    }

}
