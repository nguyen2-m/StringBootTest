package com.example1.demo1.controller;

import com.example1.demo1.Product.ResponseObject;
import com.example1.demo1.Product.modeProduct;
import com.example1.demo1.repositori.Repository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/token")
    ResponseEntity<ResponseObject> getToken() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "getToken successfully", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody modeProduct newProduct){

        List<modeProduct> foundProducts= repository.findByName(newProduct.getName().trim());
        if (foundProducts.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Product name already taken",""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Product Successfully",repository.save(newProduct))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody modeProduct newProduct, @PathVariable Long id){
        modeProduct updateProduct =  repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(newProduct);

        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Product successfully",updateProduct)
        );
    }

    @DeleteMapping("/{id}")
        ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
            boolean exists = repository.existsById(id);
            if(!exists){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Delete product successfully","")
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find product delete","")
            );
        }

}
