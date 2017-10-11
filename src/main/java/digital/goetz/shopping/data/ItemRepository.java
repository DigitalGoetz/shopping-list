package digital.goetz.shopping.data;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

	Collection<Item> findByState(State state);
}
