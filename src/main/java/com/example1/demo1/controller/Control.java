package com.example1.demo1.controller;

import com.example1.demo1.Product.ResponseObject;
import com.example1.demo1.Product.modeProduct;
import com.example1.demo1.repositori.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class Control {
    @Autowired
    Repository repository;
    @GetMapping("")
    List<modeProduct> getAllProducts(){
        return repository.findAll();

    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<modeProduct> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Query product successfully",foundProduct));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("false","Cannot find product with id = "+id,""));
        }
    }
//    Optional<modeProduct> findById(@PathVariable Long id) {
//        return repository.findById(id);
//    }
}
