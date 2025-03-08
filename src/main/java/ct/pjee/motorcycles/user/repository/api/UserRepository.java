package ct.pjee.motorcycles.user.repository.api;

import ct.pjee.motorcycles.repository.api.Repository;
import ct.pjee.motorcycles.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void create(User user);

    void delete(User user);

    void update(User user);

//    Optional<byte[]> findAvatarById(UUID id);
//
//    void createAvatar(UUID id, InputStream inputStream);
//
//    void deleteAvatar(UUID id);

}
