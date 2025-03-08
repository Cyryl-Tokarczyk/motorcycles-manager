package ct.pjee.motorcycles.motorcycle.repository.persistence;

import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import ct.pjee.motorcycles.motorcycle.repository.api.MotorcycleRepository;
import ct.pjee.motorcycles.user.entity.User;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class MotorcyclePersistenceRepository implements MotorcycleRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Motorcycle> findById(UUID id) {
        if (em.find(Motorcycle.class, id) != null) {
            em.refresh(em.find(Motorcycle.class, id));
        }
        return Optional.ofNullable(em.find(Motorcycle.class, id));
    }

    public Optional<Motorcycle> findByUserAndId(User user, UUID id) {
        try {
            return Optional.of(em.createQuery("select m from Motorcycle m where m.id = :id and m.user = :user", Motorcycle.class)
                    .setParameter("user", user)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Motorcycle> findAll() {
        return em.createQuery("select m from Motorcycle m", Motorcycle.class).getResultList();
    }

    @Override
    public void create(Motorcycle motorcycle) {
        em.persist(motorcycle);
    }

    @Override
    public void delete(Motorcycle motorcycle) {
        em.remove(em.find(Motorcycle.class, motorcycle.getId()));
    }

    @Override
    public void delete(UUID id) {
        em.remove(em.find(Motorcycle.class, id));
    }

    @Override
    public void update(Motorcycle motorcycle) {
        em.merge(motorcycle);
    }
}
