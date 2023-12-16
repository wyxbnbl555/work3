package com.kuang.work3;

import java.util.ArrayList;

public interface orderDao {
    public abstract ArrayList<products> findAllProducts();
    public abstract products findProductById(int id);
    public abstract ArrayList<bigOrder> findAllOrder();
    public abstract bigOrder findOrderById(int id);
    public abstract int productInsert(products product);

    public abstract int OrderInsert(orders order);
    public abstract int productDelete(int p_id);
    public abstract int orderDelete(int o_id);
    public abstract int productUpdate(products product);
    public abstract int orderUpdate(orders order);
    public abstract ArrayList<bigOrder> sortOrderByTime();
    public abstract ArrayList<bigOrder> sortOrderByPrice();

}
