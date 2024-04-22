package com.task.postaldelivery.enums;

public enum PostalItemStatus {
    PREPARED_FOR_SHIPMENT(0),
    ARRIVED_SORTING_CENTER(1),
    DEPART_SORTING_CENTER(2),
    RECEIVED_BY_ADDRESSEE(3);

    private int value;

    PostalItemStatus(int value) {
        this.value = value;
    }
}
