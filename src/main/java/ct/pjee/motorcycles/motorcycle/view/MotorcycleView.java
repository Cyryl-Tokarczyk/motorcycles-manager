package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.ws.rs.NotFoundException;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleModel;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import ct.pjee.motorcycles.motorcycle.service.MotorcycleService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class MotorcycleView implements Serializable {

    private BrandService brandService;

    private MotorcycleService motorcycleService;

    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private MotorcycleModel motorcycle;

    @Inject
    public MotorcycleView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @EJB
    public void setMotorcycleService(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    public void init() throws IOException {
        Optional<Motorcycle> motorcycle = motorcycleService.findForCallerPrincipal(id);
        if (motorcycle.isPresent()) {
            this.motorcycle = factory.motorcycleToModel().apply(motorcycle.get(), brandService.find(motorcycle.get().getBrand().getId()).orElseThrow(NotFoundException::new));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Motorcycle not found");
        }
    }

}
