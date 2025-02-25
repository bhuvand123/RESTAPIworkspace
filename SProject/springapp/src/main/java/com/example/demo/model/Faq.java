package com.example.demo.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data  

@Table(name = "faqs")
public class Faq {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Faq(Long id, String question, String answer, User user) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.user = user;
    }

    
}
