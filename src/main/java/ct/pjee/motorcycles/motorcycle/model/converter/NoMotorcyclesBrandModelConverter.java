package ct.pjee.motorcycles.motorcycle.model.converter;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import jakarta.ws.rs.NotFoundException;
import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.model.NoMotorcyclesBrandModel;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.UUID;

@FacesConverter(forClass = NoMotorcyclesBrandModel.class, managed = true)
public class NoMotorcyclesBrandModelConverter implements Converter<NoMotorcyclesBrandModel> {

    private final BrandService brandService;

    private final ModelFunctionFactory factory;

    @Inject
    public NoMotorcyclesBrandModelConverter(BrandService brandService, ModelFunctionFactory factory) {
        System.out.println("NoMotorcyclesBrandModelConverter constructor");
        this.brandService = brandService;
        this.factory = factory;
    }

    @Override
    public NoMotorcyclesBrandModel getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsObject");
        if (value == null || value.isBlank()) {
            return null;
        }

        Brand brand = brandService.find(UUID.fromString(value)).orElseThrow(NotFoundException::new);
        return factory.brandToNoMotorcyclesModel().apply(brand);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, NoMotorcyclesBrandModel value) {
        System.out.println("getAsString");
        if (value == null) {
            return "";
        }

        return value.getId().toString();
    }

}
