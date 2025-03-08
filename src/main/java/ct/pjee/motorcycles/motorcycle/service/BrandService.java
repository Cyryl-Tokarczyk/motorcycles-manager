package ct.pjee.motorcycles.motorcycle.service;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.repository.api.BrandRepository;
import ct.pjee.motorcycles.user.entity.UserRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class BrandService {

    private final BrandRepository repository;

    private final MotorcycleService motorcycleService;

    @Inject
    public BrandService(BrandRepository repository, MotorcycleService motorcycleService) {
        this.repository = repository;
        this.motorcycleService = motorcycleService;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Brand> find(UUID id) {
        return repository.findById(id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void create(Brand brand) {
        repository.create(brand);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void update(Brand brand) { repository.update(brand); }

    @RolesAllowed(UserRoles.ADMIN)
    public void updateMotorcycles(Brand brand, List<Motorcycle> motorcycles) {
        brand.setMotorcycles(motorcycles);
        repository.update(brand);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void delete(UUID id) {
        motorcycleService.findAll().stream()
                        .filter(motorcycle -> motorcycle.getBrand().getId().equals(id))
                        .forEach(motorcycle -> motorcycleService.delete(motorcycle));
        repository.delete(id);
    }


}
