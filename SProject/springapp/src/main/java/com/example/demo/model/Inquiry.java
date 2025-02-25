package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inquiry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String email;
    private String question;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private InquiryStatus status;  // NEW, IN_PROGRESS, RESOLVED

    public Inquiry() {
        this.createdAt = LocalDateTime.now();
        this.status = InquiryStatus.NEW;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public InquiryStatus getStatus() { return status; }
    public void setStatus(InquiryStatus status) { this.status = status; }
}
