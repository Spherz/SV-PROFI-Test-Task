package com.task.postaldelivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postalCode;

    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<PostalItem> items;

    public PostOffice(String postalCode, String address) {
        this.postalCode = postalCode;
        this.address = address;
    }

    public PostOffice() {
    }
}
