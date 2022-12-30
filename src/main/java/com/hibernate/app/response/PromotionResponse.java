package com.hibernate.app.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class PromotionResponse {
    private int promotionId;
    private String status;
    private int expired;
    private String promoCode;
    private String promoType;
    private int redemptionLimit;
    private Date startDate;
    private Date endDate;
    private int customerId;
}
