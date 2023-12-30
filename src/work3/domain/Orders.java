package work3.domain;



import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class Orders implements Serializable {
    private int orderId;
    private Products Products;
    private Date orderTime;
    private double totalPrice;

    public Orders() {
    }

    public Orders(int orderId, work3.domain.Products products, Date orderTime, double totalPrice) {
        this.orderId = orderId;
        Products = products;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public work3.domain.Products getProducts() {
        return Products;
    }

    public void setProducts(work3.domain.Products products) {
        Products = products;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", " + Products.toString() +
                ", orderTime=" + orderTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

