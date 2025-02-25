package com.example.demo.controller;

import com.example.demo.model.Inquiry;
import com.example.demo.model.InquiryStatus;
import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;  // Keep this since it's used
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // Get all inquiries but return only the content (list)
    @GetMapping
    public List<Inquiry> getAllInquiries(Pageable pageable) {
        return inquiryService.getAllInquiries(pageable);
    }

    // Get inquiries by status but return only the content (list)
    @GetMapping("/status/{status}")
    public List<Inquiry> getInquiriesByStatus(@PathVariable InquiryStatus status, Pageable pageable) {
        return inquiryService.getInquiriesByStatus(status, pageable);
    }

    // Get a single inquiry by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getInquiryById(@PathVariable Long id) {
        Optional<Inquiry> inquiry = inquiryService.getInquiryById(id);
        return inquiry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new inquiry
    @PostMapping
    public Inquiry createInquiry(@RequestBody Inquiry inquiry) {
        return inquiryService.createInquiry(inquiry);
    }

    // Update an existing inquiry
    @PutMapping("/{id}")
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable Long id, @RequestBody Inquiry inquiryDetails) {
        try {
            Inquiry updatedInquiry = inquiryService.updateInquiry(id, inquiryDetails);
            return ResponseEntity.ok(updatedInquiry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
