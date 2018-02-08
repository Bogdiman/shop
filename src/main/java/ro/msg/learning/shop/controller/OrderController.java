package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.dto.OrderRequestDTO;
import ro.msg.learning.shop.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseBody
    public Order newOrder(@RequestBody OrderRequestDTO request) throws Exception {
        return orderService.createOrder(request);
    }
}
