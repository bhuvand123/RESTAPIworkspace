package com.example.demo.service;

import com.example.demo.model.Inquiry;
import com.example.demo.model.InquiryStatus;
import com.example.demo.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    // Fetch inquiries but return only a list, not Page
    public List<Inquiry> getAllInquiries(Pageable pageable) {
        Page<Inquiry> page = inquiryRepository.findAll(pageable);
        return page.getContent(); // Extracts only the list
    }

    // Fetch inquiries by status but return only a list
    public List<Inquiry> getInquiriesByStatus(InquiryStatus status, Pageable pageable) {
        Page<Inquiry> page = inquiryRepository.findByStatus(status, pageable);
        return page.getContent(); // Extracts only the list
    }

    public Optional<Inquiry> getInquiryById(Long id) {
        return inquiryRepository.findById(id);
    }

    public Inquiry createInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    public Inquiry updateInquiry(Long id, Inquiry inquiryDetails) {
        return inquiryRepository.findById(id).map(inquiry -> {
            inquiry.setStudentName(inquiryDetails.getStudentName());
            inquiry.setEmail(inquiryDetails.getEmail());
            inquiry.setQuestion(inquiryDetails.getQuestion());
            inquiry.setStatus(inquiryDetails.getStatus());
            return inquiryRepository.save(inquiry);
        }).orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }
}
