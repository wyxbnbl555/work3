package com.kuang.work3;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    private static orderDao orderDao = new orderDaoImpl();
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        while(true) {
            System.out.println("-------------欢迎进入订单管理系统-------------");
            System.out.println("1.查询所有商品");
            System.out.println("2.查询指定的商品");
            System.out.println("3.查询所有订单");
            System.out.println("4.查询指定的订单");
            System.out.println("5.添加商品");
            System.out.println("6.添加订单");
            System.out.println("7.删除商品");
            System.out.println("8.删除订单");
            System.out.println("9.修改商品");
            System.out.println("10.修改订单");
            System.out.println("11.按下单时间排序订单");
            System.out.println("12.按价格排序订单");
            System.out.println("13.退出");
            System.out.println("-------------------------------------------");
            System.out.println("请选择菜单：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<products> list1 = orderDao.findAllProducts();
                    for (products product : list1) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("请输入要查询的商品编号：");
                    int id1 = sc.nextInt();
                    products pro = orderDao.findProductById(id1);
                    System.out.println(pro);
                    break;
                case 3:
                    ArrayList<bigOrder> list2 = orderDao.findAllOrder();
                    for (bigOrder order : list2) {
                        System.out.println(order);
                    }
                    break;
                case 4:
                    System.out.println("请输入要查询的订单编号：");
                    int id2 = sc.nextInt();
                    bigOrder order = orderDao.findOrderById(id2);
                    System.out.println(order);
                    break;
                case 5:
                    System.out.println("请输入商品编号：");
                    int id3 = sc.nextInt();
                    System.out.println("请输入商品名称：");
                    String name1 = sc.next();
                    System.out.println("请输入商品价格：");
                    double price1 = sc.nextDouble();
                    products product = new products(id3, name1, price1);
                    int i1 = orderDao.productInsert(product);
                    if (i1 > 0) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败");
                    }
                    break;
                case 6:
                    System.out.println("请输入订单编号：");
                    int id4 = sc.nextInt();
                    System.out.println("请输入商品编号：");
                    int id5 = sc.nextInt();
                    orders order2 = new orders(id4,new java.sql.Date(new java.util.Date().getTime()), id5);
                    int i2 = orderDao.OrderInsert(order2);
                    if (i2 > 0) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败");
                    }
                    break;
                case 7:
                    System.out.println("请输入要删除的商品编号：");
                    int id6 = sc.nextInt();
                    int i3 = orderDao.productDelete(id6);
                    if (i3 > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                    break;
                case 8:
                    System.out.println("请输入要删除的订单编号：");
                    int id7 = sc.nextInt();
                    int i4 = orderDao.orderDelete(id7);
                    if (i4 > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                    break;
                case 9:
                    System.out.println("要修改的商品编号为：");
                    int id8 = sc.nextInt();
                    System.out.println("将商品名称改为：");
                    String name2 = sc.next();
                    System.out.println("将商品的价格改为：");
                    double price3 = sc.nextDouble();
                    products pro2 = new products(id8, name2, price3);
                    int i5 = orderDao.productUpdate(pro2);
                    if (i5 > 0) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
                    break;
                case 10:
                    System.out.println("要修改的订单编号为：");
                    int id9 = sc.nextInt();
                    System.out.println("将商品编号改为：");
                    int id10 = sc.nextInt();
                    orders order3 = new orders(id9, new java.sql.Date(new java.util.Date().getTime()), id10);
                    int i6 = orderDao.orderUpdate(order3);
                    if (i6 > 0) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
                    break;
                case 11:
                    ArrayList<bigOrder> list3 = orderDao.sortOrderByTime();
                    for (bigOrder order4 : list3) {
                        System.out.println(order4);
                    }
                    break;
                case 12:
                    ArrayList<bigOrder> list4 = orderDao.sortOrderByPrice();
                    for (bigOrder order4 : list4) {
                        System.out.println(order4);
                    }
                    break;
                case 13:
                    System.out.println("欢迎下次使用");
                    return;
                default:
                    System.out.println("输入错误");

            }
            System.out.println("按任意键继续");
            sc.nextLine();
            sc.nextLine();
        }

    }
}
