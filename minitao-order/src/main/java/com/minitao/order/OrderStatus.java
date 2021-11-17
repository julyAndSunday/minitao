package com.minitao.order;

public enum OrderStatus {
    CANCEL(-1),
    NoPAY(0),
    PAY(1),
    RECEIVE(2);

    int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
