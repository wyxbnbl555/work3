package work3.test;



import work3.dao.orderController;
import java.util.Scanner;

public class test {
    private static orderController control = new orderController();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true){
                System.out.println("-------------欢迎进入订单管理系统-------------");
                System.out.println("1.查询所有订单");
                System.out.println("2.查询指定订单");
                System.out.println("3.查询所有商品");
                System.out.println("4.查询指定商品");
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
                        control.findAllOrderImformation();
                        break;
                    case 2:
                        control.findOrderImformationById();
                        break;
                    case 3:
                        control.findAllProducts();
                        break;
                    case 4:
                        control.findProductById();
                        break;
                    case 5:
                        control.productInsert();
                        break;
                    case 6:
                        control.OrderInsert();
                        break;
                    case 7:
                        control.productDelete();
                        break;
                    case 8:
                        control.orderDelete();
                        break;
                    case 9:
                        control.productUpdate();
                        break;
                    case 10:
                        control.orderUpdate();
                        break;
                    case 11:
                        control.sortOrderByTime();
                        break;
                    case 12:
                        control.sortOrderByPrice();
                        break;
                    case 13:
                        System.exit(0);
                        System.out.println("退出成功");
                    default:
                        System.out.println("您的输入有误，请重新输入");

                }




            }

        }

    }

