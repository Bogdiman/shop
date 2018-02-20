package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProductIdAndQuantityIsGreaterThanEqual(int id, int minimumQuantity);
    Stock findByProductIdAndLocationId(int prodId, int locId);
    List<Stock> findByLocationId(int locId);
}
