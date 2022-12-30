package com.hibernate.app.controller;

import com.hibernate.app.request.CustomerRequest;
import com.hibernate.app.response.CustomerResponse;
import com.hibernate.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/findCustomer/{id}")
    public ResponseEntity findCustomer(@PathVariable String id){
        CustomerResponse customerResponse = customerService.findCustomer(id);
        return new ResponseEntity(customerResponse,HttpStatus.OK);
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequest customerRequest,@PathVariable String id){
        CustomerResponse customerResponse = customerService.updateCustomer(customerRequest,id);
        return new ResponseEntity(customerResponse,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
       String deletedCustomer =  customerService.deleteCustomer(id);
    return new ResponseEntity(deletedCustomer,HttpStatus.NO_CONTENT);
    }
}
