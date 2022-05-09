package com.inventory;

import java.io.IOException;
import java.util.Scanner;

import com.controller.Orders;
import com.database.stockDatabase;

public class placingOrder {
    public static void main(String args[]) throws IOException {
    	loadDB();
        System.out.println("Hi There! Welcome to Walmart");
    	placingOrder billObject = new placingOrder();
        billObject.initOrderInput();
    }
    
    private static void loadDB() { 
    	stockDatabase db = stockDatabase.getInstance();
        db.initDB();
    }
    
    private void initOrderInput() { 
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please enter the order file path");
        String inputFilePath = "";
        try {
            inputFilePath = sc.next();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initTransaction(inputFilePath);
    }
    
    private void initTransaction(String filePath) {
        Orders Orders = new Orders(filePath);
        if (Orders.initiateOrder()) {
            System.out.println(">>Processing Request....>>");
            if (Orders.validateOrder()) {
                Orders.calculateFinalAmount();
                System.out.println("Request has been placed!!!");
                Orders.checkoutOrder();
                System.out.println("Final Output is in receipt.csv");
            } else {
                System.out.println("FAILED!! Order cannot be processed!! Please check error logs!!");
                Orders.generateResultFile();
            }
        } else {
            System.out.println("File path seems to be wrong!! Please validate the file path & try again");
            Orders.generateResultFile();
        }
    }
}