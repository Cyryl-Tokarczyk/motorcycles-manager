package ct.pjee.motorcycles.user.repository.persistence;

import ct.pjee.motorcycles.user.entity.User;
import ct.pjee.motorcycles.user.repository.api.UserRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class UserPersistenceRepository implements UserRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(em.find(User.class, user.getId()));
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

//    @Override
//    public Optional<byte[]> findAvatarById(UUID id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void createAvatar(UUID id, InputStream inputStream) {
//
//    }
//
//    @Override
//    public void deleteAvatar(UUID id) {
//
//    }
}
