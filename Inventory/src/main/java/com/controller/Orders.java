package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.database.stockDatabase;
import com.filefactory.OrderResultHandling;
import com.models.CartItems;
import com.models.Invoice;
import com.models.Item;

public class Orders {
    private stockDatabase inventoryDatabase = stockDatabase.getInstance();
    private OrderResultHandling file;
    private ArrayList < String > outputMessage = new ArrayList < String > ();
    private HashSet < String > cards = new HashSet < String > ();
    private ArrayList < CartItems > itemList = new ArrayList < CartItems > ();
    private Invoice currentInvoice = new Invoice();
    private double totalAmount = 0;
    
    public Orders(String filePath) {
        file = new OrderResultHandling(filePath);
    }

    public boolean initiateOrder() {
        try {
            file.readFile(true);
        } catch (Exception e) {
            outputMessage.add("Input file is wrong, please check the path");
            return false;
        }
        getItems(file.getFileContent());
        return true;
    }

    public boolean validateOrder() {
        checkItemQuantity();
        checkItemCap();
        return outputMessage.isEmpty();
    }

    public void calculateFinalAmount() {
        itemList.forEach((item) -> {
            totalAmount += item.getItemQuantity() * item.getPrice();
        });
        currentInvoice.setTotalPrice(totalAmount);
    }

    public void checkoutOrder() {
        inventoryDatabase.getOrders().add(currentInvoice);
        for (CartItems currentItem: itemList) {
            Item item = inventoryDatabase.getItems().get(currentItem.getInvoiceItemName());
            item.setItemQuantity(item.getItemQuantity() - currentItem.getItemQuantity());

        }
        for (String card: cards) {
            if (!inventoryDatabase.getCards().contains(card)) {
                inventoryDatabase.getCards().add(card);
            }
        }
        generateResultFile();
    }

    private void getItems(ArrayList < String > fileContent) {
        String firstLine = fileContent.get(0);
        String[] firstItem = firstLine.split(",");
        for (String contentLine: fileContent) {
            String[] item = contentLine.split(",");
            if (inventoryDatabase.getItems().containsKey(item[0].toLowerCase())) {
                double priceItem = inventoryDatabase.getItems().get(item[0].toLowerCase()).getItemPrice();
                try {
                    itemList.add(new CartItems(item[0].toLowerCase(), Integer.parseInt(item[1]), firstItem[2].replaceAll("\\s+", ""), priceItem));
                } catch (Exception e) {
                    System.out.println("Issue with request, check error logs");
                    outputMessage.add("Input file format is wrong");
                    break;
                }
            } else {
                outputMessage.add(item[0].toLowerCase() + " item not available in inventory.");
            }
        }
        if (!outputMessage.isEmpty()) {
            itemList.clear();
        }
    }
    
    private boolean checkItemQuantity() {
        StringBuilder message = new StringBuilder();
        for (CartItems currentItem: itemList) {
            Item item = inventoryDatabase.getItems().get(currentItem.getInvoiceItemName());
            if (item.getItemQuantity() < currentItem.getItemQuantity()) {
                if (message.length() > 0)
                    message.append(",");
                message.append(currentItem.getInvoiceItemName() + " there is no sufficient quantity in the inventory");
            } else {
                currentItem.setPrice(item.getItemPrice());
                if (!cards.contains(currentItem.getCardInfo()))
                    cards.add(currentItem.getCardInfo());
            }
        }
        if (message.length() > 0) {
            outputMessage.add("Issue with quantities: change the quantities");
            outputMessage.add(message.toString());
        }
        return (message.length() == 0);
    }
    
    private boolean checkItemCap() {
        final int essentialsCap = 3;
        final int luxuryCap = 4;
        final int miscCap = 6;
        HashMap < String, Integer > map = new HashMap < String, Integer > ();
        stockDatabase inventoryDatabase = stockDatabase.getInstance();
        for (CartItems orderItem: itemList) {
            map.put(inventoryDatabase.getItems().get(orderItem.getInvoiceItemName()).getCategory(), map.getOrDefault(inventoryDatabase.getItems().get(orderItem.getInvoiceItemName()).getCategory(), 0) + orderItem.getItemQuantity());
        }
        if (map.getOrDefault("Luxury", 0) > luxuryCap) {
            outputMessage.add("Luxury items max limit exceeded");
            return false;
        } else if (map.getOrDefault("Essentials", 0) > essentialsCap) {
            outputMessage.add("Essential items max limit exceeded");
            return false;
        } else if (map.getOrDefault("Misc", 0) > miscCap) {
            outputMessage.add("Misc items max limit exceeded");
            return false;
        }
        return true;
    }

    public void generateResultFile() {
        if (outputMessage.isEmpty()) {
            try {
                file.writeResults(outputMessage, false, itemList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.writeResults(outputMessage, true, itemList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}