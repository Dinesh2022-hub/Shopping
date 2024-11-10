package com.sailotech.shopping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Shopping {
    public static void main(String[] args) throws ClassNotFoundException, SQLException 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Shopping !");
        String className = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "12345";
        String url = "jdbc:mysql://localhost:3306/shoppingcartdb";
        Class.forName(className);
        Connection connection = DriverManager.getConnection(url, userName, password);
        while (true){
            System.out.println("Select an option:");
            System.out.println("1. Add item");
            System.out.println("2. edit item");
            System.out.println("3. Checkout");
            System.out.println("4. delete");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("enter the id to be inserted");
                    int id = scanner.nextInt();
                    System.out.println("enter the item to be inserted");
                    String item = scanner.next();
                    System.out.println("enter the price to be inserted");
                    int price = scanner.nextInt();
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into shoppingitems values(?,?,?)");
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, item);
                    preparedStatement.setInt(3, price);
                    preparedStatement.execute();
                    System.out.println("inserted");
                    break;
                case 2:
                    System.out.println("enter the id to be updated");
                    int idToUpdate = scanner.nextInt();
                    System.out.println("enter the item to be updated");
                    String itemToUpdate = scanner.next();
                    System.out.println("enter the price to be updated");
                    int priceToUpdate = scanner.nextInt();
                    PreparedStatement preparedStatement2 = connection.prepareStatement("update shoppingitems set item=?, price=? where id=?");
                    preparedStatement2.setString(1, itemToUpdate);
                    preparedStatement2.setInt(2, priceToUpdate);
                    preparedStatement2.setInt(3, idToUpdate);
                    int count = preparedStatement2.executeUpdate();
                    System.out.println(count + " row get affected");
                    break;
                case 3:
                	System.out.println("enter the id");
            		int getId=scanner.nextInt();
            		PreparedStatement preparedStatement3=connection.prepareStatement("select* from shoppingitems where id=? ");
            		preparedStatement3.setInt(1, getId);
            		ResultSet set=preparedStatement3.executeQuery();
            		System.out.println();
            		while(set.next())
            		{    
            			System.out.println("id:"+set.getInt(1));
            			System.out.println("Item:"+set.getString(2));
            			System.out.println("Price:"+set.getInt(3));
            		}
            		connection.close();
                    break;
                case 4:
                	System.out.println("enter the deleteId to delete");
            		int deleteId=scanner.nextInt();
            		PreparedStatement preparedStatement4=connection.prepareStatement("delete from shoppingitems where id=?");
            	    preparedStatement4.setInt(1, deleteId);
            	    preparedStatement4.execute();
            	    connection.close();
            	    System.out.println("Delete");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

