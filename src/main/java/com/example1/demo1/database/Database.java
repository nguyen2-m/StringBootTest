package com.example1.demo1.database;

import com.example1.demo1.Product.modeProduct;
import com.example1.demo1.repositori.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(Repository repository){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception{
                modeProduct modeA=new modeProduct(1,"mac",2020,200000.0,"");
                modeProduct modeB=new modeProduct(2,"macbook",2021,400000.0,"");

                logger.info("insert data : " + repository.save(modeA));
                logger.info("insert data : " + repository.save(modeB));




            }
        };
    }
}
