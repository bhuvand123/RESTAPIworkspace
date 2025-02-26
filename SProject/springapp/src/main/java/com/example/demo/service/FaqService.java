package com.example.demo.service;

import com.example.demo.model.Faq;
import com.example.demo.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    // Get all FAQs with Pagination & Sorting
    public Page<Faq> getAllFaqs(Pageable pageable) {
        return faqRepository.findAll(pageable);
    }

    // Get FAQ by ID
    public Optional<Faq> getFaqById(Long id) {
        return faqRepository.findById(id);
    }

    // Get FAQs by User ID (JPQL)
    public List<Faq> getFaqsByUserId(Long userId) {
        return faqRepository.findByUserId(userId);
    }

    // Search FAQs by question (JPQL)
    public List<Faq> searchFaqs(String keyword) {
        return faqRepository.searchByQuestion(keyword);
    }

    // Create a new FAQ
    public Faq createFaq(Faq faq) {
        return faqRepository.save(faq);
    }

    // Update an existing FAQ
    public Faq updateFaq(Long id, Faq faqDetails) {
        return faqRepository.findById(id).map(faq -> {
            faq.setQuestion(faqDetails.getQuestion());
            faq.setAnswer(faqDetails.getAnswer());
            faq.setUser(faqDetails.getUser());
            return faqRepository.save(faq);
        }).orElseThrow(() -> new RuntimeException("FAQ not found with id " + id));
    }
    public void deleteAuthor(int id) {
        if (faqRepository.existsById((long) id))
         {
            faqRepository.deleteById( (long) id);
        } 
        else 
        {
            throw new RuntimeException("Author not found with id " + id);
        }
    }
}
