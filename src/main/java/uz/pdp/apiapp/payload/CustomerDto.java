package uz.pdp.apiapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "PhoneNumber bush bulmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "FullName bush bulmasligi kerak")
    private String fullName;

    @NotNull(message = "Address bush bulmasligi kerak")
    private String address;
}
