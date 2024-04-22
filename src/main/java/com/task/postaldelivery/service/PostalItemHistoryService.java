package com.task.postaldelivery.service;

import com.task.postaldelivery.entity.PostOffice;
import com.task.postaldelivery.entity.PostalItem;
import com.task.postaldelivery.entity.PostalItemHistory;
import com.task.postaldelivery.enums.OperationType;
import com.task.postaldelivery.repository.PostOfficeRepository;
import com.task.postaldelivery.repository.PostalItemHistoryRepository;
import com.task.postaldelivery.repository.PostalItemRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PostalItemHistoryService {

    private final PostalItemHistoryRepository postalItemHistoryRepository;

    private final PostOfficeRepository postOfficeRepository;

    private final PostalItemRepository postalItemRepository;

    public PostalItemHistoryService(PostalItemHistoryRepository postalItemHistoryRepository, PostOfficeRepository postOfficeRepository, PostalItemRepository postalItemRepository) {
        this.postalItemHistoryRepository = postalItemHistoryRepository;
        this.postOfficeRepository = postOfficeRepository;
        this.postalItemRepository = postalItemRepository;
    }

    public List<PostalItemHistory> getItemHistory(Long postalItemId) {
        List<PostalItemHistory> itemHistory = postalItemHistoryRepository.findByPostalItemId(postalItemId);

        if(itemHistory == null) {
            throw new RuntimeException("Can't find history for item with id " + postalItemId);
        }

        return itemHistory;
    }

    public Long createRecord(Long postalItemId, Long postOfficeId, OperationType operationType) {
        PostOffice postOffice = postOfficeRepository.findById(postOfficeId)
                .orElseThrow(() -> new RuntimeException("Unable to find Post Office with id " + postOfficeId));
        PostalItem postalItem = postalItemRepository.findById(postalItemId)
                .orElseThrow(() -> new RuntimeException("Unable to find Postal Item with id " + postalItemId));

        PostalItemHistory record = new PostalItemHistory();

        record.setPostalItemId(postalItem.getId());
        record.setDate(OffsetDateTime.now());
        record.setOperationType(operationType);
        record.setPostOffice(postOffice);

        postalItemHistoryRepository.save(record);

        return record.getId();
    }
}
