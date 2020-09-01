package guru.springframework.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}
