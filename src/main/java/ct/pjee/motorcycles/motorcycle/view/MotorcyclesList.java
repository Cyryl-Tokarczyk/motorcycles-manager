package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import ct.pjee.motorcycles.motorcycle.model.MotorcyclesModel;
import ct.pjee.motorcycles.motorcycle.service.MotorcycleService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class MotorcyclesList {

    private MotorcycleService service;

    private MotorcyclesModel motorcycles;

    private final ModelFunctionFactory factory;

    @Inject
    public MotorcyclesList(final ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(final MotorcycleService service) {
        this.service = service;
    }

    public MotorcyclesModel getMotorcycles() {
        if (motorcycles == null) {
            motorcycles = factory.motorcyclesToModel().apply(service.findAllForCallerPrincipal());
        }
        return motorcycles;
    }

    public String deleteAction(MotorcyclesModel.Motorcycle motorcycle) {
        service.delete(motorcycle.getId());
        return "character_list?faces-redirect=true";
    }

}
