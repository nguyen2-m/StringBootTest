package com.example1.demo1.controller;

import com.example1.demo1.Product.modeProduct;
import com.example1.demo1.repositori.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class Control {
    @Autowired
    Repository repository;
    @GetMapping("")
    List<modeProduct> getAllProducts(){
        return repository.findAll();

    }
}
