package com.task.postaldelivery.entity;

import com.task.postaldelivery.enums.PostalItemStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class PostalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientName;

    private String itemType;

    private String postalCode;

    private String recipientAddress;

    private PostalItemStatus status;

    private Long postOfficeId;

    public PostalItem(String recipientName, String itemType, String postalCode, String recipientAddress, PostalItemStatus status) {
        this.recipientName = recipientName;
        this.itemType = itemType;
        this.postalCode = postalCode;
        this.recipientAddress = recipientAddress;
        this.status = status;
    }

    public PostalItem() {
    }
}
