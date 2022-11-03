package uz.pdp.apiapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apiapp.entity.Customer;
                                                                                   //shunaqa interface ochish kerag
//@RepositoryRestResource(path = "customer",collectionResourceDescription = "list",excerptProjection = CustomCustomer.class)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

}
