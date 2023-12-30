package work3.dao;

import work3.domain.Orders;
import work3.domain.Products;

import java.util.ArrayList;

public interface orderService {
    public abstract ArrayList<Orders> findAllOrderImformation();
    public abstract Orders findOrderImformationById(int orderId);
    public abstract ArrayList<Products> findAllProducts();
    public abstract Products findProductById(int id);
    public abstract int productInsert(Products product);
    public abstract int OrderInsert(Orders order);
    public abstract int productDelete(int p_id);
    public abstract int orderDelete(int o_id);
    public abstract int productUpdate(Products product);
    public abstract int orderUpdate(Orders order);
    public abstract ArrayList<Orders> sortOrderByTime();
    public abstract ArrayList<Orders> sortOrderByPrice();
}
