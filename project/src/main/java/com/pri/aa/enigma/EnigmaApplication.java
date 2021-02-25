package com.pri.aa.enigma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnigmaApplication {

    static final Logger logger = LoggerFactory.getLogger(EnigmaApplication.class);

    public static void main(String[] args) {
        logger.info("Before starting application");
        SpringApplication.run(EnigmaApplication.class, args);
        logger.debug("Starting enigma app in debug with {} arguments", args.length);
        logger.info("Starting enigma app with {} arguments.", args.length);
    }

}
