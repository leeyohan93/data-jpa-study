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

    private String title;

    private Money price;

    public Product(String title, Money price) {
        this.title = title;
        this.price = price;
    }
}
