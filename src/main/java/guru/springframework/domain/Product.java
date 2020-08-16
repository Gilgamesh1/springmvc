package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Product {
    private Integer id;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
