package ct.pjee.motorcycles.configuration.singleton;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.repository.api.BrandRepository;
import ct.pjee.motorcycles.motorcycle.repository.api.MotorcycleRepository;
import ct.pjee.motorcycles.user.entity.User;
import ct.pjee.motorcycles.user.entity.UserRoles;
import ct.pjee.motorcycles.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InitializedData {

    private UserRepository userRepository;

    private MotorcycleRepository motorcycleRepository;

    private BrandRepository brandRepository;

    private final RequestContextController requestContextController;

    private final Pbkdf2PasswordHash hasher;

    @Inject
    public InitializedData(
            final UserRepository userRepository,
            final MotorcycleRepository motorcycleRepository,
            final BrandRepository brandRepository,
            final RequestContextController requestContextController,
            final Pbkdf2PasswordHash hasher) {
        this.userRepository = userRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.brandRepository = brandRepository;
        this.requestContextController = requestContextController;
        this.hasher = hasher;
    }

    @Transactional
    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();
        if (userRepository.findById(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6")).isEmpty()) {

            User admin = User.builder()
                    .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .username("admin")
                    .password(hasher.generate("admin".toCharArray()))
                    .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                    .build();

            User cyryl = User.builder()
                    .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                    .username("cyryl")
                    .password(hasher.generate("cyryl".toCharArray()))
                    .roles(List.of(UserRoles.USER))
                    .build();

            User oskar = User.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                    .username("oskar")
                    .password(hasher.generate("oskar".toCharArray()))
                    .roles(List.of(UserRoles.USER))
                    .build();

            User franek = User.builder()
                    .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                    .username("franek")
                    .password(hasher.generate("franek".toCharArray()))
                    .roles(List.of(UserRoles.USER))
                    .build();

            userRepository.create(admin);
            userRepository.create(cyryl);
            userRepository.create(oskar);
            userRepository.create(franek);

            Brand yamaha = Brand.builder()
                    .id(UUID.fromString("122820a9-328b-44c9-ac28-dc609df8037c"))
                    .name("Yamaha")
                    .dateOfFounding(LocalDate.of(1887, 10, 12))
                    .numberOfEmployees(28112)
                    .build();

            Brand kawasaki = Brand.builder()
                    .id(UUID.fromString("410d9780-903e-4932-8887-717aa239ba6c"))
                    .name("Kawasaki")
                    .dateOfFounding(LocalDate.of(1887, 10, 12))
                    .numberOfEmployees(28112)
                    .build();

            brandRepository.create(yamaha);
            brandRepository.create(kawasaki);

            Motorcycle xj6 = Motorcycle.builder()
                    .id(UUID.fromString("199820a9-328b-44c9-ac28-dc609df8037c"))
                    .name("XJ-6")
                    .displacement(600)
                    .brand(yamaha)
                    .user(cyryl)
                    .build();

            Motorcycle r1 = Motorcycle.builder()
                    .id(UUID.fromString("1669404c-2b91-4305-8e75-e249d2f3c9d6"))
                    .name("YZF-R1")
                    .displacement(998)
                    .brand(yamaha)
                    .user(oskar)
                    .build();

            Motorcycle z650 = Motorcycle.builder()
                    .id(UUID.fromString("7cf3893c-6e04-40e1-9f43-ee79ab497876"))
                    .name("Z650")
                    .displacement(649)
                    .brand(kawasaki)
                    .user(franek)
                    .build();

            motorcycleRepository.create(xj6);
            motorcycleRepository.create(r1);
            motorcycleRepository.create(z650);
        }

        requestContextController.deactivate();
    }
}
