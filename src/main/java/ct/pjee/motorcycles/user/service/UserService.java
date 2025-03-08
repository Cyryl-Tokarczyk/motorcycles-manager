package ct.pjee.motorcycles.user.service;

import ct.pjee.motorcycles.user.entity.User;
import ct.pjee.motorcycles.user.entity.UserRoles;
import ct.pjee.motorcycles.user.repository.api.UserRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class UserService {

    private final UserRepository repository;

    private final Pbkdf2PasswordHash hasher;

    @Inject
    public UserService(UserRepository repository, Pbkdf2PasswordHash hasher) {
        this.repository = repository;
        this.hasher = hasher;
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Optional<User> find(UUID id) {
        return repository.findById(id);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Optional<User> find(String username) {
        return repository.findByUsername(username);
    }

//    public Optional<byte[]> findAvatar(UUID id) {
//        return repository.findAvatarById(id);
//    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<User> findAll() {
        return repository.findAll();
    }

    @PermitAll
    public void create(User user) {
        user.setPassword(hasher.generate(user.getPassword().toCharArray()));
        repository.create(user);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void delete(UUID id) {
        repository.delete(repository.findById(id).orElseThrow(NotFoundException::new));
    }

    @PermitAll
    public boolean verify(String username, String password) {
        return find(username)
                .map(user -> hasher.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }

//    public void createAvatar(UUID id, InputStream inputStream) {
//        repository.createAvatar(id, inputStream);
//    }
//
//    public void deleteAvatar(UUID id) {
//        repository.deleteAvatar(id);
//    }

}
