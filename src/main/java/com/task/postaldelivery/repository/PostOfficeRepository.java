package com.task.postaldelivery.repository;

import com.task.postaldelivery.entity.PostOffice;
import com.task.postaldelivery.entity.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

    @Query("SELECT p FROM PostOffice p JOIN p.items i WHERE i.id = :postalItemId")
    PostOffice findByPostalItemId(@Param("postalItemId") Long postalItemId);

}
