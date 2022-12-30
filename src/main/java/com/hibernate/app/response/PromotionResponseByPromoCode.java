package com.hibernate.app.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PromotionResponseByPromoCode {
    private int promotionId;
    private String status;
    private int expired;
    private String promoCode;
    private String promoType;
    private int redemptionLimit;
    private Date startDate;
    private Date endDate;
    private int customerId;
    private String customerName;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String role;
}
