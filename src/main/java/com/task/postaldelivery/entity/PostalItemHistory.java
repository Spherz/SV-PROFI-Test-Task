package com.task.postaldelivery.entity;

import com.task.postaldelivery.enums.OperationType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class PostalItemHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postalItemId;

    private OperationType operationType;

    private OffsetDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    private PostOffice postOffice;
}
