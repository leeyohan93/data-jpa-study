package study.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Money;
import study.datajpa.entity.Product;
import study.datajpa.repository.ProductRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AttributeConverterTest {

    @Autowired
    ProductRepository productRepository;

    /**
     * - EqualsAndHashcode 구현 플로우
     * Money(1) 객체 생성
     * convertToDatabaseColumn(Money(1)) - String 반환
     * convertToEntityAttribute(String) - 새로운 Money(2) 객체 생성, 반환
     * Money(2).equals(Money(1))
     * insert query
     * convertToDatabaseColumn(Money(2)) - String 반환
     *
     * - EqualsAndHashcode 미 구현 플로우
     * Money(3) 객체 생성
     * convertToDatabaseColumn(Money(3)) - String 반환
     * convertToEntityAttribute(String) - 새로운 Money(4) 생성, 반
     * Money(4).equals(Money(3))
     * insert query
     * convertToDatabaseColumn(Money(4)) - String 반환
     * update query
     * convertToDatabaseColumn(Money(3)) - String 반환
     * convertToDatabaseColumn(Money(3)) - String 반환
     * convertToEntityAttribute(9888 생성)환 - 새로운 Money(5) 생성, 반환
     */

    @Test
    void save() {
        String title = "firstProduct";
        Money price = new Money(1000, "원");
        Product product = new Product(title, price);

        Product savedProduct = productRepository.save(product);
        Product searchedTitle = productRepository.findByTitle(title);

        assertThat(savedProduct.getTitle()).isEqualTo(title);
        assertThat(savedProduct.getPrice()).isEqualTo(price);

        assertThat(searchedTitle.getTitle()).isEqualTo(title);
        assertThat(searchedTitle.getPrice()).isEqualTo(price);
    }
}
