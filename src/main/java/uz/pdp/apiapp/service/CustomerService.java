package uz.pdp.apiapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.apiapp.entity.Customer;
import uz.pdp.apiapp.payload.ApiResponse;
import uz.pdp.apiapp.payload.CustomerDto;
import uz.pdp.apiapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage.getContent();
    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("Bunday mijoz mavjud", false);
        }
        Customer customer = new Customer();
        customer.setFullName(customerDto.getFullName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz saqlandi", true);
    }

    public ApiResponse editCustomer(Integer id, CustomerDto customerDto) {
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id);
        if (existsByPhoneNumber) {
            return new ApiResponse("Bunday telefon raqamli mijoz mavjud", false);
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            return new ApiResponse("Bunday mijoz mavjud emas", true);
        }
        Customer customer = optionalCustomer.get();
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setFullName(customerDto.getFullName());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz tahrirlandi", true);
    }

    public ApiResponse deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
        return new ApiResponse("Mijoz uchirildi", true);
    }


}
