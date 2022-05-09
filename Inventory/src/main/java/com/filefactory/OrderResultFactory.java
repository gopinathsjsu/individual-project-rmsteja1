package com.filefactory;

public class OrderResultFactory {
    public OrderResultCreator getResult(boolean isError) {
        if (isError == false) {
            return new OrderBillCreator();
        } else if (isError == true) {
            return new OrderErrorCreator();
        }
        return null;
    }
}