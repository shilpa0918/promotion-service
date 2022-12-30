package com.hibernate.app.service;

import com.hibernate.app.entity.Customer;
import com.hibernate.app.repo.CustomerRepo;
import com.hibernate.app.request.CustomerRequest;
import com.hibernate.app.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Value(value="${application.uniquePrecision}")
    Integer uniquePrecision;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        double cutomerId = uniquePrecision * Math.random();
        try {
            if(isCustomerPresent(cutomerId)){
                double regeneratedCustomerId = uniquePrecision * Math.random();
                if(!isCustomerPresent(regeneratedCustomerId)) customer.setCustomerId(regeneratedCustomerId);
                else throw new Exception("There is some technical issue, please retry in 5 seconds");
            }
            else customer.setCustomerId(cutomerId);
            customer.setCustomerName(customerRequest.getCustomerName());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhone(customerRequest.getPhone());
            customer.setRole(customerRequest.getRole());
            customer.setUserName(customerRequest.getUserName());
            customer.setPassword(customerRequest.getPassword());
            Customer addedCustomer = customerRepo.saveAndFlush(customer);
            return convertedToCustomerDto(addedCustomer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCustomerPresent(double custId) {
        return customerRepo.findByCustomerId(custId).isPresent();
    }

    private CustomerResponse convertedToCustomerDto(Customer addedCustomer) {
        CustomerResponse customerResponse = new CustomerResponse();
        //customerResponse.setCustomerId(addedCustomer.getCustomerId());
        customerResponse.setCustomerName(addedCustomer.getCustomerName());
        customerResponse.setEmail(addedCustomer.getEmail());
        customerResponse.setPhone(addedCustomer.getPhone());
        customerResponse.setRole(addedCustomer.getRole());
        customerResponse.setUserName(addedCustomer.getUserName());
        customerResponse.setPassword(addedCustomer.getPassword());
        return customerResponse;
    }

    public CustomerResponse findCustomer(String id) {
        Customer customer;
        if (id.contains("@")) {
            System.out.println("this is email");
            customer = customerRepo.findByEmail(id);
        } else {
            System.out.println("this is username");
            customer = customerRepo.findByUserName(id);
        }
        CustomerResponse customerResponse = new CustomerResponse();
       // customerResponse.setCustomerId(customer.getCustomerId());
        customerResponse.setCustomerName(customer.getCustomerName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhone(customer.getPhone());
        customerResponse.setRole(customer.getRole());
        customerResponse.setUserName(customer.getUserName());
        customerResponse.setPassword(customer.getPassword());
        return customerResponse;
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest, String id) {
        Customer customer;
        if (id.contains("@")) {
            System.out.println("this is updated by email");
            customer = customerRepo.findByEmail(id);
        } else {
            System.out.println("this is updated by username");
            customer = customerRepo.findByUserName(id);
        }
        //   Customer customerData = new Customer();
       // customer.setCustomerId(customerRequest.getCustomerId());
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setRole(customerRequest.getRole());
        customer.setUserName(customerRequest.getUserName());
        customer.setPassword(customerRequest.getPassword());
        Customer updatedCustomer = customerRepo.saveAndFlush(customer);
        return convertedToCustomerDto(updatedCustomer);
    }

    public String deleteCustomer(String id) {
        Customer customer ;
        if (id.contains("@")) {
            System.out.println("this is deleted by email");
            customer = customerRepo.findByEmail(id);
        } else {
            System.out.println("this is deleted by username");
            customer = customerRepo.findByUserName(id);
        }
        customerRepo.delete(customer);
        return id +" deleted successfully";
    }
}
