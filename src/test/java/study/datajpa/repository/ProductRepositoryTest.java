package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.datajpa.entity.Money;
import study.datajpa.entity.Product;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    EntityManager em;

    @Test
    public void attribute_converter_test() {
//        Money price = new Money(1000, "원");
//        Product product = new Product(price);
//        productRepository.save(product);
//
//        em.flush();
//        em.clear();
//
//        productRepository.findById(1L);
    }

    @Test
    public void attribute_converter_not_apply_test() {
        Product product = new Product("100원");
        productRepository.save(product);

        em.flush();
        em.clear();

        productRepository.findById(1L);
    }
}