package ro.msg.learning.shop.model.dto;

import ro.msg.learning.shop.model.Location;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {
    private Date dateOfOrder;
    private Location address;
    private List<ProductRequestDTO> products;

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public List<ProductRequestDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestDTO> products) {
        this.products = products;
    }
}
