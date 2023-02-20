package com.example1.demo1.repositori;

import com.example1.demo1.Product.modeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<modeProduct, Long> {
    List<modeProduct>findByName(String productName);
}
