package com.database;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.models.Invoice;
import com.models.Item;

public class stockDatabase {
    private static stockDatabase instance;
    private HashMap < String, Item > inventory = new HashMap < String, Item > ();
    private HashSet < String > cards = new HashSet < String > ();
    private ArrayList < Invoice > orders = new ArrayList < Invoice > ();
    private stockDatabase() {};
    
    public static stockDatabase getInstance() {
        if (instance == null)
            instance = new stockDatabase();
        return instance;
    }
    public HashMap < String, Item > getItems() {
        return inventory;
    }

    public ArrayList < Invoice > getOrders() {
        return orders;
    }

    public HashSet < String > getCards() {
        return cards;
    }

    public void initDB() {
        loadEssentialsData();
        loadLuxuryData();
        loadMiscData();
        loadCardData();
    }
    
    public void loadEssentialsData() {
        inventory.put("clothes", new Item("Essentials", "clothes", 100, 20));
        inventory.put("soap", new Item("Essentials", "soap", 200, 5));
        inventory.put("shampoo", new Item("Essentials", "shampoo", 200, 10));
        inventory.put("milk", new Item("Essentials", "milk", 100, 5));
    }
    
    public void loadLuxuryData() {
        inventory.put("perfume", new Item("Luxury", "perfume", 50, 50));
        inventory.put("chocolates", new Item("Luxury", "chocolates", 300, 3));
        inventory.put("handbag", new Item("Luxury", "handbag", 75, 150));
        inventory.put("wallet", new Item("Luxury", "wallet", 100, 100));
    }
    
    public void loadMiscData() {
        inventory.put("bedsheet", new Item("Misc", "bedsheet", 150, 75));
        inventory.put("footware", new Item("Misc", "footware", 200, 25));
        inventory.put("homedecorpiece", new Item("Misc", "homedecorpiece", 100, 40));
        inventory.put("pen", new Item("Misc", "pen", 400, 3));
        inventory.put("pencil", new Item("Misc", "pencil", 400, 3));
    }
    
    public void loadCardData() {
        cards.add("5410000000000000");
        cards.add("4120000000000");
        cards.add("341000000000000");
        cards.add("6010000000000000");
    }
}