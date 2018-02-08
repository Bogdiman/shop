package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void subtractQuantityFromStock(int stockId, int quantitySold) {
        Stock stock = stockRepository.findOne(stockId);
        stock.setQuantity(stock.getQuantity() - quantitySold);

        stockRepository.save(stock);
    }
}
