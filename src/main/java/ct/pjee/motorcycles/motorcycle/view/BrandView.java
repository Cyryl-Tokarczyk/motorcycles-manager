package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.model.BrandModel;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import ct.pjee.motorcycles.motorcycle.service.MotorcycleService;
import jakarta.ejb.EJB;
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
public class BrandView implements Serializable {

    private BrandService brandService;

    private MotorcycleService motorcycleService;

    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BrandModel brand;

    @Inject
    public BrandView(ModelFunctionFactory factory) {
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
        Optional<Brand> brand = brandService.find(id);
        if (brand.isPresent()) {
            this.brand = factory.brandToModel().apply(brand.get(), motorcycleService.findMotorcyclesByBrandForCallerPrincipal(brand.get()));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Brand not found");
        }
    }

    public String deleteMotorcycleAction(BrandModel.Motorcycle motorcycle) {
        motorcycleService.delete(motorcycle.getId());
        return "brand_view?faces-redirect=true&includeViewParams=true";
    }

}
