package com.example.demo.controller;

import com.example.demo.model.Faq;
import com.example.demo.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faqs")
public class FaqController {

    @Autowired
    private FaqService faqService;

    // Get all FAQs with pagination & sorting
    @GetMapping
    public Page<Faq> getAllFaqs(Pageable pageable) {
        return faqService.getAllFaqs(pageable);
    }

    // Get a single FAQ by ID
    @GetMapping("/{id}")
    public ResponseEntity<Faq> getFaqById(@PathVariable Long id) {
        Optional<Faq> faq = faqService.getFaqById(id);
        return faq.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get FAQs by User ID
    @GetMapping("/user/{userId}")
    public List<Faq> getFaqsByUserId(@PathVariable Long userId) {
        return faqService.getFaqsByUserId(userId);
    }

    // Search FAQs by keyword in question
    @GetMapping("/search")
    public List<Faq> searchFaqs(@RequestParam String keyword) {
        return faqService.searchFaqs(keyword);
    }

    // Create a new FAQ
    @PostMapping
    public Faq createFaq(@RequestBody Faq faq) {
        return faqService.createFaq(faq);
    }

    // Update an existing FAQ
    @PutMapping("/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable Long id, @RequestBody Faq faqDetails) {
        try {
            Faq updatedFaq = faqService.updateFaq(id, faqDetails);
            return ResponseEntity.ok(updatedFaq);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
