package com.task.postaldelivery.enums;

public enum OperationType {
    REGISTER_PACKAGE(0),
    DEPART_FROM_POST(1),
    ARRIVAL_TO_POST(2),

    RECEIVED(3);

    private int value;

    OperationType(int value) {
        this.value = value;
    }
}
