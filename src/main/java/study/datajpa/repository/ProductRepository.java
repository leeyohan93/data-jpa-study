package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
