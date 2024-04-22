package com.task.postaldelivery.controller;

import com.task.postaldelivery.entity.PostalItem;
import com.task.postaldelivery.service.PostalItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postal-item")
public class PostalItemController {

    private final PostalItemService postalItemService;

    public PostalItemController(PostalItemService postalItemService) {
        this.postalItemService = postalItemService;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerItem(@RequestBody PostalItem postalItem, @RequestParam Long postOfficeId) {
        return new ResponseEntity<>(postalItemService.registerItem(postalItem, postOfficeId), HttpStatus.CREATED);
    }
}
