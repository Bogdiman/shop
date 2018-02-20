package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public static final String FILE_NAME = "stocks.csv";

    public void subtractQuantityFromStock(int stockId, int quantitySold) {
        Stock stock = stockRepository.findOne(stockId);
        stock.setQuantity(stock.getQuantity() - quantitySold);

        stockRepository.save(stock);
    }

    public void exportStocksToCsv(int locationId) throws IOException {
        List<Stock> stocks = stockRepository.findByLocationId(locationId);
        final String FILE_HEADER = "id,name,quantity";

        List<String> lines = new ArrayList<>();
        lines.add(FILE_HEADER);

        for (Stock s : stocks) {
            lines.add(s.toCsvFormat());
        }
        Path file = Paths.get(FILE_NAME);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
