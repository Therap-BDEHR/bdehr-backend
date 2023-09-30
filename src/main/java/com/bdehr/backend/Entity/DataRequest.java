package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "data_request_table")
public class DataRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="sender")
    private String sender;

    @Column(name="req_text")
    private String reqText;

    @Column(name="res_text")
    private String resText;

    @Column(name="req_time")
    private String reqTime;

    @Column(name="res_time")
    private String resTime;

    @Column(name="url")
    private String url;

    @Column(name="status") //pending, completed
    private String status;

    public DataRequest(String sender, String reqText, String reqTime, String status) {
        this.sender = sender;
        this.reqText = reqText;
        this.reqTime = reqTime;
        this.status = status;
    }
}
