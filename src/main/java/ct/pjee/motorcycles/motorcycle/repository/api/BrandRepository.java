package ct.pjee.motorcycles.motorcycle.repository.api;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.repository.api.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends Repository<Brand, UUID> {

    Optional<Brand> findById(UUID id);

    List<Brand> findAll();

    void create(Brand brand);

    void delete(Brand brand);

    void delete(UUID id);

    void update(Brand brand);
    
}
