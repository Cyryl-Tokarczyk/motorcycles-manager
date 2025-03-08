package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import ct.pjee.motorcycles.motorcycle.model.MotorcycleCreateModel;
import ct.pjee.motorcycles.motorcycle.model.NoMotorcyclesBrandModel;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import ct.pjee.motorcycles.motorcycle.service.MotorcycleService;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ViewScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class MotorcycleCreate implements Serializable {

    private MotorcycleService motorcycleService;

    private BrandService brandService;

    private final ModelFunctionFactory factory;

    @Getter
    private MotorcycleCreateModel motorcycle;

    @Getter
    private List<NoMotorcyclesBrandModel> brands;

    @Inject
    public MotorcycleCreate(
            ModelFunctionFactory factory
    ) {
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

    public void init() {
        brands = brandService.findAll().stream()
                .map(factory.brandToNoMotorcyclesModel())
                .collect(Collectors.toList());
        motorcycle = MotorcycleCreateModel.builder()
                .id(UUID.randomUUID())
                .build();

        System.out.println(brands);
        System.out.println(motorcycle);
    }

    public String cancelAction() {
        return "/motorcycle/brand_list.xhtml?faces-redirect=true";
    }

    public String confirmAction() {
        if (motorcycle.getBrand() == null) {
            throw new RuntimeException("Brand not found" + motorcycle.getName());
        }
        motorcycleService.createForCallerPrincipal(factory.modelToMotorcycle().apply(motorcycle));
        return "/motorcycle/motorcycle_view.xhtml?faces-redirect=true&id=" + motorcycle.getId();
    }

}
