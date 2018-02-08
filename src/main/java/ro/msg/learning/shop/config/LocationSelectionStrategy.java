package ro.msg.learning.shop.config;

import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.dto.OrderRequestDTO;
import ro.msg.learning.shop.model.dto.ProductRequestDTO;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.utils.LocationStrategy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocationSelectionStrategy {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Value("${location.strategy}")
    private LocationStrategy strategy;

    public Location findSuitableLocation(OrderRequestDTO request) throws Exception {
        // Replace with a switch when multiple strategies are possible. See this.strategy
        return findFirstStrategy(request);
    }

    private Location findFirstStrategy(OrderRequestDTO request) throws Exception {
        Map<Integer, Integer> locationOccurrences = new HashMap<>();

        List<Location> allLocations = locationRepository.findAll();

        for (Location location : allLocations) {
            locationOccurrences.put(location.getId(), 0);
        }

        for (ProductRequestDTO productReq : request.getProducts()) {
            for (Stock s : stockRepository.findByProductIdAndQuantityIsGreaterThanEqual(productReq.getId(),
                    productReq.getQuantity())) {
                locationOccurrences.put(s.getLocation().getId(), locationOccurrences.get(s.getLocation().getId()) + 1);
            }
        }

        int bestLocation = (int) ((Map.Entry) Collections.max(locationOccurrences.entrySet(), Comparator.comparingInt(entry -> (int) ((Map.Entry) entry).getValue()))).getKey();
        if (bestLocation >= request.getProducts().size()) {
            return locationRepository.findOne(bestLocation);
        } else {
            throw new Exception("A single location with all the products was not found!");
        }

    }
}
