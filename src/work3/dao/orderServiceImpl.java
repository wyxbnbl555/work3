package work3.dao;

import work3.domain.Orders;
import work3.domain.Products;

import java.util.ArrayList;

public class orderServiceImpl implements orderService{
    private orderDao dao = new orderDaoImpl();

    @Override
    public ArrayList<Orders> findAllOrderImformation() {
        return dao.findAllOrderImformation();
    }

    @Override
    public Orders findOrderImformationById(int orderId) {
        return dao.findOrderImformationById(orderId);
    }

    @Override
    public ArrayList<Products> findAllProducts() {
        return dao.findAllProducts();
    }

    @Override
    public Products findProductById(int id) {
        return dao.findProductById(id);
    }

    @Override
    public int productInsert(Products product) {
        return dao.productInsert(product);
    }

    @Override
    public int OrderInsert(Orders order) {
        return dao.OrderInsert(order);
    }


    @Override
    public int productDelete(int p_id) {
        return dao.productDelete(p_id);
    }

    @Override
    public int orderDelete(int o_id) {
        return dao.orderDelete(o_id);
    }

    @Override
    public int productUpdate(Products product) {
        return dao.productUpdate(product);
    }

    @Override
    public int orderUpdate(Orders order) {
        return dao.orderUpdate(order);
    }


    @Override
    public ArrayList<Orders> sortOrderByTime() {
        return dao.sortOrderByTime();
    }

    @Override
    public ArrayList<Orders> sortOrderByPrice() {
        return dao.sortOrderByPrice();
    }
}
