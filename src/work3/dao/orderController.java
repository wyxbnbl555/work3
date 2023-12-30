package work3.dao;

import work3.domain.Orders;
import work3.domain.Products;

import java.util.ArrayList;
import java.util.Scanner;

public class orderController {
        Scanner sc = new Scanner(System.in);
    private orderService service = new orderServiceImpl();
    public void findAllOrderImformation() {
        System.out.println("所有的订单如下：");
        ArrayList<Orders> list1 = service.findAllOrderImformation();
        for (Orders order1:list1) {
            System.out.println(order1);
        }
    }

    public void findOrderImformationById() {
        try {
            System.out.println("请输入要查询的订单id：");

            int o_id = sc.nextInt();
            Orders order = service.findOrderImformationById(o_id);
            if (order == null) {
                System.out.println("没有这个订单");
            } else {
                System.out.println(order.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void findAllProducts() {
        System.out.println("所有的商品如下：");
        ArrayList<Products> list3 = service.findAllProducts();
        for(Products product:list3){
            System.out.println(product.toString1());
        }
    }

    public void findProductById() {
        try {
            System.out.println("请输入要查询的id：");
            int p_id = sc.nextInt();
            Products product1 = service.findProductById(p_id);
            if (product1.getProductName() == null) {
                System.out.println("没有此商品");
            } else {
                System.out.println(product1.toString1());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void productInsert() {
        try {
            System.out.println("请输入商品名称：");
            String p_name1 = sc.next();
            System.out.println("请输入商品单价：");
            double p_price = sc.nextDouble();
            Products product2 = new Products(0, p_name1, p_price);
            int i1 = service.productInsert(product2);
            if (i1 > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void OrderInsert() {
        try {
            System.out.println("请输入商品编号：");
            int p_id2 = sc.nextInt();
            System.out.println("请输入商品数量：");
            int p_num = sc.nextInt();
            Products product3 = service.findProductById(p_id2);
            double p_price1 = product3.getProductPrice();
            String p_name = product3.getProductName();
            Products product = new Products(p_id2, p_name, p_price1, p_num);
            double t_price = p_price1 * p_num;
            Orders order1 = new Orders(0, product, new java.sql.Date(new java.util.Date().getTime()), t_price);
            int i = service.OrderInsert(order1);
            if (i > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void productDelete() {
        try {
            System.out.println("请输入要删除的商品编号：");
            int p_id2 = sc.nextInt();
            int i2 = service.productDelete(p_id2);
            if (i2 > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void orderDelete() {
        try {
            System.out.println("请输入要删除的订单编号：");
            int o_id2 = sc.nextInt();
            int i3 = service.orderDelete(o_id2);
            if (i3 > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void productUpdate() {
        try {
            System.out.println("请输入要修改的商品编号：");
            int p_id3 = sc.nextInt();
            Products product3 = service.findProductById(p_id3);
            if (product3 == null) {
                System.out.println("没有此商品");
            } else {
                System.out.println("请输入修改后的商品名称：");
                String p_name = sc.next();
                System.out.println("请输入修改后的商品价格：");
                double p_price1 = sc.nextDouble();
                Products product4 = new Products(p_id3, p_name, p_price1);
                int i4 = service.productUpdate(product4);
                if (i4 > 0) {
                    System.out.println("修改成功");
                } else {
                    System.out.println("修改失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void orderUpdate() {
        try {
            System.out.println("请输入要修改的订单编号：");
            int o_id3 = sc.nextInt();
            System.out.println("请输入修改后的商品编号：");
            int p_id = sc.nextInt();
            System.out.println("请输入修改后的商品数量：");
            int p_num = sc.nextInt();
            Products product = service.findProductById(p_id);
            product.setProductNum(p_num);
            double p_price = product.getProductPrice();
            double t_price = p_price * p_num;
            Orders order = new Orders(o_id3, product, new java.sql.Date(new java.util.Date().getTime()), t_price);
            int i = service.orderUpdate(order);
            if (i > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sortOrderByTime() {
        System.out.println("所有的订单由时间排序如下：");
        ArrayList<Orders> list4 = service.sortOrderByTime();
        for (Orders order4:list4) {
            System.out.println(order4);
        }
    }

    public void sortOrderByPrice() {
        System.out.println("所有的订单由价格排序如下：");
        ArrayList<Orders> list5 = service.sortOrderByPrice();
        for (Orders order5:list5) {
            System.out.println(order5);
        }
    }
}
