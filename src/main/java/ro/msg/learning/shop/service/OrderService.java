package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.config.LocationSelectionStrategy;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.dto.OrderRequestDTO;
import ro.msg.learning.shop.model.dto.ProductRequestDTO;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;

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
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;

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

            orderDetailRepository.save(orderDetail);
        }

        return newOrder;
    }
}
