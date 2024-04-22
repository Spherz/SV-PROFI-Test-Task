package com.task.postaldelivery.repository;

import com.task.postaldelivery.entity.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {
}
