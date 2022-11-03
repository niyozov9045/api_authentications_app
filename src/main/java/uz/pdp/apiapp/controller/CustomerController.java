package uz.pdp.apiapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apiapp.entity.Customer;
import uz.pdp.apiapp.payload.ApiResponse;
import uz.pdp.apiapp.payload.CustomerDto;
import uz.pdp.apiapp.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    //  LAVOZIMI BUYICHA QILADIGAN ISHI
    //    @PreAuthorize(value = "hasAnyRole('Manager','Director')")

    //  HUQUQLARI BUYICHA QILADIGAN ISHI
    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @GetMapping
    public ResponseEntity<?> getCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Customer> customers = customerService.getCustomers(page, size);
        return ResponseEntity.ok(customers);
    }

    //    @PreAuthorize(value = "hasAnyRole('Manager','Director','User')")
    @PreAuthorize(value = "hasAuthority('GET_ONE PRODUCT')")
    @GetMapping("{id}")
    public HttpEntity<?> getCustomerById(@PathVariable Integer id) {
        Customer customerById = customerService.getCustomerById(id);
        return ResponseEntity.status(customerById != null ? 200 : 409).body(customerById);
    }


    //     @PreAuthorize(value = "hasRole('Director')")
    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    //    @PreAuthorize(value = "hasRole('Director')")
    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.editCustomer(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }


    //    @PreAuthorize(value = "hasRole('Director')")
    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}
