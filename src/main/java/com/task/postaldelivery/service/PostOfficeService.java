package com.task.postaldelivery.service;

import com.task.postaldelivery.entity.PostOffice;
import com.task.postaldelivery.entity.PostalItem;
import com.task.postaldelivery.entity.PostalItemHistory;
import com.task.postaldelivery.enums.OperationType;
import com.task.postaldelivery.enums.PostalItemStatus;
import com.task.postaldelivery.repository.PostOfficeRepository;
import com.task.postaldelivery.repository.PostalItemRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;

    private final PostalItemRepository postalItemRepository;

    private final PostalItemHistoryService postalItemHistoryService;

    public PostOfficeService(PostOfficeRepository postOfficeRepository, PostalItemRepository postalItemRepository, PostalItemHistoryService postalItemHistoryService) {
        this.postOfficeRepository = postOfficeRepository;
        this.postalItemRepository = postalItemRepository;
        this.postalItemHistoryService = postalItemHistoryService;
    }

    public PostOffice getOfficeById(Long officeId) {
        return postOfficeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Unable to find Post Office with id " + officeId));
    }

    public PostOffice registerItemArrival(Long postOfficeId, Long postalItemId) {
        PostOffice postOffice = postOfficeRepository.findById(postOfficeId)
                .orElseThrow(() -> new RuntimeException("Unable to find Post Office with id " + postOfficeId));
        PostalItem postalItem = postalItemRepository.findById(postalItemId)
                .orElseThrow(() -> new RuntimeException("Unable to find Postal Item with id " + postalItemId));
        postOffice.getItems().add(postalItem);

        postalItemHistoryService.createRecord(postalItemId, postOfficeId, OperationType.ARRIVAL_TO_POST);

        postOfficeRepository.save(postOffice);

        return postOffice;
    }

    public PostOffice departFromOffice(Long postOfficeId, Long postalItemId) {
        PostOffice postOffice = postOfficeRepository.findById(postOfficeId)
                .orElseThrow(() -> new RuntimeException("Unable to find Post Office with id " + postOfficeId));
        PostalItem postalItem = postalItemRepository.findById(postalItemId)
                .orElseThrow(() -> new RuntimeException("Unable to find Postal Item with id " + postalItemId));

        postOffice.getItems().remove(postalItem);

        postalItemHistoryService.createRecord(postalItemId, postOfficeId, OperationType.DEPART_FROM_POST);

        postOfficeRepository.save(postOffice);

        return postOffice;
    }

    public void receivedByAddressee(Long postalItemId) {
        PostOffice postOffice = postOfficeRepository.findByPostalItemId(postalItemId);
        postOffice
                .getItems()
                .stream()
                .filter(postalItem -> Objects.equals(postalItem.getId(), postalItemId))
                .findFirst()
                .get()
                .setStatus(PostalItemStatus.RECEIVED_BY_ADDRESSEE);
        postOffice.getItems().remove(postalItemId);
        postalItemHistoryService.createRecord(postalItemId, postOffice.getId(), OperationType.RECEIVED);
    }
}
