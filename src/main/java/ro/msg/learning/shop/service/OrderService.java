package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.config.LocationSelectionStrategy;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.model.dto.OrderRequestDTO;
import ro.msg.learning.shop.model.dto.ProductRequestDTO;
import ro.msg.learning.shop.repository.*;

import javax.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LocationSelectionStrategy locationStrategy;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Order createOrder(OrderRequestDTO request) throws Exception {
        Location location = locationStrategy.findSuitableLocation(request);

        Order newOrder = new Order();
        newOrder.setShippedFrom(location);
        newOrder.setCustomer(customerRepository.findAll().get(0));

        for (ProductRequestDTO prod : request.getProducts()) {
            Product productEntity = productRepository.findOne(prod.getId());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(productEntity);
            orderDetail.setQuantity(prod.getQuantity());

            removeProductsFromStock(location, productEntity, prod.getQuantity());
            orderDetailRepository.save(orderDetail);
        }


        return newOrder;
    }

    private void removeProductsFromStock(Location location, Product productEntity, int quantity) {
        Stock stock = stockRepository.findByProductIdAndLocationId(productEntity.getId(), location.getId());
        stockService.subtractQuantityFromStock(stock.getId(), quantity);
    }
}
