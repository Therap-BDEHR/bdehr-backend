package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="text")
    private String text;

    @Column(name="sender")
    private String sender;

    @Column(name="status") //read, unread
    private String status;

    @Column(name="sent_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date sentAt;

    public Message(String text, String sender, String status) {
        this.text = text;
        this.sender = sender;
        this.status = status;
    }
}
