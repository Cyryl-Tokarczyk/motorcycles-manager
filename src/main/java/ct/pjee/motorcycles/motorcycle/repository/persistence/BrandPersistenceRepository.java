package ct.pjee.motorcycles.motorcycle.repository.persistence;

import ct.pjee.motorcycles.motorcycle.entity.Brand;
import ct.pjee.motorcycles.motorcycle.repository.api.BrandRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class BrandPersistenceRepository implements BrandRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Brand> findById(UUID id) {
        if (em.find(Brand.class, id) != null) {
            em.refresh(em.find(Brand.class, id));
        }
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    @Override
    public List<Brand> findAll() {
        return em.createQuery("select b from Brand b", Brand.class).getResultList();
    }

    @Override
    public void create(Brand brand) {
        em.persist(brand);
    }

    @Override
    public void delete(Brand brand) {
        em.remove(em.find(Brand.class, brand.getId()));
    }

    @Override
    public void delete(UUID id) {
        em.remove(em.find(Brand.class, id));
    }

    @Override
    public void update(Brand brand) {
        em.merge(brand);
    }
}
