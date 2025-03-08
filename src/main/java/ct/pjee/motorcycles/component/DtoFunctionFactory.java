package ct.pjee.motorcycles.component;

import ct.pjee.motorcycles.motorcycle.dto.function.*;
import ct.pjee.motorcycles.user.dto.function.RequestToUserFunction;
import ct.pjee.motorcycles.user.dto.function.UserToResponseFunction;
import ct.pjee.motorcycles.user.dto.function.UsersToResponseFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DtoFunctionFactory {

    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    public MotorcyclesToResponseFunction motorcyclesToResponse() {
        return new MotorcyclesToResponseFunction();
    }

    public MotorcycleToResponseFunction motorcycleToResponse() {
        return new MotorcycleToResponseFunction();
    }

    public RequestToMotorcycleFunction requestToMotorcycle() {
        return new RequestToMotorcycleFunction();
    }

    public UpdateMotorcycleWithRequestFunction updateMotorcycleWithRequest() {
        return new UpdateMotorcycleWithRequestFunction();
    }

    public BrandsToResponseFunction brandsToResponse() {
        return new BrandsToResponseFunction();
    }

    public BrandToResponseFunction brandToResponse() {
        return new BrandToResponseFunction();
    }

    public RequestToBrandFunction requestToBrand() {
        return new RequestToBrandFunction();
    }

    public UpdateBrandWithRequestFunction updateBrandWithRequest() {
        return new UpdateBrandWithRequestFunction();
    }

}
