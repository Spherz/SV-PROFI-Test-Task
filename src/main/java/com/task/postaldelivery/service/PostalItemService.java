package com.task.postaldelivery.service;

import com.task.postaldelivery.entity.PostOffice;
import com.task.postaldelivery.entity.PostalItem;
import com.task.postaldelivery.enums.OperationType;
import com.task.postaldelivery.repository.PostOfficeRepository;
import com.task.postaldelivery.repository.PostalItemRepository;
import org.springframework.stereotype.Service;

@Service
public class PostalItemService {

    private final PostalItemRepository postalItemRepository;

    private final PostOfficeRepository postOfficeRepository;

    private final PostalItemHistoryService postalItemHistoryService;

    public PostalItemService(PostalItemRepository postalItemRepository, PostOfficeRepository postOfficeRepository, PostalItemHistoryService postalItemHistoryService) {
        this.postalItemRepository = postalItemRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.postalItemHistoryService = postalItemHistoryService;
    }

    public Long registerItem(PostalItem postalItem, Long postOfficeId) {
        PostalItem item = new PostalItem();
        PostOffice office = postOfficeRepository.findById(postOfficeId).
                orElseThrow(() -> new RuntimeException("Unable to find Post Office with id " + postOfficeId));

        item.setRecipientName(postalItem.getRecipientName());
        item.setItemType(postalItem.getItemType());
        item.setPostalCode(postalItem.getPostalCode());
        item.setRecipientAddress(postalItem.getRecipientAddress());
        item.setStatus(postalItem.getStatus());

        office.getItems().add(item);

        postalItemRepository.save(item);
        postOfficeRepository.save(office);

        postalItemHistoryService.createRecord(item.getId(), office.getId(), OperationType.REGISTER_PACKAGE);

        return item.getId();
    }
}
