package com.task.postaldelivery.controller;

import com.task.postaldelivery.entity.PostalItemHistory;
import com.task.postaldelivery.service.PostalItemHistoryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class PostalItemHistoryController {

    private final PostalItemHistoryService postalItemHistoryService;

    public PostalItemHistoryController(PostalItemHistoryService postalItemHistoryService) {
        this.postalItemHistoryService = postalItemHistoryService;
    }

    @GetMapping("/{postalItemId}")
    public ResponseEntity<List<PostalItemHistory>> getItemHistory(@PathVariable("postalItemId") Long postalItemId) {
        return new ResponseEntity<>(postalItemHistoryService.getItemHistory(postalItemId), HttpStatus.OK);
    }
}
