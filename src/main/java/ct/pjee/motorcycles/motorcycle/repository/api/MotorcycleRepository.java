package ct.pjee.motorcycles.motorcycle.repository.api;

import ct.pjee.motorcycles.repository.api.Repository;
import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MotorcycleRepository extends Repository<Motorcycle, UUID> {

    Optional<Motorcycle> findById(UUID id);

    Optional<Motorcycle> findByUserAndId(User user, UUID id);

    List<Motorcycle> findAll();

    void create(Motorcycle motorcycle);

    void delete(Motorcycle motorcycle);

    void delete(UUID id);

    void update(Motorcycle motorcycle);

}
