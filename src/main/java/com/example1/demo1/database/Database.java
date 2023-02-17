package com.example1.demo1.database;

import com.example1.demo1.Product.modeProduct;
import com.example1.demo1.repositori.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(Repository repository){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception{
                modeProduct modeA=new modeProduct(1L,"mac",2020,200000.0,"");
                modeProduct modeB=new modeProduct(2L,"macbook",2021,400000.0,"");

                System.out.println("insert data : " + repository.save(modeA));
                System.out.println("insert data : " + repository.save(modeB));




            }
        };
    }
}
