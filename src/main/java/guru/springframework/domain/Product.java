package guru.springframework.domain;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Integer id;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
