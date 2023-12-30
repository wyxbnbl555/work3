package work3.dao;


import work3.domain.Orders;
import work3.domain.Products;
import work3.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class orderDaoImpl implements orderDao {

    @Override
    public ArrayList<Orders> findAllOrderImformation() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Orders> list = new ArrayList<>();

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT o_id,o_time,p_num,`products`.p_id,p_name,p_price,t_price FROM `orders`,`products` WHERE `products`.p_id = `orders`.p_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int o_id = rs.getInt(1);
                Date o_time = rs.getDate(2);
                int p_num = rs.getInt(3);
                int p_id = rs.getInt(4);
                String p_name = rs.getString(5);
                double p_price = rs.getDouble(6);
                double t_price = rs.getDouble(7);
                Products product = new Products(p_id, p_name, p_price, p_num);
                Orders order = new Orders(o_id, product, (java.sql.Date) o_time, t_price);
                list.add(order);
                conn.commit();
            }

        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public Orders findOrderImformationById(int orderId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Orders order = new Orders();
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT o_id,o_time,p_num,`products`.p_id,p_name,p_price,t_price FROM `orders`,`products` WHERE `products`.p_id = `orders`.p_id AND o_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int o_id = rs.getInt(1);
                Date o_time = rs.getDate(2);
                int p_num = rs.getInt(3);
                int p_id = rs.getInt(4);
                String p_name = rs.getString(5);
                double p_price = rs.getDouble(6);
                double t_price = rs.getDouble(7);
                Products product = new Products(p_id, p_name, p_price, p_num);
                order = new Orders(o_id, product, (java.sql.Date) o_time, t_price);
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return order;
    }

    @Override
    public ArrayList<Products> findAllProducts() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Products> list = new ArrayList<>();

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT p_id,p_name,p_price FROM products";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int p_id = rs.getInt(1);
                String p_name = rs.getString(2);
                double p_price = rs.getDouble(3);
                Products product = new Products(p_id, p_name, p_price);
                list.add(product);

            }

        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public Products findProductById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Products product = new Products();
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT p_id,p_name,p_price FROM products WHERE p_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int p_id = rs.getInt(1);
                String p_name = rs.getString(2);
                double p_price = rs.getDouble(3);
                product = new Products(p_id, p_name, p_price);

            }

        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return product;
    }

    @Override
    public int productInsert(Products product) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "INSERT INTO `products` (p_name,p_price) VALUES(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public int OrderInsert(Orders order) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "INSERT INTO `orders` (o_time,p_id,p_num,t_price) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, order.getOrderTime());
            ps.setInt(2, order.getProducts().getProductId());
            ps.setInt(3, order.getProducts().getProductNum());
            ps.setDouble(4, order.getProducts().getProductPrice() * order.getProducts().getProductNum());
            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public int productDelete(int p_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "DELETE FROM `products` WHERE p_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p_id);

            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public int orderDelete(int o_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "DELETE FROM `orders` WHERE o_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, o_id);

            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public int productUpdate(Products product) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "UPDATE `products` SET p_name = ?,p_price = ? WHERE p_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            ps.setInt(3, product.getProductId());

            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public int orderUpdate(Orders order) {
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "UPDATE `orders` SET o_time = ?,p_id = ?,p_num = ?,t_price = ? WHERE o_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, order.getOrderTime());
            ps.setInt(2, order.getProducts().getProductId());
            ps.setInt(3, order.getProducts().getProductNum());
            ps.setDouble(4, order.getProducts().getProductNum() * order.getProducts().getProductPrice());
            ps.setInt(5, order.getOrderId());

            i = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, null);
        }
        return i;
    }

    @Override
    public ArrayList<Orders> sortOrderByTime() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Orders> list = new ArrayList<>();

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT o_id,o_time,p_num,`products`.p_id,p_name,p_price,t_price FROM `orders`,`products` WHERE `products`.p_id = `orders`.p_id ORDER BY o_time ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int o_id = rs.getInt(1);
                Date o_time = rs.getDate(2);
                int p_num = rs.getInt(3);
                int p_id = rs.getInt(4);
                String p_name = rs.getString(5);
                double p_price = rs.getDouble(6);
                double t_price = rs.getDouble(7);
                Products product = new Products(p_id, p_name, p_price, p_num);
                Orders order = new Orders(o_id, product, (java.sql.Date) o_time, t_price);
                list.add(order);
                conn.commit();
            }

        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public ArrayList<Orders> sortOrderByPrice() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Orders> list = new ArrayList<>();

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT o_id,o_time,p_num,`products`.p_id,p_name,p_price,t_price FROM `orders`,`products` WHERE `products`.p_id = `orders`.p_id ORDER BY t_price ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int o_id = rs.getInt(1);
                Date o_time = rs.getDate(2);
                int p_num = rs.getInt(3);
                int p_id = rs.getInt(4);
                String p_name = rs.getString(5);
                double p_price = rs.getDouble(6);
                double t_price = rs.getDouble(7);
                Products product = new Products(p_id, p_name, p_price, p_num);
                Orders order = new Orders(o_id, product, (java.sql.Date) o_time, t_price);
                list.add(order);
                conn.commit();
            }

        } catch (SQLException e) {
            try {
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, ps, rs);
        }
        return list;
    }
}
