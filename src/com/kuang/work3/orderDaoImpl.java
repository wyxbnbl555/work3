package com.kuang.work3;

import java.sql.*;
import java.util.ArrayList;

public class orderDaoImpl implements orderDao{
//查询所有的商品信息
    @Override
    public ArrayList<products> findAllProducts() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList<products> list1 = new ArrayList<>();

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT p_id,p_name,p_price FROM products";
            rs = st.executeQuery(sql);

            while(rs.next()){
                int p_id = rs.getInt("p_id");
                String p_name = rs.getString("p_name");
                double p_price = rs.getDouble("p_price");

                products product = new products(p_id,p_name,p_price);
                list1.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
        return list1;
    }

    @Override
    public products findProductById(int id) {
        products product = new products();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList<products> list = new ArrayList<>();
        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT p_id,p_name,p_price FROM products WHERE p_id = '"+id+"'";
            rs = st.executeQuery(sql);

            while(rs.next()){
                int p_id = rs.getInt("p_id");
                String p_name = rs.getString("p_name");
                double p_price = rs.getDouble("p_price");

                product.setProductId(p_id);
                product.setProductName(p_name);
                product.setProductPrice(p_price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
        return product;


    }

    @Override
    public ArrayList<bigOrder> findAllOrder() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList<bigOrder> list2 = new ArrayList<>();

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT o_id,o_time,orders.p_id,p_name,p_price FROM orders INNER JOIN products WHERE orders.p_id = products.p_id";
            rs = st.executeQuery(sql);

            while(rs.next()){
                int o_id = rs.getInt(1);
                Date o_time= rs.getDate(2);
                int p_id = rs.getInt(3);
                String p_name = rs.getString(4);
                double p_price = rs.getDouble(5);

                bigOrder order = new bigOrder(o_id,o_time,p_id,p_name,p_price);
                list2.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
        return list2;
    }

    @Override
    public bigOrder findOrderById(int id) {
        bigOrder order = new bigOrder();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList<bigOrder> list = new ArrayList<>();
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "SELECT o_id,o_time,orders.p_id,p_name,p_price FROM orders INNER JOIN products WHERE orders.p_id = products.p_id AND orders.p_id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,id);

            rs = st.executeQuery();

            while(rs.next()){
                int o_id = rs.getInt(1);
                Date o_time = rs.getDate(2);
                int p_id = rs.getInt(3);
                String p_name = rs.getString(4);
                double p_price = rs.getDouble(5);

                order.setO_id(o_id);
                order.setO_time(o_time);
                order.setP_id(p_id);
                order.setP_name(p_name);
                order.setP_price(p_price);


            }
        } catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
        return order;
    }

    @Override
    public int productInsert(products product) {

        Connection conn = null;
        PreparedStatement st = null;
        int i=0;

        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            //区别
            //使用？占位符代替参数
            String sql = "INSERT INTO products(p_id,p_name,p_price) VALUES(?,?,?)";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setInt(1,product.getProductId());
            st.setString(2,product.getProductName());
            st.setDouble(3,product.getProductPrice());

            //执行
            i = st.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,null);
        }
        return i;


    }

    @Override
    public int OrderInsert(orders order) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int i = 0;

        try{
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);// 开启事务
            String sql1 = "INSERT INTO `orders`(o_id,o_time,p_id) VALUES(?,?,?)";
//            Date d = order.getOrderTime();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String o_time = sdf.format(d);
            st = conn.prepareStatement(sql1);
            st.setInt(1,order.getOrderId());
            st.setDate(2, order.getOrderTime());
            st.setInt(3,order.getProductId());
            i = st.executeUpdate();

//            String sql2 = "DELETE FROM products WHERE p_id = ?";
//            st.setInt(1,order.getProductId());
//            st = conn.prepareStatement(sql2);
//            i = st.executeUpdate();

            // 业务完毕，提交事务
            conn.commit();
        } catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
        return i;

    }

    @Override
    public int productDelete(int p_id) {
        Connection conn = null;
        PreparedStatement st = null;
        int i=0;

        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "DELETE FROM products WHERE p_id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,p_id);

            //执行
            i = st.executeUpdate(sql);
            conn.commit();
        }catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,null);
        }
        return i;


    }

    @Override
    public int orderDelete(int o_id) {
        Connection conn = null;
        PreparedStatement st = null;
        int i=0;

        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);// 开启事务
            String sql = "DELETE FROM orders WHERE o_id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,o_id);

            //执行
            i = st.executeUpdate(sql);
            conn.commit();
        }catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,null);
        }
        return i;


    }

    @Override
    public int productUpdate(products product) {
        Connection conn = null;
        String sql = "update products set p_name = ?, p_price = ? where p_id =?;";
        PreparedStatement ps = null;
        int i = 0;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,product.getProductName());
            ps.setDouble(2,product.getProductPrice());
            ps.setInt(3,product.getProductId());
            i = ps.executeUpdate();

            conn.commit();
        }catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,ps,null);
        }
        return i;

    }

    @Override
    public int orderUpdate(orders order) {
        Connection conn = null;
        String sql = "update orders set o_time = ?, o_price = ?,p_id where o_id =?;";
        PreparedStatement ps = null;
        int i = 0;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setDate(1,order.getOrderTime());
            ps.setInt(2,order.getProductId());
            ps.setInt(3,order.getOrderId());
            i = ps.executeUpdate();

            conn.commit();
        }catch (SQLException e) {
            try{
                conn.rollback();// 如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,ps,null);
        }
        return i;
    }

    @Override
    public ArrayList<bigOrder> sortOrderByTime() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList<bigOrder> list2 = new ArrayList<>();

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT o_id,o_time,orders.p_id,p_name,p_price FROM orders INNER JOIN products WHERE orders.p_id = products.p_id ORDER BY o_time ASC";
            rs = st.executeQuery(sql);

            while(rs.next()){
                int o_id = rs.getInt(1);
                Date o_time= rs.getDate(2);
                int p_id = rs.getInt(3);
                String p_name = rs.getString(4);
                double p_price = rs.getDouble(5);

                bigOrder order = new bigOrder(o_id,o_time,p_id,p_name,p_price);
                list2.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
        return list2;
    }



    @Override
    public ArrayList<bigOrder> sortOrderByPrice() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList<bigOrder> list2 = new ArrayList<>();

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT o_id,o_time,orders.p_id,p_name,p_price FROM orders INNER JOIN products WHERE orders.p_id = products.p_id ORDER BY p_price ASC";
            rs = st.executeQuery(sql);

            while(rs.next()){
                int o_id = rs.getInt(1);
                Date o_time= rs.getDate(2);
                int p_id = rs.getInt(3);
                String p_name = rs.getString(4);
                double p_price = rs.getDouble(5);

                bigOrder order = new bigOrder(o_id,o_time,p_id,p_name,p_price);
                list2.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
        return list2;
    }

}
