package com.hibernate.app.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class PromotionRequest {
    private int promotionId;
    private String status;
    private int expired;
    private String promoCode;
    private String promoType;
    private int redemptionLimit;
    private Date startDate;
    private Date endDate;
}
