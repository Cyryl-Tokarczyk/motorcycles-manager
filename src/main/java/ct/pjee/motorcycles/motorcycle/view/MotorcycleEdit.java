package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.OptimisticLockException;
import jakarta.ws.rs.NotFoundException;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleEditModel;
import ct.pjee.motorcycles.motorcycle.model.NoMotorcyclesBrandModel;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class MotorcycleEdit implements Serializable {

    private MotorcycleService motorcycleService;

    private BrandService brandService;

    private final ModelFunctionFactory factory;

    private final FacesContext facesContext;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private MotorcycleEditModel motorcycle;

    @Getter
    private List<NoMotorcyclesBrandModel> brands;

    @Inject
    public MotorcycleEdit(
            ModelFunctionFactory factory,
            FacesContext facesContext
    ) {
        this.factory = factory;
        this.facesContext = facesContext;
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
            this.motorcycle = factory.motorcycleToEditModel().apply(motorcycle.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Motorcycle not found");
        }
    }

    public String saveAction() throws IOException {
        try {
            motorcycleService.update(factory.updateMotorcycleWithModel().apply(motorcycleService.find(id).orElseThrow(NotFoundException::new), motorcycle));
            return "motorcycle_view.xhtml?faces-redirect=true&includeViewParams=true";
        } catch (EJBException e) {
            if (e.getCause() instanceof OptimisticLockException) {
                init();
                facesContext.addMessage(null, new FacesMessage("Version collision."));
            }
            return null;
        }
    }

}
