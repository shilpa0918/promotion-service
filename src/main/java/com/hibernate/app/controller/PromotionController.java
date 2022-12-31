package com.hibernate.app.controller;

import com.hibernate.app.request.PromotionRequest;
import com.hibernate.app.response.PromotionResponse;
import com.hibernate.app.response.PromotionResponseByPromoCode;
import com.hibernate.app.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @PostMapping("/addPromotionCode/{customerId}")
    public ResponseEntity addPromotionCode(@RequestBody PromotionRequest promotionRequest, @PathVariable Integer customerId) {
        PromotionResponse promotion = promotionService.addPromotionCode(promotionRequest, customerId);
        return new ResponseEntity(promotion, HttpStatus.CREATED);
    }

    @GetMapping("/findPromotionByCode/{promoCode}")
    public ResponseEntity findPromotionByCode(@PathVariable String promoCode) {
        PromotionResponseByPromoCode promotionResponse = promotionService.findPromotionByCode(promoCode);
        return new ResponseEntity(promotionResponse, HttpStatus.OK);
    }

    @GetMapping("/findPromotionByCustomer/{customerUsername}")
    public ResponseEntity findPromotionByCustomer(@PathVariable String customerUsername) {
        PromotionResponseByPromoCode promotionResponse = promotionService.findPromotionByCustomer(customerUsername);
        return new ResponseEntity(promotionResponse, HttpStatus.OK);
    }

    @GetMapping("/validatePromotionCode/{promoCode}")
    public ResponseEntity validatePromotionCode(@PathVariable String promoCode) {
        String validatePromotionCodeStr = promotionService.validatePromotionCode(promoCode);
        return new ResponseEntity(validatePromotionCodeStr,HttpStatus.OK);
    }

    @GetMapping("/findExpiredPromotions")
    public ResponseEntity<List<String>> findExpiredPromotions(){
        List<String> promotionResponses = promotionService.findExpiredPromotions();
        return new ResponseEntity<>(promotionResponses,HttpStatus.OK);
    }
}
