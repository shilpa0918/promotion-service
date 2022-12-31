package com.hibernate.app.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponse {
    private String customerName;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String role;
}
