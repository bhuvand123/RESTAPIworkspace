package com.example.demo.repository;

import com.example.demo.model.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

    // JPQL Query: Find FAQs by user ID
    @Query("SELECT f FROM Faq f WHERE f.user.id = :userId")
    List<Faq> findByUserId(@Param("userId") Long userId);

    // JPQL Query: Search FAQs by question containing a keyword (case insensitive)
    @Query("SELECT f FROM Faq f WHERE LOWER(f.question) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Faq> searchByQuestion(@Param("keyword") String keyword);

    // Pagination and Sorting Support
    Page<Faq> findAll(Pageable pageable);
}
