package ct.pjee.motorcycles.motorcycle.service;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.repository.api.BrandRepository;
import ct.pjee.motorcycles.motorcycle.repository.api.MotorcycleRepository;
import ct.pjee.motorcycles.user.entity.User;
import ct.pjee.motorcycles.user.entity.UserRoles;
import ct.pjee.motorcycles.user.repository.api.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Inject
    public MotorcycleService(
            MotorcycleRepository motorcycleRepository,
            BrandRepository brandRepository,
            UserRepository userRepository,
            SecurityContext securityContext) {
        this.motorcycleRepository = motorcycleRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Motorcycle> find(UUID id) {
        return motorcycleRepository.findById(id);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Motorcycle> find(User user, UUID id) {
        return motorcycleRepository.findByUserAndId(user, id);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Motorcycle> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return find(id);
        }
        User user = userRepository.findByUsername(securityContext.getCallerPrincipal().getName()).orElseThrow(IllegalStateException::new);
        return find(user, id);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<Motorcycle> findAll() {
        return motorcycleRepository.findAll();
    }

    @RolesAllowed(UserRoles.USER)
    public List<Motorcycle> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findAll();
        }
        User user = userRepository.findByUsername(securityContext.getCallerPrincipal().getName()).orElseThrow(IllegalStateException::new);
        return user.getMotorcycles();
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void create(Motorcycle motorcycle) {
        if (motorcycleRepository.findById(motorcycle.getId()).isPresent()) {
            throw new IllegalArgumentException("Motorcycle already exists");
        }
        //if (brandRepository.findById(motorcycle.getBrand().getId()).isEmpty()) {
         //   throw new IllegalArgumentException("Brand not found");
        //}
        motorcycleRepository.create(motorcycle);
    }

    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Motorcycle motorcycle) {
        User user = userRepository.findByUsername(securityContext.getCallerPrincipal().getName()).orElseThrow(IllegalStateException::new);
        motorcycle.setUser(user);
        create(motorcycle);
    }

    @RolesAllowed(UserRoles.USER)
    public void update(Motorcycle motorcycle) {
        checkAdminRoleOrOwner(motorcycleRepository.findById(motorcycle.getId()));
        motorcycleRepository.update(motorcycle);
    }

    @RolesAllowed(UserRoles.USER)
    public void updateBrand(Motorcycle motorcycle, Brand brand) {
        motorcycle.setBrand(brand);
        motorcycleRepository.update(motorcycle);
    }

    @RolesAllowed(UserRoles.USER)
    public void delete(Motorcycle motorcycle) {
        checkAdminRoleOrOwner(motorcycleRepository.findById(motorcycle.getId()));
        motorcycleRepository.delete(motorcycleRepository.findById(motorcycle.getId()).orElseThrow(NotFoundException::new));
    }

    @RolesAllowed(UserRoles.USER)
    public void delete(UUID id) {
        checkAdminRoleOrOwner(motorcycleRepository.findById(id));
        motorcycleRepository.delete(motorcycleRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<Motorcycle> findMotorcyclesByBrand(Brand brand) {
        return brand.getMotorcycles();
    }

    @RolesAllowed(UserRoles.USER)
    public List<Motorcycle> findMotorcyclesByBrandForCallerPrincipal(Brand brand) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findMotorcyclesByBrand(brand);
        }
        User user = userRepository.findByUsername(securityContext.getCallerPrincipal().getName()).orElseThrow(IllegalStateException::new);
        return brand.getMotorcycles().stream().filter(motorcycle -> motorcycle.getUser().getId() == user.getId()).toList();
    }

    @RolesAllowed(UserRoles.USER)
    public List<Motorcycle> findMotorcyclesByUser(User user) {
        return user.getMotorcycles();
    }

    private void checkAdminRoleOrOwner(Optional<Motorcycle> motorcycle) throws SecurityException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && motorcycle.isPresent()
                && motorcycle.get().getUser().getUsername().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }


}
