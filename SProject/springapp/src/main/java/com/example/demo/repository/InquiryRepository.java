package com.example.demo.repository;

import com.example.demo.model.Inquiry;
import com.example.demo.model.InquiryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    // Custom JPQL query to find inquiries by status
    @Query("SELECT i FROM Inquiry i WHERE i.status = :status")
    Page<Inquiry> findByStatus(@Param("status") InquiryStatus status, Pageable pageable);
}
