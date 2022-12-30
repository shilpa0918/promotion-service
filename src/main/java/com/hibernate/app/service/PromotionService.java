package com.hibernate.app.service;

import com.hibernate.app.constants.PromoCodeStatus;
import com.hibernate.app.entity.Customer;
import com.hibernate.app.entity.Promotion;
import com.hibernate.app.repo.CustomerRepo;
import com.hibernate.app.repo.PromotionRepo;
import com.hibernate.app.request.PromotionRequest;
import com.hibernate.app.response.PromotionResponse;
import com.hibernate.app.response.PromotionResponseByPromoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    PromotionRepo promotionRepo;
    @Autowired
    CustomerRepo customerRepo;

    public PromotionResponse addPromotionCode(PromotionRequest promotionRequest, Integer customerId) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(promotionRequest.getPromotionId());
        Optional<Customer> customer = customerRepo.findById(customerId);
        promotion.setCustomer(customer.get());
        promotion.setExpired(promotionRequest.getExpired());
        promotion.setStatus(promotionRequest.getStatus());
        promotion.setPromoCode(promotionRequest.getPromoCode());
        promotion.setEndDate(promotionRequest.getEndDate());
        promotion.setPromoType(promotionRequest.getPromoType());
        promotion.setStartDate(promotionRequest.getStartDate());
        promotion.setRedemptionLimit(promotionRequest.getRedemptionLimit());
        Promotion addedPromotion = promotionRepo.saveAndFlush(promotion);
        return convertedToPromotionDto(addedPromotion);
    }

    private PromotionResponse convertedToPromotionDto(Promotion promotion) {
        PromotionResponse promotionResponse = new PromotionResponse();
        promotionResponse.setPromotionId(promotion.getPromotionId());
        promotionResponse.setCustomerId(promotion.getCustomer().getId());
        promotionResponse.setExpired(promotion.getExpired());
        promotionResponse.setStatus(promotion.getStatus());
        promotionResponse.setPromoCode(promotion.getPromoCode());
        promotionResponse.setEndDate(promotion.getEndDate());
        promotionResponse.setPromoType(promotion.getPromoType());
        promotionResponse.setStartDate(promotion.getStartDate());
        promotionResponse.setRedemptionLimit(promotion.getRedemptionLimit());
        return promotionResponse;
    }

    public PromotionResponseByPromoCode findPromotionByCode(String promoCode) {
        Promotion promotion = promotionRepo.findByPromoCode(promoCode);
        PromotionResponseByPromoCode response = new PromotionResponseByPromoCode();
        response.setCustomerId(promotion.getCustomer().getId());
        response.setPromotionId(promotion.getPromotionId());
        response.setPromoCode(promotion.getPromoCode());
        response.setExpired(promotion.getExpired());
        response.setStatus(promotion.getStatus());
        response.setRedemptionLimit(promotion.getRedemptionLimit());
        response.setStartDate(promotion.getStartDate());
        response.setEndDate(promotion.getEndDate());
        response.setPromoType(promotion.getPromoType());
        response.setCustomerName(promotion.getCustomer().getCustomerName());
        response.setEmail(promotion.getCustomer().getEmail());
        response.setPassword(promotion.getCustomer().getPassword());
        response.setRole(promotion.getCustomer().getRole());
        response.setPhone(promotion.getCustomer().getPhone());
        response.setUserName(promotion.getCustomer().getUserName());
        return response;
    }

    public PromotionResponseByPromoCode findPromotionByCustomer(String customerUsername) {

        Customer customer = customerRepo.findByUserName(customerUsername);
        Promotion promotion = promotionRepo.findByCustomerId(customer.getId());
        PromotionResponseByPromoCode response = new PromotionResponseByPromoCode();
        response.setCustomerId(promotion.getCustomer().getId());
        response.setPromotionId(promotion.getPromotionId());
        response.setPromoCode(promotion.getPromoCode());
        response.setExpired(promotion.getExpired());
        response.setStatus(promotion.getStatus());
        response.setRedemptionLimit(promotion.getRedemptionLimit());
        response.setStartDate(promotion.getStartDate());
        response.setEndDate(promotion.getEndDate());
        response.setPromoType(promotion.getPromoType());
        response.setCustomerName(promotion.getCustomer().getCustomerName());
        response.setEmail(promotion.getCustomer().getEmail());
        response.setPassword(promotion.getCustomer().getPassword());
        response.setRole(promotion.getCustomer().getRole());
        response.setPhone(promotion.getCustomer().getPhone());
        response.setUserName(promotion.getCustomer().getUserName());
        return response;
    }

    public String validatePromotionCode(String promoCode) {
        Promotion promotion = promotionRepo.findByPromoCode(promoCode);
        Date date = new Date();
        System.out.println(date);
        if (!Objects.isNull(promotion) && promotion.getEndDate().after(date) &&
                promotion.getStartDate().before(date) && promotion.getExpired() == 0 &&
                promotion.getStatus().equalsIgnoreCase(PromoCodeStatus.Active.name())) {
            return promoCode + " this is valid promo code";
        } else return promoCode + " this is not valid promo code";
    }

    public List<String> findExpiredPromotions() {
        List<Promotion> promotionList = promotionRepo.findAll();
        Date date = new Date();
        List<String> promoCodes = promotionList.stream().filter(promotion -> promotion.getEndDate().before(date)
                        && promotion.getExpired() == 1 && promotion.getStatus().equalsIgnoreCase(PromoCodeStatus.InActive.name()))
                .map(promotion -> promotion.getPromoCode())
                .collect(Collectors.toList());
        System.out.println(promoCodes);
        return promoCodes;
    }
}
