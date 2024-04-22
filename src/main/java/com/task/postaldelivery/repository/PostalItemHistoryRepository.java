package com.task.postaldelivery.repository;

import com.task.postaldelivery.entity.PostalItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostalItemHistoryRepository extends JpaRepository<PostalItemHistory, Long> {

    List<PostalItemHistory> findByPostalItemId(Long postalItemId);
}
