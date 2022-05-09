package com.filefactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.models.CartItems;

public class OrderResultHandling {
    Path filePath;
    OrderResultCreator orderResultGenerator;
    private ArrayList < String > fileContent = new ArrayList < String > ();

    public OrderResultHandling(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void readFile(boolean ignoreFirst) throws IOException {
        if (Files.notExists(filePath)) {
            new IOException();
        }
        BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));
        String contentLine = "";
        while ((contentLine = br.readLine()) != null) {
            if (ignoreFirst) {
                ignoreFirst = false;
                continue;
            }
            fileContent.add(contentLine);
        }
    }

    public ArrayList < String > getFileContent() {
        return fileContent;
    }

    public void writeResults(ArrayList < String > message, boolean checkIfError, ArrayList < CartItems > itemList) throws IOException {
        OrderResultFactory resultFactory = new OrderResultFactory();
        OrderResultCreator orderResultGenerator = resultFactory.getResult(checkIfError);
        orderResultGenerator.writeToFile(message);
        orderResultGenerator.saveFile(filePath.getParent(), itemList);

    }
}