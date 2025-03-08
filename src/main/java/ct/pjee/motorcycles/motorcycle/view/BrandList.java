package ct.pjee.motorcycles.motorcycle.view;

import ct.pjee.motorcycles.component.ModelFunctionFactory;
import ct.pjee.motorcycles.motorcycle.model.BrandsModel;
import ct.pjee.motorcycles.motorcycle.service.BrandService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class BrandList {

    private BrandService service;

    private BrandsModel brands;

    private final ModelFunctionFactory factory;

    @Inject
    public BrandList(final ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(final BrandService service) {
        this.service = service;
    }

    public BrandsModel getBrands() {
        if (brands == null) {
            brands = factory.brandsToModel().apply(service.findAll());
        }
        return brands;
    }

    public String deleteAction(BrandsModel.Brand brand) {
        service.delete(brand.getId());
        return "brand_list?faces-redirect=true";
    }

}
