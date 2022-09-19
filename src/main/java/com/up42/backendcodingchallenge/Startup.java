package com.up42.backendcodingchallenge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Startup {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }
}
