package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.dto.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}
