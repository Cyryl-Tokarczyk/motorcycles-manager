package ct.pjee.motorcycles.repository.api;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {

    Optional<E> findById(K id);

    List<E> findAll();

    void create(E e);

    void delete(E e);

    void update(E e);

}
