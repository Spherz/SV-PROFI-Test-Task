package com.task.postaldelivery.controller;

import com.task.postaldelivery.entity.PostOffice;
import com.task.postaldelivery.service.PostOfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-office")
public class PostOfficeController {

    private final PostOfficeService postOfficeService;

    public PostOfficeController(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @GetMapping("/office/{id}")
    public ResponseEntity<PostOffice> getPostOfficeById(@PathVariable("id") Long officeId) {
        return new ResponseEntity<>(postOfficeService.getOfficeById(officeId), HttpStatus.OK);
    }

    @PatchMapping("/arrival")
    public ResponseEntity<PostOffice> registerItemArrival(@RequestParam Long postOfficeId,
                                                          @RequestParam Long postalItemId) {
        return new ResponseEntity<>(postOfficeService.registerItemArrival(postOfficeId, postalItemId), HttpStatus.OK);
    }

    @PatchMapping("/departure")
    public ResponseEntity<PostOffice> departItemFromOffice(@RequestParam Long postOfficeId,
                                                          @RequestParam Long postalItemId) {
        return new ResponseEntity<>(postOfficeService.departFromOffice(postOfficeId, postalItemId), HttpStatus.OK);
    }

    @PatchMapping("/receive")
    public ResponseEntity<HttpStatus> receivedByAddressee(@RequestParam Long postalItemId) {
        postOfficeService.receivedByAddressee(postalItemId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
