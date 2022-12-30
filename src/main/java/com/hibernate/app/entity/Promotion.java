package com.hibernate.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int promotionId;
    private String status;
    private int expired;
    @Column(unique = true)
    private String promoCode;
    private String promoType;
    private int redemptionLimit;
    private Date startDate;
    private Date endDate;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Customer customer;
}
