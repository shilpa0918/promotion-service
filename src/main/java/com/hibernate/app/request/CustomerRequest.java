package com.hibernate.app.request;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class CustomerRequest {

    private String customerName;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String role;
  //  List<PromotionRequest> promotionList;

}
