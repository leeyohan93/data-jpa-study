package study.datajpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Product {
    @Id
    @GeneratedValue
    private Long id;

//    private Money price;
//
//    public Product(Money price) {
//        this.price = price;
//    }

    private String price;

    public Product(String price) {
        this.price = price;
    }
}
